<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>出场管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/salemonkeyAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#tabs').tabs({    
		    onSelect:function(title,index){
		      if(index == 1){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '订单选猴',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/salemonkeyAction_selectmonkey.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
//		    	  window.open(sybp()+'/roomDisinfectRecordAction_list.action','main');
//		    	  initroomDisinfectTable();
		      }    
		    }    
		});  
				
		//初始化出场记录信息表
		showSalemonkeyTable('','','');
		var nowdate=CurentTime();
		$('#searchoutdate').datebox('setValue',nowdate);
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showSalemonkeyTable(monkeyid,outdate,blongsale){
    	$('#salemonkeyTable').datagrid({
			url : sybp()+'/salemonkeyAction_loadList.action?monkeyid='+monkeyid+'&outdate='+encodeURIComponent(outdate)+'&blongsale='+encodeURIComponent(blongsale),
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
				hidden:true
			},{
				title:'猴子编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'猴子类型',
				field:'typeName',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'出场日期',
				field:'outdate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'订单名称',
				field:'title',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'选择条件',
				field:'tiaojian',
				width:150,
				halign:'center',
				align:'center'
			},{
				title:'订单总数量',
				field:'salecount',
				width:70,
				halign:'center',
				align:'left'
			},{
				title:'销售许可批号',
				field:'aphao',
				width:150,
				halign:'center',
				align:'left'
			},{
				title:'运输许可批号',
				field:'tphao',
				width:150,
				halign:'tphao',
				align:'left'
			},{
				title:'销售类型',
				field:'saletypeName',
				width:60,
				halign:'center',
				align:'center'
			},{
				title:'负责人',
				field:'bossName',
				width:60,
				halign:'center',
				align:'center'
			},{
				title:'备注',
				field:'remark',
				width:150,
				halign:'center',
				align:'left'
			},{
				title:'添加标志',
				field:'addmark',
				width:150,
				halign:'center',
				align:'left',
				hidden:true
			}
				
			]],
			onSelect:function(rowIndex, rowData){
    		var rows=$("#salemonkeyTable").datagrid("getRows");
			   if(rows[0].addmark==1){
				   $('#addSalemonkeyButton').linkbutton('disable');  
			   }else{
				   $('#addSalemonkeyButton').linkbutton('enable');  
			   }
//    		  $('#editDeathButton').linkbutton('enable');
//    		  $('#deleteDeathButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){

			   var rows=$("#salemonkeyTable").datagrid("getRows");
			   if(rows[0].addmark==1){
				   $('#addSalemonkeyButton').linkbutton('disable');  
			   }else{
				   $('#addSalemonkeyButton').linkbutton('enable');  
			   }
			  
//	    	   $('#editDeathButton').linkbutton('disable');
//	           $('#deleteDeathButton').linkbutton('disable');
//	           var selectid=$('#did').val();
//	           if(selectid != ""){
//			          for(var i = 0 ; i< data.rows.length;i++){
//			             if(selectid == data.rows[i].id){
//			            	$('#deathTable').datagrid('selectRow',i);
//			             }
//			          }
//			   }
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    }
  //查找按钮事件
    function onSearchButton(){
    	var searchmonkeyid =  $('#searchmonkeyid').val();
    	var searchoutdate =  $('#searchoutdate').datebox('getValue');
    	var blongsale = $('#blongsale').val();
    	showSalemonkeyTable(searchmonkeyid,searchoutdate,blongsale);
    }
	
	//添加按钮事件
    function onAddButton(){
    	showSalemonkeyAddEditDialog('afterAddDialog','add','添加出场记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#salemonkeyTable').datagrid('reload');
    }
   
    function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var clock ="" + year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day;
    return(clock); 
} 
</script>
</head>
<body>
<!-- 死亡登记记录id -->
<s:hidden id="did" name="did"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="出场管理" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;动物编号
			    		<input id="searchmonkeyid" type="text"    style="width:100px;"></input>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 出场日期
		                &nbsp;
			        	&nbsp;<input id="searchoutdate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="true"></input>	
			      	  	&nbsp;
			    		<!-- 搜索框 -->
			    		&nbsp;&nbsp;&nbsp;&nbsp;所属订单：
			    		<input id="blongsale" type="text"  style="width:100px;height:19px;" ></input>	
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="salemonkeyTable" ></table>
            </div>
            <div title="订单挑选" border="false" style="overflow: auto;">
				<table id="roomDisinfectTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addSalemonkeyButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		<!--  	<a id="editDeathButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteDeathButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>-->
		 	<!-- <a id="deleteEmployeeRealButton" class="easyui-linkbutton" onclick="onDeleteRealButton();" data-options="iconCls:'icon-remove',plain:true,disabled:true">真删</a>
			<a id="departmentButton" class="easyui-linkbutton" onclick="onDepartmentButton();" data-options="iconCls:'icon-link',plain:true,disabled:true">部门设置</a>
			<a id="positionButton" class="easyui-linkbutton" onclick="onPositionButton();" data-options="iconCls:'icon-server',plain:true,disabled:true">职位设置</a>
			<a id="zhicButton" class="easyui-linkbutton" onclick="onZhicButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">职称设置</a> -->
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/salemonkeyAddEdit.jspf" %>
</div>
</body>
</html>