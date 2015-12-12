/**
 * 代码声明
 */
package com.gsoft.weixin.wxmessage.dao.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import com.gsoft.weixin.wxmessage.entity.WxMedia;
import com.gsoft.weixin.wxmessage.dao.WxMediaDao;

@Repository("WxMediaDao")
public class WxMediaDaoHibernate extends 
		BaseDaoHibernate<WxMedia, String> implements WxMediaDao{
	public Class<WxMedia> getModelClazz(){
		return WxMedia.class;
	}
}