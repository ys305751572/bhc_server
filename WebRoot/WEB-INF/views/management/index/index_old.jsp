<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@ include file="/WEB-INF/views/scripts.jsp"%>
	</head>
	<body>

<!--Header-part-->
<div id="header">
  <h1><a href="">相机终端采集平台</a></h1>
</div>
<!--close-Header-part--> 


<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">欢迎您!admin</span><b class="caret"></b></a>
    </li>
    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" href="page15.html" target="mainIFrame" ><i class="icon-plus"></i> 短消息</a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="page13.html" href="#"><i class="icon-envelope"></i> 平台日志</a></li>
        <li class="divider"></li>
        <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> 异常</a></li>

      </ul>
    </li>
    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
  </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch-->
<div id="search">
  <input type="text" placeholder="查询..."/>
  <button type="submit" class="tip-bottom" title="Search" onclick="search();"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->
<!--sidebar-menu-->
<div id="sidebar"><a href="page1.html" class="visible-phone"><i class="icon icon-home"></i><span>首页</span></a>
  <ul>
		  <li class="active" id="menu1" onclick="changeMenu(this.id,'首页')"><a href="${contextPath}/management/manage/page1Index" target="mainIFrame"><i class="icon icon-home"></i> <span>首页</span></a> </li>
		  <li  id="menu7" onclick="changeMenu(this.id,'过车图片综合查询')"><a href="page6.html" target="mainIFrame"><i class="icon icon-adjust"></i><span>过车图片综合查询</span></a></li>
		  <li  id="menu9" onclick="changeMenu(this.id,'在线路口')"><a href="page17.html" target="mainIFrame"><i class="icon icon-asterisk"></i><span>在线路口</span></a></li>
		  <li  id="menu10" onclick="changeMenu(this.id,'运行状态')"><a href="page9.html" target="mainIFrame"><i class="icon icon-barcode"></i><span>运行状态</span></a></li>
		  <li  id="menu11" onclick="changeMenu(this.id,'路口实时查询')"><a href="page10.html" target="mainIFrame"><i class="icon icon-beaker"></i><span>路口实时查询</span></a></li>
		  <li  id="menu12" onclick="changeMenu(this.id,'平台终端')"><a href="page11.html" target="mainIFrame"><i class="icon icon-bell"></i><span>平台终端</span></a></li>
		  <li  id="menu13" onclick="changeMenu(this.id,'统计分析')"><a href="page12.html" target="mainIFrame"><i class="icon icon-book"></i><span>统计分析</span></a></li>
		  <li  id="menu16" onclick="changeMenu(this.id,'违章查询')"><a href="page16.html" target="mainIFrame"><i class="icon icon-eye-open"></i><span>违章查询</span></a></li>
		  <li class="submenu"> <a href="javascript:void(0)"><i class="icon icon-th-list"></i> <span>应用管理</span> <span class="label label-important">5</span></a>
			  <ul>
					<li id="menu2" onclick="changeMenu(this.id,'路口信息管理')"> <a href="${contextPath}/management/manage/page2Index" target="mainIFrame"><i class="icon icon-signal"></i> <span>路口信息管理</span></a> </li>
					<li id="menu3" onclick="changeMenu(this.id,'设备信息管理')"> <a href="page3.html" target="mainIFrame"><i class="icon icon-inbox"></i> <span>设备信息管理</span></a> </li>
					<li id="menu4" onclick="changeMenu(this.id,'平台终端管理')"><a href="page4.html" target="mainIFrame"><i class="icon icon-th"></i> <span>平台终端管理</span></a></li>
					<li id="menu5" onclick="changeMenu(this.id,'基础信息管理')"><a href="page5.html" target="mainIFrame"><i class="icon icon-fullscreen"></i> <span>基础信息管理</span></a></li>
					<li id="menu8" onclick="changeMenu(this.id,'队列信息查询')"><a href="page7.html" target="mainIFrame"><i class="icon icon-briefcase"></i>队列信息查询</a></li>
			  </ul>
		  </li>
		<li class="submenu"> <a href="#"><i class="icon icon-tint"></i> <span>日常管理</span> <span class="label label-important">2</span></a>
			  <ul>
				<li id="menu14" onclick="changeMenu(this.id,'日志管理')"><a href="page13.html" target="mainIFrame"><i class="icon icon-bookmark"></i><span>日志管理</span></a></li>
				<li id="menu15" onclick="changeMenu(this.id,'硬盘管理')"><a href="page14.html" target="mainIFrame"><i class="icon icon-bookmark-empty"></i><span>硬盘管理</span></a></li>
			  </ul>
		</li>
		<li><a href=""><i class="icon icon-info-sign"></i> <span>系统管理</span></a></li>
	<!--
	<li class="content"> <span>硬盘使用情况</span>
      <div class="progress progress-mini progress-danger active progress-striped">
        <div style="width: 77%;" class="bar"></div>
      </div>
      <span class="percent">77%</span>
      <div class="stat">21419.94 / 14000 MB</div>
    </li>
    <li class="content"> <span>内存使用情况</span>
      <div class="progress progress-mini active progress-striped">
        <div style="width: 87%;" class="bar"></div>
      </div>
      <span class="percent">87%</span>
      <div class="stat">604.44 / 4000 MB</div>
    </li>
	-->
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="index-new.html" title="返回首页" class="tip-bottom"><i class="icon-home"></i> <span id="indexTitle">首页</span></a></div>
  </div>
<!--End-breadcrumbs-->

	<iframe name="mainIFrame" id="mainIFrame" src="${contextPath}/management/manage/page1Index"
								frameborder="0" scrolling="no"
								style="width: 100%; height: 400px;"
								allowtransparency="true"></iframe>
</div>
<!--end-main-container-part-->

<script type="text/javascript">

	$("#mainIFrame").load(function() {
		var height = $(this).contents().find("#content1").height() + 30;
		//这样给以一个最小高度 
			$(this).height(height < 400 ? 400 : height);
			document.getElementById("mainIFrame").contentWindow.scroll(0);
		});
		
		function search(){
			$('#mainIFrame').attr("src","page6.html");
		}
		
		//变更菜单
		function changeMenu(id,title) {
		//删除之前节点的样式
		$("#sidebar li").each(function(){
		if($(this).hasClass("active"))
			$(this).removeClass("active");
	});
		//添加当前节点的样式
		$('#'+id).addClass("active");
		$('#indexTitle').html(title);
		
	}
</script>

	</body>
</html>


