<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	
	<youi:ajaxUrl name="uploadnews" src="esb/weixinAdapter/wxHttpServices/uploadnews.json"/>
	
	<youi:form id="form_upload" caption="链接消息" action="esb/weixinAdapter/wxUploadServices/mediaUpload.json">
		<youi:fieldLayout>
			<youi:fieldSwfupload property="media" column="2" caption="图片" height="240"/>
			
			<youi:fieldArea property="title" column="2" caption="标题"></youi:fieldArea>
			<youi:fieldText property="url" column="2" caption="链接"></youi:fieldText>
		</youi:fieldLayout>
	</youi:form>
	
	<youi:form id="form_massPreview" action="esb/weixinAdapter/wxHttpServices/massPreview.json"  caption="预览消息">
		<youi:fieldLayout>
			<youi:fieldText notNull="true" property="touser" caption="touser"/>
			<youi:fieldText notNull="true" property="media_id" caption="media_id"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	<youi:func name="form_upload_afterSubmit" params="results" urls="uploadnews">
		if(funcUrls.uploadnews&&results&&results.record&&results.record.media_id){
			var params = [];
			var formRecord = $elem('form_upload',pageId).form('getFormRecord');
			
			params.push('articles[0].thumb_media_id='+results.record.media_id);
			params.push('articles[0].content='+'mytext');

			//
			$.youi.ajaxUtil.ajax({
				url:funcUrls.uploadnews,
				data: params.join('&'),
				success:function(results){
					//预览功能
					//var viewParams = ['touser'];//media_id
					$elem('form_massPreview',pageId).form('fillRecord',results.record).show();
				}
			});
		}
	</youi:func>
	<!--**********************************页面函数End**********************************-->
</youi:page>