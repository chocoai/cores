/**显示Dialog*/
function showPrivilegeDialog(clickName,title){
	/* 显示Dialog */
	
		var row= $('#roleTable').datagrid('getSelected');
        if(row != null ){
        	//window.location.href="${pageContext.request.contextPath}/roleAction_loadListPrivilege.action?roleid="
			//			+row.id ;
			$.ajax({
    		url:sybp()+'/roleAction_loadListPrivilege.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#rolesid').val(r.id);
  	    				$('#rolesname').val(r.name);
  	    				$("#rolesname").attr({ readonly: 'true' });
  	    			}
  	    		}
  	    	});
        }else{
           $.messager.alert('提示','请选择编辑权限的角色!');
        }
        $('#privilegeTable').datagrid({
    		url : sybp()+'/roleAction_loadListPrivilege1.action?id='+row.id,
    		title:'',
    		//height:330,
    		//width:475,
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
    			title:'功能权限',
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
    				   $('#privilegeTable').datagrid('checkRow',index);
    			   }
    		   });
    	   }
    	}
    	});
        document.getElementById("privilegeDialog2").style.display="block";
	$('#privilegeDialog').dialog('setTitle',title);
	$('#privilegeDialog').dialog('open'); 
	document.getElementById("privilege_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onPrivilegeSaveButton(){
	//1.
	//var checkedItems = $('#privilegeTable').datagrid('getChecked');
	//var privileges = [];
	//$.each(checkedItems, function(index, item){
	//	privileges.push(item.id);
	//});  
	//console.log(privileges.join(","));
	
	//var str="";
   // $("input[name='checkbox']:checkbox").each(function(){ 
   //     if($(this).attr("checked")){
   //         str += $(this).val()+",";
   //     }
   // });
	
	//2.
	var selectedRows = $('#privilegeTable').datagrid('getSelections');
	var privileges = new Array();
	for(var i =0;i<selectedRows.length;i++){
		var privilege = selectedRows[i].id;
		privileges = privileges.concat(privilege);
	}
	var privilegeStr = privileges.join(",");
	var roleId=$('#rolesid').val();
	var roleName=$('#rolesname').val();
		//if( $('#privilegeForm').form('validate') ){404
			//$('#savePrivilegeButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/roleAction_saveRolesPrivilege.action',
					type:'post',
					//data:$('#privilegeForm').serialize()+'&privileges='+privileges,
					//data:'id='+$('#id').val()+'&name='+$('#rolesname').val()+'&privileges='+privileges,
					data:{
						id:roleId,
						name:roleName,
						privilegeIds:privilegeStr
						},
					dataType:'json',
					success:function(r){
					$('#savePrivilegeButton').linkbutton('enable');
					if(r && r.success){
						$('#privilegeDialog').dialog('close'); 
						$('#rid').val(r.id);
						var privilege_event=document.getElementById("privilege_event");
						privilege_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#savePrivilegeButton').linkbutton('enable');
						if(r && r.success){
							$('#privilege_eventDialog').dialog('close'); 
							var privilege_event=document.getElementById("privilege_event");
							privilege_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});

		//}
}
