<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	<youi:ajaxUrl name="remove" src=""></youi:ajaxUrl>

	<!-- 编辑框 -->
	<giui:subpage src="member/vchuang/wxsubscrip/bind.html?id={id}" subpageId="edit" caption="微信绑定" 
		type="page">
	</giui:subpage>
	
	<!-- 新增框 -->
	<giui:subpage src="member/vchuang/wxsubscrip/bind.html" subpageId="bind" caption="微信编辑" 
		type="page">
	</giui:subpage>
		
	<giui:grid id="grid_wxSubscription" caption="我的公众号列表" idKeys="subscriptionId"
				removeSrc="/wxSubscriptionManager/removeWxSubscription.json"
				src="/wxSubscriptionManager/getPagerWxSubscriptionsByUser.json">
				
		<giui:button name="bindWxSubscrip" caption="绑定微信公众号"></giui:button>
		
		<giui:gridCol property="appId"  caption="开发者ID"/>
		<giui:gridCol property="appSecret"  caption="开发者密钥"/>
		<giui:gridCol property="subscriptionCaption"  caption="公众号描述"/>
		<giui:gridCol property="loginName"  caption="公众号编号"/>

		<giui:gridCol width="60" property="button" type="button" caption="操作">
			<giui:button action="/member/vchuang/wxsubscrip/index/{subscriptionId}.html" method="_blank" name="config" icon="external-link" caption="配置"/>
			
			<giui:button name="editWxSubscrip" icon="edit" caption="编辑"></giui:button>
		
			<giui:button name="removeRecord" icon="remove" caption="删除" disableProperty="appId"/>
		</giui:gridCol>
	</giui:grid>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--  -->
	<youi:func name="grid_wxSubscription_bindWxSubscrip">
		$elem('subpage_bind',pageId).subpage('open',{},'',{});
	</youi:func>
	
	<youi:func name="grid_wxSubscription_editWxSubscrip"  params="dom,options,record">
		$elem('subpage_edit',pageId).subpage('open',{},'',{id:record.subscriptionId});
	</youi:func>
	
	
	<!--**********************************页面函数End**********************************-->
</giui:page>