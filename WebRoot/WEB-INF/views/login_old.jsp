<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱奥乐健康云平台</title>
<script type="text/javascript" src="${contextPath}/resources/js/jquery-1.8.3.min.js"></script>
<link href="${contextPath}/styles/management/themes/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="">
<!-- form验证 -->
<link rel="stylesheet" href="${contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css" type="text/css"/>
<script src="${contextPath}/styles/formValidator.2.2.1/js/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/styles/formValidator.2.2.1/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
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
    	var type = readCookie("CZInvesReview_login_type");
    	if(type==""){
    		type="0";
    	}
    	$("#type").val(type);

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

</script>
</head>

<body>

	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">

			</h1>

			<div  class="login_headerContent">
				<div class="navList">

				</div>
				<h2 class="login_title">请登录</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" action="${contextPath}/login" id="formID" >
					<c:if test="${msg!=null }">
						<p style="color: red; margin-left: 10px;">${msg}</p>
					</c:if>
					<p>
						<label>用&nbsp;户&nbsp;名:</label>
						<input type="text" name="username" style="width: 150px;" class="validate[required] login_input" id="username" value="${username }"/>
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
						<input type="password" name="password" style="width: 150px;" class="validate[required] login_input" id="password"/>
					</p>
					<p>
						<label>登陆方式:</label>
						<select name="type" id="type" onchange="writeCookie('CZInvesReview_login_type',this.value);" class="validate[required] login_input">
						<option value="0">前台</option>
						<option value="1">后台</option>
						</select>
					</p>
					<p>
						<label>验&nbsp;证&nbsp;码:</label>
						<input type="text" id="captcha_key" style="width: 70px;float:left;" name="captcha_key" class="login_input validate[required,maxSize[6]]" size="6" />
						<span><img src="${contextPath}/Captcha.jpg" alt="点击刷新验证码" width="75" height="24" id="captcha"/></span>
					</p>
					<div class="login_bar" style="disply:block;float:left;">
						<input class="sub" type="submit" value=""/>
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${contextPath}/styles/management/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
				</ul>

				<div class="login_inner">
				</div>
			</div>
		</div>
		<div id="login_footer">
					<!--[if lte IE 8]>
					<br/>
					<span>
					系统检测到您正在使用ie8以下版本的浏览器，将会影响系统的正常使用。<br/><br/>
					您可以：
							1、升级IE到最新版本；
							2、如果您使用的是多核浏览器，可以尝试切换浏览器核心至“极速”模式。
							<br/><br/>
							</span>
							推荐浏览器：<a href="http://softdownload.hao123.com/hao123-soft-online-bcs/soft/C/2013-10-17_ChromeStandaloneSetup.exe">谷歌浏览器下载</a>&nbsp;&nbsp;&nbsp;<a href="http://down.360safe.com/cse/360cse_7.3.0.161.exe">360浏览器下载</a>
					<![endif]-->
		</div>
	</div>
</body>
</html>