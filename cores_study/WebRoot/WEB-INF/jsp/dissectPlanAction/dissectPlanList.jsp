<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>解剖计划</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/datagrid-detailview.js" charset="utf-8"></script>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/dissectPlanList.css" media="screen" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/dissectPlan.js"></script>
<script type="text/javascript">
	var dissectPlanListTable;//datagrid
	var dissectPlanListAddButton;//新增按钮
	var dissectPlanListEditButton;//编辑按钮
	var studyNoPara;//专题编号
	var studyState;//试验状态
	var editRowIndex;
	$(function(){
		studyNoPara = $('#studyNoPara').val();
		studyState = $('#studyState').val();
		if(studyState > 0){
			$('#dissectPlanListAddButton').linkbutton('disable');
			$('#dissectPlanListEditButton').linkbutton('disable');
			$('#dissectPlanListDeleteButton').linkbutton('disable');
			$('#dissectPlanListAnimalCodeButton').linkbutton('disable');
		}
		synchronizationDissectBeginDateAndEndDate();
		dissectPlanListTable=$('#dissectPlanListTable').datagrid({
			url:sybp()+'/tblDissectPlanAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara),
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:450,
			columns:[[
						{title:'id',field:'id',hidden:true,width:80},
						{title:'解剖次数',field:'dissectNum',width:100,halign:'center',
							formatter: function(value,row,index){
									return '第'+value+'次解剖';
							}
									},
						{title:'开始日期',field:'beginDate',width:150,halign:'center'},
						{title:'结束日期',field:'endDate',width:150,halign:'center'},
						{title:'描述',field:'describe',width:150,halign:'center'}
					]],
			toolbar:'#dissectPlanListToolbar',
			view: detailview,
			detailFormatter:function(index,row){    
	        	return '<div id="ddv-' + index + '" style="padding:5px 0"></div>';    
	    	},
	    	onExpandRow: function(index,row){    
	            $('#ddv-'+index).panel({    
	                border:false,    
	                cache:false,    
	                content: '<iframe src="' + '${pageContext.request.contextPath}/tblDissectPlanAction_animalCode.action?studyNoPara='+studyNoPara+ '&dissectNum='+row.dissectNum+'" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',   
	                onLoad:function(){    
	                    $(this).datagrid('fixDetailRowHeight',index);    
	                }    
	            });    
	            $(this).datagrid('fixDetailRowHeight',index);    
	        } 
		});
		$('#describe').combobox({
           url:sybp()+'/tblDissectPlanAction_getDescribeCombobox.action',
           panelHeight:120,
           valueField:'id',    
           textField:'text',
           onLoadSuccess:function(){
               
	        }   
                  
		}); 
		//日期选择器只读
		$(".datebox :text").attr("readonly","readonly");
		document.getElementById("bodyDiv").style.display="block";
	});
	/**新增按钮方法*/
	function onAddButton(){
		$('#beginDate').datebox('setValue','');
		$('#endDate').datebox('setValue','');
		$('#describe').combobox('setText','');
		$('#id').val('');
		//全部置为未选中
		//var animalDetailList = document.getElementsByName("animalDetailList");
		//for(var i=0;i<animalDetailList.length;i++){
		//	animalDetailList[i].checked=false;
		//}
		//显示所有
		//var allTr = document.getElementsByTagName("tr");
		//for(var i=0;i<allTr.length;i++){
		//	allTr[i].style.display='';
		//}
		$.ajax({
			url:sybp()+'/tblDissectPlanAction_loadAddUIData.action?studyNoPara='+studyNoPara,
			type:'post',
			data:'',
			dataType:'json',
			success:function(r){
				if(r){
					$('#dissectNum').val(r.dissectNextNum);
					/*
					*/
					if(r.selectedAnimalCodeList){
						for(var i=0;i<r.selectedAnimalCodeList.length;i++){
							$('#'+r.selectedAnimalCodeList[i]+'tr').css('display','none');
						}
					}
					$('#addDialog').dialog('setTitle','新增解剖计划');
					document.getElementById("addDialog2").style.display="block";
					$('#addDialog').dialog('open');
				}
			}
		});
	}
	/**编辑按钮方法*/
	function onEditButton(){
		var getSelections = dissectPlanListTable.datagrid('getSelections');
		if(getSelections.length == 1){
			$('#dissectNum').val(getSelections[0].dissectNum);
			$('#id').val(getSelections[0].id);
			$('#beginDate').datebox('setValue',getSelections[0].beginDate);
			$('#endDate').datebox('setValue',getSelections[0].endDate);
			$('#describe').combobox('setText',getSelections[0].describe);
			$('#addDialog').dialog('setTitle','编辑解剖计划');
			document.getElementById("addDialog2").style.display="block";
			$('#addDialog').dialog('open');
			editRowIndex = dissectPlanListTable.datagrid('getRowIndex',getSelections[0]);
			//全部置为未选中
			//var animalDetailList = document.getElementsByName("animalDetailList");
			//for(var i=0;i<animalDetailList.length;i++){
			//	animalDetailList[i].checked=false;
			//}
			//显示所有
			//var allTr = document.getElementsByTagName("tr");
			//for(var i=0;i<allTr.length;i++){
			//	allTr[i].style.display='';
			//}
			/*
			$.ajax({
				url:sybp()+'/tblDissectPlanAction_loadEditUIData.action?id='+getSelections[0].id,
				type:'post',
				data:'',
				dataType:'json',
				success:function(r){
					if(r){
						if(r.selectedAnimalCodeList){
							for(var i=0;i<r.selectedAnimalCodeList.length;i++){
								$('#'+r.selectedAnimalCodeList[i]+'tr').css('display','none');
							}
						}
						if(r.currentSelectedAnimalCodeList){
							for(var i=0;i<r.currentSelectedAnimalCodeList.length;i++){
								document.getElementById(r.currentSelectedAnimalCodeList[i]).checked = true;
							}
						}
						$('#addDialog').dialog('setTitle','编辑解剖计划');
						$('#addDialog').dialog('open');
					}
				}
			});*/
		}else{
			//$.messager.alert('提示','请选择要编辑的行');
			parent.parent.showMessager(2,'请选择要编辑的行',true,5000);
		}
	}
	/**保存按钮方法*/
	function onSaveButton(){
		
		if($('#addForm').form('validate')){
			var url='';
			if($('#id').val()){
				url=sybp()+'/tblDissectPlanAction_editSave.action';
			}else{
				url=sybp()+'/tblDissectPlanAction_addSave.action';
			}
				$.ajax({
					url:url,
					type:'post',
					data:$('#addForm').serialize(),
					dataType:'json',
					success:function(r){
						if(r){
								if($('#id').val()){
									dissectPlanListTable.datagrid('updateRow',{index: editRowIndex ,row: r });
									parent.parent.showMessager(1,'编辑成功',true,5000);
								}else{
									dissectPlanListTable.datagrid('appendRow',r);
									parent.parent.showMessager(1,'新增成功',true,5000);
								}
								$('#addDialog').dialog('close');
						}
					}
				});
		}
	}
	function onDeleteButton(){
		var row = dissectPlanListTable.datagrid('getSelected');
		if(row!=null){
			$.messager.confirm('确认对话框', '确定删除此次解剖计划及对应动物设置？', function(r){
 				if (r){
 					$.ajax({
 						url:sybp()+'/tblDissectPlanAction_delete.action?studyNoPara='+studyNoPara,
 						type:'post',
 						data:{id:row.id},
 						dataType:'json',
 						success:function(r){
 							if(r&&r.success){
 								dissectPlanListTable.datagrid('reload');
 								parent.parent.showMessager(1,'删除成功',true,5000);	
 							}
 						}
 					});
 				}
 			});
		}else{
			parent.parent.showMessager(2,'请选择要删除的行',true,5000);
		}
	}
	//解剖开始日期和解剖结束日期默认为同一天
    function synchronizationDissectBeginDateAndEndDate(){
    	$('#beginDate').datebox({
    	    onSelect: function(date){
    	        var beginDate=$('#beginDate').datebox('getValue');
    	        $('#endDate').datebox('setValue', beginDate);
    	    }
    	});


    }
    
    
</script>
</head>
<body>
	<div id="bodyDiv" style="display:none;">
		<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
		<s:hidden id="studyState" name="studyState"></s:hidden>
		<table id="dissectPlanListTable" >
			<tr><td>解剖计划</td></tr>
		</table>  
		<div id="dissectPlanListToolbar">
			<a id="dissectPlanListAddButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="dissectPlanListEditButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-edit',plain:true">编辑</a>
			<a id="dissectPlanListDeleteButton" class="easyui-linkbutton" onclick="onDeleteButton();" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a id="dissectPlanListAnimalCodeButton" class="easyui-linkbutton" 
					href="${pageContext.request.contextPath}/tblDissectPlanAction_animalSet.action?studyNoPara=${studyNoPara}" 
			data-options="iconCls:'icon-edit',plain:true">解剖动物设置</a>
		</div>
		<div id="dialogToolbar">
			<a id="saveButton" class="easyui-linkbutton" onclick="onSaveButton();" data-options="iconCls:'icon-add',plain:true">保存</a>
			<a id="backButton" class="easyui-linkbutton" onclick="javascript:$('#addDialog').dialog('close');" data-options="iconCls:'icon-back',plain:true">返回</a>
		</div>
	</div>
	
	
	
	
	<!-- 弹窗开始 -->
	<div id="addDialog" class="easyui-dialog" title="新增解剖计划" modal="true" closed="true" style="display:'none';width:340px;height:233px;" closable="false"
	data-options="toolbar:'#dialogToolbar'">
	<div id="addDialog2" style="display:none;">
	<s:form id="addForm" method="post">
		<s:hidden id="id" name="id"></s:hidden>
		<table class="dissectPlanAddTable">
			<tr>
				<td width="80px;">
						专题编号
				</td>
				<td width="150px;">
					<s:textfield name="studyNoPara" readonly="true"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
						解剖次数
				</td>
				<td >
					<s:textfield id="dissectNum" name="dissectNum" readonly="true"></s:textfield>
				</td>
			</tr>
			<tr>
				<td>
						描述
				</td>
				<td >
					<s:textfield id="describe" name="describe" cssClass="easyui-combobox" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td ">
						开始日期
				</td>
				<td >
					<s:textfield id="beginDate" name="beginDate"  cssClass="easyui-datebox" data-options="required:true" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td >
						结束日期
				</td>
				<td>
					<s:textfield id="endDate" name="endDate" readonly="true" cssClass="easyui-datebox" data-options="required:true"></s:textfield>
				</td>
			</tr>
		</table>
	</s:form>
	</div>
</div>
	<!-- 弹窗结束 -->
</body>
</html>
