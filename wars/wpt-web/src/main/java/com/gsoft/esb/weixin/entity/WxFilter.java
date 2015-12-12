/**
 * 
 */
package com.gsoft.esb.weixin.entity;

/**
 * @author zhyi_12
 *
 */
public class WxFilter {
	private boolean is_to_all = false;
	
    private String group_id  = "100";

	public boolean isIs_to_all() {
		return is_to_all;
	}

	public void setIs_to_all(boolean is_to_all) {
		this.is_to_all = is_to_all;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
    
    
}
