<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
  
</style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
function addStudyGroup(addOrEdit)
{
	if(addOrEdit==0)//新增
	{
		/* 显示Dialog */
		document.getElementById("addOrEditStudyGroupDialog2").style.display="block";
		document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
		$('#continueAddButton').css('display','');
		
		
		$('#updateStudyGroupID').val('');
		$('#studyGroupName').val('');
		$('#addOrEditStudyGroupDialog').dialog('setTitle','添加类别');
		$('#addOrEditStudyGroupDialog').dialog('open'); 
	}
	else if(addOrEdit==1)//编辑
	{
		/* 显示Dialog */
		document.getElementById("addOrEditStudyGroupDialog2").style.display="block";
		document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
		$('#continueAddButton').css('display','none');
		var row=$('#studyGroups').datagrid("getSelected");
		if(row!=null&&row!=''&&row!='undified')
		{
			$('#updateStudyGroupID').val(row.studyGroupId);
			$('#studyGroupName').val(row.studyGroupName);
			
			$('#addOrEditStudyGroupDialog').dialog('setTitle','编辑类别');
			$('#addOrEditStudyGroupDialog').dialog('open'); 
		}else {
			$.messager.alert("提示","请选择一个类别！");
		}
	}
	
}
function delStudyGroup()
{
	
	var row=$('#studyGroups').datagrid("getSelected");
	if(row!=null&&row!=''&&row!='undified')
	{
		$.messager.confirm('确认框','删除类别会同时解除试验和类别的关系，以及解除检查项和类别的关系，确定要删除该类别吗?',function(r){
		    if (r){
		    	qm_showQianmingDialog('afterSignDelStudyGroup');
		    	
		    }
		});
		
		
	}else {
		$.messager.alert("提示","请选择一个类别！");
	}
}
function afterSignDelStudyGroup()
{
	var row=$('#studyGroups').datagrid("getSelected");
	var index = $('#studyGroups').datagrid("getRowIndex",row);
	//如果需要前面加在这里，
	$.ajax({
		url:sybp()+'/dictStudyGroupAction_del.action',
		type:'post',
		data:{
			studyGroupId:row.studyGroupId
		},
		dataType:'json',
		success:function(){
			$('#saveDialogButton').linkbutton('enable');
		}
	});
	$('#studyGroups').datagrid('deleteRow',index);
	$('#experiments').datagrid('loadData',[]);
}

function saveOrUpdateOneStudyGroup()
{
	 var studyGroupName=$('#studyGroupName').val();
	 var updateStudyGroupID = $('#updateStudyGroupID').val();
	 
	
	 if(studyGroupName=="")
	 {
		 $.messager.alert("提示框","请输入试验类别名称！");
	 }else if( updateStudyGroupID=="" ){//新增
			$.ajax({
				url:sybp()+'/dictStudyGroupAction_addSave.action',
				type:'post',
				data:$('#oneStudyGroup').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$('#studyGroups').datagrid('appendRow',{
							studyGroupId:r.studyGroupId,
							studyGroupName:r.studyGroupName

						});
						$('#studyGroups').datagrid('selectRecord',r.studyGroupId);
					}
					else if(r && !r.success){
						$.messager.alert('提示',r.msg);
						
					}
				}
			});
			if($('#continueAddButton').attr('checked')!='checked')
			{
				 $('#addOrEditStudyGroupDialog').dialog('close');
			}else {
				$('#studyGroupName').val('');
			}
		
			
		}else{ //编辑
			
			var row=$('#studyGroups').datagrid("getSelected");
			var index = $('#studyGroups').datagrid("getRowIndex",row);
			$.ajax({
				url:sybp()+'/dictStudyGroupAction_editSave.action',
				type:'post',
				data:$('#oneStudyGroup').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$('#studyGroups').datagrid('updateRow',{
							index: index,
							row: {
								studyGroupId:r.studyGroupId,
								studyGroupName:r.studyGroupName
							}
							

						});
					}
					else if(r && !r.success){
						$.messager.alert('提示',r.msg);
						
					}
				}
			});
			 $('#addOrEditStudyGroupDialog').dialog('close');
			//$('#studyGroups').datagrid('reload');
			/*$('#studyGroups').datagrid({
				url : sybp()+"/dictStudyGroupAction_loadList.action",
			});*/
		}
	 
		
	
}
//添加试验
function selectStudyType()
{
	var row=$('#studyGroups').datagrid("getSelected");
	if(row!=null&&row!=''&&row!='undified')
	{
		document.getElementById("addGroupAndTypeDialog2").style.display="block";
		$('#tiTypeLabel').combobox({
			 url: sybp()+'/dictTestItemTypeAction_loadComboboxList.action',
			 valueField: 'id',//tiCode
		     textField: 'text',//tiType
			 onChange:function(newValue, oldValue){
				searchStudyTypeList();
		   	}
		});
		$('#tiTypeLabel').combobox('setValue','');
		$('#studyNameLabel').searchbox({ 
		     height:24,
		     width:270,
			 searcher:function(value,name){ 
				searchStudyTypeList();
			},
			prompt:'模糊查询,请输入试验名称',
		});
		$('#studyNameLabel').searchbox('setValue','');
		
		$('#studyTypeTable').datagrid({
			url : sybp()+"/dictStudyTypeAction_loadListExceptStudyGroupId.action?studyGroupId="+row.studyGroupId,
			singleSelect:false,
		});
		$('#studyTypeTable').datagrid('reload');
		$('#addGroupAndTypeDialog').dialog('open');
	}else {
		$.messager.alert("提示","请选择一个类别！");
	}
	
}
function searchStudyTypeList()
{
	var row=$('#studyGroups').datagrid("getSelected");
	if(row!=null&&row!=''&&row!='undified')
	{
		var tiTypeLabel = $('#tiTypeLabel').combobox('getValue');
		var studyNameLabel = $('#studyNameLabel').searchbox('getValue');
		$.ajax({
			url : sybp()+"/dictStudyTypeAction_loadListExceptStudyGroupId.action?studyGroupId="+row.studyGroupId,
			type:'post',
			data:{
				tiCode:tiTypeLabel,
				studyName:studyNameLabel,
			},
			dataType:'json',
			success:function(r){
				$('#studyTypeTable').datagrid('loadData',r);
			}
		});
		/*
		$('#studyTypeTable').datagrid({
			url : sybp()+"/dictStudyTypeAction_loadListExceptStudyGroupId.action?studyGroupId="+row.studyGroupId+"&tiCode="+tiTypeLabel+"&studyName="+studyNameLabel,
			singleSelect:false,
		});*/
		//$('#studyTypeTable').datagrid('reload');
		
	}
	
}

function saveGroupAndTypeButton()
{
	var row=$('#studyGroups').datagrid("getSelected");

	var rows = $('#studyTypeTable').datagrid("getSelections");
	var studyTypeCodes = new Array() ;
	for(var i=0;i<rows.length;i++)
	{
		studyTypeCodes.push(rows[i].studyTypeCode);
	}
	
	$.ajax({
		url:sybp()+'/dictStudyTypeAction_updateGroupIdByTypeCodes.action?studyTypeCodes='+encodeURIComponent(studyTypeCodes),
		type:'post',
		data:{
			studyGroupId:row.studyGroupId
		},
		dataType:'json',
		success:function(r){
			if(r&&r.success)
			{
				var index = $('#studyGroups').datagrid('getRowIndex',row);
				$('#studyGroups').datagrid('selectRow',index);
			}
			else if(r && !r.success)
			{
				$.messager.alert("提示",r.msg);
			}
		}
	});
	$('#addGroupAndTypeDialog').dialog('close');
	document.getElementById("oneStudyGroupName").innerHTML=row.studyGroupName+" 包括以下试验：";

	/*$('#experiments').datagrid({
		url : sybp()+"/dictStudyTypeAction_loadListByStudyGroupId.action?studyGroupId="+row.studyGroupId,
	});*/
	//$('#experiments').datagrid('reload');
}
function removeStudyType()
{
	var row=$('#studyGroups').datagrid("getSelected");
	
	var rows = $('#experiments').datagrid("getSelections");
	if(rows!=null&&rows!='')
	{
		$.messager.confirm('确认框','确定要移除选择的试验吗?',function(r){
		    if (r){
		    	var studyTypeCodes = new Array() ;
				for(var i=0;i<rows.length;i++)
				{
					studyTypeCodes.push(rows[i].studyTypeCode);
				}
				//alert(rows.length+"==="+studyTypeCodes);
				$.ajax({
					url:sybp()+'/dictStudyTypeAction_removeFromGroup.action?studyTypeCodes='+encodeURIComponent(studyTypeCodes),
					type:'post',
					data:{
						studyGroupId:row.studyGroupId
					},
					dataType:'json',
					success:function(r){
						if(r&&r.success)
						{
							var index = $('#studyGroups').datagrid('getRowIndex',row);
							$('#studyGroups').datagrid('selectRow',index);
						}
						else if(r && !r.success)
						{
							$.messager.alert("提示",r.msg);
						}
					}
				});
		    }
		});

	}else{
		$.messager.alert("提示","请选择一个试验");
	}
}

function changeCancelName()
{
	if($('#continueAddButton').attr('checked')=='checked')
	{
		$('#studyGroupCancelButton').linkbutton({
			text:'关闭'
	
		});
	}else {
		$('#studyGroupCancelButton').linkbutton({
			text:'取消'
	
		});
	}
}

</script>
<script type="text/javascript">
	$(function(){
		var tableSize ;
		var tableHeight = document.body.clientHeight - 100;   //datagrid 高度
		var tableWidth = document.body.clientWidth;

	
		$('#studyGroups').datagrid({
			url : sybp()+"/dictStudyGroupAction_loadList.action",
			title:'',
			iconCls:'',//'icon-save',
			singleSelect:true,//只能选一行
			pagination:false,//下面状态栏
			height:tableHeight,
			//width:tableWidth,  
			//fit:true,
			showHeader:false,//不显示头部
			fitColumns:false,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			border:false,
			idField:'studyGroupId', //pk
			//toolbar:'#toolbar',
			columns :[[
				{title :'',field :'studyGroupId',hidden:true}
				,{title:'',field :'studyGroupName',width:180,halign:'left'}
			
			]],
			
			onSelect:function(rowIndex, rowData){
				document.getElementById("oneStudyGroupName").innerHTML=rowData.studyGroupName+" 包括以下试验：";
				$('#experiments').datagrid({
					url : sybp()+"/dictStudyTypeAction_loadListByStudyGroupId.action?studyGroupId="+rowData.studyGroupId,
				});
				$('#experiments').datagrid('reload');
			}
		});
		var row=$('#studyGroups').datagrid("getSelected");
		
		$('#experiments').datagrid({
			width:351,
			height:tableHeight,
			columns:[[
			          {
				          field:'studyTypeCode',
				          title:'id',
				          hidden:true
				       },
			          {
				          field:'tiType',
				          title:'类别',
				          width:100
				       },
				       {
					       field:'studyName',
					       title:'试验名称',
					       width:230
					   }

			]],
		});
		if(row!=null&&row!=''&&row!='undified')
		{
			$('#experiments').datagrid({
				url : sybp()+"/dictStudyTypeAction_loadListByStudyGroupId.action?studyGroupId="+row.studyGroupId,
			});
			$('#experiments').datagrid('reload');
		}


	});

</script>
</head>
  
  <body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">试验分类设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800;">
				<a id="addStudyGroup"  class="easyui-linkbutton" onclick="addStudyGroup(0);" data-options="iconCls:'icon-add',plain:true">添加类别</a>
				<a id="editStudyGroup"  class="easyui-linkbutton" onclick="addStudyGroup(1);" data-options="iconCls:'icon-edit',plain:true">编辑类别</a>
				<a id="delStudyGroup" class="easyui-linkbutton" onclick="delStudyGroup();" data-options="iconCls:'icon-remove',plain:true">删除类别</a>
				<a id="selectStudyType" class="easyui-linkbutton" onclick="selectStudyType();" data-options="iconCls:'icon-add',plain:true">选择试验</a>
				<a id="removeStudyType" class="easyui-linkbutton" onclick="removeStudyType();" data-options="iconCls:'icon-remove',plain:true">移除试验</a>
				<!-- 
				<a id="selectResButton" class="easyui-linkbutton" onclick="selectResButton();" data-options="iconCls:'icon-house',plain:true">试验安置区域</a>
				 -->
			</div>
	          <span style=" position:absolute;top:30px;left:10px;width:200px;">试验类别：</span>	<br/>
			<div style=" position:absolute;top:50px;left:10px;bottom:22px;width:200px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="studyGroups" class="easyui-datagrid"></div>
			<%--
	          	 <ul class="studyGroups"  id="ul1">
					 <s:iterator value="#studyGroups" var="dictStudyGroup" status="status" >
		            	<li>
		            		<a id="${dictStudyGroup.studyGroupId}" onclick="javascript:selectOneGroup(this);">${dictStudyGroup.studyGroupName}</a>
		                			
		                 	<!-- 
		               		<input id="a${tblStudyPlan.studyNo}" type="hidden" value="${tblStudyPlan.studydirector}"/>
		               		 -->
		               	</li>
	            	</s:iterator>		
				</ul>
			--%>
			</div>
					
			<span id="oneStudyGroupName" style=" position:absolute;top:30px;left:250px;">包括以下试验：</span><br/>
			<div style=" position:absolute;top:50px;bottom:22px;left:250px;width:351px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="experiments" class="easyui-datagrid"></div>
			</div>
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictStudyGroupAction/addOrEditStudyGroup.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictStudyGroupAction/editGroupAndType.jspf" %>
  </body>
</html>
