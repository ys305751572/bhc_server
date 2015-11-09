<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<%@ include file="/WEB-INF/views/scripts.jsp"%>
	<script type="text/javascript" src="${contextPath}/resources/js/bootstrap-validation.js"  charset="UTF-8"></script>
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
	function checkDevice(){
		var deviceSerial = $("#deviceSerial").val();
		if(deviceSerial){
			$("#promptMsg").html("正在检索设备......");
			$.post("${contextPath}/management/aoluser/checkDevice",{"deviceSerial":deviceSerial},function(result){
				if(result.success){
					var deviceData = eval("(" + result.msg + ")");
					$("#deviceSerial").attr("disabled",true);
					$("#promptMsg").html("检索到序列号对应的设备，设备信息如下...");
					$("#btnSearch").hide();
					$("#btnAdd").show();
					$("#deviceInfo").show();

					$("#deviceId").val(deviceData.deviceId);
					$("#_deviceSerial").html(deviceData.deviceSerial);
					$("#_deviceType").html(deviceData.deviceType);
					$("#_bak1").html(deviceData.bak1);
					$("#_deviceProYearMonth").html(deviceData.deviceProYear+"年"+deviceData.deviceProMonth+"月");
					$("#_organiseName").html(deviceData.organiseName);
				} else {
					$("#promptMsg").html("没有检索到序列号对应的设备！请确认序列号输入正确！");
				}
			});
		} else {
			jAlert("请输入正确的序列号！", "提示");
		}
	}

	function addDevice(){
		var deviceId = $("#deviceId").val();
		var userId = $("#userId").val();
		$("#promptMsg").html("正在添加设备......");
		$.post("${contextPath}/management/aoluser/addDevice",{"deviceId":deviceId, "userId":userId},function(result){
			if(result.success){
				$("#btnAdd").attr("disabled",true);
				$("#promptMsg").html(result.msg);
				jAlert(result.msg, "提示");
			} else {
				$("#promptMsg").html(result.msg);
				jAlert(result.msg, "提示");
			}
		});
	}
	
	</script>
	
</head>
<body>		
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row-fluid z-ulnone">
			<div class="box span12">
				<div class="box-header well z-h2">
					<h2><i class="icon-hdd"></i> 添加设备</h2>
					
					<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
						<jsp:param name="url" value="${contextPath}/management/aoluser/userslist"/>
					</jsp:include>
				</div>
				<div class="box-content">
					<div style="float: left; width: 100%; padding-top: 10px;">
						<form class="form-horizontal" method="post" id="restarteProForm" action="" enctype="multipart/form-data">
						<div class="control-group">
							<label class="control-label" for="deviceSerial">请输入设备序列号：</label>
							<div class="controls">
								<input id="userId" name="userId" type="hidden" value="${aoluser.userId}" />
								<input id="deviceId" name="deviceId" type="hidden" value="" />
								<input id="deviceSerial" name="deviceSerial" type="text" placeholder="请输入设备序列号" value="" class="input-large" style="width:300px;" required="required" check-type="required" required-message="请输入设备序列号" />
								<button id="btnSearch" name="btnSearch" type="button" onclick="checkDevice();" class="btn btn-primary" style="display: ">检索设备</button>
								<button id="btnAdd" name="btnAdd" type="button" onclick="addDevice();" class="btn btn-primary" style="display: none;">添加设备</button>
								<span id="promptMsg"></span>
							</div>
						</div>
						</form>
					</div>
					<div style="float: left; width: 100%; padding-top: 10px; margin-bottom: 10px; border: 1px solid #c6d4aa;">
						<div style="float: left; width: 40%;">
							<dl style="margin-top: 0px;">
							   <dt>用户基本信息</dt>
							   <dd>姓&nbsp;名：&nbsp;${aoluser.name}</dd>
							   <dd>性&nbsp;别：&nbsp;${aoluser.sex}</dd>
							   <dd>生&nbsp;日：&nbsp;${aoluser.birthday}</dd>
							   <dd>手&nbsp;机：&nbsp;${aoluser.mobile}</dd>
							   <dd>邮&nbsp;箱：&nbsp;${aoluser.email}</dd>
							</dl>
						</div>
						<div id="deviceInfo" style="float: left; width: 60%; display: none;">
							<dl style="margin-top: 0px;">
							   <dt>设备基本信息</dt>
							   <dd>设备序列号：&nbsp;<span id="_deviceSerial"></span> </dd>
							   <dd>功&nbsp;能&nbsp;类&nbsp;型&nbsp;：&nbsp;<span id="_deviceType"></span> </dd>
							   <dd>设&nbsp;备&nbsp;类&nbsp;型&nbsp;：&nbsp;<span id="_bak1"></span> </dd>
							   <dd>生&nbsp;产&nbsp;年&nbsp;月&nbsp;：&nbsp;<span id="_deviceProYearMonth"></span> </dd>
							   <dd>所属代理商：&nbsp;<span id="_organiseName"></span> </dd>
							</dl>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>


</body>
</html>		
