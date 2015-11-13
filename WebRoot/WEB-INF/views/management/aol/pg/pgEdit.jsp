<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix = "ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		
		.tree-expanded, .tree-collapsed, .tree-checkbox, .tree-indent {
			display: inline-block;
			width: 14px;
			height: 18px;
			vertical-align: top;
			overflow: hidden;
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
					window.location.href = '${contextPath}/management/pg/pgList';
				});
			}else{
				//失败
				jAlert(result.msg,'提示',function(){
					history.back(-1);
				});
			}
		};
		
		CKEDITOR.instances.content.setData("${pg.content}");
		$("#imageFile").uploadPreview({ width: 400, height: 354, imgDiv: "#imgDiv"});
	});
	
	function submitForm() {
		var content = CKEDITOR.instances.content.getData();
		$("form").validation(1);
		$("#restarteProForm").submit();
	}
	
	//1. 简单写法：
	
	
</script>
</head>
<body>		
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row-fluid z-ulnone">
			<div class="box span12">
				<div class="box-header well z-h2">
					<h2><i class="icon-film"></i> 病理讲座信息</h2>
					<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
						<jsp:param name="url" value="${contextPath}/management/pg/pgList"/>
					</jsp:include>
				</div>
				<div class="box-content">
					<form class="form-horizontal" method="post" id="restarteProForm" action="${contextPath}/management/pg/edit" enctype="multipart/form-data">
					<table>
					  <tr>
					    <td>
					    	<div class="control-group">
								<label class="control-label_1" for="title">标题</label>
								<div class="controls_1">
									<input name="id" type="hidden" value="${pg.id}" />
									<input name="title" style="width:450px;" type="text" placeholder="请输入标题" value="${pg.title}" class="input-large" required="required"  check-type="required" required-message="请输入标题" />
								</div>
							</div>
					    </td>
					    <td rowspan="5">
					    	<div style="width:400px; height:354px; overflow:hidden; margin-top: 0px; margin-bottom: 18px; margin-left: 20px; border:1px solid blue;">
								<div id="imgDiv">
									<c:if test="${!empty pg.image}">
										<img src="${contextPath}/${pg.image}" width="400px" height="354px"  alt="${pg.image}">
									</c:if>
								</div>
							</div>
					    </td>
					  </tr>
					  <tr>
						<td>
							<div class="control-group">
							  <label class="control-label" style="width:60px;" for="content">正文</label>
							  <div class="controls" style="margin-left: 80px;">
							  	  <textarea id="content" name="content" rows="25"></textarea>
							  	  <ckeditor:replace replace="content" basePath="${contextPath}/ckeditor/"  config="<%=settings%>"></ckeditor:replace>
							  </div>
							</div>
						</td>
					 </tr>
					  <tr>
					    <td>
					    	<div class="control-group">
								<label class="control-label_1" for="url">链接地址</label>
								<div class="controls_1">
									<input name="url" style="width:450px;" type="text" placeholder="请输入URL" value="${pg.url}" class="input-large"/>
								</div>
							</div>
					    </td>
					  </tr>
					  
					  <tr>
						<td>
							<div class="control-group">
							  <label class="control-label" style="width:60px;" for="type">性别</label>
							  <div class="controls" style="margin-left: 80px;">
							  		<select id="type" name="type"  style="width: 120px;">
										<option <c:if test="${pg.type=='0'}">selected="selected" </c:if> value="0">病理</option>
										<option <c:if test="${pg.type=='1'}">selected="selected" </c:if> value="1">讲座</option>
									</select>
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
									<button id="btnSave" name="btnSave" type="button" onclick="javascript:submitForm();" class="btn btn-primary">保存</button>
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
