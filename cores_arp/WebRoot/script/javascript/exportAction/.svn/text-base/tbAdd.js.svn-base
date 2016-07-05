
function initTB24VeterinarianCombobox(){
 	$('#tb24v').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
function initTB48VeterinarianCombobox(){
 	$('#tb48v').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
function initTB72VeterinarianCombobox(){
 	$('#tb72v').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 function initTBVeterinarianCombobox(){
 	$('#tb_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initTBProtectorCombobox(){
	$('#tb_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initTBRecorderCombobox(){
 	$('#tb_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		  
 		},
 		onSelect:function(record){
 		}
 			
 	});
 }
function initTbCombobox(){
 	$('#tbyj').combobox({    
 		url : sybp()+'/tbAction_loadListTB.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		  
 		},
 		onSelect:function(record){
 		}
 			
 	});
 }


function showTbTableDialog(clickName,monkeylist){
	 document.getElementById("tbTableDialog2").style.display="block";
	 initTB72VeterinarianCombobox();
	 initTB24VeterinarianCombobox();
	 initTB48VeterinarianCombobox();
	 initTBProtectorCombobox();
	 initTBVeterinarianCombobox();
	 initTBRecorderCombobox();
	 initTbCombobox();
	 $('#tb_monkeylist').val(monkeylist);
		$('#tbTableDialog').dialog('setTitle','TB检测');
		$('#tbTableDialog').dialog('open'); 
		document.getElementById("tbTable_event").href="javascript:"+clickName+"();";
  
}
function afterAddTBDialog(){
	 parent.showMessager(1,'编辑成功',true,5000);
	 //$('#weaningTable').datagrid('reload');
}
function tbSaveButton(){
		var monkeylist=$("#tb_monkeylist").val();
		if( $('#tbTableForm').form('validate') ){
			$('#saveTbDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/tbAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#tbTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveTbDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#tbTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var tbTable_event=document.getElementById("tbTable_event");
						tbTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveTbDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#tbTableDialog').dialog('close'); 
							var tbTable_event=document.getElementById("tbTable_event");
							tbTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelTbButton(){
	 $('#tbTableDialog').dialog('close');
	 var checkbox = document.getElementById('tb');
	  if(checkbox.checked){
	  document.getElementById('tb').checked=false;
 	  }
}