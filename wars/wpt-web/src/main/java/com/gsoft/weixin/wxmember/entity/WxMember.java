/**
 *
 */
package com.gsoft.weixin.wxmember.entity;

import javax.persistence.*;

import org.hibernate.validator.*;

import com.gsoft.framework.core.dataobj.Domain;
/**
 * 实体: 微信会员
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_MEMBER")
public class WxMember implements Domain{
	
	private static final long serialVersionUID = 9063116413553389129L;
	

	@Column(name = "REMARK")
	@Length(max=20)
	private String remark;//REMARK

	@Column(name = "CITY")
	@Length(max=40)
	private String city;//CITY

	@Column(name = "NICKNAME")
	@Length(max=100)
	private String nickname;//NICKNAME

	@Column(name = "COUNTRY")
	@Length(max=40)
	private String country;//COUNTRY

	@Column(name = "PROVINCE")
	@Length(max=40)
	private String province;//PROVINCE

	@Column(name = "SEX")
	@Length(max=3)
	private String sex;//SEX

	@Column(name = "UNIONID")
	@Length(max=100)
	private String unionid;//UNIONID

	@Column(name = "SUBSCRIBE")
	@Length(max=20)
	private String subscribe;//SUBSCRIBE

	@Column(name = "LANGUAGE")
	@Length(max=100)
	private String language;//LANGUAGE
	
	@Id
	@Column(name = "OPENID")
	@Length(max=36)
	private String openid;//OPENID

	@Column(name = "HEADIMGURL")
	@Length(max=1024)
	private String headimgurl;//HEADIMGURL
	
	
	@Column(name = "USER_ID")
	@Length(max=36)
	private String userId;//公众号主键
	
	public String getRemark(){
		return this.remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getCity(){
		return this.city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	public String getNickname(){
		return this.nickname;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getCountry(){
		return this.country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	public String getProvince(){
		return this.province;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	public String getSex(){
		return this.sex;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	public String getUnionid(){
		return this.unionid;
	}
	
	public void setUnionid(String unionid){
		this.unionid = unionid;
	}
	public String getSubscribe(){
		return this.subscribe;
	}
	
	public void setSubscribe(String subscribe){
		this.subscribe = subscribe;
	}
	public String getLanguage(){
		return this.language;
	}
	
	public void setLanguage(String language){
		this.language = language;
	}
	public String getOpenid(){
		return this.openid;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public String getHeadimgurl(){
		return this.headimgurl;
	}
	
	public void setHeadimgurl(String headimgurl){
		this.headimgurl = headimgurl;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((unionid == null) ? 0 : unionid.hashCode());
		result = prime * result + ((subscribe == null) ? 0 : subscribe.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		result = prime * result + ((headimgurl == null) ? 0 : headimgurl.hashCode());
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
		final WxMember other = (WxMember) obj;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (unionid == null) {
			if (other.unionid != null)
				return false;
		} else if (!unionid.equals(other.unionid))
			return false;
		if (subscribe == null) {
			if (other.subscribe != null)
				return false;
		} else if (!subscribe.equals(other.subscribe))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		if (headimgurl == null) {
			if (other.headimgurl != null)
				return false;
		} else if (!headimgurl.equals(other.headimgurl))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}