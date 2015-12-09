/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.ChannelResolver;
import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.entity.WxToken;
import com.gsoft.framework.esb.EsbConstants;
import com.gsoft.framework.util.Assert;

/**
 * @author zhyi_12
 *
 */

@Component("wxRequestRouter")
public class WxRequestRouter extends AbstractMessageRouter{
	
	private static Map<String,WxToken> cachedTokens = new HashMap<String,WxToken>();
	
	public static final String TOEKN_HEADER_PARAM = "access_token";
	
	@Autowired
	private ChannelResolver channelResolver;
	
	public void setChannelResolver(ChannelResolver channelResolver) {
		this.channelResolver = channelResolver;
	}

	@Override
	protected void handleMessageInternal(Message<?> message) {
		
		Map<String, Object> headers = new HashMap<String,Object>();
		
		String appid = message.getHeaders().get("appid", String.class);
		
		WxToken wxToken = new WxToken();
		
		if(!message.getHeaders().containsKey(TOEKN_HEADER_PARAM)){
			if(cachedTokens.containsKey(appid)){
				wxToken = cachedTokens.get(appid);
			}
			
			if(needGetToken(wxToken)){
				Message<?> retMessage = this.getMessagingTemplate().sendAndReceive("wxGetToken", message);
				if(retMessage.getPayload() instanceof WxToken){
					wxToken = (WxToken)retMessage.getPayload();
					logger.info("重新获取token:"+wxToken);
				}
			}
			headers.put("access_token", wxToken.getAccess_token());
			logger.info("使用token:"+wxToken);
		}
		
		headers.putAll(message.getHeaders());
		
		Message<?> newMessage = MessageBuilder.fromMessage(message).copyHeaders(headers).build();
		
		super.handleMessageInternal(newMessage);
	}

	@Override
	protected Collection<MessageChannel> determineTargetChannels(
			Message<?> message) {
		MessageChannel channel;
		try {
			channel = getBeanFactory().getBean(getChannelName(message),MessageChannel.class);
		} catch (BeansException e) {
			//TODO 抛出新的异常 SerializingHttpMessageConverter
			throw e;
		}
		
		Collection<MessageChannel> channels = new ArrayList<MessageChannel>();
		channels.add(channel);
		
		return channels;
	}

	private String getChannelName(Message<?> message) {
		String channelName = message.getHeaders().get(EsbConstants.HEADER_CHANNEL,String.class);//
		Assert.notNull(channelName, "请定义头中的channle属性！");
		return channelName;
	}

	private boolean needGetToken(WxToken wxToken) {
		//
		return wxToken.getExpires_in()==0||(System.currentTimeMillis() - wxToken.getStart_time()>=wxToken.getExpires_in()*1000);
	}

	@Override
	protected void onInit() throws Exception {
		super.onInit();
		this.getMessagingTemplate().setChannelResolver(channelResolver);
	}

}
