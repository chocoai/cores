/**显示Dialog*/
function showHospitalAddEditDialog(clickName,addOrEdit,title){
	initVeterinarianCombobox();
	/* 显示Dialog */
	document.getElementById("hospitalAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#hospitalTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/hospitalAction_toEdit.action',
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
	$('#hospitalAddEditDialog').dialog('setTitle',title);
	$('#hospitalAddEditDialog').dialog('open'); 
	document.getElementById("hospitalAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#hospitalAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/hospitalAction_add.action',
					type:'post',
					data:$('#hospitalAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#hospitalAddEditDialog').dialog('close'); 
						$('#hid').val(r.id);
						var hospitalAddEdit_event=document.getElementById("hospitalAddEdit_event");
						hospitalAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#hospitalDialog').dialog('close'); 
							var hospitalAddEdit_event=document.getElementById("hospitalAddEdit_event");
							hospitalAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/hospitalAction_editSave.action',
					type:'post',
					data:$('#hospitalAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#hospitalAddEditDialog').dialog('close'); 
						$('#hid').val(r.id);
						var hospitalAddEdit_event=document.getElementById("hospitalAddEdit_event");
						hospitalAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#hospitalAddEditDialog').dialog('close'); 
							var hospitalAddEdit_event=document.getElementById("hospitalAddEdit_event");
							hospitalAddEdit_event.click();
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
		   $('#checkdate').datebox('setValue', '');
		   $('#treatveterinarian').combobox('select','');
		   $('#zzmc').val('');
		   $('#cf').val('');
	  	   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#monkeyid').val(r.monkeyid);
		   $('#checkdate').datebox('setValue', r.checkdate);
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