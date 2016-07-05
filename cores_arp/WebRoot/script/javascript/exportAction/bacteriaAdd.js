
 function initBacteriaVeterinarianCombobox(){
 	$('#bacte_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initBacteriaProtectorCombobox(){
	$('#bacte_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initBacteriaRecorderCombobox(){
 	$('#bacte_recorder').combobox({    
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



function showBacteriaTableDialog(clickname,monkeylist){
	 document.getElementById("bacteriaTableDialog2").style.display="block";
	 
	 $('#bacteria_monkeylist').val(monkeylist);
	 initBacteriaProtectorCombobox();
	 initBacteriaVeterinarianCombobox();
	 initBacteriaRecorderCombobox();
		$('#bacteriaTableDialog').dialog('setTitle','细菌检测');
		$('#bacteriaTableDialog').dialog('open'); 
		document.getElementById("bacteriaTable_event").href="javascript:"+clickname+"();";
  
}
function afterAddBacteriaDialog(){
	parent.showMessager(1,'添加细菌检测信息成功',true,5000);
}
function bacteriaSaveButton(){
		var monkeylist=document.getElementById("bacteria_monkeylist").value;
		if( $('#bacteriaTableForm').form('validate') ){
			$('#saveBacteriaDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/bacteriaAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#bacteriaTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveBacteriaDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#bacteriaTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var bacteriaTable_event=document.getElementById("bacteriaTable_event");
						bacteriaTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveBacteriaDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#bacteriaTableDialog').dialog('close'); 
							var bacteriaTable_event=document.getElementById("bacteriaTable_event");
							bacteriaTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelBacteriaButton(){
	
	 	$('#bacteriaTableDialog').dialog('close');
	 	var checkbox = document.getElementById('bacteria');
	 	if(checkbox.checked){
	 		document.getElementById('bacteria').checked=false;
	 	}
}