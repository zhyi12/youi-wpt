/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.ChannelResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.esb.weixin.entity.WxToken;
import com.gsoft.esb.weixin.reqdata.WxData;
import com.gsoft.esb.weixin.reqdata.WxJsonData;
import com.gsoft.esb.weixin.service.WxJsonDataAdapter;
import com.gsoft.framework.esb.EsbConstants;
import com.gsoft.framework.esb.data.ReqContext;
import com.gsoft.framework.upload.service.FileStoreManager;
import com.gsoft.framework.util.Assert;

/**
 * @author zhyi_12
 *
 */

@Component("wxRequestRouter")
public class WxRequestRouter extends AbstractMessageRouter implements ApplicationContextAware{
	
	private static Map<String,WxToken> cachedTokens = new HashMap<String,WxToken>();
	
	public static final String TOEKN_HEADER_PARAM = "access_token";
	
	@Autowired
	private ChannelResolver channelResolver;
	
	@Autowired
	private FileStoreManager fileStoreManager;
	
	@Autowired
	private Map<String,WxJsonDataAdapter> wxJsonDataAdapters;
	
	
	public void setChannelResolver(ChannelResolver channelResolver) {
		this.channelResolver = channelResolver;
	}

	@Override
	protected void handleMessageInternal(Message<?> message) {
		
		Map<String, Object> headers = new HashMap<String,Object>();
		
		WxApp wxApp = buildWxApp(message);//获取wxapp对象
		
		String appid = wxApp.getAppid();
		WxToken wxToken = new WxToken();
		
		headers.putAll(message.getHeaders());
		headers.put("appid", appid);
		headers.put("secret", wxApp.getSecret());
		
		
		ReqContext<Object> reqContext = null;
		if(message.getPayload() instanceof ReqContext){
			reqContext = ((ReqContext<Object>)message.getPayload());
		}
		
		if(reqContext!=null&&reqContext.containsKey("openid")){
			headers.put("openid", reqContext.getFirst("openid"));
		}
		
		if(!message.getHeaders().containsKey(TOEKN_HEADER_PARAM)){
			if(cachedTokens.containsKey(appid)){
				wxToken = cachedTokens.get(appid);
			}
			
			if(needGetToken(wxToken)){
				Message<?> retMessage = this.getMessagingTemplate().sendAndReceive("wxGetToken",
						MessageBuilder.withPayload("").copyHeaders(headers).build());
				if(retMessage.getPayload() instanceof WxToken){
					wxToken = (WxToken)retMessage.getPayload();
					cachedTokens.put(appid, wxToken);
					logger.info("重新获取token:"+wxToken);
				}
			}
			headers.put("access_token", wxToken.getAccess_token());
			logger.info("使用token:"+wxToken);
		}
		
		Object newPayload = null;
		if( "wxUploadServices".equals(getChannelName(message))){
			//设置上传头
			headers.put("Content-Type", MediaType.MULTIPART_FORM_DATA.toString());
			newPayload = buildUploadParam(reqContext);
		}else{
			newPayload = buildWxData(headers.get(EsbConstants.HEADER_SERVICE).toString(),reqContext);//微信数据构建
		}
		
		Message<?> newMessage = MessageBuilder.withPayload(newPayload).copyHeaders(headers).build();
		
		super.handleMessageInternal(newMessage);
	}

	private MultiValueMap<String,Object> buildUploadParam(ReqContext<Object> reqContext) {
		MultiValueMap<String,Object> uploadParams = new LinkedMultiValueMap<String,Object>();
		
		Resource imgResource = null;
		if(fileStoreManager!=null&&reqContext.containsKey("media")){
			
			Object media = reqContext.getFirst("media");
			
			if(media!=null){
				File file = fileStoreManager.getFile(media.toString());
				if(file!=null){
					imgResource = new FileSystemResource(file);
				}
			}
		}
		
		if(imgResource!=null){
			HttpEntity<Resource> entity  =new HttpEntity<Resource>(imgResource);
			uploadParams.add("media",  entity);
		}
		
		return uploadParams;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WxApp buildWxApp(Message<?> message) {
		WxApp wxApp = new WxApp();
		
		Object appid = "";
		Object secret = "";
		if(message.getPayload() instanceof LinkedMultiValueMap){
			LinkedMultiValueMap payload = (LinkedMultiValueMap)message.getPayload();
			appid = payload.getFirst(EsbConstants.PUB_PARAM_PREFIX+"appId");
			secret = payload.getFirst(EsbConstants.PUB_PARAM_PREFIX+"appSecret");
		}
		
		if(appid!=null)wxApp.setAppid(appid.toString());
		if(secret!=null)wxApp.setSecret(secret.toString());
		
		return wxApp;
	}

	private WxData buildWxData(String serviceName,ReqContext<Object> reqContext) {
		//查询请求
		WxData jsonData;
		String beanName = "wxJsonData."+serviceName;
		if(wxJsonDataAdapters!=null&&wxJsonDataAdapters.containsKey(beanName)){
			jsonData = wxJsonDataAdapters.get(beanName).buildWxJsonData(reqContext);
		}else{
			jsonData = new WxJsonData();
		}
		return jsonData;
	}

	@Override
	protected Collection<MessageChannel> determineTargetChannels(
			Message<?> message) {
		MessageChannel channel;
		try {
			channel = getBeanFactory().getBean(getChannelName(message),MessageChannel.class);
		} catch (BeansException e) {
			//TODO 抛出新的异常 SerializingHttpMessageConverter
			e.printStackTrace();
			throw e;
		}
		
		Collection<MessageChannel> channels = new ArrayList<MessageChannel>();
		channels.add(channel);
		
		return channels;
	}

	private String getChannelName(Message<?> message) {
		String channelName = message.getHeaders().get(EsbConstants.HEADER_SERVICE_GROUP,String.class);//
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if(wxJsonDataAdapters==null){
			wxJsonDataAdapters = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
					WxJsonDataAdapter.class, true, false);
		}
	}

}
