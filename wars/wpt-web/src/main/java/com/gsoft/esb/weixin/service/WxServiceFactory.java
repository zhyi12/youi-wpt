package com.gsoft.esb.weixin.service;

import com.gsoft.esb.weixin.entity.WxApp;
import com.gsoft.framework.core.dataobj.Domain;

public interface WxServiceFactory {

	
	public Domain getOAuthUserInfo(WxApp wxApp, String code);
}
