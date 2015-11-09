<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>

<table id="${id}" class="easyui-propertygrid" style="${style}"
	data-options="${options}">
	<jsp:doBody />
</table>
