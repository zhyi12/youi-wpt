/**
 * 
 */
package com.gsoft.esb.weixin.security;

import com.gsoft.esb.weixin.entity.WxUser;
import com.gsoft.framework.security.AbstractFormUserInfo;

/**
 * @author zhyi_12
 *
 */
public class WxAccount extends AbstractFormUserInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6243703974790380237L;

	public WxAccount(WxUser wxUser){
		super(wxUser,"");
	}

}
