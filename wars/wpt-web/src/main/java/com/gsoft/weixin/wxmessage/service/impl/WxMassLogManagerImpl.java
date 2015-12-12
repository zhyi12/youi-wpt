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

import com.gsoft.weixin.wxmessage.entity.WxMassLog;
import com.gsoft.weixin.wxmessage.dao.WxMassLogDao;
import com.gsoft.weixin.wxmessage.service.WxMassLogManager;

@Service("wxMassLogManager")
@Transactional
public class WxMassLogManagerImpl extends BaseManagerImpl implements WxMassLogManager{
	@Autowired
	private WxMassLogDao wxMassLogDao;
	
    /**
     * 查询列表
     */
    public List<WxMassLog> getWxMassLogs() throws BusException{
    	return wxMassLogDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxMassLog> getWxMassLogs(
    	@ConditionCollection(domainClazz=WxMassLog.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxMassLogDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxMassLog getWxMassLog(@ServiceParam(name="massId") String id)  throws BusException{
    	return wxMassLogDao.get(id);
    }
	
	@EsbServiceMapping
	public PagerRecords getPagerWxMassLogs(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxMassLog.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxMassLogDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping
    public WxMassLog saveWxMassLog(WxMassLog o) throws BusException{
//    	String wxMassLogId = o.getWxMassLogId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxMassLogId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxMassLogDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxMassLog(@ServiceParam(name="massId") String id) throws BusException{
    	wxMassLogDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMassLogs(@ServiceParam(name="massId") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxMassLog(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxMassLog(@ServiceParam(name="massId") String id)  throws BusException{
		return wxMassLogDao.exists(id);
	}
    
    public boolean exsitWxMassLog(String propertyName,Object value) throws BusException{
		return wxMassLogDao.exists(propertyName,value);
	}

}
