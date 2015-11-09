<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="firstDay" type="java.lang.Integer"%>
<%@ attribute name="width" type="java.lang.Integer"%>
<%@ attribute name="height" type="java.lang.Integer"%>
<%@ attribute name="fit" type="java.lang.Boolean"%>
<%@ attribute name="border" type="java.lang.Boolean"%>
<%@ attribute name="year" type="java.lang.Integer"%>
<%@ attribute name="month" type="java.lang.Integer"%>
<%@ attribute name="current" type="java.util.Date"%>

<div id="${id}" class="easyui-calendar" style="${style}"
	data-options="${options}" width="${width}" height="${height}"
	fit="${fit}" border="${border}" firstDay="${firstDay}" year="${year}"
	month="${month}" current="${current}">
</div>
