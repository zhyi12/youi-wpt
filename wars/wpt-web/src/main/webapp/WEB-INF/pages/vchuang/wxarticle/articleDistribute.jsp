<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<youi:html title="微文章转发">
	<head>
		<%response.setHeader("Cache-Control","no-cache, no-store"); %>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
		<%@ include file="/WEB-INF/pages/common/giuiScriptAndCss.jsp"%>
	</head>
	<body  style="margin:10px;">
	
		<youi:ajaxUrl name="addNews" src="/esb/weixinAdapter/wxHttpServices/addNews.json"/>
		
		<div id="wx_article_viewer" class="wx-article-editor">
			
		</div>

		<button class="btn btn-default" style="width:90%;margin-left:5%;margin-bottom:5px;">发送给朋友</button>
		<giui:gridList id="gridlist_subscription" src="/wxSubscriptionManager/getPagerWxSubscriptionsByUser.json">
			<button data-subscription-id="{subscriptionId}" data-wx-article-id="${param.wxArticleId}" 
				data-command="gridlistCommand" data-name="distribute" class="btn btn-default btn-phone" 
				style="width:90%;margin-left:5%;margin-bottom:5px;">
				<span class="youi-icon icon-user"></span>转发到{subscriptionCaption}
			</button>
		</giui:gridList>
		
		
		<!-- 文章转发 -->
		<youi:func name="gridlist_subscription_distribute" params="dom,options" urls="addNews">
			var ariticleRecord = {
				subscriptionId:options.subscriptionId,
				wxArticleId:options.wxArticleId,
				title:'',
				author:'',
				content:''
			};

			//文章转发判断
			$.youi.ajaxUtils.ajax({
				url:'/esb/web/wxArticleManager/getWxArticle.json',
				data:$.youi.parameterUtils.toParams($.youi.recordUtils.recordToParameters(ariticleRecord)).join('&'),
				success:function(results){
					if(results.record){
						//转发到微信平台
						//ariticleRecord.title = results.record.wxArticleAuthor;
						//ariticleRecord.author = results.record.wxArticleAuthor
						//ariticleRecord.content = results.record.wxArticleAuthor
					}
				}
			});
		</youi:func>
		
		<script type="text/javascript">
			$(function(){
				var wxArticleId = '${param.wxArticleId}';
				//加载文章
				$.youi.ajaxUtils.ajax({
					url:'/esb/web/wxArticleManager/getWxArticleEditorHtml.json?wxArticleId='+wxArticleId,
					success:function(results){
						if(results&&results.record&&results.record.html){
							$('#wx_article_viewer').html(results.record.html).find('[contenteditable]').removeAttr('contenteditable');
						}
					}
				});
			});
		</script>
	</body>
	
</youi:html>

