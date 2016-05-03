/**
 * 微信端选择通讯录组件 Date:2015-03-09
 * 调用_initContacts(true表示单选,回调函数名) 
 * 1.默认引入:sort-list.js文件 
 * 4.回调返回sortList: for ( var node in checkedNodes) {var
 * name = checkedNodes[node]['obj'].name;}
 */


(function($) {
	$("body").append("<div id='page-read-contacts' class='page page-read-contacts' style='display: none;'><div class='cont'>" + "<div id='sort-list2'></div> </div>" + " <footer> <div class='bar' id='_bar'></div> </footer> </div>");
	document.write("<script type='text/javascript' src='" + _webPath + "js/sort-list.js'></script>");
	document.write("<script type='text/javascript' src='" + _webPath + "js/jquery.arbitrary-anchor.js'></script>");
	var $ = window.jQuery || window.Zepto;
	var resultFun;
	
	
	//初始化通讯录选择插件
	this._initContacts=function (type,result){
		if (type != undefined && type == true) {
			  _selectOne=true;
			$("#_bar").html("<a href='javascript:void(0)' onclick='_contacts_submit()' class='phone-btn contacts-submit' id='contacts-submit' >确定</a>"); 
		} else {
			 _selectOne=false;
			$("#_bar").html("<a href='javascript:void(0)' onclick='_contacts_selectAll()' class='phone-btn selectall' style='width: 49%; float: left;'>全部成员</a><a href='javascript:void(0)'  onclick='_contacts_submit()' class='phone-btn contacts-submit' style='width: 49%; float: left; border-left: 1px #cccccc solid;'>确定</a>");
		}
		_showContacts(result);
	}
	AA_CONFIG = {
		animationLength : 500,
		easingFunction : 'linear',
		scrollOffset : 0
	};
	// sort-list.js插件配置参数
	var settings = {
		uniqueAndRequiredField : 'phone',
		scrollbar : {
			enable : true,
			otherEle : [ '#page-read-contacts > footer' ],
			animationLength : 500,
			easingFunction : 'linear',
			scrollOffset : 0
		},
		event : {
			chkboxChange : chkboxChange
		}
	};
	$.ajax({
		type : 'POST',
		url : _webPath + 'meet/loadPersons',
		data : '',
		success : function(msg) {
			var sNodes = eval(msg);
			sortList = $.fn.sortList.init($('#sort-list2'), settings, sNodes);
		}
	});
	var _selectAllTemp = false;
	function chkboxChange() {
		var checkedNodes = sortList.getCheckedNodes();
		var checkedCount = sortList.getCheckedCount();
	}
	function initNodes(_resultFun) {
		if (_resultFun != undefined) {
			resultFun = _resultFun;
		} else {
			alert("请在showContacts中传入回调函数名称!")
			location = location;
		}
		_selectAllTemp = false;
		sortList.checkAllNodes(false);
	}
	this._showContacts = function(_resultFun) {
		initNodes(_resultFun);// 初始化
		$('#page-read-contacts').siblings().slideToggle();
		$('#page-read-contacts').slideToggle();
	};
	//确认
	this._contacts_submit=function (){
		$('#page-read-contacts').siblings().slideToggle();
		$('#page-read-contacts').hide();
		resultFun(sortList);
	}
	//全选
	this._contacts_selectAll =function(){
		if (_selectAllTemp) {
			_selectAllTemp = false;
		} else {
			_selectAllTemp = true;
		}
		sortList.checkAllNodes(_selectAllTemp);
	}
	$('#page-read-contacts').css({
		background : '#ffffff'
	});
})(jQuery);