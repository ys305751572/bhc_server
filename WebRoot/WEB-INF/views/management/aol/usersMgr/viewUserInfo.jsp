<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>爱奥乐健康云平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<script src="${contextPath}/resources/FusionCharts/FusionCharts.js"></script>
	<%@ include file="/WEB-INF/views/scripts.jsp"%>
	<script type="text/javascript" src="${contextPath}/resources/js/bootstrap-validation.js"  charset="UTF-8"></script>
	<script type="text/javascript"src="${contextPath}/resources/Highcharts-3.0.7/js/highcharts.js"></script>  
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
	<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<!-- The styles -->
	<style type="text/css">
		body {
			padding-bottom: 40px;
			font: 12px/1.6 "微软雅黑","宋体";
		}
		.sidebar-nav {
			padding: 9px 0;
		}
		dt {
			font-weight: normal;
			font-size: 18px;
			padding: 4px 0;
			text-indent: 20px;
			color: #000;
		}
		dd {
			font-size: 14px;
			padding: 5px 0;
			margin: 0 40px;
		}
	</style>
	<script type="text/javascript">
		function selectdevice(){
			  //window.location.href = "${contextPath}/management/aoluser/viewUserInfo?userId=${aoluser.userId}&urltype=user&deviceId="+$("#reportype").val();
			  var formObj = "deviceid="+$("#reportype").val()+"&userid=${aoluser.userId}";
			  var type= $("#reportype").find("option:selected").text();
			  var title ;
			  if(type.indexOf('血压')>0){
				  title= {
					         text: "血压折线图",
					         x: -20 // center
					 };
				  formObj = formObj+"&measureType=1";
			  }
			  if(type.indexOf('血糖')>0){
				  title= {
					         text: "血糖折线图",
					         x: -20 // center
					 };
				  formObj = formObj+"&measureType=2";
			  }
			  if(type.indexOf('体温')>0){
				  title= {
					         text: "体温折线图",
					         x: -20 // center
					 };
				  formObj = formObj+"&measureType=3";
			  }
			  
			 chart.setTitle(title);
			  $.ajax({
				  url:'${contextPath}/management/measure/getReport',
					 type:'post',
					 cache:false,
					 data:formObj,
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
		}
		$(function() {
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
			             renderTo:'chartdiv' ,  
			             defaultSeriesType:'spline'  
			      }, // chart end
		         title: {
		            text: '血压折线图',
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
		             	  name: '高血压',  
		                  data: [0]},
		                 { 
		                  name: '低血压',  
		                  data: [0]},
		                 { 
		                  name: '心率',  
		                  data: [0]}  
		                 ]
		          });  // Highcharts.Chart end
		});
		function daleteSeries(){
			var index = chart.series.length;
			for(var i = 0;i<index;i++){
					chart.series[0].remove();
				}
		}
	</script>
</head>
<body onload="selectdevice()">		
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row-fluid z-ulnone">
			<div class="box span12">
				<div class="box-header well z-h2">
					<h2><i class="icon-user"></i> 用户信息</h2>
					
					<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
						<jsp:param name="url" value="${contextPath}/management/aoluser/userslist"/>
					</jsp:include>
				</div>
				<div class="box-content">
					<table border="1">
						<tr height="450px">
							<td width="150px">
								<dl style="margin-top: 0px;">
								   <dt>基本信息</dt>
								   <dd>姓&nbsp;名：&nbsp;${aoluser.name}</dd>
								   <dd>性&nbsp;别：&nbsp;${aoluser.sex}</dd>
								   <dd>生&nbsp;日：&nbsp;${aoluser.birthday}</dd>
								   <dd>身&nbsp;高：&nbsp;${aoluser.height}</dd>
								   <dd>体&nbsp;重：&nbsp;${aoluser.weight}</dd>
								   <dt style="padding: 24px 0 4px 0;">联系方式</dt>
								   <dd>手&nbsp;机：&nbsp;${aoluser.mobile}</dd>
								   <dd>邮&nbsp;箱：&nbsp;${aoluser.email}</dd>
								   <dt style="padding: 24px 0 4px 0;">图标展示</dt>
								   <dd>图表类型
								   	<select id="reportype" name="reportype"  style="width: 60px;" onchange="selectdevice()">
									   	<c:forEach var="device" items="${devicelist}" varStatus="status">
											<option value="${device.device_id}"  <c:if test="${device.device_id==selectdeviceid}">selected="selected"</c:if>>
												<c:if test="${device.deviceType=='1'}">血压 </c:if>
												<c:if test="${device.deviceType=='2'}">血糖 </c:if>
												<c:if test="${device.deviceType=='3'}">体温 </c:if>
												---${device.deviceSerial}
											
										</c:forEach>
									</select>
								   </dd>
								</dl>
							</td>
							<td width="800px">
								<div id="chartdiv" ></div>
							</td>
						</tr>
					
					</table>
					
				</div>
				
			</div>
		</div>
	</div>
</div>


</body>
</html>		
