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

import com.gsoft.esb.weixin.data.WxResult;
import com.gsoft.esb.weixin.reqdata.WxData;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.data.ResContext;

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

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return true;
	}
	
	@Override
	public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
		return true;
	}

	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		Object results = super.readInternal(clazz, inputMessage);
		
		if(WxResult.class.isAssignableFrom(results.getClass())){
			return ((WxResult)results).toResContext();
		}
		ResContext res = new ResContext();
		
		if(Record.class.isAssignableFrom(results.getClass())){
			res.setRecord((Record)results);
		}
		
		logger.info(results);
		
		return res;
	}

	@Override
	public Object read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		
		Object results = super.read(type,contextClass, inputMessage);
		
		if(WxResult.class.isAssignableFrom(results.getClass())){
			return ((WxResult)results).toResContext();
		}
		ResContext res = new ResContext();
		
		if(Record.class.isAssignableFrom(results.getClass())){
			res.setRecord((Record)results);
		}
		logger.info(results);
		return res;
	}


	
	
	

}
