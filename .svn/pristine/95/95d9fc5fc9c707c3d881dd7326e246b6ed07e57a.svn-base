<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
		$(function(){
			//在被 嵌套时，就刷新上级窗口
			if(window.parent!=window){
				window.parent.location.reload(true);
				}
		});
	</script>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="cache-control" content="no-store">
        <meta http-equiv="expires" content="0">
        <%response.setHeader("Pragma","No-cache");          
        response.setHeader("Cache-Control","no-cache");   
        response.setHeader("Cache-Control", "no-store");   
        response.setDateHeader("Expires",0);  
 %>
  </head>
   
  <body>
    <%
    response.setHeader("Pragma","No-cache");          
    response.setHeader("Cache-Control","no-cache");   
    response.setHeader("Cache-Control", "no-store");   
    response.setDateHeader("Expires",0);
    if(session!=null)
        session.invalidate();
    %>
    <script type="text/javascript">window.parent.parent.location.href="/cores_auserprivilege/index.jsp";</script>
  </body>
</html>
