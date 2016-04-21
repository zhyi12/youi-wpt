<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>

	<giui:subpage src="member/vchuang/wxarticle/editMyArticle.html?wxArticleId={wxArticleId}" subpageId="edit" 
		caption="新增文章" type="page">
	</giui:subpage>
	
	<giui:subpage src="member/vchuang/wxarticle/addMyArticle.html" subpageId="add" 
		caption="新增文章" type="page">
	</giui:subpage>
	
	<giui:grid id="grid_wxArticle" idKeys="wxArticleId,mediaId" caption="我的文章列表" 
				src="/wxArticleManager/getPagerWxArticlesByUser.json"
				removeSrc="/wxArticleManager/removeWxArticle.json">
		
		<giui:gridCol property="wxArticleTitle"  caption="文章标题"/>
		<giui:gridCol property="wxArticleAuthor"  caption="文章作者"/>
		<giui:gridCol property="createTime" type="date" format="millis" textFormat="yyyy-MM-dd HH:mm:ss" caption="创建时间"/>

		<giui:gridCol property="views"  caption="阅读次数"/>
		<giui:gridCol width="60" property="button" type="button" caption="操作">
			<giui:button name="edit" caption="编辑"/>
			<giui:button name="view" action="/member/vchuang/wxarticle/articleDistribute.html?wxArticleId={wxArticleId}" method="_target" icon="eye-open" caption="发布"/>
			<giui:button name="removeRecord" icon="remove" caption="删除"/>
		</giui:gridCol>
		
		<giui:button name="addWxArticle" caption="新增文章"></giui:button>
	</giui:grid>
	
	<!--**********************************页面函数Start********************************-->
	<!--  -->
	<youi:func name="grid_wxArticle_addWxArticle">
		$elem('subpage_add',pageId).subpage('open',{},'');
	</youi:func>
	
	<!-- 打开编辑微文章页面 -->
	<youi:func name="grid_wxArticle_edit" params="dom,options,record">
		$elem('subpage_edit',pageId).subpage('open',{},'',record);
	</youi:func>
	<!--**********************************页面函数End**********************************-->
</giui:page>