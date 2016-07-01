Calendar.setup=function(a){function b(b,c){if(typeof a[b]=="undefined")a[b]=c}b("inputField",null);b("displayArea",null);b("button",null);b("eventName","click");b("ifFormat","%Y/%m/%d");b("daFormat","%Y/%m/%d");b("singleClick",true);b("disableFunc",null);b("dateStatusFunc",a.disableFunc);b("dateText",null);b("firstDay",null);b("align","Br");b("range",[1900,2999]);b("weekNumbers",true);b("flat",null);b("flatCallback",null);b("onSelect",null);b("onClose",null);b("onUpdate",null);b("date",null);b("showsTime",false);b("timeFormat","24");b("electric",true);b("step",2);b("position",null);b("cache",false);b("showOthers",false);b("multiple",null);var d=["inputField","displayArea","button"];for(var e in d)if(typeof a[d[e]]=="string")a[d[e]]=document.getElementById(a[d[e]]);if(!(a.flat||a.multiple||a.inputField||a.displayArea||a.button)){alert("Calendar.setup:\n  Nothing to setup (no fields found).  Please check your code");return false}function f(b){var a=b.params,c=b.dateClicked||a.electric;if(c&&a.inputField){a.inputField.value=b.date.print(a.ifFormat);typeof a.inputField.onchange=="function"&&a.inputField.onchange()}if(c&&a.displayArea)a.displayArea.innerHTML=b.date.print(a.daFormat);if(c&&typeof a.onUpdate=="function")a.onUpdate(b);if(c&&a.flat)typeof a.flatCallback=="function"&&a.flatCallback(b);c&&a.singleClick&&b.dateClicked&&b.callCloseHandler()}if(a.flat!=null){if(typeof a.flat=="string")a.flat=document.getElementById(a.flat);if(!a.flat){alert("Calendar.setup:\n  Flat specified but can't find parent.");return false}var c=new Calendar(a.firstDay,a.date,a.onSelect||f);c.showsOtherMonths=a.showOthers;c.showsTime=a.showsTime;c.time24=a.timeFormat=="24";c.params=a;c.weekNumbers=a.weekNumbers;c.setRange(a.range[0],a.range[1]);c.setDateStatusHandler(a.dateStatusFunc);c.getDateText=a.dateText;a.ifFormat&&c.setDateFormat(a.ifFormat);a.inputField&&typeof a.inputField.value=="string"&&c.parseDate(a.inputField.value);c.create(a.flat);c.show();return false}var g=a.button||a.displayArea||a.inputField;g["on"+a.eventName]=function(){var c=a.inputField||a.displayArea,e=a.inputField?a.ifFormat:a.daFormat,d=false,b=window.calendar;if(c)a.date=Date.parseDate(c.value||c.innerHTML,e);if(!(b&&a.cache)){window.calendar=b=new Calendar(a.firstDay,a.date,a.onSelect||f,a.onClose||function(a){a.hide()});b.showsTime=a.showsTime;b.time24=a.timeFormat=="24";b.weekNumbers=a.weekNumbers;d=true}else{a.date&&b.setDate(a.date);b.hide()}if(a.multiple){b.multiple={};for(var h=a.multiple.length;--h>=0;){var g=a.multiple[h],i=g.print("%Y%m%d");b.multiple[i]=g}}b.showsOtherMonths=a.showOthers;b.yearStep=a.step;b.setRange(a.range[0],a.range[1]);b.params=a;b.setDateStatusHandler(a.dateStatusFunc);b.getDateText=a.dateText;b.setDateFormat(e);d&&b.create();b.refresh();if(!a.position)b.showAtElement(a.button||a.displayArea||a.inputField,a.align);else b.showAt(a.position[0],a.position[1]);return false};return c}
Calendar._DN = new Array("日","一","二","三","四","五","六", "日");
Calendar._SDN = new Array("日","一","二","三","四","五","六","日");
Calendar._FD = 0;
Calendar._MN = new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
Calendar._SMN = new Array("一月", "二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
Calendar._TT = {};
Calendar._TT["INFO"] = "关于此 calendar";
Calendar._TT["ABOUT"] =
"(c) 2002-2012 苏州蓝恩信息科技有限公司版权所有\n"+
"作者:JIANGYANG\n\n" + 
"网址:http://www.clicksion.cn\n";
Calendar._TT["ABOUT_TIME"] = "\n\n";
Calendar._TT["PREV_YEAR"] = "上一年 (按住出菜单)";
Calendar._TT["PREV_MONTH"] = "上一月 (按住出菜单)";
Calendar._TT["GO_TODAY"] = "到今日";
Calendar._TT["NEXT_MONTH"] = "下一月 (按住出菜单)";
Calendar._TT["NEXT_YEAR"] = "下一年 (按住出菜单)";
Calendar._TT["SEL_DATE"] = "选择日期";
Calendar._TT["DRAG_TO_MOVE"] = "拖动";
Calendar._TT["PART_TODAY"] = " (今日)";
Calendar._TT["DAY_FIRST"] = "首先显示星期一";
Calendar._TT["WEEKEND"] = "0,6";
Calendar._TT["CLOSE"] = "关闭";
Calendar._TT["TODAY"] = "今日";
Calendar._TT["TIME_PART"] = "点击改变数值";
Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "%a, %b %e";
Calendar._TT["WK"] = "周";
Calendar._TT["TIME"] = "时间:";