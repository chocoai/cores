<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES 档案管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>



<script type="text/javascript">
	  
		$(function(){
		   var tableHeight = document.body.clientHeight-2;
		   var tableWidth = document.body.clientWidth-2;
	    	var indexTabs =$('#researchCheckTabs');
    		indexTabs.tabs({
    			height:tableHeight,
    			width:tableWidth,
    			onSelect:function(title,index){
    			
    				if(title == "专题档案"){
		    			
	    				var tab0=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab0,
			    			options: {
			    				title: '专题档案',
			    				content: '<iframe id="tblFileContentStudyFrame" src="${pageContext.request.contextPath}/tblFileContentStudyAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="QA检查资料"){
						var tab1=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab1,
			    			options: {
			    				title: 'QA检查资料',
			    				content: '<iframe id="tblFileContentQAFrame" src="${pageContext.request.contextPath}/tblFileContentQACheckAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="SOP资料"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: 'SOP资料',
			    				content: '<iframe id="tblFileContentSOPFrame" src="${pageContext.request.contextPath}/tblFileContentSOPAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="综合资料"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '综合资料',
			    				content: '<iframe id="tblFileContentGlpSynthesisFrame" src="${pageContext.request.contextPath}/tblFileContentGlpSynthesisAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="仪器资料"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '仪器资料',
			    				content: '<iframe id="tblFileContentInstrumentFrame" src="${pageContext.request.contextPath}/tblFileContentInstrumentAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}else if(title =="人员档案"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '人员档案',
			    				content: '<iframe id="tblFileContentEmployeeFrame" src="${pageContext.request.contextPath}/tblFileContentEmployeeAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="行政综合"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '行政综合',
			    				content: '<iframe id="tblFileContentAdministrationFrame" src="${pageContext.request.contextPath}/tblFileContentAdministrationAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="合同资料"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '合同资料',
			    				content: '<iframe id="tblFileContentContractFrame" src="${pageContext.request.contextPath}/tblFileContentContractAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="留样管理"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '留样管理',
			    				content: '<iframe id="tblFileRecordSmplReserveFrame" src="${pageContext.request.contextPath}/tblFileRecordSmplReserveAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="标本管理"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '标本管理',
			    				content: '<iframe id="tblFileRecordSpecimenFrame" src="${pageContext.request.contextPath}/tblFileRecordSpecimenAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="基建资料"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '基建资料',
			    				content: '<iframe id="tblFileContentGlpSynthesis2Frame" src="${pageContext.request.contextPath}/tblFileContentGlpSynthesis2Action_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}else if(title =="操作日志"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '操作日志',
			    				content: '<iframe id="tblLog2Frame" src="${pageContext.request.contextPath}/tblLogAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
			    			}
			    		});
	    			}
	    			
	    			//其他按钮的显示
	    			parent.changOtherButton(index);
    				//设置left页面上的值
				    var contentWindLeft = parent.document.getElementById('archiveLeftFrame').contentWindow;
    				if(contentWindLeft.$)
    				{
    					var titleText=contentWindLeft.$('#indexText');
       		 			titleText.attr("value", index);
       		 			//让对应的condition显示
       		 			contentWindLeft.changeCondition(index);
    				}else{
    					parent.document.getElementById('archiveLeftFrame').onload=function()
					    {
    					    var contentWindLeft2 = parent.document.getElementById('archiveLeftFrame').contentWindow;
	    					var titleText=contentWindLeft2.$('#indexText');
	       		 			titleText.attr("value", index);
	       		 			contentWindLeft2.changeCondition(index);
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
	<div  class="">
       <div  style="display:block; position:absolute;top:0px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:hidden;" >
 	 	 <div id="researchCheckTabs" style="border: 1px solid #c8c8c8;" class="easyui-tabs" border="false" >
 	 	    <div title="专题档案"  border="false" style="overflow: auto;">
			专题档案
			</div>
			<div title="QA检查资料"  border="false" style="overflow: auto;">
			QA检查资料
			</div>
			<div title="SOP资料"   border="false" style="overflow: auto;">
			 SOP资料
			</div>
			<div title="综合资料"   border="false" style="overflow: auto;">
			 综合资料
			</div>
			<div title="仪器资料"   border="false" style="overflow: auto;">
			 仪器资料
			</div>
			<div title="人员档案"   border="false" style="overflow: auto;">
			 人员档案
			</div>
			<div title="行政综合"   border="false" style="overflow: auto;">
			 行政综合
			</div>
			<div title="合同资料"   border="false" style="overflow: auto;">
			合同资料
			</div>
			<div title="留样管理"   border="false" style="overflow: auto;">
			 留样管理
			</div>
			<div title="标本管理"   border="false" style="overflow: auto;">
			标本管理
			</div>
			<div title="基建资料"   border="false" style="overflow: auto;">
			 综合资料
			</div>
			<div title="操作日志"   border="false" style="overflow: auto;">
			 操作日志
			</div>
			
		 </div>
 	 </div>
       
   	<%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
    </div>
    
  
</body>
</html>




