/**
 * 
 */
package com.gsoft.esb.weixin.reqdata;

import org.springframework.stereotype.Component;

import com.gsoft.esb.weixin.entity.WxNews;
import com.gsoft.esb.weixin.entity.WxNewsMessage;
import com.gsoft.esb.weixin.service.WxJsonDataAdapter;
import com.gsoft.framework.esb.data.ReqContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxJsonData.sendCustomMessage")
public class SendCustomMessageJsonData implements WxJsonDataAdapter {

	/* 
	 * {
    "touser":"OPENID",
    "msgtype":"news",
    "news":{
        "articles": [
         {
             "title":"Happy Day",
             "description":"Is Really A Happy Day",
             "url":"URL",
             "picurl":"PIC_URL"
         },
         {
             "title":"Happy Day",
             "description":"Is Really A Happy Day",
             "url":"URL",
             "picurl":"PIC_URL"
         }
         ]
    }
}
	 * (non-Javadoc)
	 * @see com.gsoft.esb.weixin.service.WxJsonDataAdapter#buildWxJsonData(com.gsoft.framework.esb.data.ReqContext)
	 */
	@Override
	public WxData buildWxJsonData(ReqContext<Object> reqContext) {
		WxNewsMessage wxNewsMessage = new WxNewsMessage();
		
		WxNews news = new WxNews();
		
		Object title = reqContext.getFirst("title");
		Object description = reqContext.getFirst("description");
		Object url = reqContext.getFirst("url");
		Object picurl = reqContext.getFirst("picurl");
		Object touser = reqContext.getFirst("touser");
		
		news.addWxArticle(title.toString(), description.toString(), url.toString(), picurl.toString());
		
		wxNewsMessage.setNews(news );
		wxNewsMessage.setTouser(touser.toString());
		
		return wxNewsMessage;
	}

}
