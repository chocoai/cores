function showEditPathPlanVisceraWeighDialog(clickName,addOrEdit,title){
	/* 显示编辑病理计划-脏器称重Dialog */
	 document.getElementById("editPathPlanVisceraWeighDialog2").style.display="block";
	 $('#editPathPlanVisceraWeighDialog').dialog('setTitle',title);
	 $('#editPathPlanVisceraWeighDialog').dialog('open'); 
	 initVisceraList1Combobox();
	 $('#pathPlanVisceraWeighTable1').datagrid({
     	url:sybp()+'/tblPathPlanVisceraWeighAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara),
     	title:'脏器称重计划',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:362,
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
//				hidden:true,
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
				width:123,
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
				$('#deletePathPlanVisceraWeighButton').linkbutton('disable');
			   $('#editAttachedVisceraButton').linkbutton('disable');
			   var selectid = $('#pathPlanVisceraWeighId').val();
		        if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
				            	$('#pathPlanVisceraWeighTable1').datagrid('selectRow',i);
			          }
			       }
			     }
			},onDblClickRow: function(rowIndex, rowData){
				
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
}


//初始化脏器列表
function initVisceraList1Combobox(){
	$('#visceraList1').datagrid({    
		url : sybp()+'/tblPathPlanVisceraWeighAction_loadVisceraList.action?animalType='+encodeURIComponent(animalType)+'&studyNoPara='+encodeURIComponent(studyNoPara),
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
	    },
	    onSelect:function(rowIndex, rowData){
	    	$('#editAttachedVisceraButton').linkbutton('disable');
//	    	if(rowData.isPart==1){
////	    		document.getElementById("partVisceraSeparateWeigh").checked =false;
// 			   document.getElementById("partVisceraSeparateWeigh").disabled =false;
//	    	}else{
//	    		var nodes=$('#visceraList1').datagrid('getSelections');
//	 	       if(null!=nodes && nodes.length>0){
//	 	    	   for(var i=0;i<nodes.length;i++){
//	 	    		   if(nodes[i].isPart==0){
//	 	    			   document.getElementById("partVisceraSeparateWeigh").checked =false;
//	 	    			   document.getElementById("partVisceraSeparateWeigh").disabled =true;
//	 	    			   break;
//	 	    		   }else{
//	 	    			   document.getElementById("partVisceraSeparateWeigh").disabled =false;
//	 	    		   }
//	 	    	   }
//	 	       }else{
//	 	    	  document.getElementById("partVisceraSeparateWeigh").disabled =false;
//	 	       }
//	    	}
////	    	alert(rowData.visceraName);
           $('#addPathPlanVisceraWeighButton').linkbutton('enable');
           $('#deletePathPlanVisceraWeighButton').linkbutton('disable');
           var row=$('#pathPlanVisceraWeighTable1').datagrid('getSelected');
	         if(row!=null){
	        	 var rowIndex = $('#pathPlanVisceraWeighTable1').datagrid('getRowIndex', row);
	        	 $('#pathPlanVisceraWeighTable1').datagrid('unselectRow',rowIndex);
	         }
		},
		onUnselect:function(rowIndex, rowData){
//			 var nodes=$('#visceraList1').datagrid('getSelections');
//		       if(null!=nodes && nodes.length>0){
//		    	   for(var i=0;i<nodes.length;i++){
//		    		   if(nodes[i].isPart==0){
//		    			   document.getElementById("partVisceraSeparateWeigh").checked =false;
//		    			   document.getElementById("partVisceraSeparateWeigh").disabled =true;
//		    			   break;
//		    		   }else{
//		    			   document.getElementById("partVisceraSeparateWeigh").disabled =false;
//		    		   }
//		    	   }
//		       }
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
//	for(var i=0;i<rows.length;i++){
//		if(rows[i].visceraName==node.visceraName){
//			repeat=1;
//		}
//	}
//	if(repeat==1){
//		$.messager.confirm('确认对话框', '当前脏器已设置是否修改？', function(r){
//				if (r){
//					addPathPlanWeigh(studyNoPara,node.visceraCode,partVisceraSeparateWeigh,fixedWeighFlag);
//				}else{
//					return;
//				}
//			});
//	}else{
//		addPathPlanWeigh(studyNoPara,node.visceraCode,partVisceraSeparateWeigh,fixedWeighFlag);
//	}
	
    
}
//数据库实现添加  
function addPathPlanWeigh(studyNoPara,visceraCode,partVisceraSeparateWeigh,fixedWeighFlag){
	$.ajax({
		url:sybp()+'/tblPathPlanVisceraWeighAction_addPathPlanVisceraWeigh.action?studyNo='+encodeURIComponent(studyNoPara)+'&visceraCode='+visceraCode+'&partVisceraSeparateWeigh='+partVisceraSeparateWeigh+'&fixedWeighFlag='+fixedWeighFlag,
		type:'post',
		data:'',
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 $('#pathPlanVisceraWeighId').val(r.id);
				 $('#pathPlanVisceraWeighTable1').datagrid('reload');
				 $('#pathPlanVisceraWeighTable').datagrid('reload');
			}else{
				parent.parent.showMessager(2,'添加失败',true,5000);
			}
		}
	});
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
//	var abnViscera=0;
//	if(document.getElementById("abnViscera1").checked ==true){
//		abnViscera=1;
//	}
	var rows2=$('#pathPlanVisceraWeighTable1').datagrid('getRows');
	if(rows2==null || rows2.length==0){
		parent.parent.showMessager(2,'当前没有设置称重计划',true,5000);
		$('#saveWeighDialogButton').linkbutton('enable');
		return;
	}
	//解剖申请-脏器称重下脏器数组
	var visceraNames1=[];
	for(var i=0;i<rows2.length;i++){
		visceraNames1.push(rows2[i].visceraName);
	}
	//解剖申请-脏器称重下成对脏器分开称重标志数组
	var partVisceraSeparateWeighs=[];
	for(var i=0;i<rows2.length;i++){
		partVisceraSeparateWeighs.push(rows2[i].partVisceraSeparateWeigh);
	}
	//解剖申请-脏器称重下固定称重标志数组
	var fixedWeighFlags=[];
	for(var i=0;i<rows2.length;i++){
		fixedWeighFlags.push(rows2[i].fixedWeighFlag);
	}
	//解剖申请-脏器称重下附加脏器数组
	var attachedVisceras=[];
	for(var i=0;i<rows2.length;i++){
		if(rows2[i].attachedViscera==''||rows2[i].attachedViscera==null){
			attachedVisceras.push('0');
		}else{
			attachedVisceras.push(rows2[i].attachedViscera);
		}
	}
	$.ajax({
	    url:sybp()+'/tblPathPlanVisceraWeighAction_addPathPlanVisceraWeigh.action?studyNo='
	    +encodeURIComponent(studyNoPara)+'&visceraNames1='+encodeURIComponent(visceraNames1)
        +'&partVisceraSeparateWeighs='+partVisceraSeparateWeighs+'&fixedWeighFlags='+fixedWeighFlags+'&attachedVisceras='+encodeURIComponent(attachedVisceras),
//	    url:sybp()+'/tblStudyPlanAction_editAbnViscera.action?studyNo='+studyNoPara+'&abnVisceraFixedFlag='+abnViscera+'&abnVisceraHistopathCheckFlag='+abnVisceraHistopathCheckFlag,
		type:'post',
		data:'',
		dataType:'json',
		success:function(r){
			if(r && r.success){
				$('#saveWeighDialogButton').linkbutton('enable');
				$('#editPathPlanVisceraWeighDialog').dialog('close');
				$('#pathPlanVisceraWeighTable').datagrid('reload');
			}else{
				parent.parent.showMessager(2,'病理计划-脏器称重添加失败',true,5000);
			}
		}
	});
//	var check_array=document.getElementsByName("abnViscera1");
//	for(var i=0;i<check_array.length;i++)
//    {
//        if(check_array[i].checked==true)
//        {         
//        	abnViscera=parseInt(abnViscera)+parseInt(check_array[i].value);
//        }
//    }
//	if(abnViscera!=0){
//		   $.ajax({
//				url:sybp()+'/tblStudyPlanAction_editAbnVisceraWeight.action?studyNo='+studyNoPara+'&abnVisceraWeighFlag='+abnViscera,
//				type:'post',
//				data:'',
//				dataType:'json',
//				success:function(r){
//					if(r && r.success){
//						$('#editPathPlanVisceraWeighDialog').dialog('close');
//					}else{
//						parent.parent.showMessager(2,'异常组织编辑失败',true,5000);
//					}
//				}
//			});
//	}else{
//		$('#editPathPlanVisceraWeighDialog').dialog('close');
//	}
}
