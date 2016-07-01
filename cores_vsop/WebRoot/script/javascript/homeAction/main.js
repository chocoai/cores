/**
 * 新建一个 增加试验计划Dialog
 * @return
 */
function addNewStudyPlanDialog(){
	$('#newStudyNo').val('');
	$('#sponsorName').html('');
	$('#tiName').html('');
	$('#studyName').html('');
	$('#studyNoTable').datagrid('clearSelections');
	
	//显示Dialog SD增加专题的界面
	//showStudyPlanAddNoDialog();
	//直接显示增加页面
	alert("第二个界面");
	showStudyPlanAddEditDialog("afterSaveQAStudyChkIndex","add","新增专题检查索引");
	
}