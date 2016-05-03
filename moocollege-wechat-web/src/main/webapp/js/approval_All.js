	function addposting(id) {
		var content = $('#pl').val();
		if (content.length > 200) {
			layer.open({
			    content: '   内容过长',
			    time: 2
			});
			return;
		}
		if ($.trim(content) == '') {
			layer.open({
			    content: '   请填写内容后再发表',
			    time: 2
			});
			return;
		}
		content = encodeURIComponent(content);
		$.ajax({
			type : 'POST',
			url : _webPath+'approval/addPosting',
			data : {
				contentId : id,
				content : content,
				businessType : 44
			},
			success : function(data) {
				if (data.success) {
					location = _webPath+"approval/approvalDetail?id="+id
				} else {
					layer.open({
					    content: '   发表内容失败',
					    time: 5
					});
					return;
				}
			}
		});
	}