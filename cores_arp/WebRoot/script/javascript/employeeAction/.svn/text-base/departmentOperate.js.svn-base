function showDepartmentOperateDialog(clickName,addOrEdit,title){
	/* 显示部门主Dialog */
	 document.getElementById("departmentOperateDialog2").style.display="block";
	 $('#departmentOperateDialog').dialog('setTitle',title);
	 $('#departmentOperateDialog').dialog('open'); 
	$('#departmentTable').datagrid({
		url : sybp()+'/departmentAction_loadList.action',
		title:'',
		height:330,
		width:395,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    pagination:false,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'部门名',
			field:'name',
			width:150,
			halign:'center',
			align:'left'
		},{
			title:'描述',
			field:'description',
			width:220,
			halign:'center',
			align:'left'
		}
//		,{
//			title:'创建时间',
//			field:'description',
//			width:120,
//			halign:'center',
//			align:'left'
//		},{
//			title:'创建者',
//			field:'description',
//			width:200,
//			halign:'center',
//			align:'left'
//		},{
//			title:'修改者',
//			field:'roles',
//			width:60,
//			halign:'center',
//			align:'left',
//			hidden:true
//		},{
//			title:'修改时间',
//			field:'roles',
//			width:60,
//			halign:'center',
//			align:'left',
//			hidden:true
//		}
		]],
		onSelect:function(rowIndex, rowData){
	    $('#addDepartmentButton').linkbutton('enable');
	    $('#editDepartmentButton').linkbutton('enable');
	    $('#delDepartmentButton').linkbutton('enable');
         $('#backDepButton').linkbutton('enable');
	},
	onLoadSuccess:function(data){
		$('#addDepartmentButton').linkbutton('enable');
	    $('#editDepartmentButton').linkbutton('disable');
	    $('#delDepartmentButton').linkbutton('disable');
	    $('#backDepButton').linkbutton('enable');
	    var selectid = $('#did').val();
        if(selectid != ""){
          for(var i = 0 ; i< data.rows.length;i++){
             if(selectid == data.rows[i].id){
            	$('#departmentTable').datagrid('selectRow',i);
          }
       }
     }
	}
		
	});
  
}
//添加部门按钮事件
function onaddDepButton(){
	showDepartmentAddEditDialog('afterAddDepDialog','add','添加部门信息');
}
//添加部门后事件
function afterAddDepDialog(){
	parent.showMessager(1,'添加成功',true,5000);
    $('#departmentTable').datagrid('reload');
}
//编辑部门按钮事件
function oneditDepButton(){
	showDepartmentAddEditDialog('afterEditDepDialog','edit','编辑部门信息');
}
//编辑部门后事件
function afterEditDepDialog(){
	parent.showMessager(1,'编辑成功',true,5000);
    $('#departmentTable').datagrid('reload');
}
//删除部门按钮事件
function ondelDepButton(){
	 var row= $('#departmentTable').datagrid('getSelected');
 	 if(null != row ){
      	$.messager.confirm('确认对话框', '确认删除此部门？', function(r){
				if (r){
					delDepartment(row.id);
					
				}
			});
     }else{
       $.messager.alert('提示','请选择要删除的员工!');
     }
}
//根据Id删除相应部门(数据库还保存)
function delDepartment(id){
   	$.ajax({
		url:sybp()+'/departmentAction_delDepartment.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 parent.showMessager(1,'部门删除成功',true,5000);
				 $('#departmentTable').datagrid('reload');
			}else{
				 parent.showMessager(2,'部门删除失败',true,5000);
			}
		}
	});
} 
//打开部门添加编辑Dialog
function showDepartmentAddEditDialog(clickName,addOrEdit,title){
	document.getElementById("departmentAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		$('#depname').val('');
		$('#depdescription').val('');
	}else if(addOrEdit == "edit"){
		var row= $('#departmentTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/departmentAction_toEditDep.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#depid').val(r.id);
  	    				$('#olddepname').val(r.name);
  	    				$('#depname').val(r.name);
  	    				$('#depdescription').val(r.description);
  	    			}
  	    		}
  	    	});
		
	}
	$('#addOrEditDep').val(addOrEdit);
	$('#departmentAddEditDialog').dialog('setTitle',title);
	$('#departmentAddEditDialog').dialog('open'); 
	document.getElementById("departmentAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveDelButton(){
		if( $('#departmentAddEditForm').form('validate') ){
			$('#saveDepDialogButton').linkbutton('disable');
			if($('#addOrEditDep').val() == 'add'){
				$.ajax({
					url:sybp()+'/departmentAction_add.action',
					type:'post',
					data:$('#departmentAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#saveDepDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#departmentAddEditDialog').dialog('close'); 
						$('#did').val(r.id);
						var departmentAddEdit_event=document.getElementById("departmentAddEdit_event");
						departmentAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDepDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#departmentAddEditDialog').dialog('close'); 
							var departmentAddEdit_event=document.getElementById("departmentAddEdit_event");
							departmentAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/departmentAction_editSave.action',
					type:'post',
					data:$('#departmentAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDepDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#departmentAddEditDialog').dialog('close'); 
						$('#did').val(r.id);
						var departmentAddEdit_event=document.getElementById("departmentAddEdit_event");
						departmentAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDepDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#departmentAddEditDialog').dialog('close'); 
							var departmentAddEdit_event=document.getElementById("departmentAddEdit_event");
							departmentAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}