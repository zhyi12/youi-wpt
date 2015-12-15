/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import java.util.List;

import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.PrincipalConfig;

/**
 * @author zhyi_12
 *
 */
public class WxUser implements IUser {
	
	private String loginName;//登录名
	
	private String nickname;//显示名
	
	private List<String> roleIds;//系统角色
	
	private PrincipalConfig principalConfig;

	/* (non-Javadoc)
	 * @see com.gsoft.framework.security.AccountPrincipal#getPrincipalConfig()
	 */
	@Override
	public PrincipalConfig getPrincipalConfig() {
		return principalConfig==null?new PrincipalConfig():principalConfig;
	}

	public void setPrincipalConfig(PrincipalConfig principalConfig) {
		this.principalConfig = principalConfig;
	}

	/* (non-Javadoc)
	 * @see com.gsoft.framework.security.IUser#getLoginName()
	 */
	@Override
	public String getLoginName() {
		return loginName;
	}

	/* (non-Javadoc)
	 * @see com.gsoft.framework.security.IUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return "";
	}

	/* (non-Javadoc)
	 * @see com.gsoft.framework.security.IUser#roleIds()
	 */
	@Override
	public List<String> roleIds() {
		return roleIds;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return nickname;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	
}
