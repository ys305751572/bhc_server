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
				
				var columns = [ {'text':'标题','dataIndex':'title','width':'160px'},
							    {'text':'描述','dataIndex':'adsDesc','render':adsDescRender,'width':'250px'},
							    {'text':'广告链接','dataIndex':'adsLink','render':adsLinkRender,'width':'200px'},
							    {'text':'状态','dataIndex':'adsState','render':adsStateRender,'width':'60px'},
							    {'text':'代理商名称','dataIndex':'bak4','width':'60px'},
							    {'text':'时间','dataIndex':'createTime','render':createtimeRender,'width':'100px'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"adsListDataTable",
												"url":"${contextPath}/management/imageads/getAdsDataList",
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

				function adsDescRender(row){
					if(row.adsDesc.length > 50){
						return row.adsDesc.substr(0, 50) + "......";
					} else {
						return row.adsDesc;
					}
				}
				
				function adsTypeRender(row){
					var adstype = "";
					if(row.adsType == "0"){
						adstype = "";
					} else if(row.adsType == "1"){
						adstype = "";
					} else {
						adstype = "";
					}

					return adstype;
				}
				
				function adsLinkRender(row){
					if(row.adsLink.length > 30){
						return '<a  href="' + row.adsLink + '" target="_blank">' + row.adsLink.substr(0, 30) + "......" + '</a>';
					} else {
						return '<a  href="' + row.adsLink + '" target="_blank">' + row.adsLink + '</a>';
					}
				}
				
				function adsStateRender(row){
					var adsstate = "";
					if(row.adsState == "0"){
						adsstate = "未发布";
					} else if(row.adsState == "1"){
						adsstate = "已发布";
					} else {
						adsstate = "";
					}

					return adsstate;
				}

				function createtimeRender(row){
					var regtime = "";
					if(row.createTime){
						regtime = new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss")
					}
					return regtime;
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"_title","value":$("#_title").val()},
						{"name":"adsType","value":$("#adsType").val()},
						{"name":"adsState","value":$("#adsState").val()}
					];
					dataTableObj.search(arrayObj);
				}

				function addAds(){
					window.location.href = "${contextPath}/management/imageads/addads";
				}

				function editAds(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要修改的记录','提示');
						return;
					} else {
						if(dataTableObj.getSelectedRow().adsState == 0){
							window.location.href = "${contextPath}/management/imageads/editads?imageadsId="+dataTableObj.getSelectedRow().imageadsId;
						} else {
							jAlert('该广告已发布无法修改','提示');
							return;
						}
					}
				}

				function deleteAds(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要删除的记录','提示');
						return;
					} else {
						var imageadsId = dataTableObj.getSelectedRow().imageadsId;
						jConfirm('是否确认删除该广告？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/imageads/deleteads",{"imageadsId":imageadsId},function(result){
									if(result.success){
										searchBtnClick();
									}
									jAlert(result.msg,'提示');
								});
						 	}
						});
					}
				}

				function publishAds(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要发布的广告','提示');
						return;
					} else {
						if(dataTableObj.getSelectedRow().adsState == 0){
							window.location.href = "${contextPath}/management/imageads/publishads?imageadsId="+dataTableObj.getSelectedRow().imageadsId;
						} else {
							jAlert('该广告已发布无法再次发布','提示');
							return;
						}
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
					<li><a href="javascript:addAds();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-1" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;新增</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:editAds();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;修改</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:deleteAds();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-3" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;删除</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:publishAds();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-13" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;发布</a></li>
				</div>
				<!-- 操作按钮end -->
				
				<div class="box-content" style="margin: -15px;">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" onsubmit="return false;" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>标题：</td>
									<td><input id="_title" name="_title" type="text" value="" style="width: 200px; height: 17px;" /></td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>

							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>类型：</td>
									<td>
									<select id="adsType" name="adsType"  style="width: 120px;">
										<option value="" <c:if test="${adsType==''}">selected="selected" </c:if>>全部</option>
									</select>
									</td>
									<td width="10px">&nbsp;</td>
								</tr>
							</table>

							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>状态：</td>
									<td>
									<select id="adsState" name="adsState"  style="width: 120px;">
										<option value="" <c:if test="${adsState==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${adsState=='1'}">selected="selected" </c:if> value="1">已发布</option>
										<option <c:if test="${adsState=='0'}">selected="selected" </c:if> value="0">未发布</option>
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
					<div id="adsListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

