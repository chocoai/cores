function addSurfaceButton(){
	var normalid=$('#normalid').val();
	showSurfaceAddDialog('afterAddSurfaceDialog','add','添加体表检疫信息',normalid);
	
}
function showSurfaceAddDialog(clickName,addOrEdit,title,normalid){
	initSurfaceVeterinarian();
	initSurfaceProtector();
	initSurfaceRecorder();
	$('#surface_normalid').val(normalid);
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#sur_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("surfaceAddDialog2").style.display="block";
	
	$('#surfaceAddDialog').dialog('setTitle',title);
	$('#surfaceAddDialog').dialog('open'); 
	document.getElementById("surfaceAdd_event").href="javascript:"+clickName+"();";
}
function afterAddSurfaceDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
       parent.parent.showMessager(2,'数据加载中...',true,5000);
       
    }
function onDialogSaveSurfaceButton(){
		var monkeyid=document.getElementById("sur_monkeyid").value;
		if( $('#surfaceAddForm').form('validate') ){
			$('#saveSurfaceDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/surfaceAction_add.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#surfaceAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveSurfaceDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#sur_cdate').datebox('setValue','');
						$('#sur_remark').val('');
						$('#surfaceAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var surfaceAdd_event=document.getElementById("surfaceAdd_event");
						surfaceAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveSurfaceDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#surfaceAddDialog').dialog('close'); 
							var surfaceAdd_event=document.getElementById("surfaceAdd_event");
							surfaceAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initSurfaceVeterinarian(){
	$('#sur_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initSurfaceProtector(){
	$('#sur_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initSurfaceRecorder(){
	$('#sur_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
