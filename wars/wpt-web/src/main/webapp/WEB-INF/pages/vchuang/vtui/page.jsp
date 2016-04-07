<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page>
	<youi:style href="/styles/page/phone.css?1=31"></youi:style>
	
    <youi:script src="/scripts/ueditor/ueditor.config.js"/>
	<youi:script src="/scripts/ueditor/ueditor.all.min.js"/>
	<style>
		.propertyForm-popover{
			max-width:none;
			min-height:50px;
			z-index:99;
		}
		
		.propertyForm-popover.popover.right > .arrow{
			top:30px;
		}
		
		.property-group .property-desc{
			margin-bottom:2px;
			padding-bottom:3px;
			border-bottom:1px solid #f9f9f9;
		}
		
		.property-group .property-desc .youi-field{
			display:none;
		}
		
		.property-group .property-desc.editing .youi-field{
			display:block;
		}
		
		.property-group .property-desc.editing .youi-field.input-group{
			display:table;
		}
		
		.property-group .property-desc .youi-field,
		.property-group .property-desc .property-value{
		    min-height:34px;
		    line-height: 34px;
		}
		
		.property-group .property-desc.editing .property-value{
			display:none;
		}
		
		.youi-propertyForm .rich-editor{
			position:relative;
			display:none;
		}
		
		.youi-propertyForm.rich-editor-panel .property-group{
			display:none;
		}
		
		.youi-propertyForm.rich-editor-panel .rich-editor{
			display:block;
		}
		
		.widget-container,.field-container{
			min-height:24px;
			border:1px dotted silver;
		}
		
		.phone-page-content{
			position:relative;
		}
		
		.popover-close{
		    position: absolute;
		    right: -15px;
    		top: -12px;
		    border: 1px solid silver;
		    width: 30px;
		    height: 30px;
		    padding: 6px;
		    text-align: center;
		    border-radius: 15px;
		    vertical-align: middle;
		    background: white;
		    cursor:pointer;
		}
		
		.mui-widget{
			float: left;
			padding:0px;
			min-height:20px;
			border:1px dotted silver;
		}
		
		.mui-widget.selected{
			border:1px dotted red;
		}
		
		.mui-text{
			padding:5px;
		}
		
		.field-container{
			display: -webkit-box;
		    display: -webkit-flex;
		    display: -ms-flexbox;
			display: flex;
			padding:5px;
		}
		
		.field-container .mui-field-label{
			width: 80px;
   	 		line-height: 32px;
		}
		
	</style>
	
	<youi:ajaxUrl name="getWxPage" src="/wxPageManager/getWxPage.json"></youi:ajaxUrl>
	<youi:ajaxUrl name="removeWxPage" src="/wxPageManager/removeWxPage.json"></youi:ajaxUrl>
	
	<!-- 微网页树右键菜单 -->
	<giui:xmenu id="xmenu_pages">
		<giui:xmenuItem caption="添加微网页" icon="plus" name="addPage" groups="treeNode"/>
		<giui:xmenuItem caption="删除微网页 {0}" icon="remove" name="removePage" groups="page,tabpage"/>
		<giui:xmenuItem caption="删除文件夹 {0}" icon="remove" name="removeFolder" groups="folder"/>
	</giui:xmenu>
	
	<giui:form id="form_addWxPage" caption="新增微网页" dialog="true" action="/wxPageManager/saveWxPage.json">
		<giui:fieldLayout columns="1" prefix="addWxPage">
			<giui:fieldHidden property="wxPageId"/>
			<giui:fieldHidden property="subscriptionId" defaultValue="${subscriptionId}"/>
			<giui:fieldText property="wxPagePath" caption="文件夹"/>
			<giui:fieldText property="wxPageName" caption="页面名称"/>
			<giui:fieldText property="wxPageCaption" caption="页面描述"/>
			<giui:fieldText property="wxPageType" caption="页面类型"/>
			<giui:fieldText property="wxPageParams" caption="页面参数"/>
		</giui:fieldLayout>
	</giui:form>
	
	<div id="muipageditor_main" class="muipageditor">
		<giui:accordion height="567" styleClass="col-sm-3">
			<giui:panel caption="微页面" column="12">
				<giui:tree id="tree_pages" rootText="微网站页面" iteratorSrc="/wxPageManager/getPagesTree.json?subscriptionId=${subscriptionId}" xmenu="xmenu_pages">
					
				</giui:tree>
			</giui:panel>
			<giui:panel caption="微页面组件" column="12">
				<div id="tree_widgets"></div>
			</giui:panel>
			<giui:panel caption="数据接口" column="12">
				<div id="tree_interfaces"></div>
			</giui:panel>
		</giui:accordion>
		
		<div class="col-sm-3 youi-phone">
			<div class="youi-phone-page youi-shell-content" >
				<div class="phone-page-content widget-container">
					<div id="widget_0" class="mui-widget widget-container col-xs-12" data-widget-name="panel"></div>
					<div class="mui-widget widget-fieldLayout col-xs-12 selected" id="widget_0" data-widget-name="fieldLayout"><div class="widget-container field-container col-xs-12"><span class="mui-field-label"></span><span class="mui-field-input mui-widget form-control" id="widget_1" data-widget-name="fieldText"></span></div><div class="widget-container field-container col-xs-12"></div></div>
				</div>
				<div class="muipageditor-propertyForm"></div>
			</div>
		</div>
		
		<giui:panel column="6" caption="页面信息" height="567">
			<giui:form id="form_page" panel="false" caption="页面信息" submit="保存页面" reset="NOT">
				<giui:fieldLayout columns="1">
					<giui:fieldLabel property="pageTpe" caption="页面类型"/>
					<giui:fieldLabel property="wxPagePath" caption="页面路径"/>
					<giui:fieldText property="wxPageName" caption="页面名"/>
					<giui:fieldText property="wxPageCaption" caption="页面描述"/>
					<giui:fieldText property="wxPageDataSrc" caption="页面数据"/>
					<giui:fieldText property="wxPageParam" caption="页面参数"/>
				</giui:fieldLayout>
				
				<giui:button name="preview" caption="预览页面"></giui:button>
			</giui:form>
		</giui:panel>
		
		<giui:form dataType="html" dialog="true" id="from_page_preview" caption="页面预览" action="/wxapp/design/preview/page.html">
			<giui:fieldLayout>
				<giui:fieldText caption="pageXml" property="pageXml"></giui:fieldText>
			</giui:fieldLayout>
		</giui:form>
		
		<ul id="xmenu_page_tree" class="youi-xmenu">
		
		</ul>
	</div>
	
	<youi:func name="init">

		$('#xmenu_page_tree').xmenu({
			menus:[{name:'add',caption:'新增页面',groups:'treeNode'},{name:'edit',caption:'修改{0}',groups:'page,tabpage'}],
			group:'treeNode'
		});
		//
		$('#tree_interfaces').tree({
			rootText:'应用数据接口',
			children:[
				{
					'id':'pubInterfaces',text:'公共接口',
					children:[
						{'id':'getProvinces',text:'获取省份列表',group:'article'},
						{'id':'getCitesByProvince',text:'获取省份城市列表',group:'article'},
						{'id':'getSexs',text:'获取性别列表',group:'article'}
					]
				},
				{
					'id':'vtui',
					text:'微推广',
					children:[
						{'id':'getPagerWxCloumns',text:'分页获取文章栏目',group:'article'},
						{'id':'getPagerWxArticles',text:'分页获取文章列表',group:'article'},
						{'id':'getRecommendWxArticles',text:'获取推荐文章列表',group:'article'}
					]

				},{'id':'vshop',text:'微商城'}]
		});

		//
		$('#tree_widgets').tree({
			rootText:'微页面组件',
			hintTooltips:true,
			dragable:true,
			dragStyles:'widget',
			dropStyles:'widget-container',
			children:[
				{id:'layout',text:'基本元素',children:[
					{id:'panel','text':'panel',tooltips:'面板',group:'widget',icon:'credit-card'},
					{id:'text','text':'text',tooltips:'文本',group:'widget',icon:'credit-card'},
					{id:'img','text':'img',tooltips:'图片',group:'widget',icon:'credit-card'},
					{id:'link','text':'link',tooltips:'链接',group:'widget',icon:'credit-card'},
					{id:'button','text':'button',tooltips:'按钮',group:'widget',icon:'stop'},
					{id:'hr','text':'hr',tooltips:'横线',group:'widget',icon:'credit-card'},
					{id:'richtext','text':'richtext',tooltips:'富文本',group:'widget ueditor',icon:'credit-card'}
				]},
				{id:'common',text:'小部件',children:[
					{id:'gridList','text':'gridList',tooltips:'列表',group:'widget',icon:'list'},
					{id:'tabbar','text':'tabbar',tooltips:'底部导航',group:'widget',icon:'book'},
					{id:'tabs','text':'tabs',tooltips:'标签页',group:'widget',icon:'book'},
					{id:'form','text':'form',tooltips:'表单',group:'widget',icon:'columns'},
					{id:'dialog','text':'dialog',tooltips:'弹出框',group:'widget',icon:'credit-card'},
					{id:'article','text':'article',tooltips:'文章',group:'widget',icon:'edit'}
				]},
				{id:'field',text:'表单组件',children:[
					{id:'fieldLayout','text':'fieldLayout',tooltips:'表单布局',group:'widget',icon:'list'},
					{id:'fieldText','text':'fieldText',tooltips:'文本框',group:'widget field',icon:'list'},
					{id:'fieldSelect','text':'fieldSelect',tooltips:'下拉选择',group:'widget field',icon:'chevron-down'},
					{id:'fieldLabel','text':'fieldLabel',tooltips:'显示框',group:'widget field',icon:'eye-open'},
					{id:'fieldCascadeSelect','text':'fieldCascadeSelect',tooltips:'级联选择',group:'widget field',icon:'chevron-down'},
					{id:'fieldPassword','text':'fieldPassword',tooltips:'密码框',group:'widget field',icon:'key'}
				]}
			]
		});

		$('#muipageditor_main').muipageditor();

	</youi:func>
	
	<youi:func name="func_form_preview">
		var xml = $('#muipageditor_main').muipageditor('getXml');
		$elem('from_page_preview',pageId).form('fillRecord',{pageXml:xml}).form('submit');
		
	</youi:func>
	
	
	<!-- 树节点弹出菜单动作:新增微网页-->
	<youi:func name="tree_pages_xmenu_addPage" params="refTreeNodeElement">
		
		var folders = [];
		var pageFolderPath;

		if(refTreeNodeElement.hasClass('folder')){
			pageFolderPath = refTreeNodeElement.attr('id');
		}else if(refTreeNodeElement.hasClass('page')||refTreeNodeElement.hasClass('tabpage')){
			pageFolderPath = refTreeNodeElement.parents('li.folder:first').attr('id');
		}

		pageFolderPath = pageFolderPath||'/';

		$elem('form_addWxPage',pageId).form('fillRecord',{wxPagePath:pageFolderPath}).dialog('open');
	</youi:func>
	
	
	<!-- 树节点弹出菜单动作:删除微网页-->
	<youi:func name="tree_pages_xmenu_removePage" params="refTreeNodeElement" urls="removeWxPage">
			//
		var confirmMessage = '确认删除微网页'+refTreeNodeElement.find('>span>a').text()+'?';
		var removeSrc = funcUrls.removeWxPage;
		$(this).tree('ajaxRemoveNode',refTreeNodeElement,removeSrc,'wxPageId',refTreeNodeElement.data('code'),confirmMessage);
	</youi:func>
	
	<!-- 树节点弹出菜单动作:删除文件夹-->
	<youi:func name="tree_pages_xmenu_removeFolder" params="refTreeNodeElement">
			//
		if(refTreeNodeElement.find('li.treeNode:first').length>0){
			alert('文件夹下还有文件，请先删除文件.');
		}
	</youi:func>
	
	
	<!-- 微页面新增后成功回调函数 -->
	<youi:func name="form_addWxPage_afterSubmit" params="results">
		if(results.record){
			var paths = results.record.wxPagePath.split('/'),
				pageTreeElem = $elem('tree_pages',pageId),
				rootNode = pageTreeElem.find('li.treeNode:first');
			
			var parentNode = rootNode;
			for(var i=1;i<paths.length-1;i++){
				var findPath = paths.slice(0,i+1).join('/')+'/';
				
				var pathNode = pageTreeElem.find('li.treeNode[id="'+findPath+'"]');
				if(pathNode.length===0){
					pathNode = pageTreeElem.tree('addNode',parentNode,findPath,paths[i],{},{group:'folder'});
				}

				parentNode = pathNode;
			}
			//添加页面
			pageTreeElem.tree('addNode',parentNode,(results.record.wxPagePath+results.record.wxPageName+'.page/'),results.record.wxPageName,{code:results.record.wxPageId},{group:results.record.wxPageType||'page'});
			
		}
	</youi:func>
	
	<youi:func name="tree_pages_select" params="treeNodeElement" urls="getWxPage">
		//
		
		if(treeNodeElement.hasClass('page')||treeNodeElement.hasClass('tabpage')){
			var wxPageId = treeNodeElement.data('code');//获取页面ID
			//加载表单数据
			console.log(wxPageId);
			$elem('form_page',pageId).form('find',funcUrls.getWxPage+'?wxPageId='+wxPageId);
			//加载页面内容
		}
	</youi:func>
</giui:page>