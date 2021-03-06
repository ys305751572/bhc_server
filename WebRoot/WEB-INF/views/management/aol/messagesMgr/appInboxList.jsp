<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<%@ include file="/WEB-INF/views/scripts.jsp"%>
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/aolIcons.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/aol/css/mailStyle.css" />
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
			
			.mesStateCt {
				font-weight: bold;
			}
			
			.mesStateZc {
				font-weight: 100;
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
				
				var columns = [ 
								{'text':'发件人','dataIndex':'username','width':'200px','render':userNameIdRender},
							    {'text':'主题','dataIndex':'title','width':'400px','render':titleRender},
							    {'text':'发送时间','dataIndex':'sendtime','width':'120px','render':sendTimeRender},
							    {'text':'发送状态','dataIndex':'state','width':'120px','render':stateRender}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"inboxListDataTable",
												"url":"${contextPath}/management/messages/getAppInboxDataList",
												"para":arrayObj,
												"autoIframeHeight":false,
												"showIndex":false,
												"bSort":false,
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
					
					$("#ckb_selectAll").click(function (){
						if(this.checked){ 
					        $("input[name='ckbname']").attr('checked', true);
					    }else{ 
					        $("input[name='ckbname']").attr('checked', false);
					    }
					});
				});
				
				//自定义渲染的回调函数--复选框
				function ckbRender(row){
					return '<div align="center"><input type="checkbox" id="ckb_' + row.messagesId + '_Item" name="ckbname" value="' + row.messagesId + '" onclick="allchk();" title="选中/取消选中"></div>';
				}
				//消息状态
				function msgStateRender(row){
					if(row.messagesState == 0){
						return '<div align="center"><input type="button" class="cir Ru" style="width:16px;background-color: #FFFFFF;margin-top: 3px;"></div>';
					} else {
						return '<div align="center"><input type="button" class="cir Rr" style="width:16px;background-color: #FFFFFF;margin-top: 3px;"></div>';
					}
				}
				//收件人
				function userNameIdRender(row){
						return '<div class="mesStateCt">' + row.username + '</div>';
				}
				//标题
				function titleRender(row){
						return '<div class="mesStateCt">' + row.title + '</div>';
				}
				//时间
				function sendTimeRender(row){
					var sendtime = "";
					if(row.sendtime){
						sendtime = new Date(row.sendtime).format("yyyy-MM-dd hh:mm:ss");
					}
						return '<div class="mesStateCt">' + sendtime + '</div>';
				}
				//状态
				function stateRender(row){
					var state= ""
					if(row.state==0){
						return '<div class="mesStateCt">未发送</div>';
					}
					if(row.state==1){
						return '<div class="mesStateCt">已发送</div>';
					}
				}
				function searchBtnClick(){
					var arrayObj = [
						
					];
					dataTableObj.search(arrayObj);
				}
				
				//监控全选状态
				function allchk(){ 
				    var chknum = $("input[name='ckbname']").size();//选项总个数 
				    var chk = 0; 
				    $("input[name='ckbname']").each(function () {   
				        if($(this).attr("checked")){ 
				            chk++;
				        } 
				    });
				    if(chknum==chk){//全选 
				        $("#ckb_selectAll").attr("checked",true); 
				    }else{//不全选 
				        $("#ckb_selectAll").attr("checked",false); 
				    } 
				}
				
				function getCheckValue(){
					var valArr = new Array; 
			        $("input[name='ckbname']:checked").each(function(i){ 
			            valArr[i] = $(this).val(); 
			        });
			        var vals = valArr.join(',');
			        alert(vals);
				}
				//查看
				function viewMsg(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要查看的消息记录','提示');
						return;
					} else{
						window.location.href = "${contextPath}/management/messages/appViewMsg?id="+dataTableObj.getSelectedRow().id;
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
					<li><a href="javascript:viewMsg();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-15" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;查看推送消息</a></li>
				</div>
				<!-- 操作按钮end -->
				
				<div class="box-content" style="margin: -15px;">
					<!-- 列表start -->
					<div id="inboxListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

