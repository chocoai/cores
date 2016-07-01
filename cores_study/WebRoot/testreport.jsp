<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>i report</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
</head>
<body>
<div class="easyui-layout" fit="true">
	<div region="center" fit="true" >
			<iframe id="ireport" name="ireport"  src="${pageContext.request.contextPath}/testreportAction_report.action" style="border:0;width:100%;height:99.4%;" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
</div>


</body>
</html>
