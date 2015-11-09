<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<html>
<head>
<link href="${contextPath}/w2ui/css/w2ui.min.css" rel="stylesheet"
	type="text/css" />
<script src="${contextPath}/w2ui/js/jquery.min.js"
	type="text/javascript"></script>
<script src="${contextPath}/w2ui/js/w2ui.js" type="text/javascript"></script>
<script src="${contextPath}/w2ui/js/utils.js" type="text/javascript"></script>
</head>
<body>

	<div id="form">
			<div class="w2ui-label" >地址栏：</div><div style="width: 100%">
				<input type="text" style="width:700px" name="newurl" id="newurl" />&nbsp;&nbsp;<input
					type="button" value="浏览" name="save" onclick="loadDialog();">
			</div>
			<br/>
		<iframe id="ctxDialog" src="http://www.baidu.com" width="100%"
			height="90%"  frameborder="1" border="0″ marginwidth="0″ marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
	</div>
	<script>
		function loadDialog() {
			$("#ctxDialog").attr("src", $("#newurl").val());
		}
	</script>
</body>
</html>