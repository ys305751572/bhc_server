<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="content" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="title" type="java.lang.String"%>

<table id="${id}" class="easyui-treegrid" style="${style}"
	data-options="${options}" title="${title}">
	<jsp:doBody />
</table>