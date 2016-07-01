<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>CoRES项目管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<script type="text/javascript">
	var tableHeight ;
	var tblRigonTable;
	$(function(){
		tableHeight = document.body.clientHeight-5;
	   	tblRigonTable= $('#tblRigonTable').treegrid({ 
			url : sybp()+'/tblRegionAction_getTblRigonTreedatagrid.action?cid='+$('#cid').val(),
			height:tableHeight,
			width:190,
			idField:'id',    
		    treeField:'regionName', 
		    animate:false,   
		    singleSelect: true, //不支持多选
		    columns:[[  
		                {title:'id',field:'id',width:0,hidden:true},
				        {title:'地区级别',field:'level',width:0,hidden:true}, 
				        {title:'地区名称',field:'regionName',width:169},    
				        {field:'_parentId',title:'_parentId',width:0,hidden:true}
				    ]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(node){
	   			window.open(sybp()+'/tblCustomerAction_list.action?regionId ='+node.id+'&cuid='+$('#cuid').val(),'main');
			},	
			onLoadSuccess:function(row, data){
			   if(data.cid){
			    	$('#tblRigonTable').treegrid('expandTo',data.cid);
			    	$('#tblRigonTable').treegrid('select',data.cid);
			   }
		    },
		    onLoadError:function(row, data){
		    }
		 });
	
		 
	});
	
	function updateucid(){
	   $('#cuid').val('');
	}


	//刷新datagrid(选择左边导航left0时用)
	function reloadDataGrid(){

		var selected =$('#tblRigonTable').treegrid('getSelected');
		if(selected){
			window.open(sybp()+'/tblCustomerAction_list.action?regionId ='+selected.id,'main');
		}else{
			$('#tblRigonTable').treegrid('reload');
			window.open(sybp()+'/tblCustomerAction_list.action','main');
		}
	}
	
	/**更新tblRigonTable及选中对应项*/
	function afterAddCustomer(cid,cuid){
		$('#cuid').val(cuid);
		$('#tblRigonTable').treegrid({ 
			url : sybp()+'/tblRegionAction_getTblRigonTreedatagrid.action?cid='+cid
		});
		//document.getElementById("iframe_customer").src=sybp()+'/tblRegionAction_list.action?cid='+cid+'&cuid='+cuid;
	}
	/**更新tblRigonTable*/
	function updateTblRigonTable(){
		$('#tblRigonTable').treegrid({ 
			url : sybp()+'/tblRegionAction_getTblRigonTreedatagrid.action?cid='
		});
//		alert('updateTblRigonTable');
		//document.getElementById("iframe_customer").src=sybp()+'/tblRegionAction_list.action';
	}
	
</script>
</head>
<body>
<s:hidden id="cid" name="cid"></s:hidden>
<s:hidden id="cuid" name="cuid"></s:hidden>
 <div>
	<table id="tblRigonTable" ></table>
 </div>
</body>
</html>
