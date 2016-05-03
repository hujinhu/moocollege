var Calendar = function(id){
	
	this.id ="calendar";
	this.date= new Date();
	this.year=this.date.getFullYear();
	this.monthx=this.date.getMonth()+1;
	this.month =(this.monthx<10 ? "0"+this.monthx:this.monthx);
	this.maxyear=2999;
	this.maxmonth =12;
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
	if(this.startyear==this.maxyear&this.monthx>=this.maxmonth){//当最大年月==起始年月  左右按钮都禁止使用
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
	else if(this.startyear>0&this.year == this.startyear&this.monthx>0&this.month==this.monthx){//当起始年月等于当前年月，左边减月禁止使用
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
			var temp_min;
			var temp_max;
			var size=Object.keys(dateMap).length;
			var key=instance.year+"/"+instance.month+"/"+this.id;
			
			if(dateMap[key]!=undefined){//
				if(key!=minDate && key!=maxDate){
					//alert("不能取消这天");
					layer.open({  content: '不能取消这天', time: 2 });
					return;
				}
				
			}else{
				
				if(size==0){
					minDate=key;
					maxDate=key;
				}else{
					if(new Date(key).getTime()<=new Date(minDate).getTime()){//选中前面
						temp_min=key;
						temp_max=maxDate;
						if(dateMap[minDate]=='on3'){
							layer.open({  content: '时间不连续', time: 2 });
							return;
						}
					}else{//选中后面
						temp_max=key;
						temp_min=minDate;
						if(dateMap[maxDate]=='on2'){
							layer.open({  content: '亲,请假时间需连续哟!', time: 2 });
							return;
						}
					}
					
					var days = new Date(temp_max).getTime() - new Date(temp_min).getTime();
					var time = parseInt(days / (1000 * 60 * 60 * 24)+1);
					if(time!=size+1){
						layer.open({  content: '亲,请假时间需连续哟!', time: 2 });
						return;
					}else{
						minDate=temp_min;
						maxDate=temp_max;
					}
				}
			}
			
			var showday=true;
			if($(this).hasClass("checked")){//取消选中
				$("#day_des").hide();
				$("#month_des").show();
				$(this).removeClass("checked");
				showday =false;
				
				delete dateMap[key];
				
				minDate=getMDate(dateMap)['min'];
				maxDate=getMDate(dateMap)['max'];
			}
			else{//选中
				$("#sw").show();
				$("#xw").show();
				
				$("#sw").removeClass('cur');
				$("#xw").removeClass('cur');
				$("#mm").html(this.id+'<span class="hui08 f12 plr1">号</span>');
				
				$("#s1").val("00:00");
				$("#s2").val("23:00");
				
				$(this).removeAttr("class");
				$(this).addClass("checked");
				$("#day_des").show();
				$("#month_des").hide();
				showday =true;
				last_obj=this;
				last_key=key;
				dateMap[key]='on4';
				if(Object.keys(dateMap).length>1){
					if(key==minDate){
						$("#sw").hide();
						$("#xw").show();
					}else if(key==maxDate){
						$("#sw").show();
						$("#xw").hide();
					}
				}
			}
			instance.dayclick(this,showday,instance.year,instance.month,this.id);
			var sum=0;
			for(k in dateMap){
				if(dateMap[k]=='on2' || dateMap[k]=='on3'){
					sum=sum+0.5;
				}else{
					sum=sum+1;
				}
			}
			$("#num").html(sum+"天");
			
			if(Object.keys(dateMap).length>1){
				$("#s1").val("00:00");
				$("#s2").val("23:00");
				$("#sele").hide();
			}else{
				$("#sele").show();
				$("#sw").show();
				$("#xw").show();
			}
			
			console.log(minDate);
			console.log(maxDate);
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

function getMDate(map){
	var max='';
	var min='';
	var m={};
	var i=0;
	for(key in map){  
		if(i==0){
			min=key;
			max=key;
		}
		
		if(new Date(key)<=new Date(min)){
			min=key;
		}
		
		if(new Date(key)>=new Date(max)){
			max=key;
		}
		i++;
	}
	 m['max']=max;
	 m['min']=min;
	 return m;
}