/**
 * 
 */
package com.gsoft.framework.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gsoft.framework.security.AccountPrincipal;
import com.gsoft.framework.security.PrincipalConfig;
import com.gsoft.framework.security.UserService;
import com.gsoft.framework.util.SecurityUtils;
import com.gsoft.framework.util.StringUtils;
import com.gsoft.weixin.wxsubscrip.entity.WxSubscription;
import com.gsoft.weixin.wxsubscrip.service.WxSubscriptionManager;

/**
 * 会员页面控制器
 * @author zhyi_12
 */

@Controller
@RequestMapping("/member")
public class MemberPageController {
	
	@Autowired
	private UserService userService;//用户服务
	
	@Autowired
	private WxSubscriptionManager wxSubscriptionManager;
	
	@RequestMapping(value = "/{pageModule}/{pagePath}.html")
	public ModelAndView page(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("pageModule") String pageModule,
			@PathVariable("pagePath") String pagePath){
		
		return buildModelAndView(request,pageModule,StringUtils.arrayToDelimitedString(new String[]{pageModule,pagePath}, "/"));
	}
	
	@RequestMapping(value = "/{pageModule}/{pagePath}/{subPath}.html")
	public ModelAndView page(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("pageModule") String pageModule,
			@PathVariable("pagePath") String pagePath,
			@PathVariable("subPath") String subPath,
			@PathVariable("loginName") String loginName){
		return buildModelAndView(request,pageModule,StringUtils.arrayToDelimitedString(new String[]{pageModule,pagePath,subPath}, "/"));
	}
	
	/**
	 * 微窗授权页面
	 * @param request
	 * @param response
	 * @param pageModule
	 * @param pagePath
	 * @return
	 */
	@RequestMapping(value = "vchuang/{pageModule}/{pagePath}.html")
	public ModelAndView vchuangPage(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("pageModule") String pageModule,
			@PathVariable("pagePath") String pagePath){
		
		AccountPrincipal account = SecurityUtils.getAccount();
		String subscriptionId = null;
		
		if(account!=null&&account.getPrincipalConfig()!=null&&StringUtils.isNotEmpty(account.getPrincipalConfig().get("subscriptionId"))){
			//
			subscriptionId = account.getPrincipalConfig().get("subscriptionId");
		}else{
			//跳转到授权绑定页面
			pagePath = "auth";
		}
		
		ModelAndView model = buildModelAndView(request,"vchuang",StringUtils.arrayToDelimitedString(
				new String[]{pageModule,pagePath}, "/"));
		//设置公众号主键
		model.addObject("subscriptionId", subscriptionId);
		
		return model;
	}
	
	//微窗公众号界面
	@RequestMapping(value = "vchuang/{pageModule}/{pagePath}/{subscriptionId}.html")
	public ModelAndView vchuangSubscripPage(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("pageModule") String pageModule,
			@PathVariable("pagePath") String pagePath,
			@PathVariable("subscriptionId") String subscriptionId){
		
		ModelAndView model = buildModelAndView(request,"vchuang",StringUtils.arrayToDelimitedString(
				new String[]{pageModule,pagePath}, "/"));
		
		WxSubscription wxSubscription = wxSubscriptionManager.getWxSubscription(subscriptionId);
		
		//
		model.addObject("subscriptionId", subscriptionId);
		model.addObject("subscription", wxSubscription);
		//生成菜单html
		model.addObject("menuHtml", "1111111");
		
		
		return model;
	}
	
	/**
	 * @param request
	 * @param pageModule
	 * @param pagePath
	 * @return
	 */
	private ModelAndView buildModelAndView(HttpServletRequest request,String pageModule,String pagePath){
		AccountPrincipal account = SecurityUtils.getAccount();
//		if(account==null||!loginName.equals(account.getLoginName())){
//			//输出权限错误异常
//			throw new BusException("010101","权限不足!");
//		}
		ModelAndView model = new ModelAndView();
		model.setViewName(pageModule+"/"+pagePath);
		
		PrincipalConfig config = account.getPrincipalConfig();
		
		model.addObject("account", config);
		model.addObject("pagePath", pagePath);
		
		model.addObject("loginType", pageModule);
		
		
		return model;
	}

}
