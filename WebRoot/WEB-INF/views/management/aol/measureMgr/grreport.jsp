<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>爱奥乐健康云平台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@ include file="/WEB-INF/views/scripts.jsp"%>
		<!-- xxx tools -->
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
		<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
  </head>
  <script src="${contextPath}/resources/FusionCharts/FusionCharts.js"></script>
  <script type="text/javascript">
				$(document).ready(function(){
					var xmldate = window.parent.document.getElementById('strxml').value;
					var chart = new FusionCharts("${contextPath}/resources/FusionCharts/MSLine.swf", "ChartId", "970", "430");
	       			chart.setDataXML(xmldate);
	       			chart.render("chartdiv");
				});
  </script>
    <style>
  .divbox{
  position:absolute;
  top:1%;
  right: 1%
  }
  </style>
  <body>
   <div id="chartdiv" class="divbox"></div>
  </body>
</html>
