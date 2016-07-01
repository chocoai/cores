function showEditPathPlanAttachedVisceraDialog(clickName,addOrEdit,title){
	/* 显示编辑病理计划-附加脏器Dialog */
	 document.getElementById("editPathPlanAttachedVisceraDialog2").style.display="block";
	 $('#editPathPlanAttachedVisceraDialog').dialog('setTitle',title);
	 $('#editPathPlanAttachedVisceraDialog').dialog('open'); 
	 initVisceraList2Combobox();
	 $('#visceraList2').datagrid('loadData', { total: 0, rows: [] });
	 var rows=$('#visceraList1').datagrid('getRows');
	 for(var i=0;i<rows.length;i++){
		 $('#visceraList2').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 });
	 }
	 var row=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
	 $('#mainVisceraName').val(row.visceraName);
	 $('#pathPlanAttachedVisceraTable').datagrid({
 //    	url:sybp()+'/tblPathPlanAttachedVisceraAction_loadList.action?visceraWeighPlanID='+row.id,
		url:'',
     	title:'附加脏器',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			showHeader:false,
			height:250,
			width:172,
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'脏器名称',
				field:'visceraName',
				width:160,
				halign:'center',
				align:'left'
			}
			]],
	     onLoadSuccess:function(data){
		   $('#deletePathPlanAttachedVisceraButton').linkbutton('disable');
		   var selectid = $('#pathPlanAttachedVisceraId').val();
	        if(selectid != ""){
	          for(var i = 0 ; i< data.rows.length;i++){
	             if(selectid == data.rows[i].id){
			          $('#pathPlanAttachedVisceraTable').datagrid('selectRow',i);
		          }
		       }
		     }
		},
		onSelect:function(rowIndex, rowData){
		   $('#deletePathPlanAttachedVisceraButton').linkbutton('enable');
		   $('#addPathPlanAttachedVisceraButton').linkbutton('disable');
		   var row=$('#visceraList2').datagrid('getSelected');
	         if(row!=null){
	        	 var rowIndex = $('#visceraList2').datagrid('getRowIndex', row);
	        	 $('#visceraList2').datagrid('unselectRow',rowIndex);
	         }
		},
		onDblClickRow: function(rowIndex, rowData){
			var row1=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
			//记住所选脏器称重记录，更新后仍然选中
//			$('#pathPlanVisceraWeighId').val(row1.id);
			if(rowData!=null){
//				var rowIndex = $('#pathPlanAttachedVisceraTable').datagrid('getRowIndex', row);
				var rows=$('#visceraList2').datagrid('getRows');
				$.ajax({
					url:sybp()+'/tblPathPlanVisceraWeighAction_deletePathPlanVisceraWeigh.action?visceraName='+encodeURIComponent(rowData.visceraName),
					type:'post',
					data:'',
					dataType:'json',
					success:function(r){
						if(r && null!=r.isPart){
//							 $('#pathPlanAttachedVisceraTable').datagrid('reload');
//							 $('#pathPlanVisceraWeighTable1').datagrid('reload');
//							 $('#pathPlanVisceraWeighTable').datagrid('reload');
							 $('#visceraList2').datagrid('insertRow',{
								 index: rows.length,	
								   row: {
								         id:'',visceraName:rowData.visceraName,isPart:r.isPart
							          }
							 });
							$('#pathPlanAttachedVisceraTable').datagrid('deleteRow',rowIndex);
							$('#visceraList2').datagrid('selectRow',rows.length-1);	
						}else{
//							parent.parent.showMessager(2,'删除失败',true,5000);
						}
					}
				});
			}
		}
      });
	 if(null!=row.attachedViscera && row.attachedViscera!=''){
		 var attachedVisceras=row.attachedViscera.split("、");
		 $('#pathPlanAttachedVisceraTable').datagrid('loadData', { total: 0, rows: [] });
		 for(var i=0;i<attachedVisceras.length;i++){
//			 var rows=$('#visceraList1').datagrid('getRows');
				 $('#pathPlanAttachedVisceraTable').datagrid('insertRow',{
					 index: i,	
					   row: {
					      id:'',visceraName:attachedVisceras[i]
				            }
				 });
		 }
	 }
}


//初始化脏器列表
function initVisceraList2Combobox(){
	$('#visceraList2').datagrid({    
//		url : sybp()+'/tblPathPlanVisceraWeighAction_loadVisceraList.action?animalType='+animalType+'&studyNoPara='+studyNoPara,
		url:'',
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		singleSelect:true,
		showHeader:false,
		height:280,
		width:140,
		sortName:'id',
		columns :[[{
			title:'id',
			field:'visceraCode',
			width:150,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'',
			field:'visceraName',
			width:120,
			halign:'center',
			align:'left',
			styler : function(value, row, index) {
		          return 'border:0;';
		        }
		}
		]],
		onLoadSuccess:function(data){
		   $('#addPathPlanAttachedVisceraButton').linkbutton('disable');
		   var rows=$('#visceraList2').datagrid('getRows');
		   for(var i=0;i<rows.length;i++){
			   if(rows[i].visceraName==$('#mainVisceraName').val()){
				   var rowIndex = $('#visceraList2').datagrid('getRowIndex', rows[i]);
				   $('#visceraList2').datagrid('deleteRow',rowIndex);
			   }
		   }
        },
 	    onSelect:function(rowIndex, rowData){
            $('#addPathPlanAttachedVisceraButton').linkbutton('enable');
            $('#deletePathPlanAttachedVisceraButton').linkbutton('disable');
            var row=$('#pathPlanAttachedVisceraTable').datagrid('getSelected');
	         if(row!=null){
	        	 var rowIndex = $('#pathPlanAttachedVisceraTable').datagrid('getRowIndex', row);
	        	 $('#pathPlanAttachedVisceraTable').datagrid('unselectRow',rowIndex);
	         }
 		},
		onDblClickCell: function(index,field,value){
					
			var rows=$('#pathPlanAttachedVisceraTable').datagrid('getRows');
			$('#pathPlanAttachedVisceraTable').datagrid('insertRow',{
				 index: rows.length,	
				   row: {
				         id:'',visceraName:value
			          }
			 });
			$('#visceraList2').datagrid('deleteRow',index);
		    $('#pathPlanAttachedVisceraTable').datagrid('selectRow',rows.length-1);	
		}
	});
}
//添加按钮事件
function addPathPlanAttachedVisceraButton(){
	var row=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
	//记住所选脏器称重记录，更新后仍然选中
//	$('#pathPlanVisceraWeighId').val(row.id);
	var visceraWeighPlanID=$('#pathPlanVisceraWeighId').val();
	var node=$('#visceraList2').datagrid('getSelected');
	var rows=$('#pathPlanAttachedVisceraTable').datagrid('getRows');
	$('#pathPlanAttachedVisceraTable').datagrid('insertRow',{
		 index: rows.length,	
		   row: {
		         id:'',visceraName:node.visceraName
	          }
	 });
	var rowIndex = $('#visceraList2').datagrid('getRowIndex', node);
	$('#visceraList2').datagrid('deleteRow',rowIndex);
	$('#pathPlanAttachedVisceraTable').datagrid('selectRow',rows.length-1);	
//	 //判断脏器是否已设置
//	var repeat=0;
//	for(var i=0;i<rows.length;i++){
//		if(rows[i].visceraName==node.visceraName){
//			repeat=1;
//		}
//	}
//	if(repeat==1){
//		parent.parent.showMessager(2,'脏器已添加',true,5000);
//		return;
//	}
//	if(row.visceraName==node.visceraName){
//		parent.parent.showMessager(2,'不能添加自身为附加脏器',true,5000);
//		return;
//	}
//    $.ajax({
//		url:sybp()+'/tblPathPlanAttachedVisceraAction_addPathPlanAttachedViscera.action?visceraWeighPlanID='+visceraWeighPlanID+'&visceraCode='+node.visceraCode,
//		type:'post',
//		data:'',
//		dataType:'json',
//		success:function(r){
//			if(r && r.success){
//				 $('#pathPlanAttachedVisceraId').val(r.id);
//				 $('#pathPlanAttachedVisceraTable').datagrid('reload');
//				 $('#pathPlanVisceraWeighTable1').datagrid('reload');
//				 $('#pathPlanVisceraWeighTable').datagrid('reload');
//			}else{
//				parent.parent.showMessager(2,'添加失败',true,5000);
//			}
//		}
//	});
}

//删除按钮事件
function deletePathPlanAttachedVisceraButton(){
	var row=$('#pathPlanAttachedVisceraTable').datagrid('getSelected');
	var row1=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
	//记住所选脏器称重记录，更新后仍然选中
//	$('#pathPlanVisceraWeighId').val(row1.id);
	if(row!=null){
		var rowIndex = $('#pathPlanAttachedVisceraTable').datagrid('getRowIndex', row);
		var rows=$('#visceraList2').datagrid('getRows');
		$.ajax({
			url:sybp()+'/tblPathPlanVisceraWeighAction_deletePathPlanVisceraWeigh.action?visceraName='+encodeURIComponent(row.visceraName),
			type:'post',
			data:'',
			dataType:'json',
			success:function(r){
				if(r && null!=r.isPart){
//					 $('#pathPlanAttachedVisceraTable').datagrid('reload');
//					 $('#pathPlanVisceraWeighTable1').datagrid('reload');
//					 $('#pathPlanVisceraWeighTable').datagrid('reload');
					 $('#visceraList2').datagrid('insertRow',{
						 index: rows.length,	
						   row: {
						         id:'',visceraName:row.visceraName,isPart:r.isPart
					          }
					 });
					$('#pathPlanAttachedVisceraTable').datagrid('deleteRow',rowIndex);
					$('#visceraList2').datagrid('selectRow',rows.length-1);	
				}else{
//					parent.parent.showMessager(2,'删除失败',true,5000);
				}
			}
		});
		
//		$.ajax({
//			url:sybp()+'/tblPathPlanAttachedVisceraAction_deletePathPlanAttachedViscera.action',
//			type:'post',
//			data:{id:row.id},
//			dataType:'json',
//			success:function(r){
//				if(r && r.success){
//					 $('#pathPlanAttachedVisceraTable').datagrid('reload');
//					 $('#pathPlanVisceraWeighTable1').datagrid('reload');
//					 $('#pathPlanVisceraWeighTable').datagrid('reload');
//				}else{
//					parent.parent.showMessager(2,'删除失败',true,5000);
//				}
//			}
//		});
	}else{
		parent.parent.showMessager(2,'请选择要删除的记录',true,5000);
	}
}
//确定按钮事件
function confirmAttachedViscera(){
	var row=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
	var rowIndex=$('#pathPlanVisceraWeighTable1').datagrid('getRowIndex', row);
	var rows=$('#pathPlanAttachedVisceraTable').datagrid('getRows');
	var attachedViscera='';
	if(null!=rows && rows.length>0){
		for(var i=0 ;i<rows.length;i++){
			if(i==rows.length-1){
				attachedViscera=attachedViscera+rows[i].visceraName;
			}else{
				attachedViscera=attachedViscera+rows[i].visceraName+"、";
			}
		}
		$('#pathPlanVisceraWeighTable1').datagrid('updateRow',{
			 index: rowIndex,	
			   row: {
			          attachedViscera:attachedViscera
		          }
		 });
	}else{
		$('#pathPlanVisceraWeighTable1').datagrid('updateRow',{
			 index: rowIndex,	
			   row: {
			          attachedViscera:attachedViscera
		          }
		 });
	}
	$('#visceraList1').datagrid('loadData', { total: 0, rows: [] });
	 var rows=$('#visceraList2').datagrid('getRows');
	 for(var i=0;i<rows.length;i++){
		 $('#visceraList1').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 });
	 }
	$('#editPathPlanAttachedVisceraDialog').dialog('close'); 
}


