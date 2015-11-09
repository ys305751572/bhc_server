<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="href" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="onclick" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>


<a id="${id}" href="${href}" class="easyui-linkbutton" style="style"
	data-options="${options}" onclick="${onclick}"><jsp:doBody /></a>