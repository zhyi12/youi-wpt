<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page pageId="010102">
	<giui:subpage height="500" src="member/vchuang/wxmember/edit/${subscriptionId}.html?openid={openid}" subpageId="edit" caption="查看粉丝信息" 
		type="dialog"/>
		
	<youi:ajaxUrl name="getPagerUsers" src="/userManager/getPagerUsers.json"/>
	
	<giui:grid id="grid_user" 
		idKeys="subscriptionId,openid"
		caption="grid" src="/wxMemberManager/getPagerWxMembers.json?subscriptionId=${subscriptionId}">
		<giui:gridSingleQuery styleClass="col-sm-4">
			<youi:fieldText property="nickname" caption="粉丝名"/>
			<giui:fieldSelect property="country" convert="country" caption="国家"/>
		</giui:gridSingleQuery>
		
		<div class="col-sm-8"  style="padding:5px;">
			<div class="dorpdown pull-right">
				<button class="btn btn-default" data-toggle="dropdown" aria-expanded="false">
				操作<span class="caret"></span></button>
				<ul class="dropdown-menu">
					<li class="option-item" data-command="gridCommand" data-name="addUser">添加用户</li>
				</ul>
			</div>
		</div>
	
		<giui:gridCol width="25%" property="nickname"  caption="粉丝名"/>
		<giui:gridCol width="10%" property="country"  caption="国家"/>
		<giui:gridCol width="15%" property="province"  caption="省"/>
		<giui:gridCol width="15%" property="city"  caption="城市"/>
		<giui:gridCol width="10%" property="sex" convert="sex" caption="性别"/>
		<giui:gridCol width="15%" property="subscribe" convert="boolean" caption="SUBSCRIBE"/>
		<giui:gridCol width="10%" type="button" caption="操作" property="oper">
			<giui:button name="editUser" icon="edit" caption="查看"></giui:button>
			<giui:button name="removeUser" icon="remove" caption="删除"></giui:button>
		</giui:gridCol>
		
	</giui:grid>
	
	<youi:func name="grid_user_afterLoad" params="totalCount,records" urls="getPagerUsers">
		console.log(records+'---:'+funcUrls['getPagerUsers']);
	</youi:func>
	
	<youi:func name="grid_user_rowClick" params="totalCount,records" urls="getPagerUsers">
		console.log(records+'---:'+funcUrls['getPagerUsers']);
	</youi:func>
	
	<youi:func name="grid_user_addUser">
		$elem('subpage_edit',pageId).subpage('open',{},'');
	</youi:func>
	
	<youi:func name="grid_user_editUser" params="dom,commandOptions,record">
		$elem('subpage_edit',pageId).subpage('open',record,'',record);

		//esb/weixinAdapter/wxHttpServices/getUserInfo.json
		
	</youi:func>
	
	<youi:func name="init">
	</youi:func>
	
	<youi:func name="destroy">
		//页面销毁时调用
	</youi:func>
</giui:page>