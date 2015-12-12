/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.integration.MessageHeaders;

import com.gsoft.esb.weixin.data.WxResult;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.EsbConstants;

/**
 * @author zhyi_12
 *
 */
//@Component
public class WxUrlBuilder implements ApplicationContextAware {
	
	@Value("${wx.baseUrl}")
	private String wxBaseUrl;
	
	private Map<String,String> httpGetUrls = new HashMap<String,String>();
	
	private Map<String,String> httpPostUrls = new HashMap<String,String>();
	
	private Map<String, WxResult> wxResults;

	public void setHttpGetUrls(Map<String, String> httpGetUrls) {
		this.httpGetUrls = httpGetUrls;
	}

	public void setHttpPostUrls(Map<String, String> httpPostUrls) {
		this.httpPostUrls = httpPostUrls;
	}

	public void setWxBaseUrl(String wxBaseUrl) {
		this.wxBaseUrl = wxBaseUrl;
	}

	public String buildUrl(MessageHeaders headers,Object payload){
		String service = headers.get(EsbConstants.HEADER_SERVICE, String.class);
		String serviceUri = "";
		
		if(httpGetUrls.containsKey(service)){
			serviceUri = httpGetUrls.get(service);
		}else if(httpPostUrls.containsKey(service)){
			serviceUri = httpPostUrls.get(service);
		}
		
		return "https://api.weixin.qq.com/cgi-bin"+serviceUri;
	}
	
	public String getRetType(MessageHeaders headers,Object payload){
		String service = headers.get(EsbConstants.HEADER_SERVICE, String.class);
		if(wxResults!=null){
			String beanName = "wxServiceResult."+service;
			if(wxResults.containsKey(beanName)){
				return wxResults.get(beanName).getClass().getName();
			}
		}
		//默认使用record类型
		return Record.class.getName();
	}
	
	public String getHttpMethod(MessageHeaders headers){
		String service = headers.get(EsbConstants.HEADER_SERVICE, String.class);
		if(httpGetUrls.containsKey(service)){
			return "GET";
		}
		return "POST";
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if(wxResults==null){
			wxResults = BeanFactoryUtils.beansOfTypeIncludingAncestors(context,
					WxResult.class, true, false);
		}
	}
}
