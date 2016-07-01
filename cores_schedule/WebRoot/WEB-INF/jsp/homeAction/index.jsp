<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES综合管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>

<script src="${pageContext.request.contextPath}/script/javascript/tblNotification.js" type="text/javascript"></script>

<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/index.js" charset="utf-8"></script>


	  <script type="text/javascript">
	  
	   var pointerInInput = false ;
	  
	    //tabs 开始
	   var indexTabs;
    	
    	
    	$(function(){

    		showIndexTabsDiv();
        	
    		indexTabs=$('#indexTabsDiv');
    		indexTabs.tabs({
    			onSelect:function(title,index){
	    			if(title == "通知管理"){
	    			var tab0=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab0,
			    			options: {
			    				title: '通知管理',
			    				content: '<iframe  src="${pageContext.request.contextPath}/tblNotificationAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    			}
			    		});
	    			}else if(title =="日程管理"){
						var tab1=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab1,
			    			options: {
			    				title: '日程管理',
			    				content: '<iframe  src="${pageContext.request.contextPath}/homeAction_schedule.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}else if(title =="专题信息"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '专题信息',
			    				content: '<iframe  src="${pageContext.request.contextPath}/tblAppointSDAction_appointSD.action?isExistFileDis='+$('#isExistFileDis').val()+'" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}else if(title =="供试品"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '供试品',
			    				content: '<iframe src="${pageContext.request.contextPath}/tblTestItemAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}else if(title =="QA管理"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: 'QA管理',
			    				content: '<iframe  src="${pageContext.request.contextPath}/qAChkAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    			}
			    		});
	    			}else if(title =="文件打印"){
	    				var tab2=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '文件打印',
			    				content: '<iframe src="${pageContext.request.contextPath}/tblAttachmentIndexAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
	    			}else if(title =='系统日志'){
	    				var tab3=indexTabs.tabs('getTab',index);
						indexTabs.tabs('update', {
			    			tab: tab3,
			    			options: {
			    				title: '系统日志',
			    				content: '<iframe  src="${pageContext.request.contextPath}/tblLogAction_list.action" "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
	
			    			}
			    		});
		    		}
			    }
			    		
        	});
        	
        //	document.onkeydown = KeyDown;
          
			//showIndexTabsDiv();
			$('#mainbody2').css('display','');
			 
        });//匿名函数结束
      
      function showIndexTabsDiv(){
            for (i=0;i<5 ;i++ ){ 
			    var tab_option = $('#indexTabsDiv').tabs('getTab',i).panel('options').tab;  
	             tab_option.hide();  
            } 
            var  isNotification = $("#isNotification").val();//0通知管理
            var  isSchedule = $("#isSelectSchedule").val();//1日程管理
            var  islAppointSD = $("#islAppointSD").val();//2专题信息
            var  tblTestItem = $("#tblTestItem").val();//3供试品
            var  qAManagement = $('#QAManagement').val();//4QA管理
            var  attachment = $("#attachment").val();//4->5文件打印
            if(isNotification ==1){
             	$('#indexTabsDiv').tabs('select',0);
            }else if(isNotification != 1 && isSchedule ==1){
             		$('#indexTabsDiv').tabs('select',1);
            }else if(isNotification != 1 && isSchedule !=1 && islAppointSD==1){
             	$('#indexTabsDiv').tabs('select',2);
            }else if(isNotification != 1 && isSchedule !=1 && islAppointSD!=1&&tblTestItem == 1){
             	$('#indexTabsDiv').tabs('select',3);
            }else if(isNotification != 1 && isSchedule !=1 && islAppointSD!=1&&tblTestItem != 1 && qAManagement==1){
            	$('#indexTabsDiv').tabs('select',4);
            }else if(isNotification != 1 && isSchedule !=1 && islAppointSD !=1 && tblTestItem != 1 && qAManagement !=1 && attachment == 1){
           		 $('#indexTabsDiv').tabs('select',5);
            }
                
            var tab_option1;
            if(isNotification == 1){
	             tab_option1= $('#indexTabsDiv').tabs('getTab',0).panel('options').tab;  
	             tab_option1.show(); 
            }
            if(isSchedule == 1){
               tab_option1= $('#indexTabsDiv').tabs('getTab',1).panel('options').tab;  
	           tab_option1.show();  
            }
            if(islAppointSD == 1){
                tab_option1= $('#indexTabsDiv').tabs('getTab',2).panel('options').tab;  
	            tab_option1.show();  
            }
            if(tblTestItem == 1){
                tab_option1= $('#indexTabsDiv').tabs('getTab',3).panel('options').tab;  
	            tab_option1.show();  
            }
            if(qAManagement == 1){
                tab_option1= $('#indexTabsDiv').tabs('getTab',4).panel('options').tab;  
	            tab_option1.show();  
            }
            if(attachment == 1){
                tab_option1= $('#indexTabsDiv').tabs('getTab',5).panel('options').tab;  
	            tab_option1.show();  
            }

            //判断是否是附件分发并没有完成的人,如果存在的话则同样显示专题信息tab
            $.ajax({
                  url:sybp()+'/tblStudyFileIndexAction_isExistNotFinishDiv.action',
				  dataType: "json",   
			      //data: {
				//	  studyNo:rowData.studyNo
			      //  },   
			      async: false,   
			      cache: false,   
			      type: "post" ,
			      success:function(r){
					if(r)
					{
						if(r.isExist){
							 $('#isExistFileDis').val("true");
							 tab_option1= $('#indexTabsDiv').tabs('getTab',2).panel('options').tab;  
					         tab_option1.show();  
						}
					}
				  } 
			 });
            
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
      //退出
      function reset() {
        var tableHeight = document.body.clientHeight;
		var tableWidth  = document.body.clientWidth;
        document.getElementById("mainbody2").style.top = '64px';
        document.getElementById("headerbox").style.display = "";
        $("#mainbody2").css("height","90%");
        $("#mainbody2").css("width","100%");
        $('#indexTabsDiv').tabs("resize",{ height:tableHeight-65,width:tableWidth-180}); 
        if (document.exitFullscreen) {
          document.exitFullscreen();
        }
        else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
        else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        }
        else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        }
        inFullScreen = false;
        return;
      }
    //权限控制开始
    function userprivilegeReaders(){
      var userprivilege = document.getElementsByName("userprivilege")[0].value;
	  var result = userprivilege.split(",");
	  var ary = new Array();
	  for(var i=0;i<result.length;i++){
	     if(result[i] == '1-1' ){
          //权限控制显示
    	    ary = ary.concat(result[i]);
         }else if(result[i] == '1-2' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '1-3' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '1-4' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '1-5' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '1-6' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '1-7' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '1-8' ){
    	   ary = ary.concat(result[i]);
         }  
	  }
	    var readers = ary.join(",");
	    return readers;
  }
   //查看权限
    function userprivilegeReaders(){
      var userprivilege = document.getElementsByName("userprivilege")[0].value;
	  var result = userprivilege.split(",");
	  var ary = new Array();
	  for(var i=0;i<result.length;i++){
	     if(result[i] == '1-1' ){
          //权限控制显示
    	    ary = ary.concat(result[i]);
         }else if(result[i] == '1-2' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '1-3' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '1-4' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '1-5' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '1-6' ){
    	   ary = ary.concat(result[i]);
         }   else if(result[i] == '1-7' ){
    	   ary = ary.concat(result[i]);
         }   else if(result[i] == '1-8' ){
    	   ary = ary.concat(result[i]);
         }  
	  }
	    var readers = ary.join(",");
	    return readers;
  }
  //分配权限
   function userprivilegeDistribution(){
      var userprivilege = document.getElementsByName("userprivilege")[0].value;
	  var result = userprivilege.split(",");
	  var ary = new Array();
	  for(var i=0;i<result.length;i++){
	     if(result[i] == '2-1' ){
          //权限控制显示
    	    ary = ary.concat(result[i]);
         }else if(result[i] == '2-2' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '2-3' ){
    	   ary = ary.concat(result[i]);
         }if(result[i] == '2-2-1' ){
          //权限控制显示
    	    ary = ary.concat('2-4');
         }else if(result[i] == '2-2-2' ){
    	   ary = ary.concat('2-4');
         }else if(result[i] == '2-2-3' ){
    	   ary = ary.concat('2-4');
         }else if(result[i] == '2-2-4' ){
    	   ary = ary.concat('2-4');
         } else if(result[i] == '2-2-5' ){
    	   ary = ary.concat('2-4');
         } else if(result[i] == '2-2-6' ){
    	   ary = ary.concat('2-4');
         }else if(result[i] == '2-2-7' ){
    	   ary = ary.concat('2-4');
         }else if(result[i] == '2-2-8' ){
    	   ary = ary.concat('2-4');
         }else if(result[i] == '2-2-9' ){
    	   ary = ary.concat('2-4');
         }         
	  }
	    var distribution = ary.join(",");
	    alert(distribution);
	    return distribution;
  }
  
  //常规任务负责人权限
   function userprivilegeTask(){
      var userprivilege = document.getElementsByName("userprivilege")[0].value;
	  var result = userprivilege.split(",");
	  var ary = new Array();
	  for(var i=0;i<result.length;i++){
	     if(result[i] == '2-2-1' ){
          //权限控制显示
    	    ary = ary.concat(result[i]);
         }else if(result[i] == '2-2-2' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '2-2-3' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '2-2-4' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '2-2-5' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '2-2-6' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '2-2-7' ){
    	   ary = ary.concat(result[i]);
         }else if(result[i] == '2-2-8' ){
    	   ary = ary.concat(result[i]);
         } else if(result[i] == '2-2-9' ){
    	   ary = ary.concat(result[i]);
         }    
	  }
	    var task = ary.join(",");
	    return task;
  }
  
  //top 2014-08-30 wan
    var second = 40;
	var time = 0;
	var height =0;
	var timer_up;
	var down = function(){
		height = height+2;
		time = time+second;
		$('#syMessagerShow').css('top',-28+height);
		if(height >= 28){
			 clearInterval(timer_down);
			 height=0;
			 time =0;
		}
    }
    var up = function(){
    	height = height+2;
		time = time+second;
		$('#syMessagerShow').css('top',0-height);
		if(height >= 28){
			 clearInterval(timer_up);
		}
	}
	var timeout = function(){
		//重复的定时器向上
		timer_up = setInterval( up , second);
		clearTimeout(timer_out);
	}
	var timer_down;
	var timer_out;

</script>
	  
</head>

<body >
<input  type="hidden" id="isNotification" name="isNotification" value="${isNotification}"/>
<input  type="hidden" id="islAppointSD" name="islAppointSD" value="${islAppointSD}"/>
<input  type="hidden" id="isSelectSchedule" name="isSelectSchedule" value="${isSelectSchedule}"/>
<input  type="hidden" id="userprivilege" name="userprivilege" value="${indexuserprivilege}"/>
<input  type="hidden" id="tblTestItem" name="tblTestItem" value="${tblTestItem}"/>
<input  type="hidden" id="attachment" name="attachment" value="${attachment}"/>
<input  type="hidden" id="QAManagement" name="QAManagement" value="${QAManagement}"/>

<input  type="hidden" id="isExistFileDis" />

	<div class="container" id="container" >
    	<div class="header_box" id="headerbox" style="display:'';">
    		<input id="userName" type="hidden" value="${user.userName}"></input>
        <!-- top 开始 -->
           <%@ include file="/WEB-INF/jsp/homeAction/top.jspf"%>
        <!-- top 结束 -->
        </div>
        <!--  <div class="sidebar_box" id="sidebar_box" style="display:'';">
        	<iframe name="left" src="${pageContext.request.contextPath}/homeAction_leftSchedule.action" width=100% height=100% frameborder=no border=0 marginwidth=0 marginheight=0 scrolling=no allowtransparency=yes></iframe>
        </div>
        <div class="mainbody2" id="mainbody2" >
        	<iframe  name="main" src="${pageContext.request.contextPath}/homeAction_main.action" width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>
        </div> -->
        <div class="mainbody3" id="mainbody2" style="display:none;" >
 	 	 <div id="indexTabsDiv" class="easyui-tabs" fit="true" border="false" >
 	 	    <div title="通知管理"  border="false" style="overflow: hidden;">
			  通知管理
			</div>
			<div title="日程管理"  border="false" style="overflow: hidden;">
			  日程管理
			</div>
			<div title="专题信息" border="false" style="overflow: hidden;">
			 FM任命SD
			</div>
			<div title="供试品"   border="false" style="overflow: hidden;">
			  供试品
			</div>
			<div title="QA管理" border="false" style="overflow: hidden;">
			 QA管理
			</div>
			<div title="文件打印"    border="false" style="overflow: hidden;">
			  文件打印1
			</div>
			<div title="系统日志"  border="false" style="overflow: hidden;">
			  系统日志
			</div>
		 </div>
 	 </div>
       
    </div>
   <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
</body>
</html>




