/**显示Dialog*/
function showAttachementAddEditDialog(clickName,addOrEdit,title){
	/*签名Dialog*/
	document.getElementById("attachmentAddEditDialog2").style.display="block";
	$('#attachmentAddOrEdit').val(addOrEdit);
	$('#attachmentAddEditDialog').dialog('setTitle',title);
	$('#attachmentAddEditDialog').dialog('open'); 
	document.getElementById("attachmentAddEdit_event").href="javascript:"+clickName+"();";
	//清空数据
	attachmentFullEditData();
	//初始化form
	initAttachmentForm();
}

//填充数据(或清空数据)
function attachmentFullEditData(){
    //$('#attachmentFile').val('');
    //$('#attachmentName').val('');
    //$('#attachmentRemark').val('');
    document.attachmentAddEditForm.reset();//
}

function initAttachmentForm(){
	$('#attachmentAddEditForm').form({    
	    url:sybp()+'/tblContractAttachmentAction_importFile.action',
//	    contractCode='+$('#attachment_contractCode').val()
//			+'&attachmentName='+$('#attachmentName').val()+'&remark='+$('#attachmentRemark').val(),    
	    onSubmit: function(){    
			var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			}
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){   
//	    	alert(data);
	    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
	    	
//	    	var r = $.parseJSON(data); 
//	    	if(r && r.success){
//	    		$('#attachmentAddEditDialog').dialog('close');
//	    		
//	    		document.getElementById("attachmentAddEdit_event").click();
//	    	}else{
//	    		$.messager.alert('警告','文件上传失败');    
//	    	}
	    	if(data == 'true'){
	    		$('#attachmentAddEditDialog').dialog('close');
	    		
	    		document.getElementById("attachmentAddEdit_event").click();
	    	}else{
	    		$.messager.alert('警告','文件上传失败'); 
	    	}
	    } 
	});    

}

function attachmentFileChange(obj){
	var fullFileName = obj.value;
//	var pos = fileName.lastIndexOf("\");
//			fileName.substring(pos+1); 
	var pos = fullFileName.lastIndexOf('\\');
	var fileName = fullFileName.substring(pos+1); 
	$('#attachmentName').val(fileName);

}


/**确定（保存）*/
function onDialogAttachmentSaveButton(){
//	if( $('#attachmentAddEditForm').form('validate') ){
//		$('#saveAttachmentDialogButton').linkbutton('disable');
//		if($('#attachmentAddOrEdit').val() == 'add'){
//			$.ajaxFileUpload({
//		          url:sybp()+'/tblContractAttachmentAction_importFile.action?contractCode='+$('#attachment_contractCode').val()
//		          			+'&attachmentName='+$('#attachmentName').val()+'&remark='+$('#attachmentRemark').val(),            //需要链接到服务器地址
//		          secureuri:false,
//		          fileElementId:'attachmentFile',                        //文件选择框的id属性
//		          dataType: 'json',                                     //服务器返回的格式，可以是xml
//		          success: function (data, status){     
//		              if(data &&data.success){
//		            	  $('#saveAttachmentDialogButton').linkbutton('enable');
//		            	  alert('文件上传成功');
//		            	  
//		              }else{
//		            	$('#errorSpan').html(data.msg);
//		              }
//		          },
//		          error: function (data, status, e){
//		        	  $('#errorSpan').html('文件上传失败，请刷新页面后重试');
//		          }
//	      	});
//		}
		
//	}
	
	
	$.messager.progress();	// 显示进度条
	$('#attachmentAddEditForm').submit(); 
}


