<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES系统审计信息_临检</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$('#indexTabsDiv').tabs({
			onSelect:function(title,index){
    			if(title == "专题审计信息"){
    				var tab1=$(this).tabs('getTab',index);
    				$(this).tabs('update', {
		    			tab: tab1,
		    			options: {
		    				title: '专题审计信息',
		    				content: '<iframe  src="${pageContext.request.contextPath}/pathAction_path.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
		    			}
		    		});
    			}else if(title =='系统日志'){
    				var tab0=$(this).tabs('getTab',index);
    				$(this).tabs('update', {
		    			tab: tab0,
		    			options: {
		    				title: '系统日志',
		    				content: '<iframe  src="${pageContext.request.contextPath}/tblLogAction_loglist.action?systemNameIndex=2" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
		    			}
		    		});
	    		}
		    }
		    		
    	});
	});
</script>
	  
</head>

<body >
	<div class="container" id="container" >
    	<div id="indexTabsDiv" class="easyui-tabs" fit="true" border="false" style="height:100%;width:100%;">
			<div title="系统日志" border="false" style="overflow: hidden;">
			  系统日志
			</div>
			<div title="专题审计信息" border="false" style="overflow: hidden;">
			  文件打印1
			</div>
		 </div>
 	</div>
</body>
</html>




