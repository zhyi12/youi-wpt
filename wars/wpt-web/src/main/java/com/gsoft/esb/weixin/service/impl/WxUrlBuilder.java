/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.MessageHeaders;
import org.springframework.stereotype.Component;

import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.EsbConstants;

/**
 * @author zhyi_12
 *
 */
@Component
public class WxUrlBuilder {
	
	@Value("${wx.baseUrl}")
	private String wxBaseUrl;
	
	private Map<String,String> httpUrls = new HashMap<String,String>();
	
	public void setHttpUrls(Map<String, String> httpUrls) {
		this.httpUrls = httpUrls;
	}

	public String buildUrl(MessageHeaders headers,Object payload){
		String service = headers.get(EsbConstants.HEADER_SERVICE, String.class);
		String serviceUri = httpUrls.get(service);
		
		return "https://api.weixin.qq.com/cgi-bin"+serviceUri;
	}
	
	public String getRetType(MessageHeaders headers,Object payload){
		//默认使用record类型
		
		return Record.class.getName();
	}
}
