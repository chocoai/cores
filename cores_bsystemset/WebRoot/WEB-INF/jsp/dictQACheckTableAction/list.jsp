<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	function addQACheckTable(addOrEdit)
	{
		if(addOrEdit==0)//新增
		{
			/* 显示Dialog */
			document.getElementById("addOrEditQACheckTableDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddButton').css('display','');
			
			
			$('#updateChkTblId').val('');
			$('#oneChkTblCode').val('');
			$('#oneChkTblName').val('')
			
			$('#addOrEditQACheckTableDialog').dialog('setTitle','添加检查表');
			$('#addOrEditQACheckTableDialog').dialog('open'); 
		}
		else if(addOrEdit==1)//编辑
		{
			/* 显示Dialog */
			document.getElementById("addOrEditQACheckTableDialog2").style.display="block";
		document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			var row=$('#QACheckTables').datagrid("getSelected");
			if(row!=null&&row!=''&&row!='undified')
			{
				$('#updateChkTblId').val(row.chkTblId);
				$('#oneChkTblCode').val(row.chkTblCode);
				$('#oneChkTblName').val(row.chkTblName);
				
				$('#addOrEditQACheckTableDialog').dialog('setTitle','编辑检查表');
				$('#addOrEditQACheckTableDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个检查表！");
			}
		}
		
	}
	function delQACheckTable()
	{
		var row=$('#QACheckTables').datagrid("getSelected");
		if(row!=null&&row!=''&&row!='undified')
		{
			$.messager.confirm('确认框', '删除检查表会同时删除检查表内容，是否确认删除检查表?', function(r){
				if (r){
					
					qm_showQianmingDialog('afterSignDelCheckTable');
				}
			});
		}else {
			$.messager.alert("提示","请选择一个检查表！");
		}
	}
	function afterSignDelCheckTable()
	{
		var row=$('#QACheckTables').datagrid("getSelected");
		var index = $('#QACheckTables').datagrid('getRowIndex',row);
		
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/dictQACheckTableAction_del.action',
			type:'post',
			data:{
				chkTblId:row.chkTblId
			},
			dataType:'json',
			success:function(){
			}
		});
		$('#QACheckTables').datagrid("deleteRow",index);
		$('#QACheckTableContents').datagrid('loadData',[]);
		
	}
	
	function saveOrUpdateOneQACheckTable()
	{
		 var oneChkTblCode=$('#oneChkTblCode').val();
		 var oneChkTblName=$('#oneChkTblName').val();
		 var updateChkTblId = $('#updateChkTblId').val();
		 
		 if(oneChkTblCode==''||oneChkTblName=='')
		 {
			 $.messager.alert("提示框","请输入编号和检查表名称！");
		 }else if( updateChkTblId=="" ){//新增
				$.ajax({
					url:sybp()+'/dictQACheckTableAction_addSave.action',
					type:'post',
					data:$('#oneQACheckTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
						if(r&&r.success){
							 $('#QACheckTables').datagrid({
									url : sybp()+"/dictQACheckTableAction_loadList.action",
									onLoadSuccess:function(){
								 		$('#QACheckTables').datagrid('selectRecord',r.chkTblId);
									}
							 });
							 $('#QACheckTables').datagrid('reload');
						}
					}
				});
				if($('#continueAddButton').attr('checked')!='checked')
				{
					 $('#addOrEditQACheckTableDialog').dialog('close');
				}else {
					$('#oneChkTblCode').val('');
					$('#oneChkTblName').val('');
				}
				
				
			}else{ //编辑
				$.ajax({
					url:sybp()+'/dictQACheckTableAction_editSave.action',
					type:'post',
					data:$('#oneQACheckTable').serialize(),
					dataType:'json',
					success:function(r){
						if(r&&!r.success){
							$.messager.alert("提示",r.msg);
						}
						if(r&&r.success){
							 $('#QACheckTables').datagrid({
									url : sybp()+"/dictQACheckTableAction_loadList.action",
							 });
							 $('#QACheckTables').datagrid('reload');
						}
					}
				});
				var oneChkTblCode=$('#oneChkTblCode').val();
				 var oneChkTblName=$('#oneChkTblName').val();
				 var updateChkTblId = $('#updateChkTblId').val();
				 $('#addOrEditQACheckTableDialog').dialog('close');
				
			}
		// $('#QACheckTables').datagrid("reload");
		//alert("QACheckTables reload");
		
		
	}
	
	function addQACheckTableContent(addOrEdit)
	{
		var row0=$('#QACheckTables').datagrid("getSelected");
		if(row0!=null&&row0!=''&&row0!='undified')
		{
			$('#OneChkTblNameDisplay').val(row0.chkTblName);
		
		
			if(addOrEdit==0)//新增
			{
				
				document.getElementById("addOrEditQACheckTableContentDialog2").style.display="block";
				document.getElementById("continueAddLabel2").innerHTML='连续添加';//连续添加不显示
				$('#continueAddButton2').css('display','');
				
				
				$('#updateChkTblContentId').val('');
				$('#oneSn').val('');
				$('#oneChkContent').val('');
				
				$('#addOrEditQACheckTableContentDialog').dialog('setTitle','添加检查表内容');
				$('#addOrEditQACheckTableContentDialog').dialog('open'); 
			}
			else if(addOrEdit==1)//编辑
			{
				
				document.getElementById("addOrEditQACheckTableContentDialog2").style.display="block";
				document.getElementById("continueAddLabel2").innerHTML='';//连续添加不显示
				$('#continueAddButton2').css('display','none');
				
				var row=$('#QACheckTableContents').datagrid("getSelected");
				if(row!=null&&row!=''&&row!='undified')
				{
					$('#updateChkTblContentId').val(row.chkTblContentId);
					$('#oneSn').val(row.sn);
					$('#oneChkContent').val(row.chkContent);
					
					$('#addOrEditQACheckTableContentDialog').dialog('setTitle','编辑检查表内容');
					$('#addOrEditQACheckTableContentDialog').dialog('open'); 
				}else {
					$.messager.alert("提示","请选择一个检查表内容！");
				}
			}
		}else
		{
			$.messager.alert("提示","请选择一个检查表！");
		}
		
	}

	function saveOrUpdateOneQACheckContentTable()
	{
		 var updateChkTblContentId=$('#updateChkTblContentId').val();
		 var oneChkSn=$('#oneChkSn').val();
		 var oneChkContent = $('#oneChkContent').val();
		 
		 if(oneChkContent=='')
		 {
			 $.messager.alert("提示框","请输入序号和检查表内容！");
		 }else if( updateChkTblContentId=="" ){//新增
			var row=$('#QACheckTables').datagrid("getSelected");//检查表
			$.ajax({
				url:sybp()+'/dictQACheckContentTableAction_saveQACheckContetTable.action?chkTblId='+row.chkTblId,
				type:'post',
				data:$('#oneQACheckContentTable').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$('#QACheckTableContents').datagrid('appendRow',{
							 chkTblContentId:r.chkTblContentId,
					          sn:r.sn,
					         chkContent:r.chkContent,
						 
						});
					}
					else if(r && !r.success)
					{
						$.messager.alert("提示",r.msg);	
					}
				}
			});
			if($('#continueAddButton2').attr('checked')!='checked')
			{
				 $('#addOrEditQACheckTableContentDialog').dialog('close');
			}else {
				$('#updateChkTblContentId').val('');
				$('#oneSn').val('');
				$('#oneChkContent').val('');
			}
		 }else{//编辑
			 $.ajax({
					url:sybp()+'/dictQACheckContentTableAction_editQACheckContetTable.action',
					type:'post',
					data:$('#oneQACheckContentTable').serialize(),
					dataType:'json',
					success:function(r){
					 	if(r && !r.success)
						{
							$.messager.alert("提示",r.msg);	
						}
						if(r&&r.success){
							
							 var rowData=$('#QACheckTables').datagrid("getSelected");
							 var rowIndex =$('#QACheckTables').datagrid("getRowIndex",rowData);
							 $('#QACheckTables').datagrid('selectRow',rowIndex);
							 
							 $('#QACheckTableContents').datagrid("reload");
						}
					}
				});
			 $('#addOrEditQACheckTableContentDialog').dialog('close');

			
		 }
		
		
		
		
	}
	function delQACheckTableContent()
	{
		var row=$('#QACheckTableContents').datagrid("getSelected");
		if(row!=null&&row!=''&&row!='undified')
		{
			$.messager.confirm('确认框', '确认删除检查表内容?', function(r){
				if (r){
					//如果需要前面加在这里，
					$.ajax({
						url:sybp()+'/dictQACheckContentTableAction_del.action',
						type:'post',
						data:{
							chkTblContentId:row.chkTblContentId
						},
						dataType:'json',
						success:function(){
							
						}
					});
					var index = $('#QACheckTableContents').datagrid("getRowIndex",row);
					$('#QACheckTableContents').datagrid('deleteRow',index);
				}
			});
			
		}else {
			$.messager.alert("提示","请选择一个检查表内容！");
		}
		
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

</script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	$(function(){
		var tableSize ;
		var tableHeight = document.body.clientHeight - 100;   //datagrid 高度
		var tableWidth = document.body.clientWidth;

		$('#QACheckTables').datagrid({
			url : sybp()+"/dictQACheckTableAction_loadList.action",
			title:'',
			iconCls:'',//'icon-save',
			singleSelect:true,//只能选一行
			pagination:false,//下面状态栏
			height:tableHeight,
			width:320,  
			//fit:true,
			//showHeader:false,//不显示头部
			fitColumns:false,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			border:false,
			idField:'chkTblId', //pk
			//toolbar:'#toolbar',
			columns :[[
				{title :'',field :'chkTblId',hidden:true}
				,{title:'编号',field :'chkTblCode',width:100,halign:'center'}
				,{title:'检查表名称',field :'chkTblName',width:200,halign:'left'}
			
			]],
			
			onSelect:function(rowIndex, rowData){
				document.getElementById("oneQACheckTableLabel").innerHTML=rowData.chkTblName+" 内容：";
				$('#QACheckTableContents').datagrid({
					url : sybp()+"/dictQACheckContentTableAction_loadListByChkTblId.action?chkTblId="+rowData.chkTblId,
				});
			},
		});
		var row=$('#QACheckTables').datagrid("getSelected");
		
		$('#QACheckTableContents').datagrid({
			width:510,
			height:tableHeight,
			singleSelect:true,
			columns:[[
			          {
				          field:'chkTblContentId',
				          title:'',
				          hidden:true
				       },
			          {
				          field:'sn',
				          title:'序号',
				          width:90,
				       },
				       {
					       field:'chkContent',
					       title:'检查表内容',
					       width:398,
					   }

			]],
		});
		if(row!=null&&row!=''&&row!='undified')
		{
			$('#QACheckTableContents').datagrid({
				url : sybp()+"/dictQACheckContentTableAction_loadListByChkTblId.action?chkTblId="+row.chkTblId,
			});
		}


	});

</script>
</head>
  
  <body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">检查表设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800;">
				<a id="addQACheckTable"  class="easyui-linkbutton" onclick="addQACheckTable(0);" data-options="iconCls:'icon-add',plain:true">添加检查表</a>
				<a id="editQACheckTable"  class="easyui-linkbutton" onclick="addQACheckTable(1);" data-options="iconCls:'icon-edit',plain:true">编辑检查表</a>
				<a id="delQACheckTable" class="easyui-linkbutton" onclick="delQACheckTable();" data-options="iconCls:'icon-remove',plain:true">删除检查表</a>
				|
				<a id="addQACheckTableContent"  class="easyui-linkbutton" onclick="addQACheckTableContent(0);" data-options="iconCls:'icon-add',plain:true">添加检查表内容</a>
				<a id="editQACheckTableContent"  class="easyui-linkbutton" onclick="addQACheckTableContent(1);" data-options="iconCls:'icon-edit',plain:true">编辑检查表内容</a>
				<a id="delQACheckTableContent" class="easyui-linkbutton" onclick="delQACheckTableContent();" data-options="iconCls:'icon-remove',plain:true">删除检查表内容</a>
				
				<!-- 
				<a id="selectStudyType" class="easyui-linkbutton" onclick="selectStudyType();" data-options="iconCls:'icon-add',plain:true">选择试验</a>
				<a id="selectResButton" class="easyui-linkbutton" onclick="selectResButton();" data-options="iconCls:'icon-house',plain:true">试验安置区域</a>
				 -->
			</div>
	          <span style=" position:absolute;top:30px;left:10px;width:320px;">检查表列表：</span>	<br/>
			<div style="position:absolute;top:50px;left:10px;bottom:22px;width:320px; border: 1px solid #c8c8c8; ">
				<div id="QACheckTables" class="easyui-datagrid" style="width:320px;overflow-y: auto;"></div>
			
			</div>
					
			<span id="oneQACheckTableLabel" style=" position:absolute;top:30px;left:350px;">内容：</span><br/>
			<div style=" position:absolute;top:50px;bottom:22px;left:350px;width:510px; border: 1px solid #c8c8c8; ">
				<div id="QACheckTableContents" class="easyui-datagrid" style="width:510px;overflow-y: auto;"></div>
			</div>
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckTableAction/addOrEditQACheckTable.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQACheckTableAction/editCheckTableAndContent.jspf" %>
  </body>
</html>
