<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单选猴</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/selectmonkeyfromlist.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/orderAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/orderSubmit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/ysAddressOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/approvalOperate.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 60
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth-30;
		//加载数据
        initRoomDisinfectTable();
		
		
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
     function initRoomDisinfectTable(){
    	 $('#selectmonkeyTable').datagrid({
 			url : sybp()+'/salemonkeyAction_loadSaleListBySelect.action',
 			title:'',
 			height:tableHeight,
 			width:tableWidth,
 			nowarp:  false,//单元格里自动换行
 			singleSelect:true,
 			fitColumns:false,
 		    pagination:true,//分页
 			sortName:'id',
 			columns :[[{
 				title:'id',
 				field:'id',
 				width:150,
 				halign:'center',
 				align:'center',
 				hidden:true
 			},{
 				title:'订单名',
 				field:'title',
 				width:100,
 				halign:'center',
 				align:'left'
 			},{
 				title:'订单状态',
 				field:'status',
 				width:60,
 				halign:'center',
 				align:'left'
 			},{
 				title:'选猴条件',
 				field:'tiaojian',
 				width:300,
 				halign:'center',
 				align:'left'
 				
 			},{
 				title:'订单数量',
 				field:'salecount',
 				width:100,
 				halign:'center',
 				align:'center'
 			},{
 				title:'订单类型',
 				field:'saletypeName',
 				width:60,
 				halign:'center',
 				align:'center'
 		    },{
 				title:'订单运送地',
 				field:'saleaddress',
 				width:100,
 				halign:'center',
 				align:'left'
 		    },{
 				title:'负责人',
 				field:'bossName',
 				width:120,
 				halign:'center',
 				align:'center'
 			}
 			]],
 			onSelect:function(rowIndex, rowData){
 			   $('#addButton').linkbutton('enable');
 			   if(rowData.status=="确认选猴"){
 			   		$('#selectButton').linkbutton('enable');
 			   		$('#editButton').linkbutton('enable');
 			   }else{
 			   		$('#selectButton').linkbutton('disable');
 			   		$('#editButton').linkbutton('disable');
 			   }
 			   if(rowData.status=="完成选猴"){
 			   		$('#outSumbitButton').linkbutton('enable');
 			   }else{
 			   		$('#outSumbitButton').linkbutton('disable');
 			   }
 			},
 			onLoadSuccess:function(data){
 				$('#outSumbitButton').linkbutton('disable');
 	    	   $('#selectButton').linkbutton('disable');
 	    	   $('#addButton').linkbutton('enable');
 	    	   $('#editButton').linkbutton('disable');
 	    	   
 	    	   var selectid=$('#sid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#selectmonkeyTable').datagrid('selectRow',i);
			             }
			          }
			   }
 			},
 			toolbar:'#toolbar',
 			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
 	   	});		
     }

	
	//挑选按钮事件
    function onselectButton(){
    	showSelectmonkeyDialog('afterSelectDialog','select','选择猴子');
    }
    function afterSelectDialog(){
    	parent.parent.showMessager(1,'选择成功',true,5000);
        $('#selectmonkeyTable').datagrid('reload');
    }
	
	 function onAddButton(){
    	showOrderAddEditDialog('afterAddDialog','add','添加选候订单');
    	$("#title").removeAttr("readonly");
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#selectmonkeyTable').datagrid('reload');
    }
    // 确认订单出场
    function onOutSumbitButton(){
         var row= $('#selectmonkeyTable').datagrid('getSelected');
        if(row != null ){
        	showOrderSubmitDialog('afterEditDialog1','submit','确认猴子出场订单记录');
        }else{
           $.messager.alert('提示','请选择确认猴子出场订单记录!');
        }
    }
   //确认后事件
     function afterEditDialog1(){
    	 parent.parent.showMessager(1,'订单确认成功',true,5000);
    	 $('#selectmonkeyTable').datagrid('reload');
     }
  	function onAddressButton(){
    	  showAddressOperateDialog('','','运送地设置');
      }
      
    function onEditButton(){
    	var row=$('#selectmonkeyTable').datagrid('getSelected');
    	if(row!=null){
    		showOrderAddEditDialog('afterEditDialog','edit','编辑选候订单');
    		$('#title').attr({readonly:'true'});
    	}else{
    		$.messager.alert('提示','请选择需要编辑的订单！');
    	}
    }
    function afterEditDialog(){
    	parent.parent.showMessager(1,'编辑成功',true,5000);
    	//$('#selectmonkeyTable').datagrid('reload');
    	 $('#selectmonkeyTable').datagrid({
 			url : sybp()+'/salemonkeyAction_loadSaleListBySelect.action'});
    }
 	
  	function onApprovalButton(){
    	  showApprovalOperateDialog('许可证设置');
      }
</script>
</head>
<body>
<!-- 订单记录id -->
<s:hidden id="sid" name="sid"></s:hidden>
<!-- 运送地id -->
<s:hidden id="aid" name="aid"></s:hidden>
<s:hidden id="apid" name="apid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	
            <div title="订单选猴" border="false" style="overflow: auto;">
				<table id="selectmonkeyTable" ></table>
            </div>
		
		<div id="toolbar">
		    <a id="selectButton" class="easyui-linkbutton" onclick="onselectButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">挑选</a>
		    <a id="addButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		    <a id="editButton" class="easyui-linkbutton" onclick="onEditButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">订单编辑</a>
			<a id="outSumbitButton" class="easyui-linkbutton" onclick="onOutSumbitButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">出场确认</a>
			<a id="addressButton" class="easyui-linkbutton" onclick="onAddressButton()" data-options="iconCls:'icon-groupdelete',plain:true">运送地配置</a>
			<a id="approvalButton" class="easyui-linkbutton" onclick="onApprovalButton()" data-options="iconCls:'icon-tablego',plain:true">销售许可证配置</a> 
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/selectmonkeyfromlist.jspf" %>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/orderAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/orderSubmit.jspf" %>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/addressOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/addressAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/approvalOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/approvalAddEdit.jspf" %>
</div>
</body>
</html>