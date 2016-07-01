<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES专题管理系统-剂量设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script
	/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script
	/javascript/tblDoseSettingAction/list.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script
	/javascript/tblDoseSettingAction/doseSetting.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script
	/javascript/tblDoseSettingAction/doseAddEditDialog.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script
	/javascript/tblDoseSettingAction/animalCodeDialog.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script
	/javascript/tblDoseSettingAction/animalCodeAddEditDialog.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script
	/javascript/tblDoseSettingAction/animalCodeConfirmDialog.js"></script> 
<style type="text/css">
.table0{
	border-collapse:collapse;
	border-spacing:0;
	border-left:1px solid #c9c9c9;
	border-top:1px solid #c9c9c9;
	background:white;
}
.table0 td{
	border-right:1px solid #c9c9c9;
	border-bottom:1px solid #c9c9c9;
	padding:5px;
}
</style>
<script type="text/javascript">
 var lastMaleName;   //最新添加的雄性动物数量
 var lastFemaleName; //最新添加的雌性动物数量
 var lastNum;
 var animalTypeFlag;
 var isNoGenderFlag;
</script>


</head>
<body >
<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
<s:hidden id="left_member" name="left_member"></s:hidden>
<input type="hidden" id="selectedId" />
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%;display:none;">
	<div region="center" border="false" style="">
		<table id="doseSettingTable"></table>
	</div>
	<div id="toolbar">
   	    <a id="basicbutton" class = "easyui-linkbutton" plain="true" onclick="onDoseSetting();"
   	   	     data-options="iconCls:'icon-tableedit'" disabled="disabled">基本信息设置</a>
   	    <a id="addbutton" class = "easyui-linkbutton" plain="true" disabled="disabled" onclick="onAddButton();"
   	    data-options="iconCls:'icon-add'">添加</a>
   	    <a id="editbutton" class = "easyui-linkbutton" plain="true" disabled="disabled" onclick="onEditButton();"
   	    data-options="iconCls:'icon-edit'">编辑</a>
   	    <a id="delbutton" class = "easyui-linkbutton" plain="true" disabled="disabled" onclick="onDelButton();"
   	    data-options="iconCls:'icon-remove'">删除</a>
   	    <span id="codebuttonSpan">
	   	    <a id="codebutton" class = "easyui-linkbutton" plain="true" disabled="disabled" onclick="onCodeButton();"
	   	    data-options="iconCls:'icon-tableadd'">动物编号设置</a>
   	    </span>
   	    <a id="okbutton" class = "easyui-linkbutton" plain="true" disabled="disabled" onclick="onConfirm();"
   	    data-options="iconCls:'icon-ok'">剂量组设计确认</a>
   	    <a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">上移</a>
		<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownToolbarButton();" data-options="iconCls:'icon-redo',disabled:true,plain:true">下移</a>
		&nbsp;&nbsp;&nbsp;
		版本：<input id="applyReviseVer" class="easyui-combogrid"/>
		<span id="effectiveDateLabel"></span>
		<a id="changeEffectiveDateButton" class="easyui-linkbutton" onclick="changeApplyReviseEffectionDate();" data-options="iconCls:'icon-edit',disabled:true,plain:true">修改生效日期</a>
		
	</div>
	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	<%@ include file="/WEB-INF/jsp/doseSettingAction/doseSetting.jspf"%>
	<%@ include file="/WEB-INF/jsp/doseSettingAction/doseAddEditDialog.jspf"%>
	<%@ include file="/WEB-INF/jsp/doseSettingAction/animalCodeDialog.jspf"%>
	<%@ include file="/WEB-INF/jsp/doseSettingAction/animalCodeAddEditDialog.jspf"%>
	<%@ include file="/WEB-INF/jsp/doseSettingAction/animalCodeConfirmDialog.jspf"%>
	<%@ include file="/WEB-INF/jsp/doseSettingAction/changeDosesettingEffectiveDate.jspf"%>
</div>
</body>
</html>