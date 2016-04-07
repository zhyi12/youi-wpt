<%@ include file="/WEB-INF/pages/include.jsp" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:html i18n="i18n.index" title="微窗公众号">
	<head>
		<%response.setHeader("Cache-Control","no-cache, no-store"); %>
		<%@ include file="/WEB-INF/pages/common/giuiScriptAndCss.jsp"%>
	</head>
	
	<youi:body decorator="subscrip">
		<input type="hidden" id="subscriptionId" value="${subscriptionId}">
		<youi:func name="init">
			if(!window.location.hash){
				var subscriptionId = $('#subscriptionId:first').val();
				window.location.hash = '#p:/member/vchuang/wxsubscrip/welcome/'+subscriptionId+'.html';
			}
		</youi:func>
		
	</youi:body>
	
</youi:html>