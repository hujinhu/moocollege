       function approval_spr(list) {
    	          if(list!=null&&list!=""){
    	        	  var id = list[pMap.id];
  					var name = list[pMap.name];
  					var headImage = list[pMap.headImage];
  					$("#approval_shenpiren").append("<li approval_suserDate="+id+" ><a href='javascript:' onclick='approval_delspr()' class='sp-ico sp-del'></a> <img src='"+_imagePath+headImage+"'> <p>"+name+"</p> </li> ");
  					$("#approval_addspr").hide();
  					return ;
    	          }
		}
		//抄送人回调
		function approval_csr(list) {
			if (list!=null&&list!="") {
				var checkedCount = list.length;
				var currCount=0;
				for ( var i=0;i<checkedCount;i++) {
					currCount=$('#approval_chaosongren').children().length;
					if(currCount>10){
						return false;
					}else{
						var id = list[i][pMap.id];
						//判断有没有重复的人
						if($("li[approval_cuserDate$="+id+"]").length<=0){
							var name = list[i][pMap.name];
							var headImage = list[i][pMap.headImage];
							$("#approval_chaosongren").prepend("<li approval_cuserDate="+id+"><a href='javascript:'  onclick='approval_delcsr(this)' class='sp-ico sp-del'></a> <img src='"+_imagePath+headImage+"'> <p>"+name+"</p></li>");
							if(currCount>=10){
								$("#approval_addcsr").hide();
							}
						}
					}
				}
			}
		}
		//会签人
		//抄送人回调
		function approval_hqr(list) {
			if (list!=null&&list!="") {
				var checkedCount = list.length;
				var currCount=0;
				for ( var i=0;i<checkedCount;i++) {
					currCount=$('#approval_huiqianren').children().length;
					if(currCount>10){
						return false;
					}else{
						var id = list[i][pMap.id];
						//判断有没有重复的人
						if($("li[approval_cuserDate_hqr$="+id+"]").length<=0){
							var name = list[i][pMap.name];
							var headImage = list[i][pMap.headImage];
							$("#approval_huiqianren").prepend("<li approval_cuserDate_hqr="+id+"><a href='javascript:'  onclick='approval_delcsr(this)' class='sp-ico sp-del'></a> <img src='"+_imagePath+headImage+"'> <p>"+name+"</p></li>");
							if(currCount>=10){
								$("#approval_addhqr").hide();
							}
						}
					}
				}
			}
		}
		function approval_delspr(){
			$("#approval_addspr").slideToggle().siblings().remove();
		}
		function approval_delcsr(e){
			$(e).parent().remove();
			$("#approval_addcsr").show();
		}
		function approval_submit(){
			var spr=0,csr="",hqr="",pids="";
			$("#approval_shenpiren>li").each(function(){
				if($(this).attr("approval_suserDate")!=undefined){
					spr=$(this).attr("approval_suserDate");
				}
			});
			
			$("#approval_chaosongren>li").each(function(){
				if($(this).attr("approval_cuserDate")!=undefined){
					csr+=$(this).attr("approval_cuserDate")+",";
				}
			});
			$("#approval_huiqianren>li").each(function(){
				if($(this).attr("approval_cuserDate_hqr")!=undefined){
					hqr+=$(this).attr("approval_cuserDate_hqr")+",";
				}
			});
			/*$(".sp-photo>li").each(function(){
				if($(this).attr("pid")!=undefined){
					pids+=$(this).attr("pid")+",";
				}
			});*/
			 var pics='';
			 $('input[name="picbox"]').each(function(){ 
				 pics+=$(this).val()+",";
			 });
			if($.trim($("#approval_title").val()).length<=0){
				layer.open({
				    content: '   审核主题不能为空',
				    time: 2
				});
				return;
			}
			if(spr==""){
				layer.open({
				    content: '审批人不能为空',
				    time: 2
				});
				return;
			}
			
			$.ajax({
				type:"POST",
				url:_webPath+"approval/create",
				data:{
					spr:spr,
					csr:csr,
					hqr:hqr,
					pids:pics,
					title:$("#approval_title").val(),
					content:$("#approval_content").val()
				},
				success:function(result){
					spr=0,csr=0,pids=0;
					if(result.success){
						layer.open({
						    btn: ['OK'],
						    content:'审批发布成功',
						    yes: function(index){
						        location=_webPath+"approval/index?type=1";
						        layer.close(index)
						    },
						    end:function(index){
						    	location=_webPath+"approval/index?type=1";
								layer.close(index)
							},
						    time:2
						})
					}else{
						layer.open({
						    content: '发布失败,请重试',
						    time: 2
						});
					}
				}
			});
		}
		//上传图片
		var num = 9;
	$("#approval_upfile").change(function(e) {
		if ($(".num i").text() > 0) {
			
			var freader = new FileReader();
			var file = e.target.files.item(0);
			$("#approval_addcon img").attr("src",_webPath+"images/loader.gif");
			freader.readAsDataURL(file);
			freader.onloadstart = function(e) {
			}
			freader.onload = function(e) {
				target = e.target.result;
				console.log(target)
				var result = file.name.split('.');
				var suffix = (result[(result.length) - 1]).toLocaleLowerCase();
				if (suffix != 'jpg' && suffix != 'png' && suffix != 'jpeg') {
					console.log(suffix)
					layer.open({  content: '只能上传jpg,jpeg,png后缀图片', time: 2 });
					return;
				}
				if (target.indexOf('image') == -1) {
					console.log(1);
					target = target.replace('data:', 'data:image/' + result[1] + ';');
					console.log(target);
				}
				num--;
				$(".num i").text(num);
				var form = new FormData();
				form.append("file", file); 
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							var responseText = xhr.responseText;
							var s = eval('(' + responseText + ')');
							if (s == '1') {
								layer.open({  content: '只能上传jpg,jpeg,png后缀图片', time: 2 });
								$("#approval_addcon>a>img").attr("src",_webPath+"images/add1.png");
								num++;
								$(".num i").text(num);
							} else {
								sid = s.picid;
								$(".sp-photo").prepend("<li pid="+sid+"><a href='javascript:' onclick='approval_delPic(this)' class='sp-ico sp-del'></a> <img src='"+target+"'></li>");
								$('#approval_imgids').val($('approval_#imgids').val() + "," + sid);
								
								$("#approval_addcon>a>img").attr("src",_webPath+"images/add1.png");
							}
							if (num !=0) {
								$('#approval_addcon').show();
							}else{
								$('#approval_addcon').hide();
							}
						}
					}
				}
				xhr.open("post", _webPath+"approval/uploadFile", true);
				xhr.send(form);
			}
		}
	});
	function approval_delPic(e){
		$(e).parent().remove();
		num++;
		$(".num i").text(num);
		$('#approval_addcon').show();
	}
	