<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES日程管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>
<script type="text/javascript">
    var tableHeight ; 
     $(function(){
    tableHeight = document.body.clientHeight;
		//$('#accordion').accordion({
		//	height:tableHeight-10,
		//	onSelect:function(title,index){
		//	}
		//}); 
		//选择1
		//$('#accordion').accordion('select',0);
	   // document.getElementById('appointSD').click();
      });

    
</script>	  
</head>
<body >
<div class="container" id="container1" style="width:99.6%;" >
    <!-- 
	<div class="sidebar_box2" id="sidebarbox" style="display:'';">
	    <div id="accordion" class="easyui-accordion" style="width:181px;">   
    	 <div title="任命SD" data-options="selected:false" style="overflow:hidden;">
    	  <div class="content">
    	    <ul class="MM">
			    <li id="li1" ><a id = "appointSD" target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblAppointSDAction_appointSD.action">FM任命SD</a></li>
			 </ul>  
		 </div>
    	</div>   
      </div>
	</div> -->
    <div class="mainbody2" style="width:100%;left:0;" >
        <iframe id="appointSDiframe" name="appointSDmain" src="${pageContext.request.contextPath}/tblAppointSDAction_appointSD.action" width=99.6% height=100% frameborder=no border=0 scrolling=no ></iframe>
    </div>
</div>
</body>
</html>




