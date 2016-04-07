<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<giui:subpage type="dialog"
 formAction="/local/modifyPassword.json" src="page/common/giuiModifyPassword.html" subpageId="modifyPassword" caption="修改密码"></giui:subpage>
<div class="col-sm-12 youi-page-header">
	
	<div class="pull-left sys-title"><h3>
	<span class="youi-icon icon-trello"></span>
	
	<a data-menu-clear class="page-link" href='<youi:url value="/member/${loginType}/index.html#p:page/${loginType}/welcome.html"/>'>
		<youi:if test="${loginType=='vtui'}">
			微推
		</youi:if>
		<youi:if test="${loginType=='vchuang'}">
			微科研
		</youi:if>
	</a>
	</h3></div>
	<shiro:authenticated>
	<ul id="main-menu" class="nav navbar-nav navbar-right">
		<li>
			<a href="#">搜索</a>
		</li>
		<li>
			<a class="page-link" target="_balnk" href='<youi:url value="/"/>'>首页</a>
		</li>
		<li>
			<a href="#">帮助</a>
		</li>
		<li>
			<a href="#">消息</a>
		</li>
		<li class="dropdown hidden-xs">
			<a href="#" title="" class="dropdown-toggle" data-toggle="dropdown"> 
				<span class="youi-icon icon-user padding-right-small">
					<shiro:principal></shiro:principal>
				</span>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu">
				<li><a href="#" data-command="appCommand" data-name="accountInfo">账户信息</a></li>
				<li><a href="javascript:$('#subpage_modifyPassword').subpage('open');" class="page-link" data-command="appCommand" data-name="modifyPassword">修改密码</a></li>
			</ul>
		</li>
		
		<li>
			<a class="page-link" tabindex="-1" href="<youi:url value='/member/${loginType}/logout.html'/>"  title="退出系统" >
				<span class="youi-icon icon-off padding-right-small">退出</span>
			</a>
		</li>
	</ul>
	</shiro:authenticated>
</div>
