//
var studyScheduleInfoTable;
/**
 * 显示 studyScheduleNodeDialog
 */
function showStudyScheduleInfoDialog(){
	var row = $('#appointSDTable').datagrid('getSelected');
	if(row && row.studyNo){
		$('#studyNode_info').html(row.studyNo);
		document.getElementById('studyScheduleInfoDialog2').display = 'block';
		$('#studyScheduleInfoDialog').dialog('open');
		//初始化&加载datagrid数据
		initStudyScheduleInfoTable(row.studyNo);
	}
}
/**
 * 初始化&加载datagrid数据
 * @return
 */
function initStudyScheduleInfoTable(currentStudyNo){
	if(studyScheduleInfoTable){
		studyScheduleInfoTable.datagrid({url:sybp()+'/tblStudyScheduleAction_loadList2.action?studyNo='+currentStudyNo});
	}else{
		studyScheduleInfoTable = $('#studyScheduleInfoTable').datagrid({
			url:sybp()+'/tblStudyScheduleAction_loadList2.action?studyNo='+currentStudyNo,
			height:300,
			width:373,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			columns :[[{
				title:'studyNo',
				field:'studyNo',
				width:10,
				hidden:true
			},{
				title:'节点名称',
				field:'nodeName',
				width:90,
				halign:'center',
				align:'left'
			},{
				title:'计划日期',
				field:'planDate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'完成日期',
				field:'actualDate',
				width:80,
				halign:'center',
				align:'center'
			}]]
		});
	}
}


/**
 * 关闭dialog
 * @return
 */
function onCloseInfoDialog(){
	$('#studyScheduleInfoDialog').dialog('close');
	
}