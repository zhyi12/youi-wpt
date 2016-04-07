<%@ include file="/WEB-INF/pages/include.jsp"%>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<youi:script src="/scripts/3.0/lib/highcharts.js"/>
<youi:script src="/scripts/3.0/lib/highcharts-more.js"/>


<youi:page pageId="000000">
	<!-- 页面描述：欢迎页面-->
	<!--**********************************子页面**********************************-->

	<!--**********************************子页面**********************************-->
	<youi:ajaxUrl name="addUser" src="/esb001/addUser.json"></youi:ajaxUrl>
	<youi:ajaxUrl name="removeUser" src="/esb001/removeUser.json"></youi:ajaxUrl>
	<!-- 放在页面开始处，最先执行，根据实际情况调整-->
	<youi:func name="init" urls="addUser,removeUser">
		
	</youi:func>
	
	<style>
	
		.pan{
			color:red;
			border:1px solid silver;
			padding:3px;
			margin:3px;
			border-radius:10px;
		}
		
		.c1 .pan{
			font-size:17px;
		}
		
		.c2 .pan{
			font-size:15px;
		}
	</style>
		
	<div id="page_index_container">
		<img src="https://mmbiz.qlogo.cn/mmbiz/44ibhlxaHPzn7PmJluqwkiaedOVgH8VXq94jTTb668FGxKPhVzibofX0dFLk3o7xr29ICJSHAUiaVGicOq7IhSxpaMA/0?wx_fmt=jpeg">
		<youi:panel caption="我的消息" column="6" height="150">
			<youi:form id="form_s" action="a.json">
				<youi:fieldLayout>
					<youi:fieldCalendar property="cale1" format="yyyy-MM-dd" textFormat="yyyy-MM-dd" caption="日期"></youi:fieldCalendar>
					<youi:fieldCalendar property="cale2" format="yyyy-MM-dd" textFormat="yyyy-MM-dd" caption="日期"></youi:fieldCalendar>
				</youi:fieldLayout>
			</youi:form>
			
		</youi:panel>
	</div>
	
	<!-- 监听通知变化 -->
	<youi:func name="noticereceiver_notice_afterReceive" params="active">
		//alert(active);
	</youi:func>
	
	<youi:func name="grid_user_change" params="record">
		//alert(record.loginName);
	</youi:func>
	
	<youi:func name="grid_user_beforeSubmit">
		return true;
	</youi:func>
</youi:page>