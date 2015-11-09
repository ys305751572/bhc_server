<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<%@ include file="/WEB-INF/views/scripts.jsp"%>
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
	<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<!-- The styles -->
	<style type="text/css">
		body {
			padding-bottom: 40px;
			font: 12px/1.6 "微软雅黑","宋体";
		}
		.sidebar-nav {
			padding: 9px 0;
		}
		dt {
			font-weight: normal;
			font-size: 18px;
			padding: 4px 0;
			text-indent: 20px;
			color: #000;
		}
		dd {
			font-size: 14px;
			padding: 5px 0;
			margin: 0 40px;
		}
	</style>
	<script type="text/javascript">
	$(document).ready(function(){
		if(${modelSize}==2){
			$("#moreModel").show();
		}
	});
	
	function searchDevice(deviceid,userid){
		window.location.href = "${contextPath}/management/aoluser/viewUserInfo?userId="+userid+"&deviceId="+deviceid;
	}
	
	</script>
</head>
<body>		
<div class="container-fluid" style="padding-right: 0px;padding-left: 0px;">
	<div class="row-fluid">
		<div id="moreModel" style="display:none"><span style="color:red">模糊查询出现多个设备，现只显示最新设备</span></div>
		<div class="row-fluid z-ulnone">
		  <c:if test="${isOk}">
			<div class="box span4">
				<div class="box-header well z-h2">
					<h2>设备基本信息</h2>
				</div>
				<div class="box-content" style="height: 300px;">
				  <c:if test="${!empty device}">
					<dl style="margin-top: 0px;">
					   <dd>功能类型：&nbsp;<c:if test="${device.deviceType == 1}">血压</c:if><c:if test="${device.deviceType == 2}">血糖</c:if><c:if test="${device.deviceType == 3}">体重</c:if><c:if test="${device.deviceType == 4}">运动</c:if></dd>
					   <dd>设备类型：&nbsp;<c:if test="${device.bak1 == 0}">普通设备</c:if><c:if test="${device.bak1 == 1}">GSM设备</c:if></dd>
					   <dd>识&nbsp;&nbsp;别&nbsp;&nbsp;码：&nbsp;${device.deviceSerial}</dd>
					   <dd>生产年份：&nbsp;${device.deviceProYear}</dd>
					   <dd>生产月份：&nbsp;${device.deviceProMonth}</dd>
					   <dd>程序版本：&nbsp;${device.version}</dd>
					</dl>
				  </c:if>
				</div>
			</div>
			
			<div class="box span4">
				<div class="box-header well z-h2">
					<h2>用户基本信息</h2>
				</div>
				<div class="box-content" style="height: auto; min-height: 300px">
				  <c:if test="${!empty aoluserList}">
				  <c:forEach var="aoluser" items="${aoluserList}" varStatus="status">
					<dl style="margin-top: 0px;">
					   <dt>关联用户${status.index + 1}：<a href="javascript:void(0);" onclick="searchDevice('${device.device_id}','${aoluser.userId}');">近期情况</a></dt>
					   <dd>姓&nbsp;名：&nbsp;${aoluser.name}</dd>
					   <dd>性&nbsp;别：&nbsp;${aoluser.sex}</dd>
					   <dd>生&nbsp;日：&nbsp;${aoluser.birthday}</dd>
					   <dd>身&nbsp;高：&nbsp;${aoluser.height}</dd>
					   <dd>体&nbsp;重：&nbsp;${aoluser.weight}</dd>
					   <dd>手&nbsp;机：&nbsp;${aoluser.mobile}</dd>
					   <dd>邮&nbsp;箱：&nbsp;${aoluser.email}</dd>
					</dl>
				  </c:forEach>
				  </c:if>
				  <c:if test="${empty aoluserList}">
				    <dl style="margin-top: 0px;">
					   <dd>产品尚未被人使用！</dd>
					</dl>
				  </c:if>
				</div>
			</div>
			
			<div class="box span4">
				<div class="box-header well z-h2">
					<h2>代理商基本信息</h2>
				</div>
				<div class="box-content" style="height: 300px;">
				  <c:if test="${!empty org}">
					<dl style="margin-top: 0px;">
					   <dd>公司名称：&nbsp;${org.organiseName}</dd>
					   <dd>公司简称：&nbsp;${org.organiseShortname}</dd>
					   <dd>公司电话：&nbsp;${org.telephone}</dd>
					   <dd>公司邮箱：&nbsp;${org.mailbox}</dd>
					   <dd>公司地址：&nbsp;${org.organiseAddress}</dd>
					   <dd>门面地址：&nbsp;${org.storeAddress}</dd>
					</dl>
				  </c:if>
				</div>
			</div>
		  </c:if>
		  <c:if test="${!isOk}">
		    <div class="box span12">
				<div class="box-content">
					<div style="width:600px;margin-left: 20px;">
						未能找到与机器识别码匹配的设备！
					</div>
				</div>
			</div>
		  </c:if>
		</div>
	</div>
</div>

<script type="text/javascript">
	parent.setIframeHeight(document.body.scrollHeight);
</script>

</body>
</html>
