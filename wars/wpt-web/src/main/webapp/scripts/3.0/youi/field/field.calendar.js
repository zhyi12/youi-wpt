/**
 * field组件
 * Copyright (c) 2009 zhouyi
 * licenses
 * doc 
 */
(function($) {
	var _log = $.youi.log;
	/**
	 * fieldCalendar组件
	 * 
	 */
	
	$.widget("youi.fieldCalendar",$.extend({},$.youi.field,{
		options:{
			textFormat:'yyyy-MM-dd',
			format:'yyyy-MM-dd'
		},
		_initField:function(){
			if(this.options.readonly!=true&&!this.element.hasClass('readonly')){
				var self = this;
				var datepickerFormat = $.trim(this.options.textFormat);
				
				if(this.options.format=='millis'){
					datepickerFormat = 'yyyy-MM-dd HH:mm';
					this.options.textFormat = datepickerFormat;
				}
				
				if(datepickerFormat.indexOf(' ')!=-1){
//					datepickerFormat = datepickerFormat.split(' ')[0];
					this.element.find('input.textInput').addClass('timable');
				}
				
				if(console){
					console.log('f '+this.element.attr('id')+'::'+this.options.property);
				}
				
				this.element.find('input.textInput').datepicker({
					format:datepickerFormat,
					select:function(selectDate,selectValue){
						
						var value = $.youi.dateUtil.dateToStr(selectDate, self.options.format);
						
						var oldValue = self.getValue();
						
						self.setValue(value);
						
						if(oldValue!=value&&$.isFunction(window[self.options.id+'_change'])){
							//添加回调动作
							window[self.options.id+'_change'].apply(self.element[0],[value]);
						}
					},
					close:function(){
						self._checkTextValue();//检查文本框中的日期
					}
				});
			}
		},
		
		_fieldHtmls:function(){
			var htmls = [],
				iconWidth = 17,
				fieldWidth = this.options.width-11;
			htmls.push("<table cellpadding=\"0\" cellspacing=\"0\" width=\""+fieldWidth+"\"><tbody><tr>");
			htmls.push(	"<td valign=\"top\" width=\""+(fieldWidth-iconWidth)+"\"><input style=\"width:"+(fieldWidth-iconWidth-6)+"px;\" type=\"text\" class=\"textInput\"></input><input type=\"hidden\" class=\"value\"></input></td>");
			htmls.push(	"<td class=\"youi-icon select-down\" width=\""+iconWidth+"px\"></td>");
			htmls.push("</tr></tbody></table>");
			return htmls.join('');
		},
		
		_initAction:function(){
			this.element.find('.youi-icon.select-down').click(function(){
				$('input.textInput',this.parentNode).datepicker('openDatepickerPanel');
			});
			
			this.element.delegate('.calendar-time','mouseover',function(event){
				if(!$(event.delegateTarget).hasClass('readonly')){
					_showTimeSelectPanel(this.getAttribute('id'),$('input',this));
				}
			});
			
			if(!$('body',document).hasClass('event-timePop')){//防止重复绑定
				$(document).bind('mousedown',function(event){//绑定当document上
					$('body>.youi-fieldCalendar-select-panel').hide();
				});
			}
		},
		
		_checkTextValue:function(){
			var dateText = this.element.find('.textInput:first').val();
			if(dateText){//如果存在值
				var date = $.youi.dateUtil.strToDate(dateText, this.options.textFormat);
				var value ='';
				if(date){
					value = $.youi.dateUtil.dateToStr(date, this.options.format);
					this.element.find('input.value').val(value);
				}else{
					this.clear();
				}
			}
		},
		
		setValue:function(value){
			this.element.find('input.value').val(value);
			var text = '';
			
			if(value&&value!=0){
				var date = $.youi.dateUtil.strToDate(value, this.options.format);
				if(!date){
					return;
				}
				text = $.youi.dateUtil.dateToStr(date, this.options.textFormat);
			}
			this.element.find('input.textInput').val(text);
		},
		
		getValue:function(){
			return this.element.find('input.value').val();
		},
		
		clear:function(){
			this.element.find('.calendar-time input').val('');
			this.element.find('input.textInput,input.value').val('');
		},
		
		focus:function(){
			this.element.find('input.textInput').datepicker('openDatepickerPanel');
		},
		
		setWidth:function(width){
			this.element.find('table').attr('width',width+'px').find('td:first').attr('width',(width-15)+'px');
			this.element.width(width).find('.textInput').width(width-15);
		}
	}));
	
	function _showTimeSelectPanel(type,inputElement){
		var panel = null,openStyle = 'timepanel_'+new Date().getTime();
		
		$('>.youi-fieldCalendar-select-panel',$('body',document)).hide();
		
		inputElement.addClass(openStyle);
		inputElement.focus();
		var selectedValue = inputElement.val();
		switch(type){
			case 'hour':
				panel = _getTimeSelectPanel(type,1,24);
				break;
			case 'minute':
				panel = _getTimeSelectPanel(type,3,60);
				break;
			case 'second':
				panel = _getTimeSelectPanel(type,3,60);
				break;
		}
		
		if(panel&&panel.length){
			//$.youi.popUtils.showPopBackground($(window).width()-10);
			var panelTop = inputElement.offset().top+inputElement.innerHeight()+5;
			//如果弹窗口位置超出屏幕下方,调整位置显示在filed上方 
			var windowHeight = $(window).height();
			var pos = {
				left:inputElement.offset().left,
				top:panelTop
			};
			if(windowHeight-panelTop<panel.height()){
				//往上弹出
//				pos = ('top',inputElement.offset().top-inputElement.height()-1);
			}
			$('input#openStyle').val(openStyle);
			panel.css(pos).show();
		}
		
	}
	
	function _getTimeSelectPanel(type,inteval,max,noZero){
		var timePanelId = 'youi-fieldCalendar-'+type+'-select-panel';
		var timePanel = $('#'+timePanelId);
		if(timePanel.length==0){
			var timePanelHtmls = ['<div class="youi-fieldCalendar-select-panel" id="'+timePanelId+'">'];
			timePanelHtmls.push('<input type="hidden" id="openStyle"/>');
			for(var i=0;i<max/inteval;i++){
				var index = inteval*(i+(noZero?1:0));
				var text;
				if(index<10){
					text = '0'+index;//
				}else{
					text = index;
				}
				timePanelHtmls.push('<div class="option-item">'+text+'</div>');
				
				//中间存在10的倍数数据时
				if(inteval>1&&index>0&&index%10>(index+inteval)%10){
					var cIndex = Math.ceil(index/10)*10;
					if(cIndex<max&&cIndex%inteval!=0){
						timePanelHtmls.push('<div class="option-item">'+cIndex+'</div>');
					}
				}
			}
			timePanelHtmls.push('</div>');
			timePanel = $(timePanelHtmls.join('')).appendTo($('body',document));
			
			timePanel.find('.option-item').bind('mousedown',function(event){
				var panel = $(this).parent();
				var value = $(this).text();
				var openStyle = panel.find('#openStyle').val();
				$('.'+openStyle).val(value);
				panel.hide();
				$.youi.popUtils.hidePopBackground();
				return false;
			});
		}
		return timePanel;
	}
	
})(jQuery);