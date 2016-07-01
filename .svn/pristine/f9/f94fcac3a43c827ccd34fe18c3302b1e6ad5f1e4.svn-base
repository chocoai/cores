<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专题方案</title>
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
	
	
	function delOneStudyFile(index,id)
	{
	//	alert("==="+index);
		//$('#studyAttachmentDatagrid').datagrid('selectRow',index);
		
		//var  row = $('#studyAttachmentDatagrid').datagrid('getSelected');
		//if(row!=null)
		//{
			$.ajax({
				url:sybp()+'/tblStudyFileAction_del.action?id='+id,
				type:'post',
				data:$('#oneReplyMessage').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						var  row = $('#studyAttachmentDatagrid').datagrid('getSelected');
						if(row!=null)
						{
							var data = $('#studyAttachmentDatagrid').datagrid('getData');
							if(data.rows.length==1)
							{
								parent.$('#studyAttachCommitToQA').linkbutton('disable');
							}
							var index = $('#studyAttachmentDatagrid').datagrid('getRowIndex',row);
							$('#studyAttachmentDatagrid').datagrid('deleteRow',index);
						}else{
							$('#studyAttachmentDatagrid').datagrid('reload');
						}
							
					}
				}
			});
			
		//}else
		//{
		//	$.messager.alert('提示','请选择一条记录');
		//}
	}
	

	
</script>

<script type="text/javascript">

    	$(function(){
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    	
			
			//alert('${studyNoPara }'+"==="+'${fileType}')
			//如果是学习的人，不显示上传
			var isHidden=true;
			 if(parent.$('#isFileDisForAttachment').val()==1)
			 {
				 isHidden=false;
			 }
			 $('#studyAttachmentDatagrid').datagrid({
	        		height:222,
					width:637,
					singleSelect:true,
					url:sybp()+'/tblStudyFileIndexAction_getStudyFileByStudyNo.action?studyNo=${studyNoPara }&fileType=${fileType}',
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
									if(value==-1)
									{
										return "退回";
									}
									if(value==2)
									{
										return "结束";
									}
								}
							},
							{
								title:'是否有效',
								field:'delFlag',
								width:50,
								formatter:function(value,row,index)
								{
									if(value==1)
									{
										return "无效";
									}else {
										return "有效";
									}
								},
								hidden:true,
							},
							{
								title:'操作',
								field:'operate',
								width:50,
								hidden:!isHidden,
								formatter:function(value,row,index)
								{
									//row.id是studyFile的id
									//alert('<a href="#" onclick="delOneStudyFile('+index+','+row.id+');">无效</a>');
									if(value==1)
									{
										return '<a href="#" onclick="delOneStudyFile('+index+','+row.id+');">删除</a>';
									}else
									{
										return '删除';
									}
								}

							},{
								title:'下载',
								field:'downLoad',
								width:50,
								hidden:isHidden,
								formatter:function(value,row,index)
								{
									//row.id是studyFile的id
									//alert('<a href="#" onclick="delOneStudyFile('+index+','+row.id+');">无效</a>');
									return "<a href="+sybp()+"/tblStudyFileAction_downloadAttachment.action?id="+row.id+">下载</a>";
								}

							},
		         			
							
							
						]],
						onSelect:function(rowIndex,rowData)
						{
				 			
						}
	        		
	            });
			   $('#studyFileAttachmentUploadForm').form({    
				    url:sybp()+'/tblStudyFileAction_importFile.action?studyNo=${studyNoPara }&fileType=${fileType}',
				    onSubmit: function(){    
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
						}
						return isValid;	// 返回false终止表单提交
				    },    
				    success:function(data){   
				    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
				    	if(data == 'true'){
				    	//	$('#attachmentAddEditDialog').dialog('close');
				    	//  document.getElementById("attachmentAddEdit_event").click();
							$('#oneAttachmentFile').val('');
							$('#oneAttachmentDesc').val('');
							$('#oneFileVersion').val('');
						//更新datagrid
							 $('#studyAttachmentDatagrid').datagrid('reload');

							 parent.initButton();
				    		
					    }else{
				    		$.messager.alert('警告','文件上传失败'); 
				    	}
				    } 
				});

				//如果是学习的人，不显示上传
			    if(parent.$('#isFileDisForAttachment').val()==1)
				{
					 $('#studyFileAttachmentUploadForm').css('display','none');  
					 var tab = parent.$('#studyAttachmentTabs').tabs('getSelected');
						var title = tab.panel('options').title;
						var fileType = 1;
						if(title=='专题方案')
						{
							fileType = 1;
						}else if(title=='专题报告')
						{
							fileType = 2;
						}
						 //判断是方案还是报告
						$.ajax({
								url:sybp()+'/tblStudyFileIndexAction_isNoFinishFileDisByType.action?studyNo='+parent.$('#studyNoForAttachment').val()+'&fileType='+fileType,
								type:'post',
								//data:$('#oneReplyMessage').serialize(),
								dataType:'json',
								success:function(r){
									if(r&&r.exist)
									{
										parent.$('#tblStudyFileDisReadButton').linkbutton('enable');
									}else{
										parent.$('#tblStudyFileDisReadButton').linkbutton('disable');
									}
									
								}
							});
					 
				}
				
				parent.initButton();    
	            
        });//匿名函数结束

        function changeAttachmentName(obj)
        {
        	var fullFileName = obj.value;
        	var pos = fullFileName.lastIndexOf('\\');
        	var fileName = fullFileName.substring(pos+1); 
        	
            $('#oneAttachmentName').val(fileName);
            $('#oneFileVersion').val(1);
        }
        /**确定（保存）*/
        function onAttachmentSaveButton(){

        	$.messager.progress({title: '请稍后',
				msg: '处理中...',
				text:''});	// 显示进度条
        	////var versionValue = $('#oneFileVersion').val();
        	//alert(versionValue);
        	
        	
	       // if(isNaN(versionValue)){
	       // 	$.meeager.alrt("提示","版本必须为数字");
	       // } else{
	        	//	 alert("是数字");
	        	$('#studyFileAttachmentUploadForm').submit(); 
	        		 
	       // }
        	
			
        }
	 
		
</script>
	  
</head>

<body >
	 <!--这种方式传不过来值。 有待查找
	  -->
	<s:hidden id="studyNoParam2" name="studyNoPara"></s:hidden>
	<!-- 
		${studyNoPara }
		${fileType}
	 -->
	 <div class="layoutMainDiv" style="width:635;">
			<div id="studyAttachmentDatagrid" style="padding-top:0px;padding-left:5px;margin-left: 0px;margin-top: 0px;border:1px solid #f00;"></div>
		
			<form id="studyFileAttachmentUploadForm" style="padding-top:0px;border:1px solid #c8c8c8;" method="post" enctype="multipart/form-data" theme="simple">
			 <table>
			 	<tr>
			 		<td></td>
			 		<td>
			 			<input type="hidden" id="oneAttachmentName" name="attachmentName"/>
			 			<input id="oneAttachmentFile" required="true" onchange="changeAttachmentName(this);" missingMessage="请选择文件" type="file" name="attachmentfile" class="easyui-validatebox" style="width:270px;"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>描述：</td>
		 			<td>
		 				<input id="oneAttachmentDesc" type="text" name="attachmentDesc" style="width:270px;"/>
		 				<a id="oneStudyFileUploadButton" class="easyui-linkbutton" onclick="onAttachmentSaveButton();" data-options="iconCls:'icon-add',plain:true">上传</a>
			 		
		 			</td>
			 	</tr>
			 	<!-- 
			 	<tr>
			 		<td>版本：</td>
			 		<td><input name="FileVersion" id="oneFileVersion"/>	
			 		</td>
			 	</tr>
			 	 -->
			 	
			 </table>
			
	 		</form>
 
	 </div>

			
   
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</body>
</html>




