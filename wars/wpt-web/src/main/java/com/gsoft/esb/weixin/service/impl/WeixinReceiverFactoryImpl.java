/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.esb.weixin.service.WxSubscriptionFinder;
import com.gsoft.framework.security.EsbSecurityManager;
import com.gsoft.framework.util.DESUtils;

/**
 * @author zhyi_12
 *
 */
@Component("weixinReceiverFactory")
public class WeixinReceiverFactoryImpl {
	
	private static final Log logger = LogFactory.getLog(WeixinReceiverFactoryImpl.class);
	
	@Autowired(required=false)
	private WxSubscriptionFinder wxSubscriptionFinder;
	
	@Autowired
	private EsbSecurityManager esbSecurityManager;

	
	public Object receiver(Message<?> message){
		Object name = message.getHeaders().get("userId");//用户登录名
		Object httpMethod = message.getHeaders().get("http_requestMethod");//用户登录名
		
		if("GET".equals(httpMethod)&&name!=null&&wxSubscriptionFinder!=null&&esbSecurityManager!=null){
			WxApp wxApp = wxSubscriptionFinder.findAppByName(name.toString());
			//使用appid 的 3des加密生成token
			String token = DESUtils.encrypt(esbSecurityManager.getSecurityKey(), wxApp.getAppid());
			//TODO 验证
			logger.info(token);
		}else{
			logger.info(message);
		}
		//获取用户appid
		return "true";
	}
}
