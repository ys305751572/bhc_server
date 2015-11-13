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

	// 新增一道题目
	function addQuestion() {
		console.log(++index);
		var questionHtml = "";
		questionHtml += "<div id=div"+ index +">";
		questionHtml += "<tr><td><div class=\"control-group\"><label class=\"control-label\" style=\"width: 60px;\"for=\"level\">题号</label>";
		questionHtml += "<div class=\"controls\" style=\"margin-left: 80px;\"><label id=\"label"+index+"\">"+ index +"</label><input type=\"hidden\" id=\"questions["+ index +"].qno\" name=\"questions["+ index + "].qno\" value=\"" + index + "\"/></div></div></td></tr>";
		
		questionHtml += "<tr><td><div class=\"control-group\"><label class=\"control-label\" style=\"width: 60px;\"for=\"level\">题目</label>";
		questionHtml += "<div class=\"controls\" style=\"margin-left: 80px;\"><input type=\"text\" id=\"questions["+ index +"].question\" name=\"questions["+ index +"].question\" value=\"\" style=\"width: 870px;\" placeholder=\"请填写题目\" maxlength=\"1000\" />";
		questionHtml += "</div></div></td></tr>"
		
		questionHtml += "<tr><td><div class=\"control-group\"><label class=\"control-label\" style=\"width: 60px;\" for=\"level\">答案A</label><div class=\"controls\" style=\"margin-left: 80px;\">";
		questionHtml += "<input type=\"text\" id=\"questions["+ index +"].optiona\" name=\"questions["+ index +"].optiona\" value=\"\" style=\"width: 870px;\" placeholder=\"请填写答案A\" maxlength=\"1000\" /></div></div></td></tr>";
		
		questionHtml += "<tr><td><div class=\"control-group\"><label class=\"control-label\" style=\"width: 60px;\" for=\"level\">答案B</label><div class=\"controls\" style=\"margin-left: 80px;\">";
		questionHtml += "<input type=\"text\" id=\"questions["+ index +"].optionb\" name=\"questions["+ index +"].optionb\" value=\"\" style=\"width: 870px;\" placeholder=\"请填写答案B\" maxlength=\"1000\" /></div></div></td></tr>";
		
		questionHtml += "<tr><td><div class=\"control-group\"><label class=\"control-label\" style=\"width: 60px;\" for=\"level\">答案C</label><div class=\"controls\" style=\"margin-left: 80px;\">";
		questionHtml += "<input type=\"text\" id=\"questions["+ index +"].optionc\" name=\"questions["+ index +"].optionc\" value=\"\" style=\"width: 870px;\" placeholder=\"请填写答案C\" maxlength=\"1000\" /></div></div></td></tr>";
		
		questionHtml += "<tr><td><div class=\"control-group\"><label class=\"control-label\" style=\"width: 60px;\" for=\"level\">答案D</label><div class=\"controls\" style=\"margin-left: 80px;\">";
		questionHtml += "<input type=\"text\" id=\"questions["+ index +"].optiond\" name=\"questions["+ index +"].optiond\" value=\"\" style=\"width: 870px;\" placeholder=\"请填写答案D\" maxlength=\"1000\" /></div></div></td></tr>";
		
		
		questionHtml += "<tr><div class=\"control-group\"><div class=\"controls\" style=\"margin-left: 80px;\">";
		questionHtml += "<td><a href=\"javascript:addQuestion();\" class=\"button button-rounded button-flat button-tiny\" style=\"width: 50px;\"><i class=\"icon-16\" style=\"width: 20px; height: 20px; line-height: 20px;\"></i>新增</a>";
		questionHtml += "<a id=\"delete"+ index +"\" href=\"javascript:deleteQuestion("+ index +");\" class=\"button button-rounded button-flat button-tiny\" style=\"width: 50px;\"><i class=\"icon-16\" style=\"width: 20px; height: 20px; line-height: 20px;\"></i>删除</a>";
		questionHtml += "</td></div></div></tr></div>";
		
		$("#maindiv").append($(questionHtml));
	}

	function deleteQuestion(indexRow) {
		if(index == 1) {
			alert("请至少保留一条数据");
			return;
		}
		
		$("#div" + indexRow).remove();
		for(var i = (indexRow+1); i <= index; i++ ) {
			$("#label" + i).text(i-1);
			
			
			$("#div"+ i).attr("id","div"+(i-1));
			$("#label"+ i).attr("id","label" + (i-1));
			$("#label"+ i).text(i-1);
			
			document.getElementById("questions["+ i +"].qno").setAttribute("id", "questions["+ (i-1) +"].qno");
			document.getElementsByName("questions["+ i +"].qno")[0].setAttribute("name", "questions["+ (i-1) +"].qno");
			document.getElementById("questions["+ (i-1) +"].qno").setAttribute("value", (i-1));
			
			document.getElementById("questions["+ i +"].question").setAttribute("id", "questions["+ (i-1) +"].question");
			document.getElementsByName("questions["+ i +"].question")[0].setAttribute("name", "questions["+ (i-1) +"].question");
			
			document.getElementById("questions["+ i +"].optiona").setAttribute("id", "questions["+ (i-1) +"].optiona");
			document.getElementsByName("questions["+ i +"].optiona")[0].setAttribute("name", "questions["+ (i-1) +"].optiona");
			
			document.getElementById("questions["+ i +"].optionb").setAttribute("id", "questions["+ (i-1) +"].optionb");
			document.getElementsByName("questions["+ i +"].optionb")[0].setAttribute("name", "questions["+ (i-1) +"].optionb");
			
			document.getElementById("questions["+ i +"].optionc").setAttribute("id", "questions["+ (i-1) +"].optionc");
			document.getElementsByName("questions["+ i +"].optionc")[0].setAttribute("name", "questions["+ (i-1) +"].optionc");
			
			document.getElementById("questions["+ i +"].optiond").setAttribute("id", "questions["+ (i-1) +"].optiond");
			document.getElementsByName("questions["+ i +"].optiond")[0].setAttribute("name", "questions["+ (i-1) +"].optiond");
			
			document.getElementById("delete" + i).setAttribute("id", "delete"+ (i-1));
			document.getElementById("delete" + (i-1)).setAttribute("href", "javascript:deleteQuestion("+ (i-1) +")");
		}
		--index;
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
									<h2>新增试题</h2>
								</div>
							</div>

							<!--box-content start-->
							<div class="box-content">
							<input type="hidden" id="tid" name="tid" value="${tidMap.tid}"/>
							<div id="maindiv">
								<div id="div1">
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width: 60px;"
													for="level">题号</label>
												<div class="controls" style="margin-left: 80px;">
													<label id="label1">1</label>
													<input type="hidden" id="questions[0].qno" name="questions[0].qno" value="1"/>
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
													<input type="text" id="questions[0].question" name="questions[0].question" value=
														"" style="width: 870px;" placeholder="请填写题目"
														maxlength="1000" />
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
													<input type="text" id="questions[0].optiona" name="questions[0].optiona" value=""
														style="width: 870px;" placeholder="请填写答案A"
														maxlength="1000" />
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
													<input type="text" id="questions[0].optionb" name="questions[0].optionb" value=""
														style="width: 870px;" placeholder="请填写答案B"
														maxlength="1000" />
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
													<input type="text" id="questions[0].optionc" name="questions[0].optionc" value=""
														style="width: 870px;" placeholder="请填写答案C"
														maxlength="1000" />
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
													<input type="text" id="questions[0].optiond" name="questions[0].optiond" value=""
														style="width: 870px;" placeholder="请填写答案D"
														maxlength="1000" />
												</div>
											</div>
										</td>
									</tr>

									<tr>
										<div class="control-group">
											<div class="controls" style="margin-left: 80px;">
												<td><a href="javascript:addQuestion();"
													class="button button-rounded button-flat button-tiny"
													style="width: 50px;"><i class="icon-16"
														style="width: 20px; height: 20px; line-height: 20px;"></i>新增</a>
													<a href="javascript:deleteQuestion(1);"
													class="button button-rounded button-flat button-tiny"
													style="width: 50px;"><i class="icon-16"
														style="width: 20px; height: 20px; line-height: 20px;"></i>删除</a>
												</td>
											</div>
										</div>
									</tr>
								</div>
							</div>
							<!--z-informa2 end-->
							 <tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="btnSave"></label>
										<div style="margin-left: 300px;">
											<button id="btnSave" name="btnSave" type="submit" class="btn btn-primary">保存</button>
										</div>
									</div>
								</td>
							  </tr>
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

