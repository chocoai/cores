/**关闭消息*/
function close(){
	$('#syMessagerShow').css('display','none');
}
function showTopMessager(msg,isShowClose,showTime){
	second = 40;
	time = 0;
	height =0;
	timer_up;
	$('#syMessagerShow').css('top',-28);
	$('#syMessagerShow').css('display','');
	//消息
	$('#messager').html(msg);
	if(isShowClose){//true显示 关闭按钮
		$('#messager_closebutton').css('display','');
	}else{//不显示
		$('#messager_closebutton').css('display','none');
	}
	if(timer_down){
		clearInterval(timer_down);
	}
	if(timer_up){
		clearInterval(timer_up);
	}
	if(timer_out){
		clearTimeout(timer_out);
	}
	//重复的定时器向下
	timer_down = setInterval( down , second);
	if(showTime){
		//不重复的定时器
		timer_out = setTimeout(timeout,showTime);
	}
}
/*显示提示信息（1：类型（1 2 3 ），2：提示消息   3：是否可关闭   4：显示时间）*/
function showMessager(messagerType,msg,isShowClose,showTime){
    $('#topMessagerShowDiv').css('visibility','');
	if(messagerType == 1){
		$('#syMessagerShow').removeClass().addClass('topMessager_success');
	}else if(messagerType == 3){
		$('#syMessagerShow').removeClass().addClass('topMessager_fail');
	}else{
		$('#syMessagerShow').removeClass().addClass('topMessager_warn');
	}
	second = 40;
	time = 0;
	height =0;
	timer_up;
	$('#syMessagerShow').css('top',-28);
	$('#syMessagerShow').css('display','');
	//消息
	$('#messager').html(msg);
	if(isShowClose){//true显示 关闭按钮
		$('#messager_closebutton').css('display','');
	}else{//不显示
		$('#messager_closebutton').css('display','none');
	}
	if(timer_down){
		clearInterval(timer_down);
	}
	if(timer_up){
		clearInterval(timer_up);
	}
	if(timer_out){
		clearTimeout(timer_out);
	}
	//设置信息框宽度
	if(getByteLen(msg)<26){
		$('#syMessagerShow').css('width','200');
	}else{
		$('#syMessagerShow').css('width',200+(Number(getByteLen(msg))-26)*6.5);
	}
	
	
	//重复的定时器向下
	timer_down = setInterval( down , second);
	if(showTime){
		//不重复的定时器
		timer_out = setTimeout(timeout,showTime);
	}
}

/**刷新main 区域*/
function updateMain(){
	var a_updateMain=document.getElementById("a_updateMain");
	a_updateMain.click();
	$('#system_box').css('display','none');
	setTopStudyNoSD('','');
}
/**显示system——box*/
function showSystembox(){
	$('#system_box').css('display','block');
}
/**隐藏system——box*/
function hiddenSystembox(){
	$('#system_box').css('display','none');
}
/**修改密码前的方法*/
function beforeAlterPassword(){
	ap_showQianmingDialog('eventAfterAlterPassword','');
}
/***/
 function eventAfterAlterPassword(){
	 /*签名Dialog*/
		document.getElementById("alterpassword2").style.display="block";
		//提示消息
		$('#alterPassword_message').html('');
		//用户名赋值
		$('#ap_userName').val('');
		//密码清空
		$('#ap_password').val('');
		$('#ap_newPassword').val('');
		$('#ap_reNewpassword').val('');
		//关闭对话框
		$('#alterpassword').dialog('close'); 
		$.messager.show({
			title:'提示',
			msg:'密码修改成功',
			timeout:5000
		});
}
/**设置课题编号 SD*/
function setTopStudyNoSD(studyNo,sd){
	$('#top_studyNo').html(studyNo);
	$('#top_sd').html(sd);
}

/**js获取长度*/
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
            len += 2;
        else
            len += 1;
    }
    return len;
}

function hiddenTopDiv(){
       $('#topMessagerShowDiv').css('visibility','hidden');
}

//点击链接，打开窗口
function openWindow(obj){
	$.ajax({
		url:sybp()+'/userAction_isvail.action',
		type:'post',
		dataType:'json',
		success:function(r){
			if(r && r.success){
				var qm_sign =document.getElementById(obj.id+1);
				qm_sign.click();
			}
		}
	});
}