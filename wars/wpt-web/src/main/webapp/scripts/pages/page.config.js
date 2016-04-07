/* ========================================================================
 * Youi: page.config.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

var pageContainer = $.youi.getCurrentPageConatiner();
if(pageContainer){
	var Page = $.youi.page,
		ListView = $.youi.listView,
		ListItem =  $.youi.listView.item;
	ReactDOM.render(
		<Page name="config">
			<ListView>
				<ListItem icon="home" caption="相册" desc="进入相片" href="photo.html" target="PageView_open"/>
				<ListItem caption="收藏" desc="进入收藏" href="fav.html" target="PageView_open"/>
				<ListItem caption="钱包" desc="进入钱包" href="bag.html" target="PageView_open"/>
				<ListItem caption="卡包" desc="进入卡包" href="card.html" target="PageView_open"/>
			</ListView>
		</Page>
		,pageContainer[0]
	);
}