<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_${modelName}" idKeys="${id}" caption="${modelDescription}列表"  panel="false"
				src="esb/web/${modelName}Manager/getPager${modelClassName}s.json" dataFormId="form_${modelName}"
				editSrc="esb/web/${modelName}Manager/get${modelClassName}.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/${modelName}Manager/remove${modelClassName}.json">
		<youi:fieldLayout>
<#list attributes as attribute>
	<#if attribute.id!="true">
			<youi:fieldText property="${attribute.name}"  caption="${attribute.description}"/></#if>
</#list>
		</youi:fieldLayout>
<#list attributes as attribute>
	<#if attribute.id!="true">
		<youi:gridCol property="${attribute.name}"  caption="${attribute.description}"/></#if>
</#list>
		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-${modelDescription}编辑 -->
	<youi:form dialog="true" caption="${modelDescription}" id="form_${modelName}" action="esb/web/${modelName}Manager/save${modelClassName}.json">
		<youi:fieldLayout prefix="record">
<#list attributes as attribute>
			<youi:fieldText property="${attribute.name}"  caption="${attribute.description}"/>
</#list>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>