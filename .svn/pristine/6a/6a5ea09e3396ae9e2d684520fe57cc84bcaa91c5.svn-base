<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var levelTable;
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 35;
		tableWidth  = document.body.clientWidth-2;
		levelTable=$('#levelTable').datagrid({
			url : sybp()+'/dictLevelAction_loadList.action',
			title:'',
			height:tableHeight,
			width:tableWidth,  
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			border:false,
			idField:'id', //pk
			columns :[[{
				title : 'id',
				field : 'id',
				width : 50,
				hidden:true
			},{
				title:'病变程度',
				field:'level',
				halign:'center',
				width:150
			}]],
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
				var selectId = $('#selectId').val();
				if(selectId){
					$(this).datagrid('selectRecord',selectId);
				}
			}
		}); //end datagrid
		//显示整个布局
		$('#layoutMainDiv').css('display',''); 
	});
	
	/**显示Dialog*/
	function showLevelAddEditDialog(addOrEdit,title){
		/*签名Dialog*/
		document.getElementById("levelAddEditDialog2").style.display="block";
		$('#levelAddOrEdit').val(addOrEdit);
		//初始化Dialog 显示数据
		initDialogData(addOrEdit);
		$('#levelAddEditDialog').dialog('setTitle',title);
		$('#levelAddEditDialog').dialog('open'); 
	}	

	/**
	 * 初始化Dialog 显示数据
	 * @param addOrEdit
	 * @return
	 */
	function initDialogData(addOrEdit){
		//新建
		if(addOrEdit == 'addOne'){
			$('#oldLevel').val('');
			$('#level').val('');
			
		}else if(addOrEdit == 'editOne'){
			var selectRow = levelTable.datagrid('getSelected');
			$('#id').val(selectRow.id);
			$('#oldLevel').val(selectRow.level);
			$('#level').val(selectRow.level);
		}
	}
	/**
	 * 确定
	 * @return
	 */
	function onConfirmButton(){
		//确定   设为不可用
		//$('#confirmButton').linkbutton('disable');
		if($('#levelAddEditForm').form('validate')){
			var addOrEdit = $('#levelAddOrEdit').val();
			//新建
			if(addOrEdit.trim() == "addOne"){
					$.ajax({
						url:sybp()+'/dictLevelAction_addOne.action',
						type:'post',
						data:$('#levelAddEditForm').serialize(),
						dataType:'json',
						success:function(r){
							if(r && r.success){
								//刷新treegrid
								parent.showMessager(1,'新建成功！',true,5000);
								$('#selectId').val(r.msg);
								levelTable.datagrid('reload');
								$('#levelAddEditDialog').dialog('close');
							}else{
								$.messager.alert('警告','参数交互错误!');
							}
						}
					});
			}else if(addOrEdit.trim() == "editOne"){
				$.ajax({
					url:sybp()+'/dictLevelAction_editOne.action',
					type:'post',
					data:$('#levelAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						if(r && r.success){
							//刷新treegrid
							parent.showMessager(1,'编辑成功！',true,5000);
							$('#selectId').val(r.msg);
							levelTable.datagrid('reload');
							$('#levelAddEditDialog').dialog('close');
						}else{
							$.messager.alert('警告','参数交互错误!');
						}
					}
				});
			}
		}
		//确定   设为可用
		//$('#confirmButton').linkbutton('enable');
	}
	/**
	 *  新建
	 * @return
	 */
	function addOne(){
		showLevelAddEditDialog('addOne','新建');
	}
	/**
	 *  编辑
	 * @return
	 */
	function editOne(){
		showLevelAddEditDialog('editOne','编辑');
	}
	/**
	 *  删除
	 * @return
	 */
	function delOne(){
		var selectRow = levelTable.datagrid('getSelected');
		var msg = "";
			msg = '您确认想要删除记录吗？';
		$.messager.confirm('确认',msg,function(r){    
			if (r){    
				$.ajax({
					url:sybp()+'/dictLevelAction_delOne.action',
					type:'post',
					data:{
						id:selectRow.id
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							parent.showMessager(1,'删除成功！',true,5000);
							//刷新treegrid
							levelTable.datagrid('reload');
						}else{
							$.messager.alert('警告','与服务器交互错误！');
						}
					}
				});  
			}    
		});
	}
</script>
</head>
<body>
<input type="hidden" id="selectId"/>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
	<div region="center" title="" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="病变程度" border="false" style="overflow: auto;">
			<table id="levelTable" ></table>
			</div>
		</div>
		<%@include file="/WEB-INF/jsp/dictLevelAction/levelAddEdit.jspf" %>
	</div>
</div>
<div id="toolbar">
	<a id="addToolbarButton"  class="easyui-linkbutton" onclick="addOne();" data-options="iconCls:'icon-add',plain:true">新增</a>
	<a id="editToolbarButton" class="easyui-linkbutton" onclick="editOne();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
	<a id="delToolbarButton" class="easyui-linkbutton" onclick="delOne();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
</div>
</body>
</html>