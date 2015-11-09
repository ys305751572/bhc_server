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
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/aolIcons.css" />
	<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		var msg = "${msg}";
		$(document).ready(function (){
			if(msg=="ok"){
				jAlert("保存成功！","提示");
			}
		});
		
		function modifyUp(){
			document.getElementById('urgentxm').readOnly = false;
			document.getElementById('telephone').readOnly = false;
			document.getElementById('address').readOnly = false;
			document.getElementById('sffs').readOnly = false;
		}
		
		function saveUp(){
			document.getElementById('savefrom').submit();
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
		<input type="hidden" id = "pageType" value="userurgent" /><!-- 因为调用的是一个JS方法，在此设定区分参数，用来执行不同的方法 -->
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<div class="box-header well z-h2">
					<h2><i class="icon-user"></i> 紧急联系人设置</h2>
					<c:if test="${userType=='99' || userType=='1'}">
						<div class="box-icon">
							<a href="${contextPath}/management/urgentperson/orguserurgentlist" class="btn btn-close btn-round"  title="返回"><i class="icon-arrow-left"></i></a>
						</div>
					</c:if>
				</div>
				
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:modifyUp();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;修改紧急联系人</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:saveUp();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-11" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;保存紧急联系人</a></li>
				</div>
				
				<div class="box-content">
				<form id="savefrom" action="${contextPath}/management/urgentperson/saveUrgentPerson" method="POST">
					<dl style="margin-top: 0px;">
					   <dt>紧急联系人<input type="hidden" id="userid" name="userid" value="${urgentPersontemp.user_id}"/><input type="hidden" id="urgentperson_id" name="urgentperson_id" value="${urgentPersontemp.urgentperson_id}"/></dt>
					   <dd>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：&nbsp;<input type="text" id='urgentxm' name='urgentxm' value="${urgentPersontemp.urgentxm}" readonly="readonly"/></dd>
					   <dd>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：&nbsp;<input type="text" id='telephone'  name='telephone' value="${urgentPersontemp.telephone}" readonly="readonly"/></dd>
					   <dd>通讯地址：&nbsp;<input type="text" id='address' name='address' value="${urgentPersontemp.address}" style="width:500px;" readonly="readonly"/></dd>
					   <dd>自动发送：&nbsp;<input type="checkbox" id='sffs' name='sffs' <c:if test="${urgentPersontemp.sffs=='1'}">checked="checked"</c:if> readonly="readonly"/></dd>
					</dl>
				</form>
				</div> 
			</div> 
		</div> 
	</div>
	</div>
	</body>
</html>

