<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="title" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>

<%@ attribute name="resizable" type="java.lang.Boolean"%>
<%@ attribute name="maximizable" type="java.lang.Boolean"%>
<%@ attribute name="minimizable" type="java.lang.Boolean"%>
<%@ attribute name="collapsible" type="java.lang.Boolean"%>



<div id="${id}" class="easyui-dialog" title="${title}" style="${style}"
	data-options="${options}" resizable="${resizable}"
	maximizable="${maximizable}" minimizable="${minimizable}"
	collapsible="${collapsible}">
	<jsp:doBody />
</div>
