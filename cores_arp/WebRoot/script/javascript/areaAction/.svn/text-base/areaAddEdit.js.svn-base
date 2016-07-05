/**显示Dialog*/
function showAreaAddEditDialog(clickName,addOrEdit,title){
	initBlongareaCombobox();
	initAnimaltypeCombobox();
	initKeeperCombobox();
	initBossCombobox();
	initReaderCombobox();
	initVeterinarianCombobox();
	/* 显示Dialog */
	document.getElementById("areaAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#areaTable').treegrid('getSelected');
		$.ajax({
    		url:sybp()+'/areaAction_toEdit.action',
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
	$('#areaAddEditDialog').dialog('setTitle',title);
	$('#areaAddEditDialog').dialog('open'); 
	document.getElementById("areaAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#areaAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/areaAction_add.action',
					type:'post',
					data:$('#areaAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#areaAddEditDialog').dialog('close'); 
						$('#aid').val(r.id);
						var areaAddEdit_event=document.getElementById("areaAddEdit_event");
						areaAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#areaAddEditDialog').dialog('close'); 
							var areaAddEdit_event=document.getElementById("areaAddEdit_event");
							areaAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				var node = $('#areaTable').treegrid('getSelected');
				if (node){
					/*$('#areaTable').treegrid('insert', {
						before: node.id,
						data: {
							id: node.id,
							areanname: node.areanname,
							_parentId:node._parentId,
							animalType:node.animalType,
							keeperName:node.keeperName,
							bossName:node.bossName,
							remarks:node.remarks,
							readerName:node.readerName,
							veterinarianName:node.veterinarianName
						}
					});*/
					$('#areaTable').treegrid('update', {
						id: node.id,
						row: {
							id: node.id,
							areanname: node.areanname,
							_parentId:node._parentId,
							animalType:node.animalType,
							keeperName:node.keeperName,
							bossName:node.bossName,
							remarks:node.remarks,
							readerName:node.readerName,
							veterinarianName:node.veterinarianName
						}
					});
				}
				$.ajax({
					url:sybp()+'/areaAction_editSave.action',
					type:'post',
					data:$('#areaAddEditForm').serialize(),
					contentType: "application/x-www-form-urlencoded; charset=utf-8",
					dataType:'json',
					//dataType:'html',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#areaAddEditDialog').dialog('close'); 
						$('#aid').val(r.id);
						var areaAddEdit_event=document.getElementById("areaAddEdit_event");
						areaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#areaAddEditDialog').dialog('close'); 
							var areaAddEdit_event=document.getElementById("areaAddEdit_event");
							areaAddEdit_event.click();
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
		   $('#areaname').val('');
		   $('#blongarea').combobox('select','');
		   $('#animaltype').combobox('select','');
//		   $('#roompinxi').val('');
	  	   $('#keeper').combobox('select','');
	  	   $('#boss').combobox('select','');
	  	   $('#reader').combobox('select','');
		   $('#veterinarian').combobox('select','');
		   $('#remarks').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#oldareaname').val(r.areaname);
		   $('#areaname').val(r.areaname);
		   if(r.blongarea==0){
			   $('#blongarea').combobox('select','');
		   }else{
			   $('#blongarea').combobox('select',r.blongarea);
		   }
		   if(r.animaltype==0){
			   $('#animaltype').combobox('select','');
		   }else{
			   $('#animaltype').combobox('select',r.animaltype);
		   }
//		   $('#roompinxi').val('');
		   if(r.keeper==0){
			   $('#keeper').combobox('select',''); 
		   }else{
			   $('#keeper').combobox('select',r.keeper); 
		   }
		   if(r.boss==0){
			   $('#boss').combobox('select','');  
		   }else{
			   $('#boss').combobox('select',r.boss);
		   }
		   if(r.reader==0){
			   $('#reader').combobox('select','');
		   }else{
			   $('#reader').combobox('select',r.reader);
		   }
	  	   if(r.veterinarian==0){
	  		 $('#veterinarian').combobox('select','');
	  	   }else{
	  		 $('#veterinarian').combobox('select',r.veterinarian);
	  	   }
		   $('#remarks').val(r.remarks);
	   }
	   
  }

//初始化所属区域下拉选
function initBlongareaCombobox(){
	$('#blongarea').combobox({    
		url : sybp()+'/areaAction_getPareaNameId.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		},
		onSelect:function(rec){
		     
		}
			
	});
}
//初始化动物类型下拉选
function initAnimaltypeCombobox(){
	$('#animaltype').combobox({    
		url : sybp()+'/areaAction_getAllAnimaltypeIdName.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
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
			if(rec.id=="-1"){$('#keeper').combobox('select',"");}
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
			if(rec.id=="-1"){$('#boss').combobox('select',"");}
		}
		 
	});
}
//初始化档案记录下拉选
function initReaderCombobox(){
	$('#reader').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		},
		onSelect:function(rec){
			if(rec.id=="-1"){
				$('#reader').combobox('select',"");
			}
		}
		 
	});
}
//初始化兽医下拉选
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
				$('#veterinarian').combobox('select',"");
			}
		}
		 
	});
}