<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix = "ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%@ include file="/WEB-INF/views/scripts.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>

<script type="text/javascript">
	var basePath = '${contextPath}';
</script>
<script type="text/javascript" src="${contextPath}/ckeditor/ckeditor.js"></script>
<% 
	CKEditorConfig settings = new CKEditorConfig();
	settings.addConfigValue("image_previewText"," ");
	settings.addConfigValue("width","880px");
	settings.addConfigValue("height","260px");
%>

<!-- The styles -->
<style type="text/css">
	body {
		padding-bottom: 10px;
		padding-top: 0px;
	}
	.icon-briefcase {
		background-position: -432px -142px;
		padding-right: 0px;
		background-image: url("${contextPath}/resources/img/glyphicons-halflings.png");
	}
	.icon-user {
		background-position: -168px 2px;
		padding-right: 0px;
		background-image: url("${contextPath}/resources/img/glyphicons-halflings.png");
	}
	
	.tree-expanded, .tree-collapsed, .tree-checkbox, .tree-indent {
		display: inline-block;
		width: 14px;
		height: 18px;
		vertical-align: top;
		overflow: hidden;
	}
</style>
		
<script type="text/javascript">		
$(document).ready(function(){
	CKEDITOR.instances.detail.setData("${doctor.detail}");
});

function checkName() {
	var _name = $("#name").val();
	if(_name == null || _name.trim() == '') {
		$("#_name").html("请输入名称");
		
	}
}

function checkLevel() {
	var _level = $("#level").val();
	if(_level == null || _level.trim() == '') {
		$("#_level").html("清输入医师职业");
		
	}
}

function checkPart() {
	var _depart = $("#depart").val();
	if(_depart == null || _depart.trim() == '') {
		$("#_depart").html("清输入所在科室");
		
	}
}

function checkHospital() {
	var _hospital = $("#hospital").val();
	if(_hospital == null || _hospital.trim() == '') {
		$("#_hospital").html("清输入所在医院");
		
	}
}

function submitFrom(){
	
	var _name = $("#name").val();
	if(_name == null || _name.trim() == '') {
		$("#_name").html("请输入名称");
		return;
	}
	
	var _level = $("#level").val();
	if(_level == null || _level.trim() == '') {
		$("#_level").html("清输入医师职业");
		return ;
	}
	
	var _depart = $("#depart").val();
	if(_depart == null || _depart.trim() == '') {
		$("#_depart").html("清输入所在科室");
		return ;
	}
	
	var _hospital = $("#hospital").val();
	if(_hospital == null || _hospital.trim() == '') {
		$("#_hospital").html("清输入所在医院");
		return ;
	}
	var detail = CKEDITOR.instances.detail.getData();
	$("#doctorForm").submit();
}


</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<div class="row-fluid z-ulnone">
				<form class="form-horizontal" method="post" id="doctorForm" name="doctorForm" action="${contextPath}/management/doctor/saveDoctor" enctype="multipart/form-data">
					<!--box span12 start-->
					<div class="box span12">
						<div class="box-header well z-h2">
							<div class="controls" style="margin-left: 10px;">
								<h2>
									新增医师
								</h2>
							</div>
						</div>
						
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<input type="hidden" id="id" name="id" value=${doctor.id}>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="name">医师名称</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="name" name="name" value="${doctor.name}" style="width:800px;" onblur="checkName();" placeholder="请填写医师名称" maxlength="1000" required="required"  check-type="required" required-message="请输入名称"/>
											  	  <span id="_name" style="color: red;"></span>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="gender">性别</label>
											  <div class="controls" style="margin-left: 80px;">
											  		<select id="gender" name="gender"  style="width: 120px;">
														<option <c:if test="${doctor.gender=='1'}">selected="selected" </c:if> value="1">男</option>
														<option <c:if test="${doctor.gender=='2'}">selected="selected" </c:if> value="2">女</option>
													</select>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="level">职业</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="level" name="level" value="${doctor.level}" style="width:800px;" onblur="checkLevel();" placeholder="请填写医师职业" maxlength="1000" required="required"  check-type="required" required-message="请输入职业" />
											 	  <span id="_level" style="color: red;"></span>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="depart">科室</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="depart" name="depart" value="${doctor.depart}" style="width:800px;" onblur="checkPart();" placeholder="请填写医师科室" maxlength="1000" required="required"  check-type="required" required-message="请输入科室" />
											  	  <span id="_depart" style="color: red;"></span>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="hospital">所在医院</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="hospital" name="hospital" value="${doctor.hospital}" style="width:800px;" onblur="checkHospital();"  placeholder="请填写医师所在医院" maxlength="1000" required="required"  check-type="required" required-message="请输入所在医院" />
											  	  <span id="hospital" style="color: red;"></span>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="reward">所获奖励</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="reward" name="reward" value="${doctor.reward}" style="width:800px;" placeholder="请填写医师所获奖励" maxlength="1000"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="domain">擅长领域</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="reward" name="domain" value="${doctor.domain}" style="width:800px;" placeholder="请填写医师擅长领域" maxlength="1000" required="required"  check-type="required" required-message="请输入领域" />
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="detail">正文</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <textarea id="detail" name="detail" rows="25"></textarea>
											  	  <ckeditor:replace replace="detail" basePath="${contextPath}/ckeditor/"  config="<%=settings%>"></ckeditor:replace>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<div class="controls" style="margin-left: 20px;">
													<input type="hidden" id="saveORsend" name="saveORsend" value=""/>
													<button id="btnSendBottom" name="btnSendBottom" type="button" class="btn btn-primary" onclick="submitFrom();">保存</button>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div><!--z-informa2 end-->
						</div><!--box-content end-->
					</div><!--box span12 end-->
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>

