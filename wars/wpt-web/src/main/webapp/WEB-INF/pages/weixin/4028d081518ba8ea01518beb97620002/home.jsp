<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div id="youi_grid_list">
</div>

<style>
	.article-list-item{
		border:1px solid #C3C3C3;
		background:#fafafa;
		margin:2px 10px 10px 10px;
		border-radius:5px;
	}
	
	.article-list-item .article-list-title{
		padding:5px;
	}
	
	.article-list-item .article-list-content{
		padding:2px;
		background:white;
		float:left;
		width:100%;
	}
	
	.article-list-item .article-list-footer{
		line-height:36px;
		valign-text:middle;
		display: -webkit-box;
	    display: -webkit-flex;
	    display: -ms-flexbox;
		display: flex;
		padding:3px;
	}
	
	.list-cell{
		-webkit-box-flex: 1;
	    -webkit-flex: 1;
	    -ms-flex: 1;
	    flex: 1;
	}
	
	.list-cell.cell-x2{
		-webkit-box-flex: 2;
	    -webkit-flex: 2;
	    -ms-flex: 2;
	    flex: 2;
	}
	
	.user-header{
		border-radius:15px;
		border:1px solid silver;
		margin-right:5px;
	}
</style>

<script type="text/javascript">
<!--
	$(function(){
		$('#youi_grid_list').gridList({
			rowStyle:'article-list-item',
			src:$.youi.serverConfig.contextPath+'esb/web/wxArticleManager/getPagerWxArticles.json?subscriptionId=${subscriptionId}',
			template:'<a class="article-list-title page-link" href="{_contextPath}member/vchuang/wxarticle/copy2MyArticle/{subscriptionId}.html?wxArticleId={wxArticleId}">{wxArticleTitle} {createTime}'
					+'<div class="article-list-content">{wxArticleSummary}</div></a>'
					+'<div class="article-list-footer"></div>'
		});
	});
//-->
</script>
