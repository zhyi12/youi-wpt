/**
 * 
 */
package com.gsoft.esb.weixin.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gsoft.framework.security.AccountPrincipal;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.UserService;
import com.gsoft.framework.util.SecurityUtils;

/**
 * @author zhyi_12
 *
 */
@Component("wxOAuthRealm")
public class WxOAuthRealm extends AuthorizingRealm {
	
	@Autowired(required=false)
	private UserService userService;
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		AccountPrincipal account = SecurityUtils.getAccount();
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole("ROLE_USER");
		
		if(userService!=null){
			userService.getCachedMenuTree((IUser)account);
		}
		
		return simpleAuthorizationInfo;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		WxOAuthToken wxOAuthToken = (WxOAuthToken)token;
		
		return userService.getRealmUserInfo(wxOAuthToken, true);
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token!=null&&WxOAuthToken.class.isAssignableFrom(token.getClass());
	}

}
