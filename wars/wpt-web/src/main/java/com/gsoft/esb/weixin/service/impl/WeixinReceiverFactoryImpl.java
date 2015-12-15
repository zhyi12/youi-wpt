/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.esb.weixin.service.WxSubscriptionFinder;
import com.gsoft.framework.security.EsbSecurityManager;
import com.gsoft.framework.util.DESUtils;
import com.gsoft.framework.util.Dom4jUtils;

/**
 * @author zhyi_12
 *
 */
@Component("weixinReceiverFactory")
public class WeixinReceiverFactoryImpl {
	
	private static final Log logger = LogFactory.getLog(WeixinReceiverFactoryImpl.class);
	
	@Autowired(required=false)
	private WxSubscriptionFinder wxSubscriptionFinder;
	
	@Autowired(required=false)
	private EsbSecurityManager esbSecurityManager;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object receiver(Message<?> message){
		Object name = message.getHeaders().get("userId");//用户登录名
		Object httpMethod = message.getHeaders().get("http_requestMethod");//用户登录名
		
		Object retValue = null;
		if("GET".equals(httpMethod)&&name!=null&&wxSubscriptionFinder!=null&&esbSecurityManager!=null){
			WxApp wxApp = wxSubscriptionFinder.findAppByName(name.toString());
			//使用appid 的 3des加密生成token
			String token = DESUtils.encrypt(esbSecurityManager.getSecurityKey(), wxApp.getAppid());
			if(token.length()>32){
				token = token.substring(0,32);
			}
			//TODO 验证
			logger.info(token);
			if(message.getPayload() instanceof MultiValueMap){
				retValue = ((MultiValueMap)message.getPayload()).getFirst("echostr");
			}
		}else{//POST
			//logger.info(message);
//			weixinAdapter
//			WxEventDispacther;//微信事件分发处理
//			WxMessageReceiver;//微信消息接收
			//解析xml
			
			retValue = "success";//直接返回成功提示
		}
		//获取用户appid
		return retValue;
	}
	
	public static void main(String[] args){
		String xml = "<xml><ToUserName><![CDATA[gh_a8986f3d0de9]]></ToUserName><FromUserName><![CDATA[oicG7wlp02Od4L7DaaQI0AaPDomc]]></FromUserName><CreateTime>1449914130</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[test]]></Content><MsgId>6227333770760067783</MsgId></xml>";
	
		Document doc = Dom4jUtils.parseText(xml);
		//消息类型
		String messageType = doc.getRootElement().element("MsgType").getTextTrim();
		
		System.out.println(doc.getRootElement().element("ToUserName").getTextTrim());
		System.out.println(doc.getRootElement().element("FromUserName").getTextTrim());
		System.out.println(doc.getRootElement().element("Content").getTextTrim());
		System.out.println(doc.getRootElement().element("MsgId").getTextTrim());
		
	}
}
