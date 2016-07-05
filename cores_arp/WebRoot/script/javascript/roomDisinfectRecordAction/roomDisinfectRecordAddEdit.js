/**显示Dialog*/
function showRoomDisinfectRecordAddEditDialog(clickName,addOrEdit,title){
	initBlongareaCombobox();
	initRoomCombobox('');
	initDisinfectantCombobox();
	initOperatorCombobox();
	initDisinfectTypeCombobox();
	initRecorderCombobox();
	/* 显示Dialog */
	document.getElementById("roomDisinfectRecordAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#roomDisinfectTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/roomDisinfectRecordAction_toEdit.action',
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
	$('#roomDisinfectRecordAddEditDialog').dialog('setTitle',title);
	$('#roomDisinfectRecordAddEditDialog').dialog('open'); 
	document.getElementById("roomDisinfectRecordAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#roomDisinfectRecordAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/roomDisinfectRecordAction_add.action',
					type:'post',
					data:$('#roomDisinfectRecordAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#roomDisinfectRecordAddEditDialog').dialog('close'); 
						$('#rid').val(r.id);
						var roomDisinfectRecordAddEdit_event=document.getElementById("roomDisinfectRecordAddEdit_event");
						roomDisinfectRecordAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#roomDisinfectRecordAddEditDialog').dialog('close'); 
							var roomDisinfectRecordAddEdit_event=document.getElementById("roomDisinfectRecordAddEdit_event");
							roomDisinfectRecordAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/roomDisinfectRecordAction_editSave.action',
					type:'post',
					data:$('#roomDisinfectRecordAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#roomDisinfectRecordAddEditDialog').dialog('close'); 
						$('#rid').val(r.id);
						var roomDisinfectRecordAddEdit_event=document.getElementById("roomDisinfectRecordAddEdit_event");
						roomDisinfectRecordAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#roomDisinfectRecordAddEditDialog').dialog('close'); 
							var roomDisinfectRecordAddEdit_event=document.getElementById("roomDisinfectRecordAddEdit_event");
							roomDisinfectRecordAddEdit_event.click();
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
		   $('#blongarea').combobox('select','');
		   $('#area_id').combobox('select','');
	  	   $('#disinfectant_id').combobox('select','');
	  	   $('#disinfectDate').datebox('setValue', '');
	  	   $('#operator_id').combobox('select','');
	  	   $('#disinfectType').combobox('select','');
		   $('#recorder_id').combobox('select','');
	   }else{
		   $('#id').val(r.id);
		   $('#blongarea').combobox('select',r.blongarea);
		   $('#area_id').combobox('select',r.area_id);
	  	   $('#disinfectant_id').combobox('select',r.disinfectant_id);
	  	   $('#disinfectDate').datebox('setValue', r.disinfectDate);
	  	   $('#operator_id').combobox('select',r.operator_id);
	  	   $('#disinfectType').combobox('select',r.disinfectType);
		   $('#recorder_id').combobox('select',r.recorder_id);
	   }
	   
  }

//初始化所属区域下拉选
function initBlongareaCombobox(){
	$('#blongarea').combobox({    
		url : sybp()+'/areaAction_getPareaNameId.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    required:true,
	    onLoadSuccess:function(none){
		  
		},
		onSelect:function(record){
			initRoomCombobox(record.id);  
		}
			
	});
}
//初始化房间下拉选
function initRoomCombobox(id){
	if(id!=''&&id!=null){
	  $('#area_id').combobox({    
		url : sybp()+'/areaAction_getAllRoomIdName.action?blongarea='+id,
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    required:true,
//	    multiple:true,
	    onLoadSuccess:function(none){
		}
	  });
	}else{
		$('#area_id').combobox({ });   
	}
}
//初始化消毒液下拉选
function initDisinfectantCombobox(){
	$('#disinfectant_id').combobox({    
		url : sybp()+'/disinfectantAction_loadListDisinfectant.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    required:true,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
//初始化操作者下拉选
function initOperatorCombobox(){
	$('#operator_id').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
//初始化消毒方式下拉选
function initDisinfectTypeCombobox(){
	$('#disinfectType').combobox({    
		url : sybp()+'/roomDisinfectRecordAction_loadListDisinfectType.action',
	    valueField:'id',    
	    textField:'text',
	    editable:true,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
//初始化记录者下拉选
function initRecorderCombobox(){
	$('#recorder_id').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}