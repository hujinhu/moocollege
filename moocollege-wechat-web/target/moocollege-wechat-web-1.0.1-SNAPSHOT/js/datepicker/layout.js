(function($){
	var initLayout = function() {
		var hash = window.location.hash.replace('#', '');
		var currentTab = $('ul.navigationTabs a')
							.bind('click', showTab)
							.filter('a[rel=' + hash + ']');
		if (currentTab.size() == 0) {
			currentTab = $('ul.navigationTabs a:first');
		}
		showTab.apply(currentTab.get(0));
		$('#date').DatePicker({
			flat: true,
			date: '2008-07-31',
			current: '2008-07-31',
			calendars: 1,
			starts: 1,
			view: 'years'
		});
		var now = new Date();
		now.addDays(-10);
		var now2 = new Date();
		now2.addDays(-5);
		now2.setHours(0,0,0,0);
		$('#clearSelection').bind('click', function(){
			$('#date3').DatePickerClear();
			return false;
		});
		$('#date3').DatePicker({
			flat: true,
			date: [new Date().toLocaleDateString()],
			current: '2015-07-01',
			calendars:1,
			mode: 'range',
			onChange: function(formated) {
				var num=formated[1].split("-")[2];
				if(num!='NaN'){
					$("#num").html(num+'<span class="hui08 f12 plr1">号</span>');
					var arr=$('#date3').DatePickerGetDate();
					if(arr[0]=='Invalid Date' && arr[1]!='Invalid Date'){
						arr[0]=arr[1];
					}
					var days = arr[1].getTime() - arr[0].getTime();
					var time = parseInt(days / (1000 * 60 * 60 * 24)+1);
					$("#num2").html(time+"天");
					
					$("#sw").val("00:00");
					$("#xw").val("23:00");
					if(parseInt(time)>1){
						$("#sw").attr("disabled",true);
						$("#xw").attr("disabled",true);
						$("#tt").hide();
					}else{
						$("#sw").attr("disabled",false);
						$("#xw").attr("disabled",false);
						$("#tt").show();
					}
				}else{
					$("#num").html('');
				}
			}
		
		});
		var now3 = new Date();
		now3.addDays(-4);
		var now4 = new Date()
		$('#widgetCalendar').DatePicker({
			flat: true,
			format: 'd B, Y',
			date: [new Date(now3), new Date(now4)],
			calendars: 3,
			mode: 'range',
			starts: 1,
			onChange: function(formated) {
				$('#widgetField span').get(0).innerHTML = formated.join(' &divide; ');
			}
		});
		var state = false;
		$('#widgetField>a').bind('click', function(){
			$('#widgetCalendar').stop().animate({height: state ? 0 : $('#widgetCalendar div.datepicker').get(0).offsetHeight}, 500);
			state = !state;
			return false;
		});
		$('#widgetCalendar div.datepicker').css('position', 'absolute');
	};
	
	var showTab = function(e) {
		var tabIndex = $('ul.navigationTabs a')
							.removeClass('active')
							.index(this);
		$(this)
			.addClass('active')
			.blur();
		$('div.tab')
			.hide()
				.eq(tabIndex)
				.show();
	};
	
	EYE.register(initLayout, 'init');
})(jQuery)