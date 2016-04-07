/* ========================================================================
 * Youi: youi.button.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */
 
var youi = require("./youi.core.js");

youi.button = React.createClass({
	getDefaultProps:function(){
		return {
			type:'button'
		}
	},
	
	render:function() {
		var btnClassName = 'youi-btn btn-default '+this.props.name;
	    return (
	    	<button className={btnClassName} data-name={this.props.name} data-command={this.props.command} type={this.props.type}>{this.props.caption}</button>
	    );
	}
});

module.exports = youi.button;
