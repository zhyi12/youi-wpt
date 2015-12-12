package com.gsoft.weixin.wxsubscrip.entity;

import com.gsoft.framework.security.AbstractFormUserInfo;

public class WxSubscripAccount extends AbstractFormUserInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8681365685765211972L;
	
	private String username;

	public WxSubscripAccount(WxSubscription user) {
		super(user, user.getPassword());
		this.username = user.getLoginName();
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
