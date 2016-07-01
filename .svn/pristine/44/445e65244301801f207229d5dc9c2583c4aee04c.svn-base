function addSave(){
	var studyNoPara = $("#studyNoPara").val();
	var dissectNum = $("#dissectNum").val();
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var checkFlag = false;
	var animalDetailList = document.getElementsByName("animalDetailList");
	for(var i=0;i<animalDetailList.length;i++){
		if(animalDetailList[i].checked==true){
			checkFlag = true;
		}
	}
	
	if(!nullCheck(studyNoPara)){
		$("#span1").html("试验计划（课题编号）不能为空！");
	}else if (dissectNum == '') {
		$("#span1").html("解剖次数不能为空！");
	}else if (!nullCheck(beginDate)) {
		$("#span1").html("开始日期不能为空！");
	}else if (!nullCheck(endDate)) {
		$("#span1").html("结束日期不能为空！");
	}else if (!fullNumCheck(dissectNum)){
		$("#span1").html("解剖次数为非零正整数！");
	}else if (!checkFlag) {
		$("#span1").html("请选择需要解剖的动物！");
	}else {
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblDissectPlanAction_uniqueCheck.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"html",
	        data:"studyNoPara="+studyNoPara+"&dissectNumPara="+dissectNum,
	        beforeSend:function(XMLHttpRequest)
	        {
	        },
	        success:function(msg)
	        {
	        	if(msg=="is")
	        	{
		  	    	 $("#span1").html("该课题编号下解剖次数已存在！");
	        	}
	        	if(msg=="no")
	        	{
	        		$("#span1").text("");
	        		//检查无误，提交表单
	        			document.forms[0].submit();
//	        			if(confirm("您确定要保存？")){
//	        		}
	        	}
	        },
	        complete:function(XMLHttpRequest,textStatus)
	        {
	        },
	        error:function()
	        {
	             $("#span1").html("与服务器交互错误!");
	        }
	        });
	}
}

function editSave(){
	var studyNoPara = $("#studyNoPara").val();
	var dissectNum = $("#dissectNum").val();
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var checkFlag = false;
	var animalDetailList = document.getElementsByName("animalDetailList");
	for(var i=0;i<animalDetailList.length;i++){
		if(animalDetailList[i].checked==true){
			checkFlag = true;
		}
	}
	if(!nullCheck(studyNoPara)){
		$("#span1").html("试验计划（课题编号）不能为空！");
	}else if (dissectNum == '') {
		$("#span1").html("解剖次数不能为空！");
	}else if (!nullCheck(beginDate)) {
		$("#span1").html("开始日期不能为空！");
	}else if (!nullCheck(endDate)) {
		$("#span1").html("结束日期不能为空！");
	}else if (!fullNumCheck(dissectNum)){
		$("#span1").html("解剖次数为非零正整数！");
	}else if (!checkFlag) {
		$("#span1").html("请选择需要解剖的动物！");
	}else {
			document.forms[0].submit();
//			if(confirm("您确定要保存？")){
//		}
	}
}

//选中多选框
function selectCheckbox(obj){
	var id= obj.name;
	var checkbox=document.getElementById(id);
	if(checkbox.checked){
		checkbox.checked = false;
	}else{
		checkbox.checked =true;
	}
}