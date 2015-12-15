package com.gsoft.esb.weixin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.esb.weixin.service.WxServiceFactory;
import com.gsoft.framework.core.dataobj.Domain;


@ContextConfiguration(locations = {
		"classpath:applicationContext-wxtest.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class WeixinTest {

	@Autowired
	private WxServiceFactory wxServiceFactory;
	/**
	 * token
	 */
	@Test
	public void testGetOAuthUserInfo(){
		String code = "1232";
		WxApp wxApp = new WxApp();
		wxApp.setAppid("wxfdb3ad82dd67bbb3");
		wxApp.setSecret("36dbcfb432940dfd46ac81e144f4e698");
		Domain res = wxServiceFactory.getOAuthUserInfo(wxApp, code);
		System.out.println(res);
	}

}
