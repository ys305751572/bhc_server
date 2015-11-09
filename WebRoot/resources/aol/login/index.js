$(function() {
	//解决图片刷新慢 利用滚动可视插件 一次加载两屏
	
	//当鼠标滚动了75px
    $('#scene_xty').bind('inview', function(event, visible) {
    	$("#scene_xyj").addClass("scene_xyj");
    });
    $('#scene_xyj').bind('inview', function(event, visible) {
    	$("#scene_ewq").addClass("scene_ewq");
    });
    $("#dx_line").stop().animate({ opacity: 0 });
    $('#dx_p3').bind('inview', function(event, visible) {
        if (visible) {
        	 $("#dx_line").stop().animate({ opacity: 1 });
        } else {
        	 $("#dx_line").stop().animate({ opacity: 0 });
        }
      });
      $('.footer').bind('inview', function(event, visible) {
          if (visible) {
        	  $(this).stop().animate({ opacity: 1 });
          } else {
        	  $(this).stop().animate({ opacity: 0 });
          }
       });
	//当鼠标滚动了75px
    $('.header').bind('inview', function(event, visible) {
        if (visible) {
          $(this).stop().animate({ opacity: 1 });
        } else {
          $(this).stop().animate({ opacity: 0 });
        }
      });
	$body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
	$("#goto_sy").click(function(){
		$body.animate({scrollTop: 0},1000);
		return false;// 返回false可以避免在原链接后加上#
	});
	$("#goto_xty").click(function(){
		$body.animate({scrollTop: 768},1000);
		return false;// 返回false可以避免在原链接后加上#
	});
	$("#goto_xyj").click(function(){
		$body.animate({scrollTop:1536},1000);
		return false;// 返回false可以避免在原链接后加上#
	});	
	$("#goto_ewq").click(function(){
		$body.animate({scrollTop:2320},1000);
		return false;// 返回false可以避免在原链接后加上#
	});
});