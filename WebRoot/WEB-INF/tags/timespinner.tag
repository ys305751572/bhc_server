<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="separator" type="java.lang.String"%>
<%@ attribute name="showSeconds" type="java.lang.Boolean"%>
<%@ attribute name="highlight" type="java.lang.Integer"%>
<%@ attribute name="required" type="java.lang.String"%>

<input id="${id}" class="easyui-timespinner" style="${style}"
	required="${required}" data-options="${options}"  />
