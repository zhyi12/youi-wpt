/* ========================================================================
 * Youi: app.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

var PageView = $.youi.pageView;//页面管理器

/**
 * 加载页面，使用两个备用的view
 */
ReactDOM.render(
	<PageView href="react.html" caption="首页"></PageView>,
	$.youi.getCurrentPageConatiner()[0],function(){
		console.log('complete page load ');
	});

