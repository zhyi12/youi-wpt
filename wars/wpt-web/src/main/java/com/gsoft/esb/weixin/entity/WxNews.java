/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhyi_12
 *
 */
public class WxNews {

	private List<WxArticle> articles;

	public List<WxArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<WxArticle> articles) {
		this.articles = articles;
	}
	
	
	public WxNews addWxArticle(String title,String description,String url,String picurl){
		if(articles==null){
			articles = new ArrayList<WxArticle>();
		}
		articles.add(new WxArticle(title, description, url, picurl));
		return this;
	}
	
}
