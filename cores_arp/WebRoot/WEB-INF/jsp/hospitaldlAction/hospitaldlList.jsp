<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>常规治疗</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/hospitaldlAction/hospitaldlAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		 
		initTreatveterinarianCombobox();	
		showHospitaldlTable('','','','','');
		
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    
    function initTreatveterinarianCombobox(){
    	$('#searchtreatveterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 		});
    }
    function showHospitaldlTable(monkeyid,treatveterinarian,zzmc,startdate,enddate){
    	$('#hospitaldlTable').datagrid({
			url : sybp()+'/hospitaldlAction_loadList.action?monkeyid='+monkeyid+'&treatveterinarians='+encodeURIComponent(treatveterinarian)+
			'&zzmc='+zzmc+'&startdate='+encodeURIComponent(startdate)+'&enddate='+encodeURIComponent(enddate),
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
				title:'动物编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'治疗日期',
				field:'zlrq',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'症状名称',
				field:'zzmc',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'处方',
				field:'cf',
				width:280,
				halign:'center',
				align:'center'
			},{
				title:'主治兽医',
				field:'treatveterinarian',
				width:60,
				halign:'center',
				align:'center'
			},{
				title:'备注',
				field:'remark',
				width:260,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#addHospitaldlButton').linkbutton('enable');
    		  $('#editHospitaldlButton').linkbutton('enable');
    		  $('#deleteHospitaldlButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addHospitaldlButton').linkbutton('enable');
	    	   $('#editHospitaldlButton').linkbutton('disable');
	           $('#deleteHospitaldlButton').linkbutton('disable');
	           var selectid=$('#hlid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#hospitaldlTable').datagrid('selectRow',i);
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
    	var monkeyid =  $('#searchmonkeyid').val();
    	var treatveterinarian =  $('#searchtreatveterinarian').combobox('getValue');
    	var zzmc =  $('#zzmc').val();
    	var startdate =  $('#startdate').datebox('getValue');
    	var enddate =  $('#enddate').datebox('getValue');
    	if(startdate!=""&&enddate==""){
    		$.messager.alert('提示','请输入结束日期!');
    		return;
    	}
    	if(startdate==""&&enddate!=""){$.messager.alert('提示','请输入开始日期!');return;}
    	showHospitaldlTable(monkeyid,treatveterinarian,zzmc,startdate,enddate);
    }
	//添加按钮事件
    function onAddButton(){
    	showHospitaldlAddEditDialog('afterAddDialog','add','常规治疗记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#hospitaldlTable').datagrid('reload');
    }
    function onEditButton(){
         var row= $('#hospitaldlTable').datagrid('getSelected');
        if(row != null ){
        	showHospitaldlAddEditDialog('afterEditDialog','edit','编辑常规治疗记录');
        }else{
           $.messager.alert('提示','请选择编辑的常规治疗记录!');
        }
    }
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#hospitaldlTable').datagrid('reload');
     }
  
   
      function onDeleteButton(){
     	  var row= $('#hospitaldlTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此常规治疗记录？', function(r){
 				if (r){
 					delHospitaldl(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的常规治疗记录!');
         }
      }
       function delHospitaldl(id){
   	   	$.ajax({
   			url:sybp()+'/hospitaldlAction_delHospitaldl.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'常规治疗删除成功',true,5000);
   					$('#hospitaldlTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'常规治疗删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<s:hidden id="hlid" name="hlid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="常规治疗" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;动物编号：
			    		<input id="searchmonkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="searchtreatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">治疗日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="hospitaldlTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addHospitaldlButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editHospitaldlButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteHospitaldlButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/hospitaldlAction/hospitaldlAddEdit.jspf" %>
</div>
</body>
</html>