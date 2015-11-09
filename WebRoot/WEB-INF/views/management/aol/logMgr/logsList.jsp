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
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/icons.css" />
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
			
			var columns = [ {'text':'时间','dataIndex':'logtime','render':logtimeRender,'width':'130px'},
						    {'text':'用户','dataIndex':'username','width':'180px'},
						    {'text':'访问IP','dataIndex':'ip_address','width':'120px'},
						    {'text':'内容','dataIndex':'logcontent','width':'300px'}
						    ];
			var arrayObj = [];
			var dataTableObj ;
			$(function() {
				dataTableObj  = new czTools.dataTable({"columns":columns,"render":"logsListDataTable",
											"url":"${contextPath}/management/loginfo/getLogsList",
											"para":arrayObj,
											"autoIframeHeight":false,
											"showIndex":false,
											"fnComplete":function(){
												 window.parent.resetIframeHeight(dataTableObj.oTable[0].clientHeight+200);
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
			
			//重新渲染
			function logtimeRender(row){
				var logtime = "";
				if(row.logtime){
					logtime = new Date(row.logtime).format("yyyy-MM-dd hh:mm:ss")
				}
				return logtime;
			}
			
			function searchBtnClick(){
				var arrayObj = [
					{"name":"usersname","value":$("#usersname").val()},
					{"name":"logTimeQ","value":$("#logTimeQ").val()},
					{"name":"logTimeZ","value":$("#logTimeZ").val()},
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
									<td>用户：</td>
									<td><input id="usersname" name="usersname" type="text" value="" style="width: 120px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>时间范围：</td>
									<td>
										<input type="text" name="logTimeQ"  id="logTimeQ"  readonly   class="form_datetime"  required="required"  style="width: 90px"/>
										~
										<input type="text" name="logTimeZ"  id="logTimeZ"   readonly  class="form_datetime"  required="required"  style="width: 90px"/>
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
					<div id="logsListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

