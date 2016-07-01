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

<%@ include file="/script/validator.jsp"%>
</head>
<body>
        <!-- 表单内容显示 -->
        <div class="content">
        <!--toolbar-->
        <a id="aclick" href="${pageContext.request.contextPath}/tblStudyPlanAction_sign.action?studyNoPara=${studyNoPara}" target="main"></a>
          <div class="toolbar" >
          		<ul style="display: ${tblStudyPlan.studyState==0?'true':'none' }">
          		<li class="first" ><a href="#" onclick="signCheck('${studyNoPara}')" ><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />签字</a></li>
                </ul>
                <font color="red"><span id="span1"></span></font>
          </div>
        
            <div class="view_table">
                <table cellpadding="2" cellspacing="0" class="" border="1">
                    <tr><td width="300">专题编号</td>
                        <td width="300">
                        	<s:property value="tblStudyPlan.studyNo"/>
                        </td>
                    </tr>
                    <tr><td>专题类别</td>
                        <td>
                        	<s:property value="tblStudyPlan.dictStudyType.studyName"/>
						</td>
                    </tr>
                    <tr><td>专题负责人</td>
                        <td>
							<s:property value="tblStudyPlan.studydirector"/>
						</td>
                    </tr>
                    <tr><td>是否GLP</td>
                        <td>
							<s:if test="tblStudyPlan.isGLP == 1">是</s:if>
							<s:else>否</s:else>
						</td>
                    </tr>
                    <tr><td>动物种类</td>
                        <td>
							<s:property value="tblStudyPlan.animalType"/>
						</td>
                    </tr>
                    <tr><td>动物品系</td>
                        <td>
							<s:property value="tblStudyPlan.animalStrain"/>
						</td>
                    </tr>
                    <tr><td>试验启动日期</td>
                        <td>
							<s:date name="tblStudyPlan.studyStartDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>试验分组日期</td>
                        <td>
							<s:date name="tblStudyPlan.studyGroupingDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>动物引入日期</td>
                        <td>
							<s:date name="tblStudyPlan.animalImportDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>预试验日期</td>
                        <td>
							<s:date name="tblStudyPlan.preStudyDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>正式试验时期</td>
                        <td>
							<s:date name="tblStudyPlan.studyBeginDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>剂量单位</td>
                        <td>
							<s:property value="tblStudyPlan.dosageUnit"/>
						</td>
                    </tr>
                    <tr><td>QA负责人</td>
                        <td>
							<s:property value="tblStudyPlan.qa"/>
						</td>
                    </tr>
                    <tr><td>病理负责人</td>
                        <td>
							<s:property value="tblStudyPlan.pathDirector"/>
						</td>
                    </tr>
                    <tr><td>临检负责人</td>
                        <td>
							<s:property value="tblStudyPlan.clinicalTestDirector"/>
						</td>
                    </tr>
                    <tr><td>试验状态</td>
                        <td>
							<s:property value="tblStudyPlan.studyState"/>
						</td>
                    </tr>
                </table>
            </div>
        </div>

<div id="signDiv" class="signDiv" style="display: none">
	<div class="signDivHead">电子签名</div>
	<div>
		<font color="red"><span id="span2"></span></font>
		<table class="SignTable">
			<tr>
				<td>姓名:</td>
				<td><s:property value="#user.realName"/>&nbsp;</td>
			</tr>
			<tr>
				<td>请输入密码：</td>
				<td><input type="password" id="password"/> </td>
			</tr>
		</table>
	</div>
	<div align="center">
		<input type="button" onclick="sign('${studyNoPara}')" value="确定"/>
		<input type="button" onclick="closeDiv('signDiv')" value="取消" />
	</div>
</div>
</body>
</html>
