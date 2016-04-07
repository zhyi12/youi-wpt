<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<script src="<%=request.getContextPath()%>/scripts/3.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/scripts/3.0/lib/bootstrap.js" type="text/javascript"></script>
<youi:style href="/styles/3.0/bootstrap.css" />
<title>微窗登录</title>

</head>
<shiro:authenticated>
	<!-- authenticated -->
	<input id="hasLogin" type="hidden" value="true" />
	<jsp:forward page="index.html"></jsp:forward>
</shiro:authenticated>

<body>
	<div class="container">
    	<div class="row">
        
		    <div class="row">
		        <div class="col-md-12 center login-header">
		        	<br/>
		            <div align="center"><h2>微窗</h2></div>
		        </div>
		        <!--/span-->
		    </div><!--/row-->
		
		    <div class="row">
		        <div class="well col-md-6 center login-box col-md-offset-3">
		            <div class="alert alert-info">
		                	请输入您的用户名和密码登录
		            </div>
		            <form class="form-horizontal" action="login.html" method="post">
		            	<input type="hidden" name="loginType" value="${loginType}"/>
		                <fieldset>
		                    <div class="input-group input-group-lg">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
		                        <input type="text" name="username" class="form-control" value="${username}" placeholder="Username">
		                    </div>
		                    <div class="clearfix"></div><br>
		
		                    <div class="input-group input-group-lg">
		                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
		                        <input type="password" name="password" class="form-control" value="123456" placeholder="Password">
		                    </div>
		                    <br/>
		                    <div class="clearfix">
		                    </div>
		                    <div class="login-error pull-right">
		                    	<youi:out value="${error}"/>
		                    </div>
		                    <p class="center col-md-5">
		                        <button type="submit" class="btn btn-primary">登录</button>
		                       	 没有账户，请<a href="<youi:url value='/register.html'></youi:url>">注册</a>
		                    </p>
		                </fieldset>
		            </form>
		        </div>
		    </div><!--/row-->
		</div><!--/fluid-row-->
	</div>
</body>
</html>