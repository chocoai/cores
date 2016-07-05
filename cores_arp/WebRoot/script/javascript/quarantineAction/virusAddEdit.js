function showVirusAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("virusAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#virusTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/quarantineAction_toEditVirus.action',
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
		$('#virusAddEditDialog').dialog('setTitle',title);
		$('#virusAddEditDialog').dialog('open'); 
		document.getElementById("virusAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#virusAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/quarantineAction_addVirus.action',
					type:'post',
					data:$('#virusAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#virusAddEditDialog').dialog('close'); 
						$('#vid').val(r.id);
						var parasiteAddEdit_event=document.getElementById("virusAddEdit_event");
						virusAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#virusDialog').dialog('close'); 
							var virusAddEdit_event=document.getElementById("virusAddEdit_event");
							virusAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/quarantineAction_editSaveVirus.action',
					type:'post',
					data:$('#virusAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#virusAddEditDialog').dialog('close'); 
						$('#qid').val(r.id);
						var virusAddEdit_event=document.getElementById("virusAddEdit_event");
						virusAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#virusAddEditDialog').dialog('close'); 
							var virusAddEdit_event=document.getElementById("virusAddEdit_event");
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
