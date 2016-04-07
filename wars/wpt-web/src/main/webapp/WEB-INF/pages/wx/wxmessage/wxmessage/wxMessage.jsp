<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	<giui:grid id="grid_wxMessage" caption="微信消息列表" 
				src="/wxMessageManager/getPagerWxMessages.json" >
		<giui:fieldLayout styleClass="col-sm-8" columns="3" labelWidths="100,100,20">
			<giui:fieldText operator="LIKE" property="messageContent"  caption="消息内容"/>
			<giui:fieldCalendar property="userId"  caption="开始日期"/>
			<giui:fieldCalendar property="messageTitle"  caption="结束日期"/>
		</giui:fieldLayout>
		
		<div class="col-sm-4">
			<button class="btn btn-default" data-name="refresh" data-command="gridCommand">查询</button>
		</div>
		
		<giui:gridCol property="messageContent"  caption="消息内容"/>
		<giui:gridCol property="userId"  caption="公众号主键"/>
		<giui:gridCol property="messageTitle"  caption="消息标题"/>

		<giui:gridCol property="messageType"  caption="消息类型"/>
		<giui:gridCol width="60" property="button" type="button" caption="操作">
			<giui:button name="edit" caption="修改"/>
			<giui:button name="remove" caption="删除"/>
		</giui:gridCol>
	</giui:grid>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</giui:page>