/**显示Dialog*/
function showStudyMultiTypeSelectDialog(clickName,tiNo,studyTypeCodes){
	/*课题类别选择Dialog*/
	document.getElementById("studyTypeMultiDialog2").style.display="block";
	$('#studyTypeMultiDialog').dialog('open'); 
	
	//初始化  课题类别表
	initStudyTypeMultiTable();
	
	//加载 课题类别表
	loadStudyTypeMulitTable(tiNo,studyTypeCodes);
	
	this.myFunction = clickName;
}

/**加载 课题类别表*/
function loadStudyTypeMulitTable(tiNo,studyTypeCodes){
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
					$('#studyTypeMultiTable').datagrid('loadData',r);
					$('#studyTypeMultiTable').datagrid('uncheckAll');
					//if(studyTypeCodes.length > 0){
					//	for(var i = 0 ;i< studyTypeCodes.length;i++){
					//		$('#studyTypeMultiTable').datagrid('selectRecord',studyTypeCodes[i]);
					//	}
					//}
				}
			}
		});
	}
}


//初始化  课题类别表
function initStudyTypeMultiTable(){
	var rows = $('#studyTypeMultiTable').datagrid('getRows');
	if(rows.length<1){
		$('#studyTypeMultiTable').datagrid({
			//url:sybp()+'/tblStudyItemAction_loadStudyTypeList.action',
			idField:'studyTypeCode',
			singleSelect:false,
			rowStyler: function(index,row){
				return "color:#000000;";
			}
		});
	}
}
/**确定*/
function onConfirmButton_multiSelect(){
	var rows = $('#studyTypeMultiTable').datagrid('getSelections');
	
	if(rows.length > 0){
		myFunction(rows);
		$('#studyTypeMultiDialog').dialog('close'); 
	}else if(rows.length == 0){
		$.messager.alert('警告','请先选择项目类型');
	}
}

