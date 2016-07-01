
//初始化operateType  combobox
function initOperateTypeCombobox_qa(){
	$('#operateType_qa').combobox({
		onSelect:function(record){
			updateDatagrid_qa();
		}
	});
}
//初始化QA数据表格
function initDatagrid_qa(){
	var tableHeight = document.body.clientHeight - 75;
	var tableWidth =  document.body.clientWidth -225;
		$('#qatable').datagrid({
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
			columns :[[{
				field:'operateType',
				title:'操作类型',
				width:188,
				align:'left',
				halign:'center'
			},{
				field:'studyNo',
				title:'专题/报告编号',
				width:150,
				align:'left',
				halign:'center'
			},{
				field:'operator',
				title:'操作者',
				width:90,
				align:'left',
				halign:'center'
			},{
				field:'operateTime',
				title:'操作时间',
				width:120,
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
function updateDatagrid_qa(){
	var operateType = $('#operateType_qa').combobox('getValue');
	var studyNoRow = $('#studyNo').datagrid('getSelected');
	var studyNo ;
	if(studyNoRow){
		studyNo = studyNoRow.studyNo;
	}
	if(studyNo ){
		$('#qatable').datagrid({
			url:sybp()+'/qaAction_loadlist.action?studyNo='
				+studyNo+'&operateType='+operateType
		});
	}
}