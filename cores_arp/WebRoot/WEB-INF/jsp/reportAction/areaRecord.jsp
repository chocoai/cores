<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>饲养统计</title>
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
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showAreaTable();
		
		
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showAreaTable(){
    	$('#virusTable').datagrid({
			url : sybp()+'/areaAction_areaByJson.action',
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
				title:'幢号',
				field:'dong',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'房号',
				field:'room',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'公猴数量',
				field:'maleMonkeyCount',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'母猴数量',
				field:'femaleMonkeyCount',
				width:80,
				halign:'center',
				align:'center'
				
			},{
				title:'育成猴数量',
				field:'yuchengMonkeyCount',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'仔猴数量',
				field:'cubMonkeyCount',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'合计',
				field:'totalCount',
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
      
      function onPrintButton(){
    	parent.parent.showMessager(2,'数据加载中...',true,5000);
    	window.location.href=sybp()+'/areaAction_reportArea.action';
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
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="饲养数量" border="false" style="overflow: auto;">
                <div style="height:30px;">
			        <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    	<a id="printButton_list"  class="easyui-linkbutton" onclick="onPrintButton()" data-options="iconCls:'icon-print',plain:true">预览打印</a>
			    	</div>
			    </div>
				<table id="virusTable" ></table>
            </div>
		</div>
        <div id="toolbar">
		</div>
 	</div>
</div>
</body>
</html>