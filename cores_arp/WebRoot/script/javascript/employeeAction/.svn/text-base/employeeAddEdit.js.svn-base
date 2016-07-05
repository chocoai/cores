/**显示Dialog*/
function showEmployeeAddEditDialog(clickName,addOrEdit,title){
	
	initDepartmentCombobox();
	initPositionCombobox('');
	initEducationCombobox();
	initTitleCombobox();
	initRoleCombobox();
	/* 显示Dialog */
	document.getElementById("customerAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#employeeTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/employeeAction_todEitEmployee.action',
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
	$('#customerAddEditDialog').dialog('setTitle',title);
	$('#customerAddEditDialog').dialog('open'); 
	document.getElementById("customerAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#customerAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/employeeAction_add.action',
					type:'post',
					data:$('#customerAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#customerAddEditDialog').dialog('close'); 
						$('#eid').val(r.id);
						var customerAddEdit_event=document.getElementById("customerAddEdit_event");
						customerAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#customerAddEditDialog').dialog('close'); 
							var customerAddEdit_event=document.getElementById("customerAddEdit_event");
							customerAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/employeeAction_editSave.action',
					type:'post',
					data:$('#customerAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#customerAddEditDialog').dialog('close'); 
						$('#eid').val(r.id);
						var customerAddEdit_event=document.getElementById("customerAddEdit_event");
						customerAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#customerAddEditDialog').dialog('close'); 
							var customerAddEdit_event=document.getElementById("customerAddEdit_event");
							customerAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

//编辑客户时填充选中的客户信息到编辑界面
 function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#name').val('');
		   $('#sex').combobox('select','');
		   $('#married').combobox('select','');
		   $('#userid').val('');
		   $('#password').val('');
		   $('#repassword').val('');
		   $('#phone').val('');
		   $('#mobile').val('');
		   $('#email').val('');
		   $('#school').val('');
		   $('#zye').val('');
		   $('#national').val('');
 		   $('#jguan').val('');
	  	   $('#xueli').combobox('select','');
	  	   $('#zhic').combobox('select','');
	  	   $('#bod').datebox('setValue','');
	  	   $('#intime').datebox('setValue','');
	  	   $('#address').val('');
	  	   $('#employeeid').val('');
	  	   $('#departmentid').combobox('select','');
		   $('#positionid').combobox('select','');
		   $('#sfz').val('');
		   $('#description').val('');
		   $('#roles').combobox('select','');
		   $('#oldemployeeid').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#name').val(r.name);
		   $('#sex').combobox('select',r.sex);
		   $('#married').combobox('select',r.married);
		   $('#userid').val(r.userid);
		   $('#password').val(r.password);
		   $('#repassword').val('');
		   $('#phone').val(r.phone);
		   $('#mobile').val(r.mobile);
		   $('#email').val(r.email);
		   $('#school').val(r.school);
		   $('#zye').val(r.zye);
		   $('#national').val(r.national);
 		   $('#jguan').val(r.jguan);
 		   if(r.xueli!=0){
 			  $('#xueli').combobox('select',r.xueli); 
 		   }else{
 			  $('#xueli').combobox('select','');   
 		   }
 		  if(r.zhic!=0){
 			 $('#zhic').combobox('select',r.zhic);
 		   }else{
 			  $('#zhic').combobox('select','');   
 		   }
	  	   
	  	   $('#bod').datebox('setValue',r.bod);
	  	   $('#intime').datebox('setValue',r.intime);
	  	   $('#address').val(r.address);
	  	   $('#oldemployeeid').val(r.employeeid);
	  	   $('#employeeid').val(r.employeeid);
	  	   if(r.departmentid!=0){
	  		 $('#departmentid').combobox('select',r.departmentid);   
	  	   }else{
	  		 $('#departmentid').combobox('select','');  
	  	   }
	  	   if(r.positionid!=0){
	  		 $('#positionid').combobox('select',r.positionid);
	  	   }else{
	  		 $('#positionid').combobox('select','');
	  	   }
		   
		   $('#sfz').val(r.sfz);
		   $('#description').val(r.description);
		   $('#roles').combobox('select',r.roles);
	   }
	   
  }

//初始化部门下拉选
function initDepartmentCombobox(){
	$('#departmentid').combobox({    
		url : sybp()+'/departmentAction_getAllDepartmentNameId.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		},
		onSelect:function(rec){
		   initPositionCombobox(rec.id);  
		}
			
	});
}
//初始化职位下拉选
function initPositionCombobox(newValue){
	$('#positionid').combobox({    
		url : sybp()+'/positionAction_getAllPositionIdName.action?department_id='+newValue,
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
//初始化学历下拉选
function initEducationCombobox(){
	$('#xueli').combobox({    
		url : sybp()+'/educationAction_getAllEducationIdName.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
//初始化职称下拉选
function initTitleCombobox(){
	$('#zhic').combobox({    
		url : sybp()+'/titleAction_getAllTitleIdName.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
//初始化角色下拉选
function initRoleCombobox(){
	$('#roles').combobox({    
		url : sybp()+'/roleAction_loadListRoles.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
 	    required:true,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}