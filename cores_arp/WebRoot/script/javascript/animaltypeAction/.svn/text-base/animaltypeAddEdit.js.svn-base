/**显示Dialog*/
function showAnimaltypeAddEditDialog(clickName,addOrEdit,title){
	/* 显示Dialog */
	document.getElementById("animaltypeAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#animaltypeTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/animaltypeAction_toEdit.action',
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
	$('#animaltypeAddEditDialog').dialog('setTitle',title);
	$('#animaltypeAddEditDialog').dialog('open'); 
	document.getElementById("animaltypeAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#animaltypeAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/animaltypeAction_add.action',
					type:'post',
					data:$('#animaltypeAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#animaltypeAddEditDialog').dialog('close'); 
						$('#Animaltypeid').val(r.id);
						var animaltypeAddEdit_event=document.getElementById("animaltypeAddEdit_event");
						animaltypeAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#animaltypeAddEditDialog').dialog('close'); 
							var animaltypeAddEdit_event=document.getElementById("animaltypeAddEdit_event");
							animaltypeAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/animaltypeAction_editSave.action',
					type:'post',
					data:$('#animaltypeAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#animaltypeAddEditDialog').dialog('close'); 
						$('#Animaltypeid').val(r.id);
						var animaltypeAddEdit_event=document.getElementById("animaltypeAddEdit_event");
						animaltypeAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#animaltypeAddEditDialog').dialog('close'); 
							var animaltypeAddEdit_event=document.getElementById("animaltypeAddEdit_event");
							animaltypeAddEdit_event.click();
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
		   $('#desciption').val('');
	  	   
	   }else{
		   $('#id').val(r.id);
		   $('#name').val(r.name);
		   $('#desciption').val(r.desciption);
	  	   
	   }
	   
}