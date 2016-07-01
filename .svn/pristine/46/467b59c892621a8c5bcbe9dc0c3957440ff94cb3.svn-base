<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查计划</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js"></script>
<script type="text/javascript">
	function editQAChkPlan()
	{
		document.getElementById("addQAChkPlanDialog2").display="block";
		
		$('#qaChkPlanList').datagrid('reload');
		$('#oneChkPlanVersion').combobox('reload');
		clickManul();
		initChkPlanChildButton();
		var scheduleChanged = $('#scheduleChanged').val();
		if(scheduleChanged=='true')
		{
			$.messager.confirm('确认框','根据日程重新生成检查计划?',function(r){
			    if (r){
			    	$('#scheduleChanged').val('false');
			       //备份现有的,并把专题检查索引中的计划状态改成草稿
			    	$.ajax({
						url:sybp()+'/qAChkPlanAction_backupStudyPlan.action?studyNoParam='+$('#studyNoParamForList').val(),
						type:'post',
						//data:$('#oneQAChkPlan').serialize(),
						dataType:'json',
						success:function(){
							$('#chkPlanStateForList').val(0);//检查计划变更草稿状态
							
			    			clickManul();
			    			$('#addQAChkPlanDialog').dialog('open');			
						}
					});
			    }else{
			    	$.messager.alert('提示',"请处理日程变更");
			    }  
			});
		}else{
			clickManul();
			$('#addQAChkPlanDialog').dialog('open');
		}
		

	}
	
	function saveManyQAChkPlan()
	{
		var generateType = $('input[name="generatePlanStyle"]:checked').val();
		if(generateType==1)
		{
			var roots = $('#scheduleDatagrid').treegrid('getRoots');
			if(roots==null||roots==''){
				$.messager.alert('提示框','该试验没有日程设置！');
			}else{
				//根据日程生成计划之前
				if($('#lastEditTreegridIndex').val()!='')
		        {
		        	var ed = $("#scheduleDatagrid").treegrid('getEditor', {index:$('#lastEditTreegridIndex').val(),field:'planChkArea'});
					if(ed!=null)
					{
						var planChkArea = $(ed.target).combotree('getText');
						$('#treeChangesPlanChkArea').val(planChkArea);
									
					}
		        				
		        	$('#scheduleDatagrid').treegrid('endEdit', $('#lastEditTreegridIndex').val());
		        }	  
				generatePlanBySchedule();
			}
		}else if(generateType==3){
			var rows = $('#studyPeriodDatagrid').datagrid('getRows');
			if(rows==null||rows==''){
				$.messager.alert('提示框','该试验没有检查项的设置！');
			}else{
				//根据设置生成计划之前
				if($('#lastEditDictDatagridIndex').val()!='')
		        {
		        	var ed = $('#studyPeriodDatagrid').datagrid('getEditor', {index:$('#lastEditDictDatagridIndex').val(),field:'planChkArea'});
					if(ed!=null)
					{
						var planChkArea = $(ed.target).combotree('getText');
						$('#leftChangesPlanChkArea').val(planChkArea);
									
					}
		        				
		        	$('#studyPeriodDatagrid').datagrid('endEdit', $('#lastEditDictDatagridIndex').val());
		        }
				generatePlanByStudyType();
			}
		}
		
	}
	
	function saveQAChkPlan()
	{
		//var schedule = $('#scheduleDatagrid').treegrid('getSelected');
		//var item = $('#checkItemDatagrid').treegrid('getSelected');
		
		var oneScheduleName = $('#oneScheduleName').val();
		var oneScheduleTime = $('#oneScheduleTime').datebox('getValue');
		var onePlanChkTime = $('#onePlanChkTime').datebox('getValue');
		var oneChkItemName = $('#oneChkItemName').val();
		//alert(oneScheduleTime+"=="+oneScheduleName+"=="+onePlanChkTime+"=="+oneChkItemName);
		if(oneScheduleTime!=null&&oneScheduleTime!=''
			&&oneScheduleName!=null&&oneScheduleName!=''
			&&onePlanChkTime!=null&&onePlanChkTime!=''
			&&oneChkItemName!=null&&oneChkItemName!='')
		{
			
			var plan = $('#onePlanChkTime').datebox('getValue');
			
			var today = $('#planStartDateValue').val();
			if(dateCompare(today,plan))
			{
				var actionName="qAChkPlanAction";
				var chkPlanChange=$('#chkPlanChange').val();
				if(chkPlanChange==0)
				{
					actionName = "qAChkPlanHisAction";
				}
				
				//传值之前赋值
				$('#onePlanChkArea2').val($('#onePlanChkArea').combotree('getText'));								
				$.ajax({
					url:sybp()+'/'+actionName+'_save.action?studyNoParam='+$('#studyNoParamForList').val()+'&SOPFlag='+$('#oneSOPFlag').combobox('getValue'),
					type:'post',
					data:$('#oneQAChkPlan').serialize(),
					dataType:'json',
					success:function(){
						$('#qaChkPlanList').datagrid('reload');	
						initChkPlanChildButton();	
						//var oneScheduleName = $('#oneScheduleName').combobox('setValue','');
						//var oneScheduleTime = $('#oneScheduleTime').datebox('setValue','');
						//var onePlanChkTime = $('#onePlanChkTime').datebox('setValue','');
						$('#oneChkItemName').val('');				
						$('#checkItemDatagrid').datagrid('unselectAll');		
					}
				});
			}else
			{
				$.messager.alert("提示","计划检查日期必须大于当前日期");
			}
		}else
		{
			$.messager.alert("提示","请选择日程和检查项");
		}
		
	}
	function delPlan(chkPlanId)
	{
		var actionName="qAChkPlanAction";
		var chkPlanChange=$('#chkPlanChange').val();
		if(chkPlanChange==0)
		{
			actionName = "qAChkPlanHisAction";
		}	
		
		$.ajax({
			url:sybp()+'/'+actionName+'_del.action?chkPlanId='+chkPlanId,
			type:'post',
			dataType:'json',
			success:function(r){
					if(r)
					{
						if(r.success)
						{
							var delRow = $('#qaChkPlanList').datagrid('getSelected');
							var index =  $('#qaChkPlanList').datagrid('getRowIndex',delRow);
							$('#qaChkPlanList').datagrid('deleteRow',index);
						}else if(!r.success)
						{
							$.messager.alert("提示",r.msg);
						}
						
					}
			
			}
			
		});
		 

	}
	function commitQAChkPlan()
	{ 	
	 
		var dealScheduleChange=0;
		var scheduleChanged = $('#scheduleChanged').val();
		if(scheduleChanged=='true')
		{
			dealScheduleChange=1;	
		}
		$('#addQAChkPlanDialog').dialog('close');
		
		$.ajax({
			url:sybp()+'/qAChkPlanAction_commitPlans.action?studyNoParam='+$('#studyNoParamForList').val()+'&dealScheduleChange='+dealScheduleChange,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r&&r.success)
				{
					//$.messager.alert("提交成功","提交成功");
					$.messager.show({
						title:'提交成功',
						msg:'提交成功',
						showType:'show',
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
					
					$('#chkPlanStateForList').val(1);
					var  state=$('#chkPlanStateForList').val();
					var chkPlanChange=$('#chkPlanChange').val();
		
	    			////0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改  
	    			//更新上面的台头
	    			if($('#studyNoParamForList').val()!=null&&$('#studyNoParamForList').val()!='')
	    			{
	        			//alert("==="+state);
		    			var label = "专题编号："+$('#studyNoParamForList').val();
						if(state==null||state==''||state==0){
							label += "&nbsp;&nbsp;&nbsp;检查计划未制定完成";
						}else if(state==1){
							label += "&nbsp;&nbsp;&nbsp;检查计划待审批";
						}else if(state==-1){
							label += "&nbsp;&nbsp;&nbsp;检查计划审批不通过";
						}else if(state==2){	
							label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
							if(chkPlanChange==0){	
								label += "&nbsp;&nbsp;&nbsp;检查计划申请变更";
							}else if(chkPlanChange==1){
								label += "&nbsp;&nbsp;&nbsp;提交检查计划变更申请";
							}
						}
						
						label="<h4>"+label+"</h4>";
						parent.parent.parent.$('#topInfoLabel').html(label);
	    			}
					initChkPlanChildButton();
					
					//$('#qaChkPlanDatagrid').datagrid('reload');
					searchPlanList();
					//发送邮件
					//r.msgTitle r.msgContent r.receiverList
					//alert(r.msgTitle+"===="+r.msgContent+"==="+r.receiverList);
					var msg="提交检查计划后发邮件出现错误！";
					sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					  
					
				}else if(r&&!r.success)
				{
					$.messager.alert("提交失败",r.msg);
				}
			}
		});
	
		
	}
	function startCheck(oneChkPlanId)
	{
		if(oneChkPlanId.substring(0,6)=='second')//second1是跳转到第二项的第一页，second2是跳转到第二项的第二页，second4是跳转到第二项的第四页
		{
			if($('#QAMainPage').val()=='')//子页面的开始检查，现在子页面只是检查计划
			{
				$.ajax({
					url:sybp()+'/qAChkPlanAction_putChkPlanIdInSession.action?oneChildChkPlanId='+oneChkPlanId,
					type:'post',
					dataType:'json',
					success:function(){
						if(oneChkPlanId.substring(6,7)==1)
						{
							parent.$('#researchCheckTabs').tabs('select','方案/报告检查');
						}else if(oneChkPlanId.substring(6,7)==2){
							//parent.$('#researchCheckTabs').tabs('select','检查计划');//子页面中如果是检查计划页面，就不用跳转了
						}else if(oneChkPlanId.substring(6,7)==5){
							parent.$('#researchCheckTabs').tabs('select','检查报告');
						}else if(oneChkPlanId.substring(6,7)==3){
							parent.parent.$('#indexTabsDiv').tabs('select','文件管理');
						}
					
					}
				});
			}else
			{
				$.ajax({
					url:sybp()+'/qAChkPlanAction_putChkPlanIdInSession.action?oneChkPlanId='+oneChkPlanId,
					type:'post',
					dataType:'json',
					success:function(r){
						if(oneChkPlanId.substring(6,7)==3){
							parent.$('#indexTabsDiv').tabs('select','文件管理');
						}else{
							parent.$('#indexTabsDiv').tabs('select','基于研究的检查');
						
						}
						
						
					}
				});
			
		
			}
			
		}else{
			if($('#QAMainPage').val()=='')//子页面的开始检查
			{
				$.ajax({
					url:sybp()+'/qAChkPlanAction_putChkPlanIdInSession.action?oneChildChkPlanId='+oneChkPlanId,
					type:'post',
					dataType:'json',
					success:function(r){
						parent.$('#researchCheckTabs').tabs('select','检查记录');
					}
				});
			}else
			{
				//把chkPlanId放入Session中
				$.ajax({
					url:sybp()+'/qAChkPlanAction_putChkPlanIdInSession.action?oneChkPlanId='+oneChkPlanId,
					type:'post',
					dataType:'json',
					success:function(r){
						parent.$('#indexTabsDiv').tabs('select','基于研究的检查');
					}
				});
			
	
				
			}
		}
		
	}
	function generatePlanBySchedule()//根据日程自动生成计划
	{
		$.messager.confirm('确认框', '根据日程自动生成检查计划会丢失已经生成的数据?确定要继续吗？', function(r){
			if (r){
				$.messager.progress({title: '请稍后',
					msg: '处理中...',text:''});
				
				var actionName="qAChkPlanAction";
				var chkPlanChange=$('#chkPlanChange').val();
				if(chkPlanChange==0)
				{
					actionName = "qAChkPlanHisAction";
				}
				var roots = $('#scheduleDatagrid').treegrid('getRoots');
				var planChkAreas = "";
				for(var i=0;i<roots.length;i++){
					planChkAreas+=(roots[i].planChkArea)+" ,";
				}
				$.ajax({
					url:sybp()+'/'+actionName+'_generatePlanBySchedule.action?studyNoParam='+$('#studyNoParamForList').val()+'&SOPFlag='+$('#oneSOPFlag').combobox('getValue'),
					type:'post',
					data:{planChkAreas:planChkAreas},
					dataType:'json',
					success:function(r){
						$.messager.progress('close');
						if(r&&r.success)
						{
							//$.messager.alert("提示","成功");
							var record =  $('#oneChkPlanVersion').combobox('getValue');
							$('#qaChkPlanList').datagrid({
								//url:sybp()+'/'+actionName+'_getPlanListByCondition.action?studyNoParam='+$('#studyNoParamForList').val()+'&QAMainPage='+$('#QAMainPage').val(),
								url:sybp()+'/'+actionName+'_getPlanListByVersion.action?studyNoParam='+$('#studyNoParamForList').val()+'&versionStr='+record,
							});
							$('#qaChkPlanList').datagrid('reload');
							
						}else if(r&&!r.success)
						{
							$.messager.alert("提交失败",r.msg);
						}
					}
				});

			}
		});
		
	}
	
	function generatePlanByStudyType()
	{
		$.messager.confirm('确认框', '根据试验周期和试验检查项自动生成检查计划会丢失已经生成的数据?确定要继续吗？', function(r){
			if (r){
				$.messager.progress({title: '请稍后',
						msg: '处理中...',text:''});
				var actionName="qAChkPlanAction";
				var chkPlanChange=$('#chkPlanChange').val();
				if(chkPlanChange==0)
				{
					actionName = "qAChkPlanHisAction";
				}	
				//开始日期
				var rows = $('#studyPeriodDatagrid').datagrid('getRows');
				var dictChkItemsStartDateList = new Array();
				var planChkAreas = "";
				for(var i=0;i<rows.length;i++){
					dictChkItemsStartDateList.push(rows[i].dictChkItemsStartDate);
					planChkAreas+=(rows[i].planChkArea)+" ,";
				}
				$.ajax({
					url:sybp()+'/'+actionName+'_generatePlanByStudyType.action?studyNoParam='+$('#studyNoParamForList').val()+'&startDate='+$('#oneStartDate').datebox('getValue')+'&dictChkItemsStartDateList='+dictChkItemsStartDateList+'&SOPFlag='+$('#oneSOPFlag').combobox('getValue'),
					type:'post',
					data:{planChkAreas:planChkAreas},
					dataType:'json',
					success:function(r){
						$.messager.progress('close');
						
						if(r&&r.success)
						{
							//$.messager.alert("提示","成功");
							var record = $('#oneChkPlanVersion').combobox('getValue');
							$('#qaChkPlanList').datagrid({
								//url:sybp()+'/'+actionName+'_getPlanListByCondition.action?studyNoParam='+$('#studyNoParamForList').val()+'&QAMainPage='+$('#QAMainPage').val(),
								url:sybp()+'/'+actionName+'_getPlanListByVersion.action?studyNoParam='+$('#studyNoParamForList').val()+'&versionStr='+record,
							});
							$('#qaChkPlanList').datagrid('reload');
							
						}else if(r&&!r.success)
						{
							$.messager.alert("提交失败",r.msg);
						}
					}
				});

			}
		});
	
	}
	
	//比较时间大小
    function dateCompare(startdate,enddate){  
         var arr=startdate.split("-");    
         var starttime=new Date(arr[0],arr[1],arr[2]);    
         var starttimes=starttime.getTime();   
         var arrs=enddate.split("-");    
         var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
         var lktimes=lktime.getTime();   
         if(starttimes>lktimes){   
            return false;   
         }else  {
            return true;   
         }  
    }  
    //重新任命计划检查人员
    function appointChkPlanQA(addOrAgain,chkPlanId)
    {
    	//addOrAgain: add again temp
    	//alert(chkPlanId);
    	 //初始化QA下拉选   	  
    	$('#selectQAinput').combobox({
    		url:sybp()+'/tblAppointSDAction_selectQAinputCombobox.action',
    		valueField:'id',
    		textField:'text',
    		required: true,    
    		onSelect: function(record){    

    		}
    	});
    		
         $('#selectQAAddOrEdit').val(addOrAgain);
         //$('#QAStudyNo').val(studyNo)
         $('#chkPlanId').val(chkPlanId);
         
         if(addOrAgain=='add')
         {
         	$('#selectPlanQADialog').dialog('setTitle','任命临时检查员');
         }else if (addOrAgain=='again'){
         	$('#selectPlanQADialog').dialog('setTitle','任命临时检查员');
         }else if(addOrAgain=='temp'){
         	$('#selectPlanQADialog').dialog('setTitle','临时检查员申请');
         }
         $('#selectPlanQADialog').dialog('open');
         
    	
    }
    function saveAppointQA()
    {
    	// if( $('#selectQAForm').form('validate') ){
             var addOrEdit = $('#selectQAAddOrEdit').val();
            var row =$('#qaChkPlanDatagrid').datagrid('getSelected');
            
             var qa = $('#selectQAinput').combobox('getValue');//QA
             var qaL = $('#selectQAinput').combobox('getText');//QA
			 var con = true;
			 if(qa==null||qa=='')
			 {
				 $.messager.alert('提示框','请选择一个QA');
			 }else{
			 	
			 	if(row!=null&&row.planChkOperator==qaL)
			 	{
			 		 $.messager.alert('提示框','临时负责人和计划负责人不能是同一个人！');
			 	}else{
		             if(addOrEdit == "add"||addOrEdit == "again"){
						$.messager.confirm('提示框','确定要让 '+qaL+' 做该检查计划吗？',function(r){
						    if (r){
						    	qm_showQianmingDialog('afterSignSaveAppointQA');
						    }
			             }//confirm结束
						);
		             }else if(addOrEdit=='temp'){
		             	$.messager.confirm('提示框','确定申请临时让 '+qaL+' 做该检查计划吗？',function(r){
						    if (r){
						    	qm_showQianmingDialog('afterSignSavePlanTempQA');
						    }
			             }//confirm结束
						);
		             }
		             
		        }
	             
			 }
    }
   function afterSignSaveAppointQA()
   {
	   var qa = $('#selectQAinput').combobox('getValue');//QA
	   var qaName = $('#selectQAinput').combobox('getText');
       var chkPlanId= $('#chkPlanId').val();
     
        $.ajax({
   	 	  	url : sybp()+'/qAChkPlanAction_toAppointQA.action',
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: {
   		  		qa:qa,
   		  		chkPlanId:chkPlanId
   		  	},
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
   		  	    	 $('#selectPlanQADialog').dialog('close');
   		  	    	 
       		  		//parent.parent.showMessager(1,'任命成功',true,5000);
       		  		  // $.messager.confirm('确认','是否签字提交!',function(r){    
		                	     
		               	//qm_showQianmingDialog('afterSaveSubmitAppointQA');
		                			 
		                		 
					     // }); 
					     // $.messager.defaults = { ok: "确定", cancel: "取消" };
   		  	    		var row = $('#qaChkPlanDatagrid').datagrid('getSelected');
   		  	    		var index = $('#qaChkPlanDatagrid').datagrid('getRowIndex',row);
   		  	    		$('#qaChkPlanDatagrid').datagrid('updateRow',{
   		  	    					index: index,
   		  	    					row: {
		        		  	    			planChkOperator:qaName,
   		  	    					}
   		  	    		});
   		  	    		
   		  	    		var msg='指派某一检查计划检查员后发邮件出现错误！';
						sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					
   		  	        }else if(r.error){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   	}); 
   }
   //临时检查人申请
   function afterSignSavePlanTempQA()
   {
   		var qa = $('#selectQAinput').combobox('getValue');//QA
	   var qaName = $('#selectQAinput').combobox('getText');
       var chkPlanId= $('#chkPlanId').val();
     
        $.ajax({
   	 	  	url : sybp()+'/qAChkPlanAction_applyTempChkOperator.action',
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: {
   		  		qa:qa,
   		  		chkPlanId:chkPlanId
   		  	},
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
   		  	    	 $('#selectPlanQADialog').dialog('close');
   		  	    	 
   		  	    		var row = $('#qaChkPlanDatagrid').datagrid('getSelected');
   		  	    		var index = $('#qaChkPlanDatagrid').datagrid('getRowIndex',row);
   		  	    		$('#qaChkPlanDatagrid').datagrid('updateRow',{
   		  	    			index: index,
   		  	    			row: {
		        		  	    tempChkOperator:qaName,	
		        		  	    tempChkOperatorFlag:1,
   		  	    			}
   		  	    		});
   		  	    		
   		  	    		var msg='申请临时检查计划检查员后发邮件出现错误！';
						sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					
   		  	        }else if(r.error){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		}); 
   }
   //QAM快捷任命专题QA
   function qamQuickAppointQA(itemId,studyNoP)
   {
   	  if($('#QAMainPage').val()=='')//子页面
   	  {
   	  	 parent.parent.onAppointQAButton(itemId,studyNoP,1);
   	  }else{
      	 parent.parent.onAppointQAButton(itemId,studyNoP,2);
      }
   }
   
   //QAM审批临时检查人申请
   function approvalTempChkPlanQA(result,chkPlanId)
   {
   	  //2同意 -1拒绝
   	  $('#resultLabel').val(result);
   	  $('#chkPlanId').val(chkPlanId);
   	  
   	  if(result==2){
   	  		qm_showQianmingDialog('afterSignApprovalTempQA');
   	  }else if(result==-1){
   	  		writeRejectTempQAReason();
   	  }
   	  
   }
   function writeRejectTempQAReason()
   {
   		$.messager.prompt('提示信息', '请填写退回原因：', function(r){
			if (r){
				if(r != ""){
			    	  var reason = r;
			    	  $('#reason').val(reason);
			    		
			    	  qm_showQianmingDialog('afterSignApprovalTempQA');
			    }
			}else{
			        if(r == ""){
			             $.messager.defaults = { ok: "是", cancel: "否" }; 
			             $.messager.confirm('确认对话框', '请填写申请检查计划变更原因，是否继续执行申请修改操作？', function(r2){
			    			 if (r2){
			    				  writeRejectTempQAReason();
			        	  	 }
			    	  	 }); 
			    	} 
			}
			    			     
		});
		
		
   }
   
   function afterSignApprovalTempQA(){
   		 var reason = $('#reason').val();
   		   $.ajax({
	   	 	  	url : sybp()+'/qAChkPlanAction_approvalTempChkOperator.action?result='+$('#resultLabel').val(),
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
	   		  		reason:reason,
	   		  		chkPlanId:$('#chkPlanId').val()
	   		  	},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	
	   		  		if(r&&r.success){
	   		  			var row = $('#qaChkPlanDatagrid').datagrid('getSelected');
   		  	    		var index = $('#qaChkPlanDatagrid').datagrid('getRowIndex',row);
   		  	    		$('#qaChkPlanDatagrid').datagrid('updateRow',{
   		  	    			index: index,
   		  	    			row: {
		        		  	    planChkOperator:r.planChkOperator,
		        		  	    tempChkOperatorFlag:$('#resultLabel').val()
   		  	    			}
   		  	    		});
	   		  		}
	   		  	}
   		  	});
   
   }
	function searchPlanList()
    {
  		var start=$('#startDate').datebox('getValue');
		var end=$('#endDate').datebox('getValue');
		if(start==null||start=='')
		{
			start = $('#planStartDateValue').val();
		}
		if(end==null||end=='')
		{
			end = $('#planEndDateValue').val();
		}
		//alert(start+"==22searchPlanList222====="+end);
		//justChkPlan只有检查计划
		var justChkPlan = 0;
		if($('#justChkPlan').attr('checked')=='checked')
		{
			justChkPlan = 1;
		}
		 
		if(start!=null&&start!=''&&end!=null&&end!='')
		{
			var status = $('#status').combobox('getValue');
			var catalog = 0 ;
			if($('#QAMainPage').val()!='')
			{
			   catalog = $('#catalog').combobox('getValue');
			}
			var planSearcher = "";
			if($('#QAMainPage').val()!='')
			{
				planSearcher= $('#planSearcher').searchbox('getValue');
			}
			$('#qaChkPlanDatagrid').datagrid({
	    		url:sybp()+"/qAChkPlanAction_getPlanListByCondition.action?studyNoParam="+$('#studyNoParamForList').val()+'&QAMainPage='+$('#QAMainPage').val()+"&start="+start+"&end="+end+"&status="+status+"&catalog="+catalog+"&searchString="+planSearcher+"&justChkPlan="+justChkPlan,
			
	    	});
			//$('#qaChkPlanDatagrid').datagrid('reload');
		}
    }
    //提交审批
    function commitToQAM()
    {
    	var rows = $('#qaChkPlanList').datagrid('getRows');
    	if(rows==null||rows=='')
    	{
			$.messager.alert("提示","没有任何检查计划");
        }else{
        	//提交之前保存最后修改的计划
			if($('#lastEditDatagridIndex').val()!='')
			{
				var ed = $('#qaChkPlanList').datagrid('getEditor', {index:$('#lastEditDatagridIndex').val(),field:'planChkArea'});
				if(ed!=null)
				{
					var planChkArea = $(ed.target).combotree('getText');
					$('#changesPlanChkArea').val(planChkArea);
				}
				$('#qaChkPlanList').datagrid('endEdit', $('#lastEditDatagridIndex').val());
			}    
        
			var actionName = "qAChkPlanAction";
			var chkPlanChange=$('#chkPlanChange').val();
			//alert("chkPlanChange="+chkPlanChange);
			if(chkPlanChange==0)
			{	//提交的是变更
				actionName = "qAChkPlanHisAction";
			}
			//提交的是检查计划
	    	$.ajax({
		        url : sybp()+'/'+actionName+'_hasCommitted.action?studyNoParam='+$('#studyNoParamForList').val(),
		        type: 'post',
		        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			    dataType:'json',
			    success:function(r){
				      if(r&&!r.success)
				      {
							$.messager.alert("提示",r.msg);
					  }
				      else if(r&&r.success)
				      {
				        	qm_showQianmingDialog("afterSignCommitPlan");	
					  }
			   }
			});
		  
		  
        }
    		
    }
    
    function afterSignCommitPlan()
    {
    	var plans = $('#qaChkPlanList').datagrid('getData');
		//判断一下是否所有日程都已经存在检查计划，不存在的话提醒
		var dealScheduleChange=0;
		var scheduleChanged = $('#scheduleChanged').val();
		if(scheduleChanged=='true')
		{
			dealScheduleChange=1;	
		}
		$('#addQAChkPlanDialog').dialog('close');
		
		var actionName = "qAChkPlanAction";
		var chkPlanChange=$('#chkPlanChange').val();
		if(chkPlanChange==0)
		{	//提交的是变更
			actionName = "qAChkPlanHisAction";
		}
		
		  $.ajax({
	        	url : sybp()+'/'+actionName+'_commitPlans.action?studyNoParam='+$('#studyNoParamForList').val()+'&dealScheduleChange='+dealScheduleChange,
	        	type: 'post',
	        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		         dataType:'json',
		         success:function(r){
			        // alert(r);
			        	if(r&&r.success)
			        	{
			        		if(chkPlanChange==0)
			        		{
			        			$('#chkPlanChange').val(1);
			        		}else{
				        		$('#chkPlanStateForList').val(1);
			        		}
			        		
			        		$('#qaChkPlanDatagrid').datagrid('reload');
			        		var  state=$('#chkPlanStateForList').val();
			        		var changeState = $('#chkPlanChange').val();
			    			////0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改  
			    			//更新上面的台头
			    			//if($('#studyNoParamForList').val()!=null&&$('#studyNoParamForList').val()!='')
			    			//{
			        			//alert("==="+state);
				    			var label = "专题编号："+$('#studyNoParamForList').val();
								if(state==null||state==''||state==0){
									label += "&nbsp;&nbsp;&nbsp;检查计划未制定完成";
								}else if(state==1){
									label += "&nbsp;&nbsp;&nbsp;检查计划待审批";
								}else if(state==-1){
									label += "&nbsp;&nbsp;&nbsp;检查计划审批不通过";
								}else if(state==2){	
									label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
									if(changeState==1){
										label += "&nbsp;&nbsp;&nbsp;提交检查计划变更申请";
									}else if(changeState==0){
										label += "&nbsp;&nbsp;&nbsp;检查计划申请变更";
									}
								}
								
								
								label="<h4>"+label+"</h4>";
								parent.parent.parent.$('#topInfoLabel').html(label);
			    			//}
							initChkPlanChildButton();
							//发送邮件
							//r.msgTitle r.msgContent r.receiverList
							//alert("commitplans 222==="+r.msgTitle+"===="+r.msgContent+"==="+r.receiverList);
							var msg='提交检查计划后发邮件出现错误！';
							sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
							 
				        }
			        	if(r&&!r.success)
			        	{
							$.messager.alert("提示",r.msg);
				       	}
		         }
		  });
    }
    //申请变更
    function changeChkPlan()
    {
    	
    	$.ajax({
        	url : sybp()+'/qAChkPlanChangeIndexAction_isExistApply.action?studyNoParam='+$('#studyNoParamForList').val(),
        	type: 'post',
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	         dataType:'json',
	         success:function(r2){
		        	if(r2&&!r2.success)
		        	{
						$.messager.alert("提示",r2.msg);
			       	}
			       	if(r2&&r2.success){
			       		$.messager.prompt('提示信息', '是否确认申请检查计划变更!<br/>请填写申请原因：', function(r){
			    			if (r){
			    			     if(r != ""){
			    			        var reason = r;
			    			        $('#reason').val(reason);
			    			        
			    			        //申请的时候不用签名，提交的时候再签名
			    			        afterSignChangeChkPlan();
			    			       // qm_showQianmingDialog('afterSignChangeChkPlan');
			    			     }
			    			}else{
			    			         if(r == ""){
			    			              $.messager.defaults = { ok: "是", cancel: "否" }; 
			    			              $.messager.confirm('确认对话框', '请填写申请检查计划变更原因，是否继续执行申请修改操作？', function(r){
			    							  if (r){
			    								  changeChkPlan();
			    							  }
			    						  }); 
			    			         } 
			    			     }
			    		});
				    }
	         }
	  });
  	  
       

    }
    function afterSignChangeChkPlan()
    {
        var reason = $('#reason').val();
    	 //变更申请
   	 	$.ajax({
	        	url : sybp()+'/qAChkPlanChangeIndexAction_save.action?studyNoParam='+$('#studyNoParamForList').val(),
	        	type: 'post',
	        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	        	data:{reason:reason,},
		         dataType:'json',
		         success:function(r){
				       	if(r&&r.success){
				       		$('#chkPlanChange').val(0);//申请了变更
				       		$('#oneChkPlanVersion').combobox('reload');
				       		
				       		var  state=$('#chkPlanStateForList').val();
							var  chkPlanChange=$('#chkPlanChange').val();
			    			////0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 
			    			//更新上面的台头
			    			if($('#studyNoParamForList').val()!=null&&$('#studyNoParamForList').val()!='')
			    			{
			        			//alert("==="+state);
				    			var label = "专题编号："+$('#studyNoParamForList').val();
								if(state==null||state==''||state==0){
									label += "&nbsp;&nbsp;&nbsp;检查计划未制定完成";
								}else if(state==1){
									label += "&nbsp;&nbsp;&nbsp;检查计划待审批";
								}else if(state==-1){
									label += "&nbsp;&nbsp;&nbsp;检查计划审批不通过";
								}else if(state==2){	
									label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
									if(chkPlanChange==0){	
										label += "&nbsp;&nbsp;&nbsp;检查计划申请变更";
									}else if(chkPlanChange==1){	
										label += "&nbsp;&nbsp;&nbsp;提交检查计划变更申请";
									}
								}
								
								label="<h4>"+label+"</h4>";
								parent.parent.parent.$('#topInfoLabel').html(label);
			    			}
			    			
			    			clickManul();//更新Datagrid
			    			
							initChkPlanChildButton();
							//版本中加个-1的变更版本
							//$('#oneChkPlanVersion').combobox();
							
							//$('#qaChkPlanDatagrid').datagrid('reload');
							searchPlanList();
				       	
				       		//var msg='申请变更后发邮件出现错误！';
							//sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					    }else if(r&&!r.success){
							$.messager.alert("提示",r.msg);
				       	}
		         }
		  });
    }
    
    //QAM审批检查计划
    function approvalQAChkPlan()
    {
        if($('#studyNoParamForList').val()!='')
       {
	    	document.getElementById("approvalQAChkPlanDialog2").display="block";
        	var  state=$('#chkPlanStateForList').val();
			var  chkPlanChange=$('#chkPlanChange').val();
			var actionName = "qAChkPlanAction";
			if(state==2&&chkPlanChange==1){
				actionName = "qAChkPlanHisAction";
			}
							
			$('#approvalQAChkPlanList').datagrid({
				url : sybp()+'/'+actionName+'_getPlanList.action?studyNoParam='+$('#studyNoParamForList').val(),
				onLoadSuccess:function(data)
				{
					initApprovalQAChkPlanButton();
					//$('#approvalQAChkPlanList').datagrid('reload');
					var rows=$('#approvalQAChkPlanList').datagrid('getRows');
					
					if(rows!=null&&rows.length>0)
						$('#approvalQAChkPlanDialog').dialog('open');
					else{
						if(state==2&&chkPlanChange==1){
							$.messager.alert("提示","无检查计划变更记录需要审批！");
						}else{
							$.messager.alert("提示","无检查计划记录需要审批！");
						}
					}
				}
			});
			
        }else
        {
            $.messager.alert("提示","请选择左边专题");
        }
		
    }
    //QAM审批检查计划变更申请
    function approvalQAChkPlanApply(){
    	 if($('#studyNoParamForList').val()!='')
         {
	    	$.ajax({
	        	url : sybp()+'/qAChkPlanChangeIndexAction_getPlanApply.action?studyNoParam='+$('#studyNoParamForList').val(),
	        	type: 'post',
	        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		         dataType:'json',
		         success:function(r){
			        	if(r&&!r.success)
			        	{
							$.messager.alert("提示",r.msg);
				       	}
			        	if(r&&r.success)
				        {
			        		 $.messager.defaults = { ok: "批准", cancel: "退回" }; 
				              $.messager.confirm('确认对话框', '由于'+r.reason+'申请检查计划的变更，是否批准变更？', function(r3){
								  var changeState = 2;
								  if (r3){
									  changeState = 2;
									  qm_showQianmingDialog("afterSignApprovalApply");	
									  $('#changeStateLabel').val(changeState);
								  }else{
									  changeState = -1;
									  writeBackReason();
									  
								  }
								 
									
								  
							  }); 
				              $.messager.defaults = { ok: "确定", cancel: "取消" }; 
					    }
					    
		         }
		  	});
         }else
         {
             $.messager.alert("提示","请选择左边专题");
         }
    }
    
    function writeBackReason(){
        
    	 $.messager.prompt('提示信息', '是否确认退回检查计划修改!<br/>请填写退回原因：', function(r2){	
	    	if (r2){
			    var reason = r2;
			    $('#rejectReason').val(reason);
			    
			    $('#changeStateLabel').val(-1);
			    qm_showQianmingDialog("afterSignApprovalApply");
			}else if (r2==''){
				
		             $.messager.defaults = { ok: "是", cancel: "否" }; 
		             $.messager.confirm('确认对话框', '请填写退回检查计划原因，是否继续执行退回操作？', function(r4){
						  if (r4){
							  writeBackReason();
						  }
					  }); 
		          	
			}
			
    	 });
    	 $.messager.defaults = { ok: "确定", cancel: "取消" }; 
    }
    
	function afterSignApprovalApply()
	{
		var changeState =  $('#changeStateLabel').val();
		var rejectReason = $('#rejectReason').val();
		$.ajax({
        	url : sybp()+'/qAChkPlanChangeIndexAction_qamApprovalApply.action?studyNoParam='+$('#studyNoParamForList').val()+'&changeState='+changeState+'&reason='+rejectReason,
        	type: 'post',
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	        dataType:'json',
	        success:function(r){
    	       // alert(r+"=====sdfasdf"+r.state);
    	        if(r){
    	        	var state = r.state;
    	        	var chkPlanChange=$('#chkPlanChange').val();
    	        	
					var label = "专题编号："+$('#studyNoParamForList').val();
					if(state==null||state==''||state==0){
						label += "&nbsp;&nbsp;&nbsp;检查计划未制定完成";
					}else if(state==1){
						label += "&nbsp;&nbsp;&nbsp;检查计划待审批";
					}else if(state==-1){
						label += "&nbsp;&nbsp;&nbsp;检查计划审批不通过";
					}else if(state==2){	
						label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
						if(chkPlanChange==0){	
							label += "&nbsp;&nbsp;&nbsp;检查计划申请变更";
						}else if(chkPlanChange==1){	
							label += "&nbsp;&nbsp;&nbsp;提交检查计划变更申请";
						}
					}
					
					label="<h4>"+label+"</h4>";
					parent.parent.parent.$('#topInfoLabel').html(label);
					
					$('#approvalQAChkPlanApply').linkbutton('disable');
	    	        $('#qaChkPlanDatagrid').datagrid('reload');

	    	   
		       		var msg='QAM批复变更后发邮件出现错误！';
					sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
			    
    	        }
        	}
	  });
		  
	}
    
    function acceptChkPlanApply(result,quickOperateStudyNo,quickOperateChkPlanState,quickOperatechkPlanChange)
    {
    	$('#quickOperateStudyNo').val(quickOperateStudyNo);
		$('#quickOperateChkPlanState').val(quickOperateChkPlanState);
		$('#quickOperatechkPlanChange').val(quickOperatechkPlanChange);
		
        $('#resultLabel').val(result);
        if(result==-1)
        {
        	writeBackChkPlanReason();
        }else{
    		qm_showQianmingDialog("afterSignAcceptChkPlanApply");	
    	}
    }
    function writeBackChkPlanReason(){
        
    	 $.messager.prompt('提示信息', '是否确认退回检查计划!<br/>请填写退回原因：', function(r2){	
	    	if (r2){
			    var reason = r2;
			    $('#rejectReason').val(reason);
			    
			    $('#changeStateLabel').val(-1);
			    qm_showQianmingDialog("afterSignAcceptChkPlanApply");
			}else if (r2==''){
				
		             $.messager.defaults = { ok: "是", cancel: "否" }; 
		             $.messager.confirm('确认对话框', '请填写退回检查计划原因，是否继续执行退回操作？', function(r4){
						  if (r4){
							  writeBackChkPlanReason();
						  }
					  }); 
		          	
			}
			
    	 });
    	 $.messager.defaults = { ok: "确定", cancel: "取消" }; 
    }
    function afterSignAcceptChkPlanApply()
    {
        var result = $('#resultLabel').val();
        var reason = $('#rejectReason').val();
        
        $('#approvalQAChkPlanDialog').dialog('close');
    	//-1退回，2通过
    	var  state=$('#chkPlanStateForList').val();
		var  chkPlanChange=$('#chkPlanChange').val();
		var studyNoP = $('#studyNoParamForList').val();
		
		if($('#quickOperateStudyNo').val()!=''){
			studyNoP = $('#quickOperateStudyNo').val();
			state=$('#quickOperateChkPlanState').val();
			chkPlanChange=$('#quickOperatechkPlanChange').val();
		}
		var actionName = "qAChkPlanAction";
		if(state==2&&chkPlanChange==1){
			actionName = "qAChkPlanHisAction";
		}
   		 $.ajax({
	        	url : sybp()+'/'+actionName+'_saveChkPlanResult.action?studyNoParam='+studyNoP+'&status='+result,
	        	type: 'post',
	        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		         dataType:'json',
		         data:{reason:reason},
		         success:function(r){
			        	if(r&&!r.success)
			        	{
							$.messager.alert("提示",r.msg);
							$('#qaChkPlanDatagrid').datagrid('reload');
							
				       	}else if(r&&r.success)
			        	{
				       		$('#chkPlanStateForList').val(result);
				       		////0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改  
			    			//更新上面的台头
			        			//alert("==="+state);
			        			var state=result;
				    			var label = "专题编号："+$('#studyNoParamForList').val();
					       		if(state==-1){
					       			if(chkPlanChange!=1){
										label += "&nbsp;&nbsp;&nbsp;检查计划审批不通过";
									}else{
										label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
									}
								}else if(state==2){	
									label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
								}
								label="<h4>"+label+"</h4>";
								parent.parent.parent.$('#topInfoLabel').html(label);
								
								$('#approvalQAChkPlan').linkbutton('disable');
				       		$('#qaChkPlanDatagrid').datagrid('reload');

				       		//发送邮件
							//r.msgTitle r.msgContent r.receiverList
							//alert(r.msgTitle +"==="+r.msgContent+"==="+ r.receiverList);
							var msg='审批检查计划变更后发邮件出现错误！';
							sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
				       	}
				       	
		         }
		  });
    }
    function sendNotification(msgTitle,msgContent,receiverList,msg){
		if(receiverList!=null&&receiverList!=''){
			
			  $.ajax({
		      	url : sybp()+'/qAChkPlanAction_sendNotification.action',
		      	type: 'post',
		      	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			         dataType:'json',
			         data:{
		      			msgTitle:msgTitle,
		      			msgContent:msgContent,
		      			receiverList:receiverList,
				      },
			         success:function(r){
						if(r&&!r.success){
							$.messager.alert('提示框',msg);
						}
			         }
		    });
		}else{
			//$.messager.alert('提示框','发邮件没有接收人存在');
		}
    }
    
	//
   function clickManul()
   {
	  
		var chkPlanState = $('#chkPlanStateForList').val();
		var generateStyle = $('input[name="generatePlanStyle"]:checked').val();
   		var recordId = $('#oneChkPlanVersion').combobox('getValue');
   		 //alert(chkPlanState+"===="+generateStyle+"==="+recordId);
   		 
	    if((!isNaN(recordId)&&recordId==-1)//是数字的时候必须是-1变更版本
	        	||(isNaN(recordId)&&(chkPlanState=='' || chkPlanState=='0' || chkPlanState=='-1')))//是编辑状态下的最新版本
	    {
	        			
	        if(generateStyle==1)//试验日程
		    {
				$('#scheduleDatagrid').treegrid('reload');
				$('#checkItemDatagrid').datagrid('loadData',[]);
					   			
			}else if(generateStyle==2)//任务，手动
					{
						$('#taskDatagrid').treegrid('reload');
						$('#checkItemDatagrid').datagrid('loadData',[]);
							   		
					}else if(generateStyle==3)//试验周期
					{
						$('#studyPeriodDatagrid').datagrid('reload');   			
					}	            		
	        }else{
	        	if(generateStyle==1)//试验日程
				{
					$('#scheduleDatagrid').treegrid('loadData',[]);
					$('#checkItemDatagrid').datagrid('loadData',[]);
					   			
				}else if(generateStyle==2)//任务，手动
				{
					 $('#taskDatagrid').treegrid('loadData',[]);
					 $('#checkItemDatagrid').datagrid('loadData',[]);
					   		
				}else if(generateStyle==3)//试验周期
				{
					 $('#studyPeriodDatagrid').datagrid('loadData',[]);  
					 		
				}	
					
	       }
	   
   }
   function changeGeneratePlanStyle()
   {
   		var generateStyle = $('input[name="generatePlanStyle"]:checked').val();
   		if(generateStyle==1)//试验日程
   		{
   			$('#scheduleDatagridDiv').css('display','');
   			$('#scheduleDatagrid').treegrid('reload');
   			
   			$('#taskDatagridDiv').css('display','none');
   			$('#studyPeriodDatagridDiv').css('display','none');
   			
   			$('#checkItemDatagridDiv').css('display','');
   			$('#checkItemDatagrid').datagrid('loadData',[]);
   			
   			$('#saveManyQAChkPlanButton').linkbutton('enable');
   			$('#saveQAChkPlanButton').linkbutton('enable');
   			
   			$('#saveManyQAChkPlanButton').css('display','');
   			$('#saveQAChkPlanButton').css('display','');
   			$('#formDiv').css('display','');
   			$('#oneQAChkPlan')[0].reset();
   		}else if(generateStyle==2)//任务，手动
   		{
   			$('#scheduleDatagridDiv').css('display','none');
   			$('#taskDatagridDiv').css('display','');
   			$('#taskDatagrid').treegrid('reload');
   			
   			$('#studyPeriodDatagridDiv').css('display','none');
   			
   			$('#checkItemDatagridDiv').css('display','');
   			$('#checkItemDatagrid').datagrid('loadData',[]);
   		
   			$('#saveManyQAChkPlanButton').linkbutton('disable');
   			$('#saveQAChkPlanButton').linkbutton('enable');
   			
   			$('#saveManyQAChkPlanButton').css('display','none');
   			$('#saveQAChkPlanButton').css('display','');
   			$('#formDiv').css('display','');
   			$('#oneQAChkPlan')[0].reset();
   		}else if(generateStyle==3)//试验周期
   		{
   			$('#scheduleDatagridDiv').css('display','none');
   			$('#taskDatagridDiv').css('display','none');
   			$('#studyPeriodDatagridDiv').css('display','');
   			$('#studyPeriodDatagrid').datagrid('reload');
   			
   			$('#checkItemDatagridDiv').css('display','none');
   			
   			$('#saveManyQAChkPlanButton').linkbutton('enable');
   			
   			$('#saveManyQAChkPlanButton').css('display','');
   			$('#saveQAChkPlanButton').css('display','none');
   			$('#formDiv').css('display','none');
   			
   			//var myDate = $('#planStartDateValue').val();                 
	       // var month=(myDate.getMonth()+1)>9?(myDate.getMonth()+1).toString():'0' + (myDate.getMonth()+1);
	        //var date2=myDate.getDate()>9?myDate.getDate().toString():'0' + myDate.getDate();
		    var dateStr = $('#planStartDateValue').val(); 
		              
   			$('#oneStartDate').datebox('setValue',dateStr);
   		}
   }
   
</script>

<script type="text/javascript">

    	$(function(){
        	$('#wholePageForChkPlan').css('display','');
        	
    		var roleForQAChkPlan = $('#roleForChkPlan').val();
    		var haveForQAChkPlan = $('#haveChkPlanRight').val();
			
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight-30;
    		var tableWidth  = document.body.clientWidth;
			//"chkPlanId", "chkPlanType","chkItemId","chkItemName","scheduleTime","scheduleName",
			//"createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion"
    		var hiddenFlag=false;
    		if($('#QAMainPage').val()=='')//子页面
    		{
    			hiddenFlag=true;
    			//设置宽度	    		
    			// tableWidth  = tableWidth;
    			 /*
	    		 if(parent.parent.$('#changeSizeButton')&&parent.parent.$('#changeSizeButton').text()==">")
	    		 {
	    			 tableWidth = tableWidth+290;
	    	   	 }*/
    			tableHeight = tableHeight;
    			//alert("tableWidth110。8="+tableWidth);
    			
    			var  state=$('#chkPlanStateForList').val();
    			var chkPlanChange=$('#chkPlanChange').val();
    			////0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改  
    			//更新上面的台头
    			if($('#studyNoParamForList').val()!=null&&$('#studyNoParamForList').val()!='')
    			{
        			//alert("==="+state);
	    			var label = "专题编号："+$('#studyNoParamForList').val();
					if(state==null||state==''||state==0){
						label += "&nbsp;&nbsp;&nbsp;检查计划未制定完成";
					}else if(state==1){
						label += "&nbsp;&nbsp;&nbsp;检查计划待审批";
					}else if(state==-1){
						label += "&nbsp;&nbsp;&nbsp;检查计划审批不通过";
					}else if(state==2){	
						label += "&nbsp;&nbsp;&nbsp;检查计划制定完成";
						if(chkPlanChange==0){	
							label += "&nbsp;&nbsp;&nbsp;检查计划申请变更";
						}else if(chkPlanChange==1){	
							label += "&nbsp;&nbsp;&nbsp;提交检查计划变更申请";
						}
					}
					
					label="<h4>"+label+"</h4>";
					parent.parent.parent.$('#topInfoLabel').html(label);
    			}
    			
	    		
    		}else{
    			parent.$('#topInfoLabel').html('');
    		}

 			
    		 $('#startDate').datebox('setValue',$('#planStartDateValue').val());
			 $('#endDate').datebox('setValue',$('#planEndDateValue').val());
			 
    		//alert("tableWidth22="+ tableWidth+"===="+tableHeight);
    		$('#qaChkPlanDatagrid').datagrid({
        	  //url:sybp()+'/qAChkPlanAction_getPlanListByCondition.action?studyNoParam='+$('#studyNoParamForList').val()+'&QAMainPage='+$('#QAMainPage').val(),
				url:sybp()+"/qAChkPlanAction_getPlanListByCondition.action?studyNoParam="+$('#studyNoParamForList').val()+'&QAMainPage='+$('#QAMainPage').val()+"&start="+$('#planStartDateValue').val()+"&end="+$('#planEndDateValue').val(),
				height:tableHeight,
				//width:tableWidth,
				singleSelect:true,
				frozenColumns:[[
						{
							title:'专题编号',
							field:'studyNo',
							width:100,
							hidden:hiddenFlag,
						},
						{
							title:'专题名称',
							field:'studyName',
							width:110,
							hidden:hiddenFlag,
						},
						{
							title:'检查项',
							field:'chkItemName',
							width:120,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
									if((row.studyReportState==null||row.studyReportState==0)
										&&row.chkPlanState==2&&row.chkFinishedFlag!=1&&roleForQAChkPlan=='QALead'
										&&(row.chkOperator==null||row.chkOperator==''))
									{
										if(row.planChkOperator!=""&&row.planChkOperator!=null)
										{
											
											return "<br>"+value;
										}
										
									}
									
									//QAM审批
									if(roleForQAChkPlan=='QALead'&&row.tempChkOperatorFlag==1)//申请了临时检查人
									{
										if(row.tempChkOperator!=""&&row.tempChkOperator!=null)
										{
										
											return "<br>"+value;
										}
									}
									//QA申请
									if(row.haveChkPlanRight==true&&row.chkOperator==''&&row.SOPFlag!=-1)//是这个计划的负责人,没开始检查，没申请过临时检查人,是检查计划
									{
									//if(row.chkPlanState==2&&row.haveChkPlanRight==true&&row.chkFinishedFlag!=1)
										if(row.chkPlanState==2&&row.haveChkPlanRight==true&&row.tempChkOperatorFlag!=1
											&&(row.chkOperator==null||row.chkOperator=='')){
											if(row.tempChkOperator!=''&&row.tempChkOperator!=null&&row.tempChkOperatorFlag!=-1)
											{
												
												return "<br>"+value;
											}
											
										}else{
											if(row.tempChkOperatorFlag==1)//0：草稿；1：提交；-1：QAM否决；2：通过  
											{
											
												return "<br>"+value;
												
											}
										
										}
									}
									
									return value;
							}
						},
				]],
				columns:[[
						{
							title:'计划Id',
							field:'chkPlanId',
							hidden:true,
						},
						{
							title:'计划状态',
							field:'chkPlanState',//0：草稿；1：提交；-1：QAM否决；2：通过  
							hidden:true,
							
						},
						
						{
							title:'计划检查日期',
							field:'planChkTime',
							width:80,
							halign:'center',
							align:'left',
							/*
							formatter: function(value,row,index){
								if ( value == "01" ){
									return "医药";
								}else if(value=="02"){
							        return "农药";
								}else{
									return "化学品";
								}
							}*/
						},
						{
							title:'日程',
							field:'scheduleName',
							width:120,
							halign:'center',
							align:'center',
						},
						{
							title:'检查项Id',
							field:'chkItemId',
							hidden:true,
						},
						{
							title:'检查计划完成标志',//0：未完成；1：已完成；-1取消
							field:'chkFinishedFlag',
							hidden:true,
						},
						
						
						{
							title:'检查区域',
							field:'planChkArea',
							width:80,
							halign:'center',
							align:'center',
						},
						{
								title:'检查依据',
								field:'SOPFlag',
								width:80,
								halign:'center',
								align:'center',
								formatter: function(value,row,index){
									if(value==1){
										return "SOP";
									}else if(value==2){
										return "SOP+方案";
									}else if(value==3){
										return "方案";
									}
								}
						},
						{
							title:'专题报告状态',
							field:'studyReportState',//0：未完成；1：已完成
							hidden:true,
							
						},
						
						{
							title:'计划检查人员',
							field:'planChkOperator',
							width:100,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								//row.haveChkPlanRight==true&&
									if((row.studyReportState==null||row.studyReportState==0)
										&&row.chkPlanState==2&&row.chkFinishedFlag!=1&&roleForQAChkPlan=='QALead'
										&&(row.chkOperator==null||row.chkOperator==''))
									{
										
										if(value==""||value==null)
										{
											return " <a href='javascript:appointChkPlanQA(\"add\",\""+row.chkPlanId+"\");'>任命临时检查员</a>";
										}else{
											return value+"<br> <a href='javascript:appointChkPlanQA(\"add\",\""+row.chkPlanId+"\");'>任命临时检查员</a>";
										}
										
									}else
									{
										if(value==""||value==null)
										{
											return "";
										}else{
											return value;
										}
									}
								
							}
						},
						{
							title:'临时检查人申请状态',
							field:'tempChkOperatorFlag',//0：草稿；1：提交；-1：QAM否决；2：通过  
							hidden:true,
							
						},
						
						{
							title:'临时检查人申请',
							field:'tempChkOperator',
							width:100,
							//hidden:(roleForQAChkPlan=='QALead'?false:true),
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								//QAM审批
								if(roleForQAChkPlan=='QALead'&&row.tempChkOperatorFlag==1)//申请了临时检查人
								{
									if(value==""||value==null)
									{
										return " ";
									}else{
										return value+"<br> <a data-options='iconCls:\"icon-ok\",plain:true' href='javascript:approvalTempChkPlanQA(2,\""+row.chkPlanId+"\");'>同意</a>"+
														 "|<a data-options='iconCls:\"icon-cancel\",plain:true' href='javascript:approvalTempChkPlanQA(-1,\""+row.chkPlanId+"\");'>拒绝</a>";
									}
								}
								//QA申请
								if(row.haveChkPlanRight==true&&row.chkOperator==''&&row.SOPFlag!=-1)//是这个计划的负责人,没开始检查，没申请过临时检查人,是检查计划
								{
								//if(row.chkPlanState==2&&row.haveChkPlanRight==true&&row.chkFinishedFlag!=1)
									if(row.chkPlanState==2&&row.haveChkPlanRight==true&&row.tempChkOperatorFlag!=1
										&&(row.chkOperator==null||row.chkOperator=='')){
										if(value==''||value==null||row.tempChkOperatorFlag==-1)
										{
											return " <a href='javascript:appointChkPlanQA(\"temp\",\""+row.chkPlanId+"\");'>临时检查员申请</a>";
										}else{
											return value+"<br> <a href='javascript:appointChkPlanQA(\"temp\",\""+row.chkPlanId+"\");'>临时检查员申请</a>";;
										}
										
									}else{
										if(row.tempChkOperatorFlag==1)//0：草稿；1：提交；-1：QAM否决；2：通过  
										{
											return value+"<br>待审批";
											
										}else{
											if(value==''||value==null)
											{
												return '';
											}else{
												return value;
											}
										}
									
									}
								}
								
							}
						},
						{
							title:'实施检查日期',
							field:'chkTime',
							width:80,
							halign:'center',
							align:'center',
						},
						{
							title:'实施检查员',
							field:'chkOperator',
							width:80,
							halign:'center',
							align:'center',
							
						},
						{
							title:'是否有权限',
							field:'haveChkPlanRight',
							hidden:true,
							
						},
					//	"chkPlanId","chkPlanState","studyNo", "chkPlanType","chkItemId","chkItemName","scheduleTime","scheduleName","createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion","haveChkPlanRight","studyReportState"
						{
							title:'备注',
							field:'remark',
							width:80,
							halign:'center',
							align:'center',
							
							formatter: function(value,row,index){
								if('任命专题QA检查员'==row.quickOperate)
								{
									return " ";
								}else{
									return value;
								}
									
							}
							
						},
						{
							title:'操作',
							field:'operation',
							width:100,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								//跟left类似
							//	if(row.tempChkOperatorFlag==1)//0：草稿；1：提交；-1：QAM否决；2：通过
								//'chkPlanState',//0：草稿；1：提交；-1：QAM否决；2：通过 
								if(row.chkPlanState==2&&row.haveChkPlanRight==true&&row.chkFinishedFlag!=1&&row.tempChkOperatorFlag!=1)
								{	
									return "<a id='"+row.chkPlanId+"' href='javascript:startCheck(\""+row.chkPlanId+"\");'>"+value+"</a>";
								}else
								{
									return "开始检查";
								}
							}
						},{
							title:'快捷操作',
							field:'quickOperate',
							width:100,
							hidden:hiddenFlag,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								//alert("==="+value+"===remark="+row.remark)
								//$('#quickOperateStudyNo').val(quickOperateStudyNo);
								//$('#quickOperateChkPlanState').val(quickOperateChkPlanState);
								//$('#quickOperatechkPlanChange').val(quickOperatechkPlanChange);
								if('同意检查计划'==value)
								{	//chkplanstate 1是审批检查计划
									return "<a data-options='iconCls:\"icon-ok\",plain:true' href='javascript:acceptChkPlanApply(2,\""+row.studyNo+"\",1,0);'> 同意 </a>|"+
										   "<a data-options='iconCls:\"icon-cancel\",plain:true' href='javascript:acceptChkPlanApply(-1,\""+row.studyNo+"\",1,0);'> 退回 </a>";
								}else if('同意检查计划变更'==value)
								{	//chkplanstate 2 change 1是审批检查计划变更
									return "<a data-options='iconCls:\"icon-ok\",plain:true' href='javascript:acceptChkPlanApply(2,\""+row.studyNo+"\",2,1);'> 同意 </a>|"+
										   "<a data-options='iconCls:\"icon-cancel\",plain:true' href='javascript:acceptChkPlanApply(-1,\""+row.studyNo+"\",2,1);'> 退回 </a>";
								}else if('同意检查报告'==value)
								{	
									//return "<a id='"+row.chkPlanId+"' href='javascript:startCheck(\""+row.chkPlanId+"\");'>"+value+"</a>";
								}else if('任命专题QA检查员'==value)
								{	//当是任命QA时，remark是itemId
									return "<a  href='javascript:qamQuickAppointQA(\""+row.remark+"\",\""+row.studyNo+"\");'>"+value+"</a>";
								}else{
									return "";
								}
							}
						},
						

				]],
				
        	});
        	
        	$('#approvalQAChkPlanDialog').dialog({
        		left: (parent.parent.$('#researchMain').width()-$('#approvalQAChkPlanDialog').width())/2,
				
            });
        	$('#addQAChkPlanDialog').dialog({
        		
				left: (parent.parent.$('#researchMain').width()-$('#addQAChkPlanDialog').width())/2,
				top:10,
				onOpen:function(){
					initChkPlanChildButton();
				},
				onClose:function()
				{
				/*
        		 	var scheduleChanged = $('#scheduleChanged').val();
     				if(scheduleChanged=='true')
     				{
        				var state = $('#chkPlanStateForList').val();
						if(state!='1')
						{
							$.messager.alert("提示","请处理日程变更（即提交新检查计划）","",function(){
								$('#qaChkPlanDatagrid').datagrid('reload');
							});
						}
         			}
        			*/
				}
            });
           
                
        	$('#scheduleDatagrid').treegrid({
        		url:sybp()+'/qAChkPlanAction_getScheduleList.action?studyNoParam='+$('#studyNoParamForList').val(),
        		height:250,
				width:260,
				//singleSelect:true,
				//idField:'scheduleId',
				idField:'id',//scheduleId-planDate
        	    treeField:'taskName',
        	    columns:[[
        	        {title:'scheduleId',field:'scheduleId',hidden:true},
        	        {title:'任务名称',field:'taskName',width:96,align:'left'},
        	        {
						title:'检查区域',field:'planChkArea',width:65,
							halign:'center',align:'center',
							editor:{
								type:'combotree',
								options:{
									editable:true,
									url: sybp()+'/qAChkPlanAction_loadComboTreeList.action',	
								}
						},
					},
        	        {title:'计划日期',field:'planDate',width:75},
        	        {title:'number',field:'number',hidden:true}//第几次执行
        	    ]],
        	    onClickRow:function(rowData)
				{
					$('#oneScheduleId').val(rowData.scheduleId);
					$('#oneNumber').val(rowData.number);
	        		$.ajax({
	        			url:sybp()+'/qAChkPlanAction_getChkItemBySchedule.action',
						type:'post',
						data:{taskName:rowData.taskName},
						dataType:'json',
						success:function(r){
							$('#checkItemDatagrid').datagrid('loadData',r);
						}
					});
						/*
        				$('#checkItemDatagrid').treegrid({
        					url:sybp()+'/qAChkPlanAction_getChkItemBySchedule.action?taskName='+rowData.taskName,
            			});
            			
        				$('#checkItemDatagrid').datagrid('reload');
        				*/
        				$('#onePlanChkTime').datebox('setValue',rowData.planDate);
            			$('#oneScheduleTime').datebox('setValue',rowData.planDate);
            			$('#oneScheduleTime').attr('disabled');
            			$('#oneScheduleName').val(rowData.taskName);
            			$('#oneScheduleName').attr('disabled');
            			$('#oneChkItemName').val('');	
            			
            			if($('#lastEditTreegridIndex').val()!='')
		        		{
		        			var ed = $(this).treegrid('getEditor', {index:$('#lastEditTreegridIndex').val(),field:'planChkArea'});
							if(ed!=null)
							{
								var planChkArea = $(ed.target).combotree('getText');
								$('#treeChangesPlanChkArea').val(planChkArea);
									
							}
		        				
		        			$(this).treegrid('endEdit', $('#lastEditTreegridIndex').val());
		        		}
		        		
		        		
						var chkPlanChange=$('#chkPlanChange').val();
						var chkPlanState = $('#chkPlanStateForList').val();
						if(chkPlanState==undefined||chkPlanState==''//不存在QAStudyChkIndex
							||chkPlanState=='0'||chkPlanState=='-1'||
							(chkPlanState=='2'&&chkPlanChange==0))//处于编辑状态
						{
							var parent = $(this).treegrid('getParent',rowData.id);
							if(parent==null||parent=='')
							{
								$(this).treegrid('beginEdit', rowData.id);
		        			
		        				$('#lastEditTreegridIndex').val(rowData.id);
		        			}
						}
            			
				},
				
				onAfterEdit:function(rowData, changes)
				{
					var changesPlanChkArea = $('#treeChangesPlanChkArea').val();
					if(changesPlanChkArea!='')
					{
						$('#treeChangesPlanChkArea').val(null);
                				
						$(this).treegrid('update',{
							id: rowData.id,
							row: {
								planChkArea:changesPlanChkArea,
							}
						});
				
								
					}
							
							
				}
        		
            });
            $('#taskDatagrid').treegrid({
				url:sybp()+'/qAChkPlanAction_getAllTaskType.action',
				//singleSelect:true, 
			    treeField:'taskName', 
			    animate:false,   
			    height:250,
				width:260,
			    singleSelect: true, //支持多选
			    idField:'id',
			    columns:[[  
			    //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理  7分析 8生态毒理
			                {title:'id',field:'id',width:180,hidden:true},
					        {title:'任务属性',field:'taskKind',width:180,hidden:true},      
					        {title:'任务名称',field:'taskName',width:230,
								formatter: function(value,row,index){
								if ( value == 1){
									return "委托管理";
								}else if(value == 2){
				                     return "动物试验";
								}else if(value == 3){
				                     return "临床检验";
								}else if(value == 4){
				                     return "毒性病理";
								}else if(value == 5){
				                     return "QA管理";
								}else if(value == 6){
				                     return "供试品管理";
								}else if(value == 7){
				                     return "分析";
								}else if(value == 8){
				                     return "生态毒理";
								}else{
									return value;
								}}
							 },   
							{title:'任务总负责人/可见范围',field:'canSee',hidden:true},      
					        {title:'有效性',field:'validFlag',width:180,hidden:true},
					        {field:'_parentId',title:'_parentId',width:180,hidden:true}
					    ]],
					  //工具栏
						//toolbar:'#toolbar',
				    	onClickRow :function(row){
					         var validFlag = $(this).treegrid('getSelections');
					         for(var i = 0;i<validFlag.length;i++){
		                       if(validFlag[i].validFlag == 1){
								   $(this).treegrid('unselect',validFlag[i].id);
		                       }
						    }
				           if(row._parentId == ""){
				        	   $('#saveQAChkPlanButton').linkbutton('disable');
				        	   $('#generatePlanByScheduleButton').linkbutton('disable');
				        	   
							   $(this).treegrid('unselectAll');
							   $(this).treegrid('select',row.id);
							   
							   $('#checkItemDatagrid').datagrid('loadData',[]);
						   }else{
							    var row = $(this).treegrid('getSelected');
	                           
	                           $('#saveQAChkPlanButton').linkbutton('enable');
	                           $('#generatePlanByScheduleButton').linkbutton('enable');
	                           $.ajax({
				        			url:sybp()+'/qAChkPlanAction_getChkItemBySchedule.action',
									type:'post',
									data:{taskName:row.taskName},
									dataType:'json',
									success:function(r){
										$('#checkItemDatagrid').datagrid('loadData',r);
									}
								});
								
	                           var dateStr = $('#planStartDateValue').val();
	                          
	                         		$('#checkItemDatagrid').datagrid('reload');
			        				$('#onePlanChkTime').datebox('setValue',dateStr);
			            			$('#oneScheduleTime').datebox('setValue',dateStr);
			            			$('#oneScheduleName').val(row.taskName);
			            			$('#oneScheduleId').val('');
			    					$('#oneNumber').val('');
			    					$('#oneChkItemName').val('');	
						   }
				       },
						rowStyler: function(row){
							 if (row.validFlag== 1 ){
								return 'background-color:#F2F2F2;color:#080808;';
							 }
						},	
						
            });
            /*
            $.extend($.fn.datagrid.defaults.editors, {
			    combotree: {
			        getValue: function(target){
			            return $(target).attr('text');
			        },
			       
			    }
			});*/

            $('#studyPeriodDatagrid').datagrid({
            	url:sybp()+'/qAChkPlanAction_getChkItemStudyGroupReg.action?studyNoParam='+$('#studyNoParamForList').val(),
				singleSelect:true,
            	height:315,
				width:390,
				idField:'chkItemStudyGroupRegId',
				columns:[[
						{
							title:'检查项和试验分类id',
							field:'chkItemStudyGroupRegId',
							hidden:true,
							
						},
						{
							title:'检查开始日期',
							field:'dictChkItemsStartDate',
							width:85,
							editor:{
								type:'datebox',
								options:{
									editable:false,
								}
							},
							
						},
						{
							title:'检查项Id',
							field:'chkItemId',
							hidden:true,	
						},
						{
							title:'检查项名称',
							field:'chkItemName',
							width:100,
						},
						{
							title:'检查区域',
							field:'planChkArea',
							width:65,
							halign:'center',
							align:'center',
							editor:{
								type:'combotree',
								options:{
									editable:true,
									url: sybp()+'/qAChkPlanAction_loadComboTreeList.action',	
								}
							},
						},
						{
							title:'频率',
							field:'chkFreqFlag',
							width:45,
							//1：单次；2：重复
							formatter: function(value,row,index){
								if(value==1)
								{
									return "单次";
								}else if(value==2){
									return "多次";
								}
							}
							
						},
						{
							title:'周期',
							field:'chkFreq',
							width:35,
						},
						{
							title:'单位',
							field:'chkFreqUnit',
							width:35,
						},
						
				]],
				onSelect: function(rowIndex, rowData){
					
		        		if($('#lastEditDictDatagridIndex').val()!='')
		        		{
		        			var ed = $(this).datagrid('getEditor', {index:$('#lastEditDictDatagridIndex').val(),field:'planChkArea'});
							if(ed!=null)
							{
								var planChkArea = $(ed.target).combotree('getText');
								$('#leftChangesPlanChkArea').val(planChkArea);
									
							}
		        				
		        			$(this).datagrid('endEdit', $('#lastEditDictDatagridIndex').val());
		        		}
		        						
							var chkPlanChange=$('#chkPlanChange').val();
							var chkPlanState = $('#chkPlanStateForList').val();
							if(chkPlanState==undefined||chkPlanState==''//不存在QAStudyChkIndex
								||chkPlanState=='0'||chkPlanState=='-1'||
								(chkPlanState=='2'&&chkPlanChange==0))//处于编辑状态
							{
								$(this).datagrid('beginEdit', rowIndex);
		        			
		        				$('#lastEditDictDatagridIndex').val(rowIndex);
							}
					
					
		        			
				},
				onAfterEdit:function(rowIndex, rowData, changes)
				{
					var changesPlanChkArea = $('#leftChangesPlanChkArea').val();
					if(changesPlanChkArea)
					{
						$('#leftChangesPlanChkArea').val(null);
                				
						$(this).datagrid('updateRow',{
								index: rowIndex,
								row: {
									
									planChkArea:changesPlanChkArea,
								}
							});
				
								
					}
							
							
				}
				
				
            });
            
            $('#oneStartDate').datebox({
            	
            	/*onSelect: function(date){
					alert(date);
					alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				},*/
				
            	onChange:function(newValue, oldValue){
            		//根据设置生成计划之前
					if($('#lastEditDictDatagridIndex').val()!='')
			        {
			        	var ed = $('#studyPeriodDatagrid').datagrid('getEditor', {index:$('#lastEditDictDatagridIndex').val(),field:'planChkArea'});
						if(ed!=null)
						{
							var planChkArea = $(ed.target).combotree('getText');
							$('#leftChangesPlanChkArea').val(planChkArea);
										
						}
			        				
			        	$('#studyPeriodDatagrid').datagrid('endEdit', $('#lastEditDictDatagridIndex').val());
			        }
            		 
            		 if(oldValue!=null&&oldValue!=''){
	            		 var distinct = new Date(newValue).getTime()-new Date(oldValue).getTime();
	            		 var rows = $('#studyPeriodDatagrid').datagrid('getRows');
	            		 for(var i=0;i<rows.length;i++){
	    	        		 var newDate=new Date(new Date(rows[i].dictChkItemsStartDate).getTime()+distinct);
	    	        		 
							 var newDateStr = newDate.getFullYear();
							 if(newDate.getMonth()+1<10){
							 	newDateStr+="-0"+(newDate.getMonth()+1);
							 }else{
							 	newDateStr+="-"+(newDate.getMonth()+1);
							 }
							 if(newDate.getDate()<10){
								newDateStr+="-0"+newDate.getDate();
							 }else{
							 	newDateStr+="-"+newDate.getDate();
							 }
							  $('#studyPeriodDatagrid').datagrid('updateRow',{
									index: i,
									row: {
										dictChkItemsStartDate:newDateStr,
										
									}
								});
	            		 }
            		 }
            	}
            });
        	$('#checkItemDatagrid').datagrid({
        		height:250,
				width:120,
				idField:'scheduleChkItemId',
				//treeField:'chkItemName',
				singleSelect:true,
				columns:[[
						{
							title:'检查项日程Id',
							field:'scheduleChkItemId',
							hidden:true,
							
						},
						{
							title:'检查项Id',
							field:'chkItemId',
							hidden:true,
							
						},
						{
							title:'检查项',
							field:'chkItemName',
							width:100,
							halign:'center',
							align:'left',
							
						},
						{
							title:'检查所用表',
							field:'chkTblName',
							width:100,
							halign:'center',
							align:'center',
							hidden:true,
						},
					]],
					onSelect:function(rowIndex,row)
					{
            			$('#oneChkItemName').val(row.chkItemName);
            			$('#oneScheduleName').attr('disabled');
            			$('#oneChkItemId').val(row.chkItemId);
						$('#oneScheduleChkItemId').val(row.scheduleChkItemId);
    				}
        		
            });
        	//"chkPlanId", "chkPlanType","chkItemId","chkItemName","scheduleTime","scheduleName",
        	//"createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion"
        	 
			 $('#qaChkPlanList').datagrid({
	        		height:340,
					width:490,
					singleSelect:true,
					columns:[[
							{
								title:'计划Id',
								field:'chkPlanId',
								hidden:true,
							},
							{
								title:'计划检查日期',
								field:'planChkTime',
								width:85,
								halign:'center',
								align:'left',
								editor:{
									type:'datebox',
									options:{
										editable:false,
									}
								},
							},
							{
								title:'任务',
								field:'scheduleName',
								width:80,
								halign:'center',
								align:'center',
								
							},
							{
								title:'检查项',
								field:'chkItemName',
								width:80,
								halign:'center',
								align:'center',
							},
							{
								title:'检查区域',
								field:'planChkArea',
								width:70,
								halign:'center',
								align:'center',
								editor:{
									type:'combotree',
									options:{
										//valueField:'id',
										editable:true,
										
										url: sybp()+'/qAChkPlanAction_loadComboTreeList.action',
										
									}
								},
							},
							{
								title:'检查依据',
								field:'SOPFlag',
								width:70,
								halign:'center',
								align:'center',
								formatter: function(value,row,index){
									if(value==1){
										return "SOP";
									}else if(value==2){
										return "SOP+方案";
									}else if(value==3){
										return "方案";
									}
								},
								editor:{
									type:'combobox',
									options:{
										editable:false,
										valueField:'id',
										textField:'text',
										url: sybp()+'/qAChkPlanHisAction_getSOPFlagList.action',
										
									}
								},
							},
							{
								title:'chkFinishedFlag',
								field:'chkFinishedFlag',
								hidden:true,
							},
							{
								title:'操作',
								field:'chkPlanVersion',//1代表当前版本2代表历史版本',
								width:80,
								halign:'center',
								align:'center',
								formatter: function(value,row,index){
									if(value==2){
										return "删除"
									}else{
										
										var chkPlanChange=$('#chkPlanChange').val();
										var chkPlanState = $('#chkPlanStateForList').val();
										if(chkPlanState==undefined||chkPlanState==''//不存在QAStudyChkIndex
											||chkPlanState=='0'||chkPlanState=='-1'||
											(chkPlanState=='2'&&chkPlanChange==0))//处于编辑状态
										{
											return "<a href='javascript:delPlan("+row.chkPlanId+");'>删除</a>";
										}else{
											return "删除";
										}	
									}
								}
							},
						]],
		        		onSelect: function(rowIndex, rowData){
		        			if($('#lastEditDatagridIndex').val()!='')
		        			{
		        				var ed = $('#qaChkPlanList').datagrid('getEditor', {index:$('#lastEditDatagridIndex').val(),field:'planChkArea'});
								if(ed!=null)
								{
									var planChkArea = $(ed.target).combotree('getText');
									$('#changesPlanChkArea').val(planChkArea);
									
								}
		        				$(this).datagrid('endEdit', $('#lastEditDatagridIndex').val());
		        			}
		        			if(rowData.chkPlanVersion==2){//制定完成
									
							}else{
										
									var chkPlanChange=$('#chkPlanChange').val();
									var chkPlanState = $('#chkPlanStateForList').val();
									if(chkPlanState==undefined||chkPlanState==''//不存在QAStudyChkIndex
										||chkPlanState=='0'||chkPlanState=='-1'||
										(chkPlanState=='2'&&chkPlanChange==0))//处于编辑状态
									{
										$(this).datagrid('beginEdit', rowIndex);
		        			
		        						$('#lastEditDatagridIndex').val(rowIndex);
									}else{
										
									}	
							}
		        			
						},
						onAfterEdit:function(rowIndex, rowData, changes)
						{
							var changesPlanChkArea = $('#changesPlanChkArea').val();
							if(changes.planChkTime||changesPlanChkArea||changes.SOPFlag)
							{
								$('#changesPlanChkArea').val(null);
                				
								var actionName="qAChkPlanAction";
								var chkPlanChange=$('#chkPlanChange').val();
								if(chkPlanChange==0)
								{
									actionName="qAChkPlanHisAction"
								}
								$.ajax({
									url:sybp()+'/'+actionName+'_updatePlanTimeOrArea.action?chkPlanId='+rowData.chkPlanId,
									type:'post',
									data:{
										planChkTime:changes.planChkTime,
										planChkArea:changesPlanChkArea,
										SOPFlag:changes.SOPFlag
									},
									dataType:'json',
									success:function(r){
										$('#qaChkPlanList').datagrid('updateRow',{
											index: rowIndex,
											row: {
												planChkTime:r.planChkTime,
												planChkArea:r.planChkArea,
												SOPFlag:r.SOPFlag
											}
										});
									}
								});
								
							}

						}
	            });
	            //审批中的datagrid
	            $('#approvalQAChkPlanList').datagrid({
	            	height:250,
					width:500,
					columns:[[
							{
								title:'计划Id',
								field:'chkPlanId',
								hidden:true,
							},
							{
								title:'计划检查日期',
								field:'planChkTime',
								width:85,
								halign:'center',
								align:'left',
								
							},
							{
								title:'专题编号',
								field:'studyNo',
								width:85,
								halign:'center',
								align:'left',
								
							},
							{
								title:'检查项',
								field:'chkItemName',
								width:120,
								halign:'center',
								align:'center',
							},
							{
								title:'检查区域',
								field:'planChkArea',
								width:80,
								halign:'center',
								align:'center',
							},
							{
								title:'检查依据',
								field:'SOPFlag',
								width:80,
								halign:'center',
								align:'center',
								formatter: function(value,row,index){
									if(value==1){
										return "SOP";
									}else if(value==2){
										return "SOP+方案";
									}else if(value==3){
										return "方案";
									}
								},
							},
							
						]],
		        });
		        
				$('#oneQAChkPlan').width(390);
				$('#oneQAChkPlan').height(150);
	            $('#oneQAChkPlanTable').width(390);

	            if($('#QAMainPage').val()=='')
	            {
					$('#catalogLabel').css('display','none');
					$('#catalog').css('display','none');
					$('#planSearcher').css('display','none');
					
					$('#justChkPlan').css('display','none');
					$('#justChkPlanLabel').css('display','none');
					//$('#editQAChkPlan').css('display','');
		        }else{
					//主页面下的检查计划
					//$('#catalogLabel').css('display','');
					$('#catalog').css('display','');
					$('#planSearcher').css('display','');
					
					$('#editQAChkPlan').css('display','none');
					$('#approvalQAChkPlan').css('display','none');
					$('#approvalQAChkPlanApply').css('display','none');
					$('#planSearcher').searchbox({ 
			       	     height:19,
			       	     width:300,
			       		 searcher:function(value,name){ 
			       		 	searchPlanList();
			       		},
			       		prompt:'模糊查询,请输入专题编号,检查项或者日程',
			       	});
					 $('#catalog').combobox({
		            	 onSelect: function(rec){
		            		searchPlanList();
		            	 }
			        });
			        
			        $('#justChkPlan').css('display','');
			        $('#justChkPlanLabel').css('display','');
			    }
				if($('#studyNoParamForList').val()!=null&&$('#studyNoParamForList').val()!='')
	            {   
		          	
	            	
	            }else
	            {
	            	
	            }
	            //查询条件的配置
	           
	            $('#startDate').datebox({
	        		onSelect: function(date){
	            		searchPlanList();
	        		}
	        	});
	            $('#endDate').datebox({
	        		onSelect: function(date){
	            		searchPlanList();
	        		}
	        	});
	            $('#status').combobox({
	            	 onSelect: function(rec){
	            		searchPlanList();
	            	 }
		        });

	            $('#oneChkPlanVersion').combobox({
	    			url:sybp()+'/qAChkPlanAction_getPlanVersions.action?studyNoParam='+$('#studyNoParamForList').val(),
	        		valueField:'id',
	        		textField:'text',
	        		required: true,    
	        		onSelect: function(record){    
	    				var actionName="qAChkPlanAction";
	    				
	    				var chkPlanState = $('#chkPlanStateForList').val();
	    				var chkPlanChange=$('#chkPlanChange').val();
	    				if(chkPlanChange==0){
	    					actionName="qAChkPlanHisAction";
	    				}
	        			$('#qaChkPlanList').datagrid({
	        	    		url:sybp()+'/'+actionName+'_getPlanListByVersion.action?studyNoParam='+$('#studyNoParamForList').val()+'&versionStr='+record.id,
	        			});
	        			$('#qaChkPlanList').datagrid('reload');
	        			
	        			$('#isEditBySchedule').attr('disabled',false);
	        			
	        			clickManul();
	        			initChkPlanChildButton();
	        		},
	        		onLoadSuccess:function()
	        		{
	            		var data = $('#oneChkPlanVersion').combobox('getData');
	                	$('#oneChkPlanVersion').combobox('select',data[0].id);
	            	}
	    		});
	    		
	    		/*$('#qaChkPlanList').datagrid({
	        		url:sybp()+'/qAChkPlanAction_getPlanListByCondition.action?studyNoParam='+$('#studyNoParamForList').val()+'&QAMainPage='+$('#QAMainPage').val(),
	    		});*/
	    		
		           
		        initChkPlanButton();

		        var scheduleChanged = $('#scheduleChanged').val();
				if(haveForQAChkPlan=='true'&&scheduleChanged=='true')
				{
					$.messager.confirm('确认框','根据日程重新生成检查计划?',function(r){
					    if (r){
					    	$('#scheduleChanged').val('false');
					       //备份现有的,并把专题检查索引中的计划状态改成草稿
					    	$.ajax({
								url:sybp()+'/qAChkPlanAction_backupStudyPlan.action?studyNoParam='+$('#studyNoParamForList').val(),
								type:'post',
								//data:$('#oneQAChkPlan').serialize(),
								dataType:'json',
								success:function(){
									$('#chkPlanStateForList').val(0);//检查计划变更草稿状态
									
									document.getElementById("addQAChkPlanDialog2").display="block";
									initChkPlanChildButton();
							    	$('#qaChkPlanList').datagrid('reload');
					    			$('#oneChkPlanVersion').combobox('reload');
					    			clickManul();
					    			
					    			$('#addQAChkPlanDialog').dialog('open');			
								}
							});
					    }else{
					    	$.messager.alert('提示',"请处理日程变更");
					    }  
					});
				}
		        
        });//匿名函数结束
      	function initChkPlanButton()
        {
      		$('#editQAChkPlan').linkbutton('disable');
			$('#approvalQAChkPlan').linkbutton('disable');
			$('#approvalQAChkPlanApply').linkbutton('disable');

			var studyFinishForPlan=$('#studyFinishForPlan').val();
			if(studyFinishForPlan!='true')
			{
			
	      		var role1 = $('#roleForChkPlan').val();
	      		var have1 = $('#haveChkPlanRight').val();
	      		var studyChkPlanState= $('#chkPlanStateForList').val();
	      		var chkPlanChange=$('#chkPlanChange').val();
	      		//alert("chkPlanChange="+chkPlanChange);
	      		var studyReportStateForPlan=$('#studyReportStateForPlan').val();
				if(studyReportStateForPlan!='1'&&role1=="QALead")
				{
					//编辑不可用，审批可用
					$('#approvalQAChkPlan').linkbutton('disable');
					$('#approvalQAChkPlanApply').linkbutton('disable');
					if(studyChkPlanState==1//提交检查计划
						||chkPlanChange==1)//提交检查计划变更
					{
						$('#approvalQAChkPlan').linkbutton('enable');
					}
					/*
					if(chkPlanChange!=''&&chkPlanChange=='true')//3在实际过程中没有使用
					{
						$('#approvalQAChkPlanApply').linkbutton('enable');	
					}*/
					
					
					if(have1=='true'||have1==true)//是专题QA
					{
						$('#editQAChkPlan').linkbutton('enable');
					}
				}else if(studyReportStateForPlan!='1'&&role1=='QA'){
					if(have1=='true'||have1==true)//是专题QA
					{
						$('#editQAChkPlan').linkbutton('enable');
					}
				}
				
			}
      		
        }
        function initChkPlanChildButton(){
            var scheduleChanged = $('#scheduleChanged').val();
			
        	$('#applyChkPlanChangeButton').linkbutton('disable');
        	$('#cancelChkPlanChangeButton').linkbutton('disable');
        	
			$('#chkPlanCommitToQAMButton').linkbutton('disable');
			$('#saveQAChkPlanButton').linkbutton('disable');
			$('#saveManyQAChkPlanButton').linkbutton('disable');
			$('input[name="generatePlanStyle"]').attr("disabled",true);
			
			$('#generatePlanByScheduleButton').linkbutton('disable');
			$('#isEditBySchedule').attr('disabled',true);
		
			//编辑检查计划子页面的按钮
			var curVersion = $('#oneChkPlanVersion').combobox('getValue');
			///0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
			var chkPlanState = $('#chkPlanStateForList').val();
			var chkPlanChange=$('#chkPlanChange').val();
			//$.messager.alert('',"curVersion="+curVersion+"===chkPlanState="+chkPlanState+"==<br>==chkPlanChange="+chkPlanChange);
			if(chkPlanState==undefined||chkPlanState==''//不存在QAStudyChkIndex
				||chkPlanState=='0'||chkPlanState=='-1'||(chkPlanState=='2'&&chkPlanChange==0))//处于编辑状态
			{
				if((isNaN(curVersion)&&chkPlanState!='2')//处于编辑状态的检查计划
					||(chkPlanState=='2'&&curVersion==-1&&chkPlanChange==0))//处于没有提交的变更检查计划状态
				{
				
					$('#saveQAChkPlanButton').linkbutton('enable');
					$('#saveManyQAChkPlanButton').linkbutton('enable');
					$('input[name="generatePlanStyle"]').attr("disabled",false);
					
					$('#generatePlanByScheduleButton').linkbutton('enable');
					$('#isEditBySchedule').attr('disabled',false);
					
					//var rows = $('#qaChkPlanList').datagrid('getData');
					//if(rows!=null&&rows!='')
					//{
					$('#chkPlanCommitToQAMButton').linkbutton('enable');	
					//}
					
				}
				
			}
			/*必须是现在的检查计划并且检查计划是审批通过的									
			*/
			if(chkPlanState==2&&(chkPlanChange==''||chkPlanChange==-1))
			{
				$('#applyChkPlanChangeButton').linkbutton('enable');
			}else if(chkPlanChange==0||chkPlanChange==1){
				$('#cancelChkPlanChangeButton').linkbutton('enable');
			}
			if(scheduleChanged=='true')
        	{
        		$('#chkPlanCommitToQAMButton').linkbutton('enable');
        		$('#saveQAChkPlanButton').linkbutton('enable');	
        		$('#saveManyQAChkPlanButton').linkbutton('enable');	
        		$('input[name="generatePlanStyle"]').attr("disabled",false);
        		
        		$('#generatePlanByScheduleButton').linkbutton('enable');
        		$('#isEditBySchedule').attr('disabled',false);
            }
           
        }
        function initApprovalQAChkPlanButton()
        {
        	var arows = $('#approvalQAChkPlanList').datagrid('getRows');
    		//alert("approval datagrid="+"=="+arows);
        	if(arows!=null&&arows!='null'&&arows!='')
        	{
            	$('#acceptChkPlanApplyButton').linkbutton('enable');
            	$('#rejectChkPlanApplyButton').linkbutton('enable');
            }else
            {
            	$('#acceptChkPlanApplyButton').linkbutton('disable');
            	$('#rejectChkPlanApplyButton').linkbutton('disable');
            }
        }
        
        function cancelChangeChkPlan()
        {
        	var chkPlanChange = $('#chkPlanChange').val();
        	
        		$.messager.confirm('确认框', '确定要撤销该检查计划变更吗?', function(r){
					if (r){
					
						if(chkPlanChange==1){//提交过的
							qm_showQianmingDialog('afterSignCancelChangeChkPlan');
						}else{
							afterSignCancelChangeChkPlan();
						}
						
					}
				});
        	
        }
        
        function afterSignCancelChangeChkPlan()
        {
 			$.ajax({
					url:sybp()+'/qAChkPlanAction_cancelChangeChkPlan.action?studyNoParam='+$('#studyNoParamForList').val(),
					type:'post',
					//data:$('#oneQAChkPlan').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							$('#chkPlanChange').val('');
							//把变更版本去掉
							$('#oneChkPlanVersion').combobox('reload');
							initChkPlanChildButton();
							
						}else if(r&&!r.success){
							$.messager.alert("提示框",r.msg);
						}
									
					}
				});       	
        
        }
	 	
		
</script>
	  
</head>

<body >
	<s:hidden id="studyNoParamForList" name="studyNoParam"></s:hidden>
	<s:hidden id="chkPlanStateForList" name="chkPlanState"></s:hidden>
	<s:hidden id="studyReportStateForPlan" name="studyReportState"></s:hidden>
	<s:hidden id="QAMainPage" name="QAPage"></s:hidden>
	<s:hidden id="scheduleChanged" name="scheduleChanged"></s:hidden>
	<s:hidden id="resultLabel" ></s:hidden>
	<s:hidden id="reason" ></s:hidden>
	<s:hidden id="rejectReason" ></s:hidden>
	<s:hidden id="changeStateLabel" ></s:hidden>
	<s:hidden id="isQuickOperate"></s:hidden>
	<s:hidden id="quickOperateStudyNo"></s:hidden>
	<s:hidden id="quickOperateChkPlanState"></s:hidden>
	<s:hidden id="quickOperatechkPlanChange"></s:hidden>
	
	<s:hidden id="chkPlanChange" name="chkPlanChange"></s:hidden>
	
	<s:hidden id="planStartDateValue" name="planStartDate"></s:hidden>
	<s:hidden id="planEndDateValue" name="planEndDate"></s:hidden>
	
	<input id="roleForChkPlan" type="hidden" value=${role }></input>
	<s:hidden id="haveChkPlanRight" name="haveChkPlanRight"></s:hidden>
	
	<s:hidden id="studyFinishForPlan" name="studyFinishForPlan"></s:hidden>
	
	<s:hidden id="lastEditDatagridIndex" />
	<s:hidden id="lastEditDictDatagridIndex" />
	<s:hidden id="changesPlanChkArea"/>
	<s:hidden id="leftChangesPlanChkArea"/>
	<s:hidden id="lastEditTreegridIndex"/>
	<s:hidden id="treeChangesPlanChkArea"/>
	
	<div class="container" id="wholePageForChkPlan" style="display:none;">
	<div>
		<nobr>
		
            计划检查日期<div id="startDate" class="easyui-datebox" style="width: 100px;"></div>~<div id="endDate" class="easyui-datebox" style="width: 100px;"></div>
            状态    <select id="status" class="easyui-combobox" style="width:100px;">
			<option value="0" selected="selected">全部</option>
			<option value="1">未实施</option>
			<option value="2">已实施</option>
		</select>
	
		<span id="catalogLabel">类别</span>    
		<select id="catalog" style="width:150px;">
		<!-- 1:研究；2：过程；3：设施 -->
				<option value="0" selected="selected">全部</option>
				<option value="1">基于研究的检查</option>
				<!-- 
				<option value="2">基于过程的检查</option>
				<option value="3">基于设施的检查</option>
				 -->
			</select>
			&nbsp;
			<input id="justChkPlan" type="checkbox" onclick="searchPlanList();"/><span id="justChkPlanLabel">仅检查计划</span> 
			&nbsp;
		<span style="position:absolute; top:3px;">
			<input id="planSearcher" style="margin-top:3px" ></input>
		</span>
	<a id="editQAChkPlan" class="easyui-linkbutton" onclick="editQAChkPlan();"  data-options="iconCls:'icon-edit',plain:true">编辑检查计划</a>
	<a id="approvalQAChkPlan" class="easyui-linkbutton" onclick="approvalQAChkPlan();"  data-options="iconCls:'icon-tableedit',plain:true">审批检查计划</a>
	<!-- 
	<a id="approvalQAChkPlanApply" class="easyui-linkbutton" onclick="approvalQAChkPlanApply();"  data-options="iconCls:'icon-tableedit',plain:true">审批检查计划变更</a>
	 -->
		</nobr>
	</div>
		
       <div style="display:block; position:absolute;top:30px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:auto;" >
 	 	 
 	 	 <div id="qaChkPlanDatagrid" class="easyui-datagrid" style="border: 1px solid #c8c8c8;" border="false" >
		 </div>
		
 	  </div>
       
   <%@ include file="/WEB-INF/jsp/QAChkPlanAction/selectPlanQA.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkPlanAction/addChkPlan.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkPlanAction/approvalQAChkPlan.jspf"%>
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
    
   <!-- 
   	include file="/WEB-INF/jsp/QAChkPlanAction/selectQA.jspf"
   -->
    
    </div>
   
</body>
</html>




