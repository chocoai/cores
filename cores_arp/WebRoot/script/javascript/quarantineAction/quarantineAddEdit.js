function showParasiteAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("parasiteAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#parasiteTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/quarantineAction_toEdit.action',
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
		$('#parasiteAddEditDialog').dialog('setTitle',title);
		$('#parasiteAddEditDialog').dialog('open'); 
		document.getElementById("parasiteAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#parasiteAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/quarantineAction_add.action',
					type:'post',
					data:$('#parasiteAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#parasiteAddEditDialog').dialog('close'); 
						$('#qid').val(r.id);
						var parasiteAddEdit_event=document.getElementById("parasiteAddEdit_event");
						parasiteAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#quarantineDialog').dialog('close'); 
							var parasiteAddEdit_event=document.getElementById("parasiteAddEdit_event");
							weaningAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/quarantineAction_editSave.action',
					type:'post',
					data:$('#parasiteAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#parasiteAddEditDialog').dialog('close'); 
						$('#qid').val(r.id);
						var parasiteAddEdit_event=document.getElementById("parasiteAddEdit_event");
						parasiteAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#parasiteAddEditDialog').dialog('close'); 
							var parasiteAddEdit_event=document.getElementById("parasiteAddEdit_event");
							parasiteAddEdit_event.click();
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
