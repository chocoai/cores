/**
 * 实验类别编码唯一性检查
 * @return
 */
function checkCode()
{
	var submit=document.getElementById('submit');
	
	var studyTypeCode=$("#studyTypeCode").val();
	if(studyTypeCode==null ||studyTypeCode==""){
		return false;
		submit.disabled = true;
	}
    $.ajax({
        type:"POST",
        url:"${pageContext.request.contextPath}/dictStudyTypeAction_checkCode.action",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"html",
        data:"studyTypeCode="+$("#studyTypeCode").val(),
        beforeSend:function(XMLHttpRequest)
        {
        },
        success:function(msg)
        {
        	if(msg=="is")
        	{
	  	    	 $("#span1").html("编码已存在！");
	  	    	 $("#span1").css("color","red");
	  	    	submit.disabled=true;
        	}
        	if(msg=="no")
        	{
        		$("#span1").text(""); 
        		document.getElementById("submit").disabled=false;
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
        return flag;
}

/**
 * 表单提交
 * @return
 */
function formSubmit1(){
	var studyTypeCode = $("#studyTypeCode").val();
	var studyName = $("#studyName").val();
	var studyPeriod = $("#studyPeriod").val();
	var studyPeriodUnit = $("#studyPeriodUnit").val();
	if(!nullCheck(studyTypeCode)){
		document.getElementById("span1").innerHTML="*";
	}
	if(!nullCheck(studyName)){
//		$("#span2").html="*";
		document.getElementById("span2").innerHTML="*";
	}
	if(!fullNumCheck(studyPeriod)){
		document.getElementById("span3").innerHTML="*";
	}
	if(!nullCheck(studyPeriodUnit)){
		document.getElementById("span4").innerHTML="*";
	}
	if(fullNumCheck(studyPeriod) && nullCheck(studyTypeCode) && nullCheck(studyName) && nullCheck(studyPeriodUnit)){
		document.forms[0].submit();
	}
	
}