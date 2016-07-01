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
       var studyPeriod = $('#studyPeriod').val();
       if(studyPeriod == 0){
         $('#studyPeriod').val("");
       }
    });
	function onSaveButton(){
		if($('#testIndexAddForm').form('validate')){
			$('#testIndexAddForm').submit();
		}
	}
	function onExitButton(){
		window.location.href=sybp()+'/dictStudyTypeAction_list.action';
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">专题类别-编辑</a></span></span></li>
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
			<form id="testIndexAddForm" action="dictStudyTypeAction_edit.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
				<tr>
					<td width="150px;" height="30px;" align="right">供试品类型</td>
					<td>
					<input class="easyui-combobox" name="tiCode"   style="width:100px;height:19px;" data-options="valueField:'id',textField:'text',
						url:'${pageContext.request.contextPath}/dictTestItemTypeAction_loadComboboxList.action',
						panelHeight:'auto'"  value="${tiCode}" required="true" /> 
					</td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">专题类别编码</td>
					<td><input  style="width:150px;" missingMessage="必填项" 
						class="easyui-validatebox" required="true" readonly="readonly"
						name="studyTypeCode"  value="${studyTypeCode}" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">试验名称</td>
					<td><input  style="width:150px;"  missingMessage="必填项" invalidMessage="专题名称已存在" 
						class="easyui-validatebox" required="true" 
						name="studyName" value="${studyName}" type="text"/>
					<!-- 
						validType="remote[sybp()+'/dictStudyTypeAction_checkStudyNameCode.action?studyTypeCode=${studyTypeCode}','studyName']" 
					
					 -->
					
					</td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">专题名称是否包含动物</td>
					<td>
						<select class="easyui-combobox" data-options="panelHeight:'auto'" name="animalHave" style="width:80px;" >
							<option value="0" ${animalHave == '0' ? 'selected':''}>否</option>   
							<option value="1" ${animalHave == '1' ? 'selected':''}>是</option>   
						</select>
					</td>
				</tr>
				<tr>
				<td width="150px;" height="30px;" align="right">专题代号</td>
				<td><!--  <input  style="width:150px;" invalidMessage="专题代号已存在" 
						class="easyui-validatebox"  
						validType="remote[sybp()+'/dictStudyTypeAction_checkStudyCodeEdit.action?studyTypeCode=${studyTypeCode}','studyCode']" 
						name="studyCode" type="text" value="${studyCode}"/>-->
						<input  style="width:150px;" 
						class="easyui-validatebox"  
						name="studyCode" type="text" value="${studyCode}" />
				</td>
			    </tr>
				
				<tr>
					<td width="150px;" height="30px;" align="right">专题周期</td>
					<td><input  style="width:150px;"    class="easyui-numberbox" validType="signlessInteger"  id ="studyPeriod"
					name="studyPeriod" type="text"  value="${studyPeriod}"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">专题周期单位</td>
					<td>
						<select class="easyui-combobox"  data-options="panelHeight:'auto'" name="studyPeriodUnit" style="width:100px;height:19px;" >
							<option value="天" ${studyPeriodUnit == '天' ? 'selected':''} >天</option>   
							<option value="周" ${studyPeriodUnit == '周' ? 'selected':''} >周</option>   
							<option value="月" ${studyPeriodUnit == '月' ? 'selected':''} >月</option>   
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