<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/bases.css" media="screen" />
</head>
<body>
<a style="font-weight:bold; font-size:12px;">已选择动物ID号:</a>
<div style="width:260px;">
	<s:iterator value="#SelectionfAnimaldetermofdeath" var="obj1">
		<div style=" float:left; font-size:12px; border-bottom:1px solid #c9c9c9;  width:60px; padding-top:10px;">
		${obj1.animalId}</div>
	</s:iterator>
</div>
</body>
</html>