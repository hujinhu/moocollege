$(function() {
			var opt = {
				preset : 'date', //日期
				theme : 'android-ics light', //皮肤样式
				lang : 'zh',
				display : 'modal', //显示方式 
				mode : 'scroller', //日期选择模式
				dateFormat : 'yy-mm-dd', // 日期格式
				timeFormat : 'HH:ii',
				setText : '确定', //确认按钮名称
				cancelText : '取消',//取消按钮名籍我
				dateOrder : 'yymmdd', //面板中日期排列格式
				timeWheels : 'HHii',
				minDate : new Date(),
				dayText : '日',
				monthText : '月',
				yearText : '年',
				hourText : "时",
				minuteText : "分",//面板中年月日文字
				endYear : 2050
			//结束年份
			};
			$("#leave_sDate").mobiscroll(opt).datetime(opt);
			$("#leave_eDate").mobiscroll(opt).datetime(opt);
		});

		//审批人回调
		function leave_spr(list) {
//			var checkedNodes = list.getCheckedNodes();
//			var checkedCount = list.getCheckedCount();
//			if (checkedCount > 0) {
//				for ( var node in checkedNodes) {
//					var id = checkedNodes[node]['obj'].id;
//					var name = checkedNodes[node]['obj'].name;
//					var headImage = checkedNodes[node]['obj'].headImage;
			        var id = list[pMap.id];
			        var name = list[pMap.name];
			        var headImage = list[pMap.headImage];
					$("#leave_shenpiren").append("<li leave_suserDate="+id+" ><a href='javascript:' onclick='leave_delspr()' class='sp-ico sp-del'></a> <img src='"+_imagePath+headImage+"'> <p>" + name + "</p> </li> ");
					$("#leave_addspr").hide();
					return;
//				}
//			}
		}
		//抄送人回调
		function leave_csr(list) {
//			var checkedNodes = list.getCheckedNodes();
//			var checkedCount = list.getCheckedCount();
//			var currCount = 0;
			var checkedCount = list.length;
			var currCount=0;
			if (checkedCount > 0) {
				for ( var i=0;i<checkedCount;i++) {
					currCount = $('#leave_chaosongren').children().length
					if (currCount > 10) {
						return false;
					} else {
						var id = list[i][pMap.id];
						//判断有没有重复的人
						if ($("li[leave_cuserDate$=" + id + "]").length <= 0) {
							var name = list[i][pMap.name];
							var headImage =list[i][pMap.headImage];
							$("#leave_chaosongren").prepend(
									"<li leave_cuserDate="+id+"><a href='javascript:'  onclick='leave_delcsr(this)' class='sp-ico sp-del'></a> <img src='"+_imagePath+headImage+"'> <p>" + name + "</p></li>");
							if (currCount >= 10) {
								$("#leave_addcsr").hide();
							}
						}
					}
				}
			}
		}
		function leave_delspr() {
			$("#leave_addspr").slideToggle().siblings().remove();
		}
		function leave_delcsr(e) {
			$(e).parent().remove();
			$("#leave_addcsr").show();
		}
		function leave_submit() {
			var spr = 0, csr = "";

			$("#leave_shenpiren>li").each(function() {
				if ($(this).attr("leave_suserDate") != undefined) {
					spr = $(this).attr("leave_suserDate");
				}
			});
			$("#leave_chaosongren>li").each(function() {
				if ($(this).attr("leave_cuserDate") != undefined) {
					csr += $(this).attr("leave_cuserDate") + ",";
				}
			});
			if ($.trim($("#leave_content").val()).length <= 0) {
				layer.open({
					content : '   请假事由不能为空',
					time : 2
				});
				return;
			}

			if ($("#leave_type option:selected").attr("value") == undefined) {
				layer.open({
					content : '   请假类型不能为空<br>如果没有请联系管理员添加',
					time : 3
				});
				return;
			}
			if ($("#leave_sDate").val() >= $("#eDate").val()) {
				layer.open({
					content : '开始时间不能大于结束时间',
					time : 2
				});
				return;
			}
			if (spr == "") {
				layer.open({
					content : '审批人不能为空',
					time : 2
				});
				return;
			}

			$.ajax({
				type : "POST",
				url : _webPath+"leave/create",
				data : {
					spr : spr,
					csr : csr,
					type : $("#leave_type option:selected").attr("value"),
					sDate : $('#leave_sDate').val(),
					eDate : $('#leave_eDate').val(),
					content : $("#leave_content").val()
				},
				success : function(result) {
					spr = 0, csr = 0;
					if (result.success) {
						layer.open({
							btn : [ 'OK' ],
							content : '请假申请发布成功',
							yes : function(index) {
								location = _webPath+"approval/screate";
								layer.close(index)
							},
							end : function(index) {
								location = _webPath+"approval/screate";
								layer.close(index)
							},
							time : 2
						})
					} else {
						layer.open({
							content : '发布失败,请重试',
							time : 2
						});
					}
				}
			});
		}