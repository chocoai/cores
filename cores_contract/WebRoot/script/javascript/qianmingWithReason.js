/**显示签名Dialog*/
function qm_showQianmingWithReasonDialog(clickName){
	/*签名Dialog*/
	document.getElementById("qianmingWithReason2").style.display="block";
	$('#qianmingWithReason').dialog('open'); 
	document.getElementById("reason").focus();
	document.getElementById("qmWithReason_sign").href="javascript:"+clickName+"();";
	$("#reason").val('');
	$("#passwordWithReason").val('');
}

//function fuheqm_showQianmingDialog(clickName){
//	$('#fuqianmingForm').form('clear');
//	/*签名Dialog*/
//	document.getElementById("qianming3").style.display="block";
//	$('#fuqianming').dialog('open'); 
//	document.getElementById("thisUserName").focus();
//	document.getElementById("fhqm_sign").href="javascript:"+clickName+"();";
//}

/**密码验证(确定按钮事件)*/
function qmWithReason_passwordCheck(){
	if($("#qianmingWithReasonForm").form('validate')){
		$.ajax({
			type:"post",
			url:sybp()+"/userAction_passwordCheck.action",
			data:$("#qianmingWithReasonForm").serialize(),
			cache:false,
			dataType:'json',
			success:function(r){
				//密码验证通过
				if(r && r.success){
					//触发点击事件（即传入的clickName）
					var qmWithReason_sign =document.getElementById("qmWithReason_sign");
					qmWithReason_sign.click();
					//关闭Dialog
					qmWithReason_cancelQianming();
				}else{
					$('#qmWithReason_message').html(r.msg);
					$("#passwordWithReason").val('');
					document.getElementById("passwordWithReason").focus();
				}
			}
		});
	}
}

///**复核密码验证(确定按钮事件)*/
//function fhqm_passwordCheck(){
//		 if($("#fuqianmingForm").form('validate')){
//				$.ajax({
//					type:"post",
//					url:sybp()+"/userAction_fupasswordCheck.action?studyNoPara="+$('#studyNoPara').val(),
//					data:$("#fuqianmingForm").serialize(),
//					cache:false,
//					dataType:'json',
//					success:function(r){
//						//密码验证通过
//						if(r && r.success){
//							 $("#TheFHUserName").val(r.obj);
//							//触发点击事件（即传入的clickName）
//							var qm_sign =document.getElementById("fhqm_sign");
//							qm_sign.click();
//							//关闭Dialog
//							fuqm_cancelQianming();
//						}else{
//							if( r.msg == "用户名错误"){
//								$('#fhqianming_message').html(r.msg);
//								$("#TheFhPassword").val('');
//								document.getElementById("thisUserName").focus();
//							}else if( r.msg == "密码错误"){
//								$('#fhqianming_message').html(r.msg);
//								$("#TheFhPassword").val('');
//								document.getElementById("TheFhPassword").focus();
//							}else if( r.msg == "请重新选择复核人员"){
//								
//								$('#fhqianming_message').html(r.msg);
//								$("#TheFhPassword").val('');
//								 $.messager.alert("提示", "复核人员不可以和操作人员重复，请重新选择复核人员!", "info", function () {
//									  document.getElementById("thisUserName").focus();
//							      });
//								  
//								
//							}else{
//								$('#fhqianming_message').html(r.msg);
//								$("#TheFhPassword").val('');
//								$("#thisUserName").val('');
//								document.getElementById("thisUserName").focus();
//							}
//							
//						}
//					}
//				});
//			}
//  
//}

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

///**清除提示信息*/
//function fhqm_clearMessage1(){
//	$('#fhqianming_message').html('');
//}

/**密码框上的回车事件*/
function qmWithReason_EnterPress(){
	if(event.keyCode == 13){
		qmWithReason_passwordCheck();
	}
}
/**操作原因框上的回车事件*/
function reason_EnterPress(){
	if(event.keyCode == 13){
		document.getElementById("passwordWithReason").focus();
	}
}
//function fhqm_EnterPress(){
//	if(event.keyCode == 13){
//		fhqm_passwordCheck();
//	}
//}

/**清除提示信息*/
function qmWithReason_clearMessage(){
	$('#qmWithReason_message').html('');
}

/**关闭Dialog*/
function qmWithReason_cancelQianming(){
	$('#qianmingWithReason').dialog('close'); 
}
///**关闭Dialog*/
//function fuqm_cancelQianming(){
//	$('#fuqianming').dialog('close'); 
//}

