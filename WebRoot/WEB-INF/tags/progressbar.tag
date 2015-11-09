<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="name" type="java.lang.String"%>

<div id="${id}" class="easyui-progressbar" data-options="${options}" name="${name}"
	style="${style}"></div>
