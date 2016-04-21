/*!
 * youi JavaScript Library v3.0.0
 * 
 *
 * Copyright 2016-2020, zhyi_12
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Date: 2016-1-4
 */
(function( $, undefined ) {
	//
	window.require = window.require||function(){};
	
	'use strict';
	$.youi = $.youi||{};//初始化组件空间
	
	$.extend($.youi,{
		version:'3.0.0',
		
		/**
		 * 服务配置
		 */
		serverConfig:{
			contextPath:'',//web上下文路径
			convertArray:[],//服务字典
			pageUrls:{}
		},
		/**
		 * 
		 */
		getMessage:function(source, params) {
			if ( arguments.length == 1 ) {
				return function() {
					var args = $.makeArray(arguments);
					args.unshift(source);
					return $.youi.getMessage.apply( this, args );
				};
			}
				
			if ( arguments.length > 2 && params.constructor != Array  ) {
				params = $.makeArray(arguments).slice(1);
			}
			if ( params.constructor != Array ) {
				params = [ params ];
			}
			$.each(params, function(i, n) {
				source = source.replace(new RegExp("\\{" + i + "\\}", "g"), ''+n);
			});
			return source;
		},
		//国际化资源
		resource:{
			
		},
		
		/**
		 * 文本信息资源工具
		 */
		resourceUtils:{
			get:function(key){
				var value = $.youi.resource[key];
				if(!value)return '';
				if($.isFunction(value)){
					return value(Array.prototype.slice.call( arguments, 1 ));
				}
				return value;
			}
		},
		
		timerUtils:{
			/**
			 * 计时器
			 */
			countdown:function(celem,interval,callback){
				var countdownFunc = _countdown(celem,interval,callback);
				//调用倒计时函数
				countdownFunc();
				
				function _countdown(elem,interval,callback){
					return function(){
						var value = $.trim(elem.text());
						var count = 0;
						if(!isNaN(value)){
							count = parseInt(value);
							count--;
						}
						
						if(count<=0){//如果当前count为0，退出计时
							if($.isFunction(callback)){
								callback();
								elem = null;
							}
						}else{
							//等待interval时间后调用倒计时函数
							elem.delay(interval).show(countdownFunc).text(count);
						}
					}
				}
			}
		},
		
		pageHashPrefix:'#p',
		/**
		 * 页面工具
		 */
		pageUtils:{
			/**
			 * 加载页面到指定的容器
			 */
			loadPage:function(pageContianer,pageUrl,after){
				$(this).addClass('loading');
				if(pageUrl.indexOf('/')===0){
					pageUrl = $.youi.serverConfig.contextPath+''+pageUrl;
				}
				
				pageContianer.load(pageUrl,function(responseText,status,jqXhr){
					if(jqXhr.getResponseHeader('X-LOGIN')=="true"){
						window.location.reload(true);
					}
					
					$(this).removeClass('loading').addClass('loaded');
					if($.isFunction(after)){
						after.apply(this);
					}
				});
			},
			
			/**
			 * 根据元素ID和页面ID查找页面元素
			 */
			element:function(elemId,pageId){
				var id = elemId;
				if(pageId){
					id ='P_'+ pageId+'_'+elemId;
				}
				return $('#'+id);
			},
			/**
			 * 
			 */
			getWidgetPageHash:function(widgetElem){
				var pageHash = window.location.hash;
				if(pageHash){
					var pageHashs = pageHash.split('|');
					var index = widgetElem.parents('[data-hash]').length;
					return pageHashs[index]||'';
				}
			},
			/**
			 * 替换pageUrl中的参数
			 */
			parsePageUrl:function(pageUrl,paramObj){
				return $.youi.recordUtils.replaceByRecord(pageUrl,paramObj);
			},
			
			goPage:function(pageUrl){
				window.location.href = pageUrl;
			},
			
			goBackPage:function(){
				window.history.go(-1);
			},
			
			goForwardPage:function(){
				window.history.go(1);
			},
			
			getFuncUrls:function(pageId,urls){
				var funcUrls = {};
				
				if(urls&&$.youi.serverConfig.urls){
					var urlArr = urls.split(',');
					for(var i=0;i<urlArr.length;i++){
						if(urlArr[i]){
							var key = (pageId?("P_"+pageId+"_"):'')+urlArr[i];
							if($.youi.serverConfig.urls[key]){
								funcUrls[urlArr[i]] = $.youi.serverConfig.urls[key];
							}
						}
					}
				}
				return funcUrls;
			}
		},
		/**
		 * 日志
		 */
		log:{
			info:function(msg){
				if(console){
					console.log(msg);
				}
			},
			error:function(msg){
				if(console){
					console.log(msg);
				}
			},debug:function(msg){
				if(console){
					console.log(msg);
				}
			}
		},
		
		/**
		 * 组件工具
		 */
		widgetUtils:{
			/**
			 * 执行组件回调函数
			 */
			funcApply:function(widgetDom,funcName){
				var widgetId = widgetDom.getAttribute('id');
				if($.isFunction(window[widgetId+'_'+funcName])){
					var params = [];
					if ( arguments.length > 2){
						params = $.makeArray(arguments).slice(2);
					}
					return window[widgetId+'_'+funcName].apply(widgetDom,params);
				}
			},
			/**
			 * 为组件绑定位置计算方法
			 */
			bindResize:function(widgetElement,widgetName){
				widgetElement.attr('data-resize',true).bind('widget.resize',function(event,ui){
					if(widgetElement.is(':hidden'))return;
					var oldResizekey = widgetElement.data('resizekey');
					
					var resizekey = [widgetElement.height(),widgetElement.width()].join('|');
					
					if(ui&&ui.forceResize==true){
						//强制重新计算位置
						$.youi.log.debug('force resize '+widgetName);
					}
					
					if($.isFunction(widgetElement['resize'])){
						widgetElement[widgetName]('resize');
						widgetElement.data('resizekey',[widgetElement.height(),widgetElement.width()].join('|'));
					}
					//阻止冒泡
					event.stopPropagation();
				});
			},
			/**
			 * 触发位置计算
			 */
			triggerResize:function(container,forceResize){
				if(container&&container.length){
					
				}else{
					var bodyElem = $('body',document);
					if(bodyElem.is('[data-resize="true"]')){
						bodyElem.trigger('widget.resize');
					}
					container = bodyElem;
				}
				container.find(':visible[data-resize="true"]').trigger('widget.resize',{forceResize:forceResize});
			}
		},
		/**
		 *字符处理工具 
		 */
		stringUtils:{
			
			isEmpty:function(value){
				return !$.youi.stringUtils.notEmpty(value);
			},
			
			notEmpty:function(value){
				if(value){
					return true;
				}else if(value===0){
					return true;
				}else if(value===false){
					return true;
				}
				return false;
			},
			
			fixValue:function(value,defaultValue){
				var result = value;
				if($.youi.stringUtils.isEmpty(value)){
					if($.youi.stringUtils.isEmpty(defaultValue)){
						defaultValue = '';
					}
					result = defaultValue;
				}
				
				return result;
			}
		},
		/**
		 * 记录集工具
		 * $.youi.recordUtils.recordToParameters
		 */
		recordUtils:{
			replaceByRecord:function(str,record){
				if(typeof(record)==='object'){
					for(var prop in record){
						if($.youi.stringUtils.notEmpty(record[prop])){
							str = str.replace(new RegExp("\\{" + prop + "\\}", "g"), record[prop]);
						}else{
							str = str.replace(new RegExp("\\{" + prop + "\\}", "g"), '');
						}
					}
				}
				return str;
			},
			/**
			 * 
			 */
			getPropertyValue:function(record,property){
				if(!property)return;
				var value;
				var properties = property.split('.');
				if(properties.length>1){//处理多级属性
					value = record;
					for(var i=0;i<properties.length;i++){
						value = value[properties[i]];
						if($.youi.stringUtils.isEmpty(value))return;
					}
				}else{
					value = record[property];
				}
				return value;
			},
			/**
			 * 
			 */
			setPropertyValue:function(record,property,value){
				if(!property)return;
				var properties = property.split('.');
				if(properties.length>1){//处理多级属性
					
					var vObject = record[properties[0]]||{};
					record[properties[0]] = vObject;
					for(var i=1;i<properties.length;i++){
						if(i==properties.length-1){
							vObject[properties[i]] = value;
						}else{
							vObject = vObject[properties[i]] || {};
						}
					}
				}else{
					record[property] = value;
				}
			},
			
			/**
			 * record 对象转换为参数
			 */
			recordToParameters:function(record){
				var fieldValues = [];
				if(record){
					//隐藏域
					for(var property in record){
						var value = record[property];
						if(value){
							if(typeof(value)=='string'||typeof(value)=='number'){
								fieldValues.push({
									property:property,
									value:value
								});//{}
							}else if(value&&$.isArray(value)){
								if(typeof(value[0])=='object'){//集合对象类型
									$(value).each(function(index){
										var valueRecord = this;
										for(var p in valueRecord){
											fieldValues.push({
												property:property+'['+index+'].'+p,
												value:valueRecord[p]
											});
										}
									});
								}else{
									$(value).each(function(index){
										fieldValues.push({
											property:property,
											value:this
										});
									});
								}
							}else if(value&&typeof(value) === 'object') {//&&property.indexOf('.'!=-1)
								//如果是对象，直接展开
								fieldValues = fieldValues.concat($.youi.recordUtils.recordToParameters(value));
							}
						}
						value = null;
					}
				}
				
				return fieldValues;
			}
		},
		/**
		 * 
		 */
		parameterUtils:{
			toParams:function(fieldValues){
				var params = [];
				for(var i=0;i<fieldValues.length;i++){
					params.push($.youi.parameterUtils.propertyParameter(fieldValues[i].property,fieldValues[i].value));
				}
				return params;
			},
			
			propertyParameter:function(property,value){
				var pamameters = [];
				if($.isArray(value)){
					$(value).each(function(){
						if(this!=null&&property)pamameters.push(''+property+'='+encodeURIComponent(this));
					});
				}else{
					if(property&&$.youi.stringUtils.notEmpty(value))pamameters.push(''+property+'='+encodeURIComponent(value));
				}
				return pamameters.join('&');
			},
			
			connectParameter:function(src,property,value){
				return src+(src.indexOf('?')==-1?'?':'&')+$.youi.parameterUtils.propertyParameter(property,value);
			}
		},
		
		buttonUtils:{
			/**
			 * 
			 */
			createButtons:function(buttons,group){
				var htmls = [];
				if(group){
					
				}
				//排序按钮
				buttons.sort(function(obj1,obj2){
					var order1 = obj1.order||0;
					var order2 = obj2.order||0;
					
					return order1-order2;
				});
				
				if(buttons&&buttons.length){
					for(var i=0;i<buttons.length;i++){
						htmls.push($.youi.buttonUtils.createButton(buttons[i]));
					}
				}
				
				return htmls.join('');
			},
			
			createButton:function(options){
				var htmls = [];
				
				if(options&&options.name){
					var activeStyle = options.active?'active-'+options.active:'active-0';
					var styles = ['youi-button btn btn-default',(options.disabled?'disabled':'')].concat(options.buttonStyles);
					
					htmls.push('<button type="button" ');
					//if(options.action){
					htmls.push( 'data-name="'+options.name+'" ');
					if(options.command){
						htmls.push( 'data-command="'+options.command+'" ');
					}
					
					if(options.tooltips){
						htmls.push( ' title="'+options.tooltips+'" ');
					}
					
					if(options.name=='close'){
						htmls.push( ' data-dismiss="modal" ');
					}
					
					//}
					htmls.push(' class="'+styles.join(' ')+'">');
					if(options.icon){
						htmls.push('<span class="youi-icon icon-'+options.icon+'"></span> ');
					}
					if(!options.tooltips){
						htmls.push(options.caption);
					}
					htmls.push('</button>');
				}
				
				return htmls.join('');
			}
		},
		
		messageUtils:{
			/**
			 * 
			 */
			showMessage:function(msg){
				alert(msg);
			},
			/**
			 * 
			 */
			showError:function(msg){
				$.youi.log.error(msg);
			},
			/**
			 * 
			 */
			confirm:function(message,confirmFunc){
				if(message&&arguments.length > 1){
					var params = $.makeArray(arguments).slice(2);
					_showConfirm(message,confirmFunc,params);
				}
			}
		},
		/**
		 * ajax工具
		 * $.youi.ajaxUtils.ajax
		 */
		ajaxUtils:{
			ajax:function(ajaxOptions){
				if(!ajaxOptions.url){
					$.youi.messageUtils.showError('没有ajax url参数！');
					return;
				}
				if(ajaxOptions.notShowLoading!=true){
					_showLoading(ajaxOptions.wait);//
				}
				
				if($.youi.serverConfig.contextPath&&ajaxOptions.url.substring(0,1)=='/'
						&&ajaxOptions.url.indexOf($.youi.serverConfig.contextPath)==-1){
					ajaxOptions.url = $.youi.serverConfig.contextPath+ajaxOptions.url.substring(1);
				}
				
				var options = $.extend({},{
					dataType:'json',
					type:'POST',
					contentType:'application/x-www-form-urlencoded;charset=UTF-8',//配置提交的contentType
					headers:{'Authorization':$.youi.serverConfig.authorization},
					error:function(errMsg){
						$.youi.messageUtils.showError(errMsg);
					}
				},ajaxOptions);
				
				var oldSuccess = options.success || function(){};
				var oldError =  options.error || function(){};
				var domainValidator = options.domainValidator||function(){};
				
				options.complete = function(jqXHR, statusText, responseText){
					if(statusText=='error'){
						var errorMessage = $.youi.resourceUtils.get('urlNotFound',this.url);
						$.youi.messageUtils.showError(errorMessage);
						this.error.apply(this,[errorMessage]);
					}
					_hideLoading();//关闭进度显示
				}
				
				options.success = function(results){
					//关闭进度显示
					_hideLoading();
					//通用信息处理
					//
					$.youi.ajaxUtils.resultsCheck(results,$.extend({},this,{
						error:oldError,
						domainValidator:domainValidator
					}))&&oldSuccess.apply(options,[results]);
				};
				
				$.ajax(options);
			},
			/**
			 * 通用结果集检查
			 */
			resultsCheck:function(results,options){
				if(results==null)return false;
				if(results.hasError){
					$.youi.messageUtils.showError(results.errorMsg);
					options.error.apply(options,[results.errorMsg]);
					return false;
				}
				
				var message = results.message;
				var checkFlag = true;
				if(message&&message.code){
					var code = message.code;
					switch(code){
						case '000000'://成功访问
							if(message.passed==null){
								$.youi.messageUtils.showMessage(message.info);
							}
							break;
						case '111111'://登录过期
							$.youi.messageUtils.showMessage(code+':'+message.info);
							checkFlag = false;
							window.location.href = $.youi.serverConfig.contextPath;//刷新页面
							return;
						case '111112'://对象属性校验不通过
							options.domainValidator.apply(options,[results]);
							options.error.apply(options,[message.info]);
							checkFlag = false;
							return;
						default:
							$.youi.messageUtils.showError(message.info);
							options.error.apply(options,[message.info]);
							checkFlag = false;
							return;
					}
				}
				return checkFlag;
			}
		}
	});
	
	/*
	 * 抽象类
	 */
	$.widget("youi.abstractWidget",$.ui.mouse, {
		_create:function(){
//			var startTime = this._getTime();
			this.element.addClass("youi-"+this.widgetName);
			
			this.options = $.extend({},this.element.data(),this.options);
			//设置id
			this.options.id = this.element.attr('id')||(this.widgetName+'_'+this.uuid);
			
			if(!this.element.attr('id')){
				this.element.attr('id',this.options.id);
			}
			
			this._initModel();
			
			if(this.options.initHtml!=false){
				this._defaultHtmls();
			}
			
			if(this.options.bindResize){
				$.youi.widgetUtils.bindResize(this.element,this.widgetName);
			}
			
			this._initAction();
			
			this._initWidget();
		},
		
		/**
		 * 执行组件中的动作
		 */
		execCommand:function(dom,commandObj){
			if(commandObj.name){
				var commandFunc = commandObj.name+'Command';
				
				var args = Array.prototype.slice.call( arguments, 0 );
				if($.isFunction(this[commandFunc])){
					this[commandFunc].apply(this,args);
				}else if($.isFunction(this[commandObj.name])){
					this[commandObj.name].apply(this,args);
				}else if(this.options.buttonActions&&this.options.buttonActions[commandObj.name]){
					var action = this.options.buttonActions[commandObj.name];
					
					if(typeof(action)=='string'){
						action = window[action];
					}
					
					if($.isFunction(action)){
						action.apply(this,args);
					}
				}else{
					this._callGloablFunc.apply(this,[commandObj.name].concat(args));
				}
			}
		},
		/**
		 * 调用全局函数
		 */
		_callGloablFunc:function(funcName){
			var gloablFuncName = this.options.id+'_'+funcName.replace(/\./g,'_');
			
			return this.__callGloablFunc.apply(this,[gloablFuncName].concat(Array.prototype.slice.call( arguments, 1 )));//   (gloablFuncName);
		},
		
		__callGloablFunc:function(gloablFuncName){
			//this._log.debug(this._buildLogMsg('调用全局函数 '+gloablFuncName+'.'));
			
			if($.isFunction(window[gloablFuncName])){
				var args = Array.prototype.slice.call( arguments, 1 );
				try{
					return window[gloablFuncName].apply(this.element[0],args);
				}catch(err){
					
				}
			}
			
			return true;
		},
		
		resize:function(){
			if(this.options.bindResize){
				//var startTime = this._getTime();
				this._resize();
				this.element.addClass('resized');
				//this._log.debug(this._buildLogMsg('resize complete.',startTime));
			}
		},
		
		_initModel:function(){},
		_defaultHtmls:function(){},
		_initAction:function(){},
		_initWidget:function(){},
		
		_resize:function(){}
		
	});
	
	function _showLoading(wait){
		
	}
	
	function _hideLoading(){
		
	}
	
	function _showConfirm(message,confirmFunc,params,widgetInstance){
		if(message){
			var dialogId = 'youi-dialog-confirm';
			var messageDialog = $('#'+dialogId);
			if(!messageDialog.length){
				messageDialog = $('<div id="'+dialogId+'"></div>');
			}
			
			messageDialog.html(message).dialog({
				autoOpen: false,
				modal:true,
				width:600,
				position:['center',100],
				title:'确认提示',
				minHeight:300,
				zIndex:9999,
				buttons:[
				     {name:'confirm',caption:'确认',icon:'submit'}
				],
				buttonActions:{
					'confirm':function(){
						this.close();
						confirmFunc.apply(this,params);
					}
				}
			}).dialog('open').show();
		}
	}
	
})(jQuery);