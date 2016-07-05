/**显示Dialog*/
function showWeaningAddEditDialog(clickName,addOrEdit,title){
	initKeeperCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	initRecorderCombobox();
	/* 显示Dialog */
	document.getElementById("weaningAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#weaningTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/weaningAction_toEdit.action',
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
	$('#weaningAddEditDialog').dialog('setTitle',title);
	$('#weaningAddEditDialog').dialog('open'); 
	document.getElementById("weaningAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#weaningAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/weaningAction_add.action',
					type:'post',
					data:$('#weaningAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#weaningAddEditDialog').dialog('close'); 
						$('#wid').val(r.id);
						var weaningAddEdit_event=document.getElementById("weaningAddEdit_event");
						weaningAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#weaningAddEditDialog').dialog('close'); 
							var weaningAddEdit_event=document.getElementById("weaningAddEdit_event");
							weaningAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/weaningAction_editSave.action',
					type:'post',
					data:$('#weaningAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#weaningAddEditDialog').dialog('close'); 
						$('#wid').val(r.id);
						var weaningAddEdit_event=document.getElementById("weaningAddEdit_event");
						weaningAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#weaningAddEditDialog').dialog('close'); 
							var weaningAddEdit_event=document.getElementById("weaningAddEdit_event");
							weaningAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

//编辑客户时填充选中的客户信息到编辑界面
 function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#addmonkeyid').val('');
		   $('#leavebreastdate').datebox('setValue', '');
		   $('#leavebreastweight').val('');
		   $('#motherid').val('');
		   $('#keeper').combobox('select','');
		   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
	  	   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#addmonkeyid').val(r.monkeyid);
		   $('#leavebreastdate').datebox('setValue', r.leavebreastdate);
		   $('#leavebreastweight').val(r.leavebreastweight);
		   $('#motherid').val(r.motherid);
	  	   $('#keeper').combobox('select',r.keeper);
	  	   $('#remark').val(r.remark);
	  	   $('#recorder').combobox('select',r.recorder);
	  	   $('#operater').combobox('select',r.operater);
	   }
	   
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

