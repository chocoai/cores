<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>防疫管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/quarantineAction/quarantineAddEdit.js" charset="utf-8"></script>
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
		    				title: '病毒',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/quarantineAction_listVirus.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		      if(index == 2){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '细菌',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/quarantineAction_listBacteria.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		     if(index == 3){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '疫苗',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/quarantineAction_listVaccine.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		      if(index == 4){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '传染病',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/quarantineAction_listInfectious.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		      if(index == 5){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '寄生虫检疫方法',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/parasiteMAction_list.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		      if(index == 6){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '病毒检疫方法',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/virusMAction_list.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		      if(index == 7){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '细菌检疫方法',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/bacteriaMAction_list.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		    }    
		});  
				
		//初始化出场记录信息表
		showParasiteTable('');
		
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showParasiteTable(parasitename){
    	$('#parasiteTable').datagrid({
			url : sybp()+'/quarantineAction_loadList.action?name='+parasitename,
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
				title:'寄生虫名称',
				field:'name',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'描述',
				field:'remark',
				width:80,
				halign:'center',
				align:'center'
			}	
			]],
			
			onSelect:function(rowIndex, rowData){
    		  $('#addParasiteButton').linkbutton('enable');
   		  	  $('#editParasiteButton').linkbutton('enable');
    		  $('#deleteParasiteButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   
			   $('#addParasiteButton').linkbutton('enable');
	    	   $('#editParasiteButton').linkbutton('disable');
	           $('#deleteParasiteButton').linkbutton('disable');
	           var selectid=$('#qid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#parasiteTable').datagrid('selectRow',i);
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
    	var parasitename =  $('#parasitename').val();
    	showParasiteTable(parasitename);
    }
	
	//添加按钮事件
    function onAddButton(){
    	showParasiteAddEditDialog('afterAddDialog','add','添加寄生虫信息');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#parasiteTable').datagrid('reload');
    }
   function onEditButton(){
         var row= $('#parasiteTable').datagrid('getSelected');
        if(row != null ){
        	showParasiteAddEditDialog('afterEditDialog','edit','编辑寄生虫记录');
        }else{
           $.messager.alert('提示','请选择编辑的寄生虫记录!');
        }
    }
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#parasiteTable').datagrid('reload');
     }
  
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#parasiteTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此寄生虫记录？', function(r){
 				if (r){
 					delParasite(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的寄生虫!');
         }
      }
       function delParasite(id){
   	   	$.ajax({
   			url:sybp()+'/quarantineAction_delParasite.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'记录删除成功',true,5000);
   					$('#parasiteTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'记录删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<!-- 死亡登记记录id -->
<s:hidden id="qid" name="qid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="寄生虫" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;寄生虫名称
			    		<input id="parasitename" type="text"  name=""  style="width:100px;"></input> 
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="parasiteTable" ></table>
            </div>
            <div title="病毒" border="false" style="overflow: auto;">
				<table id="virusTable" ></table>
            </div>
            <div title="细菌" border="false" style="overflow: auto;">
				<table id="bacteriaTable" ></table>
            </div>
            <div title="疫苗" border="false" style="overflow: auto;">
				<table id="vaccineTable" ></table>
            </div>
            <div title="传染病" border="false" style="overflow: auto;">
				<table id="infectiousTable" ></table>
            </div>
            <div title="寄生虫检疫方法" border="false" style="overflow: auto;">
				<table id="parasiteMTable" ></table>
            </div>
            <div title="病毒检疫方法" border="false" style="overflow: auto;">
				<table id="virusMTable" ></table>
            </div>
            <div title="细菌检疫方法" border="false" style="overflow: auto;">
				<table id="bacteriaMTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addParasiteButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		  	<a id="editParasiteButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteParasiteButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	<!-- <a id="deleteEmployeeRealButton" class="easyui-linkbutton" onclick="onDeleteRealButton();" data-options="iconCls:'icon-remove',plain:true,disabled:true">真删</a>
			<a id="departmentButton" class="easyui-linkbutton" onclick="onDepartmentButton();" data-options="iconCls:'icon-link',plain:true,disabled:true">部门设置</a>
			<a id="positionButton" class="easyui-linkbutton" onclick="onPositionButton();" data-options="iconCls:'icon-server',plain:true,disabled:true">职位设置</a>
			<a id="zhicButton" class="easyui-linkbutton" onclick="onZhicButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">职称设置</a> -->
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/quarantineAction/parasiteAddEdit.jspf" %>
</div>
</body>
</html>