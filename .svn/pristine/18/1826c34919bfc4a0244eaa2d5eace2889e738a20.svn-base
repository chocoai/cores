<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	var tblStudyResAction;
	var  startDate;
	var  endDate;
	var selectRes;
	$(function(){
	    selectRes = $('#selectRes').val();
	    if(selectRes){
	       $('#selectResButton').css('display','');
	    }
		tableHeight = document.body.clientHeight - 30;
		tableWidth  = document.body.clientWidth;
	   	startDate = $('#startime').val();
	   	endDate = $('#endtime').val();
	    //加载datagrid数据
		studyResActionTable(startDate,endDate);
	    $('#startDate').datebox('setValue',startDate);
	    $('#endDate').datebox('setValue',endDate);
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
		//审核预览
		previewTable=$('#previewTable').datagrid({
			height:420,
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
			
			   	
	});//匿名函数结束
	
	//分配资源按钮事件
	function onaddStudyResButton(){
		var row =$('#tblStudyResActionTable').datagrid('getSelected');
		if(row == ''){
			parent.showMessager(2,'请先选择数据',true,5000);
		}else {
			window.location.href=sybp()+'/tblStudyResAction_setUI.action?studyNo='+row.studyNo+"&presID="+row.pResID;
		}
		
	}
	//编辑
	function oneditStudyResButton(){
		var getselections =$('#tblStudyResActionTable').datagrid('getSelections');
		var rows = getselections.length;
		if(rows == ""){
			parent.showMessager(2,'请选择要分配资源的行',true,5000);
		}else if(rows > 1){
			$.messager.alert('提示','请选择单条记录分配资源!','info');
		}else{
			var getselection =$('#tblStudyResActionTable').datagrid('getSelected');
			window.location.href=sybp()+'/tblStudyResAction_editUI.action?studyNo='+getselection.studyNo+"&id="+getselection.id+"&resName="+getselection.resName;
		}
	}

	function onSignStudyButton(){
		qm_showQianmingDialog('afterSignStudy()');
	}

	function afterSignStudy(){
		  var rows = $('#tblStudyResActionTable').datagrid('getSelections');
	      var ary = new Array();
	      for(var j=0;j<rows.length;j++){
			  var ids  =  $('#tblStudyResActionTable').datagrid('getSelections')[j].id;
			  ary = ary.concat(ids);
		  }
	      var getSelections = ary.join(",");
		$.ajax({
    		url:sybp()+'/tblStudyResAction_signStudy.action?esType=415',
    		type:'post',
    		cache:false,
    		data: {
				allStudyRes:getSelections,
			},
    		dataType:'json',
    		success:function(r){
				window.location.href=sybp()+'/tblStudyResAction_list.action';
				 parent.parent.showMessager(1,'签字成功',true,5000);
    		}
    	});
	}
	//多选框事件
    function selectAllStudy(){
       var isSelectAllStudy=$('#isSelectAllStudy').val();
       if(isSelectAllStudy == '1' ){
            $('#isSelectAllStudy').val('0');
            document.getElementById("selectAllStudy").checked=true;
       }else{
           	$('#isSelectAllStudy').val('1');
            document.getElementById("selectAllStudy").checked=false;
       }
       $('#startDate').datebox('setValue',startDate);
       $('#endDate').datebox('setValue',endDate);
       $('#tblStudyResActionTable').datagrid({
			url : sybp()+'/tblStudyResAction_loadList.action?startime='+startDate+'&endtime='+endDate+'&isSelectAllStudy='+$('#isSelectAllStudy').val()
       });
      // if(!$('#studyNo').val()){
	      // 	$('#addStudyResButton').linkbutton('disable');
	  	  //$('#toExamineButton').linkbutton('disable');
      // }
    }	
    
    function ondelStudyResButton(){
         var rows = $('#tblStudyResActionTable').datagrid('getSelections');
	      var ary = new Array();
	      for(var j=0;j<rows.length;j++){
			  var ids  =  $('#tblStudyResActionTable').datagrid('getSelections')[j].id;
			  ary = ary.concat(ids);
		  }
	      var getSelections = ary.join(",");
      	$.ajax({
    		url:sybp()+'/tblStudyResAction_delect.action',
    		type:'post',
    		cache:false,
    		data: {
				allStudyRes:getSelections,
			},
    		dataType:'json',
    		success:function(r){
				//window.location.href=sybp()+'/tblStudyResAction_list.action';
				$('#tblStudyResActionTable').datagrid('reload'); 
				parent.showMessager(1,'删除成功',true,5000);
    		}
    	});
    }
    //查询
    function saveSelectTime(){
        var currentstartDate = $('#startDate').datebox('getValue');
	    var currentendDate = $('#endDate').datebox('getValue');
	    if(dateCompare(currentstartDate,currentendDate)){
       	 	studyResActionTable(currentstartDate,currentendDate);
       	 	startDate = currentstartDate;
       	 	endDate= currentendDate;
	       	 $('#addStudyResButton').linkbutton('disable');
	    	 $('#toExamineButton').linkbutton('disable');
       	}else{
           	
         	$.messager.alert('警告','结束日期不能大于开始日期！');
         	$('#startDate').datebox('setValue',startDate);
         	$('#endDate').datebox('setValue',endDate);
       	}
    }
    //加载数据（根据开始结束日期）
    function studyResActionTable(startDate,endDate){
        tblStudyResAction=$('#tblStudyResActionTable').datagrid({
			url : sybp()+'/tblStudyResAction_loadList.action?startime='+startDate+'&endtime='+endDate+'&isSelectAllStudy='+$('#isSelectAllStudy').val(),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			sortName:'studyNo',
			columns :[[{
				title:'专题编号',
				field:'studyNo',
				width:150,
				halign:'center',
				align:'center'
			},{
				title:'房间',
				field:'resName',
				width:250,
				halign:'center',
				align:'center'
			},{
				title:'状态',
				field:'state',
				width:150,
				halign:'center',
				align:'center',
				//formatter: function(value,row,index){
				//	if ( value == 0){
				//		return "房间未分配";
				//	}else if(value == 1){
	            //         return "房间已分配";
				//	}else if(value == 2){
	            //         return "房间已确认签字";
				//	}else if(value == 3){
	            //         return "日程已审核";
				//	}
				//},
				hidden:true
			},{
				title:'日程审核人',
				field:'auditId',
				width:150,
				halign:'center',
				align:'center'
			},{
			    title:'区域',
				field:'pResName',
				width:80,
				halign:'center',
				align:'center' 
			},{
			    title:'pResID',
				field:'pResID',
				width:80,
				halign:'center',
				align:'center',
				hidden:true
			}
			]],
			//工具栏
			toolbar:'#toolbar',
			onSelect :function(rowIndex, rowData){
			     if(rowData.pResName == ""){
			        //未分配区域
			        $('#addStudyResButton').linkbutton('disable');
			        $('#selectResButton').linkbutton('enable');
			     }else{
			        //判断已分配区域是否审核签字
			        $.ajax({
						url : sybp()+'/tblStudyInfoAction_scheduleHaveReviewSignID.action',
						type: 'post',
						dataType:'json',
						data: {
							studyNo:rowData.studyNo
						},
						success:function(r){
							if(r && r.success){
							  //alert("已签字审核");
							  $('#selectResButton').linkbutton('disable');
							  $('#addStudyResButton').linkbutton('enable');
							}else{
							  //alert("未签字审核");
							  $('#selectResButton').linkbutton('enable');
							  $('#addStudyResButton').linkbutton('disable');
							}
						}
		            });
			     }
		    	 
		    	 $('#toExamineButton').linkbutton('enable');
		    	 
		    	 $('#studyNo').val(rowData.studyNo);
		    },
		    onLoadSuccess:function(data){
			    var studyNo = $('#studyNo').val();
			    var rows = data.rows;
			    var rowIndex = -1;
			    if(studyNo && rows.length){
				    for(var i = 0;i <rows.length; i++){
					    if(studyNo == rows[i].studyNo){
						    rowIndex = $('#tblStudyResActionTable').datagrid('getRowIndex',rows[i]);
					    	$('#tblStudyResActionTable').datagrid('selectRow',rowIndex);
						}
					}
				}
				if(rowIndex > -1){
				 	$('#addStudyResButton').linkbutton('enable');
				  	$('#toExamineButton').linkbutton('enable');
				}else{
				 	$('#addStudyResButton').linkbutton('disable');
				  	$('#toExamineButton').linkbutton('disable');
				}
			}
	    	
	   	}); //end datagrid
    }
    
    //日程及审核按钮事件
    function onToExamineButton(){
        listDisplay();
        $('#isValidationPara').val("0") ;
        document.getElementById("checkbox1").checked=false;
         var row =$('#tblStudyResActionTable').datagrid('getSelected');
         //判断已分配区域是否审核签字
	        $.ajax({
				url : sybp()+'/tblStudyInfoAction_scheduleHaveReviewSignID.action',
				type: 'post',
				dataType:'json',
				data: {
					studyNo:row.studyNo
				},
				success:function(r){
					if(r && r.success){
					  //alert("已签字审核");
					  $('#saveDialogButtontoExamin').linkbutton('disable');
					}else{
					  //alert("未签字审核");
					   $('#saveDialogButtontoExamin').linkbutton('enable');
					}
				}
	           });
        $('#toExaminScheduleDialog').dialog('open');
        $('#toExaminScheduleDialog2').dialog('open');
    }
    //签字审核按钮事件
    function signatureVerification(){
      $('#fhqianming_message').html('');
      fuheqm_showQianmingDialog('afterSignatureVerification');
    }
    //真正的签字审核
    function afterSignatureVerification(){
         var signuser = $('#thisUserName').val();
          var row =$('#tblStudyResActionTable').datagrid('getSelected');
          $.ajax({
				url : sybp()+'/tblStudyResAction_signatureVerification.action',
				type: 'post',
				dataType:'json',
				data: {
					studyNo:row.studyNo,
					theAuditId:$('#thisUserName').val()
				},
				success:function(r){
					if(r && r.success){
                        var  startDate = $('#startDate').datebox('getValue');
	                    var  endDate = $('#endDate').datebox('getValue');
                        parent.parent.showMessager(1,'审核签字成功',true,5000);
                        window.location.href=sybp()+'/tblStudyResAction_list.action?studyNo='+row.studyNo+'&isSelectAllStudy=1';
                		//studyResActionTable(startDate,endDate);
                		$('#toExaminScheduleDialog').dialog('close');
                        //$('#toExaminScheduleDialog2').dialog('close');
					}else{
					      // $.messager.alert('提示','此用户无审核权限!','info');
					        fuheqm_showQianmingDialog('afterSignatureVerification');
					        $('#fhqianming_message').html('此用户无审核权限!');
					}
				}
         });
        
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
   		 $('#isValidationPara').val("1");
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
    	  var row =$('#tblStudyResActionTable').datagrid('getSelected');
    	 $.ajax({
				url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns.action',
				type: 'post',
				data: {
					studyNoPara:row.studyNo,
					isValidationPara:$('#isValidationPara').val(),
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						previewTable.datagrid({
							columns:r.columns
						});
						previewTable.datagrid('loadData',r.rows);
					}
				}
         });
    }

    function tableDisplay(){
      $('#disPlaytype').val(2);
       var row =$('#tblStudyResActionTable').datagrid('getSelected');
       var currentStudyNo = '';
       if(row){
    	   currentStudyNo = row.studyNo;
       }
   	   $.ajax({
				url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns2.action',
				type: 'post',
				data: {
					studyNoPara:currentStudyNo,
					isValidationPara:$('#isValidationPara').val(),
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						previewTable.datagrid({
							columns:r.columns
						});
						previewTable.datagrid('loadData',r.rows);
					}else{

				    }
				}
     });
   }
    function selectResButton(){
    loadAnimalHouseTable();
    $('#selectStudyResDialog').dialog('open');
	$('#selectStudyResDialog2').dialog('open');
}
    //加载试验区域
function loadAnimalHouseTable(){
          $('#AnimalHouseTable').combotree({
				url:sybp()+'/tblStudyInfoAction_loadRes.action',
				valueField:'id',
				textField:'text',
		        width:150,
		        height:19,
				onSelect: function(node){ 
				   var tree = $(this).tree;  
                  //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
                   var isLeaf = tree('isLeaf', node.target);  
			       if (!isLeaf) {  
			            //清除选中  
			           $('#AnimalHouseTable').combotree('clear');  
			           $('#AnimalHouseTable').combotree('onShowPanel'); 
			            
			       }  
 				},
 				onLoadSuccess:function(){
 				   
 				}
			});
}
    
    //保存所选择区域
function saveStudyRes(){
  //alert("保存");
   var row =$('#tblStudyResActionTable').datagrid('getSelected');
   var studyNo = row.studyNo ;
   var res =  $('#AnimalHouseTable').combotree("getValue");
   $.ajax({
  		url : sybp()+'/tblStudyInfoAction_saveRes.action',
			type: 'post',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			dataType:'json',
			data:{
			   studyNo:studyNo,
			   resID:res
			},
			success:function(r){
				if(r.success){
					 $('#selectStudyResDialog').dialog('close');
					 $('#AnimalHouseTable').combotree('clear');  
					 $('#tblStudyResActionTable').datagrid('reload');
					  parent.parent.showMessager(1,'日程区域安置成功',true,5000);
			    }else{
				}
			}
    });
  
}
    
</script>
</head>
<body>
<s:hidden id="isSelectAllStudy" name="isSelectAllStudy"></s:hidden>
<s:hidden id="studyNo" name="studyNo"></s:hidden>
<s:hidden id="startime" name="startime"></s:hidden>
<s:hidden id="endtime" name="endtime"></s:hidden>
<s:hidden id="selectRes" name="selectRes"></s:hidden>
<div id="layoutMainDiv" class="easyui-tabs" fit="true" border="true" style="display:none">
	<div title="专题房间分配"  border="false" style="overflow: hidden;">
		<div>
			<table id="tblStudyResActionTable" ></table>
		</div>
		<div id="toolbar">
			<a style="margin-left:13px;">专题&nbsp;&nbsp;开始日期&nbsp;</a><input  style="width:90px;" id="startDate" type="text" class="easyui-datebox"  data-options="editable:false"  />
			<a style="margin-left:5px;">结束日期&nbsp;</a><input  style="width:90px;" id="endDate" type="text"   class="easyui-datebox" data-options="editable:false" />
			<a id="saveDialogButton" class="easyui-linkbutton" onclick="saveSelectTime();" data-options="iconCls:'icon-search',plain:true">查询</a>
			<input id="selectAllStudy"  type='checkbox' ${isSelectAllStudy == 0 ?'checked':''}  style="width:30px;vertical-align:middle" onclick="selectAllStudy();"/>
			<a href="javascript:selectAllStudy();" style="margin-left:-10px;color:black;">仅显示未审核数据</a>
			<a id="selectResButton" class="easyui-linkbutton" style="display:none" onclick="selectResButton();" data-options="iconCls:'icon-house',disabled:true,plain:true">试验安置区域</a>
			<a id="addStudyResButton"  class="easyui-linkbutton" onclick="onaddStudyResButton();" data-options="iconCls:'icon-door',disabled:true,plain:true">分配房间</a>
			<a id="toExamineButton" class="easyui-linkbutton" onclick="onToExamineButton();" data-options="iconCls:'icon-datesign',disabled:true,plain:true">日程预览及审核</a>
		</div>
		 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		 <%@ include file="/WEB-INF/jsp/tblStudyResAction/toExamineSchedule.jspf"%>
		 <%@ include file="/WEB-INF/jsp/public/fuheqianming.jspf"%>
		 <%@ include file="/WEB-INF/jsp/tblStudyResAction/selectRes.jspf"%>
	</div>
</div>
</body>
</html>