<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SOP类别设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	function addArchivePosition(addOrEdit)
	{
		//$('#onepid').combotree('reload');
		$('#addOrEdit').val(addOrEdit);
		if(addOrEdit==1)//新增
		{
			/* 显示Dialog */
			document.getElementById("addOrEditArchivePositionDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddButton').css('display','');
			
			$('#onepid').combotree({
				valueField: 'id',
	    	    textField: 'text',
	    	    url: sybp()+'/dictArchivePositionAction_loadComboTree.action',
	    	    onLoadSuccess:function(node, data)
	    	    {
	        	},
	        	onChange:function(newValue, oldValue)
	        	{
	        	}
			});
			//$('#onepid').combotree('setText',0);
			$('#onePositionName').val('');
			$('#updateArchivePositionID').val('');
			
			$('#addOrEditArchivePositionDialog').dialog('setTitle','添加档案位置');
			$('#addOrEditArchivePositionDialog').dialog('open'); 
		}
		else if(addOrEdit==2)//编辑
		{
			
			/* 显示Dialog */
			document.getElementById("addOrEditArchivePositionDialog2").style.display="block";
	
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var row=$('#archivePositionTree').treegrid("getSelected");
			if(row!=null&&row!='')
			{
				$('#onepid').combotree({
					valueField: 'id',
		    	    textField: 'text',
		    	    url: sybp()+'/dictArchivePositionAction_loadComboTree.action?id='+row.id,
		    	    onLoadSuccess:function(node, data)
		    	    {
		    	    	$('#onepid').combotree('setValue',row.pid);
		        	},
		        	onChange:function(newValue, oldValue)
		        	{
			        	if(newValue==row.id)
			        	{
				        	$.messager.alert('提示框','父类不能是自己本身','info',function(){
				        		$('#onepid').combotree('setValue',oldValue);
					        });
				        }
		        	}
				});

				$('#onePositionName').val(row.text);
				$('#updateArchivePositionID').val(row.id);
				
		      
				$('#addOrEditArchivePositionDialog').dialog('setTitle','编辑档案位置');
				$('#addOrEditArchivePositionDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个档案位置！");
			}
		}
		
	}
	function delArchivePosition()
	{
		var row=$('#archivePositionTree').treegrid("getSelected");
		
		if(row!=null&&row!='')
		{
			//判断是否存在子文件类型
			$.ajax({
				url:sybp()+'/dictArchivePositionAction_isExistChildArchivePosition.action?id='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){	
					if(r&&!r.isExist)
					{
						$.messager.confirm('确认框','确定要删除'+row.text+'吗?',function(r){
							if (r){
								qm_showQianmingDialog('afterSignDelArchivePosition');
							}
						});
					}else{
						$.messager.alert('提示框','该位置下还包含子位置，请先删除子位置');
					}
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个档案位置！");
		}
	}
	function afterSignDelArchivePosition()
	{
		var row=$('#archivePositionTree').treegrid("getSelected");
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/dictArchivePositionAction_delete.action?id='+row.id,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r && r.success){
					
					 var node = $('#archivePositionTree').treegrid('find', row.id);
					 $('#archivePositionTree').treegrid('remove',node.id);

					//禁用按钮（编辑，删除）
					$('#editArchivePosition').linkbutton('disable');
					$('#delArchivePosition').linkbutton('disable');
							
					parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.showMessager(3,r.msg,true,5000);
				}
			}
		});
		
	
	}
	
	function saveOrUpdateOneArchivePosition()
	{
		var onesopname = $('#onePositionName').val();
		//$("#oneSOPTypeTable").form('validate')
		if(onesopname=='')
		{
			  $.messager.alert("提示框","位置名称不能为空！");
		}else{
			 var addOrEdit = $('#addOrEdit').val();
		  	if( addOrEdit=='1' ){//新增
				$.ajax({
					url:sybp()+'/dictArchivePositionAction_save.action',
					type:'post',
					data:$('#oneArchivePositionTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							var node;
							if(r.pid!=null&&r.pid!='')
							{
								node = $('#archivePositionTree').treegrid('find', r.pid);
								$('#archivePositionTree').treegrid('append', {
									parent: node.id,
									data: [{
											id:r.id,
									        pid:r.pid,
									        text:r.positionName,
									        
									}]
									
								});
								
							}else{
								
								$('#archivePositionTree').treegrid('append', {
									data: [{
										id:r.id,
								        pid:r.pid,
								        text:r.positionName,
								       
									}]
									
								});
							}
							parent.showMessager(1,r.msg,true,5000);
							
							if($('#continueAddButton').attr('checked')!='checked')
							{
								 $('#addOrEditArchivePositionDialog').dialog('close');
							}else {
								//$('#onepid').combotree('select',0);
								$('#archivePositionTree').treegrid('reload');
								$('#onePositionName').val('');
								$('#updateArchivePositionID').val('');
							}
						}else if(r&&!r.success){
							parent.showMessager(3,r.msg,true,5000);
						}
					}
				});
				
				
				
			}else{ //编辑
				$.ajax({
					url:sybp()+'/dictArchivePositionAction_update.action',
					type:'post',
					data:$('#oneArchivePositionTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							//添加新的
							 	var node;
								if(r.pid!=null)
								{
									node = $('#archivePositionTree').treegrid('find', r.pid);
								}
								
								 //添加新的
								var childNodes = $('#archivePositionTree').treegrid('getChildren',r.id);
								//删除老的
								 $('#archivePositionTree').treegrid('remove',r.id);
								if(r.pid!=null&&r.pid!='')
								{
									$('#archivePositionTree').treegrid('append', {
										parent: node.id,
										data: [{
												id:r.id,
												pid:r.pid,
												text:r.positionName,
										        children:childNodes,
									        }],
									});
									
								}else{
									$('#archivePositionTree').treegrid('append', {
										data: [{
											id:r.id,
											pid:r.pid,
											text:r.positionName,
									        children:childNodes,
								        }],
									});
								}
								
								 parent.showMessager(1,r.msg,true,5000);
								 $('#addOrEditArchivePositionDialog').dialog('close');
						}else if(r&&!r.success){
							parent.showMessager(3,r.msg,true,5000);
						}
					}
				});
				
			
				
			}
		
			
		}//save之前验证
	}
	
	
	function changeCancelName()
	{
		if($('#continueAddButton').attr('checked')=='checked')
		{
			$('#cancelButton').linkbutton({
				text:'关闭'
		
			});
		}else {
			$('#cancelButton').linkbutton({
				text:'取消'
		
			});
		}
	}
	
	

	 function sendNotification(msgTitle,msgContent,receiverList,msg){
			if(receiverList!=null&&receiverList!=''){
				
				  $.ajax({
			      	url : sybp()+'/qALearnTaskAction_sendNotification.action',
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
	//上移
	function upOneArchivePosition()
	{
		var row = $('#archivePositionTree').treegrid('getSelected');
		
		
	}
	//下移
	function downOneArchivePosition()
	{
		var row = $('#archivePositionTree').treegrid('getSelected');
		
	}
	
	
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$('#wholeArchivePositionPage').css('display','');
		var tableSize ;
		var tableHeight = document.body.clientHeight-28;   //其他组件的高度
		var tableWidth = document.body.clientWidth-2;
	
	
		$('#archivePositionTree').treegrid({
			height:tableHeight,
			width:tableWidth,
			url: sybp()+'/dictArchivePositionAction_loadTree.action',
		    idField:'id',
		    treeField:'text',
			toolbar:'#toolbar',
		    columns:[[
		        {title:'id',field:'id',width:180,hidden:true},
		        {title:'pid',field:'pid',width:180,hidden:true},
		        {title:'位置名称',field:'text',width:250,state:'state',align:'left'},
		        
		    ]],
		    onSelect:function(rowIndex, rowData){
				$('#delArchivePosition').linkbutton('enable');
				$('#editArchivePosition').linkbutton('enable');
			
			},
			onUnselect:function(rowIndex, rowData){
				$('#delArchivePosition').linkbutton('disable');
				$('#editArchivePosition').linkbutton('disable');
			
			},
		});
		
		

	});//function结束





</script>
</head>
  
  <body>
  	
	<div id="wholeArchivePositionPage" class="easyui-layout" fit="true" border="false" style="display:none">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">档案位置设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="archivePositionTree" class="easyui-treegrid" ></div>
		</div>
		<div id="toolbar">
			<a id="addArchivePosition" class="easyui-linkbutton" onclick="addArchivePosition(1);" data-options="iconCls:'icon-add',plain:true">添加</a>
			<!-- 
			<a id="addSOPSonType" disabled class="easyui-linkbutton" onclick="addSOPSonType(0);" data-options="iconCls:'icon-add',plain:true">添加子一级</a>
			 -->
			<a id="editArchivePosition" disabled class="easyui-linkbutton" onclick="addArchivePosition(2);" data-options="iconCls:'icon-edit',plain:true">编辑</a>
			<a id="delArchivePosition" disabled class="easyui-linkbutton" onclick="delArchivePosition();" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<!-- 
			<a id="upOne" class="easyui-linkbutton" onclick="upOneSOPType();" data-options="plain:true,iconCls:'icon-undo'">上移</a>
			<a id="downOne" class="easyui-linkbutton" onclick="downOneSOPType();" data-options="plain:true,iconCls:'icon-redo'">下移</a>
			 -->
	
		</div>
		
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictArchivePositionAction/archivePositionAddEdit.jspf"%>
	</div>
	
  </body>
</html>