//标记已打印(附件都标记了，把对应索引也标记了)
function signHasPrintOne(id){
	$.messager.confirm('确认','您确认想要标记已打印吗？',function(r){    
		if (r){    
			$.ajax({
				url:sybp()+'/tblAttachmentIndexAction_signHasPrintOne.action',
				type:'post',
				data:{
					id:id
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						parent.reloadDatagrid();
						parent.parent.showMessager(1,r.msg,true,5000);
					}else if(r){
						parent.parent.showMessager(2,r.msg,true,5000);
					}else{
						parent.parent.showMessager(2,'与服务器交互错误！',true,5000);
					}
				}
			});  
		}    
	}); 
}

function downloadFile(id){
	if (id){    
		$.ajax({
			url:sybp()+'/tblAttachmentIndexAction_isNoCancel.action',
			type:'post',
			data:{
				id:id
			},
			success:function(r){
				if(r == 'true'){
					window.location.href = sybp()+"/tblAttachmentIndexAction_download.action?id="+id;
				}else{
					parent.parent.showMessager(2,'文件不存在或已撤销！',true,5000);
				}
			}
		});  
	}else{
		$.messager.alert('警告','参数出错，请刷新页面重试！');
	}
}