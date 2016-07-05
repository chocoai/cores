function showBacteriaMAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("bacteriaMAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#bacteriaMTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/bacteriaMAction_toEdit.action',
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
		$('#bacteriaMAddEditDialog').dialog('setTitle',title);
		$('#bacteriaMAddEditDialog').dialog('open'); 
		document.getElementById("bacteriaMAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#bacteriaMAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/bacteriaMAction_add.action',
					type:'post',
					data:$('#bacteriaMAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#bacteriaMAddEditDialog').dialog('close'); 
						$('#bmid').val(r.id);
						var bacteriaMAddEdit_event=document.getElementById("bacteriaMAddEdit_event");
						bacteriaMAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#bacteriaMDialog').dialog('close'); 
							var bacteriaMAddEdit_event=document.getElementById("bacteriaMAddEdit_event");
							bacteriaMAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/bacteriaMAction_editSave.action',
					type:'post',
					data:$('#bacteriaMAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#bacteriaMAddEditDialog').dialog('close'); 
						$('#bmid').val(r.id);
						var bacteriaMAddEdit_event=document.getElementById("bacteriaMAddEdit_event");
						bacteriaMAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#bacteriaMAddEditDialog').dialog('close'); 
							var bacteriaMAddEdit_event=document.getElementById("bacteriaMAddEdit_event");
							bacteriaMAddEdit_event.click();
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
