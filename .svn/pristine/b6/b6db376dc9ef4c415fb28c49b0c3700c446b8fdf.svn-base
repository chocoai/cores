function showEditPathPlanVisceraWeighDialog(clickName,addOrEdit,title){
	/* 显示编辑病理计划-脏器称重Dialog */
	 document.getElementById("editPathPlanVisceraWeighDialog2").style.display="block";
	 $('#editPathPlanVisceraWeighDialog').dialog('setTitle',title);
	 $('#editPathPlanVisceraWeighDialog').dialog('open'); 
	 initVisceraList1Combobox();
	 $('#pathPlanVisceraWeighTable1').datagrid({
     	url:'',
     	title:'脏器称重计划',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:325,
			width:493,
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'',
				field:'viewSn',
				width:40,
				halign:'center',
				align:'center',
				hidden:true,
				formatter: function(value,row,index){
				return index+1;
		    } 
			},{
				title:'脏器名称',
				field:'visceraName',
				width:110,
				halign:'center',
				align:'left'
			},{
				title:'成对脏器分开称重',
				field:'partVisceraSeparateWeigh',
				width:110,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    } 
			},{
				title:'固定后称重',
				field:'fixedWeighFlag',
				width:80,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    }
			},{
				title:'附加脏器',
				field:'attachedViscera',
				width:163,
				halign:'center',
				align:'left'
			} 
			]],
			onSelect:function(rowIndex, rowData){
		       $('#editAttachedVisceraButton').linkbutton('enable');
		       $('#deletePathPlanVisceraWeighButton').linkbutton('enable');
		       $('#addPathPlanVisceraWeighButton').linkbutton('disable');
		       $('#visceraList1').datagrid('unselectAll');
//		       var row=$('#visceraList1').datagrid('getSelected');
//		         if(row!=null){
//		        	 var rowIndex = $('#visceraList1').datagrid('getRowIndex', row);
//		        	 $('#visceraList1').datagrid('unselectRow',rowIndex);
//		         }
			},
			onLoadSuccess:function(data){
			   
			},
			onDblClickRow: function(rowIndex, rowData){
				
				var rows=$('#visceraList1').datagrid('getRows');
				if(rowData!=null){
					if(null!=rowData.attachedViscera && rowData.attachedViscera!=''){
//						alert('请先删除附加脏器');
						parent.parent.showMessager(2,'请先删除附加脏器',true,5000);
						addAttachedViscera();
						return;
					}
					$.ajax({
						url:sybp()+'/tblPathPlanVisceraWeighAction_deletePathPlanVisceraWeigh.action?visceraName='+encodeURIComponent(rowData.visceraName),
						type:'post',
						data:'',
						dataType:'json',
						success:function(r){
							if(r && null!=r.isPart){
//								 $('#pathPlanAttachedVisceraTable').datagrid('reload');
//								 $('#pathPlanVisceraWeighTable1').datagrid('reload');
//								 $('#pathPlanVisceraWeighTable').datagrid('reload');
								 $('#visceraList1').datagrid('insertRow',{
									 index: rows.length,	
									   row: {
									         id:'',visceraName:rowData.visceraName,isPart:r.isPart
								            }
								 });
//								 var rowIndex = $('#pathPlanVisceraWeighTable1').datagrid('getRowIndex', row);
								 $('#pathPlanVisceraWeighTable1').datagrid('deleteRow',rowIndex);
								 $('#visceraList1').datagrid('selectRow',rows.length-1);	
							}else{
//								parent.parent.showMessager(2,'删除失败',true,5000);
							}
						}
					});
					
				}
			},
			toolbar:'#attachedVisceraToolbar'
      });
	  $('#editAttachedVisceraButton').linkbutton('disable');
	  //复制主页面解剖申请-脏器称重的数据前，先清空datagrid数据
	  $('#pathPlanVisceraWeighTable1').datagrid('loadData', { total: 0, rows: [] });
		 var rows=$('#anatomyReqVisceraWeighTable').datagrid('getRows');
		 for(var i=0;i<rows.length;i++){
			 $('#pathPlanVisceraWeighTable1').datagrid('insertRow',{
				 index: i,	
				   row: rows[i]
			 })
		 };
}


//初始化脏器列表
function initVisceraList1Combobox(){
	if(null!=visceraWeighRows){
		$('#visceraList1').datagrid('loadData', { total: 0, rows: [] });
		 for(var i=0;i<visceraWeighRows.length;i++){
			 $('#visceraList1').datagrid('insertRow',{
				 index: i,	
				   row: visceraWeighRows[i]
			 })
		 };
	}else{
		addOrEdit= $('#addOrEdit').val();
		var url='';
		if(addOrEdit=='add'){
			url=sybp()+'/tblPathPlanVisceraWeighAction_loadVisceraList.action?animalType='+encodeURIComponent(animalType)+'&studyNoPara='+encodeURIComponent(studyNoPara)
		}else if(addOrEdit=='edit'){
			url=sybp()+'/tblAnatomyReqVisceraWeighAction_loadVisceraList.action?animalType='+encodeURIComponent(animalType)+'&studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+reqNo
		}
		$('#visceraList1').datagrid({    
			url : url,
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:false,
			showHeader:false,
			height:364,
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
			},{
				title:'',
				field:'isPart',
				width:120,
				halign:'center',
				align:'left',
				hidden:true
			}
			]],
			onLoadSuccess:function(data){
			   $('#addPathPlanVisceraWeighButton').linkbutton('disable');
			   $('#deletePathPlanVisceraWeighButton').linkbutton('disable');
		    },
		    onSelect:function(rowIndex, rowData){
		    	$('#editAttachedVisceraButton').linkbutton('disable');
//		    	if(rowData.isPart==1){
////		    		document.getElementById("partVisceraSeparateWeigh").checked =true;
//	 			    document.getElementById("partVisceraSeparateWeigh").disabled =false;
//	 			    alert("2");
//		    	}else{
////		    		document.getElementById("partVisceraSeparateWeigh").disabled =true;
//		    		var nodes=$('#visceraList1').datagrid('getSelections');
//		 	       if(null!=nodes && nodes.length>0){
//		 	    	   for(var i=0;i<nodes.length;i++){
//		 	    		   if(nodes[i].isPart==1){
////		 	    			   document.getElementById("partVisceraSeparateWeigh").checked =false;
//		 	    			   document.getElementById("partVisceraSeparateWeigh").disabled =false;
//		 	    			   break;
//		 	    		   }else{
//		 	    			   document.getElementById("partVisceraSeparateWeigh").disabled =true;
//		 	    			  document.getElementById("partVisceraSeparateWeigh").checked =false;
//		 	    		   }
//		 	    	   }
//		 	       }else{
//		 	    	  document.getElementById("partVisceraSeparateWeigh").disabled =false;
//		 	       }
//		    	}
	           $('#addPathPlanVisceraWeighButton').linkbutton('enable');
	           $('#deletePathPlanVisceraWeighButton').linkbutton('disable');
	           $('#editAttachedVisceraButton').linkbutton('disable');
	           var row=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
		         if(row!=null){
		        	 var rowIndex = $('#pathPlanVisceraWeighTable1').datagrid('getRowIndex', row);
		        	 $('#pathPlanVisceraWeighTable1').datagrid('unselectRow',rowIndex);
		         }
			},
			onUnselect:function(rowIndex, rowData){
//				 var nodes=$('#visceraList1').datagrid('getSelections');
//			       if(null!=nodes && nodes.length>0){
//			    	   for(var i=0;i<nodes.length;i++){
//			    		   if(nodes[i].isPart==1){
//			    			   document.getElementById("partVisceraSeparateWeigh").checked =true;
////			    			   document.getElementById("partVisceraSeparateWeigh").disabled =true;
//			    			   break;
//			    		   }else{
//			    			   document.getElementById("partVisceraSeparateWeigh").checked =false;
//			    		   }
//			    	   }
//			       }
			},
			onDblClickRow: function(rowIndex, rowData){
				
				var rows=$('#pathPlanVisceraWeighTable1').datagrid('getRows');
				//成对脏器分开称重checkbox的值
				var partVisceraSeparateWeigh=0;
				if(document.getElementById("partVisceraSeparateWeigh").checked ==true && rowData.isPart==1){
					partVisceraSeparateWeigh=1;
				}
				//固定后称重checkbox的值
			    var fixedWeighFlag=0;
			    if(document.getElementById("fixedWeighFlag").checked ==true){
			    	fixedWeighFlag=1;
				}
				$('#pathPlanVisceraWeighTable1').datagrid('insertRow',{
					 index: rows.length,	
					   row: {
					         id:'',viewSn:rows.length+1,visceraName:rowData.visceraName,partVisceraSeparateWeigh:partVisceraSeparateWeigh,fixedWeighFlag:fixedWeighFlag,attachedViscera:''
				          }
				 });
				$('#visceraList1').datagrid('deleteRow',rowIndex);
			    $('#pathPlanVisceraWeighTable1').datagrid('selectRow',rows.length-1);	
			}
		});
	}
	
}
//添加按钮事件
function addPathPlanVisceraWeighButton(){
	var rows=$('#pathPlanVisceraWeighTable1').datagrid('getRows');
	var studyNoPara = $('#studyNoPara').val();
	var nodes=$('#visceraList1').datagrid('getSelections');
	if(nodes==null||nodes.length<1){
		parent.parent.showMessager(2,'请选择脏器',true,5000);
    	return;
	}
	//成对脏器分开称重checkbox的值
	var partVisceraSeparateWeigh=0;
	if(document.getElementById("partVisceraSeparateWeigh").checked ==true){
		partVisceraSeparateWeigh=1;
	}
	//固定后称重checkbox的值
    var fixedWeighFlag=0;
    if(document.getElementById("fixedWeighFlag").checked ==true){
    	fixedWeighFlag=1;
	}
    for(var i=0;i<nodes.length;i++){
    	var partJudge=partVisceraSeparateWeigh;
    	if(partVisceraSeparateWeigh==1 && nodes[i].isPart==0){
    		partJudge=0;
    	}
    	$('#pathPlanVisceraWeighTable1').datagrid('insertRow',{
			 index: rows.length,	
			   row: {
			         id:'',viewSn:rows.length+1,visceraName:nodes[i].visceraName,partVisceraSeparateWeigh:partJudge,fixedWeighFlag:fixedWeighFlag,attachedViscera:''
		          }
		 });
    	var rowIndex = $('#visceraList1').datagrid('getRowIndex', nodes[i]);
		$('#visceraList1').datagrid('deleteRow',rowIndex);
    }
    $('#pathPlanVisceraWeighTable1').datagrid('selectRow',rows.length-1);	
  //判断脏器是否已设置
//    var repeat=0;
//    var rowIndex;
//	for(var i=0;i<rows.length;i++){
//		if(rows[i].visceraName==node.visceraName){
//			repeat=1;
//			rowIndex=i;
//		}
//	}
//	if(repeat==1){
//		$.messager.confirm('确认对话框', '当前脏器已设置是否修改？', function(r){
//				if (r){
//					//dialog中更新脏器称重
//					$('#pathPlanVisceraWeighTable1').datagrid('updateRow',{
//						 index: rowIndex,	
//						   row: {
//						         partVisceraSeparateWeigh:partVisceraSeparateWeigh,fixedWeighFlag:fixedWeighFlag
//					          }
//					 });
//					$('#pathPlanVisceraWeighTable1').datagrid('selectRow',rowIndex);
//					//页面中更新脏器称重
//					$('#anatomyReqVisceraWeighTable').datagrid('updateRow',{
//						 index: rowIndex,	
//						   row: {
//						         partVisceraSeparateWeigh:partVisceraSeparateWeigh,fixedWeighFlag:fixedWeighFlag
//					          }
//					 });
////					$('#anatomyReqVisceraWeighTable').datagrid('deleteRow',rowIndex);
//				}else{
//					return;
//				}
//			});
//	}else{
//		$('#pathPlanVisceraWeighTable1').datagrid('insertRow',{
//			 index: rows.length,	
//			   row: {
//			         id:'',viewSn:rows.length+1,visceraName:node.visceraName,partVisceraSeparateWeigh:partVisceraSeparateWeigh,fixedWeighFlag:fixedWeighFlag,attachedViscera:''
//		          }
//		 });
//		$('#pathPlanVisceraWeighTable1').datagrid('selectRow',rows.length-1);
//		$('#anatomyReqVisceraWeighTable').datagrid('insertRow',{
//			 index: rows.length,	
//			   row: {
//			         id:'',viewSn:rows.length+1,visceraName:node.visceraName,partVisceraSeparateWeigh:partVisceraSeparateWeigh,fixedWeighFlag:fixedWeighFlag,attachedViscera:''
//		          }
//		 });
//	}
	  
    
}

//删除按钮事件
function deletePathPlanVisceraWeighButton(){
	var row=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
	var rows=$('#visceraList1').datagrid('getRows');
	if(row!=null){
		if(null!=row.attachedViscera && row.attachedViscera!=''){
//			alert('请先删除附加脏器');
			parent.parent.showMessager(2,'请先删除附加脏器',true,5000);
			addAttachedViscera();
			return;
		}
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
					 $('#visceraList1').datagrid('insertRow',{
						 index: rows.length,	
						   row: {
						         id:'',visceraName:row.visceraName,isPart:r.isPart
					            }
					 });
					 var rowIndex = $('#pathPlanVisceraWeighTable1').datagrid('getRowIndex', row);
					 $('#pathPlanVisceraWeighTable1').datagrid('deleteRow',rowIndex);
					 $('#visceraList1').datagrid('selectRow',rows.length-1);	
				}else{
//					parent.parent.showMessager(2,'删除失败',true,5000);
				}
			}
		});
		
	}else{
		parent.parent.showMessager(2,'请选择要删除的记录',true,5000);
	}
}

//编辑附加脏器按钮事件
function addAttachedViscera(){
	showEditPathPlanAttachedVisceraDialog('','edit','编辑附加脏器');
}

//确定按钮事件
function saveWeigh(){
	var studyNoPara = $('#studyNoPara').val();
	//异常组织checkbox的值
	$('#anatomyReqVisceraWeighTable').datagrid('loadData', { total: 0, rows: [] });
	 var rows=$('#pathPlanVisceraWeighTable1').datagrid('getRows');
	 for(var i=0;i<rows.length;i++){
		 $('#anatomyReqVisceraWeighTable').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 })
	 };
    $('#editPathPlanVisceraWeighDialog').dialog('close');
    visceraWeighRows=$('#visceraList1').datagrid('getRows');
}
