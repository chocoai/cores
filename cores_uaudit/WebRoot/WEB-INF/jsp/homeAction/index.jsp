<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES系统审计信息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/index.js" charset="utf-8"></script>

<script type="text/javascript">
	$(function(){
		//初始化 easyui-tabs
		initTabs();
	});
	//初始化 easyui-tabs
	function initTabs(){
		var num_tab0 = 0;
		var num_tab1 = 0;
		$('#indexTabsDiv').tabs({
			onSelect:function(title,index){
    			if(title == "审计信息"){
    				if(num_tab1 == 0){
	    				var tab1=$(this).tabs('getTab',index);
	    				$(this).tabs('update', {
			    			tab: tab1,
			    			options: {
			    				title: '审计信息',
			    				content: '<iframe  src="${pageContext.request.contextPath}/studyAction_study.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    			}
			    		});
	    				num_tab1 = 1;
        			}
    			}else if(title =='系统日志'){
        			if(num_tab0 == 0){
	    				var tab0=$(this).tabs('getTab',index);
	    				$(this).tabs('update', {
			    			tab: tab0,
			    			options: {
			    				title: '系统日志',
			    				content: '<iframe  src="${pageContext.request.contextPath}/tblLogAction_loglist.action?systemNameIndex=1" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    			}
			    		});
	    				num_tab0 = 1;
            		}
	    		}
		    }
		    		
    	});
	}
</script>
	  
</head>

<body >
	<div class="container" id="container" >
    	<div class="header_box" id="headerbox" style="display:'';">
    		<input id="userName" type="hidden" value="${user.userName}"></input>
        	<!-- top 开始 -->
           	<%@ include file="/WEB-INF/jsp/homeAction/top.jspf"%>
        	<!-- top 结束 -->
        </div>
        <div class="mainbody_2" >
			<div id="indexTabsDiv" class="easyui-tabs" fit="true" border="false" style="height:100%;width:100%;">
				<div title="系统日志" border="false" style="overflow: hidden;">
				  系统日志
				</div>
				<div title="审计信息" border="false" style="overflow: hidden;">
				  审计信息
				</div>
		 	</div>
		</div>
	   	<%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
 	</div>
</body>
</html>




