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
		if($('#testIndexAddForm').form('validate')){
			$('#testIndexAddForm').submit();
		}
	}
	function onExitButton(){
		window.location.href=sybp()+'/dictUrineAction_list.action';
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">尿液指标-编辑</a></span></span></li>
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
			<form id="testIndexAddForm" action="dictUrineAction_edit.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
				<tr>
					<td width="150px;" height="30px;" align="right">名称</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="name" value="${name}" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">缩写</td>
					<td><input  style="width:150px;"  missingMessage="必填项" invalidMessage="缩写已存在" 
						class="easyui-validatebox" required="true"  value="${abbr}"
						validType="remote[sybp()+'/dictUrineAction_checkOtherAbbr.action?oldAbbr=${abbr}','abbr']" 
						name="abbr" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">精度</td>
					<td><input  style="width:150px;"    class="easyui-numberbox" validType="nonnegativeInteger" 
					 value="${precision}" name="precision" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">单位</td>
					<td>
					<input class="easyui-combobox" name="unit"  value="${unit}"  style="width:100px;height:19px;" data-options="valueField:'id',textField:'text',
						url:'${pageContext.request.contextPath}/dictDoseUnitAction_loadComboboxList.action',
						onSelect: function(record){    
						    if(record.id == -1){
						    	$(this).combobox('unselect',record.id);
						    }
						},panelHeight:'auto'" /> 
					</td>
				</tr>
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>