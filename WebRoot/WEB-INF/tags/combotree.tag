<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="name" type="java.lang.String"%>
<%@ attribute name="multiple" type="java.lang.String"%>

<select id="${id}" ${multiple} class="easyui-combotree" name="${name}" style="${style}" data-options="${options}"></select>
