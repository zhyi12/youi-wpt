/**
 * 代码声明
 */
package com.gsoft.weixin.wxsubscrip.service;

import java.util.Collection;
import java.util.List;

import com.gsoft.esb.weixin.service.WxSubscriptionFinder;
import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;
import com.gsoft.framework.core.service.BaseManager;
import com.gsoft.weixin.wxsubscrip.entity.WxSubscription;

public interface WxSubscriptionManager extends BaseManager,WxSubscriptionFinder{

    /**
     * 查询列表
     */
    public List<WxSubscription> getWxSubscriptions() throws BusException;
    
    /**
     * 条件查询列表
     */
    public List<WxSubscription> getWxSubscriptions(Collection<Condition> conditions,Collection<Order> orders) throws BusException;
    /**
     * 根据主键查询
     */
    public WxSubscription getWxSubscription(String id) throws BusException;
	/**
	 * 分页查询用户
	 * @return 分页对象
	 */
	public PagerRecords getPagerWxSubscriptions(Pager pager,//分页条件
			Collection<Condition> conditions,//查询条件
			Collection<Order> orders) throws BusException;
    /**
     * 保存并返回对象
     */
    public WxSubscription saveWxSubscription(WxSubscription o) throws BusException;

    /**
     * 删除对象
     */
    public void removeWxSubscription(String id) throws BusException;
    
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxSubscriptions(String[] ids) throws BusException;
    
     /**
     * 主键是否已经使用
     * @param ids
     */
    public boolean exsitWxSubscription(String id) throws BusException;
	/**
     * 属性值是否已经使用
     * @param ids
     */
	public boolean exsitWxSubscription(String propertyName,Object value) throws BusException;
}
