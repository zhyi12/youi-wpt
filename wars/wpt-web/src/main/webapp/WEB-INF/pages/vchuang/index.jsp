<%@ include file="/WEB-INF/pages/include.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:html i18n="i18n.index" title="微窗个人中心">
	<head>
		<%response.setHeader("Cache-Control","no-cache, no-store"); %>
		<%@ include file="/WEB-INF/pages/common/giuiScriptAndCss.jsp"%>
	</head>
	
	<youi:body decorator="giui">
		<youi:func name="init">
			if(!window.location.hash){
				window.location.hash = '#p:page/vchuang/welcome.html';
			}
		</youi:func>
	</youi:body>
	
</youi:html>