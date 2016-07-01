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
	function addChkReport(addOrEdit)
	{
		document.getElementById("addQAChkReportDialog2").display="block";
		
		var startChkDate = $('#startChkDate').datebox('getValue');
		var endChkDate = $('#endChkDate').datebox('getValue');
		if(startChkDate==''&&endChkDate=='')
		{
			 $('#startChkDate').datebox('setValue',$('#reportStartDateValue').val());
			 $('#endChkDate').datebox('setValue',$('#reportEndDateValue').val());
			    
		}
		$('#addOrEditForReport').val(addOrEdit);
		if(addOrEdit==1)//新增
		{
			document.getElementById('addQAChkReportDialog').title="新增检查报告";
			$('#changeReportRecordForm').css('display','none');
		}else if(addOrEdit==2){//编辑
			document.getElementById('addQAChkReportDialog').title="选择列入报告中的检查项";
			$('#changeReportRecordForm').css('display','');
		}
			$('#programsForAddReportDiv').css('display','');
			var chkReportCode='';
		
			if(addOrEdit==1)//新增
			{
				chkReportCode='';
				$('#programs').combobox({
					disabled:'',
				});
			}else if(addOrEdit==2)//编辑
			{
				var row = $('#reportStatusTable').datagrid('getSelected');
				if(row==null||row=='')
				{
					$.messager.alert("提示","请选择一个报告！");
					return;
				}else{
					chkReportCode =row.chkReportCode;
					if(row.rptState==-2)
					{
						document.getElementById('addQAChkReportDialog').title="编辑记录的建议";
						$('#programs').combobox({
							disabled:'disabled',
						});
						//alert("qam意见："+row.qamApprovalOpinion);
						$('#qamApprovalOpinionForReport').html("QAM审批意见："+row.qamApprovalOpinion);
					}else{
						$('#programs').combobox({
							disabled:'',
						});
					}
				}

				
			}
		    $('#reportItems').treegrid({
	    		url:sybp()+'/qAChkRecordAction_getListByDateAndProgram.action?chkReportCode='+chkReportCode+'&QAMainPage='+$('#QAMainPage').val()+'&studyNoParam='+$('#studyNoParamForReport').val()+'&startChkDate='+ $('#startChkDate').datebox('getValue')+'&endChkDate='+$('#endChkDate').datebox('getValue'),
				
			});
			//$('#reportItems').treegrid('reload');
			
			
			$('#programs').combobox({
				url:sybp()+'/qAChkIndexAction_getByChkTime.action?chkReportCode='+chkReportCode+'&QAMainPage='+$('#QAMainPage').val()+'&studyNo='+$('#studyNoParamForReport').val()+'&startChkDate='+ $('#startChkDate').datebox('getValue')+'&endChkDate='+$('#endChkDate').datebox('getValue'),
			});
			//$('#programs').combobox('reload');
			$('#addQAChkReportDialog').dialog('open');
		/*}else if(addOrEdit==2){//编辑
			var row = $('#reportStatusTable').datagrid('getSelected');
			if(row==null||row=='')
			{
				$.messager.alert("提示","请选择一个报告！");
			}else{
				//$('#changeReportRecordForm').css('display','');
				$('#changeReportRecordForm').css('display','none');
				//$('#programsForAddReportDiv').css('display','none');
				$('#programsForAddReportDiv').css('display','');
				$('#updateOneReportRecordButton').linkbutton('disable');
				
				$('#updateChkContentLabel').val('');
				$('#updateReportRcordAdvice').val('');
				$('#reportItems').treegrid({
			    	url:sybp()+'/qAChkRecordAction_getRecordsByReport.action?chkReportCode='+row.chkReportCode+'&QAMainPage='+$('#QAMainPage').val()+'&startChkDate='+ $('#startChkDate').datebox('getValue')+'&endChkDate='+$('#endChkDate').datebox('getValue'),
			    	
					onSelect:function(rowData)
					{
					
						if(rowData.chkResult=='×')
						{
							$('#updateChkContentLabel').val(rowData.chkContent);
							$('#updateReportRcordAdvice').val(rowData.advice);
							$('#updateOneReportRecordButton').linkbutton('enable');
						}else{
							$.messager.alert('提示','本条检查记录不需要回复！','',function(){

								$('#reportItems').treegrid('unselect',rowData.chkIndexId);
								$('#updateOneReportRecordButton').linkbutton('disable');
							});
						}
							
						
					}
				});
				$('#reportItems').treegrid('reload');
				$('#addQAChkReportDialog').dialog('open');
			}
			
		}*/
	
	}
	function updateOneReportRecord()
	{
		if($('#updateChkContentLabel').val()!='')
		{
			var row = $('#reportItems').treegrid('getSelected');
			if(row!=null&&row!='')
			{
				$.ajax({
					url:sybp()+'/qAChkReportRecordAction_updateOneReportRecord.action?chkRecordId='+row.chkRecordId,
					type:'post',
					data:$('#changeReportRecordForm').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							$('#reportItems').treegrid('reload');
						}else{
							$.messager.alert("提示框",r.msg);
						}
							
							
					}
				});
			}else{
				$.messager.alert("提示","请选择要修改的项");
			}
				

		}else{
			$.messager.alert('提示','请选择一条需要修改的记录');
		}	
		
	}
	
	function updatePrograms(){
		//新建的时候才有作用，编辑的时候是灰的
		var chkReportCode='';
		var addOrEdit = $('#addOrEditForReport').val();
		if(addOrEdit==1)//新增
		{
			chkReportCode='';
		}else if(addOrEdit==2)//编辑
		{
			var row = $('#reportStatusTable').datagrid('getSelected');
			if(row==null||row=='')
			{
				$.messager.alert("提示","请选择一个报告！");
				return;
			}else{
				chkReportCode =row.chkReportCode;
			}
		}
		$('#programs').combobox({
			url:sybp()+'/qAChkIndexAction_getByChkTime.action?chkReportCode='+chkReportCode+'&QAMainPage='+$('#QAMainPage').val()+'&studyNo='+$('#studyNoParamForReport').val()+'&startChkDate='+ $('#startChkDate').datebox('getValue')+'&endChkDate='+$('#endChkDate').datebox('getValue'),
		});
	
	}
	
	function searchReportRecord()
	{
		var records = $('#programs').combobox('getValues');
		var chkIndexIds = new Array();
		for(var i=0;i<records.length;i++)
		{
			chkIndexIds.push(records[i]);
		}
		var addOrEdit = $('#addOrEditForReport').val();
		var chkReportCode='';
		if(addOrEdit==1)//新增
		{
			chkReportCode='';
		}else if(addOrEdit==2)//编辑
		{
			var row = $('#reportStatusTable').datagrid('getSelected');
			if(row==null||row=='')
			{
				$.messager.alert("提示","请选择一个报告！");
				return;
			}else{
				chkReportCode =row.chkReportCode;
			}
		}
		$('#reportItems').treegrid({
			url:sybp()+'/qAChkRecordAction_getListByDateAndProgram.action?chkReportCode='+chkReportCode+'&studyNoParam='+$('#studyNoParamForReport').val()+'&startChkDate='+ $('#startChkDate').datebox('getValue')+'&endChkDate='+$('#endChkDate').datebox('getValue')+'&qaIndexIds='+chkIndexIds,
		});
		//$('#reportItems').treegrid('reload');
	}
	
	function getItemsByType(chkType,rowSize,addOrEdit,chkReportCode,reportRecord)
	{
		$.ajax({
			url:sybp()+'/qAChkReportAction_getChkItemSizeByChkType.action?addOrEdit='+addOrEdit+'&chkReportCode='+chkReportCode+'&chkType='+chkType+'&studyNoParam='+$('#studyNoParamForReport').val(),
			type:'post',
			//data:$('#oneQAChkRecord').serialize(),
			dataType:'json',
			success:function(r){
				if(r&&r.success){
					if(r.itemSize==rowSize)
					{
						realSaveReport(addOrEdit,chkReportCode,reportRecord);
					}else{
						$.messager.alert("提示","专题方案/报告的检查项还没有检查完，请先检查完毕才可以生成专题方案/报告的检查报告！");
					}
				}
			}
		});
		
	}
	function saveReport()
	{
	
		var addOrEdit = $('#addOrEditForReport').val();
	
		var reportRecord = new Array();
		var rows = $('#reportItems').treegrid('getData');//获取treegrid的所有数据
		if(rows!=null&&rows!='')
		{
			var chkReportCode='';
			if(addOrEdit==2)
			{
				var row = $('#reportStatusTable').datagrid('getSelected');
				if(row==null||row=='')
				{
					$.messager.alert("提示","请选择一个报告！");
					return;
				}else{
					chkReportCode =row.chkReportCode;
				}
			}
			var chkType = rows[0].chkType;
				var flag = true;
				for(var i=0;i<rows.length;i++)
				{
					if(rows[i].chkType==chkType)
					{
						if(rows[i].children!=null)
						{
							for(var j=0;j<rows[i].children.length;j++)
							{ 
								var children = rows[i].children[j];
								reportRecord.push(children.chkRecordId);
							}
						}
					}else{
						flag = false;
						break;
					}
							
				}
				if(flag)
				{
					//方案或报告
					if(chkType==4||chkType==5){		
				 		getItemsByType(chkType,rows.length,addOrEdit,chkReportCode,reportRecord);
					}else{
						realSaveReport(addOrEdit,chkReportCode,reportRecord);
					}
				}else{
					$.messager.alert("提示","检查数据类型（方案，报告，日程检查...）不一致，不可以生成在一个检查报告内！");
				}
					
			/*}else if(addOrEdit==2)//编辑
			{
				//因为在form中已经改变了record的值，直接关闭就可以了
				$('#addQAChkReportDialog').dialog('close');
			}*/
		}else{
			$.messager.alert("提示","请先确认检查数据！");
		}
		
	}
	function realSaveReport(addOrEdit,chkReportCode,reportRecord)
	{
		$.messager.progress({title: '请稍后',
				msg: '处理中...',text:''});
			$.ajax({
				url:sybp()+'/qAChkReportAction_save.action?addOrEdit='+addOrEdit+'&chkReportCode='+chkReportCode+'&studyNoParam='+$('#studyNoParamForReport').val()+'&records='+reportRecord,
				type:'post',
				//data:$('#oneQAChkRecord').serialize(),
				dataType:'json',
				success:function(r){
					$.messager.progress('close');
					if(r&&!r.success)
					{
						$.messager.alert("提示",r.msg);
					}
					if(r&&r.success)
					{
						if(addOrEdit==1)
						{
							$('#reportStatusTable').datagrid('insertRow',{
							index:0,//插入最前面一行
							row:{
								chkReportCode:r.chkReportCode,
								qam:r.qam,
								createTime:r.createTime,
								rptState:r.rptState,
								catalog:r.catalog,
								}
							});
							$('#reportStatusTable').datagrid('selectRow',0);
						}else if(addOrEdit==2){
							var row = $('#reportStatusTable').datagrid('getSelected');
							var index = $('#reportStatusTable').datagrid('getRowIndex',row);
							$('#reportStatusTable').datagrid('selectRow',index);
						}
									
					}
				}
			});
			$('#addQAChkReportDialog').dialog('close');
				
	}
	function delChkReport()
	{
		//var report = $('#reportStatusTable').datagrid('getSelected');
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
		
		if(reports!=null&&reports!=''&&reports!='[]')
		{
			$.messager.confirm('确认框', '确定要删除选中的报告吗?', function(r0){
				if (r0){
					$.ajax({
						//url:sybp()+'/qAChkReportAction_del.action?chkReportCode='+report.chkReportCode,
						url:sybp()+'/qAChkReportAction_del.action?reports='+reports,
						type:'post',
						//data:$('#oneQAChkRecord').serialize(),
						dataType:'json',
						success:function(){
							//var index = $('#reportStatusTable').datagrid('getRowIndex',report);
							//$('#reportStatusTable').datagrid('deleteRow',index);
							$('#reportStatusTable').datagrid('unselectAll');
							$('#reportStatusTable').datagrid('reload');
							
							$('#qaChkReportDetails').datagrid('loadData',[]);
							$('#SOPList').datagrid('loadData',[]);
							initReportButton(null);
						}
					});
				}
			});
			
		}else
		{
			$.messager.alert("提示","请选择一个报告");
		}
	}
	
	function commit(){
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
		//var report = $('#reportStatusTable').datagrid('getSelected');
		if(reports!=null&&reports!=''&&reports!='[]')
		{
			qm_showQianmingDialog('afterSignCommit');
		}else
		{
			$.messager.alert("提示","请选择一个报告");
		}
	}
	
	function afterSignCommit()
	{
		$.messager.progress({title: '请稍后',
				msg: '处理中...',text:''});
		//var report = $('#reportStatusTable').datagrid('getSelected');
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
		if(reports!=null&&reports!=''&&reports!='[]')
		{
			$.ajax({
				//url:sybp()+'/qAChkReportAction_commit.action?chkReportCode='+report.chkReportCode,
				url:sybp()+'/qAChkReportAction_commit.action?reports='+reports,
				type:'post',
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$.messager.progress('close');
						/*var index =  $('#reportStatusTable').datagrid('getRowIndex',report);
						 $('#reportStatusTable').datagrid('updateRow',
						   {	index:index,
								row:{
									 rptState:1,
								}
						 });*/
						// $('#reportStatusTable').datagrid('selectRow',index);
						// report = $('#reportStatusTable').datagrid('getSelected');
						 
						// reports = $('#reportStatusTable').datagrid('getSelections');
						// initReportButton(reports);

						 $('#reportStatusTable').datagrid('reload');
							
					    var msg='报告提交后发邮件出现错误！';
						//sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
						sendNotificationList(r.emailList,msg);//提交发多次邮件
					}
				}
			});
			
			
		}else
		{
			$.messager.alert("提示","请选择一个报告");
		}

	}
	function reChk()
	{
		//var row =  $('#reportStatusTable').datagrid('getSelected');
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
		//if(row!=null)
		if(reports!=null&&reports!=''&&reports!='[]')
		{
			document.getElementById("reChkDialog2").display="block";
			$('#reChkItems').datagrid({
				//url:sybp()+'/qAChkReportAction_getReChkRecordByReport.action?chkReportCode='+row.chkReportCode,
				url:sybp()+'/qAChkReportAction_getReChkRecordByReport.action?reports='+reports,
		 	});
		 		
			$('#reChkDialog').dialog('open'); 
		}else{
			$.messager.alert("提示","请选择一个检查报告");
		}
		
	}
	function clickReChk(reportReplyState,reportNeedDelay,chkResultFlag,reChkResult,reChkFlag,needDelay,delayFinishTime)
	{
		//var rowData = $('#reChkItems').datagrid('getSelected');
		//if(rowData!=null)
		//{
			//alert(chkResultFlag+"==="+reChkResult+"==="+needDelay+"==="+delayFinishTime);
			if((chkResultFlag=="0"||chkResultFlag=='1'||reChkResult=="1")&&reChkFlag!=1&&
					(needDelay==null||(needDelay==1&&delayFinishTime==null)))
		 	{
			 	$.messager.alert('提示框','本条记录不需要再检查');
		 	}else{
				document.getElementById("reChkResultDialog2").display="block";
				
				//var row =  $('#reportStatusTable').datagrid('getSelected');
				//回复 rowData.reChkResult0未检查，1解决，-1未解决
				var f=false;
				//(reChkResult==0||reChkResult=='-1')不管问题有没有解决，只要是需要在检查就可以检查回复
				if(reportReplyState==3&&chkResultFlag=="-1"&&reChkFlag==1)
				{
					 $('#reChkResultTableRow').css('display','');
	   			     $('#isNeedReChkTableRow').css('display','');
					 f=true;
				}else{
				     $('#reChkResultTableRow').css('display','none');
		   			 $('#isNeedReChkTableRow').css('display','none');
				}
				
				//延迟整改(报告的needDelay是状态，rowData是0：未申请；1：已申请)
				if(reportNeedDelay==3&&needDelay==1&&(delayFinishTime==null||delayFinishTime==''))
				{
				     $('#realGetTime').css('display','');
					//默认当前日期
					$('#delayFinishTimeTD').datebox('setValue',getNowFormatDate());
					
	   			 	f=true;
				}else{
					 $('#realGetTime').css('display','none');
				}
			
				if(f)
				{
					$('#reChkResultDialog').dialog('open');
				}else{
					$.messager.alert("提示框","本条记录不需要再检查！");
				}
		 	}
		//}else{
		//	$.messager.alert("提示框","请选择一条再检查记录！");
		//}
		
	}
	function selectReChkResult()
	{
		var reChkResult = $("#problemSolved").attr("checked");
		//alert(reChkResult);
		if(reChkResult)
		{
			$('#recordNotNeedReChk').attr('checked','true');
			//$('#recordNotNeedReChk').attr('disabled',true);
			//$('#recordNeedReChk').attr('disabled',true);
		}else{
			//$('#recordNotNeedReChk').attr('disabled',false);
			//$('#recordNeedReChk').attr('disabled',false);
		}
		
	}
	function signQAReChkRecords()
	{
		var signReChkRecord = new Array();
		var rows = $('#reChkItems').datagrid('getRows');
		for(var i=0;i<rows.length;i++)
		{
			signReChkRecord.push(rows[i].chkReportRecordId);
		}	
		$.ajax({
			//url:sybp()+'/qAChkReportRecordAction_saveOneReChk.action?chkReportRecordId='+row.chkReportRecordId,
			url:sybp()+'/qAChkReportRecordAction_hasReChkRecord.action?signReChkRecord='+signReChkRecord,
			type:'post',
			data:$('#oneReChkResult').serialize(),
			dataType:'json',
			success:function(r){
				if(r&&r.has)
				{
					qm_showQianmingDialog("afterSignQAReChkRecords");
				}else{
					$.messager.alert("提示框","所选报告中不存在还没有签字的再检查记录！");
				}
			}
		});
		
	}
	
	function afterSignQAReChkRecords()
	{
		var signReChkRecord = new Array();
		var rows = $('#reChkItems').datagrid('getRows');
		for(var i=0;i<rows.length;i++)
		{
			signReChkRecord.push(rows[i].chkReportRecordId);
		}
		
		$.messager.progress({title: '请稍后',
			msg: '处理中...',text:''});
		$.ajax({
			//url:sybp()+'/qAChkReportRecordAction_saveOneReChk.action?chkReportRecordId='+row.chkReportRecordId,
			url:sybp()+'/qAChkReportRecordAction_signReChk.action?signReChkRecord='+signReChkRecord,
			type:'post',
			data:$('#oneReChkResult').serialize(),
			dataType:'json',
			success:function(r){
				$.messager.progress('close');
				if(r&&r.success)
				{
					$('#reChkItems').datagrid('reload');
					 
					if(r.finishType!=''){
						$.messager.alert("提示",r.finishType);
						
						//$('#reportStatusTable').datagrid('selectRow');
					 }
					var msg='再捡查签字确认后发邮件出现错误！';
					sendNotificationList(r.emailList,msg);
					 
				}
			}
		});
	}
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = year + seperator1 + month + seperator1 + strDate;
	            //+ " " + date.getHours() + seperator2 + date.getMinutes()
	           // + seperator2 + date.getSeconds();
	    //return currentdate;
	    //当前日期
	    return $('#reportEndDateValue').val();
	}
	
	//再检查签字放最上面
	
	//function saveOneReChk(){
	//	qm_showQianmingDialog('afterSignSaveOneReChk');
	//}
	//function afterSignSaveOneReChk()
	function saveOneReChk()
	{
		var reportRow =  $('#reportStatusTable').datagrid('getSelected');
		var row = $('#reChkItems').datagrid('getSelected');
		
		$.messager.progress({title: '请稍后',
			msg: '处理中...',text:''});
		$.ajax({
			//url:sybp()+'/qAChkReportRecordAction_saveOneReChk.action?chkReportRecordId='+row.chkReportRecordId,
			url:sybp()+'/qAChkReportRecordAction_saveOneTempReChk.action?chkReportRecordId='+row.chkReportRecordId,
			type:'post',
			data:$('#oneReChkResult').serialize(),
			dataType:'json',
			success:function(r){
				$.messager.progress('close');
				if(r&&r.success)
				{
					var index =  $('#reChkItems').datagrid('getRowIndex',row);
					
					$('#reChkItems').datagrid('updateRow',
					{	index:index,
						row:{
								reChkFlagTemp:r.reChkFlag,
							 	reChkResultTemp:r.reChkResult,
							 	delayFinishTimeTemp:r.delayFinishTime,
							}
					});
					
					 $('#reChkItems').datagrid('selectRow',index);
				
					 $('#reChkResultDialog').dialog('close');
					 
				}
			}
		});
		
	}
	//分开回复和延迟整改的情况，失效
	function afterSignSaveOneReChk2()
	{
		var reChkORDelay=$('#reChkOrDelay').val();//1延迟整改 2回复
		var row = $('#reChkItems').datagrid('getSelected');
		if(reChkORDelay==2)//回复
		{
			$('#realGetTime').css('display','none');
			//var chkReportRecordId = $('#reportRecordId').val();
			$.messager.progress({title: '请稍后',
								msg: '处理中...',text:''});
			$.ajax({
				url:sybp()+'/qAChkReportRecordAction_saveOneReChk.action?chkReportRecordId='+row.chkReportRecordId,
				type:'post',
				data:$('#oneReChkResult').serialize(),
				dataType:'json',
				success:function(r){
					$.messager.progress('close');
					if(r&&r.success)
					{
						 var index =  $('#reChkItems').datagrid('getRowIndex',row);
						 var reChkFlagLabel = "";
						 if(r.reChkFlag==0)
							 reChkFlagLabel='N';
						 if(r.reChkFlag==1)
							 reChkFlagLabel='Y';
						 
						 var reChkResultLabel = "";
						 //0：未检查；-1：问题未解决；1：问题已解决
						 if(r.reChkResult==0)
							 reChkResultLabel='未检查';
						 if(r.reChkResult==1)
							 reChkResultLabel='问题已解决';
						 if(r.reChkResult==-1)
							 reChkResultLabel='问题未解决';
							
						 $('#reChkItems').datagrid('updateRow',
						   {	index:index,
								row:{
									reChkFlag:reChkFlagLabel,
							 		reChkResult:reChkResultLabel,
								}
							  });
						 $('#reChkItems').datagrid('reload');
	
						 $('#reChkResultDialog').dialog('close');
						 
						 if(r.reportFinish==true)
						 {
							 $.messager.alert("提示","所有回复项已经再检查结束,报告完成！");
							 $('#reChkDialog').dialog('close');
							 var row2 =  $('#reportStatusTable').datagrid('getSelected');
							var index2 = $('#reportStatusTable').datagrid('getRowIndex',row2);
							$('#reportStatusTable').datagrid('updateRow',
							   {	index:index2,
									row:{
										rptState:9,
									}
							});
								$('#reportStatusTable').datagrid('selectRow',index2);
							 
						 }else if(r.finish==true){
							$.messager.alert("提示","该报告回复再检查完毕！");
							var row2 =  $('#reportStatusTable').datagrid('getSelected');
							var index2 = $('#reportStatusTable').datagrid('getRowIndex',row2);
							$('#reportStatusTable').datagrid('updateRow',
						   	{	index:index2,
								 row:{
									needReChk:0,//不需再检查
								}
							  });
							$('#reportStatusTable').datagrid('selectRow',index2);
						 }
					}
				}
			});
		}else if(reChkORDelay==1){
			//延迟整改
			$('#realGetTime').css('display','');
			//alert($('#delayFinishTimeTD').datebox('getValue'));
			if($('#delayFinishTimeTD').datebox('getValue')!=null&&$('#delayFinishTimeTD').datebox('getValue')!='')
			{
				$.ajax({
					url:sybp()+'/qAChkReportRecordAction_saveOneDelay.action?chkReportRecordId='+row.chkReportRecordId,
					type:'post',
					data:$('#oneReChkResult').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							 var index =  $('#reChkItems').datagrid('getRowIndex',row);
								
							 $('#reChkItems').datagrid('updateRow',
							   {	index:index,
									row:{
										 delayFinishTime:$('#delayFinishTimeTD').datebox('getValue'),
									}
								});
							 $('#reChkItems').datagrid('reload');
		
							 $('#reChkResultDialog').dialog('close');
							 if(r.reportFinish==true)
							 {
								 $.messager.alert("提示","所有延迟整改项已经在检查结束,报告完成！");
								 $('#reChkDialog').dialog('close');
								 var row2 =  $('#reportStatusTable').datagrid('getSelected');
								var index2 = $('#reportStatusTable').datagrid('getRowIndex',row2);
								$('#reportStatusTable').datagrid('updateRow',
									  {	index:index2,
										row:{
											rptState:9,
										}
								});
								$('#reportStatusTable').datagrid('selectRow',index2);
							 }else if(r.finish==true){
								$.messager.alert("提示","所有延迟整改项已经完成!");
								var row2 =  $('#reportStatusTable').datagrid('getSelected');
								var index2 = $('#reportStatusTable').datagrid('getRowIndex',row2);
								$('#reportStatusTable').datagrid('updateRow',
							   	{	index:index2,
									 row:{
										delayState:1,//延迟整改结束
									}
								  });
								$('#reportStatusTable').datagrid('selectRow',index2);
							 }
							 
							 
						}
					}
				});
			}else{
				$.messager.alert("提示","请选择实际落实日期");
			}
			
		}
		
	}
	
	function viewReport()
	{
		document.getElementById("viewReportDialog2").display="block";
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
		$('#needConfirmReportType').val('');
	//	var row = $('#reportStatusTable').datagrid('getSelected');
		if(rows!=null&&rows!='')
		{
				$('#replyConfirm').css('display','none');
				$('#delayConfirm').css('display','none');
				$('#replyAndDelayConfirm').css('display','none');
				$('#replyConfirmLabel').css('display','none');
				$('#delayConfirmLabel').css('display','none');
				$('#replyAndDelayConfirmLabel').css('display','none');
				
			var fmRemark = "";
			var confirmLabel = "";
			for(var i=0;i<rows.length;i++)
			{
				if(rows[i].replyFmreveiwRemark!=''&&rows[i].replyFmreveiwRemark!='null'&&rows[i].replyFmreveiwRemark!=null)
					fmRemark+="FM对"+rows[i].chkReportCode+"回复的意见:"+rows[i].replyFmreveiwRemark;
				else if(rows[i].replyFmreveiwRemark=='')
					fmRemark+="FM对"+rows[i].chkReportCode+"回复无意见"
				if(rows[i].delayFmreveiwRemark!=''&&rows[i].delayFmreveiwRemark!='null'&&rows[i].delayFmreveiwRemark!=null)
				{
					if(fmRemark!='')
					{
						fmRemark+="&nbsp;&nbsp;&nbsp;";
					}
					fmRemark+="FM对"+rows[i].chkReportCode+"延迟的意见:"+rows[i].delayFmreveiwRemark;
				}else if(rows[i].delayFmreveiwRemark==''){
					if(fmRemark!='')
					{
						fmRemark+="&nbsp;&nbsp;&nbsp;";
					}
					fmRemark+="FM对"+rows[i].chkReportCode+"延迟无意见";
				}
				fmRemark += "<br>";
				if(rows[i].replyState==2||rows[i].replyState==-1)
				{
					if($('#needConfirmReportType').val()=='')
					{
						if(confirmLabel==""||confirmLabel=="回复")
						{
							confirmLabel="回复";
						}
				 		$('#needConfirmReportType').val(0);
				 		
				 	}else if($('#needConfirmReportType').val()=='1'){
				 		if(confirmLabel=="延迟整改"||confirmLabel=="回复和延迟整改")
				 		{
				 			confirmLabel="回复和延迟整改";
				 		}
				 		$('#needConfirmReportType').val(2);
				 	}
				 	//alert(i+"回复="+$('#needConfirmReportType').val());
				}
				if(rows[i].needDelay==2||rows[i].needDelay==-1)
				{
						if($('#needConfirmReportType').val()=='')
						{
							if(confirmLabel==""||confirmLabel=="延迟整改")
							{
								confirmLabel="延迟整改";
							}
							$('#needConfirmReportType').val(1);
							
						}else if($('#needConfirmReportType').val()=='0'){
							if(confirmLabel=="回复"||confirmLabel=="回复和延迟整改")
							{
								confirmLabel="回复和延迟整改";
							}
							$('#needConfirmReportType').val(2);
						}
					
					//alert(i+"延迟="+$('#needConfirmReportType').val());
				}
				
			
			}
			if($('#needConfirmReportType').val()=='0')
			{
				$('#replyConfirm').css('display','');
				$('#replyConfirmLabel').css('display','');
				$('#replyConfirm').attr('checked','checked');
			}else if($('#needConfirmReportType').val()=='1')
			{	
				$('#delayConfirm').css('display','');
				$('#delayConfirmLabel').css('display','');
				$('#delayConfirm').attr('checked','checked');
			}else if($('#needConfirmReportType').val()=='2'){
				$('#replyConfirm').css('display','');
				$('#replyConfirmLabel').css('display','');
				
				$('#delayConfirm').css('display','');
				$('#delayConfirmLabel').css('display','');
			
				$('#replyAndDelayConfirm').css('display','');
				$('#replyAndDelayConfirmLabel').css('display','');
				$('#replyAndDelayConfirm').attr('checked','checked');
			}
			
			$('#acceptConifrmTypeLabel').html("");
			
			$('#reportFMRemark').html(fmRemark);
		
			$('#replyDelayList').datagrid({
				//url:sybp()+'/qAChkReportAction_getReChkRecordByReport.action?chkReportCode='+row.chkReportCode,
				url:sybp()+'/qAChkReportAction_getReChkRecordByReport.action?reports='+reports,
		 	});
			$('#replyDelayList').datagrid('reload');
				
				
			$('#viewReportDialog').dialog('open');	
		}else{
			$.messager.alert("提示","请选择一个报告");
		}	
	}
	function printReport()
	{
		var row = $('#reportStatusTable').datagrid('getSelected');
		if(row!=null&&row!='')
		{
			 var reportStartDate =  $('#reportStartDate').datebox('getValue');
	         var reportEndDate =  $('#reportEndDate').datebox('getValue');
	         if(reportStartDate==null||reportStartDate=='')
	         {
	          	reportStartDate = $('#reportStartDateValue').val();
	         }
	         if(reportEndDate==null||reportEndDate=='')
	         {
	          	reportEndDate = $('#reportEndDateValue').val();
	         }
	         var reportStatus = $('#reportStatus').combobox('getValue');
	         var reportCatalog = $('#reportCatalog').combobox('getValue');
	         var reportSearcher = $('#reportSearcher').searchbox('getValue');
			
			window.location.href = sybp()+"/qAChkReportAction_ireport.action?QAMainPage="+$('#QAMainPage').val()+"&studyNoParam="+$('#studyNoParamForReport').val()+"&reportStartDate="+reportStartDate+"&reportEndDate="+reportEndDate+"&reportStatus="+reportStatus+"&reportCatalog="+reportCatalog+"&reportSearcher="+reportSearcher+"&chkReportCode="+row.chkReportCode+"&printContent=report";
			
		}else{
			$.messager.alert("提示","请选择一个报告");
		}
	}
	
	function qaAcceptReplyMessages()
	{
		qm_showQianmingDialog('afterSignQAAcceptReplyMessages');
	}
	function afterSignQAAcceptReplyMessages()
	{
		var isNeedReChk = $("input[name='isNeedReChk']:checked").val();
		//var confirmReportType=$('#needConfirmReportType').val();
	//	alert(isNeedReChk+"==="+confirmReportType);//0回复 1整改2两者
		var confirmReportType = $("input[name='confirmReportType']:checked").val();
		
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
	//	var row = $('#reportStatusTable').datagrid('getSelected');
		$.ajax({
			//url:sybp()+'/qAChkReportAction_confirmDelayOrReply.action?chkReportCode='+row.chkReportCode,
			url:sybp()+'/qAChkReportAction_confirmDelayOrReply.action?reports='+reports,
			type:'post',
			data:{
				needReChk:isNeedReChk,
				confirmReportType:confirmReportType,
			},
			dataType:'json',
			success:function(r){
				if(r)
				{
					 $('#viewReportDialog').dialog('close');

					/* var index =  $('#reportStatusTable').datagrid('getRowIndex',row);
					 $('#reportStatusTable').datagrid('updateRow',{
						 	index: index,
							row: {
								replyState:r.replyState,
								needDelay:r.needDelay,//needDelay和replyState是一个级别的
								needReChk:r.needReChk,
								rptState:r.rptState
							
							}
						 
					 });
					 initReportButton(row);
					 */
					 $('#reportStatusTable').datagrid('reload');
					 
					var type="";
					if(confirmReportType==0)
					{
						type="回复";
					}else if(confirmReportType==1){
						type="延迟整改";
					}else if(confirmReportType==2){
						type="回复和延迟整改";
					}
						
					var msg='QA接收'+type+'后发邮件出现错误！';
					//sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					sendNotificationList(r.emailList,msg);
				}
			}
		});

		
	}
	
	//QAM审批报告
	function qamApprovalReport()
	{
		document.getElementById("qamApprovalReportDialog2").display="block";
		var row = $('#reportStatusTable').datagrid('getSelected');
		if(row!=null&&row!='')
		{
			$('#firstChecked').attr('checked','checked');
			$('#reportApprovalOpinion').val('');
			
			$('#qamApprovalReportDialog').dialog('open');	
		}else{
			$.messager.alert("提示","请选择一个报告");
		}
		
	}
	
	function changeReportResult(approvalResultFlag)
	{
		//var approvalResultFlag = $("input[name='approvalResultFlag']:checked").val();
		//alert("click时间2222："+approvalResultFlag);
		if(approvalResultFlag==1)
		{
			$('#haveNoComments').attr('checked','checked');
			$('#reportApprovalOpinion').val('');
			$('#reportApprovalOpinion').css('readonly','readonly');
		}
		if(approvalResultFlag==-1)
		{
			$('#haveComments').attr('checked','checked');
			$('#reportApprovalOpinion').css('readonly','');
		}
		
	}
	function saveQAMApprovalReport()
	{
		var approvalResultFlag = $("input[name='approvalResultFlag']:checked").val();
		if(approvalResultFlag==-1)
		{
			var opinion = $('#reportApprovalOpinion').val();
			if(opinion=='')
			{
				$.messager.alert("提示","请填写意见！");
			}else{
				qm_showQianmingDialog('afterSignQAMApprovalReport');
			}
		}else{
			qm_showQianmingDialog('afterSignQAMApprovalReport');
		}
	}
	function afterSignQAMApprovalReport()
	{
		//var row = $('#reportStatusTable').datagrid('getSelected');
		var reports = new Array();
		var rows = $('#reportStatusTable').datagrid('getSelections');
		for(var i=0;i<rows.length;i++)
		{
			reports.push(rows[i].chkReportCode);
		}
		$.messager.progress({title: '请稍后',
			msg: '处理中...',
			text:''});
		$.ajax({
			//url:sybp()+'/qAChkReportAction_qamApprovalReport.action?chkReportCode='+row.chkReportCode,
			url:sybp()+'/qAChkReportAction_qamApprovalReport.action?reports='+reports,
			type:'post',
			data:$('#oneQAMApprovalReportResult').serialize(),
			dataType:'json',
			success:function(r){
				$.messager.progress('close');
				if(r&&!r.success)
				{
					$.messager.alert("提示",r.msg);
				}
				if(r&&r.success)
				{
					/*var index =  $('#reportStatusTable').datagrid('getRowIndex',row);
					 $('#reportStatusTable').datagrid('updateRow',{	
							 index: index,
							 row: {
							 		rptState:r.rptState,
							 		canEndReport:r.canEndReport,
						 	 }
					 	 
					 });*/
					 $('#reportStatusTable').datagrid('reload');
					 var rows = $('#reportStatusTable').datagrid('getSelections');
					// alert("success=="+row.rptState);
					 initReportButton(rows);

					 var msg='QAM审批报告后发邮件出现错误！';
					// sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
					sendNotificationList(r.emailList,msg);
				}
			}
		});
		$('#qamApprovalReportDialog').dialog('close');	
	}
	function confirmFinishReport(chkReportCode){
		$('#oneChkReportCodeForSign').val(chkReportCode);
		qm_showQianmingDialog('afterSignConfirmFinishReport');
	}
	function afterSignConfirmFinishReport(){
		var chkReportCode = $('#oneChkReportCodeForSign').val();
		$.ajax({
			url:sybp()+'/qAChkReportAction_confirmFinishReport.action?chkReportCode='+chkReportCode,
			type:'post',
		//	data:$('#oneQAMApprovalReportResult').serialize(),
			dataType:'json',
			success:function(){
					var row = $('#reportStatusTable').datagrid('getSelected');
					var index =  $('#reportStatusTable').datagrid('getRowIndex',row);
					 $('#reportStatusTable').datagrid('updateRow',{	
							 index: index,
							 row: {
							 		rptState:9,
							 		canEndReport:false,
						 	 }
					 });
					 $('#qamApprovalReportDialog').dialog('close');	
					 row = $('#reportStatusTable').datagrid('getSelected');
					 //alert("success=="+row.rptState);
					 initReportButton(row);
				
			}
		});
	}
	function confirmReportFinish()//整个专题
	{
		$.ajax({
			url:sybp()+'/qAChkReportAction_isAllReportFinish.action?&studyNoParam='+$('#studyNoParamForReport').val(),
			type:'post',
		//	data:$('#oneQAMApprovalReportResult').serialize(),
			dataType:'json',
			success:function(r){
					if(r&&r.success){
						qm_showQianmingDialog('afterSignConfirmReportFinish');
					}
					if(r&&!r.success){
						//$.messager.defaults = { ok: "是", cancel: "否" }; 
		                /*$.messager.confirm('确认','该专题中存在没有完成的报告，是否继续确认专题完成!',function(r){    
		                	 if (r){
		                		  qm_showQianmingDialog('afterSignConfirmReportFinish');
		                	 }
		                });*/
		                $.messager.alert("提示框","该专题中存在没有完成的报告！");
		               // $.messager.defaults = { ok: "确定", cancel: "取消" }; 
					}
			}
		});
		
	}
	function afterSignConfirmReportFinish()
	{
		$.ajax({
			url:sybp()+'/qAChkReportAction_confirmStudyFinishReport.action?&studyNoParam='+$('#studyNoParamForReport').val(),
			type:'post',
		//	data:$('#oneQAMApprovalReportResult').serialize(),
			dataType:'json',
			success:function(){
				$('#haveRightForEndStudy').val('false');
				$('#haveRightForiReportStudy').val('true');
				initReportButton(null);
			}
		});
		
	}
	function qAQualityIReport()
	{
		$.ajax({
			url:sybp()+'/qAChkReportAction_getQATemple.action?&studyNoParam='+$('#studyNoParamForReport').val(),
			type:'post',
			dataType:'json',
			success:function(r){
				if(r)
				{
					if(r.only)
					{
						//parent.showMessager(2,'数据加载中...',true,5000);
						var reportStartDate =  $('#reportStartDate').datebox('getValue');
				         var reportEndDate =  $('#reportEndDate').datebox('getValue');
				         if(reportStartDate==null||reportStartDate=='')
				         {
				          	reportStartDate = $('#reportStartDateValue').val();
				         }
				         if(reportEndDate==null||reportEndDate=='')
				         {
				          	reportEndDate = $('#reportEndDateValue').val();
				         }
				         var reportStatus = $('#reportStatus').combobox('getValue');
				         var reportCatalog = $('#reportCatalog').combobox('getValue');
				         var reportSearcher = $('#reportSearcher').searchbox('getValue');
						
						window.location.href = sybp()+"/qAChkReportAction_ireport.action?QAMainPage="+$('#QAMainPage').val()+"&studyNoParam="+$('#studyNoParamForReport').val()+"&reportStartDate="+reportStartDate+"&reportEndDate="+reportEndDate+"&reportStatus="+reportStatus+"&reportCatalog="+reportCatalog+"&reportSearcher="+reportSearcher+"&printContent=study&templeId="+r.templeId;
			
					}else if(r.many){
						//选择使用哪个模板
						document.getElementById("chooseTempleDialog2").display="block";
	
						$('#qAStatementTempleList').datagrid('loadData',r.rows);
											
						$('#chooseTempleDialog').dialog('open');	
					}else{
						$.messager.alert("提示框",r.msg);
					}
				}
				
			}
		});
		
	}
	
	function chooseOneTemple()
	{
		var row = $('#qAStatementTempleList').datagrid('getSelected');
		if(row==null)
		{
			$.messager.alert("提示框","请选择一个QA出具声明的模板");
		}else{
			window.location.href=sybp()+'/qAChkReportAction_ireport.action?studyNoParam='+$('#studyNoParamForReport').val()+'&printContent=study&templeId='+row.templeId;
		}
		
	}
	function sendNotificationList(emailList,msg){
			if(emailList!=null&&emailList!=''){
				
				  $.ajax({
			      	url : sybp()+'/qAChkReportAction_sendNotificationList.action',
			      	type: 'post',
			      	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				    dataType:'json',
				    data:{
			      		emailList:emailList,
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
</script>

<script type="text/javascript">

    	$(function(){
        	$('#outReportPage').css('display','');
        	//alert("开始检查传过来的报告编号："+$('#selectChkReportCodeForList').val());
        	
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.documentElement.clientHeight-32;
    		var tableWidth  = document.documentElement.clientWidth-20;
			
			var childTableWidth = tableWidth;
			//设置宽度
   		 	/*if(parent.parent.$('#changeSizeButton')&&parent.parent.$('#changeSizeButton').text()==">")
   			{
   		 		childTableWidth = childTableWidth+290;
   	   	 	}*/
			var reportStatusTableHeight=tableHeight*0.72;
			var reportStatusTableWidth=childTableWidth*0.3;;
			var isHidden = true;
			/*
			 $('#reportStatusTable').datagrid({
	        		height:tableHeight*0.72,
					width:childTableWidth*0.3,
			 });*/
			 var SOPListHeight=tableHeight*0.21;
			 var SOPListWidth=childTableWidth*0.3;
			 /*
			  $('#SOPList').datagrid({
	            	height:tableHeight*0.23,
					width:childTableWidth*0.3,
			  });
			  */
			  var qaChkReportDetailsHeight=tableHeight*0.93+10;
			  var qaChkReportDetailsWidth=childTableWidth*0.7-35;
			  /*
			 $('#qaChkReportDetails').datagrid({
		          	height:tableHeight*0.95+10,
					width:childTableWidth*0.7-45,
			 });*/
			 $('#reportMainContainer').css("height",tableHeight+30);
			 var QAMainPage = $('#QAMainPage').val();
			 if(QAMainPage!=null&&QAMainPage=='true')
			 {
			 	 $('#reportMainContainer').css("height",tableHeight);
			 	
				 $('#outReportPage').css("height",tableHeight);
				 tableHeight=tableHeight-55;
				//tableHeight = $(document).height()-90; 
				
				 isHidden = false;
				 reportStatusTableHeight=tableHeight*0.75-10;
				 reportStatusTableWidth=tableWidth*0.4;
				 /*
				 $('#reportStatusTable').datagrid({
		        		height:tableHeight*0.72,
						width:tableWidth*0.4,
				 });*/
				 SOPListHeight=tableHeight*0.25;
				 SOPListWidth=tableWidth*0.4;
				 /*
				 $('#SOPList').datagrid({
		            	height:tableHeight*0.23,
						width:tableWidth*0.4,
				  });*/
				  qaChkReportDetailsHeight=tableHeight;
				  qaChkReportDetailsWidth=tableWidth*0.6-20;
				  /*
				 $('#qaChkReportDetails').datagrid({
		          	height:tableHeight*0.95+10,
					width:tableWidth*0.6-45,
				 });*/
			 }else{
		
				 var contentWind = parent.parent.document.getElementById('chkPlanLeft').contentWindow;	
				 //把传过来的plan内容清空
					contentWind.$('#selectChkReportCodeForLeft').val('');
					contentWind.$('#oneChkPlanIdForLeft').val('');
					
					var studyRow = contentWind.$('#studyDatagrid').datagrid('getSelected');
					if(studyRow!=null&&studyRow!='')
					{
			   		 	var label = "专题编号："+studyRow.studyNo;
						if(studyRow.sd!=null&&studyRow.sd!=''){
							label += "&nbsp;&nbsp;&nbsp;SD:"+studyRow.sd;
						}else{
							label += "&nbsp;&nbsp;&nbsp;SD:待任命";
						}
						if(studyRow.qa!=null&&studyRow.qa!=''){
							label += "&nbsp;&nbsp;&nbsp;QA:"+studyRow.qa;
						}else{
							label += "&nbsp;&nbsp;&nbsp;QA:待任命";
						}
						label="<h4>"+label+"</h4>";
						parent.parent.parent.$('#topInfoLabel').html(label);
					}
					
			 }
				 
			 
			 $('#reportStartDate').datebox('setValue',$('#reportStartDateValue').val());
			 $('#reportEndDate').datebox('setValue',$('#reportEndDateValue').val());
			 
			 var role = $('#roleForChkReport').val();
				
			 var reportStartDate =  $('#reportStartDate').datebox('getValue');
	         var reportEndDate =  $('#reportEndDate').datebox('getValue');
	        //  var reportStatus = $('#reportStatus').combobox('getValue');
	        //    var reportCatalog = $('#reportCatalog').combobox('getValue');
	       //     var reportSearcher = $('#reportSearcher').searchbox('getValue');
	         //   alert(startDate+"===="+endDate+"===="+reportStatus+"===="+reportCatalog+"===="+searchString);
			
			
			 $('#reportStatusTable').datagrid({
				 	height:reportStatusTableHeight,
				 	width:reportStatusTableWidth,
					//singleSelect:true,
					idField:'chkReportCode',
					//url:sybp()+"/qAChkReportAction_getReportList.action?QAMainPage="+$('#QAMainPage').val()+"&studyNoParam="+$('#studyNoParamForReport').val()+"&reportStartDate="+reportStartDate+"&reportEndDate="+reportEndDate,
		            columns:[[
							{
								title:'报告编号',
								field:'chkReportCode',
								width:110,
							},
							{
								title:'专题编号',
								field:'studyNo',
								width:90,
								hidden:isHidden,
								
							},
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
								title:'状态',
								field:'rptState',
								width:53,
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
										  //row.needDelay//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
										//	row.delayState//0：未完成；1：已完成
										//  row.needReply //0 No;  1: yes
										//	row.replyState//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收
										//	row.needReChk //0：不需要；1：需要
										//	row.reChkState//0：问题未解决；1：问题已解决
										// role "FM" "SD" "QALead" "QA" "PathSDLead" "PathSD"
										var str="回复中";
										if(row.replyState==''||row.replyState==0)
											str="SD待回复";
										else if(row.replyState==1)
											str="FM待审批回复";
										else if(row.replyState==-1||row.replyState==2)
											str="QA待接收回复";
										if(str!="回复中")
											str+="\n";
										else
											str="";
										if(row.needDelay==1) 
										{
											str+="FM待审批延迟整改"
										}else if(row.needDelay==-1||row.needDelay==2)
										{
											str+="QA待接收延迟整改";
										}
										if(str=='')
											str="回复中";
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
								title:'qamApprovalOpinion',
								field:'qamApprovalOpinion',
								width:50,
								hidden:true,
								
							},{
								title:'SD',
								field:'sd',
								width:50,
								hidden:isHidden,
								
							},
							{
								title:'检查员',
								field:'inspector',
								width:50,
								hidden:isHidden,
							},
							{
								title:'是否可以确认整个专题完成',
								field:'canEndStudy',
								hidden:true,
							},
							{
								title:'是否可以确认报告完成',
								field:'canEndReport',
								hidden:true,
							},
							
							{
								title:'是否有权限',
								field:'haveRight',
								hidden:true,
							},
							{
								title:'QA负责人',
								field:'qam',
								width:50,
								
							},
							{
								title:'延迟整改状态',
								field:'needDelay',
								hidden:true,
								formatter: function(value,row,index){
									//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
									if(value==0)
									{
										return "未申请";
									}
									if(value==1)
									{
										return "提交FM";
									}
									if(value==-1)
									{
										return "FM退回";
									}
									if(value==2)
									{
										return "提交QA";
									}
									if(value==3)
									{
										return "QA确认接收";
									}
								}
							},
							{
								title:'延迟整改进度',
								field:'delayState',
								hidden:true,
								formatter: function(value,row,index){
									//0：未
									if(value==0)
									{
										return "未完成";
									}
									if(value==1)
									{
										return "已完成";
									}
									
								}
							},
							{
								title:'回复状态',
								field:'replyState',
								hidden:true,
								formatter: function(value,row,index){
									//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
									if(value==0)
									{
										return "未申请";
									}
									if(value==1)
									{
										return "提交FM";
									}
									if(value==-1)
									{
										return "FM有意见";
									}
									if(value==2)
									{
										return "提交QA";
									}
									if(value==3)
									{
										return "QA确认接收";
									}
								}
							},
							{
								title:'需再检查否',
								field:'needReChk',
								hidden:true,
							},{
								title:'再检查状态',
								field:'reChkState',
								hidden:true,
								formatter: function(value,row,index){
									//0：问题未解决；1：问题已解决
									if(value==0)
										return "问题未解决";
									if(value==1)
										return "问题已解决";
								
								}
							},{
								title:'FM对于回复的意见',
								field:'replyFmreveiwRemark',
								hidden:true,
							},{
								title:'FM对于整改的意见',
								field:'delayFmreveiwRemark',
								hidden:true,
							},
							/*
							{
								title:'操作',
								field:'operate',
								hidden:true,
								width:50,
								formatter: function(value,row,index){
									if(row.canEndReport==true)
									{
										return "<a onclick='confirmFinishReport("+row.chkReportCode+");' href='#' >确认完成</a>";
									}else{
										return "确认完成";
									}
										
								}
							}*/
							
						]],
						onSelect:function(rowIndex,rowData)
						{
							var reports = new Array();
							var rows = $('#reportStatusTable').datagrid('getSelections');
							for(var i=0;i<rows.length;i++)
							{
								reports.push(rows[i].chkReportCode);
							}
							
				 			$('#SOPList').datagrid({
				 				//url:sybp()+'/qAChkReportAction_getSOPByReport.action?chkReportCode='+rowData.chkReportCode,
						 		url:sybp()+'/qAChkReportAction_getSOPByReport.action?reports='+reports,
						 	});
				 			//$('#SOPList').datagrid('reload');
				 			$('#qaChkReportDetails').datagrid({
				 				//url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+rowData.chkReportCode,
								url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?reports='+reports,
								
					 		});
				 			//$('#qaChkReportDetails').datagrid('reload');
				 			
				 			var replyStateLabel="";
				 			var delayStateLabel="";
							for(var i=0;i<rows.length;i++)
							{
								var replyStateString = '';
								if(rows[i].replyState==0)
								{
					 				replyStateString+= "未申请";
								}
								if(rows[i].replyState==1)
								{
									replyStateString+= "提交FM";
								}
								if(rows[i].replyState==-1)
								{
									replyStateString+= "FM有意见";
								}
								if(rows[i].replyState==2)
								{
									replyStateString+= "提交QA";
								}
								if(rows[i].replyState==3)
								{
									replyStateString+= "QA确认接收";
								}
								if(replyStateString!='')
								{
									replyStateLabel+=rows[i].chkReportCode+'回复状态:'+replyStateString;
									replyStateLabel+="&nbsp;&nbsp;&nbsp;";
								}
								
								var delayStateString='';
					 		
					 			if(rows[i].needDelay==0)
								{
					 				delayStateString+= "未申请";
								}
								if(rows[i].needDelay==1)
								{
									delayStateString+= "提交FM";
								}
								if(rows[i].needDelay==-1)
								{
									delayStateString+= "FM有意见";
								}
								if(rows[i].needDelay==2)
								{
									delayStateString+= "提交QA";
								}
								if(rows[i].needDelay==3)
								{
									delayStateString+= "QA确认接收";
								}
								
								if(delayStateString!='')
								{
									delayStateLabel+=rows[i].chkReportCode+"延迟状态:"+delayStateString;
									delayStateLabel+="&nbsp;&nbsp;&nbsp;";
								}
							}
							if(replyStateLabel!='')
					 			$('#replyStatusLabel').html(replyStateLabel);
							else
								$('#replyStatusLabel').html('');
								
							if(delayStateLabel!='')
					 			$('#delayStatusLabel').html(delayStateLabel);
							else
								$('#delayStatusLabel').html('');
				 			initReportButton(rows);
						},
						onUnselect:function(rowIndex,rowData)
						{
							var reports = new Array();
							var rows = $('#reportStatusTable').datagrid('getSelections');
							for(var i=0;i<rows.length;i++)
							{
								reports.push(rows[i].chkReportCode);
							}
							
				 			$('#SOPList').datagrid({
				 				//url:sybp()+'/qAChkReportAction_getSOPByReport.action?chkReportCode='+rowData.chkReportCode,
						 		url:sybp()+'/qAChkReportAction_getSOPByReport.action?reports='+reports,
						 	});
				 			//$('#SOPList').datagrid('reload');
				 			$('#qaChkReportDetails').datagrid({
				 				//url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?chkReportCode='+rowData.chkReportCode,
								url:sybp()+'/qAChkReportAction_getReportRecordByReport.action?reports='+reports,
								
					 		});
				 			//$('#qaChkReportDetails').datagrid('reload');
				 			
							var replyStateLabel="";
				 			var delayStateLabel="";
							for(var i=0;i<rows.length;i++)
							{
								var  replyStateString='';
								if(rows[i].replyState==0)
								{
					 				replyStateString+= "未申请";
								}
								if(rows[i].replyState==1)
								{
									replyStateString+= "提交FM";
								}
								if(rows[i].replyState==-1)
								{
									replyStateString+= "FM有意见";
								}
								if(rows[i].replyState==2)
								{
									replyStateString+= "提交QA";
								}
								if(rows[i].replyState==3)
								{
									replyStateString+= "QA确认接收";
								}
								if(replyStateString!='')
								{
									replyStateLabel+=rows[i].chkReportCode+'回复状态:'+replyStateString;
									replyStateLabel+="&nbsp;&nbsp;&nbsp;";
								}
								
								var delayStateString='';
					 			if(rows[i].needDelay==0)
								{
					 				delayStateString+= "未申请";
								}
								if(rows[i].needDelay==1)
								{
									delayStateString+= "提交FM";
								}
								if(rows[i].needDelay==-1)
								{
									delayStateString+= "FM有意见";
								}
								if(rows[i].needDelay==2)
								{
									delayStateString+= "提交QA";
								}
								if(rows[i].needDelay==3)
								{
									delayStateString+= "QA确认接收";
								}
								if(delayStateString!='')
								{
									delayStateLabel+=rows[i].chkReportCode+"延迟状态:"+delayStateString;
									delayStateLabel+="&nbsp;&nbsp;&nbsp;";
								}
							}
							if(replyStateLabel!='')
					 			$('#replyStatusLabel').html(replyStateLabel);
							else
								$('#replyStatusLabel').html('');
								
							if(delayStateLabel!='')
					 			$('#delayStatusLabel').html(delayStateLabel);
							else
								$('#delayStatusLabel').html('');
					 		initReportButton(rows);
						},
						onLoadSuccess:function(data)
						{
							if($('#selectChkReportCodeForList').val()!=null&&$('#selectChkReportCodeForList').val()!=''
								&&$('#isSelectDGForReport').val()=='1')
							{
								$(this).datagrid('selectRecord',$('#selectChkReportCodeForList').val());
								$('#isSelectDGForReport').val('0');
								$('#selectChkReportCodeForList').val('');
							}
							if($('#chkReportCodeForReport').val()!=null&&$('#chkReportCodeForReport').val()!=''
								&&$('#isSelectDGForReport').val()=='2')
							{
								$(this).datagrid('selectRecord',$('#chkReportCodeForReport').val());
								$('#isSelectDGForReport').val('0');
								$('#chkReportCodeForReport').val('');
							}
						}
				
	        		
	            });
			
	            $('#SOPList').datagrid({
		            height:SOPListHeight,
	            	width:SOPListWidth,
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
								width:195,
							},
					]],
		        });
	            $('#qaChkReportDetails').datagrid({
					singleSelect:true,
					width:qaChkReportDetailsWidth,
					height:qaChkReportDetailsHeight,
					nowrap:false,
					columns:[[
							{
								title:'Id',
								field:'chkReportRecordId',
								hidden:true,
							},
							{
								title:'报告编号',
								field:'chkReportCode',
								width:120,
							},
							{
								title:'检查项目',
								field:'chkItemName',
								width:120,
							},
							{
								title:'检查内容',
								field:'chkContent',
								width:230,
							},
							{
								title:'检查发现',
								field:'chkResultDesc',
								width:180,
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
							},
							{
								title:'建议',
								field:'advice',
								width:150,
							},
							{
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
								title:'回复内容',
								field:'replyContent',
								width:120,
								hidden:isHidden,
							},
							{
								title:'再检查结果',
								field:'reChkResult',
								width:80,
								hidden:isHidden,
								formatter: function(value,row,index){
									if(row.chkResult=='√'){
										return "NA";
									}else{
										if (value==0||value==null){
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
								hidden:isHidden,
								formatter: function(value,row,index){
									if(row.chkResult=='√'){
										return "NA";
									}else{
										if (value==null||value==0){
											return "未申请";
										} else if (value==1){
											return "已申请";
										}
									}
								}
							
							},{
								title:'延迟原因',
								field:'delayRsn',
								width:150,
								hidden:isHidden,
							},{
								title:'预计落实',
								field:'delayPlanFinishDate',
								width:100,
								hidden:isHidden,
							},{
								title:'实际落实',
								field:'delayFinishTime',
								width:100,
								hidden:isHidden,
							},
							{
								title:'检查者',
								field:'inspector',
								width:90,
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
				// $('#oneQAChkRecord').height(tableHeight*0.2);
	          
				if($('#studyNoParamForReport').val()!=null&&$('#studyNoParamForReport').val()!='')
	            {       
		           		
		            
		            
	            	
	            }
	            //编辑检查报告页面的设置--------------------------------------------------------------------
	            $('#reportItems').treegrid({
					singleSelect:true,
	            	idField:'chkIndexId',
	            	treeField:'chkItemName',
	            	nowrap:false,
					columns:[[
								{
									title:'Id',
									field:'chkRecordId',
									hidden:true,
									/*
									formatter:function(value,row,index)
									{
										//alert( "beginEdit:"+row.chkRecordId);
									 	$('#reportItems').treegrid('beginEdit', index);  
									}*/
								},
								{
									title:'chkType',
									field:'chkType',
									hidden:true,
									
								},
								{
									title:'序号',//只是显示
									field:'sn',
									width:45,
									align:'right',
									hidden:true,
									
								},
								{
									title:'检查项目',
									field:'chkItemName',
									width:150,
								},
								{
									title:'检查内容',
									field:'chkContent',
									width:230,
								},
								{
									title:'检查结果',
									field:'chkResult',
									width:120,
								},
								{
									title:'建议',
									field:'advice',
									width:170,
								},
						]],
						rowStyler: function(row,index){
						    if (row.chkResult!=null&&row.chkResult!='√'){
						        return 'background-color:#6293BB;color:#fff;font-weight:bold;';
						    }
						},
						onLoadSuccess:function(data){
							var rows =  $('#reportItems').treegrid('getData');
							if(rows!=null&&rows.length>0)
							{
								$('#saveButtonForReport').linkbutton('enable');
							}else{
								$('#saveButtonForReport').linkbutton('disable');
							}
						},
						
	            		onClickRow:function(row){//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件   
						    $('#reportItems').treegrid('beginEdit', row.chkIndexId);  
						} ,
						onSelect:function(rowData){
							if(rowData.children==null)
							{
								if(rowData.chkResult!=null&&rowData.chkResult!='√')
								{
									$('#updateChkContentLabel').val(rowData.chkContent+"   不符合");
									$('#updateReportRcordAdvice').val(rowData.advice);
									$('#updateOneReportRecordButton').linkbutton('enable');
								}else{
									var addOrEdit = $('#addOrEditForReport').val();
									if(addOrEdit==2){//编辑
										$.messager.alert('提示框','该条记录是正常的，不需要建议！');
										$('#updateOneReportRecordButton').linkbutton('disable');
									}
								}
							}
						}
	            	
			    });
	            $('#startChkDate').datebox({
	    			onSelect: function(date){
	    				updatePrograms();
	            		searchReportRecord();
	    			}
	    		});
	    		$('#endChkDate').datebox({
	    			onSelect: function(date){
	    				updatePrograms();
	    				searchReportRecord();
	    			}
    			
	    		});
	    		$('#programs').combobox({
	    			 valueField:'chkIndexId',
	    			 textField:'timeAndItem',
	    			 multiple:true,
	    			 onChange: function(newValue, oldValue){
	    				searchReportRecord();
	    			 	
	    			 },
	    			 onLoadSuccess:function(){
	    			 	var data = $(this).combobox('getData');
	    			 	var valueArr = new Array();
	    			 	for(var i=0;i<data.length;i++){
	    			 		valueArr.push(data[i].chkIndexId);
	    			 	}
	    			 	$(this).combobox('setValues',valueArr);
	    			 }
		    	});
		    	$('#reChkDialog').dialog({
		    		left: (parent.parent.$('#researchMain').width()-$('#reChkDialog').width())/2,
					onClose:function(){
						$('#reportStatusTable').datagrid('reload');
					}
			    });
		    	$('#reChkItems').datagrid({
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
									title:'报告编号',
									field:'chkReportCode',
									width:110,
								},
								{
									title:'检查项目',
									field:'chkItemName',
									width:100,
								},
								{
									title:'检查内容',
									field:'chkContent',
									width:230,
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
									width:40,
									hidden:true,
									formatter: function(value,row,index){
										if (value==-1){
											return "Y";
										} else {
											return "N";
										}
								
									}
								},
								/*
								{
									title:'建议',
									field:'advice',
									width:100,
								},
								*/
								{
									title:'回复内容',
									field:'replyContent',
									width:80,
									hidden:true,
								},
								{
									title:'需再检查否',
									field:'reChkFlagTemp',
									width:40,
									//hidden:true,
									formatter: function(value,row,index){
										if (value==1){
											return "Y";
										} else if (value==0){
											return "N";
										}else {
											return "";
										}
								
									}
								},
								{
									title:'需再检查否',
									field:'reChkFlag',
									hidden:true,
								},
								{
									title:'再检查结果',
									field:'reChkResult',
									hidden:true,
								},
								{
									title:'实际落实',
									field:'delayFinishTime',
									hidden:true,
								},
								{
									title:'再检查结果',
									field:'reChkResultTemp',
									width:70,
									formatter: function(value,row,index){
										if(row.chkResult=='√'){
											return "NA";
										}else{
											if (value==null||value==0){
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
									width:60,
									formatter: function(value,row,index){
										if(row.chkResult=='√'){
											return "NA";
										}else{
											if (value==null||value==0){
												return "未申请";
											} else if (value==1){
												return "已申请";
											}
										}
									}
								
								},{
									title:'延迟原因',
									field:'delayRsn',
									width:120,
									hidden:true,
								},{
									title:'预计落实',
									field:'delayPlanFinishDate',
									width:80,
								},{
									title:'实际落实',
									field:'delayFinishTimeTemp',
									width:80,
								},
								{
									title:'报告的回复状态',
									field:'reportReplyState',
									hidden:true,
								},
								{
									title:'报告的整改状态',
									field:'reportNeedDelay',
									hidden:true,
								},
								{
									title:'操作',
									field:'remark',
									width:50,
									formatter: function(value,row,index){
									//clickReChk(chkResultFlag,reChkResult,needDelay,delayFinishTime)
									 	return "<a onclick='clickReChk("+row.reportReplyState+","+row.reportNeedDelay+","+row.chkResultFlag+","+row.reChkResult+","+row.reChkFlag+","+row.needDelay+",\""+row.delayFinishTime+"\");' href='#' >再检查</a>";
									}
								},
					]],
					onSelect:function(rowIndex,rowData)
					{
				 		
					},
	            	rowStyler: function(index,row){
		            	/*
		            	if (row.chkResult=="×"){
							return 'background-color:#6293BB;color:#fff;';
						}
						*/
					}
		    		
			    });
			    //查看页面-----------------------------------------------------------------
			     $('#replyDelayList').datagrid({
	            	height:320,
					width:830,
					singleSelect:true,
					nowrap:false,	
					columns:[[
							{
								title:'Id',
								field:'chkReportRecordId',
								hidden:true,
							},
							{
								title:'报告编号',
								field:'chkReportCode',
								width:80,
							},
							{
								title:'检查项目',
								field:'chkItemName',
								width:80,
							},
							{
								title:'检查内容',
								field:'chkContent',
								width:230,
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
										return "NA";
									}else{
										if (value==null||value==0){
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
			    
				//主界面下的查询条件----------------------------------------------------------------
				
				
	            $('#reportStartDate').datebox({
	        		onSelect: function(date){
	            		searchReportList();
	        		}
	        	});
	            $('#reportEndDate').datebox({
	        		onSelect: function(date){
	            		searchReportList();
	        		}
	        	});
	            $('#reportStatus').combobox({
	            	 onSelect: function(rec){
	            		searchReportList();
	            	 }
		        });
				$('#reportSearcher').searchbox({ 
			       	     height:19,
			       	     width:200,
			       		 searcher:function(value,name){ 
							searchReportList();
			       		},
			       		prompt:'模糊查询,请输入报告编号',
			       	});
				$('#reportCatalog').combobox({
		            	 onSelect: function(rec){
						 	searchReportList();
		            	 }
			        });

				
			    initReportButton(null);
	            initMainPageButton();

	            $('#addQAChkReportDialog').dialog({
	        		left: (parent.parent.$('#researchMain').width()-$('#addQAChkReportDialog').width())/2,
					
	            });

				$('#qAStatementTempleList').datagrid({

					height: 300,
					width:420,
					columns :[[
					{	
						title : '模板id',
						field : 'templeId',
						hidden:true,
					},{
						title : '模板名',
						field : 'templeName',
						width : 100
					},{
						title:'模板内容',
						field:'templeContent',
						width:300
					}]],
				});
				if($('#newReportForReportPage').val()!=null&&$('#newReportForReportPage').val()!='')
				{
					//alert("linkbutton able===="+$('#newQAChkReport').linkbutton('options').disabled );
					//if($('#newQAChkReport').linkbutton('options').disabled )
						addChkReport(1);
						$('#newReportForReportPage').val('');
				}

	            //把状态，类别，和条件写入相对应的组件中
	            if($('#reportStatusForReport').val()!=null&&$('#reportStatusForReport').val()!='')
	 	   		{
	 	   				$('#reportStatus').combobox('setValue',$('#reportStatusForReport').val());
	 	   				$('#reportStatusForReport').val('');
	 	   				
	 	   		}
	            if($('#reportCatalogForReport').val()!=null&&$('#reportCatalogForReport').val()!='')
	 	   		{
	 	   				$('#reportCatalog').combobox('setValue',$('#reportCatalogForReport').val());
	 	   				$('#reportCatalogForReport').val('');
	 	   				
	 	   		}
				$('#reportSearcher').searchbox('setValue',$('#reportSearcherForReport').val());
				$('#reportSearcherForReport').val('');
				searchReportList();
				
        });//匿名函数结束
        
        function searchReportList()
        {
            var reportStartDate =  $('#reportStartDate').datebox('getValue');
            var reportEndDate =  $('#reportEndDate').datebox('getValue');
            if(reportStartDate==null||reportStartDate=='')
            {
            	reportStartDate = $('#reportStartDateValue').val();
            }
            if(reportEndDate==null||reportEndDate=='')
            {
            	reportEndDate = $('#reportEndDateValue').val();
            }
            var reportStatus = $('#reportStatus').combobox('getValue');
            var reportCatalog = $('#reportCatalog').combobox('getValue');
            var reportSearcher = $('#reportSearcher').searchbox('getValue');
           
            $('#reportStatusTable').datagrid({
				url:sybp()+"/qAChkReportAction_getReportList.action?QAMainPage="+$('#QAMainPage').val()+"&studyNoParam="+$('#studyNoParamForReport').val()+"&reportStartDate="+reportStartDate+"&reportEndDate="+reportEndDate+"&reportStatus="+reportStatus+"&reportCatalog="+reportCatalog+"&reportSearcher="+reportSearcher,
            });
           // $('#reportStatusTable').datagrid('reload');
            if($('#selectChkReportCodeForList').val()!=null&&$('#selectChkReportCodeForList').val()!='')
			{
            	 $('#isSelectDGForReport').val('1');
			}
			if($('#chkReportCodeForReport').val()!=null&&$('#chkReportCodeForReport').val()!='')
			{
				 $('#isSelectDGForReport').val('2');
			}
           
            
        }
        
      function initReportButton(rows)
      {
    	  $('#newQAChkReport').linkbutton('disable');
          $('#editQAChkReport').linkbutton('disable');
          $('#delQAChkReport').linkbutton('disable');
          $('#commitReportToQAM').linkbutton('disable');
          $('#qamApprovalReport').linkbutton('disable');
          $('#reChkBadRecord').linkbutton('disable');
          $('#viewReport').linkbutton('disable');
          $('#confirmReportFinish').linkbutton('disable');
          $('#ireportReportInfo').linkbutton('disable');
          $('#printReport').linkbutton('disable');
          var role = $('#roleForChkReport').val();
          var haveRightForEndStudy = $('#haveRightForEndStudy').val();
	      var haveRightForiReportStudy = $('#haveRightForiReportStudy').val();

          var studyFinishForReport=$('#studyFinishForReport').val();
         // alert("studyFinishForReport="+studyFinishForReport);
          
          if(studyFinishForReport!='true')
          {
          	 var have = $('#haveRightForReport').val();
          	 if(have=='true')
          	 {
          	  	$('#newQAChkReport').linkbutton('enable');
          	 }
          	if(rows!=null&&rows.length>0)
			{
				if(role=='QALead')
				{
					$('#qamApprovalReport').linkbutton('enable');
				}
				if(rows.length==1)
				{
					$('#editQAChkReport').linkbutton('enable');
				}
				$('#delQAChkReport').linkbutton('enable');
				$('#commitReportToQAM').linkbutton('enable');
				
				$('#viewReport').linkbutton('enable');
				
				$('#reChkBadRecord').linkbutton('enable');
				
				for(var i=0;i<rows.length;i++)
				{
					if(rows[i].rptState!=1)
					{
						$('#qamApprovalReport').linkbutton('disable');
					}
					if(rows[i].rptState!=null&&rows[i].rptState!=0){
		               	$('#delQAChkReport').linkbutton('disable');
		            }
					if(rows[i].rptState!=null&&rows[i].rptState!=0&&rows[i].rptState!=-2)
	               	{
		               	$('#editQAChkReport').linkbutton('disable');
		            	
	            		$('#commitReportToQAM').linkbutton('disable');
	               	}
	               
				 	if(rows[i].replyState!=2&&rows[i].replyState!=-1&&rows[i].needDelay!=2&&rows[i].needDelay!=-1)//SD回复，2是FM同意，-1是FM退回
				 	{
			             $('#viewReport').linkbutton('disable');
				 	}
				 	if(rows[i].rptState==9||(rows[i].needReChk!=1&&(rows[i].needDelay!=3||rows[i].delayState!=0)))
				 	{
		             	$('#reChkBadRecord').linkbutton('disable');
				 	}
					
				}
			}
          
	       
	         //alert(have+"==="+row.haveRight);
	         //row.haveRight ==1的时候是false，其他的情况下，是qa的时候为true
	         
	        
	         if(have=='true'&&haveRightForEndStudy=='true')//对于整个专题来说的
			 {
	          	 $('#confirmReportFinish').linkbutton('enable');
			 }

          }
          
		//if(row!=null&&row!=''&&row.rptState==9)
		//{
		//}
		if(rows!=null&&rows.length>0&&rows.length==1)
		{
			$('#printReport').linkbutton('enable');
			for(var i=0;i<rows.length;i++)
			{
				if(rows[i].rptState<2)//rptState>=2就可以打印
				{
					$('#printReport').linkbutton('disable');
				}
			}		
		}
		var isReportQA = $('#isReportQAForReport').val();
		//if((role=="QALead"&&studyFinishForReport=='true')||(have=='true'&&haveRightForiReportStudy=='true'))
		if(isReportQA=='true'&&studyFinishForReport=='true')
		{
	        $('#ireportReportInfo').linkbutton('enable');			 
		}
      }
	 function initMainPageButton()//主界面和子界面的区别
	 {
		 var QAMainPage = $('#QAMainPage').val();
		 if(QAMainPage!=null&&QAMainPage=='true')//主界面
		 {
			$('#newQAChkReport').css('display','none');
			$('#searchConditionDiv').css('display','');
			
			$('#confirmReportFinish').css('display','');
			$('#ireportReportInfo').css('display','none');
			
    		parent.$('#topInfoLabel').html('');
    		
		 }else{
			 //$('#qamApprovalReport').css('display','none');
			 $('#searchConditionDiv').css('display','none');
			 $('#ireportReportInfo').css('display','');
		 }
		 
	 }
</script>
	  
</head>

<body >
	<div id="outReportPage" style="width:100%;height:100%;display:none;" border="false">
	
		<s:hidden id="studyNoParamForReport" name="studyNoParam"></s:hidden>
		<s:hidden id="haveRightForReport" name="haveRight"></s:hidden>
		<s:hidden id="haveRightForEndStudy" name="haveRightForEndStudy"></s:hidden>
		<s:hidden id="haveRightForiReportStudy" name="haveRightForiReportStudy"></s:hidden>
		<s:hidden id="QAMainPage" name="QAMainPage"></s:hidden>
		<s:hidden id="reportStartDateValue" name="reportStartDate2"></s:hidden>
		<s:hidden id="reportEndDateValue" name="reportEndDate2"></s:hidden>
		<s:hidden id="isReportQAForReport" name="isReportQAInReport"></s:hidden>
		
		<s:hidden id="studyFinishForReport" name="studyFinishForReport"></s:hidden>
		<s:hidden id="selectChkReportCodeForList" name="selectChkReportCode2"></s:hidden>
		<s:hidden id="newReportForReportPage" name="newReportForReportPage"></s:hidden>
	<!-- 
		<s:hidden id="reportStartDateForReport" name="reportStartDate"></s:hidden>
		<s:hidden id="reportEndDateForReport" name="reportEndDate"></s:hidden>
	 -->
		<s:hidden id="reportStatusForReport" name="reportStatus"></s:hidden>
		<s:hidden id="reportCatalogForReport" name="reportCatalog"></s:hidden>
		<s:hidden id="reportSearcherForReport" name="reportSearcher"></s:hidden>
		<s:hidden id="chkReportCodeForReport" name="chkReportCode"></s:hidden>
	
		<input id="roleForChkReport" type="hidden" value=${role }></input>
		
		<s:hidden id="chkIndexId"></s:hidden>
		<s:hidden id="oneChkReportCodeForSign"></s:hidden>
		<s:hidden id="isSelectDGForReport"></s:hidden>
		
		<div id="searchConditionDiv" style="height:30px;border: 1px solid #c8c8c8">
			  检查报告日期<div id="reportStartDate" class="easyui-datebox" style="width: 100px;"></div>~<div id="reportEndDate" class="easyui-datebox" style="width: 100px;"></div>
	                            状态    <select id="reportStatus" class="easyui-combobox" data-options="editable:false" style="width:100px;">
			<!-- 
			 0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
			 -->
				<option value="0">全部</option>
				<option value="9">完成</option>
				<option value="1">提交</option>
				<option value="3">回复中</option><!-- 需要修改 -->
				<option value="31">QA待接收</option>
				<option value="4">进入再检查</option>
				<option value="5">进入延迟整改</option>
			</select>
		
			类别<select id="reportCatalog" style="width:150px;">
			<!-- 1:研究；2：过程；3：设施 -->
					<option value="0">全部</option>
					<option value="4">方案检查</option>
					<option value="5">报告检查</option>
					<option value="1">基于研究的检查</option>
					<!-- 
					<option value="2">基于过程的检查</option>
					<option value="3">基于设施的检查</option>
					 -->
			</select>
			&nbsp;
			<span style="position:absolute; top:5px;">
				<input id="reportSearcher" style="margin-top:5px" ></input>
			</span>
		</div>
		
		<div id="reportMainContainer" style="width:100%;position: relative;">
		   <nobr>
		    <a id="newQAChkReport" class="easyui-linkbutton" disabled onclick="addChkReport(1);" data-options="iconCls:'icon-add',plain:true">新建报告</a>
			<a id="editQAChkReport" class="easyui-linkbutton" disabled onclick="addChkReport(2);" data-options="iconCls:'icon-edit',plain:true">编辑</a>
			<a id="delQAChkReport" class="easyui-linkbutton" disabled onclick="delChkReport();" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a id="commitReportToQAM" class="easyui-linkbutton" disabled onclick="commit();" data-options="iconCls:'icon-confirm',plain:true">提交QAM审核</a>
			<a id="qamApprovalReport" class="easyui-linkbutton" disabled onclick="qamApprovalReport();" data-options="iconCls:'icon-tableedit',plain:true">QAM审批</a>
			<a id="reChkBadRecord" class="easyui-linkbutton" disabled onclick="reChk();" data-options="iconCls:'icon-tablerefresh',plain:true">再检查</a>
			<a id="viewReport" class="easyui-linkbutton" disabled onclick="viewReport();" data-options="iconCls:'icon-tableedit',plain:true">接收回复或延迟</a>
			<a id="printReport" class="easyui-linkbutton"  onclick="printReport();" data-options="iconCls:'icon-print',plain:true">打印</a>
			<a id="confirmReportFinish" class="easyui-linkbutton" disabled onclick="confirmReportFinish();" data-options="iconCls:'icon-ok',plain:true">确认专题完成</a>
			<a id="ireportReportInfo" class="easyui-linkbutton" disabled onclick="qAQualityIReport();" data-options="iconCls:'icon-tableedit',plain:true">出具QA声明</a>
			<div>
				<s:label id="replyStatusLabel" name="replyState" style="width:100px;"/>
				<s:label id="delayStatusLabel" name="delayState" style="width:100px;"/>
			</div>
		 </nobr>
			<!--  -->
	    	 <div style="display:block; position:absolute;top:50px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:auto;" >
	 	 	 
					<div style="float:left;position:relative;left: 10px;">
					
						<div >
							<div id="reportStatusTable" ></div>
						</div>
						
						<div style="margin-top: 10px;">
							<div id="SOPList" class="easyui-datagrid"></div>
						</div>
					 
				   	</div><!-- 左边结束 -->
				   	<div style="float:left; margin-left: 20px;" >
				   		<div id="qaChkReportDetails" class="easyui-datagrid"></div>
				   	</div>
			</div>
		</div>
			
		
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/reChk.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/viewReport.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/chooseTemple.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/reChkResult.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/qamApprovalChkReport.jspf"%>
   <%@ include file="/WEB-INF/jsp/QAChkReportAction/addChkReport.jspf"%>
   <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</body>
</html>




