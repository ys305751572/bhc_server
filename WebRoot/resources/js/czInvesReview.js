var czTools = (function(window) {
	var tools = {};
	tools.strlength = function(strTemp) {
		var i, sum;
		sum = 0;
		for (i = 0; i < strTemp.length; i++) {
			if ((strTemp.charCodeAt(i) >= 0) && (strTemp.charCodeAt(i) <= 255))
				sum = sum + 1;
			else
				sum = sum + 2;
		}
		return sum / 2;
	};
	tools.dataTable = function(options) {
		var t = this;
		this.showIndex = options.showIndex?true:false;
		this.oTable = null;//dataTable对象
		this.columns = options.columns;//列对象
		this.searchArr = [];//搜索对象
		this.tableMove = {THMove:false,currX:0,lastX:0};//列拖动的必须元素
		this.autoIframeHeight = options.autoIframeHeight?true:false;//
		this.store;
		this.selectedRow ;
		this.bSort = true;
		this.fnComplete = options.fnComplete;
		if(options.bSort||options.bSort==false){
			this.bSort = options.bSort;
		}
		options.autoInit = options.autoInit==undefined||options.autoInit?true:false;
		this.init = function() {
			this.insertHTML();
			this.oTable = $('.datatable').dataTable({
				"sDom" : "<'span12' r>t<'row-fluid'<'datatable_tableLeft'i><'datatable_tableCenter'l><'datatable_tableRight'p>>",
				"bAutoWidth" : false,
				"bJQueryUI" : false,
				"sScrollX" : "100%",
				"bScrollCollapse" : options.bScrollCollapse||true,
				"aoColumns":options.aoColumns||null,//列
				"bProcessing" : options.bProcessing||true, // 加载数据时显示正在加载信息
				"bServerSide" : options.bServerSide||true, // 指定从服务器端获取数据
				"bFilter" : options.bFilter||false, // 不使用过滤功能
				"bLengthChange" : true, // 用户不可改变每页显示数量
				"iDisplayLength" : 10, // 每页显示10条数据
				"sAjaxSource" : options.url,// 获取数据的url
				"fnServerData" : options.fnServerData || this.fnServerData, // 获取数据的处理函数
				"sPaginationType" : "bootstrap", // 翻页界面类型
				"fnRowCallback" : options.fnRowCallback || this.fnRowCallback,
				"fnInfoCallback" : options.fnInfoCallback || this.fnInfoCallback,
				"fnPreDrawCallback" : options.fnServerData || this.fnPreDrawCallback,
				"fnDrawCallback":options.fnDrawCallback || this.fnDrawCallback,
				"bSort" :  this.bSort,
				"oLanguage" : options.oLanguage||{ //汉化
					"sLengthMenu" : "_MENU_条/页",
					"sZeroRecords" : "没有检索到数据",
					"sInfo" : "显示_START_条到_END_条,共_TOTAL_条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",
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
			return this.oTable;
		};
		this.insertHTML = function(){
			if(!options.render)//没有渲染目标,则不实现自动列渲染
				return;
			var htmlStr = '<div class="row-fluid sortable">';
				htmlStr+= '<div class="span12">';
				htmlStr+= '			<div class="box-content" style="OVERFLOW-Y: no; OVERFLOW-X: no;">';
				htmlStr+= '				<table class="table table-bordered table-hover datatable" style="table-layout: fixed;">';
				htmlStr+= '					<thead style="align:center;word-break: keep-all;white-space:nowrap">';
				htmlStr+= '						<tr>';
				if(this.showIndex)
					htmlStr+= '<th width="30px">序号</th>';
				for (var  i= 0; i < this.columns.length; i++) {
					var widthStr = this.columns[i].width?'width="'+this.columns[i].width+'"':'';
					htmlStr+= '<th '+widthStr+'>'+this.columns[i].text+'</th>';
				}
				htmlStr+= '						</tr>';
				htmlStr+= '					</thead>';
				htmlStr+= '				 <tbody>';
				htmlStr+= '				</tbody>';
				htmlStr+= '			</table>';
				htmlStr+= '		</div>';
				htmlStr+= '	</div>';
				htmlStr+= '</div>';
				
			$('#'+options.render).html(htmlStr);
		}
		this.fnPreDrawCallback = function() {
			return true;
		};
		this.columnResizeImpl = function(){
			if(!options.render)//没有渲染目标,则不实现拖动效果
				return;
			var findEle = '#'+options.render+' table:eq(1) tr td';
			$(findEle).bind("mousemove", function(event){//鼠标移动
				var th = $(this);
				 //不给第一列和最后一列添加效果
				 if (th.prevAll().length < 1 || th.nextAll().length < 1) {
				       return;
				 }
				 var left = th.offset().left;
				 //距离表头边框线左右4像素才触发效果
				 if (event.clientX - left < 10 || (th.width()-(event.clientX-left)) < 0) {
				     th.css({ 'cursor': 'e-resize' });
				 }else {
				     th.css({ 'cursor': 'default' });
				 }
			});
			$(findEle).bind("mousedown", function(event) {
				var left =  $(this).offset().left;
				if (event.clientX - left < 10 || ($(this).width()-(event.clientX-left)) < 0) {
					t.tableMove.THMove = true;
				}
			});
			$(findEle).bind("mousemove", function(event) {//鼠标移动并按下鼠标左键
			    if (t.tableMove.THMove) {
					var th = $(this);
					var cellIndex;
					var pos = th.offset();
					//总是取前一个TH对象
				    if (event.clientX - pos.left < th.width() / 2) {
				    	cellIndex = th.context.cellIndex-1
				    }else {
				    	cellIndex = th.context.cellIndex;
				    }
			    	t.tableMove.currX = event.clientX;
			    	if(t.tableMove.lastX==0)
			    		t.tableMove.lastX = event.clientX;
			    	var moveX = t.tableMove.currX-t.tableMove.lastX;
			    	t.tableMove.lastX = t.tableMove.currX;
			    	
			    	var tr1 = $('#'+options.render+' table:eq(0) tr th')[cellIndex];
			    	var tr2 = $('#'+options.render+' table:eq(1) tr th')[cellIndex];
			    	var num = (Number(tr1.style.width.split('px')[0])+moveX);
			    	if(!isNaN(num)){
				    	tr1.style.width = num+'px';
				    	tr2.style.width = num+'px';
			    	}
			     }
			});
			$(findEle).bind("mouseup", function(event) {//鼠标弹起，计数归零
				t.tableMove.THMove = false;
				t.tableMove.lastX = 0;
			});					
		}
		this.getSelectedRow = function(){//获取选中行的数据
			return t.selectedRow;
		}
		this.selectRowImpl = function(){
			if(!options.render)//没有渲染目标,则不实现拖动效果
				return;		
			var findEle = '#'+options.render+' table:eq(1) tr';
			$(findEle).click( function( e ) {
					var rowIndex = $(this).context.rowIndex-1;
					if ($(this).hasClass('datatable_row_selected') ) {
						$(this).children().each(function(){
							$(this).css({'background':''});
						});
						$(this).removeClass('datatable_row_selected');
						t.selectedRow = null;
					}else {
						var selectRow  = t.oTable.$('table:eq(1) tr.datatable_row_selected');//获取选中元素
						selectRow.children().each(function(){//选择的行移除背景
							$(this).css({'background':''});
						});//子TD移除背景
						selectRow.removeClass('datatable_row_selected');//选择的行移除背景
						$(this).children().each(function(){//当前行加上背景
							$(this).css({'background':'#cfefef'});
						})
						$(this).addClass('datatable_row_selected');
						t.selectedRow = t.store[rowIndex];
					}
			});
		}
		this.fnDrawCallback =function(){
			t.columnResizeImpl();//列拖动实现
			t.selectRowImpl();//实现选中行
			if(t.autoIframeHeight){
				if(window.parent.resetIframeHeight)//如果包含iframe,则改变高度
					window.parent.resetIframeHeight(t.oTable[0].clientHeight);
			}
			if(t.fnComplete)
				t.fnComplete();
		}
		this.fnInfoCallback = function(settingsObj, start, end, total,totalInList, formatStr) {
			// table下面的页码构建完成时触发
			return formatStr;
		};
		this.fnRowCallback = function(nRow, aData, iDisplayIndex, listIndex) {
			// 每一行构建完成时触发
			var splitLength = 10;// 截断长度
			for (var key in aData) {//截断长度过长的字符串
				var charLength = czTools.strlength(aData[key]);
				if (charLength > splitLength) {
					if(!t.columns[key]){
						continue;
					}
					if(t.columns[key].render)//如果是自定义列，则不进行过滤
						continue;
					if(!t.columns[key].isCutOff)//如果不需要截断，默认为true-->截断
						continue;
					var tmp = aData[key].substring(0, splitLength);
					$('td:eq(' + key + ')', nRow).html("<div title='" + aData[key] + "' style='overflow:hidden; text-overflow:ellipsis;word-break: keep-all;white-space:nowrap;'>" + aData[key] + "</div>");
				}
			}
		};
		this.getDataTable = function() {
			return oTable;
		};
		this.search = function(searchPara) {
			t.searchArr = [];
			//如果搜索对象集
			if(Object.prototype.toString.call(searchPara)=== '[object Array]'){
				this.searchArr = searchPara;
			}else{
				this.searchArr.push(searchPara);
			}
			// 刷新Datatable，会自动激发retrieveData
			t.oTable.fnDraw();
		};
		///刷新数据
		this.refresh = function(){
			t.oTable.fnDraw();
		}
		this.fnServerData = function(sSource, aoData, fnCallback) {
			/***实现排序***/
			var eEcho = 0;//显示的次数
			var bbSort = {"name":"bbSortName","value":""};//传入排序参数
			
			for(var i=0;i<aoData.length;i++){
				if(aoData[i].name == 'iSortCol_0'){//判断按第几列进行排序
					var tmp = t.columns[aoData[i].value].dataIndex;
					bbSort.value = tmp?tmp:"";
				}
				if(aoData[i].name == 'sEcho'){
					eEcho = aoData[i].value;
				}
			}
			if(eEcho == 1)//如果是第一次显示，则不默认排序
				bbSort.value = "";
			aoData.push(bbSort);
			/***实现搜索***/
			for (var j = 0; j < t.searchArr.length; j++) {
				aoData.push(t.searchArr[j]);
			}
			/***查询数据***/
			$.ajax({
					type : "POST",
					contentType : "application/json",
					url : sSource,
					dataType : "json",
					cache : false,
					data : JSON.stringify(aoData),
					success : function(resp) {
						var bbData = resp.returnObject.bbData;
						t.store = bbData; //存储数据源
						if(bbData&&t.columns)
							resp.returnObject.aaData = t.convertBbdata(t.columns,bbData);
						fnCallback(resp.returnObject);
					}
			});
		};
		this.convertBbdata = function(columns,bbData){
			var aaData = new Array();
//			_iDisplayLength: 10 //每页长度
//			_iDisplayStart: 0  //现在页码
			
			var nowPageIndex = t.oTable.fnSettings()._iDisplayStart;
			//alert(t.oTable.fnSettings()._iDisplayStart+" "+t.oTable.fnSettings()._iDisplayLength);
			for (var a = 0; a < bbData.length; a++) {
				var tmpData = new Array();
				if(t.showIndex){
					nowPageIndex++;
					tmpData.push(nowPageIndex);
				}
				for (var b = 0; b < columns.length; b++) {
					var row = bbData[a];
					var renderFun = columns[b].render;
					if(renderFun){//
						tmpData.push(renderFun(row));//返回自定义数组的当前行数据
						continue;
					}
						
					var pro = columns[b].dataIndex;//属性
					var tmp = row[pro];//根据列的属性获取每行json数据的数据
					tmpData.push(tmp?tmp:"");
				}
				aaData.push(tmpData);
			}
			return aaData;
		};
		if(options.autoInit)
			this.init();
		return this;
	}

	return tools;
})(window)