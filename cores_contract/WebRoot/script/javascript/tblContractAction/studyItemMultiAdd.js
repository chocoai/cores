/**显示Dialog*/
function showStudyItemMultiAddDialog(clickName,title){
	document.getElementById("studyItemMultiDialog2").style.display="block";
	//初始化studyItemTable_multi
	initStudyItemTable_multi();
	//初始化 animaltype  下拉框
	initAnimalTypeCombobox_multi();
	//初始化 SD推荐人  下拉框
	initSDManagerCombobox_multi();
	$('#studyItemMultiDialog').dialog('setTitle',title);
	$('#studyItemMultiDialog').dialog('open'); 
	this.myAddMultiFunction =clickName;
}
/***
 * 初始化studyItemTable_multi
 * @return
 */
function initStudyItemTable_multi(){
	$('#studyItemTable_multi').datagrid({
		width:560,
		height:150,
		singleSelect:true, //不支持多选
		fitColumns:false,
		nowarp:  false,//单元格里自动换行
		striped:true,
		columns:[[
			{field:'studyNo',title:'项目编号',width:140,halign:'center',height:12,editor:{type:'validatebox',options:{required : true,validType:'maxLength[50]'}}},    
		    {field:'studyName',title:'项目类型',width:180,halign:'center',height:12,editor:{type:'validatebox',options:{required : true,validType:'maxLength[200]'}}},    
		    {field:'studyTypeCode',hidden:true},   
            { field: 'animalTypeCombobox', title: '动物种类', width: 80,
		    	align: 'center', editor: { type: 'combobox', options: { url:sybp()+'/tblStudyItemAction_aniamlType.action',
		    	valueField:'text',
				textField:'text' ,
				//required : true,
				onSelect:function(record){
				    	if(record.id == '-1'){
				    		$(this).combobox('clear');
				    	}
			 	}
		     } } },
		    {field:'animalHave',hidden:true}, 
		    {field:'finishDate',title:'要求完成日期',width:100,align: 'center',
		    	 editor: { type: 'datebox',options: {required:true}}},
		    {field:'delect',title:'',width:22,halign:'center',height:12,
		    	formatter : function(value,rowData,rowIndex){
		    	  return "<img style='vertical-align: bottom;' src='style/images/delete.gif' />";
		        }
		    },    
		   
		]],
		rowStyler:function(){
			return "color:#000000;";
		},
		onAfterEdit : function(rowIndex,rowData,changes){
			
		},
		onClickRow:function(rowIndex, rowData){
			$.messager.confirm('确认对话框', '确定要删除此项目吗？', function(r){
				if (r){
					$('#studyItemTable_multi').datagrid('deleteRow',rowIndex);
				}
			});


		}
	});

	$('#studyItemTable_multi').datagrid('loadData',{total:0,rows:[]}); 
	$('#studyTypeMultiTable').datagrid('unselectAll'); 
	//
}
//初始化 animaltype  下拉框
function initAnimalTypeCombobox_multi(){
	$('#animalType_multi').combobox({
		url:sybp()+'/tblStudyItemAction_aniamlType.action',
		valueField:'id',
		textField:'text',
		onSelect:function(record){
	 		onAnimalTypeSelect(record);
	 	}
	});
}

/**确定（保存）*/
function studyItemSaveButton_multi(){
	
	$('#studyItem_tiNo_multi').val(tiNo);
	
	var dataRows = $('#studyItemTable_multi').datagrid('getRows');
	if(dataRows.length > 0){
		
		if( $('#studyItemMultiAddForm').form('validate') ){
			
			for(i=0;i<dataRows.length;i++){
				$('#studyItemTable_multi').datagrid('endEdit',i)
			}
			$('#studyItemSaveButton_multi').linkbutton('disable');
			// 
			var studyNos = new Array();
			var studyNames = new Array();
			var studyTypeCodes = new Array();
			var animaltypes = new Array();
			var finishDate = new Array();
			for(var i = 0 ;i < dataRows.length;i++){
				studyNos.push(dataRows[i].studyNo);
				studyNames.push(dataRows[i].studyName);
				studyTypeCodes.push(dataRows[i].studyTypeCode);
				if(dataRows[i].animalTypeCombobox != "&nbsp;" && dataRows[i].animalTypeCombobox != ""  ){
					animaltypes.push( encodeURIComponent(dataRows[i].animalTypeCombobox));
				}else{
					animaltypes.push("-1");
				}
				if( dataRows[i].finishDate != ""  ){
					finishDate.push( dataRows[i].finishDate);
				}else{
					finishDate.push("-1");
				}
				
			}
			$.ajax({
				url:sybp()+'/tblStudyItemAction_multiAdd.action?studyNos='
					+studyNos+'&studyNames='+ encodeURIComponent(studyNames)+'&studyTypeCodes='+studyTypeCodes+'&animaltypes='+animaltypes
					+'&finishDates='+finishDate,
				type:'post',
				data:$('#studyItemMultiAddForm').serialize(),
				dataType:'json',
				success:function(r){
				$('#studyItemSaveButton_multi').linkbutton('enable');
				if(r && r.success){
					$('#selectrowId').val("s"+r.id);
					$('#studyItemMultiDialog').dialog('close'); 
					myAddMultiFunction();
				}else{
					$.messager.alert('提示',r.msg);
					for(var i = 0;i<dataRows.length;i++){
						$('#studyItemTable_multi').datagrid('beginEdit',i);
					}
				}
			}
			});
		}
	}else{
		$.messager.alert('提示','请选择项目类型和填写编号');
	}
}
/**是否GLP单选框事件*/
function onGlpFlagRadio_multi(){
	if(document.getElementById('glpFlag0_multi').checked){
		document.getElementById('glpFlag1_multi').click();
	}else{
		document.getElementById('glpFlag0_multi').click();
	}
}
/**
 * 选择项目类型(多个)
 * @return
 */
function multiSelectStudyType(){
	this.tiNo;
	this.hasLength = 0;
	if(document.getElementById('testItemAndStudyItemTreeTable')){
		var rowData = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
		if(rowData && rowData._parentId){
			var rowData2 = $('#testItemAndStudyItemTreeTable').treegrid('getParent',rowData.id);
			if(rowData2 && rowData2.tiNo){
				tiNo = rowData2.tiNo;
				hasLength = $('#testItemAndStudyItemTreeTable').treegrid('getChildren',rowData2.id).length;
			}
		}else if(rowData && rowData.tiNo){
			tiNo = rowData.tiNo;
			hasLength = $('#testItemAndStudyItemTreeTable').treegrid('getChildren',rowData.id).length;
		}
		
	}else if(document.getElementById('testItemTable')){
		var rowData = $('#testItemTable').datagrid('getSelected');
		if(rowData && rowData.tiNo){
				tiNo = rowData.tiNo;
		}
	}
	
	var studyTypeCodes =new Array();
	var dataRows = $('#studyItemTable_multi').datagrid('getRows');
	if(dataRows.length>0){
		for(var i = 0;i<dataRows.length;i++){
			studyTypeCodes.push(dataRows[i].studyTypeCode);
		}
	}
	
	showStudyMultiTypeSelectDialog(callback_multiSelectStudyType,tiNo,studyTypeCodes);
}
/**
 * 回调函数(选择项目类型)
 * @param haha
 * @return
 */
function callback_multiSelectStudyType(rows){
	if(rows && rows.length){
		var ary = new Array();
		for(var i = 0; i < rows.length ; i++ ){
			ary = ary.concat(rows[i].studyTypeCode);
		}
		var getSelections = ary.join(",");
		var  testRow = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
		//$('#studyItemTable_multi').datagrid({
		//	url : sybp()+'/tblStudyItemAction_studyItemTablemultiLoadDate.action?tiNo='+testRow.tiNo+'&studyTypeCodes='+getSelections,
		//});
		$.ajax({
			url: sybp()+'/tblStudyItemAction_studyItemTablemultiLoadDate.action',
			type:'post',
			data:{tiNo:testRow.tiNo , studyTypeCodes:getSelections },
			dataType:'json',
			success:function(r){
			  if(r){
				var dataRows = $('#studyItemTable_multi').datagrid('getRows');
				//if(dataRows.length > 0){
				//	$('#studyItemTable_multi').datagrid('loadData',{total:0,rows:[]}); 
				//}
				for(var i = 0;i<r.length;i++){
					$('#studyItemTable_multi').datagrid('appendRow',{
						studyNo:r[i].studyNo,
						studyTypeCode: r[i].studyTypeCode,
						studyName: r[i].studyName,
						animalHave: r[i].animalHave,
						finishDate:r[i].finishDate
					});
					//$('#studyItemTable_multi').datagrid('beginEdit',dataRows.length+i-1);
				}
				for(var i = 0;i<r.length+dataRows.length;i++){
						 $('#studyItemTable_multi').datagrid('beginEdit',i);
				}
				
			  }else{
			  }
			}
		});
		
	}
}
//先删除取消数据,后添加后选数据
function updateStudyItemTableData(studyItemTable_multi,rows){
	var dataRows = studyItemTable_multi.datagrid('getRows');
	//var oldStudyTypeCodes = new Array();
	//if(dataRows.length > 0){
	//	for(var i = 0;i<dataRows.length;i++){
	//		oldStudyTypeCodes.push(dataRows[i].studyTypeCode);
	//	}
	//}
	var newStudyTypeCodes = new Array();
	if(rows.length > 0){
		for(var i = 0;i<rows.length;i++){
			newStudyTypeCodes.push(rows[i].studyTypeCode);
		}
	}
	
	var flag = false;
	//循环删除取消数据
	//do{
	//	flag = false;
	//	if(dataRows.length > 0){
	//		for(var i = 0;i<dataRows.length;i++){
	//			if(!in_array(newStudyTypeCodes,dataRows[i].studyTypeCode)){
	//				studyItemTable_multi.datagrid('deleteRow',i);
	//				dataRows = studyItemTable_multi.datagrid('getRows');
	//				flag = true;
	//			}
	//		}
	//	}
    //}while(flag)
		
	//添加后选数据
	for(var i = 0;i < rows.length;i++){
		//if(!in_array(oldStudyTypeCodes,rows[i].studyTypeCode)){
			studyItemTable_multi.datagrid('appendRow',{
				studyNo: tiNo+'-'+(hasLength+dataRows.length+ 1),
				studyTypeCode: rows[i].studyTypeCode,
				studyName: rows[i].studyName,
				animalHave: rows[i].animalHave,
				finishDate:rows[i].finishDate
			});
			studyItemTable_multi.datagrid('beginEdit',i);
		//}
	}
}

/***
 * 数组中是否有该元素
 * @param array
 * @param element
 * @return
 */
function in_array(array,element){
	for(var i=0;i<array.length;i++){  
		if(array[i] == element)  
			return true;  
	}  
	return false; 
}

//初始化 SD推荐人  下拉框
function initSDManagerCombobox_multi(){
	//loadStudyTypePartList
	$('#studyItem_sdManager_multi').combobox({
		url:sybp()+'/tblStudyItemAction_loadSDManagerList.action',
		valueField:'id',
		textField:'text',
		onSelect:function(record){
			if(record.id == -1){
				$('#studyItem_sdManager_multi').combobox('clear');
			}
		}
	});
}


/**动物类别的选择事件*/
function onAnimalTypeSelect(record){
	if(record.id == -1){
		$('#animalType_multi').combobox('clear');
	}else{
		$('#animalType_multi').combobox('select',record.text);
	}
	$.ajax({
		url:sybp()+'/tblStudyItemAction_getAnimalStrain.action',
		type:'post',
		data:{
			animalTypeId:record.id
		},
		dataType:'json',
		success:function(r){
			$('#animalStrain_multi').combobox('setValue','');
			$('#animalStrain_multi').combobox('loadData',r);
		}
	});
}

