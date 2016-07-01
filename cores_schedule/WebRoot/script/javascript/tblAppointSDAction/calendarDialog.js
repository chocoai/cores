
/**
 * 显示日期控件
 * @return
 */
function showCalendarDialog(nodeName,studyNo,oldDate,clickName){
//	document.getElementById("studyScheduleNode_click").href="javascript:"+clickName+"();";
	$('#cc').calendar({    
	    onSelect: function(date){
			var currentDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
//			savePlanDate(currentDate,studyNo,nodeName);
			document.getElementById("calendar_click").href="javascript:"+clickName+"(\'"+
			currentDate+"\',\'"+studyNo+"\',\'"+nodeName+"\');";
			var calendar_click = document.getElementById("calendar_click");
			calendar_click.click();
			$('#calendarDialog').dialog('close');
		}
	}); 
	$('#calendarDialog').dialog('open');
	if(oldDate){
		$('#cc').calendar('moveTo',new Date(oldDate));
	}else{
		$('#cc').calendar('moveTo',new Date());
	}
}
