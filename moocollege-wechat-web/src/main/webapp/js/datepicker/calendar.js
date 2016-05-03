var Calendar = function(id){
	
	this.id ="calendar";
	this.date= new Date();
	this.year=this.date.getFullYear();
	this.monthx=this.date.getMonth()+1;
	this.month =(this.monthx<10 ? "0"+this.monthx:this.monthx);
	this.maxyear=this.date.getFullYear();
	this.maxmonth =(this.monthx<10 ? "0"+this.monthx:this.monthx);
	this.startyear=-1
	this.startyear=-1;
	this.day = this.date.getDate()
	this.signdaylist=[];
	this._skeleton = $("#"+this.id+"");
	window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", orientationChange, false); 
	var host =this;
	function orientationChange(){   
		var num =window.orientation;
		host.renderWidth();
		 
	}   
}

 /**
         * 获取上一个月
         *
         * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
         */
Calendar.prototype.getPreMonth=function (date){
            var arr = date.split('-');
            var year = parseInt(arr[0]); //获取当前日期的年份
            var month =parseInt(arr[1]); //获取当前日期的月份
            var day = parseInt(arr[2]); //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中月的天数
            var year2 = year;            
            var month2 = parseInt(month) - 1;
            if (month2 == 0) {
                year2 = parseInt(year2) - 1;
                month2 = 12;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {
                day2 = days2;
            }
           
            //var t2 = year2 + '-' + month2 + '-' + day2;
            return {year:year2,month:month2}
        }
        
        /**
         * 获取下一个月
         *
         * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
         */        
Calendar.prototype.getNextMonth=function (date){
            var arr = date.split('-');
           var year = parseInt(arr[0]); //获取当前日期的年份
            var month =parseInt(arr[1]); //获取当前日期的月份
            var day = parseInt(arr[2]); //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中的月的天数
            var year2 = year;
            var month2 = parseInt(month) + 1;
            if (month2 == 13) {
                year2 = parseInt(year2) + 1;
                month2 = 1;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {
                day2 = days2;
            }
            
        
           // var t2 = year2 + '-' + month2 + '-' + day2;
            return  {year:year2,month:month2};
        }
Calendar.prototype.premonth=function(year,month){
	alert(""+year+"年"+month+"月")
}
Calendar.prototype.nextmonth=function(year,month){
	//alert(""+year+"年"+month+"月")
}
Calendar.prototype.dayclick=function(li,showday,year,month,day){
	//alert(""+year+"年"+month+"月"+day+"日")
}

Calendar.prototype.render=function(){
	if(this.month*1<10){
		this.month="0"+this.month*1;
	}
	this._skeleton.html("");
	this.date= new Date(""+this.year+"-"+this.month+"")
	var date=this.date;
	
	var maxday =new Date(this.year,this.month,0).getDate();
	var formatdate=""+this.year+"-"+this.month+"-"+this.day+"";
	var premonth=this.getPreMonth(formatdate);
	var premonth_maxday = new Date(premonth.year,premonth.month,0).getDate();
    var nextmonth=this.getNextMonth(formatdate);
	var nextmonth_maxday = new Date(nextmonth.year,nextmonth.month,0).getDate();

	
	/*获取当月第一天是星期几*/
	var firstweekday_data = this.date;
	firstweekday_data.setDate(1);
	var firstweekday =firstweekday_data.getDay();
	/*获取当月最后一天是星期几*/
	var lastweekday_data = this.date;
	lastweekday_data.setDate(maxday);
	var lastweekday_data =lastweekday_data.getDay();
	
	var monthstr = this.month;
	if(this.month*1<10) {monthstr="0"+this.month*1}
	var btndiv  =  document.createElement("div");
	btndiv.className="btndiv";
	var xdwdd=monthstr+"";
	if(monthstr*1<10){
	     xdwdd="0"+monthstr*1;
	}
	btndiv.innerHTML="<a class='leftbtn'><</a>"+
	
					  "<span class='text'>"+this.year+"年"+xdwdd+"月</span>"+
					  "<a  class='rigtbtn'>></a>";
	this._skeleton.append(btndiv);
	var instance = this;
	if(this.startyear==this.maxyear&this.startmonth>=this.maxmonth){//当最大年月==起始年月  左右按钮都禁止使用
		$(".rigtbtn",this._skeleton).addClass("gray");
		$(".leftbtn",this._skeleton).addClass("gray");
	}
	else if(this.year == this.maxyear&this.month==this.maxmonth){//当前选择的年月等于最大年月，>右边加月禁止使用
		$(".rigtbtn",this._skeleton).addClass("gray");
		$(".leftbtn",this._skeleton).click(function(){
			instance.year=premonth.year;
			instance.month=premonth.month;
			instance.premonth(instance.year,instance.month);
		});
	}
	else if(this.startyear>0&this.year == this.startyear&this.startmonth>0&this.month==this.startmonth){//当起始年月等于当前年月，左边减月禁止使用
		$(".leftbtn",this._skeleton).addClass("gray");
		$(".rigtbtn",this._skeleton).click(function(){
			instance.year=nextmonth.year;
			instance.month=nextmonth.month;
			instance.nextmonth(instance.year,instance.month);
		});
	}
	else{
		
		
		$(".leftbtn",this._skeleton).click(function(){
			instance.year=premonth.year;
			instance.month=premonth.month;
			instance.premonth(instance.year,instance.month);
		});
		$(".rigtbtn",this._skeleton).click(function(){
			instance.year=nextmonth.year;
			instance.month=nextmonth.month;
			instance.nextmonth(instance.year,instance.month);
		});
	
	}
	
	var weekdiv =  document.createElement("div");
	weekdiv.className="weekdiv";
	weekdiv.innerHTML="<span>日</span>"+
					  "<span>一</span>"+
					  "<span>二</span>"+
					  "<span>三</span>"+
					  "<span>四</span>"+
					  "<span>五</span>"+
					  "<span>六</span>";
	this._skeleton.append(weekdiv);
	
	var ul =document.createElement("ul");
	this._skeleton.append(ul);
	
	var pre_inday=premonth_maxday-firstweekday+1;
	
	for(var i=pre_inday;i<premonth_maxday+1;i++){ 

		var li = document.createElement("li");
		var span = document.createElement("span");
		span.innerHTML=i;		
		$(li).addClass("gray");
		$(li).append(span);
		$(ul).append(li);
	}	
	
	for(var i=0;i<maxday;i++){ 
		var li = document.createElement("li");
		var span = document.createElement("span");
		span.innerHTML=i+1;		
		li.id=i+1;
		var signlist =  this.signdaylist;
		for(key in signlist){
			if(key==(i+1)){
				$(li).addClass("on"+signlist[key]+"");
			}
		}
		
		$(li).click(function(){		
			var showday=true;
			if($(this).hasClass("checked")){
				$("#day_des").hide();
				$("#month_des").show();
				$(this).removeClass("checked");
				showday =false;
			}
			else{
				$(this).addClass("checked");
				$("#day_des").show();
				$("#month_des").hide();
				showday =true;
			}
			instance.dayclick(this,showday,instance.year,instance.month,this.id);
		});
		$(li).append(span);
		$(ul).append(li);
	}
	
	
	for(var i=1;i<(7-lastweekday_data);i++){ 

		var li = document.createElement("li");
		var span = document.createElement("span");
		span.innerHTML=i;
		
		$(li).addClass("gray");
		$(li).append(span);
		$(ul).append(li);
	}	
	
	this.renderWidth();
	
}

Calendar.prototype.renderWidth=function(){
	var ul_width = $("ul",this._skeleton).width();
	var li_width = ul_width/7;
	$("span",$(".weekdiv")).width(li_width);
	$(".leftbtn",$(".btndiv")).width(li_width);
	$(".rigtbtn",$(".btndiv")).width(li_width);
	$(".text",$(".btndiv")).width(ul_width-li_width*2);
	$("li",this._skeleton).width(li_width);
}