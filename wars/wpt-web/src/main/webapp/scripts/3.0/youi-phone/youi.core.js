/* ========================================================================
 * Youi: youi.core.js v3.0
 * http://www.cjyoui.xyz/docs/youi/core.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

(function($,undefined) {
	$ = window.$||{};
	
	var widget_uuid = 0;
	
	$.youi = $.youi||{
		version:'3.0.0',
		
		serverConfig:{
			contextPath:'/wtp-web/',//web上下文路径,服务器生成
			convertArray:[],//服务字典
			pageUrls:{},
			pageParams:{}
		},
		/**
		 * 获取当前页面容器
		 */
		getCurrentPageConatiner:function(){
			if(!$.youi.currentPageContainer||$.youi.currentPageContainer.length==0){
				return $('<div class="app"></div>').appendTo($('body',document).empty());
			}
			return $.youi.currentPageContainer;
		},
		/**
		 * 打开page
		 */
		openPage:function(pageId,pageUrl,target){
			window.open(pageUrl,target);
		},
		/**
		 * 加载页面
		 */
		loadPage:function(pageId,pageUrl,container){
			//加载页面
			container.addClass('loading');
			//当前加载页面容器
			$.youi.currentPageContainer = $('<div class="page-container"></div>').appendTo(container);
			$.ajax({
				url:pageUrl,
				dataType:'script',
				success:function(results){
					//after(pageDoc);
				},
				error:function(e,a,msg){
					alert(msg);
				},
				complete:function(){
					container.removeClass('loading').addClass('loaded');
					
					$.youi.setAppPageHash(pageId);
					container = null;
					$.youi.currentPageContainer = null;
				}
			});
		},
		
		setAppPageHash:function(subHash){
			if(window.parent&&window.parent.__setPageHash){
				
				var hash = window.location.hash;
				
				if(hash.indexOf('|')!=-1){
					hash = hash.substring(0,hash.indexOf('|'));
				}
				
				hash = hash+(subHash?('|'+subHash):'');
				window.parent.__setPageHash(hash);
			}
		},
		/**
		 * 输出控制台
		 */
		console:window.console||{
			log:function(message){
				//
			}
		},
		/**
		 * 替换url中的 {} 格式字符
		 * @param url
		 * @param paramRecord
		 * @returns
		 */
		resolverUrl:function(url,paramRecord,queryRecord){
			var newUrl = $.youi.serverConfig.contextPath + url;
			if(paramRecord){
				for(var paramName in paramRecord){
					newUrl = newUrl.replace(new RegExp("\\{" + paramName + "\\}", "g"),paramRecord[paramName]);
				}
			}
			
			if(queryRecord){
				if(newUrl.indexOf('?')==-1){
					newUrl = newUrl+'?t=1';
				}
				for(var paramName in queryRecord){
					if(queryRecord[paramName]!=null){
						newUrl = newUrl+('&'+paramName+'='+queryRecord[paramName]);
					}
				}
			}
			
			return newUrl;
		},
		/**
		 * 获取参数
		 */
		getQueryParam:function(paramName){
			var result = window.location.href.search.match(new RegExp("[\?\&]" + name+ "=([^\&]+)","i"));
		    if(result == null || result.length < 1){
		    	return "";
		    }
		    return result[1];
		},
		/**
		 * 图标样式名
		 */
		getIconClassName:function(icon){
			return icon?'youi-icon y-'+icon:'';
		},
		/**
		 * 
		 */
		abstractWidget:{
			/**
			 * 构建对象时调用
			 */
			_create:function(widgetName){
				this.uuid = widget_uuid++;
				this.widgetName = 'widget';
				this.eventNamespace = "." + this.widgetName + this.uuid;
				this.element = $(ReactDOM.findDOMNode(this));
				this.options = {};
				
				this.bindings = $();
			},
			/**
			 * 事件监听
			 * from jquery ui
			 */
			_on: function( suppressDisabledCheck, element, handlers ) {
				var delegateElement,
					instance = this;

				// no suppressDisabledCheck flag, shuffle arguments
				if ( typeof suppressDisabledCheck !== "boolean" ) {
					handlers = element;
					element = suppressDisabledCheck;
					suppressDisabledCheck = false;
				}

				// no element argument, shuffle and use this.element
				if ( !handlers ) {
					handlers = element;
					element = this.element;
					delegateElement = this.widget();
				} else {
					element = delegateElement = $( element );
					this.bindings = this.bindings.add( element );
				}

				$.each( handlers, function( event, handler ) {
					function handlerProxy() {
						// allow widgets to customize the disabled handling
						// - disabled as an array instead of boolean
						// - disabled class as method for disabling individual parts
						if ( !suppressDisabledCheck &&
								( instance.options.disabled === true ||
									$( this ).hasClass( "ui-state-disabled" ) ) ) {
							return;
						}
						return ( typeof handler === "string" ? instance[ handler ] : handler )
							.apply( instance, arguments );
					}

					// copy the guid so direct unbinding works
					if ( typeof handler !== "string" ) {
						handlerProxy.guid = handler.guid =
							handler.guid || handlerProxy.guid || $.guid++;
					}

					var match = event.match( /^([\w:-]*)\s*(.*)$/ ),
						eventName = match[1] + instance.eventNamespace,
						selector = match[2];
					if ( selector ) {
						delegateElement.delegate( selector, eventName, handlerProxy );
					} else {
						element.bind( eventName, handlerProxy );
					}
				});
			},
			
			_execCommand:function(dom,commandObj){
				alert(commandObj.name);
			},
			
			widget: function() {
				return this.element;
			},
			
			destroy:function(){
				
			}
		}
	};
	
	$.youi.console.log('init youi.core');
	
	window.$ = $;
	module.exports = $.youi;
})();