<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/main.css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<script type="text/javascript">
	$(function(){
		//显示整个布局
		$('#layoutMainDiv').css('display','');
		//document.onkeydown = KeyDown;
      
			 
        });//匿名函数结束
      
  
</script>
</head>
 <body >
 	<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;">   
	 	<div region="center" title="" style="overflow: hidden;">
	 		<div id="mainTabs" class="easyui-tabs" fit="true" border="false"></div>
		</div>
 	</div>
</body>
</html>
