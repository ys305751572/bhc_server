<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<link href="${contextPath}/resources/aol/css/bootstrap.min.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/charisma-app.css" rel="stylesheet">
	<link href="${contextPath}/resources/aol/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<link href="${contextPath}/resources/jquery.alerts-1.1/jquery.alerts.css" rel="stylesheet" type="text/css" />
	<script src="${contextPath}/resources/js/jquery-1.7.2.min.js"></script>
	<script src="${contextPath}/resources/js/jquery-ui-1.8.21.custom.min.js"></script>
	<script src="${contextPath}/resources/aol/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/jquery.alerts-1.1/jquery.alerts.js"></script>
	<!-- The styles -->
	<style type="text/css">
		body {
			padding-bottom: 40px;
			font: 12px/1.6 "微软雅黑","宋体";
		}
	</style>
	<script type="text/javascript">
		function searchDevice(){
			var searchValue = $('#deviceCode').val();
			if(trim(searchValue)){
				var search_url = "${contextPath}/management/devices/queryDevice?deviceCode=" + searchValue;
				var iframeHtml = '<iframe name="deviceInfoIFrame" id="deviceInfoIFrame" src="'+search_url+'" frameborder="0" scrolling="no"style="width: 100%; height: 600px;" allowtransparency="true" ></iframe>';

				var divshow = $("#deviceInfoIFrameDiv");
				divshow.text("");
				divshow.append(iframeHtml);
			} else {
				jAlert('请输入机器识别码','提示');
			}
		}

		function trim(str){
		    return str.replace(/(^\s*)|(\s*$)/g, "");
		}

		function setIframeHeight(__height){
			if(__height > 380){
				document.getElementById("deviceInfoIFrame").style.height = __height+"px";
			}
		}
	</script>
</head>
<body>		
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row-fluid z-ulnone">
			<div class="box span12">
				<div class="box-content">
				
					<div class="input-group" style="width:600px;margin-left: 20px;">
                    	<input type="text" class="form-control" id="deviceCode" name="deviceCode" placeholder="请输入机器识别码">
                    	<span class="input-group-btn" style="width:59px;">
                            <button class="btn btn-default" style="padding:9px 24px;" type="button" title="搜索" onclick="searchDevice();">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                	</div>
				</div>
			</div>
			
			<div style="margin-top: 10px; margin-bottom: 10px;">
				<div id="deviceInfoIFrameDiv" style="padding: 10px 0;">
					<div style="width:600px;margin-left: 30px;">
						根据设备的唯一机器识别码，搜索设备相关信息，包括设备基本信息、用户基本信息、代理商基本信息。
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


</body>
</html>		
		
		
		
		
		
		