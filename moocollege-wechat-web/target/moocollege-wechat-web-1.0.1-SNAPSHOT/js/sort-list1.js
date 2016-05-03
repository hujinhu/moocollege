var _selectOne = false;
(function($) {
    var nodes = {}, obj, $otherHandle, checkedNodes = {}, checkedCount = 0, $scrollbar;
    var windowHeight = 0, bodyHeight = 0;

    $window   = $(window);
    $body     = $(document.body);
    $html     = $(document.documentElement);
    $bodhtml  = $body.add( $html );

    var setting = {
        uniqueAndRequiredField: 'phone',
        scrollbar: {  // 滚动条
            enable: true,
            otherEle: [] // 滚动以外的高度
        },
        event: {
            BeforeChkboxClick: null,
            chkboxChange: null,
            afterInit: null
        }
    };
    var entity = {
        node: {
            id: '',
            name: '',
            phone: '',
            job: ''
        }
    }
    var _consts = {
        HTML: {
            HREF_PREFIX: '##'
        },
        OTHER: {
            UNIT: '人'
        },
        EVENT: {
            BeforeChkboxClick: 'beforeChkboxClick',
            chkboxChange: 'chkboxChange',
            afterInit: 'afterInit'
        },
        CLASS: {
            selectedClass: 'selected'
        }
    };
    pMap = {
        title: 'title',
        count: 'count',
        collection: 'collection',
        id: 'id',
        name: 'name',
        phone: 'phone',
        job: 'job',
        countnum:'countnum'
    }

    $.fn.sortList = {
        consts: _consts,
        init: function (_obj, _setting, sNodes) {
            obj = _obj;
            $.extend(true, setting, _setting);
            sNodes = sNodes ? tools.clone(tools.isArray(sNodes)? sNodes : [sNodes]) : [];

            obj.addClass('sort-list');

            view.drawList(obj, sNodes);

            $('.sitem li', obj).on('click', _event._liClick);

            /** init event */
            $('.sitem li', obj).bind(_consts.EVENT.chkboxChange, setting.event.chkboxChange);

            var sListTools = {
                addOtherNode: function(entity) {
                    if (!entity[setting.uniqueAndRequiredField]) {
                        return false;
                    }
                    if (nodes[entity[setting.uniqueAndRequiredField]]) {
                        nodes[entity[setting.uniqueAndRequiredField]]['html'].trigger('click');
                        return false;
                    }
                    var _ulHandle;
                    if (!$otherHandle) {
                        $otherHandle = view.drawSItem({title: 'Other'}).addClass('other');
                        obj.prepend($otherHandle);
                        _ulHandle = view.drawUl();
                        $otherHandle.append(_ulHandle);
                    } else {
                        _ulHandle = $otherHandle.find('ul');
                    }
                    var _Li = view.drawLi(entity);
                    _ulHandle.append(_Li);
                    _Li.on('click', _event._liClick).bind(_consts.EVENT.chkboxChange, setting.event.chkboxChange);
                    _Li.trigger('click');
                },
                getCheckedNodes: function () {
                    return checkedNodes;
                },
                getCheckedCount: function () {
                    return checkedCount;
                },
                refreshScrollbar: function() {
                    view.refreshScrollbar();
                },
                checkAllNodes: function(flag) {
                    var date = +new Date();
                    if (flag || flag == undefined) {
                        $('.sitem li', obj).addClass('selected');
                        checkedNodes = $.extend(false, {},nodes);
                        checkedCount = Object.keys(nodes).length;
                    } else {
                        $('.sitem li', obj).removeClass('selected');
                        checkedNodes = {};
                        checkedCount = 0
                    }
                    setting.event.chkboxChange();
                }
            }

            // 滚动条逻辑
            if (setting.scrollbar.enable) {
                view.drawScrollbar();
                view.refreshScrollbar();
            }

            if (setting.event.afterInit) setting.event.afterInit();
            return sListTools;
        }
    }
    data =  {

    }
    _event = {
        _liClick: function () {
        	if(_selectOne!=undefined && _selectOne==true){
        		$('.sitem li', obj).removeClass('selected');
                checkedNodes = {};
                checkedCount = 0;
        	}
        	  
              
            var $this = $(this);
            if (setting.event.BeforeChkboxClick) {
                var flag = setting.event.BeforeChkboxClick();
                if (!flag) {
                    return false;
                }
            }
            if ($this.hasClass(_consts.CLASS.selectedClass)) {
                $this.removeClass(_consts.CLASS.selectedClass);
                delete checkedNodes[$this.data(setting.uniqueAndRequiredField)];
                checkedCount--;
            } else {
                $this.addClass(_consts.CLASS.selectedClass);
                checkedNodes[$this.data(setting.uniqueAndRequiredField)] = nodes[$this.data(setting.uniqueAndRequiredField)];
                checkedCount++;
            }
            $this.trigger(_consts.EVENT.chkboxChange);
        }
    };
   
    function hammer() {
    	//右滑
    	$(".sitem1 li").hammer().on("panleft",function(e){	
    		var $left=Math.abs(e.gesture.deltaX),
    			$top=Math.abs(e.gesture.deltaY),
    			$index=$(".sitem1 li").index($(this));
    			if($left>$top||$left>25){
    				$(this).addClass("panLeft").removeClass("panright");	
    				$(".sitem1 li").each(function(index, obj) {
    					if(index!=$index){
    						if($(obj).hasClass("panLeft")){
    						$(obj).addClass("panright").removeClass("panLeft");}
    						}
    	            });			
    				}				
    	    });	
    	
    	//左滑	
    	$(".sitem1 li").hammer().on("panright",function(e){
    				var $left=Math.abs(e.gesture.deltaX);
    				var $top=Math.abs(e.gesture.deltaY);
    				if($left>$top||$left>1220){
    				$(this).addClass("panright").removeClass("panLeft");
    				}
    		});
    		}
    
    view = {
    
        drawList: function(parent, sNodes) {
            for (var i = 0, l = sNodes.length; i < l; i++) {
                var sItem = sNodes[i];
                parent.append(this.drawSItem(sItem));
            }
        },
        drawSItem: function(sItem) {
            var _sItem ;
            if(sItem[pMap.title]=="Other"){
            	_sItem = $('<div class="sitem"></div>');
                 var _header, _ul;
                 if (sItem[pMap.title]) { _header = this.drawAHead(sItem); }
                 if (sItem[pMap.collection]) {
                     _ul = this.drawUl(sItem);
                     var collection = sItem[pMap.collection]
                     for(var i = 0, l = collection.length; i < l; i++) {
                         _ul.append(this.drawLi(collection[i]));
                     }
                 }
                 _sItem.append(_header).append(_ul);
            }else if( sItem[pMap.collection][0][pMap.countnum]>0){
        		var _header, _ul;
        		_sItem=$('<div class="sitem sitem1 simple"></div>');
        		 if (sItem[pMap.title]) { _header = this.drawAHead(sItem,1); }
                 if (sItem[pMap.collection]) {
                     _ul = this.drawUl(sItem,1);
                     var collection = sItem[pMap.collection];
                     for(var i = 0, l = collection.length; i < l; i++) {
                         _ul.append(this.drawLi(collection[i],1));
                     }
                 }
                 _sItem.append(_header).append(_ul);

        	}else{
        		var _header, _ul;
        		_sItem=$('<div class="sitem"></div>');
        		 if (sItem[pMap.title]) { _header = this.drawAHead(sItem,2); }
                 if (sItem[pMap.collection]) {
                     _ul = this.drawUl(sItem,2);
                     var collection = sItem[pMap.collection];
                     for(var i = 0, l = collection.length; i < l; i++) {
                         _ul.append(this.drawLi(collection[i],2));
                     }
                 }
                 _sItem.append(_header).append(_ul);
        	}
            return _sItem;
        },
        drawAHead: function(sItem,type) {
            var _a = $('<a></a>');
            var count = sItem[pMap.count]? sItem[pMap.count] : sItem[pMap.collection]? sItem[pMap.collection].length : 0;
            _a.attr('href', _consts.HTML.HREF_PREFIX + sItem[pMap.title]);
            _a.attr('id', sItem[pMap.title]);
            if(type==1){
            	_a.text(sItem[pMap.title] + '（' + count  + '）');
            }else{
                _a.text(sItem[pMap.title] + '（' + count  + '）' + _consts.OTHER.UNIT);
            }
            return _a;
        },
        drawLi: function(li,type) {
        	var _temp;
            if(type==1){
                 _temp = '<li ><a class="blue-tel" href="#" onclick=meetall("'+li[pMap.phone]+'") ><img src="../images/blue-tel.png" width="18" ></a><a  href=chartPerson?groupId='+li[pMap.id]+'><p class="member ell"><span class="name">';
            	_temp+='{{name}}</span><span class="job">成员({{countnum}}): {{job}}</span></p></a><a  class="operation-del" onclick="delGroup('+li[pMap.id]+')">删除</a></li>';
            }else{
                 _temp = '<li data-rId="{{id}}" data-phone="{{phone}}"><i></i><span class="name">';
            	_temp+='{{name}}</span><span class="phone">{{phone}}</span><span class="job">{{job}}</span></li>';
            }
            _temp =_temp.replace('{{id}}', li[pMap.id])
                .replace(/\{\{name\}\}/g, li[pMap.name])
                .replace(/\{\{phone\}\}/g, li[pMap.phone])
                .replace(/\{\{job\}\}/g, li[pMap.job])
                .replace(/\{\{countnum\}\}/g, li[pMap.countnum]);

            var $_temp = $(_temp);
            nodes[li[pMap.phone]] = {};
            nodes[li[pMap.phone]]['html'] = $_temp;
            nodes[li[pMap.phone]]['obj'] = li;
            hammer();
            return $_temp;
        },
        drawUl: function (sItem) {
            return $('<ul class="over-hidden"></ul>')
        },
        drawScrollbar: function() {
            if ($scrollbar) return $scrollbar;
/*
            var _temp = '<div class="scrollbar"><ul>' +
                    '<li class="other"><a href="#">#</a></li>' +
                    '</ul></div>';*/
            var _temp='';
            var $temp = $(_temp);
            var $tempUl = $('ul', $temp);
            var _tempLi = '<li class="{{class}}" data-rid="{{letter}}"><a href="##{{letter}}">{{letter}}</a></li>';

            // A = 65 Z=90
            for (var i = 65; i < 91; i++) {
                var char = String.fromCharCode(i);
                var tempLi = _tempLi.replace(/\{\{letter\}\}/g, char).replace(/\{\{class\}\}/g, 'letter');
               if ((i - 64)%3 == 0) {
                    tempLi += '<li class="dot"><a href="##">·</a></li>';
                }
                $tempUl.append($(tempLi));
            }
            obj.append($temp);
            $scrollbar = $temp;
            $('a', $scrollbar).on('click', function(evt) {
                var $this = $(this);
                if ($this.text() === '#') { return true; }
                if ( !$this.parent().data('rid') || $('#' + $this.parent().data('rid')).length == 0) {
                    var p1 = $this.parent().prev();
                    var p2 = $this.parent().next();
                    var c1 = 0, c2 = 0;

                    for (; p1.length > 0 && (!p1.data('rid') || $('#' + p1.data('rid')).length == 0); p1 = p1.prev(), c1++) {
                        if (p2.length == 0) {
                            break;
                        }
                    }
                    for (; p2.length > 0 && (!p2.data('rid') || $('#' + p2.data('rid')).length == 0); p2 = p2.next(), c2++) {
                        if (p2.length == 0) {
                            break;
                        }
                    }
                    if (p1.data('rid')) {
                        $('a', p1).trigger('click');
                    } else if (p2.data('rid')) {
                        $('a', p2).trigger('click');
                    }
                    evt.preventDefault();
                    return false;
                }
                var hash = '##' + $this.parent().data('rid');
                location.hash = hash;
                history.replaceState(null, $('title').text(), location.href.split('##')[0] + hash);
                $window.trigger('hashchange');
                evt.preventDefault();
                return false;
            })
        },
        refreshScrollbar: function() {
            bodyHeight = $(document.body).height();
            windowHeight = $(window).height();

            var oe = setting.scrollbar.otherEle;
            var otherHeight = 0;
            if (oe && oe.length > 0) {
                for (var i = 0, l = oe.length; i < l; i++) {
                    otherHeight += $(oe[i]).height();
                }
            }
            if (windowHeight - otherHeight < 432) {
                $scrollbar.addClass('short');
            } else {
                $scrollbar.removeClass('short');
            }
            $scrollbar.css({
                bottom: $('#page-read-contacts > footer').height()
            });
        }
    }
    tools = {
        clone: function (obj){
            if (obj === null) return null;
            var o = tools.isArray(obj) ? [] : {};
            for(var i in obj){
                o[i] = (obj[i] instanceof Date) ? new Date(obj[i].getTime()) : (typeof obj[i] === "object" ? arguments.callee(obj[i]) : obj[i]);
            }
            return o;
        },
        isArray: function(arr) {
            return Object.prototype.toString.apply(arr) === "[object Array]";
        },
        eqs: function(str1, str2) {
            return str1.toLowerCase() === str2.toLowerCase();
        }
    }
 
}(window.jQuery || window.Zepto));