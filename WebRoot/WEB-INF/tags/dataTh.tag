<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ attribute name="rowspan" type="java.lang.String"%>
<%@ attribute name="colspan" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String"%>
<%@ attribute name="options" type="java.lang.String"%>

<th rowspan="${rowspan}" colspan="${colspan}" style="${style}"
	data-options="${options}">
	<jsp:doBody />
</th>