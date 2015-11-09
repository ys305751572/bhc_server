
//通过水资源分区id返回value值
function getSzyfqValue(value,row,index) {
	var url = "/SZYTZ/management/cjw/szy/szyfq/getValue?szyfqId=" + value;
	var value = "";
	$.ajax( {
		type : "GET",
		url : url,
		success : function(data) {
		//alert(data.formatterValue);
		value = data.formatterValue;
		}
	});
	return value;
}