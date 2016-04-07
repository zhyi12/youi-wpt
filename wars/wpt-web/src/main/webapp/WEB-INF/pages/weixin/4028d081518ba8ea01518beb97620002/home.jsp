<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

header
myheader
<div id="youi_grid_list">
</div>

<style>
	.article-list-item{
		border:1px solid #C3C3C3;
		background:#fafafa;
		margin:2px 10px 10px 10px;
		min-height:120px;
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
			src:'/gsoft-web/esb/web/userManager/getPagerUsers.json',
			template:'<div class="article-list-title">{userCaption}</div>'
					+'<div class="article-list-content"><img width="100%" src="http://www.ivtuiguang.com/fs/8a2d367551687e23015168a3be8c0070/image/20160302/7371456931006199.jpg"/></div>'
					+'<div class="article-list-footer"><div class="list-cell cell-x2"><img class="user-header" width="30" src="/gsoft-web/styles/images/user.jpg"/>{userCaption}</div><div class="list-cell"><span class="youi-icon icon-yen"></span>0.07元/次</div><div class="list-cell"><span class="youi-icon icon-eye-open"></span>12</div></div>'
		});
	});
//-->
</script>
