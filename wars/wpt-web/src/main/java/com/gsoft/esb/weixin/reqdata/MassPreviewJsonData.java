/**
 * 
 */
package com.gsoft.esb.weixin.reqdata;

import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.service.WxJsonDataAdapter;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.data.ReqContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxJsonData.massPreview")
public class MassPreviewJsonData implements WxJsonDataAdapter {

	/* (non-Javadoc)
	 * @see com.gsoft.esb.weixin.service.WxJsonDataAdapter#buildWxJsonData(com.gsoft.framework.esb.data.ReqContext)
	 */
	@Override
	public WxJsonData buildWxJsonData(ReqContext<Object> reqContext) {
		WxJsonData wxJsonData = new WxJsonData();
		
		Object media_id = reqContext.getFirst("media_id");
		Object touser = reqContext.getFirst("touser");
		
		if(media_id!=null&&touser!=null){
			Record mpnews = new Record();
			mpnews.put("media_id", media_id);
			
			wxJsonData.put("touser", touser);
			wxJsonData.put("msgtype", "mpnews");
			wxJsonData.put("mpnews", mpnews);
		}
		
//		{     
//		    "touser":"OPENID",
//		    "text":{           
//		           "content":"CONTENT"            
//		           },     
//		    "msgtype":"text"
//		}
		
		return wxJsonData;
	}

}
