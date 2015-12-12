<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxCustomMessage" idKeys="customeMsgId" caption="客服消息记录列表"  panel="false"
				src="esb/web/wxCustomMessageManager/getPagerWxCustomMessages.json" dataFormId="form_wxCustomMessage"
				editSrc="esb/web/wxCustomMessageManager/getWxCustomMessage.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxCustomMessageManager/removeWxCustomMessage.json">
		<youi:fieldLayout>
			<youi:fieldText property="msgSendTime"  caption="发送时间"/>
			<youi:fieldText property="msgStatus"  caption="消息状态"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="openid"  caption="接收者微信号"/>

		</youi:fieldLayout>
		<youi:gridCol property="msgSendTime"  caption="发送时间"/>
		<youi:gridCol property="msgStatus"  caption="消息状态"/>
		<youi:gridCol property="userId"  caption="公众号主键"/>
		<youi:gridCol property="openid"  caption="接收者微信号"/>

		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-客服消息记录编辑 -->
	<youi:form dialog="true" caption="客服消息记录" id="form_wxCustomMessage" action="esb/web/wxCustomMessageManager/saveWxCustomMessage.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="msgSendTime"  caption="发送时间"/>
			<youi:fieldText property="msgStatus"  caption="消息状态"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="openid"  caption="接收者微信号"/>
			<youi:fieldText property="customeMsgId"  caption="客服消息ID"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>