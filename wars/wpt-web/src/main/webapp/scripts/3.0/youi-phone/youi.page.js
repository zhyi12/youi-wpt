/* ========================================================================
 * Youi: youi.page.js v3.0
 * http://www.cjyoui.xyz/docs/youi/page.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */
 
var youi = require("./youi.core.js");

youi.page = React.createClass({
	render:function(){
		var className = ['youi-page',this.props.className].join(' ');
	    return (
	    	<div id={this.props.id} className={className}>
	        {this.props.children}
	        </div>
	    );
	}
});

youi.console.log('init youi.page');
module.exports = youi.page;
