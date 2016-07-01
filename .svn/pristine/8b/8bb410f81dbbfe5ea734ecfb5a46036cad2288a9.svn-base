<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/studyNo.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script type="text/javascript">

	var studyNoTable;
	$(function(){
		studyNoTable = $('#studyNoTable').datagrid({
			url:sybp()+'/tblStudyPlanAction_loadStudyNoList.action',  
			singleSelect:true, 
			height: 300,
			width:160, 
		    columns:[[    
		        {field:'tiName',title:'tiName',width:100,hidden:true},    
		        {field:'sponsorName',title:'sponsorName',width:100,hidden:true},    
		        {field:'studyName',title:'studyName',width:100,hidden:true},    
		        {field:'studyCode',title:'专题编号',width:150,align:'center'}    
		    ]],
		    onClickRow:function(rowIndex, rowData){
		    	//设置专题编号
		    	$('#newStudyNo').val(rowData.studyCode);
		    	$('#sponsorName').html(rowData.sponsorName);
		    	$('#tiName').html(rowData.tiName);
		    	$('#studyName').html(rowData.studyName);
		    	document.all.newStudyNo.focus();
		    }
		});
	});
</script>
</head>
<body >
<%@include file="/WEB-INF/jsp/studyPlanAction/studyPlanAddEdit.jspf" %>
	<!-- 工具栏（签字）开始 -->
		<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
			<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-ok'" onclick="studyNoCheck();">确定</a>
		</div>
	<!-- 工具栏结束 -->

	<!--显示表单内容-->
<div style="float:left; width:300px;height:400px;">
	<table id="studyNoTable"></table>
    <s:form id="studyPlanAddForm" action="tblStudyPlanAction_studyPlanAdd">
        <!-- 表单内容显示 -->
            <div >
                <table cellpadding="2" cellspacing="0" class="" >
                	<tr>
                        <td width="300">
                        	<s:textfield id="newStudyNo" name="newStudyNo" cssClass="easyui-validatebox"  data-options="required:true"  missingMessage="请选择编号" readonly="true"  ></s:textfield>
                        </td>
                    </tr>
                </table>
            </div>
    </s:form>
</div>
<div style="float:left;width:300px;height:400px;">
	<table class="studyNo">
		<tr>
			<td>委托方</td>
			<td width="200px;">&nbsp;<span id="sponsorName"></span></td>
		</tr>
		<tr>
			<td>供试品名称</td>
			<td>&nbsp;<span id="tiName"></span></td>
		</tr>
		<tr>
			<td>项目名称</td>
			<td>&nbsp;<span id="studyName"></span></td>
		</tr>
	</table>

</div>
</body>
</html>
