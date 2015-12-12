/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import com.gsoft.framework.core.dataobj.Domain;

/**
 * @author zhyi_12
 *
 */
public class WxArticle implements Domain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8945864532913302029L;

	private String title;
	
	private String description;
	
	private String url;
	
	private String picurl;
	
	

	public WxArticle(String title, String description, String url, String picurl) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.picurl = picurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	
}
