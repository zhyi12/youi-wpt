<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxSubscription" idKeys="userId" caption="微信公众号用户列表"  panel="false"
				src="esb/web/wxSubscriptionManager/getPagerWxSubscriptions.json" dataFormId="form_wxSubscription"
				editSrc="esb/web/wxSubscriptionManager/getWxSubscription.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxSubscriptionManager/removeWxSubscription.json">
		<youi:fieldLayout>
			<youi:fieldText property="userCaption"  caption="用户描述"/>
			<youi:fieldText property="password"  caption="登录密码"/>
			<youi:fieldText property="appSecret"  caption="开发者密钥"/>
			<youi:fieldText property="appId"  caption="开发者ID"/>
			<youi:fieldText property="subscriptionCaption"  caption="公众号描述"/>
			<youi:fieldText property="loginName"  caption="登录用户"/>

		</youi:fieldLayout>
		<youi:gridCol property="userCaption"  caption="用户描述"/>
		<youi:gridCol property="password"  caption="登录密码"/>
		<youi:gridCol property="appSecret"  caption="开发者密钥"/>
		<youi:gridCol property="appId"  caption="开发者ID"/>
		<youi:gridCol property="subscriptionCaption"  caption="公众号描述"/>
		<youi:gridCol property="loginName"  caption="登录用户"/>

		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-微信公众号用户编辑 -->
	<youi:form dialog="true" caption="微信公众号用户" id="form_wxSubscription" action="esb/web/wxSubscriptionManager/saveWxSubscription.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="userCaption"  caption="用户描述"/>
			<youi:fieldText property="password"  caption="登录密码"/>
			<youi:fieldText property="appSecret"  caption="开发者密钥"/>
			<youi:fieldText property="appId"  caption="开发者ID"/>
			<youi:fieldText property="subscriptionCaption"  caption="公众号描述"/>
			<youi:fieldText property="loginName"  caption="登录用户"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>