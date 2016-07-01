//页面加载后执行
window.onload=function(){
//	var currentClassElements = getElementsByClassName('current'); 
//	for (var i=0; i<currentClassElements.length; i++) { 
//		var studyNoPara = currentClassElements[i].name;
//		var editButton = document.getElementById("edit");
//		editButton.href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanEdit.action?studyNoPara="+studyNoPara;
//		editButton.disabled = "";
//		document.getElementById("").title;
//	}
}

/**
 * 点击试验计划，显示试验计划列表
 */
function studyPlan(){
	$("#studyPlanList").show();
	$("#dictList").hide();
}

/**
 * 点击系统设置，显示字典列表
 * @return
 */
function dict(){
	$("#studyPlanList").hide();
	$("#dictList").show();
}

/**
 * 选中课题编号后样式改变
 * @param index
 * @param lenght
 * @return
 */
function indexSelect(obj){
	var currentClassElements = getElementsByClassName('current'); 
	var currentClassElements1 = getElementsByClassName('newcurrent'); 
	for (var i=0; i<currentClassElements.length; i++) { 
		currentClassElements[i].className="";
	}
	for (var i=0; i<currentClassElements1.length; i++) { 
		currentClassElements1[i].className="";
	}
	obj.className="current";
	$('#studyNoPara').val(obj.innerHTML);
	$('#left_member').val("");
//	updateTopStudyPlan(obj.innerHTML,$('#a'+obj.innerHTML).val());
	updateTopStudyPlan(obj.innerHTML,$(obj).attr("id"));
}

/**
 * 选中课题编号后样式改变
 * @param index
 * @param lenght
 * @return
 */
function indexSelect1(obj){
	var currentClassElements = getElementsByClassName('current'); 
	var currentClassElements1 = getElementsByClassName('newcurrent'); 
	for (var i=0; i<currentClassElements.length; i++) { 
		currentClassElements[i].className="";
	}
	for (var i=0; i<currentClassElements1.length; i++) { 
		currentClassElements1[i].className="";
	}
	obj.className="newcurrent";
	$('#studyNoPara').val(obj.innerHTML);
	$('#left_member').val("readonly");
//	updateTopStudyPlan(obj.innerHTML,$('#a'+obj.innerHTML).val());
	updateTopStudyPlan(obj.innerHTML,$(obj).attr("id"));
}

/**更新顶部  试验计划部分*/
function updateTopStudyPlan(studyNoPara,sd){
	parent.setTopStudyNoSD(studyNoPara,sd);
}

/**
 * 年份onchange 事件
 */
function showthisyear(obj){
	var year =obj.value;
	var isValidationPara=$('#isValidationPara').val();
	window.location.href="${pageContext.request.contextPath}/homeAction_leftStudyPlan.action?year="
		+year+"&studyNoPara="+$('#sstudyNoPara').val()+"&isValidationPara="+isValidationPara;
}
/**
 * 是否显示验证试验
 * @return
 */
function onCheckbox(){
	var year =document.getElementById('yearList').value;
	var isValidationPara=$('#isValidationPara').val();
	if(isValidationPara == 0){
		isValidationPara=1;
	}else{
		isValidationPara=0;
	}
	window.location.href="${pageContext.request.contextPath}/homeAction_leftStudyPlan.action?year="
		+year+"&studyNoPara="+$('#sstudyNoPara').val()+"&isValidationPara="+isValidationPara;
}
/**
 * 改变选中后状态
 * @param obj
 * @return
 */
function changeState(obj){
	var currentClassElements = getElementsByClassName('current'); 
	for (var i=0; i<currentClassElements.length; i++) { 
		currentClassElements[i].className="";
	}

	obj.className="current"
	
}
/**
 * 请空所有状态
 * @param obj
 * @return
 */
function clearState(){
	var currentClassElements = getElementsByClassName('current'); 
	for (var i=0; i<currentClassElements.length; i++) { 
		currentClassElements[i].className="";
	}
	var editButton = document.getElementById("edit");
	editButton.disabled = true;
}


/**注销*/
function logout(){
	$.messager.confirm('注销','确定注销登录吗？',function(r){
		if(r){
			window.location.href="${pageContext.request.contextPath}/userAction_logout.action";
		}
	});
}


/**
 * 根据className 得到元素
 * @param n
 * @return
 */
function getElementsByClassName(n) { 
	var classElements = [],allElements = document.getElementsByTagName('*'); 
	for (var i=0; i< allElements.length; i++ ) 
	{ 
	   if (allElements[i].className == n ) { 
	       classElements[classElements.length] = allElements[i]; 
	   } 
	} 
	return classElements; 
}



