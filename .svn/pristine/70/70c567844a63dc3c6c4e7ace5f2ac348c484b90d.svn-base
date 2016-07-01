
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


/**
 * 查看信息
 * @return
 */
function view(id){
	window.open('${pageContext.request.contextPath}/tblNotificationAction_view.action?id='+id, 'view')
}

/**
 * 选中标签0
 * @return
 */
function selectTab0(){
	var notifytabs = $('#notifytabs').tabs('select', 0);
	$('#receievrTable').datagrid({
		url:sybp()+'/tblNotificationAction_loadList.action?readFlag = -1'
	});
}
/**打开通知管理*/
function openNotifyTab(){
	$('#indexTabsDiv').tabs('select',0);
}


//查找是否有新通知，有则提示
function getReceiveMail(){
	$.ajax({
		url : sybp()+'/tblNotificationAction_getReceiveMail.action',
		type: 'post',
		dataType:'json',
		success:function(r){
			if(r && r.unRecCount ){
				$.messager.show({
					title:'我的消息',
					msg:'<a href="javascript:openNotifyTab();">您有'+r.unRecCount+'条新消息</>',
					timeout:600000,
					showType:'slide'
				});

			}
		}
	});
}
var timer
//启动时钟
function startClock() {
	if(!timer){
		timer = window.setInterval(getReceiveMail,180000);
	}
}