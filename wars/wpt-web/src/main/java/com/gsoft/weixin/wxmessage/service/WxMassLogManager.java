/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.service;

import java.util.List;
import java.util.Collection;

import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;
import com.gsoft.framework.core.service.BaseManager;

import com.gsoft.weixin.wxmessage.entity.WxMassLog;

public interface WxMassLogManager extends BaseManager{

    /**
     * 查询列表
     */
    public List<WxMassLog> getWxMassLogs() throws BusException;
    
    /**
     * 条件查询列表
     */
    public List<WxMassLog> getWxMassLogs(Collection<Condition> conditions,Collection<Order> orders) throws BusException;
    /**
     * 根据主键查询
     */
    public WxMassLog getWxMassLog(String id) throws BusException;
	/**
	 * 分页查询用户
	 * @return 分页对象
	 */
	public PagerRecords getPagerWxMassLogs(Pager pager,//分页条件
			Collection<Condition> conditions,//查询条件
			Collection<Order> orders) throws BusException;
    /**
     * 保存并返回对象
     */
    public WxMassLog saveWxMassLog(WxMassLog o) throws BusException;

    /**
     * 删除对象
     */
    public void removeWxMassLog(String id) throws BusException;
    
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void removeWxMassLogs(String[] ids) throws BusException;
    
     /**
     * 主键是否已经使用
     * @param ids
     */
    public boolean exsitWxMassLog(String id) throws BusException;
	/**
     * 属性值是否已经使用
     * @param ids
     */
	public boolean exsitWxMassLog(String propertyName,Object value) throws BusException;
}
