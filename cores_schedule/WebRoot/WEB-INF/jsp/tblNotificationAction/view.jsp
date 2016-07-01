<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES通知信息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script src="${pageContext.request.contextPath}/script/javascript/tblNotification.js" type="text/javascript"></script>
<style type="text/css">
    
	table {
	    border: 1px solid #ccc;
	    border-collapse: collapse;
	}
	table th {
	    border: 1px solid #ccc;
	    height:25px;
	}
	table td {
		padding: 1px 5px 1px 5px;
	    border: 1px solid #ccc;
	    height:25px;
	}
</style>
<script type="text/javascript">
    $(function(){
    });//匿名函数结束
    
    
	//检查报告的链接
	function clickOneReport(chkReportCode){
		if($('#hasQATab').val()=='true'){
			
			$.ajax({
					url:sybp()+'/qAChkAction_putSelectChkReportCodeInSession.action?selectReportCode='+chkReportCode,
					type:'post',
					async:false,
					dataType:'json',
					success:function(r){
						parent.parent.parent.$('#indexTabsDiv').tabs('select','QA管理');
					}
			});
		}
		
	}
</script> 
</head>
<body>
<div style="width:100%;background-color: #f5f7fa">
	<s:hidden id="hasQATab" name="hasQATab"></s:hidden>
	<div style="font-size:16px;padding:10px 0 5px 15px;font-weight: bold" >
		<s:property value="msgTitle"/> 
	</div>
	<div style="font-size:14px;padding:0px 0 5px 13px;color: #999999;">
		<table>
		<tr>
			<td width="57px;" align="right" height="30px;">发送者：</td>
			<td><s:property value="sender"/> </td>
		</tr>
		<tr>
			<td  align="right">时　间：</td>
			<td><s:date name="sendTime" format="yyyy年MM月dd日  HH:mm" />&nbsp;（${week}）</td>
		</tr>
	</table>
	</div>
</div>
<div style="min-height200px;padding:15px 30px;">
	<div style="min-height:400px;  ">
		
		${msgContent}
	</div>
	<div style="border-bottom: 1px solid #c9c9c9;">
	</div>
</div>
</body>  
</html>