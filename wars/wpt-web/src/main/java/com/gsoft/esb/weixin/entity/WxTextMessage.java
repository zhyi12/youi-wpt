/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.esb.weixin.reqdata.WxData;

/**
 * @author zhyi_12
 *
 */
public class WxTextMessage implements WxData{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6544183748082737668L;

	private String touser;
	
	private String msgtype = "text";
	
	private WxText text;
	

	public WxTextMessage(String touser, String text) {
		super();
		this.touser = touser;
		this.text = new WxText(text);
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public WxText getText() {
		return text;
	}

	public void setText(WxText text) {
		this.text = text;
	}
	
	
}
