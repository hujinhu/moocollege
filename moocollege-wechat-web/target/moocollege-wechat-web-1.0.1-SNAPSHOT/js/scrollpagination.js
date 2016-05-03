(function($) {
	$.fn.scrollPagination = function(options) {
		var opts = $.extend($.fn.scrollPagination.defaults, options);
		var target = opts.scrollTarget;
		if (target == null) {
			target = obj;
		}
		opts.scrollTarget = target;
		return this.each(function() {
			$.fn.scrollPagination.init($(this), opts);
		});
	};
	$.fn.stopScrollPagination = function() {
		return this.each(function() {
			$(this).attr('scrollPagination', 'disabled');
		});
	};
	$.fn.reset = function() {
		return this.each(function() {
			$(this).attr('scrollPagination', 'enabled');
		});
	};
	$.fn.scrollPagination.loadContent = function(obj, opts) {
		var target = opts.scrollTarget;
		var mayLoadContent = $(target).scrollTop() + opts.heightOffset >= $(
				document).height()
				- $(target).height();
		if (mayLoadContent && $(obj).attr('scrollPagination') == 'enabled') {
			if (opts.beforeLoad != null) {
				opts.beforeLoad();
			}
			$(obj).children().attr('rel', 'loaded');
			$.ajax({
				type : 'POST',
				url : opts.contentPage + opts.pageNum,
				data : opts.contentData,
				success : function(data) {
					if (data != null && data != "") {
						$(obj).append(data);
						var objectsRendered = $(obj).children('[rel!=loaded]');
						if (opts.afterLoad != null) {
							opts.afterLoad(objectsRendered);
						}
					} else {
						$('#loading').fadeOut();
						$('#nomoreresults').fadeIn();
					}
				},
				dataType : 'html'
			});
			opts.pageNum = opts.pageNum + 1;
		}
	};
	$.fn.scrollPagination.init = function(obj, opts) {
		var target = opts.scrollTarget;
		$(obj).attr('scrollPagination', 'enabled');
		$(target).scroll(function(event) {
			if ($(obj).attr('scrollPagination') == 'enabled') {
				$.fn.scrollPagination.loadContent(obj, opts);
			} else {
				event.stopPropagation();
			}
		});
	};
	$.fn.scrollPagination.defaults = {
		'contentPage' : null,
		'contentData' : {},
		'beforeLoad' : null,
		'afterLoad' : null,
		'scrollTarget' : null,
		'heightOffset' : 0,
		'pageNum' : 2
	};
})(jQuery);