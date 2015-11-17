<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<%@ include file="/WEB-INF/views/scripts.jsp"%>
	<script type="text/javascript" src="${contextPath}/resources/js/bootstrap-validation.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${contextPath}/resources/aol/js/uploadPreview.js" charset="UTF-8"></script>
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
		
		.control-label_1 {
			float: left;
			width: 80px;
			padding-top: 5px;
			text-align: right;
		}
		
		.controls_1 {
			margin-left: 100px;
		}
	</style>
<script type="text/javascript">
var result;
try{
	result = eval(${result});
} catch(e) {
};

$(document).ready(function (){
	//提示
	if(result){
		if(result.success){
			//成功
			jAlert(result.msg,'提示',function(){
				window.location.href = '${contextPath}/management/imageads/adslist';
			});
		}else{
			//失败
			jAlert(result.msg,'提示',function(){
				history.back(-1);
			});
		}
	};

	$("#imageFile").uploadPreview({ width: 400, height: 354, imgDiv: "#imgDiv"});
});
	//1. 简单写法：
	$("form").validation(1);
	
</script>
</head>
<body>		
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row-fluid z-ulnone">
			<div class="box span12">
				<div class="box-header well z-h2">
					<h2><i class="icon-film"></i>检测题目</h2>
					
					<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
						<jsp:param name="url" value="${contextPath}/management/question/qcList"/>
					</jsp:include>
				</div>
				<div class="box-content">
					<form class="form-horizontal" method="post" id="restarteProForm" action="${contextPath}/management/question/edit" enctype="multipart/form-data">
					<table>
					  <tr>
					    <td>
					    	<div class="control-group">
								<label class="control-label_1" for="title">标题</label>
								<div class="controls_1">
									<input name="id" type="hidden" value="${qc.id}" />
									<input name="title" style="width:450px;" type="text" placeholder="请输入标题" value="${qc.title}" class="input-large" required="required"  check-type="required" required-message="请输入标题" />
								</div>
							</div>
					    </td>
					    <td rowspan="5">
					    	<div style="width:400px; height:354px; overflow:hidden; margin-top: 0px; margin-bottom: 18px; margin-left: 20px; border:1px solid blue;">
								<div id="imgDiv">
									<c:if test="${!empty qc.image}">
										<img src="${contextPath}${qc.image}" width="400px" height="354px"  alt="${qc.image}">
									</c:if>
								</div>
							</div>
					    </td>
					  </tr>
					  <tr>
					    <td>
					    	<div class="control-group">
								<label class="control-label_1" for="imageFile">图片</label>
								<div class="controls_1">
									<input id="imageFile" name="imageFile" style="width:450px;" style="width:450px;" type="file" class="input-medium" />
								</div>
							</div>
					    </td>
					  </tr>
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
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>		
