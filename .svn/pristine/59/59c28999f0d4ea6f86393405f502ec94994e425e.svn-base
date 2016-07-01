<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/testIndexPlan.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/testIndexPlan.js" charset="utf-8"></script>
<script type="text/javascript">
	var studyState;
	$(function(){
		document.getElementById("bodyDiv").style.display="block";
		studyState=$('#studyState').val();
		if(studyState ==1 || studyState ==2 ){
			$('#testIndexPlanSaveButton').linkbutton('disable');
		}else{
		    $('#testIndexPlanSaveButton').linkbutton('enable');
		}
		var member = $('#left_member').val();
		if(member!= ""){
		    $('#testIndexPlanSaveButton').linkbutton('disable');
		}
	});
</script>
</head>
<body>
<div id="bodyDiv" style="display:none;">
<s:hidden id="studyState" name="studyState"></s:hidden>
<s:form id="testIndexAction" action="tblTestIndexPlanAction_addSave">
<s:hidden id="left_member" name="left_member"></s:hidden>
<s:hidden name="studyNoPara"></s:hidden>
<div class=content>
	<!--toolbar
	<div class="toolbar" >
			<ul style="display: ${studyState==0?'true':'none' }">
			<li class="first"><a href="javascript:void(0);" onclick="editSave()" ><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />保存</a> </li>
	      </ul> <span id ="success" style="color:red;" >${success=='success'? '保存成功！':''}</span>
	      <font color="red"><span id="span1"></span></font>
	</div>-->
	<!-- 工具栏（保存）开始 -->
		<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
			<a id="testIndexPlanSaveButton" class="easyui-linkbutton" plain="true" onclick="editSave();" data-options="iconCls:'icon-save'">保存</a>
			<span id ="success" style="color:red;" ></span>
	     	<font color="red"><span id="span1"></span></font>
		</div>
	<!-- 工具栏结束 -->
	<div class="testIndexPlan_table">
	        <table cellpadding="1" cellspacing="0" class="" border="1" width="100%">
            	<tr bgcolor="#ddfdfe">
            		<td width="17%">血液检验
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictHematList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictHematList');" plain="true">清空</a>
            		</td>
            		<td width="17%">血凝检验
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictBloodCoagList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictBloodCoagList');" plain="true">清空</a>
            		</td>
            		<td width="17%">生化检验
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictBioChemList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictBioChemList');" plain="true">清空</a>
            		</td>
            		<td width="17%">尿液检验
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictUrineList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictUrineList');" plain="true">清空</a>
            		</td>
            	</tr>
            	<tr>
            		<td>
            			<div style="height:420px; overflow-y: auto">
            				<s:iterator value="#dictHematListSel" var="obj">
            					<input id="2${obj.abbr}" name="dictHematList" class="dictHematList" value="${obj.name}" type="checkbox" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}"
            						<s:iterator value="dictHematList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}selectCheckbox(2,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td valign="top">
            			<div style="height: 420px; overflow-y: auto">
            				<s:iterator value="#dictBloodCoagListSel" var="obj">
            					<input id="3${obj.abbr}" name="dictBloodCoagList" class="dictBloodCoagList" value="${obj.name}" type="checkbox" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}"
            						<s:iterator value="dictBloodCoagList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}selectCheckbox(3,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td valign="top">
            			<div style="height:420px; overflow-y: auto">
            				<s:iterator value="#dictBioChemListSel" var="obj">
            					<input id="1${obj.abbr}" name="dictBioChemList" class="dictBioChemList" value="${obj.name}" type="checkbox" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}"
            						<s:iterator value="dictBioChemList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}selectCheckbox(1,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td valign="top">
            			<div style="height: 420px;overflow-y: auto">
            				<s:iterator value="#dictUrineListSel" var="obj">
            					<input id="4${obj.abbr}" name="dictUrineList" class="dictUrineList" value="${obj.name}" type="checkbox" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}"
            						<s:iterator value="dictUrineList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="${(studyState == 0 || studyState == 3)?'':'return false'}selectCheckbox(4,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            	</tr>
            </table>
	    </div>
	</div>
</s:form>
</div>
</body>
</html>
