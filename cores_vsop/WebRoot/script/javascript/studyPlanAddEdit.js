
/**显示Dialog*/
function showStudyPlanAddEditDialog(clickName,addOrEdit,title){
	/*签名Dialog*/
	document.getElementById("studyPlanAddEditDialog2").style.display="block";
	$('#addOrEdit').val(addOrEdit);
	$('#studyPlanAddEditDialog').dialog('setTitle',title);
	$('#studyPlanAddEditDialog').dialog('open'); 
	document.getElementById("studyPlanAddEdit_event").href="javascript:"+clickName+"();";
}

/**保存*/
function onDialogSaveButton(){
	var newStudyNo = $('#newStudyNo').val();
	if( $('#studyPlanAddEditForm').form('validate') ){
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/qAStudyChkIndexAction_uniqueCheck.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"html",
	        data:"studyNoPara="+newStudyNo,
	        beforeSend:function(XMLHttpRequest)
	        {
			
	        },
	        success:function(msg)
	        {
	        
	        	if(msg=="is")
	        	{
	        		parent.showMessager(2,'课题编号已存在!',true,5000);
	        		//$.messager.alert('提示','课题编号已存在!');
	        	}
	        	if(msg=="no")
	        	{
	        		//检查无误，提交表单
	        		//document.forms[0].submit();
	        		//beforeOpenStudyPlanAddEditDialog(newStudyNo);
	        		
	        		$('#saveDialogButton').linkbutton('disable');
	        		if($('#addOrEdit').val() == 'add'){
	        			qm_showQianmingDialog("afterSignSaveOneQAStudyChkIndex");
	        			
	        		}else{
	        			$.ajax({
	        				url:sybp()+'/tblStudyPlanAction_editSave.action',
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
	        },
	        complete:function(XMLHttpRequest,textStatus)
	        {
	        	//parent.document.getElementsByName("left")[0].reload();
	        },
	        error:function()
	        {
	        	$.messager.alert('提示','与服务器交互错误!');
	        }
	   });
	
		
		
	}
}
function afterSignSaveOneQAStudyChkIndex()
{
	$.ajax({
		url:sybp()+'/qAStudyChkIndexAction_addSave.action',
		type:'post',
		data:$('#studyPlanAddEditForm').serialize(),
		dataType:'json',
		success:function(r){
			$('#saveDialogButton').linkbutton('enable');
			if(r && r.success){
				$('#newStudyNo').val(r.msg);
				$('#studyPlanAddEditDialog').dialog('close'); 
				//var studyPlanAddEdit_event=document.getElementById("studyPlanAddEdit_event");
				//studyPlanAddEdit_event.click();
				//parent.setTopStudyNoSD(r.msg,r.name);
				 document.getElementById("chkPlanLeft").contentWindow.$('#studyDatagrid').datagrid('reload');
				 //发邮件
				 var msg="QAM新增专题后发邮件出现错误！";
				 sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					
			}else{
				$.messager.alert('提示','请检查录入的数据');
			}
		}
	});
	
}

function afterSaveQAStudyChkIndex()
{
	//专题列表中增加一行,在ajax返回中已经处理过了
	// document.getElementById("chkPlanLeft").contentWindow.$('#studyDatagrid').datagrid('reload');
	
}
function sendNotification(msgTitle,msgContent,receiverList,msg){
	if(receiverList!=null&&receiverList!=''){
		
		  $.ajax({
	      	url : sybp()+'/qAStudyChkIndexAction_sendNotification.action',
	      	type: 'post',
	      	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		         dataType:'json',
		         data:{
	      			msgTitle:msgTitle,
	      			msgContent:msgContent,
	      			receiverList:receiverList,
			      },
		         success:function(r){
					if(r&&!r.success){
						$.messager.alert('提示框',msg);
					}
		         }
	    });
	}else{
		//$.messager.alert('提示框','发邮件没有接收人存在');
	}
}
/**动物类别的选择事件*/
function onAnimalTypeSelect(record){
	//animalTypeId
	//getAnimalStrain
	if(record.id != -1){
		$('#animalType').combobox('select',record.text);
		$.ajax({
			url:sybp()+'/tblStudyPlanAction_getAnimalStrain.action',
			type:'post',
			data:{
			animalTypeId:record.id
		},
		dataType:'json',
		success:function(r){
			$('#animalStrain').combobox('setValue','');
			$('#animalStrain').combobox('loadData',r);
		}
		});
	}else{
		$('#animalType').combobox('setValue','');
		$('#animalStrain').combobox('setValue','');
		$('#animalStrain').combobox('loadData',[]);
	}
}
