/**
 * 
 */
package com.gsoft.esb.weixin.reqdata;

import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.service.WxJsonDataAdapter;
import com.gsoft.framework.esb.data.ReqContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxJsonData.batchgetMaterial")
public class BatchgetMaterialJsonData implements WxJsonDataAdapter {

	/* (non-Javadoc)
	 * @see com.gsoft.esb.weixin.service.WxJsonDataAdapter#buildWxJsonData(com.gsoft.framework.esb.data.ReqContext)
	 */
	@Override
	public WxJsonData buildWxJsonData(ReqContext<Object> reqContext) {
		WxJsonData wxJsonData = new WxJsonData();
		
		wxJsonData.put("type", "news");
		wxJsonData.put("offset", 0);
		wxJsonData.put("count", 10);
		
		return wxJsonData;
	}

}
