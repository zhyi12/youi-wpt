/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.service.impl;

import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
//import com.gsoft.framework.core.orm.ConditionFactory;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;

import com.gsoft.framework.esb.annotation.*;

import com.gsoft.framework.core.service.impl.BaseManagerImpl;

import com.gsoft.weixin.wxmessage.entity.WxCustomMessage;
import com.gsoft.weixin.wxmessage.dao.WxCustomMessageDao;
import com.gsoft.weixin.wxmessage.service.WxCustomMessageManager;

@Service("wxCustomMessageManager")
@Transactional
public class WxCustomMessageManagerImpl extends BaseManagerImpl implements WxCustomMessageManager{
	@Autowired
	private WxCustomMessageDao wxCustomMessageDao;
	
    /**
     * 查询列表
     */
    public List<WxCustomMessage> getWxCustomMessages() throws BusException{
    	return wxCustomMessageDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxCustomMessage> getWxCustomMessages(
    	@ConditionCollection(domainClazz=WxCustomMessage.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxCustomMessageDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxCustomMessage getWxCustomMessage(@ServiceParam(name="customeMsgId") String id)  throws BusException{
    	return wxCustomMessageDao.get(id);
    }
	
	@EsbServiceMapping
	public PagerRecords getPagerWxCustomMessages(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxCustomMessage.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxCustomMessageDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping
    public WxCustomMessage saveWxCustomMessage(WxCustomMessage o) throws BusException{
//    	String wxCustomMessageId = o.getWxCustomMessageId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxCustomMessageId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxCustomMessageDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxCustomMessage(@ServiceParam(name="customeMsgId") String id) throws BusException{
    	wxCustomMessageDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxCustomMessages(@ServiceParam(name="customeMsgId") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxCustomMessage(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxCustomMessage(@ServiceParam(name="customeMsgId") String id)  throws BusException{
		return wxCustomMessageDao.exists(id);
	}
    
    public boolean exsitWxCustomMessage(String propertyName,Object value) throws BusException{
		return wxCustomMessageDao.exists(propertyName,value);
	}

}
