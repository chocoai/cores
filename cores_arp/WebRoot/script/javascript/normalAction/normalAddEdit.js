function onAddNormalButton() {
	showNormalAddEditDialog('afterAddDialog', 'add', '添加常规检疫记录');
}

// 添加Dialog后事件
function afterAddDialog() {
	parent.showMessager(1, '添加成功', true, 5000);
	$('#normalTable').datagrid('reload');
}
// wan 编辑按钮事件
function onEditNormalButton() {
	var row = $('#normalTable').datagrid('getSelected');
	if (row != null) {
		showNormalAddEditDialog('afterEditDialog', 'edit', '编辑常规检疫记录');
	} else {
		$.messager.alert('提示', '请选择编辑的常规检疫记录!');
	}
}
// 编辑后事件
function afterEditDialog() {
	parent.showMessager(1, '编辑成功', true, 5000);
	$('#normalTable').datagrid('reload');
}

/** 显示Dialog */
function showNormalAddEditDialog(clickName, addOrEdit, title) {
	initVeterinarianCombobox();
	initRecorderCombobox();
	initProtectorCombobox();
	/* 显示Dialog */
	document.getElementById("normalAddEditDialog2").style.display = "block";
	if (addOrEdit == "add") {
		fullEditInformation("");
	} else if (addOrEdit == "edit") {
		var row = $('#normalTable').datagrid('getSelected');
		$.ajax({
			url : sybp() + '/normalAction_toEdit.action',
			type : 'post',
			cache : false,
			data : {
				id : row.id
			},
			dataType : 'json',
			success : function(r) {
				if (r) {
					fullEditInformation(r);
				}
			}
		});

	}

	$('#addOrEditNormal').val(addOrEdit);
	$('#normalAddEditDialog').dialog('setTitle', title);
	$('#normalAddEditDialog').dialog('open');
	document.getElementById("normalAddEdit_event").href = "javascript:"
			+ clickName + "();";
}
/** 确定（保存） */
function onDialogSaveNormalButton() {
	if( $('#normalAddEditForm').form('validate') ){
		$('#saveNormalDialogButton').linkbutton('disable');
		if($('#addOrEditNormal').val() == 'add'){
			$.ajax({
				url:sybp()+'/normalAction_add.action',
				type:'post',
				data:$('#normalAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
				//$('#saveNormalDialogButton').linkbutton('enable');
				if(r && r.success){
					$('#normalAddEditDialog').dialog('close');
					$('#normal').datagrid('reload');
					$('#id').val(r.id);
					var normalAddEdit_event=document.getElementById("normalAddEdit_event");
					normalAddEdit_event.click();
				}else{
					$.messager.alert('提示','请检查录入的数据');
					$('#saveNormalDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#normalAddEditDialog').dialog('close'); 
						var normalAddEdit_event=document.getElementById("normalAddEdit_event");
						normalAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
					}
				}
			 }
			});
		}else{
			$.ajax({
				url:sybp()+'/normalAction_editSave.action',
				type:'post',
				data:$('#normalAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
				$('#saveNormalDialogButton').linkbutton('enable');
				if(r && r.success){
					$('#normalAddEditDialog').dialog('close'); 
					$('#id').val(r.id);
					var normalAddEdit_event=document.getElementById("normalAddEdit_event");
					normalAddEdit_event.click();
				}else{
					$.messager.alert('提示','请检查录入的数据');
					$('#saveNormalDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#normalAddEditDialog').dialog('close'); 
						$('#normal').datagrid('reload');
						var normalAddEdit_event=document.getElementById("normalAddEdit_event");
						normalAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
					}
				}
			 }
			});
		}
		
	}

}

// 编辑客户时填充选中的客户信息到编辑界面
function fullEditInformation(r) {
	if (r == "") {
		$('#id').val('');
		$('#normal_monkeyid').val('');
		$('#normal_cdate').datebox('setValue', '');
		$('#veterinarian').combobox('select', '');
		$('#protector').combobox('select', '');
		$('#recorder').combobox('select', '');
		$('#normal_remark').val('');
	} else {
		$('#id').val(r.id);
		$('#normal_monkeyid').val(r.normallist);
		$('#normal_cdate').datebox('setValue', r.checkdate);
		$('#normal_veterinarian').combobox('select', r.veterinarian);
		$('#normal_protector').combobox('select', r.protector);
		$('#normal_recorder').combobox('select', r.recorder);
		$('#normal_remark').val(r.remark);
	}

}
function initVeterinarianCombobox() {
	$('#normal_veterinarian').combobox({
		url : sybp() + '/employeeAction_loadListEmployee.action',
		valueField : 'id',
		textField : 'text',
		editable : false,
		onLoadSuccess : function(none) {

		}

	});
}
function initProtectorCombobox() {
	$('#normal_protector').combobox({
		url : sybp() + '/employeeAction_loadListEmployee.action',
		valueField : 'id',
		textField : 'text',
		editable : false,
		onLoadSuccess : function(none) {

		}

	});
}
function initRecorderCombobox() {
	$('#normal_recorder').combobox({
		url : sybp() + '/employeeAction_loadListEmployee.action',
		valueField : 'id',
		textField : 'text',
		editable : false,
		onLoadSuccess : function(none) {

		}

	});
}
