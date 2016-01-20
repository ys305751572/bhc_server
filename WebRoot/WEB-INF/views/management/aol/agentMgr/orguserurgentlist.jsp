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
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/aolIcons.css" />
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
				
				var columns = [ {'text':'姓名','dataIndex':'name','render':nameRender,'width':'120px'},
							    {'text':'性别','dataIndex':'sex','width':'60px'},
							    {'text':'生日','dataIndex':'birthday','width':'80px'},
							    {'text':'身高','dataIndex':'height','width':'60px'},
							    {'text':'体重','dataIndex':'weight','width':'60px'},
							    {'text':'手机号码','dataIndex':'mobile','render': mobileRender,'width':'100px'},
							    {'text':'邮箱','dataIndex':'email','width':'150px'},
							    {'text':'注册时间','dataIndex':'bak5','render':regRender,'width':'120px'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"aoluserListDataTable",
												"url":"${contextPath}/management/aoluser/getUsersDataList",
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
				
				//重新渲染姓名
				function nameRender(row){
					var _ss_name = "";
					var _name = "";
					if(row.name){
						_name = row.name;
					} else {
						_name = "<span style='color:red'>【未填】</span>";
					}
					if(row.remark){
						_ss_name = row.remark+"("+ _name +")"
					} else {
						_ss_name = _name
					}
					return '<a href="javascript:setUserUrgent(\''+row.userId+'\');">' + _ss_name + '</a>';
				}
				
				function regRender(row){
					var regtime = "";
					if(row.bak5){
						regtime = new Date(row.bak5).format("yyyy-MM-dd hh:mm:ss")
					}
					return regtime;
				}
				
				function mobileRender(row){
					if(!row.mobile){
						return "";
					}else {
						return row.mobile
					}
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"usersname","value":$("#usersname").val()},
						{"name":"regTimeQ","value":$("#regTimeQ").val()},
						{"name":"regTimeZ","value":$("#regTimeZ").val()},
						{"name":"sexType","value":$("#sexType").val()},
						{"name":"mobile","value":$("#mobile").val()},
						{"name":"birthday","value":$("#birthday").val()},
					];
					dataTableObj.search(arrayObj);
				}
				
				//查看用户信息
			    function setUserUrgent(userId){
					if(!userId){
						if(!dataTableObj.getSelectedRow()){
							jAlert('请选择正确的用户','提示');
							return;
						}
					}

					window.location.href = "${contextPath}/management/urgentperson/userurgentlist?userId="+userId;
				}
		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:setUserUrgent();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-6" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;紧急联系人设置</a></li>
				</div>
				<!-- 操作按钮end -->
				
				<div class="box-content" style="margin: -15px;">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>姓名：</td>
									<td><input id="usersname" name="usersname" type="text" value="" style="width: 90px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>手机号码：</td>
									<td><input id="mobile" name="mobile" type="text" value="" style="width: 90px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>生日：</td>
									<td><input id="birthday" name="birthday"  readonly  class="form_datetime"  required="required"  style="width: 90px"/></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>性别：</td>
									<td>
									<select id="sexType" name="sexType"  style="width: 60px;">
										<option value="" <c:if test="${deviceType==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${sexType=='男'}">selected="selected" </c:if> value="男">男</option>
										<option <c:if test="${sexType=='女'}">selected="selected" </c:if> value="女">女</option>
									</select>
									</td>
									<td width="20px">&nbsp;</td>
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>注册时间范围：</td>
									<td>
										<input type="text" name="regTimeQ"  id="regTimeQ" value="${regTimeQ}" readonly   class="form_datetime"  required="required"  style="width: 90px"/>
										~
										<input type="text" name="regTimeZ"  id="regTimeZ"   readonly  class="form_datetime"  required="required"  style="width: 90px"/>
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
					<div id="aoluserListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

