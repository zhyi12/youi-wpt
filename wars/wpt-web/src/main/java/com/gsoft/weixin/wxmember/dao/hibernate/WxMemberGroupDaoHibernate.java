/**
 * 代码声明
 */
package com.gsoft.weixin.wxmember.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmember.entity.WxMemberGroup;
import com.gsoft.weixin.wxmember.dao.WxMemberGroupDao;

@Repository("WxMemberGroupDao")
public class WxMemberGroupDaoHibernate extends 
		BaseDaoHibernate<WxMemberGroup, String> implements WxMemberGroupDao{
	public Class<WxMemberGroup> getModelClazz(){
		return WxMemberGroup.class;
	}
}