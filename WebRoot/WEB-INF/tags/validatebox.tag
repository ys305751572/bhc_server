<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="name" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="value" type="java.lang.String"%>
<%@ attribute name="disabled" type="java.lang.String"%>
<%@ attribute name="title" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input id="${id}" name="${name}" class="easyui-validatebox" title="${title}" data-options="${options}" value="${value}" <c:if test="${disabled != null}">readOnly="${disabled}"</c:if> style="${style}"/>
