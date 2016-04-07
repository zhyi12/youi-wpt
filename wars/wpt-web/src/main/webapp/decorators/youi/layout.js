/*!
 * youi JavaScript Library v3.0.0
 * 
 *
 * Copyright 2015, zhyi_12
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Date: 2015-1-27
 */
(function($) {
	'use strict';
	
	$.widget("youi.bootstrapLayout",$.youi.abstractWidget,$.extend({},{
		/**
		 * 默认参数
		 */
		options:{
			bindResize:true
		},
		/**
		 * 接口方法，初始化变量模型
		 */
		_initModel:function(){
			
		},
		/**
		 * 接口方法，初始化widget组件
		 */
		_initWidget:function(){
			
			this.middleElement = this.element.find('>.layout-md');
			
			this.tabsElement = this._getTabsElement().tabs({showClose:true});
			this._resize();
		},
		
		/**
		 * 接口方法，填充html元素
		 */
		_defaultHtmls:function(){
			
		},
		/**
		 * 接口方法，初始化组件动作
		 */
		_initAction:function(){
//			this._on({
//				'click #sys_toggle_menu':function(event){
//					this.toggleMenu();
//				}
//			});
		},
		
//		toggleMenu:function(){
//			this.element.find('.layout-panel.panel-west:first').width("10px");
//			this.element.find('.layout-panel.panel-center:first').css('paddingLeft','10px');
//		},
		
		loadPage:function(pageId,pageUrl,pageTitle,after){
			this.tabsElement.tabs('loadPage',pageId,pageUrl,pageTitle,after);
		},
		
		closePage:function(pageId){
			
		},
		
		_getTabsElement:function(){
			return $('.youi-page-content:first',this.element);
		},
		/**
		 * 接口方法，重定位
		 */
		_resize:function(){
			var height = $(window).height();
			
			var oHeight = 0;
			this.element.find('>div.layout-panel,>div.layout-md').not(this.middleElement).each(function(index){
				oHeight += $(this).outerHeight();
			});
			
			//this._log.info(this._buildLogMsg(height+' ' +oHeight));
			
			var height = height - oHeight;
			this.middleElement.height(height);
			this.middleElement.find('>div').height(height);
			
			this.middleElement.find('.tab-content:first').height(height-38-10);
		},
		/**
		 * 接口方法，销毁组件
		 */
		_destroy:function(){
			this.middleElement = null;
			this.tabsElement = null;
		}
	}));
	
})(jQuery);