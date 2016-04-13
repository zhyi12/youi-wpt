/*!
 * youi JavaScript Library v3.0.0
 * 
 *
 * Copyright 2016-2020, zhyi_12
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Date: 2016-3-3
 */
require("./youi.core.js");

(function($) {
	'use strict';
	
	$.widget("youi.tabbar",$.youi.abstractWidget,$.extend({},{
		
		_initWidget:function(){
			this.tabContent = this.element.prev('.tab-content');
			
			var hashTabitem = $(window.location.hash);
			if(hashTabitem.length){
				this._activeTabItem(hashTabitem.prevAll().length);
			}else{
				this._activeTabItem(0);
			}
		},
		
		_activeTabItem:function(itemIndex){
			var item = this.element.find('li:eq('+itemIndex+')');
			if(item.length&&!item.is('.active')){
				var pane = this.tabContent.find(item.find('>a').attr('href'));
				if(pane.length&&!pane.hasClass('active')){
					this.tabContent.find('.tab-pane.active').not(pane).removeClass('active');
					pane.addClass('active');
					
					this._loadPanePage(item.find('>a').data('id'),item.data());
				}
			}
		},
		
		_loadPanePage:function(tabItemId,item){
			var tabPaneElem = this.tabContent.find('>[data-id="'+tabItemId+'"]');
			
			var src = item.src||this.options.itemSrc;
			
			if(src&&tabPaneElem.length&&!tabPaneElem.is('.loaded')){
				console.log('load page:'+src);
				
				var paramRecord = $.extend({},this._callGloablFunc('paramRecord'),{id:tabItemId});
				var pageUrl = $.youi.pageUtils.parsePageUrl(src,paramRecord);
				
				$.youi.pageUtils.loadPage(tabPaneElem,pageUrl,function(){
					$(this).addClass('loaded');
				});
			}
		},
		
		_initAction:function(){
			var that  = this;
			this.element.delegate('>li>a','mousedown',function(event){
				//设置pagehash
				var tabItemId = event.currentTarget.getAttribute('data-id');
				
				$(event.delegateTarget).trigger('setpagehash',{hash:tabItemId});
				//加载远程页面数据
				that._activeTabItem($(event.currentTarget.parentNode).prevAll().length);
			});
			
			this._on({
				'setpagehash':function(event,ui){
					if(ui.hash){
						window.location.hash=ui.hash;
					}else{
						//加载第一个页面
						this._activeTabItem(0);
					}
					return false;
				},
				'pagehash':function(event,ui){
					if(ui.hash){
						var tabA = this.element.find('>li a[href="'+ui.hash+'"]');
						if(!tabA.parent().is('.active')){
							//tabA.tab('show');
						}
					}
					//阻止冒泡
					return false;
				}
			});
			
		},
		/**
		 * 打开标签页
		 */
		openTabitem:function(tabId){
			//
			if(tabId&&typeof(tabId)=='string'){
				var tabitemElem = this.element.find('#'+tabId);
				tabitemElem.tab('show');
				
				var itemSrc = tabitemElem.data('src')||this.options.itemSrc;
				//动态加载页面
				if(itemSrc){
					var panelElem = $(tabitemElem.attr('href'));
					//
					if(panelElem.length&&!panelElem.hasClass('loaded')){
						var paramRecord = $.extend({},this._callGloablFunc('paramRecord'),{id:tabitemElem.attr('id')});
						var pageUrl = $.youi.pageUtils.parsePageUrl(itemSrc,paramRecord);
						
						$.youi.pageUtils.loadPage(panelElem,pageUrl,function(){
							
						});
					}
				}
			}
		}
	
	}));
	
})(jQuery);