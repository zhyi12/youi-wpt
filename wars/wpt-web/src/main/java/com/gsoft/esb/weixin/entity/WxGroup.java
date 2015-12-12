/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.esb.weixin.reqdata.WxData;
import com.gsoft.framework.core.dataobj.Record;

/**
 * @author zhyi_12
 *
 */
public class WxGroup implements WxData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5365642084755070985L;
	private Record group = new Record(); 

	public WxGroup(String name) {
		super();
		this.group.put("name", name);
	}

	public Record getGroup() {
		return group;
	}

	public void setGroup(Record group) {
		this.group = group;
	}
	
}
