/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.esb.weixin.reqdata.WxData;

/**
 * @author zhyi_12
 *
 */
public class WxUpdateGroupMember implements WxData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -295949133784671070L;

	private String openid;
	
	private String to_groupid;

	public WxUpdateGroupMember(String openid, String to_groupid) {
		super();
		this.openid = openid;
		this.to_groupid = to_groupid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(String to_groupid) {
		this.to_groupid = to_groupid;
	}
	
	
}
