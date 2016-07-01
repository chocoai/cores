function animalNumSave(){
//	var maleNum =Number( $("#maleNum").val());
//	var femaleNum = Number($("#femaleNum").val());
//	var minMaleNum =Number( $("#minMaleNum").val());
//	var minFemaleNum = Number($("#minFemaleNum").val());
//	var flag =true;
//	if(!fullNumCheck(maleNum)){
//		$("#span1").html("*");
//		flag =false;
//	}else if(minMaleNum>maleNum){
//		$("#span1").html("数量不足");
//		flag =false;
//	}
//	if(!fullNumCheck(femaleNum)){
//		$("#span2").html("*");
//		flag =false;
//	}else if(minFemaleNum>femaleNum){
//		$("#span2").html("数量不足");
////		document.getElementById("span2").innerHTML="数量不足";
//		flag =false;
//	}
//	if(flag){
//	}
	if($('#tblAnimalForm').form('validate')){
		var maleNum =Number( $("#maleNum").val());
		var femaleNum = Number($("#femaleNum").val());
		
		$.messager.confirm('动物数量',"动物数量，雄："+maleNum+" 雌："+femaleNum,function(r){
			if(r){
				document.forms[0].submit();
			}
		});
//		if(confirm("动物数量，雄："+maleNum+" 雌："+femaleNum)){
//			document.forms[0].submit();
//		}
	}
}

/**
 * 保存
 * @return
 */
function animalSave(size){
	var flag = true;
	var animalId;
	var nullTgt;
	for(var i=0; i<size; i++){
		if($("#weight"+i).val()==''){
			flag = false;
			animalId = $("#animalId"+i).val();
			nullTgt = "weight";
			break;
		}
		if($("#dissectBatch"+i).val()==0){
			flag = false;
			animalId = $("#animalId"+i).val();
			nullTgt = "dissectBatch";
			break;
		}
	}
	if(!flag){
		if(nullTgt == "weight"){
			$("#span1").html("动物编号为"+animalId+"的体重未输入！");
		}
		if(nullTgt == "dissectBatch"){
			$("#span1").html("动物编号为"+animalId+"的计划解剖次数未选择！");
		}
	}else {
		$("#span1").html("");
		if(confirm("您确定要保存？")){
			document.forms[0].submit();
		}
	}
}


/**
 * 提交
 */
function submit(size){
	var pass=true;
	var currentNum=$("#currentSerialNum").val();
	var minNum=$("#minAnimalId").val();
	if(!isSignlessInteger(currentNum)){
		$("#serialError").html("*");
		pass=false;
	}
	if(!isSignlessInteger(minNum)){
		$("#minError").html("*");
		pass=false;
	}
	var currentInt=Number($("#currentSerialNum").val());
	var minInt=Number($("#minAnimalId").val());
	var length=Number(size);
	if(!document.getElementById("single").checked){
		var maxNum=$("#maxAnimalId").val();
		if(!isSignlessInteger(maxNum)){
			$("#maxError").html("*");
			pass=false;
		}else{
			var maxInt=Number($("#maxAnimalId").val());
			if(maxInt<=minInt){
				$("#maxError").html("小的动物Id号应在前");
				pass=false;
			}else{
				if((length-currentInt)<(maxInt-minInt)){
					$("#maxError").html("请检查连续录入的数量");
					pass=false;
				}
			}
		}
		
	}
	if(pass){
	 document.forms[0].submit();
	}
}
/**Id号单个录入提交*/
function submitAddOneForm(){

	if($('#addOneForm').form('validate')){
		 $.ajax({
			 url:sybp()+'/tblAnimalAction_editOneAnimalIdSave.action',
			 type:'post',
			 data:$('#addOneForm').serialize(),
			 dataType:'json',
			 success:function(r){
			 	if(r && r.success){
//			 		$('#talAnimalTable').datagrid('load');
			 		$('#talAnimalTable').datagrid('updateRow',{
			 			index: r.animal.aniSerialNum-1,
			 			row: r.animal
			 		});
			 		$('#talAnimalTable').datagrid('selectRow',r.animal.aniSerialNum-1);
			 		$("#oneMinAnimalId").val('');
			 		$("#oneMinAnimalId").focus();
			 		$('#OneCurrentSerialNum').combobox('select',r.currentSerialNum);
			 		$('#currentSerialNum').combobox('select',r.currentSerialNum);
			 		$('#currentSerialNum2').combobox('select',r.currentSerialNum);
			 		
			 		
			 		
			 		parent.parent.showMessager(1,r.msg,true,5000);
			 	}else {
			 		parent.parent.showMessager(2,r.msg,true,5000);
			 		$('#oneMinAnimalId').focus();
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
/**Id号连续录入提交*/
function submitMuchOneForm(){
	if($('#addMuchForm').form('validate') ){
			$.ajax({
				url:sybp()+'/tblAnimalAction_editMuchAnimalIdSave.action',
				type:'post',
				data:$('#addMuchForm').serialize(),
				dataType:'json',
				success:function(r){
				if(r && r.success){
					for(var i = 0;i<r.animalMapList.length;i++){
						$('#talAnimalTable').datagrid('updateRow',{
				 			index: r.animalMapList[i].aniSerialNum-1,
				 			row: r.animalMapList[i]
				 		});
						$('#talAnimalTable').datagrid('selectRow',r.animalMapList[i].aniSerialNum-1);
					}
					
					$("#minAnimalId").numberbox('setValue','');
					//$("#maxAnimalId").numberbox('setValue','');
					
					$('#OneCurrentSerialNum').combobox('select',r.currentSerialNum);
			 		$('#currentSerialNum').combobox('select',r.currentSerialNum);
			 		$('#currentSerialNum2').combobox('select',r.currentSerialNum);
			 		
			 		parent.parent.showMessager(1,r.msg,true,5000);
				}else if(r) {
					parent.parent.showMessager(2,r.errorMsg,true,5000);
					
				}else{
					parent.parent.showMessager(3,'与服务器交互错误',true,5000);
				}
			}
			});
		
	}
}


/**
 * excel导入
 * @return
 */
function excelImport(){
	var reg=/([^\s]+(?=\.(xls|xlsx))\.\2)/gi;
	var fileName = $("#excelFile").val();
	if(!nullCheck(fileName)){
		$("#span2").html("请选择要导入的excel文件！");
	}else if(!reg.test(fileName)){
		$("#span2").html("请导入以xls或者xlsx为后缀的文件！");
	}else {
		$("#span2").html("");
		if(confirm("您确定要导入？")){
			document.forms[1].submit();
		}
	}
}


/**
 * 跳到标记动物死亡
 */
function onAnimalDieButton(){
	window.location.href= sybp()+"/tblAnimalAction_animalDie.action?studyNoPara="+$('#studyNoPara').val();
}
/**
 * 死亡登记
 */
function onAnimalRegistraDeath(){
	var Automatic = $('#Automatic').val();
	if(Automatic == "Automatic"){
		$.messager.alert('提示','动物信息已自动更新!');
	}else{
	 editanimalsDie();
	 $('#animalDieDialog').dialog('open');
	 $('#animalDieDialog2').dialog('open');
	 }
}

function editanimalsDie(){
	$('#editanimalsDie').datagrid({
		url : sybp()+"/tblAnimalAction_NodieloadList.action?studyNoPara="+$('#studyNoPara').val(),
		title:'',
		height: 230,
		width:384,
		iconCls:'',//'icon-save',
		pageSize:100,
		pageList:[50,100],
		//fit:true,
		fitColumns:true,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		fit:false,
		striped:true,
		fitColumns:false,
		pagination:false,
		columns :[[{
			field:'ck',
			checkbox:true
		},{
			title :'',
			field :'id',
			//checkbox:true
			hidden:true
		},{
			title:'动物ID',
			field:'animalId',
			width:60,
			editor:{
				type:'validatebox',
				options:{
					required:true
				}
			},
			//formartter 一定要返回个字符串
			formatter : function(value,rowData,rowIndex){
				return '<span title="'+value+'">'+value+'</span>';
			}
		},{
			title:'动物编号',
			field:'animalCode',
			width:60,
			editor:{
				type:'validatebox',
				options:{
					required:true
				}
			},
			//formartter 一定要返回个字符串
			formatter : function(value,rowData,rowIndex){
				return '<span title="'+value+'">'+value+'</span>';
			}
		},{
			title:'性别',
			field:'gender',
			width:50,
			formatter :function(value,rowData,rowIndex){
				if(value == 1){
					return '♂';
				}else{
					return '♀';
				}
			}
		},{
			title:'计划解剖次数',
			field:'dissectBatch',
			width:80,
			formatter:function(value,rowData,rowIndex){
				if(value==0){
					return '';
				}else{
					return value;
				}
			}
		},{
			title:'计划解剖时间',
			field:'showdissectBatch',
			width:80
		}]],
	
	});
}

//把勾选的动物放入session中跳到死亡原因的页面
function ToCausesofDeath(){
	
	var OneCauseofdeathSerialNum =$('#OneCauseofdeathSerialNum').combobox('getValue');
	var deadDate =$('#deadDate').datebox('getValue');
	if(OneCauseofdeathSerialNum== ""){
		$.messager.alert('提示','请填写动物死亡原因');
	}else if(deadDate==""){
		$.messager.alert('提示','请填写动物死亡日期');
	}else{
		$.ajax({
			url:sybp()+'/tblAnimalAction_onUncheckofDeath.action',
			type:'post',
			cache:false,
			dataType:'json',
			success:function(r){
			var rows =$('#editanimalsDie').datagrid('getChecked');
			if(rows.length <1){
				$.messager.alert('提示','请选择死亡动物');
			}else{
				var radio ='?';
				for(var i=0;i<rows.length;i++){ 
					radio = radio +'radio2 ='+$('#editanimalsDie').datagrid('getChecked')[i].id+'&'
				 }
				radio = radio +'radio2= '
				$.ajax({
					url:sybp()+'/tblAnimalAction_ToCausesofDeath.action'+radio,
					type:'post',
					cache:false,
				dataType:'json',
				success:function(r){
					//显示已标记动物死亡信息
					 $('#totalofanimal').val(r.index);
					 $('#causeofdeath').val($('#OneCauseofdeathSerialNum').combobox('getValue'));
					 $('#timeofdeath').val($('#deadDate').datebox('getValue'));
					document.getElementById("determofdeath").innerHTML=
						"<iframe src='${pageContext.request.contextPath}/tblAnimalAction_determofdeath.action' frameborder='0' style='border:0;width:100%;height:100%;'></iframe>";
					$('#determofdeathDialog').dialog('open');
					$('#determofdeathDialog2').dialog('open');
				    
					//$.messager.confirm('确认对话框', '确认动物死亡信息录入完毕!', function(r){
					//	if (r){
					//		qm_showQianmingDialog('eventAfteranimalDeath');
					//	}
						
					//});
					
				}
				});
				
			}
			 }
		});

		
	}
	
}

function Determofdeath(){
		qm_showQianmingDialog('eventAfteranimalDeath');
}

//动物死亡签字esTYpe12
function eventAfteranimalDeath(){
	$.ajax({
		url:sybp()+"/tblAnimalAction_AnimalDeathSign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=12",
		type:'post',
		data:{
		AnimaldeadReason:$('#OneCauseofdeathSerialNum').combobox('getValue'),
		AnimaldeadDate:$('#deadDate').datebox('getValue'),
		type:$('#Selectionsbox').combobox('getValue'),
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.parent.showMessager(1,'签字成功',true,5000);
				window.location.href = "${pageContext.request.contextPath}/tblAnimalAction_animalList.action?studyNoPara="+$('#studyNoPara').val();
			}
		}
	});
	
	
}



