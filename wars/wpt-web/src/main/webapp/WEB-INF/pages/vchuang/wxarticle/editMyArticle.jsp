<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page dataSrc="/wxArticleManager/getWxArticleEditorHtml.json?wxArticleId=${param.wxArticleId}">
	
	<youi:script src="/scripts/3.0/giui/field/field.wxarticle.js"/>
	<youi:script src="/scripts/ueditor/ueditor.config.js"/>
	<youi:script src="/scripts/ueditor/ueditor.all.min.js"/>
	
	<giui:form id="form_article" caption="微文章编辑" action="/wxArticleManager/saveMyWxArticle.json" reset="NOT" submit="保存文章">
		<giui:fieldLayout columns="1" labelWidths="0">
			<giui:fieldHidden property="wxArticleId" defaultValue="${param.wxArticleId}"/>
			<giui:fieldCustom custom="fieldWxArticle" property="wxArticle" />
		</giui:fieldLayout>
		
		<giui:button name="preview" caption="预览" order="101"/>
		<giui:button name="publishToSubcr" caption="发布到公众号" order="102"/>
		
		<giui:button name="goback" icon="back" caption="返回文章列表" order="103"/>
	</giui:form>
	<!--**********************************页面函数Start********************************-->
	<youi:func name="init" params="results">
		$elem('field_wxArticle',pageId).fieldWxArticle({
			
		}).fieldWxArticle('fillWxArticle',results.record.html);
	</youi:func>
	<!--**********************************页面函数End**********************************-->
</giui:page>
