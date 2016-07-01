<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查报告</title>
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
	//回复信息
	function replyMessage()
	{
		var reportRow =  $('#reportList').datagrid('getSelected');
		if(reportRow!=null)
		{
			document.getElementById("replyMessageDialog2").display="block";
			
			$('#replyMessageList').datagrid({
				url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+reportRow.chkReportCode,
				
			});
	
			$('#replyMessageDialog').dialog('open');
		}else {
			$.messager.alert("提示","请选择一个检查报告");
		}
	
	}
	function saveOneReplyMessage()
	{
		var row = $('#replyMessageList').datagrid('getSelected');
		if(row!=null)
		{
			var replyContent = $('#oneMessage').val();
			if(replyContent!='')
			{
				$.ajax({
					url:sybp()+'/qAChkReportRecordAction_saveReplyMessage.action?chkReportRecordId='+row.chkReportRecordId,
					type:'post',
					data:$('#oneReplyMessage').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							var index = $('#replyMessageList').datagrid('getRowIndex',row);
							$('#replyMessageList').datagrid('updateRow',{
								index:index,
								row:{
									chkReportRecordId:row.chkReportRecordId,
									chkItemName:row.chkItemName,
									chkContent:row.chkContent,
									chkResult:row.chkResult,
									chkResultFlag:row.chkResultFlag,
									replyContent:replyContent,
								
								}
								
							});
						}
					}
				});
			}else
			{
				$.messager.alert("提示","请填写回复内容");
			}
		}else
		{
			$.messager.alert("提示","请选择一条要回复的报告记录");
		}
		
	}
	//保存整个report的回复
	function saveReportReply()
	{
	
		var report =  $('#reportList').datagrid('getSelected');
		if(report!=null)
		{
			//判断是否回复完了
			$.ajax({
					url:sybp()+'/qAChkReportAction_isReplyFinish.action?chkReportCode='+report.chkReportCode,
					type:'post',
					dataType:'json',
					success:function(r){
						if(r&&r.isFinish)
						{
							qm_showQianmingDialog('afterSignSaveReportReply');
						}else if(r&&!r.isFinish)
						{
							//$.messager.alert("提示","报告中存在需要回复的项还没有回复");
							//$.messager.defaults = { ok: "是", cancel: "否" }; 
			               // $.messager.confirm('确认','报告中存在需要回复的项还没有回复,是否继续提交FM!',function(r){   
			               $.messager.alert('提示框','报告中所有不符合项都需要回复!');
			              // $.messager.defaults = { ok: "确定", cancel: "取消" }; 
				               
						}
					}
			});
			
			

		}else
		{
			$.messager.alert("提示","请选择报告");
		}
	}
	function afterSignSaveReportReply()
	{
		var report =  $('#reportList').datagrid('getSelected');
		var index = $('#reportList').datagrid('getRowIndex',report);
		if(report!=null)
		{
			$.messager.progress({title: '请稍后',
						msg: '处理中...',
						text:''});
			$.ajax({
				url:sybp()+'/qAChkReportAction_commitReplyToFM.action?chkReportCode='+report.chkReportCode,
				type:'post',
				dataType:'json',
				success:function(r){
					$.messager.progress('close');
				 	$('#replyNewStatus').html(r.replyState);//回复的最新状态
				 	//$('#reportList').datagrid('reload');
				 	//$('#reportList').datagrid('select',index);
				 	$('#qaChkReportDetails').datagrid('reload');
				 	
				 	 $('#reportList').datagrid('updateRow',{
							index:index,
							row:{
								 replyState:r.replyState,
							}
					 });
				 	 $('#reportList').datagrid('selectRow',index);
				 	if(r){
				 		var msg="SD提交回复后发邮件出现错误！";
						sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					 }
				}
			});
			
			$('#replyMessageDialog').dialog('close');

		}else
		{
			$.messager.alert("提示","请选择报告");
		}
		
	}
	function printReply()
	{
		var report =  $('#reportList').datagrid('getSelected');
		if(report!=null)
		{
			var time=$('#reportTime').combobox('getValue');
			 var status = $('#reportStatus').combobox('getValue');
			 var searchCondition = $('#searchCondition').searchbox('getValue');
			window.location.href = sybp()+'/qAChkReportAction_ireport.action?time='+time+'&status='+status+'&searchCondition='+searchCondition+'&chkReportCode='+report.chkReportCode+'&printContent=reply';
			
		}else
		{
			$.messager.alert("提示","请选择报告");
		}
	
	}
	function printDelay()
	{
		var report =  $('#reportList').datagrid('getSelected');
		if(report!=null)
		{
			var time=$('#reportTime').combobox('getValue');
			 var status = $('#reportStatus').combobox('getValue');
			 var searchCondition = $('#searchCondition').searchbox('getValue');
			window.location.href = sybp()+'/qAChkReportAction_ireport.action?time='+time+'&status='+status+'&searchCondition='+searchCondition+'&chkReportCode='+report.chkReportCode+'&printContent=delay';
		}else
		{
			$.messager.alert("提示","请选择报告");
		}
	
	}
	
	//延迟整改
	function delayApply()
	{
		var reportRow =  $('#reportList').datagrid('getSelected');
		if(reportRow!=null)
		{
			document.getElementById("delayApplyDialog2").display="block";
			
			$('#delayApplyList').datagrid({
				url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+reportRow.chkReportCode,
				
			});
	
			$('#delayApplyDialog').dialog('open');
		}else {
			$.messager.alert("提示","请选择一个检查报告");
		}
		
	}
	function saveReportRecordDelay()
	{
		var row = $('#delayApplyList').datagrid('getSelected');
		if(row!=null)
		{
			var oneDelayRsn = $('#oneDelayRsn').val();
			var oneDelayPlanDate = $('#oneDelayPlanDate').datebox('getValue');
			if(oneDelayRsn!=''&&oneDelayPlanDate!='')
			{
				$.ajax({
					url:sybp()+'/qAChkReportRecordAction_saveDelayMessage.action?chkReportRecordId='+row.chkReportRecordId,
					type:'post',
					dataType:'json',
					data:$('#recordDelayForm').serialize(),
					success:function(r){
						if(r&&r.success)
						{
							var index = $('#delayApplyList').datagrid('getRowIndex',row);
							$('#delayApplyList').datagrid('updateRow',{
								index:index,
								row:{
									chkReportRecordId:row.chkReportRecordId,
									chkItemName:row.chkItemName,
									chkContent:row.chkContent,
									chkResult:row.chkResult,
									chkResultFlag:row.chkResultFlag,
									replyContent:row.replyContent,
									advice:row.advice,
									needDelay:1,
									delayRsn:oneDelayRsn,
									delayPlanFinishDate:oneDelayPlanDate
								
								}
								
							});
						}
					}
				});
			}else
			{
				$.messager.alert("提示","请填写原因和预计落实日期");
			}
		}else
		{
			$.messager.alert("提示","请选择一条要回复的报告记录");
		}
		
	}
	function removeReportRecordDelay()
	{
		var row = $('#delayApplyList').datagrid('getSelected');
		if(row!=null&&row.delayPlanFinishDate!=null&&row.delayRsn!=null)
		{
				$.ajax({
					url:sybp()+'/qAChkReportRecordAction_remodeDelay.action?chkReportRecordId='+row.chkReportRecordId,
					type:'post',
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							var index = $('#delayApplyList').datagrid('getRowIndex',row);
							$('#delayApplyList').datagrid('updateRow',{
								index:index,
								row:{
									chkReportRecordId:row.chkReportRecordId,
									chkItemName:row.chkItemName,
									chkContent:row.chkContent,
									chkResult:row.chkResult,
									chkResultFlag:row.chkResultFlag,
									replyContent:row.replyContent,
									advice:row.advice,
									needDelay:0,
									delayRsn:'',
									delayPlanFinishDate:''
								}
								
							});
						}
					}
				});
			
		}else
		{
			$.messager.alert("提示","请选择一条有延迟申请的报告记录");
		}
		
	}
	//保存整个delay
	function saveReportDelay()
	{
		var report =  $('#reportList').datagrid('getSelected');
		
		if(report!=null)
		{
			//判断是否有延迟申请
			$.ajax({
					url:sybp()+'/qAChkReportAction_hasDelayRecord.action?chkReportCode='+report.chkReportCode,
					type:'post',
					dataType:'json',
					success:function(r){
						if(r&&r.hasDelayRecord)
						{
							qm_showQianmingDialog('afterSignSaveReportDelay');
						}else
						{
							$.messager.alert("提示","该报告中不包含需要申请延迟整改的项");
						}
					}
			});
			
			
		}else
		{
			$.messager.alert("提示","请选择报告");
		}
		
	}
	function afterSignSaveReportDelay()
	{
		var report =  $('#reportList').datagrid('getSelected');
		if(report!=null)
		{
			var index =  $('#reportList').datagrid('getRowIndex',report);
			$.messager.progress({title: '请稍后',
				msg: '处理中...',
				text:''});
			$.ajax({
				url:sybp()+'/qAChkReportAction_commitDelayToFM.action?chkReportCode='+report.chkReportCode,
				type:'post',
				dataType:'json',
				success:function(r){
					$.messager.progress('close');
					$('#delayNewStatus').html(r.needDelay);//延迟的最新状态
			 	//	$('#reportList').datagrid('reload');
					$('#qaChkReportDetails').datagrid('reload');
			 		 $('#reportList').datagrid('updateRow',{
							index:index,
							row:{
								 needDelay:r.needDelay,
							}
					 });
				 	 $('#reportList').datagrid('selectRow',index);
				 	 if(r){
				 		var msg="提交延迟整改申请后发邮件出现错误！";
						sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					 }
				}
			});
			
			$('#delayApplyDialog').dialog('close');

		}else
		{
			$.messager.alert("提示","请选择报告");
		}
	}
	
	function sendNotification(msgTitle,msgContent,receiverList,msg){
		if(receiverList!=null&&receiverList!=''){
			
			  $.ajax({
		      	url : sybp()+'/qAChkReportAction_sendNotification.action',
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
	//FM批复
	function replyApplyApproval()
	{
		
		var reportRow =  $('#reportList').datagrid('getSelected');
		if(reportRow!=null)
		{
			document.getElementById("replyApprovalDialog2").display="block";
			
			$('#oneReplyFmreveiwRemark').val('');
			$('#replyApplyForApprovalList').datagrid({
				url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+reportRow.chkReportCode,
				
			});
			$('#replyApprovalDialog').dialog('open');
		}else {
			$.messager.alert("提示","请选择一个检查报告");
		}
	}
	function acceptReplyApply()
	{
		var reportRow =  $('#reportList').datagrid('getSelected');
		if(reportRow!=null)
		{
			var replyFmreviewResult = $("input[name='replyFmreviewResult']:checked").val();
			var replyFmreveiwRemark = $('#oneReplyFmreveiwRemark').val();
			if(replyFmreviewResult==1&&replyFmreveiwRemark=='')//有意见，并且没有备注
			{
				$.messager.alert("提示","请填写对于回复的意见");
			}else			
			{
				qm_showQianmingDialog('aftersignSaveReplyApproval');
			
			}
		
		}else {
			$.messager.alert("提示","请选择一个检查报告");
		}
	}
	function aftersignSaveReplyApproval()
	{
		var reportRow =  $('#reportList').datagrid('getSelected');
		$.messager.progress({title: '请稍后',
							msg: '处理中...',
							text:''});
		$.ajax({
			url:sybp()+'/qAChkReportAction_saveReplyApproval.action?chkReportCode='+reportRow.chkReportCode,
			type:'post',
			data:$('#oneReplyMessageForm').serialize(),
			dataType:'json',
			success:function(r){
				$.messager.progress('close');
				if(r&&r.success)
				{
					var index =  $('#reportList').datagrid('getRowIndex',reportRow);
					 $('#reportList').datagrid('updateRow',{
							index:index,
							row:{
								 replyState:r.replyState,
							}
					 });
					 $('#reportList').datagrid('selectRow',index);
					 $('#replyNewStatus').html(r.replyState);//回复的最新状态
					
					 var msg="FM审批回复后发邮件出现错误！";
					 sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
						 
				}
			}
		});
		
		$('#replyApprovalDialog').dialog('close');
	}
	function delayApplyApproval()
	{
		var reportRow =  $('#reportList').datagrid('getSelected');
		if(reportRow!=null)
		{
			document.getElementById("delayApprovalDialog2").display="block";
			
			$('#oneDelayFmreveiwRemark').val('');
			$('#delayApplyForApprovalList').datagrid({
				url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+reportRow.chkReportCode,
				
			});
			$('#delayApprovalDialog').dialog('open');
		}else {
			$.messager.alert("提示","请选择一个检查报告");
		}
	}
	function acceptDelayApply(delayState)
	{
		var report =  $('#reportList').datagrid('getSelected');
		$('#passedDelayState').val(delayState);
		if(report!=null)
		{
			var delayFmreviewResult = $("input[name='delayState']:checked").val();
			var delayFmreveiwRemark = $('#oneDelayFmreveiwRemark').val();
			if(delayFmreviewResult==-1&&delayFmreveiwRemark=='')//有意见，并且没有备注
			{
				$.messager.alert("提示","请填写对于延迟整改的意见");
			}else			
			{
				qm_showQianmingDialog('aftersignSaveDelayApproval');
			}
			
		
		}else
		{
			$.messager.alert("提示","请选择一个报告");
		}
	}
	
	function aftersignSaveDelayApproval()
	{
		var report =  $('#reportList').datagrid('getSelected');
		var delayState = $('#passedDelayState').val();//实际上是needDelay的值
		$.messager.progress({title: '请稍后',
			msg: '处理中...',
			text:''});
		$.ajax({
			url:sybp()+'/qAChkReportAction_acceptDelayApply.action?chkReportCode='+report.chkReportCode,
			type:'post',
			data:$('#oneDelayMessageForm').serialize(),
			dataType:'json',
			success:function(r){
			
				$.messager.progress('close');
				
				if(r&&r.success)
				{
					var index =  $('#reportList').datagrid('getRowIndex',report);
					 $('#reportList').datagrid('updateRow',{
							index:index,
							row:{
								 needDelay:r.needDelay,
							}
					 });
					 $('#reportList').datagrid('selectRow',index);
					 
	 				$('#delayNewStatus').html(r.needDelay);//延迟整改的最新状态

	 				var msg='FM审批延迟整改后发邮件出现错误！';
					sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
				}
				
			}
		});
		
		$('#delayApprovalDialog').dialog('close');
	}
	
	function searchReport()
	{
		var time=$('#reportTime').combobox('getValue');
		 var status = $('#reportStatus').combobox('getValue');
		 var searchCondition = $('#searchCondition').searchbox('getValue');
		$('#reportList').datagrid({
			singleSelect:true,
	    	url:sybp()+'/qAChkReportAction_getByTimeStatusAndCondition.action?time='+time+'&status='+status+'&searchCondition='+searchCondition,
		});
		//$('#reportList').datagrid('reload');
		$('#SOPList').datagrid('loadData',[]);
		$('#qaChkReportDetails').datagrid('loadData',[]);
			
		$('#replyNewStatus').html('');//回复的最新状态
		$('#delayNewStatus').html('');//延迟整改的最新状态
	
			
		$('#qamForReport').html('');
		$('#qamForReport2').html('');
		
		$('#qaForReport').html('');
		$('#fmForReport').html('');
		$('#sdForReport').html('');
		
		initReportButton(null);
	}
</script>

<script type="text/javascript">

    	$(function(){
        	$('#wholePageForScheduleReport').css('display','');
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight-65;
    		var tableWidth  = document.body.clientWidth;

			var childTableWidth = tableWidth*1;

			$('#reportTime').combobox({
	   			 url:sybp()+'/qAChkAction_getYears.action',
	   			 valueField:'year',
	   			 textField:'year',
	   			 onSelect: function(rec){
	   			 	searchReport();
	   			 },
	   			onLoadSuccess:function(){
	 	   		//alert( $('#timeForReportList').val()+"==="+$('#statusForReportList').val()+"==="+$('#searchConditionForReportList').val()+"==="+$('#chkReportCodeForReportList').val());
		        
	 	   			if($('#timeForReportList').val()!=null&&$('#timeForReportList').val()!='')
	 	   			{
	 	   				$('#reportTime').combobox('setValue',$('#timeForReportList').val());
	 	   				$('#timeForReportList').val('');
	 	   				
	 	   			}
	   				
		 	   	}
	    	});
			$('#reportStatus').combobox({
	   			 onSelect: function(rec){
					searchReport();
	   			 },
		    });
			if($('#statusForReportList').val()!=null&&$('#statusForReportList').val()!='')
	   		{
				$('#reportStatus').combobox('setValue',$('#statusForReportList').val());
				$('#statusForReportList').val('');
	   		}
	   		
			 $('#reportList').datagrid({
	        		height:tableHeight*0.83+92,
					//width:childTableWidth*0.4,
					width:485,
					singleSelect:true,
					idField:'chkReportCode',
					//url:sybp()+"/qAChkReportAction_getByTimeStatusAndCondition.action",
					columns:[[
								/*
							{
								title:'Id',
								field:'chkReportCode',
								hidden:true,
							},*/
							{
								title:'报告编号',
								field:'chkReportCode',
								width:110,
							},
							{
								title:'专题编号',
								field:'studyNo',
								width:100,
							},
							//如果不是不是方案或者变更（方案或者变更是没有检查计划的），直接返回研究
							{
								title:'类型',
								field:'catalog',
								width:40,
								
							},
							{
								title:'建立时间',
								field:'createTime',
								width:70,
							},
							
							{
								title:'报告状态',
								field:'rptState',
								width:80,
								formatter: function(value,row,index){
								//0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
									if(value==0)
									{
										return "草稿";
									}
									if(value==1)
									{
										return "提交";
									}
									if(value==2)
									{
										return "QAM审批通过";
									}
									if(value==-2)
									{
										return "QAM审批未通过";
									}
									if(value==3)
									{
										//"needDelay","无延迟整改信息","未申请";"提交FM";"FM有意见";"提交QA";"QA确认接收";
										
										//replyState="草稿";"提交FM";"FM有意见";"提交QA";"QA确认接收";"无回复信息");
										
										//row.needDelay//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
										//	row.delayState//0：未完成；1：已完成
										//  row.needReply //0 No;  1: yes
										//	row.replyState//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收
										//	row.needReChk //0：不需要；1：需要
										//	row.reChkState//0：问题未解决；1：问题已解决
										// role "FM" "SD" "QALead" "QA" "PathSDLead" "PathSD"
										var str="回复中";
										if(row.replyState=='无回复信息'||row.replyState=='草稿')
											str="SD待回复";
										else if(row.replyState=='提交FM')
											str="FM待审批回复";
										else if(row.replyState=='FM有意见'||row.replyState=='提交QA')
											str="QA待接收回复";
										if(str!="回复中")
											str+="\n";
										if(row.needDelay=='提交FM') 
										{
											str+="FM待审批延迟整改"
										}else if(row.needDelay=='FM有意见'||row.needDelay=='提交QA')
										{
											str+="QA待接收延迟整改";
										}
										//alert(str+"==="+row.replyState);
										return str;
										
									}
									if(value==4)
									{
										return "进入再检查";
									}
									if(value==5)
									{
										return "进入延迟整改";
									}
									if(value==9)
									{
										return "完成";
									}
		         		
								}
							},
							{
								title:'QA检查员',
								field:'qa',
								width:60,
							},
							
							{
								title:'QA负责人',
								field:'qam',
								hidden:true,
							},
							{
								title:'sd',
								field:'sd',
								hidden:true,
							},
							{
								title:'fm',
								field:'fm',
								hidden:true,
							},
							{
								title:'延迟整改状态',
								field:'needDelay',
								hidden:true,
							},
							{
								title:'延迟整改状态',
								field:'delayState',
								hidden:true,
							},
							{
								title:'是否需回复',
								field:'needReply',
								hidden:true,
							},
							{
								title:'回复状态',
								field:'replyState',
								hidden:true,
							}
							
							
						]],
						onSelect:function(rowIndex,rowData)
						{
				 			$('#SOPList').datagrid({
				 				url:sybp()+'/qAChkReportAction_getSOPByReport.action?chkReportCode='+rowData.chkReportCode,
								
						 	});
				 			//$('#SOPList').datagrid('reload');
				 			$('#qaChkReportDetails').datagrid({
				 				url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+rowData.chkReportCode,
					 		});
				 			//$('#qaChkReportDetails').datagrid('reload');
				 			
							//alert(rowData.replyState+"=="+rowData.delayState);
				 			$('#replyNewStatus').html(rowData.replyState);//回复的最新状态
				 			$('#delayNewStatus').html(rowData.needDelay);//延迟整改的最新状态
							initReportButton(rowData);

				 			
							$('#qamForReport').html(rowData.qam);
							$('#qamForReport2').html(rowData.qam);
							
							$('#qaForReport').html(rowData.qa);
							$('#fmForReport').html(rowData.fm);
							$('#sdForReport').html(rowData.sd);
						},
						onLoadSuccess: function(){
							//alert("report load success="+$('#chkReportCodeForReportList').val()+"==="+$('#isSelectOneReportFlag').val());
							if($('#chkReportCodeForReportList').val()!=null&&$('#chkReportCodeForReportList').val()!=''&&
									$('#isSelectOneReportFlag').val()=='1')
							{
								$(this).datagrid('selectRecord',$('#chkReportCodeForReportList').val());
								$('#isSelectOneReportFlag').val('0');
								$('#chkReportCodeForReportList').val('');
							}
						}
						
	            });
				if($('#chkReportCodeForReportList').val()!=null&&$('#chkReportCodeForReportList').val()!='')
				{
					$('#isSelectOneReportFlag').val('1');
					 searchReport();
				}else{
					 $('#reportList').datagrid({
						url:sybp()+"/qAChkReportAction_getByTimeStatusAndCondition.action",
					 });
				}
				
				$('#searchCondition').searchbox({
			   	 	searcher:function(value,name){
						searchReport();
					},
			  	  prompt:'请输入报告编号',
				});
				$('#searchCondition').searchbox('setValue',$('#searchConditionForReportList').val());
				$('#searchConditionForReportList').val('');	
				
	            $('#SOPList').datagrid({
	            	height:tableHeight*0.23+10,
					//width:childTableWidth*0.3,
					width:425,
					columns:[[
							{
								title:'Id',
								field:'sopRecordId',
								hidden:true,
							},
							{
								title:'fileRecordId',
								field:'fileRecordId',
								hidden:true,
							},
							{
								title:'编号',
								field:'sopCode',
								width:100,
							},
							{
								title:'检查依据的法规',
								field:'sopName',
								width:300,
							},
					]],
		        });
	            $('#qaChkReportDetails').datagrid({
	            	height:tableHeight*0.6+30,
					//width:childTableWidth*0.7,
					width:863,
					singleSelect:true,
					nowrap:false,				
					columns:[[
							{
								title:'Id',
								field:'chkReportRecordId',
								hidden:true,
							},
							{
								title:'检查项目',
								field:'chkItemName',
								width:80,
							},
							{
								title:'检查内容',
								field:'chkContent',
								width:200,
							},
							{
								title:'检查发现',
								field:'chkResultDesc',
								hidden:true,
							},
							{
								title:'检查结果',
								field:'chkResult',
								width:120,
								formatter: function(value,row,index){
									if (value=='×'){
										return row.chkResultDesc;
									} else {
										return value;
									}
							
								}
							},{
								title:'需回复否',
								field:'chkResultFlag',
								width:50,
								formatter: function(value,row,index){
									if (value==-1){
										return "是";
									} else {
										return "否";
									}
							
								}
							},
							{
								title:'建议',
								field:'advice',
								width:100,
							},
							
							{
								title:'回复内容',
								field:'replyContent',
								width:150,
							},
							{
								title:'再检查结果',
								field:'reChkResult',
								width:80,
								formatter: function(value,row,index){
									if(row.chkResult=='√'){
										return "NA";
									}else{
										if (value==0){
											return "未检查";
										} else if (value==-1){
											return "问题未解决";
										}else if (value==1){
											return "问题已解决";
										}
									}
								}
								
							},
							{
								title:'申请延迟',
								field:'needDelay',
								width:80,
								formatter: function(value,row,index){
									if(row.chkResult=='√'){
										return 'NA';
									}else{
										if (value==0){
											return "未申请";
										} else {
											return "已申请";
										}
									}
								}
							
							},{
								title:'延迟原因',
								field:'delayRsn',
								width:150,
							},{
								title:'预计落实',
								field:'delayPlanFinishDate',
								width:100,
							},{
								title:'实际落实',
								field:'delayFinishTime',
								width:100,
							},
					]],
					onSelect:function(rowIndex,rowData)
					{
				 		
					},
	            	rowStyler: function(index,row){
		            	if (row.chkResult=="×"){
							return 'background-color:#6293BB;color:#fff;';
						}
					}
		        });
				 //$('#oneQAChkRecord').width(childTableWidth*0.3);
				 $('#studyMessage').height(tableHeight*0.23+10);
	          	 $('#reportInfoTable').height(tableHeight*0.23+10);
	          	
				 $('#replyMessageList').datagrid({
					 	height:300,
						width:735,
						singleSelect:true,
						nowrap:false,			
						columns:[[
									{
										
										title:'Id',
										field:'chkReportRecordId',
										hidden:true,
									},
									{
										title:'检查项目',
										field:'chkItemName',
										width:80,
									},
									{
										title:'检查内容',
										field:'chkContent',
										width:200,
									},
									
									{
										title:'检查发现',
										field:'chkResultDesc',
										hidden:true,
									},
									{
										title:'检查结果',
										field:'chkResult',
										width:120,
										formatter: function(value,row,index){
											if (value=='×'){
												return row.chkResultDesc;
											} else {
												return value;
											}
									
										}
									},{
										title:'需回复否',
										field:'chkResultFlag',
										width:50,
										formatter: function(value,row,index){
											if (value==-1){
												return "是";
											} else {
												return "否";
											}
									
										}
									},
									{
										title:'建议',
										field:'advice',
										width:100,
									},
									{
										title:'回复内容',
										field:'replyContent',
										width:150,
									}
						]],
						onSelect:function(rowIndex,rowData)
						{
					 		if(rowData.chkResult!='×')
					 		{
					 			$.messager.alert("提示","本条内容不需要回复");
					 			$('#saveOneReplyMessageButton').linkbutton('disable');

						 	}else
						 	{
							 	$('#oneChkContent').val(rowData.chkContent);
								$('#oneMessage').val(rowData.replyContent);
								$('#saveOneReplyMessageButton').linkbutton('enable');
							}
						},
				});
				 $('#replyApplyForApprovalList').datagrid({
					 	height:300,
						width:735,
						singleSelect:true,
						nowrap:false,					
						columns:[[
									{
										
										title:'Id',
										field:'chkReportRecordId',
										hidden:true,
									},
									{
										title:'检查项目',
										field:'chkItemName',
										width:80,
									},
									{
										title:'检查内容',
										field:'chkContent',
										width:200,
									},
									
									{
										title:'检查发现',
										field:'chkResultDesc',
										hidden:true,
									},
									{
										title:'检查结果',
										field:'chkResult',
										width:120,
										formatter: function(value,row,index){
											if (value=='×'){
												return row.chkResultDesc;
											} else {
												return value;
											}
									
										}
									},{
										title:'需回复否',
										field:'chkResultFlag',
										width:50,
										formatter: function(value,row,index){
											if (value==-1){
												return "是";
											} else {
												return "否";
											}
									
										}
									},
									{
										title:'建议',
										field:'advice',
										width:100,
									},
									{
										title:'回复内容',
										field:'replyContent',
										width:150,
									}
						]],
						onSelect:function(rowIndex,rowData)
						{
					 		
						},
				});
	    		$('#delayApplyList').datagrid({
				 	height:300,
					width:950,
					singleSelect:true,
					nowrap:false,				
					columns:[[
								{
									
									title:'Id',
									field:'chkReportRecordId',
									hidden:true,
								},
								{
									title:'检查项目',
									field:'chkItemName',
									width:80,
								},
								{
									title:'检查内容',
									field:'chkContent',
									width:200
								},
								
								{
										title:'检查发现',
										field:'chkResultDesc',
										hidden:true,
								},
								{
										title:'检查结果',
										field:'chkResult',
										width:120,
										formatter: function(value,row,index){
											if (value=='×'){
												return row.chkResultDesc;
											} else {
												return value;
											}
									
										}
								},{
									title:'需回复否',
									field:'chkResultFlag',
									width:50,
									formatter: function(value,row,index){
										if (value==-1){
											return "是";
										} else {
											return "否";
										}
								
									}
								},
								{
									title:'建议',
									field:'advice',
									width:100,
								},
								{
									title:'回复内容',
									field:'replyContent',
									width:120,
								},{
									title:'申请延迟',
									field:'needDelay',
									width:60,
									formatter: function(value,row,index){
										if(row.chkResult=='√'){
											return 'NA';
										}else{
											if (value==0){
												return "未申请";
											} else {
												return "已申请";
											}
										}
									}
								
								},{
									title:'延迟原因',
									field:'delayRsn',
									width:110,
								},{
									title:'预计落实',
									field:'delayPlanFinishDate',
									width:80,
								},
					]],
					onSelect:function(rowIndex,rowData)
					{
				 		if(rowData.chkResult!='×')
				 		{
				 			$.messager.alert("提示","本条内容不需要延迟整改！");
				 			$('#saveReportRecordDelayButton').linkbutton('disable');
				 			$('#removeReportRecordDelayButton').linkbutton('disable');

					 	}else
					 	{
						 	$('#oneDelayItem').val(rowData.chkContent);
						 	$('#oneDelayRsn').val(rowData.delayRsn);
						 	$('#saveReportRecordDelayButton').linkbutton('enable');
				 			$('#removeReportRecordDelayButton').linkbutton('enable');
						}
					},
			});

		   	$('#delayApplyForApprovalList').datagrid({
		   		height:300,
				width:955,
				singleSelect:true,
				nowrap:false,			
				columns:[[
							{
								
								title:'Id',
								field:'chkReportRecordId',
								hidden:true,
							},
							{
								title:'检查项目',
								field:'chkItemName',
								width:80,
							},
							{
								title:'检查内容',
								field:'chkContent',
								width:200,
							},
							
							{
									title:'检查发现',
									field:'chkResultDesc',
									hidden:true,
							},
							{
									title:'检查结果',
									field:'chkResult',
									width:100,
									formatter: function(value,row,index){
										if (value=='×'){
											return row.chkResultDesc;
										} else {
											return value;
										}
									
									}
							},{
								title:'需回复否',
								field:'chkResultFlag',
								width:50,
								formatter: function(value,row,index){
									if (value==-1){
										return "是";
									} else {
										return "否";
									}
							
								}
							},
							{
								title:'建议',
								field:'advice',
								width:100,
							},
							{
								title:'回复内容',
								field:'replyContent',
								width:120,
							},{
								title:'申请延迟',
								field:'needDelay',
								width:60,
								formatter: function(value,row,index){
									if(row.chkResult=='√'){
										return 'NA';
									}else{
										if (value==0){
											return "未申请";
										} else {
											return "已申请";
										}
									}
							
								}
							
							},{
								title:'延迟原因',
								field:'delayRsn',
								width:120,
							},{
								title:'预计落实',
								field:'delayPlanFinishDate',
								width:90,
							},
				]],

		    });
	        initReportButton(null);

	     // alert( $('#timeForReportList').val()+"==="+$('#statusForReportList').val()+"==="+$('#searchConditionForReportList').val()+"==="+$('#chkReportCodeForReportList').val());
	        
        });//匿名函数结束
        
        function initReportButton(row)
        {
        	//"replyMessageButton" "saveReportReplyButton" "printReplyButton" "replyApplyApproval"
        	//"delayApplyButton" "saveReportDelayButton" "printDelayButton" "delayApplyApprovalButton"
			$('#replyMessageButton').linkbutton('disable');
			$('#saveReportReplyButton').linkbutton('disable');
			$('#printReplyButton').linkbutton('disable');
			$('#replyApplyApproval').linkbutton('disable');

			$('#delayApplyButton').linkbutton('disable');
			$('#saveReportDelayButton').linkbutton('disable');
			$('#printDelayButton').linkbutton('disable');
			$('#delayApplyApprovalButton').linkbutton('disable');

			//var row = $('#reportList').datagrid('getSelected');
	
			if(row!=null&&row!='')
			{
				$('#printReplyButton').linkbutton('enable');
				$('#printDelayButton').linkbutton('enable');
				var role = $('#roleForReport').val();
					//alert(role+"==="+row.replyState+"==="+row.delayState);
					if(role=='FM'&&(row.replyState!='无回复信息'&&row.replyState=='提交FM'))
					{
						$('#replyApplyApproval').linkbutton('enable');
					}
					if(role=='FM'&&(row.needDelay!='无延迟整改信息'&&row.needDelay=='提交FM'))
					{
						$('#delayApplyApprovalButton').linkbutton('enable');
					}
				if(row.haveRight==true||row.haveRight=='true')
				{
					
					if(row.rptState==3)//9完成
					{
						if(role=='SD'&&row.needReply==1&&(row.replyState=='无回复信息'||row.replyState=='草稿'))
						{
							$('#replyMessageButton').linkbutton('enable');
							$('#saveReportReplyButton').linkbutton('enable');
						}
						if(role=='SD'&&(row.needDelay=='无延迟整改信息'||row.needDelay=='草稿'||row.delayState==1))
						{
							$('#delayApplyButton').linkbutton('enable');
							$('#saveReportDelayButton').linkbutton('enable');
						}
					}

					
				}
				

				
			}
        	
        }
	 
</script>
	  
</head>

<body >
	
	<div class="container" id="wholePageForScheduleReport" style="display:none;overflow:hidden;">
		<s:hidden id="chkIndexId"></s:hidden>
		<s:hidden id="passedDelayState"></s:hidden>
		<s:hidden id="isSelectOneReportFlag"></s:hidden>
		
		<s:hidden id="roleForReport" name="role"></s:hidden>
		<!--打印返回用于保持原来状态的  -->
		<s:hidden id="timeForReportList" name="time"></s:hidden>
		<s:hidden id="statusForReportList" name="status"></s:hidden>
		<s:hidden id="searchConditionForReportList" name="searchCondition"></s:hidden>
		<s:hidden id="chkReportCodeForReportList" name="chkReportCode"></s:hidden>
		
	    <!-- 
		<a id="signQAChkRecord" class="easyui-linkbutton" onclick="editQAChkPlan();" data-options="iconCls:'icon-edit',plain:true">查看</a>
		<a id="signQAChkRecord" class="easyui-linkbutton" onclick="editQAChkPlan();" data-options="iconCls:'icon-edit',plain:true">确认报告完成</a>
		<a id="signQAChkRecord" class="easyui-linkbutton" onclick="editQAChkPlan();" data-options="iconCls:'icon-edit',plain:true">出具QA声明</a>
	     -->
		<div style="float:left;position:relative;left: 10px;margin-top:5px;height:30px;border:0;">
			时间<select id="reportTime" data-options="editable:false" class="easyui-combobox" style="width:70px;"></select>
			 状态<select id="reportStatus" data-options="editable:false" class="easyui-combobox" style="width:120px;">
			 <!-- 0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成 -->
			     <option value="10">报告未完成</option>
			     <!-- 
	   			 <option value="0">草稿</option>
	   			 <option value="1">提交</option>
	   			 <option value="2">QAM审批通过</option>
	   			 <option value="-2">QAM审批未通过</option>
			      -->
	   			 <!-- 
	   			"回复中";
				"SD待回复";
				"FM待审批回复";
				"QA待接收回复";
				"FM待审批延迟整改"
				"QA待接收延迟整改";
	   			  -->
				 <option value="3">回复中</option>
	   			 <option value="31">SD待回复</option>
	   			 <!-- 
	   			 <option value="32">FM待审批回复</option>
	   			  -->
	   			 <option value="33">QA待接收回复</option>
				<!-- 
	   			 <option value="34">FM待审批延迟整改</option>
 				-->
	   			 <option value="35">QA待接收延迟整改</option>
	   			    
	   			 <option value="4">进入再检查</option>
	   			 <option value="5">进入延迟整改</option>
	   			 <option value="9">完成</option>
			  </select>
			  &nbsp;
			  <span style="position:absolute;left:250px;top:0px;">
			  <input id="searchCondition" class="easyui-searchbox" style="position:absolute;width:200px;"></input>
			  </span>
			  <div  style="position:absolute;top:30px;">
			  	<div id="reportList" class="easyui-datagrid"></div>
		  	 </div>
	 </div><!-- 左边结束 -->
     <div style="float:left;position:absolute;margin-top:0px;left:500px;background:#fff;overflow:auto; width:100%;border:0;height:100%"  >
 	 	 		<div  style="border: 1px solid #fff;height: 70px;">
	 	 	 		<div style="float:left;border: 1px solid #c8c8c8;position:relative;width: 425px;top:10px;">
	 	 	 			<div>报告回复</div>
	 	 	 			<a id="replyMessageButton" class="easyui-linkbutton" onclick="replyMessage();" data-options="iconCls:'icon-edit',plain:true">回复信息</a>
						<a id="saveReportReplyButton" class="easyui-linkbutton" onclick="saveReportReply();" data-options="iconCls:'icon-add',plain:true">提交</a>
						<a id="printReplyButton" class="easyui-linkbutton" onclick="printReply();" data-options="iconCls:'icon-printer',plain:true">打印</a>
						<!-- 
	   					<a id="replyApplyApproval" class="easyui-linkbutton" onclick="replyApplyApproval();" data-options="iconCls:'icon-edit',plain:true">FM审批</a>
						 -->
	   					<div style="height: 20px;margin-top:8px;">
	   					<!-- 
	   						<a id="signQAChkRecord" class="easyui-linkbutton" onclick="editQAChkPlan();" data-options="iconCls:'',plain:true">
						-->
								<span id="replyNewStatus" ></span>
						<!-- 
							</a>
							-->
	   					</div>
	 	 	 		</div>
	 	 	 		<div style="float:left;border: 1px solid #c8c8c8;position:relative;left:10px;top:10px;width: 425px;">
	 	 	 			<div>延迟整改</div>
	 	 	 			<a id="delayApplyButton" class="easyui-linkbutton" onclick="delayApply();" data-options="iconCls:'icon-edit',plain:true">延迟整改信息</a>
						<a id="saveReportDelayButton" class="easyui-linkbutton" onclick="saveReportDelay();" data-options="iconCls:'icon-add',plain:true">提交</a>
						<a id="printDelayButton" class="easyui-linkbutton" onclick="printDelay();" data-options="iconCls:'icon-printer',plain:true">打印</a>
						<!-- 
	   					<a id="delayApplyApprovalButton" class="easyui-linkbutton" onclick="delayApplyApproval();" data-options="iconCls:'icon-edit',plain:true">FM审批</a>
						 -->
						 
	   					<div style="height: 20px;margin-top:8px;">
	   						<!-- 
	   						 <a id="signQAChkRecord" class="easyui-linkbutton" onclick="editQAChkPlan();" data-options="iconCls:'',plain:true">
	   						 -->
								<span id="delayNewStatus" style="width:300px;"></span>
							<!-- 
							</a>
							 -->
	   					</div>
	 	 	 		</div>
 	 	 		</div>
 	 	 	
			   	<div  style="border: 1px solid #fff;position:relative;top:10px;">
			   		<div id="qaChkReportDetails" class="easyui-datagrid" style="position:relative;top:0px;overflow: auto;"></div>
			   	</div>
			   	<div  style="border: 1px solid #fff;position:relative;top:10px;">
				   	<div id="studyMessage" style="float:left;border: 1px solid #c8c8c8;width: 425px;">
	 	 	 			
	 	 	 			<table id="reportInfoTable" style="width:100%;">
	 	 	 				<tr>
	 	 	 					<td style="width:60px;">负责人：</td>
	 	 	 					<td colspan="3"><label id="qamForReport"></label></td>
	 	 	 				</tr>
	 	 	 				<tr>
	 	 	 					<td style="width:50px;">发送：</td>
	 	 	 					<td><label id="sdForReport"></label></td>
	 	 	 					<td style="width:50px;">抄送：</td>
	 	 	 					<td><label id="fmForReport"></label></td>
	 	 	 					
	 	 	 				</tr>
	 	 	 				<tr>
	 	 	 					<td>QA检查员：</td>
	 	 	 					<td><label id="qaForReport"></label></td>
	 	 	 					<td>QAM：</td>
	 	 	 					<td><label id="qamForReport2"></label></td>
	 	 	 				</tr>
	 	 	 			</table>
	 	 	 			
	 	 	 		</div>
	 	 	 		<div style="float:left;border: 1px solid #c8c8c8;position:relative;left:10px;width: 425px;">
	 	 	 			
	 	 	 			<div id="SOPList" class="easyui-datagrid">
	   					</div>
	 	 	 		</div>
	 	 	 		
	 	 	 	</div>
			   	
			 </div>
		
	
 
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/replyMessage.jspf"%>
    <%@ include file="/WEB-INF/jsp/QAChkReportAction/delayApply.jspf"%>
    <%@ include file="/WEB-INF/jsp/QAChkReportAction/replyApproval.jspf"%>
    <%@ include file="/WEB-INF/jsp/QAChkReportAction/delayApproval.jspf"%>
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</body>
</html>




