/**
 * 代码声明
 */
package com.gsoft.weixin.wxmember.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmember.entity.WxMember;
import com.gsoft.weixin.wxmember.dao.WxMemberDao;

@Repository("WxMemberDao")
public class WxMemberDaoHibernate extends 
		BaseDaoHibernate<WxMember, String> implements WxMemberDao{
	public Class<WxMember> getModelClazz(){
		return WxMember.class;
	}
}