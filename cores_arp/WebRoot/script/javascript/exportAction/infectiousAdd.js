
 function initInfectiousVeterinarianCombobox(){
 	$('#infe_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initInfectiousCombobox(){
		$('#infectiousName').combobox({    
			url : sybp()+'/infectiousAction_loadListInfectiousName.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			}
				
		});
	}
 function initInfectiousProtectorCombobox(){
	$('#infe_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initInfectiousRecorderCombobox(){
 	$('#infe_recorder').combobox({   
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



function showInfectiousTableDialog(clickname,monkeylist){
	 document.getElementById("infectiousTableDialog2").style.display="block";
	 
	 $('#infectious_monkeylist').val(monkeylist);
	 initInfectiousProtectorCombobox();
	 initInfectiousVeterinarianCombobox();
	 initInfectiousRecorderCombobox();
	 initInfectiousCombobox();
		$('#infectiousTableDialog').dialog('setTitle','传染病检测');
		$('#infectiousTableDialog').dialog('open'); 
		document.getElementById("infectiousTable_event").href="javascript:"+clickname+"();";
  
}
function afterAddInfectiousDialog(){
	parent.showMessager(1,'传染病检测信息添加成功',true,5000);
}
function infectiousSaveButton(){
		var monkeylist=document.getElementById("infectious_monkeylist").value;
		if( $('#infectiousTableForm').form('validate') ){
			$('#saveInfectiousDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/infectiousAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#infectiousTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveInfectiousDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#infectiousTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var infectiousTable_event=document.getElementById("infectiousTable_event");
						infectiousTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveInfectiousDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#infectiousTableDialog').dialog('close'); 
							var infectiousTable_event=document.getElementById("infectiousTable_event");
							infectiousTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelInfectiousButton(){
	 $('#infectiousTableDialog').dialog('close');
	 var checkbox = document.getElementById('infectious');
	  if(checkbox.checked){
	  document.getElementById('infectious').checked=false;
 	  }
}