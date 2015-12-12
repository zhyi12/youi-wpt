/**
 * 代码声明
 */
package com.gsoft.weixin.wxsubscrip.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsoft.framework.core.dataobj.tree.TreeNode;
import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
//import com.gsoft.framework.core.orm.ConditionFactory;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;
import com.gsoft.framework.core.service.impl.BaseManagerImpl;
import com.gsoft.framework.core.web.menu.IMenu;
import com.gsoft.framework.esb.annotation.ConditionCollection;
import com.gsoft.framework.esb.annotation.EsbServiceMapping;
import com.gsoft.framework.esb.annotation.OrderCollection;
import com.gsoft.framework.esb.annotation.ServiceParam;
import com.gsoft.framework.security.DefaultLoginFormToken;
import com.gsoft.framework.security.IAgency;
import com.gsoft.framework.security.IRealmUserInfo;
import com.gsoft.framework.security.IRealmUserToken;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.IUserAdapter;
import com.gsoft.framework.security.agt.service.UserLoginService;
import com.gsoft.framework.security.fuc.entity.Menu;
import com.gsoft.framework.security.fuc.service.MenuManager;
import com.gsoft.framework.util.ConditionUtils;
import com.gsoft.weixin.wxsubscrip.Constants;
import com.gsoft.weixin.wxsubscrip.dao.WxSubscriptionDao;
import com.gsoft.weixin.wxsubscrip.entity.WxSubscripAccount;
import com.gsoft.weixin.wxsubscrip.entity.WxSubscription;
import com.gsoft.weixin.wxsubscrip.service.WxSubscriptionManager;

@Service("wxSubscriptionManager")
@Transactional
public class WxSubscriptionManagerImpl extends BaseManagerImpl implements WxSubscriptionManager
	,UserLoginService<WxSubscription>
	,IUserAdapter<WxSubscription,DefaultLoginFormToken>{
	@Autowired
	private WxSubscriptionDao wxSubscriptionDao;
	
	
	@Autowired
	private MenuManager menuManager;
	
    /**
     * 查询列表
     */
    public List<WxSubscription> getWxSubscriptions() throws BusException{
    	return wxSubscriptionDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxSubscription> getWxSubscriptions(
    	@ConditionCollection(domainClazz=WxSubscription.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxSubscriptionDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxSubscription getWxSubscription(@ServiceParam(name="userId") String id)  throws BusException{
    	return wxSubscriptionDao.get(id);
    }
	
	@EsbServiceMapping
	public PagerRecords getPagerWxSubscriptions(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxSubscription.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxSubscriptionDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping
    public WxSubscription saveWxSubscription(WxSubscription o) throws BusException{
//    	String wxSubscriptionId = o.getWxSubscriptionId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxSubscriptionId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxSubscriptionDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxSubscription(@ServiceParam(name="userId") String id) throws BusException{
    	wxSubscriptionDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxSubscriptions(@ServiceParam(name="userId") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxSubscription(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxSubscription(@ServiceParam(name="userId") String id)  throws BusException{
		return wxSubscriptionDao.exists(id);
	}
    
    public boolean exsitWxSubscription(String propertyName,Object value) throws BusException{
		return wxSubscriptionDao.exists(propertyName,value);
	}
    
	@Override
	public List<String> getAccountMenus(WxSubscription wxSubscription) {
		return menuManager.getMenuIdsByRole(Constants.ROLE_SUBSCRIPTION);
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
		Collection<Order> orders = new ArrayList<Order>();
		orders.add(ConditionUtils.getOrder("menuNum", true));
		List<Menu> menus = menuManager.getMenus(null,orders );
		List<IMenu> providerMenus = new ArrayList<IMenu>();
		for(Menu menu:menus){
			providerMenus.add(menu);
		}
		return providerMenus;
	}
	
	@Override
	public IRealmUserInfo getRealmUserInfo(DefaultLoginFormToken loginFormToken) {
		return getSubscriptionByLoginName(loginFormToken.getUsername());
	}
	
	private IRealmUserInfo getSubscriptionByLoginName(String loginName) {
		WxSubscription wxSubscription = wxSubscriptionDao.getObjectByUniqueProperty("loginName", loginName);
		return new WxSubscripAccount(wxSubscription);
	}
	
	
	@Override
	public IRealmUserInfo getRealmUserInfo(WxSubscription wxSubscription) {
		WxSubscripAccount account = new WxSubscripAccount(wxSubscription);
		return account;
	}
	
	@Override
	public boolean supports(IRealmUserToken token) {
		WxSubscription wxSubscription = this.getLoginUser(token.getUsername());
		return DefaultLoginFormToken.class.isAssignableFrom(token.getClass())&&wxSubscription!=null;
	}
	
	@Override
	public boolean supports(IUser user) {
		return WxSubscription.class.isAssignableFrom(user.getClass());
	}
	
	
	@Override
	public WxSubscription getLoginUser(String loginName) {
		return wxSubscriptionDao.getObjectByUniqueProperty("loginName", loginName);
	}
	
}
