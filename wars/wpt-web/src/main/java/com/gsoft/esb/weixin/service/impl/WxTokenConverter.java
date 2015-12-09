/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.entity.WxToken;

/**
 * @author zhyi_12
 *
 */
@Component("wxTokenConverter")
public class WxTokenConverter extends MappingJackson2HttpMessageConverter {

	
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
	}

	@Override
	public Object read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		
		WxToken wxToken = getObjectMapper().readValue(inputMessage.getBody(), WxToken.class);
		wxToken.setStart_time(System.currentTimeMillis()-10000);
		return wxToken;
	}


}
