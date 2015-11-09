<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>爱奥乐健康云平台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@ include file="/WEB-INF/views/scripts.jsp"%>
		<!-- xxx tools -->
		<script src="${contextPath}/resources/js/xxxInvesReview.js"></script>
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
		<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<!-- The styles -->
		<style type="text/css">
			body {
				padding-bottom: 10px;
				padding-top: 0px;
			}
			
			.sidebar-nav {
				padding: 9px 0;
			}
			
			#DataTables_Table_0_paginate a {
				margin-left: 5px;
			}
			.dataTables_scroll{
				float: left;
			}
			#DataTables_Table_0_wrapper .row-fluid {
				float: left;
			}
		</style>
		
<script type="text/javascript">		
Date.prototype.format = function(format){
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    };
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    };
    return format;
};
</script>
		<script type="text/javascript">
				$(document).ready(function(){
					var msg = '${msg}';
					document.getElementById('contextPath').value="${contextPath}";
					if(msg!=""){
						jAlert(msg,'提示');
					}
				});
				var delId = "";
				
				// {'text':'设备类型','dataIndex':'deviceId','width':'10px','style':'display:none'}
				var columns = [ {'text':'用户姓名','dataIndex':'userName','width':'70px'},
							    {'text':'设备类型','dataIndex':'deviceType','render':deviceTypeRender,'width':'50px'},
							    {'text':'设备序列号','dataIndex':'deviceSerial','width':'70px'},
							    {'text':'设备ID','dataIndex':'device_id','width':'10px','style':'display:none'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"xtListDataTable",
												"url":"${contextPath}/management/position/jqDateList",
												"para":arrayObj,
												"autoIframeHeight":false,
												"showIndex":true,
												"fnComplete":function(){
													 window.parent.resetIframeHeight(dataTableObj.oTable[0].clientHeight+400);
												}});
					searchBtnClick();
					
					$('.form_datetime').datetimepicker({
				        language:  'zh-CN',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
				        showMeridian: 1,
				        format:'yyyy-mm-dd',
				        minView:2
				    });
					
				});
				
				//自定义渲染的回调函数--设备类型
				function deviceTypeRender(row){
					var deviceTypename = "";
					if(row.deviceType == 1){
						deviceTypename = "血压";
					} else if(row.deviceType == 2){
						deviceTypename = "血糖";
					}else if(row.deviceType == 3){
						deviceTypename = "体重";
					} else if(row.deviceType == 4){
						deviceTypename = "运动";
					}
					return '<div align="left">' + deviceTypename + '</div>';
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"usersname","value":$("#usersname").val()},
						{"name":"deviceType","value":$("#deviceType").val()}
					];
					dataTableObj.search(arrayObj);
				}

		</script>
	</head>
	<body>
		<!-- 设置地图坐标属性 -->
		<input type="hidden" id = "zb"/>
		<input type="hidden" id = "contextPath" />
		<input type="hidden" id = "pageType" value="gdgj" /><!-- 因为调用的是一个JS方法，在此设定区分参数，用来执行不同的方法 -->
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<div class="box-content"  style="padding: 0px">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>用户姓名：</td>
									<td><input id="usersname" name="usersname" type="text" value="" style="width: 120px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>


							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>机器类型</td>
									<td>
									<select id="deviceType" name="deviceType"  style="width: 120px;">
										<option value="" <c:if test="${deviceType==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${deviceType=='1'}">selected="selected" </c:if> value="1">血压</option>
										<option <c:if test="${deviceType=='2'}">selected="selected" </c:if> value="2">血糖</option>
										<option <c:if test="${deviceType=='3'}">selected="selected" </c:if> value="3">体重</option>
										<option <c:if test="${deviceType=='4'}">selected="selected" </c:if> value="4">运动</option>
									</select>
									</td>
									<td width="20px">&nbsp;</td>
									<td height="40px" align="right">
										<button id="btnSendTop" name="btnSendTop"  style="width:50px;cursor:pointer;"type="button" class="btn btn-primary" onclick="searchBtnClick()"></i>搜索</button>
									</td>
								</tr>
							</table>
							
						</form>
						
					</div>
					<!-- 搜索条件end -->

					<!-- 列表start -->
					<table width="100%">
					<tr>
						<td>
						</td>
						<td width="99%">
							<iframe id="gdiframe" src='${contextPath}/management/position/gddhgjpagedd' width="99%" height="450px" style="border:0px"></iframe>
						</td>
						<td align="right" width="300px">
							<div style="height:460px;width:300px;overflow:auto"> 
								<div id="xtListDataTable" class="z-informaxxx"></div> 
							</div>
						</td>
					</tr>
					<table>
					
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

