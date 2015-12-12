<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxMassLog" idKeys="massId" caption="群发记录列表"  panel="false"
				src="esb/web/wxMassLogManager/getPagerWxMassLogs.json" dataFormId="form_wxMassLog"
				editSrc="esb/web/wxMassLogManager/getWxMassLog.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxMassLogManager/removeWxMassLog.json">
		<youi:fieldLayout>
			<youi:fieldText property="massStatus"  caption="群发状态"/>
			<youi:fieldText property="massDate"  caption="群发日期"/>
			<youi:fieldText property="massTime"  caption="群发时间"/>

		</youi:fieldLayout>
		<youi:gridCol property="massStatus"  caption="群发状态"/>
		<youi:gridCol property="massDate"  caption="群发日期"/>
		<youi:gridCol property="massTime"  caption="群发时间"/>

		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-群发记录编辑 -->
	<youi:form dialog="true" caption="群发记录" id="form_wxMassLog" action="esb/web/wxMassLogManager/saveWxMassLog.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="massStatus"  caption="群发状态"/>
			<youi:fieldText property="massDate"  caption="群发日期"/>
			<youi:fieldText property="massTime"  caption="群发时间"/>
			<youi:fieldText property="massId"  caption="群发ID"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>