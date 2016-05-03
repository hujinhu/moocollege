/**以下为计算器计算公式函数，分别为个人储蓄存款计算器、个人住房贷款计算器、
 **个人贷款计算器、企业贷款计算器、外币兑换计算器、外币存款计算器、外汇贷款计算器
 */
/*函数：COMMON------
 *输入参数：original（存款金额）         active（存款金额）
 *          timeSpan（存款时间：月份）   interestRate（利息税率）
 *输出参数：objArray   其中:
 *	    objArray[0]为存款利息 objArray[1]为利息税额
 *	    objArray[2]为实得利息 objArray[3]为本息合计
 *	    结果保留两位小数
 */
function privateSaveCommon(original,active,timeSpan,interestRate){
	var interest=original*active*0.001*timeSpan;
	//interest=(Math.round(interest*100))/100;//存款利息：取两位小数
	var interestTaxe=interest*interestRate;
	//interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数
        var realInterest=interest*(1-interestRate);
        //realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数
        var total=parseFloat(original)+parseFloat(realInterest);
        //total=(Math.round(total*100))/100;//本息合计：取两位小数
        var objArray=new Array();
        objArray[0]=interest;
        objArray[1]=interestTaxe;
        objArray[2]=realInterest;
        objArray[3]=total;
        return objArray;
}
/*函数：个人储蓄存款计算器计算公式--非活期
 *输入参数：original（存款金额）         active（存款金额）
 *          timeSpan（存款时间：月份）   interestRate（利息税率）
 *输出参数：objArray   其中:
 *	    objArray[0]为存款利息 objArray[1]为利息税额
 *	    objArray[2]为实得利息 objArray[3]为本息合计
 *	    结果保留两位小数
 */
function privateSave(original,active,timeSpan,interestRate){
	//alert(timeSpan);
	var interest=original*active*0.01*timeSpan;
	var interestTaxe=interest*interestRate;
        var realInterest=interest*(1-interestRate);
        var total=parseFloat(original)+parseFloat(realInterest);
	interest=(Math.round(interest*100))/100;//存款利息：取两位小数        
	realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数   
	interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数 	     
        total=(Math.round(total*100))/100;//本息合计：取两位小数
        var objArray=new Array();
        objArray[0]=interest;
        objArray[1]=interestTaxe;
        objArray[2]=realInterest;
        objArray[3]=total;
        return objArray;
}


function privateSaveLCZQ(original,active,timeSpan,interestRate){
//	alert(timeSpan);
	var timeSpan=parseFloat(timeSpan)+1;
	var interest=original*active*0.01*timeSpan*(timeSpan-1)/(2*12);
	var interestTaxe=interest*interestRate;
        var realInterest=interest*(1-interestRate);
        var total=parseFloat(original)*(timeSpan-1)+parseFloat(realInterest);
	interest=(Math.round(interest*100))/100;//存款利息：取两位小数        
	realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数   
	interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数 	     
        total=(Math.round(total*100))/100;//本息合计：取两位小数
//        document.write("original",original,"active",active,"timeSpan",timeSpan);
        var objArray=new Array();
        objArray[0]=interest;
        objArray[1]=interestTaxe;
        objArray[2]=realInterest;
        objArray[3]=total;
        return objArray;
}

function privateSaveDHLB(original,active,timeSpan,interestRate){
	//alert(timeSpan);
	var interest=original*active*0.01*timeSpan/12;	
	if (timeSpan > 3) interest *= 0.6;
	var interestTaxe=interest*interestRate;
        var realInterest=interest-interestTaxe;
        var total=parseFloat(original)+parseFloat(realInterest);
	interest=(Math.round(interest*100))/100;//存款利息：取两位小数        
	realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数   
	interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数 	     
        total=(Math.round(total*100))/100;//本息合计：取两位小数
        var objArray=new Array();
        objArray[0]=interest;
        objArray[1]=interestTaxe;
        objArray[2]=realInterest;
        objArray[3]=total;
        return objArray;
}
//函数：国债
function nationalDebtSave(original,active,timeSpan,interestRate){
	var interest=original*active*0.01*timeSpan;
	var interestTaxe=interest*interestRate;
        var realInterest=interest*(1-interestRate);
        var total=parseFloat(original)+parseFloat(realInterest);
	interest=(Math.round(interest*100))/100;//存款利息：取两位小数        
	interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数        
        realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数        
        total=(Math.round(total*100))/100;//本息合计：取两位小数
        var objArray=new Array();
        objArray[0]=interest;
        objArray[1]=interestTaxe;
        objArray[2]=realInterest;
        objArray[3]=total;
        return objArray;//利息税额：取两位小数        
     
}
//函数：个人储蓄存款计算器计算公式--活期
function privateSaveHQ(original,active,yearBegin,monthBegin,dayBegin,yearEnd,monthEnd,dayEnd,interestRate){
	var original1=original;
	var firstYear631;
	var firstMonth631;
	var firstDay631;
	var lastYear631;
	var lastMonth631;
	var lastDay631;
	var timeNow=new Date();
	//var nowYear=timeNow.getYear();
	//var nowMonth=timeNow.getMonth()+1;
	//var nowDay=timeNow.getDate();	
	var nowYear=yearEnd;
	var nowMonth=monthEnd;
	var nowDay=dayEnd;		
	if(nowMonth>=7){
		lastYear631=nowYear;			
        }
        else{
        	lastYear631=parseInt(nowYear)-1;        
        }
        if(monthBegin<7){
		firstYear631=yearBegin;	
		//alert("dddaaa:"+monthBegin+"::"+monthBegin*1+"<7")		
        }
        else{
        	firstYear631=parseInt(yearBegin)+1;    
        	//alert("ddd"+firstYear631);    
        }
        var n631=lastYear631-firstYear631;
        //alert(n631);
        if(parseInt(n631)>-1){
        	var timeSpan1=parseFloat(firstYear631)*12+parseFloat(7)
        		     -parseFloat(yearBegin)*12-parseFloat(monthBegin)-parseFloat(dayBegin/30);
        	var timeSpan2=parseFloat(nowYear)*12+parseFloat(nowMonth)+parseFloat(nowDay/30)
        		      -parseFloat(lastYear631)*12-parseFloat(7);
        	//alert("timeSpan1:"+timeSpan1+"###timeSpan2:"+timeSpan2);
        	var objArray=new Array();
        	objArray=privateSaveCommon(original,active,timeSpan1,interestRate); 
        	
        	//return objArray; 
        	
        	for(i=0;i<parseInt(n631);i++){
        		original=objArray[3];
        		//alert(original);
        		objArray=privateSaveCommon(original,active,"12",interestRate);       		
        	}
        	original=original=objArray[3];
        	objArray=privateSaveCommon(original,active,timeSpan2,interestRate);
		
		total=objArray[3];
        	realInterest=total-original1;
        	interest=realInterest/0.8;
		interestTaxe=interest-realInterest;
		interest=(Math.round(interest*100))/100;//存款利息：取两位小数        
		interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数        
        	realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数        
        	total=(Math.round(total*100))/100;//本息合计：取两位小数		
		
		objArray[0]=interest;
        	objArray[1]=interestTaxe;
        	objArray[2]=realInterest;
        	objArray[3]=total;
        	//alert(original1+":original1");        	
        	return objArray;   
        }
        if(parseInt(n631)==-1){ 
        	var timeSpan=parseFloat(nowYear*12)+parseFloat(nowMonth)+parseFloat(nowDay/30)
        		    -parseFloat(yearBegin*12)-parseFloat(monthBegin)-parseFloat(dayBegin/30)
        	var objArray=new Array();
        	//alert(timeSpan);
        	objArray=privateSave(original,active,timeSpan,interestRate);
        	return objArray;
	} 
	if(parseInt(n631)<-1){ 
		alert("选择正确的存款起止日期！");
		return;
		}
}
/*函数：外汇储蓄存款计算器计算公式
 *输入参数：original（存款金额）         active（存款金额）
 *          timeSpan（存款时间：月份）   
 *输出参数：objArray   其中:
 *	    objArray[0]为存款利息 objArray[1]为本息合计
 *	    结果保留两位小数
 */
function foreignSave(original,active,timeSpan,interestRate){
	//alert("DD");
	var interest=original*active*0.01*timeSpan;
	var interestTaxe=interest*interestRate;
        var realInterest=interest*(1-interestRate);
        var total=parseFloat(original)+parseFloat(realInterest);
	interest=(Math.round(interest*100))/100;//存款利息：取两位小数        
	realInterest=(Math.round(realInterest*100))/100;//实得利息：取两位小数   
	interestTaxe=(Math.round(interestTaxe*100))/100;//利息税额：取两位小数 	     
        total=(Math.round(total*100))/100;//本息合计：取两位小数
        var objArray=new Array();
        objArray[0]=interest;
        objArray[1]=interestTaxe;
        objArray[2]=realInterest;
        objArray[3]=total;
        return objArray;        
}
/*函数：住房贷款计算器计算公式
 *输入参数：original（贷款金额）         active（贷款利率）
 *          timeSpan（贷款时间：月份）   
 *	    objArray[0]为月还款额 objArray[1]为月还款总额
 *	    结果保留两位小数
 */
function estateBorrow(original,active,timeSpan){
	var monthBack=original*active*0.001*Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))/(Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))-1);
        var totalBack=monthBack*timeSpan;
        var totalInterest=totalBack-original;
        var monthInterest=totalInterest/timeSpan;
	totalInterest=(Math.round(totalInterest*100))/100;//存款利息：取两位小数
	monthInterest=(Math.round(monthInterest*10000))/10000;//存款利息：取两位小数	
	monthBack=(Math.round(monthBack*10000))/10000;//存款利息：取两位小数
        totalBack=(Math.round(totalBack*100))/100;//本息合计：取两位小数
        var objArray=new Array();
        objArray[0]=monthBack;
        objArray[1]=totalBack;
        objArray[2]=monthInterest;
        objArray[3]=totalInterest;        
        return objArray;
}
function estateBorrow1(original,active,timeSpan){
	var timeSpan1=parseInt(timeSpan);
	var interestTotal=0;	
	for(i=1;i<timeSpan1+1;i++){
		interestM=(original-original*(i-1)/timeSpan1)*active*0.001;
		interestTotal=parseFloat(interestTotal)+parseFloat(interestM);			
	}
	var monthBack=original*active*0.001*Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))/(Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))-1);
	interestTotal=(Math.round(interestTotal*100))/100;//贷款利息：取两位小数
        var moneyTotal=parseFloat(original)+parseFloat(interestTotal);
        var objArray=new Array();
        objArray[0]=interestTotal;
        objArray[1]=moneyTotal;
        return objArray;
}
/*函数：企业贷款计算器计算公式
 *输入参数：original（贷款金额）         active（贷款利率）
 *          timeSpan（贷款时间：月份）   
 *	    objArray[0]为每月利息 objArray[1]为累计利息  objArray[2]为还款总额
 *	    结果保留两位小数
 */
function companyBorrow(original,active,timeSpan){
        //var monthInterest=original*active*0.01;
        var totalInterest=original*active*0.01*timeSpan;
        var totalBack=parseFloat(original)+parseFloat(totalInterest)
	//monthInterest=(Math.round(monthInterest*100))/100;//存款利息：取两位小数
	totalInterest=(Math.round(totalInterest*100))/100;//存款利息：取两位小数	
	totalBack=(Math.round(totalBack*100))/100;//存款利息：取两位小数
        var objArray=new Array();
        //objArray[0]=monthInterest;
        objArray[0]=totalInterest;
        objArray[1]=totalBack;    
        return objArray;
}
//旅游贷款 综合消费贷款  短期信用贷款  :::分期还本付息
function privateborrow1(original,active,timeSpanEvery,termNum){
	var timeSpan=parseInt(timeSpan);
	var interestTotal=0;	
	for(i=1;i<parseInt(termNum)+1;i++){
		interestM=parseFloat(original)/parseInt(termNum)+(parseFloat(original)
		-parseFloat(original)*(i-1)/parseInt(termNum))*active*0.01*timeSpanEvery/12;
		interestTotal=parseFloat(interestTotal)+parseFloat(interestM);			
	}
	//var monthBack=original*active*0.001*Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))/(Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))-1);
	interestTotal=(Math.round(interestTotal*100))/100;//贷款利息：取两位小数
        //var moneyTotal=parseFloat(original)+parseFloat(interestTotal);
        var objArray=new Array();
        objArray[0]=interestTotal;
        //objArray[1]=moneyTotal;
        return interestTotal;
}
//函数：国家助学贷款
function privateborrow2(original,active,timeSpanEvery,termNum){
	var timeSpan=parseInt(timeSpan);
	var interestTotal=0;	
	for(i=1;i<parseInt(termNum)+1;i++){
		interestM=parseFloat(original)/parseInt(termNum)+(parseFloat(original)
		-parseFloat(original)*(i-1)/parseInt(termNum))*active*0.01*0.5*2*timeSpanEvery/12;
		interestTotal=parseFloat(interestTotal)+parseFloat(interestM);			
	}
	//var monthBack=original*active*0.001*Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))/(Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))-1);
	interestTotal=(Math.round(interestTotal*100))/100;//贷款利息：取两位小数
        //var moneyTotal=parseFloat(original)+parseFloat(interestTotal);
        var objArray=new Array();
        objArray[0]=interestTotal;
        //objArray[1]=moneyTotal;
        return interestTotal;
}
//函数：商业助学贷款
function privateborrow3(original,active,timeSpanEvery,termNum){
	var timeSpan=parseInt(timeSpan);
	var interestTotal=0;	
	for(i=1;i<parseInt(termNum)+1;i++){
		interestM=parseFloat(original)/parseInt(termNum)+(parseFloat(original)
		-parseFloat(original)*(i-1)/parseInt(termNum))*active*0.01*2*timeSpanEvery/12;
		interestTotal=parseFloat(interestTotal)+parseFloat(interestM);			
	}
	//var monthBack=original*active*0.001*Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))/(Math.pow((1+parseFloat(active*0.001)),parseFloat(timeSpan))-1);
	interestTotal=(Math.round(interestTotal*100))/100;//贷款利息：取两位小数
        //var moneyTotal=parseFloat(original)+parseFloat(interestTotal);
        var objArray=new Array();
        objArray[0]=interestTotal;
        //objArray[1]=moneyTotal;
        return interestTotal;
}
