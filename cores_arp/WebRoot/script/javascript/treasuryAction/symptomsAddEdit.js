function showSymptomsAddEditDialog(clickName,addOrEdit,title){
	initSysCombobox();
	 document.getElementById("symptomsAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#symptomsTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/symptomsAction_toEdit.action',
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
		$('#symptomsAddEditDialog').dialog('setTitle',title);
		$('#symptomsAddEditDialog').dialog('open'); 
		document.getElementById("symptomsAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#symptomsAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/symptomsAction_add.action',
					type:'post',
					data:$('#symptomsAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#symptomsAddEditDialog').dialog('close'); 
						$('#ssid').val(r.id);
						var symptomsAddEdit_event=document.getElementById("symptomsAddEdit_event");
						symptomsAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#symptomsDialog').dialog('close'); 
							var symptomsAddEdit_event=document.getElementById("symptomsAddEdit_event");
							symptomsAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/symptomsAction_editSave.action',
					type:'post',
					data:$('#symptomsAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#symptomsAddEditDialog').dialog('close'); 
						$('#ssid').val(r.id);
						var symptomsAddEdit_event=document.getElementById("symptomsAddEdit_event");
						symptomsAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#symptomsAddEditDialog').dialog('close'); 
							var symptomsAddEdit_event=document.getElementById("symptomsAddEdit_event");
							symptomsAddEdit_event.click();
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
		   $('#symptomssite').combobox('select','');
		   $('#reason').val('');
	  	   $('#symptomsremark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#name').val(r.name);
		   $('#symptomssite').combobox('select',r.symptomssite);
		   $('#reason').val(r.reason);
	  	   $('#symptomsremark').val(r.symptomsremark);
	   }
	   
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
