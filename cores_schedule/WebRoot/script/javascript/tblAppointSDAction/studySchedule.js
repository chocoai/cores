//
var studyScheduleTable;
var currentStudyNo ;
var actualDate;
var nodeName1;
/**
 * 显示 studyScheduleNodeDialog
 */
function showStudyScheduleDialog(){
	var row = $('#appointSDTable').datagrid('getSelected');
	if(row && row.studyNo){
		currentStudyNo = row.studyNo
		$('#studyNode').html(row.studyNo);
		document.getElementById('studyScheduleDialog2').display = 'block';
		$('#studyScheduleDialog').dialog('open');
		//初始化&加载datagrid数据
		initStudyScheduleTable();
	}
}
/**
 * 初始化&加载datagrid数据
 * @return
 */
function initStudyScheduleTable(){
	if(studyScheduleTable){
		studyScheduleTable.datagrid({url:sybp()+'/tblStudyScheduleAction_loadList2.action?studyNo='+currentStudyNo});
	}else{
		studyScheduleTable = $('#studyScheduleTable').datagrid({
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
			},{
				title:'操作',
				field:'id',
				width:100,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
				
					if(row && row.nodeName == 'SD任命') {
						return "";
					}else{
						return "<a href='javascript:setActualDate(\""+row.nodeName+"\",\""+row.actualDate+"\");'>设置日期</a>";
					}
				}
			}]],
			onLoadSuccess:function (data){
				$('#isSubmit').val('true');
				$('#studyScheduleNodeSubmitButton').linkbutton('disable');
				if(data && data.es){
//					$('#isSubmit').val('true');
//					$('#studyScheduleNodeSubmitButton').linkbutton('disable');
				}else{
					$('#isSubmit').val('false');
					$('#studyScheduleNodeSubmitButton').linkbutton('enable');
				}
			}
		});
	}
}
/**
 * 设置完成日期
 * @return
 */
function setActualDate(nodeName,actualDate){
	$.ajax({
		url:sybp()+'/tblStudyScheduleAction_isHasNoActualDateBefore.action',
		type:'post',
		data:{
			studyNo:currentStudyNo,
			nodeName:nodeName
		},
		dataType:'json',
		success:function(r){
			if(r.success){
				showCalendarDialog(nodeName,currentStudyNo,actualDate,'afterQianming');
			}else{
				$.messager.alert('警告',r.msg);    
			}
				
		}
	});
//	showCalendarDialog(nodeName,currentStudyNo,'afterSetActualDate');
}

function afterQianming(date,currentStudyNo,nodeName){
	 actualDate=date;
	 nodeName1=nodeName;
	 qm_showQianmingDialog('afterSetActualDate');
}
/**
 * 保存或更新后台实际完成日期
 * @param date
 * @param studyNo
 * @return
 */
function afterSetActualDate(){
//	 alert(currentStudyNo);
//	 alert(nodeName1);
//	 alert(actualDate);
		if(actualDate&& currentStudyNo && nodeName1){
			$.ajax({
				url:sybp()+'/tblStudyScheduleAction_updateActualDate.action',
				type:'post',
				data:{
				    actualDate:actualDate,
					studyNo:currentStudyNo,
					nodeName:nodeName1
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						$('#studyScheduleTable').datagrid('load',sybp()+'/tblStudyScheduleAction_loadList.action?studyNo='+studyNo);
//						$('#calendarDialog').dialog('close');
						parent.parent.showMessager(1,'设置成功',true,5000);
					}
				}
			});
		}
}
/**
 * 关闭dialog
 * @return
 */
function onCloseDialog(){
	$('#studyScheduleDialog').dialog('close');
	 $('#appointSDTable').datagrid('reload');
	
}