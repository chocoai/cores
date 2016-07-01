<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>日程安排</title>
    <%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/main.css" media="screen" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
   <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>

		<script type="text/javascript">
    	var schedulePlanTabs;
    	$(function(){
    		schedulePlanTabs=$('#schedulePlanTabs');
    		schedulePlanTabs.tabs({
    			onSelect:function(title,index){
    			 var title1 = $('#title').val();
    			if( title1 == '' ){
    			  var readers =  parent.parent.userprivilegeReaders();
    			  readers=readers.split(","); //字符分割 
    			 
    			  for (i=0;i<readers.length ;i++ ){ 
					   if(readers[i] == "1-1" ){
					    title = '委托管理';
					    break;
				       }else if(readers[i]  == "1-2"){
				        title = '动物试验';
				        break;
				       }else if(readers[i]  == "1-2"){
				       title = '临床检验';
				       break;
				       }else if(readers[i]  == "1-4"){
				       title = '毒性病理';
				       break;
				       }else if(readers[i]  == "1-5"){
				       title = 'QA管理';
				       break;
				       }else if(readers[i]  == "1-6"){
				       title = '供试品管理';
				       break;
				       }else if(readers[i]  == "1-7"){
				        title = '分析';
				       break;
				       }else if(readers[i]  == "1-8"){
				        title = '生态毒理';
				       break;
				       }else{
				         continue;
				       }              
			     } 
			  }
    			if(title == '委托管理'){
					var tab0=schedulePlanTabs.tabs('getTab',index);
					schedulePlanTabs.tabs('update', {
		    			tab: tab0,
		    			options: {
		    				title: '委托管理',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=1'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
		    			}
		    		});
		    		$('#title').val('委托管理');
    			}else if(title == '动物试验'){
    					var tab1=schedulePlanTabs.tabs('getTab',index);
    					schedulePlanTabs.tabs('update', {
    		    			tab: tab1,
    		    			options: {
    		    				title: '动物试验',
    		    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=2'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
    		    			}
    		    		});
    		    		$('#title').val('动物试验');
        			}else if(title ==  '临床检验'){
						var tab2=schedulePlanTabs.tabs('getTab', index);
						schedulePlanTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '临床检验',
			       				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=3'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
			    			}
			    		});
			    		$('#title').val('临床检验');
					}else if(title ==  '毒性病理'){
						var tab3=schedulePlanTabs.tabs('getTab',index);
						schedulePlanTabs.tabs('update', {
			    			tab: tab3,
			    			options: {
			    				title: '毒性病理',
			       				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=4'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
			    			}
			    		});
			    		$('#title').val('毒性病理');
					}else if(title == 'QA管理' ){
                       var tab4=schedulePlanTabs.tabs('getTab',index);
                       schedulePlanTabs.tabs('update', {
			    			tab: tab4,
			    			options: {
			    				title: 'QA管理',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=5'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
			    			}
			    		});
			    		$('#title').val('QA管理');
					}else if(title == '供试品管理' ){
                       var tab5=schedulePlanTabs.tabs('getTab',index);
                       schedulePlanTabs.tabs('update', {
			    			tab: tab5,
			    			options: {
			    				title: '供试品管理',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=6'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
			    			}
			    		});
			    		$('#title').val('供试品管理');
    			}else if(title == '分析' ){
                       var tab6=schedulePlanTabs.tabs('getTab',index);
                       schedulePlanTabs.tabs('update', {
			    			tab: tab6,
			    			options: {
			    				title: '分析',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=7'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
			    			}
			    		});
			    	  $('#title').val('分析');	
			    }else if(title == '生态毒理' ){
                       var tab6=schedulePlanTabs.tabs('getTab',index);
                       schedulePlanTabs.tabs('update', {
			    			tab: tab6,
			    			options: {
			    				title: '生态毒理',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_selectSchedulePan.action?taskKind=8'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
			    			}
			    		});
			    	  $('#title').val('生态毒理');	
			    }
			    }
			    		
        	});
        	
        	
			//显示整个布局
			$('#layoutMainDiv').css('display',''); 
			hiddenTopDiv();
			hiddenTheschedulePlanTabsidv();
			//noBackSpace();
          	//document.onkeydown = KeyDown;
      
			 
        });//匿名函数结束
      
 
       function hiddenTopDiv(){
          parent.parent.hiddenTopDiv();
       }
       
       function changeTheCtableHeight1(){
          $('#ctableHeight').val(448);
       }
       
        function changeTheCtableHeight2(){
          $('#ctableHeight').val(580);
       }
       function hiddenTheschedulePlanTabsidv(){
         var readers =  parent.parent.userprivilegeReaders();
          readers=readers.split(","); //字符分割 
            for (i=0;i<8 ;i++ ){ 
		    var tab_option = $('#schedulePlanTabs').tabs('getTab',i).panel('options').tab;  
             tab_option.hide();  
            }
			for (i=0;i<readers.length ;i++ ){ 
			   var tab_option1;
			   if(readers[i] == "1-1" ){
		          //权限控制显示
		    	 //$('#schedulePlanTabs').tabs('disableTab','委托管理');
		    	 tab_option1= $('#schedulePlanTabs').tabs('getTab',0).panel('options').tab;  
                 tab_option1.show();  
		       }else if(readers[i]  == "1-2"){
		    	 // $('#schedulePlanTabs').tabs('disableTab','动物试验');
		    	 tab_option1 = $('#schedulePlanTabs').tabs('getTab','动物试验').panel('options').tab;  
                 tab_option1.show();  
		       }else if(readers[i]  == "1-3"){
		    	//$('#schedulePlanTabs').tabs('close','临床检验');
		    	 tab_option1 = $('#schedulePlanTabs').tabs('getTab','临床检验').panel('options').tab;  
                 tab_option1.show();  
		       }else if(readers[i]  == "1-4"){
		    	//$('#schedulePlanTabs').tabs('close','毒性病理');
		    	 tab_option1 = $('#schedulePlanTabs').tabs('getTab','毒性病理').panel('options').tab;  
                 tab_option1.show();  
		       }else if(readers[i]  == "1-5"){
		    	//$('#schedulePlanTabs').tabs('close','QA管理');
		    	 tab_option1 = $('#schedulePlanTabs').tabs('getTab','QA管理').panel('options').tab;  
                 tab_option1.show();  
		       }else if(readers[i]  == "1-6"){
		    	// $('#schedulePlanTabs').tabs('close','供试品管理');
		    	 tab_option1 = $('#schedulePlanTabs').tabs('getTab','供试品管理').panel('options').tab;  
                tab_option1.show(); 
		       }else if(readers[i]  == "1-7"){
		       // $('#schedulePlanTabs').tabs('close','分析');
		         tab_option1 = $('#schedulePlanTabs').tabs('getTab','分析').panel('options').tab;  
                 tab_option1.show(); 
		       }else if(readers[i]  == "1-8"){
		         tab_option1 = $('#schedulePlanTabs').tabs('getTab','生态毒理').panel('options').tab;  
                 tab_option1.show(); 
		       }               
			} 
          
       }
       
       //全屏
	    function makeFullScreenlist() {
	    var tableHeight =   parent.parent.parent.window.screen.height;
		var tableWidth  = parent.parent.parent.window.screen.width ;
        $('#layoutMainDiv').layout("resize",{ height:tableHeight-20,width:tableWidth-20});
        $('#layoutMainDiv').layout("panel","center").panel("resize",{ height:tableHeight-20,width:tableWidth-20});
        $('#schedulePlanTabsidv').panel("resize",{ height:tableHeight-20,width:tableWidth-20});
      }
      
      //退出全屏
	    function resetScreenlist() {
	    var tableHeight = document.body.clientHeight;
		var tableWidth  = parent.parent.parent.document.body.clientWidth;
        $('#layoutMainDiv').layout("resize",{ height:tableHeight-65,width:tableWidth-190});
        $('#layoutMainDiv').layout("panel","center").panel("resize",{ height:tableHeight-65,width:tableWidth-190});
        $('#schedulePlanTabsidv').panel("resize",{ height:tableHeight-65,width:tableWidth-190});
      }
       
       
       
    </script>
	</head>
  <body >
  <div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
   <s:hidden id="title" name="title"  ></s:hidden>
  <!--   专题成员标记 -->
 	<div id="schedulePlanTabsidv" region="center" title="" >   
 	 	<div id="schedulePlanTabs" class="easyui-tabs" fit="true" border="false" >
			<div title="委托管理" id="tabsDict1" border="false" style="overflow: hidden;">
			  委托管理
			</div>
			<div title="动物试验" id="tabsDict2" border="false" style="overflow: hidden;" >
			动物试验
			</div>
			<div title="临床检验" id="tabsDict3" border="false" style="overflow: hidden;">
			临床检验
			</div>
			<div title="毒性病理" id="tabsDict4" border="false" style="overflow: hidden;">
			毒性病理
			</div>
			<div title="QA管理" id="tabsDict5" border="false" style="overflow:hidden;" >
			QA管理
			</div>
			<div title="供试品管理" id="tabsDict6" border="false" style="overflow:hidden;"  >
			  供试品管理
			</div>
			<div title="分析" id="tabsDict7" border="false" style="overflow:hidden;"  >
			  分析
			</div>
			<div title="生态毒理" id="tabsDict7" border="false" style="overflow:hidden;"  >
			  生态毒理
			</div>
		</div>
 	 </div>
 	 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
	 
  </body>
</html>
