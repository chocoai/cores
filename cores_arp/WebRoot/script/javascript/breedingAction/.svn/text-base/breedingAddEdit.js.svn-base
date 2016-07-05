/**显示Dialog*/
function showBreedingAddEditDialog(clickName,addOrEdit,title){
//	initChangeinAreaCombobox();
	initVeterinarianCombobox()
	initProtectorCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	initOestrustypeCombobox();
	/* 显示Dialog */
	document.getElementById("breedingAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#breedingTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/breedingAction_toEdit.action',
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
	$('#breedingAddEditDialog').dialog('setTitle',title);
	$('#breedingAddEditDialog').dialog('open'); 
	document.getElementById("breedingAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#breedingAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/breedingAction_add.action',
					type:'post',
					data:$('#breedingAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#breedingAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var breedingAddEdit_event=document.getElementById("breedingAddEdit_event");
						breedingAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#breedingDialog').dialog('close'); 
							$('#bid').val(r.id);
							var breedingAddEdit_event=document.getElementById("breedingAddEdit_event");
							breedingAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/breedingAction_editSave.action',
					type:'post',
					data:$('#breedingAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#breedingAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var breedingAddEdit_event=document.getElementById("breedingAddEdit_event");
						breedingAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#breedingAddEditDialog').dialog('close'); 
							$('#bid').val(r.id);
							var breedingAddEdit_event=document.getElementById("breedingAddEdit_event");
							breedingAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

//添加或编辑时加载数据
 function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#addmonkeyid').val('');
		   $('#oestrusdate').datebox('setValue', '');
		   $('#oestrustype').combobox('select',27);
		   $('#breedingdate').datebox('setValue', '');
		   $('#malesmonkeyid').val('');
	  	   $('#matingdate').datebox('setValue', '');
	  	   $('#veterinarian').combobox('select','');
		   $('#protector').combobox('select','');
	  	   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
		   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#oldmonkeyid').val(r.monkeyid);
		   $('#addmonkeyid').val(r.monkeyid);
		   $('#oestrusdate').datebox('setValue', r.oestrusdate);
		   $('#oestrustype').combobox('select',r.oestrustype);
		   $('#breedingdate').datebox('setValue', r.breedingdate);
		   $('#oldmailmonkeyids').val(r.malesmonkeyid);
		   $('#malesmonkeyid').val(r.malesmonkeyid);
	  	   $('#matingdate').datebox('setValue', r.matingdate);
	  	   $('#veterinarian').combobox('select',r.veterinarian);
	  	   $('#protector').combobox('select',r.protector);
	  	   $('#recorder').combobox('select',r.recorder);
		   $('#operater').combobox('select',r.operater);
		   $('#remark').val(r.remark);
	   }
	   
  }
//初始化公猴下拉选
 function initMalesmonkeyCombobox(){
	 $('#malesmonkeyCombobox').combobox({    
	 		url : sybp()+'/individualAction_loadMaleMonkeyListByFemailMonkey.action',
	 	    valueField:'id',    
	 	    textField:'text',
	 	    editable:false,
	 	    multiple:true,
	 	    onLoadSuccess:function(none){
	 		   
	 		}
	 		 
	 	});
 }
 
//初始化兽医下拉选
 function initVeterinarianCombobox(){
	 	$('#veterinarian').combobox({    
	 		url : sybp()+'/employeeAction_loadListEmployee.action',
	 	    valueField:'id',    
	 	    textField:'text',
	 	    editable:false,
	 	    onLoadSuccess:function(none){
	 		   
	 		},onSelect:function(rec){
	 			if(rec.id=="-1"){
	 				$('#veterinarian').combobox('select',"");
	 			}
	 		}
	 		 
	 	});
	 }
//初始化保定人员下拉选
 function initProtectorCombobox(){
 	$('#protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#protector').combobox('select',"");
 			}
 		}
 		 
 	});
 }

 
//初始化数据记录人员下拉选
 function initRecorderCombobox(){
 	$('#recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#recorder').combobox('select',"");
 			}
 		}
 		 
 	});
 }
//初始化档案录入下拉选
function initOperaterCombobox(){
	$('#operater').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(rec){
			if(rec.id=="-1"){
				$('#operater').combobox('select',"");
			}
		}
		 
	});
}

function initOestrustypeCombobox(){
	$('#oestrustype').combobox({
		url:sybp()+'/breedingAction_getOestrusType.action',
		valueField:'id',
		textField:'text',
		editable:false,
		onSelect:function(rec){
		if(rec.id=="-1"){
			$('#oestrustype').combobox('select',"");
		}
	}
	});
}