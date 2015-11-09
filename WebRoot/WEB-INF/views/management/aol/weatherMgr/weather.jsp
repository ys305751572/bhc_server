<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">     
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">     
<meta http-equiv="expires" content="0">

<title>爱奥乐健康云平台</title>

<link href="${contextPath}/resources/aol/css/common.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/css/jquery-ui-1.8.21.custom.css" rel="stylesheet" type="text/css">

<script src="${contextPath}/resources/aol/js/jquery.js" type="text/javascript"></script>
<script src="${contextPath}/resources/aol/js/jquery-ui.js" type="text/javascript"></script>

<style type="text/css">
form{padding:0px;margin:0px}
select,option,body{font-family:Helvetica, 'Microsoft Yahei', verdana;font-size:12px}
.ua-noYaHei select,
.ua-noYaHei option,
.ua-noYaHei{font-family:Simsun,sans-serif;}
html,body{background:none;line-height:20px;margin:0;zoom:1;padding:0;}
body{padding-top:24px;}
.ua-noYaHei{min-width: 100%; width: auto !important; width: 100%;}
.txt-info{color:#7D7D7D}
.ico-weather{display:inline-block;zoom:1;_vertical-align:baseline;width:16px;height:18px}
.weather .normal{position:relative}
.weather .area{position:relative;line-height:1em;height:1.2em;overflow:hidden;width:230px;white-space:nowrap;text-overflow:ellipsis\9;margin:0;padding:0;vertical-align:baseline}
.area-bg{position:absolute;height:20px;left:200px;width:30px;top:0;}
.weather .area2{position:relative;line-height:1.2em;margin-top:16px;overflow:hidden;vertical-align:baseline}
.area2-future{height:1.2em;overflow:hidden;position:absolute;right:0px;top:0;}
.area2-tomorrow{width:100%;white-space:nowrap;overflow:hidden;text-overflow:ellipsis\9}
.area2-bg{position:absolute;height:20px;left:130px;width:30px;top:0;}
.weather .area .ico-weather{margin:0px 2px} 
.weather .setting{position:absolute;width:100%;top:25px;zoom:1}
.weather .setting .split{margin:0 6px}
.weather .setting p{margin:7px 8px}
.weather .setting .arr{position:absolute;top:-12px;_top:-10px;font-size:30px;line-height:30px;height:15px;overflow:hidden}
.weather .setting .ico-close{position:absolute;right:5px;top:7px;cursor:pointer}
.weather .setting select{margin-left:0px;margin-right:5px}
.ico{display:-moz-inline-box;-moz-box-align:center;display:inline-block;zoom:1;vertical-align:middle;width:16px;height:16px;background-image: url(${contextPath}/resources/aol/images/weather.png);background-repeat:no-repeat;_vertical-align:baseline}
.ico-arr-d{background-position:-3px 0px;_background-position:-3px 2px;cursor:pointer}
.ico-close{background-position:-21px 0px;width:11px;height:11px}
/* 链接颜色 */
.txt-link,
a{color:#008810;text-decoration:none}
a:hover{text-decoration:underline}
/* 背景颜色 */
.p-bg-set{background-color:#333;color:#fff;}
.p-bg-set a{color:#fff;}
/* 模拟弹出框向上箭头颜色值和bg-side的颜色值一致 */
.p-arr-set{color:#333}
.weather .ext a:hover,
.weather .setting a:hover{text-decoration:underline}
</style>
<script language="javascript">
<!--

function readCookie(name) { 
	var search; 
	search = name + "="; 
	offset = document.cookie.indexOf(search); 
	if (offset != -1) { 
		offset += search.length;
		end = document.cookie.indexOf(";", offset);
		if (end == -1){ 
			end = document.cookie.length; 
		} 
		return unescape(document.cookie.substring(offset, end)); 
	}else{ 
		return ""; 
	} 
}

function writeCookie(name, value) { 
	exp = new Date(); 
	exp.setTime(exp.getTime() + (86400 * 1000 * 30)); 
	document.cookie = name + "=" + escape(value) + "; expires=" + exp.toGMTString() + "; path=/"; 
}

	isGetWeather = false;
	
	function fMod(sType,oData){
		try{
			var oWelcome = top.JS5.get("welcome.WelcomeModule", {});
			if(sType == "msg"){
				oWelcome.msgbox(oData);
			}else{
				oWelcome.weather(sType,oData);
			}
		} catch(e) {
		}
	}
	
	function fSetCity(oArea){
		var sArea = oArea.value;
		var oForm = oArea.form;
		var oCity = oForm.city;
		var sCity;
		var oMap = citymap[sArea];
		oCity.options.length = 1;
		if(oMap){
			for(sCity in oMap){
				var sCityArray = sCity.split("@@");
				oCity.options[oCity.length] = new Option(sCityArray[1],sCity);
			}
			if(oCity.length > 1){
				oCity.value = oCity.options[1].value;
			}
		}
	}
	//得到城市Code
	function fGet(){
		var oForm = document.forms["set"];
		var oCity = oForm.city;
		var sCity = oCity.value;
		return sCity;
	}
	function fSet(){
		var sCity = fGet();
		if(sCity){
			fCloseSet();
			fView(sCity);
			if(isGetWeather){
				writeCookie("WEATHER_DEFAULT_CITY", sCity);
			}
		}else{
			parent.jAlert('请选择您要设置的城市！','提示');
		}
	}
	function fView(sCity){
		sCity = sCity || fGet();
		if(sCity){
			fCloseSet();
		
			var formObj = "cityCode="+sCity;
	    	$.ajax({
		    	url:'${contextPath}/management/home/loadWeather',
		    	type:'post',
		    	data:formObj,
				cache:false,
				success:function(response){
		    		var resultObj = eval("(" + response + ")");

					if(resultObj.isSuccess){
			      		document.getElementById("weatherDiv").innerHTML = "";
			        	$('#weatherDiv').html(resultObj.resultdata);
			        	isGetWeather = true;
					} else {
						isGetWeather = false;
						if(resultObj.resultdata){
							document.getElementById("weatherDiv").innerHTML = "";
				        	$('#weatherDiv').html(resultObj.resultdata);
						} else {
							parent.jAlert('获取天气失败！','提示');
						}
					}
				}
		    });
		}else{
			new parent.$.Msgbox({"content":"请选择您要设置的城市！"})
		}
	}
	function fCommonGetScript(sUrl, sCharset){
		var oJs = document.createElement("script");
		oJs.setAttribute("src", sUrl);
		oJs.setAttribute("charset", sCharset || "utf-8");
		oJs.setAttribute("type", "text/javascript");
		document.body.appendChild(oJs);
		return true;
	}

	function fSwitchSet(){
		if(!window.gCitymapJs){
			fCommonGetScript('${contextPath}/resources/aol/js/citymap20141121.js');
		}
		var oTar = document.getElementById('more-city');
		oTar.style.display = (oTar.style.display == 'none' ? 'block' : 'none');
		var sHeight = 'auto';
		sHeight = (oTar.style.display == 'none' ? '69px' : 'auto');
		fFixHeight(sHeight);
	}

	function fCloseSet(){
		document.getElementById('more-city').style.display = 'none';
		fFixHeight('69px');
	}
//-->
</script>

</head>
<body class="ua-noYaHei">
<div class="weather">
	<div class="normal">
		<div id="weatherDiv">

			<script language="javascript">
				var _formObj = "101200101@@武汉";
				var _default_city = readCookie("WEATHER_DEFAULT_CITY");
				if(_default_city){
					_formObj = _default_city;
				} else {
					_formObj = "101200101@@武汉";
				}
				
				$.ajax({
			    	url:'${contextPath}/management/home/loadWeather',
			    	type:'post',
			    	data:'cityCode='+_formObj,
					cache:false,
					success:function(response){
			    		var resultObj = eval("(" + response + ")");
			    		
						if(resultObj.isSuccess){
				      		document.getElementById("weatherDiv").innerHTML = "";
				        	$('#weatherDiv').html(resultObj.resultdata);
				        	isGetWeather = true;
						} else {
							isGetWeather = false;
							if(resultObj.resultdata){
								document.getElementById("weatherDiv").innerHTML = "";
					        	$('#weatherDiv').html(resultObj.resultdata);
							} else {
								parent.jAlert('获取天气失败！','提示');
							}
						}
					}
			    });
			</script>
		  
		</div>
		<div class="setting p-bg-set" id="more-city" style="display:none;">
			<span class="arr p-arr-set">▲</span>
			<p>选择地点：</p>
			<form name="set">
				<p>
					<select id="areaSelect" onchange="fSetCity(this)">
						<option>选地区</option>
					</select>
					<select name="city">
						<option value="">请选择城市</option>
					</select>
				</p>
			</form>
			<p><a href="javascript:void(0)" onclick="fView();return false;" title="查看天气">查看天气</a><span class="split">|</span><a href="javascript:void(0)" onclick="fSet();return false;" title="设为默认">设为默认</a></p>
			<b onclick="fCloseSet()" class="ico ico-close" title="关闭"></b>
		</div>
	</div>
</div>
<script>
	window.onload = function(){
		try{
			window.oWeatherIfr = false;
			if(top && top.document.getElementById('weatherIfr')){
				window.oWeatherIfr = top.document.getElementById('weatherIfr');
				fFixHeight('69px');
			}
		}catch(e){
			window.oWeatherIfr = false;
		}
	};
	
	function fFixHeight(s){
		if(window.oWeatherIfr){
			oWeatherIfr.style.height = s;
		}else{
			return;
		}
	}
	
	try{
		var sCls = 'ua-noYaHei';
		var sBodyCls = top.document.body.className;
		if((/ua-noYaHei/ig).test(sBodyCls)){
			document.body.className += sCls
		}
	}catch(e){
	}
</script>
	
</body>
</html>








