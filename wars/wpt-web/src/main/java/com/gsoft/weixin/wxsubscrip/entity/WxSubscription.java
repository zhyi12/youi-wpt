/**
 *
 */
package com.gsoft.weixin.wxsubscrip.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.Length;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.security.IUser;
import com.gsoft.framework.security.IdUser;
import com.gsoft.framework.security.PrincipalConfig;
import com.gsoft.weixin.wxsubscrip.Constants;
/**
 * 实体: 微信公众号用户
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_SUBSCRIPTION")
public class WxSubscription implements Domain,IUser,IdUser{
	
	private static final long serialVersionUID = 2043097310402114470L;
	

	@Column(name = "USER_CAPTION")
	@Length(max=100)
	private String userCaption;//用户描述

	@Column(name = "PASSWORD")
	@Length(max=40)
	private String password;//登录密码

	@Column(name = "APP_SECRET")
	@Length(max=40)
	private String appSecret;//开发者密钥

	@Column(name = "APP_ID")
	@Length(max=40)
	private String appId;//开发者ID

	@Column(name = "SUBSCRIPTION_CAPTION")
	@Length(max=100)
	private String subscriptionCaption;//公众号描述

	@Column(name = "LOGIN_NAME")
	@Length(max=20)
	private String loginName;//登录用户
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "USER_ID")
	@Length(max=36)
	private String userId;//公众号主键
	
	public String getUserCaption(){
		return this.userCaption;
	}
	
	public void setUserCaption(String userCaption){
		this.userCaption = userCaption;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getAppSecret(){
		return this.appSecret;
	}
	
	public void setAppSecret(String appSecret){
		this.appSecret = appSecret;
	}
	public String getAppId(){
		return this.appId;
	}
	
	public void setAppId(String appId){
		this.appId = appId;
	}
	public String getSubscriptionCaption(){
		return this.subscriptionCaption;
	}
	
	public void setSubscriptionCaption(String subscriptionCaption){
		this.subscriptionCaption = subscriptionCaption;
	}
	public String getLoginName(){
		return this.loginName;
	}
	
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userCaption == null) ? 0 : userCaption.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((appSecret == null) ? 0 : appSecret.hashCode());
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((subscriptionCaption == null) ? 0 : subscriptionCaption.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final WxSubscription other = (WxSubscription) obj;
		if (userCaption == null) {
			if (other.userCaption != null)
				return false;
		} else if (!userCaption.equals(other.userCaption))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (appSecret == null) {
			if (other.appSecret != null)
				return false;
		} else if (!appSecret.equals(other.appSecret))
			return false;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (subscriptionCaption == null) {
			if (other.subscriptionCaption != null)
				return false;
		} else if (!subscriptionCaption.equals(other.subscriptionCaption))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	public String toString(){
		return this.subscriptionCaption;
	}

	@Override
	public PrincipalConfig getPrincipalConfig() {
		PrincipalConfig principalConfig = new PrincipalConfig();
		principalConfig.put("appId", this.getAppId());
		principalConfig.put("appSecret", this.getAppSecret());
		return principalConfig;
	}

	@Override
	public List<String> roleIds() {
		List<String> roles = new ArrayList<String>();
		roles.add(Constants.ROLE_SUBSCRIPTION);
		return roles;
	}
}