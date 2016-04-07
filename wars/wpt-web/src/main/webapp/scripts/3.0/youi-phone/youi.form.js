/* ========================================================================
 * Youi: youi.form.js v3.0
 * http://www.cjyoui.xyz/docs/youi/tabs.html
 * ========================================================================
 * Copyright 2015-2018 zhyi_12.
 * Licensed under MIT (http://www.cjyoui.xyz/license.html)
 * ======================================================================== */

var youi = require("./youi.core.js");
var Button = require("./youi.button.js");

youi.form = React.createClass({
	
	mixins:[youi.abstractWidget],
	
	getDefaultProps:function(){
		return{
			method:'POST'
		}
	},
	/**
	 * 初始状态
	 */
	getInitialState: function() {
		var idKeys = this.props.idKeys||'';
		var idKeyArr =idKeys.split(',');
		
		var record = {};
		
		if(idKeyArr&&idKeyArr.length>0){
			for(var i=0;i<idKeyArr.length;i++){
				record[idKeyArr[i]] = youi.serverConfig.pageParams[idKeyArr[i]];
			}
		}
		return record;
	},
	
	/**
	 * 
	 */
	componentDidMount :function(){
		
		var findSrc = this.props.findSrc;
		
		if(findSrc){//查询数据
			for(var prop in this.state){
				findSrc = findSrc.replace(new RegExp("\\{" + prop + "\\}", "g"), this.state[prop]);
			}
			
		    $.get(findSrc, function(result) {
		    	if (result&&result.record&&this.isMounted()) {
		    		this._fillRecord(result.record);
		    	}
		    }.bind(this));
		}
		this._create();
		//事件绑定
		this._on({
			//按钮事件
			'click .form-buttons button[data-command="formCommand"]':function(event){
				var datas = $(event.currentTarget).data();
				this._execCommand(event.currentTarget,datas);
			}
		});
	},
	
	/**
	 * 填充记录
	 */
	_fillRecord:function(record){
		if(this.refs&&record){
			for(var prop in this.refs){
				if(record[prop]!=null){
					$('.value',this.refs[prop]).trigger('setValue',{value:record[prop]});
				}
			}
		}
	},
	
	/**
	 * 
	 * @returns
	 */
	render:function() {
		var formState = this.state;
	    return (
	      <form method={this.props.method} className="youi-form" action={this.props.action}>
	      {
		      React.Children.map(this.props.children, function (child,index) {
		    	  if(child.props.property!=null){
		    		  var props = $.extend({},child.props,{value:formState[child.props.property]});
			    	  var field = $.extend({},child,{props:props});
			    	  return <div className="youi-field-container" ref={child.props.property}><div className="field-label">{child.props.caption}</div><div className="field-content">{field}</div></div>;
		    	  }
		      })
	      }
		      <div className="form-buttons btn-group">
		      {
			      React.Children.map(this.props.children, function (child) {
			    	  if(child.props.name!=null){
			    		  var props = $.extend({},child.props,{command:'formCommand'});
				    	  var button = $.extend({},child,{props:props});
				    	  return button;
			    	  }
			      })
		      }
		      	  <Button command="formCommand" name="submit" type="submit" caption="提交"></Button>
		      	  <Button command="formCommand" name="reset" type="reset" caption="重置"></Button>
		      </div>
		  </form>
	    );
	}
});

youi.field = {
	getValue:function(){
		
	},
	
	validate:function(){
		
	}
};

youi.field.part = React.createClass({
	render:function() {
		return <div className="field-part">{this.props.children}</div>;
	}
});

youi.fieldText = React.createClass({
	mixins:[youi.abstractWidget,youi.field],
	
	getInitialState: function() {
	    return {};
	},
	
	componentDidMount :function(){
		this._create();
		this._on({
			'setValue .value':function(event,ui){
				$(event.currentTarget).attr('value',ui.value);
				this._setValue(ui.value);
			}
		});
	},
	
	handleChange:function(event) {
	    this._setValue(event.target.value);
	},
	/**
	 * 设置值
	 */
	_setValue:function(value){
		this.setState({"value":value});
	},
	
	render:function() {
	    return <div className="youi-field fieldText">
	    			{
	    				React.Children.map(this.props.children, function (child) {
	  			    	  	if(child.props.position==='before'){
	  			    	  		return child;
	  			    	  	}
	  			      	})
	    			}
	    			<div className="field-part field-part-grow"><input className="textInput value" type="text" onChange={this.handleChange} defaultValue={this.props.value} name={this.props.property}/></div>
	    			{
	    				React.Children.map(this.props.children, function (child) {
	  			    	  	if(child.props.position!=='before'){
	  			    	  		return child;
	  			    	  	}
	  			      	})
	    			}
	    		</div>;
	}
});


youi.console.log('init youi.form');

module.exports = youi.form;
