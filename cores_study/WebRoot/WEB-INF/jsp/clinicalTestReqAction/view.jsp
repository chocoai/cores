<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/clinicalTest.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/clinicalTestApply.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js"></script>

</head>
<body>
<div class="content">
<!-- 工具栏（签字）开始 -->
	<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
		<a  class="easyui-linkbutton" plain="true"  onclick="outPort3();" data-options="iconCls:'icon-print'">打印预览</a>
		<a  class="easyui-linkbutton" plain="true"  href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara=${studyNoPara}" data-options="iconCls:'icon-back'">返回</a>
		<span id="errorMsg" style="color:red"></span>
	</div>
<!--显示表单内容-->
<s:hidden name="tblClinicalTestReq.id"></s:hidden>
<s:hidden id="reqNo" name="tblClinicalTestReq.reqNo"></s:hidden>
<s:hidden id="studyNo" name="studyNoPara"></s:hidden>
    <!-- 表单内容显示 -->
        <div class="edit_table">
            <table cellpadding="5" cellspacing="0" class=""  width="100%">
                <tr height="40px" align="left">
                	<td width="40%">专题编号：<s:property value="studyNoPara"/></td>
                    <td width="20%">动物种属：<s:property value="tblClinicalTestReq.tblStudyPlan.animalType"/></td>
                    <td>专题类型：<s:if test="tblClinicalTestReq.tblStudyPlan.isGLP == 1">GLP研究</s:if><s:else>非GLP研究</s:else> </td>
                </tr>
                <tr height="40px"><td width="15%">实验阶段:
                    ${tblClinicalTestReq.testPhase}
                    </td>
                <td colspan="2" width="15%">计划检查日期:
                		<s:textfield id="beginDate" name="tblClinicalTestReq.beginDate"  readonly="true"> 
							<s:param name="value" ><s:date name="tblClinicalTestReq.beginDate" format="yyyy-MM-dd" /></s:param>
						</s:textfield>
                		～
                		<s:textfield id="endDate" name="tblClinicalTestReq.endDate" readonly="true"> 
								<s:param name="value" ><s:date name="tblClinicalTestReq.endDate" format="yyyy-MM-dd" /></s:param> 
						</s:textfield>
                </td>
                </tr>
            </table>
            <table cellpadding="5" cellspacing="0" class=""  width="100%">
            	<tr bgcolor="#ddfdfe">
            		<td width="17%">血液检验</td>
            		<td width="17%">血凝检验</td>
            		<td width="17%">生化检验</td>
            		<td width="17%">尿液检验</td>
            		<td >动物编号</td>
            	</tr>
            	<tr>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#hematList" var="obj">
            					<input name="dictHematList" value="${obj.name}" type="checkbox" class="dictHematList"
            					checked="checked"	 onclick="return false;"
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            				<!-- 
            				<s:iterator value="dictHematList" var="s">
            					<input name="dictHematList" value="${#s}" type="checkbox" 
            					 checked="checked"	 onclick="return false;"/>
            					${s}<br/>
            				</s:iterator>
            				 -->
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#bloodCoagList" var="obj">
            					<input name="dictBloodCoagList" value="${obj.name}" type="checkbox" class="dictBloodCoagList" 
            					checked="checked"	 onclick="return false;"
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            				<!-- 
            				<s:iterator value="dictBloodCoagList" var="s">
            					<input name="dictHematList" value="${#s}" type="checkbox" 
            					 checked="checked"	 onclick="return false;"/>
            					${s}<br/>
            				</s:iterator>
            				 -->
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#bioChemList" var="obj">
            					<input name="dictBioChemList" value="${obj.name}" type="checkbox" class="dictBioChemList" 
            					checked="checked"	 onclick="return false;"
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            				<!-- 
            				<s:iterator value="#bioChemList" var="s">
            					<input name="dictHematList" value="${s.name}" type="checkbox" 
            					 checked="checked"	 onclick="return false;"/>
            					${s}<br/>
            				</s:iterator>
            				 -->
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#urineList" var="obj">
            					<input name="dictUrineList" value="${obj.name}" type="checkbox" class="dictUrineList" 
            					checked="checked"	 onclick="return false;"
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            				<!-- 
            				<s:iterator value="dictUrineList" var="s">
            					<input name="dictHematList" value="${#s}" type="checkbox" 
            					 checked="checked"	 onclick="return false;"/>
            					${s}<br/>
            				</s:iterator>
            				 -->
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            			<table cellpadding="5" cellspacing="0"  width="100%" style="border-left:1px solid #e3e6eb;">
            				<tr><td width="26px"><a >全选</a></td><td width="50px">动物编号</td><td>性别</td><td>动物ID号</td></tr>
            				<s:iterator value="tblClinicalTestReqIndex2List" var="obj">
            				<tr>
            					<td>
            						<input type= "checkbox" id="animalId_${status.index}" name="animalIds" value="${obj.id}" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
					          		checked="checked"/>
            					</td>
            					<td><s:property value="#obj.animalCode"/></td>
            					<td><s:property value="%{#obj.gender==1?'雄':'雌'}"/></td>
            					<td><s:property value="#obj.animalId"/></td>
            				</tr>
            				</s:iterator>
            			</table>
            			</div>
            		</td>
            	</tr>
            </table>
            <table cellpadding="5" cellspacing="0" class=""  width="100%">
            	<tr><td width="20%">其他检查项目</td>
                    <td><s:textfield name="tblClinicalTestReq.testOther" cssStyle="width:100%" readonly="true"/></td>
                </tr>
                <tr><td width="20%">备注</td>
                    <td><s:textfield name="tblClinicalTestReq.remark" cssStyle="width:100%" readonly="true"/></td>
                </tr>
            </table>
        </div>

</div>

</body>
</html>
