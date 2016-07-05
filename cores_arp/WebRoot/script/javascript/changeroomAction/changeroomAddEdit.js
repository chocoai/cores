/**显示Dialog*/
function showChangeroomAddEditDialog(clickName,addOrEdit,title){
	initChangeinAreaCombobox();
	initChangeinRoomCombobox('');
	initProtectorCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	/* 显示Dialog */
	document.getElementById("changeroomAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
		
	}else if(addOrEdit == "edit"){
	  var row= $('#changeroomTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/changeroomAction_toEdit.action',
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
  		
	$('#changeroomAddEditDialog').dialog('setTitle',title);
	$('#changeroomAddEditDialog').dialog('open'); 
	document.getElementById("changeroomAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#changeroomAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/changeroomAction_add.action',
					type:'post',
					data:$('#changeroomAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#changeroomAddEditDialog').dialog('close'); 
						$('#cid').val(r.id);
						var changeroomAddEdit_event=document.getElementById("changeroomAddEdit_event");
						changeroomAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#changeroomDialog').dialog('close'); 
							$('#cid').val(r.id);
							var changeroomAddEdit_event=document.getElementById("changeroomAddEdit_event");
							changeroomAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/changeroomAction_editSave.action',
					type:'post',
					data:$('#changeroomAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#changeroomAddEditDialog').dialog('close'); 
						$('#cid').val(r.id);
						var changeroomAddEdit_event=document.getElementById("changeroomAddEdit_event");
						changeroomAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#changeroomAddEditDialog').dialog('close'); 
							var changeroomAddEdit_event=document.getElementById("changeroomAddEdit_event");
							changeroomAddEdit_event.click();
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
		   $('#addmonkeyid').val('');
		   $('#changeroomdate').datebox('setValue', '');
		   $('#changeinarea').combobox('select','');
		   $('#changeinroom').combobox('select','');
		   $('#lhao').val('');
	  	   $('#protector').combobox('select','');
	  	   $('#yarea').combobox('select','');
		   $('#yroom').combobox('select','');
		   //$('#ylh').val('');
	  	   $('#remark').val('');
	  	   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
		   
	   }else{
		   $('#id').val(r.id);
		   $('#oldmonkeyid').val(r.monkeyid);
		   $('#addmonkeyid').val(r.monkeyid);
		   $('#changeroomdate').datebox('setValue', r.changeroomdate);
		   $('#changeinarea').combobox('select',r.changeinarea);
		   $('#changeinroom').combobox('select',r.changeinroom);
		   //$('#lhao').val(r.lhao);
	  	   $('#protector').combobox('select',r.protector);
	  	   $('#yarea').combobox('select',r.yarea);
		   $('#yroom').combobox('select',r.yroom);
		   $('#ylh').val(r.ylh);
	  	   $('#remark').val(r.remark);
	  	   $('#recorder').combobox('select',r.recorder);
		   $('#operater').combobox('select',r.operater);
	   }
	   
  }
//初始化调入区域下拉选
 function initChangeinAreaCombobox(){
 	$('#changeinarea').combobox({    
 		url : sybp()+'/areaAction_getPareaNameId.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		  
 		},
 		onSelect:function(record){
 			initChangeinRoomCombobox(record.id);  
 		}
 			
 	});
 }
 //初始化调入房间下拉选
 function initChangeinRoomCombobox(id){
 	if(id!=''&&id!=null){
 	  $('#changeinroom').combobox({    
 		url : sybp()+'/areaAction_getAllRoomIdName.action?blongarea='+id,
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
// 	    multiple:true,
 	    onLoadSuccess:function(none){
 		}
 	  });
 	}else{
 		$('#changeinroom').combobox({ });   
 	}
 }
//初始化保定人员下拉选
 function initProtectorCombobox(){
 	$('#protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#protector').combobox('',"");
 			}
 		}
 		 
 	});
 }
//初始化原区域下拉选
 function initYareaCombobox(){
 	$('#yarea').combobox({    
 		url : sybp()+'/areaAction_getPareaNameId.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		  
 		},
 		onSelect:function(record){
 			initYroomCombobox(record.id);  
 		}
 			
 	});
 }
 //初始化原房间下拉选
 function initYroomCombobox(id){
 	if(id!=''&&id!=null){
 	  $('#yroom').combobox({    
 		url : sybp()+'/areaAction_getAllRoomIdName.action?blongarea='+id,
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
// 	    multiple:true,
 	    onLoadSuccess:function(none){
 		}
 	  });
 	}else{
 		$('#yroom').combobox({ });   
 	}
 }
//初始化数据记录人员下拉选
 function initRecorderCombobox(){
 	$('#recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(recc){
 			if(recc.id=="-1"){
 				$('#recorder').combobox('',"");
 			}
 		}
 	});
 }
//初始化档案录入下拉选
function initOperaterCombobox(){
	$('#operater').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    required:true,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(rec){if(rec.id=="-1"){$('#operater').combobox('',"");}}
		 
	});
}

