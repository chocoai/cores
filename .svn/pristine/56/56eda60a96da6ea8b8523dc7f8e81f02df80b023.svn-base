<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/menu.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/jquery-1.8.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/syUtils.js" charset="utf-8"></script>
<style type="text/css">
* { margin: 0; padding: 0;}
html,body{
	height:100%;
	color:#000;
	font-size:12px;
	font-family:microsoft yahei;
}

ul{
	list-style:none;
	padding:0px;
	margin:0px;
}
ul li{
	width:180px;
	padding:0px 10px;
	float:none;
	border-bottom:1px solid #131313;
	border-top:1px solid #323232;
}

ul li a{
	float:none;
	height:32px;
	line-height:32px;
	width:190px;
	margin-right:0px;
	color:#FFF;
	text-decoration:none;
	display:inline-block;
}
ul li a:hover{
	color:#fc7404;
}

</style>
</head>

<body >
<ul >
	<s:if test="isSchedule == 1">
		<li>
			<a id="schedule" name="menu_a" href="javascript:void();" onclick="openWindow(this);" style="height:32px;">综合管理系统</a>
		</li>
	</s:if>
	<s:if test="isStudyPlan == 1">
		<li>
			<a id="study" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">专题管理系统</a>
		</li>
	</s:if>
	<s:if test="isContract == 1">
		<li>
			<a id="contract" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">委托管理系统</a>
		</li>
	</s:if>
	<s:if test="isQA == 1">
		<li>
			<a id="qa" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">QA管理</a>
		</li> 
	</s:if>
	<s:if test="isSOP == 1">
		<li>
			<a id="sop" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">SOP管理</a>
		</li> 
	</s:if>
	<s:if test="isArchive == 1">
		<li>
			<a id="varchive" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">档案管理</a>
		</li> 
	</s:if>
	<s:if test="audit == 1">
		<li>
			<a id="audit" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">系统审计信息</a>
		</li> 
	</s:if>
	<s:if test="isSystemSet == 1">
		<li>
			<a id="systemset" name="menu_a" href="javascript:void();"  onclick="openWindow(this);" style="height:32px;">系统管理</a>
		</li>
	</s:if>
 
</ul>
<s:hidden id="myticket" name="myticket"></s:hidden>
<s:hidden id="from" name="from"></s:hidden>
</body>
</html>

