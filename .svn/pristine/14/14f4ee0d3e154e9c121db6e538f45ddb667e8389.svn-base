//插入申请日期
function addRecDttime(){
	var theNewTime = $('#electiondate').datebox('getValue');
	if(theNewTime == ""){
		$.messager.alert('提示','新增日期不能为空!');
	}else{
		 $.ajax({
				url:sybp()+'/tblTiprpAppIndAction_adddate.action',
				type:'post',
				cache:false,
				data:{
				  endTime:$('#electiondate').datebox('getValue'),
				},
				dataType:'json',
				success:function(r){
				 if(r && r.success ){
				      $('#electiondate').datebox('setValue', r.nexttime);
				      addRexDttimerows();
			      }else{
			    	  $.messager.alert('提示','请检查重复录入的日期');
			      }
				}
				});
		 }
		 
}
//显示申请日期
function addRexDttimerows(){
		tiprpApprecdtdate=$('#tiprpApprecdtdate').datagrid({
			url : sybp()+'/tblTiprpAppIndAction_adddate.action',
			title:'',
			height: 230,
			width:220,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			columns :[[{
				title : '领用日期',
				field : 'showtime',
				width : 100,
				formatter: function(value,row,index){
				if (1==1){
					return "<span style='cursor:pointer'>"+value+"</span>";
				}
			 }
			},{
				title :'操作',
				field :' ',
				width :99,
				align:'center',
				formatter: function(value,row,index){
				if (1==1){
					return "<span style='cursor:pointer'>删除</span>";
				}
			}
				
			}]],
			onClickRow:function(rowIndex, rowData){
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
				var showtime = rowData.showtime;
				removeRecDttime(showtime);
				$(this).datagrid('deleteRow',rowIndex);
			}
			
			
		});
		}
//删除申请日期
function removeRecDttime(showtime){
		 $.ajax({
				url:sybp()+'/tblTiprpAppIndAction_removeRecDttime.action',
				type:'post',
				cache:false,
				data:{
				  endTime:showtime,
				},
				dataType:'json',
				success:function(r){
				 if(r ){
				      addRexDttimerows();
			      }
				}
				});
		}


//显示供试品申请结果  AppIndpreview(没有计算分组之前的action)
function selectAdmin(){
	 tblTiprpAppData=$('#tblTiprpAppData').datagrid({
   	    url : sybp()+"/tblTiprpAppIndAction_toaddSaveAppIndGroup.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height:260,
			width:380,
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			columns :[[{
				title : '动物编号',
				field : 'aniCode',
				width : 60
			},{
				title :'动物体重',
				field :'aniWeight',
				width :60,
				align:"right"
			},{
				title :'',
				field :'weighUnit',
				width :35,
				align:"left"
			},{
				title :'供试品重量',
				field :'smplWeight',
				width :80,
				editor:{type:'text', options:{required : true,min:1,precision:0}},
				align:"right"
			},{
				title :'',
				field :'smplUnit',
				width :35,
				align:"left"
			},{
				title :'胶囊数量(粒)',
				field :'capsNum',
				width :80,
				halign:'center',
				editor:{type:'numberbox',options:{required : true,min:0,precision:0}}
				
			}]],
			onLoadSuccess:function(data){
		     for(var i=0;i<data.rows.length;i++){
		    	 $("#size").val(data.rows.length);
		    	 tblTiprpAppData.datagrid('beginEdit',i);
			 } 
	        },
	        onClickRow :function(rowIndex, rowData){
	        	tblTiprpAppData.datagrid('unselectAll');
	        	tblTiprpAppData.datagrid('selectRow',rowIndex);	
			},
			
			

		}); //end 


	}


function afterSaveButtion(){
	for(var i=0;i<$('#size').val();i++){
		tblTiprpAppData.datagrid('endEdit',i);
	} 
	var rows =$('#tblTiprpAppData').datagrid('getRows');
	for(var i=0;i<rows.length;i++){ 
		$.ajax({
			url:sybp()+'/tblTiprpAppIndAction_appDataonSave.action',
			type:'post',
			cache:false,
			data:{
			"aniCode":rows[i].aniCode,
			"aniWeight":rows[i].aniWeight,
			"smplWeight":rows[i].smplWeight,
			"capsNum":rows[i].capsNum,
			"DatasmplUnit":rows[i].smplUnit,
		    "weightUnit":rows[i].weightUnit
		    },
			dataType:'json',
			success:function(r){
			 if(r){

			 }
				
			}
		});

	}
	
}
function onDialogAppIndSaveButton(){
	
	$.ajax({
		url:sybp()+'/tblTiprpAppIndAction_nullCheckData.action',
		type:'post',
		cache:false,
		dataType:'json',
		success:function(r){
		 if(r && r.success){
			 $.ajax({
					url:sybp()+'/tblTiprpAppIndAction_toaddSaveAppInd.action',
					type:'post',
					cache:false,
					data:$('#tblTiprpAddForm').serialize(),
					dataType:'json',
					success:function(r){
					 if(r && r.success){
						 selectAdmin();
						 $('#tblTiprpAppDataDialog').dialog('open');
						 $('#tblTiprpAppDataDialog2').dialog('open');
						 
				      }else{
				    	  var msg = r.msg;
				    	  $.messager.alert('提示','请检查输入数据   :    '+msg);
					  }
						
					}
					});
		 
	      }else
	    	  $.messager.alert('提示','请检查输入领用日期！');
		  }
			
		});
	
}




/** 签字检查*/      
function checkBeforeTiprpAppSign(){
	var studyNoPara =$('#studyNoPara').val();
	for(var i=0;i<$('#size').val();i++){
		tblTiprpAppData.datagrid('endEdit',i);
	} 
	if(studyNoPara == '' || studyNoPara == null){
		return;
	}else {
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblTiprpAppIndAction_signCheck.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"json",
	        data:"studyNoPara="+studyNoPara,
	        async:false,
	        success:function(r)
	        {
				if(r ){
						$.messager.confirm('确认对话框', '确认供试品配制申请，确认后不可在修改!', function(r){
								if (r){
									qm_showQianmingDialog('eventAfterTiprpAppIndSign');
									afterSaveButtion();
								}
						});
				}
	        },
	        error:function()
	        {
	             //$("#errorMsg").html("与服务器交互错误!");
	             parent.parent.showMessager(3,'与服务器交互错误!',true,5000);
	        }
	   });
	}
	
}

/**签字过后方法esType=10*/
function eventAfterTiprpAppIndSign(){
	$.ajax({
		url:sybp()+"/tblTiprpAppIndAction_tiprpAppIndSign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=10",
		type:'post',
		dataType:'json',
		success:function(r){
			if(r && r.success){
				
				parent.parent.showMessager(1,'签字成功',true,5000);
				var aclick=document.getElementById("aclick")
				aclick.click();
			}else{
				parent.parent.showMessager(3,'签字失败',true,5000);
			}
		}
	});
	
}


/** 撤销检查*/      
function checkBeforeTiprpAppRevoke(){
	var studyNoPara =$('#studyNoPara').val();
	if(studyNoPara == '' || studyNoPara == null){
		return;
	}else {
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblTiprpAppIndAction_signCheck1.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"json",
	        data:{
				studyNoPara:$('#studyNoPara').val(),
				AppInd:$('#AppInd').val(),
			},
	        async:false,
	        success:function(r)
	        {
				if(r && r.success ){
						$.messager.confirm('确认对话框', '确认撤销供试品配制申请，确认后不可在修改!', function(r){
								if (r){
									 qm_showQianmingDialog('eventAfterTiprpAppIndRevoke');
								}
						});
				}else{
					$.messager.alert('提示','申请已处理无法撤销');
				}
	        },
	        error:function()
	        {
	             //$("#errorMsg").html("与服务器交互错误!");
	             parent.parent.showMessager(3,'与服务器交互错误!',true,5000);
	        }
	   });
	}
	
}

function revokeAppInd(){
	$.ajax({
		url:sybp()+'/tblTiprpAppIndAction_revokeAppInd.action',
		type:'post',
		cache:false,
		data:{
			studyNoPara:$('#studyNoPara').val(),
			AppInd:$('#AppInd').val(),
		},
		dataType:'json',
		success:function(r){
		 if(r && r.success){
			
	      }else{
	    	  $.messager.alert('提示','申请已处理无法撤销');
		  }
			
		}
		});
	    
	}

/**撤销过后方法esType=11*/
function eventAfterTiprpAppIndRevoke(){
	$.ajax({
		url:sybp()+"/tblTiprpAppIndAction_tiprpAppIndSign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=11",
		type:'post',
		data:{
			AppInd:$('#AppInd').val(),
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.parent.showMessager(1,'撤销成功',true,5000);
				$('#tiprpAppInd').datagrid('updateRow',{
		 			index: $('#AppInd').val()-1,
		 			row:{
					 appStatus:-1
					}
		 		});
				$('#AppIndrescindSignButton').linkbutton('disable');
			}else{
				parent.parent.showMessager(3,'撤销失败',true,5000);
			}
		}
	});
	
}

//显示供试品申请结果
function usetblWeighInd(){
	usetblWeighInd=$('#usetblWeighInd').datagrid({
   	    url : sybp()+"/tblWeightIndAction_selectTbLWeighDataList.action?studyNoPara="+$('#studyNoPara').val(),
   	 title:'',
		height: 180,
		width:340,
		iconCls:'',//'icon-save',
		//pagination:true,//下面状态栏
		pageSize:100,
		pageList:[50,100],
		//fit:true,
		fitColumns:true,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		//border:false,
		//idField:'id', //pk
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
		columns :[[{
			title : '动物编号',
			field : 'aniCode',
			width : 100,
			align:'center'
		},{
			title :'动物体重',
			field :'weight',
			width :100,
			align:'right',
		},{
			title :'',
			field :'unit',
			width :30,
			align:'left'
		}]],
		onClickRow :function(rowIndex, rowData){
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
			$('#rowIndex').val(rowIndex);	
		},

		}); //end 


	}

//----------------------------------------------

//新增登体
function onNewTblTiprpAppIndButton(){
	$.ajax({
		url:sybp()+'/tblWeightIndAction_toNewTiprpAppInd.action',
		type:'post',
		cache:false,
		data:{
			studyNoPara:$('#studyNoPara').val(),
		},
		dataType:'json',
		success:function(r){
			if(r && r.empty != 1 ){
				$('#msg1').html(r.msg);
				$('#dosageNum').dialog('open');
				$('#weightSn').val(r.weightSn);
			    $('#dosageNum').dialog('open');
			    $('#weightDate').val(r.weightDate);
			    if(r.sign == 1){
			    	$('#toNewAppInd1').linkbutton('disable');
			    }
			}else if(r.empty ==1 ){
				$.messager.confirm('提示', '当前课题还未录入动物体重，是否新增动物体重!', function(r){
					if (r ){
						parent.addoneNewWeighIndTab($('#studyNoPara').val());
					}
			});
			}
		}
	});			
}



function onDialogTiprpSaveButton(){
		if($('#tblTiprpAddForm').form('validate')){
			
			 $.ajax({
				 url:sybp()+'/tblTiprpAppIndAction_addSave.action',
				 type:'post',
				 data:$('#tblTiprpAddForm').serialize(),
				 dataType:'json',
				 success:function(r){
				  if(r && r.success){
					  $('#saveDialogButton').linkbutton('disable');
					   $('#AppSn').val(r.AppSn);
					   $('#AppRecDStudyNo').val(r.StudyNo);
					   $('#AppRecDtAppSn').val(r.AppSn);
					   submitWeghitNumFrom();
					}else{
						$.messager.alert('提示','请检查录入的数据');
					}
			   }
			 });
		}
}

function UsecurrentWeighInd(){  
	var rows1 =$('#dosegroup').datagrid('getChecked');
	if(rows1.length<1){
		$.messager.alert('提示','请选择剂量组!');
	}else{
	selectgroup();
	
	$.ajax({
		url:sybp()+'/tblTiprpAppIndAction_toNewApply.action',
		type:'post',
		cache:false,
		data:{
			studyNoPara:$('#studyNoPara').val(),
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				$('#StudyNo').val(r.StudyNo);
				$('#AppSn').val(r.AppSn);
				$('#SmplCode').val(r.SmplCode);
				$('#WeighUnit').val(r.WeighUnit);
				$('#SmplUnit').val(r.SmplUnit);
				$('#DevType').val(r.DevType);
				$('#DevVal').val(r.DevVal);
				$('#CapsSpec').val(r.CapsSpec);
				if(r.Precision = null){
					$('#Precision').combobox('select',r.Precision);
				}else{
					$('#Precision').combobox('select',1);
				}
				
				
				addRexDttimerows();
				$('#tblTiprpAddEditDialog').dialog('open');
				$('#tblTiprpAddEditDialog2').dialog('open');
				
			}else{
				$.messager.alert('提示','请新增动物体重!');
			}
		}
	   });	
	}
}

//剂量分组选择
function selectgroup(){
	
	$.ajax({
		url:sybp()+'/tblTiprpAppIndAction_onUncheckDoseGroup.action',
		type:'post',
		cache:false,
		dataType:'json',
		success:function(r){
		var rows =$('#dosegroup').datagrid('getChecked');
		var group ='?';
		for(var i=0;i<rows.length;i++){ 
			group = group +'group ='+$('#dosegroup').datagrid('getChecked')[i].dosageNum+'&'
		 }
		group = group +'group= '
		$.ajax({
			url:sybp()+'/tblTiprpAppIndAction_onCheckDoseGroup.action'+group,
			type:'post',
			cache:false,
		   dataType:'json',
		   success:function(r){}
		});}
	});
	
	
	
	
}

function submitWeghitNumFrom(){	
	$.ajax({
		url:sybp()+'/tblTiprpAppIndAction_toAddAppData.action',
		type:'post',
		cache:false,
		data:{
			studyNoPara:$('#studyNoPara').val(),
			AppInd:$('#AppSn').val(),
		},
		dataType:'json',
		success:function(r){
			if(r&&r.success ){
				$('#AppRecDtStudyNo').val(r.StudyNo);
				$('#AppRecDtAppSn').val(r.AppSn);
				$('#tblTiprpAppRecDtDialog').dialog('open');
				$('#tblTiprpAppRecDtDialog2').dialog('open');
				$('#tblTiprpAddEditDialog').dialog('close');
				$('#tblTiprpAddEditDialog2').dialog('close');
			}
		}
		});
			
}


function selectdosegroup(){
	$.ajax({
		url:sybp()+'/tblTiprpAppIndAction_SelectDoseGroup.action',
		type:'post',
		cache:false,
		data:{
			studyNoPara:$('#studyNoPara').val(),
		},
		dataType:'json',
		success:function(r){
			if(r){
				
				$('#SelectdosegroupDialog').dialog('open');
				$('#SelectdosegroupDialog2').dialog('open');
				
			}
		}
	   });	
}

function toaddWeighInd(){
	parent.addoneNewWeighIndTab($('#studyNoPara').val());
}	


function AppIndrescindSign(){
         checkBeforeTiprpAppRevoke();
}	




