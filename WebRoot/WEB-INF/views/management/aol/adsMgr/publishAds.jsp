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
	<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		
<style type="text/css">
	body {
		padding-bottom: 40px;
	}
	.sidebar-nav {
		padding: 9px 0;
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

	//1. 简单写法：
	$("form").validation(1);
});

	function checkType(){
		var infoIds = "";
		// 获取树对象
		var infoTree = $('#orgChoose').combotree('tree');
		// 获取选择的节点
		var infoChoose = infoTree.tree('getChecked');
		for(var i=0;i<infoChoose.length;i++){
			var checknode = infoChoose[i];
			var nodeid = checknode.id;
			infoIds = infoIds + nodeid.substring(2, nodeid.length) + "@@";
		}
		if(infoIds == ""){
			jAlert('请选择广告接收对象','提示');
			return;
		}
		
		$("#publishAdsForm").submit();
	}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<!--detail start-->
			<div class="row-fluid z-ulnone">
				<div class="box span12" style="float: left;">
					<div class="box-header well z-h2">
						<h2><i class="icon-share"></i> 广告发布</h2>
						
						<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
							<jsp:param name="url" value="${contextPath}/management/imageads/adslist"/>
						</jsp:include>
					</div>
					<div class="box-content">
						<!--table2-->
						<form class="form-horizontal" style="margin: 0 0 0px;" id="publishAdsForm" name="publishAdsForm" method="post" action="${contextPath}/management/imageads/doPublishAds" enctype="multipart/form-data">
							<table>
								<tr><td>
									<div class="control-group" style="margin-bottom: 0;">
										<label class="control-label" for="orgChoose"><span style="color: red;">*</span>广告接收对象：</label>
										<div class="controls">
											<input name="imageadsId" type="hidden" value="${ads.imageadsId}" />
											<select id="orgChoose" name="orgChoose" class="easyui-combotree" style="width: 400px;" data-options="url:'${contextPath}/management/aoluser/getOrgAndUserTreeData', multiple:true, required:true"></select>  
										</div>
									</div>
								</td>
								<td>
									<div class="control-group" style="margin-bottom: 0;">
										<label class="control-label" for="btnSave"></label>
										<div class="controls" style=" margin-left: 20px; padding-bottom: 15px;">
											<button id="btnSave" name="btnSave" type="button" onclick="checkType();" class="btn btn-primary">发布</button>
										</div>
									</div>
								</td></tr>
							</table>
						</form>
					</div>
				</div>
				<c:if test="${!empty ads}">
				<div class="box span12" style="float: left; margin-left: 0;">
					<div class="box-content">
						<!--end table2-->
						<div class="z-informa2">
							<ul class="z-informaUl2">
								<li><h2>广告信息</h2></li>
							</ul>
							<table>
							  <tr>
							    <td>
							    	<div class="control-group" style="margin-top: 25px;">
										<label class="control-label_1" for="title">标题</label>
										<div class="controls_1">
											<input name="title" style="width:430px;" type="text" placeholder="请输入标题" readonly="readonly" value="${ads.title}" class="input-large" required="required"  check-type="required" required-message="请输入标题" />
										</div>
									</div>
							    </td>
							    <td rowspan="5">
							    	<div style="width:430px; height:304px; overflow:hidden; margin-top: 0px; margin-left: 20px; border:1px solid blue;">
										<div id="imgDiv">
											<c:if test="${!empty ads.imageUrl}">
												<img src="${contextPath}${ads.imageUrl}" width="430px" height="304px" alt="${ads.imageadsId}">
											</c:if>
										</div>
									</div>
							    </td>
							  </tr>
							  <tr>
							    <td>
							    	<div class="control-group">
										<label class="control-label_1" for="adsLink">链接</label>
										<div class="controls_1">
											<input name="adsLink" style="width:430px;" type="text" placeholder="请输入链接" readonly="readonly" value="${ads.adsLink}" class="input-large" required="required"  check-type="required" required-message="请输入链接" />
										</div>
									</div>
							    </td>
							  </tr>
							  <tr>
							    <td>
							    	<div class="control-group">
										<label class="control-label_1" for="title">描述</label>
										<div class="controls_1">
											<textarea style="width:430px;height:150px;" name="adsDesc" readonly="readonly" placeholder="请填写描述" maxlength="1000">${ads.adsDesc}</textarea>
										</div>
									</div>
							    </td>
							  </tr>
							</table>
						</div>
					</div>
				</div>
				</c:if>
				
			</div>
			<!--end detail-->
		</div>
	</div>
</div>
</body>
</html>
