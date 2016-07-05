<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流产登记</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/miscarriageAction/miscarriageAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#SearchButton').searchbox({ 
		    height:19,
		    width:450,
			searcher:function(value,name){ 
			 var monkeyId=$('#motherid').val();
          	 var startDate=$('#start').datebox('getValue');
         	 var endDate=$('#end').datebox('getValue');
		     showMiscarriageTable(monkeyId,startDate,endDate);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showMiscarriageTable('','','');
		
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showMiscarriageTable(motherid,startdate,enddate){
    	$('#miscarriageTable').datagrid({
			url : sybp()+'/miscarriageAction_loadList.action?monkeyid='+motherid+'&startdate='+encodeURIComponent(startdate)+'&enddate='+encodeURIComponent(enddate),
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
				title:'流产时间',
				field:'miscarriagedate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'责任兽医',
				field:'veterinarian',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'保定人员',
				field:'protector',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'流产原因',
				field:'remarks',
				width:180,
				halign:'center',
				align:'left'
			},{
				title:'记录人员',
				field:'recorder',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'档案录入',
				field:'operater',
				width:80,
				halign:'center',
				align:'left'
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#addMiscarriageButton').linkbutton('enable');
    		  $('#editMiscarriageButton').linkbutton('enable');
    		  $('#deleteMiscarriageButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addMiscarriageButton').linkbutton('enable');
	    	   $('#editMiscarriageButton').linkbutton('disable');
	           $('#deleteMiscarriageButton').linkbutton('disable');
	           var selectid=$('#mid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#miscarriageTable').datagrid('selectRow',i);
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

	
    function onAddButton(){
    	showMiscarriageAddEditDialog('afterAddDialog','add','添加流产登记记录');
    }
    
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#miscarriageTable').datagrid('reload');
    }
    function onEditButton(){
         var row= $('#miscarriageTable').datagrid('getSelected');
        if(row != null ){
        	showMiscarriageAddEditDialog('afterEditDialog','edit','编辑流产登记记录');
        }else{
           $.messager.alert('提示','请选择编辑的流产登记记录!');
        }
    }
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#miscarriageTable').datagrid('reload');
     }
  
   
       function onDeleteButton(){
     	  var row= $('#miscarriageTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此离流产记记录？', function(r){
 				if (r){
 					delMiscarriage(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的流产登记记录!');
         }
      }
       function delMiscarriage(id){
   	   	$.ajax({
   			url:sybp()+'/miscarriageAction_delMiscarriage.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'记录删除成功',true,5000);
   					$('#miscarriageTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'记录删除失败',true,5000);
   				}
   			}
   		});
       } 
     function onSearchButton(){
          var monkeyId=$('#motherid').val();
          var start=$('#start').datebox('getValue');
          var end=$('#end').datebox('getValue');
          if(start==""&&end!=""){$.messager.alert('提示','请选择查询开始日期!');return;}
          if(start!=""&&end==""){$.messager.alert('提示','请选择查询结束日期!');return;}
          showMiscarriageTable(monkeyId,start,end);
      }
</script>
</head>
<body>
<s:hidden id="mid" name="mid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="流产登记" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;母猴编号：
			    		<input id="motherid" type="text" name="motherid"  style="width:100px;"></input>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 流产时间：
		                &nbsp;
			        	&nbsp;<input id="start" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>至	
			      	  	<input id="end" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="miscarriageTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addMiscarriageButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editMiscarriageButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteMiscarriageButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/miscarriageAction/miscarriageAddEdit.jspf" %>
</div>
</body>
</html>