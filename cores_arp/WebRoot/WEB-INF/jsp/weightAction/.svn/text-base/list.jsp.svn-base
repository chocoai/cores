<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>饲养管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/employeeAddEdit.js" charset="utf-8"></script>
<style type="text/css">
	 .tree-icon {
		width:0px;
	}
	
</style>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 30;
		tableWidth  = document.body.clientWidth;
		
		 $('#weighttype').combobox({    
		    url:sybp()+'/weightAction_getWeightTypeMap.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		     }  
		    
		});
		//初始化datagrid
		$('#weightTable').datagrid({
			url : sybp()+'/weightAction_loadDate.action',
			title:'',
			height:tableHeight/2,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},
			{
				title:'动物编号',
				field:'monkeyid',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'称重日期',
				field:'weightdate',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'体重(kg)',
				field:'weight',
				width:100,
				halign:'center',
				align:'left'
			},{title:'体重类型1',field:'weighttype',hidden:true},
			{
				title:'体重类型',
				field:'weighttypeName',
				width:100,
				halign:'center',
				align:'left'//,
				//12 离乳体重 13出生体重 14 当前体重
				//formatter: function(value,row,index){
				//	if ( value == '12' ){
				//		return "离乳体重";
				//	}else if( value == '13'){
				//		return "出生体重";
				//	}else if( value == '14'){
				//	    return "当前体重";
				//	}
				//}
			},
			{
				title:'主管',
				field:'boss',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'保定人员',
				field:'protector',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'记录员',
				field:'recorder',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'称重人员',
				field:'operater',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'备注',
				field:'remark',
				width:200,
				halign:'center',
				align:'left'
			}
			]],
			onSelect:function(rowIndex, rowData){
			   $('#weightOneTable').datagrid({
			   title:"动物编号："+rowData.monkeyid + ':个体生长记录',
			   url:sybp()+'/weightAction_loadOneDate.action?monkeyid='+rowData.monkeyid});
			   if(rowData.weighttypeName=="当前体重"){
			   	$('#editIndividualButton').linkbutton('enable');
			   	$('#deleteIndividualButton').linkbutton('enable');
			   }else{
			   	$('#editIndividualButton').linkbutton('disable');
			   	$('#deleteIndividualButton').linkbutton('disable');
			   }  
			},
			onLoadSuccess:function(data){
			},
			toolbar:'#toolbar',
			pageSize : 5,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	}); //end datagrid
	   	
	   	//初始化datagrid
		$('#weightOneTable').datagrid({
			title:'个体生长记录',
			height:(tableHeight/2)-10,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:false,//分页
			sortName:'id',
			idField:'id',
			toolbar:'#onetoolbar',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},
			//{
			//	title:'动物编号',
			//	field:'monkeyid',
			//	width:100,
			//	halign:'center',
			//	align:'left',
			//},
			{
				title:'称重日期',
				field:'weightdate',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'体重(kg)',
				field:'weight',
				width:100,
				halign:'center',
				align:'left'
			},
			
			{
				title:'体重类型',
				field:'weighttypeName',
				width:100,
				halign:'center',
				align:'left'//,
				//12 离乳体重 13出生体重 14 当前体重
				//formatter: function(value,row,index){
				//	if ( value == '12' ){
				//		return "离乳体重";
				//	}else if( value == '13'){
				//		return "出生体重";
				//	}else if( value == '14'){
				//	    return "当前体重";
				//	}
				//}
			},
			{
				title:'主管',
				field:'boss',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'保定人员',
				field:'protector',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'记录员',
				field:'recorder',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'称重人员',
				field:'protector',
				width:100,
				halign:'center',
				align:'left'
			},
			{
				title:'备注',
				field:'remark',
				width:200,
				halign:'center',
				align:'left'
			}
			]],
			onSelect:function(rowIndex, rowData){
				if(rowData.weighttypeName=="当前体重"){
			    	$('#editIndividualButton').linkbutton('enable');
			    	$('#deleteIndividualButton').linkbutton('enable');
			    }else{
			    	$('#editIndividualButton').linkbutton('disable');
			    	$('#deleteIndividualButton').linkbutton('disable');
			    }
			},
			onLoadSuccess:function(data){
			},
			toolbar:'#onetoolbar',
			pageSize : 15,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	}); //end datagrid
	   	
	   	 //搜索框
		   $('#searchContract').searchbox({ 
			    height:19,
			    width:450,
				searcher:function(value,name){ 
				    $('#weightOneTable').datagrid({
			          title:"动物编号："+value+ ':个体生长记录',
			          url:sybp()+'/weightAction_loadOneDate.action?monkeyid='+value});
					 
				}, 
				prompt:'模糊查询,请输入动物编号' 
			}); 
		    
		    $('#startDate').datebox({
		     onSelect: function(date){
		        var  type =  $('#weighttype').combobox('getValue');
		       var endDate =  $('#endDate').datebox('getValue');
			        var startDate =  $('#startDate').datebox('getValue');
			     $('#weightTable').datagrid({  url : sybp()+'/weightAction_loadDate.action?startDate='+startDate+'&endDate='+endDate+'&weighttype='+type}); 
		      }
		  });
	     $('#endDate').datebox({
	         onSelect: function(date){
	             var  type =  $('#weighttype').combobox('getValue');
		        var endDate =  $('#endDate').datebox('getValue');
			        var startDate =  $('#startDate').datebox('getValue');
			     $('#weightTable').datagrid({  url : sybp()+'/weightAction_loadDate.action?startDate='+startDate+'&endDate='+endDate+'&weighttype='+type}); 
		     }   
		 });
		    $('#weighttype').combobox({    
			   onSelect:function(record){
			     var  type = record.id ;
			      var endDate =  $('#endDate').datebox('getValue');
			        var startDate =  $('#startDate').datebox('getValue');
			     $('#weightTable').datagrid({  url : sybp()+'/weightAction_loadDate.action?startDate='+startDate+'&endDate='+endDate+'&weighttype='+type});  
			   }
			});  
		    
		
		
		/**加载下拉选*/
		 $('#theBoss').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployeeNo.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		    }  
		 }); 
		 
		 $('#theProtector').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployeeNo.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		    }  
		 });
		  $('#theRecorder').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployeeNo.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		    }  
		 });
		  $('#theOperater').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployeeNo.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		    }  
		 });
		 $('#theWeighttype').combobox({    
		    url:sybp()+'/weightAction_getWeightTypeMap.action',
		    valueField:'id',    
		    textField:'text',
		    onSelect:function(record){
		    }  
		 });
		 
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束

     /**添加操作*/
   function onAddButton(){
      document.individualAddEditForm.reset();
       document.getElementById("monkeyid").readOnly=false;
      showWeightAddEditDialog('afterAddDialog','add','登记体重');
   }
   
   
   //显示dialog
   function showWeightAddEditDialog(clickName,addOrEdit,title){
        document.getElementById("addWeightAddEditDialog2").style.display="block";
        $('#addOrEdit').val(addOrEdit);
	    $('#addWeightAddEditDialog').dialog('setTitle',title);
	    $('#addWeightAddEditDialog').dialog('open'); 
	    document.getElementById("weightAddEdit_event").href="javascript:"+clickName+"();";
   }
   
  function afterAddDialog(){
      $('#addWeightAddEditDialog').dialog('close'); 
      $('#weightTable').datagrid({
			url : sybp()+'/weightAction_loadDate.action',
			pageSize : 5,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 15, 20 ]//可以选择的分页集合       
			
	  });
      
  }
   
  function onDialogSaveButton(){
      if( $('#addWeightAddEditForm').form('validate') ){
				$('#saveDialogButton').linkbutton('disable');
				if($('#addOrEdit').val() == 'add'){
					$.ajax({
						url:sybp()+'/weightAction_addSave.action',
						type:'post',
						data:$('#addWeightAddEditForm').serialize(),
						dataType:'json',
						success:function(r){
							$('#saveDialogButton').linkbutton('enable');
							if(r && r.success){
							   parent.parent.showMessager(1,'添加成功',true,5000);
							   document.getElementById("weightAddEdit_event").click();
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
					});
				}else{
					$.ajax({
						url:sybp()+'/weightAction_editSave.action',
						type:'post',
						data:$('#addWeightAddEditForm').serialize(),
						dataType:'json',
						success:function(r){
							$('#saveDialogButton').linkbutton('enable');
							if(r && r.success){
							    parent.parent.showMessager(1,'编辑成功',true,5000);
							    document.getElementById("weightAddEdit_event").click();
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
					});
				}
				
			}
     
  }
  
  /**删除*/
   function onDeleteButton(){
     var row = $('#weightTable').datagrid('getSelected');
     	$.ajax({
						url:sybp()+'/weightAction_del.action',
						type:'post',
						data:{id:row.id},
						dataType:'json',
						success:function(r){
							if(r && r.success){
							   parent.parent.showMessager(1,'删除成功',true,5000);
							   $('#weightTable').datagrid('reload');
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
		});
   }
   /**编辑*/
   function onEditButton(){
       document.individualAddEditForm.reset();
       showWeightAddEditDialog('afterEditDialog','edit','编辑体重');
       var row = $('#weightTable').datagrid('getSelected');
        fullByid(row);
      
   }
   
   /**编辑后执行*/
   function afterEditDialog(){
      $('#addWeightAddEditDialog').dialog('close'); 
       var row = $('#weightTable').datagrid('getSelected');
       $('#weightTable').datagrid('reload');
   }
   
   /**根据id填充*/
   function  fullByid(row){
      $('#monkeyid').val(row.monkeyid);
      document.getElementById("monkeyid").readOnly=true;
      $('#inid').val(row.id);
      $('#theWeighttype').combobox('select',row.weighttype);
      $('#theWeight').val(row.weight);
      $('#theWeightdate').datebox('setValue', row.weightdate);
      $('#theBoss').combobox('select',row.boss);
      $('#theProtector').combobox('select',row.protector);
      $('#theRecorder').combobox('select',row.recorder);
      $('#theOperater').combobox('select',row.operater);
      $('#theRemark').val(row.remark);
   }
</script>
</head>
<body>
<s:hidden id="onchange" name="onchange"></s:hidden>
<!-- 选择id -->
<s:hidden id="selectId" name="selectId"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="生长记录" border="false" style="overflow: auto;">
				<table  id="weightTable" ></table>
				<table  id="weightOneTable" ></table>
            </div>
		</div>
        <div id="toolbar">
           
			<a id="addIndividualButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-monkeyadd',plain:true">添加</a>
			<a id="editIndividualButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-monkeyedit',plain:true,disabled:true">编辑</a>
			<a id="deleteIndividualButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-monkeydel',plain:true,disabled:true">删除</a>
		   <!--  体重类型 12 离乳体重 13出生体重 14 当前体重 -->
		   <!-- <select id="weighttype" class="easyui-combobox" name="weighttype" style="width:80px;"  data-options="panelHeight:100" >  
			                <option value="-1">全部</option>   
						    <option value="12">离乳体重</option>   
						    <option value="13">出生体重</option>   
						    <option value="14">当前体重</option>   
		     </select> -->
		     称重类型&nbsp;<input id="weighttype" name="weighttype"/>
		       &nbsp;&nbsp;&nbsp; <a >称重日期范围&nbsp;&nbsp; &nbsp;&nbsp;<input  style="width:120px;" id="startDate" type="text" name="startDate"  class="easyui-datebox"  data-options="editable:false" /></a>
			 <a>&nbsp;&nbsp;&nbsp;&nbsp;~~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  style="width:120px;" id="endDate" type="text"  name="endDate"  class="easyui-datebox"  data-options="editable:false" /></a>
		</div>
	    <div id="onetoolbar">
		 		<input id="searchContract" ></input> 
		</div>
 	</div>
</div>
  <%@include file="/WEB-INF/jsp/weightAction/addWeight.jspf" %>
</body>
</html>