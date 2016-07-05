<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>繁殖管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/weaningAction/weaningAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		//初始化离乳登记搜索框
		$('#SearchButton').searchbox({ 
		    height:19,
		    width:450,
			searcher:function(value,name){ 
			 var monkeyId=$('#searchmonkeyid').val();
          	 var startWeaningDate=$('#startweaningdate').datebox('getValue');
         	 var endWeaningDate=$('#endweaningdate').datebox('getValue');
		     showWeaningTable(monkeyId,startWeaningDate,endWeaningDate);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		//初始化离乳登记信息表
		showWeaningTable('','','');
		
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showWeaningTable(childmonkeyid,startweaningdate,endweaningdate){
    	$('#weaningTable').datagrid({
			url : sybp()+'/weaningAction_loadList.action?monkeyid='+childmonkeyid+'&startweaningdate='+encodeURIComponent(startweaningdate)+'&endweaningdate='+encodeURIComponent(endweaningdate),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			sortOrder:'desc',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'仔猴编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'离乳日期',
				field:'leavebreastdate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'离乳体重(kg)',
				field:'leavebreastweight',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'母猴编号',
				field:'motherid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'责任饲养员',
				field:'keeperp',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'档案录入',
				field:'recorderer',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'操作员',
				field:'operaterr',
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
    		  $('#addWeaningButton').linkbutton('enable');
    		  $('#editWeaningButton').linkbutton('enable');
    		  $('#deleteWeaningButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addWeaningButton').linkbutton('enable');
	    	   $('#editWeaningButton').linkbutton('disable');
	           $('#deleteWeaningButton').linkbutton('disable');
	           var selectid=$('#wid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#weaningTable').datagrid('selectRow',i);
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
    function onAddButton(){
    	showWeaningAddEditDialog('afterAddDialog','add','添加离乳登记记录');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#weaningTable').datagrid('reload');
    }
    //wan 编辑按钮事件
    function onEditButton(){
         var row= $('#weaningTable').datagrid('getSelected');
        if(row != null ){
        	showWeaningAddEditDialog('afterEditDialog','edit','编辑离乳登记记录');
        }else{
           $.messager.alert('提示','请选择编辑的离乳登记记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#weaningTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#weaningTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此离乳登记记录？', function(r){
 				if (r){
 					delWeaning(row.id,row.monkeyid);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的离乳登记记录!');
         }
      }
     //根据Id删除相应离乳登记(数据库还保存)
       function delWeaning(id,monkeyid){
   	   	$.ajax({
   			url:sybp()+'/weaningAction_delWeaning.action',
   			type:'post',
   			data:{id:id,monkeyid:monkeyid},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'记录删除成功',true,5000);
   					$('#weaningTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'记录删除失败',true,5000);
   				}
   			}
   		});
       } 
     function onSearchButton(){
          var monkeyId=$('#searchmonkeyid').val();
          var startWeaningDate=$('#startweaningdate').datebox('getValue');
          var endWeaningDate=$('#endweaningdate').datebox('getValue');
          if(startWeaningDate!=""&&endWeaningDate==""){parent.showMessager(2,'请选择查询结束日期！',true,5000);return;}
          if(startWeaningDate==""&&endWeaningDate!=""){parent.showMessager(2,'请选择查询开始日期！',true,5000);return;}
          showWeaningTable(monkeyId,startWeaningDate,endWeaningDate);
      }
</script>
</head>
<body>
<!-- 离乳登记记录id -->
<s:hidden id="wid" name="wid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="离乳登记" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;仔猴编号
			    		<input id="searchmonkeyid" type="text" name="monkeyid"   style="width:100px;"></input>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 离乳日期
		                &nbsp;
			        	&nbsp;<input id="startweaningdate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>至	
			      	  	<input id="endweaningdate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="weaningTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addWeaningButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editWeaningButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteWeaningButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/weaningAction/weaningAddEdit.jspf" %>
</div>
</body>
</html>