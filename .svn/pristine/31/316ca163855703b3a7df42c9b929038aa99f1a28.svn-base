//动物列表初始化
function initAnatomyReqAnimalList(addOrEdit){
	var url;
	if(addOrEdit=='add'){
		url='';
		$('#animalNumber').val(0);
	}else if(addOrEdit=='edit'||addOrEdit=='view'){
		url=sybp()+'/tblAnatomyReqAnimalListAction_toEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&anatomyReqNo='+reqNo+'&addOrEdit='+addOrEdit;
	}else if(addOrEdit=='dissectNum'){
		var dissectNum=$('#dissectNum').combobox('getValue');
		url=sybp()+'/tblDissectPlanAction_getDetailListByStudyNoAndDissectNum.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&dissectNumPara='+dissectNum;
	}
	 $('#anatomyReqAnimalList').datagrid({    
			url : url,
			title:'',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:tableHeight,
			width:190,
			border:0,
			showHeader:true,
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true,
			},{
				title:'动物编号',
				field:'animalCode',
				width:105,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
					if (row.isAnatomyReq == 1 && row.addOrEdit != 'view'){
						return value+"(已被申请)";
					}else{
						return value;
					}
			    }
			},{
				title:'性别',
				field:'gender',
				width:60,
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
			},{
				title:'',
				field:'addOrEdit',
				width:105,
				halign:'center',
				align:'left',
				hidden:true
			}
			]],
			toolbar:'#anatomyReqAnimalListToobar',
			onLoadSuccess:function(data){
		       var animalNumber=$('#anatomyReqAnimalList').datagrid('getRows').length;
	           $('#animalNumber').val(animalNumber);
		    },
		    onSelect:function(rowIndex, rowData){
			},
		});
}
//编辑动物列表按钮事件
function editAnatomyReqAnimalListButton(){
	showEditAnatomyReqAnimalListDialog('','edit','编辑动物列表');
}
//初始化病理计划-脏器称重表
function initAnatomyReqVisceraWeighTable(addOrEdit){
	var url;
	if(addOrEdit=='add'){
		url=sybp()+'/tblPathPlanVisceraWeighAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara);
	}else if(addOrEdit=='edit'||addOrEdit=='view'){
		url=sybp()+'/tblAnatomyReqVisceraWeighAction_toEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+reqNo;
	}                
	$('#anatomyReqVisceraWeighTable').datagrid({
    	url:url,
    	title:'',
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		singleSelect:true,
		height:tableHeight+15,
		width:497,
		border:0,
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
			field:'sn',
			width:50,
			halign:'center',
			align:'center',
			hidden:true,
			formatter: function(value,row,index){
			return index+1;
	    } 
		},{
			title:'脏器名称',
			field:'visceraName',
			width:160,
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
			width:120,
			halign:'center',
			align:'left'
		} 
		]],
		toolbar:'#anatomyReqVisceraWeighToobar',

     });
}
function editPathPlanVisceraWeighButton(){
	showEditPathPlanVisceraWeighDialog('','edit','编辑解剖申请-脏器称重')
}
//初始化病理计划-脏器/组织学检查表
function initAnatomyReqPathPlanCheckTable(addOrEdit){
	var url;
	if(addOrEdit=='add'){
		url=sybp()+'/tblPathPlanCheckAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara);
	}else if(addOrEdit=='edit'||addOrEdit=='view'){
//		alert(reqNo);
		url=sybp()+'/tblAnatomyReqPathPlanCheckAction_toEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+reqNo;
	}
	$('#anatomyReqPathPlanCheckTable').datagrid({
    	url:url,
    	title:'',
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		singleSelect:true,
		height:tableHeight,
		width:425,
		border:0,
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
			field:'sn',
			width:50,
			halign:'center',
			align:'center',
			hidden:true,
			formatter: function(value,row,index){
			return index+1;
	    } 
		},{
			title:'脏器名称',
			field:'visceraName',
			width:160,
			halign:'center',
			align:'left'
		},{
			title:'是否需要剖检',
			field:'atanomyCheckFlag',
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
			title:'是否需要固定',
			field:'visceraFixedFlag',
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
			title:'是否需要镜检',
			field:'histopathCheckFlag',
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
		}
		]],
		toolbar:'#anatomyReqPathPlanCheckToobar',
     });
}
//编辑解剖申请-脏器/组织学检查按钮事件
function editPathPlanCheckTableButton(){
	showEditPathPlanCheckDialog('','edit','编辑解剖申请-脏器/组织学检查')
}
//解剖申请保存按钮事件
function saveAnatomyReq(){
	if( $('#anatomyReqForm').form('validate') ){
		//$('#saveAnatomyReqButton').linkbutton('disable');
		//$('#submitAnatomyReqButton').linkbutton('disable');
		addAnatomyReqToDB(0);
		
	}
	
}
//解剖申请提交按钮事件
function submitAnatomyReq(){
	if( $('#anatomyReqForm').form('validate') ){
		//$('#saveAnatomyReqButton').linkbutton('disable');
		//$('#submitAnatomyReqButton').linkbutton('disable');
		var rows=$('#anatomyReqAnimalList').datagrid('getRows');
		for(var i=0;i<rows.length;i++){
			if(rows[i].isAnatomyReq == 1){
				parent.parent.showMessager(2,'已被申请解剖的动物不能再次申请',true,5000);
				$('#saveAnatomyReqButton').linkbutton('enable');
				$('#submitAnatomyReqButton').linkbutton('enable');
				return;
			}
		}
		$.messager.confirm('确认对话框', '确认提交此申请？', function(r){
			if(r){
				qm_showQianmingDialog('addAnatomyReqToDB(1)');
//				 addAnatomyReqToDB(1);
			}else{
				$('#saveAnatomyReqButton').linkbutton('enable');
				$('#submitAnatomyReqButton').linkbutton('enable');
			}
		});
		
	 
	  
	}
}
//解剖申请新增(2：为打印前保存)
function addAnatomyReqToDB(submitFlag){
	var animalNumber=$('#animalNumber').val();
	if(animalNumber==0){
		parent.parent.showMessager(2,'动物数量不能为0',true,5000);
		$('#saveAnatomyReqButton').linkbutton('enable');
		$('#submitAnatomyReqButton').linkbutton('enable');
		return;
	}
	var beginDate=$('#beginDatebox').datebox('getValue');
	var endDate=$('#endDatebox').datebox('getValue');
	var rows=$('#anatomyReqAnimalList').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].isAnatomyReq == 1){
			parent.parent.showMessager(2,'已被申请解剖的动物不能再次申请',true,5000);
			$('#saveAnatomyReqButton').linkbutton('enable');
			$('#submitAnatomyReqButton').linkbutton('enable');
			return;
		}
	}
	var anatomyRsn=$('#anatomyRsn').combobox('getValue');
	var anatomyPlanNum=0;
	if(anatomyRsn==1){
		anatomyPlanNum=$('#dissectNum').combobox('getValue');
	}
	var testPhase=$('#testPhase').combobox('getText');
	//解剖申请-申请动物编号数组
	var animalCodes=[];
	for(var i=0;i<rows.length;i++){
		animalCodes.push(rows[i].animalCode);
	}
	//解剖申请-申请动物性别
	var genders=[];
	for(var i=0;i<rows.length;i++){
		genders.push(rows[i].gender);
	}
	var rows1=$('#anatomyReqPathPlanCheckTable').datagrid('getRows');
	//解剖申请-脏器/组织学检查下脏器数组
	var visceraNames=[];
	for(var i=0;i<rows1.length;i++){
		visceraNames.push(rows1[i].visceraName);
	}
	//解剖申请-脏器/组织学检查下是否需要剖检数组
	var atanomyCheckFlags=[];
	for(var i=0;i<rows1.length;i++){
		atanomyCheckFlags.push(rows1[i].atanomyCheckFlag);
	}
	//病理计划-脏器/组织学检查下是否需要固定数组
	var visceraFixedFlags=[];
	for(var i=0;i<rows1.length;i++){
		visceraFixedFlags.push(rows1[i].visceraFixedFlag);
	}
	//解剖申请-脏器/组织学检查下是否需要镜检数组
	var histopathCheckFlags=[];
	for(var i=0;i<rows1.length;i++){
		histopathCheckFlags.push(rows1[i].histopathCheckFlag);
	}
	var rows2=$('#anatomyReqVisceraWeighTable').datagrid('getRows');
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
	//判断解剖申请-脏器/组织学检查脏器数组，解剖申请-脏器称重脏器数组，两个数组长度不能同时等于0
	if(visceraNames.length==0&&visceraNames1.length==0){
		parent.parent.showMessager(2,'病理计划不能为空',true,5000);
		$('#saveAnatomyReqButton').linkbutton('enable');
		$('#submitAnatomyReqButton').linkbutton('enable');
		return;
	}
	
	//保存和提交之前
	$.ajax({
		url:sybp()+'/tblAnatomyReqAction_isAnimalAndVisCon.action?animalCodes='+animalCodes+'&genders='+genders
		+'&visceraNames='+encodeURIComponent(visceraNames)
		+'&visceraNames1='+encodeURIComponent(visceraNames1)
		+'&attachedVisceras='+encodeURIComponent(attachedVisceras),
		type:'post',
		data:'',
		dataType:'json',
		success:function(r){
			if(r&&r.success){
				
				realWriteToDB(submitFlag,studyNoPara,anatomyRsn,anatomyPlanNum,testPhase,animalCodes,genders,visceraNames,atanomyCheckFlags,
						visceraFixedFlags,histopathCheckFlags,visceraNames1,partVisceraSeparateWeighs,fixedWeighFlags,attachedVisceras,
						beginDate,endDate,reqId,addOrEdit,reqNo)
			}else{
				$.messager.confirm('确认框',r.msg+',确定要继续吗?',function(r0){
				    if (r0){
				    	realWriteToDB(submitFlag,studyNoPara,anatomyRsn,anatomyPlanNum,testPhase,animalCodes,genders,visceraNames,atanomyCheckFlags,
				    			visceraFixedFlags,histopathCheckFlags,visceraNames1,partVisceraSeparateWeighs,fixedWeighFlags,attachedVisceras,
				    			beginDate,endDate,reqId,addOrEdit,reqNo)
				    }else{
				    	return ;
				    }
				});
				
			}
			
			
		}
	});
}

function realWriteToDB(submitFlag,studyNoPara,anatomyRsn,anatomyPlanNum,testPhase,animalCodes,genders,visceraNames,atanomyCheckFlags,
		visceraFixedFlags,histopathCheckFlags,visceraNames1,partVisceraSeparateWeighs,fixedWeighFlags,attachedVisceras,
		beginDate,endDate,reqId,addOrEdit,reqNo){
	if (submitFlag != 2){
		$.ajax({
			url:sybp()+'/tblAnatomyReqAction_add.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&anatomyRsn='
			+anatomyRsn+'&anatomyPlanNum='+anatomyPlanNum+'&testPhase='+encodeURIComponent(testPhase)
			+'&animalCodes='+animalCodes+'&genders='+genders+'&visceraNames='+encodeURIComponent(visceraNames)
			+'&atanomyCheckFlags='+atanomyCheckFlags+'&visceraFixedFlags='+visceraFixedFlags+'&histopathCheckFlags='+histopathCheckFlags
			+'&visceraNames1='+encodeURIComponent(visceraNames1)+'&partVisceraSeparateWeighs='
			+partVisceraSeparateWeighs+'&fixedWeighFlags='+fixedWeighFlags+'&attachedVisceras='
			+encodeURIComponent(attachedVisceras)+'&submitFlag='+submitFlag
			+'&beginDate='+beginDate+'&endDate='+endDate+'&reqId='+reqId+'&addOrEdit='+addOrEdit+'&reqNo='+reqNo,
			type:'post',
			data:'',
			dataType:'json',
			success:function(r){
			if(r&&r.success){
				if(submitFlag==0){
					parent.parent.showMessager(1,'保存成功',true,5000);
					
				}else{
					parent.parent.showMessager(1,'提交成功',true,5000);
				}
				reqId=r.reqId;
				$('#backButton').click();
			}else{
				if(r && r.msg !=''){
					parent.parent.showMessager(2,r.msg,true,5000);
				}else{
					parent.parent.showMessager(2,'请检查数据',true,5000);
				}
			}
		}
		});
	}else{
		//打印,先保存
		$.messager.confirm('确定','打印前将保存数据，是否继续？',function(r){
			if(r){   
				$.ajax({
						url:sybp()+'/tblAnatomyReqAction_add.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&anatomyRsn='
						+anatomyRsn+'&anatomyPlanNum='+anatomyPlanNum+'&testPhase='+encodeURIComponent(testPhase)
						+'&animalCodes='+animalCodes+'&genders='+genders+'&visceraNames='+encodeURIComponent(visceraNames)
						+'&atanomyCheckFlags='+atanomyCheckFlags+'&visceraFixedFlags='+visceraFixedFlags+'&histopathCheckFlags='+histopathCheckFlags
						+'&visceraNames1='+encodeURIComponent(visceraNames1)+'&partVisceraSeparateWeighs='
						+partVisceraSeparateWeighs+'&fixedWeighFlags='+fixedWeighFlags+'&attachedVisceras='
						+encodeURIComponent(attachedVisceras)+'&submitFlag='+0
						+'&beginDate='+beginDate+'&endDate='+endDate+'&reqId='+reqId+'&addOrEdit='+addOrEdit+'&reqNo='+reqNo,
						type:'post',
						data:'',
						dataType:'json',
						success:function(r){
							if(r&&r.success){
								parent.parent.showMessager(2,'数据加载中...',true,5000);
					        	window.location.href=sybp()+'/tblAnatomyReqAction_toReport.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+r.reqNo+'&reqId='+r.reqId
					        	+'&toWhere=apply';
							}else{
//								parent.parent.showMessager(2,'请检查数据',true,5000);
								if(r && r.msg){
									parent.parent.showMessager(2,r.msg,true,5000);
								}else{
									parent.parent.showMessager(2,'请检查数据',true,5000);
								}
							}
						}
				});
		    } 
		});
	}
}