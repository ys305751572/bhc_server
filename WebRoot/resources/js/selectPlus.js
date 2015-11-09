var _CalF = {
   $ : function(object){//选择器
     if(object === undefined ) return;
     var getArr = function(name,tagName,attr){
           var tagName = tagName || '*',
               eles = document.getElementsByTagName(tagName),
               clas = (typeof document.body.style.maxHeight === "undefined") ? "className" : "class";//ie6
               attr = attr || clas,
               Arr = [];
           for(var i=0;i<eles.length;i++){
             if(eles[i].getAttribute(attr)==name){
               Arr.push(eles[i]);
             }
           }
           return Arr;
         };
   
     if(object.indexOf('#') === 0){  //#id 
       return document.getElementById(object.substring(1));
     }else if(object.indexOf('.') === 0){  //.class
       return getArr(object.substring(1));
     }else if(object.match(/=/g)){  //attr=name
       return getArr(object.substring(object.search(/=/g)+1),null,object.substring(0,object.search(/=/g)));
     }else if(object.match(/./g)){ //tagName.className
       return getArr(object.split('.')[1],object.split('.')[0]);
     }
   },
   addHandler:function(node, type, handler){
       node.addEventListener ? node.addEventListener(type, handler, false) : node.attachEvent('on'+ type, handler);
   },
   removeHandler: function (node, type, handler) {
       node.removeEventListener ? node.removeEventListener(type, handler, false) : node.detachEvent("on" + type, handler);
   },
   getPosition : function(obj) { //获取元素在页面里的位置和宽高
      var top = 0,
          left = 0,
          width = obj.offsetWidth,
          height = obj.offsetHeight;
  
      while(obj.offsetParent){
          top += obj.offsetTop;
          left += obj.offsetLeft;
          obj = obj.offsetParent;
      }
  
      return {"top":top,"left":left,"width":width,"height":height};
   },
   addClass:function(c,node){  // 添加样式名
       node.className = node.className + ' ' + c;
   },
   removeClass:function(c,node){ // 移除样式名
       var reg = new RegExp("(^|\\s+)" + c + "(\\s+|$)","g");
       node.className = node.className.replace(reg, '');
   },
   stopPropagation:function(event){  // 阻止冒泡
       var event = event || window.event;
       event.stopPropagation ? event.stopPropagation() : event.cancelBubble = true;
   },
   getStyle : function(obj, attr){  //获取css属性
    if(obj.currentStyle){
      return obj.currentStyle[attr];
    }else{
      return getComputedStyle(obj, false)[attr];
    }
   },
   ie6 : function(){
      return !!window.ActiveXObject && !window.XMLHttpRequest;
   }
 
 };

var cityData = ['王一雄|wangyixiong|wyx','肖进刚|xiaojingang|xjg', '王毅|wangyi|wy',  '秦商|qinshang|qs', '柳辰飞|liuchenfei|lcf', '韩千叶|hanqianye|hqy',
    '夏舒征|xiasuzheng|xsz', '花错|huacuo|hc', '西风|xifeng|xf', '华诗|huashi|hs', '马达|mada|md', '赵小雪|zhaoxiaoxue|zxx','薛文泉|xuewenquan|xwq',
    '丁建伟|dingjianwei|djw', '凡小芬|fanxiaofen|fxf','王丽|wangli|wl'];


var iCity = {
  inputSelector : function(id){ //input添加点击事件
      var input =document.getElementById(id);
      _CalF.addHandler(input,'click',function(){
          iCity.input = input;
          popList.style.visibility = "hidden";
          iCity.outClick();
          iCity.aClick();
          iCity.getPosition();
      });
      _CalF.addHandler(input,'keyup',function(event){
            var event = event || window.event,
                keycode = event.keyCode;
            popCity.style.visibility = "hidden";
            iCity.createUL();
      });
  },
  _temp : [
    "<h1>选择项目负责人(支持汉字/拼音/拼音缩写)</h1>",
    "<ul id='popMenu'>",
    "<li class='active'>热门负责人</li>",
    "<li>ABCDEFG</li>",
    "<li>HIGHLMN</li>",
    "<li>OPQRSTU</li>",
    "<li>VWXYZ</li>",
    "</ul>",
    "<div id='popCityCon'></div>"
  ],
  cityClass : function(){ //分类
      if(!this.citys){
        //{HOT:{hot:[],ABCDEFG:{a:[1,2,3],b:[1,2,3]},HIGHLMN:{},OPQRSTU:{},VWXYZ:{}}
        this.citys = {"hotCity":[],"ABCDEFG":{},"HIGHLMN":{},"OPQRSTU":{},"VWXYZ":{}};
        var make,fn,name,
            reg1 = /^[a-g]$/i, reg2 = /^[h-n]$/i, reg3 = /^[o-u]$/i,reg4 = /^[v-z]$/i;

        for(i in cityData){
          make = cityData[i].split("|");  //分割成数组
          fn = make[2].substring(0,1).toUpperCase();  //获取英文第一个字母转换成大写
          name = make[0];
          if(reg1.test(fn)){
            if(!this.citys.ABCDEFG[fn]) this.citys.ABCDEFG[fn]=[];
            this.citys.ABCDEFG[fn].push(name)
          }else if(reg2.test(fn)){
            if(!this.citys.HIGHLMN[fn]) this.citys.HIGHLMN[fn]=[];
            this.citys.HIGHLMN[fn].push(name)
          }else if(reg3.test(fn)){
            if(!this.citys.OPQRSTU[fn]) this.citys.OPQRSTU[fn]=[];
            this.citys.OPQRSTU[fn].push(name)
          }else if(reg4.test(fn)){
            if(!this.citys.VWXYZ[fn]) this.citys.VWXYZ[fn]=[];
            this.citys.VWXYZ[fn].push(name)
          }

          if(i<20){
            if(!this.citys.hotCity["HOT"]) this.citys.hotCity["HOT"]=[];
            this.citys.hotCity["HOT"].push(name);
          }
        }

      }
  },
  creatDiv : function(){
      var popCity = document.createElement("div");  //城市div
      popCity.id = "popCity";
      popCity.innerHTML = this._temp.join("");
      _CalF.addHandler(popCity,"click", _CalF.stopPropagation); //阻止事件冒泡

      var popList = document.createElement("div");  //输入提示div
       _CalF.addHandler(popList,"click", _CalF.stopPropagation); //阻止事件冒泡
      popList.id="popList";

      document.body.appendChild(popCity);
      document.body.appendChild(popList);
      this.popCity = popCity;
      this.popList = popList;
  },
  getData :function(){
      this.creatDiv();
      this.cityClass();
      var i,div,arr,dl,dt,dd,_tempDD,
          popCityCon = _CalF.$("#popCityCon"),
          data = this.citys;

      for(var i in data){
        div = document.createElement("div");
        div.id = i;
        if(div.id=="hotCity") div.className ="active";
        arr=[];

        for(var b in data[i]){
          arr.push(b);
          arr.sort();
        }

        for(var c in arr){
          dl = document.createElement("dl");
          dt = document.createElement("dt");
          dd = document.createElement("dd");
          dt.innerHTML = arr[c];

          _tempDD = [];
          for(var d in data[i][arr[c]]){
            _tempDD.push("<a>"+data[i][arr[c]][d]+"</a>");
          }
          dd.innerHTML = _tempDD.join("");

          dl.appendChild(dt);
          dl.appendChild(dd);
          div.appendChild(dl);
        }

        popCityCon.appendChild(div);
      }
      if(_CalF.ie6()){
        popCityCon.appendChild(this.createIframe());
        this.aHover();
      }
      this.tabClick();
  },
  tabClick : function(){
      var index,
          popMenu = _CalF.$("#popMenu"),
          popCityCon = _CalF.$("#popCityCon"),
          myIframe = _CalF.$("#myIframe"),
          popCity = _CalF.$("#popCity"),
          lis = popMenu.getElementsByTagName("li"),
          divs = popCityCon.getElementsByTagName("div");
      for(var i=0;i<lis.length;i++){
          lis[i].index = i;
          lis[i].onclick = function(){
              for(var j=0;j<i;j++){
                _CalF.removeClass("active",lis[j]);
                _CalF.removeClass("active",divs[j]);
              }
            _CalF.addClass("active",this);
            _CalF.addClass("active",divs[this.index]);
            if(_CalF.ie6()){myIframe.style.height = popCity.offsetHeight + 'px';};
          }
      }
  },
  aClick : function(){
      var that = this,
          popCityCon = _CalF.$("#popCityCon"),
          links = popCityCon.getElementsByTagName("a");
      for(var i in links){
        links[i].onclick = function(){
            that.input.value = this.innerHTML;
            that.popCity.style.visibility = "hidden";
        }        
      }
  },
  aHover : function(){
      var that = this,
          popCityCon = _CalF.$("#popCityCon"),
          links = popCityCon.getElementsByTagName("a");
      for(var i in links){
          links[i].onmouseover = function(){_CalF.addClass("active",this)}
          links[i].onmouseout = function(){_CalF.removeClass("active",this)}
      }
  },
  liClick :function(){
      var that = this,
          popList = that.popList,
          lis = popList.getElementsByTagName("li");
      for(var i=0, len = lis.length; i<len; i++){
          lis[i].onclick = function(){
             that.input.value = this.innerHTML;
             that.popList.style.visibility = "hidden";
          }
      }
  },
  liHover :function(){
      var that = this,
          popList = that.popList,
          lis = popList.getElementsByTagName("li");
      for(var i=0, len = lis.length; i<len; i++){
        lis[i].onmouseover = function(){_CalF.addClass("active",this)}
        lis[i].onmouseout = function(){_CalF.removeClass("active",this)}
      }
  },
  getPosition : function(){
      var Pos = _CalF.getPosition(iCity.input);
      popCity.style.top =  Pos.top + Pos.height + "px";
      popCity.style.left = Pos.left + "px";
      popCity.style.visibility = "visible";

      popList.style.top =  Pos.top + Pos.height + "px";
      popList.style.left = Pos.left + "px";
      popList.style.width = Pos.width + "px";
  },
  createIframe : function(){  //ie6创建iframe遮罩
       var myIframe =  document.createElement('iframe');
         myIframe.id = 'myIframe';
         myIframe.style.position = 'absolute';
         myIframe.style.zIndex = '-1';
         myIframe.style.left = '-1px';
         myIframe.style.top = 0;
         myIframe.style.border = 0;
         myIframe.style.filter = 'alpha(opacity= 0 )';
         myIframe.style.width = this.popCity.offsetWidth + 'px';
         myIframe.style.height = this.popCity.offsetHeight + 'px';
         this.myIframe = myIframe;
         return myIframe;
  },
  createIframe2 : function(){  //ie6创建iframe遮罩
       var myIframe2 =  document.createElement('iframe');
         myIframe2.id = 'myIframe2';
         myIframe2.style.position = 'absolute';
         myIframe2.style.zIndex = '-1';
         myIframe2.style.left = '-1px';
         myIframe2.style.top = 0;
         myIframe2.style.border = 0;
         myIframe2.style.filter = 'alpha(opacity= 0 )';
         myIframe2.style.width = this.popList.offsetWidth + 'px';
         myIframe2.style.height = this.popList.offsetHeight + 'px';
         return myIframe2;
  },
  createUL : function(){
      var value = this.input.value,
          popList = this.popList;
      if(value !==""){
        var ul = document.createElement('ul'),
            searchResult = [],
            reg = new RegExp("^" + value + "|\\|" + value, 'gi'),
            make,str;
        searchResult.push("<ul>");
        for(var i in cityData){
           if(reg.test(cityData[i])){ //含有字符串
              make = cityData[i].split("|");  //分割成数组
              str = "<li>"+make[0]+"</li>";
              searchResult.push(str);
           }
        }
        searchResult.push("</ul>");

        if(searchResult.length>2){
          popList.innerHTML = searchResult.join("");
          if(_CalF.ie6()){
            popList.appendChild(this.createIframe2());
            this.liHover();
          };
          this.liClick();
          popList.style.visibility = "visible";
        }

      }else{
          popList.style.visibility = "hidden";
      }
  },
  outClick:function(){  //鼠标在对象区域外点击隐藏
     var that = this;
     _CalF.addHandler(document, 'click',function(event){
         var event = event || window.event,
             target = event.target || event.srcElement;
         if(target == that.input || target==that.popCity || target==that.popList) return;
         that.popCity.style.visibility = "hidden";
         that.popList.style.visibility = "hidden";
     });
  }


};

iCity.getData();