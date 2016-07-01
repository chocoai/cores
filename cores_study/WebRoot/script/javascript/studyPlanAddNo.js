/**显示Dialog*/
function showStudyPlanAddNoDialog(){
	
	document.getElementById("studyPlanAddNoDialog2").style.display="block";
	$('#newStudyNo').bind('keyup',function(e){
		if(e.keyCode == '13'){
			studyNoCheck();
		}
	});
	$('#studyNoTable').datagrid({
		onClickRow:function(rowIndex, rowData){
    	//设置课题编号
    	$('#newStudyNo').val(rowData.studyCode);
    	$('#sponsorName').html(rowData.sponsorName);
    	$('#tiName').html(rowData.tiName);
    	$('#studyName').html(rowData.studyName);
    	//document.all.newStudyNo.focus();
    	//$('#newStudyNo').focus();
    	if(rowData.isConfirm){
    		$('#warnMsg').css('display','none');
    	}else{
    		$('#warnMsg').css('display','');
    	}
    }
	});
	$('#studyPlanAddNoDialog').dialog('open'); 
	
}

//检查课题编号的唯一性并提交
function studyNoCheck(){
	var newStudyNo = $("#newStudyNo").val();
	
	if($("#studyPlanAddForm").form('validate')){
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblStudyPlanAction_uniqueCheck.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"html",
	        data:"studyNoPara="+newStudyNo,
	        beforeSend:function(XMLHttpRequest)
	        {
	        },
	        success:function(msg)
	        {
	        	if(msg=="is")
	        	{
	        		parent.showMessager(2,'课题编号已存在!',true,5000);
	        		//$.messager.alert('提示','课题编号已存在!');
	        	}
	        	if(msg=="no")
	        	{
	        		//检查无误，提交表单
	        		//document.forms[0].submit();
	        		beforeOpenStudyPlanAddEditDialog(newStudyNo);
	        		
	        	}
	        },
	        complete:function(XMLHttpRequest,textStatus)
	        {
	        	//parent.document.getElementsByName("left")[0].reload();
	        },
	        error:function()
	        {
	        	$.messager.alert('提示','与服务器交互错误!');
	        }
	        });
	}
}
/**打开对话框之前的准备工作*/
function beforeOpenStudyPlanAddEditDialog(newStudyNo){
	//关闭当前dialog
	$('#studyPlanAddNoDialog').dialog('close'); 
	
	//准备数据
	$.ajax({
		url:sybp()+'/tblStudyPlanAction_studyPlanAdd.action',
		type:'post',
		data:{
			newStudyNo:newStudyNo,
		},
		dataType:'json',
		success:function(r){
			if(r){
				//qa加载数据
				$('#qa').combobox('loadData',r.qaMapList);

				//病理加载数据
				$('#pathDirector').combobox('loadData',r.pathMapList);
				
				var studyPlanMap = r.studyPlanMap;
				$('#studyNo').val(studyPlanMap.studyNo);
				$('#studydirector').val(studyPlanMap.sd);
				
				//课题类别
				$('#studyType').combobox('loadData',r.studyTypeMapList);
				if(r.studyTypeMapList.length == 1){
					$('#studyType').combobox('select',r.studyTypeMapList[0].id);
				}
				//$('#isGLP').combobox('disable');
				$('#isGLP').combobox('select',studyPlanMap.isGLP);
				
				$('#isValidation').combobox('select','0');
				
				//日期选择器只读
				$('#studyStartDate').datebox('setValue', '');
				$('#animalImportDate').datebox('setValue', '');
				$('#preStudyDate').datebox('setValue', '');
				$('#studyBeginDate').datebox('setValue', '');
				
				//动物类别
				//$('#animalType').val(studyPlanMap.animalType);
				//$('#animalStrain').val(studyPlanMap.animalStrain);
				$('#animalType').combobox('loadData',r.animalTypeMapList);
				$('#animalType').combobox('select',studyPlanMap.animalType);
				$('#animalStrain').combobox('loadData',r.studyStrainMapList);
				$('#animalStrain').combobox('select',studyPlanMap.animalStrain);
				
				//剂量单位
				$('#dosageUnit').combobox('loadData',r.doseUnitMapList);
				
				//临检
				$('#clinicalTestDirector').combobox('loadData',r.clinicalTestMapList);
				$('#clinicalTestDirector').combobox('setValue','');
				//供试品类型
				$('#studyTiCode').val(studyPlanMap.studyTiCode);
				//课题名称,课题类型
				$('#addEditStudyName').val(studyPlanMap.studyName);
				$('#studyTypeCode').val(studyPlanMap.studyTypeCode);
				
				//qa
				$('#qa').combobox('setValue',studyPlanMap.qa);

				//病理
				$('#pathDirector').combobox('setValue',studyPlanMap.pathSD);
				
				
				//显示 studyPlanAddEditDialog
				showStudyPlanAddEditDialog('afterCloseStudyPlanAddEditDialog','add','新增 试验计划');
			}
		}
	});
}
/**关闭对话框之后的事件*/
function afterCloseStudyPlanAddEditDialog(){
//	var myDate = new Date();
//	var year=myDate.getFullYear(); 
	var studyStartDate = $('#studyStartDate').datebox('getValue');
	var year = studyStartDate.substr(0,4);
	var newStudyNo = $('#newStudyNo').val();
	var newsd = $('#newsd').val();
	parent.setTopStudyNoSD(newStudyNo,newsd);
	//是否是验证试验
	var  isValidation = $('#isValidation').combobox('getValue');
	parent.left.window.location.href="${pageContext.request.contextPath}/homeAction_leftStudyPlan.action?year="+year+"&studyNoPara="+newStudyNo+"&isValidationPara="+isValidation;
	window.location.href = "${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara="+newStudyNo;                  
}
