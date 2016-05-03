// 显示对话提示框
function showTips_(txt, time) {
	closepop();
	$('.alertbox01').remove();
	htmlCon = '<div class="alertbox01 text-center"><p>' + txt + '</p></div>';
	$('body').append(htmlCon);
	var w = $(window).width();
	var w1 = $('.alertbox01').width();
	$(".alertbox01").css({
		"left" : (w - w1) / 2
	});
	if (time == '' || time == undefined) {
		time = 900;
	}
	setTimeout(function() {
		$('.alertbox01').fadeOut();
		callback_();
	}, time);
}
//显示对话提示框,传入自定义回调函数
function showTips_1(txt, time,callback) {
	closepop();
	$('.alertbox01').remove();
	htmlCon = '<div class="alertbox01 text-center"><p>' + txt + '</p></div>';
	$('body').append(htmlCon);
	var w = $(window).width();
	var w1 = $('.alertbox01').width();
	$(".alertbox01").css({
		"left" : (w - w1) / 2
	});
	if (time == '' || time == undefined) {
		time = 900;
	}
	setTimeout(function() {
		$('.alertbox01').fadeOut();
		callback();
	}, time);
}

// 显示对话提示框
function showTips(txt, time) {
	closepop();
	$('.alertbox01').remove();
	htmlCon = '<div class="alertbox01 text-center"><p>' + txt + '</p></div>';
	$('body').append(htmlCon);
	var w = $(window).width();
	var w1 = $('.alertbox01').width();
	$(".alertbox01").css({
		"left" : (w - w1) / 2
	});
	if (time == '' || time == undefined) {
		time = 900;
	}
	setTimeout(function() {
		$('.alertbox01').fadeOut();
	}, time);
}
// 隐藏弹出框
function closepop() {
	$("html").css({
		"overflow-y" : "auto"
	});
	$(".alertbox,.black-bg").hide(); /* 胡金虎说要改一下，以符合后台需求 */
}
// 弹出框
function showpop(contentid) {

	$('#contentid').val(contentid);
	$('<div class="black-bg"></div>').appendTo("body");
	$('.black-bg').css({
		"height" : $(document).innerHeight()
	}).show();
	$('.kuan-cont').show();
	$('#fix').addClass("pos-fix");
}
// 隐藏弹出框
function hidepop() {
	$('.black-bg').remove();
	$(".kuan-cont").hide();
	$('#pl').val('');
	$('#fix').removeClass('pos-fix');
}


$(document).ready(function(e) {
	// 返回顶部
	$(window).scroll(function() {

		if ($(window).scrollTop() < 50) {
			$(".back-top").hide();
		} else {
			$(".back-top").show();
		}

	});
	$(".back-top").click(function() {
		$(window).scrollTop(0);
	});
	
	//选项卡
	$(".tab-cont").find(".layout:not(:first-child)").hide();
	var tag=$(".title-cont a");
	tag.click(function(){
		var index =tag.index(this);
		$(this).addClass('cur').siblings().removeClass('cur');
	    $(".tab-cont .layout").eq(index).fadeIn().siblings().hide();
	  });	
});