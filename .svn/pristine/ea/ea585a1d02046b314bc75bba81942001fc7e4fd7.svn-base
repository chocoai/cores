/**显示Dialog*/
function showReqDialog(studyNo,reqNo,change){
	/*签名Dialog*/
	document.getElementById("reqViewDialog2").style.display="block";
	initAnatomyReqAnimalTable();
	initAnatomyReqPathPlanCheckTable();
	initAnatomyReqVisceraWeighTable();
	
	loadAnatomyReqViewData(studyNo,reqNo,change);
	
	//打开对话框
	$('#reqViewDialog').dialog('open'); 
}
//加载页面数据
function loadAnatomyReqViewData(studyNo,reqNo,change){
	$.ajax({
		type:"post",
		url:sybp()+'/pathAction_loadAnatomyReqViewData.action',
		data:{
			studyNo:studyNo,
			reqNo:reqNo,
			change:change
		},
		dataType:'json',
		success:function(r){
			if(r){
				// animalType，anatomyRsn，testPhase，anatomyDate
				$('#animalType').text(r.animalType);
				$('#anatomyRsn').text(r.anatomyRsn);
				$('#testPhase').text(r.testPhase);
				$('#anatomyDate').text(r.anatomyDate);
				
				$('#anatomyReqAnimalList').datagrid('loadData',r.animalList);
				$('#anatomyReqPathPlanCheckTable').datagrid('loadData',r.anatomyVisceraList);
				$('#anatomyReqVisceraWeighTable').datagrid('loadData',r.visceraWeighList);
			}
		}
	});
}


//初始化动物列表
function initAnatomyReqAnimalTable(){
	$('#anatomyReqAnimalList').datagrid({    
//		url: sybp()+'/',
		title:'',
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		singleSelect:true,
		height:380,
		width:130,
		showHeader:true,
		columns :[[{
			title:'动物编号',
			field:'animalCode',
			width:69,
			halign:'center',
			align:'left'
		},{
			title:'性别',
			field:'gender',
			width:40,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == 1 ){
					return "♂";
				}else if( value == 2 ){
					return "♀";
				}else{
					return "";
				}
			} 
		}]]
	});
}

//初始化病理计划-脏器/组织学检查表
function initAnatomyReqPathPlanCheckTable(){
	$('#anatomyReqPathPlanCheckTable').datagrid({
//    	url:url,
    	title:'',
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		singleSelect:true,
		height:380,
		width:306,
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
			width:110,
			halign:'center',
			align:'left'
		},{
			title:'需剖检',
			field:'atanomyCheckFlag',
			width:58,
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
			title:'需固定',
			field:'visceraFixedFlag',
			width:58,
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
			title:'需镜检',
			field:'histopathCheckFlag',
			width:57,
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
		}]]
     });
}
//初始化病理计划-脏器称重表
function initAnatomyReqVisceraWeighTable(){             
	$('#anatomyReqVisceraWeighTable').datagrid({
//    	url:url,
    	title:'',
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		singleSelect:true,
		height:380,
		width:400,
		columns :[[{
			title:'id',
			field:'id',
			width:110,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'脏器名称',
			field:'visceraName',
			width:110,
			halign:'center',
			align:'left'
		},{
			title:'成对脏器分开称重',
			field:'partVisceraSeparateWeigh',
			width:100,
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
			width:62,
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
			width:105,
			halign:'center',
			align:'left'
		}]]
     });
}

