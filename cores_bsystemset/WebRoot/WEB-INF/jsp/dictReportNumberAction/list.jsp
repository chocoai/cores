<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/dictReportNumberAction/list.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/dictReportNumberAction/reportNumberEdit.js"></script>
</head>
<body>
<!-- 存放datagrid加载后,待选中的行id -->
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
	<div region="center" title="" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="报表编号设置" border="false" style="overflow: auto;">
				<table id="reportNumberTable" ></table>
			</div>
		</div>
	</div>
</div>
<div id="toolbar" style="display:none;height:26px;padding: 2px 0;">
	<a id="editOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit',disabled:true">编辑</a>
</div>
<%@include file="/WEB-INF/jsp/dictReportNumberAction/reportNumberEdit.jspf" %>
</body>
</html>
