
//初始化 解剖所见、解剖申请 tab
function initTabs(){
	$('#indexTabsDiv').tabs({
		onSelect:function(title,index){
			if(title == "解剖所见"){
				//initAnatomyCheck();
				updateDataGrid_path();
			}else if(title =='解剖申请'){
				//initAnatomyReq();
				updateDataGrid_path();
    		}else if(title =='组织学检查'){
				//initAnatomyReq();
				updateDataGrid_path();
    		}else if(title =='脏器称重'){
				//initAnatomyReq();
				updateDataGrid_path();
    		}else if(title =='脏器固定'){
				//initAnatomyReq();
				updateDataGrid_path();
    		}else if(title =='死亡日期'){
				//initAnatomyReq();
				updateDataGrid_path();
    		}
	    }
	    		
	});
}
//初始化operateType,operateType2  combobox
function initOperateTypeCombobox_path(){
	$('#operateType_path').combobox({
		onSelect:function(record){
//			if(record.id == 2 || record.id == 4){
//				$('#anatomyCheck').datagrid('showColumn','oldAnatomyFinding');
//														  
//			}else{
//				$('#anatomyCheck').datagrid('hideColumn','oldAnatomyFinding');
//			}
			updateDataGrid_path();
		}
	});
	$('#operateType2_path').combobox({
		onSelect:function(record){
			updateDataGrid_path();
		}
	});
	$('#operateType3_path').combobox({
		onSelect:function(record){
			updateDataGrid_path();
		}
		});
	$('#operateType4_path').combobox({ 
		onSelect:function(record){
			updateDataGrid_path();
		}
	});
	$('#operateType5_path').combobox({ 
		onSelect:function(record){
			updateDataGrid_path();
		}
	});
}
//初始化 anatomyCheck 表格
function initAnatomyCheck(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#anatomyCheck').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			field:'studyNo',
			title:'专题编号',
			width:142,
			halign:'center'
		},{
			field:'animalCode',
			title:'动物编号',
			width:55,
			halign:'center'
		},{
			field:'oldAnatomyFinding',
			title:'原解剖所见',
			width:202,
			halign:'center',
			hidden:false
		},{
			field:'anatomyFinding',
			title:'解剖所见',
			width:202,
			halign:'center'
		},{
			field:'operator',
			title:'记录者',
			width:50,
			halign:'center'
		},{
			field:'operateTime',
			title:'记录时间',
			width:110,
			halign:'center'
		},{
			field:'operate',
			title:'操作',
			width:50,
			halign:'center'
		},{
			field:'operater',
			title:'操作者',
			width:50,
			halign:'center'
		},{
			field:'operateRsn',
			title:'操作原因',
			width:142,
			halign:'center'
		},{
			field:'operateDate',
			title:'操作时间',
			width:110,
			halign:'center'
		},{
			field:'fn',
			title:'备注',
			width:50,
			halign:'center'
		}]]
	});
}
//初始化 anatomyReq 表格
function initAnatomyReq(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#anatomyReq').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'专题编号',
			field:'studyNo1',
			width:142,
			halign:'center',
			align:'left',
		},{
			title:'专题编号(含有FN部分)',
			field:'studyNo',
			width:142,
			halign:'center',
			align:'left',
			hidden:true
		},{
			title:'申请编号',
			field:'reqNo',
			width:60,
			halign:'center',
			align:'center',
		},{
			title:'解剖开始日期',
			field:'beginDate',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'解剖结束日期',
			field:'endDate',
			width:100,
			halign:'center',
			align:'center'
		},{
			title:'解剖原因',
			field:'anatomyRsn',
			width:100,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == 1 ){
					return "计划解剖";
				}else if( value == 2 ){
					return "濒死解剖";
				}else if( value == 3 ){
					return "死亡解剖";
				}else if( value == 4 ){
					return "安乐死解剖";
				}
		    } 
		},{
			title:'创建人',
			field:'author',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'创建日期',
			field:'createTime',
			width:100,
			halign:'center',
			align:'center'
		},{
			title:'申请状态',
			field:'submitFlag',
			width:90,
			halign:'center',
			align:'center'
		},{
			title:'1:变更前  2：边个后',
			field:'change',
			width:90,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'状态',
			field:'state',
			width:80,
			halign:'center',
			align:'center',
			hidden:true,
			formatter: function(value,row,index){
				if ( value == 0 ){
					return "";
				}else if( value == 1 ){
					return "";
				}else{
					return "";
				}
		    }
		},{
			field:'fn',
			title:'备注',
			width:50,
			halign:'center'
		}]],
		onDblClickRow:function(rowIndex, rowData){
			showReqDialog(rowData.studyNo,rowData.reqNo,rowData.change);
		}
	});
}
//初始化 histopathCheck 表格
function initHistopathCheck(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#histopathCheck').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'专题编号',
			field:'studyNo',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'动物编号',
			field:'animalCode',
			width:60,
			halign:'center',
			align:'left'
		},{
			title:'脏器或组织',
			field:'visceraOrTissueName',
			width:120,
			halign:'center',
			align:'center'
		},{
			title:'性质',
			field:'tumorFlag',
			width:55,
			halign:'center',
			align:'left'
		},{
			title:'是否转移',
			field:'metastasisFlag',
			width:55,
			halign:'center',
			align:'center'
		},{
			title:'检查结果',
			field:'checkResult',
			width:120,
			halign:'center',
			align:'center' 
		},{
			title:'原发脏器/肿瘤',
			field:'primaryVisceraTumor',
			width:85,
			halign:'center',
			align:'center'
		},{
			title:'检查日期',
			field:'checkTime',
			width:90,
			halign:'center',
			align:'center'
		},{
			title:'复查意见',
			field:'opinion',
			width:90,
			halign:'center',
			align:'center'
		},{
			title:'操作',
			field:'state',
			width:40,
			halign:'center',
			align:'center'
		},{
			title:'病变程度',
			field:'level',
			width:55,
			halign:'center'
		},{
			field:'tumorNum',
			title:'肿瘤数量',
			width:55,
			halign:'center'
		},{
			field:'tumorPos',
			title:'肿瘤位置',
			width:55,
			halign:'center'
		},{
			field:'tumorOccurDate',
			title:'肿瘤发生日期',
			width:90,
			halign:'center'
		},{
			field:'tumorCharacter',
			title:'肿瘤特性',
			width:55,
			halign:'center'
		},{
			field:'remark',
			title:'备注',
			width:50,
			halign:'center'
		}]]
	});
}

//初始化 visceraWeighTable 表格
function initVisceraWeighTable(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#visceraWeighTable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			field:'studyNo',
			title:'专题编号',
			width:142,
			halign:'center'
		},{
			field:'animalCode',
			title:'动物编号',
			width:55,
			halign:'center'
		},{
			field:'visceraName',
			title:'脏器',
			width:102,
			halign:'center',
			hidden:false
		},{
			field:'oldWeight',
			title:'原重量(g)',
			width:60,
			halign:'center'
		},{
			field:'weight',
			title:'重量(g)',
			width:60,
			halign:'center'
		},{
			field:'attachedVisceraNames',
			title:'附加脏器',
			width:60,
			halign:'center'
		},{
			field:'fixedWeightFlag',
			title:'固定后称重',
			width:70,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
			if ( value == 0 ){
				return "否";
			}else if( value == 1 ){
				return "是";
			}
	    }
		},{
			field:'operator',
			title:'记录者',
			width:50,
			halign:'center'
		},{
			field:'operateTime',
			title:'记录时间',
			width:110,
			halign:'center'
		},{
			field:'operate',
			title:'操作',
			width:50,
			halign:'center'
		},{
			field:'operater',
			title:'操作者',
			width:50,
			halign:'center'
		},{
			field:'operateRsn',
			title:'操作原因',
			width:142,
			halign:'center'
		},{
			field:'operateDate',
			title:'操作时间',
			width:110,
			halign:'center'
		},{
			field:'fn',
			title:'备注',
			width:50,
			halign:'center'
		}]]
	});
}
//初始化 visceraFixedTable 表格
function initVisceraFixedTable(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#visceraFixedTable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			field:'studyNo',
			title:'专题编号',
			width:142,
			halign:'center'
		},{
			field:'animalCode',
			title:'动物编号',
			width:55,
			halign:'center'
		},{
			field:'visceraName',
			title:'脏器',
			width:102,
			halign:'center',
			hidden:false
		},{
			field:'operator',
			title:'记录者',
			width:50,
			halign:'center'
		},{
			field:'operateTime',
			title:'记录时间',
			width:110,
			halign:'center'
		},{
			field:'operate',
			title:'操作',
			width:50,
			halign:'center'
		},{
			field:'operater',
			title:'操作者',
			width:50,
			halign:'center'
		},{
			field:'operateRsn',
			title:'操作原因',
			width:142,
			halign:'center'
		},{
			field:'operateDate',
			title:'操作时间',
			width:110,
			halign:'center'
		},{
			field:'fn',
			title:'备注',
			width:50,
			halign:'center'
		}]]
	});
}
//初始化 deadDateTable 表格
function initDeadDateTable(){
	var tableHeight = document.body.clientHeight - 105;
	var tableWidth =  document.body.clientWidth - 225 ;
	$('#deadDateTable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			field:'studyNo',
			title:'专题编号',
			width:142,
			halign:'center'
		},{
			field:'animalCode',
			title:'动物编号',
			width:55,
			halign:'center'
		},{
			field:'oldDeadDate',
			title:'原死亡日期',
			width:80,
			halign:'center'
		},{
			field:'deadDate',
			title:'死亡日期',
			width:80,
			halign:'center'
		},{
			field:'operate',
			title:'操作',
			width:50,
			halign:'center'
		},{
			field:'operater',
			title:'操作者',
			width:50,
			halign:'center'
		},{
			field:'operateRsn',
			title:'操作原因',
			width:142,
			halign:'center'
		},{
			field:'operateDate',
			title:'操作时间',
			width:110,
			halign:'center'
		},{
			field:'fn',
			title:'备注',
			width:50,
			halign:'center'
		}]]
	});
}

function updateDataGrid_path(){
	var operateType ;
	var studyNoRow = $('#studyNo').datagrid('getSelected');
	var studyNo ;
	if(studyNoRow){
		studyNo = studyNoRow.studyNo;
	}
	var tab = $('#indexTabsDiv').tabs('getSelected');
	var index = $('#indexTabsDiv').tabs('getTabIndex',tab);
	if(studyNo && index == 0){
		operateType = $('#operateType_path').combobox('getValue');
		$('#anatomyCheck').datagrid({
			url:sybp()+'/pathAction_loadAnatomyCheckData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && index == 1){
		operateType = $('#operateType2_path').combobox('getValue');
		$('#anatomyReq').datagrid({
			url:sybp()+'/pathAction_loadAnatomyReqData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && index == 4){
		operateType = $('#operateType3_path').combobox('getValue');
		$('#histopathCheck').datagrid({
			url:sybp()+'/pathAction_loadHistopathCheckData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && index == 2){
		operateType = $('#operateType4_path').combobox('getValue');
		$('#visceraWeighTable').datagrid({
			url:sybp()+'/pathAction_loadVisceraWeightData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && index == 3){
		operateType = $('#operateType5_path').combobox('getValue');
		$('#visceraFixedTable').datagrid({
			url:sybp()+'/pathAction_loadVisceraFixedData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && index == 5){
		//operateType = $('#operateType4_path').combobox('getValue');
		$('#deadDateTable').datagrid({
			url:sybp()+'/pathAction_loadDeadDateData.action?studyNo='
				+studyNo
		});
	}
}