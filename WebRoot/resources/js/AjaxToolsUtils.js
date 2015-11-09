/*
url:${contextPath}/management/ajaxUtils/getReviewOrgJSON
para:[{"name":"1111","value":"22222"}]
*/
function AjaxUtils(url,para,callback){
	var p = "";
	if(para){
		for(var count=0;count<para.length;count++){
			if(p=="")
				p = para[count].name+"="+para[count].value;
			else
				p = p + "&"+para[count].name+"="+para[count].value;	
		}
	};
	$.ajax({
		type: "POST",
		url: url,
		cache: false,
		data: p,
		success: function(msg){
			if(callback){
				callback(msg);
			}
		}
	});
}