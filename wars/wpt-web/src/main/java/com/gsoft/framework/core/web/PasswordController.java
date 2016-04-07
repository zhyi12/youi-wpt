/**
 * 
 */
package com.gsoft.framework.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gsoft.framework.core.Constants;
import com.gsoft.framework.core.web.view.DataModelAndView;
import com.gsoft.framework.core.web.view.Message;
import com.gsoft.framework.security.AccountPrincipal;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.UserService;
import com.gsoft.framework.util.SecurityUtils;

/**
 * @author zhyi_12
 *
 */
@Controller
public class PasswordController {

	@Autowired(required=false)
	private UserService userService;
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/modifyPassword.json")
	public ModelAndView modifyPassword(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("password") String password,
    		@RequestParam("confirmPassword") String confirmPassword,
    		@RequestParam("oldPassword") String oldPassword){
		
		AccountPrincipal account = SecurityUtils.getAccount();
		
		if(account instanceof IUser){
			userService.modifyPassword((IUser)account, password, confirmPassword, oldPassword);
		}else{
			return new DataModelAndView(new Message(Constants.ACCESS_DENIED_CODE, "请先登录系统"));
		}
		
		return new DataModelAndView(new Message(Constants.SUCCESS_CODE, "密码修改成功"));
	}
	
	/**
	 * 密码重置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/resetPassword.json")
	public ModelAndView resetPassword(
    		HttpServletRequest request,
    		HttpServletResponse response){
		
		AccountPrincipal account = SecurityUtils.getAccount();
		
		if(account instanceof IUser){
			userService.resetPassword((IUser)account);
		}else{
			return new DataModelAndView(new Message(Constants.ACCESS_DENIED_CODE, "请先登录系统"));
		}
		
		return new DataModelAndView(new Message(Constants.SUCCESS_CODE, "密码重置成功"));
	}
}
