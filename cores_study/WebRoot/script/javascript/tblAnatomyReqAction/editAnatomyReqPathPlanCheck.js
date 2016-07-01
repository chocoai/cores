function showEditPathPlanCheckDialog(clickName,addOrEdit,title){
	/* 显示编辑病理计划-脏器/组织学检查Dialog */
	 document.getElementById("editPathPlanCheckDialog2").style.display="block";
	 $('#editPathPlanCheckDialog').dialog('setTitle',title);
	 $('#editPathPlanCheckDialog').dialog('open'); 
	 initVisceraListCombobox();
//	 document.getElementById("abnViscera").checked =true;
//	 document.getElementById("abnVisceraHistopathCheckFlag").checked =true;
	 document.getElementById("atanomyCheckFlag").checked = true;
	 document.getElementById("atanomyCheckFlag").disabled = true;
	 $('#pathPlanCheckTable1').datagrid({
     	url:'',
     	title:'',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:325,
			width:508,
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
				width:169,
				halign:'center',
				align:'left'
			},{
				title:'是否需要剖检',
				field:'atanomyCheckFlag',
				width:85,
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
				title:'是否需要固定',
				field:'visceraFixedFlag',
				width:85,
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
				title:'是否需要镜检',
				field:'histopathCheckFlag',
				width:85,
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
			}
			]],
			onLoadSuccess:function(data){
		        
			},
			onSelect:function(rowIndex, rowData){
		         $('#deletePathPlanCheckButton').linkbutton('enable');
		         $('#addPathPlanCheckButton').linkbutton('disable');
		         $('#visceraList').datagrid('unselectAll');
//		         var row=$('#visceraList').datagrid('getSelected');
//		         if(row!=null){
//		        	 var rowIndex = $('#visceraList').datagrid('getRowIndex', row);
//		        	 $('#visceraList').datagrid('unselectRow',rowIndex);
//		         }
//		         document.getElementById("atanomyCheckFlag").checked =false;
//		         document.getElementById("histopathCheckFlag").checked =false;
		    },
			onDblClickRow: function(rowIndex, rowData){
							
		    	var rows=$('#visceraList').datagrid('getRows');
				$('#visceraList').datagrid('insertRow',{
					 index: rows.length,	
					   row: {
					         id:'',visceraName:rowData.visceraName,
				          }
				 });
				$('#pathPlanCheckTable1').datagrid('deleteRow',rowIndex);
				$('#visceraList').datagrid('selectRow',rows.length-1);
			}
      });
	 //复制主页面解剖申请-脏器/组织学检查的数据前，先清空datagrid数据
	 $('#pathPlanCheckTable1').datagrid('loadData', { total: 0, rows: [] });
	 var rows=$('#anatomyReqPathPlanCheckTable').datagrid('getRows');
	 for(var i=0;i<rows.length;i++){
		 $('#pathPlanCheckTable1').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 })
	 };
}


//初始化脏器列表
function initVisceraListCombobox(){
	if(null!=pathCheckVisceraRows){
		$('#visceraList').datagrid('loadData', { total: 0, rows: [] });
		 for(var i=0;i<pathCheckVisceraRows.length;i++){
			 $('#visceraList').datagrid('insertRow',{
				 index: i,	
				   row: pathCheckVisceraRows[i]
			 })
		 };
	}else{
		addOrEdit= $('#addOrEdit').val();
		var url='';
		if(addOrEdit=='add'){
			url=sybp()+'/tblPathPlanCheckAction_loadVisceraList.action?animalType='+encodeURIComponent(animalType)+'&studyNoPara='+encodeURIComponent(studyNoPara)
		}else if(addOrEdit=='edit'){
			url=sybp()+'/tblAnatomyReqPathPlanCheckAction_loadVisceraList.action?animalType='+encodeURIComponent(animalType)+'&studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+reqNo
		}
		$('#visceraList').datagrid({    
			url : url,
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:false,
			height:362,
			width:140,
			showHeader:false,
			sortName:'id',
			columns :[[{
				title:'id',
				field:'visceraCode',
				width:150,
				halign:'center',
				align:'center',
				hidden:true,
				styler : function(value, row, index) {
			          return 'border:0;';
			        }
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
			   $('#addPathPlanCheckButton').linkbutton('disable');
			   $('#deletePathPlanCheckButton').linkbutton('disable');
		    },
		    onSelect:function(rowIndex, rowData){
	           $('#addPathPlanCheckButton').linkbutton('enable');
	           $('#deletePathPlanCheckButton').linkbutton('disable');
	           var row=$('#pathPlanCheckTable1').datagrid('getSelected');
		         if(row!=null){
		        	 var rowIndex = $('#pathPlanCheckTable1').datagrid('getRowIndex', row);
		        	 $('#pathPlanCheckTable1').datagrid('unselectRow',rowIndex);
		         }
		         document.getElementById("atanomyCheckFlag").checked =true;
			},
			onDblClickCell: function(index,field,value){
				var rows=$('#pathPlanCheckTable1').datagrid('getRows');
				//剖检checkbox的值
				var atanomyCheckFlag=0;
				if(document.getElementById("atanomyCheckFlag").checked ==true){
					atanomyCheckFlag=1;
				}
				//添加病理检查计划时，至少要选择剖检
			    if(atanomyCheckFlag<1){
			    	parent.parent.showMessager(2,'添加脏器剖检为必选',true,5000);
			    	return;
			    }
			  //固定checkbox的值
				var fixedFlag=0;
				if(document.getElementById("fixedFlag").checked ==true){
					fixedFlag=1;
				}
			  //镜检checkbox的值
			   var histopathCheckFlag=0;
			   if(document.getElementById("histopathCheckFlag").checked ==true){
				   histopathCheckFlag=1;
				}
				$('#pathPlanCheckTable1').datagrid('insertRow',{
					 index: rows.length,	
					   row: {
					         id:'',viewSn:rows.length+1,visceraName:value,atanomyCheckFlag:atanomyCheckFlag,visceraFixedFlag:fixedFlag,histopathCheckFlag:histopathCheckFlag
				          }
				 });
				$('#visceraList').datagrid('deleteRow',index);
			    $('#pathPlanCheckTable1').datagrid('selectRow',rows.length-1);	
			}
		});
	}
	
}
//添加按钮事件
function addPathPlanCheckButton(){
	var rows=$('#pathPlanCheckTable1').datagrid('getRows');
//	var rowIndex = $('#pathPlanCheckTable1').datagrid('getRowIndex', row);
	var studyNoPara = $('#studyNoPara').val();
	var nodes=$('#visceraList').datagrid('getSelections');
	if(nodes==null||nodes.length<1){
		parent.parent.showMessager(2,'请选择脏器',true,5000);
    	return;
	}
	//剖检checkbox的值
	var atanomyCheckFlag=0;
	if(document.getElementById("atanomyCheckFlag").checked ==true){
		atanomyCheckFlag=1;
	}
	//至少要选择剖检
    if(atanomyCheckFlag<1){
    	parent.parent.showMessager(2,'添加脏器固定为必选',true,5000);
    	return;
    }
    //固定checkbox的值
	var fixedFlag=0;
	if(document.getElementById("fixedFlag").checked ==true){
		fixedFlag=1;
	}
  //镜检checkbox的值
   var histopathCheckFlag=0;
   if(document.getElementById("histopathCheckFlag").checked ==true){
	   histopathCheckFlag=1;
	}
   for(var i=0;i<nodes.length;i++){
		$('#pathPlanCheckTable1').datagrid('insertRow',{
			 index: rows.length,	
			   row: {
			         id:'',viewSn:rows.length+1,visceraName:nodes[i].visceraName,atanomyCheckFlag:atanomyCheckFlag,visceraFixedFlag:fixedFlag,histopathCheckFlag:histopathCheckFlag
		          }
		 });
		var rowIndex = $('#visceraList').datagrid('getRowIndex', nodes[i]);
		$('#visceraList').datagrid('deleteRow',rowIndex);
	}
	$('#pathPlanCheckTable1').datagrid('selectRow',rows.length-1);	
 //判断脏器是否重复设置(当前设定是被设置为固定非镜检的脏器可以再次设置为镜检，其余都会提醒脏器是否重新设置)
//	var repeat=0;
//	var rowIndex;
//	for(var i=0;i<rows.length;i++){
//		if(rows[i].visceraName==node.visceraName&&histopathCheckFlag<=rows[i].histopathCheckFlag){
//			repeat=1;
//			rowIndex=i;
//		}else if(rows[i].visceraName==node.visceraName&&histopathCheckFlag>rows[i].histopathCheckFlag){
//			repeat=2;
//			rowIndex=i;
//		}
//	}
//	if(repeat==1){
//		$.messager.confirm('确认对话框', '当前脏器已设置镜检是否修改？', function(r){
//				if (r){
//					//dialog中更新脏器/组织学检查
//					$('#pathPlanCheckTable1').datagrid('updateRow',{
//						 index: rowIndex,	
//						   row: {
//						         atanomyCheckFlag:atanomyCheckFlag,histopathCheckFlag:histopathCheckFlag
//					          }
//					 });
//					$('#pathPlanCheckTable1').datagrid('selectRow',rowIndex);
//					//页面中更新脏器/组织学检查
//					$('#anatomyReqPathPlanCheckTable').datagrid('updateRow',{
//						 index: rowIndex,	
//						   row: {
//						         atanomyCheckFlag:atanomyCheckFlag,histopathCheckFlag:histopathCheckFlag
//					          }
//					 });
//				}else{
//					return;
//				}
//			});
//	}else if(repeat==2){
//		//dialog中更新脏器/组织学检查
//		$('#pathPlanCheckTable1').datagrid('insertRow',{
//			 index: rows.length,	
//			   row: {
//			         id:'',viewSn:rows.length+1,visceraName:node.visceraName,atanomyCheckFlag:atanomyCheckFlag,histopathCheckFlag:histopathCheckFlag
//		          }
//		 });
//		 $('#pathPlanCheckTable1').datagrid('deleteRow',rowIndex);
//		//页面中更新脏器/组织学检查
//		 $('#anatomyReqPathPlanCheckTable').datagrid('insertRow',{
//			 index: rows.length,	
//			   row: {
//			         id:'',viewSn:rows.length+1,visceraName:node.visceraName,atanomyCheckFlag:atanomyCheckFlag,histopathCheckFlag:histopathCheckFlag
//		          }
//		 });
//		$('#anatomyReqPathPlanCheckTable').datagrid('deleteRow',rowIndex);
//	}else{
//		//dialog中更新脏器/组织学检查
//		$('#pathPlanCheckTable1').datagrid('insertRow',{
//			 index: rows.length,	
//			   row: {
//			         id:'',viewSn:rows.length+1,visceraName:node.visceraName,atanomyCheckFlag:atanomyCheckFlag,histopathCheckFlag:histopathCheckFlag
//		          }
//		 });
//		$('#pathPlanCheckTable1').datagrid('selectRow',rows.length-1);
//		//页面中更新脏器/组织学检查
//		$('#anatomyReqPathPlanCheckTable').datagrid('insertRow',{
//			 index: rows.length,	
//			   row: {
//			         id:'',viewSn:rows.length+1,visceraName:node.visceraName,atanomyCheckFlag:atanomyCheckFlag,histopathCheckFlag:histopathCheckFlag
//		          }
//		 });
//	}
}

//删除按钮事件
function deletePathPlanCheckButton(){
	var row=$('#pathPlanCheckTable1').datagrid('getSelected');
	if(row!=null){
		var rowIndex = $('#pathPlanCheckTable1').datagrid('getRowIndex', row);
		var rows=$('#visceraList').datagrid('getRows');
		$('#visceraList').datagrid('insertRow',{
			 index: rows.length,	
			   row: {
			         id:'',visceraName:row.visceraName,
		          }
		 });
		$('#pathPlanCheckTable1').datagrid('deleteRow',rowIndex);
		$('#visceraList').datagrid('selectRow',rows.length-1);	
	}else{
		parent.parent.showMessager(2,'请选择要删除的记录',true,5000);
	}
	
}

//确定按钮事件
function saveCheck(){
	var rows=$('#pathPlanCheckTable1').datagrid('getRows');
	$('#anatomyReqPathPlanCheckTable').datagrid('loadData', { total: 0, rows: [] });
	 for(var i=0;i<rows.length;i++){
		 $('#anatomyReqPathPlanCheckTable').datagrid('insertRow',{
			 index: i,	
			   row: rows[i]
		 })
	 };
	$('#editPathPlanCheckDialog').dialog('close');
	pathCheckVisceraRows=$('#visceraList').datagrid('getRows');
}
//如果选中镜检，固定,剖检也将被选中
function attachedCheck(){
	if(document.getElementById("histopathCheckFlag").checked == true){
//		document.getElementById("atanomyCheckFlag").checked = true;
//		document.getElementById("atanomyCheckFlag").disabled = true;
		document.getElementById("fixedFlag").checked = true;
		document.getElementById("fixedFlag").disabled = true;
	}else{
		document.getElementById("fixedFlag").disabled = false;
	}
}
//初始化异常组织和脏器剖检、固定和镜检复选框
function initAbnVisceraCheckboxs(){
	$.ajax({
	    url:sybp()+'/tblPathPlanCheckAction_getAbnVisceraStateByStudyNo.action?studyNo='
	    +encodeURIComponent(studyNoPara),
		type:'post',
		data:'',
		dataType:'json',
		success:function(r){
			if(r ){
				if(r.abnVisceraAnatomyCheck==1){
					document.getElementById("abnVisceraAnatomyCheck").checked =true;
					document.getElementById("abnVisceraAnatomy").checked =true;
//			       	 document.getElementById("abnViscera").checked =true;
//			       	 document.getElementById("abnViscera").disabled = true;
//			       	 document.getElementById("abnVisceraHistopathCheckFlag").checked =true;
				}else{
					document.getElementById("abnVisceraAnatomyCheck").checked =false;
					document.getElementById("abnVisceraAnatomy").checked =false;
				}
				if(r.abnVisceraFixedFlag==1){
					document.getElementById("abnVisceraCheck").checked =true;
					document.getElementById("abnViscera").checked =true;
//					document.getElementById("abnVisceraAnatomy").checked =true;
//			       	 document.getElementById("abnVisceraAnatomy").disabled =true;
				}else{
					document.getElementById("abnVisceraCheck").checked =false;
					document.getElementById("abnViscera").checked =false;
				}
				if(r.abnVisceraHistopathCheckFlag==1){
//					document.getElementById("abnVisceraAnatomy").checked =true;
//			       	 document.getElementById("abnVisceraAnatomy").disabled =true;
//			       	 document.getElementById("abnViscera").checked =true;
//			       	 document.getElementById("abnViscera").disabled = true;
			       	 document.getElementById("abnVisceraCheck2").checked =true;
			       	document.getElementById("abnVisceraHistopathCheckFlag").checked =true;
				}else{
					document.getElementById("abnVisceraCheck2").checked =false;
					document.getElementById("abnVisceraHistopathCheckFlag").checked =false;
				}
				 document.getElementById("abnVisceraAnatomyCheck").disabled =true;
				 document.getElementById("abnVisceraAnatomy").disabled =true;
				 document.getElementById("abnVisceraCheck").disabled = true;
				 document.getElementById("abnViscera").disabled =true;
				 document.getElementById("abnVisceraCheck2").disabled =true;
				 document.getElementById("abnVisceraHistopathCheckFlag").disabled =true;
			}else{
//				parent.parent.showMessager(2,'病理计划-脏器/组织学检查添加失败',true,5000);
			}
		}
	});
}



//根据日期得到消毒液编号的前缀
function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var clock ="消" + year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day;
    return(clock); 
} 
