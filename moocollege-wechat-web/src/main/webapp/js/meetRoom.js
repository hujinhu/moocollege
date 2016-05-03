function init_Page() {  
        $.ajax({
        	type:"POST",
        	url:_webPath+"meetRoom/getMeetRoomAddr",
        	data:{
        	},
        	success:function(result){
        		var html="";
        		if(result.success){
        			var style = result.attributes.style_Addr;
        			if(style==2){
        				html+="<option id='' value=''>请选择</option>" ;
        				html+="<option id='' value='会议室(默认)'>会议室(默认)</option>" ;
        				
        			}else{
        			 html+="<option id='' value=''>请选择</option>";
        				var bb = result.obj;
        				for (var int = 0; int < bb.length; int++) {
            				var option_GGL = bb[int];
            				html+="<option id="+option_GGL.id+" value='"+option_GGL.addr+"'>"+option_GGL.addr+"</option>  ";
    					}
        			}
        			$("#makeupCoSe").html(html);
        		}else{
        			layer.open({
					    content: '数据加载失败,请重试',
					    time: 2
					});
        		}
        	}
        });
    }  
function init_IndexPage(type) {  
    $.ajax({
    	type:"POST",
    	url:_webPath+"meetRoom/getMeetRoomRecords?type="+type,
    	data:{
    	},
    	success:function(result){
    		var html="";
    		if(result.success){
    			var style_Addr = result.attributes.style_Addr;
    			if(style_Addr!=3){
    			jQuery.each(result.obj, function(i,item){     
    				html+="<li pid="+item.id+" onclick='detail_meet("+item.id+")' ><a href='"+_webPath+"meetRoom/detail?type="+type+"&&id="+item.id+"' onclick='approval_delPic(this)' class='sp-ico sp-del'> <div class='calander'><p class='t1'>"+item.yymm+"</p><p class='t2'>"+item.dd+"</p><p class='t3'>"+item.week+"</p></div>" 
    				html+="<div class='zhaiyao'> <h3>"+item.title+"</h3><p><span class='meetime'>"+item.startTime+"</span><span class='meetroom'>"+item.meetAddr+"</span></p> </div></a></li>";
    			});  
    			$("#meetinglist"+type).html(html);
    			}else{
    				html+="<li><div class='zhaiyao' > <h3>暂无预约会议信息</h3><p></p> </div></li>";
    			
    				$("#meetinglist"+type).html(html);
    			}
    		}else{
    			layer.open({
				    content: '数据加载失败,请重试',
				    time: 2
				});
    		}
    	}
    });
}  

function changeF()  
{  
	document.getElementById('makeupCo').value=  
	document.getElementById('makeupCoSe').options[document.getElementById('makeupCoSe').selectedIndex].value;  
}  
function addPerson(list) {
	var checkedCount = list.length;
	var currCount=0;
	if (checkedCount > 0) {
		for ( var i=0;i<checkedCount;i++) {
			currCount=$('#personList').children().length
			var id = list[i][pMap.id];
			//判断有没有重复的人
			if($("li[cuserDate$="+id+"]").length<=0){
				var name = list[i][pMap.name];
				var headImage = list[i][pMap.headImage];
				$("#personList").prepend("<li cuserDate="+id+"><a href='javascript:'  onclick='delPerson(this)' class='sp-ico sp-del'></a> <img src='"+_imgPath+headImage+"'> <p>"+name+"</p></li>");
			}
		}
	}
}
function delPerson(e){
	$(e).parent().remove();
}
function takeMeet(id_GGL,type_GGl){
	if(type_GGl=="2"){
		var content_   =$("#noTakeMeetWhy").val();
		if(content_==""||$.trim(content_).length<=0){
			layer.open({
				content: '不参加会议原因不能为空',
				time: 2
			});
			return;
		}else{
			$(".ui-dialog").removeClass("show");
		}
	}
	$.ajax({
		type:"POST",
		url:_webPath+"meetRoom/takeMeet",
		data:{
			id:id_GGL,
			type:type_GGl,
			content:$("#noTakeMeetWhy").val()
		},
		success:function(result){
			if(result.success){
				layer.open({
					 shadeClose : false,
				    btn: ['OK'],
				    content:'提交成功',
				    yes: function(index){
				        location=_webPath+"meetRoom/index?type=1";
				        layer.close(index)
				    }
				})
			}else{
				layer.open({
				    content: '预约会议取消失败',
				    time: 2
				});
			}
		}
	});
}
function cancelMeet(id_GGL){
	$.ajax({
		type:"POST",
		url:_webPath+"meetRoom/deletMeetRecord",
		data:{
			id:id_GGL
		},
		success:function(result){
			if(result.success){
				layer.open({
					 shadeClose : false,
				    btn: ['OK'],
				    content:'预约会议取消成功',
				    yes: function(index){
				        location=_webPath+"meetRoom/index?type=1";
				        layer.close(index)
				    }
				})
			}else{
				layer.open({
				    content: '预约会议取消失败',
				    time: 2
				});
			}
		}
	});
}
function addMeet(){
		var csr="";
		var startTime_PAGE =$("#startTime option:selected").text();
		var endTime_PAGE   =$("#endTime option:selected").text() ;
		var meetingReminderTime   =$("#meetingReminder option:selected").val();
		var meetAddr_PAGE  =$("input[name='makeupCo']").val() ;
		var title_PAGE     =$("#title").val();
		var content_PAGE   =$("#content").val();
		var Meettime_PAGE  =$.trim($("#Meettime").val())+" "+$("#startTime option:selected").text();
		$("#personList>li").each(function(){
			if($(this).attr("cuserDate")!=undefined){
				csr+=$(this).attr("cuserDate")+",";
			}
		});
		if($.trim(Meettime_PAGE).length<=0){
			layer.open({
				content: '会议日期不能为空',
				time: 2
			});
			return;
		}
		if(startTime_PAGE>=endTime_PAGE){
			layer.open({
			    content: '请输入正确预约会议时间',
			    time: 2
			});
			return;
		}
		if($.trim(meetAddr_PAGE).length<=0){
			layer.open({
			    content: '请输入正确的会议地点',
			    time: 2
			});
			return;
		}
		if($.trim(title_PAGE).length<=0){
			console.log(startTime_PAGE>endTime_PAGE)
			layer.open({
			    content: '会议主题不能为空',
			    time: 2
			});
			return;
		}
		if($.trim(title_PAGE).length>15){
			layer.open({
				content: '主题不可超过15个字',
				time: 2
			});
			return;
		}
		if($.trim(content_PAGE).length<=0){
			layer.open({
				content: '会议议题不能为空',
				time: 2
			});
			return;
		}
		
		if(csr==""){
			layer.open({
			    content: '   会议人员不能为空',
			    time: 2
			});
			return;
		}
		console.log($("#meetingReminder option:selected").val())
		//checkIfExit();
		$.ajax({
			type:"POST",
			url:_webPath+"meetRoom/saveMeet",
			data:{
				Meettime:Meettime_PAGE,
				personIds:csr,
				startTime:startTime_PAGE,
				endTime:endTime_PAGE,
				meetAddr:meetAddr_PAGE,
				title:title_PAGE,
				content:content_PAGE,
				meetingReminder:meetingReminderTime,
				type:1
			},
			success:function(result){
				if(result.success){
					var yes = result.attributes.yes;
					if("1"==yes){
						layer.open({
							 shadeClose : false,
						    btn: ['OK'],
						    content:'预约会议创建成功',
						    yes: function(index){
						        location=_webPath+"meetRoom/index?type=1";
						        layer.close(index)
						    }
						})
					}else if("0"==yes){
						layer.open({
						    content: '您选择的时间和地点范围内已经有预约成功的会议，请重新选择。',
						    time: 5
						});
					}
				}else {
					
					layer.open({
					    content: '预约失败，请重新创建',
					    time: 2
					});
				}
			}
		});
	}