/**
 * @param func
 *            回调函数 e 计算照片个数用
 * @param max
 *            个数
 * @param url
 *            上传fbf
 */
function chooseImage(func, e, max, url) {
	var num = e.find("li").length - 1;
	var able = 0;
	if (num == parseInt(max)) {
		return;
	} else {
		able = parseInt(max) - parseInt(num);
	}
	wx.chooseImage({
		count : able,
		sizeType : [ 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有original',
										// 'compressed'
		sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
		success : function(res) {
			wx_uploadImage(res.localIds, func, url);// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		}
	});
};
/**
 * 单张图用
 * 
 * @param func
 * @param url
 */
function chooseImageOne(func, url) {
	wx.chooseImage({
		count : 1, // 默认9
		sizeType : [ 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
		sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
		success : function(res) {
			wx_uploadImage(res.localIds, func, url);// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		}
	});
};
var wx_uploadImage = function(localIds, func, url) {
	var localId = localIds.pop();
	wx.uploadImage({
		localId : localId,
		isShowProgressTips : 1,
		success : function(res) {
			$.post(url + res.serverId, function(json) {
				if (json.success) {
					func(json, localId);
					if (localIds.length > 0) {
						wx_uploadImage(localIds, func, url);
					}
				} else {
					alert("上传失败!");
				}
			});
		}
	});
};
