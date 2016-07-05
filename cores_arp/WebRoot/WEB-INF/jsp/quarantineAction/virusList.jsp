<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>防疫管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/quarantineAction/virusAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		//初始化房舍信息表
		showVirusTable('');
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showVirusTable(names){
    	$('#virusTable').datagrid({
			url : sybp()+'/quarantineAction_loadListVirus.action?virusname='+names,
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
				title:'病毒名称',
				field:'name',
				width:80,
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
    		  $('#addVirusButton').linkbutton('enable');
    		  $('#editVirusButton').linkbutton('enable');
    		  $('#deleteVirusButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addVirusButton').linkbutton('enable');
	    	   $('#editVirusButton').linkbutton('disable');
	           $('#deleteVirusButton').linkbutton('disable');
	           var selectid=$('#vid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#virusTable').datagrid('selectRow',i);
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
    	var name =  $('#virusname').val();
    	showVirusTable(name);
    }
	//添加按钮事件
    function onAddButton(){
    	showVirusAddEditDialog('afterAddDialog','add','添加病毒记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#virusTable').datagrid('reload');
    }
    //wan 编辑按钮事件
    function onEditButton(){
         var row= $('#virusTable').datagrid('getSelected');
        if(row != null ){
        	showVirusAddEditDialog('afterEditDialog','edit','编辑病毒记录');
        }else{
           $.messager.alert('提示','请选择编辑的病毒记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.parent.showMessager(1,'编辑成功',true,5000);
    	 $('#virusTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#virusTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此记录？', function(r){
 				if (r){
 					delVirus(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的记录!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delVirus(id){
   	   	$.ajax({
   			url:sybp()+'/quarantineAction_delVirus.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.parent.showMessager(1,'删除成功',true,5000);
   					$('#virusTable').datagrid('reload');
   				}else{
   					 parent.parent.showMessager(2,'删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<s:hidden id="vid" name="vid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<!-- <div id="tabs" class="easyui-tabs" fit="true" border="false">-->
            <div title="病毒" border="false" style="overflow: auto;">
            <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;病毒名称
			    		<input id="virusname" type="text"    style="width:100px;"></input>
			    	    <!-- &nbsp;&nbsp;&nbsp;&nbsp; 出场日期
		                &nbsp;
			        	&nbsp;<input id="searchoutdate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>	
			      	  	&nbsp;
			    		<!-- 搜索框 -->
			    		<!--&nbsp;&nbsp;&nbsp;&nbsp;所属订单：-->
			    		<!--<input id="blongsale" type="text"  style="width:100px;height:19px;" ></input>	 -->
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
                <!-- <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:2px;height:22px;">
			          <table>
			             <tr>
			               <td>病毒名称</td>
			               <td><input type="text" id="name" name="name" style="width:100px;"/></td>
			               <td>发情日期</td>
			               <td><input id="searchOestrusdate" type="text" class="easyui-datebox" style="width:100px;" editable="false"/></td>
			               <td>配种日期</td>
			               <td><input id="searchBreedingdate" type="text" class="easyui-datebox" style="width:100px;" editable="false"/></td> 
                           <td>交配日期</td>
			               <td><input id="searchMatingdate" type="text" class="easyui-datebox" style="width:100px;" editable="false"/></td>
			               <td>发情类型</td>
			               <td><select id="searchOestrustype" class="easyui-combobox" name="searchOestrustype" style="width:100px;"  panelHeight=70  editable="false">
			                        <option value="0" >全部</option>   
								    <option value="27">自然发情</option>   
								    <option value="28">人工发情</option>   
								</select>  
			               </td>
			               <td><a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a></td>
			             </tr>
			             
			          </table>
			       
			    	</div>
			    
			    </div> -->
				<table id="virusTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addVirusButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editVirusButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteVirusButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	<!-- <a id="deleteEmployeeRealButton" class="easyui-linkbutton" onclick="onDeleteRealButton();" data-options="iconCls:'icon-remove',plain:true,disabled:true">真删</a>
			<a id="departmentButton" class="easyui-linkbutton" onclick="onDepartmentButton();" data-options="iconCls:'icon-link',plain:true,disabled:true">部门设置</a>
			<a id="positionButton" class="easyui-linkbutton" onclick="onPositionButton();" data-options="iconCls:'icon-server',plain:true,disabled:true">职位设置</a>
			<a id="zhicButton" class="easyui-linkbutton" onclick="onZhicButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">职称设置</a> -->
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/quarantineAction/virusAddEdit.jspf" %>
</div>
</body>
</html>