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
			.icon-briefcase {
				background-position: -432px -142px;
				padding-right: 0px;
				background-image: url("${contextPath}/resources/img/glyphicons-halflings.png");
			}
			.icon-user {
				background-position: -168px 2px;
				padding-right: 0px;
				background-image: url("${contextPath}/resources/img/glyphicons-halflings.png");
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
				
				var columns = [ {'text':'公司编码','dataIndex':'organiseCode','width':'60px'},
							    {'text':'公司名称','dataIndex':'organiseName','width':'200px'},
							    {'text':'联系电话','dataIndex':'telephone','width':'120px'},
							    {'text':'公司地址','dataIndex':'organiseAddress','width':'280px'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					$('#tt').tree({ 
						url:"${contextPath}/management/organise/getOrgTreeData",
						onClick: function(node){
							treeOnClick(node.id);  //在用户点击的时候提示
						}
					}); 

					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"organiseListDataTable",
												"url":"${contextPath}/management/organise/getOrganiseDataList",
												"para":arrayObj,
												"autoIframeHeight":false,
												"showIndex":true,
												"fnComplete":function(){
													 window.parent.resetIframeHeight(dataTableObj.oTable[0].clientHeight+400);
												}});
					//searchBtnClick();
				});
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"parentId","value":$("#parentId").val()},
						{"name":"organiseName","value":$("#organiseName").val()},
					];
					dataTableObj.search(arrayObj);
				}

				function treeOnClick(id){
					$("#parentId").val(id);
					searchBtnClick();
				}

				function addOrg(){
					//var parentid = $("#parentId").val();
					var parentid = "00000000000000000000000000000000";
					if(!parentid){
						jAlert('请选择要添加的节点','提示');
						return;
					} else {
						window.location.href = "${contextPath}/management/organise/addOrg?parentId="+parentid;
					}
				}

				function eidtOrg(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要修改的记录','提示');
						return;
					} else {
						window.location.href = "${contextPath}/management/organise/editOrg?orgId="+dataTableObj.getSelectedRow().organiseId;
					}
				}

				function deleteOrg(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要删除的记录','提示');
						return;
					} else {
						var orgId = dataTableObj.getSelectedRow().organiseId;
						jConfirm('是否确认删除该代理商？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/organise/deleteOrg",{"orgId":orgId},function(result){
									if(result.success){
										searchBtnClick();
										$('#tt').tree('reload');
									}
									jAlert(result.msg,'提示');
								});
						 	}
						});
					}

				}

				function accountMgt(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						window.location.href = "${contextPath}/management/organise/accountMgt?orgId="+dataTableObj.getSelectedRow().organiseId;
					}
				}
				
				function viewOrg(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要查看的记录','提示');
						return;
					} else {
						window.location.href = "${contextPath}/management/organise/viewOrg?orgId="+dataTableObj.getSelectedRow().organiseId;
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
					<li><a href="javascript:addOrg();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-1" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;新增</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:eidtOrg();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;修改</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:deleteOrg();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-3" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;删除</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:viewOrg();" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-4" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;查看</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:accountMgt();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-5" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;&nbsp;账号管理</a></li>
				</div>
				<!-- 操作按钮end -->
				
				<div class="box-content" style="margin: -15px;">
				  <div style="float: left;width: 20%;margin: 9px 15px;">
					<ul id="tt" class="easyui-tree"></ul>    
				  </div>
				  
				  <div style="float: left;width: 75%;">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" onsubmit="return false;" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>公司名称：</td>
									<td><input id="organiseName" name="organiseName" type="text" value="" style="width: 120px; height: 15px;" /></td>
									<td width="20px"><input id="parentId" name="parentId" type="hidden" value="" style="width: 20px;" />&nbsp;</td>
									<td height="40px" align="right">
										<button id="btnSendTop" name="btnSendTop"  style="width:50px;cursor:pointer;"type="button" class="btn btn-primary" onclick="searchBtnClick()"></i>搜索</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<!-- 搜索条件end -->

					<!-- 列表start -->
					<div id="organiseListDataTable" class="z-informa2" style="padding: 0px 15px 0px 5px;"></div> 
					<!-- 列表end -->
				  </div>
				  
				</div> 
			</div> 
		</div> 
	</body>
</html>

