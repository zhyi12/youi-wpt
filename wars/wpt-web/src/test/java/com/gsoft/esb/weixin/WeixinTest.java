package com.gsoft.esb.weixin;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsoft.esb.weixin.entity.WxMpnewsMessage;
import com.gsoft.esb.weixin.entity.WxNews;
import com.gsoft.esb.weixin.entity.WxNewsMessage;
import com.gsoft.esb.weixin.entity.WxUpdateGroupMember;
import com.gsoft.esb.weixin.entity.WxUploadNews;
import com.gsoft.framework.esb.EsbConstants;


@ContextConfiguration(locations = {
		"classpath:applicationContext-wxtest.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class WeixinTest {

	@Autowired
	private MessagingTemplate messageTemplate;
	/**
	 * token
	 */
	@Test
	public void testGetToken(){
		Map<String,String> headers = new HashMap<String,String>();
		//wx.secret=ccb585d4ef40e78af1537d4fa5919e32
		headers.put("appid", "wxfdb3ad82dd67bbb3"); //测试账号
		headers.put("secret", "36dbcfb432940dfd46ac81e144f4e698");
		
		headers.put(EsbConstants.HEADER_SERVICE, "sendallMessage");//sendallMessage
		//http通道
		headers.put(EsbConstants.HEADER_CHANNEL, "wxHttpChannel");
		
		headers.put("httpMethod", "POST");
		headers.put("access_token", "8OLM5HhZoXL9NyCDMrYgxU3Ssc7sT-CqDV-1WqWZxCVlioDbPnsfazkSbGr-DN2UpBAB0uIqq82Vqml68HNaJAs7SrGsy0FomCol7mRkMasMFGbAAARKL");
			
		//
//		headers.put("jsonClass", WxUser.class.getName());
		
		//o34IKuHcgGRyO_yVHvFpkUJOLBsY 你爱花
		//o34IKuF_Q6kY_aAcRcT1JMxgoSWM  小白
		//o34IKuL36RFCABfsdquh9gG3LY6Q zhyi_12
		//用户id
		headers.put("openid", "o34IKuL36RFCABfsdquh9gG3LY6Q");
		
		String touser = "o34IKuL36RFCABfsdquh9gG3LY6Q";
		
//		WxTextMessage wxTextMsg = new WxTextMessage("o34IKuL36RFCABfsdquh9gG3LY6Q","<a href=\"120.24.14.236:8080/wpt-web/common/index.html\">连接</a>");
		WxNewsMessage wxNewsMessage = new WxNewsMessage();
		wxNewsMessage.setTouser(touser);
		wxNewsMessage.setNews(new WxNews()
			.addWxArticle("我的红包1", "第一个", 
				"http://120.24.14.236:8080/wpt-web/common/index.html", 
				"http://h.hiphotos.baidu.com/baike/s%3D220/sign=a37d0ecaa5c27d1ea1263cc62bd4adaf/42a98226cffc1e17fdc0305f4b90f603738de965.jpg")
			.addWxArticle("我的红包2", "第二个", 
				"http://120.24.14.236:8080/wpt-web/common/index.html", 
				"http://h.hiphotos.baidu.com/baike/s%3D220/sign=a37d0ecaa5c27d1ea1263cc62bd4adaf/42a98226cffc1e17fdc0305f4b90f603738de965.jpg"));
		
		
		//pg3V-i01YLcjD7rhPOWSR8p0ksKvisSBICBm2q9LTeq0nniatq0bi1PBNkDW0kx6
		
//		"thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
//        "author":"xxx",
//"title":"Happy Day",
//"content_source_url":"www.qq.com",
//"content":"content",
//"digest":"digest",
//        "show_cover_pic":"1"
		
		WxUploadNews wxUploadNews = new WxUploadNews();
		String thumb_media_id = "pg3V-i01YLcjD7rhPOWSR8p0ksKvisSBICBm2q9LTeq0nniatq0bi1PBNkDW0kx6";
		String content = "<a href=\"http://120.24.14.236:8080/wpt-web/common/index.html\"></a>";
		String title = "我的红包";
		wxUploadNews.addUploadArticle(thumb_media_id,title,content,"1");
		
		
//		图文消息  media_id=3cHHbmIKwdMwKYnC7qkRsMVd9ZFKUTmTrpG9D2aVrVlHORuGsoq3qEF_aXql4bJ_
		
		WxMpnewsMessage wxMpnewsMessage = new WxMpnewsMessage("3cHHbmIKwdMwKYnC7qkRsMVd9ZFKUTmTrpG9D2aVrVlHORuGsoq3qEF_aXql4bJ_");
		
//		WxGroup wxGroup = new WxGroup("admin");
		
		WxUpdateGroupMember updateGroupMember = new WxUpdateGroupMember(touser,"100");
		//group 100
		
		Message<?> res = messageTemplate.sendAndReceive("wxStart", MessageBuilder.withPayload(wxMpnewsMessage).copyHeaders(headers).build());
		
		System.out.println(res);
	}

}
