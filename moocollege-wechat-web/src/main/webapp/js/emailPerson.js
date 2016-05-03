function addMsrPerson(list) {
	if (list!=null&&list!="") {
		var checkedCount = list.length;
		for ( var i=0;i<checkedCount;i++) {
			var id = list[i][pMap.id];
			$("#msr").find("."+id+"Removemsr").each(function() {
				$(this).remove();
				return;
			});
			//判断有没有重复的人
			if($("li[cuserDate$="+id+"]").length<=0){
				var name = list[i][pMap.name];
				var email = list[i][pMap.email];
				var html="";
				var  idrovemsr =id+"Removemsr";
				html='<li id='+id+' class="mf_item '+idrovemsr+'" role="option" aria-selected="false">'
						+ name
						+ '<a href="#" class="mf_remove" title="Remove">X</a><input type="hidden" class="mf_value" value='+email+'></li>';

				$("#msr").find('.mf_list').eq(0).append(html);
			
			}
		}
	}
}

function addSjrPerson(list) {
	if (list!=null&&list!="") {
		var checkedCount = list.length;
		for ( var i=0;i<checkedCount;i++) {
			var id = list[i][pMap.id];
			$("#sjr").find("."+id+"Remove").each(function() {
				$(this).remove();
				return;
			});
			console.log(2222);
			//判断有没有重复的人
			if($("li[cuserDate$="+id+"]").length<=0){
				var name = list[i][pMap.name];
				var email = list[i][pMap.email];
				var html="";
				idrove =id+"Remove";
				var   email_name = name+'&lt;'+email+'&gt;';
				html='<li class="mf_item '+idrove+'" role="option" aria-selected="false">'
						+ email_name+ '<a href="#" class="mf_remove" title="Remove">X</a><input type="hidden" class="mf_value" value='+email_name+'></li>';

				$("#sjr").find('.mf_list').eq(0).append(html);
			
			}
		}
	}
}
function addCsmsPerson(list) {
	if (list!=null&&list!="") {
		var checkedCount = list.length;
		for ( var i=0;i<checkedCount;i++) {
			var id = list[i][pMap.id];
			$("#csms").find("."+id+"Removecsr").each(function() {
				$(this).remove();
				return;
			});
			//判断有没有重复的人
			if($("li[cuserDate$="+id+"]").length<=0){
				var name = list[i][pMap.name];
				var email = list[i][pMap.email];
				var html="";
				var  idrovecsr =id+"Removecsr";
				html='<li class="mf_item '+idrovecsr+'" role="option" aria-selected="false">'
						+ name
						+ '<a href="#" class="mf_remove" title="Remove">X</a><input type="hidden" class="mf_value" value='+email+'></li>';

				$("#csms").find('.mf_list').eq(0).append(html);
			
			}
		}
	}
}
//csms