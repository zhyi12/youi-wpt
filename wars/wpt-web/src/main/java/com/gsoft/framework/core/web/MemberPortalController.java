/**
 * 
 */
package com.gsoft.framework.core.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gsoft.framework.security.AccountPrincipal;
import com.gsoft.framework.security.EsbSecurityManager;
import com.gsoft.framework.util.SecurityUtils;

/**
 * @author zhyi_12
 *
 */
@Controller
@RequestMapping("/portal")
public class MemberPortalController {
	
	@Autowired
	private EsbSecurityManager esbSecurityManager;
	
	//门户通过jsonp获取用户信息
	@RequestMapping(value = "/userInfo.html")
	public @ResponseBody String  userInfo(HttpServletRequest request,
			HttpServletResponse response){
		AccountPrincipal account = SecurityUtils.getAccount();
		StringBuilder jsonpBuilder = new StringBuilder();
		
		String requestUrl = request.getRequestURL().toString();
		String requestUri = request.getServletPath();
		
		String mcUrl = requestUrl.substring(0, requestUrl.length()-requestUri.length())+"/member/vtui/index.html";
		
		if(account!=null){
			jsonpBuilder.append("$.youi.serverConfig.authorization='"+esbSecurityManager.encryptSecurityInfo(null)+"';")
				.append("$('#user_info:first').html('"+"<a target=\"_blank\" href=\""+mcUrl+"\">")
				.append(account.getLoginName()+"</a>"+"');");
		}
		
		return jsonpBuilder.toString();
	}
	
	@RequestMapping("/logout.html")
	public ModelAndView logout(
    		HttpServletRequest request,
    		HttpServletResponse response){
		//退出系统
		org.apache.shiro.SecurityUtils.getSubject().logout();
		String redirect = request.getParameter("redirect");
		try {
			WebUtils.issueRedirect(request, response, redirect);
		} catch (IOException e) {
//			e.printStackTrace();
		}
		return null;
	}
}
