function showVirusMAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("virusMAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#virusMTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/virusMAction_toEdit.action',
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
		$('#virusMAddEditDialog').dialog('setTitle',title);
		$('#virusMAddEditDialog').dialog('open'); 
		document.getElementById("virusMAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#virusMAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/virusMAction_add.action',
					type:'post',
					data:$('#virusMAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#virusMAddEditDialog').dialog('close'); 
						$('#vmid').val(r.id);
						var virusMAddEdit_event=document.getElementById("virusMAddEdit_event");
						virusMAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#virusDialog').dialog('close'); 
							var virusMAddEdit_event=document.getElementById("virusMAddEdit_event");
							virusMAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/virusMAction_editSave.action',
					type:'post',
					data:$('#virusMAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#virusMAddEditDialog').dialog('close'); 
						$('#qid').val(r.id);
						var virusMAddEdit_event=document.getElementById("virusMAddEdit_event");
						virusMAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#virusAddEditDialog').dialog('close'); 
							var virusMAddEdit_event=document.getElementById("virusMAddEdit_event");
							virusMAddEdit_event.click();
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
