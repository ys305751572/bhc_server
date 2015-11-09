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
	<script type="text/javascript">
	function modifyOwnUser(){
		document.getElementById('name').readOnly = false;
		document.getElementById('sex').readOnly = false;
		document.getElementById('birthday').readOnly = false;
		document.getElementById('height').readOnly = false;
		document.getElementById('weight').readOnly = false;
		document.getElementById('mobile').readOnly = false;
		document.getElementById('email').readOnly = false;
		
	}
	function saveOwnUser(){
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
		<div class="row-fluid z-ulnone">
			<div class="box span12">
			<form id="savefrom" action="${contextPath}/management/aoluser/saveOwnUser" method="POST">
				<div class="box-header well z-h2">
					<h2><i class="icon-user"></i> 用户信息</h2>
					<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
						<jsp:param name="url" value="${contextPath}/management/aoluser/userslist"/>
					</jsp:include>
				</div>
				
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:modifyOwnUser();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-pencil"></i>修改</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:saveOwnUser();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-edit"></i>保存</a></li>
				</div>
				<!-- 操作按钮end -->	
				<div class="box-content">
					<dl style="margin-top: 0px;">
					   <dt>基本信息</dt>
					   <dd>姓&nbsp;名：&nbsp;<input type="text" id='name' name='name' value="${aoluser.name}" readonly="readonly"/></dd>
					   <dd>性&nbsp;别：&nbsp;
					   		<select id="sex" name="sex"  style="width: 120px;" readonly="readonly">
										<option value="" <c:if test="${aoluser.sex==''}">selected="selected" </c:if>>未填写</option>
										<option <c:if test="${aoluser.sex=='男'}">selected="selected" </c:if> value="男">男</option>
										<option <c:if test="${aoluser.sex=='女'}">selected="selected" </c:if> value="女">女</option>
									</select>
					   </dd>
					   <dd>生&nbsp;日：&nbsp;<input type="text" id='birthday'  name='birthday' value="${aoluser.birthday}" readonly="readonly"/></dd>
					   <dd>身&nbsp;高：&nbsp;<input type="text" id='height' name='height' value="${aoluser.height}" readonly="readonly"/></dd>
					   <dd>体&nbsp;重：&nbsp;<input type="text" id='weight' name='weight'  value="${aoluser.weight}" readonly="readonly"/></dd>
					   
					   <dt style="padding: 24px 0 4px 0;">联系方式</dt>
					   <dd>手&nbsp;机：&nbsp;<input type="text" id='mobile'  name='mobile'  value="${aoluser.mobile}" readonly="readonly"/></dd>
					   <dd>邮&nbsp;箱：&nbsp;<input type="text" id='email'  name='email' value="${aoluser.email}" readonly="readonly"/></dd>
					</dl>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>


</body>
</html>		
