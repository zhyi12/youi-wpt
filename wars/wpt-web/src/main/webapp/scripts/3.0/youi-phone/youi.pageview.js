/* ========================================================================
 * Youi: youi.pageview.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

var youi = require("./youi.core.js"),
	MAIN_PAGEVIEW_ITEM_ID = "PageView_main",
	OPEN_PAGEVIEW_ITEM_ID = "PageView_open";


/**
 * 页面view对象
 */
youi.pageView = React.createClass({
	/**
	 * 复合的对象
	 */
	mixins:[youi.abstractWidget],
	
	componentDidMount :function(){
		
		window.__setPageHash = function(hash){
			window.location.hash = hash;
		}
		
		//绑定window事件
		window.onhashchange = function(event){
			var currentHash = window.location.hash,
				splitIndex = currentHash.indexOf('|'),
				viewIdSelector = currentHash.substring(0,splitIndex),
				currentView = $(viewIdSelector);
			
			if(currentView.length&&!currentView.hasClass('active')){
				$('.page-view-item').not(currentView).removeClass('active');
				currentView.addClass('active');
			}
		};
	},
	
	render:function() {
		var height = $(window).innerHeight()-5;
		var pageViewId = MAIN_PAGEVIEW_ITEM_ID,
			openPageViewId = OPEN_PAGEVIEW_ITEM_ID,
			href = this.props.href+'#'+pageViewId;
	    return (
	    	<div className="page-view">
	    		<div id={pageViewId} className="page-view-item active"><iframe name={pageViewId} width="100%" height={height} frameBorder="0" src={href}></iframe></div>
	    		<div id={openPageViewId} className="page-view-item"><iframe name={openPageViewId} width="100%" height={height} frameBorder="0" src="about:blank;"></iframe></div>
	    	</div>
	    );
	}
});


module.exports = youi.pageView;