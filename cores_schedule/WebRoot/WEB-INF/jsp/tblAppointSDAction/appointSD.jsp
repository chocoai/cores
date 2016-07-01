<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SD任命</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAppointSDAction/studyScheduleNode.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAppointSDAction/studySchedule.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAppointSDAction/studyScheduleInfo.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAppointSDAction/appointSD.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblAppointSDAction/calendarDialog.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/table.css"  />

<style type="text/css">
	a{
		text-decoration:underline;
	}
</style>

<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
	        //  $.messager.defaults = { ok: "是", cancel: "否" }; 
	         tableHeight = document.body.clientHeight - 55;
		     tableWidth  = document.body.clientWidth;
	         $('#appointSDTable').datagrid({
					title:'',
					height:tableHeight,
					idField:'id',
					width:tableWidth,
					nowarp:  false,//单元格里自动换行
					fitColumns:false,
					pagination:true,//分页
					onSelect:function(rowIndex, rowData){
				      var role =  $('#role').val();
				      var department =  $('#department').val();
				      $('#studyAttachmentButton').linkbutton('disable');
				      //FM角色
                      if(role == "FM"){
					     var rows = $(this).datagrid('getSelections');
					     var allRows = $(this).datagrid('getRows');
					     $('#onAppointSDButton').linkbutton('enable'); 
					     $('#onSubmitAppointSDButton').linkbutton('enable'); 
					     $('#onAgainEditButton').linkbutton('enable'); 
					     $('#printerButton').linkbutton('enable');
					     $('#studyAttachmentButton').linkbutton('enable');//审批方案用，所以FM要可以显示
					     if(rows.length <= 0){
							     $('#onAppointSDButton').linkbutton('disable'); 
							     $('#onSubmitAppointSDButton').linkbutton('disable'); 
							     $('#onAgainEditButton').linkbutton('disable'); 
							     $('#printerButton').linkbutton('disable');
							     $('#studyAttachmentButton').linkbutton('disable');
					     }else{
						     for(var i = 0 ;i<rows.length;i++){
					                //是否有任命时间 
					                if(rows[i].sdAppointDate  != "" && rows[i].sdAppointDate != null ){
						               $('#onAppointSDButton').linkbutton('disable'); 
							        }
							        if( (rows[i].sdState  == 1 ) || rows[i].sd == ""   ){
							           $('#onSubmitAppointSDButton').linkbutton('disable');
							        }
							        if( rows[i].sdState  != 1  || rows[i].sd == ""  ){
							           $('#onAgainEditButton').linkbutton('disable'); 
							        }
							        if( rows[i].sdState  != 1  || rows[i].sd == ""  ){
							            $('#printerButton').linkbutton('disable');
							        }
						      }
					     }
					      if(rows.length == 1 &&  rows[0].sdState  == 1){
					    	  $('#studyScheduleInfoButton').linkbutton('enable');
					    	  $('#studyAttachmentButton').linkbutton('enable');
						  }else{
					    	  $('#studyScheduleInfoButton').linkbutton('disable');
					    	  $('#studyAttachmentButton').linkbutton('disable');
						  }
					   		//设置项目进度计划  按钮
							$('#studyScheduleNodeButton').linkbutton('disable');	
							$('#studyScheduleButton').linkbutton('disable');	
					   }else if(role == "SD"  || department == "true"){
					        if(rowData.own == "1"){
							   $('#studyAttachmentButton').linkbutton('enable');//专题SD才可以操作
					            //设置项目进度计划  按钮
							  	$('#studyScheduleNodeButton').linkbutton('enable');
	
							    //设置项目进度  按钮
							  	$('#studyScheduleButton').linkbutton('disable');
							  	var isSubmit = $.ajax({
								  	                  url:sybp()+'/tblStudyScheduleAction_isSubmit.action',
								            	      dataType: "json",   
				            		                  data: {
										                   studyNo:rowData.studyNo
				            	   	                  },   
				            		                  async: false,   
				            		                  cache: false,   
				            		                  type: "post"  
								               }).responseText;
								if(isSubmit == 'true'){
									$('#studyScheduleButton').linkbutton('enable');
								}	
					        
					        }else{
					            $('#studyScheduleButton').linkbutton('disable');
					            $('#studyScheduleNodeButton').linkbutton('disable');
					            $('#studyAttachmentButton').linkbutton('disable');//专题SD才可以操作
					        }
													  
					   }else if(role == "QALead"){
					          var rows = $(this).datagrid('getSelections');
					          $('#onAppointQAButton').linkbutton('enable'); 
					          $('#onSubmitAppointQAButton').linkbutton('enable'); 
					          $('#onAgainEditQAButton').linkbutton('enable'); 
					          if(rows.length <= 0){
							     $('#onAppointQAButton').linkbutton('disable'); 
							     $('#onSubmitAppointQAButton').linkbutton('disable'); 
							     $('#onAgainEditQAButton').linkbutton('disable'); 
					         }else{
						        for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].qaAppointDate   != "" && rows[i].qaAppointDate  != null ){
							           $('#onAppointQAButton').linkbutton('disable'); 
							        }if( (rows[i].qaState  == 1 ) || rows[i].qa == ""   ){
							           $('#onSubmitAppointQAButton').linkbutton('disable');
							        }if( rows[i].qaState  != 1  || rows[i].qa == ""  ){
							           $('#onAgainEditQAButton').linkbutton('disable'); 
							        }
						        }
					        }
					          if(rows.length == 1 ){
						    	  $('#studyScheduleInfoButton').linkbutton('enable');
							  }else{
						    	  $('#studyScheduleInfoButton').linkbutton('disable');
							  }
					   }else if(role == "PathSDLead"){
					         var rows = $(this).datagrid('getSelections');
					          $('#onAppointPAButton').linkbutton('enable'); 
					          $('#onSubmitAppointPAButton').linkbutton('enable'); 
					          $('#onAgainEditPAButton').linkbutton('enable'); 
					          if(rows.length <= 0){
							     $('#onAppointPAButton').linkbutton('disable'); 
							     $('#onSubmitAppointPAButton').linkbutton('disable'); 
							     $('#onAgainEditPAButton').linkbutton('disable'); 
					         }else{
						        for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].pathSDDate  != "" && rows[i].pathSDDate != null ){
							           $('#onAppointPAButton').linkbutton('disable'); 
							        }if( (rows[i].pathState  == 1 ) || rows[i].pathSD == ""   ){
							           $('#onSubmitAppointPAButton').linkbutton('disable');
							        }if( rows[i].pathState  != 1  || rows[i].pathSD == ""  ){
							           $('#onAgainEditPAButton').linkbutton('disable'); 
							        }
						        }
					        }
					          if(rows.length == 1 ){
						    	  $('#studyScheduleInfoButton').linkbutton('enable');
							  }else{
						    	  $('#studyScheduleInfoButton').linkbutton('disable');
							  }
					   }else{
						   $('#studyScheduleInfoButton').linkbutton('enable');
					   }
                      var rows = $(this).datagrid('getSelections');
					  if(rows.length == 1&&rowData.isFileDis==1)
				      {
				    	  $('#studyAttachmentButton').linkbutton('enable');
					  }
					   
					},
					onUnselect:function(rowIndex, rowData){
				      var role =  $('#role').val();
				      var department =  $('#department').val();
				      //FM角色
                      if(role == "FM"){
					     var rows = $(this).datagrid('getSelections');
					     var allRows = $(this).datagrid('getRows');
					     $('#onAppointSDButton').linkbutton('enable'); 
					     $('#onSubmitAppointSDButton').linkbutton('enable'); 
					     $('#onAgainEditButton').linkbutton('enable'); 
					     $('#printerButton').linkbutton('enable');
					     if(rows.length <= 0){
							     $('#onAppointSDButton').linkbutton('disable'); 
							     $('#onSubmitAppointSDButton').linkbutton('disable'); 
							     $('#onAgainEditButton').linkbutton('disable'); 
							     $('#printerButton').linkbutton('disable');
					     }else{
						     for(var i = 0 ;i<rows.length;i++){
						                //是否有任命时间 
						                if(rows[i].sdAppointDate  != "" && rows[i].sdAppointDate != null ){
							               $('#onAppointSDButton').linkbutton('disable'); 
								        }if( (rows[i].sdState  == 1 ) || rows[i].sd == ""   ){
								           $('#onSubmitAppointSDButton').linkbutton('disable');
								        }if( rows[i].sdState  != 1  || rows[i].sd == ""  ){
								           $('#onAgainEditButton').linkbutton('disable'); 
								        }if( rows[i].sdState  != 1  || rows[i].sd == ""  ){
								            $('#printerButton').linkbutton('disable');
								        }
						      }
					     }
						 if(rows.length == 1 &&  rows[0].sdState  == 1){
						    $('#studyScheduleInfoButton').linkbutton('enable');
						 }else{
						    $('#studyScheduleInfoButton').linkbutton('disable');
						 }
					   		//设置项目进度计划  按钮
							$('#studyScheduleNodeButton').linkbutton('disable');	
							$('#studyScheduleButton').linkbutton('disable');	
					   }else if(role == "SD"  || department == "true"){
					        if(rowData.own == "1"){
					            //设置项目进度计划  按钮
							  	$('#studyScheduleNodeButton').linkbutton('enable');
	
							    //设置项目进度  按钮
							  	$('#studyScheduleButton').linkbutton('disable');
							  	var isSubmit = $.ajax({
								  	                  url:sybp()+'/tblStudyScheduleAction_isSubmit.action',
								            	      dataType: "json",   
				            		                  data: {
										                   studyNo:rowData.studyNo
				            	   	                  },   
				            		                  async: false,   
				            		                  cache: false,   
				            		                  type: "post"  
								               }).responseText;
								if(isSubmit == 'true'){
									$('#studyScheduleButton').linkbutton('enable');
								}	
					        
					        }else{
					            $('#studyScheduleButton').linkbutton('disable');
					            $('#studyScheduleNodeButton').linkbutton('disable');
					        }
													  
					   }else if(role == "QALead"){
					          var rows = $(this).datagrid('getSelections');
					          $('#onAppointQAButton').linkbutton('enable'); 
					          $('#onSubmitAppointQAButton').linkbutton('enable'); 
					          $('#onAgainEditQAButton').linkbutton('enable'); 
					          if(rows.length <= 0){
							     $('#onAppointQAButton').linkbutton('disable'); 
							     $('#onSubmitAppointQAButton').linkbutton('disable'); 
							     $('#onAgainEditQAButton').linkbutton('disable'); 
					         }else{
						        for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].qaAppointDate   != "" && rows[i].qaAppointDate  != null ){
							           $('#onAppointQAButton').linkbutton('disable'); 
							        }if( (rows[i].qaState  == 1 ) || rows[i].qa == ""   ){
							           $('#onSubmitAppointQAButton').linkbutton('disable');
							        }if( rows[i].qaState  != 1  || rows[i].qa == ""  ){
							           $('#onAgainEditQAButton').linkbutton('disable'); 
							        }
						        }
					        }
					          if(rows.length == 1 ){
						    	  $('#studyScheduleInfoButton').linkbutton('enable');
							  }else{
						    	  $('#studyScheduleInfoButton').linkbutton('disable');
							  }
					   }else if(role == "PathSDLead"){
					         var rows = $(this).datagrid('getSelections');
					          $('#onAppointPAButton').linkbutton('enable'); 
					          $('#onSubmitAppointPAButton').linkbutton('enable'); 
					          $('#onAgainEditPAButton').linkbutton('enable'); 
					          if(rows.length <= 0){
							     $('#onAppointPAButton').linkbutton('disable'); 
							     $('#onSubmitAppointPAButton').linkbutton('disable'); 
							     $('#onAgainEditPAButton').linkbutton('disable'); 
					         }else{
						        for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].pathSDDate  != "" && rows[i].pathSDDate != null ){
							           $('#onAppointPAButton').linkbutton('disable'); 
							        }if( (rows[i].pathState  == 1 ) || rows[i].pathSD == ""   ){
							           $('#onSubmitAppointPAButton').linkbutton('disable');
							        }if( rows[i].pathState  != 1  || rows[i].pathSD == ""  ){
							           $('#onAgainEditPAButton').linkbutton('disable'); 
							        }
						        }
					        }
					          if(rows.length == 1 ){
						    	  $('#studyScheduleInfoButton').linkbutton('enable');
							  }else{
						    	  $('#studyScheduleInfoButton').linkbutton('disable');
							  }
					   }else{
						   $('#studyScheduleInfoButton').linkbutton('disable');
						}
					   
					},
					onCheckAll:function(rows){
					  var role =  $('#role').val();
					  var department =  $('#department').val();
                      if(role == "FM"){
					     var rows = $(this).datagrid('getSelections');
					     $('#onAppointSDButton').linkbutton('enable'); 
					     $('#onSubmitAppointSDButton').linkbutton('enable'); 
					     $('#onAgainEditButton').linkbutton('enable'); 
					     $('#printerButton').linkbutton('enable');
					     if(rows.length <= 0){
							     $('#onAppointSDButton').linkbutton('disable'); 
							     $('#onSubmitAppointSDButton').linkbutton('disable'); 
							     $('#onAgainEditButton').linkbutton('disable'); 
							     $('#printerButton').linkbutton('disable');
					     }else{
						     for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].sdAppointDate  != "" && rows[i].sdAppointDate != null ){
							           $('#onAppointSDButton').linkbutton('disable'); 
							        }if( (rows[i].sdState  == 1 ) || rows[i].sd == ""   ){
							           $('#onSubmitAppointSDButton').linkbutton('disable');
							        }if( rows[i].sdState  != 1  || rows[i].sd == ""  ){
							           $('#onAgainEditButton').linkbutton('disable'); 
							        }if( rows[i].sdState  != 1  || rows[i].sd == ""  ){
							            $('#printerButton').linkbutton('disable');
							        }
							        
						      }
					     }

					   		//设置项目进度计划  按钮
							$('#studyScheduleNodeButton').linkbutton('disable');	
							$('#studyScheduleButton').linkbutton('disable');	
					   }else if(role == "SD" || department == "true"){
							//设置项目进度计划  按钮
						  	$('#studyScheduleNodeButton').linkbutton('enable');

						    //设置项目进度  按钮
						  	$('#studyScheduleButton').linkbutton('disable');
						  	var isSubmit = $.ajax({
							  	                  url:sybp()+'/tblStudyScheduleAction_isSubmit.action',
							            	      dataType: "json",   
			            		                  data: {
									                   studyNo:rowData.studyNo
			            	   	                  },   
			            		                  async: false,   
			            		                  cache: false,   
			            		                  type: "post"  
							               }).responseText;
							if(isSubmit == 'true'){
								$('#studyScheduleButton').linkbutton('enable');
							}							  
					   }else if(role == "QALead"){
					          var rows = $(this).datagrid('getSelections');
					          $('#onAppointQAButton').linkbutton('enable'); 
					          $('#onSubmitAppointQAButton').linkbutton('enable'); 
					          $('#onAgainEditQAButton').linkbutton('enable'); 
					          if(rows.length <= 0){
							     $('#onAppointQAButton').linkbutton('disable'); 
							     $('#onSubmitAppointQAButton').linkbutton('disable'); 
							     $('#onAgainEditQAButton').linkbutton('disable'); 
					         }else{
						        for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].qaAppointDate  != "" && rows[i].qaAppointDate != null ){
							           $('#onAppointQAButton').linkbutton('disable'); 
							        }if( (rows[i].qaState  == 1 ) || rows[i].qa == ""   ){
							           $('#onSubmitAppointQAButton').linkbutton('disable');
							        }if( rows[i].qaState  != 1  || rows[i].qa == ""  ){
							           $('#onAgainEditQAButton').linkbutton('disable'); 
							        }
						        }
					        }
					   }else if(role == "PathSDLead"){
					         var rows = $(this).datagrid('getSelections');
					          $('#onAppointPAButton').linkbutton('enable'); 
					          $('#onSubmitAppointPAButton').linkbutton('enable'); 
					          $('#onAgainEditPAButton').linkbutton('enable'); 
					          if(rows.length <= 0){
							     $('#onAppointPAButton').linkbutton('disable'); 
							     $('#onSubmitAppointPAButton').linkbutton('disable'); 
							     $('#onAgainEditPAButton').linkbutton('disable'); 
					         }else{
						        for(var i = 0 ;i<rows.length;i++){
							        if(rows[i].pathSDDate  != "" && rows[i].pathSDDate != null ){
							           $('#onAppointPAButton').linkbutton('disable'); 
							        }if( (rows[i].pathState  == 1 ) || rows[i].pathSD == ""   ){
							           $('#onSubmitAppointPAButton').linkbutton('disable');
							        }if( rows[i].pathState  != 1  || rows[i].pathSD == ""  ){
							           $('#onAgainEditPAButton').linkbutton('disable'); 
							        }
						        }
					        }
					   
					   }
					},
					onUncheckAll:function(rows){
					    $('#onAppointSDButton').linkbutton('disable'); 
					    $('#onSubmitAppointSDButton').linkbutton('disable'); 
					    $('#onAgainEditButton').linkbutton('disable'); 
					    $('#printerButton').linkbutton('disable');
					    $('#onAppointQAButton').linkbutton('disable'); 
					    $('#onSubmitAppointQAButton').linkbutton('disable'); 
					    $('#onAgainEditQAButton').linkbutton('disable'); 
					    $('#onAppointPAButton').linkbutton('disable');
					    $('#onSubmitAppointPAButton').linkbutton('disable'); 
					    $('#onAgainEditPAButton').linkbutton('disable'); 
					    $('#studyScheduleNodeButton').linkbutton('disable'); 
					    $('#studyScheduleButton').linkbutton('disable');
					    $('#studyScheduleInfoButton').linkbutton('disable');
					},
					onLoadSuccess:function(data){
						$(this).datagrid('clearChecked');
						$(this).datagrid('clearSelections');
	                    $('#appointSDTable').datagrid("unselectAll");
					    //显示按键
	                    initroleButton();
					},
					toolbar:'#toolbar',
			   	}); //end datagrid
			   	
	    	 var role =  $('#role').val();
	    	 var isExistFileDis = $('#isExistFileDisForAppoint').val();
	         var department =  $('#department').val();
	         if( department == "true"){
	            $('#appointSDTable').datagrid({
					rowStyler: function(index,row){
						if (row.own != "1" ){
							return 'color:#FFA500 ;';
						}
					}
				});
	         }
             if(role == "SD" ||role == "QA" || role == "PathSD" || department == "true" ||(role!="FM"&&role!="QALead"&&role!="PathSDLead"&&isExistFileDis)){
		       $('#appointSDTable').datagrid({
		          singleSelect:true,
		          columns :[[{
						title:'id',
						field:'id',
						width:200,
						halign:'center',
						align:'left',
					    hidden:true,
					    sortable:'true'
					},{
						title:'isSDM',
						field:'isSDM',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true'
					}, 
					{
						title:'sid',
						field:'sid',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true'
					}, 
					{
						title:'qid',
						field:'qid',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true' 
					},
					{
						title:'pid',
						field:'pid',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true'
					},
					{
						title:'供试品类型',
						field:'tiCode',
						width:65,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if (value == "01"){
								return "医药";
							} else if(value == "02"){
								return "农药";
							}else if(value == "03"){
								return "化学品";
							}
						}
					},
					{
						title:'合同签订日期',
						field:'signingDate',
						width:100,
						halign:'center',
						align:'left',
						sortable:'true'
					},
					/*{
						title:'合同提交日期',
						field:'recordTime',
						width:100,
						halign:'center',
						align:'left',
						sortable:'true'
					},*/{
						title:'合同编号',
						field:'contractCode',
						width:140,
						halign:'center',
						align:'left',
					    sortable:'true'
					},{
						title:'供试品编码',
						field:'tINo',
						width:150,
						halign:'center',
						align:'left',
					    sortable:'true'
					},{
						title:'项目名称',
						field:'studyName',
						width:240,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'专题编号',
						field:'studyNo',
						width:160,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'SD',
						field:'sd',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						field:'sdAppointDate',
						title:'SD任命日期',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						field:'finishDate',
						title:'要求完成日期',
						width:100,
						halign:'center',
						align:'left',
					},
					{
						title:'SD状态',
						field:'sdState',
						width:60,
						halign:'center',
						align:'center',
					    sortable:'true',
						formatter: function(value,row,index){
				            if (value == 1 ){
					            return "已确认";
							}else {
							     return "";
							} 
						}
					},
					{
						title:'方案状态',
						field:'fileState',
						width:70,
						halign:'center',
						align:'center',
					    sortable:'true',
					    formatter: function(value,row,index){
				           /* if (value == 0 ){
					            return "草稿";
							}else*/
							if (value == 1 ){
					            return "提交审批中";
							}else if (value == 2 ){
							     return "结束";
							} else{
								return "";
							}
								
						}
					},
					{
						title:'QA',
						field:'qa',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'QA任命时间',
						field:'qaAppointDate',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'QA状态',
						field:'qaState',
						width:60,
						halign:'center',
						align:'center',
					    sortable:'true',
						formatter: function(value,row,index){
				            if (value == 1 ){
					            return "已确认";
							}else {
							     return "";
							} 
						}
					},
					{
						title:'病理',
						field:'pathSD',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'病理任命时间',
						field:'pathSDDate',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'病理状态',
						field:'pathState',
						width:60,
						halign:'center',
						align:'center',
					    sortable:'true',
						formatter: function(value,row,index){
				            if (value == 1 ){
					            return "已确认";
							}else {
							     return "";
							} 
						}
					},
					
					{
						title:'进度',
						field:'progress',
						width:200,
						halign:'center',
						align:'left',
						formatter:formatProgress

					},
					{
						title:'备注',
						field:'remark',
						width:220,
						halign:'center',
						align:'left'
					},{
					    title:'own',
						field:'own',
						width:200,
						halign:'center',
						align:'left',
					    hidden:true,
					},{
						title:'isFileDis',
						field:'isFileDis',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					}
					]],
		       });
		  }else{
		      $('#appointSDTable').datagrid({
		          singleSelect:false,
		          columns :[[{
						title:'id',
						field:'id',
						width:200,
						halign:'center',
						align:'left',
					    checkbox:true,
					    sortable:'true'
					},{
						title:'isSDM',
						field:'isSDM',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true'
					}, 
					{
						title:'sid',
						field:'sid',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true'
					}, 
					{
						title:'qid',
						field:'qid',
						width:200,
						halign:'center',
						align:'left',
						hidden:true ,
					    sortable:'true'
					},
					{
						title:'pid',
						field:'pid',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					    sortable:'true'
					},
					{
						title:'供试品类型',
						field:'tiCode',
						width:65,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if (value == "01"){
								return "医药";
							} else if(value == "02"){
								return "农药";
							}else if(value == "03"){
								return "化学品";
							}
						}
						
					},
					{
						title:'合同签订日期',
						field:'signingDate',
						width:100,
						halign:'center',
						align:'left',
						sortable:'true'
					},
					/*
					{
						title:'合同提交日期',
						field:'recordTime',
						width:100,
						halign:'center',
						align:'left',
						sortable:'true'
					},*/{
						title:'合同编号',
						field:'contractCode',
						width:140,
						halign:'center',
						align:'left',
					    sortable:'true'
					},{
						title:'供试品编码',
						field:'tINo',
						width:150,
						halign:'center',
						align:'left',
					    sortable:'true'
					},{
						title:'项目名称',
						field:'studyName',
						width:240,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'专题编号',
						field:'studyNo',
						width:160,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'SD',
						field:'sd',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						field:'sdAppointDate',
						title:'SD任命日期',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'方案状态',
						field:'fileState',
						width:70,
						halign:'center',
						align:'center',
					    sortable:'true',
					    formatter: function(value,row,index){
				            if (value == 0 ){
					            return "草稿";
							}else if (value == 1 ){
					            return "提交审批中";
							}else if (value == 2 ){
							     return "结束";
							} else{
								return "";
							}
								
						}
					},
					{
						field:'finishDate',
						title:'要求完成日期',
						width:100,
						halign:'center',
						align:'left',
					},
					{
						title:'SD状态',
						field:'sdState',
						width:60,
						halign:'center',
						align:'center',
					    sortable:'true',
						formatter: function(value,row,index){
				            if (value == 1 ){
					            return "已确认";
							}else {
							     return "";
							} 
						}
					},
					{
						title:'QA',
						field:'qa',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'QA任命时间',
						field:'qaAppointDate',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'QA状态',
						field:'qaState',
						width:60,
						halign:'center',
						align:'center',
					    sortable:'true',
						formatter: function(value,row,index){
				            if (value == 1 ){
					            return "已确认";
							}else {
							     return "";
							} 
						}
					},
					{
						title:'病理',
						field:'pathSD',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'病理任命时间',
						field:'pathSDDate',
						width:100,
						halign:'center',
						align:'left',
					    sortable:'true'
					},
					{
						title:'病理状态',
						field:'pathState',
						width:60,
						halign:'center',
						align:'center',
						sortable:'true',
					    sortable:'true',
						formatter: function(value,row,index){
				            if (value == 1 ){
					            return "已确认";
							}else {
							     return "";
							} 
						}
					},
					
					{
						title:'进度',
						field:'progress',
						width:200,
						halign:'center',
						align:'left',
						formatter:formatProgress

					},
					{
						title:'备注',
						field:'remark',
						width:220,
						halign:'center',
						align:'left'
					},{
					    title:'own',
						field:'own',
						width:200,
						halign:'center',
						align:'left',
					    hidden:true,
					},
					{
						title:'remark2',
						field:'remark2',
						width:220,
						halign:'center',
						align:'left',
						hidden:true
					},
					{
						title:'partner',
						field:'partner',
						width:220,
						halign:'center',
						align:'left',
						hidden:true
					},{
						title:'isFileDis',
						field:'isFileDis',
						width:200,
						halign:'center',
						align:'left',
						hidden:true,
					}]],
		       });
		  }
	    
       
         $('#mindatebox').datebox({    
             required:true,
		     onSelect: function(date){
		              var startTime =  $('#mindatebox').datebox('getValue');
    	              var endTime = $('#maxdatebox').datebox('getValue');
		              var studystate= $('#studyItem_testItem').combobox('getValue');
		              var qastate= $('#qastudyItem_testItem').combobox('getValue');
		              var pastate= $('#pastudyItem_testItem').combobox('getValue');
		              var tiNo =  $('#studyType_testItemAndNo').combobox('getValue');
		              if(studystate == ""){
		                    studystate = -1;
		              }
		              if(qastate == ""){
		                    qastate = -1;
		              }
		              if(pastate == ""){
		                    pastate = -1;
		              }
		              if(tiNo == "" ){
		                 tiNo = -1;
		              }
		              var searchString = $('#searchBoxName').searchbox('getValue');
		              showappointSDTable(startTime,endTime,studystate,qastate,pastate,tiNo,searchString);   
					 
			  }
		    
		});  
		 $('#maxdatebox').datebox({    
		    required:true,
		     onSelect: function(date){
			          var startTime =  $('#mindatebox').datebox('getValue');
    	              var endTime = $('#maxdatebox').datebox('getValue');
		              var studystate= $('#studyItem_testItem').combobox('getValue');
		              var qastate= $('#qastudyItem_testItem').combobox('getValue');
		              var pastate= $('#pastudyItem_testItem').combobox('getValue');
		              if(studystate == ""){
		                    studystate = -1;
		              }
		              if(qastate == ""){
		                    qastate = -1;
		              }
		              if(pastate == ""){
		                    pastate = -1;
		              }
		              var tiNo =  $('#studyType_testItemAndNo').combobox('getValue');
		              if(tiNo == "" ){
		                 tiNo = -1;
		              }
		              var searchString = $('#searchBoxName').searchbox('getValue');
		              showappointSDTable(startTime,endTime,studystate,qastate,pastate,tiNo,searchString);   
			  }
		    
		}); 
		$('#searchBoxName').searchbox({ 
			 height:19,
		     width:300,
			 searcher:function(value,name){ 
			 var startTime =  $('#mindatebox').datebox('getValue');
             var endTime = $('#maxdatebox').datebox('getValue');
             var studystate= $('#studyItem_testItem').combobox('getValue');
             var qastate= $('#qastudyItem_testItem').combobox('getValue');
             var pastate= $('#pastudyItem_testItem').combobox('getValue');
             if(studystate == ""){
                   studystate = -1;
             }
             if(qastate == ""){
                   qastate = -1;
             }
             if(pastate == ""){
                   pastate = -1;
             }
             var tiNo =  $('#studyType_testItemAndNo').combobox('getValue');
             if(tiNo == "" ){
                tiNo = -1;
             }
            
			 showappointSDTable(startTime,endTime,studystate,qastate,pastate,tiNo,value);   
			 },
			prompt:'模糊查询,请输入供试品编号，项目名称，专题编号、SD、QA'  
		}); 
		
        initStudyItemCombobox();
        initQAStateCombobox();
        initPAStateCombobox();
        initCombobox();
        initstartTimeAndEndTimeAndtiCode();
		initStudyTypeTestItemAndNo();
		//显示整个布局
		$('#layoutMainDiv').css('display','');  
    });//匿名函数结束
    
    
    //初始化供试品类型
    function initStudyTypeTestItemAndNo(){
    	$('#studyType_testItemAndNo').combobox({
			url:sybp()+'/tblAppointSDAction_loadTestItemAndNOList.action',
			valueField:'id',
			textField:'text',
			onSelect: function(record){    
			  if(record.id != -1){
				  $('#appointSDTable').datagrid("unselectAll");
	           }
	           var startTime =  $('#mindatebox').datebox('getValue');
    	       var endTime = $('#maxdatebox').datebox('getValue');
		       var studystate= $('#studyItem_testItem').combobox('getValue');
		       var qastate= $('#qastudyItem_testItem').combobox('getValue');
		       var pastate= $('#pastudyItem_testItem').combobox('getValue');
		       if(studystate == ""){
		            studystate = -1;
		       }
		       if(qastate == ""){
		            qastate = -1;
		       }
		       if(pastate == ""){
		             pastate = -1;
		       }
		       var searchString = $('#searchBoxName').searchbox('getValue');
		       showappointSDTable(startTime,endTime,studystate,qastate,pastate,record.id,searchString);   
	          // if(record.id == -1){
	                $('#studyType_testItemAndNo').combobox('clear');
	           //}
	          
			}
		});
    }
   
    
     //初始化 供试品类型 开始日期 结束日期
    function  initstartTimeAndEndTimeAndtiCode(){
        var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		$('#mindatebox').datebox('setValue', startDate);
		$('#maxdatebox').datebox('setValue', endDate);
	    //部门负责人显示查看自己的专题信息
	    var department =  $('#department').val();
	    if( department == "true"){
			 $('#checkbox').css('display','');  
			 $('#chooseOwnButton').css('display','');  
	    }
    }
    
     //显示appointSDTable &&  qastate!= "" &&  pastate!= ""
    function  showappointSDTable(startDate,endDate,studystate,qastate,pastate,tiNo,searchString){
        	//alert(searchString);
            if(startDate != "" && endDate != "" &&  studystate!= "" &&  qastate!= "" &&  pastate!= "" ){
	            if(studystate== "已确认"){
	              studystate = 2;
	            }
	            if(qastate== "已确认"){
	              qastate = 2;
	            }
	            if(pastate== "已确认"){
	              pastate = 2;
	            }
	            var  chooseOwn =  $('#chooseOwn').val();
	            if( chooseOwn == "0"){
	               chooseOwn = "0";
	            }else{
	               chooseOwn = "1";
	               $('#chooseOwn').val('1');
	            }
             var selectRowsid =$('#selectRowsStudyNo').val();
               $('#appointSDTable').datagrid({
					url : sybp()+'/tblAppointSDAction_loadAppointSDList.action?startDate='
					+startDate+'&endDate='+endDate+'&state='+studystate+'&qastate='+qastate+"&pastate="+pastate+"&tiNo="+tiNo+"&chooseOwn="+chooseOwn+"&searchString="+searchString+"&isExistFileDis="+$('#isExistFileDisForAppoint').val(),
					pageNumber:1,
			        pageSize : 17,//默认选择的分页是每页5行数据         
			        pageList : [17,100,200,300,400,500],//可以选择的分页集合       
		            nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		            striped : true,//设置为true将交替显示行背景。      
		            collapsible : true,//显示可折叠按钮 
			   	}); 
			   	
			   var pager = $('#appointSDTable').datagrid('getPager');    // get the pager of datagrid
	           pager.pagination({
		          showRefresh:false,
		          loading:true,
		       });
			   	
            }
          
		    
    }
    

    //加载进度条
    function formatProgress(value){
	    	if (value){
	    	   var strs= new Array(); //定义一数组 
				strs=value.split("#"); //字符分割 
				var show;
				if(strs[1] && strs[2]){
				   show = strs[1]+''+ strs[2];
				}else{
				   show ='&nbsp;';
				}
	    	   
		    	var s ;
				if(strs[3] != '' && strs[3] && dateCompare(strs[3],strs[2])){
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
		//QA状态下拉选 
		 function initQAStateCombobox(){
		      $('#qastudyItem_testItem').combobox({
				url:sybp()+'/tblAppointSDAction_loadStudyItemStateList.action',
				valueField:'id',
				textField:'text',
		        panelHeight:'auto',
		        width:70,
		        height:19,
				onSelect: function(record){ 
				     // if(record.id != -1){
				             //alert("清空");
				             $('#appointSDTable').datagrid("unselectAll");
				     // }
				      var startTime =  $('#mindatebox').datebox('getValue');
    	              var endTime = $('#maxdatebox').datebox('getValue');
		              var studystate= $('#studyItem_testItem').combobox('getValue');
		              var qastate= $('#qastudyItem_testItem').combobox('getValue');
		              var pastate= $('#pastudyItem_testItem').combobox('getValue');
		              if(studystate == ""){
		                    studystate = -1;
		              }
		              if(pastate == ""){
		                    pastate = -1;
		              }
		               var tiNo =  $('#studyType_testItemAndNo').combobox('getValue');
		              if(tiNo == "" ){
		                 tiNo = -1;
		              }
		              var select =  $('#showAppointSDTable').val();
			          if(select == 1){
			        	  var searchString = $('#searchBoxName').searchbox('getValue');
		                showappointSDTable(startTime,endTime,studystate,qastate,pastate,tiNo,searchString);     
					   }
					   if(record.id == "-1"){
					       $('#qastudyItem_testItem').combobox('clear');
					   }
					 
 				},
 				onLoadSuccess:function(){
			         var QALead = $('#role').val();
					 if(QALead == "QALead"){
					    $('#qastudyItem_testItem').combobox('select',-1);  
					    $('#qastudyItem_testItem').combobox('enable');
					 }else if(QALead == "QA"){
					    $('#qastudyItem_testItem').combobox('disable');
					    $('#qastudyItem_testItem').combobox('select',2);
					    $('#qastudyItem_testItem').combobox('setValue','已确认');
					 }else{
					     $('#qastudyItem_testItem').combobox('select',-1);
					 }
 				    
 				}
			});
		 
		 }
    
       //SD状态下拉选
	   function initStudyItemCombobox(){
			$('#studyItem_testItem').combobox({
				url:sybp()+'/tblAppointSDAction_loadStudyItemStateList.action',
				valueField:'id',
				textField:'text',
		        panelHeight:'auto',
		        width:70,
		        height:19,
				onSelect: function(record){ 
				     // if(record.id != -1){
				         //alert("清空");
				         $('#appointSDTable').datagrid("unselectAll");
				      //}
				      var startTime =  $('#mindatebox').datebox('getValue');
    	              var endTime = $('#maxdatebox').datebox('getValue');
		              var studystate= $('#studyItem_testItem').combobox('getValue');
		              var qastate= $('#qastudyItem_testItem').combobox('getValue');
		              var pastate= $('#pastudyItem_testItem').combobox('getValue');
		              if(qastate == ""){
		                    qastate = -1;
		              }
		              if(pastate == ""){
		                    pastate = -1;
		              }
		              var tiNo =  $('#studyType_testItemAndNo').combobox('getValue');
		              if(tiNo == "" ){
		                 tiNo = -1;
		              }
		              var select =  $('#showAppointSDTable').val();
			          if(select == 1){
			        	  var searchString = $('#searchBoxName').searchbox('getValue');
		                showappointSDTable(startTime,endTime,studystate,qastate,pastate,tiNo,searchString);   
					  }
					  if(record.id == "-1"){
						      $('#studyItem_testItem').combobox('clear');
					  }
					 
 				},
 				onLoadSuccess:function(){
			       	var role =  $('#role').val();
               	    if(role == "FM"){
					    $('#studyItem_testItem').combobox('select',-1);  
					    $('#studyItem_testItem').combobox('enable');
					 }else if(role == "SD"){
					    $('#studyItem_testItem').combobox('disable');
					    $('#studyItem_testItem').combobox('select',2);
					    $('#studyItem_testItem').combobox('setValue','已确认');
					 }else{
					    $('#studyItem_testItem').combobox('select',-1);
					 }
 				    
 				}
			});
		}

		 //病理状态下拉选 
		 function initPAStateCombobox(){
		      $('#pastudyItem_testItem').combobox({
				url:sybp()+'/tblAppointSDAction_loadStudyItemStateList.action',
				valueField:'id',
				textField:'text',
		        panelHeight:'auto',
		        width:70,
		        height:19,
				onSelect: function(record){ 
				         //if(record.id != -1){
				          $('#appointSDTable').datagrid("unselectAll");
				         //}
				         var startTime =  $('#mindatebox').datebox('getValue');
		                 var endTime = $('#maxdatebox').datebox('getValue');
		                 var studystate= $('#studyItem_testItem').combobox('getValue');
		                 var qastate= $('#qastudyItem_testItem').combobox('getValue');
		                 var pastate= $('#pastudyItem_testItem').combobox('getValue');
		                 if(studystate == ""){
		                    studystate = -1;
		                 }
		                 if(qastate == ""){
		                    qastate = -1;
		                 }
		                 var tiNo =  $('#studyType_testItemAndNo').combobox('getValue');
			              if(tiNo == "" ){
			                 tiNo = -1;
			              }
			              $('#showAppointSDTable').val(1);
			              var select =  $('#showAppointSDTable').val();
			              if(select == 1){
			            	  var searchString = $('#searchBoxName').searchbox('getValue');
			                showappointSDTable(startTime,endTime,studystate,qastate,pastate,tiNo,searchString);    
			              }
			             
					     if(record.id == "-1"){
					       $('#pastudyItem_testItem').combobox('clear');
					    }
					 
				},
				onLoadSuccess:function(){
			         var role =  $('#role').val();
               	     if(role == "PathSDLead"){
					    $('#pastudyItem_testItem').combobox('select',-1);  
					    $('#pastudyItem_testItem').combobox('enable');
					 }else if(role == "PathSD"){
					    $('#pastudyItem_testItem').combobox('disable');
					    $('#pastudyItem_testItem').combobox('select',2);
					    $('#pastudyItem_testItem').combobox('setValue','已确认');
				     }else{
				        $('#pastudyItem_testItem').combobox('select',-1);
				     }
				    
				}
			});
		 
		 }
		 
		 //隐藏按键
		 function initroleButton(){
               	var role =  $('#role').val();
                var department =  $('#department').val();
               	if(role == "FM"){
               	   $('#onAppointSDButton').css('display','');  
               	   $('#onSubmitAppointSDButton').css('display','');  
               	   $('#onAgainEditButton').css('display','');  
               	   $('#printerButton').css('display','');  
               	   $('#studyScheduleInfoButton').css('display','');  
               	}else if(role == "SD" || department == "true" ){
               	   $('#studyScheduleNodeButton').css('display','');  
               	   $('#studyScheduleButton').css('display','');  
               	}else if(role == "QALead"){
               	   $('#onAppointQAButton').css('display','');  
               	   $('#onSubmitAppointQAButton').css('display','');  
               	   $('#onAgainEditQAButton').css('display',''); 
               	 	$('#studyScheduleInfoButton').css('display','');  
               	}else if(role == "QA"){
               	 	$('#studyScheduleInfoButton').css('display','');  
               	}else if(role == "PathSDLead"){
               	   $('#onAppointPAButton').css('display','');  
               	   $('#onSubmitAppointPAButton').css('display','');  
               	   $('#onAgainEditPAButton').css('display',''); 
               	   $('#studyScheduleInfoButton').css('display','');  
               	}else if(role == "PathSD"){
               	   $('#studyScheduleInfoButton').css('display','');  
               	}
		 }
		 //隐藏下拉选
		 function initCombobox(){
		       	var role =  $('#role').val();
		       	var department =  $('#department').val();
               	if(role == "FM"){
               	    $('#sdCombobox').css('display','');
               	  //  $('#appointSDTable').datagrid('hideColumn','qa');
               	    $('#appointSDTable').datagrid('hideColumn','qaAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','qaState');
               	   // $('#appointSDTable').datagrid('hideColumn','pathSD');
               	    $('#appointSDTable').datagrid('hideColumn','pathSDDate');
               	    $('#appointSDTable').datagrid('hideColumn','pathState');
               	}else if(role == "SD" || department == "true"){
               	//    $('#appointSDTable').datagrid('hideColumn','qa');
               	    $('#appointSDTable').datagrid('hideColumn','qaAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','qaState');
               	  //  $('#appointSDTable').datagrid('hideColumn','pathSD');
               	    $('#appointSDTable').datagrid('hideColumn','pathSDDate');
               	    $('#appointSDTable').datagrid('hideColumn','pathState');
               	}else if(role == "QALead"){
               	    $('#qaCombobox').css('display','');
               	  //  $('#appointSDTable').datagrid('hideColumn','sd');
               	    $('#appointSDTable').datagrid('hideColumn','sdAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','sdState');
               	  //  $('#appointSDTable').datagrid('hideColumn','pathSD');
               	    $('#appointSDTable').datagrid('hideColumn','pathSDDate');
               	    $('#appointSDTable').datagrid('hideColumn','pathState');
               	}else if(role == "QA"){
               	  //  $('#appointSDTable').datagrid('hideColumn','sd');
               	    $('#appointSDTable').datagrid('hideColumn','sdAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','sdState');
               	 //   $('#appointSDTable').datagrid('hideColumn','pathSD');
               	    $('#appointSDTable').datagrid('hideColumn','pathSDDate');
               	    $('#appointSDTable').datagrid('hideColumn','pathState');
               	}else if(role == "PathSDLead"){
               	    $('#paCombobox').css('display','');
               	  //  $('#appointSDTable').datagrid('hideColumn','sd');
               	    $('#appointSDTable').datagrid('hideColumn','sdAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','sdState');
               	  //  $('#appointSDTable').datagrid('hideColumn','qa');
               	    $('#appointSDTable').datagrid('hideColumn','qaAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','qaState');
               	}else if(role == "PathSD"){
               	   // $('#appointSDTable').datagrid('hideColumn','sd');
               	    $('#appointSDTable').datagrid('hideColumn','sdAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','sdState');
               	 //  $('#appointSDTable').datagrid('hideColumn','qa');
               	    $('#appointSDTable').datagrid('hideColumn','qaAppointDate');
               	    $('#appointSDTable').datagrid('hideColumn','qaState');
               	}
		 
		 }
		 
		 function chooseTheirOwn(){
		    var chooseOwn = $('#chooseOwn').val();
		    if( chooseOwn == "1"){
		       $('#chooseOwn').val('0');
		       if(!document.getElementById("checkbox").checked){
		         document.getElementById("checkbox").checked = true;
		       }
		       $('#appointSDTable').datagrid({
					rowStyler: function(index,row){
						if (row.own != "1" ){
							return 'color:#FFA500 ;';
						}
					}
				});
		    }else{
		      $('#chooseOwn').val('1');
		      if(document.getElementById("checkbox").checked){
		          document.getElementById("checkbox").checked = false;
		        }
		        $('#appointSDTable').datagrid({
					rowStyler: function(index,row){
						if (row.own == "1" ){
							return '';
						}
					}
				});
				var rows = $('#appointSDTable').datagrid('getSelected');
				if(rows && rows.own == "0"){
				   $('#appointSDTable').datagrid('unselectAll');
				}
		    }
		   
		    var startTime =  $('#mindatebox').datebox('getValue');
    	    var endTime = $('#maxdatebox').datebox('getValue');
		    var studystate= $('#studyItem_testItem').combobox('getValue');
		    var qastate= $('#qastudyItem_testItem').combobox('getValue');
		    var pastate= $('#pastudyItem_testItem').combobox('getValue');
		    var testItemAndNo= $('#studyType_testItemAndNo').combobox('getValue');
		    if(studystate == ""){
		            studystate = -1;
		    }
		    if(qastate == ""){
		            qastate = -1;
		    }
		    if(pastate == ""){
		             pastate = -1;
		    }
		     if(testItemAndNo == ""){
		             testItemAndNo = -1;
		    }
		     var searchString = $('#searchBoxName').searchbox('getValue');
		    showappointSDTable(startTime,endTime,studystate,qastate,pastate,testItemAndNo,searchString);   
		 }
		function studyAttachment()
		{
			var study = $('#appointSDTable').datagrid('getSelected');
			if(study!=null)
			{
				document.getElementById("studyAttachmentDialog2").display="block";

				$('#studyNoForAttachment').val(study.studyNo);
				$('#isFileDisForAttachment').val(study.isFileDis);
				
				$('#studyAttachmentDialog').dialog('open');
				
			}else
			{
				$.messager.alert("提示","请选择一个专题");
			}
			
		}
		
</script>
</head>
<body>
<!-- 开始时间 -->
<s:hidden id="startTime" name="startTime"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endTime" name="endTime" ></s:hidden>
<!-- 角色 -->
<s:hidden id="role" name="role" ></s:hidden>
<!-- 部门负责人-->
<s:hidden id="department" name="department" ></s:hidden>
<s:hidden id="showAppointSDTable" name="showAppointSDTable" ></s:hidden>
<s:hidden id="chooseOwn" name="chooseOwn" ></s:hidden>

<s:hidden id="isExistFileDisForAppoint" name="isExistFileDis" ></s:hidden>

<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	  <div data-options="region:'north',border:false," style="height:30px;">
 	  		<div style="height:30px;">
			       <div style="float:left; width:100%;height:19px;margin-top:5px;" >
			    		&nbsp;&nbsp;供试品类型
			    		<input id="studyType_testItemAndNo" class="easyui-combobox" style="height:19px;width:60px;"
			    		data-options="panelHeight:'auto'"/>
			    		<span id="sdCombobox" style="display:none;" >&nbsp;&nbsp;SD任命状态&nbsp;&nbsp;<input id="studyItem_testItem"  style="height:19px;width:60px;"/></span>
			    		
			    		<span id="qaCombobox" style="display:none;" >&nbsp;&nbsp;QA任命状态&nbsp;&nbsp;<input id="qastudyItem_testItem" style="height:19px;width:60px;"/></span>
			    		
			    		<span id="paCombobox" style="display:none;" >&nbsp;&nbsp;病理任命状态&nbsp;&nbsp;<input id="pastudyItem_testItem"  style="height:19px;width:60px;"/></span>
			    	    &nbsp;&nbsp; 合同日期范围
		                
			        	&nbsp;从&nbsp;<input id="mindatebox" type="text" class="easyui-datebox" style="width:90px;height:19px;" editable="false"></input>	
			      	  	&nbsp;至&nbsp;<input id="maxdatebox" type="text" class="easyui-datebox" style="width:90px;height:19px;" editable="false"></input>   
	
			      	  	&nbsp;&nbsp;<input type="checkbox" style="display:none;vertical-align: middle;" id = "checkbox" name="checkbox" value="checkbox"  onclick="chooseTheirOwn();"/>
			      	  	<a id="chooseOwnButton" style="display:none;" class="easyui-linkbutton" onclick="chooseTheirOwn();"  data-options="plain:true" >查看部门成员专题</a>
			    		&nbsp;&nbsp;
			    		
			    		<span style="position:absolute; top:5px;">
			    			<input class="easyui-searchbox" id="searchBoxName" ></input> 
			    		</span>
			    		
			    		<a id="studyAttachmentButton" class="easyui-linkbutton" onclick="studyAttachment();"
   								data-options="iconCls:'icon-fileAdd',plain:true,disabled:true" style="display:'';position:absolute;top:5px;right:5px;height:19px;">专题附件</a>
			    		
			    		
			    		
			    	</div>
			    
			    </div>
			</div>
 	<div region="center" title="" style="overflow: auto;">
 	 
		<table id="appointSDTable" ></table>
            
        <div id="toolbar">
        <!-- SD按钮 -->
   			<a id="onAppointSDButton" class="easyui-linkbutton" onclick="onAppointSDButton();"
   				data-options="iconCls:'icon-user',plain:true,disabled:true" style="display:none;">SD任命</a>
   			<a id="onSubmitAppointSDButton" class="easyui-linkbutton" plain="true" style="display:none;"
				onclick="submitAppointSDButton();" 
				data-options="iconCls:'icon-usergo',plain:true,disabled:true">提交SD任命</a>
		    <a id="onAgainEditButton" class="easyui-linkbutton" plain="true" style="display:none;"
				onclick="onAgainEditButton();" 
				data-options="iconCls:'icon-useredit',plain:true,disabled:true"  >重新任命SD</a>
		    <a id="printerButton" class="easyui-linkbutton" onclick="printer();" style="display:none;"
		    	 data-options="iconCls:'icon-printer',plain:true,disabled:true">打印任命书</a>
		 <!-- QA按钮 -->	 
		    <a id="onAppointQAButton" class="easyui-linkbutton" onclick="onAppointQAButton();"
   				data-options="iconCls:'icon-user',plain:true,disabled:true" style="display:none;" >任命QA检查员</a>
   			<a id="onSubmitAppointQAButton" class="easyui-linkbutton" plain="true" 
				onclick="submitAppointQAButton();" style="display:none;"
				data-options="iconCls:'icon-usergo',plain:true,disabled:true">提交QA检查员任命</a>
		    <a id="onAgainEditQAButton" class="easyui-linkbutton" plain="true" 
				onclick="onAgainEditQAButton();" style="display:none;"
				data-options="iconCls:'icon-useredit',plain:true,disabled:true" >重新任命QA检查员</a>
		 <!-- 病理按钮 -->
		    <a id="onAppointPAButton" class="easyui-linkbutton" onclick="onAppointPAButton();"
   				data-options="iconCls:'icon-user',plain:true,disabled:true" style="display:none;">病理专题负责人任命</a>
   			<a id="onSubmitAppointPAButton" class="easyui-linkbutton" plain="true" 
				onclick="submitAppointPAButton();" style="display:none;"
				data-options="iconCls:'icon-usergo',plain:true,disabled:true">提交病理专题负责人任命</a>
		    <a id="onAgainEditPAButton" class="easyui-linkbutton" plain="true" 
				onclick="onAgainEditPAButton();" style="display:none;"
				data-options="iconCls:'icon-useredit',plain:true,disabled:true"  >重新任命病理专题负责人</a>	   
				
		     <a id="studyScheduleNodeButton" class="easyui-linkbutton" onclick="showStudyScheduleNodeDialog();" style="display:none;"
		     	data-options="iconCls:'icon-list',plain:true,disabled:true">设置专题进度计划</a>
		     <a id="studyScheduleButton" class="easyui-linkbutton" onclick="showStudyScheduleDialog();" style="display:none;"
		    	 data-options="iconCls:'icon-list-number',plain:true,disabled:true">设置专题进度</a>
		     <a id="studyScheduleInfoButton" class="easyui-linkbutton" onclick="showStudyScheduleInfoDialog();"  style="display:none;"
		    	 data-options="iconCls:'icon-list-number',plain:true,disabled:true">专题进度信息</a>
		</div>
	</div>
 	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/selectSD.jspf"%>
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/studyScheduleNode.jspf" %>
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/calendarDialog.jspf" %>
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/studySchedule.jspf" %> 
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/studyScheduleInfo.jspf" %> 
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/selectQA.jspf"%>
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/selectPA.jspf"%>
 	<%@ include file="/WEB-INF/jsp/tblAppointSDAction/studyAttachment.jspf"%>
</div>
</body>
</html>
