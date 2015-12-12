/**
 * 
 */
package com.gsoft.esb.weixin.service;

import com.gsoft.esb.weixin.reqdata.WxData;
import com.gsoft.framework.esb.data.ReqContext;

/**
 * @author zhyi_12
 *
 */
public interface WxJsonDataAdapter {

	WxData buildWxJsonData(ReqContext<Object> reqContext);

}
