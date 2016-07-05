<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>日程安排</title>
    <%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/main.css" media="screen" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/schedulePlanAction/tips.js" charset="utf-8"></script>
		<script type="text/javascript">
    	var schedulePlanTabs;
    	$(function(){
    		
    		//启动定时器 
        	startClock();
        
    		schedulePlanTabs=$('#schedulePlanTabs');
    		schedulePlanTabs.tabs({
    			onSelect:function(title,index){
    			 var title1 = $('#title').val();
    			/**if( title1 == '' ){
    			  var readers =  parent.parent.userprivilegeReaders();
    			  readers=readers.split(","); //字符分割 
    			 
    			  for (i=0;i<readers.length ;i++ ){ 
					   if(readers[i] == "1-1" ){
					    title = '房舍消毒';
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
			  }*/
    			if(title == '计划安排'){
					var tab0=schedulePlanTabs.tabs('getTab',index);
					schedulePlanTabs.tabs('update', {
		    			tab: tab0,
		    			options: {
		    				title: '计划安排',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/schedulePlanAction_loadSchedulePlan.action?taskKind=1'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
		    			}
		    		});
		    		$('#title').val('计划安排');
    			}else if(title == '计划汇总'){
    					var tab1=schedulePlanTabs.tabs('getTab',index);
    					schedulePlanTabs.tabs('update', {
    		    			tab: tab1,
    		    			options: {
    		    				title: '计划汇总',
    		    				content: '<iframe src="' + '${pageContext.request.contextPath}/schedulePlanAction_loadSchedulePlanByJfreechar.action?taskKind=2'+ ' "frameborder="0" style="border:0;width:99.8%;height:98%;"></iframe>',
    		    			}
    		    		});
    		    		$('#title').val('计划汇总');
        			}
			    }
			    		
        	});
        	
        	
			//显示整个布局
			$('#layoutMainDiv').css('display',''); 
			//hiddenTopDiv();
			//hiddenTheschedulePlanTabsidv();
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
 	<div id="schedulePlanTabsidv" region="center" title="" >   
 	 	<div id="schedulePlanTabs" class="easyui-tabs" fit="true" border="false" >
			<div title="计划安排" id="tabsDict1" border="false" style="overflow: hidden;">
			  计划安排
			</div>
			<div title="计划汇总" id="tabsDict2" border="false" style="overflow: hidden;" >
			计划汇总
			</div>
		</div>
 	 </div>
	</div>
	 
  </body>
</html>
