/**
 * 代码声明
 */
package com.gsoft.weixin.wxmember.service.impl;

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

import com.gsoft.weixin.wxmember.entity.WxMemberGroup;
import com.gsoft.weixin.wxmember.dao.WxMemberGroupDao;
import com.gsoft.weixin.wxmember.service.WxMemberGroupManager;

@Service("wxMemberGroupManager")
@Transactional
public class WxMemberGroupManagerImpl extends BaseManagerImpl implements WxMemberGroupManager{
	@Autowired
	private WxMemberGroupDao wxMemberGroupDao;
	
    /**
     * 查询列表
     */
    public List<WxMemberGroup> getWxMemberGroups() throws BusException{
    	return wxMemberGroupDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxMemberGroup> getWxMemberGroups(
    	@ConditionCollection(domainClazz=WxMemberGroup.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxMemberGroupDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxMemberGroup getWxMemberGroup(@ServiceParam(name="id") String id)  throws BusException{
    	return wxMemberGroupDao.get(id);
    }
	
	@EsbServiceMapping
	public PagerRecords getPagerWxMemberGroups(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxMemberGroup.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxMemberGroupDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping
    public WxMemberGroup saveWxMemberGroup(WxMemberGroup o) throws BusException{
//    	String wxMemberGroupId = o.getWxMemberGroupId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxMemberGroupId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxMemberGroupDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxMemberGroup(@ServiceParam(name="id") String id) throws BusException{
    	wxMemberGroupDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMemberGroups(@ServiceParam(name="id") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxMemberGroup(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxMemberGroup(@ServiceParam(name="id") String id)  throws BusException{
		return wxMemberGroupDao.exists(id);
	}
    
    public boolean exsitWxMemberGroup(String propertyName,Object value) throws BusException{
		return wxMemberGroupDao.exists(propertyName,value);
	}

}
