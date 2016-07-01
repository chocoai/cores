var studyitemEditRsn;//供试品编辑原因

/**显示Dialog*/
function showStudyItemAddEditDialog(clickName,addOrEdit,title){
	document.getElementById('noFinishDateCheckbox').checked = false;
		$('#studyItem_finishDate').datebox('enable');
	/*签名Dialog*/
	document.getElementById("studyItemAddEditDialog2").style.display="block";
	$('#studyitemAddOrEdit').val(addOrEdit);
		//初始化 animaltype  下拉框
		initAnimalTypeCombobox();
		//初始化 SD推荐人  下拉框
		initSDManagerCombobox();
	   if(addOrEdit == 'edit' || addOrEdit == 'add'){
		   $('#readonlyStudyItemAddEdit').css('display','none');
	   }
	   if(addOrEdit == 'edit' || addOrEdit == 'select'){
		   var row = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
		   //根据Id号查询
	       $.ajax({
				url:sybp()+'/tblStudyItemAction_selectStudyItem.action',
				type:'post',
				data:{
				  id:row.id
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						//填充数据
						studyItemFullEditData(r);
					}else{
						 parent.showMessager(2,'与数据库交互错误',true,5000);
					}
				}
	       });
	     if(addOrEdit == 'select'){
	    	 $('#studyItemSaveButton').linkbutton('disable');
	     }else{
	    	 $('#studyItemSaveButton').linkbutton('enable');
	     }
	   }
	   if(addOrEdit == 'edit1' ||addOrEdit == 'select1'){
		   var row = $('#tblStudyItemTable').datagrid('getSelected');
		   $.ajax({
				url:sybp()+'/tblStudyItemAction_selectStudyItem.action',
				type:'post',
				data:{
				  id:row.id
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
					//填充数据
					 $('#tblstudyItemcontractCode').val(r.contractCode);
					 $('#studyItem_tiNo').val(r.tiNo);
					 studyItemFullEditData(r);
					 
					}else{
						 parent.showMessager(3,'与数据库交互异常',true,5000);
					}
				}
			 });
		   if(addOrEdit == 'select1'){
		    	 $('#studyItemSaveButton').linkbutton('disable');
		     }else{
		    	 $('#readonlyStudyItemAddEdit').css('display','none');
		    	 $('#studyItemSaveButton').linkbutton('enable');
		     }
		   
	   }
	$('#studyItemAddEditDialog').dialog('setTitle',title);
	$('#studyItemAddEditDialog').dialog('open'); 
	document.getElementById("studyItemAddEdit_event").href="javascript:"+clickName+"();";
}

/**不指定完成日期
 * @return
 */
function onNoFinishDateCheckBox(){
	if(document.getElementById('noFinishDateCheckbox').checked){
		$('#studyItem_finishDate').datebox('setValue','');
		$('#studyItem_finishDate').datebox('disable');
	}else{
		$('#studyItem_finishDate').datebox('enable');
	}
}

//填充数据(或清空数据)
function studyItemFullEditData(r){
    if(r){
    	$('#studyItemId').val(r.id);
        $('#studyItem_tiNo').val(r.tiNo);
        $('#oldStudyNo').val(r.studyNo);
        $('#studyItem_studyNo').val(r.studyNo);
        $('#studyItem_studyTypeCode').val(r.studyTypeCode);
        $('#studyItem_studyName').val(r.studyName);
        //$('#glpFlag0').val(r.glpFlag);
        if(r.glpFlag == "1" ){
    		document.getElementById('glpFlag1').click();
    	}else{
    		document.getElementById('glpFlag0').click();
    	}
       
        
        $('#animalAge').val(r.animalAge);
        if(r.numMale != 0){
        	  $('#studyItem_numMale').val(r.numMale);
        }
        if(r.numFemale != 0){
        $('#studyItem_numFemale').val(r.numFemale);
        }
        $('#studyItem_sdManager').combobox('select',r.sdManager);
        $('#studyItem_remark').val(r.remark);
        if(r.animalHave == "1"){
        	 $('#animalType').combobox({required:true});
        }else{
        	 $('#animalType').combobox({required:false});
        }
        $('#animalType').combobox('select',r.animalType);
        $('#animalStrain').combobox('select',r.animalStrain);
        $('#studyItem_finishDate').datebox('setValue', r.finishDate);
        
        $('#contractState_studyitemDialog').val(r.contractState);
        $('#state_studyitemDialog').val(r.state);
    }else{
    	$('#studyItemId').val('');
        $('#studyItem_tiNo').val('');
        $('#oldStudyNo').val('');
        $('#studyItem_studyTypeCode').val('');
        $('#studyItem_studyName').val('');
        document.getElementById('glpFlag1').checked = true;
        $('#animalType').combobox('clear');
        $('#animalStrain').combobox('clear');
        $('#animalAge').val('');
        $('#studyItem_numMale').val('');
        $('#studyItem_numFemale').val('');
        $('#studyItem_sdManager').combobox('clear');
        $('#studyItem_remark').val('');
        $('#studyItem_finishDate').val('');
        
        $('#contractState_studyitemDialog').val('');
        $('#state_studyitemDialog').val('');
    }
}
function checkDate(value){
	var date = value;
    var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);

    if (result == null)
        return false;
    var d = new Date(result[1], result[3] - 1, result[4]);
    return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);
}

/**确定（保存）*/
function onStudyItemSaveButton(){
	if( $('#studyItemAddEditForm').form('validate') ){
		if(document.getElementById('noFinishDateCheckbox').checked){
			
		}else{
			
			if(checkDate($('#studyItem_finishDate').datebox('getText'))){
				
			}else{
				$.messager.alert('警告','请录入正确的要求完成日期格式！');
				return;
			}
		}
		$('#studyItemSaveButton').linkbutton('disable');
		if($('#studyitemAddOrEdit').val() == 'add'){
			$.ajax({
				url:sybp()+'/tblStudyItemAction_addSave.action',
				type:'post',
				data:$('#studyItemAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					$('#studyItemSaveButton').linkbutton('enable');
					if(r && r.success){
						$('#selectrowId').val("s"+r.id);
						$('#studyItemAddEditDialog').dialog('close'); 
						var studyItemAddEdit_event=document.getElementById("studyItemAddEdit_event");
						studyItemAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
					}
				}
			});
		}else{
			var contractState_studyitemDialog = $('#contractState_studyitemDialog').val();
			var state_studyitemDialog = $('#state_studyitemDialog').val();
			if(contractState_studyitemDialog == 2 && state_studyitemDialog == 1){
				$.messager.prompt('提示信息', '请填写委托项目编辑原因：', function(r){
	    			if (r){
	    				if(r != ""){
	    					studyitemEditRsn = r;
		    			    qm_showQianmingDialog('afterSignStudyItemEdit');
	    			    }else{
	    			    	$('#studyItemSaveButton').linkbutton('enable');
	    			    }
	    			}else{
	    				$('#studyItemSaveButton').linkbutton('enable');
	    			}
	    		});
			}else{
				$.ajax({
					url:sybp()+'/tblStudyItemAction_editSave.action',
					type:'post',
					data:$('#studyItemAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#studyItemSaveButton').linkbutton('enable');
					if(r && r.success){
						$('#selectrowId').val(r.id);
						$('#studyItemAddEditDialog').dialog('close'); 
						var studyItemAddEdit_event=document.getElementById("studyItemAddEdit_event");
						studyItemAddEdit_event.click();
					}else{
						if(r.msg && r.msg != ""){
							$.messager.alert('提示',r.msg);
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				}
				});
			}
		}
		
	}
}


/**合同再编辑状态下 委托项目编辑签字后事件
 * @return
 */
function afterSignStudyItemEdit(){
	$.ajax({
		url:sybp()+'/tblStudyItemAction_editSave.action?studyitemEditRsn='+studyitemEditRsn,
		type:'post',
		data:$('#studyItemAddEditForm').serialize(),
		dataType:'json',
		success:function(r){
		$('#studyItemSaveButton').linkbutton('enable');
		if(r && r.success){
			$('#selectrowId').val(r.id);
			$('#studyItemAddEditDialog').dialog('close'); 
			var studyItemAddEdit_event=document.getElementById("studyItemAddEdit_event");
			studyItemAddEdit_event.click();
		}else{
			if(r.msg && r.msg != ""){
				$.messager.alert('提示',r.msg);
			}else{
				$.messager.alert('提示','请检查录入的数据');
			}
		}
	}
	});
	
}

/**是否GLP单选框事件*/
function onGlpFlagRadio(){
	if(document.getElementById('glpFlag0').checked){
		document.getElementById('glpFlag1').click();
	}else{
		document.getElementById('glpFlag0').click();
	}
}

/**选择项目类型onclick*/
function onStudyTypeButton(){
	document.getElementById("studyItem_studyName").blur();
	var tiNo;
	if(document.getElementById('testItemAndStudyItemTreeTable')){
		var rowData = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
		if(rowData && rowData._parentId){
			var rowData2 = $('#testItemAndStudyItemTreeTable').treegrid('getParent',rowData.id);
			if(rowData2 && rowData2.tiNo){
				tiNo = rowData2.tiNo;
			}
		}else if(rowData && rowData.tiNo){
			tiNo = rowData.tiNo;
		}
		
	}else if(document.getElementById('testItemTable')){
		var rowData = $('#testItemTable').datagrid('getSelected');
		if(rowData && rowData.tiNo){
				tiNo = rowData.tiNo;
		}
	}
	
	showStudyTypeSelectDialog('afterOnStudyTypeButton',tiNo);
}
/**选择项目类型onclick 后事件*/
function afterOnStudyTypeButton(){
	$('#studyItem_studyTypeCode').val($('#studyType_studyTypeCode').val());
	$('#studyItem_studyName').val($('#studyType_studyName').val());
	$('#studyItem_studyName').validatebox('validate');
}
//初始化 SD推荐人  下拉框
function initSDManagerCombobox(){
	//loadStudyTypePartList
	$('#studyItem_sdManager').combobox({
		url:sybp()+'/tblStudyItemAction_loadSDManagerList.action',
		valueField:'id',
		textField:'text',
		onSelect:function(record){
			if(record.id == -1){
				$('#studyItem_sdManager').combobox('clear');
			}
		}
	});
}
//初始化 animaltype  下拉框
function initAnimalTypeCombobox(){
	//loadStudyTypePartList
	$('#animalType').combobox({
		url:sybp()+'/tblStudyItemAction_aniamlType.action',
		valueField:'id',
		textField:'text',
		onSelect:function(record){
		    onAnimalTypeSelectEdit(record);
	 	}
	});
}

/**动物类别的选择事件*/
function onAnimalTypeSelectEdit(record){
	if(record.id == -1){
		$('#animalType').combobox('clear');
	}else{
		$('#animalType').combobox('select',record.text);
	}
	$.ajax({
		url:sybp()+'/tblStudyItemAction_getAnimalStrain.action',
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
}

