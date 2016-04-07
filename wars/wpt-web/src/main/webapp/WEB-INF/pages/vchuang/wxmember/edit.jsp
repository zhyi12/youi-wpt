<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page dataSrc="/esb/weixinAdapter/wxHttpServices/getUserInfo.json?subscriptionId=${subscriptionId}&openid=${param.openid}">
	<giui:form id="form_member" panel="false" submit="NOT" reset="NOT">
		<giui:fieldLayout>
			<giui:fieldLabel column="2"  property="nickname"  caption="粉丝名"/>
			<giui:fieldLabel property="country"  caption="国家"/>
			<giui:fieldLabel property="province"  caption="省份"/>
			<giui:fieldLabel property="city"  caption="城市"/>
			<giui:fieldLabel property="sex"  caption="性别"/>
			
			<giui:fieldLabel column="2" property="headimgurl" format="image" height="300" caption="头像"/>
			
		</giui:fieldLayout>
	</giui:form>
	
	<youi:func name="init" params="results">
		if(results){
			$elem('form_member',pageId).form('fillRecord',results.record);
		}
	</youi:func>
	
	<youi:func name="destroy">
		//页面销毁时调用
	</youi:func>
</giui:page>