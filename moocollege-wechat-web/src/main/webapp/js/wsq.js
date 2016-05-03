//评论框光标
(function($) {
	$.fn.extend({
		insertAtCaret : function(myValue) {
			var $t = $(this)[0];
			if (document.selection) {
				this.focus();
				sel = document.selection.createRange();
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
	})
})

function valChange() {
	$text = $('#pl').val();

}

$(document).ready(function(e) {

	// 评论字数
	$("#pl").on('input', function() {
		var comment = $('#pl').val();
		var length = 2000;
		var content_len = comment.length;
		var in_len = length - content_len;
		$("#lastnum").html(in_len);
	});
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

	// 发表评论
	$('.face').click(function() {
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
		else {
			$text = $("#pl").val();
		}

	});

	$('.faces-list li').on("click", function() {
		var textinput = $('.wenb');// 评论框
		var face_ico = $(this).find('img').attr('rel');// 找到它点击的表情图片
		$text += face_ico;
		textinput.val($text);
		var face_len = face_ico.length;// 获取表情字符的字数
		var num = parseInt($("#lastnum").text());// 获取提示里的数字
		var txt_len = num - face_len;// 计算输入表情后提示里的数字变化
		$("#lastnum").html(txt_len);
	});
});

// 初始化图片
function initImgs() {
	imgs = $(".b-map  img");
	for (var i = 0; i < imgs.length; i++) {
		var img = imgs[i];
		var index = img.getAttribute("data-index");
		var gid = img.getAttribute("data-gid");
		var dsrc = img.getAttribute("data-src");
		if (index && gid && dsrc) {
			imgDate[gid] = imgDate[gid] || [];
			imgDate[gid][index] = dsrc;
		}
		;
	}
	;
}
// 图片点击事件
function showImg(groupid, index) {
	if (typeof window.WeixinJSBridge != 'undefined') {
		WeixinJSBridge.invoke('imagePreview', {
			'current' : imgDate[groupid][index],// 当前地址
			'urls' : imgDate[groupid]
		// 组
		});
	} else {
		alert("请在微信中查看", null, function() {
		});
		return false;
	}
}
function kuandu() {
	$(".b-map").each(function(index, obj) {
		var $scroll = 100;
		$(obj).find(" a img").each(function(index01, objimg) {

			$scroll += $(objimg).width();
		});
		$(".scroller").eq(index).width($scroll);

	});
}