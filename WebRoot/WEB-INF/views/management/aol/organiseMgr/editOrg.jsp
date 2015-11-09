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
			.divShow {
				height: 18px;
				padding: 5px 4px;
				font-size: 13px;
				line-height: 18px;
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
	
	function download1(attachId){
		window.open('${contextPath}/management/organise/download?attachId='+attachId);
	};

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
							<h2><i class="icon-briefcase"></i> 编辑代理商基本信息</h2>
							<div class="box-icon">
								<a href="javascript:backlist();" class="btn btn-close btn-round"  title="返回"><i class="icon-arrow-left"></i></a>
							</div>
						</div>
						<div class="box-content">
							<!--table2-->
							<form class="form-horizontal" method="post" id="restarteProForm" action="${contextPath}/management/organise/saveOrg" enctype="multipart/form-data">
								<div class="z-informa2" style="padding-top: 10px;">
									<table>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="organiseName">公司名称<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="organiseId" type="hidden" value="${org.organiseId}" />
														<input name="parentId" type="hidden" value="${parentId}" />
														<input name="userId" type="hidden" value="${org.userId}" />
														<input name="organiseName" type="text" placeholder="请输入公司名称" value="${org.organiseName}" class="input-large" style="width: 596px;" required="required" check-type="required" required-message="请输入公司名称" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="organiseCode">公司编码&nbsp;</label>
													<div class="controls">
														<input name="organiseCode" type="text" placeholder="请填写公司编码" value="${org.organiseCode}" class="input-large" />
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="organiseShortname">公司简称&nbsp;</label>
													<div class="controls">
														<input name="organiseShortname" type="text" placeholder="请填写公司简称" value="${org.organiseShortname}" class="input-large" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="telephone">联系电话<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="telephone" type="text" placeholder="请填写联系电话" value="${org.telephone}" class="input-large" required="required" check-type="required" required-message="请填写联系电话" />
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="fax">传真号码<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="fax" type="text" placeholder="请填写传真号码" value="${org.fax}" class="input-large" required="required" check-type="required" required-message="请填写传真号码" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="mailbox">公司邮箱<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="mailbox" type="text" placeholder="请填写公司邮箱" value="${org.mailbox}" class="input-large" required="required" check-type="required" required-message="请填写公司邮箱" />
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="zipcode">邮政编码<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="zipcode" type="text" placeholder="请填写邮政编码" value="${org.zipcode}" class="input-large" required="required" check-type="required" required-message="请填写邮政编码" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="website">公司网址<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="website" type="text" placeholder="请填写公司网址" value="${org.website}" class="input-large" style="width: 596px;" required="required" check-type="required" required-message="请填写公司网址" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="organiseAddress">公司地址<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="organiseAddress" type="text" placeholder="请填写公司地址" value="${org.organiseAddress}" class="input-large" style="width: 596px;" required="required" check-type="required" required-message="请填写公司地址" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="storeAddress">门店地址<span style="color:red;">*</span></label>
													<div class="controls">
														<input name="storeAddress" type="text" placeholder="请填写门店地址" value="${org.storeAddress}" class="input-large" style="width: 596px;" required="required" check-type="required" required-message="请填写门店地址" />
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="uploadAttach">资质材料<span style="color:red;">*</span></label>
													<div class="controls">
														<c:if test="${!empty attach}">
															<input name="uploadAttach" type="file" value="" class="input-medium" />
														</c:if>
														<c:if test="${empty attach}">
															<input name="uploadAttach" type="file" value="" class="input-medium" required="required" check-type="required" required-message="请上传资质材料" />
														</c:if>
													</div>
												</div>
												<c:if test="${!empty attach}">
												<div class="control-group">
													<label class="control-label" for="attachId">&nbsp;</label>
													<div class="controls">
														<input name="attachId" type="hidden" value="${org.attachId}"/>
														${attach.attachTruename}&nbsp;&nbsp;<a onclick="javascript:download1('${attach.attachId}');" style="cursor: pointer;">下载</a>
													</div>
												</div>
												</c:if>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="button1id"></label>
													<div class="controls">
														<button id="btnSave" name="btnSave" type="submit" class="btn btn-primary">保存</button>
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="zhuyi">&nbsp;</label>
													<div class="controls">
														<input name="zhuyi" type="hidden" value=" "/>
														<div class="divShow">注意：带<span style="color:red;">*</span>号的为必填项！</div>
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
<script type="text/javascript">
$('.form_datetime').datetimepicker({
	language:  'zh-CN',
	weekStart: 1,
	todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	forceParse: 0,
	showMeridian: 1,
	format:'yyyy-mm-dd',
	minView:2
});
</script>
</html>
