<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
    <!-- 以下三行是日期选择器 -->
<link href="${pageContext.request.contextPath}/script/myCalendar/winter.css"  rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/myCalendar/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/myCalendar/calendar-setup.js"></script>
</head>
<body>
<!--page tab-->
<div class="page_tab">
      <ul>
          <li class="active"><span><span><a href="#">试验计划-编辑</a><a href="#" class="close_tab"></a></span></span></li>
      </ul>
</div>

<s:form action="tblStudyPlanAction_editSave">
<font color="red"><span id="span1"></span> </font>
       <div class="content">
        <!--toolbar-->
          <div class="toolbar">
          		<ul>
          		<li class="first"><a href="#" onclick="addSaveCheck(this)"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />保存</a></li>
          		<li class="last"><a href="javascript:history.go(-1);" ><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />取消</a></li>
                </ul>
                <font color="red"><span id="span1"></span></font>
          </div>
            <div class="edit_table">
                <table cellpadding="2" cellspacing="0" class="" border="1">
                    <tr><td width="300">专题编号</td>
                        <td width="300">
                        	<s:textfield id="studyNo" name="tblStudyPlan.studyNo" cssClass="InputStyle required" readonly="true"></s:textfield>
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
							<s:textfield id="studydirector" name="tblStudyPlan.studydirector" readonly="true" cssClass="InputStyle required" onclick="emptySpan(this)"></s:textfield>
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
							 
							    onchange="showStrain(this.value);" headerKey="%{tblStudyPlan.animalType}" headerValue="%{tblStudyPlan.animalType}" onclick="emptySpan(this)"></s:select>
							 &nbsp;&nbsp;<span id="span5" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>动物品系</td>
                        <td>
                            <!--
							 <s:textfield id="animalStrain" name="tblStudyPlan.animalStrain" cssClass="InputStyle required"></s:textfield>
							 -->
							 <s:select id="animalStrain" name="tblStudyPlan.animalStrain"  list="#dictAnimalStrainList" cssStyle="width:100px;" listKey="strainName" listValue="strainName" headerKey="%{tblStudyPlan.animalStrain}" headerValue="%{tblStudyPlan.animalStrain}" onclick="emptySpan(this)"></s:select>
						     &nbsp;&nbsp;<span id="span6" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>试验启动日期</td>
                        <td>
							<s:textfield id="studyStartDate" name="tblStudyPlan.studyStartDate" readonly="true" cssClass="InputStyle required"  onchange="emptySpan(this)">
							<s:param name="value"><s:date name="tblStudyPlan.studyStartDate" format="yyyy-MM-dd"/></s:param> 
							</s:textfield> 
                       	 	<script type="text/javascript">  
                                Calendar.setup({  
                                button : "studyStartDate", 
                                showsTime : false,
                                inputField : "studyStartDate",   
                                ifFormat : "%Y-%m-%d" 
                                });   
                    		 </script>
                    		 &nbsp;&nbsp;<span id="span7" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>动物引入日期</td>
                        <td>
							<s:textfield id="animalImportDate" name="tblStudyPlan.animalImportDate" readonly="true" cssClass="InputStyle required" onchange="emptySpan(this)">
							<s:param name="value"><s:date name="tblStudyPlan.animalImportDate" format="yyyy-MM-dd"/></s:param> 
							</s:textfield> 
							<script type="text/javascript">  
                                Calendar.setup({  
                                button : "animalImportDate", 
                                showsTime : false,
                                inputField : "animalImportDate",   
                                ifFormat : "%Y-%m-%d" 
                                });   
                    		 </script>
                    		 &nbsp;&nbsp;<span id="span8" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>预试验日期</td>
                        <td>
							<s:textfield id="preStudyDate" name="tblStudyPlan.preStudyDate" readonly="true" cssClass="InputStyle required" onchange="emptySpan(this)">
							<s:param name="value"><s:date name="tblStudyPlan.preStudyDate" format="yyyy-MM-dd"/></s:param> 
							</s:textfield>  
							<script type="text/javascript">  
                                Calendar.setup({  
                                button : "preStudyDate", 
                                showsTime : false,
                                inputField : "preStudyDate",   
                                ifFormat : "%Y-%m-%d" 
                                });   
                    		 </script>
                    		 &nbsp;&nbsp;<span id="span9" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>正式试验时期</td>
                        <td>
							<s:textfield id="studyBeginDate" name="tblStudyPlan.studyBeginDate" readonly="true" cssClass="InputStyle required" onchange="emptySpan(this)">
							<s:param name="value"><s:date name="tblStudyPlan.studyBeginDate" format="yyyy-MM-dd"/></s:param> 
							</s:textfield>  
							<script type="text/javascript">  
                                Calendar.setup({  
                                button : "studyBeginDate", 
                                showsTime : false,
                                inputField : "studyBeginDate",   
                                ifFormat : "%Y-%m-%d" 
                                });   
                    		 </script>
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
						<s:select id="qa"  cssStyle="width:100px;" name="tblStudyPlan.qa" list="#qaList" listKey="realName" listValue="realName" headerKey="%{tblStudyPlan.qa}" headerValue="%{tblStudyPlan.qa}" onclick="emptySpan(this)"></s:select>
						&nbsp;&nbsp;<span id="span12" style=" color:red;"></span> 
						</td>
                    </tr>
                    <tr><td>病理负责人</td>
                        <td>
                        <!--  
							<s:textfield id="pathDirector" name="tblStudyPlan.pathDirector" cssClass="InputStyle required"></s:textfield>
						-->
						<s:select id="pathDirector"  cssStyle="width:100px;" name="tblStudyPlan.pathDirector" list="#pathList" listKey="realName" listValue="realName" headerKey="%{tblStudyPlan.pathDirector}" headerValue="%{tblStudyPlan.pathDirector}" onclick="emptySpan(this)"></s:select>
						&nbsp;&nbsp;<span id="span13" style=" color:red;"></span>
						</td>
                    </tr>
                    <tr><td>临检负责人</td>
                        <td>
                        <!--  
							<s:textfield id="clinicalTestDirector" name="tblStudyPlan.clinicalTestDirector" cssClass="InputStyle required"></s:textfield>
						-->
						<s:select id="clinicalTestDirector"  cssStyle="width:100px;" name="tblStudyPlan.clinicalTestDirector" list="#clinicalTestList" listKey="realName" listValue="realName" headerKey="%{tblStudyPlan.clinicalTestDirector}" headerValue="%{tblStudyPlan.clinicalTestDirector}" onclick="emptySpan(this)"></s:select>
						&nbsp;&nbsp;<span id="span14" style=" color:red;"></span>
						</td>
                    </tr>
                </table>
            </div>
        </div>
    </s:form>
</body>
</html>
