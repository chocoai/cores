<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>防疫管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/quarantineAction/vaccineAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		//初始化房舍信息表
		showVaccineTable('');
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showVaccineTable(names){
    	$('#vaccineTable').datagrid({
			url : sybp()+'/quarantineAction_loadListVaccine.action?vaccinename='+names,
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
				title:'疫苗名称',
				field:'name',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'备注',
				field:'remark',
				width:160,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#addVaccineButton').linkbutton('enable');
    		  if(rowData.name=='Measles'||rowData.name=='HepatitisA'||rowData.name=='HepatitisB'){
    		  	$('#editVaccineButton').linkbutton('disable');
    		  	$('#deleteVaccineButton').linkbutton('disable');
    		  }else{
    		  	$('#editVaccineButton').linkbutton('enable');
    		  	$('#deleteVaccineButton').linkbutton('enable');
    		  }
			},
			onLoadSuccess:function(data){
			   $('#addVaccineButton').linkbutton('enable');
	    	   $('#editVaccineButton').linkbutton('disable');
	           $('#deleteVaccineButton').linkbutton('disable');
	           var selectid=$('#vvid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#vaccineTable').datagrid('selectRow',i);
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
    	var name =  $('#vaccinename').val();
    	showVaccineTable(name);
    }
	//添加按钮事件
    function onAddButton(){
    	showVaccineAddEditDialog('afterAddDialog','add','添加疫苗记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#vaccineTable').datagrid('reload');
    }
    //  编辑按钮事件
    function onEditButton(){
         var row= $('#vaccineTable').datagrid('getSelected');
        if(row != null ){
        	showVaccineAddEditDialog('afterEditDialog','edit','编辑疫苗记录');
        }else{
           $.messager.alert('提示','请选择编辑的疫苗记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.parent.showMessager(1,'编辑成功',true,5000);
    	 $('#vaccineTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#vaccineTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此记录？', function(r){
 				if (r){
 					delVaccine(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的记录!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delVaccine(id){
   	   	$.ajax({
   			url:sybp()+'/quarantineAction_delVaccine.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.parent.showMessager(1,'删除成功',true,5000);
   					$('#vaccineTable').datagrid('reload');
   				}else{
   					 parent.parent.showMessager(2,'删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<s:hidden id="vvid" name="vvid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
            <div title="疫苗" border="false" style="overflow: auto;">
            <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;疫苗名称
			    		<input id="vaccinename" type="text"    style="width:100px;"></input>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
                
				<table id="vaccineTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addVaccineButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editVaccineButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteVaccineButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/quarantineAction/vaccineAddEdit.jspf" %>
</div>
</body>
</html>