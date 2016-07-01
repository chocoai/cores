<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	
	function addQACheckItemAndQAFileReg()
	{
		var fileRows = $('#QAFileRegs').datagrid("getSelections");
		if(fileRows!=null&&fileRows!='')
		{
				var itemRow = $('#QACheckItems').datagrid("getSelected");
				if(itemRow!=null&&itemRow!='')
				{
					var fileRegIds = new Array();
					for(var i=0;i<fileRows.length;i++)
					{
						fileRegIds.push(fileRows[i].fileRegId);
					}
					//更新试验分类和文件登记表
					$.ajax({
						url:sybp()+'/dictChkItemQAFileRegAction_save.action?chkItemId='+itemRow.chkItemId+"&fileRegIds="+fileRegIds,
						type:'post',
						//data:'',
						dataType:'json',
						success:function(r){
							
							if(r!=null&&!r.success)
							{
								$.messager.alert('提示',r.msg,'info');
							}
							$('#checkItemAndQAFileTable').datagrid('reload');
						}
					});				
				}else
				{
					$.messager.alert("提示","请选择一个检查项！");
				}
			
		}else
		{
			$.messager.alert("提示","请选择一个文件！");
		}
		
	}
	function removeQACheckItemAndQAFileReg()
	{
		var regRow = $('#checkItemAndQAFileTable').datagrid("getSelected");
		
		if(regRow!=null)
		{
			var index = $('#checkItemAndQAFileTable').datagrid("getRowIndex",regRow);
				$.ajax({
					url:sybp()+'/dictChkItemQAFileRegAction_remove.action?chkItemQAFileRegId='+regRow.chkItemChkTblRegId,
					type:'post',
					//data:'',
					dataType:'json',
					success:function(r){
						if(r && r.success){
							$('#checkItemAndQAFileTable').datagrid('deleteRow', index);
							//$('#checkItemAndScheduleTable').datagrid('reload');
						}else{
							if(r!=null&&!r.success)
							{
								$.messager.alert('提示',r.msg,'info');
							}
						}
					}
				});				
			
		}else
		{
			$.messager.alert("提示","请选择要移出的项！");
		}
	}
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		var tableSize ;
		var tableHeight = document.body.clientHeight - 80;   //datagrid 高度
		var tableWidth = document.body.clientWidth;

		
		$('#QACheckItems').datagrid({
			url : sybp()+"/dictQACheckItemAction_loadList.action",
			title:'',
			iconCls:'',//'icon-save',
			singleSelect:true,//只能选一行
			pagination:false,//下面状态栏
			height:tableHeight,
			//width:tableWidth,  
			//fit:true,
			//showHeader:false,//不显示头部
			fitColumns:false,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			border:false,
			idField:'chkItemId', //pk
			//toolbar:'#toolbar',
			columns :[[
				{title :'',field :'chkItemId',hidden:true}
				,{title :'',field :'studyGroupId',hidden:true}
				,{title:'分类',field :'chkItemType',width:100,halign:'center',
					formatter: function(value,row,index){
						if (value==1){
							return "方案";
						} 
						if (value==2){
							return "报告";
						} 
						if (value==3){
							return "变更";
						} 
						if (value==4){
							return "基于研究的检查项";
						} 
					}
				}
				,{title:'检查项名称',field :'chkItemName',width:120,halign:'left'}
			
			]],
			onSelect:function(rowIndex,rowData){
			 	$('#checkItemAndQAFileTable').datagrid({
				 	url:sybp()+'/dictChkItemQAFileRegAction_loadListByChkItem.action?chkItemId='+rowData.chkItemId,
				});
			}
			
		});
		//法规
		$('#QAFileTypes').tree({
			
			url: sybp()+'/qAFileTypeAction_loadList.action',
			onSelect: function(node){
				$('#QAFileRegs').datagrid({
					height:tableHeight,
					url:sybp()+'/qAFileRegAction_loadListByType.action?fileTypeId='+node.id,
				});
			}
		});
		$('#QAFileRegs').datagrid({
			columns:[[
			       //  "fileRegId", "fileType", "fileTypeName","fileCode","fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
			  	    
						{
						    field:'fileRegId',
						    title:'',
						    hidden:true
						 },
						 {
					          field:'fileCode',
					          title:'文件编号',
					          width:100
					        
					      },
					       {
						          field:'fileName',
						          title:'文件名称',
						          width:150
						        
						   },

			]],

		});
		 $('#checkItemAndQAFileTable').datagrid({
			 	//url:sybp()+'/dictChkItemQAFileRegAction_loadList.action',
				//showHeader:false,//不显示头部
				singleSelect:true,
				height:tableHeight,
				columns:[[
							{
							    field:'chkItemChkTblRegId',
							    title:'',
							    hidden:true
							  
							 },
				          {
					          field:'chkItemName',
					          title:'检查项',
					          width:150
					        
					       },
					       {
						       	  title:"文件名称",
						          field:'fileName',
						          width:200
						         
						     },
					     ]]
	     });
		
		

	});

</script>
</head>
  
  <body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">检查项与检查依据</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			
	        <span style=" position:absolute;top:10px;left:10px;width:240px;">检查项列表：</span>	<br/>
			<div style=" position:absolute;top:30px;left:10px;bottom:22px;width:240px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="QACheckItems" class="easyui-datagrid"></div>
			</div>
			<span style=" position:absolute;top:10px;left:260px;width:480px;">法规列表：</span>	<br/>
			<div style=" position:absolute;top:30px;left:260px;bottom:22px;width:480px; border: 1px solid #c8c8c8; overflow-y: auto;">
					
				<div id="QAFileTypes" class="easyui-tree" style=" width:208px;height:99.5%; border: 1px solid #c8c8c8; overflow-y: auto; float: left;"></div>
				<div id="QAFileRegs" class="easyui-datagrid" style=" width:270px; border: 1px solid #c8c8c8; overflow-y: auto; float:left;"></div>
					
			</div>
			<div style="position:absolute;top:50px;left:730px;bottom:22px;width:35px;">
				<div style="position:absolute;top:50%;left:50%;">
					<button onclick="addQACheckItemAndQAFileReg();" style="width: 35px;">></button><br/><br/>
					<button onclick="removeQACheckItemAndQAFileReg();" style="width: 35px;"><</button>
				</div>	
			</div>
			<span style=" position:absolute;top:10px;left:785px;">检查项与法规：</span><br/>
			<div style=" position:absolute;top:30px;bottom:22px;left:785px;width:422px; border: 1px solid #c8c8c8; ">
				<div id="checkItemAndQAFileTable" style=" width:382px; border: 1px solid #c8c8c8; overflow-y: auto; float:left;"></div>
			</div>
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckItemAction/addOrEditQACheckItem.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckItemAction/selectQACheckItem.jspf"%>
		
  </body>
</html>
