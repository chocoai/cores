var contractEditRsn;
/**显示Dialog*/
function showContractAddEditDialog(clickName,addOrEdit,title){
	/*签名Dialog*/
	document.getElementById("contractAddEditDialog2").style.display="block";
	$('#contractAddOrEdit').val(addOrEdit);
	//if(addOrEdit == 'add'){
	//	document.getElementById("contractCode0").style.display ='';
	//	document.getElementById("contractCode0").disabled =false;
	//	document.getElementById("contractCode1").style.display ='none';
	//	document.getElementById("contractCode1").disabled =true;
	//}else{
	//	document.getElementById("contractCode0").style.display ='none';
	//	document.getElementById("contractCode0").disabled =true;
	//	document.getElementById("contractCode1").style.display ='';
	//	document.getElementById("contractCode1").disabled =false;
	//}
	$('#contractAddEditDialog').dialog('setTitle',title);
	$('#contractAddEditDialog').dialog('open'); 
	document.getElementById("contractAddEdit_event").href="javascript:"+clickName+"();";
}

//获得下一个合同编号
function getNextContractPoolNum(){
   $.ajax({
     url:sybp()+'/tblContractAction_getNextContractPoolNum.action',
     dataType:'json',
		success:function(r){
			if(r ){
			   $('#contractCode').val(r.num);
			}
		}
   });
}

//填充数据(或清空数据)
function fullEditData(r){
    if(r){
    	$('#id').val(r.id);
        $('#contractCode').val(r.contractCode);
        $('#oldcontractCode').val(r.contractCode);
        
        $('#contractName').val(r.contractName);
        $('#sponsorId').val(r.sponsorId);
        $('#sponsorName').val(r.sponsorName);
        if(r.sponsorIsVender == 1){
        	$('#verderButton').linkbutton('disable');
        	document.getElementById('sponsorIsVender01').checked = true;
        	document.getElementById('sponsorIsVender01').click();
        	
        }else{
        	$('#verderButton').linkbutton('enable');
        	document.getElementById('sponsorIsVender11').checked = true;
        	document.getElementById('sponsorIsVender11').click();
        }
        $('#venderId').val(r.venderId);
        $('#venderNameC').val(r.venderName);
        $('#contractPrice').val(r.contractPrice);
        $('#priceUnit').val(r.priceUnit);
        $('#signingDate').datebox('setValue',r.signingDate);
        $('#effectiveDate').datebox('setValue',r.effectiveDate);
        $('#finishDate').datebox('setValue',r.finishDate);
        $('#remark').val(r.remark);
        
        $('#contractState_contractDialog').val(r.contractState);
    }else{
    	$('#id').val('');
        $('#contractCode').val('');
        $('#contractName').val('');
        $('#sponsorId').val('');
        $('#sponsorName').val('');
        document.getElementById('sponsorIsVender01').checked = true;
      	document.getElementById('sponsorIsVender01').click();
        $('#venderId').val('');
        $('#venderNameC').val('');
        $('#contractPrice').val('');
        $('#priceUnit').val(0);
        $('#signingDate').datebox('setValue','');
        $('#effectiveDate').datebox('setValue','');
        $('#finishDate').datebox('setValue','');
        $('#remark').val('');
        
        $('#contractState_contractDialog').val('');
    }
}


/**确定（保存）*/
function onDialogContractSaveButton(){
	//$('#contractAddEditDialog').dialog('close'); 
	//var contractAddEdit_event=document.getElementById("contractAddEdit_event");
	//contractAddEdit_event.click();
	if( $('#contractAddEditForm').form('validate') ){
		$('#saveDialogButton').linkbutton('disable');
		if($('#contractAddOrEdit').val() == 'add'){
			$.ajax({
				url:sybp()+'/tblContractAction_addSave.action',
				type:'post',
				data:$('#contractAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#currentId').val(r.id);
						$('#contractCode0').val(r.contractCode);
						$('#contractAddEditDialog').dialog('close'); 
						var contractAddEdit_event=document.getElementById("contractAddEdit_event");
						contractAddEdit_event.click();
					}else{
						if(r.msg && r.msg != ""){
							$.messager.alert('提示',r.msg);
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
						
					}
				}
			});
		}else{
			var contractState_contractDialog = $('#contractState_contractDialog').val();
			if(contractState_contractDialog == 2){
				$.messager.prompt('提示信息', '请填写合同编辑原因：', function(r){
	    			if (r){
	    				if(r != ""){
		    			    contractEditRsn = r;
		    			    qm_showQianmingDialog('afterSignContractEdit');
	    			    }else{
	    			    	$('#saveDialogButton').linkbutton('enable');
	    			    }
	    			}else{
	    				$('#saveDialogButton').linkbutton('enable');
	    			}
	    		});
			}else{
				$.ajax({
					url:sybp()+'/tblContractAction_editSave.action',
					type:'post',
					data:$('#contractAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#contractAddEditDialog').dialog('close'); 
						var contractAddEdit_event=document.getElementById("contractAddEdit_event");
						contractAddEdit_event.click();
					}else{
						if(r.msg && r.msg != ""){
							$.messager.alert('提示',r.msg);
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				}
				});
			}
		}
		
	}
					
}

/**合同编辑签字后执行
 * @return
 */
function afterSignContractEdit(){
	$.ajax({
		url:sybp()+'/tblContractAction_editSave.action?contractEditRsn='+contractEditRsn,
		type:'post',
		data:$('#contractAddEditForm').serialize(),
		dataType:'json',
		success:function(r){
			$('#saveDialogButton').linkbutton('enable');
			if(r && r.success){
				$('#contractAddEditDialog').dialog('close'); 
				var contractAddEdit_event=document.getElementById("contractAddEdit_event");
				contractAddEdit_event.click();
			}else{
				if(r.msg && r.msg != ""){
					$.messager.alert('提示',r.msg);
				}else{
					$.messager.alert('提示','请检查录入的数据');
				}
			}
		}
	});
}

/**委托方即厂家单选框事件*/
function onSponsorIsVenderRadio(){
	if(document.getElementById('sponsorIsVender11').checked){
		document.getElementById('sponsorIsVender01').click();
	}else{
		document.getElementById('sponsorIsVender11').click();
	}
}
/**委托方即厂家单选框change事件*/
function sponsorIsVenderChange(){
	//选择的是  ：是
	if(document.getElementById('sponsorIsVender01').checked){
		$('#verderButton').linkbutton('disable');
		$('#venderId').val($('#sponsorId').val());
		$('#venderNameC').val($('#sponsorName').val());
		document.getElementById('venderNameC').disabled=true;
	}else{
		$('#verderButton').linkbutton('enable');
		document.getElementById('venderNameC').disabled=false;
	}
}
/**选择委托方按钮事件*/
function onSponsorButton(){
	document.getElementById("sponsorName").blur();
	showSelectCustomerDialog('afterOnSponsorButton','查询客户信息');
}
/**选择委托方按钮  后事件*/
function afterOnSponsorButton(){
	$('#sponsorId').val($('#cuid').val());
	$('#sponsorName').val($('#customerNameadd').val());
	$('#sponsorName').validatebox('validate');
	if(document.getElementById('sponsorIsVender01').checked){
		$('#venderId').val($('#sponsorId').val());
		$('#venderNameC').val($('#sponsorName').val());
	}
}


/**选择厂家按钮事件*/
function onVerderButtonCon(){
	//$('#venderId').val('50000000028');
	//$('#venderNameC').val('xx农药');
	document.getElementById("venderNameC").blur();
	showSelectCustomerDialog('afteronVerderButtonCon','查询客户信息');
}
/**选择厂家按钮  后事件*/
function afteronVerderButtonCon(){
	$('#venderId').val($('#cuid').val());
	$('#venderNameC').val($('#customerNameadd').val());
	$('#venderNameC').validatebox('validate');
}

