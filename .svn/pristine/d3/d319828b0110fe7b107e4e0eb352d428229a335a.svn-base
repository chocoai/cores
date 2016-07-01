<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES综合管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblAttachmentIndexAction/list.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblAttachmentIndexAction/attachmentAdd.js"></script> 
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
</head>
<body >
<!-- 开始时间 -->
<s:hidden id="startDate" name="startDate"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endDate" name="endDate" ></s:hidden>   
<!-- 当前登录用户  -->
<input id="realName" type="hidden" value="${user.realName}"/>
<!-- 是否有登记的权限 -->
<input id="write" type="hidden" value="${write}"/>
<input id="printAll" type="hidden" value="${printAll}"/>
<input id="selectedId" type="hidden"/>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%;display:none;">
	<div region="center" border="false" style="">
		<div style="margin:5px;border:1px solid #e3e6eb;height:93%;">
			<iframe src=""  frameborder="0" src="${pageContext.request.contextPath}/tblAttachmentIndexAction_view.action" style="border:0;width:100%;height:99.4%;" name="view"></iframe>
		</div>
	</div>
	<div id="west" region="west" border="false" style="width:750px;">
		<table id="indexTable"></table>
	</div>
	<div id="north" region="north" border="false" style="height:30px;">
		<div style="margin-top:5px;height:22px;border-bottom: 1px solid #e3e6eb">
			&nbsp;&nbsp; 日期范围&nbsp;&nbsp;从&nbsp;&nbsp;
	   	    <input id="mindatebox" type="text" class="easyui-datebox" style="width:90px;height:19px;" editable="false"></input>	
	     	  	&nbsp;至&nbsp;&nbsp;
	     	<input id="maxdatebox" type="text" class="easyui-datebox" style="width:90px;height:19px;" editable="false"></input>   	
			&nbsp;&nbsp;&nbsp;&nbsp;提交者
	   		<input id="creater" class="easyui-combobox" data-options="valueField:'id',textField:'text',panelHeight:'auto',
	   			url:'${pageContext.request.contextPath}/tblAttachmentIndexAction_loadAllCreater.action'" style="width:70px;height:19px;"/>
	   		&nbsp;&nbsp;&nbsp;&nbsp;状态
	   		<select id="state" class="easyui-combobox" name="state" data-options="panelHeight:'auto'" style="width:70px;height:19px;">   
			    <option value="-1">全部</option>   
			    <option value="0">未打印</option>   
			    <option value="1">已打印</option>   
			    <option value="2">已撤销</option>   
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
	   		<span style="position:absolute; top:5px;">
	   			<input id="searchbox" ></input> 
	   		</span>  
		</div>
	</div>
	<div id="toolbar">
   	    <a class = "easyui-linkbutton" plain="true" onclick="onAddButton();"
   	   	     data-options="iconCls:'icon-fileAdd'" ${write == 'false' ? 'disabled':'' }>增加</a>
   	    <a id="cancelbutton" class = "easyui-linkbutton" plain="true" disabled="disabled" onclick="onCancel();"
   	    data-options="iconCls:'icon-fileDel'">撤销</a>
	</div>
	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	<%@include file="/WEB-INF/jsp/tblAttachmentIndexAction/attachmentAdd.jspf" %>
</div>
</body>
</html>




