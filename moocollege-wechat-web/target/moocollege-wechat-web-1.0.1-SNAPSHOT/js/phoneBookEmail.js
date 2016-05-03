/**
 * 微信端选择通讯录组件 Date:2015-03-09
 * 调用_initContacts(true表示单选,回调函数名) 
 * 1.默认引入:phoneBook.js文件 
 * 4.回调返回sortList: for ( var node in checkedNodes) {var
 * name = checkedNodes[node]['obj'].name;}
 */

(function($) {
	$("body").append("<div id='page-read-contacts' class='page page-read-contacts' style='display: none;'><div class='cont'>" + "<div id='sort-list2'></div> </div>" + " <footer> <div class='bar' id='_bar'></div> </footer> </div>");
	document.write("<script type='text/javascript' src='" + _webPath + "js/ridiobookemail.js'></script>");
	document.write("<script type='text/javascript' src='" + _webPath + "js/jquery.arbitrary-anchor.js'></script>");
	document.write("<link type='text/css' rel='stylesheet' href='" + _webPath + "css/tongxun.css'>");
	var obj=$('#sort-list2');
	var html="";
 		html+='<body class="bg-bai"><header class="bg-hui02  pt10 pb10 plr10 display-box bb-hui" id="so">';
 		html+='<div class="bg-bai flex1 pl8  bt-hui bb-hui bf-hui br5-tbl"><input  id="serchtext" class="h34  br0 f13 ver-middle hei01 search-input  pct-w100" placeholder="输入姓名搜索"></div>';
 		html+='<input type="button" value=""  onclick="searchso('+checktype+')" class="bg-blue search-img2 h36 text-center w50 display-bl bt-hui bb-hui br-hui br5-tbr borleft">';
 		html+='</header><form  name="formridio" method="post">';
	var $ = window.jQuery || window.Zepto;
	var resultFun="";
	var checktype="";
	var sortList="";
	var sNodes="";
	var checkallsize;
	var functionNamestr="";
	var serchtext="";
	 pMap = {
		        title: 'title',
		        count: 'count',
		        collection: 'collection',
		        headImage:'headImage',
		        id: 'id',
		        name: 'name',
		        phone: 'phone',
		        email: 'email',
		        job: 'job'
		    };
	//初始化通讯录选择插件
	this._initContacts=function (type,result){
		 functionNamestr=result.name || this.toString().match(/function\s*([^(]*)\(/)[1];
		 if (type != undefined && type == true) {
				checktype=true;
			    html+='</form><div class="pt5 confirm pos-rel" style="z-index:100"> <div class="centerbutton" style="position:absolute;left:50%;"><a href="#" onclick="cancel()" class="qdbtn display-bl pct-w36 fl">返回</a><a href="#" onclick="ridiosave()" class="fl qdbtn display-bl pct-w36" style="margin-left:20px">确定</a></div>';
			  	html+='</div></body>';
			} else {
			    checktype=false;
			    html+='</form><div class="pt5 confirm pos-rel" style="z-index:100"> <div class="centerbutton" style="position:absolute;left:50%;"><a href="#" onclick="cancel()" class="qdbtn display-bl pct-w36 fl">返回</a><a href="#" onclick="checksave()" class="fl qdbtn display-bl pct-w36" style="margin-left:20px">确定</a></div>';
				html+='<input type="checkbox" id="all" onclick="quanxuanclick()"  class="option-input fr pos-rel quanxuan"></div></div></body>';
			}
	  	obj.html(html);	
		$.ajax({
			type : 'POST',
			url : _webPath + 'email/likePersons',
			data : {serchtext: serchtext},
			success : function(msg) {
				 sNodes = eval(msg);
				sortList = $.fn.sortList.init(obj, sNodes,checktype);
				_showContacts(result);
			}
		});
		
	};
	//搜索
	this.searchso=function (){
		   serchtext=document.getElementById("serchtext").value;
		   if (checktype != undefined && checktype == true) {
				checktype=true;
			    html+='</form><div class="pt5 confirm pos-rel" style="z-index:100"> <div class="centerbutton" style="position:absolute;left:50%;"><a href="#" onclick="cancel()" class="qdbtn display-bl pct-w36 fl">返回</a><a href="#" onclick="ridiosave()" class="fl qdbtn display-bl pct-w36" style="margin-left:20px">确定</a></div>';
			  	html+='</div></body>';
			} else {
			    checktype=false;
			    html+='</form><div class="pt5 confirm pos-rel" style="z-index:100"> <div class="centerbutton" style="position:absolute;left:50%;"><a href="#" onclick="cancel()" class="qdbtn display-bl pct-w36 fl">返回</a><a href="#" onclick="checksave()" class="fl qdbtn display-bl pct-w36" style="margin-left:20px">确定</a></div>';
			    html+='<input type="checkbox" id="all" onclick="quanxuanclick()" class="option-input fr pos-rel quanxuan"></div></div></body>';
			}
		  	obj.html(html);		
			$.ajax({
				type : 'POST',
				url : _webPath + 'email/likePersons',
				data : {serchtext: serchtext},
				success : function(msg) {
					 sNodes = eval(msg);
					sortList = $.fn.sortList.init(obj, sNodes,checktype);
				}
			});
		//}
	};
	
	this.ridiosave = function() {
		if (sortList != undefined && sortList!="") {
			resultFun(sortList);
			$('#page-read-contacts').siblings().slideToggle();
			$('#page-read-contacts').hide();
		}
	};
	this.ridioselect=function(){
		var ridiovalue = $('input:radio[name="radio"]:checked').val();
		returnlist(ridiovalue);
	};
	
	function initNodes(_resultFun) {
		if (_resultFun != undefined) {
			resultFun = _resultFun;
		} else {
			//alert("请在showContacts中传入回调函数名称!");
			alert("网络异常，请稍后重试。");
			location = location;
		}
	}
	this._showContacts = function(_resultFun) {
		initNodes(_resultFun);// 初始化
		$('#page-read-contacts').siblings().slideToggle();
		$('#page-read-contacts').slideToggle();
		$(".btb").each(function(){
			if($(this).parent().find("h3").length==0){
				$(this).find("section").show();
			}
		});
	};
	 
	$('#page-read-contacts').css({
		background : '#ffffff'
	});
	//根据选择框的值返回对应的数组信息
	function returnlist(ridiovalue){
		for(var i=0;i<sNodes.length;i++){
			var  ridiocollection=sNodes[i][pMap.collection];
			for(var j=0;j<ridiocollection.length;j++){
				var ridioarray=ridiocollection[j];		
				if(ridioarray[pMap.id]==ridiovalue){
					sortList=ridioarray;
						break ;
				}
			}
		}
		     return sortList;
	}
	 /*单个选择*/
   this.dange= function (tar){
          $tar = $(tar);
          var checkboxs = $("section :checkbox");
          var checkednum = 0;
          for(var i=0;i<checkboxs.length;i++){
              var e=checkboxs[i];
              if(e.checked){
                  checkednum++;
              }
          }
          var checkboxs2 = $tar.parent().parent().parent().find('input');
          var checkednum2 = 0;
          for(var k=0;k<checkboxs2.length;k++){
              var s=checkboxs2[k];
              if(s.checked){
                  checkednum2++;
              }
          }
          if(checkednum2 < checkboxs2.length){
              $tar.parent().parent().parent().prev().find('input').prop("checked",false);
          }else{
              $tar.parent().parent().parent().prev().find('input').prop("checked",true);
          }

          if(checkednum!=0){
              $(".qdbtn").addClass('qdactive');
          }else{
              $(".qdbtn").removeClass('qdactive');
          }
      };
	/*部门全选*/
    this.bumenquanxuan=function (id){
            var $id = $(id);
            var checkboxs = $id.parent().next().find("input");
            var checkednum = 0;
            for(var i=0;i<checkboxs.length;i++){
                var e=checkboxs[i];
                if(e.checked){
                    checkednum++;
                    if(checkednum == checkboxs.length){
                        var checkboxs2 = $id.parent().next().find('input');
                        for(var j=0;j<checkboxs2.length;j++){
                            var l=checkboxs2[j];
                            l.checked= false;
                        }
                    }
                }else{
                    e.checked=!e.checked;
                }
            }
            var checkboxs1 = $("section :checkbox");
            var checkednum1 = 0;
            for(var v=0;v<checkboxs1.length;v++){
                var f=checkboxs1[v];
                if(f.checked){
                    checkednum1++;
                    if(checkednum1 == checkboxs1.length){
                        $(".quanxuan").prop("checked",true);
                    }else{
                        $(".quanxuan").prop("checked",false);
                    }
                }
            }
        };
	/*全选*/
    var _temp=true;
    this.quanxuanclick=function (){
    	 /*if(_temp){
    		 if($('#all').is(':checked')) {
    	            $(":checkbox").prop("checked",false);
    	         }else{
    	            $(":checkbox").prop("checked",true);
    	         }
    	 }else{
    		 if($('#all').is(':checked')) {
    	            $(":checkbox").prop("checked",true);
    	         }else{
    	            $(":checkbox").prop("checked",false);
    	         }
    		 _temp=true; 
    	 }
     	*/
    	     if(_temp){
    	    	 _temp=false;
    	    	 $(":checkbox").prop("checked",true);
    	     }else{
    	    	 _temp=true;
    	    	 $(":checkbox").prop("checked",false);
    	     }
          
        };
   //复选框按钮保存
    this.checksave=function(){
    	 var obj=document.getElementsByName("check"); 
    	 checkallsize=obj.length;
    	 var listarray=new Array();
    	 for(var i=0; i<obj.length; i++){ 
    	   if(obj[i].checked) {
      	     listarray[listarray.length]=returnlist(obj[i].value);
    	   }
    	 }
    	 
 		if (listarray != undefined && listarray!="") {
 			if(functionNamestr=="wsqreturnlist"){
 				resultFun(listarray,checkallsize);
 			}else{
 				resultFun(listarray);
 			}
			$('#page-read-contacts').siblings().slideToggle();
	 		$('#page-read-contacts').hide();
		}
 		_temp=true;
     };
   this.cancel=function(){
    	resultFun("");
    	$('#page-read-contacts').siblings().slideToggle();
 		$('#page-read-contacts').hide();
   };
})(jQuery);