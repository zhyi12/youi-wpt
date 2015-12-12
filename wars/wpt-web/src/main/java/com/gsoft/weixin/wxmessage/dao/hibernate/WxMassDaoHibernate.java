/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmessage.entity.WxMass;
import com.gsoft.weixin.wxmessage.dao.WxMassDao;

@Repository("WxMassDao")
public class WxMassDaoHibernate extends 
		BaseDaoHibernate<WxMass, String> implements WxMassDao{
	public Class<WxMass> getModelClazz(){
		return WxMass.class;
	}
}