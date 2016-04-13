<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>

	<giui:subpage src="member/vchuang/wxarticle/edit/${subscriptionId}.html" subpageId="edit" 
		caption="新增文章" type="page">
	</giui:subpage>
	
	<giui:subpage height="500" src="member/vchuang/wxarticle/view/${subscriptionId}.html?mediaId={mediaId}" subpageId="view" 
		caption="编辑文章" type="dialog">
	</giui:subpage>
	
	<giui:subpage height="500" src="member/vchuang/wxarticle/syncWxArticles/${subscriptionId}.html" subpageId="syncWxArticle" 
		caption="同步公众号文章" type="dialog">
	</giui:subpage>
		
	<giui:grid id="grid_wxArticle" idKeys="wxArticleId,mediaId" caption="微文章列表" 
				src="/wxArticleManager/getPagerWxArticles.json?subscriptionId=${subscriptionId}"
				removeSrc="/wxArticleManager/removeWxArticle.json">
		
		<giui:gridCol property="wxArticleTitle"  caption="文章标题"/>
		<giui:gridCol property="wxArticleAuthor"  caption="文章作者"/>
		<giui:gridCol property="createTime" type="date" format="millis" textFormat="yyyy-MM-dd HH:mm:ss" caption="创建时间"/>

		<giui:gridCol property="views"  caption="阅读次数"/>
		<giui:gridCol width="60" property="button" type="button" caption="操作">
			<giui:button name="edit" caption="编辑"/>
			<giui:button name="view" action="/member/vchuang/wxarticle/article/{subscriptionId}.html?wxArticleId={wxArticleId}" method="_target" icon="eye-open" caption="查看"/>
			<giui:button name="remove" caption="删除"/>
		</giui:gridCol>
		
		<giui:button name="addWxArticle" caption="新增文章"></giui:button>
		<giui:button name="syncWxArticle" caption="从公众号同步"></giui:button>
	</giui:grid>
	
	<!--**********************************页面函数Start********************************-->
	<youi:func name="grid_wxArticle_addWxArticle">
		$elem('subpage_edit',pageId).subpage('open',{},'',{});
	</youi:func>
	
	<youi:func name="grid_wxArticle_edit" params="dom,options,record">
		$elem('subpage_view',pageId).subpage('open',{},'',record);
	</youi:func>
	
	<youi:func name="grid_wxArticle_syncWxArticle" params="dom,options,record">
		$elem('subpage_syncWxArticle',pageId).subpage('open',{},'');
	</youi:func>
	
	<!--**********************************页面函数End**********************************-->
</giui:page>