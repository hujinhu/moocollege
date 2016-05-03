     //上传图片
	var num = 9;
	$("#approval_upfile").change(function(e) {
		if ($(".num i").text() > 0) {
			
			var freader = new FileReader();
			var file = e.target.files.item(0);
			$("#approval_addcon img").attr("src",_webPath+"images/loader.gif");
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
				num--;
				$(".num i").text(num);
				var form = new FormData();
				form.append("file", file); 
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							var responseText = xhr.responseText;
							console.log(responseText);
							var s = eval('(' + responseText + ')');
							if (s == '1') {
								layer.open({  content: '只能上传jpg,jpeg,png后缀图片', time: 2 });
								$("#approval_addcon>a>img").attr("src",_webPath+"images/add1.png");
								num++;
								$(".num i").text(num);
							} else {
								sid = s.picid;
								imgUrl_GG=s.picUrl;
								/*$(".sp-photo").prepend("<li pid="+sid+"><a href='javascript:' onclick='approval_delPic(this)' style='list-style-position: inside;margin-left:-17px; margin-top:30px' class='sp-ico sp-del'></a> <img src='"+target+"'></li>");
								*/
								$(".sp-photo").prepend("<li pid="+sid+"><a href='javascript:' onclick='approval_delPic(this)' style='list-style-position: inside;margin-left:-17px; margin-top:30px' class='sp-ico sp-del'></a> <img src='"+_imagePath+""+imgUrl_GG+"'></li>");
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
				};
				xhr.open("post", _webPath+"email/uploadFile", true);
				xhr.send(form);
			};
		}
	});
	
	function approval_delPic(e){
		$(e).parent().remove();
		num++;
		$(".num i").text(num);
		$('#approval_addcon').show();
	}