<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>繁殖管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/breedingAction/gestationAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#searchIsHave').combobox('setValue',-1);
		var searchIsHave=$('#searchIsHave').combobox('getValue');
		//初始化妊娠检查表
		showGestationTable('','','');
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showGestationTable(femailMonkeyId,searchCheckdate,searchIsHave){
    	$('#gestationTable').datagrid({
			url : sybp()+'/gestationAction_loadList.action?monkeyid='+femailMonkeyId+'&checkdate='+searchCheckdate+'&ishave='+searchIsHave,
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
				title:'母猴编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'检查日期',
				field:'checkdate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'是否有胎',
				field:'ishave',
				width:80,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "没有";
					}else{
						return "有";
					}
				}
			},{
				title:'备注',
				field:'remarks',
				width:230,
				halign:'center',
				align:'center'
			},{
				title:'兽医',
				field:'veterinarianName',
				width:60,
				halign:'center',
				align:'center'
			},{
				title:'保定人员',
				field:'protectorName',
				width:60,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#addDeathButton').linkbutton('enable');
    		  $('#editDeathButton').linkbutton('enable');
    		  $('#deleteDeathButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addDeathButton').linkbutton('enable');
	    	   $('#editDeathButton').linkbutton('disable');
	           $('#deleteDeathButton').linkbutton('disable');
	           var selectid=$('#bid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#gestationTable').datagrid('selectRow',i);
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
    	var femailMonkeyId =  $('#femailMonkeyId').val();
    	var searchCheckdate =  $('#searchCheckdate').datebox('getValue');
    	var searchIsHave=$('#searchIsHave').combobox('getValue');
    	showGestationTable(femailMonkeyId,searchCheckdate,searchIsHave);
    }
	//添加按钮事件
    function onAddButton(){
    	showGestationAddEditDialog('afterAddDialog','add','添加妊娠检查记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#gestationTable').datagrid('reload');
    }
    //wan 编辑按钮事件
    function onEditButton(){
         var row= $('#gestationTable').datagrid('getSelected');
        if(row != null ){
        	showGestationAddEditDialog('afterEditDialog','edit','编辑妊娠检查记录');
        }else{
           $.messager.alert('提示','请选择编辑的调栏记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#gestationTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#gestationTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此记录？', function(r){
 				if (r){
 					del(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的记录!');
         }
      }
     //根据Id删除相应记录(数据库还保存)
       function del(id){
   	   	$.ajax({
   			url:sybp()+'/gestationAction_delete.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'删除成功',true,5000);
   					$('#gestationTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<!-- 发情配种记录id -->
<s:hidden id="bid" name="bid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="妊娠检查" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:2px;height:22px;">
			          <table>
			             <tr>
			               <td>母猴编号</td>
			               <td><input type="text" id="femailMonkeyId" name="femailMonkeyId" style="width:100px;"/></td>
			               <td>检查日期</td>
			               <td><input id="searchCheckdate" type="text" class="easyui-datebox" style="width:100px;" editable="false"/></td>
			               <td>是否有胎</td>
			               <td><select id="searchIsHave" class="easyui-combobox" name="searchIsHave" style="width:100px;"  panelHeight=70  editable="false">
			                        <option value="-1" >全部</option>   
								    <option value="0">没有</option>   
								    <option value="1">有</option>   
								</select>  
			               </td>
			               <td><a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a></td>
			             </tr>
			             
			          </table>
			           <!--  
			    		&nbsp;&nbsp;动物编号
			    		<input id="searchmonkeyid" type="text" name="monkeyid"   style="width:100px;"></input>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 调栏日期
		                &nbsp;
			        	&nbsp;<input id="searchchangeroomdate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>	
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    		-->
			    	</div>
			    
			    </div>
				<table id="gestationTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addDeathButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editDeathButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteDeathButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	<!-- <a id="deleteEmployeeRealButton" class="easyui-linkbutton" onclick="onDeleteRealButton();" data-options="iconCls:'icon-remove',plain:true,disabled:true">真删</a>
			<a id="departmentButton" class="easyui-linkbutton" onclick="onDepartmentButton();" data-options="iconCls:'icon-link',plain:true,disabled:true">部门设置</a>
			<a id="positionButton" class="easyui-linkbutton" onclick="onPositionButton();" data-options="iconCls:'icon-server',plain:true,disabled:true">职位设置</a>
			<a id="zhicButton" class="easyui-linkbutton" onclick="onZhicButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">职称设置</a> -->
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/breedingAction/gestationAddEdit.jspf" %>
</div>
</body>
</html>