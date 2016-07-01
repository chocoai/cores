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
 * 改变选中后状态
 * @param obj
 * @return
 */
function changeState(obj){
	//请空所有状态
	clearState()
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

/**注销*/
function logout(){
	$.messager.confirm('注销','确定注销登录吗？',function(r){
		if(r){
			window.location.href="${pageContext.request.contextPath}/userAction_logout.action";
		}
	});
}






