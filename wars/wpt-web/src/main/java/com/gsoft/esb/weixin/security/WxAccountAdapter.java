/**
 * 
 */
package com.gsoft.esb.weixin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.esb.weixin.entity.WxUser;
import com.gsoft.esb.weixin.service.WxServiceFactory;
import com.gsoft.esb.weixin.service.WxSubscriptionFinder;
import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.core.dataobj.tree.TreeNode;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.web.menu.IMenu;
import com.gsoft.framework.security.IAgency;
import com.gsoft.framework.security.IRealmUserInfo;
import com.gsoft.framework.security.IRealmUserToken;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.IUserAdapter;
import com.gsoft.framework.security.fuc.entity.Menu;
import com.gsoft.framework.security.fuc.service.MenuManager;
import com.gsoft.framework.util.ConditionUtils;

/**
 * @author zhyi_12
 *
 */
@Service
public class WxAccountAdapter implements IUserAdapter<WxUser,WxOAuthToken>{

	@Autowired
	private WxServiceFactory wxServiceFactory;
	
	@Autowired(required=false)
	private MenuManager menuManager;
	
	@Autowired(required=false)
	private WxSubscriptionFinder wxSubscriptionFinder;
	
	@Autowired(required=false)
	private WxUserFinder wxUserFinder;
	
	@Override
	public List<String> getAccountMenus(WxUser wxUser) {
		if(menuManager==null||wxUser==null||wxUser.roleIds()==null||wxUser.roleIds().size()==0){
			return new ArrayList<String>();
		}
		String roleId = wxUser.roleIds().get(0);
		return menuManager.getMenuIdsByRole(roleId);
	}

	@Override
	public List<IAgency> getAgencyByParent(String agency) {
		return null;
	}

	@Override
	public TreeNode getAgencyTree() {
		return null;
	}

	@Override
	public List<IMenu> getProviderMenus() {
		List<IMenu> providerMenus = new ArrayList<IMenu>();
		if(menuManager!=null){
			Collection<Order> orders = new ArrayList<Order>();
			orders.add(ConditionUtils.getOrder("menuNum", true));
			List<Menu> menus = menuManager.getMenus(null,orders );
			for(Menu menu:menus){
				providerMenus.add(menu);
			}
		}
		return providerMenus;
	}

	@Override
	public IRealmUserInfo getRealmUserInfo(WxOAuthToken wxOAuthToken) {
		//
		String code = wxOAuthToken.getCode();//微信授权码
		String name = wxOAuthToken.getName();//微信公众号平台登录名
		
		WxUser wxUser = null;
		
		if(wxSubscriptionFinder!=null&&wxServiceFactory!=null){
			WxApp wxApp = wxSubscriptionFinder.findAppByName(name);
			Domain record = wxServiceFactory.getOAuthUserInfo(wxApp,code);
			
			Object openid = null;
			if(record instanceof Record){
				openid = ((Record) record).get("openid");
			}
			
			if(openid==null){//测试用户
				openid = "o34IKuL36RFCABfsdquh9gG3LY6Q";
			}
			
			if(wxUserFinder!=null&&openid!=null){
				wxUser = wxUserFinder.findWxUserByOpenid(openid.toString());
			}
			
			if(wxUser==null&&openid!=null){
				//远程获取 获取微信用户信息
				wxUser = new WxUser();
				wxUser.setLoginName(openid.toString());
				wxUser.setNickname(openid.toString());
			}
		}
		
		WxAccount wxAccount = new WxAccount(wxUser);
		return wxAccount;
	}

	@Override
	public IRealmUserInfo getRealmUserInfo(WxUser wxUser) {
		return new WxAccount(wxUser);
	}

	@Override
	public boolean supports(IRealmUserToken token) {
		return token!=null&&WxOAuthToken.class.isAssignableFrom(token.getClass());
	}

	@Override
	public boolean supports(IUser user) {
		return user!=null&&WxUser.class.isAssignableFrom(user.getClass());
	}

}
