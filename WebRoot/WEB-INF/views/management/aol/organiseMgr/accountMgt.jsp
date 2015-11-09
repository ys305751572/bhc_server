<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="/WEB-INF/views/scripts.jsp"%>
		
		<script type="text/javascript" src="${contextPath}/resources/js/bootstrap-validation.js"  charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		
			<!-- The styles -->
		<style type="text/css">
			body {
				padding-bottom: 40px;
			}
			.sidebar-nav {
				padding: 9px 0;
			}
		</style>
		<script type="text/javascript">
var result;
try{
	result = eval(${result});
} catch(e) {};
$(document).ready(function (){
	//提示
	if(result){
		if(result.success){
			//成功
			jAlert(result.msg,'提示',function(){
				window.location.href = '${contextPath}/management/organise/organiseslist';
			});
		}else{
			//失败
			jAlert(result.msg,'提示',function(){
				history.back(-1);
			});
		}
	};
});
	//1. 简单写法：
	$("form").validation(1);

	function checkName(){
		var _name = $("#_name").val();
		if(_name==""){
			$("#_name_msg").html("昵称不能为空");
		} else {
			$("#_name_msg").html("");
		}
	}

	function checkAccount(){
		var _account = $("#_account").val();
		if(_account==""){
			$("#_account_msg").html("账号不能为空");
		} else {
			$("#_account_msg").html("");
		}
	}
	
	function checkPassword(){
		var pass1 = $("#_password").val();
		var pass2 = $("#_repassword").val();
		
		if(pass2 != pass1){
			$("#repass_msg").html("两次密码输入不一致");
		} else if(pass1=="" && pass2==""){
			$("#repass_msg").html("两次密码输入不一致");
		} else {
			$("#repass_msg").html("");
		}
	}

	function saveAccount(){
		var isok = false;
		var userId = $("#userId").val();
		var _name = $("#_name").val();
		var _account = $("#_account").val();
		var pass1 = $("#_password").val();
		var pass2 = $("#_repassword").val();
		
		if(_name==""){
			$("#_name_msg").html("昵称不能为空");
			isok = false;
			return false;
		} else {
			isok = true;
			$("#_name_msg").html("");
		}

		if(_account==""){
			$("#_account_msg").html("账号不能为空");
			isok = false;
			return false;
		} else {
			isok = true;
			$("#_account_msg").html("");
		}

		if(pass2 != pass1){
			isok = false;
			return false;
		} else if(pass1=="" && pass2==""){
			isok = false;
			return false;
		} else {
			isok = true;
			$("#repass_msg").html("");
		}

		if(isok){
			$.ajax({
	    		url:'${contextPath}/management/organise/checkAccount?userId=' + userId + '&account=' + _account,
	    		type:'post',
				cache:false,
				success:function(response){
					if(response == "false"){
						$("#_account_msg").html("该账号已被使用");
					}else{
						$("#saveAccountForm").submit();
					}
				}
	   		});
		}
	}
	
	function backlist(){
		history.back(-1);
	}
</script>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span12">
				<!--detail start-->
				<div class="row-fluid z-ulnone">
					<div class="box span12">
						<div class="box-header well z-h2">
							<h2><i class="icon-user"></i> 登录账号管理</h2>
							<div class="box-icon">
								<a href="javascript:backlist();" class="btn btn-close btn-round"  title="返回"><i class="icon-arrow-left"></i></a>
							</div>
						</div>
						<div class="box-content">
							<!--table2-->
							<form class="form-horizontal" method="post" id="saveAccountForm" name="saveAccountForm" action="${contextPath}/management/organise/saveAccount" enctype="multipart/form-data">
								<div class="z-informa2" style="padding-top: 10px;">
									<table>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="_name">昵称</label>
													<div class="controls">
														<input id="organiseId" name="organiseId" type="hidden" value="${orgId}" />
														<input id="userId" name="userId" type="hidden" value="${aoluser.userId}" />
														<input id="_name" name="_name" type="text" placeholder="请输入昵称" value="${aoluser.name}" onBlur="checkName();" class="input-large" required="required" check-type="required" required-message="请输入昵称" />
														<span id="_name_msg" style="color: red;"></span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="_account">账号</label>
													<div class="controls">
														<input id="_account" name="_account" type="text" placeholder="请输入账号" value="${aoluser.account}" onBlur="checkAccount();" class="input-large" required="required" check-type="required" required-message="请输入账号" />
														<span id="_account_msg" style="color: red;"></span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="_password">密码</label>
													<div class="controls">
														<input id="_password" name="_password" type="password" placeholder="请输入密码" value="${aoluser.password}" onBlur="checkPassword();" class="input-large" required="required" check-type="required" required-message="请输入密码" />
														<span id="pass_msg" style="color: red;"></span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="_repassword">确认密码</label>
													<div class="controls">
														<input id="_repassword" name="_repassword" type="password" placeholder="请输入确认密码" value="${aoluser.password}" onBlur="checkPassword();" class="input-large" required="required" check-type="required" required-message="请输入确认密码" />
														<span id="repass_msg" style="color: red;"></span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="accountstate">账号类型</label>
													<div class="controls">
														<select id="accountstate" name="accountstate" >
															<option value="1" <c:if test="${accountstate=='' || accountstate=='1'}">selected="selected" </c:if>>代理商管理员</option>
															<option value="2" <c:if test="${accountstate=='2'}">selected="selected" </c:if> >特殊用户</option>
														</select>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="button1id"></label>
													<div class="controls">
														<button id="btnSave" name="btnSave" type="button" onclick="saveAccount()" class="btn btn-primary">保存</button>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<!--end table2-->
							</form>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<!--end detail-->
			</div>
			<!--/.fluid-container-->
		</div>
	</div>
</body>
</html>
