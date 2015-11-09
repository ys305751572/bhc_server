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
		<div class="w2ui-page page-0">

			<div class="w2ui-label">新密码：</div>
			<div class="w2ui-field">
				<input type="password" name="plainPassword" id="newPassword"
					class="required" maxlength="32" />
			</div>

			<div class="w2ui-label">确认新密码：</div>
			<div class="w2ui-field">
				<input type="password" name="rPassword" id="rPassword"
					class="required" maxlength="32" />
			</div>

		</div>

		<div class="w2ui-buttons">
			<input type="button" value="修改" name="save"> <input
				type="button" value="关闭" name="returned">
		</div>

	</div>
	<script>
		$(function() {
			$().w2destroy('form');

			$('#form').w2form({
				name : 'form',
				url : '${contextPath }/management/index/updatePwd',
				fields : [ {
					name : 'plainPassword',
					type : 'hidden',
					required : true
				}, {
					name : 'rPassword',
					type : 'hidden',
					required : true
				} ],
				actions : {
					reset : function() {
						this.clear();
					},
					save : function() {
						this.save({}, function(data) {
							showMsg('消息', data.message);
						});
					}

				}
			});

		});
	</script>
</body>
</html>