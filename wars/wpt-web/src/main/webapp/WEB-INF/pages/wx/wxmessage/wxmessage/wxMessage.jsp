<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxMessage" idKeys="messageId" caption="微信消息列表"  panel="false"
				src="esb/web/wxMessageManager/getPagerWxMessages.json" dataFormId="form_wxMessage"
				editSrc="esb/web/wxMessageManager/getWxMessage.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxMessageManager/removeWxMessage.json">
		<youi:fieldLayout>
			<youi:fieldText property="messageContent"  caption="消息内容"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="messageTitle"  caption="消息标题"/>

			<youi:fieldText property="messageType"  caption="消息类型"/>
		</youi:fieldLayout>
		<youi:gridCol property="messageContent"  caption="消息内容"/>
		<youi:gridCol property="userId"  caption="公众号主键"/>
		<youi:gridCol property="messageTitle"  caption="消息标题"/>

		<youi:gridCol property="messageType"  caption="消息类型"/>
		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-微信消息编辑 -->
	<youi:form dialog="true" caption="微信消息" id="form_wxMessage" action="esb/web/wxMessageManager/saveWxMessage.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="messageContent"  caption="消息内容"/>
			<youi:fieldText property="userId"  caption="公众号主键"/>
			<youi:fieldText property="messageTitle"  caption="消息标题"/>
			<youi:fieldText property="messageId"  caption="消息ID"/>
			<youi:fieldText property="messageType"  caption="消息类型"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>