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
    var index =0;
	$(document).ready(function() {
		++index
	});

	function submitFrom() {
		$("#testForm").submit();
	}

</script>
</head>
<body>
	<div class="container-fluid" style="overflow:auto;">
		<div class="row-fluid" style="overflow:auto;">
			<div id="content" class="span12" style="overflow:auto;">
				<div class="row-fluid z-ulnone" style="overflow:auto;">
					<form class="form-horizontal" method="post" id="testForm" name="doctorForm" action="${contextPath}/management/question/saveQuestions"
						enctype="multipart/form-data">
						<!--box span12 start-->
						<div class="box span12" style="overflow:auto;">
							<div class="box-header well z-h2">
								<div class="controls" style="margin-left: 10px;">
									<h2>查看试题</h2>
									<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
										<jsp:param name="url" value="${contextPath}/management/question/pageList"/>
									</jsp:include>
								</div>
							</div>

							<!--box-content start-->
							<div class="box-content">
							<input type="hidden" id="tid" name="tid" value="${tidMap.tid}"/>
							<div id="maindiv">
								<c:forEach items="${list}" var="question">
									<div id="div1">
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">题号</label>
												<div class="controls" style="margin-left: 80px;">
													<label id="label1">${question.qno}</label>
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">题目</label>
												<div class="controls" style="margin-left: 80px;">
													${question.question }
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">答案A</label>
												<div class="controls" style="margin-left: 80px;">
													${question.optiona }
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">答案B</label>
												<div class="controls" style="margin-left: 80px;">
													${question.optionb}
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">答案C</label>
												<div class="controls" style="margin-left: 80px;">
													${question.optionc }
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">答案D</label>
												<div class="controls" style="margin-left: 80px;">
													${question.optiond }
												</div>
											</div>
										</td>
									</tr>
									<div>-----------------------------------------------------------------------<div>
								</c:forEach>
								</div>
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

