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
	
	function addQACheckItemAndSchedule()
	{
		var scheduleRow = $('#scheduleList').datagrid("getSelected");
		if(scheduleRow!=null)
		{
				var itemRow = $('#QACheckItems').datagrid("getSelected");
				if(itemRow!=null)
				{
					
					//更新试验分类和日程登记表
					$.ajax({
						url:sybp()+'/dictScheduleChkItemAction_save.action?chkItemId='+itemRow.chkItemId+"&id="+scheduleRow.id,
						type:'post',
						//data:'',
						dataType:'json',
						success:function(r){
							if(r && r.success){
								
								$('#checkItemAndScheduleTable').datagrid('appendRow', 
								{
									scheduleChkItemId:r.scheduleChkItemId,
									chkItemName:r.chkItemName,
									taskName:r.taskName
									
								});
							//	$('#studyGroupAndCheckItemTable').datagrid('reload');
							}else{
								if(!r.success)
								{
									$.messager.alert('提示',r.msg,'info');
								}
							}
						}
					});				
				}else
				{
					$.messager.alert("提示","请选择一个检查项！");
				}
			
		}else
		{
			$.messager.alert("提示","请选择一个日程！");
		}
		
	}
	function removeQACheckItemAndSchedule()
	{
		var regRow = $('#checkItemAndScheduleTable').datagrid("getSelected");
		
		if(regRow!=null)
		{
			var index = $('#checkItemAndScheduleTable').datagrid("getRowIndex",regRow);
				$.ajax({
					url:sybp()+'/dictScheduleChkItemAction_remove.action?scheduleChkItemId='+regRow.scheduleChkItemId,
					type:'post',
					//data:'',
					dataType:'json',
					success:function(r){
						if(r && r.success){
							$('#checkItemAndScheduleTable').datagrid('deleteRow', index);
							//$('#checkItemAndScheduleTable').datagrid('reload');
						}else{
							if(!r.success)
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
		var tableHeight = document.body.clientHeight - 30;   //datagrid 高度
		var tableWidth = document.body.clientWidth;

		
		$('#scheduleList').datagrid({
			height:tableHeight-50,
			url:sybp()+'/tblTaskTypeAction_loadListForQA.action',
			singleSelect:true,//只能选一行
			columns:[[
						{title :'',field :'id',hidden:true}
						,{title:'任务类别 ',field :'taskKind',width:70,halign:'center',
							formatter: function(value,row,index){
								if (value==1){
									return "委托管理 ";
								} 
								if (value==2){
									return "动物试验";
								} 
								if (value==3){
									return "临床检验";
								} 
								if (value==4){
									return "毒性病理";
								} 
								if (value==5){
									return "QA管理";
								} 
								if (value==6){
									return "供试品管理";
								} 
								if(value==7){
									return "分析";
								}
								if(value==8){
									return "生态毒理";
								}
								
							}
						}
						,{title:'任务名称',field :'taskName',width:149,halign:'left'}
					
					]],
				onSelect:function(rowIndex, rowData){
			 		$('#checkItemAndScheduleTable').datagrid({
			 			url:sybp()+'/dictScheduleChkItemAction_loadListBySchedule.action?taskNameId='+rowData.id,
				 	});
				}
		});  
		
	
		$('#QACheckItems').datagrid({
			height:tableHeight-50,
			url : sybp()+"/dictQACheckItemAction_loadScheduleChkItemList.action",
			title:'',
			iconCls:'',//'icon-save',
			singleSelect:true,//只能选一行
			pagination:false,//下面状态栏
			//height:tableHeight,
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
				,{title:'检查项名称',field :'chkItemName',width:150,halign:'left'}
			
			]],
			
		});
		 $('#checkItemAndScheduleTable').datagrid({
				height:tableHeight-50,
			 //	url:sybp()+'/dictScheduleChkItemAction_loadList.action',
				//showHeader:false,//不显示头部
				singleSelect:true,
				columns:[[
							{
							    field:'scheduleChkItemId',
							    title:'',
							    hidden:true
							  
							},
					        {
						       	  title:"日程",
						          field:'taskName',
						          width:200
						         
						     },
						     {
						          field:'chkItemName',
						          title:'检查项',
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
		          <li class="active"><span><span><a href="javascript:void(0);">检查项与日程</a></span></span></li>
		      </ul>
			</div>
		</div>
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			
	        <span style=" position:absolute;top:10px;left:10px;width:240px;">日程列表：</span>	<br/>
			<div style=" position:absolute;top:30px;left:10px;bottom:22px;width:240px; border: 1px solid #c8c8c8;">
				<div id="scheduleList" class="easyui-datagrid" style="overflow:auto;" style=" position:absolute;top:0px;left:0px;bottom:0px;width:240px; "></div>
			</div>
			<span style=" position:absolute;top:10px;left:260px;width:260px;">检查项列表：</span>	<br/>
			<div style=" position:absolute;top:30px;left:260px;bottom:22px;width:270px; border: 1px solid #c8c8c8;">
				<div id="QACheckItems" class="easyui-datagrid" style="overflow:auto;" style=" position:absolute;top:0px;left:0px;bottom:0px;width:270px; "></div>
			</div>
			<div style="position:absolute;top:30px;left:520px;bottom:22px;width:50px;">
				<div style="position:absolute;top:50%;left:50%;">
					<button onclick="addQACheckItemAndSchedule();" style="width: 40px;">></button><br/><br/>
					<button onclick="removeQACheckItemAndSchedule();" style="width: 40px;"><</button>
				</div>	
			</div>
			<span id="oneQACheckTableLabel" style=" position:absolute;top:10px;left:600px;">检查项与日程：</span><br/>
			<div style=" position:absolute;top:30px;bottom:22px;left:600px;width:421px; border: 1px solid #c8c8c8;">
				<div id="checkItemAndScheduleTable" class="easyui-tree" style="overflow-y: auto;"></div>
			</div>
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckItemAction/addOrEditQACheckItem.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckItemAction/selectQACheckItem.jspf"%>
		
  </body>
</html>
