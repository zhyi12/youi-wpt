/**
 * 
 */
package com.gsoft.esb.weixin.security;

import com.gsoft.esb.weixin.entity.WxUser;

/**
 * @author zhyi_12
 *
 */
public interface WxUserFinder {

	public WxUser findWxUserByOpenid(String openid);

}
