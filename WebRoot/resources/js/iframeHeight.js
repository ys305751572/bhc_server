
$(document).ready(function(){ 
if($(window.parent.document).find("#main")){
var realHeight=0;  
 if (navigator.userAgent.indexOf("Firefox")>0||navigator.userAgent.indexOf("Mozilla")>0||navigator.userAgent.indexOf("Safari")>0) { // Mozilla, Safari, ...  
  realHeight=window.document.documentElement.offsetHeight + 2;
 } else if (navigator.userAgent.indexOf("MSIE")>0) { // IE  
   var bodyScrollHeight = window.document.body.scrollHeight + 21;
   var elementScrollHeight = window.document.documentElement.scrollHeight + 1; 
    realHeight = bodyScrollHeight;  
 }else{
  realHeight=window.document.body.scrollHeight + window.document.body.clientHeight + 1;  
 }  
//$(window.parent.document).find("#main").load(function(){
var main = $(window.parent.document).find("#main");
var wheight =  window.screen.availHeight-350;
var thisheight = realHeight<wheight?wheight:realHeight;
main.height(thisheight);
//});
}
});
