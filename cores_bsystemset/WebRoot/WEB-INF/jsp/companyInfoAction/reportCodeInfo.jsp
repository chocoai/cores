<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报告编号设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>

<script type="text/javascript">
	var dictReportCodeTable;
	var tableSize ;
	$(function(){
		var tableHeight = document.body.clientHeight-30;
		dictReportCodeTable=$('#dictReportCodeTable').datagrid({
			url : sybp()+'/companyInfoAction_loadReportCodeList.action',
			title:'',
			height: tableHeight,
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			idField:'id',
			border:false,
			columns :[[
			{
				title:'id',
				field:'id',
				width:100,
				hidden:true,
				
									
			},
			{
				title:'报告名称',
				field:'reportName',
				width:100,
				
				
			},
			{
				title:'报告编号',
				field:'number',
				width:150
			},]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('enable');
				//$('#delToolbarButton').linkbutton('enable');
				
			},
			onUnselect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('disable');
				//$('#delToolbarButton').linkbutton('disable');
			
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
			}
		}); //end datagrid
		
	});
		
	//编辑
	function editOneReportCode(){
		var row = dictReportCodeTable.datagrid('getSelected');
		if(row!=null&&row!=''){
			/* 显示Dialog */
			document.getElementById("editReportCodeDialog2").style.display="block";
			
			$('#number').val(row.number);
			$('#reportName').val(row.reportName);
			$('#oneId').val(row.id);
				
			$('#editReportCodeDialog').dialog('setTitle','编辑报告编号');
			$('#editReportCodeDialog').dialog('open'); 
		
		}else{
			parent.showMessager(2,'请选择编辑行',true,5000);
		}
		
	}
	
	
	
	function saveOneReportCode()
	{
		var row = dictReportCodeTable.datagrid('getSelected');
		var number =	$('#number').val();
		if(number=='')
		{
			$.messager.alert("提示框","报告编号不能为空");
		}else if(number==row.number){
			$.messager.alert("提示框","报告编号没有改动");
		}else{
			writeChangeReason();
		}
	}
	
	function writeChangeReason()
	{
		$.messager.prompt('提示信息', '确认要修改该类报告的报告编码吗？<br/>请填写修改原因：', function(r){
			if (r){
			     if(r != ""){
			        var reason = r;
			        $('#reason').val(reason);
			        qm_showQianmingDialog('afterSignSaveOneReportCode');
			     }
			}else{
			         if(r == ""){
			              $.messager.defaults = { ok: "是", cancel: "否" }; 
			              $.messager.confirm('确认对话框', '请填写修改原因，是否继续执行修改操作？', function(r){
							  if (r){
							      writeChangeReason();
							  }
						  }); 
			         } 
			     }
		});
 		$.messager.defaults = { ok: "确定", cancel: "取消" }; 
 	
	}
	function afterSignSaveOneReportCode()
	{
		var row = dictReportCodeTable.datagrid('getSelected');
		var index = dictReportCodeTable.datagrid('getRowIndex',row);
							
			
			var url = sybp()+'/companyInfoAction_saveReportCode.action';
			
			$.ajax({
				url:url,
				type:'post',
				data:$('#reportCodeEditForm').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
							dictReportCodeTable.datagrid('updateRow',{
								index:index,
								row:{
									number: r.number,
								}
							});
							dictReportCodeTable.datagrid('selectRow',index);
							$('#editReportCodeDialog').dialog('close');
						
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
		          <li class="active"><span><span><a href="javascript:void(0);">报告编号设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="dictReportCodeTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="editOneReportCode();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			
			<!-- 
			<a id="addToolbarButton"  class="easyui-linkbutton" onclick="addOneReportCode(1);" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="delToolbarButton" class="easyui-linkbutton" onclick="onDelToolbarButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
			<a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">上移</a>
			<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownToolbarButton();" data-options="iconCls:'icon-redo',disabled:true,plain:true">下移</a>
			 -->
		</div>
		<%@include file="/WEB-INF/jsp/companyInfoAction/editReportCode.jspf" %>
		<%@include file="/WEB-INF/jsp/public/qianming.jspf" %>
	</div>
</body>
</html>