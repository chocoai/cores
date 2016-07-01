//初始化合同数据表格
function initContractDatagrid(){
	var tableHeight = (document.body.clientHeight - 105)/3;
	var tableWidth =  document.body.clientWidth -225;
		$('#contracttable').datagrid({
			url:sybp()+'/contractAction_loadContractList.action?contractCode='
			+'2016-001',
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
			columns :[[{
				title:'合同编号',
				field:'contractCode',
				width:80,
				halign:'center',
				align:'left'
			},{
				title:'合同名称',
				field:'contractName',
				width:200,
				halign:'center',
				align:'left'
			},{
				title:'合同金额',
				field:'contractPrice',
				width:80,
				halign:'center',
				align:'left'
		    },{
				title:'开始日期',
				field:'effectiveDate',
				width:80,
				halign:'center',
				align:'center'
		    },{
				title:'结束日期',
				field:'finishDate',
				width:80,
				halign:'center',
				align:'center'
		    },{
				title:'合同状态',
				field:'contractState',
				width:60,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
					if ( value == "0" ){
						return "未生效";
					}else if(value=="1"){
	                    return "执行中";
					}else if(value=="2"){
	                    return "可编辑";
					}else if(value=="3"){
	                    return "完成";
					}else if(value=="-1"){
	                    return "终止";
					}  
				}
		    },{
				title:'委托方名称',
				field:'sponsorName',
				width:160,
				halign:'center',
				align:'left'
				
			},{
				title:'委托方地址',
				field:'sponsorAddress',
				width:223,
				halign:'center'
			},{
				title:'委托方联系人',
				field:'sponsorLinkman',
				width:80,
				halign:'center'
			},{
				title:'委托方电话',
				field:'sponsorTel',
				width:90,
				halign:'center'
			},{
				title:'委托方电子邮件',
				field:'sponsorEmail',
				width:90,
				halign:'center'
			},{
				title:'委托方传真',
				field:'sd5',
				width:90,
				halign:'sponsorFax'
			},{
				title:'报告出具方',
				field:'venderName',
				width:200,
				halign:'center'
			},{
				title:'报告出具方地址',
				field:'venderAddress',
				width:223,
				halign:'center'
			},{
				title:'报告出具方联系人',
				field:'venderLinkman',
				width:80,
				halign:'center'
			},{
				title:'报告出具方电话',
				field:'venderTel',
				width:90,
				halign:'center'
			},{
				title:'报告出具方电子邮件',
				field:'venderEmail',
				width:90,
				halign:'center'
			},{
				title:'报告出具方传真',
				field:'venderFax',
				width:90,
				halign:'center'
			},{
				title:'操作',
				field:'operate',
				width:50,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == "0" ){
						return "";
					}else {
	                    return "编辑";
					} 
				}
		    },{
		    	title:'操作时间',
		    	field:'operateTime',
		    	width:122,
		    	halign:'center',
		    	align:'left'
		    },{
		    	title:'操作原因',
		    	field:'operateRsn',
		    	width:100,
		    	halign:'center',
		    	align:'left'
		    }]]
		});
}

//更新表格数据
function updateContractDatagrid(rowData){
//	var contractCodeRow = $('#contractCode').datagrid('getChecked');
	var contractCode ;
	if(rowData){
		contractCode = rowData.contractCode;
	}
	if(contractCode ){
		$('#contracttable').datagrid({
			url:sybp()+'/contractAction_loadContractList.action?contractCode='
				+contractCode
		});
	}
}
//初始化供试品数据表格
function initTestitemDatagrid(){
	var tableHeight = (document.body.clientHeight - 105)/3;
	var tableWidth =  document.body.clientWidth -225;
	$('#testitemtable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			title:'合同编号',
			field:'contractCode',
			width:80,
			halign:'center',
			align:'left'
		},{
			title:'供试品编号',
			field:'tiNo',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'供试品名称',
			field:'tiName',
			width:150,
			halign:'center',
			align:'left'
		},{
			title:'含量',
			field:'content',
			width:50,
			halign:'center',
			align:'left'
			
		},{
			title:'外观',
			field:'physical',
			width:70,
			halign:'center',
			align:'left'
			
		},{
			title:'存储条件',
			field:'storageCondition',
			width:50,
			halign:'center',
			align:'left'
			
		},{
			title:'有效期限/失效期限',
			field:'validityPeriod',
			width:120,
			halign:'center',
			align:'left'
			
		},{
			title:'留样量',
			field:'reserveNum',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'批号',
			field:'batchNo',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'封样号',
			field:'sealNo',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'备案号',
			field:'fileNo',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'熔点',
			field:'meltPoint',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'沸点',
			field:'boilPoint',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'光解性',
			field:'photolysis',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'挥发性',
			field:'volatility',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'成分',
			field:'composition',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'相对密度',
			field:'density',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'水中溶解度',
			field:'waterSolubility',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'水中稳定性',
			field:'waterStability',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'有机溶剂溶解度',
			field:'solventSolubility',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'有机溶剂稳定性',
			field:'solvenStability',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'PH值',
			field:'ph',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'特殊安全防护措施',
			field:'securityMeasures',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'稳定性和均一性分析',
			field:'analysis',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'样品检测后处理',
			field:'postTreatment',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'CAS',
			field:'cas',
			width:50,
			halign:'center',
			align:'left'
		},{
			title:'提供方',
			field:'venderName',
			width:160,
			halign:'center'
		},{
			title:'提供方地址',
			field:'venderAddress',
			width:200,
			halign:'center'
		},{
			title:'提供方联系人',
			field:'venderLinkman',
			width:80,
			halign:'center'
		},{
			title:'提供方电话',
			field:'venderTel',
			width:90,
			halign:'center'
		},{
			title:'提供方电子邮件',
			field:'venderEmail',
			width:90,
			halign:'center'
		},{
			title:'提供方传真',
			field:'venderFax',
			width:90,
			halign:'center'
		},{
			title:'操作',
			field:'operate',
			width:50,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == "0" ){
					return "";
				}else {
                    return "编辑";
				} 
			}
	    },{
	    	title:'操作时间',
	    	field:'operateTime',
	    	width:122,
	    	halign:'center',
	    	align:'left'
	    },{
	    	title:'操作原因',
	    	field:'operateRsn',
	    	width:100,
	    	halign:'center',
	    	align:'left'
	    }]],
		rowStyler:function(index,row){
			if(row.es == 1){
				return 'background-color:#98fb98;';
			}
		}
	});
}

//更新表格数据
function updateTestitemDatagrid(rowData){
//	var contractCodeRow = $('#contractCode').datagrid('getSelected');
	var contractCode ;
	if(rowData){
		contractCode = rowData.contractCode;
	}
	if(contractCode ){
		$('#testitemtable').datagrid({
			url:sybp()+'/contractAction_loadTestitemList.action?contractCode='
			+contractCode
		});
	}
}
//初始化委托项目数据表格
function initStudyitemDatagrid(){
	var tableHeight = (document.body.clientHeight - 105)/3;
	var tableWidth =  document.body.clientWidth -225;
	$('#studyitemtable').datagrid({
		singleSelect:true,
		height:tableHeight,
		width:tableWidth,
		columns :[[{
			title:'合同编号',
			field:'contractCode',
			width:80,
			halign:'center',
			align:'left'
		},{
			title:'供试品编号',
			field:'tiNo',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'项目编号',
			field:'studyNo',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'项目名称',
			field:'studyName',
			width:160,
			halign:'center',
			align:'left'
		},{
			title:'是否GLP',
			field:'glpFlag',
			width:48,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == 0 ){
					return "否";
				}else{
					return "GLP";
				}
			}
		},{
			title:'动物种类',
			field:'animalType',
			width:55,
			halign:'center',
			align:'left'
		},{
			title:'动物品系',
			field:'animalStrain',
			width:55,
			halign:'center',
			align:'left'
		},{
			title:'动物年龄',
			field:'animalAge',
			width:55,
			halign:'center',
			align:'left'
		},{
			title:'数量(♂)',
			field:'numMale',
			width:55,
			halign:'center',
			align:'left'
		},{
			title:'数量(♀)',
			field:'numFemale',
			width:55,
			halign:'center',
			align:'left'
	    },{
			field:'finishDateStr',
			title:'要求完成日期',
			width:80,
			halign:'center',
			align:'left'
	    },{
	    	title:'备注',
	    	field:'remark',
	    	width:79,
	    	halign:'center',
	    	align:'left'
		},{
			title:'操作',
			field:'operate',
			width:44,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == "0" ){
					return "";
				}else {
                    return "编辑";
				} 
			}
	    },{
	    	title:'操作时间',
	    	field:'operateTime',
	    	width:122,
	    	halign:'center',
	    	align:'left'
	    },{
	    	title:'操作原因',
	    	field:'operateRsn',
	    	width:100,
	    	halign:'center',
	    	align:'left'
	    }
		]]
	});
}

//更新表格数据
function updateStudyitemDatagrid(rowData){
//	var contractCodeRow = $('#contractCode').datagrid('getSelected');
	var contractCode ;
	if(rowData){
		contractCode = rowData.contractCode;
	}
	if(contractCode ){
		$('#studyitemtable').datagrid({
			url:sybp()+'/contractAction_loadStudyitemList.action?contractCode='
			+contractCode
		});
	}
}