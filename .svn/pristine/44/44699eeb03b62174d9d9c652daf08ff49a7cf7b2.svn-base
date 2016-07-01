<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblAnimal.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimal.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblTiprpAppInd.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">

	var studyNoPara=$('#studyNoPara').val();

	var previewTable;
	var taskNameTable;
	$(function(){
		 taskNameTable=$('#taskNameTable').datagrid({
				url : sybp()+"/tblSchedulePlanAction_selectTaskItemType.action?studyNoPara="+$('#studyNoPara').val(),
				title:'',
				height: 460,
				width:140,
				iconCls:'',//'icon-save',
				//pagination:true,//下面状态栏
				pageSize:100,
				pageList:[50,100],
				//fit:true,
				fitColumns:true,//不出现横向滚动条
		    	nowarp:  false,//单元格里自动换行
			   //border:false,
			   //idField:'id', //pk
			   //sortName:'aniSerialNum',//排序字段
			   //sortOrder:'desc',//排序方法
				columns :[[
				{
					title : '任务类型',
					field : 'taskName',
					width : 60,
					align :'center',
				}]],
				onClickRow :function(rowIndex, rowData){	
			     var allrows = $(this).datagrid('getRows');
			     $(this).datagrid('unselectRow',allrows.length-1);	
			     var rows = $(this).datagrid('getSelections');
			    
			     var isValidationPara=$('#isValidationPara').val();
			     var disPlaytype=$('#disPlaytype').val();
			     if( disPlaytype == "1"){
			    	 if(rowData.taskName == "全部"){
			    		 $(this).datagrid('selectAll');	
			    		   listDisplay();	
					 }else{
							var ary = new Array();
							for(var j=0;j<rows.length;j++){
									if($('#taskNameTable').datagrid('getSelections')[j].taskName!="全部"){
										var gettaskName  =  $('#taskNameTable').datagrid('getSelections')[j].taskName;
										ary = ary.concat(gettaskName);
								    }
								}
							var getSelections = ary.join(",");
							$.ajax({
								url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns.action',
								type: 'post',
								contentType: "application/x-www-form-urlencoded; charset=utf-8", 
								data: {
									studyNoPara:$('#studyNoPara').val(),
									isValidationPara:$('#isValidationPara').val(),
									getTaskNames:getSelections,
								},
								dataType:'json',
								success:function(r){
									if(r){
										previewTable.datagrid({
											columns:r.columns
										});
										previewTable.datagrid('loadData',r.rows);
									}
								}
				      });		
				  }
				 }else{
                    if(rowData.taskName == "全部"){
			    		 $(this).datagrid('selectAll');	
			    		 tableDisplay();	
					 }else{
							var ary = new Array();
							for(var j=0;j<rows.length;j++){
									if($('#taskNameTable').datagrid('getSelections')[j].taskName!="全部"){
										var gettaskName  =  $('#taskNameTable').datagrid('getSelections')[j].taskName;
										ary = ary.concat(gettaskName);
								    }
								}
							var getSelections = ary.join(",");
							$.ajax({
								url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns2.action',
								type: 'post',
								contentType: "application/x-www-form-urlencoded; charset=utf-8", 
								data: {
									studyNoPara:$('#studyNoPara').val(),
									isValidationPara:$('#isValidationPara').val(),
									getTaskNames:getSelections,
								},
								dataType:'json',
								success:function(r){
									if(r){
										previewTable.datagrid({
											columns:r.columns
										});
										previewTable.datagrid('loadData',r.rows);
									}
								}
				      });		
				  }
			     }
			    	
					  
			    } ,
			    onLoadSuccess:function(date){
			    	 $(this).datagrid('selectAll');	
			    }     
			}); //end 
			
		//预览
		previewTable=$('#previewTable').datagrid({
			height:460,
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
		var disPlaytype=$('#disPlaytype').val();
		if(disPlaytype==1){
			listDisplay();//表格显示
	    }else{
	    	tableDisplay();//列表显示
		}
		
		//表头居中
		$('.datagrid-header div').css('textAlign','center');
		
		var member = $('#left_member').val();
        if(member != ""){
          $('#addSchedulePlanButton').linkbutton('disable');
        }
		//显示整个布局
		$('#SchedulePlanPreviewDiv').css('display','');  
		$('#toolbar').css('display',''); 
		
		//initButton();
		
	});

   function backSchedulePlan(){
	   window.location.href= sybp()+"/tblSchedulePlanAction_list.action?studyNoPara="+$('#studyNoPara').val();
   }

   function onCheckbox1(){
	   var isValidationPara=$('#isValidationPara').val();
	   var disPlaytype=$('#disPlaytype').val();
	   if( isValidationPara == "1"){
		   $('#isValidationPara').val("0") ;
           document.getElementById("checkbox1").checked=false;
		   if( disPlaytype == "1"){
			   listDisplay();
		   }else{
			   tableDisplay();
		   }
   	  }else{
   		  $('#isValidationPara').val("1") ;
           document.getElementById("checkbox1").checked=true;
   	       if( disPlaytype == "1"){
		      listDisplay();
	        }else{
		     tableDisplay();
	        }
       }
    }

 	//表格显示
    function listDisplay(){
    	 $('#disPlaytype').val(1);
    	 $('#taskNameTable').datagrid('selectAll');	     
    	 $.ajax({
				url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					isValidationPara:$('#isValidationPara').val(),
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						previewTable.datagrid({
							frozenColumns: r.frozenColumns,
							columns:r.columns,
						});
						previewTable.datagrid('loadData',r.rows);
					}
				}
         });
    }

    function tableDisplay(){
      $('#disPlaytype').val(2);
      $('#taskNameTable').datagrid('selectAll');	
   	  $.ajax({
				url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns2.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					isValidationPara:$('#isValidationPara').val(),
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						previewTable.datagrid({
							frozenColumns:r.frozenColumns,
							columns:r.columns,
						});
						previewTable.datagrid('loadData',r.rows);
					}else{

				    }
				}
     });
   }
    
</script>
</head>
<body>
    <div id="SchedulePlanPreviewDiv"  style="width:100%;height:100%; display:none;">
    <s:hidden id="left_member" name="left_member"></s:hidden>
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <s:hidden id="isValidationPara" name="isValidationPara"></s:hidden>
    <s:hidden id="studyStateMain" name="scheduleState"></s:hidden>
     <s:hidden id="reason"></s:hidden>
    <!-- 显示类型，1表示类别，2表示表格 -->
    <s:hidden id="disPlaytype" name="disPlaytype"></s:hidden>
    <!-- 编辑前日程安排的日期 -->
    <s:hidden id="oldDatetime" name="oldDatetime"></s:hidden>
    <!-- 选择日期的颜色 -->
    <s:hidden id="rowColor" name="rowColor"></s:hidden>
	<div id="toolbar" style="display:none;">
		<a id="addSchedulePlanButton"  class="easyui-linkbutton" onclick="backSchedulePlan();" data-options="iconCls:'icon-edit',plain:true">设置</a>
		<a id="listDisplayButton"  class="easyui-linkbutton" onclick="tableDisplay();" data-options="iconCls:'',plain:true">列表显示</a>
		<a id="tableDisplayButton"  class="easyui-linkbutton" onclick="listDisplay();" data-options="iconCls:'',plain:true">表格显示</a>
		<input id="checkbox1" type='checkbox' ${isValidationPara == 1 ?'checked':''}  style="width:30px;vertical-align:middle" onclick="onCheckbox1();" /><a id="checkboxa1" href="javascript:onCheckbox1();">显示空白日期</a>
		<!-- 
		<a id="signSchedulePlanButton"  class="easyui-linkbutton" onclick="signSchedulePlan();" data-options="iconCls:'icon-edit',plain:true">签字确认</a>
		<a id="applyChangeButton" class="easyui-linkbutton" plain="true"  onclick="applyChangeSchedule();" data-options="iconCls:'icon-tablerefresh'">申请修改</a>  
		 -->
	</div>
   
   
   <div class="easyui-layout" fit="true" border="false" >

		<div region="west" border="false" style="width:150px;">
        	<table id="taskNameTable" ></table> 
		</div>
		
		<div region="center" border="false" style="width:450px;">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false">
				<div><table id="previewTable" ></table></div>
				</div>

			</div>
		</div>
	</div>

   
     <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>

	 </div>
</body>
</html>
