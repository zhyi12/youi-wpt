<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<giui:page >
	<youi:script src="/scripts/3.0/lib/twaver.js"/>
	<div id="twaver"></div>
	<script>
		
	</script>
	<youi:func name="init">
		console.log('-------');
		var network = new twaver.network.Network();
		var box = network.getElementBox();
		var node = new twaver.Node();
		node.setName("from");
		node.setLocation(100, 100);
		box.add(node);
		var node2 = new twaver.Node();
		node2.setName("to");
		node2.setLocation(300, 300);
		box.add(node2);
		var link = new twaver.Link(node, node2);
		link.setName("Hello TWaver");
		link.setToolTip("<b>Hello TWaver</b>");
		box.add(link);
		var networkDom = network.getView();
		networkDom.style.width = "100%";
		networkDom.style.height = "100%";

		$('#twaver').append(networkDom);
		
	</youi:func>
</giui:page>