<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>动物在库明细</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;		
		showAnimalTable('','');

		$('#layoutMainDiv').css('display','');
		$('#keeper').combobox({    
			url : sybp()+'/areaAction_getAllKeeper.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			},
			onSelect:function(rec){
				//showAnimalTable(rec.id);
			}
		});
		$('#blongarea').combobox({    
			url : sybp()+'/areaAction_getPareaNameId.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			},
			onSelect:function(rec){
				//showAnimalTable(rec.id);
			}
		});
		$('#keeper').combobox({
			onSelect:function(rec){
				var keep=rec.id;
				var blongarea=$('#blongarea').combobox('getValue');
				$('#animalTable').datagrid({url:sybp()+'/areaAction_animalByJson.action?keeper='+keep+'&blongarea='+blongarea});
			}
		});
		$('#blongarea').combobox({
			onSelect:function(rec){
				var keep=$('#keeper').combobox('getValue');
				var blong=rec.id;
				$('#animalTable').datagrid({url:sybp()+'/areaAction_animalByJson.action?keeper='+keep+'&blongarea='+blong});
			}
		});
    });
    function showAnimalTable(keeper,area){
    	$('#animalTable').datagrid({
			url : sybp()+'/areaAction_animalByJson.action?keeper='+keeper+'&blongarea='+area,
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
				title:'房间ID',
				field:'roomid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'房间名称',
				field:'roomname',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'动物编号',
				field:'monkeylist',
				width:980,
				halign:'center',
				align:'center'
			}	
			]],
			
			onSelect:function(rowIndex, rowData){
    		  $('#addTreasuryButton').linkbutton('enable');
   		  	  $('#editTreasuryButton').linkbutton('enable');
    		  $('#deleteTreasuryButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   
			   $('#addTreasuryButton').linkbutton('enable');
	    	   $('#editTreasuryButton').linkbutton('disable');
	           $('#deleteTreasuryButton').linkbutton('disable');
	           var selectid=$('#ttid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#xcgTable').datagrid('selectRow',i);
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
function onPrintButton(){
		var data=$('#animalTable').datagrid('getData');
        var area=$('#blongarea').combobox('getValue');
        var keeper=$('#keeper').combobox('getValue');
        if(keeper==""){
        	$.messager.alert('提示','请选择饲养人员!');
    		return;
    	}
    	if(data.total==0){
    		$.messager.confirm('提示','当前饲养员下所属房舍，动物信息均没有。是否还要预览打印',function(r){
    			if(r){
    				window.location.href=sybp()+'/areaAction_reportAnimal.action?keeper='+keeper+'&blongarea='+area;
    			}
    		});
    	}else{
        	parent.parent.showMessager(2,'数据加载中...',true,5000);
    		window.location.href=sybp()+'/areaAction_reportAnimal.action?keeper='+keeper+'&blongarea='+area;
     	}
}
function onSearchButton(){
          var area=$('#blongarea').combobox('getValue');
          var keeper=$('#keeper').combobox('getValue');
          showAnimalTable(keeper,area);
      }
</script>
</head>
<body>
<s:hidden id="ttid" name="ttid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="动物在库明细" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;饲养员
			    		<input id="keeper" type="text"  name=""  style="width:100px;"></input>
			    		&nbsp;&nbsp;区域
			    		<input id="blongarea" type="text"  name=""  style="width:100px;"></input>
			    		<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="animalTable" ></table>
            </div>
            </div>
        <div id="toolbar">
			<!--<a id="addTreasuryButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		  	<a id="editTreasuryButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteTreasuryButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	--><a id="printButton_list"  class="easyui-linkbutton" onclick="onPrintButton()" data-options="iconCls:'icon-print',plain:true">预览打印</a>
		</div>
 	</div>
</div>
</body>
</html>