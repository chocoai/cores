/**显示Dialog*/
function showPaymentRecordAddEditDialog(clickName,addOrEdit,title){
	/* 显示Dialog */
	document.getElementById("paymentRecordAddEditDialog2").style.display="block";
	loadPriceUnitFromContact();
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#paymentRecordTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/tblPaymentRecordAction_toEdit.action',
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
  
  		$('#addOrEdit').val(addOrEdit);
	$('#paymentRecordAddEditDialog').dialog('setTitle',title);
	$('#paymentRecordAddEditDialog').dialog('open'); 
	document.getElementById("paymentRecordAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#paymentRecordAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({         
					url:sybp()+'/tblPaymentRecordAction_add.action',
					type:'post',
					data:$('#paymentRecordAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					   $('#saveDialogButton').linkbutton('enable');
					 if(r && r.success){
						$('#paymentRecordAddEditDialog').dialog('close');
//						alert(r.id);
						$('#prId').val(r.id);
						var paymentRecordAddEdit_event=document.getElementById("paymentRecordAddEdit_event");
						paymentRecordAddEdit_event.click();
						//afterAddDialog0();
					}else{
						//$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#paymentRecordAddEditDialog').dialog('close'); 
							var customerAddEdit_event=document.getElementById("paymentRecordAddEdit_event");
							paymentRecordAddEdit_event.click();
						}else{
							if(r.msg && r.msg != ""){
								$.messager.alert('提示',r.msg);
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
					
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/tblPaymentRecordAction_editSave.action',
					type:'post',
					data:$('#paymentRecordAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#paymentRecordAddEditDialog').dialog('close');
						$('#prId').val(r.id);
						var customerAddEdit_event=document.getElementById("paymentRecordAddEdit_event");
						paymentRecordAddEdit_event.click();
					}else{
						//$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#paymentRecordAddEditDialog').dialog('close'); 
							var customerAddEdit_event=document.getElementById("paymentRecordAddEdit_event");
							paymentRecordAddEdit_event.click();
						}else{
							if(r.msg && r.msg != ""){
								$.messager.alert('提示',r.msg);
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
					}
				 }
				});
			}
			
		}
}

//编辑客户时填充选中的客户信息到编辑界面
function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#contractCode').val($('#currentContractCode').val());
		   $('#paymentDate').datebox('setValue','');
		   $('#amount').val('');
		   $('#priceUnit').val('');
		   $('#receiptFlag').val('setValue',0);
		   $('#operateTime').datebox('setValue','');
		   
	   }else{
		   $('#id').val(r.id);
		   $('#contractCode').val($('#currentContractCode').val());
		   $('#paymentDate').datebox('setValue', r.paymentDate);
		   $('#amount').val(r.amount);
		   $('#priceUnit').val(r.priceUnit);
		   $('#receiptFlag').val(r.receiptFlag);
		   $('#operateTime').datebox('setValue',r.operateTime);
	   }
	   
 }
//加载数据库当前有的付款单位信息
function loadPriceUnitFromContact(){
	var contractCode=$('#currentContractCode').val();
//	$('#priceUnit').combobox({    
//		url : sybp()+'/tblContractAction_getPriceUnitByContractCode.action?contractCode='+contractCode,
//	    valueField:'id',    
//	    textField:'text',
//	    required:true,
//	    panelHeight:90,
//	    editable:false,
//	    onChange:function(newValue, oldValue){    
//	    },
//	    onLoadSuccess:function(none){
//	    }   
//	});  
	$.ajax({
		url: sybp()+'/tblContractAction_getPriceUnitByContractCode.action?contractCode='+contractCode,
		type:'post',
		data:$('#customerAddEditForm').serialize(),
		dataType:'json',
		success:function(r){
//		alert(r.id);
		 $('#priceUnit').val(r.id);
	     $('#priceUnit1').val(r.text);
	    }
	});
}
