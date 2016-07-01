<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>动物实验管理系统</title>
<!-- 
 -->
 <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/jquery-1.8.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<script src="${pageContext.request.contextPath}/script/accordion/prototype.lite.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/accordion/moo.fx.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/accordion/moo.fx.pack.js" type="text/javascript"></script>
  <script type="text/javascript">
  function myfunction(){
	  var userprivilege = document.getElementsByName("userprivilege")[0].value;
	  var result = userprivilege.split(",");
	  for(var i=0;i<result.length;i++){
	     if(result[i] == 1 ){
          //权限控制显示
    	  document.getElementById("dict1").style.display="";
         }else if(result[i] == 2){
    	  document.getElementById("dict2").style.display="";
         }else if(result[i] == 3){
    	  document.getElementById("dict3").style.display="";
         }    
	  }
	 
       
     
  }
  </script>
</head>
<body  onload="myfunction()" >
<!-- 权限 -->
<input  type="hidden" id="userprivilege" name="userprivilege" value="${userprivilege}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="182" height="20" valign="top">
	<div id="container" >
      <!-- 临检指标 -->
      <div id="dict1" style="display:none;" >
      <h1 class="type"><a href="javascript:void(0)">临检指标<span></span></a></h1>
        <div class="content">
           <ul class="MM">
				<li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictBioChemAction_list.action">生化指标</a></li>
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictUrineAction_list.action">尿液指标</a></li>
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictHematAction_list.action">血液指标</a></li>
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictBloodCoagAction_list.action">血凝指标</a></li>
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictSpecimenAction_list.action">标本类型</a></li>
			</ul>
		</div>
	  </div>
	  <div id="dict2" style="display:none;" >	
      <h1 class="type"><a href="javascript:void(0)">试验相关<span></span></a></h1>
        <div class="content">
           <ul class="MM">
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictTestItemTypeAction_list.action">供试品类型</a></li>
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictStudyTypeAction_list.action">专题类别</a></li>
                <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictDoseUnitAction_list.action">剂量单位</a></li>
			</ul>
		</div>
	 </div>
	   <div id="dict3" style="display:none;" >	
	    <h1 class="type"><a href="javascript:void(0)">资源分配<span></span></a></h1>
        <div class="content">
           <ul class="MM">
				<li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblAnimalHouseAction_list.action">动物房设置</a></li>
			</ul>  
          </div>
		</div>
    </div>  
       
    <script type="text/javascript">
			var contents = document.getElementsByClassName('content');
			var toggles = document.getElementsByClassName('type');
	
			var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
			);
			  var dict1 = document.getElementById("dict1").style.display;
			  var dict2 = document.getElementById("dict2").style.display;
			  var userprivilege = document.getElementsByName("userprivilege")[0].value;
			  var result = userprivilege.split(",");
			  for(var i=0;i<result.length;i++){
			     if(result[i] == 1 ){
		          //权限控制显示
			    	 myAccordion.showThisHideOpen(contents[0]);
                     break;
				   }else if(result[i] == 2){
		        	 myAccordion.showThisHideOpen(contents[1]);
		        	 break;
		       }   
			  }
		
		 
	</script>
    </td>
  </tr>
</table>
</body>
</html>
