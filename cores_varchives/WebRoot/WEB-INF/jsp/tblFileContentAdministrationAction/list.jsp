<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员档案</title>
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
   	   	 	$('#tblFileContentAdministrationDatagrid').datagrid({
				//url : sybp()+'/tblFileContentStudyAction_loadList.action',
        		height:tableHeight,
				width:tableWidth,
				singleSelect:true,

				pagination:true,//分页
				pageNumber:1,
		        pageSize : 16,//默认选择的分页是每页5行数据         
		        pageList : [5,16,100,200,300,400,500],//可以选择的分页集合       
	            striped : true,//设置为true将交替显示行背景。      
	           // collapsible : true,//显示可折叠按钮 
	            idField:'fileRecordId',
	            
				nowrap:false,
				 frozenColumns:[[
					{
						title:'分类代号',
						field:'archiveTypeCode',
						hidden:false,
						width:100,
					},	
					{
						title:'档案编号',
						field:'archiveCode',
						hidden:false,
						width:100,
						formatter: function(value,row,index){
							if(row.fileRecordSn>0&&row.fileRecordSn<10){
								return value+'-00'+row.fileRecordSn;
							}else if(row.fileRecordSn>=10&&row.fileRecordSn<100){
								return value+'-0'+row.fileRecordSn;
							}else if(row.fileRecordSn>=100){
								return value+'-'+row.fileRecordSn;
							}else{
								return value;
							}
							
						}
					},
					{
						title:'题名',
						field:'archiveTitle',
						width:250,
						halign:'center',
						align:'left',
						
					},
					{
						title:'资料编号',
						field:'docCode',
						width:80,
						halign:'center',
						align:'left',
						
					},
				 ]],
				columns:[[
						{
							title:'id',
							field:'fileRecordId',
							hidden:true,
						},
						{
							title:'archiveTypeName',
							field:'archiveTypeName',
							hidden:true,
						},
						{
							title:'archiveMediaFlag',
							field:'archiveMediaFlag',
							hidden:true,
						},
						{
							title:'archiveMedia',
							field:'archiveMedia',
							hidden:true,
						},
						{
							title:'archiveMediaEleCode',
							field:'archiveMediaEleCode',
							hidden:true,
						},
						{
							title:'序号',
							field:'fileRecordSn',
							hidden:true,
						},
						{
							title:'类别代码',
							field:'docTypeFlag',
							width:90,
							/*
							formatter: function(value,row,index){
								if(value=="1")
								{
									return "GLP相关资料";	
								}
								if(value=="2")
								{
									return "外来文件";
								}
								if(value=="3")
								{
									return "内部发文";
								}
								if(value=="4")
								{
									return "人字头文件";
								}
								
							}*/
						},
						{
							title:'类别名称',
							field:'docTypeName',
							width:120,
							halign:'center',
							align:'left',
							
						},
						{
							title:'资料名称',
							field:'docName',
							width:150,
						},
						{
							title:'发文单位',
							field:'dispatchUnit',
							width:100,
							halign:'center',
							align:'left',
							
						},
						{
							title:'发文时间',
							field:'dispatchDate',
							width:80,
							halign:'center',
							align:'left',
							
						},
						{
							title:'收件人',
							field:'receiptMan',
							width:100,
							halign:'center',
							align:'left',
							
						},
						{
							title:'收件时间',
							field:'receiptDate',
							width:80,
							halign:'center',
							align:'left',
							
						},
						
						{
							title:'储存位置',
							field:'storePosition',
							width:100,
							halign:'center',
							align:'left',
						},
						{
							title:'归档时间',
							field:'fileDate',
							width:80,
							halign:'center',
							align:'left',
							
						},
						{
							title:'立卷人',
							field:'archiveMaker',
							width:60,
							halign:'center',
							align:'center',
						},
						{
							title:'归档人',
							field:'fileOperator',
							width:60,
							halign:'center',
							align:'center',
						},
						{
							title:'保存期限',
							field:'keepDate',
							width:80,
							halign:'center',
							align:'center',
						},
						{
							title:'销毁日期',
							field:'destoryDate',
							width:80,
							hidden:false,
						},
						{
							title:'验证记录',
							field:'validationFlag',
							width:50,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								if(value=="0")
								{
									return "";	
								}
								if(value=="1")
								{
									return "是";
								}
								
	         		
							}
						},
						
						{
							title:'备注',
							field:'remark',
							width:120,
							halign:'center',
							align:'center',
							
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
					
				},
				onDblClickRow:function(rowIndex, rowData){//查看
					parent.parent.newOneAdministration(4);
				},
				rowStyler: function(index,row){
					if(row.destoryDate!=null&&row.destoryDate!=''){
						return 'color:#f00;';
					}
					
					else if(row.validationFlag!=null&&row.validationFlag!='')
					{
						return 'color:#0f0;';
					}
					
					
				}
			
				
        	});
   	   		var pager = $('#tblFileContentAdministrationDatagrid').datagrid('getPager');    // get the pager of datagrid
   	   		var opts = $('#tblFileContentAdministrationDatagrid').datagrid('options');
        	pager.pagination({
	          showRefresh:false,
	          loading:true,
	          onSelectPage:function(pageNum, pageSize){
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				/*pager.pagination('refresh',{
					pageNumber:pageNum,
					pageSize:pageSize
				});*/
				var contentWind = parent.parent.document.getElementById('archiveLeftFrame').contentWindow;
				if(contentWind.$)
				 {
					 //contentWind.searchRecord();
					contentWind.$('#searchRecordButton').click();
				 }
			  }
	        });
        	
			 var contentWind = parent.parent.document.getElementById('archiveLeftFrame').contentWindow;
			 if(contentWind.$)
			 {
				 //contentWind.searchRecord();
				//加载成功以后点击左边的查询
				contentWind.$('#searchRecordButton').click();
		    		
			 }

			parent.parent.$('#oneArchiveMaker6').combobox({
				 valueField:'realName',
				 textField:'realName',
				 editable:false,
	 			 url:sybp()+'/tblFileContentStudyAction_getUserList.action'			 
			 });
			 parent.parent.$('#receiptMan').combobox({
				 valueField:'name',
				 textField:'name',
				 editable:false,
				 url:sybp()+'/tblFileContentAdministrationAction_getUserList.action'				 
			 });
			 parent.parent.$('#oneStorePosition6').combotree({
				 valueField:'text',
				 textField:'text',
				 editable:true,
				 url:sybp()+'/tblFileContentStudyAction_loadArchivePositionTree.action',	
				 onSelect:function(n){
					parent.parent.hasOtherInThisPlace(n.text);
				 }
			 });
			
			 parent.parent.$('#docTypeFlag').combobox({
				 width:100,
				 valueField:'docTypeFlag',
				 textField:'docTypeName',
				 editable:false,
				 url:sybp()+'/tblFileContentAdministrationAction_getDocTypeList.action',
				 onSelect: function(rec){
				 	parent.parent.$('#docTypeName').val(rec.docTypeName);
				 }
			 });
			
        });//匿名函数结束
      
      	
        
       
</script>
	  
</head>

<body >
	<div class="container">
          
       <div style="display:block; position:absolute;top:0px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:hidden;" >
 	 	 
 	 	 <div id="tblFileContentAdministrationDatagrid" class="easyui-datagrid" style="border: 1px solid #c8c8c8;" border="false" >
 	 
		 </div>
		
 	  </div>
       
      <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
      <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
     
</body>
</html>




