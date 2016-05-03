function FastClick(t,e){"use strict";function i(t,e){return function(){return t.apply(e,arguments)}}var n;if(e=e||{},this.trackingClick=!1,this.trackingClickStart=0,this.targetElement=null,this.touchStartX=0,this.touchStartY=0,this.lastTouchIdentifier=0,this.touchBoundary=e.touchBoundary||10,this.layer=t,this.tapDelay=e.tapDelay||200,!FastClick.notNeeded(t)){for(var o=["onMouse","onClick","onTouchStart","onTouchMove","onTouchEnd","onTouchCancel"],r=this,s=0,c=o.length;c>s;s++)r[o[s]]=i(r[o[s]],r);deviceIsAndroid&&(t.addEventListener("mouseover",this.onMouse,!0),t.addEventListener("mousedown",this.onMouse,!0),t.addEventListener("mouseup",this.onMouse,!0)),t.addEventListener("click",this.onClick,!0),t.addEventListener("touchstart",this.onTouchStart,!1),t.addEventListener("touchmove",this.onTouchMove,!1),t.addEventListener("touchend",this.onTouchEnd,!1),t.addEventListener("touchcancel",this.onTouchCancel,!1),Event.prototype.stopImmediatePropagation||(t.removeEventListener=function(e,i,n){var o=Node.prototype.removeEventListener;"click"===e?o.call(t,e,i.hijacked||i,n):o.call(t,e,i,n)},t.addEventListener=function(e,i,n){var o=Node.prototype.addEventListener;"click"===e?o.call(t,e,i.hijacked||(i.hijacked=function(t){t.propagationStopped||i(t)}),n):o.call(t,e,i,n)}),"function"==typeof t.onclick&&(n=t.onclick,t.addEventListener("click",function(t){n(t)},!1),t.onclick=null)}}var deviceIsAndroid=navigator.userAgent.indexOf("Android")>0,deviceIsIOS=/iP(ad|hone|od)/.test(navigator.userAgent),deviceIsIOS4=deviceIsIOS&&/OS 4_\d(_\d)?/.test(navigator.userAgent),deviceIsIOSWithBadTarget=deviceIsIOS&&/OS ([6-9]|\d{2})_\d/.test(navigator.userAgent),deviceIsBlackBerry10=navigator.userAgent.indexOf("BB10")>0;FastClick.prototype.needsClick=function(t){"use strict";switch(t.nodeName.toLowerCase()){case"button":case"select":case"textarea":if(t.disabled)return!0;break;case"input":if(deviceIsIOS&&"file"===t.type||t.disabled)return!0;break;case"label":case"video":return!0}return/\bneedsclick\b/.test(t.className)},FastClick.prototype.needsFocus=function(t){"use strict";switch(t.nodeName.toLowerCase()){case"textarea":return!0;case"select":return!deviceIsAndroid;case"input":switch(t.type){case"button":case"checkbox":case"file":case"image":case"radio":case"submit":return!1}return!t.disabled&&!t.readOnly;default:return/\bneedsfocus\b/.test(t.className)}},FastClick.prototype.sendClick=function(t,e){"use strict";var i,n;document.activeElement&&document.activeElement!==t&&document.activeElement.blur(),n=e.changedTouches[0],i=document.createEvent("MouseEvents"),i.initMouseEvent(this.determineEventType(t),!0,!0,window,1,n.screenX,n.screenY,n.clientX,n.clientY,!1,!1,!1,!1,0,null),i.forwardedTouchEvent=!0,t.dispatchEvent(i)},FastClick.prototype.determineEventType=function(t){"use strict";return deviceIsAndroid&&"select"===t.tagName.toLowerCase()?"mousedown":"click"},FastClick.prototype.focus=function(t){"use strict";var e;deviceIsIOS&&t.setSelectionRange&&0!==t.type.indexOf("date")&&"time"!==t.type?(e=t.value.length,t.setSelectionRange(e,e)):t.focus()},FastClick.prototype.updateScrollParent=function(t){"use strict";var e,i;if(e=t.fastClickScrollParent,!e||!e.contains(t)){i=t;do{if(i.scrollHeight>i.offsetHeight){e=i,t.fastClickScrollParent=i;break}i=i.parentElement}while(i)}e&&(e.fastClickLastScrollTop=e.scrollTop)},FastClick.prototype.getTargetElementFromEventTarget=function(t){"use strict";return t.nodeType===Node.TEXT_NODE?t.parentNode:t},FastClick.prototype.onTouchStart=function(t){"use strict";var e,i,n;if(t.targetTouches.length>1)return!0;if(e=this.getTargetElementFromEventTarget(t.target),i=t.targetTouches[0],deviceIsIOS){if(n=window.getSelection(),n.rangeCount&&!n.isCollapsed)return!0;if(!deviceIsIOS4){if(i.identifier&&i.identifier===this.lastTouchIdentifier)return t.preventDefault(),!1;this.lastTouchIdentifier=i.identifier,this.updateScrollParent(e)}}return this.trackingClick=!0,this.trackingClickStart=t.timeStamp,this.targetElement=e,this.touchStartX=i.pageX,this.touchStartY=i.pageY,t.timeStamp-this.lastClickTime<this.tapDelay&&t.preventDefault(),!0},FastClick.prototype.touchHasMoved=function(t){"use strict";var e=t.changedTouches[0],i=this.touchBoundary;return Math.abs(e.pageX-this.touchStartX)>i||Math.abs(e.pageY-this.touchStartY)>i?!0:!1},FastClick.prototype.onTouchMove=function(t){"use strict";return this.trackingClick?((this.targetElement!==this.getTargetElementFromEventTarget(t.target)||this.touchHasMoved(t))&&(this.trackingClick=!1,this.targetElement=null),!0):!0},FastClick.prototype.findControl=function(t){"use strict";return void 0!==t.control?t.control:t.htmlFor?document.getElementById(t.htmlFor):t.querySelector("button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea")},FastClick.prototype.onTouchEnd=function(t){"use strict";var e,i,n,o,r,s=this.targetElement;if(!this.trackingClick)return!0;if(t.timeStamp-this.lastClickTime<this.tapDelay)return this.cancelNextClick=!0,!0;if(this.cancelNextClick=!1,this.lastClickTime=t.timeStamp,i=this.trackingClickStart,this.trackingClick=!1,this.trackingClickStart=0,deviceIsIOSWithBadTarget&&(r=t.changedTouches[0],s=document.elementFromPoint(r.pageX-window.pageXOffset,r.pageY-window.pageYOffset)||s,s.fastClickScrollParent=this.targetElement.fastClickScrollParent),n=s.tagName.toLowerCase(),"label"===n){if(e=this.findControl(s)){if(this.focus(s),deviceIsAndroid)return!1;s=e}}else if(this.needsFocus(s))return t.timeStamp-i>100||deviceIsIOS&&window.top!==window&&"input"===n?(this.targetElement=null,!1):(this.focus(s),this.sendClick(s,t),deviceIsIOS&&"select"===n||(this.targetElement=null,t.preventDefault()),!1);return deviceIsIOS&&!deviceIsIOS4&&(o=s.fastClickScrollParent,o&&o.fastClickLastScrollTop!==o.scrollTop)?!0:(this.needsClick(s)||(t.preventDefault(),this.sendClick(s,t)),!1)},FastClick.prototype.onTouchCancel=function(){"use strict";this.trackingClick=!1,this.targetElement=null},FastClick.prototype.onMouse=function(t){"use strict";return this.targetElement?t.forwardedTouchEvent?!0:t.cancelable&&(!this.needsClick(this.targetElement)||this.cancelNextClick)?(t.stopImmediatePropagation?t.stopImmediatePropagation():t.propagationStopped=!0,t.stopPropagation(),t.preventDefault(),!1):!0:!0},FastClick.prototype.onClick=function(t){"use strict";var e;return this.trackingClick?(this.targetElement=null,this.trackingClick=!1,!0):"submit"===t.target.type&&0===t.detail?!0:(e=this.onMouse(t),e||(this.targetElement=null),e)},FastClick.prototype.destroy=function(){"use strict";var t=this.layer;deviceIsAndroid&&(t.removeEventListener("mouseover",this.onMouse,!0),t.removeEventListener("mousedown",this.onMouse,!0),t.removeEventListener("mouseup",this.onMouse,!0)),t.removeEventListener("click",this.onClick,!0),t.removeEventListener("touchstart",this.onTouchStart,!1),t.removeEventListener("touchmove",this.onTouchMove,!1),t.removeEventListener("touchend",this.onTouchEnd,!1),t.removeEventListener("touchcancel",this.onTouchCancel,!1)},FastClick.notNeeded=function(t){"use strict";var e,i,n;if("undefined"==typeof window.ontouchstart)return!0;if(i=+(/Chrome\/([0-9]+)/.exec(navigator.userAgent)||[,0])[1]){if(!deviceIsAndroid)return!0;if(e=document.querySelector("meta[name=viewport]")){if(-1!==e.content.indexOf("user-scalable=no"))return!0;if(i>31&&document.documentElement.scrollWidth<=window.outerWidth)return!0}}if(deviceIsBlackBerry10&&(n=navigator.userAgent.match(/Version\/([0-9]*)\.([0-9]*)/),n[1]>=10&&n[2]>=3&&(e=document.querySelector("meta[name=viewport]")))){if(-1!==e.content.indexOf("user-scalable=no"))return!0;if(document.documentElement.scrollWidth<=window.outerWidth)return!0}return"none"===t.style.msTouchAction?!0:!1},FastClick.attach=function(t,e){"use strict";return new FastClick(t,e)},"function"==typeof define&&"object"==typeof define.amd&&define.amd?define(function(){"use strict";return FastClick}):"undefined"!=typeof module&&module.exports?(module.exports=FastClick.attach,module.exports.FastClick=FastClick):window.FastClick=FastClick;
window.addEventListener('load', function () {
	FastClick.attach(document.body);
	
}, false);
(function($) {
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
			if(opts.scrollType==1){
				$.ajax({
					type : 'POST',
					url : opts.contentPage + opts.comPageNum,
					data : opts.contentData,
					success : function(data) {
						if (data != null && data != "") {
							var objectsRendered = $(obj).children('[rel!=loaded]');
							if (opts.afterLoad != null) {
								opts.afterLoad(objectsRendered);
							}
							if(data.success){
								maxTotal=data.obj.total;
								var html='';
								var list=data.obj.list;
								for(var i = 0; i < list.length; i++){
									html+='<li name="'+list[i].personInfo.name+'"   commentId='+list[i].id+' ><div class="pic fl mr10">';
							        html+='<img src="'+data.obj.imgPath+list[i].personInfo.headImage+'" class="br4"></div>';
							        html+='<div class="pos-rel over-hidden"><p class="clearfix"><span class="fr hui01">';
							        html+=list[i].createtimeStr+'</span><span class="f15 fl hei01">';
							        if(list[i].parentId!=0 && list[i].parentId!=null && list[i].parentId!=""){
							        	html+=list[i].personInfo.name+" 回复 "+list[i].commentLog.personInfo.name;
							        }else{
							        	html+=list[i].personInfo.name;
							        }
							        html+='</span></p><div class="f13 lh25 hei01 pt6">';
							        html+=list[i].commentContentblob.replace(new RegExp("\r\n","gm"),"\n").replace(new RegExp("\n","gm"),"<br/>")+"</div></div></li>";
								}
								$('#commentContent').append(html);
							}
						} else {
							$('#loading').fadeOut();
						}
					},
					dataType : 'json'
				});
				opts.comPageNum = opts.comPageNum + 1;
			}else{
				$.ajax({
					type : 'POST',
					url : opts.contentPage + opts.priPageNum,
					data : opts.contentData,
					success : function(data) {
						if (data != null && data != "") {
							var objectsRendered = $(obj).children('[rel!=loaded]');
							if (opts.afterLoad != null) {
								opts.afterLoad(objectsRendered);
							}
							if(data.success){
								maxTotal=data.obj.total;
								var html='';
								var list=data.obj.list;
								for(var i = 0; i < list.length; i++){
									html+='<li ><div class="pic fl mr10">';
							        html+='<img src="'+data.obj.imgPath+list[i].headImage+'" class="br4"></div>';
							        html+='<div class="pos-rel over-hidden"><p class="clearfix"><span class="fr hui01">';
							        html+=list[i].createtimeStr+'</span><span class="f15 fl hei01">';
							        html+=list[i].personName+'</span></p></div></li>';
								}
								$('#priseScorll').append(html);
							}
						} else {
							$('#loading').fadeOut();
						}
					},
					dataType : 'json'
				});
				opts.priPageNum = opts.priPageNum + 1;
			}
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
		'scrollType':null,
		'scrollTarget' : null,
		'heightOffset' : 0,
		'comPageNum' : 2,
		'priPageNum' : 2
	};
})(jQuery);

//评论框光标
(function($) {
	$.fn.extend({
		insertAtCaret : function(myValue) {
			var $t = $(this)[0];
			if (document.selection) {
				this.focus();

				$.sel = document.selection.createRange();
				sel.text = myValue;
				this.focus();
			} else if ($t.selectionStart || $t.selectionStart == '0') {
				var startPos = $t.selectionStart;
				var endPos = $t.selectionEnd;
				var scrollTop = $t.scrollTop;
				$t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
				this.focus();
				$t.selectionStart = startPos + myValue.length;
				$t.selectionEnd = startPos + myValue.length;
				$t.scrollTop = scrollTop;
			} else {
				this.value += myValue;
				this.focus();
			}
		}
	});
})(jQuery);
/**初始化**/
var xxType='';
var xxContentId=0;
function init(id,contentId,type){
	/**表情框开始**/
	var faceHtml='';
	faceHtml+='<div class="faces-list clearfix">';
	faceHtml+='<ul>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/0.gif" alt="" width="24" height="24" rel="[微笑]"></li>';      
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/1.gif" alt="" width="24" height="24" rel="[撇嘴]"></li>';      
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/2.gif" alt="" width="24" height="24" rel="[色]"></li>';        
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/3.gif" alt="" width="24" height="24" rel="[发呆]"></li>';     
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/4.gif" alt="" width="24" height="24" rel="[得意]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/5.gif" alt="" width="24" height="24" rel="[流泪]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/6.gif" alt="" width="24" height="24" rel="[害羞]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/7.gif" alt="" width="24" height="24" rel="[闭嘴]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/8.gif" alt="" width="24" height="24" rel="[睡]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/9.gif" alt="" width="24" height="24" rel="[大哭]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/10.gif" alt="" width="24" height="24" rel="[尴尬]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/11.gif" alt="" width="24" height="24" rel="[发怒]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/12.gif" alt="" width="24" height="24" rel="[调皮]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/13.gif" alt="" width="24" height="24" rel="[呲牙]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/14.gif" alt="" width="24" height="24" rel="[惊讶]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/15.gif" alt="" width="24" height="24" rel="[难过]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/16.gif" alt="" width="24" height="24" rel="[酷]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/17.gif" alt="" width="24" height="24" rel="[冷汗]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/18.gif" alt="" width="24" height="24" rel="[抓狂]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/19.gif" alt="" width="24" height="24" rel="[吐]"></li>';
	faceHtml+='<li><img class="faces" src="'+_webPath+'images/face/20.gif" alt="" width="24" height="24" rel="[偷笑]"></li>';
	faceHtml+='</ul>';
	faceHtml+='</div>';
	xxType=type;
	xxContentId=contentId;
	/**表情框结束**/	
	var html='';
	html+='<div class="bg-hui06 h10 over-hidden"></div>';
	html+='<div class="comment-nav title-cont hui01 clearfix f13">';
	html+='<a class="fl cur" id="commentNumId"></a>';
	html+='<a class="fr" id="priseNumId"></a>';
	html+='</div>';
	html+='<div class="comment-list tab-cont ml15 mr15">';
	//评论内容部分
	html+='<div class="layout" id="commentScorll"><ul id="commentContent"></ul></div>';
	//点赞内容部分
	html+='<div class="layout" id="priseScorll"><ul id="priseContent"></ul></div>';
	html+='</div><div style="height:80px"></div>';
	html+='<footer class="bt-hui bg-bai display-box lh40 footer-bar pct-w100 ">';
	html+='<a href="javascript:void(0);" onclick="showCommentPop('+contentId+',1,2);" class="f16 display-bl flex1 text-center lan"><i class="icon01 comment-ico display-ib ver-2 mr5"></i>评论</a>';
	html+='<a id="zanLink" href="javascript:void(0);" onclick="prise('+contentId+','+type+')" class="f16 display-bl flex1 text-center lan"><i class="icon01 zanblue-ico  mr5 display-ib ver-2"></i><span id="zanTxt">赞</span></a>';
	html+='</footer>';
	//评论框
	html+='<div class="black-bg"></div>';
	html+='<div class="kuan-cont" style="top:0">';
	html+=' <div class="kuan01 bg-bai" style="width:100%">';
	if(type==1){
		html+='<p class="plr15 pt10"><textarea  id="pl" name="" cols="" rows="" maxlength="500" class="wenb textareabox pct-w100 ft-yahei h115 f16 hui" placeholder="说点什么吧。。。" oninput="valChange();"></textarea></p>';
	}else{
		html+='<p class="plr15 pt10"><textarea  id="pl" name="" cols="" rows="" maxlength="140" class="wenb textareabox pct-w100 ft-yahei h115 f16 hui" placeholder="说点什么吧。。。" oninput="valChange();"></textarea></p>';
	}
	html+='<div class="mt10 clearfix plr15 pb10 bt-hui pt10 display-box">'; 
	html+='<a href="javascript:void(0);" class="face face-ico icon01 display-ib"></a>';
	html+='<div class="clearfix text-right flex1">';
	if(type==1){
		html+='<span class="f12 hui01 lh33" id="lastnum">500</span>';
	}else{
		html+='<span class="f12 hui01 lh33" id="lastnum">140</span>';
	}
	html+='<a href="javascript:void(0)" onClick="hideCommentPop('+type+')" class=" bd-hui lh28 br4 display-ib pct-w30 text-center f15 ml4">取消</a>';
	html+=' <a id="commentId" href="javascript:void(0);" class=" bg-blue lh30 bai br4 display-ib pct-w30 text-center ml8 f15">发表</a>';    
	html+='<input type="hidden" id="contentid" value="" >';
	html+='</div>';
	
	html+='</div>';
	html+='<div class="face-box display-no" id="plPop">';
	html+=faceHtml;
	html+='</div></div></div>';
	html+='<div class="text-center f13 mt16 hui01 pb20" id="loading" style="display: none">';
	html+='<img src="'+_webPath+'images/loading.gif" width="20" height="20" class="ver-middle mt-3  mr10"/>加载中...';
	html+='</div>';
	//评论框结束
	$('#'+id).html(html);
	firstLoadComment(contentId,type);
	firstLoadPrise(contentId,type);
	changeTab(contentId,type,1);
	commentReady(contentId,type);
};

/**弹出评论框**/
function showCommentPop(contentid,type,name) {
	$text='';
	$('#commentId').attr("onclick",'comment('+contentid+','+xxType+','+type+');')
	if(type==2){
		$('#pl').attr("placeholder","回复"+name);
	}
	$('#contentid').val(contentid);
	$('<div class="black-bg" style="z-index:200;position:relative;"></div>').appendTo("body");
	$('.black-bg').show();
	$('.kuan-cont').show();
	$('#fix').addClass("pos-fix");
}
/**隐藏弹出框**/
function hideCommentPop(type) {
	$('#commentId').attr("onclick",'')
	$('.black-bg').hide();
	$(".kuan-cont").hide();
	$('#pl').val('');
	if(type==1){
		$("#lastnum").html(500);
	}else{
		$("#lastnum").html(140);
	}
	$('#fix').removeClass('pos-fix');
}
/**首次载入评论**/
function firstLoadComment(contentId,type){
	$.ajax({
		type:'post',
		url:_webPath+'common/comment/loadCommentList?pageNumber=1',
		dataType:'json',
		data:{
			contentId:contentId,
			businessType:type
		},
		success:function(data){
			if(data.success){
				maxTotal=data.obj.total;
				var html='';
				var list=data.obj.list;
				$('#commentNumId').html('评论'+maxTotal);
				for(var i = 0; i < list.length; i++){
					html+='<li name="'+list[i].personInfo.name+'"   commentId='+list[i].id+' ><div class="pic fl mr10">';
			        html+='<img src="'+data.obj.imgPath+list[i].personInfo.headImage+'" class="br4"></div>';
			        html+='<div class="pos-rel over-hidden"><p class="clearfix"><span class="fr hui01">';
			        html+=list[i].createtimeStr+'</span><span class="f15 fl hei01">';
			        if(list[i].parentId!=0 && list[i].parentId!=null && list[i].parentId!=""){
			        	html+=list[i].personInfo.name+" 回复 "+list[i].commentLog.personInfo.name;
			        }else{
			        	html+=list[i].personInfo.name;
			        }
			        html+='</span></p><div class="f13 lh25 hei01 pt6">';
			        html+=list[i].commentContentblob.replace(new RegExp("\r\n","gm"),"\n").replace(new RegExp("\n","gm"),"<br/>")+"</div></div></li>";
				}
				$('#commentContent').html(html);
			}
		}
});
}

/**首次载入点赞列表**/
function firstLoadPrise(contentId,type){
	$.ajax({
		type:'post',
		url:_webPath+'common/prise/loadPriseList?pageNumber=1',
		dataType:'json',
		data:{
			contentId:contentId,
			businessType:type
		},
		success:function(data){
			if(data.success){
				var zanStatus=data.obj.zanStatus;
				if(zanStatus==1){
					$('#zanLink').find(".icon01").toggleClass("yizan-ico");
					$('#zanLink').find("span").eq(0).addClass("red").text("已赞");
				}
				maxTotal=data.obj.total;
				$('#priseNumId').html('赞'+'<i>'+maxTotal+'</i>');
				var html='';
				var list=data.obj.list;
				for(var i = 0; i < list.length; i++){
					html+='<li><div class="pic fl mr10">';
			        html+='<img src="'+data.obj.imgPath+list[i].headImage+'" class="br4"></div>';
			        html+='<div class="pos-rel over-hidden"><p class="clearfix"><span class="fr hui01">';
			        html+=list[i].createtimeStr+'</span><span class="f15 fl hei01">';
			        html+=list[i].personName+'</span></p></div></li>';
				}
				$('#priseContent').html(html);
				
			}
		}
});
}
/**切换点赞和评论**/
function changeTab(contentId,type,temp){
	if(temp==1){
		comScroll(contentId,type);
	}else{
		priScroll(contentId,type);
	}
};

//评论框文本输入调用函数
function valChange(){
	$text=$('#pl').val();
	$('#lastnum').html('');
}
// 发表评论
$(".face").click(function() {
	$(this).next('.face-box').toggle();// 显示评论表情框
});
//评论 xx=1是评论,2是回复评论
var parentId=0;
function comment(contentId,type,xx){
	var content="";
	if($('#lastnum').html()<0){
		$('#lastnum').html('<font color=red>内容过长</font>');
			return false;		
		}
	content= $('#pl').val();
	if($.trim(content)==''){
		$('#lastnum').html('<font color=red>请填写内容</font>');
		return false;		
	}
    $('.faces').each(function(index,obj){
 
		while(content.indexOf($(obj).attr('rel'))>=0)
		{  
			content=content.replace($(obj).attr('rel'),"<img src=\""+$(obj).attr('src')+"\" />");
		}
	});
    content=content.replace(/<^>.*?>/g,'');
	content=encodeURIComponent(content);
	$('#commentId').removeAttr("onclick");
	$.ajax({
		type:'post',
		url:_webPath+'common/comment/comment',
		async:false,
		dataType:'json',
		data:{
			contentId:contentId,
			content:content,
			parentId:parentId,
			businessType:type
		},
		success:function(data){
			if(data.success){
				hideCommentPop(type);
				firstLoadComment(contentId,type);
			}else{
				$('#commentId').attr("onclick","comment("+contentId+","+type+","+xx+")");
			};
		}
	});
	$('#commentId').removeAttr("onclick");
}
//点赞
function prise(contentId,businessType){
	var iszan=0;
	var $num=parseInt($('#priseNumId').find("i").text());
	$('#zanLink .icon01').toggleClass("yizan-ico");
	 if($('#zanLink').find(".icon01").hasClass("yizan-ico")){
		 $num++;
		 $('#zanLink').find("span").eq(0).addClass("red").text("已赞");
		 $('#priseNumId').find("i").text($num);
		 iszan=1;	 
		 }
	else{
		$num--;
		$('#zanLink').find(".yizan-ico").toggleClass("icon01");
		$('#zanLink').find("span").eq(0).removeClass("red").text("赞");
		$('#priseNumId').find("i").text($num);
		iszan=2;			
	}
	$.ajax({
		type:'post',
		url:_webPath+'common/prise/prise',
		dataType:'json',
		data:{
			contentId:contentId,
			type:iszan,
			businessType:businessType
			
		},
		success:function(data){
			if(data.success){
				NextLoadPrise(contentId,businessType);
			}
		},
	});
}

/**二次载入点赞列表**/
function NextLoadPrise(contentId,type){
	$.ajax({
		type:'post',
		url:_webPath+'common/prise/loadPriseList?pageNumber=1',
		dataType:'json',
		data:{
			contentId:contentId,
			businessType:type
		},
		success:function(data){
			if(data.success){
				maxTotal=data.obj.total;
				$('#priseNumId').html('赞'+'<i>'+maxTotal+'</i>');
				var html='';
				var list=data.obj.list;
				for(var i = 0; i < list.length; i++){
					html+='<li><div class="pic fl mr10">';
			        html+='<img src="'+data.obj.imgPath+list[i].headImage+'" class="br4"></div>';
			        html+='<div class="pos-rel over-hidden"><p class="clearfix"><span class="fr hui01">';
			        html+=list[i].createtimeStr+'</span><span class="f15 fl hei01">';
			        html+=list[i].personName+'</span></p></div></li>';
				}
				$('#priseContent').html(html);
				
			}
		}
});
}
function comScroll(contentId,type){
	//加载更多
	$('#commentScorll').scrollPagination({
		'contentPage': _webPath+'/common/comment/loadCommentList?pageNumber=',
		'contentData': {
			businessType:type,
			contentId:contentId,
		}, 
		'scrollType':1,
		'scrollTarget': $(window), 
		'heightOffset': 10, 
		'beforeLoad': function(){ 
			$('#loading').fadeIn();
			$('#commentScorll').stopScrollPagination();
		},
		'afterLoad': function(elementsLoaded){ 
			 $('#loading').fadeOut();
			 $('#commentScorll').reset();
			if($("#commentScorll").children().length>=maxTotal){
				$('#commentScorll').stopScrollPagination();
			}
		}
	});
}

function priScroll(contentId,type){
	//加载更多
	$('#priseScorll').scrollPagination({
		'contentPage': _webPath+'common/prise/loadPriseList?pageNumber=',
		'contentData': {
			businessType:type,
			contentId:contentId,
		}, 
		'scrollType':2,
		'scrollTarget': $(window), 
		'heightOffset': 10, 
		'beforeLoad': function(){ 
			$('#loading').fadeIn();
			$('#priseScorll').stopScrollPagination();
		},
		'afterLoad': function(elementsLoaded){ 
			 $('#loading').fadeOut();
			 $('#priseScorll').reset();
			if($("#priseScorll").children().length>=maxTotal){
				$('#priseScorll').stopScrollPagination();
			}
		}
	});
}

function commentReady(contentId,type){
	// 评论字数
	$("#pl").on('input', function() {
		var comment = $('#pl').val();
		var length = 140;
		if(type==1){
			length = 500;
		}
		var content_len = comment.length;
		var in_len = length - content_len;
		$("#lastnum").html(in_len);
	});
	
	// 发表评论
	$(".face").click(function() {
		$(this).parent().next('.face-box').toggle();// 显示评论表情框
	});
	
	$text = "";
	$("#pl").blur(function() {
		if ($text !== "") {
			var $inputVal = $("#pl").val();
			$inputVal = $inputVal.split($text);
			$text += $inputVal[1];
			$("#pl").val($text);
		}
		// 第一次输入文字的时候
		else {
			$text = $("#pl").val();
		};
	});
	$textImg = "";
	$('#plPop .faces-list li').on("click", function() {
		$textImg=$text;
		var textinput = $('.wenb');// 评论框
		var face_ico = $(this).find('img').attr('rel');// 找到它点击的表情图片
		$textImg += face_ico;
		// 移动光标，阻止软键盘弹上来
		var face_len = face_ico.length;// 获取表情字符的字数
		var num = parseInt($("#lastnum").text());// 获取提示里的数字
		var txt_len = num - face_len;// 计算输入表情后提示里的数字变化
        if(txt_len>=0){
        	$text=$textImg;
        	textinput.val($textImg);
    		$("#lastnum").html(txt_len);
	    }
	});
	
	//选项卡
	$(".tab-cont").find(".layout:not(:first-child)").hide();
	var tag=$(".title-cont a");
	tag.click(function(){
		if($(this).attr("id")=='commentNumId'){
			changeTab(contentId,type,1);
		}else{
			changeTab(contentId,type,2);
		}
		var index =tag.index(this);
		$(this).addClass('cur').siblings().removeClass('cur');
	    $(".tab-cont .layout").eq(index).fadeIn().siblings().hide();
	  });	
}
$(function(){
	$(document).on('click', '#commentContent li', function(e) {
		e.stopPropagation();
		/**调用回复框**/		
		parentId=$(this).attr("commentId");
		showCommentPop(xxContentId,2,$(this).attr("name"));
	});
});

