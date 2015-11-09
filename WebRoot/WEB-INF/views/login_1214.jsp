<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱奥乐健康云平台</title>
<link href="${contextPath}/resources/aol/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="">
<!-- form验证 -->
<link rel="stylesheet" href="${contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css" type="text/css"/>
<script src="${contextPath}/styles/formValidator.2.2.1/js/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/styles/formValidator.2.2.1/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
.main-inner-left{
	float:left;
	width:902px;
	height:620px;
	overflow:visible;
	margin:0px 0px;
	position:relative;
	clear:both;
	background-image: url(${contextPath}/resources/aol/images/login_image.png); 
	background-position: 0% 0%; 
	background-repeat: no-repeat no-repeat;
}
.main-inner-right{
	float:left;
	width:396px;
	height:620px;
	overflow:visible;
	margin:0px 0px;
	position:relative;
}
.login{
	width:338px;
	height:328px;
	position: absolute; 
	top: 76px; 
	right: 60px; 
	float: right; 
	background:#fff;
	border:1px solid #b7c2c9;
	_display:inline;
	text-align:left;
	position:relative;
	z-index:2;
	border-radius:2px;
}

.topTitle {
	float: right;
	position: relative;
	height: 18px;
	margin-top: 31px;
	margin-right: 31px;
	display: block;
	color: #666;
	font-size: 14px;
}


</style>
<script>
function readCookie(name) { 
	var search; 
	search = name + "="; 
	offset = document.cookie.indexOf(search); 
	if (offset != -1) { 
		offset += search.length;
		end = document.cookie.indexOf(";", offset);
		if (end == -1){ 
			end = document.cookie.length; 
		} 
		return unescape(document.cookie.substring(offset, end)); 
	}else{ 
		return ""; 
	} 
}

function writeCookie(name, value) { 
	exp = new Date(); 
	exp.setTime(exp.getTime() + (86400 * 1000 * 30)); 
	document.cookie = name + "=" + escape(value) + "; expires=" + exp.toGMTString() + "; path=/"; 
} 

jQuery(document).ready(function(){
	jQuery("#formID").validationEngine();
	if(window.parent.window.location.href!=window.location.href)//判断超时
		window.top.location.href = window.location.href;
});

jQuery(document).ready(function(){
	$("#captcha").click(function(){
  		$(this).attr("src", "${contextPath}/Captcha.jpg?time=" + new Date());
  		return false;
  	});
});

jQuery(document).ready(function(){
	//绑定输入框事件
	//用户名
	if($('#username').val() == ''){
		$('#username').next().show();
	}else{
		$('#username').next().hide();
	}
	$('#userNameInputLine').mouseover(function(){
		$(this).attr('class',function(){return this.className + ' loginFormIpt-over'});
	});
	$('#userNameInputLine').mouseout(function(){
		$(this).attr('class',function(){return this.className.replace(/\sloginFormIpt-over/g, '')});
	});
	$('#username').focus(function(){
		$(this).next().hide();
		$('#checkDiv').html("");
		$(this).attr('class',function(){return this.className + ' loginFormTdIpt-focus'});
		$('#userNameInputLine').attr('class',function(){return this.className + ' loginFormIpt-focus'});
	});
	$('#username').click(function(){
		$(this).next().hide();
	});
	$('#username').blur(function(){
		if($(this).val() == ''){
			$(this).next().show();
		}
		$('#userNameInputLine').attr('class',function(){return this.className.replace(/\sloginFormIpt-focus/g, '')});
		if($(this).val() == ''){
			$('#userNameInputLine').attr('class',function(){return this.className + ' showPlaceholder'});
			$(this).attr('class',function(){return this.className.replace(/\sloginFormTdIpt-focus/g, '')});
		}
	});

	//密码
	if($('#password').val() == ''){
		$('#password').next().show();
	}else{
		$('#password').next().hide();
	}
	$('#pwdInputLine').mouseover(function(){
		$(this).attr('class',function(){return this.className + ' loginFormIpt-over'});
	});
	$('#pwdInputLine').mouseout(function(){
		$(this).attr('class',function(){return this.className.replace(/\sloginFormIpt-over/g, '')});
	});
	$('#password').focus(function(){
		$(this).next().hide();
		$('#passwordError').html("");
		$(this).attr('class',function(){return this.className + ' loginFormTdIpt-focus'});
		$('#pwdInputLine').attr('class',function(){return this.className + ' loginFormIpt-focus'});
	});
	$('#password').click(function(){
		$(this).next().hide();
	});
	$('#password').blur(function(){
		if($(this).val() == ''){
			$(this).next().show();
		}
		$('#pwdInputLine').attr('class',function(){return this.className.replace(/\sloginFormIpt-focus/g, '')});
		if($(this).val() == ''){
			$('#pwdInputLine').attr('class',function(){return this.className + ' showPlaceholder'});
			$(this).attr('class',function(){return this.className.replace(/\sloginFormTdIpt-focus/g, '')});
		}
	});

	//验证码
	if($('#captcha_key').val() == ''){
		$('#captcha_key').next().show();
	} else {
		$('#captcha_key').next().hide();
	}
	$('#checkCodeInputLine').mouseover(function(){
		$(this).attr('class',function(){return this.className + ' loginFormIpt-over'});
	});
	$('#checkCodeInputLine').mouseout(function(){
		$(this).attr('class',function(){return this.className.replace(/\sloginFormIpt-over/g, '')});
	});
	$('#captcha_key').focus(function(){
		$(this).next().hide();
		$('#checkCodeError').html("");
		$(this).attr('class',function(){return this.className + ' loginFormTdIpt-focus'});
		$('#checkCodeInputLine').attr('class',function(){return this.className + ' loginFormIpt-focus'});
	});
	$('#captcha_key').click(function(){
		$(this).next().hide();
	});
	$('#captcha_key').blur(function(){
		if($(this).val() == ''){
			$(this).next().show();
		}
		$('#checkCodeInputLine').attr('class',function(){return this.className.replace(/\sloginFormIpt-focus/g, '')});
		if($(this).val() == ''){
			$('#checkCodeInputLine').attr('class',function(){return this.className + ' showPlaceholder'});
			$(this).attr('class',function(){return this.className.replace(/\sloginFormTdIpt-focus/g, '')});
		}
	});

	//登录按钮
	$('#loginBtn').mouseover(function(){
		$(this).attr('class',function(){return this.className + ' btn-login-hover'});
	});
	$('#loginBtn').mouseout(function(){
		$(this).attr('class','btn btn-login');
	});
	$('#loginBtn').mousedown(function(){
		$(this).attr('class',function(){return this.className + ' btn-login-active'});
	});
});

</script>
</head>

<body>
	<section class="main" id="mainBg">
	  <div style="width:1299px; height: 700px; margin: 0 auto; ">
		<div style="height: 80px;background-color: #FFFFFF;">
			<div style="float:left; width: 224px; height: 56px; margin: 12px 35px; background-image: url(${contextPath}/resources/aol/images/logo.png); background-position: 0px 0px; background-repeat: no-repeat no-repeat;"></div>
			<div class="topTitle"><a href="#" style="color: #666;">忘了密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" style="color: #666;">立即注册</a></div>
		</div>
		
		<div style="width:1299px; height: 620px;margin: 0 0; background-image: url(${contextPath}/resources/aol/images/login_image_bg.png);">
			<div class="main-inner-left" style="">
				<div style="height: 11px; margin:0px 0px; background-image: url(${contextPath}/resources/aol/images/listbg.png);"></div>
			</div>
			<div class="main-inner-right" style="">
				<div style="height: 11px; margin:0px 0px; background-image: url(${contextPath}/resources/aol/images/listbg.png);"></div>
				<div id="loginBlock" class="login">
					<form class="loginForm" autocomplete="off" action="${contextPath}/login" id="formID" method="post">
						<b class="loginFormTitle">用户登录</b>
						
						<div id="checkDiv" class="loginErrorDiv"></div>
						<div id="userNameInputLine" class="loginFormIpt showPlaceholder">
							<input class="loginFormTdIpt loginFormTdIpt-focus" tabindex="1" title="请输入用户名" id="username" name="username" type="text" value="${username}">
							<label for="username" class="placeholder" id="idPlaceholder" style="display: none;">用户名</label>
							<input type="hidden" id="type" name="type" value="0" />
						</div>
						
						<div id="passwordError" class="loginErrorDiv"></div>
						<div id="pwdInputLine" class="loginFormIpt showPlaceholder">
							<input class="loginFormTdIpt loginFormTdIpt-focus loginFormTdIpt-focus loginFormTdIpt-focus" tabindex="2" title="请输入密码" value="" id="password" name="password" type="password" maxlength="20">
							<label for="password" class="placeholder" id="pwdPlaceholder" style="display: none;">密码</label>
						</div>
						
						<div id="checkCodeError" class="loginErrorDiv"></div>
						<div class="loginFormIpt loginFormIptWiotTh">
							<div id="checkCodeInputLine" class="loginFormIpt showPlaceholder" style="width: 158px;float: left; margin: 0px 10px 0px 0px;clear: none;">
								<input class="loginFormTdIpt" style="width: 142px; padding: 7px 8px 6px 8px;" tabindex="3" title="请输入验证码" id="captcha_key" name="captcha_key" type="text" maxlength="6" autocomplete="off">
								<label for="captcha_key" class="placeholder" id="checkCodePlaceholder">验证码</label>
							</div>
			        
					        <span id="checkCodeImg" style="height: 35px;float: left; display: block;">
					        	<img alt="验证码" src="${contextPath}/Captcha.jpg" height="33" width="85" id="captcha"">
					        </span>
						</div>
						
						<div class="loginFormIpt loginFormIptWiotTh" style="height: 14px; line-height: 14px; margin: 5px 0px 5px 42px;">
							<c:if test="${msg!=null}">
								<p style="color: red; margin-left: 10px;">${msg}</p>
							</c:if>
						</div>
						
						<div class="loginFormIpt loginFormIptWiotTh" style="text-align: center">
							<button id="loginBtn" class="btn btn-login" style="margin-left: 75px;" type="submit" tabindex="7">登 录</button>
						</div>
					</form>
	
				</div>
			</div>
		</div>
	  </div>
	</section>
</body>
</html>