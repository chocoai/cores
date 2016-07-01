<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>CoRES系统管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
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
     document.onkeydown = KeyDown; 
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
         }else if(result[i] == 4){
    	  	document.getElementById("dict4").style.display="";
         }else if(result[i] == 5){
    	  	document.getElementById("dict5").style.display="";
         }else if(result[i] == 6){
    	  	document.getElementById("dict6").style.display="";
         }else if(result[i] == 7){
    	  	document.getElementById("dict7").style.display="";
         }else if(result[i] == 8){
    	  	document.getElementById("dict8").style.display="";
    	 }else if(result[i] == "animalHome"){
    	  	document.getElementById("animalHome").style.display="";
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
	    <h1 class="type"><a href="javascript:void(0)">资源设置<span></span></a></h1>
        <div class="content">
           	<ul class="MM">
			   <li id = "animalHome" style="display:none;"><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblAnimalHouseAction_list.action">动物房设置</a></li>
			   <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblTaskTypeAction_list.action">常规任务设置</a></li>
			</ul>   
          </div>
		</div>
	   <div id="dict4" style="display:none;" >	
	    <h1 class="type"><a href="javascript:void(0)">毒性病理<span></span></a></h1>
        <div class="content">
           	<ul class="MM">
			   <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictVisceraAction_list.action">脏器字典</a></li>
			   <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictPathCommonAction_list.action">病理字典</a></li>
			   <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/tblSuperficialTumorVisceraAction_list.action">浅表肿瘤脏器</a></li>
			   <li><a target="main" onclick="changeState(this);" href="${pageContext.request.contextPath}/dictLevelAction_list.action">病变程度</a></li>
			</ul>   
          </div>
		</div>
	<div id="dict5" style="display:none;" >	
		<h1 class="type"><a href="javascript:void(0)">报表编号<span></span></a></h1>
		<div class="content">
			<ul class="MM">
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictReportNumberAction_list.action">
					报表编号设置</a></li>
			</ul>   
		</div>
	</div>
	<div id="dict6" style="display:none;" >	
		<h1 class="type"><a href="javascript:void(0)">QA检查设置<span></span></a></h1>
		<div class="content">
			<ul class="MM">
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictStudyGroupAction_list.action">
					试验分类设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictQACheckTableAction_list.action">
					检查表设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictQACheckItemAction_list.action">
					检查项设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictScheduleChkItemAction_list.action">
					检查项与日程</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictChkItemQAFileRegAction_list.action">
					检查项与检查依据</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictQAStatementTempleAction_list.action">
					QA声明模板</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictChkAreaAction_list.action">
					检查区域</a></li>
			</ul>   
		</div>
	</div>
	<div id="dict7" style="display:none;" >	
		<h1 class="type"><a href="javascript:void(0)">档案管理<span></span></a></h1>
		<div class="content">
			<ul class="MM">
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictArchiveTypeAction_list.action">
					档案分类设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictArchivePositionAction_list.action">
					档案位置设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictSOPTypeAction_list.action">
					SOP类别设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/dictAdministrationTypeAction_list.action">
					综合类别设置</a></li>
			</ul>   
		</div>
	</div>
	<div id="dict8" style="display:none;" >	
		<h1 class="type"><a href="javascript:void(0)">公司信息管理<span></span></a></h1>
		<div class="content">
			<ul class="MM">
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/companyInfoAction_companyInfo.action">
					公司信息设置</a></li>
				<li><a target="main" onclick="changeState(this);" 
					href="${pageContext.request.contextPath}/companyInfoAction_reportCodeInfo.action">
					报告编号设置</a></li>
				
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
		           }else if(result[i] == 3){
		        	 myAccordion.showThisHideOpen(contents[2]);
		        	 break;
		           }else if(result[i] == 4){
			          myAccordion.showThisHideOpen(contents[3]);
			          break;
			       }else if(result[i] == 5){
				      myAccordion.showThisHideOpen(contents[4]);
				      break;
				   }else if(result[i] == 6){
		        	   myAccordion.showThisHideOpen(contents[5]);
		        	   break;
		           }else if(result[i] == 7){
		        	   myAccordion.showThisHideOpen(contents[6]);
		        	   break;
		           }else if(result[i] == 8){
		        	   myAccordion.showThisHideOpen(contents[7]);
		        	   break;
		           }   
			  }
		
		 
	</script>
    </td>
  </tr>
</table>
</body>
</html>
