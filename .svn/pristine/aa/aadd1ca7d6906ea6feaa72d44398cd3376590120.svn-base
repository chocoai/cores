function showEditAnatomyReqAnimalListDialog(clickName,addOrEdit,title){
	/* 显示编辑解剖申请-解剖动物列表编辑Dialog */
	 document.getElementById("editAnatomyReqAnimalListDialog2").style.display="block";
	 $('#editAnatomyReqAnimalListDialog').dialog('setTitle',title);
	 $('#editAnatomyReqAnimalListDialog').dialog('open'); 
	 $("#editAnatomyReqAnimalListDialog").dialog('move',{top:$(document).scrollTop() + ($(window).height()-480) * 0.5});  
	 initStudyAnimalListCombobox();
	 
	 $('#anatomyReqAnimalList1').datagrid({
     	url:'',
     	title:'已选动物',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:367,
			width:160,
			sortName:'id',
			columns :[[
				{
					title:'动物编号',
					field:'animalCode',
					width:105,
					halign:'center',
					align:'left',
					formatter: function(value,row,index){
						if (row.isAnatomyReq == 1){
							return value+"(已被申请)";
						}else{
							return value;
						}
			        }
				},{
					title:'性别',
					field:'gender',
					width:30,
					halign:'center',
					align:'center',
					formatter: function(value,row,index){
						if (value == 1){
							return '♂';
						} else {
							return '♀';
						}
				    }
				},{
					title:'',
					field:'isAnatomyReq',
					width:105,
					halign:'center',
					align:'left',
					hidden:true
				}
					
			]],
			onLoadSuccess:function(data){
		        
			},
			onSelect:function(rowIndex, rowData){
				$('#studyAnimalList').datagrid('unselectAll');
		    },
		    onDblClickRow: function(rowIndex, rowData){
				var rows=$('#studyAnimalList').datagrid('getRows');
				$('#studyAnimalList').datagrid('insertRow',{
					 index: rows.length,	
					   row: rowData
				 });
				$('#anatomyReqAnimalList1').datagrid('deleteRow',rowIndex);
			    $('#studyAnimalList').datagrid('selectRow',rows.length-1);	
			}
		    
      });
	 //在复制解剖申请动物列表前，先清空数据
	 $('#anatomyReqAnimalList1').datagrid('loadData', { total: 0, rows: [] });
	 var rows=$('#anatomyReqAnimalList').datagrid('getRows');
	 for(var i=0;i<rows.length;i++){
		 $('#anatomyReqAnimalList1').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 })
	 };
	 
}


//初始化动物选择列表
function initStudyAnimalListCombobox(){
//	if(null!=animalListRows){
//		for(var i=0;i<animalListRows.length;i++){
//			$('#studyAnimalList').datagrid('insertRow',{
//				 index: rows.length,	
//				   row: {animalCode:animalListRows[i].animalCode,gender:animalListRows[i].gender}
//			 });
//		}
//	}else{
		$('#studyAnimalList').datagrid({    
			url : sybp()+'/tblAnatomyReqAnimalListAction_getAllAnimalByStudyNo.action?studyNoPara='+encodeURIComponent(studyNoPara),
			title:'可选动物',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:false,
			height:367,
			width:160,
			sortName:'id',
			columns :[[
				{
					title:'动物编号',
					field:'animalCode',
					width:105,
					halign:'center',
					align:'left',
					
				},{
					title:'性别',
					field:'gender',
					width:30,
					halign:'center',
					align:'center',
					formatter: function(value,row,index){
						if (value == 1){
							return '♂';
						} else {
							return '♀';
						}
				    }
				}
			]],
			onLoadSuccess:function(data){
//			   $('#addPathPlanCheckButton').linkbutton('disable');
			 var rows=$('#anatomyReqAnimalList').datagrid('getRows');
			 var rows1=$('#studyAnimalList').datagrid('getRows');
			 for(var i=0;i<rows.length;i++){
				 for(var j=0;j<rows1.length;j++){
				    if(rows1[j].animalCode==rows[i].animalCode){
				    	$('#studyAnimalList').datagrid('deleteRow',j);
				    }
			     }
		     } 
		    },
		    onSelect:function(rowIndex, rowData){
		    	var row=$('#anatomyReqAnimalList1').datagrid('getSelected');
		    	if(null!=row){
		    		var rowIndex=$('#anatomyReqAnimalList1').datagrid('getRowIndex',row);
				    $('#anatomyReqAnimalList1').datagrid('unselectRow',rowIndex);	
		    	}
		    	
			},
			onDblClickRow: function(rowIndex, rowData){
				var rows=$('#anatomyReqAnimalList1').datagrid('getRows');
				$('#anatomyReqAnimalList1').datagrid('insertRow',{
					 index: rows.length,	
					   row: rowData
				 });
				$('#studyAnimalList').datagrid('deleteRow',rowIndex);
			    $('#anatomyReqAnimalList1').datagrid('selectRow',rows.length-1);	
			}
		});
//	}
	
}
//编辑解剖申请动物对话框的添加按钮事件
function addAnatomyReqAnimalListButton(){
	var rows1=$('#studyAnimalList').datagrid('getSelections');
	var rows=$('#anatomyReqAnimalList1').datagrid('getRows');
	if(rows1!=null && rows1.length>0){
		for(var i=0;i<rows1.length;i++){
			$('#anatomyReqAnimalList1').datagrid('insertRow',{
				 index: rows.length,	
				   row: rows1[i]
			 });
			var rowIndex = $('#studyAnimalList').datagrid('getRowIndex', rows1[i]);
			$('#studyAnimalList').datagrid('deleteRow',rowIndex);
		}
		   rows=$('#anatomyReqAnimalList1').datagrid('getRows');
		   $('#anatomyReqAnimalList1').datagrid('selectRow',rows.length-1);	
	}else{
		parent.parent.showMessager(2,'请选择动物',true,5000);
	}
}
//编辑解剖申请动物对话框的删除按钮事件
function deleteAnatomyReqAnimalListButton(){
	var row=$('#anatomyReqAnimalList1').datagrid('getSelected');
	var rows=$('#studyAnimalList').datagrid('getRows');
	if(row!=null){
		if(row.isAnatomyReq==1){
			var rowIndex = $('#anatomyReqAnimalList1').datagrid('getRowIndex', row);
			$('#anatomyReqAnimalList1').datagrid('deleteRow',rowIndex);
		}else{
		   var rowIndex = $('#anatomyReqAnimalList1').datagrid('getRowIndex', row);
//			var animalNumber=$('#anatomyReqAnimalList').datagrid('getRows').length;
//	        $('#animalNumber').val(animalNumber);
			$('#studyAnimalList').datagrid('insertRow',{
				 index: rows.length,	
				   row: {animalCode:row.animalCode,gender:row.gender}
			 });
			rows=$('#studyAnimalList').datagrid('getRows');
			$('#studyAnimalList').datagrid('selectRow',rows.length-1);	
			$('#anatomyReqAnimalList1').datagrid('deleteRow',rowIndex);
		}
	}
}
function saveAnatomyReqAnimalList(){
	$('#anatomyReqAnimalList').datagrid('loadData', { total: 0, rows: [] });
	 var rows=$('#anatomyReqAnimalList1').datagrid('getRows');
	 for(var i=0;i<rows.length;i++){
		 if(rows[i].isAnatomyReq==1){
			 parent.parent.showMessager(2,'已被申请的动物不能重复申请',true,5000);
			 return;
		 }
	 }
	 for(var i=0;i<rows.length;i++){
		 $('#anatomyReqAnimalList').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 })
	 };
	 var animalNumber=$('#anatomyReqAnimalList').datagrid('getRows').length;
     $('#animalNumber').val(animalNumber);
	 animalListRows=$('#studyAnimalList').datagrid('getRows');
	 $('#editAnatomyReqAnimalListDialog').dialog('close'); 
}
