<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>体外寄生虫记录</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
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
			 var monkeyId=$('#searchmonkeyid').val();
          	 var parasitedate=$('#parasitedate').datebox('getValue');
		     showParasiteTable(monkeyId,parasitedate);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showParasiteTable('','');
		
		
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showParasiteTable(monkeyid,parasitedate){
    	$('#parasiteTable').datagrid({
			url : sybp()+'/parasiteAction_outParasiteByJson.action?monkeyid='+monkeyid+'&cdate='+encodeURIComponent(parasitedate),
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
				title:'房号',
				field:'roomid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'笼号',
				field:'lhao',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'猴子编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'性别',
				field:'sex',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'检疫编号',
				field:'checkId',
				width:120,
				halign:'center',
				align:'center'
			},{
				title:'样本号',
				field:'yangb',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'采样',
				field:'caiy',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'体外寄生虫(节肢动物)',
				field:'twjsc',
				width:180,
				halign:'center',
				align:'center'
			},{
				title:'备注',
				field:'remark',
				width:80
				
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

    function onAddButton(){
    	var row= $('#vaccineTable').datagrid('getSelected');
    	showWeaningAddEditDialog('afterAddDialog','add','添加离乳登记记录');
    }
    
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#weaningTable').datagrid('reload');
    }
    function onEditButton(){
         var row= $('#weaningTable').datagrid('getSelected');
        if(row != null ){
        	showWeaningAddEditDialog('afterEditDialog','edit','编辑离乳登记记录');
        }else{
           $.messager.alert('提示','请选择编辑的离乳登记记录!');
        }
    }
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#weaningTable').datagrid('reload');
     }
  
   
       function onDeleteButton(){
     	  var row= $('#weaningTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此离乳登记记录？', function(r){
 				if (r){
 					delWeaning(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的离乳登记记录!');
         }
      }
       function delWeaning(id){
   	   	$.ajax({
   			url:sybp()+'/weaningAction_delWeaning.action',
   			type:'post',
   			data:{id:id},
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
          var parasitedate=$('#parasitedate').datebox('getValue');
          showParasiteTable(monkeyId,parasitedate);
      }
      
      function onPrintButton(){
        var parasitedate=$('#parasitedate').datebox('getValue');
        var monkeyid=$('#searchmonkeyid').val();
    	parent.parent.showMessager(2,'数据加载中...',true,5000);
    	window.location.href=sybp()+'/parasiteAction_reportOutParasite.action?monkeyid='+monkeyid+'&parasitedate='+parasitedate;
    }
</script>
</head>
<body>
<s:hidden id="wid" name="wid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<!-- <div id="tabs" class="easyui-tabs" fit="true" border="false"> -->
            <div title="体外寄生虫检测记录" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;猴子编号：
			    		<input id="searchmonkeyid" type="text" name="monkeyid"   style="width:100px;"></input>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 采样日期：
		                &nbsp;
			        	&nbsp;<input id="parasitedate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>	
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="parasiteTable" ></table>
            </div>
		<!-- </div> -->
        <div id="toolbar">
			<!--<a id="addWeaningButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editWeaningButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteWeaningButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
			--><a id="printButton_list"  class="easyui-linkbutton" onclick="onPrintButton()" data-options="iconCls:'icon-print',plain:true">打印</a>
		</div>
 	</div>
</div>
</body>
</html>