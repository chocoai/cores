<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>动物实验管理系统</title>
<!--  
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
-->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/jquery-1.8.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_leftStudyPlan.css"  />

</head>
<body>
<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
<s:hidden id="left_member" name="left_member"></s:hidden>
<s:hidden id="isValidationPara" name="isValidationPara"></s:hidden>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="182" valign="top">
	<div id="container">
		<!-- 
		<h1 class="type"><a href="javascript:void(0)">试验计划<span></span></a></h1>
		 -->
        <div class="content">
		        <div class="left_select">
		         <!-- 
                  <select name="select" id="select">
                    <option>2005</option>
                  </select>-->
                  <s:select id="yearList" list="#yearList" value="%{year}"  onchange="showthisyear(this);"> </s:select>
                  
                  <a>&nbsp;&nbsp;年</a>
                </div><!-- height:435px; -->
               	<div style=" position:absolute;top:38px;bottom:22px; border: 1px solid #c8c8c8; overflow-y: scroll;">
          			 <ul class="MM"  id="ul1">
						 <s:iterator value="#tblStudyPlanList" var="tblStudyPlan" status="status" >
	            			<li>
	                    		<a id="${tblStudyPlan.studydirector}" name="${tblStudyPlan.studyNo}" target="main" class="${tblStudyPlan.studyNo ==studyNoPara ? 'current':'' }" 
	                    		onclick="javascript:indexSelect(this);" href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara=${tblStudyPlan.studyNo}">${tblStudyPlan.studyNo }</a>
	                			<input id="a${tblStudyPlan.studyNo}" type="hidden" value="${tblStudyPlan.studydirector}"/>
	                		</li>
            			</s:iterator>
            			
            			
					</ul>
					
					<ul class="MM"  id="ul2">
					   <s:iterator value="#membertblStudyPlanList" var="membertblStudyPlan" status="status" >
	            			<li >
	                    		<a id="${membertblStudyPlan.studydirector}" style="color:#0000EE" name="${membertblStudyPlan.studyNo}" target="main" class="${membertblStudyPlan.studyNo == studyNoPara ? 'newcurrent':'' }" 
	                    		onclick="javascript:indexSelect1(this);" href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara=${membertblStudyPlan.studyNo}&member=realonly">${membertblStudyPlan.studyNo }</a>
	                			<input id="a${membertblStudyPlan.studyNo}" type="hidden" value="${membertblStudyPlan.studydirector}"/>
	                		</li>
            			</s:iterator>
					</ul>
				</div>
			  <div style=" position:absolute;bottom:2px; height:20px;">
				<input id="checkbox" type="checkbox" ${isValidationPara == 1 ?'checked':''}  onclick="onCheckbox();"/><a style="text-decoration:none;"  href="javascript:onCheckbox();">显示验证试验</a>
              </div>
		</div>
    </div>  
    </td>
  </tr>
</table>
</body>
</html>
