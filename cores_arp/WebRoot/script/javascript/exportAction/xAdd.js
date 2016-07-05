
 function initXVeterinarianCombobox(){
 	$('#x_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initXProtectorCombobox(){
	$('#x_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initXRecorderCombobox(){
 	$('#x_recorder').combobox({    
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
function initCheckAreaCombobox(){
 	$('#checkarea').combobox({    
 		url : sybp()+'/xAction_getCheckArea.action',
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


function showXTableDialog(clickname,monkeylist){
	 document.getElementById("xTableDialog2").style.display="block";
	 $('#x_monkeylist').val(monkeylist);
	 initXProtectorCombobox();
	 initXVeterinarianCombobox();
	 initXRecorderCombobox();
	 initCheckAreaCombobox();
		$('#xTableDialog').dialog('setTitle','X光检测');
		$('#xTableDialog').dialog('open'); 
		document.getElementById("xTable_event").href="javascript:"+clickname+"();";;
  
}
function afterAddXDialog(){
	parent.showMessager(1,'X光检测信息添加成功',true,5000);
}
function xSaveButton(){
		var monkeylist=document.getElementById("x_monkeylist").value;
		if( $('#xTableForm').form('validate') ){
			$('#saveXDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/xAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#xTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveXDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#xTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var xTable_event=document.getElementById("xTable_event");
						xTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveXDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#xTableDialog').dialog('close'); 
							var xTable_event=document.getElementById("xTable_event");
							xTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelXButton(){
	 $('#xTableDialog').dialog('close');
	 var checkbox = document.getElementById('x');
	  if(checkbox.checked){
	  document.getElementById('x').checked=false;
 	  }
}