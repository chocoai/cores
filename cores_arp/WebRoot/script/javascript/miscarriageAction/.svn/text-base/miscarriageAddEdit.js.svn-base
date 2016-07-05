/**显示Dialog*/
function showMiscarriageAddEditDialog(clickName,addOrEdit,title){
	initVeterinarianCombobox();
	initProtectorCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	/* 显示Dialog */
	document.getElementById("miscarriageAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#miscarriageTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/miscarriageAction_toEdit.action',
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
	$('#miscarriageAddEditDialog').dialog('setTitle',title);
	$('#miscarriageAddEditDialog').dialog('open'); 
	document.getElementById("miscarriageAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#miscarriageAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/miscarriageAction_add.action',
					type:'post',
					data:$('#miscarriageAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#miscarriageAddEditDialog').dialog('close'); 
						$('#mid').val(r.id);//赋值，使新增，修改后选中原数据.
						var miscarriageAddEdit_event=document.getElementById("miscarriageAddEdit_event");
						miscarriageAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#miscarriageDialog').dialog('close'); 
							var miscarriageAddEdit_event=document.getElementById("miscarriageAddEdit_event");
							miscarriageAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/miscarriageAction_editSave.action',
					type:'post',
					data:$('#miscarriageAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#miscarriageAddEditDialog').dialog('close'); 
						$('#mid').val(r.id);
						var miscarriageAddEdit_event=document.getElementById("miscarriageAddEdit_event");
						miscarriageAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#miscarriageAddEditDialog').dialog('close'); 
							var miscarriageAddEdit_event=document.getElementById("miscarriageAddEdit_event");
							miscarriageAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

 function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#monkeyid').val('');
		   $('#miscarriagedate').datebox('setValue', '');
		   $('#veterinarian').combobox('select','');
		   $('#protector').combobox('select','');
		   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
	  	   $('#remarks').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#monkeyid').val(r.monkeyid);
		   $('#miscarriagedate').datebox('setValue', r.miscarriagedate);
	  	   $('#veterinarian').combobox('select',r.veterinarian);
	  	   $('#remarks').val(r.remarks);
	  	   $('#protector').combobox('select',r.protector);
	  	   $('#recorder').combobox('select',r.recorder);
	  	   $('#operater').combobox('select',r.operater);
	   }
	   
  }
//初始化房间兽医下拉选
 function initVeterinarianCombobox(){
 	$('#veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#veterinarian').combobox('select',"");
 			}
 		}
 		 
 	});
 }
 //初始化保定人员下拉框
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
//初始化记录人员下拉选
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
 	    onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#operater').combobox('select',"");
 			}
 	}
 		 
 	});
 }

