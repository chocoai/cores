/**显示Dialog*/
function showStudyTypeSelectDialog(clickName){
	/*课题类别选择Dialog*/
	document.getElementById("studyTypeDialog2").style.display="block";
	$('#studyTypeDialog').dialog('open'); 
	document.getElementById("studyTypeSelect_event").href="javascript:"+clickName+"();";
}
/**供试品类型 选择事件*/
function onTISelect(record){
	if(record){
		$.ajax({
			url:sybp()+'/dictStudyTypeAction_loadPartList.action',
			type:'post',
			data:{
				tiCode:record.id
			},
			dataType:'json',
			success:function(r){
				if(r){
					$('#studyTypeTable').datagrid('loadData',r);
				}
			}
		});
	}
}


/**保存*/
function onDialogSelectButton(){
	var rows = $('#studyTypeTable').datagrid('getSelections');
	if(rows.length == 1){
		$('#studyTypeCode').val(rows[0].studyTypeCode);
		$('#addEditStudyName').val(rows[0].studyName);
		$('#studyTypeDialog').dialog('close'); 
	}else if(rows.length == 0){
		parent.showMessager(2,'请选择数据',true,5000);
	}
}
function onTIDblClickRow(rowIndex, rowData){
	$('#studyTypeCode').val(rowData.studyTypeCode);
	$('#addEditStudyName').val(rowData.studyName);
	$('#studyTypeDialog').dialog('close'); 
}

