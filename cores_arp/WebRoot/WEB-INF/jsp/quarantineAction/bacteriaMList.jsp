<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>防疫管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/quarantineAction/bacteriaMAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		//初始化房舍信息表
		showBacteriaMTable('');
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showBacteriaMTable(names){
    	$('#bacteriaMTable').datagrid({
			url : sybp()+'/bacteriaMAction_loadList.action?name='+names,
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
				title:'细菌检疫方法名称',
				field:'name',
				width:140,
				halign:'center',
				align:'center'
			},{
				title:'备注',
				field:'remark',
				width:80,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#addBacteriaMButton').linkbutton('enable');
    		  $('#editBacteriaMButton').linkbutton('enable');
    		  $('#deleteBacteriaMButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addBacteriaMButton').linkbutton('enable');
	    	   $('#editBacteriaMButton').linkbutton('disable');
	           $('#deleteBacteriaMButton').linkbutton('disable');
	           var selectid=$('#bmid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#bacteriaMTable').datagrid('selectRow',i);
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
    //查找按钮事件
    function onSearchButton(){
    	var name =  $('#bacteriamname').val();
    	showBacteriaMTable(name);
    }
	//添加按钮事件
    function onAddButton(){
    	showBacteriaMAddEditDialog('afterAddDialog','add','添加细菌记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#bacteriaMTable').datagrid('reload');
    }
    //wan 编辑按钮事件
    function onEditButton(){
         var row= $('#bacteriaMTable').datagrid('getSelected');
        if(row != null ){
        	showBacteriaMAddEditDialog('afterEditDialog','edit','编辑细菌记录');
        }else{
           $.messager.alert('提示','请选择编辑的细菌记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.parent.showMessager(1,'编辑成功',true,5000);
    	 $('#bacteriaMTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#bacteriaMTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此记录？', function(r){
 				if (r){
 					delBacteriaM(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的记录!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delBacteriaM(id){
   	   	$.ajax({
   			url:sybp()+'/bacteriaMAction_delBacteria.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.parent.showMessager(1,'删除成功',true,5000);
   					$('#bacteriaMTable').datagrid('reload');
   				}else{
   					 parent.parent.showMessager(2,'删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<s:hidden id="bmid" name="bmid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
            <div title="细菌" border="false" style="overflow: auto;">
            <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;细菌名称
			    		<input id="bacteriamname" type="text"    style="width:100px;"></input>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
                
				<table id="bacteriaMTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addBacteriaMButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editBacteriaMButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteBacteriaMButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/quarantineAction/bacteriaMAddEdit.jspf" %>
</div>
</body>
</html>