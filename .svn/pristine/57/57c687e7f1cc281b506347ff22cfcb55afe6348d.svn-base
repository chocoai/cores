<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES基于研究的检查</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js"></script>
<!-- 手动登记 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/homeAction/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddNo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyTypeSelect.js"></script>

<script type="text/javascript">
	  
    	//var tableHeight = window.screen.height-197;
	    var tableWidth  = window.innerWidth-20;
	    var leftWidth = 270;
	   
    	$(function(){
		  var tableHeight = document.body.clientHeight-3;
		 // var tableWidth  = document.body.clientWidth-20;
		   
		   
    		$('#researchLeft').css({"width":leftWidth});
    		$('#researchLeft').css({"height":tableHeight});
    		$('#researchMain').css({"width":tableWidth-leftWidth});
    		$('#researchMain').css({"height":tableHeight});
			 $('#changeButton').css({"width":20});
			 $('#changeButton').css({"height":tableHeight});

			//开始检查页面过来的，还是直接点击进来的
			
			//alert("==="+$('#selectStudyNoPara').val());
			
			if($('#selectStudyNoPara').val()!=null&&$('#selectStudyNoPara').val()!='')
			{
				var actionFlag = $('#afterTabOpenAction').val();//1方案2报告3任命qa
				if(actionFlag==null||actionFlag==''||actionFlag=='1'||actionFlag=='2')
				{
					changeWidth();
				}
				document.getElementById('chkPlanLeft').src="${pageContext.request.contextPath}/qAChkPlanAction_left.action?studyNoParam="+$('#selectStudyNoPara').val()+"&oneChkPlanId="+$('#selectChkPlanId').val()+"&selectChkReportCode="+$('#selectChkReportCode').val()+"&indexTitle="+$('#indexTextInMain').val()+"&afterTabOpenAction="+actionFlag;
				//document.getElementById('chkPlanMain').src="${pageContext.request.contextPath}/qAChkPlanAction_main.action?studyNoParam="+$('#selectStudyNoPara').val()+"&oneChkPlanId="+$('#selectChkPlanId').val()+"&selectChkReportCode="+$('#selectChkReportCode').val()+"&index="+$('#indexTextInMain').val();
				document.getElementById('chkPlanMain').src="";//有选择的专题的话，左边会默认选中一个专题，main会更新，这里不更新
			}else{
				document.getElementById('chkPlanLeft').src="${pageContext.request.contextPath}/qAChkPlanAction_left.action";
				document.getElementById('chkPlanMain').src="${pageContext.request.contextPath}/qAChkPlanAction_main.action";
				
			}
			
        });//匿名函数结束
      	
     
        function changeWidth()
        {
			
			if($('#changeSizeButton').attr('src')=='${pageContext.request.contextPath}/style/images/right.png')
			{
				$('#changeSizeButton').attr('src','${pageContext.request.contextPath}/style/images/left.png');
				$('#researchLeft').css({"width":leftWidth});
	    		$('#researchMain').css({"width":tableWidth-leftWidth});
	    		//document.getElementById('chkPlanMain').contentWindow.$('#researchCheckTabs').tabs('resize');
	    		//document.getElementById('chkPlanLeft').contentWindow.$('#studyDatagrid').datagrid({width:leftWidth,});
			}else
			{
				$('#changeSizeButton').attr('src','${pageContext.request.contextPath}/style/images/right.png');
				$('#researchLeft').css({"width":0});
	    		$('#researchMain').css({"width":tableWidth});
				//document.getElementById('chkPlanMain').contentWindow.$('#researchCheckTabs').tabs('resize');
				
			}
				
         }
</script>
	  
</head>

<body >
	<s:hidden id="selectStudyNoPara" name="selectStudyNoPara"></s:hidden>
	<s:hidden id="selectChkPlanId" name="selectChkPlanId"></s:hidden>
	<s:hidden id="selectChkReportCode" name="selectChkReportCode"></s:hidden>
	<s:hidden id="indexTextInMain" name="indexText"></s:hidden>
	<s:hidden id="afterTabOpenAction" name="afterTabOpenAction"></s:hidden>
    <div>
 		<div id="researchLeft" style="float: left;" style="border: 1px solid #c8c8c8;">
        	<iframe style="overflow: auto" id="chkPlanLeft" name="chkPlanleft" width=100% height=100% frameborder=no border=0 marginwidth=0 marginheight=0 scrolling=no allowtransparency=yes></iframe>
        </div>
        
        <div id="changeButton" style="float: left;" >
        	<img id="changeSizeButton" style="width: 15px;" onclick="changeWidth();"
        		src="${pageContext.request.contextPath}/style/images/left.png" alt="" />
        	
        </div>
       
         
        <div id="researchMain" style="float: left;" style="border: 1px solid #c8c8c8;">
        	<iframe style="overflow: auto" id="chkPlanMain" name="chkPlanMain"  width=100% height=100% frameborder=no border=0 scrolling=no></iframe>
        </div>

  
  <%@include file="/WEB-INF/jsp/left/studyPlanAddEdit.jspf" %>
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
   <!-- 手动登记专题 -->
    </div>
   
   
</body>
</html>




