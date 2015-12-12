/**
 * 
 */
package com.gsoft.framework.core.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gsoft.framework.core.Constants;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.core.dataobj.tree.HtmlTreeNode;
import com.gsoft.framework.core.dataobj.tree.TreeNode;
import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.web.view.DataModelAndView;
import com.gsoft.framework.core.web.view.Message;
import com.gsoft.framework.security.AccountPrincipal;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.IUserAdapter;
import com.gsoft.framework.security.UserService;
import com.gsoft.framework.util.SecurityUtils;

/**
 * 登录账户相关
 * @author zhyi_12
 *
 */
@Controller
public class LoginController {
	
	@Autowired(required=false)
	private UserService userService;

	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/login.html")
	public ModelAndView login(
    		HttpServletRequest request,
    		HttpServletResponse response){
		
		String userAdapterName = WebUtils.getCleanParam(request, "userAdapter");
		
		IUserAdapter userAdapter = userService.getUserAdapter(userAdapterName);
		
		String viewName = "login";
		
		if(userAdapter!=null){
			viewName +=("-"+userAdapterName);
		}
		
		return new ModelAndView(viewName);
	}
	
	/**
	 * 退出登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout.html")
	public ModelAndView logout(
    		HttpServletRequest request,
    		HttpServletResponse response){
		//退出系统
		org.apache.shiro.SecurityUtils.getSubject().logout();
		
		try {
			WebUtils.issueRedirect(request, response, "/login.html");
		} catch (IOException e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 首页，登录成功跳转页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(
    		HttpServletRequest request,
    		HttpServletResponse response){
		
		//
		AccountPrincipal account = SecurityUtils.getAccount();
		
		if(account!=null){
			if(account.roleIds().contains("ROLE_MEMBER")){
				//会员用户登录
				return new ModelAndView("redirect:cms/account/index/"+account.getLoginName()+".html");
			}else{
				return new ModelAndView("index");
			}
		}
		//非登录用户首页
		return new ModelAndView("login");
	}
	
	/**
	 * 欢迎页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchmenu.json")
	public DataModelAndView searchMenu(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("term") String term){
		
		AccountPrincipal account = SecurityUtils.getAccount();
		
		List<Record> records = new ArrayList<Record>();
		if(account!=null&&IUser.class.isAssignableFrom(account.getClass())){
			HtmlTreeNode menuTree = userService.getCachedMenuTree((IUser)account);
			HtmlTreeNodeSearch nodeSearch = new HtmlTreeNodeSearch(menuTree,term);
			records.addAll(nodeSearch.getResults());
		}
		
		return new DataModelAndView(records.toArray(new Record[records.size()]));
	}
	
	/**
	 * 欢迎页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/welcome.html")
	public ModelAndView welcome(
    		HttpServletRequest request,
    		HttpServletResponse response){
		return new ModelAndView("welcome");
	}
	
	/**
	 * 密码修改页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/modifyPassword.html")
	public ModelAndView modifyPassword(
    		HttpServletRequest request,
    		HttpServletResponse response){
		return new ModelAndView("modifyPassword");
	}
	
	/**
	 * 修改用户登录密码交易
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doModifyPassword.json")
	public DataModelAndView doModifyPassword(
    		HttpServletRequest request,
    		HttpServletResponse response){
//		AccountPrincipal currentAccount = SecurityUtils.getAccount();
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String oldPassword = request.getParameter("oldPassword");
		
		if(StringUtils.isEmpty(oldPassword)){
			throw new BusException("旧密码不能为空！");
		}
		if(StringUtils.isEmpty(password)){
			throw new BusException("新密码不能为空！");
		}
		if(StringUtils.isEmpty(confirmPassword)){
			throw new BusException("确认密码不能为空！");
		}
		
		if(!password.equals(confirmPassword)){
			throw new BusException("两次输入的密码不一致！");
		}
		
//		userService.modifyPassword(currentAccount.getLoginName(), password, confirmPassword, oldPassword);
		
		
		return new DataModelAndView(new Message(Constants.SUCCESS_CODE,"密码修改成功"));
	}
	
	private class HtmlTreeNodeSearch{
		
		private String term;
		
		List<Record> records = new ArrayList<Record>();

		public HtmlTreeNodeSearch(HtmlTreeNode tree,String term) {
			this.term = term;
			doSearchNode(tree);
			//排序
			Collections.sort(records, new Comparator<Record>(){
				@Override
				public int compare(Record o1, Record o2) {
					return o1.get("value").toString().compareTo(o2.get("value").toString());
				}
			});
		}

		private void doSearchNode(TreeNode treeNode) {
			if(treeNode.getText().contains(term)||treeNode.getId().contains(term)){
				if(!StringUtils.isEmpty(treeNode.getHref())){
					Record record = new Record();
					record.put("label", treeNode.getId()+" - "+treeNode.getText());
					record.put("value", treeNode.getId());
					record.put("propertyValue", treeNode.getCode());
					records.add(record);
				}
			}
			
			List<TreeNode> children = treeNode.getChildren();
			
			if(children!=null){
				for(TreeNode childNode:children){
					doSearchNode(childNode);
				}
			}
		}

		public Collection<? extends Record> getResults() {
			return records;
		}
	}
}
