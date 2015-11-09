<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>自定义绘制点标记</title>
<link rel="stylesheet" type="text/css" href="http://developer.amap.com/Public/css/demo.Default.css" /> 
<script language="javascript" src="http://webapi.amap.com/maps?v=1.3&key=您申请的key值"></script>
<script language="javascript">
var mapObj,marker;
var hzb;
var zzb;
//初始化地图对象，加载地图
function mapInit(){
	hzb= window.parent.document.getElementById('hzb').value;
	zzb= window.parent.document.getElementById('zzb').value;
	mapObj = new AMap.Map("iCenter",{
    	view: new AMap.View2D({
      		center:new AMap.LngLat(hzb,zzb),//地图中心
      		zoom:13 //地图显示的缩放级别
    	})
    }); 	
	//添加自定义点标记
	addMarker();
}
 
//添加带文本的点标记覆盖物
function addMarker(){ 
    marker=new AMap.Marker({				  
	icon:new AMap.Icon({    //复杂图标
			size:new AMap.Size(28,37),//图标大小
			image:"http://webapi.amap.com/images/custom_a_j.png", //大图地址
			imageOffset:new AMap.Pixel(-28,0)//相对于大图的取图位置
		}),
	position:new AMap.LngLat(hzb,zzb)
	});
	marker.setMap(mapObj);  //在地图上添加点
	marker.setTitle('我是地图中心点哦~');
}
</script>
</head>
<body onLoad="mapInit()">
	<div id="iCenter" style="width:99%; height:446px;"></div>
</body>
</html>						
