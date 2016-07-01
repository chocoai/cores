<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QA声明模板</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	function addQATemple(addOrEdit)
	{
		
		if(addOrEdit==0)//新增
		{
			var tiCodeRow = $('#tiCodeDatagrid').datagrid('getSelected');
			if(tiCodeRow==null){
				$.messager.alert("提示框","请选择一个供试品类型");
			}else{
				/* 显示Dialog */
				document.getElementById("addOrEditQAStatementTempleDialog2").style.display="block";
	
				document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
				$('#continueAddButton').css('display','');
				
				
				$('#oneTempleId').val('');
				$('#oneTempleName').val('');
				$('#oneTempleContent').val('')
				
				$('#addOrEditQAStatementTempleDialog').dialog('setTitle','添加声明模板');
				$('#addOrEditQAStatementTempleDialog').dialog('open'); 
			}
			
		}
		else if(addOrEdit==1)//编辑
		{
			/* 显示Dialog */
			document.getElementById("addOrEditQAStatementTempleDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var row=$('#QAStatementTemples').datagrid("getSelected");
			if(row!=null&&row!=''&&row!='undified')
			{
				$('#oneTempleId').val(row.templeId);
				$('#oneTempleName').val(row.templeName);
				$('#oneTempleContent').val(row.templeContent);
				
				$('#addOrEditQAStatementTempleDialog').dialog('setTitle','编辑声明模板');
				$('#addOrEditQAStatementTempleDialog').dialog('open'); 
			}else {
				$.messager.alert("提示","请选择一个模板！");
			}
		}
		
	}
	
	function saveOrUpdateOneQAStatementTemple()
	{
		var templeId = $('#oneTempleId').val();
		var tiCodeRow = $('#tiCodeDatagrid').datagrid('getSelected');
		if(templeId=='')//新增
		{
			$.ajax({
				url:sybp()+'/dictQAStatementTempleAction_save.action?tiCode='+tiCodeRow.tiCode,
				type:'post',
				data:$('#oneQAStatementTemple').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$('#QAStatementTemples').datagrid("appendRow",
						{
							templeId:r.templeId,
							templeName:r.templeName,
							templeContent:r.templeContent,
						});
						if($('#continueAddButton').attr('checked')!='checked')
						{
							$('#addOrEditQAStatementTempleDialog').dialog('close');
						}
					}else{
						$.messager.alert("提示框",r.msg);
					}
				}
			});
			
		}else{//编辑
			var  row = $('#QAStatementTemples').datagrid('getSelected');
			var index = $('#QAStatementTemples').datagrid('getRowIndex',row);
			$.ajax({
				url:sybp()+'/dictQAStatementTempleAction_update.action?tiCode='+tiCodeRow.tiCode,
				type:'post',
				data:$('#oneQAStatementTemple').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						$('#QAStatementTemples').datagrid("updateRow",
							{
							 	index:index,
							 	row:{
									templeName:r.templeName,
									templeContent:r.templeContent,
								}
							}
						);
						$('#addOrEditQAStatementTempleDialog').dialog('close');
					}else{
						$.messager.alert("提示框",r.msg);
					}
				}
			});
		}
	}
	function delQATemple()
	{
		var row = $('#QAStatementTemples').datagrid("getSelected");
		if(row!=null&&row!=''&&row!='undified')
		{
			$.messager.confirm('确认框', '确定要删除该模板吗?', function(r){
				if (r){
					qm_showQianmingDialog('afterSignDelQATemple');
					
					
				}
			});
		}else {
			$.messager.alert("提示","请选择一个模板！");
		}
	}
	function afterSignDelQATemple(){
		
		var row = $('#QAStatementTemples').datagrid("getSelected");
		var index = $('#QAStatementTemples').datagrid('getRowIndex',row);
		
		//如果需要前面加在这里，
		$.ajax({
			url:sybp()+'/dictQAStatementTempleAction_del.action',
			type:'post',
			data:{
				templeId:row.templeId
			},
			dataType:'json',
			success:function(){
				$('#QAStatementTemples').datagrid("deleteRow",index);
			}
		});
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
		var tableHeight = document.body.clientHeight - 90;   //datagrid 高度
		var tableWidth = document.body.clientWidth;

		$('#tiCodeDatagrid').datagrid({
			url : sybp()+'/dictTestItemTypeAction_loadList.action',
			title:'',
			height: tableHeight,
			width:220,
			singleSelect:true,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			//pageSize:50,
			//pageList:[50,100],
			//fit:true,
			//fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			border:false,
			columns :[[{
				title : '供试品类型代码',
				field : 'tiCode',
				width : 100
			},{
				title:'供试品类型名称',
				field:'tiType',
				width:100
			}]],
			
			onSelect:function(rowIndex, rowData){
				$('#QAStatementTemples').datagrid({
					url : sybp()+"/dictQAStatementTempleAction_loadListByTiCode.action?tiCode="+rowData.tiCode,
				});
				
			},
		});

		$('#QAStatementTemples').datagrid({
			height: tableHeight,
			singleSelect:true,
			width:420,
			columns :[[
			{	
				title : '模板id',
				field : 'templeId',
				hidden:true,
			},{
				title : '模板名',
				field : 'templeName',
				width : 100
			},{
				title:'模板内容',
				field:'templeContent',
				width:300
			}]],
			
		});
		
		


	});

</script>
</head>
  
  <body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">QA声明模板</a></span></span></li>
		      </ul>
			</div>
		</div>
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800;">
				<a id="addQATemple"  class="easyui-linkbutton" onclick="addQATemple(0);" data-options="iconCls:'icon-add',plain:true">增加模板</a>
				<a id="editQATemple"  class="easyui-linkbutton" onclick="addQATemple(1);" data-options="iconCls:'icon-edit',plain:true">编辑模板</a>
				<a id="delQATemple" class="easyui-linkbutton" onclick="delQATemple();" data-options="iconCls:'icon-remove',plain:true">删除模板</a>
			</div>
			
			<div style="position:absolute;top:40px;left:10px;bottom:22px;width:220px; border: 1px solid #c8c8c8; ">
				<div id="tiCodeDatagrid" class="easyui-datagrid" style="width:220px;overflow-y: auto;"></div>
			
			</div>
			<div style="position:absolute;top:40px;left:250px;bottom:22px;width:420px; border: 1px solid #c8c8c8; ">
				<div id="QAStatementTemples" class="easyui-datagrid" style="width:420px;overflow-y: auto;"></div>
			
			</div>	
			
		</div>
		<!-- table end -->
		
	</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		<%@ include file="/WEB-INF/jsp/dictQAStatementTempleAction/addOrEditQAStatementTemple.jspf"%>
		
  </body>
</html>
