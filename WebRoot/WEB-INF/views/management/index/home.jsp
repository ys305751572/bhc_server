<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title></title>
	<%@ include file="/WEB-INF/views/scripts.jsp"%>
	<link href="${contextPath}/resources/aol/css/homeStyle.css" rel="stylesheet" type="text/css" />
	<link href="${contextPath}/resources/aol/css/common.css" rel="stylesheet" type="text/css" />
	<script src="${contextPath}/resources/aol/js/focus.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
		function gotoPage(url){
			window.location.href = url;
		}
	</script>
</head>
<body>

<div id="main-content" class="main-content scroll" style="width: 100%;height: 500px;">

<div class="main-center">
	<div class="center-panel">
		<div class="panel-tab">
			<div class="panel-tab-borderBottom">
			</div>
			<div class="panel-tab-a panel-tab-onA" style="display: block; margin-left: 0;">
				<div class="panel-tab-borderLeft"></div>
				<div class="panel-tab-text">健康推荐</div>
			</div>
		</div>
		
		<div class="panel-info" style="padding-bottom: 5px;">

			<div class="sub_box">
				<div id="p-select" class="sub_nav">
					<c:if test="${!empty adsMore}">
						<div class="sub_more"><a href="javascript:function(){}" onfocus="this.blur()" title="查看更多广告信息" style="font-size:12px;"> 更多>>&nbsp; </a></div>
					</c:if>
					<div class="sub_no" id="bd1lfsj">
						<ul>
							<c:choose>  
							    <c:when test="${!empty adsList}">
							    	<c:forEach var="ads" items="${adsList}" varStatus="status" >
							    		<li <c:if test="${status.index == 0}">class="show"</c:if> > ${status.index + 1} </li>
							    	</c:forEach>
							    </c:when>  
							    <c:otherwise>  
							    	<li class="show">1</li>
									<li>2</li>
									<li>3</li>
									<li>4</li>
							    </c:otherwise>
							</c:choose>
							<li></li>
						</ul>
					</div>
				</div>
				
				<div id="bd1lfimg">
					<div>
						<c:choose>  
						    <c:when test="${!empty adsList}">
						    	<c:forEach var="ads" items="${adsList}" varStatus="status" >
						    		<dl <c:if test="${status.index == 0}">class="show"</c:if> >
										<dt><a href="${ads.adsLink}" target="_blank"><img src="${contextPath}${ads.imageUrl}" alt="${ads.title}"></a></dt>
										<dd>
											<h2><a href="${ads.adsLink}" target="_blank">${ads.title}</a></h2>
											<tt>${ads.adsDesc}</tt>
										</dd>
									</dl>
						    	</c:forEach>
						    </c:when>  
						    <c:otherwise>  
						    	<dl class="show">
									<dt><a href="http://www.bioland.com.cn/index.php" target="_blank"><img src="${contextPath}/advert/1.jpg" alt="移动血糖仪"></a></dt>
									<dd>
										<h2><a href="http://www.bioland.com.cn/index.php" target="_blank">移动血糖仪</a></h2>
										<tt><a href="http://www.bioland.com.cn/index.php" target="_blank">移动血糖仪…</a></tt>
									</dd>
								</dl>
								<dl>
									<dt><a href="http://www.bioland.com.cn/index.php" target="_blank"><img src="${contextPath}/advert/2.jpg" alt="智能互联血糖仪"></a></dt>
									<dd>
										<h2><a href="http://www.bioland.com.cn/index.php" target="_blank">智能互联血糖仪</a></h2>
										<tt><a href="http://www.bioland.com.cn/index.php" target="_blank">智能互联血糖仪…</a></tt>
									</dd>
								</dl>
								<dl>
									<dt><a href="http://www.bioland.com.cn/index.php" target="_blank"><img src="${contextPath}/advert/3.jpg" alt="移动互联体温计"></a></dt>
									<dd>
										<h2><a href="http://www.bioland.com.cn/index.php" target="_blank">移动互联体温计</a></h2>
										<tt><a href="http://www.bioland.com.cn/index.php" target="_blank">移动互联体温计…</a></tt>
									</dd>
								</dl>
								<dl>
									<dt><a href="http://www.bioland.com.cn/index.php" target="_blank"><img src="${contextPath}/advert/4.jpg" alt="移动互联血压计"></a></dt>
									<dd>
										<h2><a href="http://www.bioland.com.cn/index.php" target="_blank">移动互联血压计</a></h2>
										<tt><a href="http://www.bioland.com.cn/index.php" target="_blank">移动互联血压计…</a></tt>
									</dd>
								</dl>
						    </c:otherwise>
						</c:choose>
						<dl></dl>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>


<div class="main-right">
	<div class="weather-div">
		<div class="weather-info">
			<div class="weather-info-nav">
				<div class="weather-info-nav-a weather-info-nav-onA">
					<div class="weather-info-nav-aText">天气</div>
				</div>
				
			</div>
			
			<div class="weather-info-line"></div>
			
			<div class="weather-info-cnt">
				<div class="weather-info-cnt-each" style="display: block;">
					<iframe id="weatherIfr" src="${contextPath}/management/home/weatherPage" allowtransparency="allowTransparency" class="gWel-weatherIfr" frameborder="0" scrolling="no" style="height: 220px; width: 230px; z-index: 1;"></iframe>
				</div>
			</div>
		</div>
	</div>
	
	<div style="margin-top: 130px; padding-bottom: 50px;">	
		<div class="upShow" style="">
		</div>
		
		<div class="downShow" style="">
			<a class="downShow-imgLink">
				<img class="downShow-imgLink-img" src="${contextPath}/resources/aol/images/help_03.png" alt="">
			</a>
			
			<div class="downShow-textLink">
				<a class="downShow-textLink-a txt-link" href="javascript:gotoPage('${contextPath}/management/leavemsg/leavemessage');" >在线留言</a>
			</div>
			
			<div class="downShow-textTips txt-tips">您的疑问我们将及时解答</div>
		</div>
	</div>
</div>

</div>

<script type="text/javascript">movec();</script>
</body>
</html>


