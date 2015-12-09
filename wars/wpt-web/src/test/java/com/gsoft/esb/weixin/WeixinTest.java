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

import com.gsoft.esb.weixin.entity.WxNews;
import com.gsoft.esb.weixin.entity.WxNewsMessage;
import com.gsoft.esb.weixin.entity.WxUser;
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
		
		headers.put(EsbConstants.HEADER_SERVICE, "sendCustomMessage");
		//httpGet通道
		headers.put(EsbConstants.HEADER_CHANNEL, "wxHttpChannel");
		headers.put("httpMethod", "POST");
		headers.put("access_token", "wq4uPofddKlDU4NqdKMBs-VFUt5BapUBB5cLRIWgpC27aGvZ7M1RcjJHqBqgd1uroSwzQkwkh-FrZoOi4pK2BUhDOQQhxumcO1u164zzHIYDXZiABAQKM");
		
		//
		headers.put("jsonClass", WxUser.class.getName());
		
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
		
		Message<?> res = messageTemplate.sendAndReceive("wxStart", MessageBuilder.withPayload(wxNewsMessage).copyHeaders(headers).build());
		
		System.out.println(res);
	}

}
