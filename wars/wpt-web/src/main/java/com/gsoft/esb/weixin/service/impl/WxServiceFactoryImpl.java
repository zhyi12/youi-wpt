/**
 * 
 */
package com.gsoft.esb.weixin.service.impl;

import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.reqdata.WxData;
import com.gsoft.esb.weixin.reqdata.WxJsonData;

/**
 * @author zhyi_12
 *
 */
@Component("wxServiceFactory")
public class WxServiceFactoryImpl {

	public WxData doService(Message<?> message){
		//从请求参数中组织wx json数据
		return new WxJsonData();
	}
}
