<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	
	<youi:form id="form_customMessage" caption="链接消息" action="esb/weixinAdapter/wxHttpServices/sendCustomMessage.json">
		<youi:fieldLayout columns="1">
			<youi:fieldText property="title" caption="title"></youi:fieldText>
			<youi:fieldText property="description" caption="description"></youi:fieldText>
			<youi:fieldText property="url" caption="url"></youi:fieldText>
			<youi:fieldText property="picurl" caption="picurl"></youi:fieldText>
			<youi:fieldText property="touser" caption="touser"></youi:fieldText>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	<youi:form id="form_upload" caption="图片上传" submit="NOT" reset="NOT" height="230">
		<youi:fieldLayout>
			<youi:fieldSwfupload property="media" column="2" caption="图片" height="200"/>
		</youi:fieldLayout>
	</youi:form>
	<!--**********************************页面函数End**********************************-->
</youi:page>