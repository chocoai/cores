<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var studyTypeTable;
	var tableSize ;
	$(function(){
		studyTypeTable=$('#studyTypeTable').datagrid({
			url : sybp()+'/dictStudyTypeAction_loadList.action',
			title:'',
			height: 520,
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			border:false,
			columns :[[{
				title : '供试品类型代码',
				field : 'tiCode',
				width : 100
			},{
				title:'专题类别编码',
				field:'studyTypeCode',
				width:150
			},{
				title:'专题名称',
				field:'studyName',
				width:150
			},{
				title:'专题周期',
				field:'studyPeriod',
				width:150,
				formatter : function(value,rowData,rowIndex){
				return value==0 ? '':value;
			}
			},{
				title:'专题周期单位',
				field:'studyPeriodUnit',
				width:150
			}]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('enable');
				$('#indexToolbarButton').linkbutton('enable');
				
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
			}
		}); //end datagrid
		
	});
	//新增
	function onAddToolbarButton(){
		window.location.href=sybp()+'/dictStudyTypeAction_addUI.action';
	}
	//编辑
	function onEditToolbarButton(){
		var rows = studyTypeTable.datagrid('getSelections');
		if(rows.length == 1){
			window.location.href=sybp()+'/dictStudyTypeAction_editUI.action?studyTypeCode='+rows[0].studyTypeCode;
		}else if(rows.length == 0){
			parent.showMessager(2,'请选择编辑行',true,5000);
		}
	}
	//专题缺省检验指标
	function onIndexToolbarButton(){
		var rows = studyTypeTable.datagrid('getSelections');
		if(rows.length == 1){
			window.location.href=sybp()+'/dictStudyTestIndexAction_editUI.action?studyTypeCode='+rows[0].studyTypeCode;
					
		}else if(rows.length == 0){
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
		          <li class="active"><span><span><a href="javascript:void(0);">专题类别</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="studyTypeTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addToolbarButton"  class="easyui-linkbutton" onclick="onAddToolbarButton();" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="onEditToolbarButton();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="indexToolbarButton" class="easyui-linkbutton" onclick="onIndexToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">专题缺省检验指标</a>
		</div>
	</div>
</body>
</html>