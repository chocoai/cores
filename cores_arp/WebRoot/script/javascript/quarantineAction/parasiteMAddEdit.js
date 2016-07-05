function showParasiteMAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("parasiteMAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#parasiteMTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/parasiteMAction_toEdit.action',
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
		$('#parasiteMAddEditDialog').dialog('setTitle',title);
		$('#parasiteMAddEditDialog').dialog('open'); 
		document.getElementById("parasiteMAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#parasiteMAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/parasiteMAction_add.action',
					type:'post',
					data:$('#parasiteMAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#parasiteMAddEditDialog').dialog('close'); 
						$('#pmid').val(r.id);
						var parasiteMAddEdit_event=document.getElementById("parasiteMAddEdit_event");
						parasiteMAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#parasiteMDialog').dialog('close'); 
							var parasiteMAddEdit_event=document.getElementById("parasiteMAddEdit_event");
							parasiteMAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/parasiteMAction_editSave.action',
					type:'post',
					data:$('#parasiteMAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#parasiteMAddEditDialog').dialog('close'); 
						$('#pmid').val(r.id);
						var parasiteMAddEdit_event=document.getElementById("parasiteMAddEdit_event");
						parasiteMAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#parasiteMAddEditDialog').dialog('close'); 
							var parasiteMAddEdit_event=document.getElementById("parasiteMAddEdit_event");
							parasiteMAddEdit_event.click();
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
		   $('#name').val('');
	  	   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#name').val(r.name);
	  	   $('#remark').val(r.remark);
	   }
	   
  }
