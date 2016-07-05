
 function initVirusVeterinarianCombobox(){
 	$('#virus_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initVirusProtectorCombobox(){
	$('#virus_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initVirusRecorderCombobox(){
 	$('#virus_recorder').combobox({    
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



function showVirusTableDialog(clickName,monkeylist){
	 document.getElementById("virusTableDialog2").style.display="block";
	 $('#virus_monkeylist').val(monkeylist);
	 initVirusProtectorCombobox();
	 initVirusVeterinarianCombobox();
	 initVirusRecorderCombobox();
		$('#virusTableDialog').dialog('setTitle','病毒检测');
		$('#virusTableDialog').dialog('open'); 
	document.getElementById("virusTable_event").href="javascript:"+clickName+"();";
}
function afterAddVirusDialog(){
	parent.showMessager(1,'添加病毒检测信息成功',true,5000);
}
function virusSaveButton(){
		if( $('#virusTableForm').form('validate') ){
			$('#saveVirusDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/virusAction_addMonkeyList.action?',
					type:'post',
					data:$('#virusTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveVirusDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#virusTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var virusTable_event=document.getElementById("virusTable_event");
						virusTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveVirusDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#virusTableDialog').dialog('close'); 
							var virusTable_event=document.getElementById("virusTable_event");
							virusTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelVirusButton(){
	 $('#virusTableDialog').dialog('close');
	 var checkbox = document.getElementById('virus');
	  if(checkbox.checked){
	  document.getElementById('virus').checked=false;
 	  }
}