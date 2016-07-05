<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>区域信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/roomDisinfectRecordAction/roomDisinfectRecordAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/roomDisinfectRecordAction/disfectantOperate.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 30;
		tableWidth  = document.body.clientWidth;
		//加载数据
        initRoomDisinfectTable('','');
		
		
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
     function initRoomDisinfectTable(roomName,disDate){
    	 $('#roomDisinfectTable').datagrid({
 			url : sybp()+'/roomDisinfectRecordAction_loadList.action?disinfectType='+encodeURIComponent(roomName)+"&disinfectDate="+encodeURIComponent(disDate),
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
 				title:'房间名',
 				field:'roomname',
 				width:100,
 				halign:'center',
 				align:'left'
 			},{
 				title:'所属区域',
 				field:'areaname',
 				width:60,
 				halign:'center',
 				align:'left'
 			},{
 				title:'消毒液编号',
 				field:'disinfectantCode',
 				width:110,
 				halign:'center',
 				align:'left'
 				
 			},{
 				title:'消毒日期',
 				field:'disinfectDate',
 				width:100,
 				halign:'center',
 				align:'center'
 			},{
 				title:'操作者',
 				field:'operator',
 				width:100,
 				halign:'center',
 				align:'left'
 			},{
 				title:'消毒方式',
 				field:'disinfectType',
 				width:100,
 				halign:'center',
 				align:'left'
 		    },{
 				title:'记录人',
 				field:'recorder',
 				width:100,
 				halign:'center',
 				align:'left'
 		    }
 			]],
 			onSelect:function(rowIndex, rowData){
 			   $('#adddisinfectButton').linkbutton('enable');
   		       $('#editdisinfectButton').linkbutton('enable');
   		       $('#deletedisinfectButton').linkbutton('enable');
   		       $('#disinfectantButton').linkbutton('enable');
 			},
 			onLoadSuccess:function(data){
 			   $('#adddisinfectButton').linkbutton('enable');
 	    	   $('#editdisinfectButton').linkbutton('disable');
 	    	   $('#deletedisinfectButton').linkbutton('disable');
 	    	   $('#disinfectantButton').linkbutton('enable');
 	    	   var selectid = $('#rid').val();
 		        if(selectid != ""){
 		          for(var i = 0 ; i< data.rows.length;i++){
 		             if(selectid == data.rows[i].id){
 		            	$('#roomDisinfectTable').datagrid('selectRow',i);
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

	
	//添加按钮事件
    function onAdddisinfectButton(){
    	showRoomDisinfectRecordAddEditDialog('afterAddDialog','add','添加房间消毒记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
    	parent.parent.showMessager(1,'添加成功',true,5000);
       $('#roomDisinfectTable').datagrid('reload');
    }
    //wan 编辑按钮事件
    function onEditdisinfectButton(){
         var row= $('#roomDisinfectTable').datagrid('getSelected');
        if(row != null ){
        	showRoomDisinfectRecordAddEditDialog('afterEditDialog','edit','编辑房间消毒记录');
        }else{
           $.messager.alert('提示','请选择编辑的房间消毒记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.parent.showMessager(1,'编辑成功',true,5000);
		 $('#roomDisinfectTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeletedisinfectButton(){
     	  var row= $('#roomDisinfectTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此房间消毒记录？', function(r){
 				if (r){
 					delroomDisinfectRecord(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的房间消毒记录!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delroomDisinfectRecord(id){
   	   	$.ajax({
   			url:sybp()+'/roomDisinfectRecordAction_delete.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					parent.parent.showMessager(1,'房间消毒记录删除成功',true,5000);
   					$('#roomDisinfectTable').datagrid('reload');
   				}else{
   					parent.parent.showMessager(2,'房间消毒记录删除失败',true,5000);
   				}
   			}
   		});
       } 
      function onDisinfectantButton(){
    	  showDisinfectantOperateDialog('','','消毒液设置');
      }
      //查找按钮事件
      function onSearchButton(){
          var roomName=$('#roomName').val();
          var disDate=$('#disDate').datebox('getValue');
          initRoomDisinfectTable(roomName,disDate);
      }
</script>
</head>
<body>
<!-- 房间消毒记录id -->
<s:hidden id="rid" name="rid"></s:hidden>
<!-- 消毒液id -->
<s:hidden id="did" name="did"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	
            <div title="房舍消毒" border="false" style="overflow: auto;">
				<table id="roomDisinfectTable" ></table>
            </div>
		
		<div id="toolbar">
		   <a id="adddisinfectButton" class="easyui-linkbutton" onclick="onAdddisinfectButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editdisinfectButton" class="easyui-linkbutton" onclick="onEditdisinfectButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deletedisinfectButton" class="easyui-linkbutton" onclick="onDeletedisinfectButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
			<a id="disinfectantButton" class="easyui-linkbutton" onclick="onDisinfectantButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">消毒液设置</a>
		</div>
		<span style="position:absolute; top:3px;left:600px">
			    		房间名：<input id="roomName"  name="roomName"/>&nbsp;&nbsp;&nbsp;消毒日期：<input id="disDate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"/>
			    		<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    		</span> 
 	</div>
    <%@include file="/WEB-INF/jsp/roomDisinfectRecordAction/roomDisinfectRecordAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/roomDisinfectRecordAction/disinfectantOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/roomDisinfectRecordAction/disinfectantAddEdit.jspf" %>
</div>
</body>
</html>