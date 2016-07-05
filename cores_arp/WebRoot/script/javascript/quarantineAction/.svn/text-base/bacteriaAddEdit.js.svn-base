function showBacteriaAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("bacteriaAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#bacteriaTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/quarantineAction_toEditBacteria.action',
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
		$('#bacteriaAddEditDialog').dialog('setTitle',title);
		$('#bacteriaAddEditDialog').dialog('open'); 
		document.getElementById("bacteriaAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#bacteriaAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/quarantineAction_addBacteria.action',
					type:'post',
					data:$('#bacteriaAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#bacteriaAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var bacteriaAddEdit_event=document.getElementById("bacteriaAddEdit_event");
						bacteriaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#bacteriaDialog').dialog('close'); 
							var bacteriaAddEdit_event=document.getElementById("bacteriaAddEdit_event");
							bacteriaAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/quarantineAction_editSaveBacteria.action',
					type:'post',
					data:$('#bacteriaAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#bacteriaAddEditDialog').dialog('close'); 
						$('#bid').val(r.id);
						var bacteriaAddEdit_event=document.getElementById("bacteriaAddEdit_event");
						bacteriaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#bacteriaAddEditDialog').dialog('close'); 
							var bacteriaAddEdit_event=document.getElementById("bacteriaAddEdit_event");
							bacteriaAddEdit_event.click();
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
