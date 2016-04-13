<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div align="center">
	<img style="border-radius:40px;" width="80" height="80" src="${account.headimgurl}"/>
	
	<div id="me_page_list" class="list-container">
		
	</div>
	
	<script type="text/javascript">
		$(function(){
			$('#me_page_list').gridList({
				rowStyle:'col-xs-4 list-item',
				template:'<a class="page-link" href="{href}">{caption}</a>',
				records:[
					{'caption':'我的团队','href':'settings.main.html'},
					{'caption':'我的二维码','href':'settings.main.html'},
					{'caption':'提现记录','href':'settings.main.html'},
					{'caption':'关注捧场','href':'settings.main.html'},
					{'caption':'新手指南','href':'settings.main.html'},
					{'caption':'设置','href':'settings.main.html'}
				]
			});
		});
	</script>
</div>

