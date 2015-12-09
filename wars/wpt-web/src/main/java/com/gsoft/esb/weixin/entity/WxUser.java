/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.framework.core.dataobj.Domain;

/**
 * @author zhyi_12
 *
 */
public class WxUser implements Domain{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2932743233473540047L;

	private String openid;
	
	private String nickname;
	
	private int sex;
	
	private int subscribe;
	
	private String language;
	
	private String city;
	
	private String province;
	
	private String country;
	
	private String headimgurl;
	
	private String unionid;
	
	private String remark;
	
	private int groupid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	
}
