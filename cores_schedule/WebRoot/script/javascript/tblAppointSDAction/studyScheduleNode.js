//
var studyScheduleNodeTable;
var currentStudyNo ;
/**
 * 显示 studyScheduleNodeDialog
 */
function showStudyScheduleNodeDialog(){
	$('#isSubmit').val('true');
	$('#studyScheduleNodeSubmitButton').linkbutton('disable');
	var row = $('#appointSDTable').datagrid('getSelected');
	if(row && row.studyNo){
		currentStudyNo = row.studyNo
		$('#studyNo').html(row.studyNo);
		$('#studyScheduleNodeDialog').dialog('open');
		//初始化&加载datagrid数据
		initStudyScheduleNodeTable();
	}
}
/**
 * 初始化&加载datagrid数据
 * @return
 */
function initStudyScheduleNodeTable(){
	if(studyScheduleNodeTable){
		studyScheduleNodeTable.datagrid({url:sybp()+'/tblStudyScheduleAction_loadList.action?studyNo='+currentStudyNo});
	}else{
		studyScheduleNodeTable = $('#studyScheduleNodeTable').datagrid({
			url:sybp()+'/tblStudyScheduleAction_loadList.action?studyNo='+currentStudyNo,
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
				align:'left'
			},{
				title:'操作',
				field:'id',
				width:175,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
					if (row && row.nodeName =='试验分组' && row.planDate ){
						return '<a  href="javascript:setPlanDate(\''+row.nodeName+'\',\''+row.planDate+'\');">设置日期</a>'+
						'&nbsp;&nbsp;&nbsp;<a id="setDefaultDateA" style="display:\'none\'" href="javascript:setDefaultDate();">计算其他项默认日期</a>';
					}else {
						return "<a href='javascript:setPlanDate(\""+row.nodeName+"\",\""+row.planDate+"\");'>设置日期</a>";
					}
				}
			}]],
			onLoadSuccess:function (data){
				$('#isSubmit').val('true');
				$('#studyScheduleNodeSubmitButton').linkbutton('disable');
				
				if(data && data.es){
//					$('#isSubmit').val('true');
//					$('#studyScheduleNodeSubmitButton').linkbutton('disable');
					$('#setDefaultDateA').css('display','none');
				}else{
					$('#isSubmit').val('false');
					$('#studyScheduleNodeSubmitButton').linkbutton('enable');
					$('#setDefaultDateA').css('display','');
				}
			}
		});
	}
}
/**
 * 设置计划日期
 * @return
 */
function setPlanDate(nodeName,planDate){
	if($('#isSubmit').val() == 'true'){
		//签过字，判断是否有实际日期
		var isHasActualDate = $.ajax({
			url : sybp()+'/tblStudyScheduleAction_isHasActualDate.action',
			type: 'post',
			data:{
				studyNo:currentStudyNo,
				nodeName:nodeName
			},
			async: false,   
    		cache: false,  
			dataType:'json'
		}).responseText;
		if(isHasActualDate == 'true'){
			$.messager.alert('警告','该节点项已完成，不可再设置计划日期');
		}else{
			showCalendarDialog(nodeName,currentStudyNo,planDate,'afterSetPlanDate');
		}
	}else{
		showCalendarDialog(nodeName,currentStudyNo,planDate,'afterSetPlanDate');
	}
}
/**
 * 保存或更新后台计划日期
 * @param date
 * @param studyNo
 * @return
 */
function afterSetPlanDate(date,studyNo,nodeName){
		if(date && studyNo && nodeName){
			if($('#isSubmit').val() == 'true'){
				qm_showQianmingDialog('thisFunction');
			}else{
				//保存或更新后台计划日期
				updatePlanDate(date,studyNo,nodeName);
			}
		}
		this.thisFunction=function(){
			//保存或更新后台计划日期
			updatePlanDate(date,studyNo,nodeName);
		}
}
/**
 * 保存或更新后台计划日期(真正)
 * @param date
 * @param studyNo
 * @param nodeName
 * @return
 */
function updatePlanDate(date,studyNo,nodeName){
	$.ajax({
		url:sybp()+'/tblStudyScheduleAction_updatePlanDate.action',
		type:'post',
		data:{
		planDate:date,
		studyNo:studyNo,
		nodeName:nodeName
	},
	dataType:'json',
	success:function(r){
		if(r && r.success){
			$('#studyScheduleNodeTable').datagrid('load',sybp()+'/tblStudyScheduleAction_loadList.action?studyNo='+studyNo);
//			$('#calendarDialog').dialog('close');
			parent.parent.showMessager(1,'设置成功',true,5000);
		}
	}
	});
}

/**
 * 根据‘试验分组‘计划日期日期，计算其他项目计划日期
 * @return
 */
function setDefaultDate(){
	var rows = studyScheduleNodeTable.datagrid('getRows');
	//是否已经有日期（除首次给药外）
	var isHasPlan = "false";
	for(var i = 0;i<rows.length;i++){
		if(rows[i].planDate && rows[i].nodeName != '试验分组'){
			isHasPlan = 'true';
			break;
		}
	}
	
	if(isHasPlan == 'true'){
		$.messager.confirm('确认','已设置日期的节点项将不会重新设置日期，是否继续？',function(r){    
		    if (r){    
		    	//访问后台，计算默认日期
		    	setDefaultDate_(); 
		    }    
		}); 
	}else{
		//访问后台，计算默认日期
		setDefaultDate_(); 
	}
}

/**
 * 访问后台，计算默认日期
 * @return
 */
function setDefaultDate_(){
	$.ajax({
		url:sybp()+'/tblStudyScheduleAction_setDefaultDate.action',
		type:'post',
		data:{
			studyNo:currentStudyNo,
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				$('#studyScheduleNodeTable').datagrid('load',sybp()+'/tblStudyScheduleAction_loadList.action?studyNo='+studyNo);
//				$('#calendarDialog').dialog('close');
				parent.parent.showMessager(1,r.msg,true,5000);
			}else{
				parent.parent.showMessager(2,r.msg,true,5000);
			}
		}
	});
}
/**
 * 专题计划提交
 * @return
 */
function onSubmit(){
	var rows = studyScheduleNodeTable.datagrid('getRows');
	//是否有未设置日项
	var isExist = "false";
	for(var i = 0;i<rows.length;i++){
		if(!rows[i].planDate){
			isExist = 'true';
			break;
		}
	}
	if(isExist == 'true'){
		$.messager.confirm('确认','存在未设置计划日期的节点项，是否继续？',function(r){    
		    if (r){    
		    	qm_showQianmingDialog('studyScheduleSubmit');
		    }    
		});
	}else{
		qm_showQianmingDialog('studyScheduleSubmit');
	}
}


/**
 * 专题计划提交(真正提交)
 * @return
 */
function studyScheduleSubmit(){
	$.ajax({
		url:sybp()+'/tblStudyScheduleAction_submit.action',
		type:'post',
		data:{
			studyNo:currentStudyNo,
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				$('#isSubmit').val('true');
				$('#studyScheduleNodeSubmitButton').linkbutton('disable');
				parent.parent.showMessager(1,r.msg,true,5000);
			}else{
				parent.parent.showMessager(2,r.msg,true,5000);
			}
		}
	});
}

//关闭进度计划
function closestudyScheduleNodeDialog(){
	$('#studyScheduleNodeDialog').dialog('close');
	var row = $('#appointSDTable').datagrid('getSelected');
	$('#appointSDTable').datagrid('clearSelections');
	$('#appointSDTable').datagrid('selectRecord',row.id);
}