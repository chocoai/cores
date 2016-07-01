/***/
function initEndDate(){
	var endDate= $('#endDate').datebox('getValue');
	var beginDate=$('#beginDate').datebox('getValue');
	if(endDate == ''){
		$('#endDate').datebox('setValue',beginDate);
	}
}
/**
 * 保存
 * @param length
 * @return
 */
function save(){
	var PReqNo = $('#PReqNo').val();
	if(checkAllbeforeSave()) {
		//document.forms[0].submit();
		$.ajax({
			url:sybp()+'/tblClinicalTestReqAction_save.action?reqNoPara='+PReqNo,
			type:'post',
			data:$('#tblClinicalTestReqForm').serialize(),
			dataType:'json',
			success:function(r){
				if(r && r.success){
					document.getElementById('tblClinicalTestReqId').value=r.currentid;
					document.getElementById('reqNo').value=r.currentReqNo;
					parent.parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.parent.showMessager(3,r.msg,true,5000);
				}
			}
		});
	}
}

/*保存前的检查*/
function checkAllbeforeSave(){
	var animalIdList = document.getElementsByName("animalIds");
	var animalNum = 0;
	for(var i=0;i<animalIdList.length;i++){
		if(animalIdList[i].checked){
			animalNum++;
		}
	}
	$('#errorMsg').html('');
	if(!testPhaseCheck()){
		//$('#errorMsg').html("请填写试验阶段！");
		parent.parent.showMessager(2,'请填写试验阶段！',true,5000);
		
	} else if (!reqDateCheck()) {
		//$('#errorMsg').html("请填写计划检查日期！");
		parent.parent.showMessager(2,'请填写计划检查日期！',true,5000);
	} else if(animalNum == 0){
		//$('#errorMsg').html("请选择需要临检的动物！");
		parent.parent.showMessager(2,'请选择需要临检的动物！',true,5000);
	}else{
//			var testOther = document.getElementById('testOther').value;
//			var remark = document.getElementById('remark').value;
//			if(getByteLen(testOther)>20  ){
//				$('#errorMsg').html("其他检测项目信息不能超过20！");
//			}else if(getByteLen(remark)>99){
//				$('#errorMsg').html("备注信息不能超过100！");
//			}else{
//				return true;
//			}
			var testOther1 = $('#testOther').val();
			var remark1 = $('#remark').val();
			if(getByteLen(testOther1)>20  ){
				//$('#errorMsg').html("其他检测项目信息不能超过20！");
				parent.parent.showMessager(2,'其他检测项目信息不能超过20！',true,5000);
			}else if(getByteLen(remark1)>99){
				//$('#errorMsg').html("备注信息不能超过100！");
				parent.parent.showMessager(2,'备注信息不能超过100！',true,5000);
			}else{
				return true;
			}
		
	}
	return false;
}
/**js获取长度*/
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
            len += 2;
        else
            len += 1;
    }
    return len;
}
/**
 * 试验阶段检查
 * @return
 */
function testPhaseCheck(){
	var testPhase = $("#testPhase").val();
	var testPhaseNum = $("#testPhaseNum").val();
	if(testPhase==2||testPhase==3||testPhase==4||testPhase==5){
		if(testPhaseNum == ""){
			return false;
		}
	}
	return true;
}
/**
 * 检查日期检查
 * @return
 */
function reqDateCheck(){
	var beginDate = $("#beginDate").datebox('getValue');
	var endDate = $("#endDate").datebox('getValue');
	if(beginDate==""){
		return false;
	}else if(endDate==""){
		return false;
	}else{
		return true;
	}
}


/**
 * 全选
 * @return
 */
function animalCheckAll(){
	var animalIdList = document.getElementsByName("animalIds");
	
//		for(var i=0;i<animalIdList.length;i++){
//			animalIdList[i].checked =false;;
//		}
		for(var i=0;i<animalIdList.length;i++){
			animalIdList[i].checked =true;
		}
}
/**
 * 全不选
 * @return
 */
function animalCheckAllNo(){
	var animalIdList = document.getElementsByName("animalIds");
	
		for(var i=0;i<animalIdList.length;i++){
			animalIdList[i].checked =false;;
		}
//	for(var i=0;i<animalIdList.length;i++){
//		animalIdList[i].checked =true;
//	}
}
//onSelect: function(record){    
//    animalCheckAllNo();  
//    var selectCheckboxs = $('.animalId_'+record.id);
//    for(var i = 0 ; i<selectCheckboxs.length;i++){
//    	selectCheckboxs[i].checked="checked";
//    }
//},panelHeight:'auto'"/>动物
/**选择动物*/
function onCCSelect(record){
	animalCheckAllNo();  
	if(record.id == -1){
		$('#animalCombobox').combobox('unselect',record.id);
	}
    var selectCheckboxs = $('.animalId_'+record.id);
    for(var i = 0 ; i<selectCheckboxs.length;i++){
    	selectCheckboxs[i].checked="checked";
    }
}

function animalCheckClick(es){
	if(es == 1){
		return false;
	}
}

/**
 * 打印预览(编辑页面)
 * @return
 */
function outPort(){
	//1.提醒，预览前要保存
//	if(!confirm('预览前将保存数据，是否继续？')){
		//不继续，返回
//		return ;
//	}
	
	//2.检查数据
	if(!checkAllbeforeSave()) {
		//检查未通过，返回
		return ;
	}
	var PReqNo = $('#PReqNo').val();
	$.messager.confirm('确定','预览前将保存数据，是否继续？',function(r){
		if(r){    
			//3.ajax保存
			$.ajax({
				url:sybp()+'/tblClinicalTestReqAction_saveWithjson.action?reqNoPara='+PReqNo,
				type:'post',
				data:$('#tblClinicalTestReqForm').serialize(),
				dataType:'json',
				success:function(r){
				if(r && r.success){
					//4.打印预览
					parent.parent.showMessager(2,'数据加载中...',true,5000);
					window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_ireport.action?studyNoPara="
						+r.obj.studyNo+"&reqNoPara="+r.obj.reqNo+"&toWhere=apply" ;//
				}
			}
			});
	    } 
	});

}
/**
 * 打印预览(查看页面)
 * @return
 */
function outPort3(){
	//4.打印预览
	parent.parent.showMessager(2,'数据加载中...',true,5000);
	window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_ireport.action?studyNoPara="
		+$('#studyNo').val()+"&reqNoPara="+$('#reqNo').val()+"&toWhere=view" ;
}
/**
 * 打印预览(list页面)
 * @return
 */
function outPort2(studyNoPara, reqNoPara){
	//4.打印预览
	parent.parent.showMessager(2,'数据加载中...',true,5000);
	window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_ireport.action?studyNoPara="
		+studyNoPara+"&reqNoPara="+reqNoPara;
}

/**签字前的检查*/
function checkBeforeSign(){
	//1.提醒，提交前要保存
//	if(!confirm('提交前将保存数据，是否继续？')){
		//不继续，返回
//		return ;
//	}
	//2.检查数据
	if(!checkAllbeforeSave()) {
		//检查未通过，返回
		return ;
	}
	var PReqNo = $('#PReqNo').val();
	$.messager.confirm('确定','提交前将保存数据,且该数据将不可以再编辑，是否继续？',function(r){
		if(r){
			//3.ajax保存
			$.ajax({
				url:sybp()+'/tblClinicalTestReqAction_saveWithjson.action?reqNoPara='+PReqNo,
				type:'post',
				data:$('#tblClinicalTestReqForm').serialize(),
				dataType:'json',
				success:function(r){
					if(r && r.success){
						//4.弹出签字窗口（传入签字后事件）
						qm_showQianmingDialog('eventAfterSign');
						$('#studyNo').val(r.obj.studyNo);
						$('#reqNo').val(r.obj.reqNo);
					}
				}
			});
		}
	});
}
/**签字前的检查*/
function checkBeforeSign2(studyNoPara, reqNoPara){
	$.messager.confirm('提交','提交后数据将不可以再编辑，是否继续？',function(r){
		if(r){
			qm_showQianmingDialog('eventAfterSign2');
			$('#studyNo').val(studyNoPara);
			$('#reqNo').val(reqNoPara);
		}
	});
}
function eventAfterSign(){
	window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_sign.action?studyNoPara="+$('#studyNo').val()+"&reqNoPara="+$('#reqNo').val();
}
//签字后提交
function eventAfterSign2(){
	$.ajax({
		url:sybp()+'/tblClinicalTestReqAction_sign2.action',
		type:'post',
		data:{
			studyNoPara:$('#studyNo').val(),
			reqNoPara:$('#reqNo').val()
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
//				$.messager.show({
//					title:'提示',
//					msg:'提交成功',
//					timeout:5000
//				});
				parent.parent.showMessager(1,'提交成功',true,5000);
				tblClinicalTestReqTable.datagrid('load');
				
				editClinicalTestReqButton.linkbutton('disable');
				readClinicalTestReqButton.linkbutton('enable');
				submitClinicalTestReqButton.linkbutton('disable');
				delClinicalTestReqButton.linkbutton('disable');
				resultClinicalTestReqButton.linkbutton('enable');
				printClinicalTestReqButton.linkbutton('enable');
			}else{
				parent.parent.showMessager(3,'提交失败',true,5000);
//				$.messager.show({
//					title:'提示',
//					msg:'提交失败',
//					timeout:5000
//				});
			}
		}
	});
}

function deleteConfirm(studyNoPara, reqNoPara){
	
	$.messager.confirm('删除','确定删除吗？',function(r){
		if(r){
			$.ajax({
				url:sybp()+'/tblClinicalTestReqAction_delete.action',
				type:'post',
				data:{
					studyNoPara:studyNoPara,
					reqNoPara:reqNoPara
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
//						$.messager.show({
//							title:'提示',
//							msg:r.msg,
//							timeout:5000
//						});
						parent.parent.showMessager(1,r.msg,true,5000);
						var rows =tblClinicalTestReqTable.datagrid('getSelections');
						var index =tblClinicalTestReqTable.datagrid('getRowIndex',rows[0]);
						tblClinicalTestReqTable.datagrid('deleteRow',index);
					}else{
//						$.messager.show({
//							title:'提示',
//							msg:r.msg,
//							timeout:5000
//						});
						parent.parent.showMessager(3,r.msg,true,5000);
					}
				}
			});
		}
	});
	
	//deleteConfirm
//	if (confirm('确定删除吗？')){
//		
//	}else{
//		return false;
//	}
}

/**全选*/
function selectAll(checkName){
	var allCheckBox = $('.'+checkName);
	for(var i=0;i<allCheckBox.length;i++){
		allCheckBox[i].checked = true;
	}
}
/**全不选*/
function unSelectAll(checkName){
	var allCheckBox = $('.'+checkName);
	for(var i=0;i<allCheckBox.length;i++){
		allCheckBox[i].checked = false;
	}
}

function clinicalTestApply(){
	window.location.href= "tblClinicalTestReqAction_clinicalTestApply.action?parentReqNo1="+$('#connectionNumber').val()+"&studyNoPara="+$('#studyNoPara').val();
}