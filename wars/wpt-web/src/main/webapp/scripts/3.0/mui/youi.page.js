/*!
 * youi JavaScript Library v3.0.0
 * 
 *
 * Copyright 2016-2020, zhyi_12
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Date: 2016-1-4
 */

require("./youi.core.js");

(function($) {
//	var _log = $.youi.log;
	/**
	 *  page组件
	 * @author  zhouyi
	 * @version 1.0.0
	 */
	
	$.widget("youi.page",$.youi.abstractWidget,$.extend({},{
		options:{
			bindResize:true
		},
		
		_initWidget:function(){
			var that = this,
				initFuncName = 'P_'+this.options.pageId+'_init';
			//调用初始化函数
			if(this.options.dataSrc){
				//获取数据
				$.youi.ajaxUtils.ajax({
					url:this.options.dataSrc,
					success:function(result){
						that.__callGloablFunc(initFuncName,result);
					}
				});
			}else{
				this.__callGloablFunc(initFuncName,{});
			}
		},
		
		_initAction:function(){
			this._on({
				'setpagehash [data-hash]':function(event,ui){
					return false;
				}
			});
		},
		
		_destroy:function(){
			//调用页面销毁函数
			this._callGloablFunc('destroy');
		}
	}));
	
})(jQuery);