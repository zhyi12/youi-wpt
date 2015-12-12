/**
 * 
 */
package com.gsoft.esb.weixin.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.data.ResContext;

/**
 * @author zhyi_12
 *
 */
@Component("wxServiceResult.batchgetMaterial")
public class WxBatchgetMaterialResult implements WxResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4221929942995171832L;

	private int total_count;
	
	private int item_count;
	
	private Record[] item;

//	{
//		   "total_count": TOTAL_COUNT,
//		   "item_count": ITEM_COUNT,
//		   "item": [{
//		       "media_id": MEDIA_ID,
//		       "content": {
//		           "news_item": [{
//		               "title": TITLE,
//		               "thumb_media_id": THUMB_MEDIA_ID,
//		               "show_cover_pic": SHOW_COVER_PIC(0 / 1),
//		               "author": AUTHOR,
//		               "digest": DIGEST,
//		               "content": CONTENT,
//		               "url": URL,
//		               "content_source_url": CONTETN_SOURCE_URL
//		           },
//		           //多图文消息会在此处有多篇文章
//		           ]
//		        },
//		        "update_time": UPDATE_TIME
//		    },
//		    //可能有多个图文消息item结构
//		  ]
//		}
	
	public ResContext toResContext(){
		ResContext resContext = new ResContext();
		
		resContext.setTotalCount(total_count);
		
		List<Domain> datas = new ArrayList<Domain>();
		
		Collections.addAll(datas, item);
		
		resContext.setRecords(datas);
		
		return resContext;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getItem_count() {
		return item_count;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	public Record[] getItem() {
		return item;
	}

	public void setItem(Record[] item) {
		this.item = item;
	}


}
