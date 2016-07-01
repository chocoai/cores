<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/maleAndfemaleNum.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimal.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
</head>
<body>
	    <s:hidden id="minMaleNum" name="minMaleNum"></s:hidden>
	    <s:hidden id="minFemaleNum" name="minFemaleNum"></s:hidden>
		<s:form id="tblAnimalForm" action="tblAnimalAction_numSave" method="post">
		<s:hidden name="studyNoPara"></s:hidden>
			<div class="content">
				<!-- 
				<div class="toolbar">
					<ul>
						<li class="first">
							<a href="javascript:animalNumSave();" ><img
									src="${pageContext.request.contextPath}/style/images/icon_add.png" />保存</a>
						</li>
					</ul>
				</div>
				 -->
				<!-- 工具栏 开始 -->
				<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
					<a  class="easyui-linkbutton" plain="true"  onclick="animalNumSave();" data-options="iconCls:'icon-ok'">确定</a>
				</div>
				<!-- 工具栏结束 -->
				<!--table-->
				<div class="animalNum_table">
					<table width="30%" >
						<tr>
							<td colspan="2" align="center"><a>动物数量</a></td>
						</tr>
						<tr>
							<td width="50px;">雄(>=${minMaleNum})</td>
							<td width="">
							<!-- 
								<s:textfield id="maleNum" name="maleNum" onclick="emptySpan(this)"></s:textfield>
							 -->
							<input id="maleNum" name="maleNum" class="easyui-numberbox" required="true" data-options="validType:'minNumber[${minMaleNum}]'"/>
							<span id="span1" style="color:red;width:30px;"></span></td>
						</tr>
						<tr>
							<td>雌(>=${minFemaleNum})</td>
							<td>
							<!-- 
								<s:textfield id="femaleNum" name="femaleNum" onclick="emptySpan(this)"></s:textfield>
							 -->
							<input id="femaleNum" name="femaleNum" class="easyui-numberbox" required="true" data-options="validType:'minNumber[${minFemaleNum}]'"/>
							<span id="span2" style="color:red;width:30px;"></span></td>
						</tr>
					</table>
				</div>
			</div>
		</s:form>
</body>
</html>
