/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.data.ResContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxUploadConverter")
public class WxUploadConverter implements HttpMessageConverter<Object>{
	
	private FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
	
	private MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return formHttpMessageConverter.getSupportedMediaTypes();
	}

	@Override
	public ResContext read(Class clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		Object record = jsonConverter.read(Record.class, inputMessage);
		
		ResContext  resContext = new ResContext();
		
		if(record instanceof Record){
			resContext.setRecord((Record)record);
		}
		return resContext;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write(Object map, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		formHttpMessageConverter.write((MultiValueMap<String, ?>) map, contentType, outputMessage);
	}
	
}
