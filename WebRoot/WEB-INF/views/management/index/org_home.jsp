<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<%@ include file="/WEB-INF/views/scripts.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>爱奥乐健康云平台</title>

    <!-- Bootstrap Core CSS -->
    <link href="${contextPath}/resources/orgHomePage/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${contextPath}/resources/orgHomePage/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${contextPath}/resources/orgHomePage/css/plugins/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${contextPath}/resources/orgHomePage/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${contextPath}/resources/orgHomePage/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${contextPath}/resources/orgHomePage/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script type="text/javascript">
		$(document).ready(function(){
			Morris.Bar({
		        element: 'org-homepage-chart',
		        data: [{
		            y: '高压偏高',
		            a: ${highToUpCount}
		        }, {
		            y: '高压偏低',
		            a: ${highToDownCount}
		        }, {
		            y: '低压偏高',
		            a: ${lowToUpCount}
		        }, {
		            y: '低压偏低',
		            a: ${lowToDownCount}
		        }, {
		            y: '血糖偏高',
		            a: ${sugarToUpCount}
		        }, {
		            y: '血糖偏低',
		            a: ${sugarToDownCount}
		        }],
		        xkey: ['y'],
		        ykeys: ['a'],
		        labels: ['记录数'],
		        hideHover: 'auto',
		        resize: true
		    }).on('click', function(i, row){
			      //i当前区域，即y属性
			      //row为行
			      if(row.y=='高压偏高'){
			      	window.location.href  = '${contextPath}/management/devices/querymeasurelist?starttime=${starttime}&xtstate=3&measureType=1 ';
			      }
			      if(row.y=='高压偏低'){
			      	window.location.href  = '${contextPath}/management/devices/querymeasurelist?starttime=${starttime}&xtstate=4&measureType=1 ';
			      }
			      if(row.y=='低压偏高'){
			      	window.location.href  = '${contextPath}/management/devices/querymeasurelist?starttime=${starttime}&xtstate=5&measureType=1 ';
			      }
			      if(row.y=='低压偏低'){
			      	window.location.href  = '${contextPath}/management/devices/querymeasurelist?starttime=${starttime}&xtstate=6&measureType=1 ';
			      }
			      if(row.y=='血糖偏高'){
			      	window.location.href  = '${contextPath}/management/devices/querymeasurelist?starttime=${starttime}&xtstate=2&measureType=2 ';
			      }
			      if(row.y=='血糖偏低'){
			      	window.location.href  = '${contextPath}/management/devices/querymeasurelist?starttime=${starttime}&xtstate=1&measureType=2 ';
			      }
			     
				});
		});
	
	</script>
</head>

<body>

  
        <div>
            <div class="row"><!--
                <div class="col-lg-12">
                    <h1 class="page-header">欢迎来到首页</h1>
                </div>
                --><!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-3 col-md-6" style="width: 25%">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img src='${contextPath}/resources/img/su1.png' style="max-width:77px">
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">总用户${countUser}</div>
                                    <div>本月新增${newAddUseCount}</div>
                                </div>
                            </div>
                        </div>
                        <a href="${contextPath}/management/aoluser/userslist?starttime=${starttime}">
                            <div class="panel-footer">
                                <span class="pull-left">查看明细</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6" style="width: 25%">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img src='${contextPath}/resources/img/su2.png' style="max-width:77px">
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">血压计数量${xyCount}</div>
                                    <div>本月新增${newAddxyCount}</div>
                                </div>
                            </div>
                        </div>
                        <a href="${contextPath}/management/devices/deviceslist?deviceType='1'&starttime=${starttime}">
                            <div class="panel-footer">
                                <span class="pull-left">查看明细</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6" style="width: 25%">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img src='${contextPath}/resources/img/su3.png' style="max-width:77px">
                                </div>
                                <div class="col-xs-9 text-right">
                                      <div class="huge">血糖仪数量${xtCount}</div>
                                    <div>本月新增${newAddxtCount}</div>
                                </div>
                            </div>
                        </div>
                        <a href="${contextPath}/management/devices/deviceslist?deviceType='2'&starttime=${starttime}">
                            <div class="panel-footer">
                                <span class="pull-left">查看明细</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <c:choose>
                	<c:when test="${loginUser.userType == 99}">
                		<div class="col-lg-3 col-md-6" style="width: 25%">
		                    <div class="panel panel-red">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <img src='${contextPath}/resources/img/su4.png' style="max-width:77px">
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                     <div class="huge">代理商数量${countOrg}</div>
		                                    <div>本月新增${newAddOrgCount}</div>
		                                </div>
		                            </div>
		                        </div>
		                        <a href="${contextPath}/management/organise/organiseslist">
		                            <div class="panel-footer">
		                                <span class="pull-left">查看明细</span>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
		                        </a>
		                    </div>
		                </div>
                	</c:when>
                </c:choose>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 柱状图
                        </div>
                        <div class="panel-body">
                            <div id="org-homepage-chart"></div>
                        </div>
                    </div>
                    
                    
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

   


    <!-- jQuery Version 1.11.0 -->
    <script src="${contextPath}/resources/orgHomePage/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/resources/orgHomePage/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${contextPath}/resources/orgHomePage/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${contextPath}/resources/orgHomePage/js/plugins/morris/raphael.min.js"></script>
    <script src="${contextPath}/resources/orgHomePage/js/plugins/morris/morris.min.js"></script>
    <script src="${contextPath}/resources/orgHomePage/js/plugins/morris/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${contextPath}/resources/orgHomePage/js/sb-admin-2.js"></script>

</body>

</html>


