function addStudyGroup(addOrEdit)
{
	if(addOrEdit==0)//新增
	{
		
	}
	else if(addOrEdit==1)//编辑
	{
		
	}
	
}

function saveOrUpdateOneStudyGroup()
{
	 var studyGroupName=$('#studyGroupName').val();
	 var updateStudyGroupID = $('#updateStudyGroupID').val();
	 
	 alert("studyGroupName="+studyGroupName);
	 if(studyGroupName=="")
	 {
		 $.messager.alert("提示框","请输入试验类别名称！");
	 }else if( updateStudyGroupID=="" ){//新增
		
			$.ajax({
				url:sybp()+'/DictStudyGroupAction_addSave.action',
				type:'post',
				data:$('#oneStudyGroup').serialize(),
				dataType:'json',
				success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						/*$('#newStudyNo').val(r.msg);
						$('#studyPlanAddEditDialog').dialog('close'); 
						var studyPlanAddEdit_event=document.getElementById("studyPlanAddEdit_event");
						studyPlanAddEdit_event.click();*/
						parent.setTopStudyNoSD(r.msg,r.name);
						
					}else{
						$.messager.alert('提示','请检查录入的数据');
					}
				}
			});
		}else{ //编辑
			$.ajax({
				url:sybp()+'/DictStudyGroupAction_editSave.action',
				type:'post',
				data:$('#studyPlanAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#newStudyNo').val(r.msg);
						$('#newsd').val(r.obj);
						$('#studyPlanAddEditDialog').dialog('close'); 
						var studyPlanAddEdit_event=document.getElementById("studyPlanAddEdit_event");
						studyPlanAddEdit_event.click();
					}else{
						$.messager.alert('提示',r.msg,'info');
					}
				}
			});
		}
		
	
}
