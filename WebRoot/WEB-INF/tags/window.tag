<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="title" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="collapsible" type="java.lang.Boolean"%>
<%@ attribute name="minimizable" type="java.lang.Boolean"%>
<%@ attribute name="maximizable" type="java.lang.Boolean"%>
<%@ attribute name="closable" type="java.lang.Boolean"%>
<%@ attribute name="closed" type="java.lang.Boolean"%>
<%@ attribute name="draggable" type="java.lang.Boolean"%>
<%@ attribute name="resizable" type="java.lang.Boolean"%>
<%@ attribute name="shadow" type="java.lang.Boolean"%>
<%@ attribute name="inline" type="java.lang.Boolean"%>
<%@ attribute name="modal" type="java.lang.Boolean"%>
<%@ attribute name="zIndex" type="java.lang.Integer"%>

<div id="${id}" class="easyui-window" title="${title}"
	data-options="${options}" style="${style}" collapsible="${collapsible}"
	minimizable="${minimizable}" maximizable="${maximizable}"
	closable="${closable}" closed="${closed}" zIndex="${zIndex}"
	draggable="${draggable}" resizable="${resizable}" shadow="${shadow}"
	inline="${inline}" modal="${modal}">
	<jsp:doBody />
</div>
