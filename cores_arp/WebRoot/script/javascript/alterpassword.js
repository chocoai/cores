/**显示修改密码Dialog*/
function ap_showQianmingDialog(clickName,msg){
	/*签名Dialog*/
	document.getElementById("alterpassword2").style.display="block";
	//提示消息
	$('#alterPassword_message').html(msg);
	//用户名赋值
	$('#ap_userName').val($('#userName').val());
	//打开对话框
	$('#alterpassword').dialog('open'); 
	document.getElementById("ap_password").focus();
	document.getElementById("ap_click").href="javascript:"+clickName+"();";
}
/**确认提交(确定按钮事件)*/
function ap_submit(){
	if($("#alterpasswordForm").form('validate')){
		//验证密码正确性
		$.ajax({
			type:"post",
			url:sybp()+"/userAction_passwordCheck.action",
			data:{
				password:$('#ap_password').val()
			},
			cache:false,
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//判断新旧密码是否相同
					if($('#ap_password').val() != $('#ap_newPassword').val()){
						//修改密码
						$.ajax({
							type:"post",
							url:sybp()+"/userAction_alterPassword.action",
							data:$('#alterpasswordForm').serialize(),
							cache:false,
							dataType:'json',
							success:function(r){
								if(r && r.success){
									var ap_click = 	document.getElementById("ap_click");
									ap_click.click();
								}else{
									$.messger.alert('提示','密码修改失败');
								}
								
							}
						});
					}else{
						$.messager.alert('提示','新密码不可以和原密码相同');
					}
				}else{
					$.messager.alert('提示','原密码错误');
				}
			}
		});
		
	}
}
/**检查密码是否正确*/
function checkPasswrod(){
	var ap_password =$('#ap_password').val();
	if(ap_password){
		$.ajax({
			type:"post",
			url:sybp()+"/userAction_passwordCheck.action",
			data:{
				password:ap_password
			},
			cache:false,
			dataType:'json',
			success:function(r){
				if(r && r.success){
					
				}else{
					$.messager.alert('提示','原密码错误');
				}
			}
		});
	}
}
/**密码框上的回车事件*/
function ap_EnterPress(){
	if(event.keyCode == 13){
		ap_submit();
	}
}

/**关闭Dialog*/
function ap_cancelAlterpassword(){
	$('#alterpassword').dialog('close'); 
}

