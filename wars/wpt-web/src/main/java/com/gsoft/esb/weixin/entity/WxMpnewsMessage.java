/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.esb.weixin.reqdata.WxData;


/**
 * @author zhyi_12
 *{
   "filter":{
      "is_to_all":false
      "group_id":"2"
   },
   "mpnews":{
      "media_id":"123dsdajkasd231jhksad"
   },
    "msgtype":"mpnews"
}
 */
public class WxMpnewsMessage implements WxData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6564701919823414164L;

	private WxFilter filter = new WxFilter();
	
	private WxMpnews mpnews;
	
	private String msgtype = "mpnews";
	
	

	public WxMpnewsMessage(String media_id) {
		super();
		this.mpnews = new WxMpnews(media_id);
	}

	public WxFilter getFilter() {
		return filter;
	}

	public void setFilter(WxFilter filter) {
		this.filter = filter;
	}



	public WxMpnews getMpnews() {
		return mpnews;
	}

	public void setMpnews(WxMpnews mpnews) {
		this.mpnews = mpnews;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	
}
