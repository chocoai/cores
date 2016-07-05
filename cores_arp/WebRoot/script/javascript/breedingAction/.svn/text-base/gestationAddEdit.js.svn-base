/**显示Dialog*/
function showGestationAddEditDialog(clickName,addOrEdit,title){
	initVeterinarianCombobox()
	initProtectorCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	/* 显示Dialog */
	document.getElementById("gestationAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#gestationTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/gestationAction_toEdit.action',
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
	$('#gestationAddEditDialog').dialog('setTitle',title);
	$('#gestationAddEditDialog').dialog('open'); 
	document.getElementById("gestationAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#gestationAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/gestationAction_add.action',
					type:'post',
					data:$('#gestationAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#gestationAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var gestationAddEdit_event=document.getElementById("gestationAddEdit_event");
						gestationAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#gestationDialog').dialog('close'); 
							$('#bid').val(r.id);
							var gestationAddEdit_event=document.getElementById("gestationAddEdit_event");
							gestationAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/gestationAction_editSave.action',
					type:'post',
					data:$('#gestationAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#gestationAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var gestationAddEdit_event=document.getElementById("gestationAddEdit_event");
						gestationAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#gestationAddEditDialog').dialog('close'); 
							$('#bid').val(r.id);
							var gestationAddEdit_event=document.getElementById("gestationAddEdit_event");
							gestationAddEdit_event.click();
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
		   $('#checkdate').datebox('setValue', '');
		   $('#ishave').combobox('select',0);
	  	   $('#veterinarian').combobox('select','');
		   $('#protector').combobox('select','');
	  	   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
		   $('#remarks').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#oldmonkeyid').val(r.monkeyid);
		   $('#addmonkeyid').val(r.monkeyid);
		   $('#checkdate').datebox('setValue', r.checkdate);
		   $('#ishave').combobox('select',r.ishave);
	  	   $('#veterinarian').combobox('select',r.veterinarian);
	  	   $('#protector').combobox('select',r.protector);
	  	   $('#recorder').combobox('select',r.recorder);
		   $('#operater').combobox('select',r.operater);
		   $('#remarks').val(r.remarks);
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
	 	    onSelect:function(rec){
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
			if(rec.id=="-1"){$('#operater').combobox('select',"");}
	}
		 
	});
}

