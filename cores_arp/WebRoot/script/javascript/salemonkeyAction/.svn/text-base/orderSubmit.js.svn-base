/**显示Dialog*/
function showOrderSubmitDialog(clickName,addOrEdit,title){
	initMonkeyTypeCombobox();
	initAddressCombobox();
	initSaleTypeCombobox();
	initBossCombobox();
	initApproveserial1();
	/* 显示Dialog */
	document.getElementById("orderSubmitDialog2").style.display="block";
	if(addOrEdit == "submit"){
	  var row= $('#selectmonkeyTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/saleAction_toSubmitOrder.action?',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				fullEditInformation(r);
  	    			}
  	    		}
  	    	});

  		}
	
	///$('#submit_title').attr("disabled", "disabled");
			
  		$('#addOrEdit').val(addOrEdit);
	$('#orderSubmitDialog').dialog('setTitle',title);
	$('#orderSubmitDialog').dialog('open'); 
	document.getElementById("orderSubmit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onSubmitDialogSaveButton(){
		if( $('#orderSubmitForm').form('validate') ){
			$('#saveSubmitDialogButton').linkbutton('disable');
			
				$.ajax({
					url:sybp()+'/saleAction_saveSubmitOrder.action',
					type:'post',
					data:$('#orderSubmitForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveSubmitDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#orderSubmitDialog').dialog('close'); 
						$('#sid').val(r.id);
						var orderSubmit_event=document.getElementById("orderSubmit_event");
						orderSubmit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#orderSubmitDialog').dialog('close'); 
							var orderSubmit_event=document.getElementById("orderSubmit_event");
							orderSubmit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}
function  fullEditInformation(r){
	   if(r == ""){
		   $('#submit_id').val('');
		   $('#submit_title').val('');
		   $('#submit_monkeylist').val('');
		   $('#submit_saledate').datebox('setValue', '');
		   $('#submit_boss').combobox('select','');
		   $('#submit_saletype').combobox('select','');
	  	   $('#submit_remark').val('');
	  	   $('#submit_saleaddress_id').combobox('select');
	  	   $('#submit_monkeytype').combobox('select');
	  	   $('#submit_approveserial').combobox('select','');
	  	   $('#submit_trance').combobox('select','');
	   }else{
		   $('#submit_id').val(r.id);
		   $('#submit_monkeylist').val(r.monkeylist);
		   $('#submit_title').val(r.title);
		   $('#submit_saledate').datebox('setValue', r.saledate);
	  	   $('#submit_remark').val(r.remark);
	  	   $('#submit_boss').combobox('select',r.boss);
	  	   $('#submit_saletype').combobox('select',r.saletype);
	  	   $('#submit_saleaddress_id').combobox('select',r.saleaddress_id);
	  	   $('#submit_monkeytype').combobox('select',r.monkeytype);
	  	   $('#submit_cites').val(r.cites);
	  	   $('#submit_carriagenumber').val(r.carriagenumber);
	  	   $('#submit_tiaojian').val(r.tiaojian);
	  	   $('#submit_remark').val(r.remark);
	  	   $('#submit_approveserial').combobox('select',r.approveserial);
	  	   $('#submit_trance').combobox('select',r.trance);
	   }
	   
  }

//初始化主管下拉选
 function initBossCombobox(){
 	$('#submit_boss').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
//初始化销售类型下拉选
 function initSaleTypeCombobox(){
 	$('#submit_saletype').combobox({    
 		url : sybp()+'/saleAction_loadListSaleType.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
//初始化销售地下拉选
 function initAddressCombobox(){
 	$('#submit_saleaddress_id').combobox({    
 		url : sybp()+'/saleAction_loadListAddress.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
//初始化猴子种类下拉选
function initMonkeyTypeCombobox(){
	$('#submit_monkeytype').combobox({    
		url : sybp()+'/individualAction_loadListAnimaltype.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
function initApproveserial1(){
	$('#submit_approveserial').combobox({    
		url : sybp()+'/salemonkeyAction_loadListApprovalMap.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(recorder){
			if(recorder.id==-1){
				$('#submit_approveserial').combobox('select',"");
			}
		}
		 
	});
	$('#submit_trance').combobox({    
		url : sybp()+'/salemonkeyAction_loadListApprovalMap.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(recorder){
			if(recorder.id==-1){
				$('#submit_trance').combobox('select',"");
			}
		}
	});
	
}
