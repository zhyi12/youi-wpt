/**
 * 
 */
package com.gsoft.esb.weixin.data;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.esb.data.ResContext;

/**
 * @author zhyi_12
 *
 */
public interface WxResult extends Domain {

	/**
	 * 转换成ResContext对象
	 * @return
	 */
	public ResContext toResContext();
}
