<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>试验计划</title>
    <%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/studyplan.css" media="screen" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/main.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/homeAction/main.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddNo.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyTypeSelect.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlan.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
 
    <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblWeight.js" charset="utf-8"></script>
    <style type="text/css">
    .tree-icon{
    	width:0px;
    }
    </style>
    <script type="text/javascript">
    	var toWhere;
    	var studyPlanTabs;
    	var studyNoPara;
    	var studyState;
    	var isAnimalIdES;
    	var member;
    	var hasAnimal;
    	$(function(){
    		toWhere=$('#toWhere').val();
    		studyPlanTabs=$('#studyPlanTabs');
    		studyNoPara=$('#studyNoPara').val();
    		studyState=$('#studyState').val();
    		isAnimalIdES=$('#isAnimalIdES').val();
    		member = $('#left_member').val();
    		hasAnimal = $('#hasAnimal').val();
    		studyPlanTabs.tabs({
    			onSelect:function(title,index){
    				if(index == 1){
    					var tab1=studyPlanTabs.tabs('getTab',1);
    					studyPlanTabs.tabs('update', {
    		    			tab: tab1,
    		    			options: {
    		    				title: '剂量设置',
    		    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblDoseSettingAction_list.action?studyNoPara='+studyNoPara+ '&member='+$('#left_member').val()+' " frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
    		    			}
    		    		});
        			}else if(index == 2){
						var tab2=studyPlanTabs.tabs('getTab',2);
						studyPlanTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '解剖计划',
			       				content: '<iframe src="' + '${pageContext.request.contextPath}/tblDissectPlanAction_dissectPlanList.action?studyNoPara='+studyNoPara+ '&member='+$('#left_member').val()+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			    			}
			    		});
					}else if(index == 3){
						var tab3=studyPlanTabs.tabs('getTab',3);
						studyPlanTabs.tabs('update', {
			    			tab: tab3,
			    			options: {
			    				title: '检验指标',
			       				content: '<iframe src="' + '${pageContext.request.contextPath}/tblTestIndexPlanAction_testIndexPlanList.action?studyNoPara='+studyNoPara+ '&member='+$('#left_member').val()+'" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			    			}
			    		});
					}else if(index == 5 ){
                       //window.open('${pageContext.request.contextPath}/tblSchedulePlanAction_list.action');
                       var tab4=studyPlanTabs.tabs('getTab',5);
						studyPlanTabs.tabs('update', {
			    			tab: tab4,
			    			options: {
			    				title: '日程安排',
			       				//content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_list.action?studyNoPara='+studyNoPara+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblSchedulePlanAction_previewSchedulePlan.action?isValidationPara=1&disPlaytype=1&studyNoPara='+studyNoPara+ '&member='+$('#left_member').val()+'" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			    			}
			    		});
					}else if(index == 4 ){
	                       //window.open('${pageContext.request.contextPath}/tblSchedulePlanAction_list.action');
	                       var tab4=studyPlanTabs.tabs('getTab',4);
							studyPlanTabs.tabs('update', {
				    			tab: tab4,
				    			options: {
				    				title: '病理计划',
				    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblPathPlanAction_pathPlanList.action?studyNoPara='+studyNoPara+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				    			}
				    		});
						}
    			}
        	});
			//处理菜单、按钮状态
			manageMenuItemButtonState(); 
			//处理加载的标签页
			manageTabs();   
			//显示整个布局
			$('#layoutMainDiv').css('display','');   
			//专题成员只读	
			MemberReadOnly();

			
        });//匿名函数结束

        /**处理加载的标签页*/
        function manageTabs(){
            if(toWhere == 'animalList'){
            	addNewAnimalTab();
            }else if(toWhere == 'animalWeight'){
                 	addNewlTiprpAppInd();
            }else if(toWhere == 'Weight1'){
            	   addNewWeighIndTab();
            }
        }
     	/**处理菜单、按钮状态*/
		function manageMenuItemButtonState(){
			
			//临床检验管理
			var itemEI01=$('#clinicalTestMenuItem')[0];
			//动物管理
			var itemEI10=$('#animalMenuItem')[0];

			//胶囊申请
			var itemEI02=$('#lTiprpAppIndMenuItem')[0];
			
			//解剖申请
			var itemEI20=$('#addNewAnatomyReqMenuItem')[0];

			//动物体重
			var itemEI11=$('#animalWeighInd')[0];
			member = $('#left_member').val();
			var submitAndApplyChangeStatueStr="";
			var studyState=$('#studyState').val();
			if(studyState==0)
			{
				 submitAndApplyChangeStatueStr+="试验计划可签字提交,";
			}
			if(studyState>0){
			   //是否具有审批修改权限
			   var role = $('#role').val();
			   if(studyState == "1"){
				   submitAndApplyChangeStatueStr+="试验计划可申请修改,";
			        //签字和编辑按钮禁用
					$('#qianziStudyPlanButton').linkbutton('disable');
					$('#copyFromExistStudyButton').linkbutton('disable');
					$('#editStudyPlanButton').linkbutton('disable');
					//申请修改可以  2014-12-10
					if(member == ""){
						$('#applyReviseButton').linkbutton('enable');
					}else{
					    $('#applyReviseButton').linkbutton('disable');
					}
				
					if(hasAnimal == "1"){
						$('#menu1').menu('enableItem',itemEI10);
						$('#menu0').menu('enableItem',itemEI01);
						$('#menu0').menu('enableItem',itemEI02);
						$('#menu0').menu('enableItem',itemEI20);
						$('#menu1').menu('enableItem',itemEI11);
					}else{
						$('#menu1').menu('disableItem',itemEI10);
						$('#menu0').menu('disableItem',itemEI01);
						$('#menu0').menu('disableItem',itemEI02);
						$('#menu0').menu('disableItem',itemEI20);
						$('#menu1').menu('disableItem',itemEI11);
					}
			   }else if(studyState == "2"){
				   submitAndApplyChangeStatueStr+="试验计划等待审批,";
			        if(role == "have"){
			           $('#approvalReviseButton').css('display','');  
			        }else{
			           $('#approvalReviseButton').css('display','none');  
			        }
			        $('#qianziStudyPlanButton').linkbutton('disable');
			        $('#copyFromExistStudyButton').linkbutton('disable');
					$('#editStudyPlanButton').linkbutton('disable');
					//申请修改可以  2014-12-10
					$('#applyReviseButton').linkbutton('disable');
					if(hasAnimal == "1"){
						$('#menu1').menu('enableItem',itemEI10);
						$('#menu0').menu('enableItem',itemEI01);
						$('#menu0').menu('enableItem',itemEI02);
						$('#menu0').menu('enableItem',itemEI20);
						$('#menu1').menu('enableItem',itemEI11);
					}else{
						$('#menu1').menu('disableItem',itemEI10);
						$('#menu0').menu('disableItem',itemEI01);
						$('#menu0').menu('disableItem',itemEI02);
						$('#menu0').menu('disableItem',itemEI20);
						$('#menu1').menu('disableItem',itemEI11);
					}
					//if(isAnimalIdES == 1){
			   }else if(studyState == "3"){
				    submitAndApplyChangeStatueStr+="试验计划可签字提交,";
			        //申请修改不可用  2014-12-10
			    	$('#applyReviseButton').linkbutton('disable')
			        if(member == ""){
					    $('#qianziStudyPlanButton').linkbutton('enable');
					    $('#copyFromExistStudyButton').linkbutton('enable');
				    	$('#editStudyPlanButton').linkbutton('enable');
				    }else{
				        $('#qianziStudyPlanButton').linkbutton('disable');
				        $('#copyFromExistStudyButton').linkbutton('disable');
				    	$('#editStudyPlanButton').linkbutton('disable');
				    }
			    	if(hasAnimal == "1"){
						$('#menu1').menu('enableItem',itemEI10);
						$('#menu0').menu('enableItem',itemEI01);
						$('#menu0').menu('enableItem',itemEI02);
						$('#menu0').menu('enableItem',itemEI20);
						$('#menu1').menu('enableItem',itemEI11);
					}else{
						$('#menu1').menu('disableItem',itemEI10);
						$('#menu0').menu('disableItem',itemEI01);
						$('#menu0').menu('disableItem',itemEI02);
						$('#menu0').menu('disableItem',itemEI20);
						$('#menu1').menu('disableItem',itemEI11);
					}
			   }
			}else{
				//申请修改不可用  2014-12-10
				$('#applyReviseButton').linkbutton('disable');
				//只读
				if(member != ''){
				   $('#qianziStudyPlanButton').linkbutton('disable');
				   $('#copyFromExistStudyButton').linkbutton('disable');
				   $('#editStudyPlanButton').linkbutton('disable');
				}
				$('#menu0').menu('disableItem',itemEI01);
				$('#menu1').menu('disableItem',itemEI10);
				$('#menu1').menu('disableItem',itemEI11);
				$('#menu0').menu('disableItem',itemEI20);
				$('#menu0').menu('disableItem',itemEI02);
			}
			
//		   $('#menu0').menu('enableItem',itemEI20);
			//null, 0 未提交 1 提交 2 申请再编辑 3再编辑
			//alert(studyState+" state:"+$('#scheduleState').val()+"==="+member);
				var scheduleState=$('#scheduleState').val();
				if(scheduleState>=0){
				   //是否具有审批修改权限
				   var role = $('#role').val();
				   if(scheduleState==1){ 
					   submitAndApplyChangeStatueStr+="日程可申请修改";
						//申请修改可以  2014-12-10
						if(member == ""){
							//$('#applyReviseButton').linkbutton('enable');
						}
				   }else if(scheduleState == "2"){
					   submitAndApplyChangeStatueStr+="日程等待审批";
				        if(role == "have"){
				           $('#approvalReviseButton').css('display','');  
				        }
				   }else if(scheduleState == "3"||scheduleState==''||scheduleState==0){
					   submitAndApplyChangeStatueStr+="日程可签字提交";
				        if(member == ""){
						   // $('#qianziStudyPlanButton').linkbutton('enable');
					    }
				   }
				   
				}
				$('#submitAndApplyChangeStatue').html(submitAndApplyChangeStatueStr);
			
			initButton();
		}
		
	//日程的按钮处理
	function initButton()
	{	
		var member = $('#left_member').val();
		 //var studyState =  $('#studyStateMain').val();
		 var studyState =  $('#scheduleState').val()
		 if(studyState>0){
			   //是否具有审批修改权限
			 
			   if(studyState == 1){
			        //签字和编辑按钮禁用
					$('#signSchedulePlanButton').linkbutton('disable');
					//申请修改可以  2014-12-10
					if(member == ""){
						$('#applyChangeButton').linkbutton('enable');
					}else{
					    $('#applyChangeButton').linkbutton('disable');
					}
				
			   }else if(studyState == 2){
			        $('#signSchedulePlanButton').linkbutton('disable');
			       
					//申请修改可以  2014-12-10
					$('#applyChangeButton').linkbutton('disable');
					
					//if(isAnimalIdES == 1){
			   }else if(studyState==''||studyState == 3){
			        //申请修改不可用  2014-12-10
			    	$('#applyChangeButton').linkbutton('disable')
			        if(member == ""){
					    $('#signSchedulePlanButton').linkbutton('enable');
				    }else{
				        $('#signSchedulePlanButton').linkbutton('disable');
				    }
			    	
			   }
			}else{
				//申请修改不可用  2014-12-10
				$('#applyChangeButton').linkbutton('disable');
				//只读
				if(member != ''){
				   $('#signSchedulePlanButton').linkbutton('disable');
				}
				
			}
		 
		
	}
	//日程签字确认
	function signSchedulePlan(){
		studyPlanTabs.tabs('select',5);
        var studyNo = $("#studyNoPara").val();
    	//realCheckBeforeStudyPlanSign(1);//直接使用专题的提交
    	$.ajax({
	        type:"POST",
	        url:sybp()+"/tblStudyPlanAction_signCheck.action?continueFlag=1",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"html",
	        data:"studyNoPara="+studyNo,
	        async:false,
	        success:function(msg)
	        {
		        if (msg=="tblSchedulePlan") {					
		        		$.messager.confirm('确认对话框', '日程没有设置，确定不设置吗？', function(r){
		        			if(r){
		        				qm_showQianmingDialog('eventAfterStudyPlanSign2');
		        			}
		        		});
					
		        }else  if (msg=="pass") {	
		        	qm_showQianmingDialog('eventAfterStudyPlanSign2');	
		        }
			}
    	});
    	
	}
  function eventAfterStudyPlanSign2(){
	
      var studyNo = $("#studyNoPara").val();
     // window.location.href = "${pageContext.request.contextPath}/tblStudyPlanAction_sign.action?studyNoPara="+studyNo+"&continueFlag="+1;
  	 // parent.showMessager(1,'签字成功!',true,3000);
	  $.ajax({
			url : sybp()+'/tblStudyPlanAction_sign.action?studyNoPara='+studyNo+'&continueFlag=1',
			type: 'post',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 			
			dataType:'json',
			success:function(){
				
			}
        });
	 	 parent.parent.showMessager(1,'日程提交签字成功!',true,5000);
		//$('#studyStateMain').val(1);
		
		$('#scheduleState').val(1);
		manageMenuItemButtonState(); 	
			
     }  	
     //日程申请修改
	function applyChangeSchedule()
	{
		studyPlanTabs.tabs('select',5);
		$.messager.prompt('提示信息', '是否确认修改日程!<br/>请填写修改原因：', function(r){
			if (r){
			     if(r != ""){
			        var reason = r;
			        $('#reason').val(reason);
			        qm_showQianmingDialog('afterApplyRevise1');
			     }
			}else{
			         if(r == ""){
			              $.messager.defaults = { ok: "是", cancel: "否" }; 
			              $.messager.confirm('确认对话框', '请填写修改原因，是否继续执行修改操作？', function(r){
							  if (r){
							      applyChangeSchedule();
							  }
						  }); 
			         } 
			     }
		});

	}
	
     //真正的日程申请修改
     function afterApplyRevise1(){
       var studyNoPara = $('#studyNoPara').val();
       var reason =  $('#reason').val();
        $.ajax({
				url:sybp()+'/tblApplyReviseAction_applyRevise.action?continueFlag='+1,//申请日程
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				type:'post',
				data:{studyNoPara:studyNoPara,
				      reason:reason},
				dataType:'json',
				success:function(r){
						if(r && r.success){
                        parent.parent.showMessager(1,'日程置为编辑成功',false,5000);
                        $('#applyChangeButton').linkbutton('disable');
                        $('#reason').val("");
                        
                        $('#scheduleState').val(3);
                        manageMenuItemButtonState(); 
                        
						}else{
						   parent.parent.showMessager(3,'与数据库交互错误',false,5000);
						}
					}
			});
     }

        function  MemberReadOnly(){
        	member = $('#left_member').val();
            if(member==''){
    			var itemEI0Add=$('#addNewStudyPlanButton')[0];
    			$('#menu0').menu('enableItem',itemEI0Add);
             }else{
            	 var itemEI0Add=$('#addNewStudyPlanButton')[0];
     		
     			 var SD = $('#SD').val();
     			 if(SD == "true"){
     			    $('#menu0').menu('enableItem',itemEI0Add);
     			 }else{
     			   	 $('#menu0').menu('disableItem',itemEI0Add);
     			 }
             }
        }
        
        //申请修改
        function applyRevise(){
        	studyPlanTabs.tabs('select',0);
        	var studyState = $('#studyState').val();
        	var scheduleState = $('#scheduleState').val();
        	//0 未提交 1 提交 2 申请再编辑 3再编辑
        	var studyFlag = 0;
        	if(studyState==1)
        	{
        		studyFlag = 1;
        	}
        	var scheduleFlag = 0;
        	if(scheduleState==1)
        	{
        		scheduleFlag = 1;
        	}
        	var labelStr = "";
        	if(studyFlag==1&&scheduleFlag==0)
        	{
        		//$.messager.alert('提示', '申请修改试验计划');
        		labelStr="试验计划";
        		$('#continueFlag').val(0);
        		//applyReviseReson(0);
        			
        	}else if(studyFlag==0&&scheduleFlag==1)
        	{
        		//$.messager.alert('提示', '申请修改日程信息');
        		labelStr="日程";
        		$('#continueFlag').val(1);
        	//	applyReviseReson(1);//只检查日程
        		
        	}else if(studyFlag==1&&scheduleFlag==1)
        	{
        		labelStr="试验计划和日程";
        		$('#continueFlag').val(2);
            	/*
        		$.messager.defaults = { ok: "是", cancel: "否" }; 
        		$.messager.confirm('确认对话框', '申请修改专题试验计划,是否同时申请修改日程？', function(r){
        			if (r){
        				$('#continueFlag').val(2);
        				applyReviseReson(2);//两个都检查
        			}else {
        				$('#continueFlag').val(0);
        				applyReviseReson(0);//只检查专题
        			}
        		});*/
        	}
        	document.getElementById("chooseApplyChangeDialog2").style.display="block";
			//alert(r.type+"==="+r.reason+",是否批准修改？");
			$('#applyChangeResonLabel').html("修改"+labelStr);
			$('#applyItemTable').css('display','none');
			var type = $('#continueFlag').val();
			if(type==null||type==''||type==0)
			{
				$('#justApplyStudyPlan').attr('checked','checked'); 
			}
			else if(type==1)
			{
				$('#justApplySchedule').attr('checked','checked');
			}
			else if(type==2)
			{
				$('#applyBothOfStudyAndSchedule').attr('checked','checked');
			
				$('#applyItemTable').css('display','');
			}
        	
        	$('#chooseApplyChangeDialog').dialog('open'); 
        	
        }
        function readApplyChange()
        {
			var type = $("input[name='applyChangeScope']:checked").val();
			applyReviseReson(type);
        }
        
        function applyReviseReson(continueFlag){
			var str = "";
			if(continueFlag==0)
			{
				str="试验计划";
			}
			if(continueFlag==1)
			{
				str="日程";
			}
			if(continueFlag==2)
			{
				str="试验计划和日程";
			}
            
			$.messager.prompt('提示信息', '是否确认修改'+str+'!<br/>请填写修改原因：', function(r){
				if (r){
				     if(r != ""){
				        var reason = r;
				        $('#reason').val(reason);
				        $('#continueFlag').val(continueFlag);
				        qm_showQianmingDialog('afterApplyRevise');
				     }
				}else{
				         if(r == ""){
				              $.messager.defaults = { ok: "是", cancel: "否" }; 
				              $.messager.confirm('确认对话框', '请填写修改'+str+'原因，是否继续执行修改操作？', function(r){
								  if (r){
									  applyReviseReson(continueFlag);
								  }
							  }); 
				         } 
				     }
			});

			 
        }
        //真正的申请修改
        function afterApplyRevise(){
          var studyNoPara = $('#studyNoPara').val();
          var reason =  $('#reason').val();
          var continueFlag = $('#continueFlag').val();
          var str = "";
			if(continueFlag==0)
			{
				str="试验计划";
			}
			if(continueFlag==1)
			{
				str="日程";
			}
			if(continueFlag==2)
			{
				str="试验计划和日程";
			}
           $.ajax({
				url:sybp()+'/tblApplyReviseAction_applyRevise.action?continueFlag='+continueFlag,
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				type:'post',
				data:{studyNoPara:studyNoPara,
				      reason:reason},
				dataType:'json',
				success:function(r){
						if(r && r.success){
                           parent.parent.showMessager(1,str+'置为编辑成功',false,5000);
                           $('#applyReviseButton').linkbutton('disable');
                           $('#reason').val("");
							if(continueFlag==0||continueFlag==2)
							{
								//$('#studyState').val(2);
								$('#studyState').val(3);
							}
							if(continueFlag==1||continueFlag==2)
							{
								//$('#scheduleState').val(2);
								$('#scheduleState').val(3);
							}
                      		manageMenuItemButtonState(); 
                           
						}else{
						   parent.parent.showMessager(3,'与数据库交互错误',false,5000);
						}
					}
			});
           $('#chooseApplyChangeDialog').dialog('close'); 
        }
        //批准修改
        function approvalRevise(){
           var studyNoPara = $('#studyNoPara').val();
           $.ajax({
				url:sybp()+'/tblApplyReviseAction_approvalRevise.action',
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				type:'post',
				data:{studyNoPara:studyNoPara},
				dataType:'json',
				success:function(r){
						if(r)
						{
							document.getElementById("chooseApprovalChangeDialog2").style.display="block";
							//alert(r.type+"==="+r.reason+",是否批准修改？");
							$('#approvalChangeResonLabel').html(r.reason+",是否批准修改？");
							$('#approvalItemTable').css('display','none');
							if(r.type==null||r.type==''||r.type==0)
							{
								$('#justStudyPlan').attr('checked','checked'); 
							}
							if(r.type==1)
							{
								$('#justSchedule').attr('checked','checked');
							}
							if(r.type==2)
							{
								$('#bothOfStudyAndSchedule').attr('checked','checked');
							
								$('#approvalItemTable').css('display','');
							}
				        	
				        	$('#chooseApprovalChangeDialog').dialog('open'); 
						}
						
						
						
					}
			});
        }
        function rejectApplyChange()
        {
        	  $.messager.defaults = { ok: "确定", cancel: "取消" }; 
			   	$.messager.prompt('提示信息', '是否确认拒绝申请修改!<br/>请填写申请原因：', function(r){
			   	    if (r){
					     if(r != ""){
					         var reason = r;
                          $('#reason').val(reason);
                          $('#applyFlag').val(-1);
					        qm_showQianmingDialog('allowApprovalRevise');
					     }
					}else{   
					         if(r == ""){
					              $.messager.defaults = { ok: "是", cancel: "否" }; 
					              $.messager.confirm('确认对话框', '请填写拒绝申请原因，是否继续执行审批操作？', function(r){
									  if (r){
									      approvalRevise();
									  }
								  }); 
					         } 
					  }
			   	    
			   	});
        }
        
        //签字后操作
        function allowApprovalRevise(){
           var studyNoPara = $('#studyNoPara').val();
           var reason = $('#reason').val();
           var applyFlag = $('#applyFlag').val();
           var type = $("input[name='approvalChangeScope']:checked").val();
          // alert("===="+type);
           
           $.ajax({
				url:sybp()+'/tblApplyReviseAction_signAllowApprovalRevise.action',
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				type:'post',
				data:{studyNoPara:studyNoPara,applyFlag:applyFlag,refusalReason:reason,continueFlag:type},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						parent.parent.showMessager(1,'签字成功',false,5000);
					    $('#approvalReviseButton').linkbutton('disable');
					}else{
						parent.parent.showMessager(2,'与服务器交互错误',false,5000);
				    }
				    
				}
			});
           $('#chooseApprovalChangeDialog').dialog('close');
        }
        //从已有的专题复制
        function copyFromExistStudy()
        {
        	document.getElementById("studyPlanDialog2").style.display="block";
        	$('#studyPlanTable').datagrid({
        		url:sybp()+'/tblStudyPlanAction_loadListByStudyType.action?studyNoPara='+$('#studyNoPara').val(),
            });
        	$('#studyPlanDialog').dialog('open'); 
        }
       //
       function onstudyPlanDialogButton()
       {
    	   var row = $('#studyPlanTable').datagrid("getSelected");
    	   //alert("选择了："+row);

    	   if(row!=null)
    	   {
    	   	   $('#studyPlanDialog').dialog('close'); 
    	   	   $.messager.defaults = { ok: "确定", cancel: "取消" };
	    	   $.messager.confirm("确认对话框","如果复制专题信息，将会覆盖原有信息",function(r)
	    	   {
	        	   if(r){
		    	  	 //复制row.studyNo的内容给$('#studyNoPara').val()
	        		   //先判断是否有日程计划，有的话提示复制的开始时间
	                   $.ajax({
		   				url:sybp()+'/tblSchedulePlanAction_isExistTblSchedulePlan.action',
		   				contentType: "application/x-www-form-urlencoded; charset=utf-8",
		   				type:'post',
		   				data:{
		   						studyNoPara:row.studyNo
		   				     },
		   				dataType:'json',
		   				success:function(r){
		   					if(r && r.success){
		   					    $.messager.defaults = { ok: "是", cancel: "否" }; 
		                        $.messager.confirm("确认对话框","是否复制日程？",function(r2)
		                        {
			                        if(r2){
					                   //弹出对话框，让选择开始日期
					                   document.getElementById("selectScheduleStartDateDialog2").style.display="block";
					                   $('#studyNodeNew').html($('#studyNoPara').val());
					                   $('#studyNodeOld').html(row.studyNo);
										//获取日期
									$.ajax({
										url:sybp()+'/tblStudyPlanAction_isExistGroupingDate.action',
										contentType: "application/x-www-form-urlencoded; charset=utf-8",
										type:'post',
										data:{sourceStudyPlanNo:row.studyNo},
										dataType:'json',
										success:function(r){
											
											if(r){
												  $('#dateOld').datebox("setValue",r.date);//源专题分组日期
											}else{
											}
										    
										}
									});
						                 
					                 
					                   $('#selectScheduleStartDateDialog').dialog('open'); 
										//确定点击以后再复制
										
		                            }else{
		   						 		//alert("不复制日程");
		   						 		//复制其他的
		   						 		copyStudyPlan(false);
		   							}
		   						
		   						});
		   					}else
		   					{
			   					//不存在日程记录的
		   						copyStudyPlan(false);
		   					}
	                  
		   				}
	                   });//ajax结束
	                   
	           	}else{
					//什么都不做
	           		//$.messager.alert("","不复制专题！");
	           	} 
	    	  });
    	   }else
    	   {
    		   $.messager.defaults = { ok: "确定", cancel: "取消" }; 
    		   $.messager.alert("提示框","请选择要复制的专题！"); 
    	   }
       }
       function onselectScheduleStartDateSaveButton()
       {
    	   var dateOld = $('#dateOld').datebox('getValue');//源专题分组日期
           var dateNew = $('#dateNew').datebox('getValue');//目标专题分组日期
			
           if(dateOld!=null&&dateOld!=""&&dateNew!=null&&dateNew!="")
           {
				$('#selectScheduleStartDateDialog').dialog('close');
				copyStudyPlan(true,dateOld,dateNew);
			
           }else
           {
				//停在原页面
        	   $.messager.defaults = { ok: "确定", cancel: "取消" }; 
				 $.messager.alert("提示框","请填写完整！"); 
           }
       }
       function onselectScheduleStartDateCancelButton()
       {
    	   $.messager.defaults = { ok: "是", cancel: "否" }; 
           $.messager.confirm("确认对话框","是否取消日程的复制？",function(r)
           {
               if(r)
               {
            	   copyStudyPlan(false);
            	   $('#selectScheduleStartDateDialog').dialog('close');
               }
               
            	 //停在原页面
            	   
                
           });
    	  
       }
       function copyStudyPlan(flag,dateOld,dateNew)
       {
           var row = $('#studyPlanTable').datagrid("getSelected");
           // widow.location.url=sybp()+'/tblStudyPlanAction_copyStudyPlan.action?studyNoPara="+$('#studyNoPara').val()+"&destStudyPlanNo="+row.studyNo;
		   $.ajax({
				url:sybp()+'/tblStudyPlanAction_copyStudyPlan.action?dateOld='+encodeURIComponent(dateOld)+'&dateNew='+encodeURIComponent(dateNew),
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				type:'post',
				data:{
	   				  studyNoPara:$('#studyNoPara').val(),
	   				  sourceStudyPlanNo:row.studyNo,
	   				  flag:flag,
				     },
				dataType:'json',
				success:function(r){
					if(r && r.success){
                    //  parent.showMessager(1,'复制成功',false,5000);
                    	$.messager.defaults = { ok: "确定", cancel: "取消" }; 
                    	$.messager.alert("成功","复制成功！");
					}else{
					 //  parent.parent.showMessager(3,'与数据库交互错误',false,5000);
					 $.messager.defaults = { ok: "确定", cancel: "取消" }; 
						$.messager.alert("失败","复制失败！"); 
					}
				},
				error:function(r){
					$.messager.defaults = { ok: "确定", cancel: "否" }; 
					$.messager.alert("失败","复制失败！"); 
				}
		   });
       }
    </script>
  </head>
  <body >
  <div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
  <!--   专题成员标记 -->
   <s:hidden id="Automatic" name="Automatic"></s:hidden>
    <s:hidden id="left_member" name="left_member"></s:hidden>
  	<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
  	<s:hidden id="studyState" name="tblStudyPlan.studyState"></s:hidden>
  	<s:hidden id="scheduleState" name="tblStudyPlan.scheduleState"></s:hidden>
  	<s:hidden id="isAnimalIdES" name="isAnimalIdES"></s:hidden>
  	<s:hidden id="toWhere" name="toWhere"></s:hidden>
  	<s:hidden id="reason" name="reason"></s:hidden>
  	<s:hidden id="applyFlag"></s:hidden>
  	<s:hidden id="role" name="role"></s:hidden>
  	<s:hidden id="SD" name="SD"></s:hidden>
  	<s:hidden id="hasAnimal" name="hasAnimal"></s:hidden>
  	<s:hidden id="continueFlag"></s:hidden>
  	<div region="north" title="" style="height:30px;">
	  	<a id="a" href="${pageContext.request.contextPath}/homeAction_leftStudyPlan.action" target="left" ></a>
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu0',iconCls:'icon-edit'" onclick="">专题管理</a>  
	       	<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu1'" onclick="">动物信息</a>  
	       	<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu2'" onclick="">系统</a>  
			<div id="menu0"  class="easyui-menu" data-options="minWidth:'80px'">   
				<div id="addNewStudyPlanButton" data-options="iconCls:'icon-add'" style="width:110px;"  onclick="addNewStudyPlanDialog();">新建专题</div> 
				
				<a id="qianziStudyPlanButton"   onclick="checkBeforeStudyPlanSign();" style="width:110px;"  data-options="iconCls:'icon-ok'" class="easyui-linkbutton" plain="true">提交专题信息</a>  
				<br/>
				<a id="applyReviseButton"    onclick="applyRevise();" style="width:110px;"  data-options="iconCls:'icon-tablerefresh'" class="easyui-linkbutton" plain="true">修改专题信息</a>
				<br/>
				<p style="border-top:1px solid #e3e6eb;height:0px;"></p>
				
				<a id="signSchedulePlanButton"   onclick="signSchedulePlan();" data-options="iconCls:'icon-ok',plain:true" class="easyui-linkbutton" plain="true">提交日程</a>
				<br/>
				<a id="applyChangeButton"   onclick="applyChangeSchedule();" data-options="iconCls:'icon-tablerefresh'" class="easyui-linkbutton" plain="true">修改日程</a>  
				<br/>
				<p style="border-top:1px solid #e3e6eb;height:0px;"></p>
					
				<div id="clinicalTestMenuItem" data-options="iconCls:''" onclick="addNewClinicalTestReqTab();" style="width:110px;">临床检验管理</div> 
				<div id="lTiprpAppIndMenuItem" data-options="iconCls:''" onclick="addNewlTiprpAppInd();" style="width:110px;">胶囊配制申请</div>
				<div id="addNewAnatomyReqMenuItem" data-options="iconCls:''" onclick="addNewAnatomyReq();" style="width:110px;">解剖申请</div>
				
				<!-- 专题成员 -->
				<p style="border-top:1px solid #e3e6eb;height:0px;"></p>
				<div id="" data-options="iconCls:''" onclick="StudyMemberTab();" style="width:110px;">专题成员</div>
			</div>
			<div id="menu1"  class="easyui-menu" data-options="minWidth:'100px'">   
				<div id="animalMenuItem" data-options="iconCls:''" onclick="addNewAnimalTab();" style="width:100px;">动物信息</div>   
				<div id="animalWeighInd" data-options="iconCls:''" onclick="addNewWeighIndTab();" style="width:100px;">动物体重</div>  
			</div> 
			<div id="menu2"  class="easyui-menu" data-options="minWidth:'100px'">   
				<div id="logMenuItem" data-options="iconCls:''" style="width:100px;" onclick="addNewLogTab();">系统日志</div>   
			</div> 
  		</div>
  	</div>
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="studyPlanTabs" class="easyui-tabs" fit="true" border="false">
			<div title="专题基本信息" border="false" style="overflow: hidden;">
				<!-- 工具栏（签字）开始 -->
				<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
				<!-- 
					<a id="qianziStudyPlanButton" class="easyui-linkbutton" plain="true"  onclick="checkBeforeStudyPlanSign();" data-options="iconCls:'icon-ok'">确认专题信息录入完毕</a>
				 -->
					<a id="editStudyPlanButton" class="easyui-linkbutton" plain="true" onclick="beforeOpenStudyPlanAddEditDialog_edit('${studyNoPara}');" data-options="iconCls:'icon-edit'">编辑</a>
					<!-- 
					<a id="applyReviseButton" class="easyui-linkbutton" plain="true"  onclick="applyRevise();" data-options="iconCls:'icon-tablerefresh'">申请修改</a>
					 -->
					<a id="approvalReviseButton" style="display:none;" class="easyui-linkbutton" plain="true"  onclick="approvalRevise();" data-options="iconCls:'icon-tableedit'">批准修改</a>
					<a id="copyFromExistStudyButton" class="easyui-linkbutton" plain="true"  onclick="copyFromExistStudy();" data-options="iconCls:'icon-edit'">从已有的专题复制</a>
					<span id="submitAndApplyChangeStatue" style="position:relative;left: 30px;"></span>
				</div>
				<!-- 工具栏结束 -->
				  <!-- 表格开始-->
				<div class="view_table">
                <table class="studyplan">
                    <tr><td width="100px;" >专题编号</td>
                        <td width="200px;">
                        	<s:property value="tblStudyPlan.studyNo"/>
                        </td>
                    </tr>
                    <tr><td>专题类别</td>
                        <td>
                        	<s:property value="tblStudyPlan.studyName"/>
						</td>
                    </tr>
                    <tr><td>专题负责人</td>
                        <td>
							<s:property value="tblStudyPlan.studydirector"/>
						</td>
                    </tr>
                    <tr><td>是否GLP</td>
                        <td>
							<s:if test="tblStudyPlan.isGLP == 1">是</s:if>
							<s:else>否</s:else>
						</td>
                    </tr>
                    <tr><td>动物种类</td>
                        <td>
							<s:property value="tblStudyPlan.animalType"/>
						</td>
                    </tr>
                    <tr><td>动物品系</td>
                        <td>
							<s:property value="tblStudyPlan.animalStrain"/>
						</td>
                    </tr>
                    
                    <tr><td>试验启动日期</td>
                        <td>
							<s:date name="tblStudyPlan.studyStartDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                     <tr><td>试验分组日期</td>
                        <td>
							<s:date name="tblStudyPlan.studyGroupingDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>动物引入日期</td>
                        <td>
							<s:date name="tblStudyPlan.animalImportDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>预试验日期</td>
                        <td>
							<s:date name="tblStudyPlan.preStudyDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>正式试验时期</td>
                        <td>
							<s:date name="tblStudyPlan.studyBeginDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>剂量单位</td>
                        <td>
							<s:property value="tblStudyPlan.dosageUnit"/>
						</td>
                    </tr>
                    <!-- 
                    <tr><td>QA负责人</td>
                        <td>
							<s:property value="tblStudyPlan.qa"/>
						</td>
                    </tr>
                    <tr><td>病理负责人</td>
                        <td>
							<s:property value="tblStudyPlan.pathDirector"/>
						</td>
                    </tr>
                    <tr><td>临检负责人</td>
                        <td>
							<s:property value="tblStudyPlan.clinicalTestDirector"/>
						</td>
                    </tr>
                     -->
                    <tr><td>试验状态</td>
                        <td>
							<s:if test="tblStudyPlan.studyState == 0">尚未启动</s:if>
							<s:else>试验启动</s:else>
						</td>
                    </tr>
                    <tr bgcolor="#dddddd"><td>是否验证试验</td>
                        <td>
							<s:if test="tblStudyPlan.isValidation == 1">是</s:if>
							<s:else>否</s:else>
						</td>
                    </tr>
                </table>
            </div>
            <!-- 表格结束 -->
			</div>
			<div title="剂量设置" border="false" style="overflow: hidden;" >
			</div>
			<div title="解剖计划" border="false" style="overflow: hidden;">
			</div>
			<div title="检验指标" border="false" style="overflow: hidden;">
				检验指标
			</div>
			
			<div title="病理计划" border="false" style="overflow: hidden;" >
				病理计划
			</div>
			<div title="日程安排" border="false" style="overflow: hidden;" >
				日程安排
			</div>
		</div>
 	 </div>
 	  <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 	  <%@include file="/WEB-INF/jsp/studyPlanAction/studyPlanAddNo.jspf" %>
	  <%@include file="/WEB-INF/jsp/studyPlanAction/studyPlanAddEdit.jspf" %>
	  <%@include file="/WEB-INF/jsp/studyPlanAction/studyTypeSelect.jspf" %>
	  <%@include file="/WEB-INF/jsp/studyPlanAction/copyFromExistStudy.jspf" %>
	  <%@include file="/WEB-INF/jsp/studyPlanAction/schedulePlanStartDateEdit.jspf" %>
	  <%@include file="/WEB-INF/jsp/schedulePlan/chooseApprovalChangeItem.jspf" %>
	  <%@include file="/WEB-INF/jsp/schedulePlan/chooseApplyChangeItem.jspf" %>
	  <%@include file="/WEB-INF/jsp/schedulePlan/chooseCommitItem.jspf" %>
	  <%@include file="/WEB-INF/jsp/studyPlanAction/chooseDosesettingEffectiveDate.jspf" %>
	</div>
  </body>
</html>
