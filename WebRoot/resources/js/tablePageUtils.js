(function($) {
	tablePageUtil = function(options) {
		var oTable = null;

		return me = {
			init : function() {
				var t = this;
				$(function() {
					if (oTable == null) { // 仅第一次检索时初始化Datatable
						oTable = $('.datatable').dataTable({
							"sDom" : "<'row-fluid'<'span6'l><'span6 right'f>r>t<'row-fluid'<'span12 right'i><'span12 center'p>>",
							"bAutoWidth" : false,
							/*
							 * //不自动计算列宽度 "aoColumns": [ //设定各列宽度 {"sWidth":
							 * "130px"}, {"sWidth": "120px"}, {"sWidth":
							 * "160px"}, {"sWidth": "110px"}, {"sWidth": "*"} ],
							 */
							"bProcessing" : true, // 加载数据时显示正在加载信息
							"bServerSide" : true, // 指定从服务器端获取数据
							"bFilter" : false, // 不使用过滤功能
							"bLengthChange" : true, // 用户不可改变每页显示数量
							"iDisplayLength" : 10, // 每页显示10条数据
							"sAjaxSource" : options.url,// 获取数据的url
							"fnServerData" : t.retrieveData, // 获取数据的处理函数
							"sPaginationType" : "full_numbers", // 翻页界面类型
							"bSort" : true,
							"oLanguage" : { // 汉化
								"sLengthMenu" : "每页显示 _MENU_ 条记录",
								"sZeroRecords" : "没有检索到数据",
								"sInfo" : "当前数据为从第 _START_ 到第 _END_ 条数据 总共有 _TOTAL_ 条记录",
								"sInfoEmtpy" : "没有数据",
								"sProcessing" : "正在加载数据...",
								"oPaginate" : {
									"sFirst" : "首页",
									"sPrevious" : "前页",
									"sNext" : "后页",
									"sLast" : "尾页"
								}
							}
						});
					}
				});
			},
			getDataTable:function(){
				return oTable;	
			},
			search : function() {
				// 刷新Datatable，会自动激发retrieveData
				oTable.fnDraw();
			},
			retrieveData : function(sSource, aoData, fnCallback) {
				// 格式：数组形式存入参数
				if (options.para) {
					var para = options.para;
					for (var i = 0; i < para.length; i++) {
						aoData.push({
									"name" : para[i],
									"value" : $('#' + para[i]).val()
								});
					}
				}
				$.ajax({
						type : "POST",
						contentType : "application/json",
						url : sSource,
						dataType : "json",
						cache : false,
						data : JSON.stringify(aoData),
						success : function(resp) {
							fnCallback(resp.returnObject);
						}
				});
			}
		};
	};
})(jQuery);