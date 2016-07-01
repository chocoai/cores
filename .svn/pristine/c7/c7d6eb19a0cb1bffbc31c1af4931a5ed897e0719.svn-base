<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var resManagerTable;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		resManagerTable = $('#resManagerTable').treegrid({    
		    url:sybp()+'/tblResManagerAction_loadList.action?currentResId='+$('#currentResId').val(),    
		    idField:'id',    
		    treeField:'resName', 
		    height:tableHeight,
		    animate:false,   
		    singleSelect: true, //单选
		    columns:[[  
                {title:'id',field:'id',width:180,hidden:true},
		        {field:'resKind',title:'resKind',width:180,hidden:true}, 
		        {title:'资源名称',field:'resName',width:150},    
		        {field:'_parentId',title:'_parentId',width:180,hidden:true},
		        {field:'resManager',title:'负责人',width:260},
		        {field:'sign',title:'签字确认',width:260,hidden:true}
		    ]],
		    //工具栏
			toolbar:'#toolbar',  
	     	onLoadSuccess:function(row, data){
		     	if(data && data.currentResId){
	     			$('#resManagerTable').treegrid('expandTo',data.currentResId);
	     			$('#resManagerTable').treegrid('select',data.currentResId);
				}
		    }
		}); 

		$('#easyuiIayout').css('display',''); 
		
	});
	//负责人设置
	function oneditresManagerButton(){
		var row = $('#resManagerTable').treegrid('getSelected');
		if(row){
			if(row.id != ""){
				window.location.href=sybp()+'/tblResManagerAction_setUI.action?currentResId='+row.id;
				$('#nodeid').val("");
			}else if(row.id  == ""){
				parent.showMessager(2,'请选择编辑行',true,5000);
			}
		}else{
			$.messager.alert('警告','请先选择数据');
		}
	}	
</script>
</head>
<body>
<s:hidden id="currentResId" name="currentResId"> </s:hidden>
<div id="" class="easyui-tabs" fit="true" border="true" >
	<div title="动物房负责人设置"  border="false" style="overflow: hidden;">
		<table id="resManagerTable" ></table>
	</div>
</div>
	 <div id = "easyuiIayout" style="display:none">
		<div id="toolbar">
			<a id="editresManagerButton" class="easyui-linkbutton" onclick="oneditresManagerButton();" data-options="iconCls:'icon-user',disabled:false,plain:true">负责人设置</a>
		</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</body>
</html>