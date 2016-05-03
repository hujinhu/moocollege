(function($) {
	function loadMeinv() {
		$.ajax({
			type : 'POST',
			url : webPath+'photoAlbum/loadData?pageNum=1',
			success : function(data) {
					if(data.success){
						
						total=data.obj.total;
						var titlePhoto="";
						if(data.obj.list.length>0){
							
							for(var i=0;i<data.obj.list.length;i++){
								 titlePhoto = data.obj.list[i].name;
								if(titlePhoto.length>15){
									var tem =titlePhoto.slice(0,13);
									titlePhoto=tem+"..."
								}
								// <div class="ell photo-txt">优秀员工巴厘岛旅游优秀员工巴厘岛旅游</div>
						        //  <div class="ell photo-txt1"><span><img src="time.png" class="ver-3">2015-06-08 </span><span class="pl26"><img src="cishu.png" class="ver-3">42</span></div>
								
								var html='<li><section class="pos-rel">';
								html+='<a href="'+webPath+'photoAlbum/detail?albumId='+data.obj.list[i].id+'&picId='+data.obj.list[i].picList[0].id+'">';
								html+='<img style="min-height:150px" src="'+imgPath+((data.obj.list[i].coverPic==null)?(data.obj.list[i].picList[0].picUrl.replace('.','_320x0.')):(data.obj.list[i].coverPic.replace('.','_320x0.')))+'"/>';
								html+='<div class="ell photo-txt">';
								html+=''+titlePhoto+'</div> ';
								html+='<div class="ell photo-txt1"><span><img style="height: 12px;width: 12px;vertical-align: -2px;" src="'+webPath+'/images/time.png" class="ver-3 mr5">'+data.obj.list[i].picList[0].createtimeStr+'</span>';
								html+='<span class="pl26"><img style="height:9px;width:11px;vertical-align:-1px;" src="'+webPath+'/images/cishu.png" class="mr5" style="ver">'+data.obj.list[i].picList.length+'</span></div>';
								html+='</a></section></li>';
								$minUl = getMinUl();
								$minUl.append(html);
							}
						}else{
							$('body').addClass("bg-hui");
							$('body').html('<article class=" pt200"><img src="'+webPath+'images/no-con.png" class="display-bl m0a " width="61"><p class="f15 text-center  lh16 mt15">暂无内容</p></article>');
							
						}
					
				} 
		},
			dataType : 'json'
		});
	}
	loadMeinv();
	function getMinUl() {//每次获取最短的ul,将图片放到其后
		var $arrUl = $("#container .col");
		var $minUl = $arrUl.eq(0);
		$arrUl.each(function(index, elem) {
			if ($(elem).height() < $minUl.height()) {
				$minUl = $(elem);
			}
		});
		return $minUl;
	}
	
	
	$.fn.scrollPagination = function(options) {
		var opts = $.extend($.fn.scrollPagination.defaults, options);
		var target = opts.scrollTarget;
		if (target == null) {
			target = obj;
		}
		opts.scrollTarget = target;
		return this.each(function() {
			$.fn.scrollPagination.init($(this), opts);
		});
	};
	$.fn.stopScrollPagination = function() {
		return this.each(function() {
			$(this).attr('scrollPagination', 'disabled');
		});
	};
	$.fn.reset = function() {
		return this.each(function() {
			$(this).attr('scrollPagination', 'enabled');
		});
	};
	$.fn.scrollPagination.loadContent = function(obj, opts) {
		var target = opts.scrollTarget;
		var mayLoadContent = $(target).scrollTop() + opts.heightOffset >= $(
				document).height()
				- $(target).height();
		if (mayLoadContent && $(obj).attr('scrollPagination') == 'enabled') {
			if (opts.beforeLoad != null) {
				opts.beforeLoad();
			}
			$(obj).children().attr('rel', 'loaded');
			$.ajax({
				type : 'POST',
				url : opts.contentPage+opts.pageNum,
				data : opts.contentData,
				success : function(data) {
					if (data != null && data != "") {
						var objectsRendered = $(obj).children('[rel!=loaded]');
						if (opts.afterLoad != null) {
							opts.afterLoad(objectsRendered);
						}
						if(data.success){
							total=data.obj.total;
							var titlePhoto ="";
								for(var i=0;i<data.obj.list.length;i++){
									titlePhoto= data.obj.list[i].name;
									if(titlePhoto.length>8){
										var tem =titlePhoto.slice(0,13);
										titlePhoto=tem+"..."
									}
									var html='<li><section class="pos-rel">';
									html+='<a href="'+webPath+'photoAlbum/detail?albumId='+data.obj.list[i].id+'&picId='+data.obj.list[i].picList[0].id+'">';
									html+='<img style="min-height:150px" src="'+imgPath+((data.obj.list[i].coverPic==null)?(data.obj.list[i].picList[0].picUrl.replace('.','_320x0.')):(data.obj.list[i].coverPic.replace('.','_320x0.')))+'"/>';
									html+='<div class="ell photo-txt">';
									html+=''+titlePhoto+'</div> ';
									html+='<div class="ell photo-txt1"><span><img style="height: 15px;width: 15px;" src="'+webPath+'/images/time.png" class="ver-3 mr5"/>'+data.obj.list[i].picList[0].createtimeStr+'</span>';
									html+='<span class="pl26"><img  style="height: 15px;width: 16px;" src="'+webPath+'/images/cishu.png" class="ver-3 mr5">'+data.obj.list[i].picList.length+'</span></div>';
									html+='</a></section></li>';
									$minUl = getMinUl();
									$minUl.append(html);
								}
					}
				} else {
						$('#loading').fadeOut();
						$('#nomoreresults').fadeIn();
				}
			},
				dataType : 'json'
			});
			opts.pageNum = opts.pageNum + 1;
		}
	};
	$.fn.scrollPagination.init = function(obj, opts) {
		var target = opts.scrollTarget;
		$(obj).attr('scrollPagination', 'enabled');
		$(target).scroll(function(event) {
			if ($(obj).attr('scrollPagination') == 'enabled') {
				$.fn.scrollPagination.loadContent(obj, opts);
			} else {
				event.stopPropagation();
			}
		});
	};
	$.fn.scrollPagination.defaults = {
		'contentPage' : null,
		'contentData' : {},
		'beforeLoad' : null,
		'afterLoad' : null,
		'scrollTarget' : null,
		'heightOffset' : 0,
		'pageNum' :2
	};
})(jQuery);