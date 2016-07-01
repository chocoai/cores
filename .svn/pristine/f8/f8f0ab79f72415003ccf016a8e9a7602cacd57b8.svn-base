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
    	var specType = $('#specType').val();
        if(specType == 1){
        	$('#specType').val('血液');
        }else if(specType == 2){
        	$('#specType').val('尿液');
        }else if(specType == 3){
        	$('#specType').val('其他');
        }
        });
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
		          <li class="active"><span><span><a href="javascript:void(0);">标本类型-编辑</a></span></span></li>
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
			<form id="specimenAddForm" action="dictSpecimenAction_edit.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
			<tr style="display:none">
					<td width="150px;" height="30px;" align="right">ID</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="id" id ="id" value="${id}" type="text"/></td>
				</tr>
					<!-- 	validType="remote[sybp()+'/dictDoseUnitAction_checkOtherAbbr.action?oldAbbr=${specKind}','abbr']"  -->
				<tr>
					<td width="150px;" height="30px;" align="right">标本类型</td>
					<td><input  style="width:150px;"  missingMessage="必填项" invalidMessage="缩写已存在" 
						class="easyui-validatebox" required="true"  value="${specKind}"
						validType="remote[sybp()+'/dictSpecimenAction_checkOtherspecKind.action?oldKind=${specKind}','specKind']" 
						name="specKind" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">标本种类</td>
					<td>
					   <input id="specType" style="width:150px;" disabled="disabled" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="specType" value="${specType}" type="text"/>
					</td>
				</tr>
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>