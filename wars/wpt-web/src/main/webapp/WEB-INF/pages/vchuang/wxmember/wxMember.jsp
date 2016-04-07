<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page pageId="010101" >

	<giui:tabs id="tabs_mem" pageHash="true">
		<giui:tabItem caption="基本信息" id="base" url="member/vchuang/wxmember/base/${subscriptionId}.html">
			
		</giui:tabItem>
		<giui:tabItem caption="其他信息" id="other">
			<giui:gridList caption="" template="" src="/wxMemberManager/getPagerWxMembers.json">
				<div class='myitem'>
					<img width="150px" height="150px" style="border-radius:25px;" src="{headimgurl}"/>
					{nickname}---{country}
				</div>
			</giui:gridList>
		</giui:tabItem>
	</giui:tabs>
	
	<youi:func name="init">
	</youi:func>
	
	<youi:func name="destroy">
		//页面销毁时调用
	</youi:func>
</giui:page>