<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head> 
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link href="${contextPath}/resources/aol/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/aol/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/aol/css/sb-admin-2.css" rel="stylesheet">
    <link href="${contextPath}/resources/aol/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="${contextPath}/resources/aol/js/jquery-1.11.0.js"></script>

  <!-- The HTML5 shim, for IE6-8 support of HTML5 elements --> 
  <!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]--> 
	<script type="text/javascript">
		if(typeof JSON == 'undefined'){
			$('head').append($("<script type='text/javascript' src='${contextPath}/resources/js/json2.js'>"));
		}
	</script>

    <script src="${contextPath}/resources/aol/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/aol/js/plugins/metisMenu/metisMenu.min.js"></script>
    <script src="${contextPath}/resources/aol/js/sb-admin-2.js"></script>
</head>
<body>
<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}/management/index">爱奥乐健康云平台</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" style="padding: 15px 5px; font-style:italic;" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> ${loginUser.name} 欢迎您！
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li>
                    	<c:if test="${loginUser.userType == 0}">
	                    	<a href="${contextPath}/management/aoluser/viewOwnInfo" target="mainIFrame"><i class="fa fa-user fa-fw"></i> 基本信息</a>
                    	</c:if>
                    	<c:if test="${loginUser.userType != 0}">
	                    	<a href="${contextPath}/management/organise/viewowninfo" target="mainIFrame"><i class="fa fa-user fa-fw"></i> 基本信息</a>
                    	</c:if>
                    </li>
                </ul>
            </li>
            <!-- /.dropdown -->
            
            <li class="dropdown">
                <a class="dropdown-toggle" style="padding: 15px 5px;" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i> (${msgCount}) <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages" style="width: 400px;">
                    <c:if test="${empty msgsList}">
                    	<li>
                            <div style="font-size: 8px;">
                                <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;没有收到最新消息！</strong>
                            </div>
	                    </li>
                    </c:if>
                    <c:if test="${!empty msgsList}">
                    <c:forEach var="mes" items="${msgsList}" >
	                    <li>
	                        <a href="${contextPath}/management/messages/viewMsg?messagesId=${mes.messagesId}" target="mainIFrame" style="font-size: 8px;">
	                            <div>
	                                <strong>
										<c:choose>  
										    <c:when test="${fn:length(mes.messagesTitle) > 15}">
										    	${fn:substring(mes.messagesTitle, 0, 15)}......
										    </c:when>  
										    <c:otherwise>  
										    	${mes.messagesTitle}
										    </c:otherwise>
										</c:choose> 
	                                </strong>
	                                <span class="pull-right text-muted">
	                                    <em>${mes.messagesTime}</em>
	                                </span>
	                            </div>
	                        </a>
	                    </li>
                    </c:forEach>
	                    <li class="divider"></li>                   
	                    <li>
	                        <a class="text-center" href="${contextPath}/management/messages/inboxlist" target="mainIFrame" style="font-size: 8px;">
	                            <strong>更多...</strong>
	                            <i class="fa fa-angle-right"></i>
	                        </a>
	                    </li>
                    </c:if>
                </ul>
            </li>
            <!-- /.dropdown -->
           
            <li class="dropdown">
                <a class="dropdown-toggle" style="padding: 15px 5px;" data-toggle="dropdown" href="#">
                    <i class="fa fa-gear fa-fw"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="${contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                    </li>
                </ul>
            </li>
            <!-- /.dropdown -->
            
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div id="sidebar" class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    
                    <%-- 查询暂时屏蔽
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="查询...">
                            <span class="input-group-btn">
                            <button class="btn btn-default" type="button" title="Search" onclick="search();">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                        </div>
                    </li>
                    --%>
                     
                    <li id="menu0" onclick="changeMenu(this.id,'首页')">
                        <a class="active" href="${contextPath}/management/home/homePage" target="mainIFrame"><i class="fa fa-dashboard fa-fw"></i> 首页</a>
                    </li>
			                    
			<c:choose>
				<c:when test="${loginUser.userType == 0}">
					<%-- 普通用户权限 --%>
					<li id="menu1">
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 健康监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'健康监护 / 血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'健康监护 / 血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'健康监护 / 体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#"><i class="fa fa-globe fa-fw"></i> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护 / 位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护 / 轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护 / 呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu3">
                        <a href="#"><i class="fa fa-edit fa-fw"></i> 个人设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu3_1" onclick="changeMenu(this.id,'个人设置 / 基本信息')">
                                <a href="${contextPath}/management/aoluser/viewOwnInfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu3_2" onclick="changeMenu(this.id,'个人设置 / 紧急联系人设置')">
                                <a href="${contextPath}/management/urgentperson/userurgentlist" target="mainIFrame">紧急联系人设置</a>
                            </li>
                            <li id="menu3_3" onclick="changeMenu(this.id,'个人设置 / 监护人设置')">
                                <a href="${contextPath}/management/urgentperson/guardianlist" target="mainIFrame">监护人设置</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#"><i class="fa fa-comments fa-fw"></i> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_2" onclick="changeMenu(this.id,'消息管理 / 消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理 / 已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                            <li id="menu4_5" onclick="changeMenu(this.id,'消息管理 / 在线留言')">
                                <a href="${contextPath}/management/leavemsg/leavemessage" target="mainIFrame">在线留言</a>
                            </li>
                        </ul>
                    </li>
				</c:when>  
				<c:when test="${loginUser.userType == 1}">
					<%-- 代理商权限 --%>
					<li id="menu1">
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 健康监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'健康监护 / 血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'健康监护 / 血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'健康监护 / 体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                            <li id="menu1_4" onclick="changeMenu(this.id,'健康监护 / 血压预警')">
                                <a href="${contextPath}/management/measure/xylist" target="mainIFrame">血压预警</a>
                            </li>
                            <li id="menu1_5" onclick="changeMenu(this.id,'健康监护 / 血糖预警')">
                                <a href="${contextPath}/management/measure/xtlist" target="mainIFrame">血糖预警</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#"><i class="fa fa-globe fa-fw"></i> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护 / 位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护 / 轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护 / 呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#"><i class="fa fa-comments fa-fw"></i> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_1_1" onclick="changeMenu(this.id,'消息管理 / 写消息')">
                                <a href="${contextPath}/management/messages/orgwritemessage" target="mainIFrame">写消息</a>
                            </li>
                            <li id="menu4_2" onclick="changeMenu(this.id,'消息管理 / 消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_3" onclick="changeMenu(this.id,'消息管理 / 草稿箱')">
                                <a href="${contextPath}/management/messages/draftboxlist" target="mainIFrame">草稿箱</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理 / 已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                            <li id="menu4_5" onclick="changeMenu(this.id,'消息管理 / 在线留言')">
                                <a href="${contextPath}/management/leavemsg/leavemessage" target="mainIFrame">在线留言</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu5">
                        <a href="#"><i class="fa fa-user fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu5_1" onclick="changeMenu(this.id,'用户管理 / 用户信息查询')">
                                <a href="${contextPath}/management/aoluser/userslist" target="mainIFrame">用户信息查询</a>
                            </li>
                            <li id="menu5_2" onclick="changeMenu(this.id,'用户管理 / 机器码查询')">
                                <a href="${contextPath}/management/devices/gotoqueryDevice" target="mainIFrame">机器码查询</a>
                            </li>
                        </ul>
                    </li>
					<li id="menu8">
                        <a href="#"><i class="fa fa-wrench fa-fw"></i> 系统管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu8_1" onclick="changeMenu(this.id,'系统管理 / 基本信息')">
                                <a href="${contextPath}/management/organise/viewowninfo" target="mainIFrame">基本信息</a>
                            </li>
                        </ul>
                    </li>
				</c:when>  
				<c:when test="${loginUser.userType == 2}">
					<%-- 特殊用户权限 --%>
				
				</c:when> 
				<c:when test="${loginUser.userType == 99}">
					<%-- 总公司权限 --%> 
					<li id="menu1">
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 健康监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'健康监护 / 血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'健康监护 / 血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'健康监护 / 体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                            <li id="menu1_4" onclick="changeMenu(this.id,'健康监护 / 血压预警')">
                                <a href="${contextPath}/management/measure/xylist" target="mainIFrame">血压预警</a>
                            </li>
                            <li id="menu1_5" onclick="changeMenu(this.id,'健康监护 / 血糖预警')">
                                <a href="${contextPath}/management/measure/xtlist" target="mainIFrame">血糖预警</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#"><i class="fa fa-globe fa-fw"></i> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护 / 位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护 / 轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护 / 呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu3">
                        <a href="#"><i class="fa fa-edit fa-fw"></i> 个人设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu3_2" onclick="changeMenu(this.id,'个人设置 / 紧急联系人设置')">
                                <a href="${contextPath}/management/urgentperson/userurgentlist" target="mainIFrame">紧急联系人设置</a>
                            </li>
                            <li id="menu3_3" onclick="changeMenu(this.id,'个人设置 / 监护人设置')">
                                <a href="${contextPath}/management/urgentperson/guardianlist" target="mainIFrame">监护人设置</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#"><i class="fa fa-comments fa-fw"></i> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_1" onclick="changeMenu(this.id,'消息管理 / 写消息')">
                                <a href="${contextPath}/management/messages/writemessage" target="mainIFrame">写消息</a>
                            </li>
                            <li id="menu4_2" onclick="changeMenu(this.id,'消息管理 / 消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_3" onclick="changeMenu(this.id,'消息管理 / 草稿箱')">
                                <a href="${contextPath}/management/messages/draftboxlist" target="mainIFrame">草稿箱</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理 / 已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu5">
                        <a href="#"><i class="fa fa-user fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu5_1" onclick="changeMenu(this.id,'用户管理 / 用户信息查询')">
                                <a href="${contextPath}/management/aoluser/userslist" target="mainIFrame">用户信息查询</a>
                            </li>
                            <li id="menu5_2" onclick="changeMenu(this.id,'用户管理 / 机器码查询')">
                                <a href="${contextPath}/management/devices/gotoqueryDevice" target="mainIFrame">机器码查询</a>
                            </li>
                            <li id="menu5_3" onclick="changeMenu(this.id,'用户管理 / 用户转移')">
                                <a href="${contextPath}/management/aoluser/usertransfer" target="mainIFrame">用户转移</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu6">
                        <a href="#"><i class="fa fa-support fa-fw"></i> 设备管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu6_1" onclick="changeMenu(this.id,'设备管理 / 设备信息查询')">
                                <a href="${contextPath}/management/devices/deviceslist" target="mainIFrame">设备信息查询</a>
                            </li>
                            <li id="menu6_2" onclick="changeMenu(this.id,'设备管理 / 设备批量导入')">
                                <a href="${contextPath}/management/devices/uploaddevice" target="mainIFrame">设备批量导入</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu7">
                        <a href="#"><i class="fa fa-tasks fa-fw"></i> 代理商管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu7_1" onclick="changeMenu(this.id,'代理商管理 / 代理商管理')">
                                <a href="${contextPath}/management/organise/organiseslist" target="mainIFrame">代理商管理</a>
                            </li>
                            <li id="menu7_2" onclick="changeMenu(this.id,'代理商管理 / 图片广告管理')">
                                <a href="${contextPath}/management/imageads/adslist" target="mainIFrame">图片广告管理</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu8">
                        <a href="#"><i class="fa fa-wrench fa-fw"></i> 系统管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu8_1" onclick="changeMenu(this.id,'系统管理 / 基本信息')">
                                <a href="${contextPath}/management/organise/viewowninfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu8_2" onclick="changeMenu(this.id,'系统管理 / 日志信息查询')">
                                <a href="${contextPath}/management/home/buildPage" target="mainIFrame">日志信息查询</a>
                            </li>
                        </ul>
                    </li>
				</c:when>  
				<c:otherwise>  
				
				</c:otherwise>
			</c:choose> 
                 <%--  
                    <li id="menu1">
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 健康监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'健康监护 / 血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'健康监护 / 血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'健康监护 / 体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                            <li id="menu1_4" onclick="changeMenu(this.id,'健康监护 / 血压预警')">
                                <a href="${contextPath}/management/measure/xylist" target="mainIFrame">血压预警</a>
                            </li>
                            <li id="menu1_5" onclick="changeMenu(this.id,'健康监护 / 血糖预警')">
                                <a href="${contextPath}/management/measure/xtlist" target="mainIFrame">血糖预警</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#"><i class="fa fa-globe fa-fw"></i> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护 / 位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护 / 轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护 / 呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu3">
                        <a href="#"><i class="fa fa-edit fa-fw"></i> 个人设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu3_1" onclick="changeMenu(this.id,'个人设置 / 基本信息')">
                                <a href="${contextPath}/management/aoluser/viewOwnInfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu3_2" onclick="changeMenu(this.id,'个人设置 / 紧急联系人设置')">
                                <a href="${contextPath}/management/urgentperson/userurgentlist" target="mainIFrame">紧急联系人设置</a>
                            </li>
                            <li id="menu3_3" onclick="changeMenu(this.id,'个人设置 / 监护人设置')">
                                <a href="${contextPath}/management/urgentperson/guardianlist" target="mainIFrame">监护人设置</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#"><i class="fa fa-comments fa-fw"></i> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_1" onclick="changeMenu(this.id,'消息管理 / 写消息')">
                                <a href="${contextPath}/management/messages/writemessage" target="mainIFrame">写消息</a>
                            </li>
                            <li id="menu4_1_1" onclick="changeMenu(this.id,'消息管理 / 写消息')">
                                <a href="${contextPath}/management/messages/orgwritemessage" target="mainIFrame">写消息</a>
                            </li>
                            <li id="menu4_2" onclick="changeMenu(this.id,'消息管理 / 消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_3" onclick="changeMenu(this.id,'消息管理 / 草稿箱')">
                                <a href="${contextPath}/management/messages/draftboxlist" target="mainIFrame">草稿箱</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理 / 已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                            <li id="menu4_5" onclick="changeMenu(this.id,'消息管理 / 在线留言')">
                                <a href="${contextPath}/management/leavemsg/leavemessage" target="mainIFrame">在线留言</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu5">
                        <a href="#"><i class="fa fa-user fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu5_1" onclick="changeMenu(this.id,'用户管理 / 用户信息查询')">
                                <a href="${contextPath}/management/aoluser/userslist" target="mainIFrame">用户信息查询</a>
                            </li>
                            <li id="menu5_2" onclick="changeMenu(this.id,'用户管理 / 机器码查询')">
                                <a href="${contextPath}/management/devices/gotoqueryDevice" target="mainIFrame">机器码查询</a>
                            </li>
                            <li id="menu5_3" onclick="changeMenu(this.id,'用户管理 / 用户转移')">
                                <a href="${contextPath}/management/aoluser/usertransfer" target="mainIFrame">用户转移</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu6">
                        <a href="#"><i class="fa fa-support fa-fw"></i> 设备管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu6_1" onclick="changeMenu(this.id,'设备管理 / 设备信息查询')">
                                <a href="${contextPath}/management/devices/deviceslist" target="mainIFrame">设备信息查询</a>
                            </li>
                            <li id="menu6_2" onclick="changeMenu(this.id,'设备管理 / 设备批量导入')">
                                <a href="${contextPath}/management/devices/uploaddevice" target="mainIFrame">设备批量导入</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu7">
                        <a href="#"><i class="fa fa-tasks fa-fw"></i> 代理商管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu7_1" onclick="changeMenu(this.id,'代理商管理 / 代理商管理')">
                                <a href="${contextPath}/management/organise/organiseslist" target="mainIFrame">代理商管理</a>
                            </li>
                            <li id="menu7_2" onclick="changeMenu(this.id,'代理商管理 / 图片广告管理')">
                                <a href="${contextPath}/management/imageads/adslist" target="mainIFrame">图片广告管理</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu8">
                        <a href="#"><i class="fa fa-wrench fa-fw"></i> 系统管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu8_1" onclick="changeMenu(this.id,'系统管理 / 基本信息')">
                                <a href="${contextPath}/management/organise/viewowninfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu8_2" onclick="changeMenu(this.id,'系统管理 / 日志信息查询')">
                                <a href="${contextPath}/management/home/buildPage" target="mainIFrame">日志信息查询</a>
                            </li>
                        </ul>
                    </li>
                --%>   

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
		<div class="row">
	         <div class="page-header" style="margin: 20px 0 5px;">
	         	<a href="${contextPath}/management/index" title="返回首页"><i class="fa fa-dashboard fa-fw"></i></a>&nbsp;&nbsp;<span id="indexTitle">首页</span>
	         </div>
	         
	         <iframe name="mainIFrame" id="mainIFrame" src="${contextPath}/management/home/homePage" frameborder="0" scrolling="no"
					style="width: 100%; height: 400px;" allowtransparency="true"></iframe>
        </div>
    </div>
    <!-- /#page-wrapper -->
    
</div>
<!-- /#wrapper -->

<script type="text/javascript">

	$("#mainIFrame").load(function(){ 
		var height = $(this).contents().height() + 40; 
		//这样给以一个最小高度 
		$(this).height( height < 400 ? 400 : height ); 
		document.getElementById("mainIFrame").contentWindow.scroll(0);
	}); 
	
	function resetIframeHeight(height){
		height = height + 100;
		var iframeHeight = $('#mainIFrame').contents().height() + 40; 
		//这样给以一个最小高度 
		$('#mainIFrame').height( height < 400 ? 400 : height ); 
	}
	
	function search(){
		$('#mainIFrame').attr("src","${contextPath}/management/home/buildPage");
	}
		
	//变更菜单
	function changeMenu(id,title) {
		//删除之前节点的样式
		$("#sidebar li a").each(function(){
			if($(this).hasClass("active"))
				$(this).removeClass("active");
		});
		//添加当前节点的样式
		$('#'+id + ' a').addClass("active");
		$('#indexTitle').html(title);
	
	}
</script>

	</body>
</html>


