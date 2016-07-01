/**显示Dialog*/
function showAnimalCodeAddEditDialog(clickName,addOrEdit_animalCodeAddEdit,row){
	/*签名Dialog*/
	document.getElementById("animalCodeAddEditDialog2").style.display="block";
	$('#animalCodeAddEditDialog').dialog('open'); 
	$('#addOrEdit_animalCodeAddEdit').val(addOrEdit_animalCodeAddEdit);
	if(addOrEdit_animalCodeAddEdit == 'add'){
		$('#animalCodeAddEditDialog').dialog('setTitle','添加动物编号'); 
	}else{
		$('#animalCodeAddEditDialog').dialog('setTitle','编辑动物编号'); 
	}
	//剂量组
	initdosageNumCombobox();
	//性别
	initGenderCombobox();
	
	//回调函数
	this.myComeback = clickName;
	//填充数据(或清空数据)
	animalCodeAddEditFormFullEditData(row);
	
	//
	initCheckBox(addOrEdit_animalCodeAddEdit);
}
function initCheckBox(addOrEdit_animalCodeAddEdit){
	if(addOrEdit_animalCodeAddEdit == 'add'){
		//显示连续录入复选框
		document.getElementById("continueAddCheckBox").style.display="";
		document.getElementById("continueAddA").style.display="";
		document.getElementById("continueAddCheckBox").checked = false;
	}else{
		//显示连续录入复选框
		document.getElementById("continueAddCheckBox").style.display="none";
		document.getElementById("continueAddA").style.display="none";
	}
}
//剂量组
function initdosageNumCombobox(){
	var studyNoPara= encodeURIComponent($('#studyNoPara').val());
	$('#dosageNum').combobox({
		url:sybp()+'/tblDoseSettingAction_dosageNum.action?studyNoPara='+studyNoPara,
		valueField:'id',
		textField:'text',
		panelHeight:150,
		editable:false
	});
}
//性别
function initGenderCombobox(){
	var str = '[{"id":1,"text":"♂"},{"id":2,"text":"♀"}]';
	var json = eval('(' + str + ')');
	//var json = str.parseJSON(); //由JSON字符串转换为JSON对象
   // var json = JSON.parse(str); //由JSON字符串转换为JSON对象
	$('#gender').combobox({
		valueField:'id',
		textField:'text',
		panelHeight:50,
		data:json,
		editable:false
	});
}

//填充数据(或清空数据)
function animalCodeAddEditFormFullEditData(row){
    document.animalCodeAddEditForm.reset();//
    
    var studyNoPara= encodeURIComponent($('#studyNoPara').val());
    $('#studyNo').val(studyNoPara);
    var addOrEdit_animalCodeAddEdit = $('#addOrEdit_animalCodeAddEdit').val();
    if(studyNoPara && addOrEdit_animalCodeAddEdit == 'add'){
    	if(row){
    		$('#dosageNum').combobox('select',row.dosageNum);
        	$('#radio'+row.gender).click();
        	$('#animalCode').focus();
        	
    	}
    }else {
    	$('#codeId').val(row.id);
    	$('#dosageNum').combobox('select',row.dosageNum);
    	$('#radio'+row.gender).click();
    	$('#animalCode').val(row.animalCode);
    }
}






/**确定（保存）检查*/
function onAnimalCodeAddEditDialogSaveButton(){
	
	var isValid = $('#animalCodeAddEditForm').form('validate');
	if(isValid){
		submitAnimalCodeAddEditForm();
	}
	
	
}

/**
 * form 提交
 * @return
 */
function submitAnimalCodeAddEditForm(){
	var addOrEdit_animalCodeAddEdit = $('#addOrEdit_animalCodeAddEdit').val();
	if(addOrEdit_animalCodeAddEdit == 'add') {
		$.ajax({    
			url:sybp()+'/tblDoseSettingAction_saveAnimalCode.action',
			type:'post',
			dataType:'json',
			data:$('#animalCodeAddEditForm').serialize() ,
			success:function(r){  
				if(r && r.success == 'true'){
					myComeback(r.msg);
					//parent.parent.showMessager(1,"剂量组添加成功！",true,5000);
					if(document.getElementById("continueAddCheckBox").checked){
						//初始化
						animalCodeAddEditFormFullEditData(r.next);
					}else{
						$('#animalCodeAddEditDialog').dialog('close'); 
					}
				}else if(r && r.msg){
					$.messager.alert('警告',r.msg);     
				}else{
					$.messager.alert('警告','与服务器交互错误');     
				}
			} 
		});    
	}else{
		$.ajax({    
			url:sybp()+'/tblDoseSettingAction_editAnimalCode.action',
			type:'post',
			dataType:'json',
			data:$('#animalCodeAddEditForm').serialize() ,
			success:function(r){   
				if(r && r.success == 'true'){
					$('#animalCodeAddEditDialog').dialog('close');
					myComeback();
				}else if(r && r.msg){
					$.messager.alert('警告',r.msg);     
				}else{
					$.messager.alert('警告','与服务器交互错误');     
				}
			} 
		}); 
	}
}

