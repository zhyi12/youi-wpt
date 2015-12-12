/**
 * 代码声明
 */
package com.gsoft.weixin.wxsubscrip.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxsubscrip.entity.WxSubscription;
import com.gsoft.weixin.wxsubscrip.dao.WxSubscriptionDao;

@Repository("WxSubscriptionDao")
public class WxSubscriptionDaoHibernate extends 
		BaseDaoHibernate<WxSubscription, String> implements WxSubscriptionDao{
	public Class<WxSubscription> getModelClazz(){
		return WxSubscription.class;
	}
}