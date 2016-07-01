<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES系统管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/dictStudyTypeTestIndex.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/dictStudyTypeTestIndex.js" charset="utf-8"></script>
</head>
<body>
<!-- 标题显示 --> 
<!--page tab-->
<div class="page_tab">
      <ul>
          <li class="active"><span><span><a href="#">缺省检验指标</a></span></span></li>
      </ul>
</div>
<!--toolbar-->
<!--显示表单内容-->
<div id=content>
<div class="toolbar">
    <a class="easyui-linkbutton" plain="true" onclick="editSave();" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" plain="true" href="${pageContext.request.contextPath}/dictStudyTypeAction_list.action?studyTypeCode=${studyTypeCode}" data-options="iconCls:'icon-back'">返回</a>
    <span id="span1" style="color:red;"></span>
</div>
    <s:form action="dictStudyTestIndexAction_save">
       <s:hidden name="studyTypeCode"></s:hidden>
		<!-- 表单内容显示 -->
        <div class="testIndexPlan_table" style="margin-top: 0px;">
	        <table cellpadding="5" cellspacing="0" class="" border="1" width="100%">
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
            			<div style="height:440px; overflow-y: auto">
            				<s:iterator value="#dictHematListSel" var="obj">
            					<input id="2${obj.abbr}" name="dictHematList" class="dictHematList" value="${obj.name}" type="checkbox" 
            						<s:iterator value="dictHematList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="selectCheckbox(2,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td valign="top">
            			<div style="height: 440px; overflow-y: auto">
            				<s:iterator value="#dictBloodCoagListSel" var="obj">
            					<input id="3${obj.abbr}" name="dictBloodCoagList" class="dictBloodCoagList" value="${obj.name}" type="checkbox" 
            						<s:iterator value="dictBloodCoagList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="selectCheckbox(3,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td valign="top">
            			<div style="height:440px; overflow-y: auto">
            				<s:iterator value="#dictBioChemListSel" var="obj">
            					<input id="1${obj.abbr}" name="dictBioChemList" class="dictBioChemList" value="${obj.name}" type="checkbox" 
            						<s:iterator value="dictBioChemList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="selectCheckbox(1,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td valign="top">
            			<div style="height: 440px;overflow-y: auto">
            				<s:iterator value="#dictUrineListSel" var="obj">
            					<input id="4${obj.abbr}" name="dictUrineList" class="dictUrineList" value="${obj.name}" type="checkbox" 
            						<s:iterator value="dictUrineList" var="s">            
					          			<s:if test="#obj.name==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					<a id="${obj.abbr}" href="javascript:void(0);" onclick="selectCheckbox(4,this);" >${obj.abbr}(${obj.name})</a>
            				<br/>
            				</s:iterator>
            			</div>
            		</td>
            	</tr>
            </table>
	    </div>
    </s:form>
</div>
</body>
</html>

