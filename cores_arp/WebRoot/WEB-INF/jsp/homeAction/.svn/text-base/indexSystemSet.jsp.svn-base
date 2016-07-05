<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES系统管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
</head>

<body SCROLL=no>
	<div class="container">
    	<div class="header_box">
    		<input id="userName" type="hidden" value="${user.userid}"></input>
        <jsp:include page="topSystemSet.jsp"></jsp:include>
        </div>
        <div class="sidebar_box">
        	<iframe name="left" src="${pageContext.request.contextPath}/homeAction_leftSystemSet.action?name=${userid}" width=100% height=100% frameborder=no border=0 marginwidth=0 marginheight=0 scrolling=no allowtransparency=yes></iframe>
        </div>
        <div class="mainbody2">
        	<iframe name="main" src="${pageContext.request.contextPath}/homeAction_mainSytemSet.action" width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>
        </div>

    </div>
    <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
</body>
</html>




