/**
 * 
 */
package com.gsoft.esb.weixin.reqdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.service.WxJsonDataAdapter;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.data.ReqContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxJsonData.uploadnews")
public class UploadnewsJsonData implements WxJsonDataAdapter {

	/* (non-Javadoc)
	 * @see com.gsoft.esb.weixin.service.WxJsonDataAdapter#buildWxJsonData(com.gsoft.framework.esb.data.ReqContext)
	 */
	@Override
	public WxJsonData buildWxJsonData(ReqContext<Object> reqContext) {
		WxJsonData wxJsonData = new WxJsonData();
		
		Object thumb_media_id = reqContext.getFirst("articles[0].thumb_media_id");
		
		if(thumb_media_id!=null){
			List<Record> articles = new ArrayList<Record>();
			Record article = new Record();
			
			article.put("thumb_media_id", thumb_media_id);
			article.put("author", "zhyi_12");
			article.put("description", "description");
			article.put("title", "title");
			article.put("url", "http://120.24.14.236:8080/wpt-web/common/index.html");
			article.put("picurl", "http://120.24.14.236:8080/wpt-web/common/index.html");
//			article.put("content", "<a href=\"www.baidu.com\">www.baidu.com</a>");
			article.put("digest", "<a href=\"www.baidu.com\">www.baidu.com</a>");
			article.put("show_cover_pic", "1");
			articles.add(article);
			
			wxJsonData.put("articles", articles);
		}
		
		return wxJsonData;
	}

}
