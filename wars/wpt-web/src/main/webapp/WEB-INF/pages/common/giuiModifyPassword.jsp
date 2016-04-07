<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	<giui:fieldLayout columns="1" showTooltips="true" labelWidths="100">
		<giui:fieldPassword notNull="true" property="password" caption="新密码" tooltips="6-20位字符"/>
		<giui:fieldPassword notNull="true" property="confirmPassword" caption="密码确认" tooltips="6-20位字符,和新密码一致"/>
		<giui:fieldPassword notNull="true" property="oldPassword" caption="旧密码" tooltips="6-20位字符"/>
	</giui:fieldLayout>
</giui:page>