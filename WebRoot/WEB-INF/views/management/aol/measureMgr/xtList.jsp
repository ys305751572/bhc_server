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
					var msg = '${msg}';
					if(msg!=""){
						jAlert(msg,'提示');
					}
				});
				var delId = "";
				
				var columns = [ {'text':'用户姓名','dataIndex':'measureName','render':measureNameRender,'width':'110px'},
							    {'text':'设备类型','dataIndex':'measureType','render':measureTypeRender,'width':'100px'},
							    {'text':'测量状态','dataIndex':'measureState','render':measureStateRender,'width':'100px'},
							    {'text':'测量血糖','dataIndex':'result4','width':'100px'},
							    {'text':'血糖状态','render':xtztRender,'width':'60px'},
							    {'text':'预警血糖','render':yjxtRender,'width':'100px'},
							    {'text':'测量时间','dataIndex':'sendTime','width':'200px'},
							    {'text':'操作','dataIndex':'userxx','width':'100px','render':caoZuoRender}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"xtListDataTable",
												"url":"${contextPath}/management/measure/getXtDataList",
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
				function caoZuoRender(row){
					return "<button id='btnSendTop' name='btnSendTop'  style='width:80px;cursor:pointer;'type='button' class='btn btn-primary' onclick='viewUserInfo(\""+row.userId+"\")'></i>查看信息</button>"
				}
				function measureNameRender(row){
					var _measureName = "";
					if(row.measureName){
						_measureName = row.measureName;
					} else {
						_measureName = "<span style='color:red'>【未填】</span>";
					}
					return _measureName;
				}
				
				//自定义渲染的回调函数--设备类型
				function measureTypeRender(row){
					var mstype = "";
					if(row.measureType == 1){
						mstype = "血压";
					} else if(row.measureType == 2){
						mstype = "血糖";
					} else if(row.measureType == 3){
						mstype = "心率";
					} else if(row.measureType == 4){
						mstype = "血脂";
					} else {
					}
					return '<div align="left">' + mstype + '</div>';
				}
				
				//自定义渲染的回调函数--测量状态
				function measureStateRender(row){
					var state = "";
					if(row.measureState == 1){
						state = "设备记录";
					} else if(row.measureState == 2){
						state = "手动记录";
					} else {
					}
					return '<div align="left">' + state + '</div>';
				}
				
				//自定义渲染的回调函数--血糖状态
				function xtztRender(row){
					var jt = "";
				
					var xt = parseFloat(row.result4);
				    if(xt < 3.9){
				    	jt = "<span style='color: red; font-weight:bold;'>↓</span>";
				    } else if(xt > 6.1){
				    	jt = "<span style='color: red; font-weight:bold;'>↑</span>";
				    } else {
				    	jt = "<span style='font-weight:bold;'>--</span>";
				    }
				    
					return '<div align="center">' + jt + '</div>';
				}
				//自定义渲染的回调函数--预警血糖
				function yjxtRender(row){
					return '<div align="left">3.9~6.1</div>';
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"usersname","value":$("#usersname").val()},
						{"name":"sendTimeQ","value":$("#sendTimeQ").val()},
						{"name":"sendTimeZ","value":$("#sendTimeZ").val()},
						{"name":"xtstate","value":$("#xtstate").val()}
					];
					dataTableObj.search(arrayObj);
				}
				
				
				//查看用户信息
			    function viewUserInfo(userId){
					if(!userId){
						if(!dataTableObj.getSelectedRow()){
							jAlert('请选择要查看的用户','提示');
							return;
						} else{
							//判断用户是否能查找到数据
							$.ajax({
					    		url:"${contextPath}/management/aoluser/sfUserInfo?userId="+dataTableObj.getSelectedRow().userId,
					    		type:'post',
								cache:false,
								success:function(response){
									if(response == "无法查询"){
										jAlert('无法查询','提示');
									}else{
										window.location.href = "${contextPath}/management/aoluser/viewUserInfo?userId="+dataTableObj.getSelectedRow().userId;
									}
								}
					   		 });	
						}
					} else {
						$.ajax({
					    		url:"${contextPath}/management/aoluser/sfUserInfo?userId="+userId,
					    		type:'post',
								cache:false,
								success:function(response){
									if(response == "无法查询"){
										jAlert('无法查询','提示');
									}else{
										window.location.href = "${contextPath}/management/aoluser/viewUserInfo?userId="+userId;
									}
								}
					   		 });
					}
				}
		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<div class="box-content"   style="padding: 0px">
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
									<td>测量时间范围：</td>
									<td>
										<input type="text" name="sendTimeQ"  id="sendTimeQ" value="${sendTimeQ}"  readonly   class="form_datetime"  required="required"  style="width: 90px"/>
										~
										<input type="text" name="sendTimeZ"  id="sendTimeZ"   readonly  class="form_datetime"  required="required"  style="width: 90px"/>
									</td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>

							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>血糖状态：</td>
									<td>
									<select id="xtstate" name="xtstate"  style="width: 120px;">
										<option value="" <c:if test="${xtstate==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${xtstate=='0'}">selected="selected" </c:if> value="0">正常</option>
										<option <c:if test="${xtstate=='1'}">selected="selected" </c:if> value="1">低血糖</option>
										<option <c:if test="${xtstate=='2'}">selected="selected" </c:if> value="2">高血糖</option>
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
					<div id="xtListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

