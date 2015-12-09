/**
 * 
 */
package com.gsoft.esb.weixin.entity;

/**
 * 图文消息
 * @author zhyi_12
 *
 */
public class WxNewsMessage {

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
