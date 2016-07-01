<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作日志</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>


<script type="text/javascript">

    	$(function(){
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight;
    		var tableWidth  = document.body.clientWidth-2;
   	   	 	$('#tblLog2Datagrid').datagrid({
				height:tableHeight,
				width:tableWidth,
				singleSelect:true,
				nowrap:false,

				pagination:true,//分页
				pageNumber:1,
		        pageSize : 16,//默认选择的分页是每页5行数据         
		        pageList : [5,16,100,200,300,400,500],//可以选择的分页集合       
	            striped : true,//设置为true将交替显示行背景。      
	           // collapsible : true,//显示可折叠按钮 
	           
				columns:[[
						{
							title:'id',
							field:'id',
							hidden:true,
						},
						{
							title:'时间',
							field:'operateTime',
							hidden:false,
							width:70,
						},
						{
							title:'baseGlpFlag',
							field:'baseGlpFlag',
							hidden:true,
						},
						
						{
							title:'档案类别',
							field:'archiveTypeFlag',
							hidden:false,
							width:80,
							formatter: function(value,row,index){
			//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10：标本
								if(value==1){
									return "专题";
								}else if(value==2){
									return "QA检查资料";
								}else if(value==3){
									return "SOP资料";
								}else if(value==4){
									if(row.baseGlpFlag==2){
										return "基建";
									}else{
										return "综合资料";
									}
								}else if(value==5){
									return "仪器资料";
								}else if(value==6){
									return "人员档案";
								}else if(value==7){
									return "行政综合资料";
								}else if(value==8){
									return "合同资料";
								}else if(value==9){
									return "供试品留样";
								}else if(value==10){
									return "标本管理";
								}
								
							}
						},
						{
							title:'档案编号',
							field:'archiveCode',
							hidden:false,
							width:150,
							formatter: function(value,row,index){
								var vL = value;
							
								if(row.fileRecordSn>0&&row.fileRecordSn<10){
									 vL = value+'-00'+row.fileRecordSn;
								}else if(row.fileRecordSn>=10&&row.fileRecordSn<100){
									vL = value+'-0'+row.fileRecordSn;
								}else if(row.fileRecordSn>=100){
									vL = value+'-'+row.fileRecordSn;
								}else{
									vL = value;
								}
								
							//searchArchiveRecord(logId,archiveTypeFlag,operateTypeFlag,fieldDesc,oldValue,newValue)
								return "<a href='javascript:searchArchiveRecord("+row.id+","+row.archiveTypeFlag+","+row.operateTypeFlag+",\""+row.fieldDesc+"\",\""+row.oldValue+"\",\""+row.newValue+"\");'>"+vL+"</a>";
							}
						},
						{
							title:'归档记录号',
							field:'fileRecordSn',
							width:50,
						},
						{//1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
							title:'操作类型标志',
							field:'operateTypeFlag',
							hidden:true,
							width:60,
						},
						{
							title:'操作类型',
							field:'operateType',
							width:60,
							halign:'center',
							align:'left',
						},
						{
							title:'操作内容',
							field:'fieldDesc',//fieldName
							hidden:false,
							width:120,
						},
						
						{
							title:'原值',
							field:'oldValue',
							hidden:false,
							width:100,
							
						},
						{
							title:'新值',
							field:'newValue',
							hidden:false,
							width:100,
							
						},
						
						{
							title:'操作者',
							field:'operator',
							width:60,
						},
						{
							title:'原因',
							field:'operateRsn',
							hidden:false,
							width:150,
						},
						
						
						/*
						{
							title:'操作',
							field:'keyWord',
							width:70,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								if(row.isSign=='true'||row.isSign==true)
								{
									return "删除";
								}else{
									return "<a href='javascript:delRecord("+row.chkRecordId+");'>删除</a>";
								}
							}
						},
						*/

				]],
				onLoadSuccess:function()
				{
					
				}
				
        	});
			
			 var contentWind = parent.parent.document.getElementById('archiveLeftFrame').contentWindow;
			 if(contentWind.$)
			 {
				 //contentWind.searchRecord();
				//加载成功以后点击左边的查询
				contentWind.$('#searchRecordButton').click();
		    		
			 }

			 
			 
			

				            
        });//匿名函数结束

        function searchArchiveRecord(logId,archiveTypeFlag,operateTypeFlag,fieldDesc,oldValue,newValue)
		{	
			
			/* 显示Dialog */
			document.getElementById("showLogOperateRecordDialog2").style.display="block";
			
			
			//operateTypeFlag 1：修改；2：删除；3：销毁；4：SOP作废；5：合同终止
			if(operateTypeFlag!=null){
				if(operateTypeFlag=='1'){
							
					if(oldValue==null||oldValue=='null')
						oldValue="空";
					if(newValue==null||newValue=='null')
						newValue="空";
						$('#logRecordChange').html("修改 "+fieldDesc+": 由 "+oldValue+" 修改为 "+newValue);
				}else if(operateTypeFlag=='2'){
					$('#logRecordChange').html("删除了该档案！");
				}else if(operateTypeFlag=='3'){
					$('#logRecordChange').html("销毁了该档案！");
				}else if(operateTypeFlag=='4'){
					$('#logRecordChange').html("SOP作废！");
				}else if(operateTypeFlag=='5'){
					$('#logRecordChange').html("合同终止！");
				}else if(operateTypeFlag=='6'){
					$('#logRecordChange').html("销毁了该供试品留样！");
				}else if(operateTypeFlag=='7'){
					$('#logRecordChange').html("销毁了该标本！");
				}else{
					 $('#logRecordChange').html("");
				}
			}
			$('#archiveRecordDetailDatagrid').propertygrid({
				showHeader:false,
				nowrap:false,
				url : sybp()+'/tblLogAction_getArchiveRecordDetailById.action?id='+logId,
			});
			$('#showLogOperateRecordDialog').dialog('setTitle','数据详情');
			$('#showLogOperateRecordDialog').dialog('open'); 
					 	
				 	
			

			
			
		}
     
      	
        
       
</script>
	  
</head>

<body >
	<div class="container">
       
       <div style="display:block; position:absolute;top:0px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:hidden;" >
 	 	 
 	 	 <div id="tblLog2Datagrid" class="easyui-datagrid" style="border: 1px solid #c8c8c8;" border="false" >
 	 
		 </div>
		
 	  </div>
       
      <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
      <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
      <%@ include file="/WEB-INF/jsp/tblLogAction/searchArchiveRecord.jspf"%>
	</div>
     
</body>
</html>




