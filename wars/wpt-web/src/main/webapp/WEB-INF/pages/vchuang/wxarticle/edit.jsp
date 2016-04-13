<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	
	<youi:ajaxUrl name="addNews" src="/esb/weixinAdapter/wxHttpServices/addNews.json"></youi:ajaxUrl>
	<youi:ajaxUrl name="saveWxArticle" src="/wxArticleManager/saveWxArticle.json"></youi:ajaxUrl>
	
	<giui:form  caption="微文章" id="form_wxArticle" action="/esb/weixinAdapter/wxMediaServices/image.json">
		<giui:fieldLayout prefix="record" columns="1">
			<giui:fieldHidden property="subscriptionId" defaultValue="${subscriptionId}"></giui:fieldHidden>
			<giui:fieldText property="title"  caption="文章标题"/>
			<giui:fieldText property="author"  caption="文章作者"/>
			<giui:fieldText property="digest"  caption="文章摘要"/>
			<giui:fieldText property="content"  caption="文章内容"/>
			<giui:fieldImgupload property="media" caption="封面" fileQueueLimit="1"/>
		</giui:fieldLayout>
		
		<giui:button name="goback" icon="back" caption="返回" order="101"/>
	</giui:form>
	
	<youi:func name="init" params="results">
		
	</youi:func>
	<!-- 封面素材上传成功后调用 -->
	<youi:func name="form_wxArticle_afterSubmit" params="results" urls="addNews,saveWxArticle">
		var formRecord = $(this).form('getFormRecord');
		if(results&&results.record&&results.record.media_id){
			//上传文章素材到微信公众号服务器
			formRecord.thumb_media_id = results.record.media_id;
			$.youi.ajaxUtils.ajax({
				url:funcUrls.addNews,
				data:$.youi.parameterUtils.toParams($.youi.recordUtils.recordToParameters(formRecord)).join('&'),
				success:function(addNewsResults){
					if(addNewsResults&&addNewsResults.record&&addNewsResults.record.media_id){
						var wxArticle  = {
							wxArticleAuthor:formRecord.author,
							wxArticleTitle:formRecord.title,
							wxArticleSummary:formRecord.digest,
							mediaId:addNewsResults.record.media_id,
							subscriptionId:formRecord.subscriptionId
						};

						//保存到当前服务器文章表
						$.youi.ajaxUtils.ajax({
							url:funcUrls.saveWxArticle,
							data:$.youi.parameterUtils.toParams($.youi.recordUtils.recordToParameters(wxArticle)).join('&'),
							success:function(wxArticleResults){
							}
						});
					}
				}
			});
		}
		
	</youi:func>
	
</giui:page>