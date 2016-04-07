/* ========================================================================
 * Youi: youi.list.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */


var youi = require("./youi.core.js");

/**
 * 标签对象
 */
youi.listView = React.createClass({
	
	/**
	 * 复合的对象
	 */
	mixins:[youi.abstractWidget],
	/**
	 * 默认属性
	 */
	getDefaultProps:function(){
		return {}
	},
	
	/**
	 * 界面绘制完成后调用
	 */
	componentDidMount :function(){
		this._create();
		this._on({
			'click .list-view-item>a':function(event){
				var target = event.currentTarget.getAttribute('target'),
					href = event.currentTarget.getAttribute('href'),
					url = href+'?_'+new Date().getTime()+'#'+target;
				//
				youi.openPage(href,url,target);
				return false;
			}
		});
	},
	
	render:function() {
	    return (
	    	<ul className="youi-list-view">
	    	{this.props.children}
	    	</ul>
	    );
	}
});

youi.listView.item = React.createClass({
	
	render:function() {
		var info = this.props.desc?(<i className="desc">{this.props.desc}</i>):null;
		var href = this.props.href;
		var target = this.props.target||'';
		var icon;
		if(this.props.icon){
			var iconClassName = youi.getIconClassName(this.props.icon);
			icon = this.props.icon?(<span className={iconClassName}></span>):null;
		}
	    return (
	    	<li className="list-view-item">
	    		<a target={target} href={href}>{icon}{this.props.caption}{info}</a>
	    	</li>
	    );
	}
});

module.exports = youi.listView;