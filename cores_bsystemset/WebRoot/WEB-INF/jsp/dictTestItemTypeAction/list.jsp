<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var dictTestItemTypeTable;
	$(function(){
		dictTestItemTypeTable=$('#dictTestItemTypeTable').datagrid({
			url : sybp()+'/dictTestItemTypeAction_loadList.action',
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
			border:false,
			columns :[[{
				title : '供试品类型代码',
				field : 'tiCode',
				width : 150
			},{
				title:'供试品类型名称',
				field:'tiType',
				width:150
			}]],
			//工具栏
			toolbar:[{
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					window.location.href=sybp()+'/dictTestItemTypeAction_addUI.action';
				}
			},{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					var rows = dictTestItemTypeTable.datagrid('getSelections');
					if(rows.length == 1){
						window.location.href=sybp()+'/dictTestItemTypeAction_editUI.action?tiCode='+rows[0].tiCode+"&tiType="+rows[0].tiType;
					}else if(rows.length == 0){
						//$.messager.alert('提示','请选择编辑行');
						parent.showMessager(2,'请选择编辑行',true,5000);
					}
				}
			},{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var rows = dictTestItemTypeTable.datagrid('getSelections');
					if(rows.length == 1){
						//window.location.href=sybp()+'/dictTestItemTypeAction_delete.action?tiCode='+rows[0].tiCode;
						$.messager.confirm('提示', '确定删除 '+rows[0].tiType+' ?', function(r){
							if (r){
								$.ajax({
									url:sybp()+'/dictTestItemTypeAction_delete.action',
									type:'post',
									data:{
										tiCode:rows[0].tiCode
									},
									dataType:'json',
									success:function(r){
										if(r && r.success){
											//删除行
											var index =dictTestItemTypeTable.datagrid('getRowIndex',rows[0]);
											dictTestItemTypeTable.datagrid('deleteRow',index);
											/*$.messager.show({
												title : '提示',
												msg : r.msg,
												timeout:2000
											});*/
											parent.showMessager(1,r.msg,true,5000);
										}else{
											//$.messager.alert('提示',r.msg);
											parent.showMessager(3,r.msg,true,5000);
										}
									}
								});
							}
						});
					}else if(rows.length == 0){
						parent.showMessager(2,'请选择删除行',true,5000);
					}
												
				}
			}],
			//保存（编辑或增加后的保存）
			onClickRow : function(rowIndex,rowData){
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
			},
			onDblClickCell:function(rowIndex,field,value){
				$(this).datagrid('unselectAll');
			}
		}); //end datagrid
		//表头居中
		$('.datagrid-header div').css('textAlign','center');
	});
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">供试品类型</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="dictTestItemTypeTable" ></table>
		</div>
		<!-- table end -->
	</div>
</body>
</html>