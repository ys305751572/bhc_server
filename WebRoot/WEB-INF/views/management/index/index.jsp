<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head> 
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link href="${contextPath}/resources/aol/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/aol/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/aol/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/resources/aol/css/sb-admin-2.css" rel="stylesheet">
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
    
    <style type="text/css">
		
    </style>
</head>
<body>
<div id="wrapper" style="min-width: 1330px;">

    <!-- Navigation -->
    <div class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0; background-color: #007cc3;">
        <div class="navbar-header" style="float:left; width:400px; height:86px;">
            <a class="navbar-brand" style="height:86px;" href="${contextPath}/management/index" style="">&nbsp;</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right" style="float: right!important; height:86px;">
            <li class="dropdown" style="height:86px;">
                <a style="height:86px; padding: 15px 5px; font-style:italic; color: #FFFFFF; background-color: #007cc3;" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <span style="font-size: 14px;">
                    <c:if test="${loginUser.userType == 0}">
	                    ${loginUser.name}  欢迎您！
                    </c:if>
                    <c:if test="${loginUser.userType != 0}">
                    	<c:if test="${empty loginUser.organiseShortname}">
                    		${loginUser.organiseName}管理员  欢迎您！
                   		</c:if>
                   		<c:if test="${!empty loginUser.organiseShortname}">
                    		${loginUser.organiseShortname}管理员  欢迎您！
                   		</c:if>
                   	</c:if>            
                  </span>
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
            
            <li class="dropdown" style="height:86px;">
                <a class="dropdown-toggle" style="padding: 15px 5px; color: #FFFFFF; background-color: #007cc3;" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i> (${msgCount}) <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user" style="width: 400px;">
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
	                        <a href="${contextPath}/management/messages/viewMsg?messagesId=${mes.messagesId}" target="mainIFrame" style="font-size: 12px;">
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
	                        <a class="text-center" href="${contextPath}/management/messages/inboxlist" target="mainIFrame" style="font-size: 12px;">
	                            <strong>更多...</strong>
	                            <i class="fa fa-angle-right"></i>
	                        </a>
	                    </li>
                    </c:if>
                </ul>
            </li>
            <!-- /.dropdown -->
           
            <li class="dropdown" style="height:86px;">
                <a class="dropdown-toggle" style="padding: 15px 5px; color: #FFFFFF; background-color: #007cc3;" data-toggle="dropdown" href="#">
                    <i class="fa fa-gear fa-fw"></i>
                </a>
                <ul class="dropdown-menu dropdown-user" style="width: 100px; min-width: 60px;">
                    <li><a href="${contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                    </li>
                </ul>
            </li>
            <!-- /.dropdown -->
            
        </ul>
        <!-- /.navbar-top-links -->
    </div>
	
	<div style="width: auto; height: auto;">
		<div class="navbar-default sidebar" style="float: left; width: 250px; margin-top: 0px;" role="navigation">
            <div id="sidebar" name="sidebar" class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li id="menu0" onclick="changeMenu(this.id,'首页')">
                        <c:choose>
							 <c:when test="${loginUser.userType == 0}">
					         	<a class="first-active" href="${contextPath}/management/home/homePage" target="mainIFrame"><span class="aol aol-homePage"></span> 首页</a>
					         </c:when>  
							 <c:otherwise>  
							 	<a class="first-active" href="${contextPath}/management/home/orgHomePage" target="mainIFrame"><span class="aol aol-homePage"></span> 首页</a>
							 </c:otherwise>
					     </c:choose> 
                    </li>
			                    
			<c:choose>
				<c:when test="${loginUser.userType == 0}">
					<%-- 普通用户权限 --%>
					<li id="menu1">
                        <a href="#" name="firstMenu"><span class="aol aol-healthy"></span> 健康监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'健康监护&nbsp;>&nbsp;血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'健康监护&nbsp;>&nbsp;血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'健康监护&nbsp;>&nbsp;体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#" name="firstMenu"><span class="aol aol-safety"></span> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu3">
                        <a href="#" name="firstMenu"><span class="aol aol-user"></span> 个人设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu3_1" onclick="changeMenu(this.id,'个人设置&nbsp;>&nbsp;基本信息')">
                                <a href="${contextPath}/management/aoluser/viewOwnInfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu3_2" onclick="changeMenu(this.id,'个人设置&nbsp;>&nbsp;紧急联系人设置')">
                                <a href="${contextPath}/management/urgentperson/userurgentlist" target="mainIFrame">紧急联系人设置</a>
                            </li>
                            <li id="menu3_3" onclick="changeMenu(this.id,'个人设置&nbsp;>&nbsp;监护人设置')">
                                <a href="${contextPath}/management/urgentperson/guardianlist" target="mainIFrame">监护人设置</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#" name="firstMenu"><span class="aol aol-news"></span> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_2" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                            <li id="menu4_5" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;在线留言')">
                                <a href="${contextPath}/management/leavemsg/leavemessage" target="mainIFrame">在线留言</a>
                            </li>
                        </ul>
                    </li>
				</c:when>  
				<c:when test="${loginUser.userType == 1}">
					<%-- 代理商权限 --%>
					<li id="menu1">
                        <a href="#" name="firstMenu"><span class="aol aol-healthy"></span> 测量结果<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                            <li id="menu1_4" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血压预警')">
                                <a href="${contextPath}/management/measure/xylist" target="mainIFrame">血压预警</a>
                            </li>
                            <li id="menu1_5" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血糖预警')">
                                <a href="${contextPath}/management/measure/xtlist" target="mainIFrame">血糖预警</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#" name="firstMenu"><span class="aol aol-safety"></span> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#" name="firstMenu"><span class="aol aol-news"></span> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_1_1" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;写消息')">
                                <a href="${contextPath}/management/messages/orgwritemessage" target="mainIFrame">写消息</a>
                            </li>
                            <li id="menu4_1_2" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;app消息推送')">
                                <a href="${contextPath}/management/messages/orgappmessageset" target="mainIFrame">app消息推送</a>
                            </li>
                            <li id="menu4_2_1" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_2_2" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;app消息查看')">
                                <a href="${contextPath}/management/messages/appInboxlist" target="mainIFrame">app消息查看</a>
                            </li>
                            <li id="menu4_3" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;草稿箱')">
                                <a href="${contextPath}/management/messages/draftboxlist" target="mainIFrame">草稿箱</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                            <li id="menu4_5" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;在线留言')">
                                <a href="${contextPath}/management/leavemsg/leavemessage" target="mainIFrame">在线留言</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu5">
                        <a href="#" name="firstMenu"><span class="aol aol-users"></span> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu5_1" onclick="changeMenu(this.id,'用户管理&nbsp;>&nbsp;用户信息查询')">
                                <a href="${contextPath}/management/aoluser/userslist" target="mainIFrame">用户信息查询</a>
                            </li>
                        </ul>
                    </li>
                    
                    <li id="menu6">
                        <a href="#" name="firstMenu"><span class="aol aol-device"></span> 设备管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu6_3" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;机器码查询')">
                                <a href="${contextPath}/management/devices/gotoqueryDevice" target="mainIFrame">机器码查询</a>
                            </li>
                            <li id="menu6_4" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;测量数据查询')">
                                <a href="${contextPath}/management/devices/querymeasurelist" target="mainIFrame">测量数据查询</a>
                            </li>
                        </ul>
                    </li>
                    
					<li id="menu8">
                        <a href="#" name="firstMenu"><span class="aol aol-agent"></span> 系统管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu8_1" onclick="changeMenu(this.id,'系统管理&nbsp;>&nbsp;基本信息')">
                                <a href="${contextPath}/management/organise/viewowninfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu7_2" onclick="changeMenu(this.id,'代理商管理&nbsp;>&nbsp;图片广告管理')">
                                <a href="${contextPath}/management/imageads/adslist" target="mainIFrame">图片广告管理</a>
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
                        <a href="#" name="firstMenu"><span class="aol aol-healthy"></span> 测量结果<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu1_1" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血糖信息')">
                                <a href="${contextPath}/management/measure/xtReport" target="mainIFrame">血糖信息</a>
                            </li>
                            <li id="menu1_2" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血压信息')">
                                <a href="${contextPath}/management/measure/xyReport" target="mainIFrame">血压信息</a>
                            </li>
                            <li id="menu1_3" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;体温信息')">
                                <a href="${contextPath}/management/measure/twReport" target="mainIFrame">体温信息</a>
                            </li>
                            <li id="menu1_4" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血压预警')">
                                <a href="${contextPath}/management/measure/xylist" target="mainIFrame">血压预警</a>
                            </li>
                            <li id="menu1_5" onclick="changeMenu(this.id,'测量结果&nbsp;>&nbsp;血糖预警')">
                                <a href="${contextPath}/management/measure/xtlist" target="mainIFrame">血糖预警</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu2">
                        <a href="#" name="firstMenu"><span class="aol aol-safety"></span> 安全监护<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu2_1" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;位置信息查询')">
                                <a href="${contextPath}/management/position/wzxxlist" target="mainIFrame">位置信息查询</a>
                            </li>
                            <li id="menu2_2" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;轨迹信息查询')">
                                <a href="${contextPath}/management/position/wzgjxxlist" target="mainIFrame">轨迹信息查询</a>
                            </li>
                            <li id="menu2_3" onclick="changeMenu(this.id,'安全监护&nbsp;>&nbsp;呼救监控')">
                                <a href="${contextPath}/management/position/hjjklist" target="mainIFrame">呼救监控</a>
                            </li>
                        </ul>
                    </li>

					<li id="menu3">
                        <a href="#" name="firstMenu"><span class="aol aol-user"></span> 个人设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu3_2" onclick="changeMenu(this.id,'个人设置&nbsp;>&nbsp;紧急联系人设置')">
                                <a href="${contextPath}/management/urgentperson/orguserurgentlist" target="mainIFrame">紧急联系人设置</a>
                            </li>
                            <li id="menu3_3" onclick="changeMenu(this.id,'个人设置&nbsp;>&nbsp;监护人设置')">
                                <a href="${contextPath}/management/urgentperson/orgguardianlist" target="mainIFrame">监护人设置</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu4">
                        <a href="#" name="firstMenu"><span class="aol aol-news"></span> 消息管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu4_1" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;写消息')">
                                <a href="${contextPath}/management/messages/writemessage" target="mainIFrame">写消息</a>
                            </li>
                            <li id="menu4_2" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;消息查看')">
                                <a href="${contextPath}/management/messages/inboxlist" target="mainIFrame">消息查看</a>
                            </li>
                            <li id="menu4_1_3" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;app消息推送')">
                                <a href="${contextPath}/management/messages/orgappmessageset" target="mainIFrame">app消息推送</a>
                            </li>
                            <li id="menu4_2_3" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;app消息查看')">
                                <a href="${contextPath}/management/messages/appInboxlist" target="mainIFrame">app消息查看</a>
                            </li>
                            <li id="menu4_3" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;草稿箱')">
                                <a href="${contextPath}/management/messages/draftboxlist" target="mainIFrame">草稿箱</a>
                            </li>
                            <li id="menu4_4" onclick="changeMenu(this.id,'消息管理&nbsp;>&nbsp;已发送')">
                                <a href="${contextPath}/management/messages/sendedlist" target="mainIFrame">已发送</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu5">
                        <a href="#" name="firstMenu"><span class="aol aol-users"></span> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu5_1" onclick="changeMenu(this.id,'用户管理&nbsp;>&nbsp;用户信息查询')">
                                <a href="${contextPath}/management/aoluser/userslist" target="mainIFrame">用户信息查询</a>
                            </li>
                            <li id="menu5_2" onclick="changeMenu(this.id,'用户管理&nbsp;>&nbsp;用户转移')">
                                <a href="${contextPath}/management/aoluser/usertransfer" target="mainIFrame">用户转移</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu6">
                        <a href="#" name="firstMenu"><span class="aol aol-users"></span> 医师管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu6_1" onclick="changeMenu(this.id,'医师管理&nbsp;>&nbsp;医师信息查询')">
                                <a href="${contextPath}/management/doctor/paegList" target="mainIFrame">医师信息查询</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu7">
                        <a href="#" name="firstMenu"><span class="aol aol-device"></span> 设备管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu7_1" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;设备信息查询')">
                                <a href="${contextPath}/management/devices/deviceslist" target="mainIFrame">设备信息查询</a>
                            </li>
                            <li id="menu7_2" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;设备批量导入')">
                                <a href="${contextPath}/management/devices/uploaddevice" target="mainIFrame">设备批量导入</a>
                            </li>
                            <li id="menu7_3" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;机器码查询')">
                                <a href="${contextPath}/management/devices/gotoqueryDevice" target="mainIFrame">机器码查询</a>
                            </li>
                            <li id="menu7_4" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;测量数据查询')">
                                <a href="${contextPath}/management/devices/querymeasurelist" target="mainIFrame">测量数据查询</a>
                            </li>
                            <li id="menu7_5" onclick="changeMenu(this.id,'设备管理&nbsp;>&nbsp;设备转移')">
                                <a href="${contextPath}/management/devices/gotoDevTransfer" target="mainIFrame">设备转移</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu8">
                        <a href="#" name="firstMenu"><span class="aol aol-agent"></span> 代理商管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu8_1" onclick="changeMenu(this.id,'代理商管理&nbsp;>&nbsp;代理商管理')">
                                <a href="${contextPath}/management/organise/organiseslist" target="mainIFrame">代理商管理</a>
                            </li>
                            <li id="menu8_2" onclick="changeMenu(this.id,'代理商管理&nbsp;>&nbsp;图片广告管理')">
                                <a href="${contextPath}/management/imageads/adslist" target="mainIFrame">图片广告管理</a>
                            </li>
                        </ul>
                    </li>
					
					<li id="menu9">
                        <a href="#" name="firstMenu"><span class="aol aol-system"></span> 系统管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu9_1" onclick="changeMenu(this.id,'系统管理&nbsp;>&nbsp;基本信息')">
                                <a href="${contextPath}/management/organise/viewowninfo" target="mainIFrame">基本信息</a>
                            </li>
                            <li id="menu9_2" onclick="changeMenu(this.id,'系统管理&nbsp;>&nbsp;日志信息查询')">
                                <a href="${contextPath}/management/loginfo/logslist" target="mainIFrame">日志信息查询</a>
                            </li>
                        </ul>
                    </li>
                    
                    <li id="menu10">
                        <a href="#" name="firstMenu"><span class="aol aol-system"></span> 检测工具题库管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu10_1" onclick="changeMenu(this.id,'系统管理&nbsp;>&nbsp;检测工具题库管理')">
                                <a href="${contextPath}/management/question/pageList" target="mainIFrame">检测工具题库信息查询</a>
                            </li>
                        </ul>
                    </li>
                     <li id="menu11">
                        <a href="#" name="firstMenu"><span class="aol aol-system"></span> 病理讲座管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li id="menu10_1" onclick="changeMenu(this.id,'系统管理&nbsp;>&nbsp;病理讲座管理')">
                                <a href="${contextPath}/management/pg/pageList" target="mainIFrame">病理讲座信息查询</a>
                            </li>
                        </ul>
                    </li>
				</c:when>  
				<c:otherwise>  
				
				</c:otherwise>
			</c:choose> 
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
		</div>
	
		<div id="page-wrapper"  style="float: left; width: 80%; min-width: 1065px; margin-top: 0px;">
			<div class="row" style="min-width: 1060px;">
		         <div class="page-header" style="margin: 20px 0 5px;">
		         	<a href="${contextPath}/management/index" title="返回首页"><span class="aol" style="background:url('${contextPath}/resources/aol/images/index/i_1.png') no-repeat center center;"></span></a>&nbsp;<span id="indexTitle">首页</span>
		         </div>
		         
		         <c:choose>
					 <c:when test="${loginUser.userType == 0}">
					 	<iframe name="mainIFrame" id="mainIFrame" src="${contextPath}/management/home/homePage" frameborder="0" scrolling="no" style="width: 100%; height: 400px;" allowtransparency="true"></iframe>
			         </c:when>  
					 <c:otherwise>  
					 	<iframe name="mainIFrame" id="mainIFrame" src="${contextPath}/management/home/orgHomePage" frameborder="0" scrolling="auto" style="width: 100%; height: 400px;" allowtransparency="true"></iframe>
					 </c:otherwise>
			     </c:choose> 
		         
	        </div>
	    </div>
    
	</div>
    
</div>
<!-- /#wrapper -->

<script type="text/javascript">

	$("#mainIFrame").load(function(){ 
		var height = $(this).contents().height() + 40; 
		//这样给以一个最小高度 
		$(this).height( height < 700 ? 700 : height ); 
		document.getElementById("mainIFrame").contentWindow.scroll(0, 0);
	}); 
	
	function resetIframeHeight(height){
		height = height + 100;
		var iframeHeight = $('#mainIFrame').contents().height() + 40; 
		//这样给以一个最小高度 
		$('#mainIFrame').height( height < 700 ? 700 : height ); 
	}
	
	function search(){
		$('#mainIFrame').attr("src","${contextPath}/management/home/buildPage");
	}
		
	//变更菜单
	function changeMenu(id,title) {
		//删除之前节点的样式
		$("#sidebar li").each(function(){
			if($(this).hasClass("active"))
				$(this).removeClass("active");
			
		});
		$("#sidebar li a").each(function(){
			if($(this).hasClass("active"))
				$(this).removeClass("active");

			if($(this).hasClass("first-active"))
				$(this).removeClass("first-active");
		});
		
		//添加当前节点的样式
		var __index = id.indexOf("_");
		if(__index == -1){
			$('#'+id + ' a').addClass("first-active");
		} else {
			var ids = id.split('_');
			$('#'+id + ' a').addClass("active");
			$('#'+ids[0] + ' [name=firstMenu]').addClass("first-active");
		}
		
		$('#indexTitle').html(title);
	
	}
</script>

	</body>
</html>


