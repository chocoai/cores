<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	function onSaveButton(){
		if($('#specimenAddForm').form('validate')){
			$('#specimenAddForm').submit();
		}
	}
	function onExitButton(){
		window.location.href=sybp()+'/dictSpecimenAction_list.action';
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">标本类型-添加</a></span></span></li>
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
			<form id="specimenAddForm" action="dictSpecimenAction_add.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
				<tr>
					<td width="150px;" height="30px;" align="right">标本类型</td>
					<td><input  style="width:150px;" missingMessage="必填项" invalidMessage="名称已存在" 
						class="easyui-validatebox" required="true" 
						validType="remote[sybp()+'/dictSpecimenAction_checkspecKind.action','specKind']" 
						name="specKind" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">标本种类</td>
					<td> <select id="specType" class="easyui-combobox" style="width:120px;height:19px;" editable="false" panelHeight="80" name="specType" required="true" >
                        		<option value="1">血液</option>
                        		<option value="2">尿液</option>
                        		<option value="3">其他</option>
                        </select>
                    </td>
				</tr>
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>