<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
		var tableHeight;//当前页面可见区域高度 - 86
		var tableWidth;
		var tblResManagerTable ;
		var currentResId;
		var resFullName;
		$(function(){
			tableHeight = document.body.clientHeight - 86;
			tableWidth  = document.body.clientWidth;
			currentResId = $('#currentResId').val();
			resFullName = $('#resFullName').val();
			tblResManagerTable=$('#tblResManagerTable').datagrid({
				url : sybp()+'/tblResManagerAction_loadResManager.action?currentResId='+currentResId+'&resFullName='+encodeURIComponent(resFullName),
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
					title:'资源名称',
					field:'resFullName',
					width:150,
					halign:'center',
					align:'center'
				},{
					title:'负责人',
					field:'leader',
					width:150,
					halign:'center',
					align:'center'
				},{
					title:'开始日期',
					field:'startDate',
					width:80,
					halign:'center',
					align:'center'
				},{
					title:'结束日期(确认人)',
					field:'endDate',
					width:130,
					halign:'center',
					align:'left'
		        },{
					title:'确认人',
					field:'signer',
					width:150,
					halign:'center',
					align:'center'
		        } ,{
					title:'',
					field:'resManager',
					width:150,
					halign:'center',
					align:'center',
					hidden:true
		        }]],
				//工具栏
				toolbar:'#toolbar',
				onSelect :function(rowIndex, rowData){
				    if(rowData.signer == '' || rowData.signer == null){
				    	$('#delResManagerButton').linkbutton('enable');
				    	$('#noavailResManagerButton').linkbutton('disable');
				    	$('#signResManagerButton').linkbutton('enable');
					}else if(rowData.endDate !='' && rowData.endDate !=null){
				    	$('#delResManagerButton').linkbutton('disable');
				    	$('#noavailResManagerButton').linkbutton('disable');
				    	$('#signResManagerButton').linkbutton('disable');
					}else{
				    	$('#delResManagerButton').linkbutton('disable');
				    	$('#noavailResManagerButton').linkbutton('enable');
				    	$('#signResManagerButton').linkbutton('disable');
					}
			    },
			    onLoadSuccess:function(data){
					if(data.rows.length <= 0){
				     document.getElementById('addResManagerButton').click();
		           }
				}
			   
		   	}); //end datagrid
			
		  //显示整个布局
			$('#layoutMainDiv').css('display','');	

			
			//按钮置为无效
	    	$('#delResManagerButton').linkbutton('disable');
	    	$('#noavailResManagerButton').linkbutton('disable');
	    	$('#signResManagerButton').linkbutton('disable');
	    	
	    	
				   	
		});//匿名函数结束
		
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
			
			$('#resManagerTable').datagrid('reload');
			
			$('#addResManagerDialog').dialog('setTitle','动物房负责人添加');
			$('#addResManagerDialog').dialog('open');
			$('#addResManagerDialog2').dialog('open');

		}
		//检查日期
		function checkDate(endtime){
			var r = endtime.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
	        if(r==null){
	        	return false
	        }else{
	          var d= new Date(r[1], r[3]-1, r[4]);     
	          return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]); 
	        }
		}
		//保存按钮（dialog）
		function onSaveButton(){
			var startDate = $('#startDate').datebox('getValue')
			if(startDate==null || startDate=='' ){
				$.messager.alert('警告','请选择开始日期');    
			}else{
				var selectedRows = $('#resManagerTable').datagrid('getSelections');
				var userNames = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].id;
					userNames = userNames.concat(userName);
				}
				var userNamesStr = userNames.join(",");
				if(userNames.length > 0){
					$.ajax({
						url:sybp()+'/tblResManagerAction_save.action',
						type:'post',
						data:{
							userNamesStr:userNamesStr,
							startDate:startDate,
							currentResId:currentResId
							},
						dataType:'json',
						success:function(r){
							if(r && r.success){
								tblResManagerTable.datagrid('reload');
								onBackButton();

								//按钮置为无效
						    	$('#delResManagerButton').linkbutton('disable');
						    	$('#noavailResManagerButton').linkbutton('disable');
						    	$('#signResManagerButton').linkbutton('disable');
         
								var ids = r.obj;
								$('#signAllIds').val(ids);
								parent.parent.showMessager(1,r.msg,true,5000);
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

			if( $('#resManagerAddForm').form('validate') ){
				
			}else{
				
			}
			//
		}
		
		
		function afterSignAll(){
		  var ids = $('#signAllIds').val();
		  $.ajax({
					url:sybp()+'/tblResManagerAction_signResManager.action',
					type:'post',
					data:{
						ids:ids
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								tblResManagerTable.datagrid('reload');
								//按钮置为无效
						    	$('#delResManagerButton').linkbutton('disable');
						    	$('#noavailResManagerButton').linkbutton('disable');
						    	$('#signResManagerButton').linkbutton('disable');
						    	
								parent.parent.showMessager(1,'签字成功',true,5000);
							}else{
								$.messager.alert('警告',"与数据库交互异常");
							}
						}
				});
		}
		//返回(添加dialog)
		function onBackButton(){
			$('#addResManagerDialog').dialog('close');
		}
		//删除
		function onDelButton(){
			var rowData = tblResManagerTable.datagrid('getSelected');
			if(null != rowData){
				$.ajax({
					url:sybp()+'/tblResManagerAction_delete.action',
					type:'post',
					data:{
						id:rowData.id
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								var rowIndex = tblResManagerTable.datagrid('getRowIndex',rowData);
								tblResManagerTable.datagrid('deleteRow',rowIndex);
								//按钮置为无效
						    	$('#delResManagerButton').linkbutton('disable');
						    	$('#noavailResManagerButton').linkbutton('disable');
						    	$('#signResManagerButton').linkbutton('disable');
						    	
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
		//签字
		function onSignButton(){
			var rowData = tblResManagerTable.datagrid('getSelected');
			if(null != rowData && (rowData.signer == null || rowData.signer =='')){
				qm_showQianmingDialog('afterSign');
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		//签字事件
		function afterSign(){
			var rowData = tblResManagerTable.datagrid('getSelected');
			if(null != rowData){
				$.ajax({
					url:sybp()+'/tblResManagerAction_sign.action',
					type:'post',
					data:{
						id:rowData.id
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								tblResManagerTable.datagrid('reload');
								//按钮置为无效
						    	$('#delResManagerButton').linkbutton('disable');
						    	$('#noavailResManagerButton').linkbutton('disable');
						    	$('#signResManagerButton').linkbutton('disable');
						    	
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
		//置为无效
		function onNovailButton(){
			var rowData = tblResManagerTable.datagrid('getSelected');
			if(null != rowData && (rowData.signer != null && rowData.signer !='')
					&& (rowData.endDate ==null || rowData.endDate == '' )){
				//qm_showQianmingDialog('afterSign');
				$('#novaliId').val(rowData.id);
				$('#novaliLeader').html(rowData.leader);
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
				qm_showQianmingDialog('afterSign_novail');
			}
		}
		//置为无效，签字后事件
		function afterSign_novail(){
			$.ajax({
				url:sybp()+'/tblResManagerAction_novail.action',
				type:'post',
				data:$('#novaliForm').serialize(),
				dataType:'json',
				success:function(r){
						if(r && r.success){
							tblResManagerTable.datagrid('reload');
							//按钮置为无效
					    	$('#delResManagerButton').linkbutton('disable');
					    	$('#noavailResManagerButton').linkbutton('disable');
					    	$('#signResManagerButton').linkbutton('disable');
					    	
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
		
		//回到list页面
		function onExitButton(){
			window.location.href=sybp()+'/tblResManagerAction_list.action?currentResId='+currentResId;
		}
		
</script>
</head>
<body>
<s:hidden id="currentDate" name="currentDate"></s:hidden>
<s:hidden id="currentResId" name="currentResId"></s:hidden>
<s:hidden id="resFullName" name="resFullName"/>	
<s:hidden id="signAllIds" name="signAllIds"/>	
<div id="layoutMainDiv" class="easyui-tabs" fit="true" border="true" style="display:none">
	<div title="动物房负责人设置"  border="false" style="overflow:hidden;">
		<!-- 工具栏-start -->
		<div style="height: 27px; padding-left: 5px; padding-top: 1px; border: 1px solid #c9c9c9;">
			<a id="addResManagerButton" class="easyui-linkbutton"  plain="true"
						onclick="onAddButton();" data-options="iconCls:'icon-useradd'">添加</a>

			<a id="delResManagerButton" class="easyui-linkbutton" plain="true"
						onclick="onDelButton();" data-options="iconCls:'icon-userdel'">删除</a>
			<a id="noavailResManagerButton" class="easyui-linkbutton" plain="true"
						onclick="onNovailButton();" data-options="iconCls:'icon-dateend'">设置结束日期</a>
			<a id="signResManagerButton" class="easyui-linkbutton" plain="true"
						onclick="onSignButton();" data-options="iconCls:'icon-edit'">签字确认</a>
			<a id="exitButton" class="easyui-linkbutton" plain="true"
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
		</div>
		<!-- 工具栏-end-->
		<div style="border-bottom: 1px solid #c8c8c8; height:20px;margin-top:5px;">
			&nbsp;&nbsp;&nbsp;&nbsp;资源名称：<s:property value="{#resFullName}"/>
		</div>
		<div>
			<table id="tblResManagerTable" ></table>
		</div>
		<%@include file="/WEB-INF/jsp/tblResManagerAction/addResManagerDialog.jspf" %>
		<%@include file="/WEB-INF/jsp/tblResManagerAction/novaliDialog.jspf" %>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</div>

</body>
</html>