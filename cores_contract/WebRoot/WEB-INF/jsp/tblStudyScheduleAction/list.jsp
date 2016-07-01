<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>进度</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<style type="text/css">
.tree-icon {
	width:0px;
}
</style>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 28;
		tableWidth  = document.body.clientWidth;
		var contractCode = $('#contractCode').val();
		$('#tblStudyScheduleTable').treegrid({ 
			url : sybp()+'/tblStudyScheduleAction_loadList.action?contractCode='+contractCode,
			idField:'id',    
			height:tableHeight,
			width:tableWidth,
		    treeField:'studyNo', 
		    animate:false,   
		    singleSelect: true, //不支持多选
		    columns:[[  
		                {title:'id',field:'id',width:180,hidden:true},
				        {title:'项目编号/进度节点',field:'studyNo',width:180,halign:'center'},  
				        {title:'供试品编号/计划日期',field:'tiNo',width:180,halign:'center'},
				        //{title:'供试品类型',field:'tiType',width:70},
				        {title:'课题类型/完成日期',field:'studyName',width:200,halign:'center'},
				        {title:'项目进度',field:'progress',width:280,formatter:formatProgress,halign:'center'},
				        {title:'',field:'sd',width:280},
				        {field:'_parentId',title:'_parentId',width:10,hidden:true},
				    ]]
		 });
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
    //加载进度条
    function formatProgress(value){
    	if (value){
    	   	var strs= new Array(); //定义一数组 
			strs=value.split("#"); //字符分割 
			var show;
			if(strs[1] && strs[2]){
			   show = strs[1]+' '+ strs[2];
			}else{
			   show ='&nbsp;';
			}
			var s ;
			if(strs[3] != '' &&  strs[3] && dateCompare(strs[3],strs[2])){
			    s = '<div style="width:100%;border:1px solid #ccc;text-align:center;">' +
	    			'<div style="width:' + strs[0] + '%;background:#EEB422;color:#FF0000";>' +show + '</div>'
	    			'</div>';
			}else{
			    s = '<div style="width:100%;border:1px solid #ccc;text-align:center;">' +
	    			'<div style="width:' + strs[0] + '%;background:#EEB422;color:#000";>' +show + '</div>'
	    			'</div>';
			}
	    	
	    	return s;
    	} else {
	    	return '';
    	}
	}
	
	//比较时间大小
function dateCompare(startdate,enddate){   
     var arr=startdate.split("-");    
     var starttime=new Date(arr[0],arr[1],arr[2]);    
     var starttimes=starttime.getTime();   
     var arrs=enddate.split("-");    
     var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
     var lktimes=lktime.getTime();   
     if(starttimes>=lktimes){   
       return false;   
     }else{
       return true;
     }   
 }  
</script>
</head>
<body>
<s:hidden id="contractCode" name="contractCode"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"
	style="width: 100%; height: 100%; left:-1px;top:-1px; display: none;">
	<div region="center" title="" style="overflow: hidden;">
		<div>
			<table id="tblStudyScheduleTable"></table>
		</div>
	</div>
</div>
</body>
</html>
