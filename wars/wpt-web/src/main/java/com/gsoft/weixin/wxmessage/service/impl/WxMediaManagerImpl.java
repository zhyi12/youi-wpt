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

import com.gsoft.weixin.wxmessage.entity.WxMedia;
import com.gsoft.weixin.wxmessage.dao.WxMediaDao;
import com.gsoft.weixin.wxmessage.service.WxMediaManager;

@Service("wxMediaManager")
@Transactional
public class WxMediaManagerImpl extends BaseManagerImpl implements WxMediaManager{
	@Autowired
	private WxMediaDao wxMediaDao;
	
    /**
     * 查询列表
     */
    public List<WxMedia> getWxMedias() throws BusException{
    	return wxMediaDao.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<WxMedia> getWxMedias(
    	@ConditionCollection(domainClazz=WxMedia.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return wxMediaDao.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public WxMedia getWxMedia(@ServiceParam(name="mediaId") String id)  throws BusException{
    	return wxMediaDao.get(id);
    }
	
	@EsbServiceMapping
	public PagerRecords getPagerWxMedias(Pager pager,//分页条件
			@ConditionCollection(domainClazz=WxMedia.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = wxMediaDao.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping
    public WxMedia saveWxMedia(WxMedia o) throws BusException{
//    	String wxMediaId = o.getWxMediaId();
//    	boolean isUpdate = StringUtils.isNotEmpty(wxMediaId);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return wxMediaDao.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void removeWxMedia(@ServiceParam(name="mediaId") String id) throws BusException{
    	wxMediaDao.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMedias(@ServiceParam(name="mediaId") String[] ids)  throws BusException{
   		for(String id:ids){
    		removeWxMedia(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsitWxMedia(@ServiceParam(name="mediaId") String id)  throws BusException{
		return wxMediaDao.exists(id);
	}
    
    public boolean exsitWxMedia(String propertyName,Object value) throws BusException{
		return wxMediaDao.exists(propertyName,value);
	}

}
