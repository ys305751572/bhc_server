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
		<script type="text/javascript" src="${contextPath}/resources/js/expandEasyuiTree.js"></script>
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
			
			.tree-expanded, .tree-collapsed, .tree-checkbox, .tree-indent {
				display: inline-block;
				width: 14px;
				height: 18px;
				vertical-align: top;
				overflow: hidden;
			}
		</style>
		<script type="text/javascript">
				$(function() {
					$('#startingSide').tree({ 
						url:"${contextPath}/management/aoluser/getOrgAndUserTreeData",
						checkbox: true,
						onExpand: function(node){
							window.parent.resetIframeHeight(document.getElementById("devtreediv").clientHeight+200);
						},
						onCollapse: function(node){
							window.parent.resetIframeHeight(document.getElementById("devtreediv").clientHeight+200);
						},
						onClick: function(node){
							if(node.state == "open"){
								$(this).tree('collapse',node.target);
							} else {
								$(this).tree('expand',node.target);
							}
						}
					}); 

					$('#endingSide').tree({ 
						url:"${contextPath}/management/aoluser/getOrgTreeData",
						onClick: function(node){
							treeOnClick(node.id);  //在用户点击的时候提示
						}
					}); 
					
				});

				function treeOnClick(id){
					$("#orgId").val(id);
				}

				function searchBtnClick(){
					var search_username = $("#username").val();
					$('#startingSide').tree("search", search_username);
				}

				function doChange(_state){
					var userIds = "";
					var orgIds = new Array();
					var orgIndex = 0;
					//获取所有已勾选的节点
					var checkNodes = $('#startingSide').tree('getChecked');
					for(var i=0;i<checkNodes.length;i++){
						var checknode = checkNodes[i];
						var nodeid = checknode.id;
						if(nodeid.substr(0, 2) == "U_" || nodeid.substr(0, 2) == "W_"){
							userIds = userIds + nodeid + "@@";
						} else if(nodeid.substr(0, 2) == "O_"){
							orgIds[orgIndex] = nodeid.substring(2, nodeid.length);
							orgIndex++;
						}
					}
					//获取所有不确定的节点
					var checkOrgNodes = $('#startingSide').tree('getChecked', 'indeterminate');
					if(checkOrgNodes){
						for(var j=0;j<checkOrgNodes.length;j++){
							var orgNode = checkOrgNodes[j];
							var orgid = orgNode.id;
							if(orgid.substr(0, 2) == "O_"){
								orgIds[orgIndex] = orgid.substring(2, orgid.length);
								orgIndex++;
							}
						}
					}
					
					if(userIds == ""){
						jAlert('请勾选要操作的用户！','提示');
						return;
					}

					var orgId = $("#orgId").val();
					if(!orgId){
						jAlert('请选择用户接收单位！','提示');
						return;
					}

					if(orgIds){
						for(var k=0;k<orgIds.length;k++){
							if(orgId == orgIds[k]){
								jAlert('需操作的用户已在接收单位名下，无需重复操作！','提示');
								return;
							}
						}
					}

					var isOkMsg = "";
					if(_state == "copy"){
						isOkMsg = "是否确认复制所选用户至接收单位？"
					} else if(_state == "change") {
						isOkMsg = "是否确认转移所选用户至接收单位？"
					} else {
						jAlert('请选择操作类型！','提示');
						return;
					}

					jConfirm(isOkMsg,"提示",function(r){
						if(r) { 
							$.post("${contextPath}/management/aoluser/doChange",{"doState":_state, "userIds":userIds, "orgId":orgId},function(result){
								if(result.success){
									$("#orgId").val("");
									$('#startingSide').tree('reload');
									$('#endingSide').tree('reload');
								}
								jAlert(result.msg,'提示');
							});
					 	}
					});
				}

		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:doChange('change');" class="button button-rounded button-flat button-tiny" style="width: 60px;"><i class="icon-8" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;转移</a></li>
				</div>
				<!-- 操作按钮end -->
				
				<div class="box-content" style="margin: -15px;">
				  
				  <div style="float: left; width: 90%; margin: 0px 15px 10px;">
				  	<div style="height: 28px; line-height: 28px;">
						<form id="form1" name="form1" class="form-horizontal" style="margin: 0px 0px;" action="" method="post" enctype="multipart/form-data">
							<span style="font-size: 15px;font-weight: normal;line-height: 18px;">用户名：</span>
							<input id="username" name="username" type="text" value="" style="width: 200px; height: 15px;" />
							<button id="btnSendTop" name="btnSendTop" type="button" style="width:50px;cursor:pointer;" class="btn btn-primary" onclick="searchBtnClick()"></i>搜索</button>
						</form>
					</div>
				  </div>
				
				  <div style="float: left; width: 30%; margin: 0px 15px 9px;">
					<div style="float: left; width: 100%; margin-bottom: 5px;">
						<span style="font-size: 15px;font-weight: normal;line-height: 18px;">请选择用户：</span>
					</div>
					<div id="devtreediv" style="float: left; width: 100%; border: 1px solid #c6d4aa; margin: 0px 0px 0px 30px;">
						<ul id="startingSide" class="easyui-tree"></ul>
					</div>
				  </div>
				  <div style="float: left;width: 5%;">
					<input id="orgId" name="orgId" type="hidden" value=""/>
					&nbsp;
				  </div>
				  <div style="float: left; width: 30%; margin: 0px 15px 9px;">
					<div style="float: left; width: 100%; margin-bottom: 5px;">
						<span style="font-size: 15px;font-weight: normal;line-height: 18px;">请选择接收单位：</span>
					</div>
					<div style="float: left; width: 100%; border: 1px solid #c6d4aa; margin: 0px 0px 0px 30px;">
						<ul id="endingSide" class="easyui-tree"></ul>
					</div>
				  </div>
				  
				</div> 
			</div> 
		</div> 
	</body>
</html>

