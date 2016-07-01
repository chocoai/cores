<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$('#toolsTable').datagrid({
			title:'',
			height: 30,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			//pageSize:50,
			//pageList:[50,100],
			//fit:true,
			//fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			border:false,
			//工具栏
			toolbar:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					if($('#dictTestItemTypeAddForm').form('validate')){
						$.ajax({
							type:"post",
							url:sybp()+'/dictTestItemTypeAction_save.action',
							data:$('#dictTestItemTypeAddForm').serialize(),
							cache:false,
							dataType:'json',
							success:function(r){
								if(r && r.success){
									window.location.href=sybp()+"/dictTestItemTypeAction_list.action";
								}else{
									//$.messager.alert('提示','保存数据有问题');
									parent.showMessager(3,'保存数据有问题',true,5000);
								}
							}
						});
					}
				}
			},{
				text:'返回',
				iconCls:'icon-back',
				handler:function(){
					window.location.href=sybp()+"/dictTestItemTypeAction_list.action";
				}
			}],
		}); //end datagrid
	});
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">供试品类型-添加</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<!-- 工具栏（保存 返回）start -->
			<table id="toolsTable" ></table>
			<!-- 工具栏（保存 返回）end -->
			<form id="dictTestItemTypeAddForm" method="post">
			<table width="400px;" style="margin-top: 20px;">
				<tr>
					<td width="150px;" height="30px;" align="center">供试品类型编码</td>
					<td><input  style="width:250px;" missingMessage="必填项" invalidMessage="编码已存在" class="easyui-validatebox" required="true" validType="remote[sybp()+'/dictTestItemTypeAction_checkTiCode.action','tiCode']" name="tiCode" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="center">供试品类型名称</td>
					<td><input  style="width:250px;"  missingMessage="必填项" invalidMessage="名称已存在" class="easyui-validatebox" required="true" validType="remote[sybp()+'/dictTestItemTypeAction_checkTiType.action','tiType']" name="tiType" type="text"/></td>
				</tr>
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>