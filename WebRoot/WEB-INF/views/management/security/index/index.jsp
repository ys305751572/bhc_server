<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>后台管理</title>
<link href="${contextPath}/styles/management/themes/silver/style.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/styles/management/themes/css/core.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/styles/management/themes/css/print.css"
	rel="stylesheet" type="text/css" media="print" />
<link href="${contextPath}/styles/management/themes/css/custom.css"
	rel="stylesheet" type="text/css" media="screen" />
<%-- 
<link href="${contextPath}/styles/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
--%>
<!--[if IE]>
<link href="${contextPath}/styles/management/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->


<style>
.signout{
	position:absolute; 
	bottom:1px; 
	left:0px; 
	width:100%;
}
</style>

<script src="${contextPath}/styles/management/js/speedup.js"
	type="text/javascript"></script>
<script src="${contextPath}/styles/management/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<%-- 
<script src="${contextPath}/styles/management/js/jquery.cookie.js" type="text/javascript"></script>
--%>
<script src="${contextPath}/styles/management/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="${contextPath}/styles/management/js/jquery.bgiframe.js"
	type="text/javascript"></script>
<%-- 
<script src="${contextPath}/styles/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${contextPath}/styles/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>
--%>
<script src="${contextPath}/styles/management/js/dwz.min.js"
	type="text/javascript"></script>
<script src="${contextPath}/styles/management/js/dwz.regional.zh.js"
	type="text/javascript"></script>
<!-- 自定义JS -->
<script src="${contextPath}/styles/management/js/customer.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		DWZ.init("${contextPath}/styles/management/dwz.frag.xml", {
			loginUrl : "${contextPath}/login/timeout",
			loginTitle : "登录", // 弹出登录对话框
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "${contextPath}/styles/management/themes"
				});
			}
		});
	});
</script>
</head>
<body scroll="no">
	<div id="layout">



		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>菜单</h2>
					<div>collapse</div>
				</div>
				<div class="accordion" fillSpace="sideBar">
					<c:forEach var="level1" items="${menuModule.children }">
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>${level1.name }
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder expand">
								<c:forEach var="level2" items="${level1.children }">
									<li><a href="${contextPath}${level2.url}" target="navTab"
										rel="moduleListNav_${level2.id }">${level2.name }</a> <gcs:menuAccordion
											child="${level2 }" target="navTab"
											urlPrefix="${contextPath }" /></li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main">
							    <a href="javascript:void(0)"><span><span class="home_icon">主页</span></span></a>
							</li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="${contextPath}/logout">退出</a>
					</li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="right">
								<p>
									<fmt:formatDate value="<%=new Date() %>"
										pattern="yyyy-MM-dd EEEE" />
								</p>
							</div>
							<p>
								<span>欢迎, ${login_user.realname } .</span>
							</p>
						</div>
						<div class="pageFormContent" layouth="80">
							<fieldset>
								<legend>基本信息</legend>
								<dl>
									<dt>登录名称：</dt>
									<dd>
										<span class="unit">${login_user.username }</span>
									</dd>
								</dl>
								<dl>
									<dt>真实名字：</dt>
									<dd>
										<span class="unit">${login_user.realname }</span>
									</dd>
								</dl>
								<dl>
									<dt>电话：</dt>
									<dd>
										<span class="unit">${login_user.phone }</span>
									</dd>
								</dl>
								<dl>
									<dt>E-Mail：</dt>
									<dd>
										<span class="unit">${login_user.email }</span>
									</dd>
								</dl>
								<dl>
									<dt>创建时间：</dt>
									<dd>
										<span class="unit"><fmt:formatDate
												value="${login_user.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</span>
									</dd>
								</dl>
								<dl>
									<dt>可用状态：</dt>
									<dd>
										<span class="unit">${(login_user.status == "enabled")?
											"可用":"不可用"}</span>
									</dd>
								</dl>
								<dl>
									<dt>所属机构：</dt>
									<dd>
										<span class="unit">${login_user.organization.name }</span>
									</dd>
								</dl>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script>
	window.location.href
	</script>
	</div>
	<div id="back_home" class="signout" >
    	<a class="btn btn-info">
			<button onclick="window.location.href='${contextPath}/logout'">&nbsp;退&nbsp;&nbsp;出&nbsp;</button>
		</a>
	</div>
</body>
</html>