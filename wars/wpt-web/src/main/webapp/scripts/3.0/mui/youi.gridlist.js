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
require("./youi.resource.js");

(function($) {
//	var _log = $.youi.log;
	/**
	 *  page组件
	 * @author  zhouyi
	 * @version 1.0.0
	 */
	
	$.widget("youi.gridList",$.youi.abstractWidget,$.extend({},{
		options:{
			bindResize:true,
			rowStyle:'list-item',
		},
		
		_initWidget:function(){
			var that = this;
			
			if(this.options.src){
				$.youi.ajaxUtils.ajax({
					url:this.options.src,
					success:function(results){
						if(results&&results.records){
							that._parseRecords(results.records);
						}
					}
				});
			}else if(this.options.records){
				this._parseRecords(this.options.records);
			}
		},
		
		_parseRecords:function(records){
			var htmls = [];
			
			var template = '<div class="'+this.options.rowStyle+'">'+this.options.template+'</div>';
			
			$(records).each(function(){
				htmls.push($.youi.recordUtils.replaceByRecord(template,this));
			});
			
			this.element.html(htmls.join(''));
		},
		
		_destroy:function(){
			//调用页面销毁函数
			this._callGloablFunc('destroy');
		}
	}));
	
})(jQuery);