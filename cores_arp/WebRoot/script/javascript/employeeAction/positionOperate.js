function showPositionOperateDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("positionOperateDialog2").style.display="block";
	 $('#positionOperateDialog').dialog('setTitle',title);
	 $('#positionOperateDialog').dialog('open'); 
	$('#positionTable').datagrid({
		url : sybp()+'/positionAction_loadList.action',
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
			width:100,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'职位名',
			field:'name',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'所属部门',
			field:'department',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'描述',
			field:'description',
			width:132,
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
	    $('#addPositionButton').linkbutton('enable');
	    $('#editPositionButton').linkbutton('enable');
	    $('#delPositionButton').linkbutton('enable');
         $('#backPosButton').linkbutton('enable');
	},
	onLoadSuccess:function(data){
		$('#addPositionButton').linkbutton('enable');
	    $('#editPositionButton').linkbutton('disable');
	    $('#delPositionButton').linkbutton('disable');
	    $('#backPosButton').linkbutton('enable');
	    var selectid = $('#pid').val();
        if(selectid != ""){
          for(var i = 0 ; i< data.rows.length;i++){
             if(selectid == data.rows[i].id){
            	$('#positionTable').datagrid('selectRow',i);
          }
       }
     }
	}
		
	});
  
}
//添加部门按钮事件
function onaddPosButton(){
	showPositionAddEditDialog('afterAddPosDialog','add','添加职位信息');
}
//添加部门后事件
function afterAddPosDialog(){
	parent.showMessager(1,'添加成功',true,5000);
    $('#positionTable').datagrid('reload');
}
//编辑部门按钮事件
function oneditPosButton(){
	showPositionAddEditDialog('afterEditPosDialog','edit','编辑职位信息');
}
//编辑职位后事件
function afterEditPosDialog(){
	parent.showMessager(1,'编辑成功',true,5000);
    $('#positionTable').datagrid('reload');
}
//删除职位按钮事件
function ondelPosButton(){
	 var row= $('#positionTable').datagrid('getSelected');
 	 if(null != row ){
      	$.messager.confirm('确认对话框', '确认删除此职位？', function(r){
				if (r){
					delPosition(row.id);
					
				}
			});
     }else{
       $.messager.alert('提示','请选择要删除的职位!');
     }
}
//根据Id删除相应职位(数据库不保存)
function delPosition(id){
   	$.ajax({
		url:sybp()+'/positionAction_delPosition.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 parent.showMessager(1,'职位删除成功',true,5000);
				 $('#positionTable').datagrid('reload');
			}else{
				 parent.showMessager(2,'职位删除失败',true,5000);
			}
		}
	});
} 
//打开职位添加编辑Dialog
function showPositionAddEditDialog(clickName,addOrEdit,title){
	document.getElementById("positionAddEditDialog2").style.display="block";
	initposDepartmentCombobox();
	if(addOrEdit == "add"){
		$('#posname').val('');
		$('#posdepartmentid').combobox('select','');
		$('#posdescription').val('');
	}else if(addOrEdit == "edit"){
		var row= $('#positionTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/positionAction_toEditPos.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#posid').val(r.id);
  	    				$('#oldposname').val(r.name);
  	    				$('#posname').val(r.name);
  	    				$('#posdepartmentid').combobox('select',r.department_id);
  	    				$('#posdescription').val(r.description);
  	    			}
  	    		}
  	    	});
		
	}
	$('#addOrEditPos').val(addOrEdit);
	$('#positionAddEditDialog').dialog('setTitle',title);
	$('#positionAddEditDialog').dialog('open'); 
	document.getElementById("positionAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSavePosButton(){
		if( $('#positionAddEditForm').form('validate') ){
			$('#saveDepDialogButton').linkbutton('disable');
			if($('#addOrEditPos').val() == 'add'){
				$.ajax({
					url:sybp()+'/positionAction_add.action',
					type:'post',
					data:$('#positionAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#savePosDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#positionAddEditDialog').dialog('close'); 
						$('#pid').val(r.id);
						var positionAddEdit_event=document.getElementById("positionAddEdit_event");
						positionAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#savePosDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#positionAddEditDialog').dialog('close'); 
							var positionAddEdit_event=document.getElementById("positionAddEdit_event");
							positionAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/positionAction_editSave.action',
					type:'post',
					data:$('#positionAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#savePosDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#positionAddEditDialog').dialog('close'); 
						$('#pid').val(r.id);
						var positionAddEdit_event=document.getElementById("positionAddEdit_event");
						positionAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#savePosDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#positionAddEditDialog').dialog('close'); 
							var positionAddEdit_event=document.getElementById("positionAddEdit_event");
							positionAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

//初始化部门下拉选
function initposDepartmentCombobox(){
	$('#posdepartmentid').combobox({    
		url : sybp()+'/departmentAction_getAllDepartmentNameId.action',
	    valueField:'id',    
	    textField:'text',
	    required:true,
	    editable:false,
	    onLoadSuccess:function(none){
		  
		},
		onSelect:function(rec){
		   initPositionCombobox(rec.id);  
		}
			
	});
}