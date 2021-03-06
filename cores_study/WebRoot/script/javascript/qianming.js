/**显示签名Dialog*/
function qm_showQianmingDialog(clickName){
	/*签名Dialog*/
	document.getElementById("qianming2").style.display="block";
	$('#qianming').dialog('open'); 
	document.getElementById("password").focus();
	document.getElementById("qm_sign").href="javascript:"+clickName+"();";
	$("#password").val('');
}

function fuheqm_showQianmingDialog(clickName){
	$('#fuqianmingForm').form('clear');
	/*签名Dialog*/
	document.getElementById("qianming3").style.display="block";
	$('#fuqianming').dialog('open'); 
	document.getElementById("thisUserName").focus();
	document.getElementById("fhqm_sign").href="javascript:"+clickName+"();";
}

/**密码验证(确定按钮事件)*/
function qm_passwordCheck(){
	if($("#qianmingForm").form('validate')){
		$.ajax({
			type:"post",
			url:sybp()+"/userAction_passwordCheck.action",
			data:$("#qianmingForm").serialize(),
			cache:false,
			dataType:'json',
			success:function(r){
				//密码验证通过
				if(r && r.success){
					//触发点击事件（即传入的clickName）
					var qm_sign =document.getElementById("qm_sign");
					qm_sign.click();
					//关闭Dialog
					qm_cancelQianming();
					
				}else{
					$('#qianming_message').html(r.msg);
					$("#password").val('');
					document.getElementById("password").focus();
				}
			}
		});
	}
}

/**复核密码验证(确定按钮事件)*/
function fhqm_passwordCheck(){
		 if($("#fuqianmingForm").form('validate')){
				$.ajax({
					type:"post",
					url:sybp()+"/userAction_fupasswordCheck.action?studyNoPara="+$('#studyNoPara').val(),
					data:$("#fuqianmingForm").serialize(),
					cache:false,
					dataType:'json',
					success:function(r){
						//密码验证通过
						if(r && r.success){
							 $("#TheFHUserName").val(r.obj);
							//触发点击事件（即传入的clickName）
							var qm_sign =document.getElementById("fhqm_sign");
							qm_sign.click();
							//关闭Dialog
							fuqm_cancelQianming();
						}else{
							if( r.msg == "用户名错误"){
								$('#fhqianming_message').html(r.msg);
								$("#TheFhPassword").val('');
								document.getElementById("thisUserName").focus();
							}else if( r.msg == "密码错误"){
								$('#fhqianming_message').html(r.msg);
								$("#TheFhPassword").val('');
								document.getElementById("TheFhPassword").focus();
							}else if( r.msg == "请重新选择复核人员"){
								
								$('#fhqianming_message').html(r.msg);
								$("#TheFhPassword").val('');
								 $.messager.alert("提示", "复核人员不可以和操作人员重复，请重新选择复核人员!", "info", function () {
									  document.getElementById("thisUserName").focus();
							      });
								  
								
							}else{
								$('#fhqianming_message').html(r.msg);
								$("#TheFhPassword").val('');
								$("#thisUserName").val('');
								document.getElementById("thisUserName").focus();
							}
							
						}
					}
				});
			}
  
}

//function QueryTheApplicant(){
//	 $.ajax({
//			url:sybp()+'/userAction_QueryTheApplicant.action?studyNoPara='+$('#studyNoPara').val(),
//			type:'post',
//			cache:false,
//			dataType:'json',
//			success:function(r){
//				 if(r){
//					 $("#qianziname").val(r.qianziname); 
//					 $("#tempUsername").val(r.tempUsername);
//					 $("#TheFHUserName").val(r.TheFHUserName);
//					 fhqm_passwordCheck();
//			      }
//			}
//			});
//}

/**清楚提示信息*/
function fhqm_clearMessage1(){
	$('#fhqianming_message').html('');
}

/**密码框上的回车事件*/
function qm_EnterPress(){
	if(event.keyCode == 13){
		qm_passwordCheck();
	}
}

function fhqm_EnterPress(){
	if(event.keyCode == 13){
		fhqm_passwordCheck();
	}
}

/**清楚提示信息*/
function qm_clearMessage(){
	$('#qianming_message').html('');
}

/**关闭Dialog*/
function qm_cancelQianming(){
	$('#qianming').dialog('close'); 
}
/**关闭Dialog*/
function fuqm_cancelQianming(){
	$('#fuqianming').dialog('close'); 
}

