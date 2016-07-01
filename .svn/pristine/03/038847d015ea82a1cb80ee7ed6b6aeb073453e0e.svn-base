<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SOP资料</title>
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
    		var tableHeight = document.body.clientHeight-30;
    		var tableWidth  = document.body.clientWidth-2;
   	   	 	
			$('#tblFileContentSOPDatagrid').treegrid({
				//url : sybp()+'/tblFileContentStudyAction_loadList.action',
        		height:tableHeight,
				width:tableWidth-30,
				singleSelect:true,
				nowrap:false,
				
				pagination:true,//分页
				pageNumber:1,
		        pageSize : 16,//默认选择的分页是每页5行数据         
		        pageList : [5,16,100,200,300,400,500],//可以选择的分页集合       
	            striped : true,//设置为true将交替显示行背景。      
	           // collapsible : true,//显示可折叠按钮 
	           
	             idField:'fileRecordId',
	             treeField:'soptypeCode',
						
	           frozenColumns:[[
					{
						title:'SOP类别',
						field:'soptypeCode',
						width:260,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if(value!=''&&value!=null)
							{
								return row.soptypeName+"("+value+")";
							}else{
								return "";
							}
							
						}
							
					},
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
						
							if(value!=''&&value!=null)
							{

								if(row.fileRecordSn>0&&row.fileRecordSn<10){
									return value+'-00'+row.fileRecordSn;
								}else if(row.fileRecordSn>=10&&row.fileRecordSn<100){
									return value+'-0'+row.fileRecordSn;
								}else if(row.fileRecordSn>=100){
									return value+'-'+row.fileRecordSn;
								}else{
									return value;
								}
								
							}else{
								if(typeof(row.sopflag)!='undefined'&&row.fileRecordId.substring(0,6)!='typeId'){
									if(row.sopflag==''||row.sopflag==null||row.sopflag=="0")
									{
										return "该SOP资料还没归档";	
									}else if(row.sopflag=="1")
									{
										return "该SOP表格还没归档";
									}
								}
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
						title:'SOP编号',
						field:'sopcode',
						width:100,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if(value!=''&&value!=null)
							{
								return value+"-"+row.sopver;
							}else{
								return "";
							}
							
						}
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
							title:'序号',
							field:'fileRecordSn',
							hidden:true,
						},
						
						
						{
							title:'SOP类别名称',
							field:'soptypeName',
							width:120,
							halign:'center',
							align:'left',
							hidden:true,
							
						},
						
						{
							title:'类型',
							field:'sopflag',
							width:50,
							halign:'center',
							align:'center',
							hidden:true,
							formatter: function(value,row,index){
								if(value==''||value==null||value=="0")
								{
									return "SOP资料";	
								}
								if(value=="1")
								{
									return "SOP表格";
								}
								
							}
						},
						{
							title:'SOP名称',
							field:'sopname',
							width:200,
							halign:'center',
							align:'left',
							
						},
						{
							title:'SOP版本',
							field:'sopver',
							width:60,
							halign:'center',
							align:'left',
							hidden:true,
						},
						{
							title:'SOP生效日期',
							field:'sopeffectiveDate',
							width:70,
							halign:'center',
							align:'left',
							
						},
						{
							title:'SOP作废日期',
							field:'sopinvalidDate',
							width:70,
							halign:'center',
							align:'left',
							
						},
						{
							title:'SOP是否作废',
							field:'isInvalid',
							hidden:true,
							
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
						},{
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
							
						},{
							title:'是否是最高版本',//1是 0 不是
							field:'delFlag',
							width:120,
							hidden:true,
							
						},
						
						{
							title:'文件',
							field:'sopfile',
							width:70,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								if(value==''||value==null)
								{
									return "";
								}else{
									return "<a href='javascript:downLoadOneSop("+row.fileRecordId+");'>下载</a>";
								}
							}
						},
						

				]],
				onLoadSuccess:function()
				{
					
				},
				onClickRow:function(rowData){
					if(rowData!=null&&rowData.children!=''&&rowData.children!=null&&typeof(rowData.children)!='undefined')
					{
						$(this).treegrid('unselect',rowData.fileRecordId);
					}else{
					
						if(rowData.sopflag==null||rowData.sopflag==''||rowData.sopflag==0)
						{
							$('#sopRelTableDiv').attr('title','SOP关联的表格');
						}else if(rowData.sopflag==1){
							$('#sopRelTableDiv').attr('title','表格关联的SOP');
						}
						
						if(rowData.sopflag==null)
							rowData.sopflag=0;
						$('#tblSOPTblLinkDatagrid').treegrid({
							 url:sybp()+'/tblFileContentSOPAction_loadRelationSOPList.action?sopcode='+rowData.sopcode+'&sopver='+rowData.sopver+'&sopflag='+rowData.sopflag,
						});
					}
				},
				onDblClickRow:function(rowIndex, rowData){//查看
					parent.parent.newOneSOP(4);
				},
				rowStyler: function(row){
					if(typeof(row.sopflag)!='undefined'){
					    if(row.archiveCode==null||row.archiveCode==''){//没有归档的没有档案号
							return 'color:#00f;';
						}else if(row.destoryDate!=null&&row.destoryDate!=''){
							return 'color:#f00;';
						}else if (row.sopinvalidDate!=null&&row.sopinvalidDate!=''){
							return 'color:#ff8c00;';
						}else if(row.validationFlag!=null&&row.validationFlag!='')
						{
							return 'color:#0f0;';
						}
						else if(row.delFlag!=null&&row.delFlag!=1)//1是最高版本，其余的是历史版本
						{
							//SOP作废的黄色（深），历史版本：灰色，已销毁的红色，验证的绿色。
							return 'color:#a9a9a9;';
						}
					}
					
				}
				
        	});

			$('#tblSOPTblLinkDatagrid').treegrid({
				//url : sybp()+'/tblFileContentStudyAction_loadList.action',
        		height:tableHeight-30,
				width:735,
				singleSelect:true,
				nowrap:false,
				toolbar: '#dialogToolbarForRel',
			 
	            idField:'fileRecordId',
	            treeField:'soptypeCode',
		        frozenColumns:[[
						{
							title:'SOP类别',
							field:'soptypeCode',
							width:230,
							halign:'center',
							align:'left',
							formatter: function(value,row,index){
								if(value!=''&&value!=null)
								{
									return row.soptypeName+"("+value+")";
								}else{
									return "";
								}
								
							}
								
						},
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
						
							if(value!=''&&value!=null)
							{

									if(row.fileRecordSn>0&&row.fileRecordSn<10){
										return value+'-00'+row.fileRecordSn;
									}else if(row.fileRecordSn>=10&&row.fileRecordSn<100){
										return value+'-0'+row.fileRecordSn;
									}else if(row.fileRecordSn>=100){
										return value+'-'+row.fileRecordSn;
									}else{
										return value;
									}
									
								
							}else{
								if(typeof(row.sopflag)!='undefined'&&row.fileRecordId.substring(0,6)!='typeId'){
									if(row.sopflag==''||row.sopflag==null||row.sopflag=="0")
									{
										return "该SOP资料还没归档";	
									}else if(row.sopflag=="1")
									{
										return "该SOP表格还没归档";
									}
								}
							}
							
						}
					},
					
					{
						title:'SOP编号',
						field:'sopcode',
						width:100,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if(value!=null&&value!='')
							{
								return value+"-"+row.sopver;
							}else{
								return "";
							}
							
						}
					},
					{
						title:'SOP生效日期',
						field:'sopeffectiveDate',
						width:70,
						halign:'center',
						align:'left',
						
					},
					{
						title:'SOP版本',
						field:'sopver',
						width:60,
						halign:'center',
						align:'left',
						hidden:true,
					},

	            ]],
				columns:[[
						{
							title:'linkId',
							field:'id',
							hidden:true,
						},
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
							title:'序号',
							field:'fileRecordSn',
							hidden:true,
						},
						
						
						{
							title:'SOP类别名称',
							field:'soptypeName',
							width:120,
							halign:'center',
							align:'left',
							hidden:true,
						},
						
						{
							title:'类型',
							field:'sopflag',
							width:50,
							halign:'center',
							align:'center',
							hidden:true,
							formatter: function(value,row,index){
								if(value==''||value==null||value=="0")
								{
									return "SOP资料";	
								}
								if(value=="1")
								{
									return "SOP表格";
								}
								
							}
						},
						{
							title:'SOP名称',
							field:'sopname',
							width:200,
							halign:'center',
							align:'left',
							
						},
						{
							title:'题名',
							field:'archiveTitle',
							width:250,
							halign:'center',
							align:'left',
							
						},
						
						{
							title:'SOP作废日期',
							field:'sopinvalidDate',
							width:70,
							halign:'center',
							align:'left',
							
						},
						{
							title:'SOP是否作废',
							field:'isInvalid',
							hidden:true,
							
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
						},{
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
							
						},{
							title:'是否是最高版本',//1是 0 不是
							field:'delFlag',
							width:120,
							hidden:true,
							
						},
						
						{
							title:'操作',
							field:'operate',
							width:70,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								
								return "<a href='javascript:delOneLink("+row.id+");'>删除</a>";
								
							}
						},
						

				]],
				onLoadSuccess:function()
				{
					
				},
				onClickRow:function(row){
					
					if(row!=null&&row.children!=''&&row.children!=null&&typeof(row.children)!='undefined')
					{
						$(this).treegrid('unselect',row.fileRecordId);
					}
				},
				onDblClickRow:function( rowData){//查看
					parent.parent.newOneSOP(4);
				},
				rowStyler: function(row){
					if(typeof(row.sopflag)!='undefined'){
						if(row.archiveCode==null||row.archiveCode==''){//没有归档的没有档案号
							return 'color:#00f;';
						}
						else if(row.destoryDate!=null&&row.destoryDate!=''){
							return 'color:#f00;';
						}
						else if (row.sopinvalidDate!=null&&row.sopinvalidDate!=''){
							return 'color:#ff8c00;';
						}
						
						else if(row.validationFlag!=null&&row.validationFlag!='')
						{
							return 'color:#0f0;';
						}
						else if(row.delFlag!=null&&row.delFlag!=1)//1是最高版本，其余的是历史版本
						{
							//SOP作废的黄色（深），历史版本：灰色，已销毁的红色，验证的绿色。
							return 'color:#a9a9a9;';
						}
					}
					
				}
				
        	});
			$('#addTblSopTblDatagrid').treegrid({
				//url : sybp()+'/tblFileContentStudyAction_loadList.action',
        		height:350,
				width:800,
				singleSelect:false,
				nowrap:false,
				
	            idField:'fileRecordId',
			    treeField:'soptypeCode',
	           frozenColumns:[[
					{
						title:'SOP类别',
						field:'soptypeCode',
						width:260,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if(value!=''&&value!=null)
							{
								return row.soptypeName+"("+value+")";
							}else{
								return "";
							}
							
						}
							
					},
					{
						title:'SOP类别名称',
						field:'soptypeName',
						width:120,
						halign:'center',
						align:'left',
						hidden:true,
							
					},{
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
						
							if(value!=''&&value!=null)
							{

								
									if(row.fileRecordSn>0&&row.fileRecordSn<10){
										return value+'-00'+row.fileRecordSn;
									}else if(row.fileRecordSn>=10&&row.fileRecordSn<100){
										return value+'-0'+row.fileRecordSn;
									}else if(row.fileRecordSn>=100){
										return value+'-'+row.fileRecordSn;
									}else{
										return value;
									}
									
								
							}else{
								if(typeof(row.sopflag)!='undefined'&&row.fileRecordId.substring(0,6)!='typeId'){
									if(row.sopflag==''||row.sopflag==null||row.sopflag=="0")
									{
										return "该SOP资料还没归档";	
									}else if(row.sopflag=="1")
									{
										return "该SOP表格还没归档";
									}
								}
							}
							
						}
					},
					
					{
						title:'SOP编号',
						field:'sopcode',
						width:100,
						halign:'center',
						align:'left',
						formatter: function(value,row,index){
							if(value!=''&&value!=null)
							{
								return value+"-"+row.sopver;
							}else{
								return "";
							}
							
						}
					},
					{
						title:'SOP版本',
						field:'sopver',
						width:60,
						halign:'center',
						align:'left',
						hidden:true,
					},
					{
						title:'SOP生效日期',
						field:'sopeffectiveDate',
						width:70,
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
							title:'序号',
							field:'fileRecordSn',
							hidden:true,
						},
						
						
						
						{
							title:'类型',
							field:'sopflag',
							width:50,
							halign:'center',
							align:'center',
							hidden:true,
							formatter: function(value,row,index){
								if(value==''||value==null||value=="0")
								{
									return "SOP资料";	
								}
								if(value=="1")
								{
									return "SOP表格";
								}
								
							}
						},
						{
							title:'SOP名称',
							field:'sopname',
							width:200,
							halign:'center',
							align:'left',
							
						},
						{
							title:'题名',
							field:'archiveTitle',
							width:250,
							halign:'center',
							align:'left',
							
						},
						
						{
							title:'SOP作废日期',
							field:'sopinvalidDate',
							width:70,
							halign:'center',
							align:'left',
							
						},
						{
							title:'SOP是否作废',
							field:'isInvalid',
							hidden:true,
							
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
						},{
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
							
						},{
							title:'是否是最高版本',//1是 0 不是
							field:'delFlag',
							width:120,
							hidden:true,
							
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
				onClickRow:function(row){
					if(row!=null&&row.children!=''&&row.children!=null&&typeof(row.children)!='undefined')
					{
						$(this).treegrid('unselect',row.fileRecordId);
					}
				},
				
				rowStyler: function(row){
					if(typeof(row.sopflag)!='undefined'){
						if(row.archiveCode==null||row.archiveCode==''){//没有归档的没有档案号
							return 'color:#00f;';
						}else if(row.destoryDate!=null&&row.destoryDate!=''){
							return 'color:#f00;';
						}
						else if (row.sopinvalidDate!=null&&row.sopinvalidDate!=''){
							return 'color:#ff8c00;';
						}
						
						else if(row.validationFlag!=null&&row.validationFlag!='')
						{
							return 'color:#0f0;';
						}
						else if(row.delFlag!=null&&row.delFlag!=1)//1是最高版本，其余的是历史版本
						{
							//SOP作废的黄色（深），历史版本：灰色，已销毁的红色，验证的绿色。
							return 'color:#a9a9a9;';
						}
					}
					
				}
				
        	});

			var pager = $('#tblFileContentSOPDatagrid').treegrid('getPager');    // get the pager of datagrid
		   	var opts = $('#tblFileContentSOPDatagrid').treegrid('options');
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
			
			 parent.parent.$('#soptypeCode').combotree({
					valueField:'id',
					textField:'text',
					url:sybp()+'/tblFileContentSOPAction_loadTree.action',
					required:true				 
				 });
			 parent.parent.$('#oneArchiveMaker2').combobox({
					 valueField:'realName',
					 textField:'realName',
					 editable:false,
		 			 url:sybp()+'/tblFileContentStudyAction_getUserList.action'			 
				 });
				 
			 parent.parent.$('#oneStorePosition2').combotree({
				 valueField:'text',
				 textField:'text',
				 editable:true,
				 url:sybp()+'/tblFileContentStudyAction_loadArchivePositionTree.action',	
				 onSelect:function(n){
					parent.parent.hasOtherInThisPlace(n.text);
				 }
			 });
	            
        });//匿名函数结束


        function buildSOPRel()
        {
        	/* 显示Dialog */
    		document.getElementById("addSopTableLinkDialog2").style.display="block";
    		var sop = $('#tblFileContentSOPDatagrid').treegrid('getSelected');
  			if(sop!=null&&sop!='')
  			{
  				var sopflag = sop.sopflag;
  				var sopFlagStr="";
  				var getSopFlag = "";
  				if(sopflag==''||sopflag==0){
  					sopFlagStr = "SOP资料";
  					getSopFlag = 1;
  				}else{
  					sopFlagStr = "SOP表格";
  					getSopFlag = 0;
  				}
  				$('#addTblSopTblDatagrid').treegrid({
  					url:sybp()+'/tblFileContentSOPAction_getAllvalidSopOrTbl.action?sopflag='+getSopFlag+'&sopcode='+sop.sopcode+'&sopver='+sop.sopver,
  	  			});
  	        	
    			$('#addSopTableLinkDialog').dialog('setTitle','添加'+sopFlagStr+'关联');
				$('#addSopTableLinkDialog').dialog('open'); 

  			}else{
				$.messager.alert('提示框','请选择一个SOP或表格！');
			}
        }
        
        function saveTblSOPTableLink()
        {
            var sop = $('#tblFileContentSOPDatagrid').treegrid('getSelected');
			if(sop!=null&&sop!='')
			{
	        	var rows = $('#addTblSopTblDatagrid').treegrid('getSelections');
				if(rows!=null&&rows.length>0)
				{
		        	var codes = new Array();
					for(var i=0;i<rows.length;i++){
						codes.push(rows[i].sopcode+"~"+rows[i].sopver);
					}
					
					 $.ajax({
				 	 	  	url : sybp()+'/tblFileContentSOPAction_saveTblSopTableLinkList.action?sopcode='+sop.sopcode+'&sopver='+sop.sopver+'&sopflag='+sop.sopflag+'&codes='+codes,
				 		  	type: 'post',
				 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				 		  //	data: $('#oneTblFileContentSOPForm').serialize(),
				 		  	dataType:'json',
				 		  	success:function(r){
				 		  		$('#addSopTableLinkDialog').dialog('close');
				 		  		
				 		  		$('#tblSOPTblLinkDatagrid').treegrid('reload');
				 		  	}
		
					 });
					 
				}else{
					$.messager.alert('提示框','请选择一个SOP或表格！');
				}
				 
			}else{
				$.messager.alert('提示框','请选择一个SOP或表格！');
			}
        	
        }
        
		function delOneLink(id){
			$('#delOneLinkId').val(id);
			qm_showQianmingDialog('afterSignDelOneLink');
		}
        function delOneSOPRel(){
        	var row = $('#tblSOPTblLinkDatagrid').treegrid('getSelected');
        	if(row==null||row=='')
        	{
            	$.messager.alert('提示框','请选择一个关联关系！');
            }else{
				//删除一个关系
				$('#delOneLinkId').val(row.id);
				qm_showQianmingDialog('afterSignDelOneLink');
            }
        }
      	function afterSignDelOneLink()
      	{
      		 $.ajax({
		 	 	  	url : sybp()+'/tblFileContentSOPAction_delOneSopTableLink.action?id='+$('#delOneLinkId').val(),
		 		  	type: 'post',
		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  //	data: $('#oneTblFileContentSOPForm').serialize(),
		 		  	dataType:'json',
		 		  	success:function(r){
		 		  		
		 		  		$('#tblSOPTblLinkDatagrid').treegrid('reload');
		 		  	}

			 });
        }

        function downLoadOneSop(fileRecordId)
        {
            //下载
        	window.location.href=sybp()+'/tblFileContentSOPAction_downloadSOPFile.action?fileRecordId='+fileRecordId;
			
        }
        function clickSopFlagRadio()
        {
        	 var contentWind = parent.parent.parent.document.getElementById('archiveLeftFrame').contentWindow;
	    	 contentWind.$('#searchRecordButton').click();
	    	 var sopf = $('input[name="sopflag"]:checked').val();
				if(sopf==0)
				{
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopcode').title="SOP编号";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','soptypeCode').title="SOP类别编号";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','soptypeName').title="SOP类别名称";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopname').title="SOP名称";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopver').title="SOP版本";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopeffectiveDate').title="SOP生效日期";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopinvalidDate').title="SOP作废日期";

					$("#tblFileContentSOPDatagrid").treegrid({});


					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopcode').title="表格编号";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','soptypeCode').title="表格类别编号";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','soptypeName').title="表格类别名称";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopname').title="表格名称";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopver').title="表格版本";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopeffectiveDate').title="表格生效日期";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopinvalidDate').title="表格作废日期";
					
					$("#tblSOPTblLinkDatagrid").treegrid({});

					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopcode').title="表格编号";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','soptypeCode').title="表格类别编号";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','soptypeName').title="表格类别名称";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopname').title="表格名称";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopver').title="表格版本";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopeffectiveDate').title="表格生效日期";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopinvalidDate').title="表格作废日期";
					
					$("#addTblSopTblDatagrid").treegrid({});

					$('#addSOPRelLabel').html("添加相关表格");
					$('#delSOPRelLabel').html("删除相关表格");
					
				}else if(sopf==1){
					
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopcode').title="表格编号";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','soptypeCode').title="表格类别编号";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','soptypeName').title="表格类别名称";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopname').title="表格名称";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopver').title="表格版本";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopeffectiveDate').title="表格生效日期";
					$("#tblFileContentSOPDatagrid").treegrid('getColumnOption','sopinvalidDate').title="表格作废日期";
					
					$("#tblFileContentSOPDatagrid").treegrid({});

					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopcode').title="SOP编号";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','soptypeCode').title="SOP类别编号";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','soptypeName').title="SOP类别名称";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopname').title="SOP名称";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopver').title="SOP版本";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopeffectiveDate').title="SOP生效日期";
					$("#tblSOPTblLinkDatagrid").treegrid('getColumnOption','sopinvalidDate').title="SOP作废日期";

					$("#tblSOPTblLinkDatagrid").treegrid({});
					
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopcode').title="SOP编号";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','soptypeCode').title="SOP类别编号";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','soptypeName').title="SOP类别名称";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopname').title="SOP名称";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopver').title="SOP版本";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopeffectiveDate').title="SOP生效日期";
					$("#addTblSopTblDatagrid").treegrid('getColumnOption','sopinvalidDate').title="SOP作废日期";

					$("#addTblSopTblDatagrid").treegrid({});

					$('#addSOPRelLabel').html("添加相关SOP");
					$('#delSOPRelLabel').html("删除相关SOP");

				}

				
        }
       
</script>
	  
</head>

<body >
	<div id="sopLayoutMainDiv" class="easyui-layout" style="width:100%;height:100%; display:'';">
        <s:hidden id="delOneLinkId"></s:hidden>
       <div data-options="region:'north',border:false," style="height:30px;">
 	 	 <div style="margin-left:10px;margin-top:10px;">
 	 	 	<label for="sopflag0">
 		 	 <input id="sopflag0" type="radio" name="sopflag" value="0" checked="checked" onclick="clickSopFlagRadio();"/>SOP资料
 	 	 	</label>
 	 	 	<label for="sopflag1">
	 	 	 <input id="sopflag1" type="radio" name="sopflag" value="1" onclick="clickSopFlagRadio()"/>SOP表格
 	 	 	</label>
 	 	 </div>
 	 	 
 	  </div>
 	  <div region="center" title="" style="overflow: hidden;" data-options="border:false" >
 	 	 <div id="tblFileContentSOPDatagrid" class="easyui-treegrid" style="border: 1px solid #c8c8c8;margin-top:5px;" border="false" >
		 </div>
	  </div>
		
	 <div id="sopRelTableDiv" data-options="region:'east',title:'SOP关联关系',collapsed:true,border:false,iconCls:'icon-attach'"  style="width:740px;">
	 	  <table id="tblSOPTblLinkDatagrid" ></table>
	 	  <div id="dialogToolbarForRel">
		      <a id="buildSOPRelButton" class="easyui-linkbutton" onclick="buildSOPRel();" data-options="iconCls:'icon-add',plain:true" ><span id="addSOPRelLabel">添加相关表格</span></a>
		      <a id="delSOPRelButton" class="easyui-linkbutton" onclick="delOneSOPRel();" data-options="iconCls:'icon-remove',plain:true"><span id="delSOPRelLabel">删除相关表格</span></a>
		      <a id="backButton" class="easyui-linkbutton" onclick="$('#sopLayoutMainDiv').layout('collapse','east');" data-options="iconCls:'icon-nook',plain:true">关闭</a>
	      </div>
	 </div> 
       
      <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
      <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
      
       <%@ include file="/WEB-INF/jsp/tblFileContentSOPAction/addTblSOPTableLink.jspf"%>
	</div>
     
</body>
</html>




