<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/dictPathCommonAction/list.js"></script>
</head>
<body>
<!-- 存放datagrid加载后,待选中的行id -->
<input type="hidden" id="selectId"/>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
	<div region="center" title="" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="病理字典" border="false" style="overflow: auto;">
				<div style="height:20px; border-bottom:  1px solid #c8c8c8;padding: 5px 0 5px 0px;">
					<span style="padding:5px 5px 5px 15px;" >字典项：</span><input id="dictType" />
					<a id="dictVisceraSpan" style="padding:5px 5px 5px 15px;">脏器名称：<input id="dictViscera"/></a>
				</div>
				<table id="pathTable" ></table>
			</div>
		</div>
	</div>
</div>
<div id="toolbar" style="display:none;height:26px;padding: 2px 0;">
	<a id="addOne" class="easyui-linkbutton"  data-options="plain:true,iconCls:'icon-add'">新建</a>
	<a id="editOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit',disabled:true">编辑</a>
	<a id="delOne" class="easyui-linkbutton"  data-options="plain:true,iconCls:'icon-remove',disabled:true">删除</a>
	<a id="upOne" class="easyui-linkbutton"   data-options="plain:true,iconCls:'icon-undo',disabled:true">上移</a>
	<a id="downOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo',disabled:true">下移</a>
	<input id="lineNum" type="text" class="easyui-numberbox" 
	    value="1" data-options="min:1,precision:0,max:1000" style="width:35px;"></input>
	行
</div>
<%@include file="/WEB-INF/jsp/dictPathCommonAction/pathAddEdit.jspf" %>
</body>
</html>
