/**显示Dialog*/
function showTreasuryAddEditDialog(clickName,addOrEdit,title){
//	initChangeinAreaCombobox();
	initSysCombobox();
	/* 显示Dialog */
	document.getElementById("treasuryAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#treasuryTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/treasuryAction_toEdit.action',
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
	$('#treasuryAddEditDialog').dialog('setTitle',title);
	$('#treasuryAddEditDialog').dialog('open'); 
	document.getElementById("treasuryAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#treasuryAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/treasuryAction_add.action',
					type:'post',
					data:$('#treasuryAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#treasuryAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var treasuryAddEdit_event=document.getElementById("treasuryAddEdit_event");
						treasuryAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#treasuryDialog').dialog('close'); 
							$('#bid').val(r.id);
							var treasuryAddEdit_event=document.getElementById("treasuryAddEdit_event");
							treasuryAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/treasuryAction_editSave.action',
					type:'post',
					data:$('#treasuryAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#treasuryAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var treasuryAddEdit_event=document.getElementById("treasuryAddEdit_event");
						treasuryAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#treasuryAddEditDialog').dialog('close'); 
							$('#bid').val(r.id);
							var treasuryAddEdit_event=document.getElementById("treasuryAddEdit_event");
							treasuryAddEdit_event.click();
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
		   $('#name').val('');
		   $('#symptomssite').combobox('select','');
	  	   $('#treasurydate').datebox('setValue', '');
	  	   $('#reason').val('');
		   $('#symptomsremark').val('');
	  	   $('#prevention').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#name').val(r.name);
		   $('#symptomssite').combobox('select',r.symptomssite);
	  	   $('#treasurydate').datebox('setValue', r.treasurydate);
	  	   $('#reason').val(r.reason);
	  	   $('#symptomsremark').val(r.symptomsremark);
	  	   $('#prevention').val(r.prevention);
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
//初始化保定人员下拉选
 function initProtectorCombobox(){
 	$('#protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
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
 		   
 		}
 		 
 	});
 }
function initSysCombobox(){
	$('#symptomssite').combobox({    
		url : sybp()+'/symptomsareaAction_loadListSymptomsarea.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}

