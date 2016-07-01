<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
	<script type="text/javascript">
		var tblLogTable;
		$(function(){
			tblLogTable=$('#tblLogTable').datagrid({
				url : sybp()+"/tblLogAction_loadList.action",
				title:'',
				iconCls:'',//'icon-save',
				singleSelect:true,//只能选一行
				pagination:false,//下面状态栏
				height:444,
				//fit:true,
				fitColumns:false,//不出现横向滚动条
				nowarp:  false,//单元格里自动换行
				border:false,
				idField:'id', //pk
				columns :[[{
					title :'',
					field :'id',
					hidden:true
				},{
					title : '操作类型',
					field : 'operatType',
					width : 100,
				},{
					title:'操作对象',
					field:'operatOject',
					width:100,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'操作者',
					field:'operator',
					width:100,
					//formartter 一定要返回个字符串
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'操作时间',
					field:'operatTime',
					width:100,
					//formartter 一定要返回个字符串
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'操作内容',
					field:'operatContent',
					width:230,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'操作位置',
					field:'operatHost',
					width:170,
					hidden:true
					
				},{
					title:'备注',
					field:'remark',
					width:150,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				}]]
			});
		});
		/**查询按钮方法*/
		function onSelectButton(){
			if($('#selectForm').form('validate')){
				$.ajax({
					url:sybp()+'/tblLogAction_loadList.action',
					type:'post',
					data:$('#selectForm').serialize(),
					dataType:'json',
					success:function(r){
						if(r){
							tblLogTable.datagrid('loadData',r);
						}
					}
				});
			}
		}
	</script>
</head>
<body>
<div class="easyui-layout" fit="true" border="false" >
		<!-- table start -->
		<div region="center" border="false" style="border: 1px solid #c8c8c8;">
			<table id="tblLogTable" ></table>
		</div>
		<div region="north" border="false" style="height:35px; border-bottom:  1px solid #c8c8c8;border-right:  1px solid #c8c8c8;border-left:  1px solid #c8c8c8;">
			<div>
				<form id="selectForm" action="" method="post">
					<table>
						<tr>
							<td>操作日期：</td>
							<td><input id="beginDate" name="beginDate" value="${beginDate}" class="easyui-datebox" data-options="width:90,editable:false" required="true"/>&nbsp;&nbsp;&nbsp;至</td>
							<td><input id="endDate" name="endDate" value="${endDate}" class="easyui-datebox" data-options="width:90,editable:false" required="true"/></td>
							
							<td style="display:none;">操作位置：</td>
							<td style="display:none;" ><input id="host" name="host" class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto',
							url:'${pageContext.request.contextPath}/tblLogAction_selectHostList.action',width:200"/></td>
							<td><a class="easyui-linkbutton" plain="true" onclick="onSelectButton();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
