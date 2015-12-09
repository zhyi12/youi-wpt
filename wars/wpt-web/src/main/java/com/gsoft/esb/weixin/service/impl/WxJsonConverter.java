/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.entity.WxData;

/**
 * @author zhyi_12
 *
 */

@Component("wxJsonConverter")
public class WxJsonConverter<T> extends MappingJackson2HttpMessageConverter {

	
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if(object instanceof WxData){
			super.writeInternal(object, outputMessage);
		}
	}
}
