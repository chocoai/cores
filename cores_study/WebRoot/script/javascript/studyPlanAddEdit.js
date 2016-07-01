/**显示Dialog*/
function showStudyPlanAddEditDialog(clickName,addOrEdit,title){
	/*签名Dialog*/
	document.getElementById("studyPlanAddEditDialog2").style.display="block";
	$('#addOrEdit').val(addOrEdit);
	$('#studyPlanAddEditDialog').dialog('setTitle',title);
	$('#studyPlanAddEditDialog').dialog('open'); 
	document.getElementById("studyPlanAddEdit_event").href="javascript:"+clickName+"();";
}
/**保存*/
function onDialogSaveButton(){
	if( $('#studyPlanAddEditForm').form('validate') ){
		$('#saveDialogButton').linkbutton('disable');
		if($('#addOrEdit').val() == 'add'){
			$.ajax({
				url:sybp()+'/tblStudyPlanAction_addSave.action',
				type:'post',
				data:$('#studyPlanAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#newStudyNo').val(r.msg);
						$('#studyPlanAddEditDialog').dialog('close'); 
						var studyPlanAddEdit_event=document.getElementById("studyPlanAddEdit_event");
						studyPlanAddEdit_event.click();
						parent.setTopStudyNoSD(r.msg,r.name);
						
					}else{
						$.messager.alert('提示',r.msg);
					}
				}
			});
		}else{
			$.ajax({
				url:sybp()+'/tblStudyPlanAction_editSave.action',
				type:'post',
				data:$('#studyPlanAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#newStudyNo').val(r.msg);
						$('#newsd').val(r.obj);
						$('#studyPlanAddEditDialog').dialog('close'); 
						var studyPlanAddEdit_event=document.getElementById("studyPlanAddEdit_event");
						studyPlanAddEdit_event.click();
					}else{
						$.messager.alert('提示',r.msg,'info');
					}
				}
			});
		}
		
	}
}
/**动物类别的选择事件*/
function onAnimalTypeSelect(record){
	//animalTypeId
	//getAnimalStrain
	if(record.id != -1){
		$('#animalType').combobox('select',record.text);
		$.ajax({
			url:sybp()+'/tblStudyPlanAction_getAnimalStrain.action',
			type:'post',
			data:{
			animalTypeId:record.id
		},
		dataType:'json',
		success:function(r){
			$('#animalStrain').combobox('setValue','');
			$('#animalStrain').combobox('loadData',r);
		}
		});
	}else{
		$('#animalType').combobox('setValue','');
		$('#animalStrain').combobox('setValue','');
		$('#animalStrain').combobox('loadData',[]);
	}
}
