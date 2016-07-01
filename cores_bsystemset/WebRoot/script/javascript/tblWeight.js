/**体重单个录入提交*/
function submitAddOneWegihtForm(){
	if($('#addOneForm').form('validate')){
		 $.ajax({
			 url:sybp()+'/tblWeightIndAction_editOneAnimalIdSave.action',
			 type:'post',
			 data:$('#addOneForm').serialize(),
			 dataType:'json',
			 success:function(r){
			 	if(r && r.success){
//			 		$('#talAnimalTable').datagrid('load');
			 		$('#talAnimalTable').datagrid('updateRow',{
			 			index: r.index,
			 			row: r.animal
			 		});
				 	$('#talAnimalTable').datagrid('selectRow',r.index1);
				
			 		//$("#oneWeightInd").numberbox('setValue',$('#talAnimalTable').datagrid('getRows')[r.index1].weight);
			 		$('#oneWeightInd').val($('#talAnimalTable').datagrid('getRows')[r.index1].weight);
			 		$('#oneWeightInd').focus();
			 		$('#OneCurrentSerialNum').combobox('select',$('#talAnimalTable').datagrid('getRows')[r.index1].aniCode);
	     	 		$('#currentSerialNum').combobox('select',$('#talAnimalTable').datagrid('getRows')[r.index1].aniCode);
			 	}else {
			 		parent.parent.showMessager(2,r.msg,true,5000);
			 		$('#oneWeightInd').focus();
			 		//以下五行把焦点定位到  输入框  输入值后面
			 		var obj = event.srcElement;
			 		var txt =obj.createTextRange();
			 		txt.moveStart('character',obj.value.length);
			 		txt.collapse(true);
			 		txt.select();
			 	}
		 	}
		 });
	}
}

//动物体重
function addNewWeighIndTab(){
	var isAnimalIdES=$('#isAnimalIdES').val();
	if(isAnimalIdES ==1){
		if (studyPlanTabs.tabs('exists', '动物体重')) {
			studyPlanTabs.tabs('select', '动物体重');
		}else{
			var length = studyPlanTabs.tabs('tabs').length;
			for(var i=0;i<length;i++){
				studyPlanTabs.tabs('close',0);
			}
			studyPlanTabs.tabs('add', {
				title : '动物体重',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/tblWeightIndAction_list.action?studyNoPara='+studyNoPara + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	}else{
		parent.showMessager(2,'请先录入动物列表',true,5000);
	}
}


/**新建一个 胶囊配置申请(清除其他标签)*/addNewlTiprpAppInd
function addNewlTiprpAppInd(){
	var isAnimalIdES=$('#isAnimalIdES').val();
	if(isAnimalIdES ==1){
		if (studyPlanTabs.tabs('exists', '胶囊配制申请')) {
			studyPlanTabs.tabs('select', '胶囊配制申请');
		}else{
			var length = studyPlanTabs.tabs('tabs').length;
			for(var i=0;i<length;i++){
				studyPlanTabs.tabs('close',0);
			}
			studyPlanTabs.tabs('add', {
				title : '胶囊配制申请',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/tblTiprpAppIndAction_list.action?studyNoPara='+studyNoPara + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	}else{
		parent.showMessager(2,'请先录入动物列表',true,5000);
	}
}

function addoneNewWeighIndTab(studyNoPara){

	var length = studyPlanTabs.tabs('tabs').length;
	for(var i=0;i<length;i++){
		studyPlanTabs.tabs('close',0);
	}
	studyPlanTabs.tabs('add', {
		title : '动物体重',
		closable : false,
		content : '<iframe src="' + '${pageContext.request.contextPath}/tblWeightIndAction_list.action?studyNoPara='+studyNoPara +'&sign=1'+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
	});

}

