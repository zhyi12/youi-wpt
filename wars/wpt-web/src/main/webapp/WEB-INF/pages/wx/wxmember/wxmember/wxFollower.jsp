<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:ajaxUrl name="wxGetUsers" src="esb/weixinAdapter/wxHttpServices/getUsers.json"/>
	<youi:ajaxUrl name="wxGetUserInfo" src="esb/weixinAdapter/wxHttpServices/getUserInfo.json"/>
	<youi:ajaxUrl name="saveFollower" src="esb/web/wxMemberManager/saveWxMember.json"></youi:ajaxUrl>
	
	<youi:grid id="grid_wxMember" idKeys="openid" caption="微信会员列表"  panel="false"
				src="esb/web/wxMemberManager/getPagerWxMembers.json" dataFormId="form_wxMember"
				editSrc="esb/web/wxMemberManager/getWxMember.json" edit="NOT" remove="NOT" add="NOT" showCheckbox="true"
				removeSrc="esb/web/wxMemberManager/removeWxMember.json">
		<youi:fieldLayout>
			<youi:fieldText property="nickname" operator="LIKE" caption="NICKNAME"/>
			<youi:fieldText property="sex" caption="SEX"/>
			<youi:fieldCalendar property="cale" format="yyyy-MM-dd" textFormat="yyyy-MM-dd" caption="日期"></youi:fieldCalendar>
			<youi:fieldCalendar property="cale1" format="yyyy-MM-dd" textFormat="yyyy-MM-dd" caption="日期"></youi:fieldCalendar>
					
		</youi:fieldLayout>
		
		<youi:gridCol width="20%" property="openid" caption="openid"></youi:gridCol>
		<youi:gridCol width="20%" property="nickname"  caption="NICKNAME"/>
		
		<youi:gridCol width="10%" property="country"  caption="COUNTRY"/>
		<youi:gridCol width="10%" property="province"  caption="PROVINCE"/>
		<youi:gridCol width="10%" property="city"  caption="CITY"/>
		<youi:gridCol width="10%" property="sex"  caption="SEX"/>
		<youi:gridCol width="10%" property="subscribe"  caption="SUBSCRIBE"/>
		
		<youi:gridCol width="10%" fixed="false" property="button" type="button" caption="操作">
			<youi:button name="wxMemberViewer" icon="edit" caption="查看详细"/>
		</youi:gridCol>
		
		<youi:button name="syncFollowers" caption="同步粉丝"></youi:button>
	</youi:grid>
	
	<youi:form dialog="true" caption="微信会员" id="form_wxMember" idKeys="openid" findAction="esb/weixinAdapter/wxHttpServices/getUserInfo.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldLabel column="2" property="openid"  caption="OPENID"/>
			<youi:fieldLabel column="2"  property="nickname"  caption="NICKNAME"/>
			
			<youi:fieldLabel property="city"  caption="CITY"/>
			<youi:fieldLabel property="country"  caption="COUNTRY"/>
			<youi:fieldLabel property="province"  caption="PROVINCE"/>
			<youi:fieldLabel property="sex"  caption="SEX"/>
			
			<youi:fieldLabel column="2" property="remark"  caption="REMARK"/>
			<youi:fieldLabel column="2" property="unionid"  caption="UNIONID"/>
			<youi:fieldLabel column="2" property="subscribe"  caption="SUBSCRIBE"/>
			<youi:fieldLabel column="2" property="language"  caption="LANGUAGE"/>
			
			<youi:fieldLabel column="2" property="headimgurl"  caption="HEADIMGURL"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	<youi:func name="grid_wxMember_col_button_wxMemberViewer" params="record">
		$elem('form_wxMember',pageId).form('fillRecord',record).form('loadRecord').form('open');
	</youi:func>
	
	<youi:func name="func_grid_syncFollowers" urls="wxGetUsers">
		if(funcUrls.wxGetUsers){
			$.youi.ajaxUtil.ajax({
				url:funcUrls.wxGetUsers,
				success:function(results){
					//预览功能 
					$(results.records).each(function(){
						//$.youi.pageUtils.doPageFunc('findAndSaveFollower',pageId);
						var fullFuncName = 'P_'+pageId+'_findAndSaveFollower';
						if($.isFunction(window[fullFuncName])){
							window[fullFuncName](this.openid);
						}
					});
				}
			});
		}
	</youi:func>
	
	<youi:func name="findAndSaveFollower" params="openid" urls="wxGetUserInfo">
		if(funcUrls.wxGetUserInfo){
			$.youi.ajaxUtil.ajax({
				url:funcUrls.wxGetUserInfo,
				data:'openid='+openid,
				success:function(results){
					if(results.record){
						var fullFuncName = 'P_'+pageId+'_saveFollower';
						if($.isFunction(window[fullFuncName])){
							window[fullFuncName](results.record);
						}
					}
				}
			});
		}
	</youi:func>
	
	<youi:func name="saveFollower" params="record" urls="saveFollower">
		if(funcUrls.saveFollower){
			$.youi.ajaxUtil.ajax({
				url:funcUrls.saveFollower,
				data:record,
				success:function(results){
					
				}
			});
		}
	</youi:func>
	<!--**********************************页面函数End**********************************-->
</youi:page>