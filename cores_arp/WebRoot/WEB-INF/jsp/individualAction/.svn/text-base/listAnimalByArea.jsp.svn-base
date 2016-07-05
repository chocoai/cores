<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>总揽</title>
		<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/style/css/style.css"
			media="screen" />
		<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function() {
		tableHeight = document.body.clientHeight - 60;
		tableWidth = document.body.clientWidth;

		initAreaCombobox();
		showRoompeTable('');
		$('#area').combobox({onSelect:function(rec){var area=rec.id;$('#animalTable').datagrid({url:sybp()+'/individualAction_loadListAnimalByArea.action?area='+area+'&areaName=A-101'})}});
		//显示整个布局
		$('#layoutMainDiv').css('display', '');
	});//匿名函数结束
	function showRoompeTable(area) {
		$('#animalTable')
				.datagrid(
						{
							url : sybp()
									+ '/individualAction_loadListAnimalByArea.action?area='
									+ area,
							title : '',
							height : tableHeight,
							width : tableWidth,
							nowarp : false,//单元格里自动换行
							singleSelect : true,
							fitColumns : false,
							pagination : true,//分页
							sortName : 'id',
							columns : [ [ {
								title : 'id',
								field : 'id',
								width : 150,
								hidden : true
							}, {
								title : '房舍名称',
								field : 'room',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '动物种类',
								field : 'animaltype',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '公猴数量',
								field : 'malemonkey',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '母猴数量',
								field : 'femalemonkey',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '育成猴数',
								field : 'yuchengmonkey',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '仔猴数量',
								field : 'cubmonkey',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '饲养员',
								field : 'keeper',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '兽医',
								field : 'veterinarian',
								width : 80,
								halign : 'center',
								align : 'center'
							}, {
								title : '主管',
								field : 'boss',
								width : 80,
								halign : 'center',
								align : 'center'
							} ] ],
							onSelect : function(rowIndex, rowData) {

							},
							onLoadSuccess : function(data) {

							},
							toolbar : '#toolbar',
							pageSize : 12,//默认选择的分页是每页5行数据         
							pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
							nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
							striped : true,//设置为true将交替显示行背景。      
							collapsible : true
						//显示可折叠按钮 

						});
	}

	function onSearchButton() {
		var area = $('#area').combobox('getValue');
		showRoompeTable(area);
	}
	function initAreaCombobox() {
		$('#area').combobox({
			url : sybp() + '/areaAction_getPareaNameId.action',
			valueField : 'id',
			textField : 'text',
			editable : false,
			onLoadSuccess : function(none) {

			}

		});
	}
</script>
	</head>
	<body>
		<s:hidden id="Aid" name="Aid"></s:hidden>
		<s:hidden id="onchange" name="onchange"></s:hidden>
		<div id="progressBar"
			style="position: absolute; z-index: 100; left: 30%; top: 50%;">
			<div id="p" style="width: 400px;"></div>
		</div>
		<div id="layoutMainDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs" class="easyui-tabs" fit="true" border="false">
					<div title="总揽" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">

								&nbsp;&nbsp;养殖区域：
								<input id="area" type="text" name="area" style="width: 100px;"></input>
								<a id="searchButton" class="easyui-linkbutton"
									onclick="onSearchButton();"
									data-options="iconCls:'icon-search',plain:true">搜索</a>
							</div>
						</div>
						<table id="animalTable" style="height: auto;"></table>
					</div>
				</div>
				<div id="toolbar">
				</div>
			</div>
		</div>
	</body>
</html>