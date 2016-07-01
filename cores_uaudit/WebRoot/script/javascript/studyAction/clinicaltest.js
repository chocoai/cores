
//初始化operateType  combobox
function initOperateTypeCombobox_clinicaltest(){
	$('#operateType_clinicaltest').combobox({
		onSelect:function(record){
			initDatagrid_clinicaltest(record.id);
			updateDatagrid_clinicaltest();
		}
	});
}
//初始化数据表格
function initDatagrid_clinicaltest(value){
	var tableHeight = document.body.clientHeight - 75;
	var tableWidth =  document.body.clientWidth -225;
	if(value == 1){
		$('#clinicalData').datagrid({
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
			columns :[[{
				field:'studyNo',
				title:'专题编号',
				width:138,
				align:'left',
				halign:'center'
			},{
				field:'reqNo',
				title:'申请编号',
				width:60,
				align:'center',
				halign:'center'
			},{
				field:'specimenCode',
				title:'检验编号',
				width:90,
				align:'left',
				halign:'center'
			},{
				field:'animalCode',
				title:'动物编号',
				width:80,
				align:'left',
				halign:'center'
			},{
				field:'animalId',
				title:'动物Id号',
				width:80,
				align:'left',
				halign:'center'
			},{
				field:'testItem',
				title:'检验项目',
				width:80,
				align:'left',
				halign:'center',
				formatter: function(value,row,index){
					if(value == 1){
						return '生化检验';
					} else if(value == 2){
						return '血液检验';
					} else if(value == 3){
						return '血凝检验';
					} else if(value == 4){
						return '尿液检验';
					} 
				}
			},{
				field:'testIndex',
				title:'检验指标',
				width:140,
				align:'left',
				halign:'center'
			},{
				field:'testIndexAbbr',
				title:'缩写',
				width:60,
				align:'left',
				halign:'center'
			},{
				field:'testData',
				title:'新数据',
				width:60,
				align:'right',
				halign:'center'
			},{
				field:'testIndexUnit',
				title:'单位',
				width:60,
				align:'left',
				halign:'center'
			},{
				field:'collectionTime',
				title:'检验时间',
				width:130,
				align:'left',
				halign:'center'
			},{
				field:'confirmFlag',
				title:'第几次检测',
				width:80,
				align:'left',
				halign:'center'
			},{
				field:'es',
				title:'签字',
				width:130,
				align:'left',
				hidden:true
			},{
				field:'fn',
				title:'备注',
				width:50,
				halign:'center'
			}]],
			rowStyler:function(index,row){
				if(row.es == 1){
					return 'background-color:#98fb98;';
				}
			}
		});
	}else if(value == 2){
		$('#clinicalData').datagrid({
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
			columns :[[{
				field:'studyNo',
				title:'专题编号',
				width:138,
				align:'left',
				halign:'center'
			},{
				field:'reqNo',
				title:'申请编号',
				width:60,
				align:'center',
				halign:'center'
			},{
				field:'specimenCode',
				title:'检验编号',
				width:90,
				align:'left',
				halign:'center'
			},{
				field:'testItem',
				title:'检验项目',
				width:80,
				align:'left',
				halign:'center',
				formatter: function(value,row,index){
					if(value == 1){
						return '生化检验';
					} else if(value == 2){
						return '血液检验';
					} else if(value == 3){
						return '血凝检验';
					} else if(value == 4){
						return '尿液检验';
					} 
				}
			},{
				field:'testIndexAbbr',
				title:'检验指标',
				width:60,
				align:'left',
				halign:'center'
			},{
				field:'oldValue',
				title:'原数据',
				width:60,
				align:'right',
				halign:'center'
			},{
				field:'newValue2',
				title:'新数据',
				width:60,
				align:'right',
				halign:'center'
			},{
				field:'collectionTime',
				title:'检验时间',
				width:130,
				align:'left',
				halign:'center'
			},{
				field:'operateMode',
				title:'操作',
				width:50,
				align:'left',
				halign:'center',
				formatter: function(value,row,index){
					if(value == 1){
						return '编辑';
					} else if(value == 2){
						return '删除';
					} else {
						return '';
					} 
				}
			},{
				field:'operator',
				title:'操作者',
				width:60,
				align:'left',
				halign:'center'
			},{
				field:'modifyReason',
				title:'操作原因',
				width:130,
				align:'left',
				halign:'center'
			},{
				field:'modifyTime',
				title:'操作时间',
				width:130,
				align:'left',
				halign:'center'
			},{
				field:'fn',
				title:'备注',
				width:50,
				halign:'center'
			}]]
		});
	}
}

//更新表格数据
function updateDatagrid_clinicaltest(){
	var operateType = $('#operateType_clinicaltest').combobox('getValue');
	var studyNoRow = $('#studyNo').datagrid('getSelected');
	var studyNo ;
	if(studyNoRow){
		studyNo = studyNoRow.studyNo;
	}
	if(studyNo && operateType == 1){
		$('#clinicalData').datagrid({
			url:sybp()+'/clinicalTestAction_loadClinicalData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}else if(studyNo && operateType == 2){
		$('#clinicalData').datagrid({
			url:sybp()+'/clinicalTestAction_loadClinicalData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}
}