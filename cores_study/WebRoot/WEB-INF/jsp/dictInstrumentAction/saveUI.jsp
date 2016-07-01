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
    <!-- 以下三行是日期选择器 -->
<link href="${pageContext.request.contextPath}/script/myCalendar/winter.css"  rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/myCalendar/calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/myCalendar/calendar-setup.js"></script>

<script type="text/javascript">
    function checkName()
    {
        var submit=document.getElementById('submit');
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/dictInstrumentAction_checkInStrumentId.action",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType:"html",
            data:"instrumentId="+$("#instrumentId").val(),
            beforeSend:function(XMLHttpRequest)
                {
                    
                },
            success:function(msg)
                {
          	     if(msg=="is")
          	     {
          	    	 $("#span1").html("设备Id已存在！");
          	    	 $("#span1").css("color","red");
          	    	 submit.disabled=true;
          	    }
          	    if(msg=="no")
          	     {
          	    	 $("#span1").text(""); 
          	    	 submit.disabled=false;
          	     }
                },
            complete:function(XMLHttpRequest,textStatus)
                {
                },
            error:function()
               {
                     $("#span1").html("与服务器交互错误!");
               }
            });
        
    }
   </script>

<%@ include file="/script/validator.jsp"%>
</head>
<body>
<!--page tab-->
<div class="page_tab">
      <ul>
          <li class="active"><span><span><a href="#">设备登记表</a></span></span></li>
      </ul>
</div>

<!--显示表单内容-->
<div id=content>
<!--toolbar-->
    <div class="toolbar">
    		<ul>
    		<li class="first"><a id="submit" onclick="formSubmit()" <s:if test="%{#oldName ==null}">disabled="disabled"</s:if>  href="#"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />保存</a></li>
          <li class="last"><a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/icon_delete.png" />取消</a></li>
          </ul>
    </div>
    <s:form action="dictInstrumentAction_%{#oldId == null ? 'add' : 'edit'}">
        <s:hidden name="oldId" value="oldId"></s:hidden>
        <font color="red"><s:fielderror/></font>
        
        <!-- 表单内容显示 -->
        <div class="">
                <table cellpadding="0" cellspacing="0" class="" border="1">
                    <tr><td width="100">设备Id</td>
                        <td>
                          	<s:textfield id="instrumentId"  readonly="%{#oldId != null ? true :false}" name="instrumentId"  
                          	    onblur="javascript:%{#oldId ==null ?  'checkName();':''}" cssClass="InputStyle required {maxlength:50}"/>                    
                         	 <span id="span1"></span> *
                        </td>
                    </tr>
                    <tr><td>设备名称</td>
                        <td><s:textfield name="instrumentName" cssClass="InputStyle required {maxlength:50}"/> *</td>
                    </tr>
                    <tr><td>类别（检测项目）</td>
                        <td><s:select list="#{1:'生化',2:'血常规',3:'血凝',4:'尿常规'}" name="instrumentType" headerKey="instrumentType"  cssClass="InputStyle"></s:select> *</td>
                    </tr>
                    <tr><td>生产厂家</td>
                        <td><s:textfield name="manufacturer" cssClass="InputStyle required {maxlength:50}"/> *</td>
                    </tr>
                    <tr><td>型号</td>
                        <td><s:textfield name="modelNumber" cssClass="InputStyle required {maxlength:50}"/> *</td>
                    </tr>
                    <tr><td>生产日期</td>
                        <td><s:textfield id="createDateString" name="createDateString" value="%{#createDateString}" readonly="true" cssClass="InputStyle required"/> 
                        <img src="${pageContext.request.contextPath}/script/myCalendar/calendar.gif" id="img10" name="img10" border="0"
								style="cursor: hand" alt="选择日期" />
							<script type="text/javascript">  
                                Calendar.setup({  
                                button   : "img10", 
                                showsTime    : false,
                                inputField    : "createDateString",   
                                ifFormat    : "%Y-%m-%d" 
                                });   
                    		 </script>
                        *</td>
                    </tr>
                    <tr><td>负责人</td>
                        <td><s:textfield name="director" cssClass="InputStyle required  {maxlength:50}"/> *</td>
                    </tr>
                </table>
        </div>
    </s:form>
</div>

</body>
</html>
