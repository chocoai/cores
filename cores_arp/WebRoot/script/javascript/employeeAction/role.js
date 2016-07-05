/**显示Dialog*/
function showRoleDialog(clickName,title){
	/* 显示Dialog */
	
		var row= $('#employeeTable').datagrid('getSelected');
        if(row != null ){
        	
			$.ajax({
    		url:sybp()+'/employeeAction_loadListRole.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#employeeid').val(r.id);
  	    				$('#employeename').val(r.name);
  	    				$("#employeename").attr({ readonly: 'true' });
  	    			}
  	    		}
  	    	});
        }else{
           $.messager.alert('提示','请选择编辑角色的员工!');
        }
        $('#roleTable').datagrid({
    		url : sybp()+'/employeeAction_loadListRole1.action?id='+row.id,
    		title:'',
    		nowarp:  false,//单元格里自动换行
    		singleSelect:false,
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
    			title:'ck',
    			field:'ck',
    			checkbox:true
    		},{
    			title:'角色',
    			field:'name',
    			width:120,
    			halign:'center',
    			align:'left'
    		}
    		]],
    		onSelect:function(rowIndex, rowData){
    	   
    	},
    	onLoadSuccess:function(data){
    	   //选中已有的权限.
    	   if(data){
    		   $.each(data.rows,function(index,item){
    			   if(item.ck){
    				   $('#roleTable').datagrid('checkRow',index);
    			   }
    		   });
    	   }
    	}
    	});
        document.getElementById("roleDialog2").style.display="block";
	$('#roleDialog').dialog('setTitle',title);
	$('#roleDialog').dialog('open'); 
	document.getElementById("role_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onRoleSaveButton(){

	var selectedRows = $('#roleTable').datagrid('getSelections');
	var roles = new Array();
	for(var i =0;i<selectedRows.length;i++){
		var role = selectedRows[i].id;
		roles = roles.concat(role);
	}
	var roleStr = roles.join(",");
	var employeeId=$('#employeeid').val();
	var employeeName=$('#employeename').val();
		
				$.ajax({
					url:sybp()+'/employeeAction_saveEmployeeRole.action',
					type:'post',
					data:{
						id:employeeId,
						name:employeeName,
						roleIds:roleStr
						},
					dataType:'json',
					success:function(r){
					$('#saveRoleButton').linkbutton('enable');
					if(r && r.success){
						$('#roleDialog').dialog('close'); 
						$('#rid').val(r.id);
						var privilege_event=document.getElementById("role_event");
						role_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveRoleButton').linkbutton('enable');
						if(r && r.success){
							$('#role_eventDialog').dialog('close'); 
							var role_event=document.getElementById("role_event");
							role_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
}
