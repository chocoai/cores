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

function select_showQianmingDialog(clickName){
	$('#selectqianming_message').html("");
	$('#selectqianmingForm').form('clear');
	/*签名Dialog*/
	document.getElementById("qianming4").style.display="block";
	$('#selectqianming').dialog('open'); 
	document.getElementById("thisSelectUserName").focus();
	document.getElementById("selectqm_sign").href="javascript:"+clickName+"();";
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
//					qm_cancelQianming();
					$('#qianming').dialog('close'); 
					
				}else{
					$('#qianming_message').html(r.msg);
					$("#password").val('');
					document.getElementById("password").focus();
				}
			}
		});
	}
}

/** 复核密码验证(确定按钮事件) */
function fhqm_passwordCheck() {
	if ($("#fuqianmingForm").form('validate')) {
		$.ajax( {
			type : "post",
			url : sybp() + "/userAction_fupasswordCheck.action?",
			data : $("#fuqianmingForm").serialize(),
			cache : false,
			dataType : 'json',
			success : function(r) {
				// 密码验证通过
				if (r && r.success) {
					$("#TheFHUserName").val(r.obj);
					// 触发点击事件（即传入的clickName）
					var qm_sign = document.getElementById("fhqm_sign");
					qm_sign.click();
					// 关闭Dialog
					fuqm_cancelQianming();
				} else {
					
					$('#fhqianming_message').html(r.msg);
					document.getElementById("thisUserName").focus();
					$("#TheFHUserName").val('');
				}
			}
		});
	}
}
         
function selectqm_passwordCheck(){
	var name1  =$('#realName11').combotree('getText');
	if(name1 == ""){
		$('#selectqianming_message').html("请输入正确的用户名");
	}else{
	var qm_sign = document.getElementById("selectqm_sign");
	qm_sign.click();
	document.getElementById('thisSelectUserName').readOnly=true;
	$('#selectqianming').dialog('close'); 
	}
}

// function QueryTheApplicant(){
// $.ajax({
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
/**密码框上的回车事件(复核用，手动输用户名)*/
function qm_EnterPress_fuhe(){
	if(event.keyCode == 13){
		fhqm_passwordCheck();
	}
}
function qm_EnterPress_select(){
	if(event.keyCode == 13){
		select_passwordCheck();
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
/**关闭Dialog*/
function select_cancelQianming(){
	$('#selectqianming').dialog('close'); 
}
