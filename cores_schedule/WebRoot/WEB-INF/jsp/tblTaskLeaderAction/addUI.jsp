<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblResManager.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
    var tableHeight;//当前页面可见区域高度 - 86
	var tableWidth;
	var taskid;
	var tblTaskLeadersTable;
    $(function(){
            tableHeight = document.body.clientHeight - 86;
			tableWidth  = document.body.clientWidth;
			taskid = $('#taskid').val();
			tblTaskLeadersTable=$('#tblTaskLeadersTable').datagrid({
				url : sybp()+'/tblTaskLeaderAction_loadtaskLeader.action?id='+taskid,
				title:'',
				height:tableHeight,
				width:tableWidth,
				iconCls:'',//'icon-save',
				nowarp:  false,//单元格里自动换行
				fit:false,
				striped:true,
				fitColumns:false,
				pagination:false,
				singleSelect:true,
				columns :[[{
					title:'',
					field:'id',
					width:150,
					hidden:true
				},{
					title:'负责人',
					field:'taskLeader',
					width:150,
					halign:'center',
					align:'center'
				},{
					title:'开始日期',
					field:'startTime',
					width:80//,
					//halign:'center',
					//align:'center'
				},{
					title:'结束日期(确认人)',
					field:'endTime',
					width:130//,
					//halign:'center',
					//align:'left'
		        },{
					title:'确认人',
					field:'signId',
					width:150,
					halign:'center',
					align:'center'
		        } ]],
				//工具栏
				toolbar:'#toolbar',
				onSelect :function(rowIndex, rowData){
				    if(rowData.signId == '' || rowData.signId == null){
				    	$('#delTaskLeaderButton').linkbutton('enable');
				    	$('#noavailTaskLeaderButton').linkbutton('disable');
				    	$('#signTaskLeaderButton').linkbutton('enable');
					}else if(rowData.endDate !='' && rowData.endDate !=null){
				    	$('#delTaskLeaderButton').linkbutton('disable');
				    	$('#noavailTaskLeaderButton').linkbutton('disable');
				    	$('#signTaskLeaderButton').linkbutton('disable');
					}else{
				    	$('#delTaskLeaderButton').linkbutton('disable');
				    	$('#noavailTaskLeaderButton').linkbutton('enable');
				    	$('#signTaskLeaderButton').linkbutton('disable');
					}
			    },
			    onLoadSuccess:function(data){
					    if(data.rows.length <= 0){
					      document.getElementById('addTaskLeaderButton').click();
					    }
			    }
			    
			   
		   	}); //end datagrid
    
        //显示整个布局
		$('#layoutMainDiv').css('display','');	
    });
	
	
	
	//回到list页面
	function onExitButton(){
	    var taskid = $('#taskid').val();
		window.location.href=sybp()+'/tblTaskLeaderAction_list.action?tlid='+taskid;
	}
	
	//签字
		function onSignButton(){
			var rowData = tblTaskLeadersTable.datagrid('getSelected');
			if(null != rowData && (rowData.signId == null || rowData.signId =='')){
				qm_showQianmingDialog('afterSign');
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
	
	//签字事件
		function afterSign(){
			var rowData = tblTaskLeadersTable.datagrid('getSelected');
			if(null != rowData){
				$.ajax({
					url:sybp()+'/tblTaskLeaderAction_sign.action',
					type:'post',
					data:{
						id:rowData.id
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								tblTaskLeadersTable.datagrid('reload');
								//按钮置为无效
						    	$('#delTaskLeaderButton').linkbutton('disable');
						    	$('#noavailTaskLeaderButton').linkbutton('disable');
						    	$('#signTaskLeaderButton').linkbutton('disable');
								parent.parent.showMessager(1,'签字成功',true,5000);
							}else{
								$.messager.alert('警告',r.msg);
							}
						}
				});
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		
		//删除
		function onDelButton(){
			var rowData = tblTaskLeadersTable.datagrid('getSelected');
			if(null != rowData){
				$.ajax({
					url:sybp()+'/tblTaskLeaderAction_delete.action',
					type:'post',
					data:{
						id:rowData.id
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								var rowIndex = tblTaskLeadersTable.datagrid('getRowIndex',rowData);
								tblTaskLeadersTable.datagrid('deleteRow',rowIndex);
								//按钮置为无效
						    	$('#delTaskLeaderButton').linkbutton('disable');
						    	$('#noavailTaskLeaderButton').linkbutton('disable');
						    	$('#signTaskLeaderButton').linkbutton('disable');
								parent.parent.showMessager(1,'删除成功',true,5000);
							}else{
								$.messager.alert('警告',r.msg);
							}
						}
				});
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		//补0函数
		function appendZero(s){
			return ("00"+ s).substr((s+"").length);
		}  
		
		//添加按钮事件
	    function onAddButton(){
			var d = new Date();
			var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
			  // $('#oneDayTime').datebox('setValue', oneDayTime);
			$('#startDate').datebox('setValue',oneDayTime);
			$('#addTaskLeaderDialog').dialog('setTitle','常规任务操作者添加');
			$('#addTaskLeaderDialog').dialog('open');
			$('#addTaskLeaderDialog2').dialog('open');
		}
		
		//返回(添加dialog)
		function onBackButton(){
			$('#addTaskLeaderDialog').dialog('close');
		}
		
		//保存按钮（dialog）
		function onSaveButton(){
			var startDate = $('#startDate').datebox('getValue')
			if(startDate==null || startDate=='' ){
				$.messager.alert('警告','请选择开始日期');    
			}else{
				var selectedRows = $('#taskLeaderTable').datagrid('getSelections');
				var userNames = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].id;
					userNames = userNames.concat(userName);
				}
				var userNamesStr = userNames.join(",");
				if(userNames.length > 0){
					$.ajax({
						url:sybp()+'/tblTaskLeaderAction_save.action',
						type:'post',
						data:{
							userNamesStr:userNamesStr,
							startDate:startDate,
							taskTypeID:taskid
							},
						dataType:'json',
						success:function(r){
							if(r && r.success){
								tblTaskLeadersTable.datagrid('reload');
								onBackButton();

								//按钮置为无效
						    	$('#delTaskLeaderButton').linkbutton('disable');
						    	$('#noavailTaskLeaderButton').linkbutton('disable');
						    	$('#signTaskLeaderButton').linkbutton('disable');

								
								parent.parent.showMessager(1,r.msg,true,5000);
								var ids = r.obj;
								$('#signAllIds').val(ids)
								$.messager.defaults = { ok: "是", cancel: "否" }; 
								
				                $.messager.confirm('确认','是否签字提交!',function(r){    
				                		  if (r){
				                			 qm_showQianmingDialog('afterSignAll()');
				                		  }else{
				                		  }    
				                }); 
							}else{
								$.messager.alert('警告',r.msg);
							}
						}
		
					});
				}else{
					$.messager.alert('提示','请先选择负责人');  
				}

				
			}
				if( $('#taskLeaderAddForm').form('validate') ){
				
			}else{
				
			}
			//
		}
		
		function afterSignAll(){
		  var ids = $('#signAllIds').val();
		  $.ajax({
					url:sybp()+'/tblTaskLeaderAction_signAll.action',
					type:'post',
					data:{
						ids:ids
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								tblTaskLeadersTable.datagrid('reload');
								//按钮置为无效
						    	$('#delTaskLeaderButton').linkbutton('disable');
						    	$('#noavailTaskLeaderButton').linkbutton('disable');
						    	$('#signTaskLeaderButton').linkbutton('disable');
								parent.parent.showMessager(1,'签字成功',true,5000);
							}else{
								$.messager.alert('警告',"与数据库交互异常");
							}
						}
				});
		}
		//置为无效
		function onNovailButton(){
			var rowData = tblTaskLeadersTable.datagrid('getSelected');
			if(null != rowData && (rowData.signId != null && rowData.signId !='')
					&& (rowData.endDate ==null || rowData.endDate == '' )){
				//qm_showQianmingDialog('afterSign');
				$('#novaliId').val(rowData.id);
				$('#novaliLeader').html(rowData.signId);
				//$('#novailEndDate').datebox('setValue','');
				var d = new Date();
				var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
				  // $('#oneDayTime').datebox('setValue', oneDayTime);
				$('#novailEndDate').datebox('setValue',oneDayTime);
				
				$('#novaliDialog').dialog('open');
				$('#novaliDialog2').dialog('open');
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}

		//在置为无效dialog上的确定按钮事件
		function onButton_novaliDialog(){
			if( $('#novaliForm').form('validate') ){
				$('#novaliDialog').dialog('close');
				var endDate  = $('#novailEndDate').datebox('getValue');
				var rowData = tblTaskLeadersTable.datagrid('getSelected');
				if(dateCompare(rowData.startTime,endDate)){
				  qm_showQianmingDialog('afterSign_novail');
				}else{
				  	$.messager.alert('警告','结束日期不能早于开始日期！');
				}
			}
		}
		//置为无效，签字后事件
		function afterSign_novail(){
			$.ajax({
				url:sybp()+'/tblTaskLeaderAction_setupInvalid.action',
				type:'post',
				data:$('#novaliForm').serialize(),
				dataType:'json',
				success:function(r){
						if(r && r.success){
							tblTaskLeadersTable.datagrid('reload');
							//按钮置为无效
					    	$('#delTaskLeaderButton').linkbutton('disable');
					    	$('#noavailTaskLeaderButton').linkbutton('disable');
					    	$('#signTaskLeaderButton').linkbutton('disable');
					    	
							parent.parent.showMessager(1,'设置结束日期成功',true,5000);
						}else{
							$.messager.alert('警告',r.msg);
						}
					}
			});
		}
		//在置为无效dialog上的返回按钮事件
		function onBackButton_novaliDialog(){
			$('#novaliDialog').dialog('close');
		}
		
		
		
</script>
</head>
<body>
<s:hidden id="taskid" name="taskid"/>	
<s:hidden id="taskFullName" name="taskFullName"/>	
<s:hidden id="signAllIds" name="signAllIds"/>	
<div id="layoutMainDiv" class="easyui-tabs" fit="true" border="true" style="display:none">
	<div title="常规任务操作者设置"  border="false" style="overflow:hidden;">
		<!-- 工具栏-start -->
		<div style="height: 27px; padding-left: 5px; padding-top: 1px; border: 1px solid #c9c9c9;">
			<a id="addTaskLeaderButton" class="easyui-linkbutton"  plain="true"
						onclick="onAddButton();" data-options="iconCls:'icon-useradd'">添加</a>

			<a id="delTaskLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onDelButton();" data-options="iconCls:'icon-userdel',disabled:true">删除</a>
			<a id="noavailTaskLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onNovailButton();" data-options="iconCls:'icon-dateend',disabled:true">设置结束日期</a>
			<a id="signTaskLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onSignButton();" data-options="iconCls:'icon-edit',disabled:true">签字确认</a>
			<a id="exitButton" class="easyui-linkbutton" plain="true"
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
		</div>
		<!-- 工具栏-end-->
		<div style="border-bottom: 1px solid #c8c8c8; height:20px;margin-top:5px;">
			&nbsp;&nbsp;&nbsp;&nbsp;常规任务名称：<s:property value="{#taskFullName}"/>
		</div>
		<div>
		  <table id="tblTaskLeadersTable" ></table>
		</div>
	    <%@include file="/WEB-INF/jsp/tblTaskLeaderAction/addTaskLeaderDialog.jspf" %>
	    <%@include file="/WEB-INF/jsp/tblTaskLeaderAction/novaliDialog.jspf" %>
	    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</div>

</body>
</html>