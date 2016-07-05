function showSymptomsareaAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("symptomsareaAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#symptomsareaTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/symptomsareaAction_toEdit.action',
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
		$('#symptomsareaAddEditDialog').dialog('setTitle',title);
		$('#symptomsareaAddEditDialog').dialog('open'); 
		document.getElementById("symptomsareaAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#symptomsareaAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/symptomsareaAction_add.action',
					type:'post',
					data:$('#symptomsareaAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#symptomsareaAddEditDialog').dialog('close'); 
						$('#sssid').val(r.id);
						var symptomsareaAddEdit_event=document.getElementById("symptomsareaAddEdit_event");
						symptomsareaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#symptomsareaDialog').dialog('close'); 
							var symptomsareaAddEdit_event=document.getElementById("symptomsareaAddEdit_event");
							symptomsareaAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/symptomsareaAction_editSave.action',
					type:'post',
					data:$('#symptomsareaAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#symptomsareaAddEditDialog').dialog('close'); 
						$('#sssid').val(r.id);
						var symptomsareaAddEdit_event=document.getElementById("symptomsareaAddEdit_event");
						symptomsareaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#symptomsareaAddEditDialog').dialog('close'); 
							var symptomsareaAddEdit_event=document.getElementById("symptomsareaAddEdit_event");
							symptomsareaAddEdit_event.click();
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
		   $('#name').val('');
	  	   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#name').val(r.name);
	  	   $('#remark').val(r.remark);
	   }
	   
  }
