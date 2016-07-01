//初始化 专题操作日志、数据修改跟踪日志 tab
function inityydbTabs(){
	$('#yydbTabsDiv').tabs({
		onSelect:function(title,index){
			if(title == "专题操作日志"){
				updateDataGrid_yydb();
			}else if(title =='数据修改跟踪日志'){
				updateDataGrid_yydb();
    		}
	    }
	    		
	});
}

function updateDataGrid_yydb(){
	var operateType ;
	var studyNoRow = $('#studyNo').datagrid('getSelected');
	var studyNo ;
	if(studyNoRow){
		studyNo = studyNoRow.studyNo;
	}
	var tab = $('#yydbTabsDiv').tabs('getSelected');
	var index = $('#yydbTabsDiv').tabs('getTabIndex',tab);
	if(studyNo && index == 0){
		operateType = $('#operateType_yydb').combobox('getValue');
		$('#yydbtable').datagrid({
			url:sybp()+'/yydbAction_loadStudyData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && index == 1){
		operateType = $('#operateType2_path').combobox('getValue');
		$('#yydbtable2').datagrid({
			url:sybp()+'/yydbAction_loadDataTrace.action?studyNo='
				+studyNo
		});
	}
}

//初始化operateType
function initOperateTypeCombobox_yydb(){
	$('#operateType_yydb').combobox({
		onSelect:function(record){
			updateDataGrid_yydb();
		}
	});
}

//初始化 yydbtable ,yydbtable2表格
function inityydbtable(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#yydbtable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			field:'operatType',
			title:'类型',
			width:100,
			halign:'center'
		},{
			field:'testcode',
			title:'专题编号',
			width:150,
			halign:'center'
		},{
			field:'logdesc',
			title:'日志描述',
			width:202,
			halign:'center',
			halign:'center'
		},{
			field:'operator',
			title:'操作者',
			width:50,
			halign:'center'
		},{
			field:'logtime',
			title:'记录时间',
			width:150,
			halign:'center'
		}]]
	});
	$('#yydbtable2').datagrid({
		singleSelect:true,
		height:tableHeight+25,
		width:tableWidth,
		columns :[[{
			field:'testcode',
			title:'专题编号',
			width:130,
			halign:'center'
		},{
			field:'animals',
			title:'动物编号',
			width:70,
			halign:'center',
			halign:'center'
		},{
			field:'datatype',
			title:'数据类别',
			width:60,
			halign:'center',
			halign:'center'
		},{
			field:'olddata',
			title:'原数据',
			width:70,
			halign:'center',
			halign:'center'
		},{
			field:'newdata',
			title:'新数据',
			width:70,
			halign:'center',
			halign:'center'
		},{
			field:'rawdatatime',
			title:'数据的时间',
			width:70,
			halign:'center',
			halign:'center'
		},{
			field:'datachangersn',
			title:'修改原因',
			width:226,
			halign:'center',
			halign:'center'
		},{
			field:'datachangetime',
			title:'修改时间',
			width:136,
			halign:'center',
			halign:'center'
		},{
			field:'operator',
			title:'操作者',
			width:50,
			halign:'center'
		}]]
	});
}