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
		<script src="${contextPath}/resources/jquery treegrid/jquery treegrid/jquery.treegrid.js"></script>
		<script src="${contextPath}/resources/jquery treegrid/jquery treegrid/jquery.treegrid.bootstrap2.js"></script>
		<script type="text/javascript"src="${contextPath}/resources/Highcharts-3.0.7/js/highcharts.js"></script>  
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/jquery treegrid/jquery treegrid/jquery.treegrid.css" />
		
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
			
			.z-ulnone ul, .z-ulnone li {
				list-style: none;
				padding: 1px 0px;
				margin: 0;
			}
			.tree-title {
				font-size: 12px;
				display: inline-block;
				text-decoration: none;
				vertical-align: top;
				white-space: nowrap;
				padding: 0 6px;
				height: 18px;
				line-height: 18px;
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
	document.getElementById('contextPath').value="${contextPath}";
	$('#usertree').tree({ 
			url:'${contextPath}/management/measure/reportxtTreeList',
			onClick: function(node){
				treeOnClick(node);  //在用户点击的时候提示
			}
	});
	function treeOnClick(node){
		if(node.attributes){
			var sendTimeQ = $("#sendTimeQ").val();
			var sendTimeZ = $("#sendTimeZ").val();
			if(sendTimeQ==''||sendTimeZ==''){
				jAlert('请先确定时间范围再查询','提示');
				return;
			}

			var formObj = "deviceid="+node.id+"&userid="+node.attributes.userid+"&sendTimeQ="+sendTimeQ+"&sendTimeZ="+sendTimeZ;
			$.ajax({
				 url:document.getElementById('contextPath').value+'/management/measure/getReport',
				 type:'post',
				 data:formObj+"&measureType=2",
				 cache:false,
				 success:function(response){
					if(response == "未找到记录"){
						jAlert('未找到记录','提示');
						document.getElementById('strxml').value="";
					}else{
						//清除数据
						daleteSeries();
						var highvaluelist = response;
						// 获取json数据，转换为需要的数据格式
					     for(var i=0;i<highvaluelist.length;i++){
					     // 抽取json中的Y元素变成数组
					           var datavalue = new Array();
					           var time = new Array();
					           for(var j=0;j<highvaluelist[i].highvalue.length;j++){
					               datavalue[j] = Number(highvaluelist[i].highvalue[j].y);
					               time.push(highvaluelist[i].highvalue[j].x);
					           }
					           chart.xAxis[0].setCategories(time);
					           chart.addSeries({
					            	// 设置污染项
					            	name:highvaluelist[i].typename,
					            	// 设置监测的数组
					             	data:datavalue,
					             	visible : true
					             },true);
					             	chart.redraw();
					             
					        }
						//document.getElementById('gdiframe').contentWindow.location.reload(true);
					}
				}
			});	
		} else {
			if(node.state == "open"){
				$('#usertree').tree('collapse',node.target);
			} else {
				$('#usertree').tree('expand',node.target);
			}
		}
	}
});
$(function() {
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
	//初始化highcharts
	Highcharts.setOptions({  
	     global: {  
	         useUTC: false  
	          }  
	     });  
    // 声明报表对象
    chart = new Highcharts.Chart({   
	     chart:{   
	             // 将报表对象渲染到层上
	             type: 'line',
	             renderTo:'gdiframe' ,  
	             defaultSeriesType:'spline'  
	      }, // chart end
         title: {
            text: '血糖折线图',
            x: -20 // center
         }, 
         colors:['#058DC7', '#50B432', '#ED561B'],
         xAxis: {
             categories: [
             ],
             labels: {
        	     enabled:false,
                 style: {
                   color: '#999',
                   font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
                 }
              } 
         },
         yAxis: {
              min: 0,
              max: 300,
              title: {
                  text: ''
              },
              plotLines: [{
                  value: 0,
                  width: 1,
                  color: '#808080'
              }]
           },
         series:[
                 {
             	  name: '血糖',  
                  data: [0]}
                 ]
          });  // Highcharts.Chart end
});
function searchBtnClick(){
	$('#usertree').tree("options").url = '${contextPath}/management/measure/reportxtTreeList?usersname='+$("#usersname").val();
    $('#usertree').tree('reload');
}
function td_click7(){
	var myDate = new Date();
	$("#sendTimeQ").val(jDate(myDate,7));
	$("#sendTimeZ").val(jDate(myDate,0));
}
function td_click30(){
	var myDate = new Date();
	$("#sendTimeQ").val(jDate(myDate,30));
	$("#sendTimeZ").val(jDate(myDate,0));
}
function td_clickM(){
	var myDate = new Date();
	$("#sendTimeQ").val(benyue(myDate));
	$("#sendTimeZ").val(jDate(myDate,0));
}
function jDate(date,days){ 
	var d=new Date(date); 
    d.setDate(d.getDate()-days); 
    var m=d.getMonth()+1; 
    return d.getFullYear()+'-'+m+'-'+d.getDate(); 
} 
function benyue(date){ 
	var d=new Date(date); 
    var m=d.getMonth()+1; 
    return d.getFullYear()+'-'+m+'-1'; 
} 

function daleteSeries(){
	var index = chart.series.length;
	for(var i = 0;i<index;i++){
			chart.series[0].remove();
		}
}
</script>	

	</head>
	<body>
		<!-- 设置地图坐标属性 -->
		<input type="hidden" id = "strxml" value="${strxml}"/>
		<input type="hidden" id = "contextPath" />
		<input type="hidden" id = "pageType" value="xt" /><!-- 因为调用的是一个JS方法，在此设定区分参数，用来执行不同的方法 -->
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<div class="box-content"  style="padding: 0px">
					<!-- 列表start -->
					<table>
					<tr>
						<td width="100%">
							<table border="0px"
								style="height: 20px; word-break: keep-all; white-space: nowrap; float: left;">
								<tr>
									<td width="20px">
										&nbsp;
									</td>
									<td style="cursor:pointer" onClick="td_click7()">
										最近7天
									</td>
									<td width="10px">
										&nbsp;
									</td>
									<td style="cursor:pointer" onClick="td_click30()">
										最近30天
									</td>
									<td width="10px">
										&nbsp;
									</td>
									<td style="cursor:pointer" onClick="td_clickM()">
										本月
									</td>
									<td width="10px">
										&nbsp;
									</td>
									<td>
										测量时间范围：
									</td>
									<td>
										<input type="text" name="sendTimeQ" id="sendTimeQ"
											value="${sendTimeQ}" readonly class="form_datetime" required="required"
											style="width: 90px" />
										~
										<input type="text" name="sendTimeZ" id="sendTimeZ"
											value="${sendTimeZ}" class="form_datetime" readonly required="required" style="width: 90px" />
									</td>
									<td width="10px">
										&nbsp;
									</td>
								</tr>
							</table>
							<div id="gdiframe" src='${contextPath}/management/measure/report' width="100%" height="450px" style="border: 0"></div>
						</td>
						<td align="right" width="300px">
							<table border="0px" style="height:20px;word-break:keep-all;white-space:nowrap;float:left;margin-top:30px;">
								<tr>
									<td>
										<div >
											<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
											<input id="usersname" name="usersname" type="text" value="" style="width: 110px; height: 15px;" />
											<button id="btnSendTop" name="btnSendTop"  style="width:45px;cursor:pointer;"type="button" class="btn btn-primary" onclick="searchBtnClick()"></i>搜索</button>
											</form>
										</div>
									</td>
								</tr>
								<tr>
									<td>
									 用户列表
									</td>
								</tr>
								<tr>
									<td>
										<div style="height:450px;width:100%;overflow:auto;" align="left"> 
												<ul id="usertree" class="easyui-tree"></ul>
										</div>
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
					</table>
					
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

