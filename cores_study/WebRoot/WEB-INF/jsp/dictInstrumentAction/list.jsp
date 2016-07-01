<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
</head>
<body>
<!--page tab-->
           <div class="page_tab">
                <ul>
                    <li class="active"><span><span><a href="#">设备登记表</a></span></span></li>
                </ul>
          </div>
        <!--content-->
        <div class="content">
          <!--toolbar-->
          <div class="toolbar">
          		<ul>
          		<li class="first"><a href="dictInstrumentAction_addUI.action"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />新增</a></li>
                <li class="last"><a href="#"><img src="${pageContext.request.contextPath}/style/images/icon_delete.png" />删除</a></li>
                </ul>
          </div>
          <!--table-->
          <div class="dict_table" style="overflow-x: auto; overflow-y: auto; height: 80%">
       		<table width="100%">
                  <tr>
                    <th width="10px">No.</th>
                    <th width="60px">设备ID</th>
					<th width="100px">设备名称</th>
					<th width="100px">类别（检测项目）</th>
					<th width="100px">生产厂家</th>
					<th width="100px">生型号</th>
					<th width="80px">生产日期</th>
					<th width="60px">负责人</th>
					<th>相关操作</th>
                  </tr>
                  	<s:iterator value="#objList" status="status">
						<tr>
						<td>${status.index+1 }</td>
							<td>
							${instrumentId}&nbsp;
							</td>
							<td>${instrumentName}&nbsp;</td>
							
							<td>
							<s:if test="%{instrumentType == 1}">生化</s:if>
							<s:elseif test="%{instrumentType == 2}">血常规</s:elseif>
							<s:elseif test="%{instrumentType == 3}">血凝</s:elseif>
							<s:elseif test="%{instrumentType == 4}">尿常规</s:elseif>
							<s:else>其他</s:else>
							&nbsp;
							</td>
							
							<td>${manufacturer}&nbsp;</td>
							<td>${modelNumber}&nbsp;</td>
							<td><fmt:formatDate value="${createDate}" pattern="yyyy-M-d"/> &nbsp;</td>
							<td width="100px"> ${director}&nbsp;</td>
							<td><s:a onclick="return window.confirm('您确定要删除吗？')" href="dictInstrumentAction_delete.action?instrumentId=%{instrumentId}">删除</s:a>
								<s:a href="dictInstrumentAction_editUI.action?instrumentId=%{instrumentId}">修改</s:a>
								<s:a href="dictInstrumentAction_comParamUI.action?instrumentId=%{instrumentId}">接口参数</s:a>
							</td>
						</tr>
					</s:iterator>
                </table>
          </div>
          <table width="100%">
          	<tr>
               <td>
               <div class="page">
	            	<div class="totle">共 <span>${fn:length(objList)}</span> 条记录</div>
	                <div class="clear"></div>
            	</div>
               	</td>
             </tr>
          </table>
        </div>
</body>
</html>
