<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div id="sys_toggle_menu" class="page-menu-toggle">
	<span class="youi-icon icon-indent-left" title="关闭菜单"></span>
	<span class="youi-icon icon-indent-right" title="展开菜单"></span>
</div>


<div class="youi-menu v-menu">
	
	<div style="color:#000;background-color:#f9f9f9;margin:10px;border-radius:15px;padding:3px;8px;text-align:center;">
		${subscription.subscriptionCaption}(已认证)
	</div>
	
	<h1 id="700000" class="menu-bar-title">
		<div class="icon-text">
			<span class="span-text">基础功能</span>
		</div>
	</h1>
	<div class="menu-bar-content">
		<ul class="menu-content-ul">
			<li class="menu-item">
				<span class="tree-span treeNode menu-item group last">
					<span class="youi-icon icon-file"></span>
					<a class="tree-a page-link" href="member/vchuang/wxarticle/articles/${subscriptionId}.html">微信文章</a>
				</span>
			</li>
			<li class="menu-item">
				<span class="tree-span treeNode menu-item group last">
					<span class="youi-icon icon-file"></span>
					<a class="tree-a page-link" href="member/vchuang/menu/custom/${subscriptionId}.html">微信菜单</a>
				</span>
			</li>
			
			<li class="menu-item">
				<span class="tree-span treeNode menu-item group last">
					<span class="youi-icon icon-group"></span>
					<a class="tree-a page-link" href="member/vchuang/vtui/page/${subscriptionId}.html">微网站</a>
				</span>
			</li>
		</ul>
	</div>
	
	<h1 id="700100" class="menu-bar-title">
		<div class="icon-text">
			<span class="span-text">微信粉丝</span>
		</div>
	</h1>
	<div class="menu-bar-content">
		<ul class="menu-content-ul">
			<li class="menu-item">
				<span title="粉丝管理" class="tree-span treeNode menu-item group last">
					<span class="youi-icon icon-group"></span>
					<a class="tree-a page-link" href="member/vchuang/wxmember/wxMember/${subscriptionId}.html">粉丝管理</a>
				</span>
			</li>
		</ul>
	</div>
	
	
	<h1 id="700300" class="menu-bar-title">
		<div class="icon-text">
			<span class="span-text">微配置</span>
		</div>
	</h1>
	<div class="menu-bar-content">
		<ul class="menu-content-ul">
			<li class="menu-item">
				<span title="粉丝管理" class="tree-span treeNode menu-item group last">
					<span class="youi-icon icon-group"></span>
					<a class="tree-a page-link" href="member/vchuang/vtui/config/${subscriptionId}.html">微推基本配置</a>
				</span>
			</li>
		</ul>
	</div>
	
</div>

