<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%@ include file="/WEB-INF/views/scripts.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
<script type="text/javascript"
	src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>

<script type="text/javascript">
	var basePath = '${contextPath}';
</script>
<script type="text/javascript" src="${contextPath}/ckeditor/ckeditor.js"></script>
<%
	CKEditorConfig settings = new CKEditorConfig();
	settings.addConfigValue("image_previewText", " ");
	settings.addConfigValue("width", "880px");
	settings.addConfigValue("height", "260px");
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
	background-image:
		url("${contextPath}/resources/img/glyphicons-halflings.png");
}

.icon-user {
	background-position: -168px 2px;
	padding-right: 0px;
	background-image:
		url("${contextPath}/resources/img/glyphicons-halflings.png");
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
	$(document).ready(function() {
		CKEDITOR.instances.detail.setData("${doctor.detail}");
	});

	function submitFrom() {
		$("#testForm").submit();
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span12">
				<div class="row-fluid z-ulnone">
					<form class="form-horizontal" method="post" id="testForm"
						name="doctorForm" action="${contextPath}/management/question/testList" enctype="multipart/form-data">
						<!--box span12 start-->
						<div class="box span12">
							<div class="box-header well z-h2">
								<div class="controls" style="margin-left: 10px;">
									<h2>新增医师</h2>
								</div>
							</div>

							<!--box-content start-->
							<div class="box-content">

								<!--z-informa2 start-->
								<div class="z-informa2" style="margin-bottom: 10px;">
									<table>
										<tbody>
											<tr>
												<td><input name="questions[0].id" value="aaa" /></td>
												<td><input name="questions[0].answer" value="bbb" /></td>
											</tr>
											<tr>
												<td><input name="questions[1].id" value="ccc" /></td>
												<td><input name="questions[1].answer" value="ddd" /></td>
											</tr>
											<tr>
												<td><input name="questions[2].id" value="eee" /></td>
												<td><input name="questions[2].answer" value="fff" /></td>
											</tr>
											<tr>
												<td>
													<button id="btnSendBottom" name="btnSendBottom" type="button" class="btn btn-primary" onclick="submitFrom();">保存</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--z-informa2 end-->
							</div>
							<!--box-content end-->
						</div>
						<!--box span12 end-->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

