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
					$("#deviceType").val(${deviceType});
					var msg = '${msg}';
					if(msg!=""){
						jAlert(msg,'提示');
					}
				});
				var delId = "";
				
				var columns = [ {'text':'设备序列号','dataIndex':'deviceSerial','width':'260px'},
							    {'text':'功能类型','dataIndex':'deviceType','render':deviceTypeRender,'width':'80px'},
							    {'text':'设备类型','dataIndex':'bak1','render':bak1Render,'width':'80px'},
							    {'text':'生产年份','dataIndex':'deviceProYear','width':'80px'},
							    {'text':'生产月份','dataIndex':'deviceProMonth','width':'80px'},
							    // {'text':'所属代理商','dataIndex':'organiseName','render':organiseNameRender,'width':'200px'},
							    {'text':'使用状态','dataIndex':'usedState','render':usedStateRender,'width':'80px'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"deviceListDataTable",
												"url":"${contextPath}/management/devices/getDevicesDataList",
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
				
				function deviceTypeRender(row){
					var devtype = "";
					if(row.deviceType == "1"){
						devtype = "血压";
					} else if(row.deviceType == "2"){
						devtype = "血糖";
					} else if(row.deviceType == "3"){
						devtype = "体重";
					} else if(row.deviceType == "4"){
						devtype = "运动";
					} else {
					}
					return devtype;
				}

				function bak1Render(row){
					var devtype = "";
					if(row.bak1 == "0"){
						devtype = "普通设备";
					} else if(row.bak1 == "1"){
						devtype = "GSM设备";
					} else {
					}
					return devtype;
				}

				function usedStateRender(row){
					var usedstate = "";
					if(row.usedState == "0"){
						usedstate = "未被使用";
					} else if(row.usedState == "1"){
						usedstate = "已被使用";
					} else {
					}
					return usedstate;
				}

				function organiseNameRender(row){
					var _name = "";
					if(row.organiseName){
						_name = row.organiseName;
					} else {
						_name = "<span style='color:red'>【没有找到对应的代理商】</span>";
					}
					return _name;
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"deviceSerial","value":$("#deviceSerial").val()},
						{"name":"organiseName","value":$("#organiseName").val()},
						{"name":"usedState","value":$("#usedState").val()},
						{"name":"deviceType","value":$("#deviceType").val()},
						{"name":"starttime","value":$("#starttime").val()}
					];
					dataTableObj.search(arrayObj);
				}
				
		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				
				<div class="box-content" style="margin: -15px;">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>设备序列号：</td>
									<td><input id="deviceSerial" name="deviceSerial" type="text" value="" style="width: 160px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>代理商：</td>
									<td><input id="organiseName" name="organiseName" type="text" value="" style="width: 160px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>设备类型：<c:if test="${deviceType=='1'}">selected="selected" </c:if></td>
									<td>
										<select id="deviceType" name="deviceType"  style="width: 120px;">
											<option value="" <c:if test="${deviceType==''}">selected="selected" </c:if>>全部</option>
											<option <c:if test="${deviceType=='1'}">selected="selected" </c:if> value="1">血压</option>
											<option <c:if test="${deviceType=='2'}">selected="selected" </c:if> value="2">血糖</option>
										</select>
									</td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>使用状态：</td>
									<td>
									<select id="usedState" name="usedState"  style="width: 120px;">
										<option value="" <c:if test="${usedState==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${usedState=='1'}">selected="selected" </c:if> value="1">已被使用</option>
										<option <c:if test="${usedState=='0'}">selected="selected" </c:if> value="0">未被使用</option>
									</select>
									</td>
									<td width="20px">&nbsp;<input id="starttime" name="starttime" type="hidden" value="${starttime}" /></td>
									<td height="40px" align="right">
										<button id="btnSendTop" name="btnSendTop"  style="width:50px;cursor:pointer;"type="button" class="btn btn-primary" onclick="searchBtnClick()"></i>搜索</button>
									</td>
								</tr>
							</table>
						</form>
						
					</div>
					<!-- 搜索条件end -->

					<!-- 列表start -->
					<div id="deviceListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

