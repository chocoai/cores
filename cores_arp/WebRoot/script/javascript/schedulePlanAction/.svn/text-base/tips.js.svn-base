/**打开通知管理*/
function openNotifyTab(){
	//$('#indexTabsDiv').tabs('select',0);使用在list.jsp
	$('#schedulePlanTabs').tabs('select',0);
}


//查找是否有新通知，有则提示
function getReceiveMail1(){
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
function getReceiveMail(){
	$.ajax({
		url : sybp()+'/schedulePlanAction_getSchedulePlanTips.action',
		type: 'post',
		dataType:'json',
		success:function(r){
			if(r.total>0){
				var str='您有'+r.total+'条新消息<br/>';
				for(var i=0;i<r.rows.length;i++){
					str=str+(i+1)+').'+r.rows[i].title+'<br/>';
				}
				$.messager.show({
					title:'我的消息',
					height:'100%',//默认100px，100%自适应.
					msg:str,
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
		timer = window.setInterval(getReceiveMail,300000*2);//10分钟
		//timer = window.setInterval(getReceiveMail,6000*2);
	}
}