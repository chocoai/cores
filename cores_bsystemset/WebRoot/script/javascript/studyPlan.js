/**打开对话框之前的准备工作*/
function beforeOpenStudyPlanAddEditDialog_edit(newStudyNo){
	//准备数据
	$.ajax({
		url:sybp()+'/tblStudyPlanAction_studyPlanEdit.action',
		type:'post',
		data:{
			newStudyNo:newStudyNo,
		},
		dataType:'json',
		success:function(r){
			if(r){
				var studyPlanMap = r.studyPlanMap;
				$('#studyNo').val(studyPlanMap.studyNo);
				$('#studydirector').val(studyPlanMap.sd);
				//$('#animalType').val(studyPlanMap.animalType);
				//$('#animalStrain').val(studyPlanMap.animalStrain);
				//动物类别
				$('#animalType').combobox('loadData',r.animalTypeMapList);
				$('#animalType').combobox('select',studyPlanMap.animalType);
				$('#animalStrain').combobox('select',studyPlanMap.animalStrain);
				$('#animalStrain').combobox('loadData',r.studyStrainMapList);
				//课题类别
//				$('#studyType').combobox('loadData',r.studyTypeMapList);
//				$('#studyType').combobox('select',studyPlanMap.studyTypeSel);
				
				$('#addEditStudyName').val(studyPlanMap.studyName);
				$('#studyTypeCode').val(studyPlanMap.studyTypeCode);
				
				$('#isGLP').combobox('select',studyPlanMap.isGLP);
				$('#isValidation').combobox('select',studyPlanMap.isValidation);
//				$('#isGLP').combobox('disable');
				//日期选择器只读
				$(".datebox :text").attr("readonly","readonly");
				
				$('#studyStartDate').datebox('setValue', studyPlanMap.studyStartDate);
				$('#animalImportDate').datebox('setValue', studyPlanMap.animalImportDate);
				$('#preStudyDate').datebox('setValue', studyPlanMap.preStudyDate);
				$('#studyBeginDate').datebox('setValue', studyPlanMap.studyBeginDate);
				
				
				//剂量单位
				$('#dosageUnit').combobox('loadData',r.doseUnitMapList);
				$('#dosageUnit').combobox('select',studyPlanMap.dosageUnit);
				//qa
				$('#qa').combobox('loadData',r.qaMapList);
				$('#qa').combobox('select',studyPlanMap.qa);
				//病理
				$('#pathDirector').combobox('loadData',r.pathMapList);
				$('#pathDirector').combobox('select',studyPlanMap.pathDirector);
				//临检
				$('#clinicalTestDirector').combobox('loadData',r.clinicalTestMapList);
				$('#clinicalTestDirector').combobox('select',studyPlanMap.clinicalTestDir);
				//显示 studyPlanAddEditDialog
				showStudyPlanAddEditDialog('afterCloseStudyPlanAddEditDialog_edit','edit','编辑 试验计划');
			}
		}
	});
}
/**关闭对话框之后的事件(编辑)*/
function afterCloseStudyPlanAddEditDialog_edit(){
	var newStudyNo = $('#newStudyNo').val();
	window.location.href = "${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara="+newStudyNo;
}

///**
// * 提交表单前的验证-新增
// * @return
// */
//function addSaveCheck(obj){
//	var pass =true;
//	var studyNo = $("#studyNo").val();
//	var studyType = $("#studyType").val();
//	var studydirector = $("#studydirector").val();
//	var isGLP = $("#isGLP").val();
//	var animalType = $("#animalType").val();
//	var animalStrain = $("#animalStrain").val();
//	var studyStartDate =$("#studyStartDate").datebox("getValue");
//	
//	var animalImportDate =$("#animalImportDate").datebox("getValue");
//	var preStudyDate = $("#preStudyDate").datebox("getValue");
//	var studyBeginDate = $("#studyBeginDate").datebox("getValue");
//	var dosageUnit = $("#dosageUnit").val();
//	var qa = $("#qa").val();
//	var pathDirector = $("#pathDirector").val();
//	var clinicalTestDirector = $("#clinicalTestDirector").val();
//	if(!nullCheck(studyNo)){
//		$("#span1").html("*"); pass=false;
//	}
//    if(!nullCheck(studyType)){
//		$("#span2").html("*");pass=false;
//	}
//    if(!nullCheck(studydirector)){
//		$("#span3").html("*");pass=false;
//	}
//    if(!nullCheck(isGLP)){
//		$("#span4").html("*");pass=false;
//	}
//     if(!nullCheck(animalType)){
//		$("#span5").html("*");pass=false;
//	}
//     if(!nullCheck(animalStrain)){
//		$("#span6").html("*");pass=false;
//	}
//    if(!nullCheck(studyStartDate)){
//		$("#span7").html("*");pass=false;
//	}
//    if(!nullCheck(animalImportDate)){
//		$("#span8").html("*");pass=false;
//	}
//    if(!nullCheck(preStudyDate)){
//		$("#span9").html("*");pass=false;
//	}
//    if(!nullCheck(studyBeginDate)){
//		$("#span10").html("*");pass=false;
//	}
//    
//    if(!nullCheck(dosageUnit)){
//		$("#span11").html("*");pass=false;
//	}
//    if(!nullCheck(qa)){
//		$("#span12").html("*");pass=false;
//	}
//    if(!nullCheck(pathDirector)){
//		$("#span13").html("*");pass=false;
//	}
//    if(!nullCheck(clinicalTestDirector)){
//		$("#span14").html("*");pass=false;
//	}
//    	
//    if( $('#addStudyPlanForm').form('validate')&& pass ){
////    	obj.href="${pageContext.request.contextPath}/homeAction_left.action" ;
////	    document.forms[0].submit();
//	    $('#addStudyPlanForm').form('submit',{
//	    	onSubmit:function(){
//	    		$('#addStudyPlanButton').linkbutton('disable');
//	    		return true;
//	    	},
//	    	success:function(data){
//	    		if(data){
//	    			var gotoLeftStudyPlanA= document.getElementById("gotoLeftStudyPlanA") ;
//	    			var myDate = new Date();
//	        		var year=myDate.getFullYear(); 
//	    			gotoLeftStudyPlanA.href="${pageContext.request.contextPath}/homeAction_leftStudyPlan.action?year="+year+"&studyNoPara="+data;
//	    			gotoLeftStudyPlanA.click();
//	    			var gotoStudyPlanMainA= document.getElementById("gotoStudyPlanMainA") ;
//	    			gotoStudyPlanMainA.href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara="+data;
//	    			gotoStudyPlanMainA.click();
////	    			parent.document.getElementsByName("main")[0].src="tblStudyPlanAction_studyPlanMain.action?studyNoPara="+data;
//	    		}
//	    	}
//	    });
//	}
//	
//}

///**
// * 编辑
// * @param studyNoPara
// * @return
// */
//function studyPlanEdit(){
//	var studyNoPara = document.getElementById("editPara").value;
//	parent.document.getElementsByName("main")[0].src="tblStudyPlanAction_studyPlanEdit.action?studyNoPara="+studyNoPara;
//}

///**
// * 提交表单前的验证-编辑
// * @return
// */
//function editSaveCheck(){
//	var studyType = $("#studyType").val();
//	var studydirector = $("#studydirector").val();
//	var isGLP = $("#isGLP").val();
//	var animalType = $("#animalType").val();
//	var animalStrain = $("#animalStrain").val();
//	var studyStartDate = $("#studyStartDate").val();
//	var animalImportDate = $("#animalImportDate").val();
//	var preStudyDate = $("#preStudyDate").val();
//	var studyBeginDate = $("#studyBeginDate").val();
//	var dosageUnit = $("#dosageUnit").val();
//	var qa = $("#qa").val();
//	var pathDirector = $("#pathDirector").val();
//	var clinicalTestDirector = $("#clinicalTestDirector").val();
//	if(!nullCheck(studyType)){
//		$("#span1").html("课题类别不能为空！");
//	}else if(!nullCheck(studydirector)){
//		$("#span1").html("课题负责人不能为空！");
//	}else if(!nullCheck(isGLP)){
//		$("#span1").html("是否GLP不能为空！");
//	}else if(!nullCheck(animalType)){
//		$("#span1").html("动物种类不能为空！");
//	}else if(!nullCheck(animalStrain)){
//		$("#span1").html("动物品系不能为空！");
//	}else if(!nullCheck(studyStartDate)){
//		$("#span1").html("试验启动日期不能为空！");
//	}else if(!nullCheck(animalImportDate)){
//		$("#span1").html("动物引入日期不能为空！");
//	}else if(!nullCheck(preStudyDate)){
//		$("#span1").html("预试验日期不能为空！");
//	}else if(!nullCheck(studyBeginDate)){
//		$("#span1").html("正式试验时期不能为空！");
//	}else if(!nullCheck(dosageUnit)){
//		$("#span1").html("剂量单位不能为空！");
//	}else if(!nullCheck(qa)){
//		$("#span1").html("QA负责人不能为空！");
//	}else if(!nullCheck(pathDirector)){
//		$("#span1").html("病理负责人不能为空！");
//	}else if(!nullCheck(clinicalTestDirector)){
//		$("#span1").html("临检负责人不能为空！");
//	}else {
//		//验证无误，提交表单
//		if(confirm("您确定要保存？")){
//			document.forms[0].submit();
//		}
//	}
//}

/**
 * 标签页切换
 * @param ProTag
 * @param ProBox
 * @return
 */
function $get(id){
    return document.getElementById(id);
}

function changeTab(currentPageIndex,pageCounts){
    for(var i=1;i<=pageCounts;i++){
		if(i==currentPageIndex){
			document.getElementById("li"+i).className="active";
		}else{
			document.getElementById("li"+i).className="";
		}
    }
}

/**
 * 临检申请
 * @param studyNoPara
 * @return
 */
function clinicalTestApply(studyNoPara){
	//ajax验证是否已经签字
	if(studyNoPara == '' || studyNoPara == null){
		//alert("数据异常！");
		parent.showTopMessager('数据异常!',true,5000);
		return;
	}else {
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblStudyPlanAction_testApplyCheck.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"html",
	        data:"studyNoPara="+studyNoPara,
	        async:false,
	        success:function(msg){
				if (msg == "unPass") {
					//alert("该试验计划还未签字，不能临检申请！");
					parent.showTopMessager('该试验计划还未签字，不能临检申请!',true,5000);
				}else if ("pass") {
					window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara="+studyNoPara;
				}
	        }
	        });
	}
	
}

//function signCheck(studyNoPara){
//	//ajax验证是否所有设置均已完成
//	if(studyNoPara == '' || studyNoPara == null){
//		return;
//	}else {
//		$.ajax({
//	        type:"POST",
//	        url:"${pageContext.request.contextPath}/tblStudyPlanAction_signCheck.action",
//	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
//	        dataType:"html",
//	        data:"studyNoPara="+studyNoPara,
//	        async:false,
//	        success:function(msg)
//	        {
//				if(msg=="doseSetting") {
//		  	    	 $("#span1").html("请先设置剂量设置！");
//				}else if (msg=="testIndexPlan") {
//					$("#span1").html("请先设置课题计划检验指标！");
//				}else {
//					$("#span1").text("");
//					if (msg=="dissectPlan") {
//			       		if(confirm("解剖计划没有设置，确定放弃设置吗？")){
//			       			showDiv("signDiv");
//			       		}
//					}else{
//						showDiv("signDiv");
//					}
//					
//					
//	        	}
//	        },
//	        error:function()
//	        {
//	             $("#span1").html("与服务器交互错误!");
//	        }
//	   });
//	}
//}
//
///**
// * 签名
// * @param studyNoPara
// * @return
// */
//function sign(studyNoPara){
//	var password=$("#password").val();
//	if(password == ''){
//		$("#span2").html("请输入密码！");
//	}else{
//		$.ajax({
//	        type:"POST",
//	        url:"${pageContext.request.contextPath}/tblStudyPlanAction_passwordCheck.action",
//	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
//	        dataType:"html",
//	        data:"password="+password,
//	        async:false,
//	        success:function(msg)
//	        {
//				if(msg=="no") {
//					$("#span2").html("密码不正确！");
//				}else{
//					$("#span2").html("");
////					window.location.href = "${pageContext.request.contextPath}/tblStudyPlanAction_sign.action?studyNoPara="+studyNoPara;
//					var aclick =document.getElementById("aclick");
//					aclick.click();
//				}
//	        },
//	        error:function()
//	        {
//	             $("#span2").html("与服务器交互错误!");
//	        }
//	   });
//	}
//	
//}
/**
 * 动物种类、品系联动
 * @param typeName
 * @return
 */
function showStrain(typeName){
//	$.post("${pageContext.request.contextPath}/tblStudyPlanAction_animalStrain.action",{typeName:typeName} ,function(data,textStatuts){
//        var dataObj =eval("("+data+")");	    	
//        //删除城市列表
////        $("select[id='animalStrain'] option[value!='']").remove();
//        $("select[id='animalStrain'] option").remove();
//        var $option=$("<option></option>");
//            $option.attr("value","");
//            $option.text("----");
//        $("select[id='animalStrain']").append($option);
//        for(var i=0;i<dataObj.length;i++){
//             var $option=$("<option></option>");
//             $option.attr("value",dataObj[i].strainName);
//             $option.text(dataObj[i].strainName);
//             $("select[id='animalStrain']").append($option); 
//            }
//    });
	$.ajax({
		url:'${pageContext.request.contextPath}/tblStudyPlanAction_animalStrain.action',
		type:'post',
		data:{typeName:typeName},
		dataType:'json',
		success:function(data){
//			 var dataObj =eval("("+data+")");	
			var dataObj=data;
	        //删除城市列表
//	        $("select[id='animalStrain'] option[value!='']").remove();
	        $("select[id='animalStrain'] option").remove();
	        var $option=$("<option></option>");
	            $option.attr("value","");
	            $option.text("----");
	        $("select[id='animalStrain']").append($option);
	        for(var i=0;i<dataObj.length;i++){
	             var $option=$("<option></option>");
	             $option.attr("value",dataObj[i].strainName);
	             $option.text(dataObj[i].strainName);
	             $("select[id='animalStrain']").append($option); 
	            }
		}
	});
}

//菜单00点击事件
/** 新建一个 增加试验计划tab(清除其他标签)*/
function addNewStudyPlanTab(){
	if (studyPlanTabs.tabs('exists', '新建试验计划')) {
		studyPlanTabs.tabs('select', '新建试验计划');
	}else{
		var length = studyPlanTabs.tabs('tabs').length;
		for(var i=0;i<length;i++){
			studyPlanTabs.tabs('close',0);
		}
		studyPlanTabs.tabs('add', {
			title : '新建试验计划',
			closable : false,
			content : '<iframe src="' + '${pageContext.request.contextPath}/tblStudyPlanAction_studyNo.action' + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
		});
		//临床检验管理
		var itemEI01=$('#clinicalTestMenuItem')[0];
		//动物管理
		var itemEI10=$('#animalMenuItem')[0];
		$('#menu0').menu('disableItem',itemEI01);
		$('#menu1').menu('disableItem',itemEI10);
	}
}
//菜单10点击事件
/** 新建一个 动物信息tab(清除其他标签)*/
function addNewAnimalTab(){
	var studyState=$('#studyState').val();
	if(studyState >0){
		if (studyPlanTabs.tabs('exists', '动物信息')) {
			studyPlanTabs.tabs('select', '动物信息');
		}else{
			var length = studyPlanTabs.tabs('tabs').length;
			for(var i=0;i<length;i++){
				studyPlanTabs.tabs('close',0);
			}
			studyPlanTabs.tabs('add', {
				title : '动物信息',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/tblAnimalAction_animalList.action?studyNoPara='+studyNoPara +'&Automatic='+$('#Automatic').val()+' " frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	}else{
		parent.showMessager(2,'实验计划还未签字!',true,5000);
	}
}
//菜单01点击事件
/** 新建一个 临检申请tab(清除其他标签)*/
function addNewClinicalTestReqTab(){
	var isAnimalIdES=$('#isAnimalIdES').val();
	if(isAnimalIdES ==1){
		if (studyPlanTabs.tabs('exists', '临检申请')) {
			studyPlanTabs.tabs('select', '临检申请');
		}else{
			var length = studyPlanTabs.tabs('tabs').length;
			for(var i=0;i<length;i++){
				studyPlanTabs.tabs('close',0);
			}
			studyPlanTabs.tabs('add', {
				title : '临检申请',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara='+studyNoPara + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	}else{
		parent.showMessager(2,'请先录入动物列表',true,5000);
	}
}
//菜单01点击事件
/** 新建一个 临检申请tab(清除其他标签)*/
function addNewLogTab(){
		if (studyPlanTabs.tabs('exists', '系统日志')) {
			studyPlanTabs.tabs('select', '系统日志');
		}else{
			var length = studyPlanTabs.tabs('tabs').length;
			for(var i=0;i<length;i++){
				studyPlanTabs.tabs('close',0);
			}
			studyPlanTabs.tabs('add', {
				title : '系统日志',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/tblLogAction_list.action?" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
}
/**课题成员管理*/
function StudyMemberTab(){
	if (studyPlanTabs.tabs('exists', '专题成员')) {
		studyPlanTabs.tabs('select', '专题成员');
	}else{
		var length = studyPlanTabs.tabs('tabs').length;
		for(var i=0;i<length;i++){
			studyPlanTabs.tabs('close',0);
		}
		studyPlanTabs.tabs('add', {
			title : '专题成员',
			closable : false,
			content : '<iframe src=${pageContext.request.contextPath}/tblStudyMemberAction_list.action?studyNoPara='+studyNoPara+' frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
		});
	}
}
/**试验计划签字之前的检查*/
function checkBeforeStudyPlanSign(){
	//ajax验证是否所有设置均已完成
	if(studyNoPara == '' || studyNoPara == null){
		return;
	}else {
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblStudyPlanAction_signCheck.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"html",
	        data:"studyNoPara="+studyNoPara,
	        async:false,
	        success:function(msg)
	        {
				if(msg=="doseSetting") {
		  	    	 //$("#span1").html("请先设置剂量设置！");
					//$.messager.alert('提示','请先设置剂量设置！');
					parent.showMessager(2,'请先设置剂量设置!',true,5000);
				}else if (msg=="testIndexPlan") {
					//$("#span1").html("请先设置课题计划检验指标！");
					//$.messager.alert('提示','请先设置课题计划检验指标！');
					parent.showMessager(2,'请先设置课题计划检验指标!',true,5000);
				}else {
					if (msg=="dissectPlan") {
						$.messager.confirm('确认对话框', '解剖计划没有设置，确定放弃设置吗？', function(r){
							if (r){
								$.messager.confirm('确认对话框', '确认课题信息录入完毕过后，课题信息将不可以再编辑，确定继续吗？', function(r){
									if (r){
										qm_showQianmingDialog('eventAfterStudyPlanSign');
									}
								});
							}
						});
					}else{
						$.messager.confirm('确认对话框', '确认课题信息录入完毕过后，课题信息将不可以再编辑，确定继续吗？', function(r){
							if (r){
								qm_showQianmingDialog('eventAfterStudyPlanSign');
							}
						});
					}
	        	}
	        },
	        error:function()
	        {
	        	parent.showMessager(2,'与服务器交互错误!',true,5000);
//	             $.messager.show({
//	            	 title:'提示',
//	            	 msg:'与服务器交互错误!',
//	            	 timeout:5000
//	             });
	        }
	   });
	}
				
}

/**试验计划签字之后的事件*/
function eventAfterStudyPlanSign(){
	window.location.href = "${pageContext.request.contextPath}/tblStudyPlanAction_sign.action?studyNoPara="+studyNoPara;
	parent.showMessager(1,'签字成功!',true,3000);
}