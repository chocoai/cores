<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.lanen.model.qa.QAFileReg"%>
<%@page import="java.util.ArrayList"%>
<%! Map<String,List<QAFileReg>> sopMap = new HashMap<String,List<QAFileReg>>(); %> 
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查记录</title>
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
	
	
	function delRecord(chkRecordId)
	{
		var qaIndexId = $('#chkIndexId').val();
		
		$.ajax({
			url:sybp()+'/qAChkRecordAction_del.action?chkRecordId='+chkRecordId+'&qaIndexId='+qaIndexId,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r&&r.success){
					$('#chkIndexId').val(r.qaIndexId);
				}
			}
		});
		 var delRow = $('#qaChkRecordResult').datagrid('getSelected');
		 var index =  $('#qaChkRecordResult').datagrid('getRowIndex',delRow);
		 $('#qaChkRecordResult').datagrid('deleteRow',index);

	}
	function delOneSOP(sopRecordId)
	{
		$.ajax({
			url:sybp()+'/qAChkRecordAction_delOneSop.action?sopRecordId='+sopRecordId,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r&&r.success){
					$('#chkIndexId').val(r.qaIndexId);
				}
			}
		});
		 var delRow = $('#SOPList').datagrid('getSelected');
		 var index =  $('#SOPList').datagrid('getRowIndex',delRow);
		 $('#SOPList').datagrid('deleteRow',index);
	}

	function selectResult(flag)
	{
		if(flag==-1)//不符合
		{
			document.getElementById('oneAdvice').readOnly=false;//编辑建议
			document.getElementById('oneChkResultDesc').readOnly=false;//编辑检查发现

			$('#otherAllOperate').linkbutton('disable');
		}else if(flag==1||flag==0){
			$('#oneAdvice').val('');
			document.getElementById('oneAdvice').readOnly=true;
			$('#oneChkResultDesc').val('');
			document.getElementById('oneChkResultDesc').readOnly=true;
			

			$('#otherAllOperate').linkbutton('enable');
			if(flag==0)//NA
			{
				$('#otherAllOperate').linkbutton({
					text:'其余都不适用',
				});
			}else if(flag==1){//符合
				$('#otherAllOperate').linkbutton({
					text:'其余都符合',
				});
			}
		}

	}
	function saveChkRecord()
	{
		var item = $('#checkItems').combobox('getValue');//plan和item
		var tbl = $('#checkTables').combobox('getValue');//表格
		var tblContent = $('#chkTableContent').datagrid('getSelected');
		var qaIndexId = $('#chkIndexId').val();
		var studyNoParam = $('#studyNoParamForRecord').val();
		if(item!=null&&tbl!=null&&tblContent!=null)
		{
			
			if($("input[name='chkResult']:checked").val()=="×"&&
				($('#oneAdvice').val()==''||$('#oneChkResultDesc').val()==''))
			{
				$.messager.alert("提示","检查结果不符合，请填写检查发现和建议");
			}else
			{
				
				$.ajax({
					url:sybp()+'/qAChkRecordAction_save.action?studyNoParam='+studyNoParam+'&planIdItemId='+item+'&chkTblId='+tbl+"&chkTblContentId="+tblContent.chkTblContentId+"&qaIndexId="+qaIndexId,
					type:'post',
					data:$('#oneQAChkRecord').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&!r.success)
						{
							if(r.noFinishRead){
								$.messager.alert('提示框','在开始检查之前，请阅读方案文件！','info',function(){
									parent.$('#researchCheckTabs').tabs('select','方案/报告检查');
								});
								
							}else{
								$.messager.alert("提示",r.msg);
							}
						}
						if(r&&r.success)
						{
							if($('#chkIndexId').val()==null||$('#chkIndexId').val()=='')
							{
								$('#SOPList').datagrid({
									url:sybp()+'/qAChkRecordAction_getSOPByChkIndex.action?qaIndexId='+r.qaIndexId,
								});
								//$('#SOPList').datagrid('reload');
							}
							
							$('#chkIndexId').val(r.qaIndexId);
							$('#qaChkRecordResult').datagrid('appendRow',{
								chkRecordId:r.chkRecordId,
								sn:r.sn,
								chkTblName:r.chkTblName,
								chkContent:r.chkContent,
								chkResultDesc:r.chkResultDesc,
								chkResultFlag:r.chkResultFlag,
								advice:r.advice,
								chkTime:r.chkTime
								
							});
							//指向下一条记录
							var content = $('#chkTableContent').datagrid('getSelected');
							var index = $('#chkTableContent').datagrid('getRowIndex',content);
							$('#chkTableContent').datagrid('selectRow',index+1);
							$('#oneAdvice').val("");
							$('#oneChkResultDesc').val('');
							
						}
					}
				});
			}
		}else
		{
			$.messager.alert("提示","请填写完整");
		}
		
	}
	function saveQAChkIndex()
	{
		$.ajax({
			url:sybp()+'/qAChkIndexAction_save.action',
			type:'post',
			data:$('#oneQAChkIndex').serialize(),
			dataType:'json',
			success:function(r){
				if(r)
				{
					$('#chkIndexId').val(r.chkIndexId);
					$('#chkIndexStatus').val(r.status);
					//更新法规列表
					$('#SOPList').datagrid({
						url:sybp()+'/qAChkRecordAction_getSOPByChkIndex.action?qaIndexId='+r.chkIndexId,
					});
					//$('#SOPList').datagrid('reload');
					initSignAndWriteReportButton();
				}
			}
		});
		$('#addQAChkIndexDialog').dialog('close');
		
	}
 	//签字确认，是对QAChkIndex表来的，证明检查项结束
 	function signQAChkRecord()
 	{
 		var rows = $('#qaChkRecordResult').datagrid('getRows');
 		var table = $('#checkTables').combobox('getValue');
 		var chkTblContentIds = new Array();
 		for(var i=0;i<rows.length;i++)
 		{
 			chkTblContentIds.push(rows[i].chkTblContentId);
 	 	}
 	 	//alert(rows+"==="+rows.length);
		if(rows!=null&&rows.length>0)
		{
	 		//判断检查表是否全部检查完毕
	 		$.ajax({
				url:sybp()+'/qAChkIndexAction_isFinish.action?chkIndexId='+$('#chkIndexId').val(),
				type:'post',
				dataType:'json',
				success:function(r){
					if(r&&r.success&&r.finish)
					{
						if(r.otherTables&&r.otherTables!='')
						{
							/*$.messager.confirm('确认框', '签字确认是相对于当前检查项的，与该检查项有关的检查表，还有 '+r.otherTables+" 没有使用，如果继续则该检查项将不可编辑！", function(r){
								if (r){
									qm_showQianmingDialog('afterSignQAChkIndex');
								}
							});*/
							$.messager.alert('警告框', '与该检查项有关的检查表: '+r.otherTables+" 没有检查，请检查完毕!");
						}else{
							qm_showQianmingDialog('afterSignQAChkIndex');
						}
					}else if(r&&!r.success)
					{
						$.messager.alert("提示",r.msg);
					}
				}
			});
		}else
		{
			$.messager.alert("提示","请填写记录");
		}
		
		
 	}
	//签字过后的检查索引完成操作
 	function afterSignQAChkIndex()
 	{
 	 	//alert(sybp()+'/qAChkIndexAction_sign.action?chkIndexId='+$('#chkIndexId').val());
 		$.ajax({
			url:sybp()+'/qAChkIndexAction_sign.action?chkIndexId='+$('#chkIndexId').val(),
			type:'post',
			dataType:'json',
			success:function(r){
 				$('#chkIndexStatus').val(2);
 				var qaIndexId = $('#chkIndexId').val();
 				 $('#qaChkRecordResult').datagrid({
		           	 url:sybp()+'/qAChkRecordAction_getRecordsByTable.action?qaIndexId='+qaIndexId,
				 });
 				$('#qaChkRecordResult').datagrid('reload');
 				 $('#SOPList').datagrid('reload');
 				initSignAndWriteReportButton();
			},
		});
 		
 	}
 	function writeQAChkReport()
 	{
 		$.ajax({
			url:sybp()+'/qAChkPlanAction_putChkPlanIdInSession.action?newReport=1',
			type:'post',
			dataType:'json',
			success:function(r){
		 		parent.$('#researchCheckTabs').tabs('select','检查报告');
				
			}
		});
 		
 	}
	 
</script>

<script type="text/javascript">

    	$(function(){

    		
			var planStateForRecord = $('#planStateForRecord').val();
			if(planStateForRecord=='false'){
				/*
				$.messager.show({
					title:'',
					msg:'日程相关检查计划没有最终确定，如果需要检查日程检查项，请先设置检查计划！',
					showType:'show',
					timeout:4000,
					style:{
						right:'',
						top:document.documentElement.scrollTop,
						bottom:''
					}
				});*/
			}

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
			
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight =  document.body.clientHeight-40;
    		var tableWidth  = document.body.clientWidth-10;

			var childTableWidth = tableWidth;
    		//设置宽度
   		 	/*if(parent.parent.$('#changeSizeButton')&&parent.parent.$('#changeSizeButton').text()==">")
   			{
   		 		childTableWidth = childTableWidth+290;
   	   	 	}*/
    		
			 $('#qaChkRecordResult').datagrid({
	        		height:tableHeight*0.96+20,
					width:childTableWidth-350,
					singleSelect:true,
					nowrap:false,		
					columns:[[
							{
								title:'Id',
								field:'chkRecordId',
								hidden:true,
							},
							
							{
								title:'序号',
								field:'sn',
								width:50,
								hidden:true,
							},
							{
								title:'chkTblContentId',
								field:'chkTblContentId',
								hidden:true,
							},
							{
								title:'chkResultDesc',
								field:'chkResultDesc',
								hidden:true,
							},
							{
								title:'检查表',
								field:'chkTblName',
								width:130,
							},
							
							{
								title:'检查内容',
								field:'chkContent',
								width:200,
							},
							{
								title:'检查发现',
								field:'chkResultFlag',
								width:100,
								formatter: function(value,row,index){
									if(value==1)
									{
										//return "符合";
										return "√";
									}
									if(value==-1)
									{
										//return "不符合";
										//return "×";
										return row.chkResultDesc;
									}
									if(value==0)
									{
										//return "不适用";
										return "NA";
									}
		         		
								}
							},
							
							{
								title:'需回复否',
								field:'sd',
								width:50,
								formatter: function(value,row,index){
									if(row.chkResultFlag==1||row.chkResultFlag==0)
									{
										//return "符合";
										return "否";
									}
									if(row.chkResultFlag==-1)
									{
										//return "不符合";
										return "是";
									}
									
		         		
								}
							},
							{
								title:'建议',
								field:'advice',
								width:80,
							},
							{
								title:'检查时间',
								field:'chkTime',
								width:70,
							},
							{
								title:'操作',
								field:'inspector',
								width:50,
								halign:'center',
								align:'center',
								formatter: function(value,row,index){
								
									var indexState =  $('#chkIndexStatus').val();//0:：草稿；1：检查中（启动）2：完成
									if(indexState!='2')
									{
										return "<a href='javascript:delRecord("+row.chkRecordId+");'>删除</a>";
									}else{
										return "删除";
									}
			         		
								}
							},
						]],
						rowStyler: function(index,row){
						    if (row.chkResultFlag==-1){
						        return 'background-color:#6293BB;color:#fff;font-weight:bold;';
						    }
						},
						/*
						onLoadSuccess:function(data){
							initSignAndWriteReportButton();
						}*/
	        		
	            });
			 $('#chkTableContent').datagrid({
	            	height:tableHeight*0.45,
					width:320,
					singleSelect:true,
					nowrap:false,		
					columns:[[
							{
								title:'Id',
								field:'chkTblContentId',
								hidden:true,
							},
							{
								title:'序号',
								field:'sn',
								width:50,
							},
							{
								title:'检查内容',
								field:'chkContent',
								width:247,
							},
					]],
					onSelect:function(rowIndex,rowData)
					{
						if(rowData)
						{
				 			$('#checkContent').html(rowData.chkContent);
				 		}
					}
		        });
	            $('#SOPList').datagrid({
	            	height:tableHeight*0.23,
					width:320,
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
								width:90,
							},
							{
								title:'检查依据的法规',
								field:'sopName',
								width:180,
							},
							{
								title:'操作',
								field:'remark',
								width:50,
								hidden:true,
								halign:'center',
								align:'center',
								formatter: function(value,row,index){
								
									var indexState =  $('#chkIndexStatus').val();
									if(indexState!='2')
									{
										return "<a href='javascript:delOneSOP("+row.sopRecordId+");'>删除</a>";
									}else{
										return "删除";
									}
			         		
								}
							},
					]],
		        });
				 $('#oneQAChkRecord').width(308);
				 $('#oneQAChkRecord').height(tableHeight*0.29);
	          
				 if($('#studyNoParamForRecord').val()!=null&&$('#studyNoParamForRecord').val()!='')
	            {       
					 $('#isPlanDateSelectFlag').val('0');
		            //初始化几个下拉框
		            $('#planDate').combobox({
		            	url:sybp()+'/qAChkRecordAction_getPlanDateList.action?studyNoParam='+$('#studyNoParamForRecord').val(),
		            	valueField:'planChkTime',
		                textField:'planChkTime',
		                onSelect: function(rec){
		            		var scheduleChanged = $('#scheduleChangedForRecord').val();
		              		if(scheduleChanged=='true')
		              		{
		              			$.messager.alert('提示',"日程有变更，请根据日程变更生成新的检查计划！","",function(){
		              				parent.$('#researchCheckTabs').tabs('select','检查计划');
		              				//alert("sadsd");
		              			});
		              		}else{
			                	//选择日期更新检查项目
			               	 	$('#checkItems').combobox({
			               	 		url:sybp()+'/qAChkRecordAction_getItemListByDate.action?studyNoParam='+$('#studyNoParamForRecord').val()+'&planDate='+$('#planDate').combobox('getValue'),
				            	});
		              		}
		            	},
		            	
		            	onLoadSuccess:function(){
			            	
		            		var data = $(this).combobox('getData');
		 					if(data==null||data.length==0){
								//只有方案或者报告的情况
		            			 $('#checkItems').combobox({
				               	 	url:sybp()+'/qAChkRecordAction_getItemListByDate.action?studyNoParam='+$('#studyNoParamForRecord').val(),
					             });
			            	 }else{
				            	 
			            	    if($('#onePlanChkDateForList').val()!=null&&$('#onePlanChkDateForList').val()!=''){
									$('#isPlanDateSelectFlag').val('1');
									//alert("1111");
									$(this).combobox('select', $('#onePlanChkDateForList').val());
									$('#onePlanChkDateForList').val('');
				            	}else{
				            		 $('#isPlanDateSelectFlag').val('0');
				            		 $(this).combobox('select', data[0].planChkTime);
				            		
					            }
			            	 }
			            }
			        });
		            $('#checkItems').combobox({
		            	valueField:'chkItemId',
		                textField:'chkItemName',
		                //默认是方案或者报告
		              //url:sybp()+'/qAChkRecordAction_getItemListByDate.action?studyNoParam='+$('#studyNoParamForRecord').val(),
	               	 	
		                onSelect: function(record){
							//查看是否已经操作检查索引表，如果没有就由QA检察员新建一个
							/*在增加检查记录的时候再检查是否存在，不存在直接添加*/
		                	$.ajax({
								url:sybp()+'/qAChkRecordAction_isExistChkIndex.action?chkPlanId='+record.chkItemId,
								type:'post',
								dataType:'json',
								success:function(r){
									if(r&&r.success)
									{
										
										if(r.isExist)
										{//已经存在
											$('#chkIndexId').val(r.chkIndexId);
											$('#chkIndexStatus').val(r.status);
											
										}else{
											$('#chkIndexId').val('');
											$('#chkIndexStatus').val(0);
										}
										
										var qaIndexId = $('#chkIndexId').val();
								        $('#qaChkRecordResult').datagrid({
								           	 url:sybp()+'/qAChkRecordAction_getRecordsByTable.action?qaIndexId='+qaIndexId,
										 });
										 
								        $('#SOPList').datagrid({
											url:sybp()+'/qAChkRecordAction_getSOPByChkIndex.action?qaIndexId='+qaIndexId+'&planIdItemId='+record.chkItemId,
										});
										//$('#SOPList').datagrid('reload');
										
								        initSignAndWriteReportButton();
									}
									if(r&&!r.success){
										//更新表头
		                				var opt = $('#chkTableContent').datagrid('getColumnOption','chkContent');
		                				opt.title='【】检查内容';
										 $('#chkTableContent').datagrid('loadData',[]);
										 $('#qaChkRecordResult').datagrid('loadData',[]);
										 $('#SOPList').datagrid('loadData',[]);
										$.messager.alert("提示框",r.msg);
										 
									}
								},
							});
		                	
		            		$('#checkTables').combobox({
				            	url:sybp()+'/qAChkRecordAction_getTableByItem.action?chkItemId='+record.chkItemId,
				            	onLoadSuccess:function()
				            	{
		            				var data = $('#checkTables').combobox('getData');
	               	 				if(data!=null&&data.length>0)
	               						$('#checkTables').combobox('select',data[0].chkTblId);
		            				
				            	}
		            		});
		            		$.messager.progress('close');
               	 		},
		            	//如果有方案或者报告直接加进去
		            	onLoadSuccess:function()
		            	{
		            		$.messager.progress('close');
		            		
               	 			var data = $('#checkItems').combobox('getData');
               	 			var dd = $('#planDate').combobox('getValue');
               	 		   	if(dd!=null&&dd!=''&&$('#oneChkPlanIdForList').val()!=null&&$('#oneChkPlanIdForList').val()!=''&&
               	 		 		 $('#isPlanDateSelectFlag').val()=='1'){
								// plan.getChkPlanId()+":"+plan.getChkItemId()
								//alert("===="+$('#oneChkPlanIdForList').val());
								 $('#checkItems').combobox('select', $('#oneChkPlanIdForList').val());
								 $('#oneChkPlanIdForList').val('');
								 
								 $('#isPlanDateSelectFlag').val('0');
								
			            	}else if(data!=null&&data.length>0){
               					$('#checkItems').combobox('select',data[0].chkItemId);
			            	}
               	 			
		            	}
               		 	
               	 	});
		           
		            $('#checkTables').combobox({
		            	valueField:'chkTblId',
		                textField:'chkTblName',
		                onSelect: function(rec){
		                	//更新表头
		                	var opt = $('#chkTableContent').datagrid('getColumnOption','chkContent');
		                	opt.title='【'+rec.chkTblName+'】检查内容';
		                	
		            	 	 $('#chkTableContent').datagrid({
			            	 	 url:sybp()+'/qAChkRecordAction_getTableContent.action?chkTblId='+rec.chkTblId,
			            	 });
		            	 	$('#oneAdvice').val('');
		            		$('#oneChkResultDesc').val('');
		            		 
               	 		}
               	 	});
		            
	            	
	            }
				$('#addQAChkIndexDialog').dialog({
					left:10,
					top:10,
				});
				hasScheduleChange();
				
        });//匿名函数结束
      function	initSignAndWriteReportButton()
      {
    	  $('#signQAChkRecord').linkbutton('disable');
    	  $('#writeQAChkReport').linkbutton('disable');
    	  $('#chkRecordSaveChkRecordButton').linkbutton('disable');
    	  $('#otherAllOperate').linkbutton('disable');
    	  $('label input[name="chkResult"]').attr('disabled','disabled');
          
    	 var state =  $('#chkIndexStatus').val();//0:：草稿；1：检查中（启动）2：完成
    	// var rows = $('#qaChkRecordResult').datagrid('getRows');
    	//alert(rows+"=="+rows.length);
		/* if(state!='2'&&rows!=null&&rows.length>0)
		 {
			 $('#signQAChkRecord').linkbutton('enable');
		 }*/
		 var studyFinishForRecord=$('#studyFinishForRecord').val();
        // alert("studyFinishForRecord="+studyFinishForRecord);
         if(studyFinishForRecord!='true')
         {
			if(state=='2')
			{
				$('#writeQAChkReport').linkbutton('enable');
				$('#addItemSOPFile').linkbutton('disable');
			}else
			{
				$('#signQAChkRecord').linkbutton('enable');
				$('#chkRecordSaveChkRecordButton').linkbutton('enable');
				 $('#otherAllOperate').linkbutton('enable');
				$('label input[name="chkResult"]').attr('disabled',false);
				$('#addItemSOPFile').linkbutton('enable');
			}
         }else{
			$('#planDate').combobox('disable');
			$('#checkItems').combobox('disable');

			
         }
             
    	 
      }
	 
      function hasScheduleChange(){
    	var scheduleChanged = $('#scheduleChangedForRecord').val();
  		if(scheduleChanged=='true')
  		{
  			 $.messager.alert('提示',"日程有变更，请根据日程变更生成新的检查计划！",'',function(){
		  		parent.$('#researchCheckTabs').tabs('select','检查计划');

  	  		});	
  			
  		}
      }
	
	function otherAllOperate(){
		 var allOperate = $('input[name="chkResult"]:checked').val();
		 var str='';
		 if(allOperate=='√')
		 {
		 	str='符合';
		 }else if(allOperate=='NA'){
		 	str='不适用';
		 }
		$.messager.confirm('确认框','其余都'+str+',是对检查项的其余所有检查内容操作的，确认要执行该操作?',function(r){
		    if (r){
		        afterConfirmOtherAllOperate();
		    }
		});
	}
      function afterConfirmOtherAllOperate()
      {
          //其余都符合或者其余都不适用
		  var allOperate = $('input[name="chkResult"]:checked').val();
          
          var chkTblId=$('#checkTables').combobox('getValue');

          var qaIndexId = $('#chkIndexId').val();
          var planIdItemId = $('#checkItems').combobox('getValue');//plan和item
          var studyNoParam = $('#studyNoParamForRecord').val();
          
          $.ajax({
				url:sybp()+'/qAChkRecordAction_otherAllOpearte.action?studyNoParam='+studyNoParam+'&qaIndexId='+qaIndexId+'&planIdItemId='+planIdItemId+'&chkTblId='+chkTblId,
				type:'post',
				dataType:'json',
				data:{allOperate:allOperate},
				success:function(r){
					
	        	    if(r&&!r.success)
					{
						if(r.noFinishRead){
							$.messager.alert('提示框','在开始检查之前，请阅读方案文件！','info',function(){
								parent.$('#researchCheckTabs').tabs('select','方案/报告检查');
							});
							
						}else{
							$.messager.alert("提示",r.msg);
						}
					}
					if(r&&r.success)
					{
						if($('#chkIndexId').val()==null||$('#chkIndexId').val()=='')
						{
							$('#SOPList').datagrid({
								url:sybp()+'/qAChkRecordAction_getSOPByChkIndex.action?qaIndexId='+r.chkIndexId,
							});
							//$('#SOPList').datagrid('reload');
						}
						
						$('#chkIndexId').val(r.chkIndexId);
						$('#qaChkRecordResult').datagrid({
				           	url:sybp()+'/qAChkRecordAction_getRecordsByTable.action?qaIndexId='+r.chkIndexId,
						});
		 				//$('#qaChkRecordResult').datagrid('reload');
						
					}
					
          		}
          });
          
      }
      
      function chooseSOP(){
    	  document.getElementById("chooseChkSOPDialog2").display="block";
			
    	  var planIdItemId = $('#checkItems').combobox('getValue');//plan和item
  		
  		  $('#dictItemSOPList').datagrid({
  		 	url:sybp()+'/qAChkRecordAction_getDictSopForItem.action?planIdItemId='+planIdItemId,
  	  	  });
  		// $('#dictItemSOPList').datagrid('reload');
  		 $('#chooseChkSOPDialog').dialog('open');

      }
      
      function saveQAChkSOP(){
    	var rows = $('#dictItemSOPList').datagrid('getSelections');
		if(rows==null||rows=='')
		{
			$.messager.alert('提示','没有选择任何法规文件');
			$('#chooseChkSOPDialog').dialog('close');
		}else{
			var qaIndexId = $('#chkIndexId').val();
			var planIdItemId = $('#checkItems').combobox('getValue');//plan和item
			var studyNoParam = $('#studyNoParamForRecord').val();
			
			var fileRegIds = new Array();
			for(var i=0;i<rows.length;i++){
				fileRegIds.push(rows[i].fileRegId);
			}
			$.ajax({
				url:sybp()+'/qAChkRecordAction_saveQAChkSOP.action?studyNoParam='+studyNoParam+'&qaIndexId='+qaIndexId+'&planIdItemId='+planIdItemId+'&fileRegIds='+fileRegIds,
				type:'post',
				dataType:'json',
				success:function(r){
					if(r)
					{
						if(r.exist){
							$.messager.alert('提示','部分法规文件已经存在');
						}
					}
					$('#chkIndexId').val(r.chkIndexId);
					$('#SOPList').datagrid({
						url:sybp()+'/qAChkRecordAction_getSOPByChkIndex.action?qaIndexId='+r.chkIndexId,
					});
					//$('#SOPList').datagrid('reload');
				
				    $('#chooseChkSOPDialog').dialog('close');
				},
			});
		}
			
		
		
      }
</script>
	  
</head>

<body >
	    	
	
	<s:hidden id="studyNoParamForRecord" name="studyNoParam"></s:hidden>
	<s:hidden id="planStateForRecord" name="planStateForChkRecord"></s:hidden>
	<input id="roleForChkRecord" type="hidden" value=${role }></input>
	<s:hidden id="scheduleChangedForRecord" name="scheduleChangedForRecord"></s:hidden>
	<s:hidden id="studyFinishForRecord" name="studyFinishForRecord"></s:hidden>
	<s:hidden id="oneChkPlanIdForList" name="oneChkPlanIdForList"></s:hidden>
	<s:hidden id="onePlanChkDateForList" name="onePlanChkDate"></s:hidden>
	
	<s:hidden id="chkIndexId"></s:hidden>
	<s:hidden id="chkIndexStatus"></s:hidden>
	<s:hidden id="isPlanDateSelectFlag"></s:hidden>
	<!-- 
		<div>
			如果没有相应的检查项，请查看配制中是否把检查项和试验类别做了关联
		</div>
	 -->
	<div class="container">
	<div style="display:block; position:absolute;top:0px;left:0px;right:0px; height:30px;background:#fff;padding:0px 0px 0px 0px;">
	    <nobr>
		            计划检查日期<div id="planDate" data-options="editable:false" class="easyui-combobox" style="width: 100px;"></div>
		            检查项目    <select id="checkItems" data-options="editable:false" class="easyui-combobox" style="width:200px;">		
				</select>
			 检查用表格    <select id="checkTables" data-options="editable:false" class="easyui-combobox" style="width:200px;">
				</select>
			<a id="signQAChkRecord" class="easyui-linkbutton" onclick="signQAChkRecord();" disabled data-options="iconCls:'icon-edit',plain:true">签字确认</a>
			<a id="writeQAChkReport" class="easyui-linkbutton" onclick="writeQAChkReport();" disabled data-options="iconCls:'icon-edit',plain:true">填写检查报告</a>
	    </nobr> 
	</div>
     <div style="display:block; position:absolute;top:30px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:auto;" >
 	 	 
				<div style="float:left;position:relative;left: 10px;">
				
					<div >
						<div id="chkTableContent"></div>
					</div>
					<!-- 
					<div style="margin-top: 2px; padding:5px; ">
					 -->
				   		<form id="oneQAChkRecord" style="margin-top: 2px; padding:5px;border: 1px solid #c8c8c8;">
				   			<div>
				   				内容: <label id="checkContent" style="width: 270px; height: 20px; "/>
				   			</div>
				   			<div style="margin-top: 5px;">
				   				发现:
				   				<!-- 
					   			<div  style="border: 1px solid #c8c8c8;margin-top: 10px;">
				   				 -->
				   				<label for="chkRecordRight">
						   			<input id="chkRecordRight" value="√" disabled name="chkResult" checked="checked" type="radio" onclick="selectResult(1);"/>√:符合
								</label>
								<label for="chkRecordWrong">
									<input id="chkRecordWrong" value="×" disabled name="chkResult" type="radio" onclick="selectResult(-1);"/>×:不符合
								</label>							
								<label for="chkRecordNA">
									<input id="chkRecordNA" value="NA" disabled name="chkResult" type="radio" onclick="selectResult(0);"/>NA:不适用
					  	 		</label>
					  	 		<!-- $('label input[name="chkResult]"')
					  	 		</div>
					  	 		<div >
					  	 		
						   	 		<input id="needBack" type="checkbox" style="vertical-align: middle;" readonly="readonly" disabled="disabled" >需回复</input>
					  	 		
					  	 		</div>
					  	 		 -->
				   			</div>
				   			<div style="margin-top: 5px;">
				   	 			问题:<input id="oneChkResultDesc" readonly="readonly" name="chkResultDesc"  style="width:270px;height: 20px;"/>
				   	 						   	 			
				   	 		</div>
				   	 		<div style="margin-top: 5px;">
				   	 			建议:<input id="oneAdvice" readonly="readonly" name="advice"  style="width:270px;height: 20px;"/>
				   	 			
				   	 		</div>
				   	 		<div>
				   	 			<a id="otherAllOperate" class="easyui-linkbutton" onclick="otherAllOperate();" disabled data-options="iconCls:'icon-add',plain:true">其余都符合</a>
					 			&nbsp;&nbsp;&nbsp;
					 			<a id="chkRecordSaveChkRecordButton" class="easyui-linkbutton" onclick="saveChkRecord();" disabled data-options="iconCls:'icon-rightarr',plain:true">确定</a>
				   	 			
					 		</div>
				   		</form>
				   		<!-- 
					</div> 
				   		 -->
					<div style="margin-top: 2px;">
						<div id="SOPList" class="easyui-datagrid"></div>
				   	 			
					</div>
					<!-- 
						<a id="addItemSOPFile" class="easyui-linkbutton" onclick="chooseSOP();" disabled data-options="iconCls:'icon-add',plain:true">选择SOP文件</a>
					 -->
				 
			   	</div><!-- 左边结束 -->
			   	<div style="float:left;margin-left:10px;left:10px;padding-left: 10px;">
			   		<div id="qaChkRecordResult" class="easyui-datagrid"></div>
			   	</div>
			 </div>
		</div>
		
	
   <%@ include file="/WEB-INF/jsp/QAChkRecordAction/chooseChkSOP.jspf"%>
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</body>
</html>




