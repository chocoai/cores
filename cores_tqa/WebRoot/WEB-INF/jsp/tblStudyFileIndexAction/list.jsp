<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES方案/报告检查</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>



<script type="text/javascript">
	  
			
   	   	function initStudyFileIndexButton()
   	   	{
   	 	  	var role1 = $('#roleForStudyFile').val();
			var have1 = $('#haveStudyFileRight').val();
   	   		var indexId = $('#studyChkFileType').combobox('getValue');
	   	   	var strs= new Array(); //定义一数组 
			strs=indexId.split(":"); //字符分割 
			//alert(strs[0]+"==="+strs[1]);
			
			
			var studyFinish = $('#studyFinishForStudyFile').val();
			if(studyFinish!='true')//专题没有完成
			{
				if(role1=="QALead")
	   	  		{
					$('#approvalStudyFileChangeButton').linkbutton('enable');
		   	  	}
				if(strs[1]!=null&&strs[1]!='null'&&strs[1]!='')
				{ 
					//已经确认过的
						$('#studyPlanReadButton').linkbutton('enable');
			   	  		$('#acceptStudyFileButton').linkbutton('disable');
			   	  		if(role1=='QA'||role1=="QALead")//是QA并且是该专题的QA
			   	  		{
			   	  			if(have1==true||have1=='true')//有权限
			   	  			{
			   	  				$('#writeStudyFileChkRecordButton').linkbutton('enable');
			   	  			}else
			   	  			{
			   	  				$('#writeStudyFileChkRecordButton').linkbutton('disable');
			   	  			}
			   	  		}
			   	  		
				}else{
					//没有确认过
					$('#studyPlanReadButton').linkbutton('disable');
					$('#writeStudyFileChkRecordButton').linkbutton('disable');
					if(role1=='QA'||role1=="QALead")//是QA并且是该专题的QA
			   	  		{
			   	  			if(have1==true||have1=='true')//有权限
			   	  			{	
								$('#acceptStudyFileButton').linkbutton('enable');
			   	  			}else{
		   	   	  				$('#acceptStudyFileButton').linkbutton('disable');
		   	   	  			}
		   	   	  			
			   	  		}
			   	  			
				}
			}else{
			
			}
			
			
   	   	}
   	   	   	
   	   	
    	$(function(){
    		var tableHeight = document.body.clientHeight-26;
    		var tableWidth  = parent.parent.$('#researchMain').css("width")+15;
       	   	
			var role1 = $('#roleForStudyFile').val();
			var have1 = $('#haveStudyFileRight').val();
   		
	   	  // alert(role1+"==="+have1);
	   	   
        	$('#studyChkFileType').combobox({
        		url:sybp()+'/tblStudyFileIndexAction_getTypeStudyNo.action?studyNo='+$('#studyNoParamForStudyIndex').val(),
        		valueField:'indexId',
        		textField:'text',
        		onSelect: function(rec){
	        		var strs= new Array(); //定义一数组 
	    			strs=rec.indexId.split(":"); //字符分割 
	    			
	        		$('#tblStudyFileDatagrid').datagrid({
	    				url:sybp()+'/tblStudyFileIndexAction_getStudyFileByStudyFileIndexId.action?studyFileIndexId='+strs[0],
	    				
	    			});
	        		//$('#tblStudyFileDatagrid').datagrid('reload');
	        		initStudyFileIndexButton();
            	},
            	onLoadSuccess:function(){
            		var data = $('#studyChkFileType').combobox('getData');
            		if(data!=null&&data.length>0)
            		{
            			var afterAction = $('#afterTabOpenActionForStudy').val();
            			if(afterAction==null||afterAction==''||afterAction=='1')
            			{
                			$('#studyChkFileType').combobox('select',data[0].indexId);
                		}else if(afterAction=='2'){
                			$('#studyChkFileType').combobox('select',data[1].indexId);
                		}
                	}
                }
        	
            });
        	$('#tblStudyFileDatagrid').datagrid({
        		height:tableHeight,
				width:tableWidth,
				singleSelect:true,
				//url:sybp()+'/tblStudyFileIndexAction_getStudyFileByStudyNo.action?studyNo='+$('#studyNoParamForStudyIndex').val(),
				// linkId,eslink.[recordTime],[chkReportCode],[studyNo],eslink.[esTypeDesc],es.signer
				columns:[[
						{
							title:'studyFileIndexId',//studyFileIndex id
							field:'studyFileIndexId',
							hidden:true,
						},
						{
							title:'id',//studyFile id
							field:'id',
							hidden:true,
						},
						
						{
							title:'文件名称',
							field:'attachmentName',
							width:150,
						},
						
						{
							title:'添加日期',
							field:'submitTime',
							width:100,
						},
						{
							title:'描述',
							field:'attachmentDesc',
							width:150,
						},
						{
							title:'专题状态',
							field:'fileState',
							width:80,
							formatter:function(value,row,index)
							{
								//0：草稿；1：提交审批中；2：结束
								if(value==0)
								{
									return "草稿";
								}
								if(value==1)
								{
									return "提交审批中";
								}
								if(value==2)
								{
									return "结束";
								}
							}
						},{
							title:'下载',
							field:'downLoad',
							width:50,
							hidden:false,
							formatter:function(value,row,index)
							{
								//row.id是studyFile的id
								//alert('<a href="#" onclick="delOneStudyFile('+index+','+row.id+');">无效</a>');
								return "<a href="+sybp()+"/tblStudyFileIndexAction_downloadAttachment.action?fileId="+row.id+">下载</a>";
							}

						},
						
					]],
					onSelect:function(rowIndex,rowData)
					{
			 			
					}
        		
            });
        	
        	
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

	function writeChkRecord()
	{
		//有方案或者报告（SD提交的）
 	  	var studyFileSelect=$('#studyChkFileType').combobox('getValue');
 	  //	alert("writeChkRecord="+studyFileSelect);
 	  	if(studyFileSelect==null||studyFileSelect=='')
 	  	{
 	 	  	$.messager.alert("提示","请选择一个方案或者文件");
 	  	}else
 	  	{
			parent.window.location.href=sybp()+"/qAChkPlanAction_main.action?studyNoParam="+$('#studyNoParamForStudyIndex').val()+"&fileIndexId="+studyFileSelect+"&index=2";
			
			
	 	}
 	  			
		
	}
	function qaConfirmStudyFileIndex()
	{
		 qm_showQianmingDialog("afterSignQAConfirmStudyFileIndex");	
	}
	function afterSignQAConfirmStudyFileIndex()
	{
		var indexId = $('#studyChkFileType').combobox('getValue');
		var indexName = $('#studyChkFileType').combobox('getText');
		var strs= new Array(); //定义一数组 
		strs=indexId.split(":"); //字符分割 
			
		 $.ajax({
	        	url : sybp()+'/tblStudyFileIndexAction_confirmStudyFileIndex.action?studyFileIndexId='+strs[0],
	        	type: 'post',
	        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		         dataType:'json',
		         success:function(r){
			         if(r)
			         {
				       
	        			$('#studyChkFileType').combobox('setValue',r.indexId);
	        			$('#studyChkFileType').combobox('setText',indexName);
	        			$('#studyChkFileType').combobox('reload');
	        			initStudyFileIndexButton();
			         }
		         }
		  });
	}
	
	//qam审批修改申请
	function approvalStudyFileChange()
	{
		$.ajax({
        	url : sybp()+'/tblStudyFileIndexAction_getChangeApply.action?studyNoParam='+$('#studyNoParamForStudyIndex').val(),
        	type: 'post',
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	         dataType:'json',
	         success:function(r){
		         if(r)
		         {
			         if(r.type==0||r.type==1)
			         {
				         var str = "";
				         if(r.type==0)
				        	 str = "方案";
			        	 if(r.type==1)
			        		 str = "报告";
		        		 $('#changeTypeLabel').val(r.type);
		        		 
			        	  $.messager.defaults = { ok: "批准", cancel: "退回" }; 
			              $.messager.confirm('确认对话框', r.reason+'是否批准变更？', function(r){
							  var changeState = 2;
							  if (r){
								  changeState = 2;
							  }else{
								  changeState = -2;
							  }
							  $('#changeStateLabel').val(changeState);
							  qm_showQianmingDialog("afterSignApprovalApply");	
							  
						  }); 
			              $.messager.defaults = { ok: "确定", cancel: "取消" }; 
			         }else if(r.type==2){
			        	 document.getElementById("approvalQAChkPlanDialog2").display="block";
			 			
				         $('#approvalChangeResonLabel').val(r.reason);
				         
			 			
			 			$('#approvalQAChkPlanDialog').dialog('open');
				      	   
			         }else{
							$.messager.alert("提示","没有方案变更");
				     }
		         }
	         },
	  });
		
	}
	function selectApprovalChangeType(changeState)
	{
		 $('changeStateLabel').val(changeState);
		 var type = $('input[name=approvalChangeScope]:checked').val();
		 $('#changeTypeLabel').val(type);
		 qm_showQianmingDialog("afterSignApprovalApply");	
	}
	function afterSignApprovalApply()
	{
		var changeState =  $('#changeStateLabel').val();
		var changeType =  $('#changeTypeLabel').val();
		
		$.ajax({
        	url : sybp()+'/tblStudyFileIndexAction_afterSignApprovalApply.action?studyNoParam='+$('#studyNoParamForStudyIndex').val()+'&fileType='+changeType+'&changeFlag='+changeState,
        	type: 'post',
        	/*data:{
				  fileType:changeType,
        		  changeFlag:changeState,
        	},*/
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	         dataType:'json',
	         success:function(r){
        		initStudyFileIndexButton();
        		if(r)
        		{
            		var type = "";
        			if(changeType==0)
        				type="方案";
        			if(changeType==1)
        				type="报告";
        			if(changeType==2)
        				type="方案和报告";
        			var msg='QAM审批'+type+'变更申请后发邮件出现错误！';
					sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
        		}
        	}
		});
		
	}
	function studyPlanReadButton()
	{
		 qm_showQianmingDialog("afterSignStudyPlanReadFinish");	
	}
	function afterSignStudyPlanReadFinish()
	{
		$.ajax({
        	url : sybp()+'/tblStudyFileIndexAction_saveStudyPlanRead.action?studyNoParam='+$('#studyNoParamForStudyIndex').val(),
        	type: 'post',
        	
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	         dataType:'json',
	         success:function(r){
        		$.messager.show({
					//title:'',
					msg:'方案阅读完毕，可以开始检查',
					timeout:4000,
					showType:'show',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						height:30,
					}
				});
        	 }
		});
	}
	
	 function sendNotification(msgTitle,msgContent,receiverList,msg){
			if(receiverList!=null&&receiverList!=''){
				
				  $.ajax({
			      	url : sybp()+'/tblStudyFileIndexAction_sendNotification.action',
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
	  
</head>

<body >
	<s:hidden id="studyNoParamForStudyIndex" name="studyNoParam"></s:hidden>
	<s:hidden id="studyFinishForStudyFile" name="studyFinishForStudyFile"></s:hidden>
	
	<input id="roleForStudyFile" type="hidden" value=${role }></input>
	<s:hidden id="haveStudyFileRight" name="have"></s:hidden>
	<s:hidden id="changeStateLabel"></s:hidden>
	<s:hidden id="changeTypeLabel"></s:hidden>
	<s:hidden id="afterTabOpenActionForStudy" name="afterTabOpenAction"></s:hidden>
	<div >
		<div>
			<!-- 1：方案；2：报告； -->
			<select id="studyChkFileType" data-options="editable:false" class="easyui-combobox" style="width:200px;">
			</select>
			<a id="acceptStudyFileButton" class="easyui-linkbutton" disabled onclick="qaConfirmStudyFileIndex();" data-options="iconCls:'icon-ok',plain:true">QA确认接收</a>		
			<a id="writeStudyFileChkRecordButton" class="easyui-linkbutton" disabled onclick="writeChkRecord();" style="display:none;" data-options="iconCls:'icon-add',plain:true">填写检查记录</a>	
			<a id="studyPlanReadButton" class="easyui-linkbutton" disabled onclick="studyPlanReadButton();" data-options="iconCls:'icon-ok',plain:true">确认方案阅读完毕</a>	
			<!-- 
			<a id="approvalStudyFileChangeButton" class="easyui-linkbutton" disabled onclick="approvalStudyFileChange();" data-options="iconCls:'icon-tableedit',plain:true">QAM审批方案变更</a>		
			 -->	
		
		</div>
       <div  >
 	 	 <div id="tblStudyFileDatagrid" class="easyui-datagrid">
 	 	   
			
		 </div>
 	 </div>
       
    </div>
    
   <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
   <%@ include file="/WEB-INF/jsp/tblStudyFileIndexAction/chooseApprovalChangeItem.jspf"%>
</body>
</html>




