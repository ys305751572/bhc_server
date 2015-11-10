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
				
				var columns = [ {'text':'医生名字','dataIndex':'name','width':'70px'},
							    {'text':'所在科室','dataIndex':'depart','width':'60px'},
							    {'text':'手机号','dataIndex':'mobile','width':'60px'},
							    {'text':'性别','dataIndex':'gender','render': genderRender,'width':'60px'},
							    {'text':'医师等级','dataIndex':'level','width':'50px'},
							    {'text':'操作','dataIndex':'userxx','width':'110px','render':caoZuoRender}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"doctorListDataTable",
												"url":"${contextPath}/management/doctor/page",
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
				
				function genderRender(row) {
					if(row.gender == 1) {
						return '男';
					}
					else {
						return '女';
					}
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
				function caoZuoRender(row){
					return "<button id='btnSendTop' name='btnSendTop'  style='width:80px;cursor:pointer;'type='button' class='btn btn-primary' onclick='viewUserInfo(\""+row.id+"\")'></i>查看信息</button>"
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
				
				//自定义渲染的回调函数--高压状态
				function gyztRender(row){
					var jt = "";
				
					var xt = parseFloat(row.result2);
					if(xt >= 140){
						jt = "<span style='color: red; font-weight:bold;'>↑</span>";
					} else if(xt >= 130 && xt <= 139){
						jt = "<span style='font-weight:bold;'>↑</span>";
					} else if(xt >=90 && xt <= 129){
						jt = "<span style='font-weight:bold;'>--</span>";
					} else {
						jt = "<span style='color: red; font-weight:bold;'>↓</span>";
					}
				    
					return '<div align="center">' + jt + '</div>';
				}
				//自定义渲染的回调函数--预警高压（收缩压）
				function yjgyRender(row){
					return '<div align="left">90~129</div>';
				}
				
				//自定义渲染的回调函数--低压状态
				function dyztRender(row){
					var jt = "";
				
					var xt = parseFloat(row.result3);
				    if(xt >= 90){
						jt = "<span style='color: red; font-weight:bold;'>↑</span>";
					} else if(xt >= 85 && xt <= 89){
						jt = "<span style='font-weight:bold;'>↑</span>";
					} else if(xt >=60 && xt <= 84){
						jt = "<span style='font-weight:bold;'>--</span>";
					} else {
						jt = "<span style='color: red; font-weight:bold;'>↓</span>";
					}
				    
					return '<div align="center">' + jt + '</div>';
				}
				//自定义渲染的回调函数--预警低压（舒张压）
				function yjdyRender(row){
					return '<div align="left">60~84</div>';
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
				
				// 新增医师
				function addDoctor() {
					window.location.href = "${contextPath}/management/doctor/pageAdd";
				}
				
				function editDoctor(id) {
					if(!id){
						if(!dataTableObj.getSelectedRow()){
							jAlert('请选择要查看的用户','提示');
							return;
						} else{
							//判断用户是否能查找到数据
							$.ajax({
					    		url:"${contextPath}/management/doctor/sfDoctorInfo?id="+dataTableObj.getSelectedRow().id,
					    		type:'post',
								cache:false,
								success:function(response){
									if(response == "无法查询"){
										jAlert('无法编辑','提示');
									}else{
										window.location.href = "${contextPath}/management/doctor/editDoctor?id="+dataTableObj.getSelectedRow().id;
									}
								}
					   		 });	
						}
					} else {
						$.ajax({
					    		url:"${contextPath}/management/doctor/sfDoctorInfo?id="+id,
					    		type:'post',
								cache:false,
								success:function(response){
									if(response == "无法查询"){
										jAlert('无法编辑','提示');
									}else{
										window.location.href = "${contextPath}/management/doctor/editDoctor?id="+id;
									}
								}
					   		 });
					}
				}
				
			    //查看用户信息
			    function viewUserInfo(id){
					if(!id){
						if(!dataTableObj.getSelectedRow()){
							jAlert('请选择要查看的用户','提示');
							return;
						} else{
							//判断用户是否能查找到数据
							$.ajax({
					    		url:"${contextPath}/management/doctor/sfDoctorInfo?id="+dataTableObj.getSelectedRow().id,
					    		type:'post',
								cache:false,
								success:function(response){
									if(response == "无法查询"){
										jAlert('无法查询','提示');
									}else{
										window.location.href = "${contextPath}/management/doctor/viewDoctorInfo?id="+dataTableObj.getSelectedRow().id;
									}
								}
					   		 });	
						}
					} else {
						$.ajax({
					    		url:"${contextPath}/management/doctor/sfDoctorInfo?id="+id,
					    		type:'post',
								cache:false,
								success:function(response){
									if(response == "无法查询"){
										jAlert('无法查询','提示');
									}else{
										window.location.href = "${contextPath}/management/doctor/viewDoctorInfo?id="+id;
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
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:addDoctor();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-6" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;新增医师</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:editDoctor();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;编辑医师</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="#" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-7" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;添加设备</a></li>
				</div>
				<!-- 操作按钮end -->
			
				<div class="box-content"   style="padding: 0px;border: 0px">
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
										<input type="text" name="sendTimeQ"  id="sendTimeQ" value="${sendTimeQ}" readonly   class="form_datetime"  required="required"  style="width: 90px"/>
										~
										<input type="text" name="sendTimeZ"  id="sendTimeZ"   readonly  class="form_datetime"  required="required"  style="width: 90px"/>
									</td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>

							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>血压状态：</td>
									<td>
									<select id="xtstate" name="xtstate"  style="width: 120px;">
										<option value="" <c:if test="${xtstate==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${xtstate=='1'}">selected="selected" </c:if> value="1">正常</option>
										<option <c:if test="${xtstate=='2'}">selected="selected" </c:if> value="2">正常高压</option>
										<option <c:if test="${xtstate=='3'}">selected="selected" </c:if> value="3">高血压</option>
										<option <c:if test="${xtstate=='0'}">selected="selected" </c:if> value="0">低血压</option>
										<option <c:if test="${xtstate=='4'}">selected="selected" </c:if> value="4">高压偏高</option>
										<option <c:if test="${xtstate=='5'}">selected="selected" </c:if> value="5">高压偏低</option>
										<option <c:if test="${xtstate=='6'}">selected="selected" </c:if> value="6">低压偏高</option>
										<option <c:if test="${xtstate=='7'}">selected="selected" </c:if> value="7">低压偏低</option>
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
					<div id="doctorListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

