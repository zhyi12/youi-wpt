/* ========================================================================
 * Youi: page.msg.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

var pageContainer = $.youi.getCurrentPageConatiner();
if(pageContainer){
	var Page = $.youi.page;
	ReactDOM.render(
		<Page name="msg">消息</Page>
		,pageContainer[0]
	);
}