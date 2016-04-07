/**
 * field组件
 * Copyright (c) 2009 zhouyi
 * licenses
 * doc 
 */
(function($) {
	var _log = $.youi.log;
	
	/*********************************fieldLayout 组件*******************************
	 * @class fieldLayout field布局组件
	 *******************************************************************************/
	$.widget("youi.fieldLayout",{
		options:{
			initHtml:true,
			columns:2
		},
		
		fields:[],//field模型集合
		
		_create:function(){
			//初始化属性
			this.fields = [];
			//初始化模型
			this._initFieldModels();
			this._defaultHtmls();
			this._initAction();
			//初始化field
			this._initFields();
			this._autoAlign();
			
		},
		
		/**
		 * 初始化字段集模型
		 */
		_initFieldModels:function(){
			if(!this.options.fields||!this.options.fields.length)return;
			
			var optionsFields = this.options.fields,
				fields = [],
				prefix		= this.options.prefix;
			$(optionsFields).each(function(index){
				
				var fieldId = $.youi.fieldUtils.getFieldId(this.property,prefix),
					prevField = '',
					nextField = '';
				//功能权限过滤 TODO
				
				//获得上一个可见字段
				var prevIndex = index-1;
				while(optionsFields[prevIndex]){
					if(optionsFields[prevIndex].type!='fieldHidden'){
						prevField=$.youi.fieldUtils.getFieldId(optionsFields[prevIndex].property,prefix);
						break;
					}
					prevIndex--;
				}
				//获得下一个可见字段
				var nextIndex = index+1;
				while(optionsFields[nextIndex]){
					if(optionsFields[nextIndex].type!='fieldHidden'){
						nextField=$.youi.fieldUtils.getFieldId(optionsFields[nextIndex].property,prefix);
						break;
					}
					nextIndex++;
				}
				if(this.parentProperties&&this.parentProperties.length){
					var parentIds = [];
					$(this.parentProperties).each(function(){
						parentIds.push($.youi.fieldUtils.getFieldId(this));
					});
					//_log.info('parentIds:'+parentIds);
					this.parentIds = parentIds;
				}
				//处理模型
				fields.push($.extend({
					id	  :fieldId,
					type  :'fieldText',
					column:1,
					nextField:nextField,
					prevField:prevField,
					prefix   :prefix
				},this));
			});
			this.fields = fields;
		},
		/**
		 * 使用js生成html
		 */
		_defaultHtmls:function(){
			if(!this.options.initHtml)return;
			var html = $.youi.flcFactory.getContent('simple',this.fields,{columns:this.options.columns,showBorder:this.options.showBorder});
			this.element.addClass('youi-fieldLayout').html(html);
		},
		
		_initAction:function(){
			//body.resize
			$('body',document).bind('body.resize',{ui:this.element},function(event){
				try{
					event.data.ui.fieldLayout('resize');
				}catch(err){
					
				}
			});
		},
		/**
		 * 初始化fields
		 */
		_initFields:function(){
			var fields = this.fields;
			this.element.find('.youi-field').each(function(index){
				var id		  = this.getAttribute('id'),
					fieldModel	  = getFieldModel(fields,id);
				
				if(console){
					console.log(index+' - - '+id+':'+fieldModel.property);
				}
				if(fieldModel&&$.youi[fieldModel.type]){//$(this)[fieldModel.type]
					//初始化field组件
					$(this)[fieldModel.type](fieldModel);
				}
			});
			
			
		},
		
		validate:function(){
			this.element.find('.youi-field').fieldValidate();
			if(this.element.find('.youi-field.validating,.youi-field.validate-error').length==0){
				return true;
			}
			return false;
		},
		
		/**
		 * 获得键值对
		 */
		getFieldValues:function(){
			var fieldValues = [];
			var fields = this.fields;
			this.element.find('.youi-field').each(function(index){
				var id = this.getAttribute('id'),
					fieldModel = getFieldModel(fields,id),
					value;
					//fieldCustom
				if($(this).hasClass('fieldCustom')){//自定义field
					fieldModel = {
						property:this.getAttribute('property')
					};
				}
				if(fieldModel){
					value = $(this).fieldValue();
					property = fieldModel.property;
					if(typeof(value)=='string'){
						fieldValues.push({
							property:property,
							value:value
						});
					}else if(value&&value.length){
						$(value).each(function(){
							fieldValues.push({
								property:property,
								value:this
							});
						});
					}
				}
			});
			return fieldValues;
		},
		
		/**
		 *
		 */
		getRecord:function(){
			var record = {};
			this.element.find('.youi-field').each(function(index){
				var property = this.getAttribute('property'),
					value;
				if(property){
					value = $(this).fieldValue();
					if(value){
						record[property] = value;
					}
					value = null;
				}
			});
			return record;
		},
		
		getFieldParamters:function(){
			var params = [];
			var fields = this.fields;
			this.element.find('.youi-field').each(function(index){
				var id = this.getAttribute('id'),
					fieldModel = getFieldModel(fields,id),
					value;
				if($(this).hasClass('fieldCustom')){//自定义field
					fieldModel = {
						property:this.getAttribute('property')
					};
				}
				if(fieldModel){
					value = $(this).fieldValue();
					property = fieldModel.property;
					if(typeof(value)=='string'){
						params.push($.youi.parameterUtils.propertyParameter(property,value));
					}else if(value&&value.length){
						$(value).each(function(){
							params.push($.youi.parameterUtils.propertyParameter(property,value));
						});
					}
				}
			});
			return params;
		},
		/**
		 * 设置fieldLayout中的field的值
		 */
		setFieldValues:function(record){
			var fields = this.fields;
			this.element.find('.youi-field').each(function(index){
				var property = this.getAttribute('property'),
					showProperty = $('input.textInput',this).attr('showProperty'),
					value = $.youi.recordUtils.getPropertyValue(record,property),//propertyMap[property.replace(/\./g,'_')];
					show = $.youi.recordUtils.getPropertyValue(record,showProperty);
				if(value!=null&&!value.length==0){
					try{
						$(this).fieldValue(value,show?show:value);
					}catch(err){
						//捕捉设置field值时可能抛出的异常
						_log.info('设置值异常：'+err);
					}
				}
			});
		},
		
		fieldDefaultValue:function(property,value){
			var fieldElement = this.element.find('.youi-field[property="'+property+'"]');
			fieldElement.fieldDefaultValue(value);
			fieldElement.fieldReset();
		},
		
		fieldReadonly:function(readonly){
			if(readonly==true){
				this.element.find('.youi-field').addClass('readonly');
				this.element.find('.youi-field input.textInput').attr('readonly','readonly');
			}else{
				this.element.find('.youi-field').removeClass('readonly');
				this.element.find('.youi-field input.textInput').removeAttr('readonly');
			}
		},
		
		reset:function(){
			this.element.find('.youi-field').fieldReset();
		},
		/**
		 * 
		 */
		resize:function(){
			if(!this.element.is(':visible')){
				return;
			}
			this._autoAlign();
		},
		/**
		 * 自动对齐
		 */
		_autoAlign:function(){
			if(this.element.is(':visible')){
				//自动对齐
				this.element.find('.youi-field.autoAlign').each(function(){
					var containerWidth = $(this).parent().width();
					$(this).fieldWidth(containerWidth-5);//重新计算宽度
				});
			}
		},
		
		destroy:function(){
			//
			$.Widget.prototype.destroy.apply(this);
		}
	});
	/**
	 * 转化record对象为 键值对<String,String> 类型对象
	 * 使用符号 _ 连接嵌套对象的属性
	 */
	function recordToStringMap(record,prefix){
		var propertyMap = {},
			key,
			value;
			
		for(var property in record){
			value = record[property];
			key = (prefix?prefix+'_':'')+property;
			if(!value)continue;
			if(typeof(value)=='string'||value.length){
				propertyMap[key] = value;
			}else if(typeof(value)=='object'){
				$.extend(propertyMap,recordToStringMap(value,(prefix?prefix+'_':'')+property));
			}
			value = null;
			key   = null;
		}
		return propertyMap;
	}
	
	function getFieldModel(fields,fieldId){
		for(var i=0;i<fields.length;i++){
			if(fields[i].id==fieldId){
				return fields[i];
			}
		}
		return null;
	}
	
	$.extend($.youi.fieldLayout,{
		getter:['getFieldValues','getFieldParamters']
	});
	
	$.youi.flcFactory = {
		getContent:function(type,fields,options){
			switch(type){
				default:
					return $.youi.flcFactory.getSimpleContent(fields,options);
			}
		},
		
		getSimpleContent : function(fields,options){
			var fieldClass  = 'youi-field',
				htmls 		= [],
				rowIndex   	= 0,//行定位
				cellIndex   = 0,//当前列定位
				rowHtmls    = [],//存储每一行的td内容
				tableColumns= options.columns*2,//表格的总列数
				fieldIndex =  0,//field定位索引
				
				filedCount 	=  fields.length,
				fieldsOption = [];
				
			$(fields).each(function(index){
				if(this.type=='fieldHidden'){
					htmls.push('<div fieldType="fieldHidden" class="'+fieldClass+' fieldHidden" id="'+this.id+'"></div>');
				}else{
					var fieldColumn = Math.min(this.column*2,tableColumns),//记录每一个field所占用的table的列数
						colspan = fieldColumn-1;//当前字段的列占位
						
					fieldIndex += fieldColumn;//field 在所有fileds中的占位索引
					cellIndex  += fieldColumn;//field 在当前行的占位索引
					//_log.info(this.property+'-cellIndex:'+cellIndex);
					if(cellIndex>tableColumns){//如果超出了table的最大列数，则折行
						//补充跳过的单元格
						var addOnTds = tableColumns - (cellIndex-fieldColumn);
						for(var i=0;i<addOnTds;i++){
							rowHtmls[rowIndex].push('<td></td>');
						}
						fieldIndex   +=addOnTds;//加上跳过单元格的占位索引
						cellIndex    = fieldColumn;
					}
					rowIndex = Math.ceil(fieldIndex/tableColumns)-1;//行索引
					//_log.info(this.property+'-fieldIndex'+fieldIndex+'-rowIndex'+rowIndex+',fieldColumn:'+fieldColumn);
					if(!rowHtmls[rowIndex]){
						rowHtmls[rowIndex] = [];
						cellIndex = fieldColumn;
					}
					//
					rowHtmls[rowIndex].push('<td class="field-label '+(this.notNull?'notNull':'')+'"><label for="'+this.type+'-'+this.id+'">'+this.caption+'：</label></td>');
					rowHtmls[rowIndex].push('<td');
					if(colspan>1){
						rowHtmls[rowIndex].push(' colspan="'+colspan+'" ');
					}
					rowHtmls[rowIndex].push('><div id="'+this.id+'" class="'+fieldClass+'" fieldType="'+this.type+'"></div></td>');
					fieldOption = null;
				}
			});
			//补充最后一行的空单元格
			var rows = rowHtmls.length;
			if(fieldIndex<tableColumns*rows){
				for(var i=0;i<tableColumns*rows-fieldIndex;i++){
					rowHtmls[rows-1].push('<td></td>');
				}
			}
			htmls.push('<table'+(options.showBorder?' class="showBorder"  border="1"':'')+' width="100%">');
			//列宽度控制行
			htmls.push('<tr>');
			for(var i=0;i<tableColumns;i++){
				htmls.push('<td class="contral" style="'+(i%2==0?'width:80px;':'')+'"></td>');
			}
			htmls.push('</tr>');
			$(rowHtmls).each(function(){
				htmls.push('<tr>');
				htmls = htmls.concat(this);
				htmls.push('</tr>');
			});
			htmls.push('</table>');
			return htmls.join('');
		}
	}
})(jQuery);