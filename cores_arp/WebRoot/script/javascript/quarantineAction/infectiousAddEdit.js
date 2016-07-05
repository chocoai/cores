function showInfectiousAddEditDialog(clickName,addOrEdit,title){
	/* 显示传染病主Dialog */
	 document.getElementById("infectiousAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#infectiousTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/quarantineAction_toEditInfectious.action',
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
		$('#infectiousAddEditDialog').dialog('setTitle',title);
		$('#infectiousAddEditDialog').dialog('open'); 
		document.getElementById("infectiousAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#infectiousAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/quarantineAction_addInfectious.action',
					type:'post',
					data:$('#infectiousAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#infectiousAddEditDialog').dialog('close'); 
						$('#iid').val(r.id);
						var infectiousAddEdit_event=document.getElementById("infectiousAddEdit_event");
						infectiousAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#infectiousDialog').dialog('close'); 
							var infectiousAddEdit_event=document.getElementById("infectiousAddEdit_event");
							infectiousAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/quarantineAction_editSaveInfectious.action',
					type:'post',
					data:$('#infectiousAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#infectiousAddEditDialog').dialog('close'); 
						$('#iid').val(r.id);
						var infectiousAddEdit_event=document.getElementById("infectiousAddEdit_event");
						infectiousAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#infectiousAddEditDialog').dialog('close'); 
							var infectiousAddEdit_event=document.getElementById("infectiousAddEdit_event");
							infectiousAddEdit_event.click();
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
