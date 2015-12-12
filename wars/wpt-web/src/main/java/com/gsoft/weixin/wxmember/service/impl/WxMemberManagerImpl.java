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
import com.gsoft.weixin.wxmember.entity.WxMember;
import com.gsoft.weixin.wxmember.dao.WxMemberDao;
import com.gsoft.weixin.wxmember.service.WxMemberManager;

@Service("wxMemberManager")
@Transactional
public class WxMemberManagerImpl extends BaseManagerImpl implements WxMemberManager{
	@Autowired
	private WxMemberDao wxMemberDao;
	
    /**
     * 查询列表
     */
    public List<WxMember> getWxMembers() throws BusException{
    	return wxMemberDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
    public List<WxMember> getWxMembers(
    	@ConditionCollection(domainClazz=WxMember.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxMemberDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxMember getWxMember(@ServiceParam(name="openid") String id)  throws BusException{
    	return wxMemberDao.get(id);
    }
	
	@EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
	public PagerRecords getPagerWxMembers(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxMember.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxMemberDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping(pubConditions=@PubCondition(property="userId",pubProperty="userId"))
    public WxMember saveWxMember(WxMember o) throws BusException{
//    	String wxMemberId = o.getWxMemberId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxMemberId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxMemberDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxMember(@ServiceParam(name="openid") String id) throws BusException{
    	wxMemberDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMembers(@ServiceParam(name="openid") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxMember(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxMember(@ServiceParam(name="openid") String id)  throws BusException{
		return wxMemberDao.exists(id);
	}
    
    public boolean exsitWxMember(String propertyName,Object value) throws BusException{
		return wxMemberDao.exists(propertyName,value);
	}

}
