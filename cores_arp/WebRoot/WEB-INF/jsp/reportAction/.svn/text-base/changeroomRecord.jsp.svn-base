<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>猴群调拨明细</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;		
		showChangeroomTable('','','','');

		$('#layoutMainDiv').css('display','');
		$('#ykeeper').combobox({    
			url : sybp()+'/changeroomAction_listAllYKeeper.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			},
			onSelect:function(rec){
				//showAnimalTable(rec.id);
			}
		});
		$('#xkeeper').combobox({    
			url : sybp()+'/changeroomAction_listAllXKeeper.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			},
			onSelect:function(rec){
				//showAnimalTable(rec.id);
			}
		});
		//点击自动进行搜索
		$('#ykeeper').combobox({
			onSelect:function(rec){
				var ykeep=rec.id;
				var xkeep=$('#xkeeper').combobox('getValue');
				var changeroomdate=$('#changeroomdate').datebox('getValue');
				var monkeyid=$('#monkeyid').val();
				$('#changeroomTable').datagrid({  url : sybp()+'/changeroomAction_changeroomByJson.action?ykeeper='+ykeep+'&xkeeper='+xkeep+'&monkeyid='+monkeyid+'&changeroomdate='+changeroomdate});
			}
		});
		$('#xkeeper').combobox({
			onSelect:function(rec){
				var ykeep=$('#ykeeper').combobox('getValue');
				var xkeep=rec.id;
				var changeroomdate=$('#changeroomdate').datebox('getValue');
				var monkeyid=$('#monkeyid').val();
				$('#changeroomTable').datagrid({url:sybp()+'/changeroomAction_changeroomByJson.action?ykeeper='+ykeep+'&xkeeper='+xkeep+'&monkeyid='+monkeyid+'&changeroomdate='+changeroomdate});
			}
		});
		$('#changeroomdate').datebox({
			onSelect:function(rec){
				var ykeep=$('#ykeeper').combobox('getValue');
				var xkeep=$('#xkeeper').combobox('getValue');
				var changeroomdate=$('#changeroomdate').datebox('getValue');
				var monkeyid=$('#monkeyid').val();
				$('#changeroomTable').datagrid({url:sybp()+'/changeroomAction_changeroomByJson.action?ykeeper='+ykeep+'&xkeeper='+xkeep+'&monkeyid='+monkeyid+'&changeroomdate='+changeroomdate}); 
			}
		});
    });
    function showChangeroomTable(monkeyid,changeroomdate,ykeeper,xkeeper){
    	$('#changeroomTable').datagrid({
			url : sybp()+'/changeroomAction_changeroomByJson.action?monkeyid='+monkeyid+'&changeroomdate='+changeroomdate+'&ykeeper='+ykeeper+'&xkeeper='+xkeeper,
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
				title:'猴号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'性别',
				field:'sex',
				width:80,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "公";
					}else if(value==1){
						return "母";
					}else{
						return "";
					}
				}
			},{
				title:'体重(kg)',
				field:'weight',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'原区域',
				field:'yareaname',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'原房舍',
				field:'yroomname',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'原笼号',
				field:'ylh',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'原饲养员',
				field:'ykeeper',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'现区域',
				field:'changeinarea',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'现房舍',
				field:'changeinroom',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'现笼号',
				field:'lhao',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'现饲养员',
				field:'xkeeper',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'调栏日期',
				field:'changeroomdate',
				width:120,
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
        var monkeyid=$('#monkeyid').val();
        var changeroomdate=$('#changeroomdate').datebox('getValue');
        var xkeeper=$('#xkeeper').combobox('getValue');
        var ykeeper=$('#ykeeper').combobox('getValue');
        parent.parent.showMessager(2,'数据加载中...',true,5000);
    	window.location.href=sybp()+'/changeroomAction_reportChangeroom.action?monkeyid='+monkeyid+'&changeroomdate='+changeroomdate+'&ykeeper='+ykeeper+'&xkeeper='+xkeeper;
     
}
function onSearchButton(){
          var monkeyid=$('#monkeyid').val();
          var changeroomdate=$('#changeroomdate').datebox('getValue');
          var xkeeper=$('#xkeeper').combobox('getValue');
          var ykeeper=$('#ykeeper').combobox('getValue');
          showChangeroomTable(monkeyid,changeroomdate,ykeeper,xkeeper);
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
            <div title="猴群调拨明细" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;猴子编号：
			    		<input id="monkeyid" type="text"  name=""  style="width:100px;"></input>
			    		&nbsp;&nbsp;调拨日期：
			    		<input id="changeroomdate" class="easyui-datebox" type="text"  style="width:100px;height:19px;" editable="false"></input>
			    		&nbsp;&nbsp;原饲养员：
			    		<input id="ykeeper" type="text"  name=""  style="width:100px;"></input>
			    		&nbsp;&nbsp;现饲养员：
			    		<input id="xkeeper" type="text"  name=""  style="width:100px;"></input>
			    		<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="changeroomTable" ></table>
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