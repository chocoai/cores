<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	function addDictChkArea(addOrEdit)
	{
		$('#oneParentAreaID').combotree('reload');
		
		if(addOrEdit==0)//新增
		{
			
	        $('#oneParentFileTypeID').combobox({
	           valueField: 'id',
	           textField: 'text',
	           url: sybp()+'/dictChkAreaAction_loadComboTreeList.action',
	        });
				
			/* 显示Dialog */
			document.getElementById("addOrEditDictChkAreaDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddButton').css('display','');
			
			
			$('#updateAreaID').val('');
			//$('#oneParentAreaID').combotree('setValue','');
			$('#oneAreaName').val('');
			
			$('#addOrEditDictChkAreaDialog').dialog('setTitle','添加区域');
			$('#addOrEditDictChkAreaDialog').dialog('open'); 
		}
		else if(addOrEdit==1)//编辑
		{
			/* 显示Dialog */
			document.getElementById("addOrEditDictChkAreaDialog2").style.display="block";
	
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var row=$('#dictChkAreaTree').tree("getSelected");
			if(row!=null)
			{
				$.ajax({
					url:sybp()+'/dictChkAreaAction_getParentById.action?areaID='+row.id,
					type:'post',
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							$('#oneParentAreaID').combotree('setValue',r.parentAreaID);
						}
					}
				});
				
				$('#updateAreaID').val(row.id);
				$('#oneAreaName').val(row.text);
			
				
				$('#addOrEditDictChkAreaDialog').dialog('setTitle','编辑区域');
				$('#addOrEditDictChkAreaDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个区域！");
			}
		}
		
	}
	function delDictChkArea()
	{
		var row=$('#dictChkAreaTree').tree("getSelected");
		
		if(row!=null)
		{
			//判断是否存在子文件类型
			$.ajax({
				url:sybp()+'/dictChkAreaAction_isExistChildArea.action?areaID='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){	
					if(r&&!r.isExistChild)
					{
						$.messager.confirm('确认框','确定要删除该区域吗?',function(r){
							if (r){
								qm_showQianmingDialog('afterSignDelDictChkArea');
							}
						});
					}else{
						$.messager.alert('提示框','该区域下还包含子区域，请先删除子区域');
					}
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个区域！");
		}
	}
	function afterSignDelDictChkArea()
	{
		var row=$('#dictChkAreaTree').tree("getSelected");
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/dictChkAreaAction_del.action?areaID='+row.id,
			type:'post',
			dataType:'json',
			success:function(){	
			}
		});
		 var node = $('#dictChkAreaTree').tree('find', row.id);
		 $('#dictChkAreaTree').tree('remove',node.target);
	
	}
	
	function saveOrUpdateOneDictChkArea()
	{
		 var oneParentAreaID=$('#oneParentAreaID').combotree("getValue");
		 var oneAreaName=$('#oneAreaName').val();
		 var updateAreaID = $('#updateAreaID').val();
		 
		 if(oneAreaName=='')
		 {
			 $.messager.alert("提示框","请填写区域名称！");
		 }else if( updateAreaID=="" ){//新增
				$.ajax({
					url:sybp()+'/dictChkAreaAction_save.action',
					type:'post',
					data:$('#oneDictChkAreaTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							//$('#QAFileTypes').tree("reload");
							var node;
							if(r.parentId!=null&&r.parentId!='')
							{
								node = $('#dictChkAreaTree').tree('find', r.parentId).target;
							}else
							{
								node = "";
							}
							$('#dictChkAreaTree').tree('append', {
								parent: node,
								data: [{
									id: r.id,
									text: r.text
								}]
								
							});
						}else if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
					}
				});
				if($('#continueAddButton').attr('checked')!='checked')
				{
					 $('#addOrEditDictChkAreaDialog').dialog('close');
				}else {
				
					$('#updateAreaID').val('');
					//$('#oneParentAreaID').combotree('setValue','');
					$('#oneAreaName').val('');
				}
				
				
			}else{ //编辑
				$.ajax({
					url:sybp()+'/dictChkAreaAction_editSave.action',
					type:'post',
					data:$('#oneDictChkAreaTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							//添加新的
							 	var node;
								if(r.parentId!=null)
								{
									node = $('#dictChkAreaTree').tree('find', r.parentId).target;
								}else
								{
									node = "";
								}
								var changeNode = $('#dictChkAreaTree').tree('find', r.id);
								$('#dictChkAreaTree').tree('update', {
									target: changeNode.target,
									text: r.text,
								});
								var nodeData = $('#dictChkAreaTree').tree('getData',changeNode.target);
								$('#dictChkAreaTree').tree('append', {
									parent: node,
									data: [nodeData],
									
								});
								//删除老的
								//var id=$('#updateFileTypeID').val();
								// var nodeDel = $('#QAFileTypes').tree('find',id);
								 $('#dictChkAreaTree').tree('remove',changeNode.target);
						}else if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
					}
				});
				
				 $('#addOrEditDictChkAreaDialog').dialog('close');
				// $('#QAFileTypes').tree("reload");
				
			}
		
		
		
	}
	function changeCancelName()
	{
		if($('#continueAddButton').attr("checked")=="checked")
		{
			$('#backButton').linkbutton({text:'关闭'});
		}else {
			$('#backButton').linkbutton({text:'取消'});
		}
	}
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		var tableSize ;
		var tableHeight = document.body.clientHeight - 80;   //datagrid 高度
		var tableWidth = document.body.clientWidth;
		
		$('#dictChkAreaTree').tree({
			url: sybp()+'/dictChkAreaAction_loadList.action',
			
		});
		$('#oneParentAreaID').combotree(
		{
			url: sybp()+'/dictChkAreaAction_loadComboTreeList.action',	
		});
		

	});

</script>
</head>
  
  <body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">检查区域</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800;">
				<a id="addDictChkArea"  class="easyui-linkbutton" onclick="addDictChkArea(0);" data-options="iconCls:'icon-add',plain:true">添加区域</a>
				<a id="editDictChkArea"  class="easyui-linkbutton" onclick="addDictChkArea(1);" data-options="iconCls:'icon-edit',plain:true">编辑区域</a>
				<a id="delDictChkArea" class="easyui-linkbutton" onclick="delDictChkArea();" data-options="iconCls:'icon-remove',plain:true">删除区域</a>
			</div>		
	        <div style=" position:absolute;top:30px;left:10px;bottom:22px;width:240px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="dictChkAreaTree" class="easyui-tree"></div>
			</div>
			
			
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictChkAreaAction/addOrEditChkArea.jspf"%>
		
  </body>
</html>
