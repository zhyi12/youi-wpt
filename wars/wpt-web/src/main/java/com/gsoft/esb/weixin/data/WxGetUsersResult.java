/**
 * 
 */
package com.gsoft.esb.weixin.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gsoft.framework.core.dataobj.Domain;
import com.gsoft.framework.core.dataobj.Record;
import com.gsoft.framework.esb.data.ResContext;
import com.gsoft.framework.util.StringUtils;

/**
 * @author zhyi_12
 *
 */
@Component("wxServiceResult.getUsers")
public class WxGetUsersResult implements WxResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4675061229773283119L;
	
	//{"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
	private String total;
	
	private String count;
	
	private Record data;
	
	private String next_openid;

	
	@SuppressWarnings("unchecked")
	public ResContext toResContext(){
		ResContext resContext = new ResContext();
		
		resContext.setTotalCount(Integer.parseInt(total));
		
		List<Domain> datas = new ArrayList<Domain>();
		
		Object openids = data.get("openid");
		
		if(openids instanceof List){
			List<String> openidList = (List<String>)openids;
			for(String openid:openidList){
				if(StringUtils.isNotEmpty(openid)){
					Record record = new Record();
					record.put("openid", openid);
					datas.add(record);
				}
			}
		}
		
		resContext.setRecords(datas);
		
		return resContext;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public Record getData() {
		return data;
	}


	public void setData(Record data) {
		this.data = data;
	}


	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
}
