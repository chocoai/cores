<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合分类设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>

<script type="text/javascript">
	var dictAdministrationTypeTable;
	var tableSize ;
	$(function(){
		var tableHeight = document.body.clientHeight-30;
		dictAdministrationTypeTable=$('#dictAdministrationTypeTable').datagrid({
			url : sybp()+'/dictAdministrationTypeAction_loadList.action',
			title:'',
			height: tableHeight,
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			idField:'archiveTypeCode',
			border:false,
			columns :[[
			{
				title:'类别代码',
				field:'docTypeFlag',
				width:100
			},
			{
				title:'类别名称',
				field:'docTypeName',
				width:150
			},]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('enable');
				$('#delToolbarButton').linkbutton('enable');
				
			},
			onUnselect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('disable');
				$('#delToolbarButton').linkbutton('disable');
			
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
			}
		}); //end datagrid
		
	});
	//新增
	function addOneAdministration(addOrEdit){
		$('#addOrEdit').val(addOrEdit);
		if(addOrEdit==1)//新增
		{
			/* 显示Dialog */
			document.getElementById("addAdministrationDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddCheckBox').css('display','');
			
			
			$('#oneAdministrationTypeName').val('');
			$('#oneAdministrationTypeCode').val('');
			$('#oldAdministrationTypeCode').val('');
			$('#oneAdministrationTypeCode').attr('readOnly',false);
			
			
			$('#addAdministrationDialog').dialog('setTitle','添加综合类别');
			$('#addAdministrationDialog').dialog('open'); 
		}else if(addOrEdit==2){
			var row = dictAdministrationTypeTable.datagrid('getSelected');
			if(row!=null&&row!=''){
				/* 显示Dialog */
				document.getElementById("addAdministrationDialog2").style.display="block";
				document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
				$('#continueAddCheckBox').css('display','none');
				
				
				$('#oneAdministrationTypeName').val(row.docTypeName);
				$('#oneAdministrationTypeCode').val(row.docTypeFlag);
				$('#oneAdministrationTypeCode').attr('readOnly',true);
				
				$('#oldAdministrationTypeCode').val(row.docTypeFlag);//用于后台更新的时候查找原记录
				
				$('#addAdministrationDialog').dialog('setTitle','编辑综合类别');
				$('#addAdministrationDialog').dialog('open'); 
						
			}else{
				parent.showMessager(2,'请选择编辑行',true,5000);
			}
			
		}
		
	}
	
	function saveOneAdministration()
	{
		var oneArchiveTypeName = $('#oneAdministrationTypeName').val();
		var oneArchiveTypeCode = $('#oneAdministrationTypeCode').val();
		if(oneArchiveTypeName==''||oneArchiveTypeCode=='')
		{
			$.messager.alert("提示框","类型名和类型代码都不能为空");
		}else{
		
			var addOrEdit = $('#addOrEdit').val();
			var url = sybp()+'/dictAdministrationTypeAction_save.action';
			if(addOrEdit==1)
			{
				url = sybp()+'/dictAdministrationTypeAction_save.action';
			}else if(addOrEdit==2)
			{
				url = sybp()+'/dictAdministrationTypeAction_update.action';
			}
			$.ajax({
				url:url,
				type:'post',
				data:$('#administrationAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						//获得行索引
						if(addOrEdit==1)
						{
							dictAdministrationTypeTable.datagrid('appendRow',{
								docTypeFlag: r.docTypeFlag,
								docTypeName: r.docTypeName,
								
							});
							dictAdministrationTypeTable.datagrid('selectRecord',r.docTypeFlag);
							if($('#continueAddCheckBox').attr('checked')!='checked')
							{
								$('#addAdministrationDialog').dialog('close');
							}else{
								//$('#oneArchiveTypeFlag').combobox('setValue','1');
								$('#oneAdministrationTypeName').val('');
								$('#oneAdministrationTypeCode').val('');
								$('#oldAdministrationTypeCode').val('');
							}
						}else if(addOrEdit==2){
							var row = dictAdministrationTypeTable.datagrid('getSelected');
							var index = dictAdministrationTypeTable.datagrid('getRowIndex',row);
							dictAdministrationTypeTable.datagrid('updateRow',{
								index:index,
								row:{
									docTypeName: r.docTypeName,
									docTypeFlag: r.docTypeFlag,
								}
							});
							dictAdministrationTypeTable.datagrid('selectRow',index);
							$('#addAdministrationDialog').dialog('close');
						}
						parent.showMessager(1,r.msg,true,5000);
					}else{
						parent.showMessager(3,r.msg,true,5000);
					}
				}
			});
			
		}
		
	}
	//删除
	function onDelToolbarButton(){
		var row = dictAdministrationTypeTable.datagrid('getSelected');
		if(row!=null&&row!=''){
			
			$.messager.confirm('提示', '确定删除\' '+row.docTypeName+'\' ?', function(r){
				if (r){
						qm_showQianmingDialog('afterSignDelAdministrationType');
							
					}else{
						parent.showMessager(2,r.msg,true,5000);
					}
				
				});
		}else{
			parent.showMessager(2,'请选择删除行',true,5000);
		}
	}
	function afterSignDelAdministrationType()
	{
		var row = dictAdministrationTypeTable.datagrid('getSelected');
		$.ajax({
			url:sybp()+'/dictAdministrationTypeAction_delete.action',
			type:'post',
			data:{
				docTypeFlag:row.docTypeFlag,
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//获得行索引
					var index = dictAdministrationTypeTable.datagrid('getRowIndex',row);
					//删除行
					dictAdministrationTypeTable.datagrid('deleteRow',index);
					//禁用按钮（编辑，删除）
					$('#editToolbarButton').linkbutton('disable');
					$('#delToolbarButton').linkbutton('disable');
							
					parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.showMessager(3,r.msg,true,5000);
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">综合类别设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="dictAdministrationTypeTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addToolbarButton"  class="easyui-linkbutton" onclick="addOneAdministration(1);" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="addOneAdministration(2);" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delToolbarButton" class="easyui-linkbutton" onclick="onDelToolbarButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
			<!-- 
			<a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">上移</a>
			<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownToolbarButton();" data-options="iconCls:'icon-redo',disabled:true,plain:true">下移</a>
			 -->
		</div>
		<%@include file="/WEB-INF/jsp/dictAdministrationTypeAction/addAdministration.jspf" %>
		<%@include file="/WEB-INF/jsp/public/qianming.jspf" %>
	</div>
</body>
</html>