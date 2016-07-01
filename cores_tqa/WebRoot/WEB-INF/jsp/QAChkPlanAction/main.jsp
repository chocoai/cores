<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES基于研究的检查</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>



<script type="text/javascript">
	  
		var tableWidth  = parent.parent.$('#researchMain').css("width")+15;
    	$(function(){
		   var tableHeight = document.body.clientHeight-3;
	    	var indexTabs =$('#researchCheckTabs');
    		indexTabs.tabs({
    			height:tableHeight,
    			width:tableWidth,
    			onSelect:function(title,index){
    				
    				if(title == "方案/报告检查"){
		    			
	    				var tab0=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab0,
			    			options: {
			    				title: '方案/报告检查',
			    				content: '<iframe src="${pageContext.request.contextPath}/tblStudyFileIndexAction_list.action?studyNoParam='+$('#studyNoParam').val()+'&afterTabOpenAction='+$('#afterTabOpenActionForMain').val()+'" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="检查计划"){
						var tab1=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab1,
			    			options: {
			    				title: '检查计划',
			    				content: '<iframe src="${pageContext.request.contextPath}/qAChkPlanAction_list.action?studyNoParam='+$('#studyNoParam').val()+'" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="检查记录"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '检查记录',
			    				content: '<iframe src="${pageContext.request.contextPath}/qAChkRecordAction_list.action?studyNoParam='+$('#studyNoParam').val()+'&oneChkPlanId='+$('#oneChkPlanIdForMain').val()+'" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="检查结果"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '检查结果',
			    				content: '<iframe src="${pageContext.request.contextPath}/qAChkRecordAction_result.action?studyNoParam='+$('#studyNoParam').val()+'" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="检查报告"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '检查报告',
			    				content: '<iframe src="${pageContext.request.contextPath}/qAChkReportAction_list.action?studyNoParam='+$('#studyNoParam').val()+'&selectChkReportCode='+$('#selectChkReportCodeForMain').val()+'" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}
    				//设置left页面上的值
				    var contentWind = parent.document.getElementById('chkPlanLeft').contentWindow;
    				if(contentWind.$)
    				{
    					var titleText=contentWind.$('#indexText');
       		 			titleText.attr("value", index);
    				}else{
    					parent.document.getElementById('chkPlanLeft').onload=function()
					    {
	    					var titleText=contentWind.$('#indexText');
	       		 			titleText.attr("value", index);
	    				};
        			}
        				
			    }
			    		
        	});
        	if($('#indexTitle').val()!='')
        	{
            	//alert($('#indexTitle').val());
            	indexTabs.tabs('select',parseInt($('#indexTitle').val()));
        	}
			 
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
	 
</script>
	  
</head>

<body >
	<s:hidden id="studyNoParam" name="studyNoParam"></s:hidden>
	<s:hidden id="indexTitle" name="index"></s:hidden>
	<s:hidden id="fileIndexId" name="fileIndexId"></s:hidden>
	
	<s:hidden id="oneChkPlanIdForMain" name="oneChkPlanId"></s:hidden>
	<s:hidden id="selectChkReportCodeForMain" name="selectChkReportCode"></s:hidden>
	<s:hidden id="afterTabOpenActionForMain" name="afterTabOpenAction"></s:hidden>
	
    <!-- 子页面之间互相传参数 -->
    <s:hidden id="chkPlanIdChild"/>
	<div  >
       <div  style="display:block; position:absolute;top:0px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:auto;" >
 	 	 <div id="researchCheckTabs" style="border: 1px solid #c8c8c8;" class="easyui-tabs" border="false" >
 	 	    <div title="方案/报告检查"  border="false" style="overflow: auto;">
			方案/报告检查
			</div>
			<div title="检查计划"  border="false" style="overflow: auto;">
			 检查计划
			</div>
			<div title="检查记录"   border="false" style="overflow: auto;">
			  检查记录
			</div>
			<div title="检查结果"   border="false" style="overflow: auto;">
			  检查结果
			</div>
			<div title="检查报告"   border="false" style="overflow: auto;">
			  检查报告
			</div>
			
		 </div>
 	 </div>
       
    </div>
    
   <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>

</body>
</html>




