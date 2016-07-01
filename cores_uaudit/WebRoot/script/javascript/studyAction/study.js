var tableHeight ;
window.onload=function(){
	tableHeight = document.body.clientHeight - 77;
	//初始化 year combobox值
	initYearComboboxData();
	//初始化 year combobox
	initYearCombobox();
	
	//初始化 year2 combobox值
	initYearComboboxData2();
	//初始化 year2 combobox
	initYearCombobox2();
	
	//初始化 studyNo DataGrid
	initStudyNoDataGrid();
	//初始化studyNo DataGrid 值
	initStudyNoDataGridData();
	
	//初始化 contractCode DataGrid
	initContractCodeDataGrid();
	//初始化contractCode DataGrid 值
	initContractCodeDataGridData();
	
	//初始化 systemName combobox 
	initSystemNameCombobox();
	
	//临检管理
	//初始化operateType_clinicaltest  combobox
	initOperateTypeCombobox_clinicaltest();
	$('#operateType_clinicaltest').combobox('select',1);
	
	//病理管理
	//初始化 解剖所见、解剖申请 tab
	initTabs();
	//初始化operateType,operateType2  combobox
	initOperateTypeCombobox_path();
	initAnatomyCheck();
	initAnatomyReq();
	initHistopathCheck();
	initVisceraWeighTable();
	initVisceraFixedTable();
	initDeadDateTable();

	//初始化operateType_schedule  combobox
	initDatagrid_schedule();
	initOperateTypeCombobox_schedule();
	$('#operateType_schedule').combobox('select',4);
	
	//一般毒理
	//初始化 专题操作日志、数据修改跟踪日志 tab
	inityydbTabs();
	//初始化 yydbtable,yydbtable2 表格
	inityydbtable();
	//初始化operateType
	initOperateTypeCombobox_yydb();
	
	//QA管理
	//	初始化operateType  combobox
	initOperateTypeCombobox_qa();
	//初始化QA数据表格
	initDatagrid_qa();
	
	//委托管理
	//初始化委托项目数据表格
	initStudyitemDatagrid();
	
	initTestitemDatagrid();
	
	initContractDatagrid();
	
}
//初始化 year combobox值
function initYearComboboxData(){
	var yearListStr = $('#yearListStr').val();
	$('#year').combobox('loadData', eval(yearListStr));
	var yearStr = $('#yearStr').val();
	$('#year').combobox('select',yearStr);
}
//初始化 year combobox
function initYearCombobox(){
	$('#year').combobox({
		onSelect:function(record){
			updateStudyNoDatagrid();
		}
	});
}
//初始化 year2 combobox值
function initYearComboboxData2(){
	var yearListStr2 = $('#yearListStr2').val();
	$('#year2').combobox('loadData', eval(yearListStr2));
	var yearStr = $('#yearStr').val();
	$('#year2').combobox('select',yearStr);
}
//初始化 year2 combobox
function initYearCombobox2(){
	$('#year2').combobox({
		onSelect:function(record){
			//合同编号列表
			updateContractCodeDatagrid();
		}
	});
}
//初始化 studyNo DataGrid
function initStudyNoDataGrid(){
	$('#studyNo').datagrid({
		//url:sybp()+'/clinicaltestAction_loadStudyNoList.action'
		singleSelect:true,
		height:tableHeight,
		width:145,
		columns :[[{
			field:'studyNo',
			title:'专题编号',
			width:125,
			halign:'center'
		}]],
		onClickRow:function(rowIndex, rowData){
			//更新对应值
			updateDataGrid();
   		}
	});
}
//初始化 contractCode DataGrid
function initContractCodeDataGrid(){
	$('#contractCode').datagrid({
		singleSelect:true,
		height:tableHeight+30,
		width:145,
		columns :[[{
			field:'contractCode',
			title:'合同编号',
			width:125,
			halign:'center'
		}]],
		onSelect:function(rowIndex, rowData){
			//TODO 合同、供试品、委托项目
			updateData_contract(rowData);
		}
	});
}

//更新studyNo DataGrid 数据
function updateStudyNoDatagrid(){
	var year = $('#year').combobox('getText');
	var testItemType;
	var temp = document.getElementsByName("testItemType");
	for(var i=0;i<temp.length;i++){
		if(temp[i].checked)
		testItemType = temp[i].value;
	}
	$('#studyNo').datagrid({
		url:sybp()+'/studyAction_loadStudyNoList.action?year='+year+'&testItemType='+testItemType
	});
}
//更新contractCode DataGrid 数据
function updateContractCodeDatagrid(){
	var year = $('#year2').combobox('getText');
	$('#contractCode').datagrid({
		url:sybp()+'/studyAction_loadContractCodeList.action?year='+year
	});
}
//初始化studyNo DataGrid 值
function initStudyNoDataGridData(){
	var studyNoListStr = $('#studyNoListStr').val();
	$('#studyNo').datagrid('loadData', eval(studyNoListStr));
}
//初始化contractCode DataGrid 值
function initContractCodeDataGridData(){
	var contractCodeListStr = $('#contractCodeListStr').val();
	$('#contractCode').datagrid('loadData', eval(contractCodeListStr));
	$('#contractCode').datagrid('selectAll');
}
/**点击对应单选框*/
function onRadioCk(selectNum){
	$('#radio'+selectNum).attr('checked','checked');
	$('#radio'+selectNum).click(); 
}
//初始化 systemName combobox 
function initSystemNameCombobox(){
	$('#systemName').combobox({
		onChange:function(record,oldValue){
			$("#clinicaltestDiv").css('display','none');
			$("#pathDiv").css('display','none');
			$("#scheduleDiv").css('display','none');
			$("#yydbDiv").css('display','none');
			$("#qaDiv").css('display','none');
			$("#contractDiv").css('display','none');
			if(record == 1){
				$("#clinicaltestDiv").css('display','block');
			}else if(record == 2){
				$("#pathDiv").css('display','block');
			}else if(record == 3){
				$("#scheduleDiv").css('display','block');
			}else if(record == 4){
				$("#yydbDiv").css('display','block');
			}else if(record == 5){
				$("#qaDiv").css('display','block');
			}else if(record == 6){
				$("#contractDiv").css('display','block');
			}
			
			if(record == 6){
				$("#contractDiv2").css('display','block');
				$("#studyDiv").css('display','none');
				var selectRow = $('#contractCode').datagrid('getSelected');
				if(selectRow){
					var rowIndex = $('#contractCode').datagrid('getRowIndex',selectRow);
					$('#contractCode').datagrid('refreshRow',rowIndex);
					$('#contractCode').datagrid('selectRow',rowIndex);
				}
				
			}else{
				$("#contractDiv2").css('display','none');
				$("#studyDiv").css('display','block');
			}
			
			updateDataGrid();
		}
	});
	$('#systemName').combobox('select',1);
}

/**更新datagrid的值
 * @return
 */
function updateDataGrid(){
	var systemNameIndex = $('#systemName').combobox('getValue');
	if(!systemNameIndex){
		systemNameIndex = 1;
	}
	if(systemNameIndex == 1){
		updateDatagrid_clinicaltest();
	}else if(systemNameIndex == 2){
		updateDataGrid_path();
	}else if(systemNameIndex == 3){
		updateDatagrid_schedule();
	}else if(systemNameIndex == 4){
		updateDataGrid_yydb();
	}else if(systemNameIndex == 5){
		updateDatagrid_qa();
	}
}
//合同、供试品、委托项目 表更新值
function updateData_contract(rowData){
	//更新合同表格数据
	updateContractDatagrid(rowData);
	//更新供试品表格数据
	updateTestitemDatagrid(rowData);
	//更新表格数据
	updateStudyitemDatagrid(rowData);
}