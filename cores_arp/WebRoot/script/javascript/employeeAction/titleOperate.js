function showTitleOperateDialog(clickName,addOrEdit,title){
	/* 显示职称主Dialog */
	 document.getElementById("titleOperateDialog2").style.display="block";
	 $('#titleOperateDialog').dialog('setTitle',title);
	 $('#titleOperateDialog').dialog('open'); 
	$('#titleTable').datagrid({
		url : sybp()+'/titleAction_loadList.action',
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
			title:'职称名',
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
	    $('#addTitleButton').linkbutton('enable');
	    $('#editTitleButton').linkbutton('enable');
	    $('#delTitleButton').linkbutton('enable');
         $('#backTitButton').linkbutton('enable');
	},
	onLoadSuccess:function(data){
		$('#addTitleButton').linkbutton('enable');
	    $('#editTitleButton').linkbutton('disable');
	    $('#delTitleButton').linkbutton('disable');
	    $('#backTitButton').linkbutton('enable');
	    var selectid = $('#tid').val();
        if(selectid != ""){
          for(var i = 0 ; i< data.rows.length;i++){
             if(selectid == data.rows[i].id){
            	$('#titleTable').datagrid('selectRow',i);
          }
       }
     }
	}
		
	});
  
}
//添加部门按钮事件
function onaddTitButton(){
	showTitleAddEditDialog('afterAddTitDialog','add','添加职称信息');
}
//添加部门后事件
function afterAddTitDialog(){
	parent.showMessager(1,'添加成功',true,5000);
    $('#titleTable').datagrid('reload');
}
//编辑部门按钮事件
function oneditTitButton(){
	showTitleAddEditDialog('afterEditTitDialog','edit','编辑职称信息');
}
//编辑部门后事件
function afterEditTitDialog(){
	parent.showMessager(1,'编辑成功',true,5000);
    $('#titleTable').datagrid('reload');
}
//删除部门按钮事件
function ondelTitButton(){
	 var row= $('#titleTable').datagrid('getSelected');
 	 if(null != row ){
      	$.messager.confirm('确认对话框', '确认删除此职称？', function(r){
				if (r){
					delTitle(row.id);
					
				}
			});
     }else{
       $.messager.alert('提示','请选择要删除的职称!');
     }
}
//根据Id删除相应部门(数据库还保存)
function delTitle(id){
   	$.ajax({
		url:sybp()+'/titleAction_delTitle.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 parent.showMessager(1,'职称删除成功',true,5000);
				 $('#titleTable').datagrid('reload');
			}else{
				 parent.showMessager(2,'职称删除失败',true,5000);
			}
		}
	});
} 
//打开部门添加编辑Dialog
function showTitleAddEditDialog(clickName,addOrEdit,title){
	document.getElementById("titleAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		$('#titname').val('');
		$('#titdescription').val('');
	}else if(addOrEdit == "edit"){
		var row= $('#titleTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/titleAction_toEditTit.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#titid').val(r.id);
  	    				$('#oldtitname').val(r.name);
  	    				$('#titname').val(r.name);
  	    				$('#titdescription').val(r.description);
  	    			}
  	    		}
  	    	});
		
	}
	$('#addOrEditTit').val(addOrEdit);
	$('#titleAddEditDialog').dialog('setTitle',title);
	$('#titleAddEditDialog').dialog('open'); 
	document.getElementById("titleAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveTitButton(){
		if( $('#titleAddEditForm').form('validate') ){
			$('#saveTitDialogButton').linkbutton('disable');
			if($('#addOrEditTit').val() == 'add'){
				$.ajax({
					url:sybp()+'/titleAction_add.action',
					type:'post',
					data:$('#titleAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#saveTitDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#titleAddEditDialog').dialog('close'); 
						$('#tid').val(r.id);
						var titleAddEdit_event=document.getElementById("titleAddEdit_event");
						titleAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveTitDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#titleAddEditDialog').dialog('close'); 
							var titleAddEdit_event=document.getElementById("titleAddEdit_event");
							titleAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/titleAction_editSave.action',
					type:'post',
					data:$('#titleAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveTitDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#titleAddEditDialog').dialog('close'); 
						$('#tid').val(r.id);
						var titleAddEdit_event=document.getElementById("titleAddEdit_event");
						titleAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveTitDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#titleAddEditDialog').dialog('close'); 
							var titleAddEdit_event=document.getElementById("titleAddEdit_event");
							titleAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}