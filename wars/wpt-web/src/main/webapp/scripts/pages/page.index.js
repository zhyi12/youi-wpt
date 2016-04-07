$.youi.serverConfig = $.extend({},$.youi.serverConfig,{
	pageParams : {id:'zs'}
});

var Page = $.youi.page,
	Tabs = $.youi.tabs,
	TabItem = $.youi.tabs.item,
	Form = $.youi.form,
	FieldText = $.youi.fieldText,
	Button = $.youi.button,
	FieldPart = $.youi.field.part;

ReactDOM.render(
	<Tabs itemSrc="scripts/3.0/lib/page.{id}.min.js">
		<TabItem id="welcome" icon="home" caption="首页"></TabItem>
		<TabItem id="msg" icon="msg" caption="消息"></TabItem>
		<TabItem id="tel" icon="tel" caption="通讯录"></TabItem>
		<TabItem id="config" icon="config" caption="设置"></TabItem>
	</Tabs>, 
	$.youi.getCurrentPageConatiner()[0],function(){
		console.log('complete page load ');
	});

