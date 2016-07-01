<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>i report</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	$(function(){
		var iframe = document.getElementById('ireport');
		if (!/*@cc_on!@*/0) { //if not IE
			iframe.onload = function(){
				//alert("Local iframe is now loaded.1");
				parent.close();
			};
		} else {
			iframe.onreadystatechange = function(){
				if (iframe.readyState == "complete"){
					//alert("Local iframe is now loaded.2");
					parent.close();
				}
			};
		} 
	});

	function onBackClick()
	{
		//javascript:history.back(-1);
		url="${pageContext.request.contextPath}/qAChkReportAction_list.action?chkReportCode=${chkReportCode}&QAMainPage="+$('#QAMainPage').val()+"&studyNoParam="+$('#studyNoParam').val()+"&reportStartDate="+$('#reportStartDate').val()+"&reportEndDate="+$('#reportEndDate').val()+"&reportStatus="+$('#reportStatus').val()+"&reportCatalog="+$('#reportCatalog').val()+"&reportSearcher="+$('#reportSearcher').val();
		window.location.href=url;
		
	}
	
</script>
</head>
<body>
<div class="easyui-layout" fit="true">

		<s:hidden id="QAMainPage" name="QAMainPage"></s:hidden>
		<s:hidden id="studyNoParam" name="studyNoParam"></s:hidden>
		<s:hidden id="reportStartDate" name="reportStartDate"></s:hidden>
		<s:hidden id="reportEndDate" name="reportEndDate"></s:hidden>
		<s:hidden id="reportStatus" name="reportStatus"></s:hidden>
		<s:hidden id="reportCatalog" name="reportCatalog"></s:hidden>
		<s:hidden id="reportSearcher" name="reportSearcher"></s:hidden>
		
	<div region="north" style="height:30px;">
			<a class="easyui-linkbutton" href="#" onclick="onBackClick();" data-options="iconCls:'icon-back',plain:true">返回</a>
	</div>
	
	<div region="center" fit="true" >
			<iframe id="ireport" name="ireport"  src="${pageContext.request.contextPath}/qAChkReportAction_outport.action?studyNoParam=${studyNoParam}&chkReportCode=${chkReportCode}&printContent=${printContent}&templeId=${templeId}" style="border:0;width:100%;height:100%;" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
</div>


</body>
</html>
