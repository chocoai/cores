<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES日程管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<script type="text/javascript">

    $(function(){
		//document.onkeydown = KeyDown;
        });//匿名函数结束
      

    
	function bodyOnLoad() {
		var userprivilege = document.getElementsByName("userprivilege")[0].value;
		var result = userprivilege.split(",");
		for ( var i = 0; i < result.length; i++) {
			if (result[i] == 1) {
				//权限控制显示
				document.getElementById("dict1").style.display = "";
			} else if (result[i] == 2) {
				document.getElementById("dict2").style.display = "";
			} else if (result[i] == "2-1") {//日程管理-分配-资源负责人
				document.getElementById("li1").style.display = "";
				//$('#li1').css('display',''); 
			} else if (result[i] == "2-2") {//日程管理-分配-专题任务分配
				//$('#li4').css('display',''); 
				document.getElementById("li4").style.display = "";
			} else if (result[i] == "2-3") {//日程管理-分配-专题资源分配
				//$('#li2').css('display',''); 
				document.getElementById("li2").style.display = "";
			} else if (result[i] == "2-4") {//日程管理-常规任务分配-委托管理
				//$('#li3').css('display',''); 
				document.getElementById("li3").style.display = "";
			}
		}

		//document.getElementById('showSchedule').click();
		window.open(sybp()+'/tblSchedulePlanAction_list.action','main');
		//href="${pageContext.request.contextPath}/tblSchedulePlanAction_list.action"
	}
	
	function sidebardisplayNone(){
	   var tableWidth  = parent.parent.document.body.clientWidth;
	   document.getElementById("sidebarbox").style.display = "none";
	   //$('#sidebarbox').css('display','none');   
	   document.getElementById("mainbody2-1").style.width = tableWidth;
	   document.getElementById("mainbody2-1").style.height = "98%";
	   document.getElementById("mainbody2-1").style.top = '5px';
	   document.getElementById("mainbody2-1").style.left = '5px';
	   document.getElementById("iframe").style.width = tableWidth;
	   document.getElementById("iframe").style.height = "98%";
	   document.getElementById("container").style.width = tableWidth;
	   document.getElementById("container").style.height = "98%";
	}
	
	function sidebardisplayshow(){
	   var tableWidth  = parent.parent.document.body.clientWidth;
	   document.getElementById("sidebarbox").style.display = "";
	   //$('#sidebarbox').css('display','');   
	   document.getElementById("mainbody2-1").style.width = tableWidth-190;
	   document.getElementById("mainbody2-1").style.height ="99.2%";
	   document.getElementById("mainbody2-1").style.top = '0px';
	   document.getElementById("mainbody2-1").style.left = '185px';
	   document.getElementById("iframe").style.width = tableWidth-190;
	   document.getElementById("iframe").style.height = "99.2%";
	   document.getElementById("container1").style.width = tableWidth;
	   document.getElementById("container1").style.height = "99.2%";
	}
</script>	  
</head>
<body onload="bodyOnLoad();">
<!-- 权限 -->
<input  type="hidden" id="userprivilege" name="userprivilege" value="${userprivilege}"/>
<div class="container" id="container1">
	<div class="sidebar_box2" id="sidebarbox" style="display:'';">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td width="182" height="20" valign="top">
				<div id="container" >
				 <div id="dict1" style="display:none;" >	
				    <h1 class="type"><a href="javascript:void(0)">查看日程<span></span></a></h1>
			        <div class="content">
			           <ul class="MM">
						   <li><a id="showSchedule" target="main" class="current" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblSchedulePlanAction_list.action">查看日程</a></li>
						</ul>  
			          </div>
					</div>
				   <div id="dict2" style="display:none;" >	
				    <h1 class="type"><a href="javascript:void(0)">工作安排<span></span></a></h1>
			        <div class="content">
			           <ul class="MM">
						   <li id="li1" style="display:none;"><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblResManagerAction_list.action">动物房负责人设置</a></li>
						   <li id="li3" style="display:none;"><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblTaskLeaderAction_list.action">常规任务分配</a></li>
						   <li id="li2" style="display:none;"><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblStudyResAction_list.action">专题房间分配</a></li>
						   <li id="li4" style="display:none;"><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblSOLeaderAction_list.action">专题任务分配</a></li>
						</ul>  
			          </div>
					</div>
			    </div>  
			       
			    <script type="text/javascript">
						//var contents = document.getElementsByClassName('content');
						//var toggles = document.getElementsByClassName('type');
						//var myAccordion = new fx.Accordion(
						//toggles, contents, {opacity: true, duration: 400}
						//);
						//var dict2 = document.getElementById("dict1").style.display;
						//myAccordion.showThisHideOpen(contents[0]);
						 
				</script>
			    </td>
			  </tr>
			</table>
	</div>
    <div class="mainbody2" id="mainbody2-1" >
        <iframe id="iframe" name="main" src="${pageContext.request.contextPath}/homeAction_main.action" width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>
    </div>
</div>
</body>
</html>




