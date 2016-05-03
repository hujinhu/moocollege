	
//弥补通讯录最下面显示不完
	function initPerson(a,b){
		_initContacts(a,b);
		var obj=$('#sort-list2');
		obj.after('<div style="height:50px"></div>');
	}	


//alert
	function showMsg(text){
		 layer.open({
			    content: text,
			    time: 2
			});
	 }
	 
		//这个是调用微信图片浏览器的函数 
		function imagePreview(curSrc,srcList) {
		                //这个检测是否参数为空
		                if(!curSrc || !srcList || srcList.length == 0) {
		                    return;
		                }
		                //这个使用了微信浏览器提供的JsAPI 调用微信图片浏览器
		                WeixinJSBridge.invoke('imagePreview', { 
		                    'current' : curSrc,
		                    'urls' : srcList
		                });
		};
		
		
//绑定预览图片		
		function reloadPic(){
			try{
					$('#pics img').bind("click",function(){
		        	var aa=$('#pics img');
		        	var src=[];
		        	for(var i=0;i<aa.length;i++){
		        		src[i]=aa[i].src;
		        	}
		        
	                var index = $('#pics img').index(this);
	     			imagePreview(src[index],src);
				});
			}catch(e){
				console.log(e);
			}

		}

		
//		上传图片
	$("#upfile").change(function(e) {
		var num =$("#pics").children("li").length;
		if (num<=9) {
			var freader = new FileReader();
			var file = e.target.files.item(0);
			$("#daily_pic img").attr("src",_webPath+"images/loader.gif");
			freader.readAsDataURL(file);
			freader.onloadstart = function(e) {
			};
			freader.onload = function(e) {
				target = e.target.result;
				var result = file.name.split('.');
				var suffix = (result[(result.length) - 1]).toLocaleLowerCase();
				if (suffix != 'jpg' && suffix != 'png' && suffix != 'jpeg') {
					layer.open({  content: '只能上传jpg,jpeg,png后缀图片', time: 2 });
					return;
				}
				if (target.indexOf('image') == -1) {
					target = target.replace('data:', 'data:image/' + result[1] + ';');
				}
				num++;
				$("#left").text("最多可再上传"+parseInt(10-num)+"张图片");
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
								$("#daily_pic>a>img").attr("src",_webPath+"images/daily/sc-photo.png");
								num--;
								$("#left").text("最多可再上传"+parseInt(10-num)+"张图片");
							} else {
								sid = s.picid;
								$(".sp-photo").prepend('<li><a href="javascript:void(0)" onclick="delThis(this)" class="sp-ico sp-del"></a><img src="'+target+'"><input style="display:none;" type="checkbox" name="picbox" value="'+sid+'"></li>');
								$("#daily_pic>a>img").attr("src",_webPath+"images/daily/sc-photo.png");
							}
							if (num !=4) {
								$('#daily_pic').show();
							}else{
								$('#daily_pic').hide();
								$("#left").hide();
							}
						}
					}
				};
				xhr.open("post", _webPath+"/daily/uploadFile", true);
				xhr.send(form);
			};
		}
	});