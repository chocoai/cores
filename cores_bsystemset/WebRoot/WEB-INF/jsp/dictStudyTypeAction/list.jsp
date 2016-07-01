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
	var tableHeight ; //datagrid 高度
	$(function(){
		tableHeight = document.body.clientHeight - 30; 
		studyTypeTable=$('#studyTypeTable').datagrid({
			url : sybp()+'/dictStudyTypeAction_loadList.action',
			title:'',
			height: tableHeight,
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			border:false,
			idField:'studyTypeCode',
			columns :[[{
				title : '供试品类型代码',
				field : 'tiCode',
				width : 100,
				formatter: function(value,row,index){
					if (value == '01'){
						return '医药';
					} else if (value == '02'){
						return '农药';
					}else{
						return '化学品';
					}
				}
			},{
				title:'专题类别编码',
				field:'studyTypeCode',
				width:100
			},{
				title:'试验名称',
				field:'studyName',
				width:250
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
			},{
			   title:'代号',
			   field:'studyCode',
			   width:120
			},{
				title:'专题周期',
				field:'studyPeriod',
				width:150,
				formatter : function(value,rowData,rowIndex){
				return value==0 ? '':value;
			}
			},{
				title:'专题名称包含动物',
				field:'animalHave',
				width:110,
				formatter : function(value,rowData,rowIndex){
				   if(value == "0"){
				       return "否";
				   }else{
				       return "是";
				   }
			    }
			}]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('enable');
				$('#indexToolbarButton').linkbutton('enable');
				$('#nodeToolbarButton').linkbutton('enable');
				
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
				$('#studyTypeTable').datagrid('selectRecord',$('#studyTypeCode').val());
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
	//专题进度节点设置
	function onNodeToolbarButton(){
		var rows = studyTypeTable.datagrid('getSelections');
		if(rows.length == 1){
			window.location.href=sybp()+'/tblStudyScheduleNodeAction_list.action?studyTypeCode='+rows[0].studyTypeCode;
		}else if(rows.length == 0){
			parent.showMessager(2,'请先选择行',true,5000);
		}
	}
</script>
</head>
<body>
	<s:hidden id = "studyTypeCode" name="studyTypeCode"></s:hidden>
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
			<a id="indexToolbarButton" class="easyui-linkbutton" onclick="onIndexToolbarButton();" data-options="iconCls:'icon-table',disabled:true,plain:true">专题缺省检验指标</a>
			<a id="nodeToolbarButton" class="easyui-linkbutton" onclick="onNodeToolbarButton();" data-options="iconCls:'icon-list',disabled:true,plain:true">专题进度节点设置</a>
		</div>
	</div>
</body>
</html>