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
	function addQACheckItem(addOrEdit)
	{
		if(addOrEdit==0)//新增
		{
			/* 显示Dialog */
			document.getElementById("addOrEditQACheckItemDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddButton').css('display','');
			
			$('#updateChkItemId').val('');
			$('#oneChkItemType').combobox('setValue','');
			$('#oneChkItemName').val('');
			
			$('#addOrEditQACheckItemDialog').dialog('setTitle','添加检查项');
			$('#addOrEditQACheckItemDialog').dialog('open'); 
		}
		else if(addOrEdit==1)//编辑
		{
			/* 显示Dialog */
			document.getElementById("addOrEditQACheckItemDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			var row=$('#QACheckItems').datagrid("getSelected");
			if(row!=null&&row!=''&&row!='undified')
			{
				$('#updateChkItemId').val(row.chkItemId);
				$('#oneChkItemType').combobox("setValue",row.chkItemType);
				$('#oneChkItemName').val(row.chkItemName);
				
				$('#addOrEditQACheckItemDialog').dialog('setTitle','编辑检查项');
				$('#addOrEditQACheckItemDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个检查项！");
			}
		}
		
	}
	function delQACheckItem()
	{
		var row=$('#QACheckItems').datagrid("getSelected");
		if(row!=null&&row!=''&&row!='undified')
		{
			$.messager.confirm('确认框', '删除检查项同时会删除检查项的一些关联，并且如果该检查项已经被检查过程使用，删除可能会导致异常，确认要删除吗?', function(r){
				if (r){
					
					qm_showQianmingDialog('afterSignDelCheckItem');
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个检查项！");
		}
		
	}
	function afterSignDelCheckItem()
	{
		var row=$('#QACheckItems').datagrid("getSelected");
		var index = $('#QACheckItems').datagrid('getRowIndex',row);
		
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/dictQACheckItemAction_del.action',
			type:'post',
			data:{
				chkItemId:row.chkItemId
			},
			dataType:'json',
			success:function(){	
				$('#QACheckItems').datagrid("deleteRow",index);
				$('#itemQACheckTables').datagrid('loadData',[]);
				$('#studyGroupAndCheckItemTable').treegrid('reload');
			}
		});
	}
	function saveOrUpdateOneQACheckItem()
	{
		var chkItemId=$('#updateChkItemId').val();
		var chkItemType=$('#oneChkItemType').combobox('getValue');
		var chkItemName=$('#oneChkItemName').val();
		 
		 if(chkItemType==''||chkItemName=='')
		 {
			 $.messager.alert("提示框","请输入检查项分类和检查项名称！");
		 }else if( chkItemId=="" ){//新增
				$.ajax({
					url:sybp()+'/dictQACheckItemAction_addSave.action',
					type:'post',
					data:$('#oneQACheckItem').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							
							$('#QACheckItems').datagrid('appendRow',{
								studyGroupId:r.studyGroupId,
								chkItemId:r.chkItemId,
								chkItemType:r.chkItemType,
								chkItemName:r.chkItemName,
							});
							$('#QACheckItems').datagrid('selectRecord',r.chkItemId);
						}
						else if(r && !r.success){
							$.messager.alert('提示',r.msg);
							
						}
					}
				});
				if($('#continueAddButton').attr('checked')!='checked')
				{
					 $('#addOrEditQACheckItemDialog').dialog('close');
				}else {
					//$('#oneChkItemType').combobox('setValue','');
					$('#oneChkItemName').val('');
				}
				
				
			}else{ //编辑
				var row=$('#QACheckItems').datagrid("getSelected");
				var index = $('#QACheckItems').datagrid('getRowIndex',row);
				$.ajax({
					url:sybp()+'/dictQACheckItemAction_editSave.action',
					type:'post',
					data:$('#oneQACheckItem').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&r.success){
							
							$('#QACheckItems').datagrid('updateRow',{
								index:index,
								row:{
									studyGroupId:r.studyGroupId,
									chkItemId:r.chkItemId,
									chkItemType:r.chkItemType,
									chkItemName:r.chkItemName,
								}
							});
							$('#QACheckItems').datagrid('selectRow',index);
							$('#studyGroupAndCheckItemTable').treegrid('reload');
						}
						else if(r && !r.success){
								$.messager.alert('提示',r.msg);
						}
					}
				});
				 $('#addOrEditQACheckItemDialog').dialog('close');
			}
		// $('#QACheckItems').datagrid("reload");
		/* $('#QACheckItems').datagrid({
				url : sybp()+"/dictQACheckItemAction_loadList.action",
		 });*/
		
	}
	
	
	
	function changeCancelName()
	{
		if($('#continueAddButton').attr('checked')=='checked')
		{
			$('#studyGroupCancelButton').linkbutton({
				text:'关闭'
		
			});
		}else {
			$('#studyGroupCancelButton').linkbutton({
				text:'取消'
		
			});
		}
	}
	function selectCheckTableButton()//选择检查表
	{
		var row=$('#QACheckItems').datagrid("getSelected");
		if(row!=null&&row!=''&&row!='undified')
		{
			document.getElementById("selectCheckTableDialog2").style.display="block";
			
			$('#selectChkTblCodeLabel').searchbox({ 
			     height:21,
			     width:280,
				 searcher:function(value,name){ 
				 	searchChkTableByCodeAndName();
					
				},
				prompt:'模糊查询,请输入检查表编号',
			});
			$('#selectChkTblCodeLabel').searchbox('setValue',''); 
			
			$('#selectChkTblNameLabel').searchbox({ 
			     height:21,
			     width:280,
				 searcher:function(value,name){ 
					 searchChkTableByCodeAndName();
				},
				prompt:'模糊查询,请输入检查表名称',
			});
			$('#selectChkTblNameLabel').searchbox('setValue',''); 
			
			$('#selectChkTblTable').datagrid({
				url : sybp()+"/dictQACheckTableAction_loadList.action",
				singleSelect:false,
			});
			$('#selectCheckTableDialog').dialog('open');
			
		}else {
			$.messager.alert("提示","请选择一个检查项！");
		}

	}
	function searchChkTableByCodeAndName()
	{
		var selectChkTblCodeLabel = $('#selectChkTblCodeLabel').searchbox("getValue");
		var selectChkTblNameLabel = $('#selectChkTblNameLabel').searchbox("getValue");
		$.ajax({
			url : sybp()+"/dictQACheckTableAction_loadList.action",
			type:'post',
			data:{
				chkTblCode:selectChkTblCodeLabel,
				chkTblName:selectChkTblNameLabel,
			},
			dataType:'json',
			success:function(r){
				$('#selectChkTblTable').datagrid('loadData',r);
			}
		});
		/*
		$('#selectChkTblTable').datagrid({
			url : sybp()+"/dictQACheckTableAction_loadList.action?chkTblCode="+selectChkTblCodeLabel+"&chkTblName="+selectChkTblNameLabel,
			singleSelect:false,
		});*/
		
	}
	
	function deleteCheckTableButton()
	{
		var rowTable=$('#itemQACheckTables').datagrid("getSelected");
		if(rowTable!=null)
		{
			var index = $('#itemQACheckTables').datagrid("getRowIndex",rowTable);
			$.ajax({
				url:sybp()+'/dictChkItemChkTblRegAction_del.action?chkItemChkTblRegId='+rowTable.chkItemChkTblRegId,
				type:'post',
				//data:'',
				dataType:'json',
				success:function(){
				}
			});
			$('#itemQACheckTables').datagrid('deleteRow',index);
		}else
		{
			$.messager.alert("提示","请选择一个检查表");
		}
	}
	function saveCheckTableAndItemButton()
	{
		var rowItem=$('#QACheckItems').datagrid("getSelected");
		var rowTables=$('#selectChkTblTable').datagrid("getSelections");
		if(rowTables!=null&&rowTables!=''&&rowTables!='undified')
		{
			var chkTblIds = new Array();
			for(var i=0;i<rowTables.length;i++)
			{
				chkTblIds.push(rowTables[i].chkTblId);
			}
			$.ajax({
				url:sybp()+'/dictChkItemChkTblRegAction_save.action?chkItemId='+rowItem.chkItemId+"&chkTblIds="+chkTblIds,
				type:'post',
				//data:'',
				dataType:'json',
				success:function(r){
					if(r && r.success){
					}else{
						$.messager.alert('提示',r.msg,'info');
					}
					var row= $('#QACheckItems').datagrid('getSelected');
					var index = $('#QACheckItems').datagrid('getRowIndex',row);
					$('#QACheckItems').datagrid('selectRow',index);
					$('#itemQACheckTables').datagrid('reload');
				}
			});
			 $('#selectCheckTableDialog').dialog('close');
			/* $('#itemQACheckTables').datagrid({
					url:sybp()+'/dictChkItemChkTblRegAction_loadList.action?chkItemId='+rowItem.chkItemId,	
		     });*/
		}else
		{
			$.messager.alert("提示","请选择一个检查表！");
		}
		
		
	}
	function addQACheckItemToGroup()
	{
		var treeRow = $('#studyGroupAndCheckItemTable').treegrid("getSelected");
		if(treeRow!=null)
		{
			//var parent = $('#studyGroupAndCheckItemTable').treegrid("getParent",treeRow.target);
			var parent = $('#studyGroupAndCheckItemTable').treegrid("getParent",treeRow.id);
			//alert($('#studyGroupAndCheckItemTable').tree("isLeaf",treeRow.target));//isleaf始终为true
			if(parent==null)
			{
				var itemRow = $('#QACheckItems').datagrid("getSelected");
				if(itemRow!=null)
				{
					if($('input[name="chkFreqFlag"]:checked').val()==2//多次
							&&($('#oneChkFreq').val()==''||$('#oneChkFreq').val()==null))
					{
						$.messager.alert("提示","请输入检查频率！");
					}else{
						
						//更新试验分类和试验项登记表
						$.ajax({
							url:sybp()+'/dictChkItemStudyGroupRegAction_save.action?chkItemId='+itemRow.chkItemId+"&studyGroupId="+treeRow.id,
							contentType: "application/x-www-form-urlencoded",
							type:'post',
							data:$('#chkFreqForm').serialize(),
							dataType:'json',
							success:function(r){
								if(r && r.success){
									
									$('#studyGroupAndCheckItemTable').treegrid('append', {
										//parent: treeRow.target,
										parent:treeRow.id,
										data: [{
											id:r.chkItemId,//关系表中是groupId-chkItemId
											text: r.chkItemName,
											chkFreqFlag:r.chkFreqFlag,
								        	chkFreq:r.chkFreq,
								        	chkFreqUnit:r.chkFreqUnit,
											state: 'open',
										}]
										
									});
								}else{
									if(!r.success)
									{
										$.messager.alert('提示',r.msg,'info');
									}
								}
							}
						});		
					}		
				}else
				{
					$.messager.alert("提示","请选择一个检查项！");
				}
			}else
			{
				$.messager.alert("提示","请选择一个试验分类节点！");
			}
		}else
		{
			$.messager.alert("提示","请选择一个试验分类！");
		}
		
	}
	function removeQACheckItemToGroup()
	{
		var treeRow = $('#studyGroupAndCheckItemTable').treegrid("getSelected");
		if(treeRow!=null)
		{
			var parent = $('#studyGroupAndCheckItemTable').treegrid("getParent",treeRow.id);
			//alert($('#studyGroupAndCheckItemTable').tree("isLeaf",treeRow.target));//isleaf始终为true
			if(parent!=null)//子节点
			{
				$.ajax({
					url:sybp()+'/dictChkItemStudyGroupRegAction_remove.action?chkItemId='+treeRow.id+"&studyGroupId="+parent.id,
					type:'post',
					//data:'',
					dataType:'json',
					success:function(r){
						if(r && r.success){
							
							//$('#studyGroupAndCheckItemTable').treegrid('remove', treeRow.target);
							$('#studyGroupAndCheckItemTable').treegrid('remove', treeRow.id);
							
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
				$.messager.alert("提示","请选择要移出的检查项！");
			}
		}else
		{
			$.messager.alert("提示","请选择要移出的检查项！");
		}
	}
	function selectOneTimeReg()
	{
		var freq = $('input[name="chkFreqFlag"]:checked').val();
		//alert("选择了那个："+freq);
		if(freq==1)
		{
			$('#oneChkFreq').val('');
			$('#oneChkFreq').attr('disabled',true);
			$('#oneChkFreqUnit').attr('disabled',true);
			//$('#chkFreqTR').css('display','none');
		}else{
			$('#oneChkFreq').val(1);
			$('#oneChkFreq').attr('disabled',false);
			$('#oneChkFreqUnit').attr('disabled',false);	
			//$('#chkFreqTR').css('display','');
		}
		
	}
	
</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		var tableSize ;
		var tableHeight = document.body.clientHeight - 100;   //datagrid 高度
		var tableWidth = document.body.clientWidth;

		$('#QACheckItems').datagrid({
			url : sybp()+"/dictQACheckItemAction_loadList.action",
			title:'',
			iconCls:'',//'icon-save',
			singleSelect:true,//只能选一行
			pagination:false,//下面状态栏
			height:tableHeight-130,
			width:240,  
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
			
			onSelect:function(rowIndex, rowData){
				document.getElementById("oneCheckItemLabel").innerHTML=rowData.chkItemName;
				 $('#itemQACheckTables').datagrid({
						url:sybp()+'/dictChkItemChkTblRegAction_loadList.action?chkItemId='+rowData.chkItemId,	
			     });
				
			},
		});
		 $('#itemQACheckTables').datagrid({
				showHeader:false,//不显示头部
				width:240,
				height:75,
				singleSelect:true,
				columns:[[
				          {
					          field:'chkItemChkTblRegId',
					          title:'',
					          hidden:true
					       },
					       {
						       	  title:"检查表名称",
						          field:'chkTblName',
						          width:220
						         
						     },
					     ]]
	     });
		
		$('#studyGroupAndCheckItemTable').treegrid({
		   url:sybp()+'/dictStudyGroupAction_loadGroupAndItemTree.action',
		    idField:'id',
		    treeField:'text',
		    width:515,
		    height:tableHeight,
		    columns:[[
		        {title:'id',field:'id',hidden:true},//树是groupid，叶子是itemid
		        {title:'名称',field:'text',width:250,align:'left',},//树是gourp名字，叶子是item名字
		        {title:'检查频率',field:'chkFreqFlag',width:80,
		        	formatter: function(value,row,index){
						if (value==1){
							return "单次";
						} else if (value==2){
							return "多次";
						}
					}	
		        },
		        {title:'重复频率',field:'chkFreq',width:80},
		        {title:'频率单位',field:'chkFreqUnit',width:80}
		    ]],
		    
		});

	});

</script>
</head>
  
  <body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">检查项设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800;">
				<a id="addQACheckTable"  class="easyui-linkbutton" onclick="addQACheckItem(0);" data-options="iconCls:'icon-add',plain:true">添加检查项</a>
				<a id="editQACheckTable"  class="easyui-linkbutton" onclick="addQACheckItem(1);" data-options="iconCls:'icon-edit',plain:true">编辑检查项</a>
				<a id="delQACheckTable" class="easyui-linkbutton" onclick="delQACheckItem();" data-options="iconCls:'icon-remove',plain:true">删除检查项</a>
				
			</div>
	          <span style=" position:absolute;top:30px;left:10px;width:240px;">检查项列表：</span>	<br/>
			<div style=" position:absolute;top:50px;left:10px;bottom:22px;width:240px; border: 1px solid #c8c8c8; ">
				<div id="QACheckItems" class="easyui-datagrid" style="overflow-y: auto;"></div>
				<div style="width:240px; height:50px;margin-top:10px; border: 1px solid #c8c8c8;">
					检查项：<span id="oneCheckItemLabel"></span><br/>
					 检查表：(<a class="easyui-linkbutton" onclick="selectCheckTableButton();" data-options="iconCls:'icon-add',plain:true">选择</a>)
					 (<a class="easyui-linkbutton" onclick="deleteCheckTableButton();" data-options="iconCls:'icon-remove',plain:true">移除</a>)
				</div>
				<div id="itemQACheckTables" class="easyui-datagrid"></div>
			</div>
			<span style=" position:absolute;top:30px;left:260px;width:160px;">检查频率和检查项使用表：</span>
			<div style=" position:absolute;top:50px;left:260px;width:150px;height:100; border: 1px solid #c8c8c8; overflow-y: auto;">
				
				<form id="chkFreqForm" style="height:80px;border: 0px solid #c8c8c8;">
					<table>
						<tr>
							<td  style="width:100px;">
							<!-- 1：单次；2：重复	 -->
								<label for="oneTimeReg">
									<input id="oneTimeReg" onclick="selectOneTimeReg();" type="radio" name="chkFreqFlag" value="1" checked="checked"/>单次
								</label>
							</td>
						</tr>
						<tr>
							<td  style="width:100px;">
								<label for="manyTimeReg">
									<input id="manyTimeReg" onclick="selectOneTimeReg();" type="radio" name="chkFreqFlag" value="2"/>多次
								</label>
							</td>
						</tr>
						<tr id="chkFreqTR" style="display: '';">
							<td >频率
								<input id="oneChkFreq" disabled name="chkFreq" style="width:50px;" onblur="if(isNaN(value)){value='';}"/>
								<select id="oneChkFreqUnit" disabled name="chkFreqUnit"  style="width: 40px;">
									<option value="天">天</option>
									<option value="周">周</option>
									<option value="月">月</option>
								</select>
							</td>
						</tr>
					
					</table>
				</form>
			</div>
				<div style="position:absolute;top:140px;left:280px;bottom:132px;width:50px;">
			
					<div style="position:absolute;top:35%;left:50%;">
						<button onclick="addQACheckItemToGroup();" style="width: 40px;">></button><br/><br/>
						<button onclick="removeQACheckItemToGroup();" style="width: 40px;"><</button>
					</div>	
				</div>
				
				
			
			<span id="oneQACheckTableLabel" style=" position:absolute;top:30px;left:420px;">试验分类与检查项：</span><br/>
			<div style=" position:absolute;top:50px;bottom:22px;left:420px;width:515px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="studyGroupAndCheckItemTable" class="easyui-treegrid"></div>
			</div>
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckItemAction/addOrEditQACheckItem.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckItemAction/selectQACheckItem.jspf"%>
		
  </body>
</html>
