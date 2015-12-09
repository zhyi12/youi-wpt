/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gsoft.framework.core.dataobj.Domain;

/**
 * @author zhyi_12
 *
 */
@JsonIgnoreProperties("start_time")
public class WxToken implements Domain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 414293913015754469L;
	
	private String access_token;
	
	private int expires_in = 0;
	
	private long start_time;//

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}

	@Override
	public String toString() {
		return "WxToken [access_token=" + access_token + ", expires_in="
				+ expires_in + ", start_time=" + start_time + "]";
	}

}
