/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.esb.weixin.reqdata.WxData;

/**
 * 图文消息
 * @author zhyi_12
 *
 */
public class WxNewsMessage implements WxData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3309740973192019481L;

	private String touser;
	
	private String msgtype = "news";
	
	private WxNews news;

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

	public WxNews getNews() {
		return news;
	}

	public void setNews(WxNews news) {
		this.news = news;
	}
	
	
}
