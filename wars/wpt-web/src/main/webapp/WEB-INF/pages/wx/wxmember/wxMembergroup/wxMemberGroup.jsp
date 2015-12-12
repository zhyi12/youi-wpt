<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxMemberGroup" idKeys="id" caption="粉丝分组列表"  panel="false"
				src="esb/web/wxMemberGroupManager/getPagerWxMemberGroups.json" dataFormId="form_wxMemberGroup"
				editSrc="esb/web/wxMemberGroupManager/getWxMemberGroup.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxMemberGroupManager/removeWxMemberGroup.json">
		<youi:fieldLayout>

			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="name"  caption="分组名"/>
		</youi:fieldLayout>

		<youi:gridCol property="userId"  caption="公众号主键"/>
		<youi:gridCol property="name"  caption="分组名"/>
		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-粉丝分组编辑 -->
	<youi:form dialog="true" caption="粉丝分组" id="form_wxMemberGroup" action="esb/web/wxMemberGroupManager/saveWxMemberGroup.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="id"  caption="分组ID"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="name"  caption="分组名"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>