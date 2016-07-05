/**显示Dialog*/
function showHospitaldlAddEditDialog(clickName,addOrEdit,title){
	initVeterinarianCombobox();
	/* 显示Dialog */
	document.getElementById("hospitaldlAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#hospitaldlTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/hospitaldlAction_toEdit.action',
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
	$('#hospitaldlAddEditDialog').dialog('setTitle',title);
	$('#hospitaldlAddEditDialog').dialog('open'); 
	document.getElementById("hospitaldlAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#hospitaldlAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/hospitaldlAction_add.action',
					type:'post',
					data:$('#hospitaldlAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#hospitaldlAddEditDialog').dialog('close'); 
						$('#hlid').val(r.id);
						var hospitaldlAddEdit_event=document.getElementById("hospitaldlAddEdit_event");
						hospitaldlAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#hospitaldlDialog').dialog('close'); 
							var hospitaldlAddEdit_event=document.getElementById("hospitaldlAddEdit_event");
							hospitaldlAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/hospitaldlAction_editSave.action',
					type:'post',
					data:$('#hospitaldlAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#hospitaldlAddEditDialog').dialog('close'); 
						$('#hlid').val(r.id);
						var hospitaldlAddEdit_event=document.getElementById("hospitaldlAddEdit_event");
						hospitaldlAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#hospitaldlAddEditDialog').dialog('close'); 
							var hospitaldlAddEdit_event=document.getElementById("hospitaldlAddEdit_event");
							hospitaldlAddEdit_event.click();
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
		   $('#zlrq').datebox('setValue', '');
		   $('#treatveterinarian').combobox('select','');
		   $('#zzmc').val('');
		   $('#cf').val('');
	  	   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#monkeyid').val(r.monkeyid);
		   $('#zlrq').datebox('setValue', r.zlrq);
	  	   $('#treatveterinarian').combobox('select',r.treatveterinarian);
	  	   $('#zzmc').val(r.zzmc);
	  	   $('#cf').val(r.cf);
	  	   $('#remark').val(r.remark);
	   }
	   
  }
//初始化主治兽医下拉选
 function initVeterinarianCombobox(){
 	$('#treatveterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#treatveterinarian').combobox('select',"");
 			}
 	}
 		 
 	});
 }