/*!
 * youi JavaScript Library v3.0.0
 * 
 *
 * Copyright 2016-2020, zhyi_12
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Date: 2016-1-4
 */
(function($) {
	/**
	 * @version 1.0.0
	 */
	
	var HASH_PAGE_PREFIX = $.youi.pageHashPrefix;
	
	$.widget("youi.app",$.youi.abstractWidget,$.extend({},{
		options:{
			bindResize:true
		},
		/**
		 * 
		 */
		_initWidget:function(){
			
		},
		/**
		 * app 事件
		 */
		_initAction:function(){
			//hash变化
			$(window).bind('hashchange',function(event){
				_goPageHash();//根据location.hash 定位页面
			});
			/*
			 * 全局动作监听
			 */
			this._on({
				'click a:not(.page-link)':function(event){
					//屏蔽元素的默认动作
					event.preventDefault();
				},
				'click a[data-menu-clear]':function(event){
					//屏蔽元素的默认动作
					$('.menu-item.selected').removeClass('selected');
				}
			});
			//由hash变化触发
			this.element.find('.page-container:first').bind('pagehash',function(event,ui){
				if(event.target===this){
					//页面加载
					var pageContainer = $(event.currentTarget);
					var currentHash = pageContainer.attr('data-hash');
					
					if(ui.hash!=currentHash){
						//加载页面
						pageContainer.attr('data-hash',ui.hash);
						$.youi.pageUtils.loadPage(pageContainer,ui.hash,function(){
							//页面加载完成后，执行goPageHash方法，处理页面中的hash跳转
							_goPageHash(1);
						});
					}
				}
			}).bind('setpagehash',function(event,ui){
				//page-container容器设置location的hash值
				if(ui.hash){
					window.location.hash = 'p:'+ui.hash;
				}
				return false;
			});
			
			//默认的pageHash
			_goPageHash();
		}
		
	}));
	
	/**
	 * 加载页面
	 */
	function _goPageHash(startIndex){
		var startIndex = startIndex||0;
			pagehash = window.location.hash,
		
		//触发pagehash动作
		$('body',document).find('[data-hash]:visible').each(function(index){
			if(index>=startIndex){
				$(this).trigger('pagehash',{hash:pagehash});
			}
		});
	}
	
})(jQuery);