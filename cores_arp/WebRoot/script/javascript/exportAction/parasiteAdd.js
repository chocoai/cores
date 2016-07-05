
 function initParasiteQcryCombobox(){
 	$('#qcry').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 function initParasiteVeterinarianCombobox(){
 	$('#paras_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initParasiteProtectorCombobox(){
	$('#paras_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initParasiteRecorderCombobox(){
 	$('#paras_recorder').combobox({    
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
function initYPCombobox(){
 	$('#yb_id').combobox({    
 		url : sybp()+'/parasiteAction_loadListYP.action',
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
function initQCRYCombobox(){
 	$('#q_id').combobox({    
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
function initQCBDCombobox(){
 	$('#qcbd').combobox({    
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

function showParasiteTableDialog(clickName,monkeylist){
	 document.getElementById("parasiteTableDialog2").style.display="block";
	 
	 initParasiteProtectorCombobox();
	 initParasiteVeterinarianCombobox();
	 initParasiteRecorderCombobox();
	 initYPCombobox();
	 initQCRYCombobox();
	 initQCBDCombobox();
	 
	 $('#parasite_monkeylist').val(monkeylist);
		$('#parasiteTableDialog').dialog('setTitle','寄生虫检测');
		$('#parasiteTableDialog').dialog('open'); 
		document.getElementById("parasiteTable_event").href="javascript:"+clickName+"();";
  
}
function afterAddParasiteDialog(){
	parent.showMessager(1,'添加寄生虫检疫信息成功',true,5000);
	// $('#weaningTable').datagrid('reload');
}
function parasiteSaveButton(){
		var monkeylist=$('#monkeylist').val();
		if( $('#parasiteTableForm').form('validate') ){
			$('#saveParasiteDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/parasiteAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#parasiteTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveParasiteDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#parasiteTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var parasiteTable_event=document.getElementById("parasiteTable_event");
						parasiteTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveParasiteDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#parasiteTableDialog').dialog('close'); 
							var parasiteTable_event=document.getElementById("parasiteTable_event");
							parasiteTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelParasiteButton(){
	 $('#parasiteTableDialog').dialog('close');
	 var checkbox = document.getElementById('parasite');
	  if(checkbox.checked){
	  document.getElementById('parasite').checked=false;
 	  }
}