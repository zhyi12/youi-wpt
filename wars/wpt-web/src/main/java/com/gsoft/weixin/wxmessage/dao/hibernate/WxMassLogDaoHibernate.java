/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmessage.entity.WxMassLog;
import com.gsoft.weixin.wxmessage.dao.WxMassLogDao;

@Repository("WxMassLogDao")
public class WxMassLogDaoHibernate extends 
		BaseDaoHibernate<WxMassLog, String> implements WxMassLogDao{
	public Class<WxMassLog> getModelClazz(){
		return WxMassLog.class;
	}
}