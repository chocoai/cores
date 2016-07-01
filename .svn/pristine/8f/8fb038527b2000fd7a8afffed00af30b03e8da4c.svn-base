/**显示Dialog*/
function showStudyTypeSelectDialog(clickName,tiNo){
	/*课题类别选择Dialog*/
	document.getElementById("studyTypeDialog2").style.display="block";
	$('#studyTypeDialog').dialog('open'); 
	document.getElementById("studyTypeSelect_event").href="javascript:"+clickName+"();";

//	//初始化供试品下拉框
//	initTestItemCombobox();
	
	//初始化  课题类别表
	initStudyTypeTable();
	
	//加载 课题类别表
	loadStudyTypeTable(tiNo);
}

////初始化供试品下拉框
//function initTestItemCombobox(){
//	$('#studyType_testItem').combobox({
//		url:sybp()+'/tblStudyItemAction_loadTestItemList.action',
//		valueField:'id',
//		textField:'text',
//		onSelect: function(record){    
//	    	onTISelect(record);
//		}
//	});
//}
/**加载 课题类别表*/
function loadStudyTypeTable(tiNo){
	if(tiNo){
		$.ajax({
			url:sybp()+'/tblStudyItemAction_loadStudyTypePartList.action',
			type:'post',
			data:{
				tiNo:tiNo
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

/**供试品类型 选择事件*/
function onTISelect(record){
	if(record){
		$.ajax({
			url:sybp()+'/tblStudyItemAction_loadStudyTypePartList.action',
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
//初始化  课题类别表
function initStudyTypeTable(){
	var rows = $('#studyTypeTable').datagrid('getRows');
	if(rows.length<1){
		$('#studyTypeTable').datagrid({
			//url:sybp()+'/tblStudyItemAction_loadStudyTypeList.action',
			singleSelect:true,
			onDblClickRow:function(rowIndex, rowData){
				onTIDblClickRow(rowIndex, rowData);
			}
		});
	}
}
/**确定*/
function onStudyTypeDialogButton(){
	var rows = $('#studyTypeTable').datagrid('getSelections');
	if(rows.length == 1){
		$('#studyType_studyTypeCode').val(rows[0].studyTypeCode);
		$('#studyType_studyName').val(rows[0].studyName);
		$('#studyTypeDialog').dialog('close'); 
		var studyTypeSelect_event=document.getElementById("studyTypeSelect_event");
		studyTypeSelect_event.click();
	}else if(rows.length == 0){
		$.messager.alert('警告','请先选择项目类型');
	}
}
/**datagrid 双击事件*/
function onTIDblClickRow(rowIndex, rowData){
	$('#studyType_studyTypeCode').val(rowData.studyTypeCode);
	$('#studyType_studyName').val(rowData.studyName);
	$('#studyTypeDialog').dialog('close');
	var studyTypeSelect_event=document.getElementById("studyTypeSelect_event");
	studyTypeSelect_event.click();
}

