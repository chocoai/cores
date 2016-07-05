/**显示Dialog*/
function showDeathAddEditDialog(clickName,addOrEdit,title){
	initVeterinarianCombobox();
	initDissectveterinarianCombobox();
	initPathologyCombobox();
	initBossCombobox();
	initKeeperCombobox();
	initRecorderCombobox();
	initOperaterCombobox();
	/* 显示Dialog */
	document.getElementById("deathAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#deathTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/deathAction_toEdit.action',
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
	$('#deathAddEditDialog').dialog('setTitle',title);
	$('#deathAddEditDialog').dialog('open'); 
	document.getElementById("deathAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#deathAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/deathAction_add.action',
					type:'post',
					data:$('#deathAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#deathAddEditDialog').dialog('close'); 
						$('#did').val(r.id);
						var deathAddEdit_event=document.getElementById("deathAddEdit_event");
						deathAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#deathDialog').dialog('close'); 
							var deathAddEdit_event=document.getElementById("deathAddEdit_event");
							deathAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/deathAction_editSave.action',
					type:'post',
					data:$('#deathAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#deathAddEditDialog').dialog('close'); 
						$('#did').val(r.id);
						var deathAddEdit_event=document.getElementById("deathAddEdit_event");
						deathAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#deathAddEditDialog').dialog('close'); 
							var deathAddEdit_event=document.getElementById("deathAddEdit_event");
							deathAddEdit_event.click();
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
		   $('#deathdate').datebox('setValue', '');
		   $('#adddissectdate').datebox('setValue', '');
		   $('#veterinarian').combobox('select','');
		   $('#dissectveterinarian').combobox('select','');
	  	   $('#pathology').combobox('select','');
	  	   $('#boss').combobox('select','');
	  	   $('#keeper').combobox('select','');
	  	   $('#remarks').val('');
	  	   $('#bl_remarks').val('');
	  	   $('#sc_remarks').val('');
	  	   $('#others').val('');
	  	   $('#recorder').combobox('select','');
		   $('#operater').combobox('select','');
	   }else{
		   $('#id').val(r.id);
		   $('#oldmonkeyid').val(r.monkeyid);
		   $('#addmonkeyid').val(r.monkeyid);
		   $('#deathdate').datebox('setValue', r.deathdate);
		   $('#adddissectdate').datebox('setValue', r.dissectdate);
		   $('#veterinarian').combobox('select',r.veterinarian);
		   $('#dissectveterinarian').combobox('select',r.dissectveterinarian);
	  	   $('#pathology').combobox('select',r.pathology);
	  	   $('#boss').combobox('select',r.boss);
	  	   $('#keeper').combobox('select',r.keeper);
	  	   $('#remarks').val(r.remarks);
	  	   $('#bl_remarks').val(r.bl_remarks);
	  	   $('#sc_remarks').val(r.sc_remarks);
	  	   $('#others').val(r.others);
	  	   $('#recorder').combobox('select',r.recorder);
		   $('#operater').combobox('select',r.operater);
	   }
	   
  }
//初始化房间兽医下拉选
 function initVeterinarianCombobox(){
 	$('#veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#veterinarian').combobox('',"");
 			}
 		}
 		 
 	});
 }
//初始化解剖兽医下拉选
 function initDissectveterinarianCombobox(){
 	$('#dissectveterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#dissectveterinarian').combobox('',"");
 			}
 		}
 		 
 	});
 }
//初始化病理人员下拉选
 function initPathologyCombobox(){
 	$('#pathology').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#pathology').combobox('select',"");
 			}
 		}
 		 
 	});
 }
//初始化主管下拉选
 function initBossCombobox(){
 	$('#boss').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#boss').combobox();
 			}
 		}
 		 
 	});
 }
//初始化饲养员下拉选
 function initKeeperCombobox(){
 	$('#keeper').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		},onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#keeper').combobox('select',"");
 			}
 		}
 		 
 	});
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
 		onSelect:function(rec){
 			if(rec.id=="-1"){
 				$('#recorder').combobox('select',"");
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
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(rec){
			if(rec.id=="-1"){
				$('#operater').combobox('select',"");
			}
		}
		 
	});
}

