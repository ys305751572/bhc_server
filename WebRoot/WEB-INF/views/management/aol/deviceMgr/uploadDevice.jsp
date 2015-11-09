<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/WEB-INF/views/scripts.jsp"%>
		
	<script type="text/javascript" src="${contextPath}/resources/js/bootstrap-validation.js"  charset="UTF-8"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/aolIcons.css" />
	<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		
<style type="text/css">
	body {
		padding-bottom: 40px;
	}
	.sidebar-nav {
		padding: 9px 0;
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
</style>
<script type="text/javascript">
	var msg = '${msg}';
	$(document).ready(function (){
		if(msg!=""){
			jAlert(msg,'提示');
		};
		//1. 简单写法：
		$("form").validation(1);
	});

	function checkFileType(){
		var filename = $("#uploadFile").val();
		if(filename){
			var filetype = filename.substring(filename.lastIndexOf(".")+1,filename.length).toUpperCase();
			if(filetype != 'XLS' && filetype != 'XLSX'){
				jAlert('文件格式不对，请选择正确的文件！','提示');
				return;
			} else if(filetype == 'XLSX'){
				jAlert('文件类型不支持，请转化成Excel 2003版，谢谢配合！','提示');
				return;
			}
		} else {
			jAlert('请选择文件路径！','提示');
			return;
		}
		
		// 获取树对象
		var orgTree = $('#orgChoose').combotree('tree');
		// 获取选择的节点
		var orgChoose = orgTree.tree('getSelected');
		if(!orgChoose){
			jAlert('请选择所属代理商！','提示');
			return;
		}

		$("#deviceForm").submit();
	}

	function downloaddev(){
		window.open('${contextPath}/management/devices/download');
	};
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<!--detail start-->
			<div class="row-fluid z-ulnone">
				<div class="box span12">
					<div class="box-content">
						<!--table2-->
						<form class="form-horizontal" style="margin: 0 0 0px;" id="deviceForm" name="deviceForm" method="post" action="${contextPath}/management/devices/uploadDeviceFile" enctype="multipart/form-data">
							<div>
								<table>
								<tr><td>
									<div class="control-group">
										<label class="control-label" for="uploadFile"><span style="color: red;">*</span>选择文件：</label>
										<div class="controls">
											<input id="uploadFile" name="uploadFile" type="file" class="input-medium" style="width: 400px"  required="required" check-type="required" required-message="请选择文件路径"/>
										</div>
									</div>
								</td></tr>
								<tr><td>
									<div class="control-group">
										<label class="control-label" for="orgChoose"><span style="color: red;">*</span>所属代理商：</label>
										<div class="controls">
											<select id="orgChoose" name="orgChoose" class="easyui-combotree" style="width: 400px;" data-options="url:'${contextPath}/management/devices/getOrgTreeData',required:true"></select>  
										</div>
									</div>
								</td></tr>
								<tr><td>
									<div class="control-group">
										<label class="control-label" for="btnSave"></label>
										<div class="controls">
											<button id="btnSave" name="btnSave" type="button" onclick="checkFileType();" class="btn btn-primary">导入</button>
										</div>
									</div>
								</td></tr>
								<tr><td>
									<div class="control-group" style="margin-top: 20px; margin-bottom: 0px;">
										<label class="control-label" style="width: 210px;"><a onclick="javascript:downloaddev();" style="cursor: pointer;"><i class="icon-12" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;下载设备批量导入文件模板</a></label>
									</div>
								</td></tr>
								</table>
							</div>
						</form>
						<!--end table2-->
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<!--end detail-->
		</div>
	</div>
</div>
</body>
</html>
