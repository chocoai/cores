/**显示Dialog*/
function showAttachementAddDialog(clickName,title){
	/*签名Dialog*/
	document.getElementById("attachmentAddDialog2").style.display="block";
	$('#attachmentAddDialog').dialog('setTitle',title);
	$('#attachmentAddDialog').dialog('open'); 
	//回调函数
	this.myComeback = clickName;
	//清空数据
	attachmentFullEditData();
	//初始化form
	initAttachmentForm();
	
}
//填充数据(或清空数据)
function attachmentFullEditData(){
    document.attachmentAddForm.reset();//
}
/**
 * 添加一行文件选择框
 * @return
 */
function addFileList(){
	var fileList = $('#fileList');
	var fileList_1 = $('#fileList_1');
	var deleteA = $('#deleteA');
	var fileList_1_clone = fileList_1.clone(true);
	var deleteA_clone = deleteA.clone(true);
	deleteA_clone.css('display','');
	
	fileList_1_clone.attr('id','11');
	fileList_1_clone.append(deleteA_clone);
	fileList.append(fileList_1_clone);
	
}
/**
 * 删除行
 * @param currentA
 * @return
 */
function deleteCurrentDiv(currentA){
//	document.getElementById("1").parentNode.firstChild.nodeValue
	$(currentA.parentNode).remove();
}

function initAttachmentForm(){
	$('#attachmentAddForm').form({    
	    url:sybp()+'/tblAttachmentIndexAction_upload.action',
	    onSubmit: function(){    
			var isValid = $(this).form('validate');
			if (isValid){
//				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				$.messager.progress({text:'上传中...'});	// 显示进度条
			}
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){  
	    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
	    	var r = jQuery.parseJSON(data); 
	    	if(r && r.success == 'true'){
	    		$('#attachmentAddDialog').dialog('close');
	    		myComeback(r.msg);
	    	}else if(r && r.msg){
	    		$.messager.alert('警告',r.msg);     
	    	}else{
	    		$.messager.alert('警告','文件上传失败');     
	    	}
	    } 
	});    

}

/**
 * 文件选择框   onchange事件
 * @param obj
 * @return
 */
function attachmentFileChange(obj){
	var fullFileName = obj.value;
	if(!fullFileName){
		return false;
	}
//	var pos = fileName.lastIndexOf("\");
//			fileName.substring(pos+1); 
	
	
	//判断文件名是否存在，若存在则清空且提示
	var uploadFiles = document.getElementsByName('uploadFile');
	
	var num = 0;
	for(var i = 0;i < uploadFiles.length; i++){
		if(uploadFiles[i].value == fullFileName){
			num++;
		}
	} 
	var pos = fullFileName.lastIndexOf('\\');
	var fileName = fullFileName.substring(pos+1);
	if(num >1){
		$(obj).val('');
		$.messager.alert('警告',"文件'"+fileName+"'已选择，请选择其他文件。"); 
	}else{
		var pos1 = fileName.lastIndexOf('.');
		//有无后缀
		if(pos1>-1){
			suffix = fileName.substring(pos1+1);
			if(suffix == 'exe' || suffix == 'bat'){
				//无后缀
				$(obj).val('');
				$.messager.alert('警告',"不能上传以.exe或.bat结尾的文件。"); 
			}
		}else{
			//无后缀
			$(obj).val('');
			$.messager.alert('警告',"文件格式不正确。"); 
		}
		if(!$('#describe').val()){
			$('#describe').val(fileName);
		}
	}
}


/**确定（保存）*/
function onDialogAttachmentSaveButton(){
	
	var isValid = $('#attachmentAddForm').form('validate');
	if(isValid){
		//$.messager.progress({text:'上传中...'});	// 显示进度条
		
		var studyNo = $('#studyNo').val();
		if(studyNo){
			qm_showQianmingDialog('submitForm');
		}else{
			$.messager.confirm('确认','"专题编号"未填写，确定继续吗？',function(r){    
			    if (r){    
			    	qm_showQianmingDialog('submitForm');    
			    }    
			});  
		}
	}
	
	
}
/**
 * form 提交
 * @return
 */
function submitForm(){
	$('#attachmentAddForm').submit(); 
}

