<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<youi:page>
	<youi:grid id="grid_wxMember" idKeys="openid" caption="微信会员列表"  panel="false"
				src="esb/web/wxMemberManager/getPagerWxMembers.json" dataFormId="form_wxMember"
				editSrc="esb/web/wxMemberManager/getWxMember.json" edit="NOT" remove="NOT" showCheckbox="true"
				removeSrc="esb/web/wxMemberManager/removeWxMember.json">
		<youi:fieldLayout>
			<youi:fieldText property="remark"  caption="REMARK"/>
			<youi:fieldText property="city"  caption="CITY"/>
			<youi:fieldText property="nickname"  caption="NICKNAME"/>
			<youi:fieldText property="country"  caption="COUNTRY"/>
			<youi:fieldText property="province"  caption="PROVINCE"/>
			<youi:fieldText property="sex"  caption="SEX"/>
			<youi:fieldText property="unionid"  caption="UNIONID"/>
			<youi:fieldText property="subscribe"  caption="SUBSCRIBE"/>
			<youi:fieldText property="language"  caption="LANGUAGE"/>

			<youi:fieldText property="headimgurl"  caption="HEADIMGURL"/>
		</youi:fieldLayout>
		<youi:gridCol property="remark"  caption="REMARK"/>
		<youi:gridCol property="city"  caption="CITY"/>
		<youi:gridCol property="nickname"  caption="NICKNAME"/>
		<youi:gridCol property="country"  caption="COUNTRY"/>
		<youi:gridCol property="province"  caption="PROVINCE"/>
		<youi:gridCol property="sex"  caption="SEX"/>
		<youi:gridCol property="unionid"  caption="UNIONID"/>
		<youi:gridCol property="subscribe"  caption="SUBSCRIBE"/>
		<youi:gridCol property="language"  caption="LANGUAGE"/>

		<youi:gridCol property="headimgurl"  caption="HEADIMGURL"/>
		<youi:gridCol width="60" fixed="true" property="button" type="button" caption="操作">
			<youi:button name="edit" caption="修改"/>
			<youi:button name="remove" caption="删除"/>
		</youi:gridCol>
	</youi:grid>
	
	<!-- form-微信会员编辑 -->
	<youi:form dialog="true" caption="微信会员" id="form_wxMember" action="esb/web/wxMemberManager/saveWxMember.json">
		<youi:fieldLayout prefix="record">
			<youi:fieldText property="remark"  caption="REMARK"/>
			<youi:fieldText property="city"  caption="CITY"/>
			<youi:fieldText property="nickname"  caption="NICKNAME"/>
			<youi:fieldText property="country"  caption="COUNTRY"/>
			<youi:fieldText property="province"  caption="PROVINCE"/>
			<youi:fieldText property="sex"  caption="SEX"/>
			<youi:fieldText property="unionid"  caption="UNIONID"/>
			<youi:fieldText property="subscribe"  caption="SUBSCRIBE"/>
			<youi:fieldText property="language"  caption="LANGUAGE"/>
			<youi:fieldText property="openid"  caption="OPENID"/>
			<youi:fieldText property="headimgurl"  caption="HEADIMGURL"/>
		</youi:fieldLayout>
	</youi:form>
	
	<!--**********************************页面函数Start********************************-->
	
	<!--**********************************页面函数End**********************************-->
</youi:page>