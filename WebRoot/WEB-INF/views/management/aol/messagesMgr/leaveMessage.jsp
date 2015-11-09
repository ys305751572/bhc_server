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
<!-- The styles -->
<style type="text/css">
	body {
		padding-bottom: 10px;
		padding-top: 0px;
	}
</style>
		
<script type="text/javascript">		
var result = '${msg}';

$(document).ready(function(){
	//提示
	if(result){
		//成功
		jAlert(result,'提示');
		$('#messagesTitle').val("");
	};
	
	CKEDITOR.replace('messagesContent',{
		language:'zh-cn',//简体中文   
		width: 800,
		height: 200,
		toolbar:[
			['Bold','Italic','Underline','Strike'],
			['Smiley','SpecialChar'],
			['Format','Font','FontSize'],
			['TextColor','BGColor']
		]
	});
});

function submitMessageFrom(){
	var isOk = false;
/*	
	var messagesSendee = "";
	var obj = document.getElementsByName("messagesSendee");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
        	messagesSendee = messagesSendee + obj[i].value;
        }
    }
	if(messagesSendee){
		isOk = true;
	} else {
		jAlert('请选择留言对象！','提示');
		isOk = false;
		return;
	}
*/	
	var messagesTitle = $('#messagesTitle').val();
	if(messagesTitle){
		isOk = true;
	} else {
		jAlert('请填写主题！','提示');
		isOk = false;
	}
	var msgStr = CKEDITOR.instances.messagesContent.getData();
	if(msgStr){
		isOk = true;
	} else {
		jAlert('请填写留言！','提示');
		isOk = false;
	}
	
    if(isOk){
    	$('#messageForm').submit();
    } 
}

</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<div class="row-fluid z-ulnone">
				<form class="form-horizontal" method="post" id="messageForm" name="messageForm" action="${contextPath}/management/leavemsg/saveLeaveMessage" enctype="multipart/form-data">
					<!--box span12 start-->
					<div class="box span12">
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<tr>
										<td>
											<div class="control-group" style="margin-top: 10px;">
											  <label class="control-label" style="width:60px;" for="messagesTitle">主题</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="messagesTitle" name="messagesTitle" value="${messages.messagesTitle}" style="width:792px;" placeholder="请填写主题" maxlength="1000"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group" style="margin-bottom: 0px;">
											  <label class="control-label" style="width:60px;" for="messagesContent">正文</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <textarea id="messagesContent" name="messagesContent" rows="10"></textarea>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<div class="controls" style="margin-left: 760px;">
													<button id="btnSendBottom" name="btnSendBottom" type="button" onclick="submitMessageFrom();" class="btn btn-primary">&nbsp;发&nbsp;表&nbsp;</button>
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

