/* ========================================================================
 * Youi: youi.tabs.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

var youi = require("./youi.core.js"),
	Page = require("./youi.page.js");

/**
 * 标签对象
 */
youi.tabs = React.createClass({
	/**
	 * 复合的对象
	 */
	mixins:[youi.abstractWidget],
	/**
	 * 默认属性
	 */
	getDefaultProps:function(){
		return {
			type:'navbar'
		}
	},
	
	/**
	 * 界面绘制完成后调用
	 */
	componentDidMount :function(){
		this._create();
		this._on({
			'click .tabs-header .youi-tabitem':function(event){
				this._openTabitem(event.currentTarget);
			}
		});
		
		this._openDefaultTabitem();
	},
	
	_openDefaultTabitem:function(){
		
		var itemId,
			itemElem;
		
		//从网址中获取当前需要定位到的标签
		var splits = window.location.href.split('#');//获取锚点
		if(splits.length>1){
			itemId = splits[splits.length-1];
		}
		
		if(itemId.indexOf('|')!=-1){
			itemId = itemId.substring(itemId.indexOf('|')+1);
		}
		
		youi.console.log('default open item:'+itemId);
		if(itemId){
			itemElem = this.element.find('nav #'+itemId);
		}
		//取第一个标签
		if(!itemElem||itemElem.length==0){
			itemElem = this.element.find('.youi-tabitem:first');
		}
		
		if(itemElem.length){
			this._openTabitem(itemElem[0]);
		}
	},
	/**
	 * 打开标签页
	 */
	_openTabitem:function(headerDom){
		if(!headerDom)return;
		var id = headerDom.getAttribute('id');
		if(this.refs&&this.refs[id]){
			var tabPanel = $(this.refs[id]);
			if(!tabPanel.hasClass('active')){
				var headElem = $(headerDom);
				
				$('>.active',headElem.parent()).removeClass('active');
				$('>.active',tabPanel.parent()).removeClass('active');
				
				headElem.addClass('active');
				tabPanel.addClass('active');
				
				//itemSrc
				if(this.props.itemSrc&&!tabPanel.hasClass('loaded')){
					//加载页面
					youi.loadPage(id,$.youi.resolverUrl(this.props.itemSrc,{id:id}),tabPanel);
				}else{
					youi.setAppPageHash(id);//设置hash
				}
			}
		}
	},
	
	/**
	 * 界面呈现
	 */
	render:function() {
	    return (
	    	<div className="youi-tabs">
	    		<nav className="tabs-header header">
	    		{this.props.children}
	    		</nav>
	    		<div className="tabs-content">
	    		{
	    			React.Children.map(this.props.children, function (child,index) {
	    				var tabPanelId = child.props.id+'_panel';
		  		    	return <div ref={child.props.id} className="youi-tabpanel" id={tabPanelId}></div>;
		  		    })
	    		}
	    		</div>
	    	</div>
	    );
	}
});

/*
 * 标签项对象
 */
youi.tabs.item = React.createClass({
	render:function() {
		var iconClassName = youi.getIconClassName(this.props.icon);
		var pageHash = window.location.hash||'#';//当前页面的hash
		var href = pageHash+'|'+this.props.id;
	    return (
	    	<a href={href} id={this.props.id} className="youi-tabitem">
	    		<span className={iconClassName}></span>
	    		<span className="title">{this.props.caption}</span>
	    	</a>
	    );
	}
});

module.exports = youi.tabs;
