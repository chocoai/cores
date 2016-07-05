<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>盘点管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/salemonkeyAction/salemonkeyAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#keeper').combobox({    
			url : sybp()+'/areaAction_getAllKeeper.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			},
			onSelect:function(rec){
//				$('#building').combobox('setValue','');
				showSalemonkeyTable(rec.id);
				$('#inventoryDate').datebox('setValue','');
			}
		});
		$('#building').combobox({    
			url : sybp()+'/areaAction_getAllBuilding.action',
		    valueField:'id',    
		    textField:'text',
		    editable:false,
		    onLoadSuccess:function(none){
			  
			},
			onSelect:function(rec){
				if(rec !=''){
					showSalemonkeyTable(rec.id);
				}
				
			}
		});
		
				
		//初始化盘点信息表
		showSalemonkeyTable('');
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    //加载盘点数据，初始化table
    function showSalemonkeyTable(keeperId){
    	$('#inventoryTable').datagrid({
			url : sybp()+'/areaAction_loadListByKeeper.action?keeper='+keeperId,
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			sortName:'id',
			columns :[[{
				title:'房间名',
				field:'areaname',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'应盘公猴数量',
				field:'maleMonkeyCount',
				width:90,
				halign:'center',
				align:'center'
			},{
				title:'实盘公猴数量',
				field:'realmaleMonkeyCount',
				width:90,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: { } }
			},{
				title:'应盘母猴数量',
				field:'femaleMonkeyCount',
				width:90,
				halign:'center',
				align:'center'
			},{
				title:'实盘母猴数量',
				field:'realfemaleMonkeyCount',
				width:90,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: { } }
			},{
				title:'应盘育成猴数量',
				field:'yuchengMonkeyCount',
				width:90,
				halign:'center',
				align:'center'
			},{
				title:'实盘育成猴数量',
				field:'realyuchengMonkeyCount',
				width:90,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: { } }
			},{
				title:'应盘仔猴数量',
				field:'cubMonkeyCount',
				width:90,
				halign:'center',
				align:'center'
			},{
				title:'实盘仔猴数量',
				field:'realcubMonkeyCount',
				width:90,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: { } }
			},{
				title:'应盘合计',
				field:'totalCount',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'实盘合计',
				field:'realtotalCount',
				width:80,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: { } }
			}
			]],
			onSelect:function(rowIndex, rowData){
    		
//    		  $('#editDeathButton').linkbutton('enable');
//    		  $('#deleteDeathButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
				var keeper=$('#keeper').combobox('getValue');
				if(keeper!=null&&keeper!=''){
					$('#saveInventoryRecordButton').linkbutton('enable');
					$('#exportExcel').linkbutton('enable');
				}else{
					$('#saveInventoryRecordButton').linkbutton('disable');
					$('#exportExcel').linkbutton('disable');
				}
			    var rows=$("#inventoryTable").datagrid("getRows");
    			 for(var i=0;i<rows.length;i++){
      				var rowIndex = $('#inventoryTable').datagrid('getRowIndex', rows[i]);
      				$('#inventoryTable').datagrid('beginEdit', rowIndex);
      			} 
			},
			toolbar:'#toolbar'
			
	   	});
    }
	
	//保存按钮事件
    function onSaveInventoryRecordButton(){
    	var rows=$("#inventoryTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#inventoryTable').datagrid('getRowIndex', rows[i]);
			$('#inventoryTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#inventoryTable").datagrid("getRows");
    	var checkNumber=0;
    	for(var i=0;i<rows.length;i++){
        	var maleMonkeyCount=rows[i].maleMonkeyCount;
        	var realmaleMonkeyCount=rows[i].realmaleMonkeyCount;
        	var femaleMonkeyCount=rows[i].femaleMonkeyCount;
        	var realfemaleMonkeyCount=rows[i].realfemaleMonkeyCount;
        	var yuchengMonkeyCount=rows[i].yuchengMonkeyCount;
        	var realyuchengMonkeyCount=rows[i].realyuchengMonkeyCount;
        	var cubMonkeyCount=rows[i].cubMonkeyCount;
        	var realcubMonkeyCount=rows[i].realcubMonkeyCount;
        	var totalCount=rows[i].totalCount;
        	var realtotalCount=rows[i].realtotalCount;
        	if(maleMonkeyCount!=realmaleMonkeyCount || femaleMonkeyCount!=realfemaleMonkeyCount || 
        	   yuchengMonkeyCount!=realyuchengMonkeyCount || cubMonkeyCount!=realcubMonkeyCount || 	
        	   totalCount!=realtotalCount){
        		checkNumber=1;
        	}
    	}
    	if(checkNumber == 1){
    		$.messager.confirm('确认对话框', '确认保存此次盘点录入数据？当前有未填或者应盘实盘不一致的数据，未填将默认为0', function(r){
 				if (r){
 					saveInventoryRecord();
 				}else{
 					for(var i=0;i<rows.length;i++){
 	      				var rowIndex = $('#inventoryTable').datagrid('getRowIndex', rows[i]);
 	      				$('#inventoryTable').datagrid('beginEdit', rowIndex);
 	      			} 
 				}
 			});
    	}else{
    		$.messager.confirm('确认对话框', '是否确认保存？', function(r){
    			if (r){
 					saveInventoryRecord();
 				}else{
 					for(var i=0;i<rows.length;i++){
 	      				var rowIndex = $('#inventoryTable').datagrid('getRowIndex', rows[i]);
 	      				$('#inventoryTable').datagrid('beginEdit', rowIndex);
 	      			} 
 				}
    		})
    	}
    }
    //后台保存盘点记录，保存后打开记录
    function saveInventoryRecord(){
        var keeper=$('#keeper').combobox('getValue');
        var rows=$("#inventoryTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#inventoryTable').datagrid('getRowIndex', rows[i]);
			$('#inventoryTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#inventoryTable").datagrid("getRows");
        var realmaleMonkeyCounts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].realmaleMonkeyCount==''){
            	realmaleMonkeyCounts.push(0);   
            }else{
            	realmaleMonkeyCounts.push(rows[i].realmaleMonkeyCount); 
            }
        }
        var realfemaleMonkeyCounts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].realfemaleMonkeyCount==''){
            	realfemaleMonkeyCounts.push(0);   
            }else{
            	realfemaleMonkeyCounts.push(rows[i].realfemaleMonkeyCount); 
            }
        }
        var realyuchengMonkeyCounts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].realyuchengMonkeyCount==''){
            	realyuchengMonkeyCounts.push(0);   
            }else{
            	realyuchengMonkeyCounts.push(rows[i].realyuchengMonkeyCount); 
            }
        }
        var realcubMonkeyCounts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].realcubMonkeyCount==''){
            	realcubMonkeyCounts.push(0);   
            }else{
            	realcubMonkeyCounts.push(rows[i].realcubMonkeyCount); 
            }
        }
        var realtotalCounts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].realtotalCount==''){
            	realtotalCounts.push(0);   
            }else{
            	realtotalCounts.push(rows[i].realtotalCount); 
            }
        }
    	$.ajax({
   			url:sybp()+'/inventoryAction_saveRecord.action?keeper='+keeper+'&realmaleMonkeyCounts='
   			          +realmaleMonkeyCounts+'&realfemaleMonkeyCounts='+realfemaleMonkeyCounts+'&realyuchengMonkeyCounts='
   			          +realyuchengMonkeyCounts+'&realcubMonkeyCounts='+realcubMonkeyCounts+'&realtotalCounts='
   			          +realtotalCounts,
   			type:'post',
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					parent.showMessager(1,'盘点记录保存成功',true,5000);
   					$('#inventoryDate').datebox('setValue',r.inventoryDate);
   					onSearchButton();
   				}else{
   					parent.parent.showMessager(2,'盘点记录保存失败',true,5000);
   				}
   			}
   		});
    }
    //查看按钮事件，刷新table
   function onSearchButton(){
	   var inventoryDate=$('#inventoryDate').datebox('getValue');
	   var keeper=$('#keeper').combobox('getValue');
	   if(inventoryDate!=null&&inventoryDate!=''){
		   $('#inventoryTable').datagrid({
				url : sybp()+'/inventoryAction_loadListByKeeperAndInventoryDate.action?keeper='+keeper+'&inventoryDate='+inventoryDate,
				title:'',
				height:tableHeight,
				width:tableWidth,
				nowarp:  false,//单元格里自动换行
				singleSelect:true,
				fitColumns:false,
				sortName:'id',
				columns :[[{
					title:'房间名',
					field:'areaname',
					width:80,
					halign:'center',
					align:'center'
				},{
					title:'饲养员',
					field:'keeperName',
					width:70,
					halign:'center',
					align:'center'
				},{
					title:'应盘公猴数量',
					field:'maleMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'实盘公猴数量',
					field:'realmaleMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'应盘母猴数量',
					field:'femaleMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'实盘母猴数量',
					field:'realfemaleMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'应盘育成猴数量',
					field:'yuchengMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'实盘育成猴数量',
					field:'realyuchengMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'应盘仔猴数量',
					field:'cubMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'实盘仔猴数量',
					field:'realcubMonkeyCount',
					width:90,
					halign:'center',
					align:'center'
				},{
					title:'应盘合计',
					field:'totalCount',
					width:80,
					halign:'center',
					align:'center'
				},{
					title:'实盘合计',
					field:'realtotalCount',
					width:80,
					halign:'center',
					align:'center'
				},{
					title:'盘点日期',
					field:'inventoryDate',
					width:80,
					halign:'center',
					align:'center'
				}
				]],
				onSelect:function(rowIndex, rowData){
	    		
//	    		  $('#editDeathButton').linkbutton('enable');
//	    		  $('#deleteDeathButton').linkbutton('enable');
				},
				onLoadSuccess:function(data){
					$('#saveInventoryRecordButton').linkbutton('disable');
					$('#exportExcel').linkbutton('enable');
				},
				toolbar:'#toolbar'
				
		   	});
	   }else{
		   alert("请选择盘点日期");
	   }
   }
   //导出Excel
   function exportExcel(){
	   var rows=$("#inventoryTable").datagrid("getRows");
	   var keeper=$('#keeper').combobox('getValue');
	   var inventoryDate=$('#inventoryDate').datebox('getValue');
	   if(keeper!=null&&keeper!=''&&rows[0].inventoryDate==null){
		   var path = sybp()+'/inventoryAction_export.action?keeper='+keeper;
		   window.open(path,'target','param');
	   }else{
		   var path = sybp()+'/inventoryAction_export1.action?keeper='+keeper+'&inventoryDate='+inventoryDate;
		   window.open(path,'target','param');
	   }  
   }
</script>
</head>
<body>
<!-- 死亡登记记录id -->
<s:hidden id="did" name="did"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="盘点管理" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;饲养员 &nbsp;&nbsp;
			    		<input id="keeper"   style="width:100px;"></input>
			    	<!--   &nbsp;&nbsp;&nbsp;&nbsp; 栋舍
		                &nbsp;
			        	&nbsp;<input id="building" style="width:100px;"></input>	
			      	  	&nbsp;
			    		 -->  
			    	</div>
			    
			    </div>
				<table id="inventoryTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="saveInventoryRecordButton" class="easyui-linkbutton" onclick="onSaveInventoryRecordButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">保存</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			盘点日期：<input id="inventoryDate" class="easyui-datebox"/>
			<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">查看盘点记录</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="exportExcel" class="easyui-linkbutton" onclick="exportExcel();" data-options="iconCls:'icon-excel',plain:true">导出Excel</a>
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/salemonkeyAction/salemonkeyAddEdit.jspf" %>
</div>
</body>
</html>