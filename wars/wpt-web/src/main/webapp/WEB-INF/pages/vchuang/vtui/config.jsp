<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	<giui:form  height="300" id="form_config" submit="NOT" reset="NOT">
		<giui:fieldLayout columns="1" labelWidths="100">
			<giui:fieldHidden caption="subscriptionId" property="subscriptionId" defaultValue="${subscriptionId}"/>
			<giui:fieldLabel caption="微信发布地址" property="address"/>
			<giui:fieldLabel caption="数据接口地址" property="apiAddress"/>
		</giui:fieldLayout>
	</giui:form>
	
	<youi:func name="init">
		var formElem = $elem('form_config',pageId);
		var record = formElem.form('getFormRecord');
		record.address = window.location.href.split($.youi.serverConfig.contextPath)[0]+$.youi.serverConfig.contextPath+'weixin/'+record.subscriptionId+'/login.html';
		record.apiAddress = window.location.href.split($.youi.serverConfig.contextPath)[0]+$.youi.serverConfig.contextPath+'esb/web/';
		$elem('form_config',pageId).form('fillRecord',record);
	</youi:func>
</giui:page>