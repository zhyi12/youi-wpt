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
import com.gsoft.weixin.wxmessage.entity.WxMass;
import com.gsoft.weixin.wxmessage.dao.WxMassDao;
import com.gsoft.weixin.wxmessage.service.WxMassManager;

@Service("wxMassManager")
@Transactional
public class WxMassManagerImpl extends BaseManagerImpl implements WxMassManager{
	@Autowired
	private WxMassDao wxMassDao;
	
    /**
     * 查询列表
     */
    public List<WxMass> getWxMasss() throws BusException{
    	return wxMassDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxMass> getWxMasss(
    	@ConditionCollection(domainClazz=WxMass.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxMassDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxMass getWxMass(@ServiceParam(name="massId") String id)  throws BusException{
    	return wxMassDao.get(id);
    }
	
    @EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
	public PagerRecords getPagerWxMasss(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxMass.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxMassDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
    public WxMass saveWxMass(WxMass o) throws BusException{
//    	String wxMassId = o.getWxMassId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxMassId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxMassDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxMass(@ServiceParam(name="massId") String id) throws BusException{
    	wxMassDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMasss(@ServiceParam(name="massId") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxMass(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxMass(@ServiceParam(name="massId") String id)  throws BusException{
		return wxMassDao.exists(id);
	}
    
    public boolean exsitWxMass(String propertyName,Object value) throws BusException{
		return wxMassDao.exists(propertyName,value);
	}

}
