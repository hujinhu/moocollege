(function($) {
    var setting = {
        maxLength: 60,
        showAllBtnText: ' （展开全文）'
    }
    var consts = {
        key: {
            originalText: 'originalText'
        }
    }
    $.fn.showAll = function(_setting) {
        $.extend(setting, _setting);
        this.each(function() {
            var $this = $(this);
            var originalText = $(this).text();
            if (originalText.length > setting.maxLength) {
                $this.data(consts.key.originalText, originalText);
                $this.text(originalText.substring(0, setting.maxLength) + '...');

                $this.append(renderShowAllBtn());
            }
        })
    }
    function renderShowAllBtn() {
        var temp = '<a href="#" class="showall">' + setting.showAllBtnText + '</a>';
        var $temp = $(temp);
        $temp.on('click', function() {
            var $this = $(this);
            var $parent = $this.parent();
            $parent.text($parent.data(consts.key.originalText));
        })
        return $temp;
    }
})(window.jQuery || window.Zepto);