/* ========================================================================
 * Youi: page.welcome.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */
var pageContainer = $.youi.getCurrentPageConatiner();
if(pageContainer){
	var Page = $.youi.page,
		Form = $.youi.form,
		FieldText = $.youi.fieldText,
		Button = $.youi.button;
	ReactDOM.render(
		<Page name="welcome">
			<Form findSrc="user.json?id={id}" idKeys="id" action="/action.json">
				<FieldText property="username" caption="用户名">
				</FieldText>
				<FieldText property="password" caption="密码"></FieldText>
				<Button name="add" caption="addUser"></Button>
			</Form>
		</Page>
		,pageContainer[0]
	);
}

