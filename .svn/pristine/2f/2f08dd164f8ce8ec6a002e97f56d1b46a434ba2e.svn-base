<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/studyplan.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$(".datebox :text").attr("readonly","readonly");
	});
</script>
</head>
<body>

<!--显示表单内容-->
<div>
    <s:form id="addStudyPlanForm" action="tblStudyPlanAction_addSave">
        <!-- 表单内容显示 -->
        <div class="content">
        	<a id="gotoStudyPlanMainA" href="" target="main"></a>
        	<a id="gotoLeftStudyPlanA" href="" target="left"></a>
		<!-- 工具栏（签字）开始 -->
			<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
				<a id="addStudyPlanButton" class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-ok'"  target="left" onclick="addSaveCheck(this)">保存</a>
				<a class="easyui-linkbutton" data-options="plain:'true',iconCls:'icon-ok'" href="${pageContext.request.contextPath}/tblStudyPlanAction_studyNo.action">返回</a>
			</div>
            <div class="edit_table">
                <table  class="studyplan" style="top:50px;">
                    <tr><td width="100px;">专题编号</td>
                        <td width="200px;">
                        	<s:textfield id="studyNo" name="tblStudyPlan.studyNo"  cssClass="InputStyle required" readonly="true"></s:textfield>
                        	&nbsp;&nbsp;<span id="span1" style=" color:red;"></span>
                        </td>
                    </tr>
                    <tr><td>专题类别</td>
                        <td>
                        	<s:select id="studyType"  cssStyle="width:100px;" list="studyTypeMap" listKey="key" listValue="value" name="studyTypeSel" headerKey="" headerValue="" cssClass="InputStyle required" onclick="emptySpan(this)"></s:select>
						    &nbsp;&nbsp;<span id="span2" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>专题负责人</td>
                        <td>
							<s:textfield id="studydirector" name="tblStudyPlan.studydirector" value="%{#user.realName}" readonly="true" cssClass="InputStyle required" onclick="emptySpan(this)"></s:textfield>
						    &nbsp;&nbsp;<span id="span3" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>是否GLP</td>
                        <td>
                        	<s:select id="isGLP" cssStyle="width:100px;" list="#{0:'否',1:'是'}" listKey="key" listValue="value" name="tblStudyPlan.isGLP" cssClass="InputStyle required" onclick="emptySpan(this)"></s:select>
						    &nbsp;&nbsp;<span id="span4" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>动物种类</td>
                        <td>
                            <!-- 
							<s:textfield id="animalType" name="tblStudyPlan.animalType" cssClass="InputStyle required"></s:textfield>
							 -->
							 <s:select id="animalType" cssStyle="width:100px;" name="tblStudyPlan.animalType" list="#dictAnimalTypeList" listKey="typeName" listValue="typeName"  
							 
							    onchange="showStrain(this.value);" headerKey="" headerValue="----" onclick="emptySpan(this)"></s:select>
							 &nbsp;&nbsp;<span id="span5" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>动物品系</td>
                        <td>
                            <!--
							 <s:textfield id="animalStrain" name="tblStudyPlan.animalStrain" cssClass="InputStyle required"></s:textfield>
							 -->
							 <s:select list="{}" cssStyle="width:100px;" id="animalStrain" name="tblStudyPlan.animalStrain" headerKey="" headerValue="----" onclick="emptySpan(this)"></s:select>
						     &nbsp;&nbsp;<span id="span6" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>试验启动日期</td>
                        <td>
							<s:textfield id="studyStartDate" name="tblStudyPlan.studyStartDate" readonly="true" cssClass="InputStyle easyui-datebox"  onchange="emptySpan(this)"/> 
                    		 &nbsp;&nbsp;<span id="span7" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>动物引入日期</td>
                        <td>
							<s:textfield id="animalImportDate" name="tblStudyPlan.animalImportDate"  cssClass="InputStyle easyui-datebox"  onchange="emptySpan(this)"/> 
                    		 &nbsp;&nbsp;<span id="span8" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>预试验日期</td>
                        <td>
							<s:textfield id="preStudyDate" name="tblStudyPlan.preStudyDate"  cssClass="InputStyle easyui-datebox"  onchange="emptySpan(this)"/> 
                    		 &nbsp;&nbsp;<span id="span9" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>正式试验时期</td>
                        <td>
							<s:textfield id="studyBeginDate" name="tblStudyPlan.studyBeginDate" cssClass="InputStyle easyui-datebox" onchange="emptySpan(this)"/> 
                    		 &nbsp;&nbsp;<span id="span10" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>剂量单位</td>
                        <td>
							<s:textfield id="dosageUnit" name="tblStudyPlan.dosageUnit" cssClass="InputStyle required" onclick="emptySpan(this)"></s:textfield>
							&nbsp;&nbsp;<span id="span11" style=" color:red;"></span> 
						</td>
                    </tr>
                    <tr><td>QA负责人</td>
                        <td>
                        <!--  
							<s:textfield id="qa" name="tblStudyPlan.qa" cssClass="InputStyle required"></s:textfield>
						-->
						<s:select id="qa"  cssStyle="width:100px;" name="tblStudyPlan.qa" list="#qaList" listKey="realName" listValue="realName" headerKey="" headerValue="" onclick="emptySpan(this)"></s:select>
						&nbsp;&nbsp;<span id="span12" style=" color:red;"></span> 
						</td>
                    </tr>
                    <tr><td>病理负责人</td>
                        <td>
                        <!--  
							<s:textfield id="pathDirector" name="tblStudyPlan.pathDirector" cssClass="InputStyle required"></s:textfield>
						-->
						<s:select id="pathDirector"  cssStyle="width:100px;" name="tblStudyPlan.pathDirector" list="#pathList" listKey="realName" listValue="realName" headerKey="" headerValue="" onclick="emptySpan(this)"></s:select>
						&nbsp;&nbsp;<span id="span13" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>临检负责人</td>
                        <td>
                        <!--  
							<s:textfield id="clinicalTestDirector" name="tblStudyPlan.clinicalTestDirector" cssClass="InputStyle required"></s:textfield>
						-->
						<s:select id="clinicalTestDirector"  cssStyle="width:100px;" name="tblStudyPlan.clinicalTestDirector" list="#clinicalTestList" listKey="realName" listValue="realName" headerKey="" headerValue="" onclick="emptySpan(this)"></s:select>
						&nbsp;&nbsp;<span id="span14" style=" color:red;"></span>
						</td>
                    </tr>
                </table>
            </div>
        </div>
    </s:form>
</div>


</body>
</html>
