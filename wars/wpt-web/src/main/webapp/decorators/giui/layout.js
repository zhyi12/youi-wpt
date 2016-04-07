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
	
	$(window).bind('resize',function(event){
		window.setTimeout(function(){
			$.youi.widgetUtils.triggerResize(null,true);
		});
		
		$('.ui-widget-overlay:visible').each(function(){
			$(this).css({
				width:$(window).width(),
				height:$(window).height()
			});
		});
	});
	
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
			this.westPanel = this.element.find('.layout-panel.panel-west:first');
			
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
			//resize动作集成
			
			this._on({
				'click #sys_toggle_menu':function(event){
					this.toggleMenu();
				},
				
				'click .menu-bar-title':function(event){
					this._activeMenuBar($(event.currentTarget).next('.menu-bar-content'));
				},
				'click .menu-item>span>a':function(event){
					
					//菜单点击
					var href = $(event.currentTarget).attr('href');
					//alert(href);
					if(href.indexOf('#')!=0){
						$(event.currentTarget).attr('data-href',href);
						href = '#p:'+href;
					}
					
					//
					
					var menuItem = $(event.currentTarget.parentNode.parentNode);
					if(menuItem.is('.selected')){
						return false;
					}else{
						this.element.find('.selected.menu-item').removeClass('selected');
						menuItem.addClass('selected');
						$(event.currentTarget).attr('href',href);
					}
				}
			});
		},
		
		_activeMenuBar:function(menuContentElem){
			
			menuContentElem.addClass('active');
			//
			menuContentElem.parent().find('.active').not(menuContentElem).removeClass('active');
			
			menuContentElem.prev('.menu-bar-title').addClass('active');
			
		},
		
		toggleMenu:function(){
			var isClosed = this.westPanel.toggleClass('closed').hasClass('closed');
			this.element.find('.layout-panel.panel-center:first').css('paddingLeft',this.westPanel.outerWidth());
		},
		
		loadPage:function(pageId,pageUrl,pageTitle,after){
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
//			
			var height = height - oHeight;
			this.middleElement.css('minHeight',height);
			this.middleElement.find('>div').css('minHeight',height);
			
			this.element.find('.layout-panel.panel-center:first').css('paddingLeft',this.westPanel.outerWidth());
			
			this.middleElement.find('.page-container:first').height(height-38);
		},
		/**
		 * 接口方法，销毁组件
		 */
		_destroy:function(){
			this.middleElement = null;
		}
	}));
	
})(jQuery);