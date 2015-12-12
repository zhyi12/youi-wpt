/**
 * 
 */
package com.gsoft.esb.weixin.service;

import com.gsoft.esb.weixin.entity.WxApp;

/**
 * @author zhyi_12
 *
 */
public interface WxSubscriptionFinder {

	/**
	 * @param name
	 * @return
	 */
	public WxApp findAppByName(String name);

}
