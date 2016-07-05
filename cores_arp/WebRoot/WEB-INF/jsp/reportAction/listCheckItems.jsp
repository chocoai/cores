<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>检疫查询</title>
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
		$('#SearchButton').searchbox({
			height : 19,
			width : 450,
			searcher : function(value, name) {
				var monkeyId = $('#searchmonkeyid').val();
				var checkdate = $('#checkdate').datebox('getValue');
				var checkItem = $('#checkItem').combobox('getValue');
				//showCheckItemTable(monkeyId, checkdate, checkItem);
			},
			prompt : '模糊查询,请输入结论关键字'
		});
		showCheckItemTable('', '', '');
		//默认先显示Virus table.		
		//showCheckItemTable('','','virus');
		QcCheckTab();
		$('#layoutMainDiv').css('display', '');
	});//匿名函数结束
	function showCheckItemTable(monkeyid, checkdate, checkItem) {
		//病毒检测
		if (checkItem == "virus") {
			//window.location.href="${pageContext.request.contextPath}/virusAction_listVirus.action";
			//document.getElementById("virus").style.display="block";
			$('#layoutMainDiv').css('display', 'none');
			$('#layoutVirusDiv').css('display', '');
			$('#virusTable').datagrid(
					{
						url : sybp()
								+ '/virusAction_virusByJson.action?monkeyid='
								+ monkeyid + '&cdate='
								+ encodeURIComponent(checkdate),
						title : '病毒检测',
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
							title : '房号',
							field : 'roomid',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '笼号',
							field : 'lhao',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '猴子编号',
							field : 'monkeyid',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '性别',
							field : 'sex',
							width : 80,
							halign : 'center',
							align : 'center'

						}, {
							title : '血清号',
							field : 'xueq',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '采样',
							field : 'cy',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : 'B-V',
							field : 'bv',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : 'STLV',
							field : 'stlv',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : 'SRV',
							field : 'srv',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : 'SIV',
							field : 'siv',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : 'FILO',
							field : 'filo',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '其他',
							field : 'other',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '备注',
							field : 'remark',
							width : 80

						} ] ],
						toolbar : '#toolbar1',
						pageSize : 12,//默认选择的分页是每页5行数据         
						pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
						nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
						striped : true,//设置为true将交替显示行背景。      
						collapsible : true
					//显示可折叠按钮 

					});
		}
		//细菌检测
		if (checkItem == "bacteria") {
			//document.getElementById("bacteria").style.display="block";
			//window.location.href="${pageContext.request.contextPath}/bacteriaAction_listBacteria.action";
			$('#layoutMainDiv').css('display', 'none');
			$('#layoutBacteriaDiv').css('display', '');
			$('#bacteriaTable')
					.datagrid(
							{
								url : sybp()
										+ '/bacteriaAction_bacteriaByJson.action?monkeyid='
										+ monkeyid + '&cdate='
										+ encodeURIComponent(checkdate),
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
									title : '房号',
									field : 'roomid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '笼号',
									field : 'lhao',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '猴子编号',
									field : 'monkeyid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '性别',
									field : 'sex',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '体重',
									field : 'weight',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '肛拭号',
									field : 'gangs',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '采样',
									field : 'caiy',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '沙门氏菌',
									field : 'shig',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '致贺氏菌',
									field : 'salm',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '耶尔森氏菌',
									field : 'yers',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '备注',
									field : 'remark',
									width : 80

								} ] ],
								toolbar : '#toolbar2',
								pageSize : 12,//默认选择的分页是每页5行数据         
								pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
								nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
								striped : true,//设置为true将交替显示行背景。      
								collapsible : true
							//显示可折叠按钮 

							});
		}
		//体外寄生虫
		if (checkItem == "outParasite") {
			//window.location.href="${pageContext.request.contextPath}/parasiteAction_listOutParasite.action";
			$('#layoutMainDiv').css('display', 'none');
			$('#layoutOutParasiteDiv').css('display', '');

			$('#outParasiteTable')
					.datagrid(
							{
								url : sybp()
										+ '/parasiteAction_outParasiteByJson.action?monkeyid='
										+ monkeyid + '&cdate='
										+ encodeURIComponent(checkdate),
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
									title : '房号',
									field : 'roomid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '笼号',
									field : 'lhao',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '猴子编号',
									field : 'monkeyid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '性别',
									field : 'sex',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '样本号',
									field : 'yangb',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '采样',
									field : 'caiy',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '体外寄生虫(节肢动物)',
									field : 'twjsc',
									width : 180,
									halign : 'center',
									align : 'center'
								}, {
									title : '备注',
									field : 'remark',
									width : 80

								} ] ],
								onSelect : function(rowIndex, rowData) {
								},
								onLoadSuccess : function(data) {
								},
								toolbar : '#toolbar3',
								pageSize : 12,//默认选择的分页是每页5行数据         
								pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
								nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
								striped : true,//设置为true将交替显示行背景。      
								collapsible : true
							//显示可折叠按钮 

							});
		}
		//体内寄生虫
		if (checkItem == "inParasite") {
			//window.location.href="${pageContext.request.contextPath}/parasiteAction_listInParasite.action";
			$('#layoutMainDiv').css('display', 'none');
			$('#layoutInParasiteDiv').css('display', '');
			$('#inParasiteTable')
					.datagrid(
							{
								url : sybp()
										+ '/parasiteAction_inParasiteByJson.action?monkeyid='
										+ monkeyid + '&cdate='
										+ encodeURIComponent(checkdate),
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
									title : '房号',
									field : 'roomid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '笼号',
									field : 'lhao',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '猴子编号',
									field : 'monkeyid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '性别',
									field : 'sex',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '样本号',
									field : 'yangb',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '采样',
									field : 'caiy',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '溶组织内阿米',
									field : 'nam',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '蠕虫',
									field : 'rc',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '鞭毛虫',
									field : 'bmc',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '其他',
									field : 'other',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '备注',
									field : 'remark',
									width : 80

								} ] ],
								onSelect : function(rowIndex, rowData) {

								},
								onLoadSuccess : function(data) {

								},
								toolbar : '#toolbar4',
								pageSize : 12,//默认选择的分页是每页5行数据         
								pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
								nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
								striped : true,//设置为true将交替显示行背景。      
								collapsible : true
							//显示可折叠按钮 

							});

		}
		//TB
		if (checkItem == "tb") {
			//window.location.href="${pageContext.request.contextPath}/tbAction_listTB.action";
			$('#layoutMainDiv').css('display', 'none');
			$('#layoutTBDiv').css('display', '');
			$('#tbTable').datagrid(
					{
						url : sybp() + '/tbAction_tbByJson.action?monkeyid='
								+ monkeyid + '&cdate='
								+ encodeURIComponent(checkdate),
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
							title : '房号',
							field : 'roomid',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '笼号',
							field : 'lhao',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '猴子编号',
							field : 'monkeyid',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '性别',
							field : 'sex',
							width : 80,
							halign : 'center',
							align : 'center',
							formatter : function(value, row, index) {
								if (value == 0) {
									return "公";
								} else {
									return "母";
								}
							}
						}, {
							title : '右眼',
							field : 'rightEyes',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '24h',
							field : 'tb24',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '48h',
							field : 'tb48',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '72h',
							field : 'tb72',
							width : 80,
							halign : 'center',
							align : 'center'
						}, {
							title : '备注',
							field : 'remark',
							width : 80

						} ] ],
						onSelect : function(rowIndex, rowData) {

						},
						onLoadSuccess : function(data) {

						},
						toolbar : '#toolbar5',
						pageSize : 12,//默认选择的分页是每页5行数据         
						pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
						nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
						striped : true,//设置为true将交替显示行背景。      
						collapsible : true
					//显示可折叠按钮 

					});
		}
		//疫苗
		if (checkItem == "vaccine") {
			//window.location.href="${pageContext.request.contextPath}/vaccineAction_listVaccine.action";
			$('#layoutMainDiv').css('display', 'none');
			$('#layoutVaccineDiv').css('display', '');
			$('#vaccineTable')
					.datagrid(
							{
								url : sybp()
										+ '/vaccineAction_loadListVaccineByJson.action?monkeyid='
										+ monkeyid + '&cdate='
										+ encodeURIComponent(checkdate),
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
									title : '房号',
									field : 'roomId',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '笼号',
									field : 'lhao',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '猴子编号',
									field : 'monkeyid',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '性别',
									field : 'sex',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '麻疹',
									field : 'isMeasles',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '甲肝',
									field : 'isHepatitisA',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '乙肝',
									field : 'isHepatitisB',
									width : 80,
									halign : 'center',
									align : 'center'
								}, {
									title : '备注',
									field : 'remark',
									width : 80

								} ] ],
								onSelect : function(rowIndex, rowData) {

								},
								onLoadSuccess : function(data) {

								},
								toolbar : '#toolbar6',
								pageSize : 12,//默认选择的分页是每页5行数据         
								pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
								nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
								striped : true,//设置为true将交替显示行背景。      
								collapsible : true
							//显示可折叠按钮 

							});
		}
	}
	function onSearchButton() {
		var monkeyId = $('#searchmonkeyid').val();
		var checkdate = $('#checkdate').datebox('getValue');
		var checkItem = $('#checkItem').combobox('getValue');
		showCheckItemTable(monkeyId, checkdate, checkItem);
	}
	function onBackButton() {
		window.location.href = "${pageContext.request.contextPath}/reportAction_listCheckItems.action";
	}
	
		//菜单01点击事件
/** 新建一个 临检申请tab(清除其他标签)*/
function QcCheckTab(){
	//var isAnimalIdES=$('#isAnimalIdES').val();
	//if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '驱虫检疫')) {
			$('#tabs').tabs('select', '驱虫检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '驱虫检疫',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/qcAction_listQC.action' + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	//}else{
	//	parent.showMessager(2,'请先录入动物列表',true,5000);
	//}
}
	//菜单01点击事件
/** 新建一个 临检申请tab(清除其他标签)*/
function InParasiteCheckTab(){
	//var isAnimalIdES=$('#isAnimalIdES').val();
	//if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '寄生虫检疫')) {
			$('#tabs').tabs('select', '寄生虫检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '寄生虫检疫',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/parasiteAction_listInParasite.action' + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	//}else{
	//	parent.showMessager(2,'请先录入动物列表',true,5000);
	//}
}
	//菜单01点击事件
/** 新建一个 临检申请tab(清除其他标签)*/
function OutParasiteCheckTab(){
	//var isAnimalIdES=$('#isAnimalIdES').val();
	//if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '体外寄生虫检疫')) {
			$('#tabs').tabs('select', '体外寄生虫检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '体外寄生虫检疫',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/parasiteAction_listOutParasite.action' + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	//}else{
	//	parent.showMessager(2,'请先录入动物列表',true,5000);
	//}
}
	//菜单01点击事件
/** 新建一个 临检申请tab(清除其他标签)*/
function VirusCheckTab(){
	//var isAnimalIdES=$('#isAnimalIdES').val();
	//if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '病毒检疫')) {
			$('#tabs').tabs('select', '病毒检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '病毒检疫',
				closable : false,
				content : '<iframe src="' + '${pageContext.request.contextPath}/virusAction_listVirus.action' + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
	//}else{
	//	parent.showMessager(2,'请先录入动物列表',true,5000);
	//}
}
function BacteriaCheckTab(){
//	var isAnimalIdES=$('#isAnimalIdES').val();
//	if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '细菌检疫')) {
			$('#tabs').tabs('select', '细菌检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '细菌检疫',
				closable : false,
				content : '<iframe src=${pageContext.request.contextPath}/bacteriaAction_listBacteria.action?monkeyid='+""+' frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
//	}else{
//		parent.showMessager(2,'请先录入动物列表',true,5000);
//	}
}
function VaccineCheckTab(){
//	var isAnimalIdES=$('#isAnimalIdES').val();
//	if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '疫苗检疫')) {
			$('#tabs').tabs('select', '疫苗检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '疫苗检疫',
				closable : false,
				content : '<iframe src=${pageContext.request.contextPath}/vaccineAction_listVaccine.action?monkeyid='+""+' frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
//	}else{
//		parent.showMessager(2,'请先录入动物列表',true,5000);
//	}
}
function XcgCheckTab(){
//	var isAnimalIdES=$('#isAnimalIdES').val();
//	if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '血常规检疫')) {
			$('#tabs').tabs('select', '血常规检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '血常规检疫',
				closable : false,
				content : '<iframe src=${pageContext.request.contextPath}/xcgAction_listXCG1.action?monkeyid='+""+' frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
//	}else{
//		parent.showMessager(2,'请先录入动物列表',true,5000);
//	}
}
function TbCheckTab(){
//	var isAnimalIdES=$('#isAnimalIdES').val();
//	if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', 'TB检疫')) {
			$('#tabs').tabs('select', 'TB检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : 'TB检疫',
				closable : false,
				content : '<iframe src=${pageContext.request.contextPath}/tbAction_listTB.action?monkeyid='+""+' frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
//	}else{
//		parent.showMessager(2,'请先录入动物列表',true,5000);
//	}
}
function XyshCheckTab(){
//	var isAnimalIdES=$('#isAnimalIdES').val();
//	if(isAnimalIdES ==1){
		if ($('#tabs').tabs('exists', '血生化检疫')) {
			$('#tabs').tabs('select', '血生化检疫');
		}else{
			var length = $('#tabs').tabs('tabs').length;
			for(var i=0;i<length;i++){
				$('#tabs').tabs('close',0);
			}
			$('#tabs').tabs('add', {
				title : '血生化检疫',
				closable : false,
				content : '<iframe src=${pageContext.request.contextPath}/xyshAction_listXYSH.action?monkeyid='+""+' frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
			});
		}
//	}else{
//		parent.showMessager(2,'请先录入动物列表',true,5000);
//	}
}
</script>
	</head>
	<body>
		<s:hidden id="wid" name="wid"></s:hidden>
		<s:hidden id="onchange" name="onchange"></s:hidden>
		<div id="progressBar"
			style="position: absolute; z-index: 100; left: 30%; top: 50%;">
			<div id="p" style="width: 400px;"></div>
		</div>
		<div id="layoutMainDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="north" title="" style="height:30px;">
  				<div style=" padding-left:5px;margin-top: 1px;">
	  			<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu0',iconCls:'icon-edit'" onclick="">检疫</a>  
				<div id="menu0"  class="easyui-menu" data-options="minWidth:'80px'">   
					<div id="addNewStudyPlanButton" data-options="iconCls:'icon-add'" style="width:110px;"  onclick="QcCheckTab();">驱虫检疫</div>   
					<div id="clinicalTestMenuItem" data-options="iconCls:''" onclick="InParasiteCheckTab();" style="width:110px;">寄生虫检疫</div> 
					<!--  <div id="lTiprpAppIndMenuItem" data-options="iconCls:''" onclick="OutParasiteCheckTab();" style="width:110px;">体外寄生虫检疫</div>-->
					<div id="addNewAnatomyReqMenuItem" data-options="iconCls:''" onclick="VirusCheckTab();" style="width:110px;">病毒检疫</div>
					<div id="" data-options="iconCls:''" onclick="BacteriaCheckTab();" style="width:110px;">细菌检疫</div>
					<div id="" data-options="iconCls:''" onclick="VaccineCheckTab();" style="width:110px;">疫苗检疫</div>
					<div id="" data-options="iconCls:''" onclick="XcgCheckTab();" style="width:110px;">血常规检疫</div>
					<div id="" data-options="iconCls:''" onclick="TbCheckTab();" style="width:110px;">TB检疫</div>
					<div id="" data-options="iconCls:''" onclick="XyshCheckTab();" style="width:110px;">血生化检疫</div>
				</div>
  				</div>
  			</div>
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs" class="easyui-tabs" fit="true" border="false">
					<div title="检疫查询" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								&nbsp;&nbsp;猴子编号
								<input id="searchmonkeyid" type="text" name="searchmonkeyid"
									style="width: 100px;"></input>
								&nbsp;&nbsp;&nbsp;&nbsp; 检疫日期 &nbsp; &nbsp;
								<input id="checkdate" type="text" class="easyui-datebox"
									style="width: 100px; height: 19px;" editable="false"></input>
								&nbsp;&nbsp;&nbsp;&nbsp; 检疫项目 &nbsp;
								<select id="checkItem" class="easyui-combobox" name="checkItem"
									style="width: 200px;">
									<option value="bacteria">细菌</option>
									<option value="virus">病毒</option>
									<option value="inParasite">体外寄生虫</option>
									<option value="outParasite">体内寄生虫</option>
									<option value="tb">TB</option>
									<option value="vaccine">疫苗</option>
								</select>
								&nbsp;
								<a id="searchButton" class="easyui-linkbutton"
									onclick="onSearchButton();"
									data-options="iconCls:'icon-search',plain:true">搜索</a>
							</div>
						</div>

					</div>
				</div>
				<div title="剂量设置" border="false" style="overflow: hidden;" >
				</div>
				<div title="解剖计划" border="false" style="overflow: hidden;">
				</div>
				<div title="检验指标" border="false" style="overflow: hidden;">
					检验指标
				</div>
				<div title="日程安排" border="false" style="overflow: hidden;" >
					日程安排
				</div>
				<div title="病理计划" border="false" style="overflow: hidden;" >
					病理计划
				</div>
				<div id="toolbar">
				</div>
			</div>
		</div>
		<div id="layoutVirusDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs1" class="easyui-tabs" fit="true" border="false">
					<div title="病毒检疫信息" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								<!--
			    		&nbsp;&nbsp;动物编号：
			    		<input id="monkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="treatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">诊断日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	-->
							</div>

						</div>
						<table id="virusTable"></table>
					</div>
				</div>
				<div id="toolbar1">
					<a class="easyui-linkbutton" onclick="onBackButton();"
						data-options="iconCls:'icon-back',plain:true">返回</a>
				</div>
			</div>
		</div>
		<div id="layoutBacteriaDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs1" class="easyui-tabs" fit="true" border="false">
					<div title="细菌检疫信息" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								<!--
			    		&nbsp;&nbsp;动物编号：
			    		<input id="monkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="treatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">诊断日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	-->
							</div>

						</div>
						<table id="bacteriaTable"></table>
					</div>
				</div>
				<div id="toolbar2">
					<a class="easyui-linkbutton" onclick="onBackButton();"
						data-options="iconCls:'icon-back',plain:true">返回</a>
				</div>
			</div>
		</div>
		<div id="layoutOutParasiteDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs1" class="easyui-tabs" fit="true" border="false">
					<div title="体外寄生虫检疫信息" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								<!--
			    		&nbsp;&nbsp;动物编号：
			    		<input id="monkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="treatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">诊断日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	-->
							</div>

						</div>
						<table id="outParasiteTable"></table>
					</div>
				</div>
				<div id="toolbar3">
					<a class="easyui-linkbutton" onclick="onBackButton();"
						data-options="iconCls:'icon-back',plain:true">返回</a>
				</div>
			</div>
		</div>
		<div id="layoutInParasiteDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs1" class="easyui-tabs" fit="true" border="false">
					<div title="体内寄生虫检疫信息" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								<!--
			    		&nbsp;&nbsp;动物编号：
			    		<input id="monkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="treatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">诊断日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	-->
							</div>

						</div>
						<table id="inParasiteTable"></table>
					</div>
				</div>
				<div id="toolbar4">
					<a class="easyui-linkbutton" onclick="onBackButton();"
						data-options="iconCls:'icon-back',plain:true">返回</a>
				</div>
			</div>
		</div>
		<div id="layoutTBDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs1" class="easyui-tabs" fit="true" border="false">
					<div title="TB检疫信息" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								<!--
			    		&nbsp;&nbsp;动物编号：
			    		<input id="monkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="treatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">诊断日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	-->
							</div>

						</div>
						<table id="tbTable"></table>
					</div>
				</div>
				<div id="toolbar5">
					<a class="easyui-linkbutton" onclick="onBackButton();"
						data-options="iconCls:'icon-back',plain:true">返回</a>
				</div>
			</div>
		</div>
		<div id="layoutVaccineDiv" class="easyui-layout"
			style="width: 100%; height: 100%; display: none;">
			<div region="center" title="" style="overflow: hidden;">
				<div id="tabs1" class="easyui-tabs" fit="true" border="false">
					<div title="疫苗检疫信息" border="false" style="overflow: auto;">
						<div style="height: 30px;">
							<div
								style="float: left; width: 100%; padding-top: 5px; height: 22px;">
								<!--
			    		&nbsp;&nbsp;动物编号：
			    		<input id="monkeyid" type="text" style="width:100px;"></input>主治兽医：<select id="treatveterinarian" class="easyui-combobox" name="treatveterinarian" style="width:80px;"  data-options="panelHeight:100" >  
							</select>症状名称：
			    		<input id="zzmc" type="text" style="width:100px;">诊断日期：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	-->
							</div>

						</div>
						<table id="vaccineTable"></table>
					</div>
				</div>
				<div id="toolbar6">
					<a class="easyui-linkbutton" onclick="onBackButton();"
						data-options="iconCls:'icon-back',plain:true">返回</a>
				</div>
			</div>
		</div>
	</body>
</html>