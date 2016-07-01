<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看日程</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblAnimal.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimal.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblTiprpAppInd.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">

	var studyNoPara=$('#studyNoPara').val();

	var previewTable;
	var sameTaskNameTable;
	$(function(){
		//预览
		sameTaskNameTable=$('#sameTaskNameTable').datagrid({
			height:560,
			fit:false,
			fitColumns:false,
			resizable:true,
			onClickRow :function(rowIndex, rowData){
			  $('#rowColor').val(rowIndex);
			 $(this).datagrid('rejectChanges');
			  $(this).datagrid('unselectAll');
			  $(this).datagrid('selectRow',rowIndex);
		      $(this).datagrid('refreshRow',rowIndex);
		    },
		    rowStyler: function(index,row){
		    	var rowColor=$('#rowColor').val();
		    	
		    	 if(row.week == "六" ){
		    		 if( index == rowColor ){
							return 'background-color:#0092DC;color:#FFFFFF;';		 
				    }else{
					    	return 'background-color:#F2F2F2;color:#080808;';
				    }
				 }else if(row.week== "日"){
					 if( index == rowColor ){
							return 'background-color:#0092DC;color:#FFFFFF;';		 
				      }else{
						 return 'background-color:#BFEFFF;color:#080808;';
				      }
				 }	
			},
        });
		
	    
		//表头居中
		$('.datagrid-header div').css('textAlign','center');

		//显示整个布局
		$('#SchedulePlanPreviewDiv').css('display','');  
		$('#toolbar').css('display',''); 

		showtimeSchedulePlan();
		
	});
	
	 function onCheckbox1(){
		   var isValidationPara=$('#isValidationPara').val();
		   var disPlaytype=$('#disPlaytype').val();
		   if( isValidationPara == "1"){
			   $('#isValidationPara').val(0) ;
			   document.getElementById("checkbox1").checked=false;
				listDisplay();
	   	  }else{
	   		 $('#isValidationPara').val(1) ;
	   		 document.getElementById("checkbox1").checked=true;
			 listDisplay();
	       }
	    }
   
    function listDisplay(){
    	 $.ajax({
				url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns3.action',
				type: 'post',
				data: {
    		        sameTaskName:$('#taskName').val(),
					isValidationPara:$('#isValidationPara').val(),
					startime:$('#startime').val(),
		            endtime:$('#endtime').val()
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						sameTaskNameTable.datagrid({
							columns:r.columns
						});
						sameTaskNameTable.datagrid('loadData',r.rows);
					}else{
						sameTaskNameTable.datagrid({
							columns:[]
						});
						$('#sameTaskNameTable').datagrid('loadData',{total:0,rows:[]});
					}
				}
         });
         
    }

    //按时间查询
    function timeSelectSchedulePlan(){
      var startime = $('#showStartime').datebox('getValue');
      var endtime =  $('#showEndtime').datebox('getValue');
      $('#startime').val(startime);
      $('#endtime').val(endtime);
      listDisplay();
    
    }

    //显示开始结束时间
    function showtimeSchedulePlan(){
    	var startime = $('#startime').val();
    	var endtime = $('#endtime').val();
    	var taskName = $('#taskName').val();
        $('#showStartime').datebox('setValue', startime);
        $('#showEndtime').datebox('setValue', endtime);
        $('#taskItemType').val(taskName);
        listDisplay();
    }

    
</script>
</head>
<body >
    <div id="SchedulePlanPreviewDiv"  style="width:100%;height:100%; display:none;">
    <s:hidden id="left_member" name="left_member"></s:hidden>
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <s:hidden id="isValidationPara" name="isValidationPara"></s:hidden>
    <s:hidden id="endtime" name="endtime"></s:hidden>
    <s:hidden id="startime" name="startime"></s:hidden>
    <s:hidden id="taskName" name="taskName"></s:hidden>
      <!-- 选择日期的颜色 -->
    <s:hidden id="rowColor" name="rowColor"></s:hidden>
	<div id="toolbar" style="display:none;height:25px;">
	 任务名称 ：<input id="taskItemType" type="text" name="" style="width:80px;"  required="true" readonly="true" />
	 <input id="checkbox1" type='checkbox' ${isValidationPara == 1 ?'checked':''}  style="vertical-align:middle" onclick="onCheckbox1();" />
	<a id="checkboxa1" href="javascript:onCheckbox1();"  style="color:black;">显示空白日期</a>
	<span style="width:500px;">开始日期 <input id="showStartime" type="text" name="" class="easyui-datebox" required="true"  /> </span>
	<span style="width:500px;">结束日期<input id="showEndtime" type="text" name="" class="easyui-datebox" required="true" /> </span>
	<a id="timeSchedulePlanButton"  class="easyui-linkbutton" onclick="timeSelectSchedulePlan();" data-options="iconCls:'icon-search',plain:true">查询</a>	 
	</div>
	<div ><table id="sameTaskNameTable" ></table></div>
	 </div>
</body>
</html>
