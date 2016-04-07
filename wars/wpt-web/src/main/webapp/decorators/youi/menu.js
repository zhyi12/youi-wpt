/*!
 * youi JavaScript Library v3.0.0
 * 
 *
 * Copyright 2015, zhyi_12
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Date: 2015-1-26
 */

(function($) {
	'use strict';
	
	$.widget("youi.sysmenu",$.youi.abstractWidget,$.extend({},{
		/**
		 * 默认参数
		 */
		options:{
			
		},
		/**
		 * 接口方法，初始化变量模型
		 */
		_initModel:function(){
			//查找约定的搜索框
			this.searchField = this.element.prev().find('.youi-field.fieldAutocomplete');
		},
		/**
		 * 接口方法，初始化widget组件
		 */
		_initWidget:function(){
			this._active(this.options.activeIndex||0);
		},
		
		_active:function(index){
			this.element.find('>.menu-bar-content:eq('+index+')').addClass('show');
			this.resize();
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
			var _self = this;
			this.element.find('>.menu-bar-title').bind('click',function(index){
				$(this).parent().sysmenu('showBar',this);
			});
			var _widgetFullName = this.widgetFullName;
			this.element.delegate('.treeNode.tree-span','click',function(event){
				return $.data(event.delegateTarget,_widgetFullName).exec($(this).parent());
			});
			
			if(this.searchField.length){
				this.searchField.bind('selectvalue',function(event,ui){
					var menuId = ui.value;
					
					var menuElement = _self.element.find('#'+menuId);
					if(menuElement.length){
						_self.showBar(menuElement.parents('.menu-bar-content:first').prev());
						_self.exec(menuElement);
					}
				});
			}
		},
		
		/**
		 * 执行菜单动作
		 */
		exec:function(menuElement,paramRecord){
//			this._toggleChildren(menuElement);
			var pageId = menuElement.attr('id'),
				pageUrl = menuElement.find('>span>a').attr('href'),
				pageTitle = menuElement.find('>span:first').text(),
				target = menuElement.find('>span>a').attr('target');
			
			//
			if(menuElement.hasClass('expandable')){
				this._toggleChildren(menuElement);
			}
			
			if(!pageUrl||'javascript:void(0)'==$.trim(pageUrl))return false;
			
			this.element.find('.selected').not(menuElement).removeClass('selected');
			menuElement.addClass('selected');
			menuElement.find('>span').addClass('selected');
			
			if(target==='_blank'){//直接打开URL
				return true;
			}else if(target==='_subpage'){//直接打开URL
				//打开子页面
				this._openSubpage(pageId,pageUrl,pageTitle);
				return false;
			}
			
			pageUrl = $.youi.parameterUtils.connectParameter(pageUrl,'timeStamp_',new Date().getTime());
			pageUrl = $.youi.parameterUtils.connectParameter(pageUrl,'page:pageId',pageId);
			
			if(paramRecord&&typeof(paramRecord) =='object'){
				for(var iParam in paramRecord){
					pageUrl = $.youi.parameterUtils.connectParameter(pageUrl,iParam,paramRecord[iParam]);
				}
			}
			try{
				this._loadPage(pageId, pageUrl, pageTitle);
			}catch(err){
				//
			}
			
			return false;
		},
		/**
		 * 打开菜单
		 */
		openPage:function(pageId,paramRecord){
			var menuElement = this.element.find('li.menu-item#'+pageId);
			if(menuElement.length){
				if(paramRecord){
					//关闭已经打开的标签页
					$('body',document).bootstrapLayout('closePage',pageId);
				}
				this.exec(menuElement,paramRecord);
			}
		},
		/**
		 * 展开或关闭子菜单
		 */
		_toggleChildren:function(menuElement){
			menuElement.toggleClass('expanded');
			if(menuElement.is('.expanded')){
				menuElement.find('ul:first').show();
			}else{
				menuElement.find('ul:first').hide();
			}
		},
		/**
		 * 加载页面
		 */
		_loadPage:function(pageId,pageUrl,pageTitle){
			var self =this;
			//加载页面
			$('body',document).bootstrapLayout('loadPage',pageId,pageUrl,pageTitle,function(){
				//在page上方添加title
				//self._showTrace(pageId);
			});
		},
		
		_openSubpage:function(pageId,pageUrl,pageTitle){
			var subPageContanier = $('#subpage_'+pageId);
			if(subPageContanier.length==0){
				subPageContanier = $('<div id="subpage_'+pageId+'"><div class="subpage-content"></div></div>').appendTo($('body',document));
				
				subPageContanier.subpage({
					subpageId:pageId,
					type:'dialog',
					height:'500',
					src:pageUrl
				});
			}
			subPageContanier.subpage('open',{},pageTitle,{'a':'1'});
			
			return subPageContanier;
		},
		/**
		 * 
		 */
		showBar:function(barTitleDom){
			var elem = $(barTitleDom);
			var current = this.element.find('>.menu-bar-content.show');
			current.removeClass('show');
			elem.next().addClass('show').height(current.height());
		},
		/**
		 * 接口方法，重定位
		 */
		_resize:function(){
			var height = this.element.offsetParent().height();
			var prevHeight = this.element.prev().outerHeight();
			var oHeight = 0;
			this.element.find('>.menu-bar-title').each(function(index){
				oHeight += $(this).outerHeight();
			});
			this.element.find('.show.menu-bar-content').height(height - oHeight-prevHeight);
		},
		/**
		 * 接口方法，销毁组件
		 */
		_destroy:function(){
			
		}
	}));
	
})(jQuery);