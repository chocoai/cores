<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/dictionary.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<%@ include file="/script/validator.jsp"%>
</head>
<body>
<!--page tab-->
<div class="page_tab">
      <ul>
          <li class="active"><span><span><a href="#">设备接口参数</a></span></span></li>
      </ul>
</div>

<!--显示表单内容-->
<div id=content>
<!--toolbar-->
    <div class="toolbar">
    		<ul>
    		<li class="first"><a id="submit" onclick="formSubmit()" href="#"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />保存</a></li>
          <li class="last"><a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/icon_delete.png" />取消</a></li>
          </ul>
    </div>
    <s:form action="dictInstrumentAction_comParam">
        <font color="red"><s:fielderror/></font>
        
        <!-- 表单内容显示 -->
        <div class="">
                <table cellpadding="0" cellspacing="0" class="" border="1">
                    <tr><td width="100">设备Id</td>
                        <td>
                          	<s:textfield id="instrumentId"  readonly="true" name="instrumentId"  
                          	     cssClass="InputStyle required {maxlength:50}"/>                    
                         	  *
                        </td>
                    </tr>
                    <tr><td>设备名称</td>
                        <td><s:textfield name="instrumentName"  readonly="true" cssClass="InputStyle required {maxlength:50}"/> *</td>
                    </tr>
                    <tr><td>接入串口名称</td>
                        <td><s:textfield name="comPort" value="%{#comPort}" cssClass="InputStyle required {maxlength:50}"/> *</td>
                    </tr>
                    <tr><td>波特率</td>
                        <td><s:textfield name="baudRateString" value="%{#baudRateString}"  cssClass="InputStyle digits required {maxlength:50}"/> *</td>
                    </tr>
                    <tr><td>数据位</td>
                        <td><s:textfield name="dataBitString" value="%{#dataBitString}"  cssClass="InputStyle digits required  {maxlength:50,range:[0,9]}"/> *</td>
                    </tr>
                    <tr><td>停止位</td>
                        <td><s:textfield name="stopBitString" value="%{#stopBitString}"  cssClass="InputStyle digits required  {maxlength:50,range:[0,9]}"/> *</td>
                    </tr>
                    <tr><td>校验方式</td>
                        <td><s:textfield name="checkModeString" value="%{#checkModeString}"  cssClass="InputStyle digits required  {maxlength:50,range:[0,9]}"/> *</td>
                    </tr>
                </table>
        </div>
    </s:form>
</div>
</body>
</html>
