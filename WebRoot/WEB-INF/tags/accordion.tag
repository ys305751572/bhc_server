<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String" %>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="name" type="java.lang.String"%>


<div id="${id}" class="easyui-accordion" style="${style}" name="${name}"
	data-options="${options}">
	<jsp:doBody />
</div>