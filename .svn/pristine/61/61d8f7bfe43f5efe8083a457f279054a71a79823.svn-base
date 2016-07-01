<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var dictSpecimenTable;
	var tableSize ;
	$(function(){
		dictSpecimenTable=$('#dictSpecimenTable').datagrid({
			url : sybp()+'/dictSpecimenAction_loadList.action',
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
				title:'',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'标本类型',
				field:'specKind',
				width:150
			},{
				title:'标本种类',
				field:'specType',
				width:150,
				formatter: function(value,row,index){
				if ( value == 1){
					return "血液";
				}else if(value == 2){
                     return "尿液";
				}else if(value == 3){
                     return "其他";
				}}
			}]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editSpcButton').linkbutton('enable');
				$('#delSpcrButton').linkbutton('enable');
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
				$('#editSpcButton').linkbutton('disable');
				$('#delSpcrButton').linkbutton('disable');
				$('#upToolbarButton').linkbutton('disable');
				$('#downToolbarButton').linkbutton('disable');
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
			}
		}); //end datagrid
		
	});
	//新增
	function onaddSpcrButton(){
		window.location.href=sybp()+'/dictSpecimenAction_addUI.action';
	}
	//编辑
	function oneditSpcButton(){
		var rows = dictSpecimenTable.datagrid('getSelections');
		if(rows.length == 1){
			window.location.href=sybp()+'/dictSpecimenAction_editUI.action?id='+rows[0].id;
		}else if(rows.length == 0){
			parent.showMessager(2,'请选择编辑行',true,5000);
		}
	}
	//删除
	function ondelSpcrButton(){
		var rows = dictSpecimenTable.datagrid('getSelections');
		if(rows.length == 1){
			//window.location.href=sybp()+'/dictTestItemTypeAction_delete.action?tiCode='+rows[0].tiCode;
			$.messager.confirm('提示', '确定删除\' '+rows[0].specKind+'\' ?', function(r){
				if (r){
					$.ajax({
						url:sybp()+'/dictSpecimenAction_delete.action',
						type:'post',
						data:{
							id:rows[0].id
						},
						dataType:'json',
						success:function(r){
							if(r && r.success){
								//获得行索引
								var index =dictSpecimenTable.datagrid('getRowIndex',rows[0]);
								//删除行
								dictSpecimenTable.datagrid('deleteRow',index);
								//禁用按钮（编辑，删除）
								$('#editSpcButton').linkbutton('disable');
								$('#delSpcrButton').linkbutton('disable');
								parent.showMessager(1,r.msg,true,5000);
							}else{
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
	
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">标本类型</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="dictSpecimenTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addSpcrButton"  class="easyui-linkbutton" onclick="onaddSpcrButton();" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editSpcButton" class="easyui-linkbutton" onclick="oneditSpcButton();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delSpcrButton" class="easyui-linkbutton" onclick="ondelSpcrButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
		</div>
	</div>
</body>
</html>