<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxMedia" idKeys="mediaId" caption="图文素材列表"  panel="false"
				src="esb/weixinAdapter/wxHttpServices/batchgetMaterial.json" dataFormId="form_wxMedia"
				editSrc="esb/web/wxMediaManager/getWxMedia.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxMediaManager/removeWxMedia.json">
		<youi:fieldLayout>
			<youi:fieldText property="mediaUrl"  caption="素材URL"/>
			<youi:fieldText property="mediaType"  caption="素材类型"/>

			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="mediaCaption"  caption="素材名称"/>
		</youi:fieldLayout>
		<youi:gridCol property="mediaUrl"  caption="素材URL"/>
		<youi:gridCol property="mediaType"  caption="素材类型"/>

		<youi:gridCol property="userId"  caption="公众号主键"/>
		<youi:gridCol property="mediaCaption"  caption="素材名称"/>
		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-图文素材编辑 -->
	<youi:form dialog="true" caption="图文素材" id="form_wxMedia" action="esb/web/wxMediaManager/saveWxMedia.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="mediaUrl"  caption="素材URL"/>
			<youi:fieldText property="mediaType"  caption="素材类型"/>
			<youi:fieldText property="mediaId"  caption="素材ID"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="mediaCaption"  caption="素材名称"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>