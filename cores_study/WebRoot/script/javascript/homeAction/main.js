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
	//显示Dialog
	showStudyPlanAddNoDialog();
}