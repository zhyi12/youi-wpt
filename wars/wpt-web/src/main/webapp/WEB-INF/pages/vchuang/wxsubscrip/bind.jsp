<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	<giui:form id="bind" idKeys="appid"
		caption="微信绑定" submit="绑定公众号" action="/wxSubscriptionManager/bindWxSubscription111.json">
		<giui:fieldLayout columns="1" labelWidths="120">
			<giui:fieldText 
				notNull="true" property="subscriptionCaption" caption="应用名称"/>
			<giui:fieldText notNull="true" property="appId" caption="应用ID"/>
			
			<giui:fieldImgupload property="img" fileQueueLimit="3" caption="商品图"/>
			
			<giui:fieldFile property="file" caption="文件"/>
			
			<giui:fieldLabel property="img1" format="millis" textFormat="yyyy年 MM月 dd日" caption="商品图"/>
			
			<giui:fieldSelect property="bo" caption="boolean" convert="boolean"/>
			
			<giui:fieldSelect property="bo1" caption="boolean" convert="boolean"/>
			
			<giui:fieldPassword notNull="true" property="appSecret" caption="应用密钥"/>
		</giui:fieldLayout>
		
		<giui:button name="goback" icon="back" caption="返回" order="101"/>
		
	</giui:form>
	
	<youi:func name="init" params="results">
		//console.log(results);

		$elem('bind',pageId).form('fillRecord',{
			img1:'1459220313196',
			img:['201603/929f327c-c4b8-4976-87f5-218b00a6ea6a.jpg','201603/5a36cf4d-5158-4f10-b0f3-2c277c91686b.png']
		});

	</youi:func>
	
	<youi:func name="bind_afterSubmit">
		$.youi.pageUtils.goBackPage();
	</youi:func>
	
	<youi:func name="field_bo_change" params="value">
		
	</youi:func>
	
	<!-- 校验回调函数 -->
	<youi:func name="field_subscriptionCaption_validate" params="params">
		
	</youi:func>
</giui:page>