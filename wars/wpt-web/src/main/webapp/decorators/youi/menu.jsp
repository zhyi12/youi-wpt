<%@ include file="/WEB-INF/pages/include.jsp"%>

<%@ page language="java" pageEncoding="UTF-8"%>
<script>
	$(function(){
		$('#system_v_menu').sysmenu({
			bindResize:true
		});
	});
</script>

<!-- 纵向系统菜单 -->
<div class="system-menu-search">
	<youi:fieldAutocomplete propValueProperty="value" styleClass="addon-search input-group" src="/local/searchmenu.json" property="menu" caption="搜索菜单"></youi:fieldAutocomplete>
</div>
<youi:sysVertMenu id="system_v_menu"></youi:sysVertMenu>
