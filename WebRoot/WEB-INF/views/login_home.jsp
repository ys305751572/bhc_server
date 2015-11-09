<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱奥乐云健康管理平台</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/login/index.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/login/skrollr.css">
<script type="text/javascript" src="${contextPath}/resources/aol/login/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/aol/login/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/aol/login/jquery.inview.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/aol/login/index.js"></script>
<link rel="shortcut icon" href="">

<style type="text/css">
a{
	text-decoration:none;
}
.header{
	width:100%;
	min-width:1200px;
	height: 80px;
	background-color: #FFFFFF;
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
</style>
<script type="text/javascript">

</script>
</head>

<body style="height: 3120px; overflow:visible; font-size: 12px;font-family: verdana; background-color: #FFFFFF;">
        <div class="right" style="position:fixed; z-index:10000;margin-top: -50px;right: 25px;top: 50%;">
            <ul id="page-nav" style="display: block;">
                <li id="goto_sy" class="nav-li hov" title="首页" style="cursor: pointer;"></li>
                <li id="goto_xty" class="nav-li" title="智能血糖仪" style="cursor: pointer;"></li>
                <li id="goto_xyj" class="nav-li" title="智能血压计" style="cursor: pointer;"></li>
                <li id="goto_ewq" class="nav-li" title="智能耳温枪" style="cursor: pointer;"></li>
            </ul>
        </div>
        <div class="section" id="flex_slider" style="z-index:1006;width: 1365px; margin: 0 auto; height: 768px; min-height: 768px; background:url('${contextPath}/resources/aol/login/image_1_1.png');background-repeat: no-repeat; background-attachment: fixed; background-position: center center; bottom: 0px; display: block;">
            <div class="header" style="opacity: 1; bottom: 0px; display: block;">
				<div style="width: 1300px;height: 80px;margin: 0 auto;background-color: #FFFFFF;">
					<div style="float:left; width: 224px; height: 56px; margin: 12px 35px; background-image: url(${contextPath}/resources/aol/images/logo.png);background-position: 0px 0px; background-repeat: no-repeat no-repeat;"></div>
					<div class="topTitle"><a href="${contextPath}/login/loginHome" style="color: #666;">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${contextPath}/login" style="color: #666;">登录</a></div>
				</div>
			</div>
            
            <div style="width: 1300px; height:684px; margin: 0 auto;">
               
            </div>
        </div>
        <div class="section" style="z-index:1005; width: 1366px; height: 768px; min-height: 768px; margin: 0 auto;background-image: url('${contextPath}/resources/aol/login/image_2_1.png');" id="scene_xty">
            <div style="width: 1300px; height: 768px; margin: 0 auto; overflow: hidden;">
                
            </div>
        </div>
        <div class="section" style="z-index:1004; width: 1366px; height: 768px; min-height: 768px; margin: 0 auto;" id="scene_xyj">
            <div style="width: 1300px; height: 768px; margin: 0 auto; overflow: hidden;">
               
            </div>
        </div>
        <div class="" style="z-index:1004; width: 1366px; height: 768px; min-height: 768px; margin: 0 auto; background-repeat: no-repeat; background-position: center center; background-attachment: fixed;" id="scene_ewq">
            <div style="width: 1300px; height: 768px; margin: 0 auto; overflow: hidden;">
               
            </div>
        </div>
<script type="text/javascript" src="${contextPath}/resources/aol/login/skrollr.min.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="${contextPath}/resources/aol/login/skrollr.ie.min.js"></script>
<![endif]-->
<script type="text/javascript">
	$(document).ready(function() {
		var s = skrollr.init({
			render: function(data) {
				var n= Math.floor(data.curTop / 768);
				$("#page-nav li").eq(n).addClass("hov").siblings().removeClass().addClass("nav-li");
				if(n==2){
					$("#scene_xyj").addClass("scene_xyj");
				}
				if(n==3){
					$("#scene_ewq").addClass("scene_ewq");
				}
			}
		});
		$("img.lazy").lazyload({
			threshhold:500,
			effect: "fadeIn"
		});
	});
</script>
</body>
</html>