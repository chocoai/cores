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
	function addSOPType(addOrEdit)
	{
		//$('#onepid').combotree('reload');
		$('#addOrEdit').val(addOrEdit);
		if(addOrEdit==1)//新增
		{
			/* 显示Dialog */
			document.getElementById("addOrEditSOPTypeDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddButton').css('display','');
			
			$('#onepid').combotree({
				valueField: 'id',
	    	    textField: 'text',
	    	    url: sybp()+'/dictSOPTypeAction_loadComboTree.action',
	    	    onLoadSuccess:function(node, data)
	    	    {
	    	    	
	        	},
	        	onChange:function(newValue, oldValue)
	        	{
		        	
	        	}
			});
			//$('#onepid').combotree('setText',0);
			$('#onesopname').val('');
			$('#onesoptypeCode').val('');
			$('#updateSOPTypeID').val('');
			
			$('#addOrEditSOPTypeDialog').dialog('setTitle','添加SOP类别');
			$('#addOrEditSOPTypeDialog').dialog('open'); 
		}
		else if(addOrEdit==2)//编辑
		{
			
			/* 显示Dialog */
			document.getElementById("addOrEditSOPTypeDialog2").style.display="block";
	
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var row=$('#SOPTypeTree').treegrid("getSelected");
			if(row!=null&&row!='')
			{
				$('#onepid').combotree({
					valueField: 'id',
		    	    textField: 'text',
		    	    url: sybp()+'/dictSOPTypeAction_loadComboTree.action?id='+row.id,
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
				
				$('#onesopname').val(row.text);
				$('#onesoptypeCode').val(row.sopTypeCode);
				$('#updateSOPTypeID').val(row.id);
		      
				$('#addOrEditSOPTypeDialog').dialog('setTitle','编辑SOP类别');
				$('#addOrEditSOPTypeDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个SOP类别！");
			}
		}
		
	}
	function delSOPType()
	{
		var row=$('#SOPTypeTree').treegrid("getSelected");
		
		if(row!=null&&row!='')
		{
			//判断是否存在子文件类型
			$.ajax({
				url:sybp()+'/dictSOPTypeAction_isExistChildSOPType.action?id='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){	
					if(r&&!r.isExist)
					{
						$.messager.confirm('确认框','确定要删除'+row.text+'吗?',function(r){
							if (r){
								qm_showQianmingDialog('afterSignDelSOPType');
							}
						});
					}else{
						$.messager.alert('提示框','该SOP类别下还包含子的SOP类别，请先删除子SOP类别');
					}
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个SOP类别！");
		}
	}
	function afterSignDelSOPType()
	{
		var row=$('#SOPTypeTree').treegrid("getSelected");
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/dictSOPTypeAction_delete.action?id='+row.id,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r && r.success){
					
					 var node = $('#SOPTypeTree').treegrid('find', row.id);
					 $('#SOPTypeTree').treegrid('remove',node.id);

					//禁用按钮（编辑，删除）
					$('#editSOPType').linkbutton('disable');
					$('#delSOPType').linkbutton('disable');
							
					parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.showMessager(3,r.msg,true,5000);
				}
			}
		});
		
	
	}
	
	function saveOrUpdateOneQAFileType()
	{
		var onesopname = $('#onesopname').val();
		var onesoptypeCode = $('#onesoptypeCode').val();
		//$("#oneSOPTypeTable").form('validate')
		if(onesoptypeCode==''||onesopname=='')
		{
			  $.messager.alert("提示框","类型名称和类型代码不能为空！");
		}else{
			 var addOrEdit = $('#addOrEdit').val();
		  	if( addOrEdit=='1' ){//新增
				$.ajax({
					url:sybp()+'/dictSOPTypeAction_save.action',
					type:'post',
					data:$('#oneSOPTypeTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							var node;
							if(r.pid!=null&&r.pid!='')
							{
								node = $('#SOPTypeTree').treegrid('find', r.pid);
								$('#SOPTypeTree').treegrid('append', {
									parent: node.id,
									data: [{
											id:r.id,
									        pid:r.pid,
									        text:r.sopName,
									        sopTypeCode:r.soptypeCode,
									}]
									
								});
								
							}else{
								
								$('#SOPTypeTree').treegrid('append', {
									data: [{
										id:r.id,
								        pid:r.pid,
								        text:r.sopName,
								        sopTypeCode:r.soptypeCode,
									}]
									
								});
							}
							parent.showMessager(1,r.msg,true,5000);
							
							if($('#continueAddButton').attr('checked')!='checked')
							{
								 $('#addOrEditSOPTypeDialog').dialog('close');
							}else {
								//$('#onepid').combotree('select',0);
								$('#SOPTypeTree').treegrid('reload');
								$('#onesopname').val('');
								$('#onesoptypeCode').val('');
								$('#updateSOPTypeID').val('');
							}
						}else if(r&&!r.success){
							parent.showMessager(3,r.msg,true,5000);
						}
					}
				});
				
				
				
			}else{ //编辑
				$.ajax({
					url:sybp()+'/dictSOPTypeAction_update.action',
					type:'post',
					data:$('#oneSOPTypeTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							//添加新的
							 	var node;
								if(r.pid!=null)
								{
									node = $('#SOPTypeTree').treegrid('find', r.pid);
								}
								
								 //添加新的
								var childNodes = $('#SOPTypeTree').treegrid('getChildren',r.id);
								//删除老的
								 $('#SOPTypeTree').treegrid('remove',r.id);
								if(r.pid!=null&&r.pid!='')
								{
									$('#SOPTypeTree').treegrid('append', {
										parent: node.id,
										data: [{
												id:r.id,
												pid:r.pid,
												text:r.sopName,
										        sopTypeCode:r.soptypeCode,
										        children:childNodes,
									        }],
									});
									
								}else{
									$('#SOPTypeTree').treegrid('append', {
										data: [{
											id:r.id,
											pid:r.pid,
											text:r.sopName,
									        sopTypeCode:r.soptypeCode,
									        children:childNodes,
								        }],
									});
								}
								
								 parent.showMessager(1,r.msg,true,5000);
								 $('#addOrEditSOPTypeDialog').dialog('close');
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
	function upOneSOPType()
	{
		var row = $('#SOPTypeTree').treegrid('getSelected');
		
		
	}
	//下移
	function downOneSOPType()
	{
		var row = $('#SOPTypeTree').treegrid('getSelected');
		
	}
	
	
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$('#wholeSopTypePage').css('display','');
		var tableSize ;
		var tableHeight = document.body.clientHeight-28;   //其他组件的高度
		var tableWidth = document.body.clientWidth-2;
	
	
		$('#SOPTypeTree').treegrid({
			height:tableHeight,
			width:tableWidth,
			url: sybp()+'/dictSOPTypeAction_loadTree.action',
		    idField:'id',
		    treeField:'text',
			toolbar:'#toolbar',
		    columns:[[
		        {title:'id',field:'id',width:180,hidden:true},
		        {title:'pid',field:'pid',width:180,hidden:true},
		        {title:'SOP类别名称',field:'text',width:250,state:'state',align:'left'},
		        {title:'代码',field:'sopTypeCode',width:100,align:'center'},
		    ]],
		    onSelect:function(rowIndex, rowData){
				$('#delSOPType').linkbutton('enable');
				$('#editSOPType').linkbutton('enable');
			
			},
			onUnselect:function(rowIndex, rowData){
				$('#delSOPType').linkbutton('disable');
				$('#editSOPType').linkbutton('disable');
			
			},
		});
		
		

	});//function结束





</script>
</head>
  
  <body>
  	
	<div id="wholeSopTypePage" class="easyui-layout" fit="true" border="false" style="display:none">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">SOP类别设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="SOPTypeTree" class="easyui-treegrid" ></div>
		</div>
		<div id="toolbar">
			<a id="addSOPType" class="easyui-linkbutton" onclick="addSOPType(1);" data-options="iconCls:'icon-add',plain:true">添加</a>
			<!-- 
			<a id="addSOPSonType" disabled class="easyui-linkbutton" onclick="addSOPSonType(0);" data-options="iconCls:'icon-add',plain:true">添加子一级</a>
			 -->
			<a id="editSOPType" disabled class="easyui-linkbutton" onclick="addSOPType(2);" data-options="iconCls:'icon-edit',plain:true">编辑</a>
			<a id="delSOPType" disabled class="easyui-linkbutton" onclick="delSOPType();" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<!-- 
			<a id="upOne" class="easyui-linkbutton" onclick="upOneSOPType();" data-options="plain:true,iconCls:'icon-undo'">上移</a>
			<a id="downOne" class="easyui-linkbutton" onclick="downOneSOPType();" data-options="plain:true,iconCls:'icon-redo'">下移</a>
			 -->
	
		</div>
		
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictSOPTypeAction/sopTypeAddEdit.jspf"%>
	</div>
	
  </body>
</html>