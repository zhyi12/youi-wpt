/**
 * 
 */
package com.gsoft.esb.weixin.entity;

import java.util.ArrayList;
import java.util.List;

import com.gsoft.esb.weixin.reqdata.WxData;

/**
 * @author zhyi_12
 *
 */
public class WxUploadNews implements WxData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5726414365502347686L;
	
	private List<WxUploadArticle> articles;

	public List<WxUploadArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<WxUploadArticle> articles) {
		this.articles = articles;
	}

	public WxUploadNews addUploadArticle(String thumb_media_id, String title,
			String content, String show_cover_pic) {
		if(articles==null){
			articles = new ArrayList<WxUploadArticle>();
		}
		
		WxUploadArticle  wxUploadArticle = new WxUploadArticle();
		wxUploadArticle.setAuthor("zhyi_12");
		wxUploadArticle.setContent(content);
		wxUploadArticle.setContent_source_url("");
		
		wxUploadArticle.setShow_cover_pic(show_cover_pic);
		wxUploadArticle.setThumb_media_id(thumb_media_id);
		
		wxUploadArticle.setTitle(title);
		
		articles.add(wxUploadArticle);
		
		return this;
		
	}
	
	
}
