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
<script src="${contextPath}/resources/js/urgentpersonInvesReview.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/aolIcons.css" />
<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<!-- The styles -->
<style type="text/css">
	body {
		padding-bottom: 10px;
		padding-top: 0px;
	}
	
	.sidebar-nav {
		padding: 9px 0;
	}
	
	#DataTables_Table_0_paginate a {
		margin-left: 5px;
	}
	.dataTables_scroll{
		float: left;
	}
	#DataTables_Table_0_wrapper .row-fluid {
		float: left;
	}
</style>
<script type="text/javascript">
	var msg = "${msg}";
	$(document).ready(function (){
		if(msg=="ok"){
			jAlert("保存成功！","提示");
		}
	});
		
	function modifyGd(){
		document.getElementById('urgentxm1').readOnly = false;
		document.getElementById('telephone1').readOnly = false;
		document.getElementById('address1').readOnly = false;
		document.getElementById('gx1').readOnly = false;
		
		document.getElementById('urgentxm2').readOnly = false;
		document.getElementById('telephone2').readOnly = false;
		document.getElementById('address2').readOnly = false;
		document.getElementById('gx2').readOnly = false;
		
		document.getElementById('urgentxm3').readOnly = false;
		document.getElementById('telephone3').readOnly = false;
		document.getElementById('address3').readOnly = false;
		document.getElementById('gx3').readOnly = false;
	}
	
	function saveGd(){
		document.getElementById('savefrom').submit();
	}
	
	function setMassage(index){
		var massage = ""
		if($("#telephone"+index).val()==""){
			jAlert("没有设置监护人");
			return;
		}
		
		jPrompt('发送消息：', massage, '消息内容', function(massagevalues){ 
			if(massagevalues){
				if(massagevalues.length>200){
					jAlert("输入信息太多");
					return;
				}
				$.post("${contextPath}/management/urgentperson/setMessage",{"message":massagevalues, "telephone":$("#telephone"+index).val()},function(result){
 								
 								jAlert(result.msg,"提示");
						});
			} 
		});
	}
</script>
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
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid">
		<input type="hidden" id = "pageType" value="guardian" /><!-- 因为调用的是一个JS方法，在此设定区分参数，用来执行不同的方法 -->
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">
				<div class="box-header well z-h2">
					<h2><i class="icon-user"></i> 监护人设置</h2>
					<c:if test="${userType=='99' || userType=='1'}">
						<div class="box-icon">
							<a href="${contextPath}/management/urgentperson/orgguardianlist" class="btn btn-close btn-round"  title="返回"><i class="icon-arrow-left"></i></a>
						</div>
					</c:if>
				</div>
			
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:modifyGd();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;修改监护人</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:saveGd();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-11" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;保存监护人</a></li>
				</div>
				
				<!-- 监护人设置start -->
				<div class="box-content">
					<form id="savefrom" action="${contextPath}/management/urgentperson/saveGuardian" method="POST">
					<input type="hidden" id = "userid" name = "userid" value="${userid}"/>
					<table>
						<tr>
							<td>
								<dl style="margin-top: 0px;">
									<dt>监护人1<input type="hidden" id = "urgentperson_id1" name = "urgentperson_id1" value="${urgentPerson1.urgentperson_id}"/></dt>
								   	<dd>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;<input type="text" id='urgentxm1' name='urgentxm1' value="${urgentPerson1.urgentxm}" readonly="readonly"/></dd>
								  	<dd>关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：&nbsp;<input type="text" id='gx1' name='gx1' value="${urgentPerson1.gx}" readonly="readonly"/></dd>
								  	<dd>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：&nbsp;<input type="text" id='telephone1'  name='telephone1' value="${urgentPerson1.telephone}" readonly="readonly"/></dd>
								  	<dd>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;<input type="text" id='address1'  name='address1' value="${urgentPerson1.address}"  readonly="readonly"/></dd>
								</dl>
								<dl style="text-align: center;">
									<a class="button glow button-rounded button-tiny" style="width: 100px;cursor:pointer;" onclick="setMassage(1)"><i class="icon-reload"></i>发送消息</a>
								</dl>
							</td>
							<td>
								<dl style="margin-top: 0px;">
									<dt>监护人2<input type="hidden" id = "urgentperson_id2" name = "urgentperson_id2" value="${urgentPerson2.urgentperson_id}"/></dt>
								   	<dd>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;<input type="text" id='urgentxm2' name='urgentxm2' value="${urgentPerson2.urgentxm}" readonly="readonly"/></dd>
								   	<dd>关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：&nbsp;<input type="text" id='gx2' name='gx2' value="${urgentPerson2.gx}" readonly="readonly"/></dd>
								   	<dd>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：&nbsp;<input type="text" id='telephone2'  name='telephone2' value="${urgentPerson2.telephone}" readonly="readonly"/></dd>
								   	<dd>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;<input type="text" id='address2'  name='address2' value="${urgentPerson2.address}"  readonly="readonly"/></dd>
								</dl>
								<dl style="text-align: center;">
									<a class="button glow button-rounded button-tiny" style="width: 100px;cursor:pointer;" onclick="setMassage(2)"><i class="icon-reload"></i>发送消息</a>
								</dl>
							</td>
							<td>
								<dl style="margin-top: 0px;">
									<dt>监护人3<input type="hidden" id = "urgentperson_id3" name = "urgentperson_id3" value="${urgentPerson3.urgentperson_id}"/></dt>
								   	<dd>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;<input type="text" id='urgentxm3' name='urgentxm3' value="${urgentPerson3.urgentxm}" readonly="readonly"/></dd>
								   	<dd>关&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：&nbsp;<input type="text" id='gx3' name='gx3' value="${urgentPerson3.gx}" readonly="readonly"/></dd>
								   	<dd>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：&nbsp;<input type="text" id='telephone3'  name='telephone3' value="${urgentPerson3.telephone}" readonly="readonly"/></dd>
								   	<dd>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;<input type="text" id='address3'  name='address3' value="${urgentPerson3.address}"  readonly="readonly"/></dd>
								</dl>
								<dl style="text-align: center;">
									<a class="button glow button-rounded button-tiny" style="width: 100px;cursor:pointer;" onclick="setMassage(3)"><i class="icon-reload"></i>发送消息</a>
								</dl>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<!-- 监护人设置end -->
				
			</div>
		</div>
		<!--detail end-->
		
	</div>
	</div>
</body>
</html>

