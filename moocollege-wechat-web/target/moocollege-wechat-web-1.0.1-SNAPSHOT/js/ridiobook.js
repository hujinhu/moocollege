(function($) {
    var nodes = {}, obj;

    $window   = $(window);
    $body     = $(document.body);
    $html     = $(document.documentElement);
    $bodhtml  = $body.add( $html );

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
    
    $.fn.sortList = {
        init: function (_obj, sNodes,checktype) {
            obj = _obj;
            obj.addClass('sort-list');
          view.drawList(obj,sNodes,checktype);
          //  view.drawList(obj, sNodes);
        }
    };
    view = {
         drawList: function(parent,sNodes,checktype) {
                for (var i = 0, l = sNodes.length; i < l; i++) {
                    var sItem = sNodes[i];
                    parent.append(this.drawSItem(sItem,checktype));
                }
                parent.append('<div class="kong"></div>');
            },
            drawSItem: function(sItem,checktype) {
                var _sItem = $('<div></div>');
                var _header="", _ul="";
                if (sItem[pMap.title]) { _header = this.drawAHead(sItem,checktype); }
                if (sItem[pMap.collection]) {
                    _ul = this.drawUl(sItem,checktype);
                    var collection = sItem[pMap.collection];
                    for(var i = 0, l = collection.length; i < l; i++) {
                        _ul.append(this.drawLi(collection[i],checktype));
                    }
                }
                _sItem.append(_header).append(_ul);
                return _sItem;
            },
            drawAHead: function(sItem,checktype) {
                var _a;
                if(checktype){
                    _a= $('<h3 class="h30 lh30 bold hei01 bg-hui04 pl15 f14 apartname" onclick="dislpayDiv(this)">'+sItem[pMap.title]+'</h3>');
                }else{
                    _a= $('<h3 class="h30 lh30 bold hei01 bg-hui04 pl15 f14 apartname" onclick="dislpayDiv(this)">'+sItem[pMap.title]+'<input type="checkbox"  class="option-input fr pos-rel bumenquanxuan"  onclick="bumenquanxuan(this)" ></h3>');
                }
              //  _a.text(sItem[pMap.title] +  _consts.OTHER.UNIT);
                return _a;
            },
            drawLi: function(li,checktype) {
            	var _temp;
            	if(checktype){
            	   _temp='<section  style="display:none;" class="txl-tit01 ml15 "><label ><p class="f16 hei01">{{name}}</p>'
            		+'<p class="hui01 lh20">{{phone}}<span class="plr15">{{job}}</span></p>'
            		+'<input data-rId="{{id}}" data-phone="{{phone}}" class="option-input br20 " name="radio" onclick="ridioselect(this)" value="'+li[pMap.id]+'" type="radio"></label></section>';
            	 }else{
            	   _temp='<section  style="display:none;" class="txl-tit01 ml15 "><label><p class="f16 hei01">{{name}}</p>'
                    +'<p class="hui01 lh20">{{phone}}<span class="plr15">{{job}}</span></p>'
                 	+'<input data-rId="{{id}}" data-phone="{{phone}}" type="checkbox" name="check" class="option-input"   onclick="dange(this)" value="'+li[pMap.id]+'"></label></section>';
                 	 }
            	_temp =_temp.replace('{{id}}', li[pMap.id])
                    .replace(/\{\{name\}\}/g, li[pMap.name])
                    .replace(/\{\{phone\}\}/g, li[pMap.phone])
                    .replace(/\{\{job\}\}/g, li[pMap.job]);

                var $_temp = $(_temp);
                nodes[li[pMap.phone]] = {};
                nodes[li[pMap.phone]]['html'] = $_temp;
                nodes[li[pMap.phone]]['obj'] = li;
                return $_temp;
            },
            drawUl: function (sItem,checktype) {
            	if(checktype){
                return $('<div class="btb"></div>');
            	} else{
                return $('<div class="btb bumen"></div>');
                }
            }
    };
    
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
    };
    

  
}(window.jQuery || window.Zepto));

function dislpayDiv(e){
	if($(e).parent().find("section").is(":visible")){
		$(e).parent().find("section").hide();
	}else{
		$(e).parent().find("section").show();
	}
	 $(e).toggleClass('active');
}
