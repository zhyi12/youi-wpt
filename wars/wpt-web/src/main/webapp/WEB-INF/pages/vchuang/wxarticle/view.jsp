<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page dataSrc="/esb/weixinAdapter/wxHttpServices/getMaterial.json?subscriptionId=${subscriptionId}&media_id=${param.mediaId}">
	
	<giui:form panel="false" caption="微文章" id="form_wxArticle" reset="NOT" submit="NOT">
		<giui:fieldLayout prefix="record" columns="1">
			<giui:fieldText property="title"  caption="文章标题"/>
			<giui:fieldText property="author"  caption="文章作者"/>
			<giui:fieldText property="digest"  caption="文章摘要"/>
			<giui:fieldText property="content"  caption="文章内容"/>
		</giui:fieldLayout>
	</giui:form>
	
	<youi:func name="init" params="results">
		if(results&&results.record&&results.record.news_item){
			var item = results.record.news_item[0]||{};
			$elem('form_wxArticle',pageId).form('fillRecord',item);
		}
	</youi:func>
	
</giui:page>