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

import com.gsoft.framework.core.dataobj.tree.HtmlTreeNode;
import com.gsoft.framework.core.dataobj.tree.TreeNode;
import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.security.AccountPrincipal;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.UserService;
import com.gsoft.framework.util.SecurityUtils;
import com.gsoft.framework.util.StringUtils;

/**
 * @author zhyi_12
 */

@Controller
@RequestMapping("/cms")
public class CmsController {
	
	@Autowired
	private UserService userService;//用户服务
	
	//@PathVariable("subfolder") String subfolder
	@RequestMapping(value = "/{pageModule}/{pagePath}/{loginName}.html")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("pageModule") String pageModule,
			@PathVariable("pagePath") String pagePath,
			@PathVariable("loginName") String loginName){
		
		return buildModelAndView(request,pageModule,StringUtils.arrayToDelimitedString(new String[]{pageModule,pagePath}, "/"),loginName);
	}
	
	@RequestMapping(value = "/{pageModule}/{pagePath}/{subPath}/{loginName}.html")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("pageModule") String pageModule,
			@PathVariable("pagePath") String pagePath,
			@PathVariable("subPath") String subPath,
			@PathVariable("loginName") String loginName){
		
		return buildModelAndView(request,pageModule,StringUtils.arrayToDelimitedString(new String[]{pageModule,pagePath,subPath}, "/"),loginName);
	}
	
	private ModelAndView buildModelAndView(HttpServletRequest request,String pageModule,String pagePath,String loginName){
		AccountPrincipal account = SecurityUtils.getAccount();
		if(account==null||!loginName.equals(account.getLoginName())){
			//输出权限错误异常
			throw new BusException("010101","权限不足!");
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("cms/"+pagePath);
		model.addObject("account", account);
		model.addObject("pagePath", pagePath);
		
		if(account!=null&&account instanceof IUser){
			String contextPath = request.getContextPath();
			//用户菜单树
			HtmlTreeNode menuTree = userService.getCachedMenuTree((IUser)account);
			if(menuTree!=null){
				for(TreeNode moduleNode:menuTree.getChildren()){
					//根据menu配置的 group属性生成当前模块的菜单
					if(pageModule.equals(((HtmlTreeNode)moduleNode).getGroup())){
						//TODO 根据项目实际情况重新画菜单html
						String sysMenuHtml =  moduleNode.toString();
						
						sysMenuHtml = sysMenuHtml.replaceAll("\\{contextPath\\}", contextPath)
							.replaceAll("\\{loginName\\}", account.getLoginName());
						
						model.addObject("sysMenuHtml",sysMenuHtml);
						break;
					}
				}
			}
		}
		
		return model;
	}

}
