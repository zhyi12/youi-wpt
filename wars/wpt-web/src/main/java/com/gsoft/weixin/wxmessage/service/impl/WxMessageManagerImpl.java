/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
//import com.gsoft.framework.core.orm.ConditionFactory;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;
import com.gsoft.framework.core.service.impl.BaseManagerImpl;
import com.gsoft.framework.esb.annotation.ConditionCollection;
import com.gsoft.framework.esb.annotation.EsbServiceMapping;
import com.gsoft.framework.esb.annotation.OrderCollection;
import com.gsoft.framework.esb.annotation.PubCondition;
import com.gsoft.framework.esb.annotation.ServiceParam;
import com.gsoft.framework.esb.data.PubContext;
import com.gsoft.weixin.wxmessage.dao.WxMessageDao;
import com.gsoft.weixin.wxmessage.entity.WxMessage;
import com.gsoft.weixin.wxmessage.service.WxMessageManager;

@Service("wxMessageManager")
@Transactional
public class WxMessageManagerImpl extends BaseManagerImpl implements WxMessageManager{
	@Autowired
	private WxMessageDao wxMessageDao;
	
    /**
     * 查询列表
     */
    public List<WxMessage> getWxMessages() throws BusException{
    	return wxMessageDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxMessage> getWxMessages(
    	PubContext pubContext,//公共参数
    	@ConditionCollection(domainClazz=WxMessage.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxMessageDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxMessage getWxMessage(@ServiceParam(name="messageId") String id)  throws BusException{
    	return wxMessageDao.get(id);
    }
	
	@EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
	public PagerRecords getPagerWxMessages(
			PubContext pubContext,//公共参数
			Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxMessage.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		
		PagerRecords pagerRecords = wxMessageDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
    public WxMessage saveWxMessage(WxMessage o) throws BusException{
//    	String wxMessageId = o.getWxMessageId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxMessageId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxMessageDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxMessage(@ServiceParam(name="messageId") String id) throws BusException{
    	wxMessageDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMessages(@ServiceParam(name="messageId") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxMessage(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxMessage(@ServiceParam(name="messageId") String id)  throws BusException{
		return wxMessageDao.exists(id);
	}
    
    public boolean exsitWxMessage(String propertyName,Object value) throws BusException{
		return wxMessageDao.exists(propertyName,value);
	}

}
