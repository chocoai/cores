window.onload =function(){
	var length = $("#length").val();
	if(length == 0){
		showDiv("doseDiv");
		var a = document.getElementById("a");
//		a.style.display = "none";
		a.disabled = true;
	}else {
		for(var i=0;i<length;i++){
			dosageNum = $("#dosageNum"+i).val();
			if(!$("#dosageDesc"+i).val()){
				if(dosageNum == 1){
						$("#dosageDesc"+i).val("对照组");
				}else if(dosageNum == 2){
					$("#dosageDesc"+i).val("低剂量组");
				}else if(dosageNum == 3){
					$("#dosageDesc"+i).val("中剂量组");
				}else if(dosageNum == 4){
					$("#dosageDesc"+i).val("高剂量组");
				}else if(dosageNum == 5){
					$("#dosageDesc"+i).val("阳性量组");
				}
			}
		}
	}
}
/**单选框  事件*/
function onRadioCk(selectNum){
	if(selectNum == '1'){
		$('#animalCodeMode').numberbox('setValue',1);
//		$('#animalCodeMode').val('1');
		$('#radioA').attr("checked",'checked'); 
		$('#radioB').removeAttr("checked");
	}else{
		$('#animalCodeMode').numberbox('setValue',2);
		$('#radioB').attr("checked",'checked'); 
		$('#radioA').removeAttr("checked");
	}
}
/**
 * 剂量组设置
 * @param studyNoPara
 * @return
 */
function doseNumSetting(studyNoPara){
	allDoseNum = $("#dose").val();
	if(!fullNumCheck(allDoseNum)){
		$("#span2").html("剂量组数为大于零的整数！");
	}else{
		window.location.href="${pageContext.request.contextPath}/tblDoseSettingAction_doseSettingEdit.action?studyNoPara="+studyNoPara+"&allDoseNum="+allDoseNum;
	}
}

/**
 * 解剖次数变更
 * @param index
 * @return
 */
function exChangeNum(index){
	dosageNum = $("#dosageNum"+index).val();
	if(dosageNum == 1){
		$("#dosageDesc"+index).val("对照组");
	}else if(dosageNum == 2){
		$("#dosageDesc"+index).val("低剂量组");
	}else if(dosageNum == 3){
		$("#dosageDesc"+index).val("中剂量组");
	}else if(dosageNum == 4){
		$("#dosageDesc"+index).val("高剂量组");
	}else if(dosageNum == 5){
		$("#dosageDesc"+index).val("阳性量组");
	}
}

function saveAll(){
	var length = $("#length").val();
	var flag=true;
	for(var i=0; i<length; i++){
		var dosageNum = $("#dosageNum"+i).val();
		var dosageDesc = $("#dosageDesc"+i).val();
		var dosage = $("#dosage"+i).val();
		var maleNum = $("#maleNum"+i).val();
		var femaleNum = $("#femaleNum"+i).val();
		if(!nullCheck(dosageNum)){
			$("#span1").html("第"+(i+1)+"剂量组编号不能为空！");
			flag=false;
			break;
		}else if (!fullNumCheck(dosageNum)) {
			$("#span1").html("第"+(i+1)+"剂量组编号为正整数！");
			flag=false;
			break;
		}else if (!nullCheck(dosageDesc)) {
			$("#span1").html("第"+(i+1)+"剂量组说明不能为空！");
			flag=false;
			break;
		}else if(!nullCheck(dosage)){
			$("#span1").html("第"+(i+1)+"剂量组剂量不能为空！");
			flag=false;
			break;
		}else if (numberCheck(dosage)) {
			$("#span1").html("第"+(i+1)+"剂量组剂量为数值！");
			flag=false;
			break;
		}else if(!nullCheck(maleNum)){
			$("#span1").html("第"+(i+1)+"剂量组雄性数量不能为空！");
			flag=false;
			break;
		}else if (!fullNumCheck(maleNum)) {
			$("#span1").html("第"+(i+1)+"剂量组雄性数量为正整数！");
			flag=false;
			break;
		}else if(!nullCheck(femaleNum)){
			$("#span1").html("第"+(i+1)+"剂量组雌性数量不能为空！");
			flag=false;
			break;
		}else if (!fullNumCheck(femaleNum)) {
			$("#span1").html("第"+(i+1) 	+"剂量组雌性数量为正整数！");
			flag=false;
			break;
		}else {
			$("#span1").html("");
			flag=true;
		}
	}
	if(flag){
			document.forms[0].submit();
//			if(confirm("您确定要保存吗？")){
//		}
	}
}

//转到编辑页面
function doseSettingEdit(studyNoPara){
	window.location.href="tblDoseSettingAction_doseSettingEdit.action?studyNoPara="+studyNoPara;
}