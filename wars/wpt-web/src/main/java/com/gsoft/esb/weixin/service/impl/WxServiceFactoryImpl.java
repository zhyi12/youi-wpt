/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.esb.weixin.service.WxServiceFactory;
import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.esb.EsbConstants;
import com.gsoft.framework.esb.data.ReqContext;
import com.gsoft.framework.esb.data.ResContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxServiceFactory")
public class WxServiceFactoryImpl implements WxServiceFactory{
	
	@Autowired
	private MessagingTemplate messageTemplate;
	
	public static final String WX_SERVICE_GROUP_HTTP = "wxHttpServices";
	public static final String WX_SERVICE_GROUP_UPLOAD = "wxUploadServices";

	
	public Object doService(WxApp wxApp,String groupName,String serviceName,Map<String, String> headers,ReqContext<Object> req){
		if(req==null){
			req = new ReqContext<Object>();
		}
		
		if(headers==null){
			headers = new HashMap<String,String>();
		}
		
		headers.put(EsbConstants.HEADER_SERVICE_GROUP, groupName);
		headers.put(EsbConstants.HEADER_SERVICE, serviceName);
		
		req.add(EsbConstants.PUB_PARAM_PREFIX+"appId", wxApp.getAppid());
		req.add(EsbConstants.PUB_PARAM_PREFIX+"appSecret", wxApp.getSecret());
		
		Message<?> requestMessage = MessageBuilder.withPayload(req).copyHeaders(headers).build();
		Message<?> results = messageTemplate.sendAndReceive("weixinAdapter", requestMessage );
		
		return results.getPayload();
	}
	/**
	 * {
	   "access_token":"ACCESS_TOKEN",
	   "expires_in":7200,
	   "refresh_token":"REFRESH_TOKEN",
	   "openid":"OPENID",
	   "scope":"SCOPE",
	   "unionid":"o6_bmasdasdsad6_2sgVt7hMZOPfL"
	}
	 */
	@Override
	public Domain getOAuthUserInfo(WxApp wxApp, String code) {
		ReqContext<Object> req = new ReqContext<Object>();
		Map<String, String> headers = new HashMap<String,String>();
		
		headers.put("appid", wxApp.getAppid());
		headers.put("secret", wxApp.getSecret());
		headers.put("code", code);
		
		headers.put(WxRequestRouter.TOEKN_HEADER_PARAM, "66ACrehhh8cUGL0hWrHyK8OnU3mP3IojV5Nkh-DC_YFsE9tv1Z0AnmjRWrFvI2ppx741-KGC9fQPAuW8zIQ_jtUNu_pfqjEql9Pi9_-oKTkVRYgADAJFM");
		
		Object results  = this.doService(wxApp, WX_SERVICE_GROUP_HTTP, "getOAuth2Token",headers, req);
		
		if(results instanceof ResContext){
			return  ((ResContext) results).getRecord();
		}
		
		return null;
	}
}
