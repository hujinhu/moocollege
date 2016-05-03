// JavaScript Document
$(function(){
	//$('.right table tr:odd').css('background-color', '#fafafa');
	//$('.right table tr:eq(1)').css('background', 'url(../images/title_tr.jpg) repeat-x');
	$('.right table.lcjsq_table tr:eq(1)').css('background', 'none');
	
	$('.cbga li span a:eq(1)').css('background','none');
	$('.cbga li span a:eq(2)').css('background','none');
	$('.cbga li span a:eq(3)').css('background','none');
	$('.cbga li span a').click(function(){
		$(this).parent().parent().find('ul').slideToggle().siblings('ul').slideUp();
		$(this).parent().parent().find('span').toggleClass('active');
		$(this).toggleClass('active2');
		})
		
	$('.cbga li i').click(function(){
		$(this).parent().find('p').slideToggle().siblings('p').slideUp();
		$(this).toggleClass('active1');
		})
		
	if($(".cbga ul li p a:eq(0)").hasClass("current")){
		$(".cbga ul li span:eq(0)").addClass("active");
		$(".cbga ul li ul:eq(0)").css("display","block");
		$(".cbga ul li span a:eq(0)").addClass("active2");
		$(".cbga ul li i:eq(0)").addClass("active1");
		$(".cbga ul li p:eq(0)").css("display","block");
	}	
	if($(".cbga ul li p a:eq(1)").hasClass("current")){
		$(".cbga ul li span:eq(0)").addClass("active");
		$(".cbga ul li ul:eq(0)").css("display","block");
		$(".cbga ul li span a:eq(0)").addClass("active2");
		$(".cbga ul li i:eq(0)").addClass("active1");
		$(".cbga ul li p:eq(0)").css("display","block");
	}	
	if($(".cbga ul li i:eq(1)").hasClass("active3")){
		$(".cbga ul li i:eq(1)").removeAttr("style");
		$(".cbga ul li span:eq(0)").addClass("active");
		$(".cbga ul li ul:eq(0)").css("display","block");
		$(".cbga ul li span a:eq(0)").addClass("active2");
	}
	if($(".cbga ul li i:eq(4)").hasClass("active3")){
		
		$(".cbga ul li i:eq(4)").removeAttr("style");
		
		$(".cbga ul li span a:eq(4)").addClass("active2");
		
		$(".cbga ul li span:eq(4)").addClass("active");
		
		$(".cbga ul li ul:eq(1)").css("display","block");
		
	}
	if($(".cbga ul li i:eq(5)").hasClass("active3")){
		$(".cbga ul li i:eq(5)").removeAttr("style");
		$(".cbga ul li span a:eq(4)").addClass("active2");
		$(".cbga ul li span:eq(4)").addClass("active");
		$(".cbga ul li ul:eq(1)").css("display","block");
	}
	if($(".cbga ul li i:eq(6)").hasClass("active3")){
		$(".cbga ul li i:eq(6)").removeAttr("style");
		$(".cbga ul li span a:eq(4)").addClass("active2");
		$(".cbga ul li span:eq(4)").addClass("active");
		$(".cbga ul li ul:eq(1)").css("display","block");
	}
	if($(".cbga ul li i:eq(7)").hasClass("active3")){
		$(".cbga ul li i:eq(7)").removeAttr("style");
		$(".cbga ul li span a:eq(4)").addClass("active2");
		$(".cbga ul li span:eq(4)").addClass("active");
		$(".cbga ul li ul:eq(1)").css("display","block");
	}
})
jQuery172(function(){
	if($(".cbga ul li i:eq(4)").hasClass("active3")){
		$(".cbga ul li i:eq(4)").removeAttr("style");		
		$(".cbga ul li span a:eq(4)").addClass("active2");		
		$(".cbga ul li span:eq(4)").addClass("active");		
		$(".cbga ul li ul:eq(1)").css("display","block");		
	}
	if($(".cbga ul li i:eq(5)").hasClass("active3")){
		$(".cbga ul li i:eq(5)").removeAttr("style");
		$(".cbga ul li span a:eq(4)").addClass("active2");
		$(".cbga ul li span:eq(4)").addClass("active");
		$(".cbga ul li ul:eq(1)").css("display","block");
	}
	if($(".cbga ul li i:eq(6)").hasClass("active3")){
		$(".cbga ul li i:eq(6)").removeAttr("style");
		$(".cbga ul li span a:eq(4)").addClass("active2");
		$(".cbga ul li span:eq(4)").addClass("active");
		$(".cbga ul li ul:eq(1)").css("display","block");
	}
	if($(".cbga ul li i:eq(7)").hasClass("active3")){
		$(".cbga ul li i:eq(7)").removeAttr("style");
		$(".cbga ul li span a:eq(4)").addClass("active2");
		$(".cbga ul li span:eq(4)").addClass("active");
		$(".cbga ul li ul:eq(1)").css("display","block");
	}
});
if (/*@cc_on!@*/true) { 
document.documentElement.className += 'ie' + document.documentMode; 
} 