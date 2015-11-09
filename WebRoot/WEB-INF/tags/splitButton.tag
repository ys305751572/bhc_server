<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="href" type="java.lang.String"%>
<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>
<%@ attribute name="onclick" type="java.lang.String"%>

<a href="${href}" id="${id}" class="easyui-splitbutton"
	data-options="${options}" onclick="${onclick}"> <jsp:doBody /> </a>