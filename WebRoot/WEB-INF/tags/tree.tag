<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="title" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="content" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>

<ul id="${id}" style="${style}" class="easyui-tree" data-options="${options}"></ul>