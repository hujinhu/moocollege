

//  user InitToAll(年option'name,月option'name,日option'name,form'name)
// send 2000 03 30
// -----sample  html---------
//<html>
//<script src='time.js'></script>
//<body><form1>
// this is sample time optin 
// this can do 2 month change!
// InitToAll('yearname','monthname','dayname','form1');
////InitToAll(yearname,monthname,dayname,formname,firstyear,yearspan,direction)
//</form1></body></html>
//---- end----


function fGetDaysInMonth(iMonth, iYear) {
	//var dPrevDate = new Date(iYear, iMonth, 0);


  if(iMonth==1|iMonth==3|iMonth==5|iMonth==7|iMonth==8|iMonth==10|iMonth==12)
     {
	    return 31;
	 }
  
  if(iMonth==4|iMonth==6|iMonth==9|iMonth==11)
     {
	    return 30;
	 } 
	 var frun,frun1,frun2;

	frun=Math.floor(iYear/4) * 4;
	frun1=Math.floor(iYear/100)*100
	frun2=Math.floor(iYear/400)*400
    if(frun1==iYear)
	{
	   if(frun2==iYear)
	      return 29;
       else
	      return 28;
    }
	else
	{
       if(frun==iYear)
	   {return 29;}
       else
	   {return 28;}
	 }
	
}
function changeday(dayname,monthname,yearname,formname)
{     
 
		sgetyear="syear=document." + formname + "." + yearname + ".options[document." + formname + "." + yearname+ ".options.selectedIndex].text";


        eval(sgetyear);

        sgetmonth="smonth=document." + formname + "." + monthname + ".options[document." + formname + "." + monthname + ".options.selectedIndex].text";
       eval(sgetmonth);
    
       bigestday=fGetDaysInMonth(smonth,syear)

       sStr = "document." +formname+"." + dayname + ".length=" + bigestday;
       eval(sStr);
       today=new Date();
       day1=today.getDate();
      
       for (loop=1; loop <=bigestday; loop++)
       {     
            loop1=loop-1;
            sStr="document."+formname+"." + dayname + ".options[" + loop1 + "].text='"+loop+"'";
            eval(sStr);
            if(loop<10)
            {
                sStr="document."+formname+"." + dayname + ".options[" + loop1 + "].value='0"+loop+"'"; 
            }
            else
                sStr="document."+formname+"." + dayname + ".options[" + loop1 + "].value='"+loop+"'"; 
              eval(sStr);
       }
        // sStr="document."+formname+"." + dayname + ".options[5].Selected=true";  
      //eval(sStr);
  

}
function InitToMonth(yearname,monthname,dayname,formname,ch)
{ 
   var loop;
   var month1;
   today=new Date();
   if (ch=="")
         month1=today.getMonth()+1;
   else
      month1= parseInt(ch);
  // monthji=month1;
   var sOut;
   sOut = "<select SIZE='1' style='width: 44px;' NAME='";
   sOut = sOut + monthname + "' onChange=changeday('" + dayname + "','" + monthname + "','" + yearname + "','" + formname + "')>";
   document.writeln(sOut);
   //alert(sOut);
   for (loop=1; loop <13; loop++)
     {   
    
	     if(loop<10)
		 {
			if(month1==loop)
				   sOut = "<option selected value='0" + loop;
			else
				   sOut = "<option	value='0" + loop;
	     }
		 else
		 {
		      if(month1==loop)
			   sOut = "<option selected value='"+ loop;
			  else
			    sOut = "<option	value='"+ loop;
		 }
		 sOut = sOut + "'>" + loop + "</option>";
		 document.writeln(sOut);

     }

 document.writeln("</select>");
   
}

function InitToYear(yearname,monthname,dayname,formname,ch,yearspan,direction)
{

   today=new Date();
     if (ch==""){
         year=2000;

	 }
     else
        year= parseInt(ch);
		//yearji=year;
	
   str="<select SIZE='1' style='width: 57px;' NAME=" + yearname+" onChange=changeday('" +dayname + "','" + monthname + "','" + yearname +"','"+formname +"')>";

document.writeln(str);
 //  document.writeln("<select SIZE='1' NAME=");
  // document.writeln(ocxName+">");
//document.writeln(ocxName);
    
   //document.writeln("<select SIZE='1' NAME='tYear'>");
   if(direction=='up'){
   for (loop=year; loop < parseInt(year)+parseInt(yearspan); loop++)
     {
          // <option selected value="22222">22222</option>
         if (loop==year)
         
    	 document.writeln("<option selected value=");		 
		 else
    	 document.writeln("<option value=");
         document.writeln(loop) ;
         document.writeln(">");
         document.writeln(loop);
         document.writeln("</option>");
     }
   }
   else if(direction=='down'){
   for (loop=year; loop > year-yearspan; loop--)
     {
          // <option selected value="22222">22222</option>
         if (loop==year)
         
    	 document.writeln("<option selected value=");		 
		 else
    	 document.writeln("<option value=");
         document.writeln(loop) ;
         document.writeln(">");
         document.writeln(loop);
         document.writeln("</option>");
     }
   }
  

 document.writeln("</select>");
   
}
function InitToDay(yearname,monthname,dayname,formname,ch)
{  
    var day1;
 
   today=new Date();
   if (ch=="")
   {
      day1=today.getDate();
   }
  
   else
   {
   day1= parseInt(ch);
   }
sgetyear="syear=document." + formname + "." + yearname + ".options[document." + formname + "." + yearname+ ".options.selectedIndex].text";


        eval(sgetyear);

        sgetmonth="smonth=document." + formname + "." + monthname + ".options[document." + formname + "." + monthname + ".options.selectedIndex].text";
       eval(sgetmonth);
    
       bigestday=fGetDaysInMonth(smonth,syear);
   //bigestday=fGetDaysInMonth(monthji,yearji) ;
   document.writeln("<select SIZE='1' style='width: 47px;' NAME=");
   document.writeln(dayname+">");

   var sOut;
   var sAll="";
   for (loop=1;	loop <=bigestday; loop++)
	 {	 
	     if(loop<10)
		 {
			if(day1==loop)
				   sOut = "<option selected value='0" + loop;
			else
				   sOut = "<option value='0" + loop;
	     }
		 else
		 {
		      if(day1==loop)
			   sOut = "<option selected value='"+ loop;
			  else
			    sOut = "<option	value='"+ loop;
		 }
		 sOut = sOut + "'>" + loop + "</option>";
		 document.writeln(sOut);
		 sAll = sAll + sOut + "\n";
	 }

 document.writeln("</select>");

 }
 function InitToAll(yearname,monthname,dayname,formname,firstyear,yearspan,direction)
 {     
       InitToYear(yearname,monthname,dayname,formname,firstyear,yearspan,direction);
       document.writeln("年<br>");          
       InitToMonth(yearname,monthname,dayname,formname,'');
	   document.writeln("月<br>");
	   InitToDay(yearname,monthname,dayname,formname,'');
	   document.writeln("日");
 }
 function InitToAll1(yearname,monthname,dayname,formname,firstyear,yearspan,direction)
 {     
       InitToYear(yearname,monthname,dayname,formname,firstyear,yearspan,direction);
       document.writeln("年");          
       InitToMonth(yearname,monthname,dayname,formname,'');
	   document.writeln("月");
	   InitToDay(yearname,monthname,dayname,formname,'');
	   document.writeln("日");
 }
