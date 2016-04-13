<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<youi:html title="微文章查看">
	<head>
		<%@ include file="/WEB-INF/pages/common/giuiScriptAndCss.jsp"%>
	</head>
	<body>
		<script type="text/javascript">
			$(function(){
				var wxArticleId = '${param.wxArticleId}';
				//加载文章
				$.youi.ajaxUtils.ajax({
					url:'/esb/web/wxArticleManager/getWxArticle.json?wxArticleId='+wxArticleId,
					success:function(results){
						if(results&&results.record&&results.record.wxArticleUrl){
							window.location.href=results.record.wxArticleUrl;
						}
					}
				});
			});
		</script>
	</body>
	
</youi:html>

