<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 28;
		tableWidth  = document.body.clientWidth;
		var contractCode = $('#contractCode').val();
		 $('#tblReportRecordTable').datagrid({
			url : sybp()+'/tblReportRecordAction_loadList.action?selectid='+ $('#selectid').val()+'&contractCode='+contractCode,
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:true,//分页
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'项目编号',
				field:'studyCode',
				width:120,
				halign:'center',
				align:'left',
			},{
				title:'报告编号',
				field:'reportCode',
				width:120,
				halign:'center',
				align:'left',
			},{
				title:'报告名称',
				field:'reportName',
				width:150,
				halign:'center',
				align:'left',
			},{
				title:'报告完成日期',
				field:'finishDate',
				width:80,
				halign:'center',
				align:'left',
			},{
				title:'SD',
				field:'sd',
				width:50,
				halign:'center',
				align:'left',
			},{
				title:'提交人',
				field:'submitter',
				width:50,
				halign:'center',
				align:'left',
			},{
				title:'交付人',
				field:'deliverer',
				width:50,
				halign:'center',
				align:'left',
			},{
				title:'交付日期',
				field:'deliveryDate',
				width:74,
				halign:'center',
				align:'left',
			},{
				title:'接收人',
				field:'receiver',
				width:50,
				halign:'center',
				align:'left',
			},{
				title:'接收日期',
				field:'receiveDate',
				width:74,
				halign:'center',
				align:'left',
			},{
				title:'交付方式',
				field:'deliveryMode',
				width:100,
				halign:'center',
				align:'left',
			},{
				title:'交付相关信息',
				field:'deliveryinfo',
				width:130,
				halign:'center',
				align:'left',
			},{
				title:'备注',
				field:'remark',
				width:170,
				halign:'center',
				align:'left',
			}]],
			onSelect:function(rowIndex, rowData){
			    var falg = $('#addContract').val();
    			if(falg == 0){
    			    $('#addReportRecordButton').linkbutton('disable'); 
    		    	$('#editReportRecordButton').linkbutton('disable'); 
				    $('#delReportRecordButton').linkbutton('disable');
    			}else{
				    $('#editReportRecordButton').linkbutton('enable'); 
				    $('#delReportRecordButton').linkbutton('enable');
				}
			},
			onLoadSuccess:function(data){
			    var falg = $('#addContract').val();
    			if(falg == 0){
    			    $('#addReportRecordButton').linkbutton('disable'); 
    		    	$('#editReportRecordButton').linkbutton('disable'); 
				    $('#delReportRecordButton').linkbutton('disable');
    			}else{
	    			if(data && data.id){
				             $('#tblReportRecordTable').datagrid('selectRecord',data.id);
				             $('#selectid').val('');
				    }
    			}
			    
			},
			toolbar:'#toolbar',
	   	}); //end datagrid
		
		 var pager = $('#tblReportRecordTable').datagrid('getPager');    // get the pager of datagrid
		  pager.pagination({
		        showPageList:false,
		        showRefresh:false,
		        loading:true,
		        displayMsg:	' 共 &nbsp;&nbsp;{total}&nbsp;&nbsp;条记录',
		        beforePageText:'',
		        afterPageText:''
		    });
		
		//初始化SD
		initSDManagerCombobox();
		
		//加载课题
		loadstudyCodeCombobox();
		//加载接收方式
		initdelivery();
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
    
   
    
	 /**显示Dialog*/
	function showReportRecordDialog(clickName,addOrEdit,title){
		/*签名Dialog*/
		document.getElementById("reportRecordDialog2").style.display="block";
		$('#reportRecordAddOrEdit').val(addOrEdit);
		document.getElementById("reportRecordDialogAddEdit_event").href="javascript:"+clickName+"();";
		$('#reportRecordDialog').dialog('setTitle',title);
		$('#reportRecordDialog').dialog('open'); 
		document.getElementById("reportRecordDialogAddEdit_event").href="javascript:"+clickName+"();";
		//清空数据
		//attachmentFullEditData();
		 document.reportRecordAddEditForm.reset();
		 	var contractCode = $('#contractCode').val();
		 	$('#thecontractCode').val(contractCode);
		 	$('#reportCodecheckName').val("");

		 //加载接收方式
		initdelivery();
		 	 	
	}
	/**关闭Dialog*/
    function onDialogContractclose(){
      $('#reportRecordDialog').dialog('close'); 
    }
	/** 添加报告登记 */
	function onaddReportRecordButton(){
	 $('#studyCode').combobox('enable');
	  showReportRecordDialog('afterAdd','add','添加报告登记')
	}
	/**执行完添加*/
	function afterAdd(){
	    // $('#tblReportRecordTable').datagrid('reload');
	    var contractCode = $('#contractCode').val();
		   $('#tblReportRecordTable').datagrid({
		   url : sybp()+'/tblReportRecordAction_loadList.action?selectid='+ $('#selectid').val()+'&contractCode='+contractCode});
	     parent.parent.showMessager(1,'报告登记成功',true,5000);
	}
	/**保存报告登记*/
	function onDialogContractSaveButton(){
	   if( $('#reportRecordDialog').form('validate') ){
	     $('#saveDialogButton').linkbutton('disable'); 
	        if($('#reportRecordAddOrEdit').val() == 'add'){
	            $.ajax({
					url:sybp()+'/tblReportRecordAction_addSave.action',
					type:'post',
					data:$('#reportRecordAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#reportRecordDialog').dialog('close'); 
							 $('#selectid').val(r.id);
							var reportRecordDialogAddEdit_event=document.getElementById("reportRecordDialogAddEdit_event");
							reportRecordDialogAddEdit_event.click();
						}else{
						    $.messager.alert('提示',r.msg);
						}
					}
			});
	        }else{
	         $.ajax({
					url:sybp()+'/tblReportRecordAction_editSave.action',
					type:'post',
					data:$('#reportRecordAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#reportRecordDialog').dialog('close'); 
							var theid =  $('#theid').val();
							 $('#selectid').val(theid);
							var reportRecordDialogAddEdit_event=document.getElementById("reportRecordDialogAddEdit_event");
							reportRecordDialogAddEdit_event.click();
						}else{
						    $.messager.alert('提示',r.msg);
						}
					}
			});
	        
	        }
	    }
	 }
	 /**加载课题编号下拉选*/
	 function loadstudyCodeCombobox(){
	   var contractCode = $('#contractCode').val();
	    $('#studyCode').combobox({    
		    url:sybp()+'/tblReportRecordAction_loadstudyCode.action?contractCode='+contractCode, 
		    valueField:'id',    
		    textField:'text',
		    required:true,
		    editable:false,
		    panelHeight:80,
			 onSelect:function(record){
			     if(record.id != ''){
			        selectSubmitter(record.id);
			     }
			 }   
		}); 
	 }
	 /**根据课题编号选择SD*/
	 function selectSubmitter(studyCode){
	     $.ajax({
					url:sybp()+'/tblReportRecordAction_selectSubmitter.action?studyCode='+studyCode,
					type:'post',
					dataType:'json',
					required:true,
					editable:false,
					success:function(r){
						if(r && r.success){
						  $('#submitter').combobox('select',r.sd);
						}
					}
			    });
	 }
	 
	 /**加载sd*/
	 //初始化 SD推荐人  下拉框
		function initSDManagerCombobox(){
			//loadStudyTypePartList
			$('#submitter').combobox({
				url:sybp()+'/tblStudyItemAction_loadSDList.action',
				valueField:'id',
				textField:'text',
				required:true,
				editable:false,
				onSelect:function(record){
				}
			});
		}
		
      /**加载接受方式下拉选*/
	  function	initdelivery(){
	        $('#deliveryMode').combobox({
				url:sybp()+'/tblReportRecordAction_delivery.action',
				valueField:'id',
				textField:'text',
				required:true,
				onSelect:function(record){
				}
			});
	  }
	  
	  /**删除报告*/
	  function onDelReportRecordButton(){
	     	$.messager.confirm('确认对话框', '确定删除此报告？', function(r){
     				if (r){
     					afterDelReportRecordButton();
     				}
     			});
	  }
	  
	  //执行完删除报告
	  function afterDelReportRecordButton(){
	     var row = $('#tblReportRecordTable').datagrid('getSelected');
	     if(row){
	       $.ajax({
					url:sybp()+'/tblReportRecordAction_onDelReportRecord.action?id='+row.id,
					type:'post',
					dataType:'json',
					required:true,
					editable:false,
					success:function(r){
						if(r && r.success){
							 //$('#tblReportRecordTable').datagrid('reload');
							 var rows = $('#tblReportRecordTable').datagrid('getRows');
							 for(var i = 0;i<rows.length;i++){
					               if(rows[i].id == row.id){
					                $('#tblReportRecordTable').datagrid('deleteRow',i);
					                 break;
					               }
				             }
							  $('#editReportRecordButton').linkbutton('disable'); 
				              $('#delReportRecordButton').linkbutton('disable');
		                      parent.parent.showMessager(1,'报告删除成功',true,5000);  
						}else{
						    $.messager.alert('提示',r.msg);
						}
					}
			    });
	     }
	  }
	  /** 编辑前填充 */
	  function fullEditReportRecord(){
	     var row = $('#tblReportRecordTable').datagrid('getSelected');
	     if(row){
	       $.ajax({
					url:sybp()+'/tblReportRecordAction_onEdit.action?id='+row.id,
					type:'post',
					dataType:'json',
					required:true,
					editable:false,
					success:function(r){
						if(r){
						   $('#theid').val(r.id);
						   $('#thecontractCode').val(r.contractCode);
						   //$('#studyCode').val(r.studyCode);
						   $('#studyCode').combobox('select',r.studyCode);
						   $('#studyCode').combobox('disable');
						   $('#reportCodecheckName').val(r.reportCode);
						   $('#reportCode').val(r.reportCode);
						   $('#reportName').val(r.reportName);
						   //$('#finishDate').val(r.finishDate);
						   $('#finishDate').datebox('setValue',r.finishDate);
						   $('#submitter').combobox('select',r.submitter);
						   $('#submitDate').datebox('setValue',r.submitDate);
						   $('#deliverer').val(r.deliverer);
						  // $('#deliveryDate').val(r.deliveryDate);
						   $('#deliveryDate').datebox('setValue',r.deliveryDate);
						   $('#receiver').val(r.receiver);
						   //$('#receiveDate').val(r.receiveDate);
						   $('#receiveDate').datebox('setValue',r.receiveDate);
						    $('#deliveryMode').combobox('select',r.deliveryMode);
						   //$('#deliveryMode').val(r.deliveryMode);
						   $('#deliveryinfo').val(r.deliveryinfo);
						   $('#remark').val(r.remark);
						   
						}
					}
			    });
	     }
	  }
	  
	  /**编辑*/
	  function editReportRecordButton(){
	      showReportRecordDialog('afterEdit','edit','编辑报告登记')
	      fullEditReportRecord();
	  }
	  
	  /**编辑完成*/
	  function afterEdit(){
	      //$('#tblReportRecordTable').datagrid('reload');
	       var contractCode = $('#contractCode').val();
		   $('#tblReportRecordTable').datagrid({
		   url : sybp()+'/tblReportRecordAction_loadList.action?selectid='+ $('#selectid').val()+'&contractCode='+contractCode});
	       parent.parent.showMessager(1,'报告编辑完成',true,5000);
	  }
		
</script>
	</head>
	<body>
	<!-- 刷新过后选中的id -->
		<s:hidden id="selectid" name="selectid"></s:hidden>
		<s:hidden id="contractCode" name="contractCode"></s:hidden>
		<!-- 是否只读 -->
        <s:hidden id="addContract" name="addContract"></s:hidden>
		<div id="layoutMainDiv" class="easyui-layout"
			style="width: 100%; height: 100%;left:-1px;top:-1px; display: none;overflow: hidden;">
			<div region="center" title="" style="overflow: hidden;">
				<div style="overflow: hidden;">
					<table id="tblReportRecordTable"></table>
				</div>
				<div id="toolbar">
					<a id="addReportRecordButton" class="easyui-linkbutton"
						plain="true" onclick="onaddReportRecordButton()" data-options="iconCls:'icon-tableadd'">添加报告</a>

					<a id="editReportRecordButton" class="easyui-linkbutton"
						plain="true" onclick="editReportRecordButton()" data-options="iconCls:'icon-tableedit2',disabled:true">编辑报告</a>
					<a id="delReportRecordButton" class="easyui-linkbutton"
						plain="true" onclick="onDelReportRecordButton()" data-options="iconCls:'icon-tabledelete',disabled:true">删除报告</a>
				</div>
			</div>
	        <%@ include file="/WEB-INF/jsp/tblReportRecordAction/reportRecordDialog.jspf"%>
		</div>
		
	</body>
</html>
