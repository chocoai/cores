function showEditPathPlanCheckDialog(clickName,addOrEdit,title){
	/* 显示编辑病理计划-脏器/组织学检查Dialog */
	 document.getElementById("editPathPlanCheckDialog2").style.display="block";
	 $('#editPathPlanCheckDialog').dialog('setTitle',title);
	 $('#editPathPlanCheckDialog').dialog('open'); 
	 initVisceraListCombobox();
	 
	 document.getElementById("atanomyCheckFlag").checked = true;
	 document.getElementById("atanomyCheckFlag").disabled = true;
	 $('#pathPlanCheckTable1').datagrid({
     	url:sybp()+'/tblPathPlanCheckAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara),
     	title:'',
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
//				hidden:true,
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
		        var rows=$('#pathPlanCheckTable1').datagrid('getRows');
//		        alert(rows.length);
		        if(rows.length==0){
		        	 document.getElementById("abnVisceraAnatomy").checked =true;
			       	 document.getElementById("abnVisceraAnatomy").disabled =true;
			       	 document.getElementById("abnViscera").checked =true;
			       	 document.getElementById("abnViscera").disabled = true;
			       	 document.getElementById("abnVisceraHistopathCheckFlag").checked =true;
		        }else{
		        	initAbnVisceraCheckboxs();
		        }
		        $('#deletePathPlanCheckButton').linkbutton('disable');
				var selectid = $('#pathPlanCheckId').val();
		        if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
				            	$('#pathPlanCheckTable1').datagrid('selectRow',i);
			          }
			       }
			     }
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
}


//初始化脏器列表
function initVisceraListCombobox(){
	$('#visceraList').datagrid({    
		url : sybp()+'/tblPathPlanCheckAction_loadVisceraList.action?animalType='+encodeURIComponent(animalType)+'&studyNoPara='+encodeURIComponent(studyNoPara),
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
//		   var rows=$('#visceraList').datagrid('getRows'); 
//		   for(var i=0;i<rows.lengrh;i++){
//			   
//		   }
	    },
	    onSelect:function(rowIndex, rowData){
           $('#addPathPlanCheckButton').linkbutton('enable');
           $('#deletePathPlanCheckButton').linkbutton('disable');
           var row=$('#pathPlanCheckTable1').datagrid('getSelected');
	         if(row!=null){
	        	 var rowIndex = $('#pathPlanCheckTable1').datagrid('getRowIndex', row);
	        	 $('#pathPlanCheckTable1').datagrid('unselectRow',rowIndex);
	         }
//	         document.getElementById("atanomyCheckFlag").checked =true;
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
//添加按钮事件
function addPathPlanCheckButton(){
	var rows=$('#pathPlanCheckTable1').datagrid('getRows');
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
// //判断脏器是否重复设置(当前设定是被设置为固定非镜检的脏器可以再次设置为镜检，其余都会提醒脏器是否重新设置)
//	var repeat=0;
//	for(var i=0;i<rows.length;i++){
//		if(rows[i].visceraName==node.visceraName&&histopathCheckFlag<=rows[i].histopathCheckFlag){
//			repeat=1;
//		}
//	}
//	if(repeat==1){
//		$.messager.confirm('确认对话框', '当前脏器已设置是否修改？', function(r){
//				if (r){
//					addPathPlanCheck(studyNoPara,node.visceraCode,atanomyCheckFlag,histopathCheckFlag);
//				}else{
//					return;
//				}
//			});
//	}else{
//		addPathPlanCheck(studyNoPara,node.visceraCode,atanomyCheckFlag,fixedFlag,histopathCheckFlag);
//	}
		//dialog中更新脏器/组织学检查
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
}
//添加病理检查计划
function addPathPlanCheck(studyNoPara,visceraCode,atanomyCheckFlag,fixedFlag,histopathCheckFlag){
	$.ajax({
		url:sybp()+'/tblPathPlanCheckAction_addPathPlanCheck.action?studyNo='+encodeURIComponent(studyNoPara)+'&visceraCode='+visceraCode+'&atanomyCheckFlag='+atanomyCheckFlag+'&fixedFlag='+fixedFlag+'&histopathCheckFlag='+histopathCheckFlag,
		type:'post',
		data:'',
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 $('#pathPlanCheckId').val(r.id);
				 $('#pathPlanCheckTable1').datagrid('reload');
				 $('#pathPlanCheckTable').datagrid('reload');
			}else{
				parent.parent.showMessager(2,'添加失败',true,5000);
			}
		}
	});
}
//删除按钮事件
function deletePathPlanCheckButton(){
	var row=$('#pathPlanCheckTable1').datagrid('getSelected');
	if(row!=null){
//		$.ajax({
//			url:sybp()+'/tblPathPlanCheckAction_deletePathPlanCheck.action',
//			type:'post',
//			data:{id:row.id},
//			dataType:'json',
//			success:function(r){
//				if(r && r.success){
//					 $('#pathPlanCheckTable1').datagrid('reload');
//					 $('#pathPlanCheckTable').datagrid('reload');
//				}else{
//					parent.parent.showMessager(2,'删除失败',true,5000);
//				}
//			}
//		});
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
	$('#saveCheckDialogButton').linkbutton('disable');
	var rows1=$('#pathPlanCheckTable1').datagrid('getRows');
	if(rows1==null ||rows1.length==0){
		parent.parent.showMessager(2,'未设置计划',true,5000);
		$('#saveCheckDialogButton').linkbutton('enable');
		return;
	}
	//病理计划-脏器/组织学检查下脏器数组
	var visceraNames=[];
	for(var i=0;i<rows1.length;i++){
		visceraNames.push(rows1[i].visceraName);
	}
	//病理计划-脏器/组织学检查下是否需要剖检数组
	var atanomyCheckFlags=[];
	for(var i=0;i<rows1.length;i++){
		atanomyCheckFlags.push(rows1[i].atanomyCheckFlag);
	}
	//病理计划-脏器/组织学检查下是否需要固定数组
	var visceraFixedFlags=[];
	for(var i=0;i<rows1.length;i++){
		visceraFixedFlags.push(rows1[i].visceraFixedFlag);
	}
	//病理计划-脏器/组织学检查下是否需要镜检数组
	var histopathCheckFlags=[];
	for(var i=0;i<rows1.length;i++){
		histopathCheckFlags.push(rows1[i].histopathCheckFlag);
	}
	var studyNoPara = $('#studyNoPara').val();
	//异常组织剖检checkbox的值
	var abnVisceraAnatomy=0;
	 if(document.getElementById("abnVisceraAnatomy").checked ==true){
		 abnVisceraAnatomy=1;
	 }
	//异常组织固定checkbox的值
	var abnViscera=0;
	 if(document.getElementById("abnViscera").checked ==true){
		 abnViscera=1;
	 }
	//异常组织镜检checkbox的值
	 var abnVisceraHistopathCheckFlag=0;
	 if(document.getElementById("abnVisceraHistopathCheckFlag").checked ==true){
		 abnVisceraHistopathCheckFlag=1;
		 document.getElementById("abnViscera").checked =true
	 }
//	if(abnViscera!=0){
//		var atanomyCheckFlag=0;
//		 var check_array1=document.getElementsByName("atanomyCheckFlag");
//	    for(var i=0;i<check_array1.length;i++)
//	    {
//	        if(check_array1[i].checked==true)
//	        {         
//	       	 atanomyCheckFlag=parseInt(atanomyCheckFlag)+parseInt(check_array1[i].value);
//	        }
//	    }
//	   var histopathCheckFlag=0;
//	   var check_array2=document.getElementsByName("histopathCheckFlag");
//	   for(var i=0;i<check_array2.length;i++)
//	   {
//	       if(check_array2[i].checked==true)
//	       {         
//	       	histopathCheckFlag=parseInt(histopathCheckFlag)+parseInt(check_array2[i].value);
//	       }
//	   }
//	   if(atanomyCheckFlag!=0||histopathCheckFlag!=0){
		   $.ajax({
			    url:sybp()+'/tblPathPlanCheckAction_addPathPlanCheck.action?studyNo='
			    +encodeURIComponent(studyNoPara)+'&visceraNames='+encodeURIComponent(visceraNames)
                +'&atanomyCheckFlags='+atanomyCheckFlags+'&visceraFixedFlags='+visceraFixedFlags+'&histopathCheckFlags='+histopathCheckFlags
                +'&abnVisceraFixedFlag='+abnViscera+'&abnVisceraHistopathCheckFlag='+abnVisceraHistopathCheckFlag+'&abnVisceraAnatomyCheckFlag='+abnVisceraAnatomy,
//			    url:sybp()+'/tblStudyPlanAction_editAbnViscera.action?studyNo='+studyNoPara+'&abnVisceraFixedFlag='+abnViscera+'&abnVisceraHistopathCheckFlag='+abnVisceraHistopathCheckFlag,
				type:'post',
				data:'',
				dataType:'json',
				success:function(r){
					if(r && r.success){
						$('#saveCheckDialogButton').linkbutton('enable');
						$('#editPathPlanCheckDialog').dialog('close');
						$('#pathPlanCheckTable').datagrid('reload');
					}else{
						parent.parent.showMessager(2,'病理计划-脏器/组织学检查添加失败',true,5000);
					}
				}
			});
//	   }else{
//		   $('#editPathPlanCheckDialog').dialog('close');
//	   }
//	}else{
//		$('#editPathPlanCheckDialog').dialog('close');
//	}
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
//如果异常脏器镜检选择，异常脏器固定也将被选中
function attachedAbnCheck(){
	if(document.getElementById("abnVisceraHistopathCheckFlag").checked == true){
		document.getElementById("abnViscera").checked = true;
		document.getElementById("abnViscera").disabled = true;
		document.getElementById("abnVisceraAnatomy").checked = true;
		document.getElementById("abnVisceraAnatomy").disabled = true;
	}else{
		document.getElementById("abnViscera").disabled = false;
		document.getElementById("abnVisceraAnatomy").disabled = false;
		if(document.getElementById("abnViscera").checked == true){
			document.getElementById("abnVisceraAnatomy").checked = true;
			document.getElementById("abnVisceraAnatomy").disabled = true;
		}else{
			document.getElementById("abnVisceraAnatomy").disabled = false;
		}
	}
}
//如果异常脏器固定选择，异常脏器剖检也将被选中
function attachedAbnCheck2(){
	if(document.getElementById("abnViscera").checked == true){
		document.getElementById("abnVisceraAnatomy").checked = true;
		document.getElementById("abnVisceraAnatomy").disabled = true;
	}else{
		document.getElementById("abnVisceraAnatomy").disabled = false;
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
					document.getElementById("abnVisceraAnatomy").checked =true;
//			       	 document.getElementById("abnVisceraAnatomy").disabled =true;
//			       	 document.getElementById("abnViscera").checked =true;
//			       	 document.getElementById("abnViscera").disabled = true;
//			       	 document.getElementById("abnVisceraHistopathCheckFlag").checked =true;
				}else{
					document.getElementById("abnVisceraAnatomy").checked =false;
				}
				if(r.abnVisceraFixedFlag==1){
					document.getElementById("abnViscera").checked =true;
					document.getElementById("abnVisceraAnatomy").checked =true;
			       	 document.getElementById("abnVisceraAnatomy").disabled =true;
				}else{
					document.getElementById("abnViscera").checked =false;
				}
				if(r.abnVisceraHistopathCheckFlag==1){
					document.getElementById("abnVisceraAnatomy").checked =true;
			       	 document.getElementById("abnVisceraAnatomy").disabled =true;
			       	 document.getElementById("abnViscera").checked =true;
			       	 document.getElementById("abnViscera").disabled = true;
			       	 document.getElementById("abnVisceraHistopathCheckFlag").checked =true;
				}else{
					document.getElementById("abnVisceraHistopathCheckFlag").checked =false;
				}
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
