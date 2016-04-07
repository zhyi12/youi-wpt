<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0"/>
		<title>个人设置</title>
		
		<youi:script src="/scripts/3.0/lib/jquery.js"/>
		<youi:script src="/scripts/3.0/lib/jquery-ui.min.js"/>
		<youi:script src="/scripts/3.0/lib/bootstrap.js"/>
		
		<youi:script src="/scripts/3.0/mui/youi.core.js"/>
		<youi:script src="/scripts/3.0/mui/youi.app.js"/>
		<youi:script src="/scripts/3.0/mui/youi.gridlist.js"/>
		
		<youi:style href="/styles/3.0/giui/giui.awesome.css"/>
		<youi:style href="/styles/3.0/bootstrap.css"/>
		
		<style type="text/css">
			body{
				overflow-x:hidden;
			}
			.list-box{
			    overflow: hidden;
			    border-radius: 5px;
			}
			
			.list-cell>a{
				padding: 10px 15px;
			    position: relative;
			    display: -webkit-box;
			    display: -webkit-flex;
			    display: -ms-flexbox;
			    display: flex;
			    -webkit-box-align: center;
			    -webkit-align-items: center;
			    -ms-flex-align: center;
			    align-items: center;
			}
			
			.list-cell>a:before {
			    content: " ";
			    position: absolute;
			    left: 0;
			    top: 0;
			    width: 100%;
			    height: 1px;
			    border-top: 1px solid #D9D9D9;
			    color: #D9D9D9;
			    -webkit-transform-origin: 0 0;
			    transform-origin: 0 0;
			    -webkit-transform: scaleY(0.5);
			    transform: scaleY(0.5);
			    left: 15px;
			}
			
			.list-cell:last-child{
				 border-bottom: 1px solid #D9D9D9;
			}
			
			.cell_primary{
				-webkit-box-flex: 1;
			    -webkit-flex: 1;
			    -ms-flex: 1;
			    flex: 1;
			}
			
			.cell_ft:after{
				content: " ";
			    display: inline-block;
			    -webkit-transform: rotate(45deg);
			    transform: rotate(45deg);
			    height: 6px;
			    width: 6px;
			    border-width: 2px 2px 0 0;
			    border-color: #C8C8CD;
			    border-style: solid;
			    position: relative;
			    top: -2px;
			    top: -1px;
			    margin-left: .3em;
			}
			
			.btn-area{
				padding: 10px 0;
			    width: 60%;
			    margin: 0 auto;
			    text-align: justify;
			    text-justify: distribute-all-lines;
			    font-size: 0;
			}
			
			.btn{
				width: 100%;
			}
			
		</style>
	</head>
	<body>
		<div id="setting_grid_list" class="list-box"></div>
		
		<div class="btn-area">
			<button class="list-cell btn btn-default">退出系统</button>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$('#setting_grid_list').gridList({
					rowStyle:'list-cell',
					template:'<a href="{href}"><div class="cell_primary">{caption}</div><div class="cell_ft"></div></a>',
					records:[
						{'caption':'绑定手机号码','href':'settings.bindPhone.html'},
						{'caption':'修改密码','href':'settings.bindPhone.html'},
						{'caption':'完善个人资料','href':'settings.bindPhone.html'},
						{'caption':'关于我们','href':'settings.bindPhone.html'},
						{'caption':'意见反馈','href':'settings.bindPhone.html'}
					]
				});
			});
		</script>
	</body>
</html>