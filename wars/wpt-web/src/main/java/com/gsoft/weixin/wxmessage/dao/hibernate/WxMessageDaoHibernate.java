/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmessage.entity.WxMessage;
import com.gsoft.weixin.wxmessage.dao.WxMessageDao;

@Repository("WxMessageDao")
public class WxMessageDaoHibernate extends 
		BaseDaoHibernate<WxMessage, String> implements WxMessageDao{
	public Class<WxMessage> getModelClazz(){
		return WxMessage.class;
	}
}