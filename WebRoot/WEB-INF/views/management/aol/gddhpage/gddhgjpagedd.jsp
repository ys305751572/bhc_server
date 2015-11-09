<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>自定义绘制点标记</title>
<link rel="stylesheet" type="text/css" href="http://developer.amap.com/Public/css/demo.Default.css" /> 
<script language="javascript" src="http://webapi.amap.com/maps?v=1.3&key=您申请的key值"></script>
<script language="javascript">
var mapObj,marker;
//初始化地图对象，加载地图
function mapInit(){
	var zb= window.parent.document.getElementById('zb').value;
	var msglist = zb.split("%");
	if(msglist==""){
		return;
	}
	//设置地图中心点
	var zx = msglist[1].split("*");
	mapObj = new AMap.Map("iCenter",{
    	view: new AMap.View2D({
      		center:new AMap.LngLat(zx[0],zx[1]),//地图中心点
      		zoom:13 //地图显示的缩放级别
    	})
    });
    var img = 0;//设置图片坐标
    for (var i=1;i<msglist.length;i++)
	{
		var zblist = msglist[i].split("*");
		addMarker(zblist[0],zblist[1],img);
		img = img - 28;
		if(i-2==10){
			return;
		}
	}
	//添加自定义点标记
	//addMarker();
}
 
//添加带文本的点标记覆盖物
function addMarker(var1,var2,var3){ 
   marker=new AMap.Marker({				  
	icon:new AMap.Icon({    //复杂图标
			size:new AMap.Size(28,37),//图标大小
			image:"http://webapi.amap.com/images/custom_a_j.png", //大图地址
			imageOffset:new AMap.Pixel(var3,0)//相对于大图的取图位置
		}),
	position:new AMap.LngLat(var1,var2)
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
