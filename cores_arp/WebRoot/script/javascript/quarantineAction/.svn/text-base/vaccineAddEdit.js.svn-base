function showVaccineAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("vaccineAddEditDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }else if(addOrEdit == "edit"){
	  	var row= $('#vaccineTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/quarantineAction_toEditVaccine.action',
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
		$('#vaccineAddEditDialog').dialog('setTitle',title);
		$('#vaccineAddEditDialog').dialog('open'); 
		document.getElementById("vaccineAddEdit_event").href="javascript:"+clickName+"();";
  
}

function onDialogSaveButton(){
		if( $('#vaccineAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/quarantineAction_addVaccine.action',
					type:'post',
					data:$('#vaccineAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#vaccineAddEditDialog').dialog('close'); 
						$('#vvid').val(r.id);
						var vaccineAddEdit_event=document.getElementById("vaccineAddEdit_event");
						vaccineAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#vaccineDialog').dialog('close'); 
							var vaccineAddEdit_event=document.getElementById("vaccineAddEdit_event");
							vaccineAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/quarantineAction_editSaveVaccine.action',
					type:'post',
					data:$('#vaccineAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#vaccineAddEditDialog').dialog('close'); 
						$('#vvid').val(r.id);
						var vaccineAddEdit_event=document.getElementById("vaccineAddEdit_event");
						vaccineAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#vaccineAddEditDialog').dialog('close'); 
							var vaccineAddEdit_event=document.getElementById("vaccineAddEdit_event");
							vaccineAddEdit_event.click();
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
