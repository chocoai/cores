<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	function addQAFileType(addOrEdit)
	{
		if(addOrEdit==0)//新增
		{
			$('#oneFileType').combobox({
				onSelect: function(rec){
	            	$('#oneParentFileTypeID').combobox({
	            		 valueField: 'fileTypeId',
	            	     textField: 'fileTypeName',
	            	     url: sybp()+'/qAFileTypeAction_loadListByFileType.action?fileType='+rec.value,
	            	  
	            	});
				}
			});
			/* 显示Dialog */
			document.getElementById("addOrEditQAFileTypeDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddButton').css('display','');
			
			
			$('#updateFileTypeID').val('');
			$('#oneFileType').combobox('setValue','');
			$('#oneParentFileTypeID').combobox('setValue','');
			$('#oneFileTypeName').val('');
			
			$('#addOrEditQAFileTypeDialog').dialog('setTitle','添加文件类型');
			$('#addOrEditQAFileTypeDialog').dialog('open'); 
		}
		else if(addOrEdit==1)//编辑
		{
			
			/* 显示Dialog */
			document.getElementById("addOrEditQAFileTypeDialog2").style.display="block";
	
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var row=$('#QAFileTypes').tree("getSelected");
			if(row!=null&&row.id!=1&&row.id!=2&&row.id!=3)
			{
				$.ajax({
					url:sybp()+'/qAFileTypeAction_getById.action?fileTypeId='+row.id,
					type:'post',
					dataType:'json',
					success:function(r){
						if(r ){
							$('#oneFileType').combobox({
								onSelect: function(rec){
					            	$('#oneParentFileTypeID').combobox({
					            		 valueField: 'fileTypeId',
					            	     textField: 'fileTypeName',
					            	     url: sybp()+'/qAFileTypeAction_loadListByFileType.action?fileType='+rec.value+'&noFileTypeId='+r.fileTypeId
					            	  
					            	});
								}
							});

							
							$('#updateFileTypeID').val(r.fileTypeId);
							$('#oneFileType').combobox('setValue',r.fileType);
							
							$('#oneFileType').combobox('select',r.fileType);
							$('#oneFileTypeName').val(r.fileTypeName);
							$('#oneParentFileTypeID').combobox('setValue',r.parentFileTypeId);
						}
					}
				});
				
			
				
				$('#addOrEditQAFileTypeDialog').dialog('setTitle','编辑文件类型');
				$('#addOrEditQAFileTypeDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个文件类型！");
			}
		}
		
	}
	function delQAFileType()
	{
		var row=$('#QAFileTypes').tree("getSelected");
		
		if(row!=null&&row.id!=1&&row.id!=2&&row.id!=3)
		{
			//判断是否存在子文件类型
			$.ajax({
				url:sybp()+'/qAFileTypeAction_isExistChildFileType.action?fileTypeId='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){	
					if(r&&!r.isExistChild)
					{
						if(!r.isExistTask)
						{
							$.messager.confirm('确认框','删除文件类型的同时会删除他的文件以及文件附件，确定要删除该文件类型吗?',function(r){
								if (r){
									qm_showQianmingDialog('afterSignDelQAFileType');
								}
							});
						}else{
							$.messager.alert('提示框','该文件类型下的部分文件已经建立学习任务！');
						}
					}else{
						$.messager.alert('提示框','该文件类型下还包含子的文件类型，请先删除子文件类型');
					}
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个文件类型！");
		}
	}
	function afterSignDelQAFileType()
	{
		var row=$('#QAFileTypes').tree("getSelected");
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/qAFileTypeAction_del.action?fileTypeId='+row.id,
			type:'post',
			dataType:'json',
			success:function(){	
			}
		});
		 var node = $('#QAFileTypes').tree('find', row.id);
		 $('#QAFileTypes').tree('remove',node.target);
	
	}
	
	function saveOrUpdateOneQAFileType()
	{
		 var oneFileType=$('#oneFileType').combobox("getValue");
		 var oneParentFileTypeID=$('#oneParentFileTypeID').combobox("getValue");
		 var oneFileTypeName=$('#oneFileTypeName').val();
		 var updateFileTypeID = $('#updateFileTypeID').val();
		 
		 if(oneFileType==''||oneFileTypeName=='')
		 {
			 $.messager.alert("提示框","请选择类型和填写类型名称！");
		 }else if( updateFileTypeID=="" ){//新增
				$.ajax({
					url:sybp()+'/qAFileTypeAction_save.action',
					type:'post',
					data:$('#oneQAFileTypeTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							//$('#QAFileTypes').tree("reload");
							var node;
							if(r.parentId!=null&&r.parentId!='')
							{
								node = $('#QAFileTypes').tree('find', r.parentId);
							}else
							{
								node = $('#QAFileTypes').tree('find', r.fileType);
							}
							$('#QAFileTypes').tree('append', {
								parent: node.target,
								data: [{
									id: r.id,
									text: r.text
								}]
								
							});
						}else if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
					}
				});
				if($('#continueAddButton').attr('checked')!='checked')
				{
					 $('#addOrEditQAFileTypeDialog').dialog('close');
				}else {
				
					$('#oneFileType').combobox("setValue","");
					$('#oneParentFileTypeID').combobox("setValue","");
					$('#oneFileTypeName').val('');
					$('#updateFileTypeID').val('');
				}
				
				
			}else{ //编辑
				$.ajax({
					url:sybp()+'/qAFileTypeAction_editSave.action',
					type:'post',
					data:$('#oneQAFileTypeTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							//添加新的
							 	var node;
								if(r.parentId!=null)
								{
									node = $('#QAFileTypes').tree('find', r.parentId);
								}else
								{
									node = $('#QAFileTypes').tree('find', r.fileType);
								}
								var changeNode = $('#QAFileTypes').tree('find', r.id);
								$('#QAFileTypes').tree('update', {
									target: changeNode.target,
									text: r.text,
								});
								var nodeData = $('#QAFileTypes').tree('getData',changeNode.target);
								$('#QAFileTypes').tree('append', {
									parent: node.target,
									data: [nodeData],
									
								});
								//删除老的
								//var id=$('#updateFileTypeID').val();
								// var nodeDel = $('#QAFileTypes').tree('find',id);
								 $('#QAFileTypes').tree('remove',changeNode.target);
						}else if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
					}
				});
				
				 $('#addOrEditQAFileTypeDialog').dialog('close');
				// $('#QAFileTypes').tree("reload");
				
			}
		
			
		
	}
	
	function addQAFileReg(addOrEdit)
	{
		
		var row0=$('#QAFileTypes').tree("getSelected");
		
		if(row0!=null&&row0.id!=1&&row0.id!=2&&row0.id!=3)//不是三个类型的其余都可以添加文件
		{
			if(addOrEdit==0)//新增
			{
				document.getElementById("addOrEditQAFileRegDialog2").style.display="block";
				document.getElementById("continueAddLabel2").innerHTML='连续添加';//连续添加不显示
				$('#continueAddButton2').css('display','');

				$('#updateFileRegId').val('');
				$('#oneFileName').val('');
				$('#oneFileCode').val('');
				$('#oneFilePublishDepartment').val('');
				$('#oneFilePublishTime').val('');
				$('#oneFileVersion').val('');
				$('#oneIsVersionUpdate').val('');
				
				$('#addOrEditQAFileRegDialog').dialog('setTitle','添加文件');
				$('#addOrEditQAFileRegDialog').dialog('open'); 
			}
			else if(addOrEdit==1)//编辑
			{
				
				document.getElementById("addOrEditQAFileRegDialog2").style.display="block";
				document.getElementById("continueAddLabel2").innerHTML='';//连续添加不显示
				$('#continueAddButton2').css('display','none');
				
				var row=$('#QAFileRegs').datagrid("getSelected");
				if(row!=null&&row!=''&&row!='undified')
				{
					$('#updateFileRegId').val(row.fileRegId);
					$('#oneFileName').val(row.fileName);
					$('#oneFileCode').val(row.fileCode);
					$('#oneFilePublishDepartment').val(row.filePublishDepartment);
					$('#oneFilePublishTime').datebox('setValue',row.filePublishTime);
					$('#oneFileVersion').val(row.fileVersion);
					$('#oneRemark').val(row.remark);
					
					if(row.isVersionUpdate==1)
						$('#oneIsVersionUpdate').attr('selected','selected');;
					
					$('#addOrEditQAFileRegDialog').dialog('setTitle','编辑文件');
					$('#addOrEditQAFileRegDialog').dialog('open'); 
				}else {
					$.messager.alert("提示","请选择一个文件！");
				}
			}
		}else
		{
			$.messager.alert("提示","请选择一个文件类型！");
		}
		
	}

	function saveOrUpdateOneQAFileReg()
	{
		 var updateFileRegId=$('#updateFileRegId').val();
		 var oneFileName = $('#oneFileName').val();
		 var oneFileCode = $('#oneFileCode').val();
		 var oneFilePublishDepartment = $('#oneFilePublishDepartment').val();
		 var oneFilePublishTime =  $('#oneFilePublishTime').datebox("getValue");
		 var oneFileVersion =  $('#oneFileVersion').val();
		 var oneIsVersionUpdate = $('#oneIsVersionUpdate').val();

		 var isupdate=0;
		 if($('#oneIsVersionUpdate').attr('checked')=='checked')
			 isupdate=1;
		 if(oneFileName==''||oneFileCode==''||oneFilePublishDepartment==''||oneFilePublishTime==''||oneFileVersion=='')
		 {
			 $.messager.alert("提示框","请输入完整信息！");
		 }else if( updateFileRegId=="" ){//新增
			var row=$('#QAFileTypes').tree("getSelected");//
			$.ajax({
				url:sybp()+'/qAFileRegAction_save.action?fileTypeId='+row.id+'&isVersionUpdate='+isupdate,
				type:'post',
				data:$('#oneQAFileReg').serialize(),
				dataType:'json',
				success:function(r){
					if(r && r.success)
					{
						$('#QAFileTypes').tree("select",row.target);
					}else if(r && !r.success)
					{
						$.messager.alert("提示",r.msg);	
					}
				}
			});
			if($('#continueAddButton2').attr('checked')!='checked')
			{
				 $('#addOrEditQAFileRegDialog').dialog('close');
			}else {
				$('#oneFileName').val('');
				$('#oneFileCode').val('');
				$('#oneFilePublishDepartment').val('');
				$('#oneFilePublishTime').datebox('setValue','');
				$('#oneFileVersion').val('');
				$('#oneIsVersionUpdate').attr('selected');
				
			}
		 }else{//编辑
			 var isupdate=0;
			 if($('#oneIsVersionUpdate').attr('checked')=='checked')
				 isupdate=1;
			 $.ajax({
					url:sybp()+'/qAFileRegAction_editSave.action?isVersionUpdate='+isupdate,
					type:'post',
					data:$('#oneQAFileReg').serialize(),
					dataType:'json',
					success:function(r){
					 	if(r && r.success)
						{
					 		var row=$('#QAFileTypes').tree("getSelected");
							$('#QAFileTypes').tree("select",row.target);
							
						}else	if(r && !r.success)
						{
							$.messager.alert("提示",r.msg);	
						}
					}
				});
			 $('#addOrEditQAFileRegDialog').dialog('close');
		 }
		
		
	}
	function delQAFileReg()
	{
		var row=$('#QAFileRegs').datagrid("getSelected");
		if(row!=null)
		{
			$.ajax({
				url:sybp()+'/qAFileRegAction_isExistTaskFile.action',
				type:'post',
				data:{
					fileRegId:row.fileRegId
				},
				dataType:'json',
				success:function(r){
					if(r&&!r.isExistTask)
					{
				
						$.messager.confirm('确认框','删除文件的同时会删除检查依据以及文件附件,确定要删除该文件吗?',function(r){
							if (r){
								qm_showQianmingDialog('afterSignDelQAFileReg');
							}
						});
					}else{
						$.messager.alert('提示框','学习任务中包含该文件！');
					}
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个文件！");
		}
		 $('#QACheckTableContents').datagrid("reload");
	}
	function afterSignDelQAFileReg()
	{
		var row=$('#QAFileRegs').datagrid("getSelected");
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/qAFileRegAction_del.action',
			type:'post',
			data:{
				fileRegId:row.fileRegId
			},
			dataType:'json',
			success:function(){

			}
		});
		var index = $('#QAFileRegs').datagrid('getRowIndex',row);
		$('#QAFileRegs').datagrid('deleteRow',index);
		$('#QAFileAttachmentDG').datagrid('loadData',[]);
	}
	function changeCancelName()
	{
		if($('#continueAddButton').attr('checked')=='checked')
		{
			$('#cancelButton').linkbutton({
				text:'关闭'
		
			});
		}else {
			$('#cancelButton').linkbutton({
				text:'取消'
		
			});
		}
	}
	function addQAFileAttachment()
	{
		var row=$('#QAFileRegs').datagrid("getSelected");
		if(row!=null)
		{
			document.getElementById("addQAFileAttachmentDialog2").style.display="block";
			$('#fileAttachments').datagrid({
				url:sybp()+'/qAFileAttachmentAction_getListByFileRegId.action?fileRegId='+row.fileRegId,
			});
			//$('#fileAttachments').datagrid('reload');
			
			$('#addQAFileAttachmentDialog').dialog('setTitle','管理附件');
			$('#theUploadFile').val(row.fileRegId);
			$('#addQAFileAttachmentDialog').dialog('open');
		}else
		{
			$.messager.alert('提示','请选择一个文件');
		}
		
	}
	
	/**确定（保存）*/
	function onDialogAttachmentSaveButton(){
		//var row=$('#QAFileRegs').datagrid("getSelected");
		//文件上传的form
		 $('#oneQAFileAttachment').form({    
			    url:sybp()+'/qAFileAttachmentAction_importFile.action',
			    type:'post',
				dataType:'json',
			    onSubmit: function(){    
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
			    },    
			    success:function(data){   
			    
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	
			    	if(data=='true'){
			    		var fileRegId=$('#theUploadFile').val();
						$.ajax({
							url:sybp()+'/qAFileAttachmentAction_getListByFileRegId.action?fileRegId='+fileRegId,
							type:'post',
							dataType:'json',
							success:function(r){
							 	if(r)
								{
							 		$('#fileAttachments').datagrid('loadData',r);
							 		$('#QAFileAttachments').datagrid('loadData',r);
								}
							}
						});
			    		
			    	}else{
			    		$.messager.alert('警告','文件上传失败'); 
			    	}
			    } 
			});  

			
		$.messager.progress({title: '请稍后',
				msg: '处理中...',text:''});	// 显示进度条
		$('#oneQAFileAttachment').submit();
		
	}
	
	function delQAFileAttachment2()
	{
		var row=$('#QAFileRegs').datagrid("getSelected");
		$.ajax({
				url:sybp()+'/qAFileRegAction_isExistTaskFile.action',
				type:'post',
				data:{
					fileRegId:row.fileRegId
				},
				dataType:'json',
				success:function(r){
					if(r&&!r.isExistTask)
					{
						$.messager.confirm('确认框','确定要删除该附件?',function(r){
						    if (r){
						        afterConfirmDelQAFileAttachment2();
						    }
						});
						
						
					}else{
						$.messager.alert('提示框','学习任务中包含该附件所属文件！');
					}
				}
			});
	}
	function afterConfirmDelQAFileAttachment2()
	{
		var row = $('#fileAttachments').datagrid('getSelected');
		if(row!=null)
		{
			$.ajax({
				url:sybp()+'/qAFileAttachmentAction_delete.action?attachmentId='+row.attachmentId,
				type:'post',
				dataType:'json',
				success:function(r){
				 	if(r)
					{
						var index = $('#fileAttachments').datagrid('getRowIndex',row);
						$('#fileAttachments').datagrid('deleteRow',index);
						$('#fileAttachments').datagrid('reload');
						$('#QAFileAttachments').datagrid('deleteRow',index);
						$('#QAFileAttachments').datagrid('reload');
						
					}
				}
			});
		}else
		{
			$.messager.alert("提示","请选择一个附件");
		}
	}
	function delQAFileAttachment()
	{
		
		var row = $('#QAFileAttachments').datagrid('getSelected');
		if(row!=null)
		{
			$.ajax({
				url:sybp()+'/qAFileAttachmentAction_delete.action?attachmentId='+row.attachmentId,
				type:'post',
				dataType:'json',
				success:function(r){
				 	if(r)
					{
						var index = $('#QAFileAttachments').datagrid('getRowIndex',row);
				 		$('#QAFileAttachments').datagrid('deleteRow',index);
						
					}
				}
			});
		}else
		{
			$.messager.alert("提示","请选择一个附件");
		}
		
	}
	function delQATaskFile2(learnTaskFileId,learnTaskId)
	{
		
		$.ajax({
			url:sybp()+'/qALearnTaskFileAction_del.action?learnTaskFileId='+learnTaskFileId,
			type:'post',
			dataType:'json',
			success:function(){
			 	
			}
		});
		$('#qaLearnTasks').treegrid('remove',learnTaskId);
 		
	}
	function delQATask2(learnTaskId)
	{
		$.ajax({
			url:sybp()+'/qALearnTaskAction_del.action?learnTaskId='+learnTaskId,
			type:'post',
			dataType:'json',
			success:function(){
			 	
			}
		});
		$('#qaLearnTasks').treegrid('remove',learnTaskId);
 		
		
	}
	function commitTask()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		var index = $('#taskListDatagrid').datagrid('getRowIndex',task);
		if(task!=null&&task!='')
		{
			$.ajax({
				url:sybp()+'/qALearnTaskAction_canCommit.action?learnTaskId='+task.learnTaskId,
				type:'post',
				dataType:'json',
				success:function(r){
					if(r&&r.success){
						qm_showQianmingDialog('afterSignCommitTask');	
					}else if(r&&!r.success){
						var msg = r.msg;
						if(r.noAttFileNames)
							msg+=r.noAttFileNames;
						$.messager.alert("提示",msg);
					}
				}
			});
		}else{
			$.messager.alert("提示","请选择要提交的任务");
		}
		
	}
	function afterSignCommitTask()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		var index = $('#taskListDatagrid').datagrid('getRowIndex',task);
		if(task!=null&&task!='')
		{
			//alert("提交任务："+task.learnTaskId);
			$.messager.progress({title: '请稍后',
					msg: '处理中...',text:''});	// 显示进度条
			$.ajax({
				url:sybp()+'/qALearnTaskAction_commit.action?learnTaskId='+task.learnTaskId,
				type:'post',
				dataType:'json',
				success:function(r){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					if(r&&r.success)
					{
						$('#taskListDatagrid').datagrid('updateRow',{
							index: index,
							row: {
								learnState:1,
							}
						});
						initTaskManButton(1);
						$('#taskFileList').datagrid('reload');
						$('#fileRegReaderList').datagrid('reload');

						var msg='提交学习任务后发邮件出现错误！';
						sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
						
					}else if(r&&!r.success){
						$.messager.alert("提示",r.msg);
					}
				}
			});
		}else{
			$.messager.alert("提示","请选择要提交的任务");
		}
	}
	
	function cancelTask()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		var index = $('#taskListDatagrid').datagrid('getRowIndex',task);
		if(task!=null&&task!='')
		{
			if(task.learnState==null||task.learnState==0||task.learnState==1)
			{
				$.messager.confirm('确认框','确定要撤销该任务?',function(r){
				    if (r){
				        if(task.learnState==1)
						{
							qm_showQianmingDialog('afterSignCancelTask');
						}else{
							afterSignCancelTask();
						}
				    }
				});
				
				
			}else if(task.learnState==2){	
				$.messager.alert("提示","该任务已经结束！");
			}
					
			
		}else{
			$.messager.alert("提示","请选择要撤销的任务");
		}
	}
	function afterSignCancelTask()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		var index = $('#taskListDatagrid').datagrid('getRowIndex',task);
		if(task!=null&&task!='')
		{
			//alert("提交任务："+task.learnTaskId);
			$.messager.progress({title: '请稍后',
					msg: '处理中...',text:''});	// 显示进度条
			$.ajax({
				url:sybp()+'/qALearnTaskAction_cancelTask.action?learnTaskId='+task.learnTaskId,
				type:'post',
				dataType:'json',
				success:function(r){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					if(r&&r.success)
					{
						$('#taskListDatagrid').datagrid('updateRow',{
							index: index,
							row: {
								learnState:-1,
							}
						});
						initTaskManButton(-1);
						$('#taskFileList').datagrid('reload');
						$('#fileRegReaderList').datagrid('reload');

						var msg='撤销学习任务后发邮件出现错误！';
						sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
						
					}else if(r&&!r.success){
						$.messager.alert("提示",r.msg);
					}
				}
			});
		}else{
			$.messager.alert("提示","请选择要撤销的任务！");
		}
	}
	

	 function sendNotification(msgTitle,msgContent,receiverList,msg){
			if(receiverList!=null&&receiverList!=''){
				
				  $.ajax({
			      	url : sybp()+'/qALearnTaskAction_sendNotification.action',
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
	//学习任务有关
	
	function operateQALearnTask()
	{
		document.getElementById("addQALearnTaskDialog2").style.display="block";
		
		$('#fileRegReaderListDiv').css('display','');
		$('#fileAttachmentListDiv').css('display','none');

		$('#openType').val(1);
		//isDelHidden = false;
		$('#taskFileList').datagrid('showColumn','operate');
		
		//$('#conformTaskFinish').css('display','');
		$('#conformTaskFinish').css('display','none');
		
		//"learnTaskId", "purpose","student","createTime","learnState"
		$('#taskListDatagrid').datagrid({
			url:sybp()+'/qALearnTaskAction_getTaskList.action',
			onSelect:function(rowIndex, rowData){
			
				//加载文件
				$('#taskFileList').datagrid({
					url:sybp()+'/qALearnTaskFileAction_getFileListByTaskId.action?learnTaskId='+rowData.learnTaskId,
				});
				//$('#taskFileList').datagrid('reload');
				
				//加载学习者
				$('#fileRegReaderList').datagrid({
					url:sybp()+'/qAFileRegReaderAction_getReadersByTaskId.action?learnTaskId='+rowData.learnTaskId,	
				});
				//$('#fileRegReaderList').datagrid('reload');
				
				initTaskManButton(rowData.learnState);
				
			}
		});
		
		$('#taskFileList').datagrid('loadData',[]);
		//$('#fileRegReaderList').datagrid('loadData',[]);//没找到为什么不行
		
		//$('#fileRegReaderList').datagrid('loadData',[]);
		
		$('#newQALearnTaskButton').css('display','');
		$('#delQALearnTaskButton').css('display','');
		$('#chooseTaskFileButton').css('display','');
		$('#chooseLearPersonButton').css('display','');
		$('#commitTaskButton').css('display','');
		$('#cancelTaskButton').css('display','');

		$('#conformTaskFinish').css('display','none');
		
	
		$('#addQALearnTaskDialog').dialog('setTitle','新建学习任务');
		$('#addQALearnTaskDialog').dialog('open'); 
		
	}
	
	function searchTaskByState()
	{
		var learnState=$('#taskTypeForTaskPage').combobox('getValue');
		var openType = $('#openType').val();
		
		var url = sybp()+'/qALearnTaskAction_getTaskList.action?learnState='+learnState;
		if(openType==1)
		{
			url=sybp()+'/qALearnTaskAction_getTaskList.action?learnState='+learnState;

		}else if(openType==2){
			url=sybp()+'/qALearnTaskAction_getTaskListByUser.action?learnState='+learnState;
		}
		$('#taskListDatagrid').datagrid({
			url:url,
		});
		$('#taskListDatagrid').datagrid('reload');
		
		$('#taskFileList').datagrid('loadData',[]);
		$('#fileRegReaderList').datagrid('loadData',[]);
	}
	
	function addOneQALearnTask()
	{
		document.getElementById("addOneQALearnTaskDialog2").style.display="block";
		$('#onePurpose').val('');
		
		$('#addOneQALearnTaskDialog').dialog('setTitle','新建学习任务');
		$('#addOneQALearnTaskDialog').dialog('open'); 
		
	}
	function saveOneQALearnTask()
	{
		var purpose = $('#onePurpose').val();
		var oneStudent = $('#oneStudent').combobox('getValue');
		if(purpose!=null)
		{
			$.ajax({
				url:sybp()+'/qALearnTaskAction_saveOneTask.action',
				type:'post',
				data:{
					purpose:purpose,
					student:oneStudent
				},
				dataType:'json',
				success:function(r){
					if(r&&r.success){
						$('#addOneQALearnTaskDialog').dialog('close');
						
						$('#taskListDatagrid').datagrid('appendRow',{
								learnTaskId: r.learnTaskId,
								createTime: r.createTime,
								purpose: r.purpose,
								learnState:0,
								student:r.student,
						});
					}
				 	
				}
			});
		}else{
			$.messager.alert("提示","请填写培训目的");
			
		}
			
		
	}
	function delOneQALearnTask()
	{
		var row = $('#taskListDatagrid').datagrid('getSelected');
		if(row!=null)
		{
			
			$.messager.confirm('确认框','删除学习任务的同时会删除任务的学习记录，确定要删除该学习任务吗?',function(r){
			    if (r){
				    qm_showQianmingDialog('afterSignDelOneQALearnTask');
			    }
			});
			
		}else{
			$.messager.alert('提示框','请选择一个任务！');
			
		}
		
	}
	function afterSignDelOneQALearnTask()
	{
		var row = $('#taskListDatagrid').datagrid('getSelected');
		var index = $('#taskListDatagrid').datagrid('getRowIndex',row);
		//删除一个任务
			$.ajax({
				url:sybp()+'/qALearnTaskAction_delOneTask.action?learnTaskId='+row.learnTaskId,
				type:'post',
				dataType:'json',
				success:function(r){
					$('#taskListDatagrid').datagrid('deleteRow',index);
					//$('#taskListDatagrid').datagrid('reload');
					$('#taskFileList').datagrid('loadData',[]);
					$('#fileRegReaderList').datagrid('loadData',[]);
				}
			});
	}
	
	function chooseFileReg()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		if(task!=null)
		{
			document.getElementById("chooseTaskFileDialog2").style.display="block";
			//添加学习任务的时候的树形文件类型结构数据
			$('#addFileTaskFileTypes').tree({
				url: sybp()+'/qAFileTypeAction_loadList.action',
				
				onSelect: function(node){
					$('#oneaddTaskFileReg').datagrid({
						url:sybp()+'/qAFileRegAction_loadListByType.action?fileTypeId='+node.id,
						
					
					});
				}
			});
			/*
			$('#addTaskFileRegs').datagrid({
				url:sybp()+'/qALearnTaskFileAction_getFileListByTaskId.action?learnTaskId='+task.learnTaskId,
				
			});
			*/
			
			$('#chooseTaskFileDialog').dialog('setTitle','选择任务文件');
			$('#chooseTaskFileDialog').dialog('open'); 
	
		}else{
			$.messager.alert("提示","请选择一个任务");
		}
	}
	function saveTaskFileList()
	{
		var oneaddTaskFileRegs = $('#addTaskFileRegs').datagrid("getRows");//文件
		var task = $('#taskListDatagrid').datagrid('getSelected');
		if(oneaddTaskFileRegs!=null&&oneaddTaskFileRegs!='')
		{
			var regs = new Array();
			for(var i=0;i<oneaddTaskFileRegs.length;i++)
			{
				regs.push(oneaddTaskFileRegs[i].fileRegId);
			}
			//alert("resg="+regs);
			$.ajax({
				url:sybp()+'/qALearnTaskFileAction_saveFileList.action?learnTaskId='+task.learnTaskId+'&fileList='+regs,
				type:'post',
				
				dataType:'json',
				success:function(r){
					if(r){
						if(r.flag)
						{
							$.messager.alert("提示","部分文件在本任务下已经存在，只添加了不存在的文件");
						}
						var index = $('#taskListDatagrid').datagrid("getRowIndex",task);
						$('#taskListDatagrid').datagrid('selectRow',index);
						$('#chooseTaskFileDialog').dialog('close'); 
					}
				}
			});
			
		}else{
			$.messager.alert("提示","没有选择文件");
		}
		
		
	}
	function chooseLearnPeople()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		if(task!=null)
		{
			document.getElementById("chooseLearnPeopleDialog2").style.display="block";
			$.ajax({
				url:sybp()+'/qAFileRegReaderAction_getListByStudent.action',
				type:'post',
				data:{
					student:task.student,
				},
				dataType:'json',
				success:function(r){
					/*$('#peopleForStudent').datagrid({
						url:sybp()+'/qAFileRegReaderAction_getListByStudent.action?student='+task.student,
					});*/
					$('#peopleForStudent').datagrid('loadData',r);
				}
			});
			
			$('#chooseLearnPeopleDialog').dialog('setTitle','选择任务学习者');
			$('#chooseLearnPeopleDialog').dialog('open'); 
		}else{
			$.messager.alert("提示","请选择一个任务");
		}
			
	}
	function saveLearnPeople()
	{
		var people = $('#peopleForStudent').datagrid('getSelections');
		var task = $('#taskListDatagrid').datagrid('getSelected');
		
		if(people!=null&&people!=''){
			var regs = new Array();
			for(var i=0;i<people.length;i++)
			{
				regs.push(people[i].id);
			}
			//alert("resg="+regs);
			$.ajax({
				url:sybp()+'/qAFileRegReaderAction_saveStudents.action?learnTaskId='+task.learnTaskId+'&studentList='+regs,
				type:'post',
				
				dataType:'json',
				success:function(r){
					if(r)
					{
						if(r.flag)
						{
							$.messager.alert("提示","部分学习者已经存在，只增加了不存在的学习者");
						}
						var index = $('#taskListDatagrid').datagrid("getRowIndex",task);
						$('#taskListDatagrid').datagrid('selectRow',index);
						
						$('#chooseLearnPeopleDialog').dialog('close'); 
					}
				}
			});
			
		}else{
			$.messager.alert("提示","请选择学习人员");
		}
		
		
	}
	
	//原先的，无效
	function saveLearnTask()
	{
		var oneaddTaskFileRegs = $('#addTaskFileRegs').datagrid("getRows");//文件
		var oneStudent = $('#oneStudent').val();
		var onePlanFinishTime = $('#onePlanFinishTime').combobox('getValue');
		var onePurpose = $('#onePurpose').val();
		if(oneaddTaskFileRegs!=null&&oneStudent!=null&&onePlanFinishTime!=null)
		{
			var regs = new Array();
			for(var i=0;i<oneaddTaskFileRegs.length;i++)
			{
				regs.push(oneaddTaskFileRegs[i].fileRegId);
			}
			//alert("resg="+regs);
			$.ajax({
				url:sybp()+'/qALearnTaskAction_save.action?fileRegIds='+regs,
				type:'post',
				data:$('#oneQAFileTaskList').serialize(),
				dataType:'json',
				success:function(r){
					if(r ){
						$('#updateFileTypeID').val(r.fileTypeId);
						$('#oneFileType').combobox('setValue',r.fileType);
						$('#oneFileType').combobox('select',r.fileType);
						$('#oneFileTypeName').val(r.fileTypeName);
						$('#oneParentFileTypeID').combobox('setValue',r.parentFileTypeId);
						$('#addQALearnTaskDialog').dialog('close'); 
						$('#qaLearnTasks').treegrid({
							url:sybp()+'/qALearnTaskFileAction_list.action',
						});
					}
				}
			});

		}else{
			$.messager.alert('提示','请填写完整');
		}
		
		
	}
	function delAddTaskFile()
	{
		var row = $('#addTaskFileRegs').datagrid('getSelected');
		var index = $('#addTaskFileRegs').datagrid('getRowIndex',row);
		$('#addTaskFileRegs').datagrid('deleteRow',index);
	}
	function confirmLearnTask()
	{	//对一个任务来说的
		document.getElementById("addQALearnTaskDialog2").style.display="block";
		$('#fileRegReaderListDiv').css('display','none');
		$('#fileAttachmentListDiv').css('display','');
		
		$('#openType').val(2);
		$('#taskFileList').datagrid('hideColumn','operate');
		
		//"learnTaskId", "purpose","student","createTime","learnState"
		$('#taskListDatagrid').datagrid({
			url:sybp()+'/qALearnTaskAction_getTaskListByUser.action',
			onSelect:function(rowIndex, rowData){
				
				//加载文件
				$('#taskFileList').datagrid({
					url:sybp()+'/qALearnTaskFileAction_getFileListByTaskId.action?learnTaskId='+rowData.learnTaskId,
					onSelect:function(rowIndex2,rowData2){
					 	var openType = $('#openType').val();
						if(openType==2){//我的学习任务
							//加载附件
							$('#fileAttachmentList').datagrid({
								url:sybp()+'/qAFileAttachmentAction_getLearnAttListByFileRegId.action?learnTaskId='+rowData.learnTaskId+'&fileRegId='+rowData2.fileRegId,
							});
							//$('#fileAttachmentList').datagrid('reload');
							
						}
				 
				  	}
				});
				$('#fileAttachmentList').datagrid('loadData',[]);
				
				 initTaskManButton(rowData.learnState);
				
			},
			onLoadSuccess:function(data){
				if($('#selectLearnTaskId').val()!=null&&$('#selectLearnTaskId').val()!='')
				{
					$('#taskListDatagrid').datagrid('selectRecord',$('#selectLearnTaskId').val());
				}
			}
		});
		
		$('#taskFileList').datagrid('loadData',[]);		
		$('#fileAttachmentList').datagrid('loadData',[]);
		
		
		$('#newQALearnTaskButton').css('display','none');
		$('#delQALearnTaskButton').css('display','none');
		$('#chooseTaskFileButton').css('display','none');
		$('#chooseLearPersonButton').css('display','none');
		$('#commitTaskButton').css('display','none');
		$('#cancelTaskButton').css('display','none');
		
		//$('#conformTaskFinish').css('display','');
		$('#conformTaskFinish').css('display','none');
		
		
		$('#addQALearnTaskDialog').dialog('setTitle','我的学习任务');
		$('#addQALearnTaskDialog').dialog('open'); 
		
		/*
		var row=$('#QAFileRegs').datagrid("getSelected");
		if(row!=null)
		{
			//如果需要前面加在这里，
			$.ajax({
				url:sybp()+'/qALearnTaskFileAction_confirmLearnFinish.action',
				type:'post',
				data:{
					fileRegId:row.fileRegId
				},
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$.messager.alert("提示","确认学习完成成功！");
					}
					else if(r&&!r.success)
					{
						$.messager.alert("提示",r.msg);
					}
				}
			});
			
		}else
		{
			$.messager.alert("提示","请选择一个文件");
		}
		*/
		
	}
	function downloadOneAttachment(attachmentId)
	{
	
		var task = $('#taskListDatagrid').datagrid('getSelected');
	
		if(task==null||task=='')
		{
			$.messager.alert("提示","请选择一个任务");
		}else{
			var row=$('#taskFileList').datagrid("getSelected");
			if(row!=null)
			{
				
				$.ajax({
					url:sybp()+'/qAFileAttachmentAction_isExistAtt.action?learnTaskId='+task.learnTaskId+'&fileRegId='+row.fileRegId+'&attachmentId='+attachmentId,
					type:'post',
					
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							//learnTaskId,fileRegId
							window.location.href=sybp()+'/qAFileAttachmentAction_downloadAttachment.action?learnTaskId='+task.learnTaskId+'&fileRegId='+row.fileRegId+'&attachmentId='+attachmentId;
							
							var attRow = $('#fileAttachmentList').datagrid('getSelected');
							if(attRow!=null&&attRow!=''&&attRow.operator!=2){
								var index = $('#fileAttachmentList').datagrid('getRowIndex',attRow);
								$('#fileAttachmentList').datagrid('updateRow',{
									index: index,
									row: {
										operator:1,//学习中
									}
								});
							}
							
						}else {
							$.messager.alert("提示","该附件不存在！");
						}
					}
				});
				
			}
			
			
		}
	} 
	
	function finishLearnAttachment(attachmentId)
	{
		$('#oneAttachmentId').val(attachmentId);
		var task = $('#taskListDatagrid').datagrid('getSelected');
		if(task==null||task=='')
		{
			$.messager.alert("提示","请选择一个任务");
		}else{
			var row=$('#taskFileList').datagrid("getSelected");
			if(row!=null)
			{
			//learnTaskId,fileRegId
				$.ajax({
					url:sybp()+'/qAFileAttachmentAction_canFinishLearnAtt.action?learnTaskId='+task.learnTaskId+'&fileRegId='+row.fileRegId+'&attachmentId='+attachmentId,
					type:'post',
					
					dataType:'json',
					success:function(r){
					
						if(r&&r.success){
							qm_showQianmingDialog('afterSignFinishLearnAttch');
						}else if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
					
					}
				});
			
		
			}
		}
	}
	function afterSignFinishLearnAttch()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		
		var row=$('#taskFileList').datagrid("getSelected");
		var attachmentId = $('#oneAttachmentId').val();
		//learnTaskId,fileRegId
		$.ajax({
			url:sybp()+'/qAFileAttachmentAction_finishLearnAttachment.action?learnTaskId='+task.learnTaskId+'&fileRegId='+row.fileRegId+'&attachmentId='+attachmentId,
			type:'post',
					
			dataType:'json',
			success:function(r){
					
				if(r&&r.success){
					var attach = $('#fileAttachmentList').datagrid('getSelected');
					var index = $('#fileAttachmentList').datagrid('getRowIndex',attach);
					
							
					if(r.taskReadFinish){
						var taskIndex = $('#taskListDatagrid').datagrid('getRowIndex',task);
						$('#taskListDatagrid').datagrid('updateRow',{
							index: taskIndex,
							row: {
								learnState:2,
							}
						});
						$('#fileAttachmentList').datagrid('updateRow',{
							index: index,
							row: {
								readFinishFlag:1,
								operator:2,
							}
						});
							
					}else{
						$('#fileAttachmentList').datagrid('updateRow',{
							index: index,
							row: {
								readFinishFlag:1,
							}
						});
					}
							
			}else if(r&&!r.success){
				$.messager.alert("提示",r.msg);
			}
					
		 }
		});
			
	}
	//确认一个任务完成，失效
	function conformTaskFinish()
	{
		var task = $('#taskListDatagrid').datagrid('getSelected');
		
		if(task==null||task=='')
		{
			$.messager.alert("提示","请选择一个任务");
		}else{
			$.ajax({
				url:sybp()+'/qALearnTaskAction_confirmFinish.action?learnTaskId='+task.learnTaskId,
				type:'post',
				
				dataType:'json',
				success:function(r){
					
					var index = $('#taskListDatagrid').datagrid('getRowIndex',task);
					if(r&&r.finish){
						$('#taskListDatagrid').datagrid('updateRow',{
							index: index,
							row: {
								learnState:2,
							}
						});
						
					}
					if(r&&r.success){
						$('#taskListDatagrid').datagrid('reload');
						//$('#taskListDatagrid').datagrid('selectRow',index);
						$('#taskFileList').datagrid('loadData',[]);
						$('#fileRegReaderList').datagrid('loadData',[]);
					}else if(r&&!r.success){
						$.messager.alert("提示",r.msg);
					}
					
				}
			});
			
		}

		
	}
	
	function addQAReaderComment()
	{
		var row=$('#QAFileRegs').datagrid("getSelected");
		if(row!=null)
		{
			$.ajax({
				url:sybp()+'/qAFileRegReaderAction_hasAddedComment.action',
				type:'post',
				data:{
					fileRegId:row.fileRegId
				},
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						/* 显示Dialog */
						document.getElementById("addOrEditQAFileCommentDialog2").style.display="block";
			
						$('#commentLearnRecordId').val(r.learnRecordId);
						$('#commentText').val('');
						
						$('#addOrEditQAFileCommentDialog').dialog('setTitle','添加文件评论');
						$('#addOrEditQAFileCommentDialog').dialog('open'); 
						
					}
					else if(r&&!r.success)
					{
						$.messager.alert("提示",r.msg);
					}
				}
			});
			
			
		}else
		{
			$.messager.alert("提示","请选择一个文件");
		}
		
	}
	function saveOneLearnRecordComment()
	{
		
		$.ajax({
			url:sybp()+'/qAFileRegReaderAction_saveComment.action',
			type:'post',
			data:$('#oneLearnRecordComment').serialize(),
			dataType:'json',
			success:function(r){
				if(r&&!r.success)
				{
					$.messager.alert("提示",r.msg);
				}
			}
		});
		$('#addOrEditQAFileCommentDialog').dialog('close');
		 
	}
	
	function delQALearnFile(learnTaskFileId)
	{
		$.ajax({
			url:sybp()+'/qALearnTaskFileAction_del.action?learnTaskFileId='+learnTaskFileId,
			type:'post',
			data:$('#oneLearnRecordComment').serialize(),
			dataType:'json',
			success:function(){
				
					var row = $('#taskFileList').datagrid('getSelected');
					var index = $('#taskFileList').datagrid('getRowIndex',row);
					$('#taskFileList').datagrid('deleteRow',index);
				
				
			}
		});
		

	}
	function delQAFileReader(learnRecordId)
	{
		$.ajax({
			url:sybp()+'/qAFileRegReaderAction_del.action?learnRecordId='+learnRecordId,
			type:'post',
			data:$('#oneLearnRecordComment').serialize(),
			dataType:'json',
			success:function(){
				
					var row = $('#fileRegReaderList').datagrid('getSelected');
					var index = $('#fileRegReaderList').datagrid('getRowIndex',row);
					$('#fileRegReaderList').datagrid('deleteRow',index);
				
			}
		});
		
	}
	function searchFileForAddTaskFile()
	{
		var fileTypeCondition = $('#fileTypeConditionForAdd').combobox('getValue');
		var fileStateCondition = $('#fileStateConditionForAdd').combobox('getValue');
		var value = $('#fileSearchConditionForAdd').searchbox('getValue');
		//alert(fileTypeCondition+"==="+fileStateCondition+"===="+value);
		$('#oneaddTaskFileReg').datagrid({
			url:sybp()+'/qAFileRegAction_loadListByCondition.action?fileType='+fileTypeCondition+'&fileStateCondition='+fileStateCondition+'&fileSearchCondition='+value,
		});
		$('#oneaddTaskFileReg').datagrid('reload');
		
	}
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$('#wholeFileRegPage').css('display','');
		
    	parent.$('#topInfoLabel').html('');
    		
		var tableSize ;
		var tableHeight = document.body.clientHeight-75;   //其他组件的高度
		var tableWidth = document.body.clientWidth-20;
		
	
	$('#QAFileRegs').datagrid({
		height:tableHeight-4,
	//	width:tableWidth, 
	//	width:680,
		singleSelect:true,
		columns:[[
		       //  "fileRegId", "fileType", 
		  	    
					{
					    field:'fileRegId',
					    title:'',
					    hidden:true
					 },
					 {
						    field:'fileType',
						    title:'文件类型',
						  	 width:70,
						  	formatter: function(value,row,index){
								if (value==1){
									return "法规";
								} else if (value==2){
									return "指导原则";
								} else if (value==3){
									return "SOP";
								}
							}
					 },
					 {
				          field:'fileTypeName',
				          title:'文件类别',
				          width:130
				        
				      },
				      //"fileTypeName","fileCode",
				       {
					          field:'fileCode',
					          title:'编号',
					          width:80
					        
					   },
					   {
					          field:'fileName',
					          title:'文件名称',
					          width:250
					        
					   },
					  // "fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
					   {
					          field:'fileVersion',
					          title:'版本',
					          width:50
					        
					   },
					   {
					          field:'filePublishDepartment',
					          title:'颁发机构',
					          width:100
					        
					   },
					   
					   {
					          field:'remark',
					          title:'remark',
					          hidden:true
					        
					   },
					   
					   {
					          field:'filePublishTime',
					          title:'颁布时间',
					          width:80
					        
					   },
					   
					   {
					          field:'isVersionUpdate',
					          title:'',
					          hidden:true
					        
					   },
		]],
		onSelect:function(rowIndex, rowData){
			//加载该文件的附件信息
			$('#QAFileAttachments').datagrid({
				url:sybp()+'/qAFileAttachmentAction_getListByFileRegId.action?fileRegId='+rowData.fileRegId,
			});
			//加载该文件的评论信息
			$('#QAFileComments').datagrid({
				url:sybp()+'/qAFileRegReaderAction_getCommentByFileRegId.action?fileRegId='+rowData.fileRegId,
				
			});
			//加载该文件的附件信息
			$('#QAFileAttachmentDG').datagrid({
				url:sybp()+'/qAFileAttachmentAction_getListByFileRegId.action?fileRegId='+rowData.fileRegId,
				
			});
		},
		onUnselect:function(rowIndex, rowData){
			//清空该文件的附件信息
			$('#QAFileAttachmentDG').datagrid('loadData',[]);
		}
	
	});
	$('#QAFileAttachmentDG').datagrid({
		    height:tableHeight-4,
		//	width:tableWidth, 
			//width:200,
			singleSelect:true,
			columns:[[
//"attachmentId", "attachmentName","fileName","remark","appendDate","operator"
						{
						    field:'attachmentId',
						    title:'',
						    hidden:true
						 },
						 {
							    field:'attachmentName',
							    title:'附件名称',
							  	width:130,
							  	
						 },
						 {
					          field:'operator',
					          title:'操作',
					          width:50,
					          formatter: function(value,row,index){
									return "<a href="+sybp()+"/qAFileAttachmentAction_downloadAttachment.action?attachmentId="+row.attachmentId+">下载</a>";
							  }
					          
					        
					      },
					     
						 
			]],
	});
	
	$('#QAFileTypes').tree({
			url: sybp()+'/qAFileTypeAction_loadList.action',
			onSelect: function(node){
				$('#QAFileRegs').datagrid({
					url:sybp()+'/qAFileRegAction_loadListByType.action?fileTypeId='+node.id,
				});
				$('#QAFileRegs').datagrid('reload');
				$('#QAFileAttachmentDG').datagrid('loadData',[]);
			}
	});

	
	$('#QAFileAttachments').datagrid({
		height:tableHeight/2-30,
		//width:tableWidth/2-10, 
		columns:[[
			{
			    field:'attachmentId',
			    title:'',
			    hidden:true
			 },
			 {
				    field:'attachmentName',
				    title:'附件名称',
				    width:150
			},
			 {
			    field:'operator',
			    title:'操作',
			    width:50,
			    formatter:function(value,row,index){
					return "<a href="+sybp()+"/qAFileAttachmentAction_downloadAttachment.action?attachmentId="+row.attachmentId+">下载</a>";
         			//return "<a href='javascript:downloadAttachment("+row.attachmentId+");'></a>";
         		}
		},
		]],
	});
	
	$('#QAFileComments').datagrid({
		height:tableHeight/2-30,
		width:tableWidth/2-10, 
		columns:[[
			       //  "fileRegId", "fileType", 
			  	    
						{
						    field:'fileRegId',
						    title:'',
						    hidden:true
						 },
						 {
							    field:'fileType',
							    title:'文件类型',
							  	 width:100,
							  	formatter: function(value,row,index){
									if (value==1){
										return "法规";
									} else if (value==2){
										return "指导原则";
									} else if (value==3){
										return "SOP";
									}
								}
						 },
						 {
					          field:'fileTypeName',
					          title:'文件类别',
					          width:100
					        
					      },
					      //"fileTypeName","fileCode",
					       {
						          field:'fileCode',
						          title:'编号',
						          width:100
						        
						   },
						   {
						          field:'fileName',
						          title:'文件名称',
						          width:150
						        
						   },
						  // "fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
						   {
						          field:'fileVersion',
						          title:'版本',
						          width:100
						        
						   },
						   {
						          field:'filePublishDepartment',
						          title:'颁发机构',
						          width:150
						        
						   },
				]],
	});

	$('#fileAttachments').datagrid({
		height:300,
		width:500,
		singleSelect:true,
		columns:[[
		       	{
					    field:'attachmentId',
					    title:'',
					    hidden:true,
					 },
					   {
					          field:'attachmentName',
					          title:'附件名称',
					          width:250,
					        
					   },
					   {
					          field:'readMinute',
					          title:'需学习时间',
					          width:50,
					          editor:'text',
					   },
					   {
					          field:'readMinuteUnit',
					          title:'时间单位',
					          width:50,
					          editor:{
								 type:'combobox',
								 options:{
								 	valueField:'id',
									textField:'text',
									url: sybp()+'/qAFileAttachmentAction_readTimeUnitList.action',	
								 }
							  },
							  formatter: function(value,row,index){
									if (value==1){
										return "分钟";
									} else if (value==2){
										return "小时";
									}
							  },
					   },
					   {
					          field:'operator',
					          title:'操作',
					          width:130,
					      	  halign:'center',
							  align:'center',
					          formatter:function(value,row,index){
				         		return "<a href='javascript:delQAFileAttachment2();'>删除</a>";
				         		
							  }
						}
				
				]],
				
				onSelect: function(rowIndex, rowData){
					if($('#lastEditRowIndex').val()!='')
					{
						$(this).datagrid('endEdit', $('#lastEditRowIndex').val());
					}
		        	$(this).datagrid('beginEdit', rowIndex);
		        	$('#lastEditRowIndex').val(rowIndex);
		        },
		        onDblClickRow:function(rowIndex, rowData){
		        	if(rowData.readMinuteUnit!=null||rowData.readMinute!=null)
					{
						if(!isNaN(rowData.readMinute))
						{
							$.ajax({
								url:sybp()+'/qAFileAttachmentAction_updateMinute.action?attachmentId='+rowData.attachmentId,
								type:'post',
								data:{
									readMinute:rowData.readMinute,
									readMinuteUnit:rowData.readMinuteUnit,
								},
								dataType:'json',
								success:function(r){
								    
								}
								
							});
						}else{
							$.messager.alert('提示框','阅读时间必须是数字！');
							var ed = $('#dg').datagrid('getEditor', {index:rowIndex,field:'readMinute'});
							$(ed.target).val('');
							
						}
						
					
					}
		        },
				onAfterEdit:function(rowIndex, rowData, changes)
				{
					if(changes.readMinuteUnit!=null||changes.readMinute!=null)
					{
						if(!isNaN(rowData.readMinute))
						{
							$.ajax({
								url:sybp()+'/qAFileAttachmentAction_updateMinute.action?attachmentId='+rowData.attachmentId,
								type:'post',
								data:{
									readMinute:rowData.readMinute,
									readMinuteUnit:rowData.readMinuteUnit,
								},
								dataType:'json',
								success:function(r){
								    
								}
								
							});
						}else{
							$.messager.alert('提示框','阅读时间必须是数字！');
							var ed = $('#dg').datagrid('getEditor', {index:rowIndex,field:'readMinute'});
							$(ed.target).val('');
							
						}
						
					
					}
					
				}
			});
//"learnTaskId", "purpose","student","createTime","learnState"
	$('#taskListDatagrid').datagrid({
		height:320,
		width:300,
		singleSelect:true,
		idField:'learnTaskId',
		columns:[[
		  		{
		  		    field:'learnTaskId',
		  		    title:'',
		  		    hidden:true
		  		 },
		  		 {
		  	          title:'培训任务列表',
		  	          field:'createTime',
		  	          width:80,
		  	        
		  	      },
		  	      {
		  	          title:'培训对象',
		  	          field:'student',
		  	          width:50,
		  	        
		  	      },
		  	    {
		  	          title:'状态',
		  	          field:'learnState',
		  	          width:45,
		  	       	 formatter:function(value,row,index){
			  	       	 //0：未提交；1：学习中（已提交）；2：完成
			  	       	 if(value==null||value==0)
			  	       	 {
				  	     	return "未提交";  	 
				  	     } else if(value==1){
							return "学习中";
					  	 }else if(value==2){
							return "完成";
					  	 }else if(value==-1){
					  	 	return "撤销";
					  	 }
		         		
					  }
		  	        
		  	      },
		  	    {
		  	          title:'培训目的',
		  	          field:'purpose',
		  	          width:100
		  	        
		  	      },
		  	   
		  ]],
		
		
	});
	// ; ;fileType;//1：法规；2：指导原则；3：SOP
	 $('#taskFileList').datagrid({
		 height:320,
		 width:380,
		 singleSelect:true,
		 columns:[[
		           {
		        	   title:'',
		        	   field:'learnTaskFileId',
			  		    hidden:true
			         },
			        {
			           title:'',
			           field:'taskState',
				  		hidden:true
				    },
			  		{
			  		    title:'',
			  		    field:'fileRegId',
			  		    hidden:true
			  		 },
			  		 {
			  			title :'文件类型',
			  	        field:'fileType',
			  	          width:50,
			  	          formatter:function(value,row,index){
							if(value==1){
								return "法规";
							}else if(value==2){
								return "指导原则";
							}else if(value==3){
								return "SOP";
							}
					  	  }
			  	        
			  	      },
			  	    //   fileTypeName; fileCode; fileName;fileVersion; filePublishTime;filePublishDepartment;remark;isVersionUpdate;
			  		
			  	      {
			  	    	title :'文件类别',
			  	        field:'fileTypeName',
			  	          width:80,
			  	        
			  	      },
			  	    {
			  	    	title:'编号',
			  	    	field:'fileCode',
			  	        width:45,
			  	       	
			  	        
			  	      },
			  	    {
			  	    	title:'文件名称',
			  	    	field :'fileName',
			  	          width:100
			  	        
			  	      },
			  	    {
			  	    	title:'版本',
			  	    	field :'fileVersion',
			  	          width:40
			  	        
			  	      },
			  	    {
			  	    	field :'operate',
			  	    	title :'操作',
				          width:40,
				      	  halign:'center',
						  align:'center',
				          formatter:function(value,row,index){
				          	if($('#openType').val()=='1')
				          	{
					          	if(row.taskState==null||row.taskState==0)
				         			return "<a href='javascript:delQALearnFile("+row.learnTaskFileId+");'>删除</a>";
				         		else
					         		return "删除";
				          	}else return "删除";
				          	
						  }
					},
			  ]],
			
	});

	
	$('#peopleForStudent').datagrid({
		height:300,
		width:180,
		 columns:[[
			  		{
			  		    field:'id',
			  		    title:'',
			  		    hidden:true
			  		 },
			  		 {
			  			title:'姓名',
			  	        field:'realName',
			  	          width:150,
			  	          
			  	      },
			  	   
			  ]],
	});

	
	$('#qaLearnTasks').treegrid({
		url:sybp()+'/qALearnTaskFileAction_list.action',
		title:'',
		iconCls:'',//'icon-save',
		singleSelect:true,//只能选一行
		pagination:false,//下面状态栏
		//height:tableHeight,
		//width:tableWidth,  
		//fit:true,
		fitColumns:false,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		border:false,
		idField:'learnTaskId', //pk
		treeField:'fileName', 
		columns:[[
					{
						 field:'learnTaskId',
						 title:'任务Id',
						 width:150,
						 hidden:true
					},
					
					
					{
					    field:'learnTaskFileId',//删除taskfile时有用
					    title:'id',
					    hidden:true
					 },
					 {
							field:'fileRegId',
							 title:'文件id',
							 hidden:true
							
					},
					 /*
					 {
							field:'createTime',
							 title:'任务创建时间',
							 width:100
							
					},*/
					{
						field:'fileName',
						 title:'文件名称',
						 width:150,
						
					},
					 {
						    field:'learnState',
						    title:'任务状态',
						  	 width:60,
						  	formatter: function(value,row,index){
								if (value==0){
									return "未提交";
								} else if (value==1){
									return "学习中";
								} else if (value==2){
									return "已完成";
								}
							}
					 },
					 {
							field:'student',
							 title:'学习对象',
							 width:150
							
					},
					
						{
							field:'operate',
							 title:'操作',
							 width:150,
							 formatter:function(value,row,index){
						 		if(row.learnState==0)
								{
				         			if(row.fileRegId!=null)//file
									{
										return "<a href='javascript:delQATaskFile2("+row.learnTaskFileId+","+row.learnTaskId+");'>删除</a>";
										
									}else//task
									{
										return "<a href='javascript:commitTask("+row.learnTaskId+");'>提交</a>|<a href='javascript:delQATask2("+row.learnTaskId+","+row.learnTaskId+");'>删除</a>";
										
									}
								}
								
						  	}

						},
						
				]],
		onSelect:function(row){
			
		},
		onLoadSuccess:function(row,data){
			
			
		}
	});//end treegrid
	$('#addTaskFileRegs').datagrid({
		showHeader:false,
		height:350,
		width:200,
		singleSelect:true,
		columns:[[
		{
		    field:'fileRegId',
		    title:'',
		    hidden:true
		 },
		 {
	          field:'fileName',
	          title:'文件名称',
	          width:135,
	        
	      },
	      {
	          field:'operate',
	          title:'操作',
	          width:45,
	          formatter: function(value,row,index){
				
				return  "<a href='javascript:delAddTaskFile();'>删除</a>";
				
			}
	        
	      },

		]],
	});

	$('#addFileTaskFileTypes').tree({
		height:300,
		width:230,
	});
	$('#oneaddTaskFileReg').datagrid({
		height:350,
		width:200,
		singleSelect:true,
		columns:[[
		{
		    field:'fileRegId',
		    title:'',
		    hidden:true
		 },
		 {
	          field:'fileName',
	          title:'文件名称',
	          width:179
	        
	      },

		]],
		onDblClickRow:function(rowIndex, rowData){
			$('#addTaskFileRegs').datagrid('appendRow',{
				fileRegId:rowData.fileRegId,
				fileName:rowData.fileName,
			});	
		}
	});
	
	$('#fileAttachmentList').datagrid({
			height:320,
			width:260,
			singleSelect:true,
			columns:[[
				{
				    field:'attachmentId',
				    title:'',
				    hidden:true
				 },
				 {
					    field:'attachmentName',
					    title:'附件名称',
					    width:140,
				},
				{
				    field:'fileReadRecordId',//附件学习记录
				    title:'',
				    hidden:true,
				 },
				 {
				    field:'operator',
				    title:'状态',
				    width:40,
				    formatter:function(value,row,index){
						//return "<a href="+sybp()+"/qAFileAttachmentAction_downloadAttachment.action?attachmentId="+row.attachmentId+">学习</a>";
	         			if(value==1){
	         				return "<a href='javascript:downloadOneAttachment("+row.attachmentId+");'>学习中</a>";
	         			}else if(value==2){
	         				return "<a href='javascript:downloadOneAttachment("+row.attachmentId+");'>完成</a>";;
	         			}else{
	         				return "<a href='javascript:downloadOneAttachment("+row.attachmentId+");'>学习</a>";
	         			}
	         		}
	         	},
	         	{
				    field:'readFinishFlag',
				    title:'操作',
				    width:60,
				    formatter:function(value,row,index){
						//return "<a href="+sybp()+"/qAFileAttachmentAction_finishAttachmentLearn.action?attachmentId="+row.attachmentId+">学习完成</a>";
	         			if(value==1)
	         			{
	         				return "已完成";
	         			}else{
	         				return "<a href='javascript:finishLearnAttachment("+row.attachmentId+");'>确认完成</a>";
	         			}
	         		}
	         	},
			
			]],
		});
	$('#fileRegReaderList').datagrid({
			height:320,
			width:255,
			singleSelect:true,
			//"learnRecordId", "readerName","readerCode","learnState","createTime","planFinishTime","finishTime","remark"
			columns:[[
					  		{
					  			title :'learnRecordId',
					  		    field :'',
					  		    hidden:true
					  		 },
					  		 {
					  			title :'姓名',
					  	        field:'readerName',
					  	          width:50,
					  	          
					  	      },
					  	  
					  	      {
					  	    	title:'登记时间',
					  	    	field :'createTime',
					  	        width:70,
					  	        
					  	      },
					  	    {
					  	    	title :'完成时间',
					  	        field:'finishTime',
					  	          width:70,
					  	       	
					  	        
					  	      },
					  	    {
						           title:'',
						           field:'taskState',
							  	   hidden:true
							   },
					  	  
					  	    {
						          field:'remark',
						          title:'操作',
						          width:40,
						      	  halign:'center',
								  align:'center',
						          formatter:function(value,row,index){
									   //if($('#openType').val()=='1')
							           //{
								          if(row.taskState==null||row.taskState==0)
								          {
						         				return "<a href='javascript:delQAFileReader("+row.learnRecordId+");'>删除</a>";
								          }else{
												return "删除";
										  }
										  
							          	//}else return "删除";
									          
								  }
							},
					  ]],

		});
	initSearchCondition();

	initButton();
	
	if($('#selectLearnTaskId').val()!=null&&$('#selectLearnTaskId').val()!=''){
		//打开我的学习任务
		confirmLearnTask();
	}
	

});//function结束

function initButton()
{
	var role = $('#roleForFileReg').val();

	$('#addQAFileType').linkbutton('disable');
	$('#editQAFileType').linkbutton('disable');
	$('#delQAFileType').linkbutton('disable');

	$('#addQAFileReg').linkbutton('disable');
	$('#editQAFileReg').linkbutton('disable');
	$('#delQAFileReg').linkbutton('disable');

	$('#addAttachmentButton').linkbutton('disable');
//	$('#delAttachmentButton').linkbutton('disable');

	$('#learnTaskManButton').linkbutton('disable');
	
	$('#confirmLearnFinish').linkbutton('enable');

	if(role=="QALead")
	{
		$('#addQAFileType').linkbutton('enable');
		$('#editQAFileType').linkbutton('enable');
		$('#delQAFileType').linkbutton('enable');

		$('#addQAFileReg').linkbutton('enable');
		$('#editQAFileReg').linkbutton('enable');
		$('#delQAFileReg').linkbutton('enable');
		
		$('#addAttachmentButton').linkbutton('enable');
		//$('#delAttachmentButton').linkbutton('enable');

		$('#learnTaskManButton').linkbutton('enable');
		
	
		/*$('#addQAFileReg').linkbutton('enable');
		$('#editQAFileReg').linkbutton('enable');
		$('#delQAFileReg').linkbutton('enable');

		$('#addAttachmentButton').linkbutton('enable');*/
		//$('#delAttachmentButton').linkbutton('enable');
	}

	
}

function initTaskManButton(learnState)
{
	$('#newQALearnTaskButton').linkbutton('enable');
	
	$('#delQALearnTaskButton').linkbutton('disable');
	$('#chooseTaskFileButton').linkbutton('disable');
	$('#chooseLearPersonButton').linkbutton('disable');
	$('#commitTaskButton').linkbutton('disable');
	$('#cancelTaskButton').linkbutton('disable');
	$('#conformTaskFinish').linkbutton('disable');

	if(learnState==null||learnState==0)
	{
		$('#delQALearnTaskButton').linkbutton('enable');
		$('#chooseTaskFileButton').linkbutton('enable');
		$('#chooseLearPersonButton').linkbutton('enable');
		$('#commitTaskButton').linkbutton('enable');
		$('#cancelTaskButton').linkbutton('enable');
	}
	if(learnState==1)
	{
		$('#conformTaskFinish').linkbutton('enable');
		$('#cancelTaskButton').linkbutton('enable');
	}
	
}

function initSearchCondition()
{
	$('#fileSearchCondition').searchbox({ 
	     height:19,
	     width:200,
		 searcher:function(value,name){ 
			var fileTypeCondition = $('#fileTypeCondition').combobox('getValue');
			var fileStateCondition = $('#fileStateCondition').combobox('getValue');
			
			$('#QAFileRegs').datagrid({
				url:sybp()+'/qAFileRegAction_loadListByCondition.action?fileType='+fileTypeCondition+'&fileStateCondition='+fileStateCondition+'&fileSearchCondition='+value,
			});
			$('#QAFileRegs').datagrid('reload');
			$('#QAFileAttachmentDG').datagrid('loadData',[]);
		},
		prompt:'模糊查询,请输入文件名',
	});
	
	$('#fileStateCondition').combobox({
		onSelect:function(record){
			
			var fileTypeCondition = $('#fileTypeCondition').combobox('getValue');
			var fileStateCondition = $('#fileStateCondition').combobox('getValue');
			var value = $('#fileSearchCondition').searchbox('getValue');
			$('#QAFileRegs').datagrid({
				url:sybp()+'/qAFileRegAction_loadListByCondition.action?fileType='+fileTypeCondition+'&fileStateCondition='+fileStateCondition+'&fileSearchCondition='+value,
			});
			$('#QAFileRegs').datagrid('reload');
			$('#QAFileAttachmentDG').datagrid('loadData',[]);
		
		}
	});
	$('#fileTypeCondition').combobox({
		onSelect:function(record){
			//alert("onSelect");
			var fileTypeCondition = $('#fileTypeCondition').combobox('getValue');
			var fileStateCondition = $('#fileStateCondition').combobox('getValue');
			var value = $('#fileSearchCondition').searchbox('getValue');
			//alert(fileTypeCondition+"==="+fileStateCondition+"===="+value);
			$('#QAFileRegs').datagrid({
				url:sybp()+'/qAFileRegAction_loadListByCondition.action?fileType='+fileTypeCondition+'&fileStateCondition='+fileStateCondition+'&fileSearchCondition='+value,
			});
			$('#QAFileRegs').datagrid('reload');
			$('#QAFileAttachmentDG').datagrid('loadData',[]);
		}
	});
	//学习任务管理中的查询combobox
	$('#taskTypeForTaskPage').combobox({
		onSelect:function(record){
			searchTaskByState();
		}
	});
	$('#fileSearchConditionForAdd').searchbox({ 
		 searcher:function(value,name){ 
			searchFileForAddTaskFile();
		 },
		 prompt:'模糊查询,请输入文件名',
	});
	$('#fileStateConditionForAdd').combobox({
		onSelect:function(record){
			searchFileForAddTaskFile();
		}
	});
	$('#fileTypeCondition').combobox({
		onSelect:function(record){
			searchFileForAddTaskFile();
		}
	});
		
	
}


</script>
</head>
  
  <body>
  	<input id="roleForFileReg" type="hidden" value=${role }></input>
  	<input id="lastEditRowIndex" type="hidden"></input>
  	<s:hidden id="selectLearnTaskId" name="selectLearnTaskId"> </s:hidden>
  	<s:hidden id="oneAttachmentId"></s:hidden>
  
	<div id="wholeFileRegPage" class="easyui-layout" fit="true" border="false" style="display:none">
	<!-- 
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">文件登记</a></span></span></li>
		      </ul>
			</div>
		</div>
	 -->
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800px;">
				<div style="float:left; height=25px;width=300px;">
					<div>
						<a id="addQAFileType" disabled class="easyui-linkbutton" onclick="addQAFileType(0);" data-options="iconCls:'icon-add',plain:true">添加目录</a>
						<a id="editQAFileType" disabled class="easyui-linkbutton" onclick="addQAFileType(1);" data-options="iconCls:'icon-edit',plain:true">编辑目录</a>
						<a id="delQAFileType" disabled class="easyui-linkbutton" onclick="delQAFileType();" data-options="iconCls:'icon-remove',plain:true">删除目录</a>
					</div>
				</div>

			</div>
			<div style="position:absolute;top:0px;left:350px;height=38px;width=500px;">
					<div style="float:left; height=25px;width=300px;">
						<div>
							学习任务：
							<a id="learnTaskManButton" disabled class="easyui-linkbutton" onclick="operateQALearnTask();" data-options="iconCls:'icon-add',plain:true">任务管理</a>
							<a id="confirmLearnFinish" disabled class="easyui-linkbutton" onclick="confirmLearnTask();" data-options="iconCls:'icon-confirm',plain:true">我的任务</a>
							| 文件管理：
							<a id="addQAFileReg"  disabled class="easyui-linkbutton" onclick="addQAFileReg(0);" data-options="iconCls:'icon-add',plain:true">添加文件</a>
							<a id="editQAFileReg" disabled class="easyui-linkbutton" onclick="addQAFileReg(1);" data-options="iconCls:'icon-edit',plain:true">编辑文件</a>
							<a id="delQAFileReg" disabled class="easyui-linkbutton" onclick="delQAFileReg();" data-options="iconCls:'icon-remove',plain:true">删除文件</a>
							| 附件管理：
							<a id="addAttachmentButton" disabled class="easyui-linkbutton" onclick="addQAFileAttachment();" data-options="iconCls:'icon-add',plain:true">管理附件</a>
					
							
							<!-- 
							<a id="delAttachmentButton" disabled class="easyui-linkbutton" onclick="delQAFileAttachment();" data-options="iconCls:'icon-remove',plain:true">删除附件</a>
							
							<a class="easyui-linkbutton" onclick="addQAReaderComment();" data-options="iconCls:'icon-add',plain:true">填写评论</a>
							<a class="easyui-linkbutton" onclick="confirmSOPFinish();" data-options="iconCls:'icon-remove',plain:true">确认SOP修订完成</a>
							 -->
						
						</div>
						<div style="height: 38px;">
							类型：<select id="fileTypeCondition" data-options="editable:false" class="easyui-combobox"style="width:100px;">
								    <option value="0">全部</option>
								    <option value="1">法规</option>
								    <option value="2">指导原则</option>
								    <option value="3">SOP</option>
								</select>
							状态：<select id="fileStateCondition" data-options="editable:false" class="easyui-combobox" style="width:150px;">
								    <option value="1">全部</option>
								    <option value="2">尚未安排学习的文件</option>
								    <option value="3">尚未完成学习的文件</option>
								    <option value="4">全部学习完成的文件</option>
								    <!-- 
								    <option value="5">尚未完成SOP修订的文件</option>
								     -->
								</select>
								&nbsp;&nbsp;&nbsp;
							<span style="position:absolute;left:350px; top:28px;">
								<input id="fileSearchCondition" style="width:300px;" class='easyui-searchbox'></input>
							 </span>	
						</div>
					
					</div>
				</div>
			<!-- 
	        <span style=" position:absolute;top:55px;left:10px;width:320px;bottom:2px;">目录</span>	<br/>
			 -->
			<div style="position:absolute;top:35px;left:10px;bottom:2px;width:320px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="QAFileTypes" class="easyui-tree"></div>
			
			</div>
					
			<span id="oneQACheckTableLabel" style=" position:absolute;top:55px;left:350px;">文件：</span><br/>
		 
			<div style="position:absolute;top:75px;left:350px; width:1020px;bottom:2px;border: 0px solid #c8c8c8; overflow-y: auto;">
		 		<div style="float:left;position:relative;left: 0px;width: 790px;">
					<div id="QAFileRegs" class="easyui-datagrid" style="width:790px;border:1px solid #00F;height:50px;float:left;"></div>
				</div>
				<div style="float:left;position:relative;left: 10px;width: 200px;">
					<div id="QAFileAttachmentDG" class="easyui-datagrid" style="width:200px;border:1px solid #00F;height:50px;float:left;"></div>
		 		</div>
		 		
			</div>
			<!-- 
			<span style=" position:absolute;top:300px;left:350px;">文件附件：</span>
			<div style=" position:absolute;top:320px;left:350px;bottom:22px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="QAFileAttachments" class="easyui-datagrid" ></div>
			</div>
			
	 		<span style=" position:absolute;top:300px;left:770px;">评论信息：</span>
			<div style=" position:absolute;top:320px;left:770px;width:410px;bottom:22px; border: 1px solid #c8c8c8; overflow-y: auto;">
		 		<div id="QAFileComments" class="easyui-datagrid" ></div>
			</div>
			 -->
			 <hr style="position:absolute;top:0;bottom:22px;left:340px;height:100%;border:1px solid #c8c8c8;" />
		</div>
		<!-- table end -->
		
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/QAFileRegAction/addOrEditQAFileReg.jspf"%>
		<%@ include file="/WEB-INF/jsp/QAFileRegAction/addOrEditQAFileType.jspf" %>
		<%@ include file="/WEB-INF/jsp/QAFileRegAction/addQAFileAttachment.jspf" %>
		<%@ include file="/WEB-INF/jsp/QAFileRegAction/addFileComment.jspf" %>
		<%@ include file="/WEB-INF/jsp/QALearnTaskAction/QALearnTaskList.jspf" %>
		<%@ include file="/WEB-INF/jsp/QALearnTaskAction/addOneQALearnTask.jspf" %>
		<%@ include file="/WEB-INF/jsp/QALearnTaskAction/chooseTaskFile.jspf" %>
		<%@ include file="/WEB-INF/jsp/QALearnTaskAction/chooseLearnPeople.jspf" %>
	</div>
	
  </body>
</html>