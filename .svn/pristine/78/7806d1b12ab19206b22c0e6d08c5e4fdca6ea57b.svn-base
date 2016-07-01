
//初始化operateType  combobox
function initOperateTypeCombobox_schedule(){
	$('#operateType_schedule').combobox({
		onSelect:function(record){
//			$('#appointTable').datagrid('showColumn','sd');
//			$('#appointTable').datagrid('hideColumn','qa');
//			$('#appointTable').datagrid('hideColumn','pathSD');
//			if(record.id == 2){
//				$('#appointTable').datagrid('hideColumn','sd');
//				$('#appointTable').datagrid('showColumn','qa');
//				$('#appointTable').datagrid('hideColumn','pathSD');
//			}else if(record.id == 3){
//				$('#appointTable').datagrid('hideColumn','sd');
//				$('#appointTable').datagrid('hideColumn','qa');
//				$('#appointTable').datagrid('showColumn','pathSD');
//			}
			updateDatagrid_schedule();
		}
	});
}
//初始化数据表格
function initDatagrid_schedule(){
	var tableHeight = document.body.clientHeight - 75;
	var tableWidth =  document.body.clientWidth -225;

	$('#appointTable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			field:'id',
			title:'id',
			width:30,
			align:'left',
			halign:'center',
			hidden:true
		},{
			field:'studyNo',
			title:'专题编号',
			width:138,
			align:'left',
			halign:'center'
		},{
			field:'studyName',
			title:'专题名称',
			width:180,
			align:'center',
			halign:'center'
		},{
			field:'tiName',
			title:'供试品名称',
			width:180,
			align:'left',
			halign:'center'
		},{
			field:'sd',
			title:'SD',
			width:100,
			align:'left',
			halign:'center'
		},{
			field:'qa',
			title:'QA检查员',
			width:100,
			align:'left',
			halign:'center'
		},{
			field:'pathSD',
			title:'病理专题负责人',
			width:100,
			align:'left',
			halign:'center'
		},{
			field:'appointDate',
			title:'任命日期',
			width:90,
			align:'left',
			halign:'center'
		},{
			field:'cancelDate',
			title:'撤销日期',
			width:90,
			align:'left',
			halign:'center'
		},{
			field:'operate',
			title:'操作',
			width:140,
			align:'left',
			halign:'center'
		}]],
		rowStyler:function(index,row){
			if(row.es == 1){
				return 'background-color:#98fb98;';
			}
		}
	});

}

//更新表格数据
function updateDatagrid_schedule(){
	var operateType = $('#operateType_schedule').combobox('getValue');
	var studyNoRow = $('#studyNo').datagrid('getSelected');
	var studyNo ;
	if(studyNoRow){
		studyNo = studyNoRow.studyNo;
	}
	if(studyNo && operateType > 0){
		$('#appointTable').datagrid({
			url:sybp()+'/scheduleAction_loadData.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}
}