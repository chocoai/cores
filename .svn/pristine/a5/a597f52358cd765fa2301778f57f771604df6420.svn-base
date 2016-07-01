<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES综合管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script type="text/javascript" 
src="${pageContext.request.contextPath}/script/javascript/tblAttachmentIndexAction/view.js"></script> 
</head>
<body >
<div style="padding:5px;height:87%;overflow-x:auto;">
	<s:iterator value="resultList" id = "result">
		<div style="background-color: #f5f7fa;minHeight:90px;border:1px solid #cddee6;padding:10px;">
			<table border="0">
				<tr>
					<td width="48px;" style="vertical-align:top;line-height: 25px;">文件名：</td><td>
								<a style="cursor:pointer;color: blue;font-size: 15px;font-weight: bold;" onclick="downloadFile('${result.id}');" 
								>${result.fileName}</a>
					</td>
				</tr>
				<tr height="25px;">
					<td colspan="2">提交者：${result.creater}&nbsp;&nbsp;&nbsp;&nbsp;提交时间：${result.createTime}
					&nbsp;&nbsp;&nbsp;&nbsp;打印份数&nbsp;${result.printNum}&nbsp;份</td>
				</tr>
				<s:if test="#result.state == 1">
					<tr height="25px;">
						<td colspan="2">打印者：${result.printer}&nbsp;&nbsp;&nbsp;&nbsp;打印时间：${result.finishTime}</td>
					</tr>
				</s:if>
				<tr height="25px;">
					<td colspan="2">相关操作：
						<s:if test="#result.state == 2">
							<a disabled="disabled">打开</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<a style="cursor:pointer;color: blue;" onclick="downloadFile('${result.id}');" >打开</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</s:else>
						<s:if test="#result.state == 0 && #session.printAll == true ">
							<a style="cursor:pointer;color: blue;" onclick="signHasPrintOne('${result.id}');" >标记已打印</a>
						</s:if>
					</td>
				</tr>
			</table>
		</div>
		<div style="height:5px;"></div>
	</s:iterator>
</div>
<div style="background-color: #eeffbb;height:6%;border:1px solid #cddee6;padding:5px;margin:10px 5px 5px 5px;">
	<a style="font-weight:bold;">备注：</a>${remark}
</div>
</body>
</html>




