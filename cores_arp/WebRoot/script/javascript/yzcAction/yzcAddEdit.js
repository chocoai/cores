/**显示Dialog*/
function showYzcAddEditDialog(clickName,addOrEdit,title){
	/* 显示Dialog */
	document.getElementById("yzcAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#yzcTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/yzcAction_toEdit.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				fullEditInformation(r);
  	    			}
  	    		}
  	    	});

  		}
  
  		$('#addOrEdit').val(addOrEdit);
	$('#yzcAddEditDialog').dialog('setTitle',title);
	$('#yzcAddEditDialog').dialog('open'); 
	document.getElementById("yzcAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#yzcAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/yzcAction_add.action',
					type:'post',
					data:$('#yzcAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#yzcAddEditDialog').dialog('close'); 
						$('#yzcid').val(r.id);
						var yzcAddEdit_event=document.getElementById("yzcAddEdit_event");
						yzcAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#yzcAddEditDialog').dialog('close'); 
							var yzcAddEdit_event=document.getElementById("yzcAddEdit_event");
							yzcAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/yzcAction_editSave.action',
					type:'post',
					data:$('#yzcAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#yzcAddEditDialog').dialog('close'); 
						$('#yzcid').val(r.id);
						var yzcAddEdit_event=document.getElementById("yzcAddEdit_event");
						yzcAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#yzcAddEditDialog').dialog('close'); 
							var yzcAddEdit_event=document.getElementById("yzcAddEdit_event");
							yzcAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}
function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#yzcmane').val('');
		   $('#xxdz').val('');
	  	   $('#dwxz').val('');
	  	   $('#fzr').val('');
		   $('#frdb').val('');
		   $('#yzbm').val('');
	  	   $('#lxdh').val('');
	  	   $('#zjrs').val('');
		   $('#jsrs').val('');
		   $('#syrs').val('');
	  	   $('#syrs').val('');
	  	   $('#jcrs').val('');
		   $('#hqrs').val('');
		   $('#glrs').val('');
	  	   $('#zgzrs').val('');
	  	   $('#jzmj').val('');
		   $('#symj').val('');
		   $('#sys').val('');
	  	   $('#jyf').val('');
	  	   $('#slf').val('');
		   $('#syf').val('');
		   $('#yqsb').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#yzcmane').val(r.yzcmane);
		   $('#xxdz').val(r.xxdz);
	  	   $('#dwxz').val(r.dwxz);
	  	   $('#fzr').val(r.fzr);
		   $('#frdb').val(r.frdb);
		   $('#yzbm').val(r.yzbm);
	  	   $('#lxdh').val(r.lxdh);
	  	   $('#zjrs').val(r.zjrs);
		   $('#jsrs').val(r.jsrs);
		   $('#syrs').val(r.syrs);
	  	   $('#syrs').val(r.syrs);
	  	   $('#jcrs').val(r.jcrs);
		   $('#hqrs').val(r.hqrs);
		   $('#glrs').val(r.glrs);
	  	   $('#zgzrs').val(r.zgzrs);
	  	   $('#jzmj').val(r.jzmj);
		   $('#symj').val(r.symj);
		   $('#sys').val(r.sys);
	  	   $('#jyf').val(r.jyf);
	  	   $('#slf').val(r.slf);
		   $('#syf').val(r.syf);
		   $('#yqsb').val(r.yqsb);
	   }
	   
}