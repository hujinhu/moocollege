/*函数：检验字段是否为空
  输入：objArray（数组类型，其中各元素为需要检验的表单内文本对象）
  输出：若为空则弹出警告信息，焦点集中在为空的输入框内，并返回值：false
        全不为空则返回值：true
**/
function checkData(objArray){
	var textnow;
	var rst="true";
	for(i=0;i<objArray.length;i++){
	        textnow=eval(objArray[i])
		if(textnow.value==""){
			rst="false";
			alert("请输入完整！");
			textnow.focus()	;
			textnow.select();
			break;
		}	
	}
	return rst	

}
/*函数：检验字段是否为数字
  输入：objArray（数组类型，其中各元素为需要检验的表单内文本对象）
  输出：若不为数字则弹出警告信息，焦点集中在该输入框内，并返回值：false；
        全为数字则返回值：true
**/
function isInteger(objArray){
	var textnow;
	var rst="true";
	for(i=0;i<objArray.length;i++){
	        textnow=eval(objArray[i]);
		if(isNaN(textnow.value)){
			rst="false";
			alert("请输入数字！");
			textnow.focus();
			textnow.select();
			break;
		}	
	}
	return rst	
}
/*函数：检验年份字段是否合法。
  输入：objYear（需要检验的表单内文本对象）
  输出：若不合法则弹出警告信息，焦点集中在该输入框内，并返回值：false；
        合法则返回值：true
**/
function isTrueYear(objYear){
	var textnow=eval(objYear);
	var rst="true";
	var nowDate=new Date();
	var nowYear=nowDate.getYear();
	if(textnow.value<1900){
		rst="false";
		alert("请输入正确年份！");
		textnow.focus();
		textnow.select();
	}
	return rst;
}
/*函数：检验月份字段是否合法。
  输入：objMonth（需要检验的表单内文本对象）
  输出：若不合法则弹出警告信息，焦点集中在该输入框内，并返回值：false；
        合法则返回值：true
**/
function isTrueMonth(objMonth){
	var textnow=eval(objMonth);
	var rst="true";
	if(textnow.value>12||textnow.value<1){
		rst="false";
		alert("请输入正确月份！");
		textnow.focus();
		textnow.select();
	}
	return rst;
}
/*函数：检验日期字段是否合法。
  输入：objDate（需要检验的表单内文本对象）
  输出：若不合法则弹出警告信息，焦点集中在该输入框内，并返回值：false；
        合法则返回值：true
**/
function isTrueDate(objDate){
	var textnow=eval(objDate);
	var rst="true";
	if(textnow.value>31){
		rst="false";
		alert("请输入正确日期！");
		textnow.focus();
		textnow.select();
	}
	return rst;
}
/*函数：根据起始日期，计算距现在的月份
  输入：yearBegin,monthBegin,dayBegin
  输出：月份（类型：FLOAT；时间段）
**/
function getMonths(yearBegin,monthBegin,dayBegin){
	var timeBegin=parseInt(yearBegin*12)+parseInt(monthBegin)+parseFloat(dayBegin/30);
	var dateNow=new Date();
	var timeNow=parseInt(dateNow.getYear()*12)+parseInt(dateNow.getMonth()+1)+parseFloat(dateNow.getDate()/30);
	var timeSpan=timeNow-timeBegin
	//alert(timeSpan);
	return timeSpan;
}
