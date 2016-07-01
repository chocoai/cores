<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var testIndexTable;
	var tableSize ;
	$(function(){
		testIndexTable=$('#testIndexTable').datagrid({
			url : sybp()+'/dictBioChemAction_loadList.action',
			title:'',
			height: 520,
			//width:560,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			//pageSize:50,
			//pageList:[50,100],
			//fit:true,
			//fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			border:false,
			columns :[[{
				title : '序号',
				field : 'orderNo',
				width : 50
			},{
				title:'名称',
				field:'name',
				width:150
			},{
				title:'缩写',
				field:'abbr',
				width:150
			},{
				title:'精度',
				field:'precision',
				width:150
			},{
				title:'单位',
				field:'unit',
				width:150
			}]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('enable');
				$('#delToolbarButton').linkbutton('enable');
				if(rowIndex!=0){
					$('#upToolbarButton').linkbutton('enable');
				}else{
					$('#upToolbarButton').linkbutton('disable');
				}
				if(rowIndex != tableSize-1){
					$('#downToolbarButton').linkbutton('enable');
				}else{
					$('#downToolbarButton').linkbutton('disable');
				}
			},
			onUnselect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('disable');
				$('#delToolbarButton').linkbutton('disable');
				$('#upToolbarButton').linkbutton('disable');
				$('#downToolbarButton').linkbutton('disable');
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
			}
		}); //end datagrid
		
	});
	//新增
	function onAddToolbarButton(){
		window.location.href=sybp()+'/dictBioChemAction_addUI.action';
	}
	//编辑
	function onEditToolbarButton(){
		var rows = testIndexTable.datagrid('getSelections');
		if(rows.length == 1){
			$.ajax({
				url:sybp()+'/dictBioChemAction_checkIsExist.action',
				type:'post',
				data:{
					abbr:rows[0].abbr
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						window.location.href=sybp()+'/dictBioChemAction_editUI.action?name='+rows[0].name;
					}else{
						parent.showMessager(2,r.msg,true,5000);
					}
				}
				});
		}else if(rows.length == 0){
			parent.showMessager(2,'请选择编辑行',true,5000);
		}
	}
	//删除
	function onDelToolbarButton(){
		var rows = testIndexTable.datagrid('getSelections');
		if(rows.length == 1){
			$.ajax({
				url:sybp()+'/dictBioChemAction_checkIsExist.action',
				type:'post',
				data:{
					abbr:rows[0].abbr
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						$.messager.confirm('提示', '确定删除\' '+rows[0].name+'\' ?', function(r){
							if (r){
								$.ajax({
									url:sybp()+'/dictBioChemAction_delete.action',
									type:'post',
									data:{
										name:rows[0].name
									},
									dataType:'json',
									success:function(r){
										if(r && r.success){
											//获得行索引
											var index =testIndexTable.datagrid('getRowIndex',rows[0]);
											//删除行
											testIndexTable.datagrid('deleteRow',index);
											//禁用按钮（编辑，删除，上移，下移）
											$('#editToolbarButton').linkbutton('disable');
											$('#delToolbarButton').linkbutton('disable');
											$('#upToolbarButton').linkbutton('disable');
											$('#downToolbarButton').linkbutton('disable');
											parent.showMessager(1,r.msg,true,5000);
										}else{
											parent.showMessager(3,r.msg,true,5000);
										}
									}
								});
							}
						});
					}else{
						parent.showMessager(2,r.msg,true,5000);
					}
				}
				});
		}else if(rows.length == 0){
			parent.showMessager(2,'请选择删除行',true,5000);
		}
	}
	//上移
	function onUpToolbarButton(){
		var rows = testIndexTable.datagrid('getSelections');
		var rowIndex ;
		if(rows.length == 1){
			//获得行索引
			rowIndex =testIndexTable.datagrid('getRowIndex',rows[0]);
			if(rowIndex > 0){
				//下一个数据
				var nextRowData = testIndexTable.datagrid('getRows')[rowIndex-1];
				var currentRowData=rows[0];
				$.ajax({
					url:sybp()+'/dictBioChemAction_moveOrder.action',
					type:'post',
					data:{
						orderNoPara:currentRowData.orderNo,
						orderNoNext:nextRowData.orderNo
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							testIndexTable.datagrid('updateRow',{
					 			index: rowIndex-1,
					 			row: r.nextRow
					 		});
							testIndexTable.datagrid('updateRow',{
					 			index: rowIndex,
					 			row: r.currentRow
					 		});
							testIndexTable.datagrid('selectRow',rowIndex-1);
							parent.showTopMessager(r.msg,true,5000);
						}else{
							parent.showTopMessager(r.msg,true,5000);
						}
					}
				});
			}else{
				parent.showMessager(2,'已经是第一行，无法再移',true,5000);
			}
		}else{
			parent.showMessager(2,'请先选择行',true,5000);
		}
	}
	//下移
	function onDownToolbarButton(){
		var rows = testIndexTable.datagrid('getSelections');
		var rowIndex ;
		if(rows.length == 1){
			//获得行索引
			rowIndex =testIndexTable.datagrid('getRowIndex',rows[0]);
			if(rowIndex < tableSize-1){
				//下一个数据
				var nextRowData = testIndexTable.datagrid('getRows')[rowIndex+1];
				var currentRowData=rows[0];
				$.ajax({
					url:sybp()+'/dictBioChemAction_moveOrder.action',
					type:'post',
					data:{
						orderNoPara:currentRowData.orderNo,
						orderNoNext:nextRowData.orderNo
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							testIndexTable.datagrid('updateRow',{
					 			index: rowIndex+1,
					 			row: r.nextRow
					 		});
							testIndexTable.datagrid('updateRow',{
					 			index: rowIndex,
					 			row: r.currentRow
					 		});
							testIndexTable.datagrid('selectRow',rowIndex+1);
							parent.showTopMessager(r.msg,true,5000);
						}else{
							parent.showTopMessager(r.msg,true,5000);
						}
					}
				});
			}else{
				parent.showMessager(2,'已经是最后一行，无法再移',true,5000);
			}
		}else{
			parent.showMessager(2,'请先选择行',true,5000);
		}
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">生化指标</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="testIndexTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addToolbarButton"  class="easyui-linkbutton" onclick="onAddToolbarButton();" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="onEditToolbarButton();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delToolbarButton" class="easyui-linkbutton" onclick="onDelToolbarButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
			<a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">上移</a>
			<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownToolbarButton();" data-options="iconCls:'icon-redo',disabled:true,plain:true">下移</a>
		</div>
	</div>
</body>
</html>