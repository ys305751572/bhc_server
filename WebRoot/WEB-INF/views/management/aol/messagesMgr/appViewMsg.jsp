<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
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

<!-- The styles -->
<style type="text/css">
	body {
		padding-bottom: 10px;
		padding-top: 0px;
		color: #000;
		font-weight: normal;
		font-family: "lucida Grande",Verdana,"Microsoft YaHei";
	}
</style>
		
<script type="text/javascript">

</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<div class="row-fluid z-ulnone">
				<!--box span12 start-->
				<div class="box span12">
					<div class="box-header well z-h2">
						<h2><i class="icon-th"></i> ${message.messagesTitle}</h2>
						<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
							<jsp:param name="url" value="${contextPath}/management/messages/${backUrl}"/>
						</jsp:include>
					</div>
					
					<!--box-content start-->
					<div class="box-content">
						<!--z-informa2 start-->
						<div class="z-informa2" style="margin-bottom: 10px;">
							<div style="background: #f2f2f2;border-top: none;border-left: none;border-right: none;">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="word-break:break-all;padding:2px 12px 0 14px;line-height:19px;text-align: left;" width="99%" >
											<span style="color: #8fa7b3;">时&nbsp;&nbsp;&nbsp;间：</span><b style="font: normal 12px 'lucida Grande',Verdana,'Microsoft YaHei';color: #7f7f7f;">${appPushList.sendtime} </b>
										</td>
									</tr>
									<tr>
										<td style="padding:0 0 0 14px;line-height:19px;text-align:left;">
											<div>
												<div style="color: #8fa7b3;white-space: nowrap;position:absolute;">消息接受人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
												<div style="padding-left:100px;font-size:12px;overflow:hidden; zoom:1;">${appPushList.username}</div>
												<div style="height:5px;">&nbsp;</div>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<div id="contentDiv" style="position:relative;font-size:14px;height:auto;padding:15px 15px 10px 15px;z-index:1;zoom:1;line-height:1.7;color: #000;font-weight: normal;font-family: 'lucida Grande',Verdana,'Microsoft YaHei';">
								<div id="mailContentContainer" style="height: auto;min-height: 100px;_height: 100px;word-wrap: break-word;font-size: 14px;font-family: 'lucida Grande',Verdana,'Microsoft YaHei';">
									<div>
										${appPushList.text}
									</div>
								</div>
							</div>
							
						
						</div><!--z-informa2 end-->
					</div><!--box-content end-->
				</div><!--box span12 end-->
			</div>
		</div>
	</div>
</div>
</body>
</html>

