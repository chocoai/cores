<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>i report</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/clinicalTestApply.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js"></script>
<script type="text/javascript">
	$(function(){
		var iframe = document.getElementById('ireport');
		if (!/*@cc_on!@*/0) { //if not IE
			iframe.onload = function(){
				//alert("Local iframe is now loaded.");
				parent.parent.close();
			};
		} else {
			iframe.onreadystatechange = function(){
				if (iframe.readyState == "complete"){
					//alert("Local iframe is now loaded.");
					parent.parent.close();
				}
			};
		} 
	});
	/**返回按钮事件*/
	function onBackButton(){
		var toWhere = $('#toWhere').val();
		var studyNoPara = $('#studyNoPara').val();
		var reqNoPara = $('#reqNoPara').val();
		if(toWhere == 'apply'){
			window.location.href=sybp()+'/tblClinicalTestReqAction_clinicalTestApply.action?studyNoPara='
			+studyNoPara+'&reqNoPara='+reqNoPara;
		}else if(toWhere == 'view'){
			window.location.href=sybp()+'/tblClinicalTestReqAction_view.action?studyNoPara='
			+studyNoPara+'&reqNoPara='+reqNoPara;
		}else{
			window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara="
				+studyNoPara+'&reqNoPara='+reqNoPara;
		}
	}
</script>
</head>
<body>
<div class="easyui-layout" fit="true">
	<s:hidden id="toWhere" name="toWhere"></s:hidden>
	<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
	<s:hidden id="reqNoPara" name="reqNoPara"></s:hidden>
	<div region="north" style="height:30px;">
			<a class="easyui-linkbutton" onclick="onBackButton();" data-options="iconCls:'icon-back',plain:true">返回</a>
	</div>
	<div region="center" fit="true" >
			<iframe id="ireport" name="ireport"  src="${pageContext.request.contextPath}/tblClinicalTestReqAction_outportClinicalTestReq.action?studyNoPara=${studyNoPara}&reqNoPara=${reqNoPara}" style="border:0;width:100%;height:99.4%;" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
</div>


</body>
</html>
