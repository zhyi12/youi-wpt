/**
 * 
 */
package com.gsoft.esb.weixin.security;

import com.gsoft.framework.security.IRealmUserToken;

/**
 * @author zhyi_12
 */
public class WxOAuthToken implements IRealmUserToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2777604109224235472L;
	
	private String code;//微信用户授权码
	
	private String name;//公众号登录名

	public WxOAuthToken(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public Object getPrincipal() {
		return "";
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUsername() {
		return "";
	}

}
