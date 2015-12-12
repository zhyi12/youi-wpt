/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmessage.entity.WxCustomMessage;
import com.gsoft.weixin.wxmessage.dao.WxCustomMessageDao;

@Repository("WxCustomMessageDao")
public class WxCustomMessageDaoHibernate extends 
		BaseDaoHibernate<WxCustomMessage, String> implements WxCustomMessageDao{
	public Class<WxCustomMessage> getModelClazz(){
		return WxCustomMessage.class;
	}
}