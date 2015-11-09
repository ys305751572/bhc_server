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
			jAlert(result.msg,'提示');
		}else{
			//失败
			jAlert(result.msg,'提示');
		}
	};
});
	//1. 简单写法：
	$("form").validation(1);
	
	function download1(attachId){
		window.open('${contextPath}/management/organise/download?attachId='+attachId);
	};

	function editOwnInfo(){
		window.location.href = "${contextPath}/management/organise/editowninfo";
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
							<h2><i class="icon-briefcase"></i> 基本信息</h2>
							<div style="float: right">
								<button id="btnSendTop" name="btnSendTop" type="button" class="btn btn-primary" onclick="editOwnInfo();">编辑</button>
							</div>
						</div>
						<div class="box-content">
							<!--table2-->
							<form class="form-horizontal" method="post" id="restarteProForm" action="" enctype="multipart/form-data">
								<div class="z-informa2" style="padding-top: 10px;">
									<table>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="organiseName">公司名称</label>
													<div class="controls">
														<input name="organiseName" type="hidden" value=" "/>
														<div class="divShow">${org.organiseName}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="organiseCode">公司编码</label>
													<div class="controls">
														<input name="organiseCode" type="hidden" value=" "/>
														<div class="divShow">${org.organiseCode}</div>
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="organiseShortname">公司简称</label>
													<div class="controls">
														<input name="organiseShortname" type="hidden" value=" "/>
														<div class="divShow">${org.organiseShortname}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="telephone">联系电话</label>
													<div class="controls">
														<input name="telephone" type="hidden" value=" "/>
														<div class="divShow">${org.telephone}</div>
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="fax">传真号码</label>
													<div class="controls">
														<input name="fax" type="hidden" value=" "/>
														<div class="divShow">${org.fax}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="control-group">
													<label class="control-label" for="mailbox">公司邮箱</label>
													<div class="controls">
														<input name="mailbox" type="hidden" value=" "/>
														<div class="divShow">${org.mailbox}</div>
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label" for="zipcode">邮政编码</label>
													<div class="controls">
														<input name="zipcode" type="hidden" value=" "/>
														<div class="divShow">${org.zipcode}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="website">公司网址</label>
													<div class="controls">
														<input name="website" type="hidden" value=" "/>
														<div class="divShow">${org.website}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="organiseAddress">公司地址</label>
													<div class="controls">
														<input name="organiseAddress" type="hidden" value=" "/>
														<div class="divShow">${org.organiseAddress}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="storeAddress">门店地址</label>
													<div class="controls">
														<input name="storeAddress" type="hidden" value=" "/>
														<div class="divShow">${org.storeAddress}</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="control-group">
													<label class="control-label" for="attachId">资质材料</label>
													<div class="controls">
														<input name="attachId" type="hidden" value="${org.attachId}"/>
														<c:if test="${!empty attach}">
															<div class="divShow">
																${attach.attachTruename}&nbsp;&nbsp;<a onclick="javascript:download1('${attach.attachId}');" style="cursor: pointer;">下载</a>
															</div>    
														</c:if>
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
