<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
		
		<title>科研指南针</title>
		
		<youi:script src="/scripts/3.0/lib/jquery.js"/>
		<youi:script src="/scripts/3.0/lib/jquery-ui.min.js"/>
		
		<youi:script src="/scripts/3.0/mui/youi.core.js"/>
		<youi:script src="/scripts/3.0/mui/youi.app.js"/>
		<youi:script src="/scripts/3.0/mui/youi.tabbar.js"/>
		<youi:script src="/scripts/3.0/mui/youi.gridlist.js"/>
		
		<youi:style href="/styles/3.0/giui/giui.awesome.css"/>
		<youi:style href="/styles/3.0/bootstrap.css"/>
		<youi:style href="/styles/3.0/mui/mui.core.css"/>
	</head>
	<body>
		<div id="youi-phone-page" class="youi-phone-page">
			<div class="phone-page-content tab-content page-container">
				<div role="tabpanel" class="tab-pane" data-id="home" id="home"></div>
			    <div role="tabpanel" class="tab-pane" data-id="finder" id="finder"></div>
			    <div role="tabpanel" class="tab-pane" data-id="me" id="me"></div>
			    <div role="tabpanel" class="tab-pane" id="messages" data-id="messages"></div>
			</div>
			<ul id="tabbar" class="nav nav-tabs phone-page-tabbar" role="tablist" data-hash>
			    <li role="presentation" class="tabbar-item" data-src="home.html">
			    	<a data-id="home" href="#home" aria-controls="home" role="tab" data-toggle="tab">
			    		<span class="item-icon icon-home"></span>
						<div class="item-text">精选</div>
			    	</a>
			    </li>
			    <li role="presentation" class="tabbar-item"  data-src="finder.html">
			    	<a data-id="finder" href="#finder" aria-controls="profile" role="tab" data-toggle="tab">
			    		<span class="item-icon icon-eye-open"></span>
						<div class="item-text">发现</div>
			    	</a>
			    </li>
			    <li role="presentation" class="tabbar-item" data-src="me.html">
			    	<a data-id="me" href="#me" aria-controls="messages" role="tab" data-toggle="tab">
			    		<span class="item-icon icon-user"></span>
						<div class="item-text">我</div>
			    	</a>
			    </li>
			    <li role="presentation" class="tabbar-item" data-src="messages.html">
			    	<a href="#messages" data-id="messages" aria-controls="settings" role="tab" data-toggle="tab">
			    		<span class="item-icon icon-envelope"></span>
						<div class="item-file">消息</div>
			    	</a>
			    </li>
			</ul>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$.youi.serverConfig.contextPath = '<%=request.getContextPath()%>/';
				$('#youi-phone-page').app();
				$('#tabbar').tabbar();
			});
		</script>
	</body>
</html>

