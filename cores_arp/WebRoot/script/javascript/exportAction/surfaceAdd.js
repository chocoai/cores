
 function initSurfaceVeterinarianCombobox(){
 	$('#surfa_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initSurfaceProtectorCombobox(){
	$('#surfa_protector').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		}
			
	});
}
function initSurfaceRecorderCombobox(){
 	$('#surfa_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		  
 		},
 		onSelect:function(record){
 		}
 			
 	});
 }

function showp(proj){
	var monkeylist=document.getElementById("monkeylist").value;
	
	//出口检疫单号
	var titleid=$('#title').val();
	if(titleid==null||titleid==""){
		alert("请填写检疫单号");
		if(document.getElementById("surface").checked==true){
			surface.checked=false;
		}
		if(document.getElementById("tb").checked==true){
			tb.checked=false;
		}
		if(document.getElementById("parasite").checked==true){
			parasite.checked=false;
		}
		if(document.getElementById("virus").checked==true){
			virus.checked=false;
		}
		if(document.getElementById("bacteria").checked==true){
			bacteria.checked=false;
		}
		if(document.getElementById("vaccine").checked==true){
			vaccine.checked=false;
		}
		if(document.getElementById("x").checked==true){
			x.checked=false;
		}
		if(document.getElementById("infectious").checked==true){
			infectious.checked=false;
		}
	}
	//加载检测项目
    if(proj.checked==true && proj.value=="体表" && titleid!=""){
    	showSurfaceTableDialog('afterAddSurfaceDialog',monkeylist); 
    }else if(proj.checked==true && proj.value=="TB" && titleid!=""){
    	
    	showTbTableDialog('afterAddTBDialog',monkeylist);
    }else if(proj.checked==true && proj.value=="寄生虫" && titleid!=""){
    	showParasiteTableDialog('afterAddParasiteDialog',monkeylist);
    }else if(proj.checked==true && proj.value=="病毒" && titleid!=""){
    	showVirusTableDialog('afterAddVirusDialog',monkeylist);
    }else if(proj.checked==true && proj.value=="细菌" && titleid!=""){
    	showBacteriaTableDialog('afterAddBacteriaDialog',monkeylist);
    }else if(proj.checked==true && proj.value=="疫苗" && titleid!=""){
    	showVaccineTableDialog('afterAddVaccineDialog',monkeylist);
    }else if(proj.checked==true && proj.value=="X光" && titleid!=""){
    	showXTableDialog('afterAddXDialog',monkeylist);
    }else if(proj.checked==true && proj.value=="传染病" && titleid!=""){
    	showInfectiousTableDialog('afterAddInfectiousDialog',monkeylist);
    }
}

function showSurfaceTableDialog(clickName,monkeylist){
	 document.getElementById("surfaceTableDialog2").style.display="block";
	 $('#surface_monkeylist').val(monkeylist);
	 initSurfaceProtectorCombobox();
	 initSurfaceVeterinarianCombobox();
	 initSurfaceRecorderCombobox();
		$('#surfaceTableDialog').dialog('setTitle','体表检测');
		$('#surfaceTableDialog').dialog('open'); 
		document.getElementById("surfaceTable_event").href="javascript:"+clickName+"();";

  
}
function afterAddSurfaceDialog(){
	parent.showMessager(1,'添加成功',true,5000);
}
//体表检测录入
function surfaceSaveButton(){
		var monkeylist=document.getElementById("surface_monkeylist").value;
		if( $('#surfaceTableForm').form('validate') ){
			$('#saveSurfaceDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/surfaceAction_addMonkeyList.action?monkeylist='+monkeylist,
					type:'post',
					data:$('#surfaceTableForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveSurfaceDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#surfaceTableDialog').dialog('close'); 
						$('#bid').val(r.id);
						var surfaceTable_event=document.getElementById("surfaceTable_event");
						surfaceTable_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveSurfaceDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#surfaceTableDialog').dialog('close'); 
							var surfaceTable_event=document.getElementById("surfaceTable_event");
							surfaceTable_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
	
		}
}

function cancelSurfaceButton(){
	 $('#surfaceTableDialog').dialog('close');
	 var checkbox = document.getElementById('surface');
	  if(checkbox.checked){
	  //$("[name='checkbox']").removeAttr("checked");
	  document.getElementById('surface').checked=false;
 	  }
}