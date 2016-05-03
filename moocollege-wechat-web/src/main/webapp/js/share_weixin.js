/**
 * 微信分享网页的缩略图、链接、标题和摘要
 */
wx.ready(function() {
	// 分享给朋友
	wx.onMenuShareAppMessage({
		title: shareTitle, // 分享标题
		desc: descContent, // 分享描述
		link: lineLink, // 分享链接
		imgUrl: imgUrl, // 分享图标
		type: '', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		success: function() {
			 
		},
		cancel: function() {
			 
		}
	});

	// 分享到朋友圈
	wx.onMenuShareTimeline({
		title:  descContent, // 分享标题
		link: lineLink, // 分享链接
		imgUrl: imgUrl, // 分享图标
		success: function() {
		},
		cancel: function() {
		}
	});

//	wx.onMenuShareQQ({
//		title: shareTitle, // 分享标题
//		desc: descContent, // 分享描述
//		link: lineLink, // 分享链接
//		imgUrl: imgUrl, // 分享图标
//		success: function() {},
//		cancel: function() {}
//	});
//
//	wx.onMenuShareWeibo({
//		title: shareTitle, // 分享标题
//		desc: descContent, // 分享描述
//		link: lineLink, // 分享链接
//		imgUrl: imgUrl, // 分享图标
//		success: function() {},
//		cancel: function() {}
//	});

	// 总是要显示右上角的分享
	wx.showOptionMenu();
	wx.hideMenuItems({
		    menuList: ["menuItem:copyUrl","menuItem:share:qq","menuItem:openWithQQBrowser","menuItem:exposeArticle","menuItem:share:facebook","menuItem:share:QZone","menuItem:openWithSafari"] // 要隐藏的菜单项，所有menu项见附录3
	});
});
