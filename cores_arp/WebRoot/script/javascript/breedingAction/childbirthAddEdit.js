/**显示Dialog*/
function showChildbirthAddEditDialog(clickName,addOrEdit,title){
//	initChangeinAreaCombobox();
	initVeterinarianCombobox();
	initKeeperCombobox();
	initProtectorCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	/* 显示Dialog */
	document.getElementById("childbirthAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#childbirthTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/childbirthAction_toEdit.action',
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
	$('#childbirthAddEditDialog').dialog('setTitle',title);
	$('#childbirthAddEditDialog').dialog('open'); 
	document.getElementById("childbirthAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#childbirthAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/childbirthAction_add.action',
					type:'post',
					data:$('#childbirthAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#childbirthAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var childbirthAddEdit_event=document.getElementById("childbirthAddEdit_event");
						childbirthAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#childbirthDialog').dialog('close'); 
							$('#bid').val(r.id);
							var childbirthAddEdit_event=document.getElementById("childbirthAddEdit_event");
							childbirthAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/childbirthAction_editSave.action',
					type:'post',
					data:$('#childbirthAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#childbirthAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var childbirthAddEdit_event=document.getElementById("childbirthAddEdit_event");
						childbirthAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#childbirthAddEditDialog').dialog('close'); 
							$('#bid').val(r.id);
							var childbirthAddEdit_event=document.getElementById("childbirthAddEdit_event");
							childbirthAddEdit_event.click();
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
		   $('#labordate').datebox('setValue', '');
		   $('#childercount').val('');
		   $('#monkeyids').val('');
		   $('#laborcondition').val('');
	  	   $('#veterinarian').combobox('select','');
	  	   $('#keeper').combobox('select','');
		   $('#protector').combobox('select','');
	  	   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
	   }else{
		   $('#id').val(r.id);
		   $('#oldmonkeyid').val(r.monkeyid);
		   $('#addmonkeyid').val(r.monkeyid);
		   $('#labordate').datebox('setValue', r.labordate);
		   $('#childercount').val(r.childercount);
		   $('#monkeyids').val('');
		   $('#laborcondition').val(r.laborcondition);
	  	   $('#veterinarian').combobox('select',r.veterinarian);
	  	   $('#keeper').combobox('select',r.keeper);
	  	   $('#protector').combobox('select',r.protector);
	  	   $('#recorder').combobox('select',r.recorder);
		   $('#operater').combobox('select',r.operater);
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
	 		   
	 		}
	 		 
	 	});
	 }
//初始化饲养员下拉选
 function initKeeperCombobox(){
	 	$('#keeper').combobox({    
	 		url : sybp()+'/employeeAction_loadListEmployee.action',
	 	    valueField:'id',    
	 	    textField:'text',
	 	    editable:false,
	 	    onSelect:function(rec){
	 			if(rec.id=="-1"){
	 				$('#keeper').combobox('select',"");
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
			if(rec.id=="-1"){
				$('#operater').combobox('select',"");
			}
	}
		 
	});
}

