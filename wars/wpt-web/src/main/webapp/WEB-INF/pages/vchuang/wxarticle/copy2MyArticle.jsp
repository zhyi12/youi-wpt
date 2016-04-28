<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<youi:html title="生成我的文章">
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
		<%@ include file="/WEB-INF/pages/common/giuiScriptAndCss.jsp"%>
	</head>
	<body>
	
		<youi:ajaxUrl name="addNews" src="/esb/weixinAdapter/wxHttpServices/addNews.json"/>
		
		<div id="wx_article_editor" class="wx-article-editor" style="margin-top:10px;padding:5px;">
			
		</div>

		<button id="make_my_article" class="btn btn-default" style="width:90%;margin-left:5%;margin-bottom:5px;">生成我的文章</button>
		
		<script type="text/javascript">
			$(function(){
				var wxArticleId = '${param.wxArticleId}';
				var subscriptionId = '${subscriptionId}';
				//加载文章
				$.youi.ajaxUtils.ajax({
					url:'/esb/web/wxArticleManager/getWxArticleEditorHtml.json?wxArticleId='+wxArticleId,
					success:function(results){
						if(results&&results.record&&results.record.html){
							$('#wx_article_editor').append(results.record.html);
						}
					}
				});
				
				//获取封面图片
				$.youi.ajaxUtils.ajax({
					url:'/esb/web/wxArticleManager/getArticleThumbPath.json?wxArticleId='+wxArticleId,
					success:function(results){
						if(results&&results.record&&results.record.html){
							var imgPath = $.youi.serverConfig.contextPath+'upload/download/'+results.record.html+'.html';
							$('#wx_article_editor').prepend('<img class="wx-article-thumb" height="60px" src="'+imgPath+'"/>');
						}
					}
				});
				
				//绑定事件
				$('#make_my_article').bind('click',function(){
					//保存文章
					var articleRecord = {
							wxArticleId:wxArticleId,
							wxArticleTitle:$('#wx_article_editor>.wx-article-title').text(),
							wxArticleAuthor:$('#wx_article_editor>.wx-article-author').text(),
							wxArticleSummary:$('#wx_article_editor>.wx-article-digest').text(),
							wxArticleContent:$('#wx_article_editor>.wx-article-content').html()
						};
					//保存为我的文章
					$.youi.ajaxUtils.ajax({
						url:'/esb/web/wxArticleManager/copyToUserWxArticle.json',
						data:$.youi.parameterUtils.toParams($.youi.recordUtils.recordToParameters(articleRecord)).join('&'),
						success:function(results){
							//打开我的文章
							if(results&&results.record&&results.record.wxArticleId){
								$.youi.pageUtils.goPage($.youi.serverConfig.contextPath+'member/vchuang/wxarticle/articleDistribute/${subscriptionId}.html?wxArticleId='+results.record.wxArticleId);
							}
						}
					});
				});
			});
		</script>
	</body>
	
</youi:html>

