/**显示Dialog*/
function showOrderAddEditDialog(clickName,addOrEdit,title){
	initMonkeyTypeCombobox1();
	initAddressCombobox1();
	initSaleTypeCombobox1();
	initBossCombobox1();
	initApproveserial();
	/* 显示Dialog */
	document.getElementById("orderAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation1("");
	}else if(addOrEdit == "edit"){
	  var row= $('#selectmonkeyTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/saleAction_toEditOrder.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				fullEditInformation1(r);
  	    			}
  	    		}
  	    	});

  		}
  
  		$('#addOrEdit').val(addOrEdit);
	$('#orderAddEditDialog').dialog('setTitle',title);
	$('#orderAddEditDialog').dialog('open'); 
	document.getElementById("orderAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#orderAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/saleAction_addOrder.action',
					type:'post',
					data:$('#orderAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#orderAddEditDialog').dialog('close'); 
						$('#sid').val(r.id);
						var orderAddEdit_event=document.getElementById("orderAddEdit_event");
						orderAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#orderAddEditDialog').dialog('close'); 
							var orderAddEdit_event=document.getElementById("orderAddEdit_event");
							orderAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/saleAction_editSaveOrder.action',
					type:'post',
					data:$('#orderAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#orderAddEditDialog').dialog('close'); 
						$('#sid').val(r.id);
						var orderAddEdit_event=document.getElementById("orderAddEdit_event");
						orderAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#orderAddEditDialog').dialog('close'); 
							var orderAddEdit_event=document.getElementById("orderAddEdit_event");
							orderAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}
function  fullEditInformation1(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#title').val('');
		   $('#saledate').datebox('setValue', '');
		   $('#boss').combobox('select','');
		   $('#saletype').combobox('select','');
	  	   $('#remark').val('');
	  	   $('#saleaddress_id').combobox('select','');
	  	   $('#monkeytype').combobox('select','');
	  	   $('#cites').val('');
	  	   $('#carriagenumber').val('');
	  	   $('#tiaojian').val('');
	  	   $('#approveserial').combobox('select','');
	  	   $('#trance').combobox('select','');
	   }else{
		   $('#id').val(r.id);
		   $('#oldTitle').val(r.title);
		   $('#title').val(r.title);
		   $('#saledate').datebox('setValue', r.saledate);
	  	   $('#remark').val(r.remark);
	  	   $('#boss').combobox('select',r.boss);
	  	   $('#saletype').combobox('select',r.saletype);
	  	   $('#saleaddress_id').combobox('select',r.saleaddress_id);
	  	   $('#monkeytype').combobox('select',r.monkeytype);
	  	   $('#cites').val(r.cites);
	  	   $('#carriagenumber').val(r.carriagenumber);
	  	   $('#tiaojian').val(r.tiaojian);
	  	   $('#approveserial').combobox('select',r.approveserial);
	  	   $('#trance').combobox('select',r.trance);
	   }
	   
  }

//初始化主管下拉选
 function initBossCombobox1(){
 	$('#boss').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#boss').combobox('select',"");
 			}
 		}
 		 
 	});
 }
//初始化销售类型下拉选
 function initSaleTypeCombobox1(){
 	$('#saletype').combobox({    
 		url : sybp()+'/saleAction_loadListSaleType.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
//初始化销售地下拉选
 function initAddressCombobox1(){
 	$('#saleaddress_id').combobox({    
 		url : sybp()+'/saleAction_loadListAddress.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
//初始化猴子种类下拉选
function initMonkeyTypeCombobox1(){
	$('#monkeytype').combobox({    
		url : sybp()+'/individualAction_loadListAnimaltype.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(recorder){
			if(recorder.id==-1){
				$('#monkeytype').combobox('select',"");
			}
		}
		 
	});
}
function initApproveserial(){
	$('#approveserial').combobox({    
		url : sybp()+'/salemonkeyAction_loadListApprovalMap.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(recorder){
			if(recorder.id==-1){
				$('#approveserial').combobox('select',"");
			}
		}
		 
	});
	$('#trance').combobox({    
		url : sybp()+'/salemonkeyAction_loadListApprovalMap.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(recorder){
			if(recorder.id==-1){
				$('#trance').combobox('select',"");
			}
		}
		 
	});
	
}
