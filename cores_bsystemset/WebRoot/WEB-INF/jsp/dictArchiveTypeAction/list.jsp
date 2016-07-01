<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>档案分类设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>

<script type="text/javascript">
	var dictArchiveTypeTable;
	var tableSize ;
	$(function(){
		var tableHeight = document.body.clientHeight-30;
		dictArchiveTypeTable=$('#dictArchiveTypeTable').datagrid({
			url : sybp()+'/dictArchiveTypeAction_loadList.action',
			title:'',
			height: tableHeight,
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			idField:'archiveTypeCode',
			border:false,
			columns :[[{
				title : '类别标识',
				field : 'archiveTypeFlag',
				width : 80,
				formatter: function(value,row,index){
				// <!-- 1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6:行政综合；7：人员档案，8：合同；9：供试品留样；10：标本 -->
				   if (value==1){
						return "专题";
					} 
					if (value==2){
						return "QA检查资料";
					} 
					if (value==3){
						return "SOP资料";
					} 
					if (value==4){
						return "综合资料";
					} 
					if (value==5){
						return "仪器资料";
					} 
					if (value==6){
						return "人员档案";
					} 
					if (value==7){
						return "行政综合";
					} 
					if (value==8){
						return "合同";
					} 
					if (value==9){
						return "供试品留样";
					}
					if (value==10){
						return "标本";
					}
					if (value==11){
						return "基建";
					} 
				}
				
			},{
				title:'类别名称',
				field:'archiveTypeName',
				width:150
			},{
				title:'类别代码',
				field:'archiveTypeCode',
				width:150
			}]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('enable');
				$('#delToolbarButton').linkbutton('enable');
				
			},
			onUnselect:function(rowIndex, rowData){
				$('#editToolbarButton').linkbutton('disable');
				$('#delToolbarButton').linkbutton('disable');
			
			},
			onLoadSuccess:function(data){
				tableSize = data.total;
			}
		}); //end datagrid
		
	});
	//新增
	function addOneArchive(addOrEdit){
		$('#addOrEdit').val(addOrEdit);
		if(addOrEdit==1)//新增
		{
			/* 显示Dialog */
			document.getElementById("addArchiveDialog2").style.display="block";
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加不显示
			$('#continueAddCheckBox').css('display','');
			
			$('#oneArchiveTypeFlag').combobox('setValue','1');
			$('#oneArchiveTypeName').val('');
			$('#oneArchiveTypeCode').val('');
			$('#oldArchiveTypeCode').val('');
			$('#oneArchiveTypeCode').attr('readOnly',false);
			
			
			$('#addArchiveDialog').dialog('setTitle','添加档案类别');
			$('#addArchiveDialog').dialog('open'); 
		}else if(addOrEdit==2){
			var row = dictArchiveTypeTable.datagrid('getSelected');
			if(row!=null&&row!=''){
				/* 显示Dialog */
				document.getElementById("addArchiveDialog2").style.display="block";
				document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
				$('#continueAddCheckBox').css('display','none');
				
				
				$('#oneArchiveTypeFlag').combobox('setValue',row.archiveTypeFlag);
				$('#oneArchiveTypeName').val(row.archiveTypeName);
				$('#oneArchiveTypeCode').val(row.archiveTypeCode);
				$('#oneArchiveTypeCode').attr('readOnly',true);
				
				$('#oldArchiveTypeCode').val(row.archiveTypeCode);//用于后台更新的时候查找原记录
				
				$('#addArchiveDialog').dialog('setTitle','编辑档案类别');
				$('#addArchiveDialog').dialog('open'); 
						
			}else{
				parent.showMessager(2,'请选择编辑行',true,5000);
			}
			
		}
		
	}
	
	function saveOneArchive()
	{
		var oneArchiveTypeName = $('#oneArchiveTypeName').val();
		var oneArchiveTypeCode = $('#oneArchiveTypeCode').val();
		if(oneArchiveTypeName==''||oneArchiveTypeCode=='')
		{
			$.messager.alert("提示框","类型名和类型代码都不能为空");
		}else{
		
			var addOrEdit = $('#addOrEdit').val();
			var url = sybp()+'/dictArchiveTypeAction_save.action';
			if(addOrEdit==1)
			{
				url = sybp()+'/dictArchiveTypeAction_save.action';
			}else if(addOrEdit==2)
			{
				url = sybp()+'/dictArchiveTypeAction_update.action';
			}
			$.ajax({
				url:url,
				type:'post',
				data:$('#archiveAddEditForm').serialize(),
				dataType:'json',
				success:function(r){
					if(r&&r.success)
					{
						//获得行索引
						if(addOrEdit==1)
						{
							dictArchiveTypeTable.datagrid('appendRow',{
								archiveTypeName: r.archiveTypeName,
								archiveTypeCode: r.archiveTypeCode,
								archiveTypeFlag: r.archiveTypeFlag,
							});
							dictArchiveTypeTable.datagrid('selectRecord',r.archiveTypeCode);
							if($('#continueAddCheckBox').attr('checked')!='checked')
							{
								$('#addArchiveDialog').dialog('close');
							}else{
								//$('#oneArchiveTypeFlag').combobox('setValue','1');
								$('#oneArchiveTypeName').val('');
								$('#oneArchiveTypeCode').val('');
								$('#oldArchiveTypeCode').val('');
							}
						}else if(addOrEdit==2){
							var row = dictArchiveTypeTable.datagrid('getSelected');
							var index = dictArchiveTypeTable.datagrid('getRowIndex',row);
							dictArchiveTypeTable.datagrid('updateRow',{
								index:index,
								row:{
									archiveTypeName: r.archiveTypeName,
									archiveTypeCode: r.archiveTypeCode,
									archiveTypeFlag: r.archiveTypeFlag,
								}
							});
							dictArchiveTypeTable.datagrid('selectRow',index);
							$('#addArchiveDialog').dialog('close');
						}
						parent.showMessager(1,r.msg,true,5000);
					}else{
						parent.showMessager(3,r.msg,true,5000);
					}
				}
			});
			
		}
		
	}
	//删除
	function onDelToolbarButton(){
		var row = dictArchiveTypeTable.datagrid('getSelected');
		if(row!=null&&row!=''){
			
			$.messager.confirm('提示', '确定删除\' '+row.archiveTypeName+'\' ?', function(r){
				if (r){
						qm_showQianmingDialog('afterSignDelArchiveType');
							
					}else{
						parent.showMessager(2,r.msg,true,5000);
					}
				
				});
		}else{
			parent.showMessager(2,'请选择删除行',true,5000);
		}
	}
	function afterSignDelArchiveType()
	{
		var row = dictArchiveTypeTable.datagrid('getSelected');
		$.ajax({
			url:sybp()+'/dictArchiveTypeAction_delete.action',
			type:'post',
			data:{
				archiveTypeCode:row.archiveTypeCode,
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//获得行索引
					var index = dictArchiveTypeTable.datagrid('getRowIndex',row);
					//删除行
					dictArchiveTypeTable.datagrid('deleteRow',index);
					//禁用按钮（编辑，删除）
					$('#editToolbarButton').linkbutton('disable');
					$('#delToolbarButton').linkbutton('disable');
							
					parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.showMessager(3,r.msg,true,5000);
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">档案分类设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="dictArchiveTypeTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addToolbarButton"  class="easyui-linkbutton" onclick="addOneArchive(1);" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="addOneArchive(2);" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delToolbarButton" class="easyui-linkbutton" onclick="onDelToolbarButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
			<!-- 
			<a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">上移</a>
			<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownToolbarButton();" data-options="iconCls:'icon-redo',disabled:true,plain:true">下移</a>
			 -->
		</div>
		<%@include file="/WEB-INF/jsp/dictArchiveTypeAction/addArchive.jspf" %>
		<%@include file="/WEB-INF/jsp/public/qianming.jspf" %>
	</div>
</body>
</html>