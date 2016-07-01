<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES SOP管理系统</title>
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
    			
    				if(title =="SOP资料"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: 'SOP资料',
			    				content: '<iframe id="tblFileContentSOPFrame" src="${pageContext.request.contextPath}/tblFileContentSOPAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    				
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
 	 	   
			<div title="SOP资料"   border="false" style="overflow: auto;">
			 SOP资料
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




