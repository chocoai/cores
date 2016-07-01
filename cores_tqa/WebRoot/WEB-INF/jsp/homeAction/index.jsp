<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES QA管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>

<!-- 任命QA相关的 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/appointQA.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js"></script>

<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>


	  <script type="text/javascript" charset="utf-8">

	  
	  
    	$(function(){
		   var pointerInInput = false ;
		    //tabs 开始
		   var indexTabs;
		   var tableHeight = document.documentElement.clientHeight-68;
			var tableWidth  = document.body.clientWidth-3;
	    	
    		indexTabs=$('#indexTabsDiv');
    		indexTabs.tabs({
    			height:tableHeight,
    			width:tableWidth,
    			onSelect:function(title,index){
	    			if(title == "待办事宜"){
	    			var tab0=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab0,
			    			options: {
			    				title: '待办事宜',
			    				content: '<iframe id="qAChkPlanPlanFrame" src="${pageContext.request.contextPath}/qAChkPlanAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="基于研究的检查"){
						var tab1=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab1,
			    			options: {
			    				title: '基于研究的检查',
			    				content: '<iframe id="qAChkPlanMainFrame" src="${pageContext.request.contextPath}/qAChkPlanAction_qAChkPlanMain.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="检查报告"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '检查报告',
			    				content: '<iframe src="${pageContext.request.contextPath}/qAChkReportAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="文件管理"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '文件管理',
			    				content: '<iframe src="${pageContext.request.contextPath}/qAFileRegAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}
			    }
			    		
        	});
        	
        //	document.onkeydown = KeyDown;
          
		//	showIndexTabsDiv();
		//	$('#mainbody2').css('display','');
			 
        });//匿名函数结束
      
      function showIndexTabsDiv(){
            for (i=0;i<4 ;i++ ){ 
			    var tab_option = $('#indexTabsDiv').tabs('getTab',i).panel('options').tab;  
	             tab_option.hide();  
            } 
         
                

      } 
	
	  function tableHeight(){
	     var tableHeight = document.body.clientHeight;
		 return tableHeight;
	  }
	  function tableWidth(){
	     var tableWidth  = document.body.clientWidth;
	     return tableWidth;
	  }
	  //全屏
	    function makeFullScreen() {
	    var tableHeight = window.screen.height;
		var tableWidth  = window.screen.width;
        divObj = document.getElementById("mainbody2");
        document.getElementById("mainbody2").style.top = '-32px';
        document.getElementById("headerbox").style.display = "none";
        $("#mainbody2").css("height",tableHeight+32);
        $("#mainbody2").css("width",tableWidth);
        $('#indexTabsDiv').tabs("resize",{ height:tableHeight,width:tableWidth}); 
        if (divObj.requestFullscreen) {
          divObj.requestFullscreen();
        }
        else if (divObj.msRequestFullscreen) {
          divObj.msRequestFullscreen();
        }
        else if (divObj.mozRequestFullScreen) {
          divObj.mozRequestFullScreen();
        }
        else if (divObj.webkitRequestFullscreen) {
          divObj.webkitRequestFullscreen();
        }
      }
	   
</script>
	  
</head>

<body >

	<div class="container" id="container" >
    	<div class="header_box" id="headerbox" style="display:'';">
    		<input id="userName" type="hidden" value="${user.userName}"></input>
    		<input id="pos" type="hidden"></input>
        <!-- top 开始 -->
           <%@ include file="/WEB-INF/jsp/homeAction/top.jspf"%>
        <!-- top 结束 -->
        </div>
       
        <div class="mainbody3" id="mainbody2" style="display:block; position:absolute;top:64px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:hidden;" >
 	 	 <div id="indexTabsDiv" style="border: 1px solid #c8c8c8;" class="easyui-tabs" border="false" >
 	 	    <div title="待办事宜"  border="false" style="overflow: auto;">
			待办事宜
			</div>
			<div title="基于研究的检查"  border="false" style="overflow: auto;">
			 基于研究的检查
			</div>
			<div title="检查报告"   border="false" style="overflow: auto;">
			  检查报告
			</div>
			<div title="文件管理"   border="false" style="overflow: auto;">
			  文件管理
			</div>
		 </div>
 	 </div>
       
    <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
    <%@ include file="/WEB-INF/jsp/QAChkPlanAction/selectQA.jspf"%>
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
    </div>
</body>
</html>




