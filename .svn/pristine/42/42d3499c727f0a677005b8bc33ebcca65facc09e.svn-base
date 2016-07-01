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
    		if($('#animalHouseAddForm').form('validate')){
    			$('#animalHouseAddForm').submit();
    		}
	}
	function onExitButton(){
	   var x = document.getElementById('parentId').value;
		window.location.href=sybp()+'/tblAnimalHouseAction_list.action?rid='+x;
	}

	
	
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">动物房设置-添加</a></span></span></li>
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
			<form id="animalHouseAddForm" action="tblAnimalHouseAction_add.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
				
				<tr>
					<td width="150px;" height="30px;" align="right">类型</td>
					<td> 
					<input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="resKindname" id ="resKindname" value="${resKindName}" type="text"/>
                    </td>
				</tr>
				<!-- style="display:none" -->
				<tr  style="display:none" >
					<td width="150px;" height="30px;" align="right">resKind</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="resKind" id ="resKind" value="${resKind1}" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">名称</td>
					<td><input  id="resName" style="width:150px;" missingMessage="必填项" invalidMessage="名称已存在" 
						class="easyui-validatebox" required="true" 
						validType="remote[sybp()+'/tblAnimalHouseAction_checkresName.action?parentId=${parentId}','resName']" 
						name="resName" type="text"/></td>
				</tr>
               	<tr id="parentnameTr" style="display:none">
					<td width="150px;" height="30px;" align="right">父级名称</td>
					<td>
					 <input id="parentname1" type="text" name="parentname1" value="${parentname}" class="validatebox"   readonly="true"/>
					</td>
				</tr>
				<tr style="display:none">
					<td width="150px;" height="30px;" align="right">PID</td>
					<td><input  style="width:150px;" 
						name="parentId" id ="parentId" value="${parentId}" /></td>
				</tr>			
				
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>