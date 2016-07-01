<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>最新消息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js"></script>
<script type="text/javascript">
	
	function reflushDateGrid()
	{
		var start = $('#reportStartTime').datebox('getValue');
		var end = $('#reportEndTime').datebox('getValue');
		var searchCondition = $('#searchCondition').searchbox('getValue');
		 $('#newestDatagrid').datagrid({
			url:sybp()+'/qAChkReportAction_getReportNewestList.action?startDate='+start+'&endDate='+end+'&searchCondition='+searchCondition,	
		 });
	}

	
</script>

<script type="text/javascript">

    	$(function(){
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight-57;
    		var tableWidth  = document.body.clientWidth;

			var childTableWidth = tableWidth-10;

			$('#reportStartTime').datebox('setValue',$('#startTime').val());
			$('#reportEndTime').datebox('setValue',$('#endTime').val());
			
			$('#searchCondition').searchbox({ 
				height:19,
				width:300,
				searcher:function(value,name){ 
					reflushDateGrid();
				},
				prompt:'模糊查询,请输入专题或者报告编号名称',
			});
				

			$('#reportStartTime').datebox({
   				 onSelect: function(rec){
					reflushDateGrid();
   				 }
	    	});
			$('#reportEndTime').datebox({
				 onSelect: function(rec){
					reflushDateGrid();
				 }
		    });
			 $('#newestDatagrid').datagrid({
	        		height:tableHeight,
					width:childTableWidth,
					singleSelect:true,
					url:sybp()+'/qAChkReportAction_getReportNewestList.action?startDate='+$('#startTime').val()+'&endDate='+$('#endTime').val(),
					// linkId,eslink.[recordTime],[chkReportCode],[studyNo],eslink.[esTypeDesc],es.signer
					columns:[[
								
							{
								title:'linkId',
								field:'linkId',
								hidden:true,
							},
							{
								title:'时间',
								field:'recordTime',
								width:120,
							},
							{
								title:'专题编号',
								field:'studyNo',
								width:100,
							},
							
							{
								title:'检查报告编号',
								field:'chkReportCode',
								width:120,
							},
							{
								title:'内容',
								field:'esTypeDesc',
								width:250,
							},
							{
								title:'操作者',//签字者
								field:'signer',
								width:80,
							},
							
							
						]],
						onSelect:function(rowIndex,rowData)
						{
				 			
						}
	        		
	            });
	        
	            
        });//匿名函数结束
      
	 
</script>
	  
</head>

<body >
	<div class="container">
   
		<s:hidden id="startTime" name="startTime"></s:hidden>
		<s:hidden id="endTime" name="endTime"></s:hidden>
		
		<div style="float:left;position:relative;left: 10px;height:30px; margin-top:5px;">
			时间<select data-options="editable:false" id="reportStartTime" class="easyui-datebox" style="width:100px;">
			  </select>~<select data-options="editable:false" id="reportEndTime" class="easyui-datebox" style="width:100px;">
			  </select>
			  &nbsp;
			<span style="position:absolute;left:240px; top:1px;">
			  <input id="searchCondition" class="easyui-searchbox" ></input>
			</span>
		 </div>
		 
		<div style="float:left;position:absolute;left: 10px;top:30px;">
			<div id="newestDatagrid" class="easyui-datagrid" style="position:relative; margin-top:65px;"></div>
		</div>	 
 	</div>
 
  
   <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</body>
</html>




