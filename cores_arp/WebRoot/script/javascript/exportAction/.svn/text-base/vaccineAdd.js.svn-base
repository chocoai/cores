
 function initVaccineVeterinarianCombobox(){
 	$('#vacci_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initVaccineProtectorCombobox(){
	$('#vacci_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initVaccineRecorderCombobox(){
 	$('#vacci_recorder').combobox({    
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
function initVaccineCombobox(){
 	$('#vaccineType').combobox({    
 		url : sybp()+'/vaccineAction_loadYMLX.action',
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


function showVaccineTableDialog(clickname,monkeylist){
	 document.getElementById("vaccineTableDialog2").style.display="block";
	 
	 $('#vaccine_monkeylist').val(monkeylist);
	 initVaccineProtectorCombobox();
	 initVaccineVeterinarianCombobox();
	 initVaccineRecorderCombobox();
	 initVaccineCombobox();
		$('#vaccineTableDialog').dialog('setTitle','疫苗检测');
		$('#vaccineTableDialog').dialog('open');
		document.getElementById("vaccineTable_event").href="javascript:"+clickname+"();";
  
}
function afterAddVaccineDialog(){
	parent.showMessager(1,'添加疫苗检疫信息成功',true,5000);
}
function vaccineSaveButton(){
		var monkeylist=document.getElementById("vaccine_monkeylist").value;
		if( $('#vaccineTableForm').form('validate') ){
			$('#saveVaccineDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/vaccineAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#vaccineTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveVaccineDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#vaccineTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var vaccineTable_event=document.getElementById("vaccineTable_event");
						vaccineTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveVaccineDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#vaccineTableDialog').dialog('close'); 
							var vaccineTable_event=document.getElementById("vaccineTable_event");
							vaccineTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelVaccineButton(){
	 $('#vaccineTableDialog').dialog('close');
	 var checkbox = document.getElementById('vaccine');
	  if(checkbox.checked){
	  document.getElementById('vaccine').checked=false;
 	  }
}