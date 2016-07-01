<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
    $(function(){
	   var parentId = $('#parentId').val();
	   if(parentId != ""){
		 document.getElementById('parentnameTr').style.display = "";
      }
    });
	function onSaveButton(){
		if($('#animalHouseeditForm').form('validate')){
			$('#animalHouseeditForm').submit();
		}
	}
	function onExitButton(){
		window.location.href=sybp()+'/tblAnimalHouseAction_list.action?rid='+$('#rid').val();
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">动物房设置-编辑</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<!-- 工具栏（保存 返回）start -->
				<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
					<a id="saveButton" class="easyui-linkbutton" plain="true"  
						onclick="onSaveButton();" data-options="iconCls:'icon-save'">保存</a>
					<a id="editButton" class="easyui-linkbutton" plain="true" 
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
				</div>
				<!-- 工具栏结束 -->
			<form id="animalHouseeditForm" action="tblAnimalHouseAction_edit.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
				<tr style="display:none">
					<td width="150px;" height="30px;" align="right">类型</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="resKind" value="${resKind}" type="text"/></td>
				</tr>
				<tr style="display:none" >
					<td width="150px;" height="30px;" align="right">id</td>
					<td><input id = "rid" style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="id" value="${id}" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">类型</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="resKindName" value="${resKindName}" disabled="disabled" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">名称</td>
					<td>
					<input type="hidden" id="oldName" value="${resName}"/>
					<input  style="width:150px;"  missingMessage="必填项" invalidMessage="名称已存在" 
						class="easyui-validatebox" required="true"  value="${resName}"
						validType="remotett[sybp()+'/tblAnimalHouseAction_checkresName.action','resName','#oldName']" 
						name="resName" type="text"/></td>
				</tr>
				<tr id="parentnameTr" style="display:none">
					<td width="150px;" height="30px;" align="right">父级名称</td>
					<td>
					 <input id="parentname1" class="easyui-validatebox"  type="text" name="parentname1" value="${parentname}" class="validatebox"  disabled="disabled" readonly="readonly" />					 
					</td>
				</tr>
				<!--  -->
				<tr style="display:none">
					<td width="150px;" height="30px;" align="right">PID</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" 
						name="parentId" id ="parentId" value="${parentId}" type="text"/></td>
				</tr>
				
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>