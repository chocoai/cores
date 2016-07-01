<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>高级查询</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<style type="text/css">
	.tree-icon {
		width:0px;
	}
	.datagrid-row-selected{
		background:#d2dfff;
	}
	/*
	.tabs-title{
		color:#FFFFFF;
	}
	*/
</style>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 135
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 135;
		tableWidth = document.body.clientWidth;
		$('#highGradeTreeTable').treegrid({ 
			//url:sybp()+'/tblIntegratedInformAction_loadHighGradeQuery.action',
			idField:'id',   
			fit:true, 
			fitColumns:true,
			treeField:'code', 
			height:tableHeight,
			width:tableWidth,
			animate:false,  
			//pagination:true, 
			singleSelect:true, //不支持多选
			columns:[[  
				{title:'id',field:'id',width:10,hidden:true},
				{field:'_parentId',title:'_parentId',width:10,hidden:true},
				{field:'code',title:'编号',width:200,halign:'center'},
				{field:'name',title:'名称',width:240,halign:'center'},
				{field:'type',title:'类型',width:70,halign:'center'},
				{field:'text',title:'',width:500,halign:'center'}
			]],
			rowStyler:function(field,row){
				var level = field.level;
				if(level == 3){
					return 'color:#FF8C00;';
				}else if(level == 2){
					return 'color:green;';
				}
				return 'color:blue;';
			},
			onLoadSuccess:function(row, data){
			   $.messager.progress('close');
				if(data.rows){
					var rows = data.rows;
					for(var i = 0 ;i<rows.length;i++){
						if(!rows[i]._parentId){
							$('#highGradeTreeTable').treegrid('select',rows[i].id);
							$('#selectContractButton').linkbutton('enable');
							break;
						}
					}
					$('#total').html(data.total+'');
					$('#sTotal').html(data.sTotal+'');
					$('#tTotal').html(data.tTotal+'');
				}else{
					$('#selectContractButton').linkbutton('disable');
					$('#total').html('0');
					$('#sTotal').html('0');
					$('#tTotal').html('0');
				}
			},
			onBeforeLoad:function(){
			  // $.messager.progress(); 
			   
			},
			toolbar:'#toolbar'
		});//treegrid end


		//var pager = $('#highGradeTreeTable').treegrid('getPager');    // get the pager of datagrid
        //pager.pagination({
	     //   showPageList:false,
	     //   showRefresh:false,
	      //  loading:true,
	     //   displayMsg:	' 合同共 &nbsp;&nbsp;{total}&nbsp;&nbsp;条记录,&nbsp;&nbsp;供试品共&nbsp;&nbsp;<span id="tTotal">0</span>&nbsp;&nbsp;条记录,&nbsp;&nbsp;委托项目共&nbsp;&nbsp;<span id="sTotal">0</span>&nbsp;&nbsp;条记录',
	     //   beforePageText:'',
	     //   afterPageText:'',
	    //});

		//添加面板展开和折叠事件
		$('#layoutMainDiv').layout('panel','north').panel({
			onCollapse:function(){
				$('#condition').css("display",'');
				$('#condition1').css("display",'');
			},
			onExpand:function(){
				$('#condition').css("display",'none');
				$('#condition1').css("display",'none');
			}
		});
		
		$('#layoutMainDiv').css('display','');

		$('div#tt div input').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
            	onSubmit();
            }
        });

		$('#glpFlag').bind('keypress',function(event){
			if(event.keyCode == '13'){
				onSubmit();
			}
		});

		$('#tiCode').bind('keypress',function(event){
			if(event.keyCode == '13'){
				onSubmit();
			}
		});
		
		
	});  
	
	//查询
	function onSubmit(){
	$.messager.progress({text:'数据处理中,请稍后...',interval:50});
	       $.ajax({
				url:sybp()+'/tblIntegratedInformAction_loadHighGradeQuery.action',
				type:'post',
				data:$('#highGradeQueryForm').serialize(),
				dataType:'json',
				success:function(r){
					if(r ){
					   if(r && r.success){
						      if(r.rows ){
									$('#highGradeTreeTable').treegrid('loadData',r);
								}else{
									$('#highGradeTreeTable').treegrid('loadData',[]);
								}
								$('#layoutMainDiv').layout('collapse','north');
						 }else{
						     $.messager.progress('close');
						     $.messager.alert('消息','请填写查询条件！','info');
						 }
						
					}
				}
			});
		
	}
	//查看合同
	function onViewContract(){
		var row = $('#highGradeTreeTable').treegrid('getSelected');
		if(row){
			while(row._parentId){
				row = $('#highGradeTreeTable').treegrid('getParent',row.id);
			}
			var contractCode = row.code;
			parent.window.frames["left"].frames["left1"].updateCodeCombobox(contractCode);
		}
	}
</script>
</head>
<body>
<!-- 
 -->
<div id="condition1" class="panel-title" style="position:absolute;top:6px;left:15px;z-index:200;display:none;">高级查询</div>
<div id="condition" class="panel-tool" style="top:15px;right:20px;z-index:200;display:none;">
	<a href="javascript:void(0);"class="panel-title" onclick="$('#layoutMainDiv').layout('expand','north');" style="width:50px;color:#575765;">查询条件</a>
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
	<div  region="center" title="" border="false" style="overflow: hidden;">
		<!-- 表格 -->
		<table id="highGradeTreeTable"></table>
		<div id="toolbar">
			<a id="selectContractButton" class="easyui-linkbutton" onclick="onViewContract();" 
				data-options="iconCls:'icon-tablemultiple',plain:true,disabled:true">查看合同</a>
		</div>
 	</div>
 	<div region="south" border ="true" style="height:30px;text-align:right;padding-top:5px; border-top: 0px;" >
		合同共 &nbsp;&nbsp;<span id="total">0</span>&nbsp;&nbsp;条记录,&nbsp;&nbsp;供试品共&nbsp;&nbsp;<span id="tTotal">0</span>&nbsp;&nbsp;条记录,&nbsp;&nbsp;委托项目共&nbsp;&nbsp;<span id="sTotal">0</span>&nbsp;&nbsp;条记录&nbsp;&nbsp;
 	</div>
	<div id="regionNorth" data-options="region:'north',split:false,collapsed:false" style="height:135px;" border="false">
		<div id="tt" class="easyui-tabs" fit="true">   
		    <div title="高级查询" border="false" style="overflow: auto;background-color: #fafafa;">   
				<form id="highGradeQueryForm" name="highGradeQueryForm" action="">
				    <div style="height:20px;padding:8px 15px 2px 15px;">
				      	<span style="margin:0 5px 0 10px;">合同编号</span><input id="contractCode" name="contractCode" type="text" style="width:120px;"/>
				      	<span style="margin:0 5px 0 10px;">合同名称</span><input id="contractName" name="contractName" type="text" style="1width:50px;"/>
				      	<span style="margin:0 5px 0 10px;">委托方名称</span><input id="sponsorName" name="sponsorName" type="text" style="width:150px;"/>
				      	<span style="margin:0 5px 0 10px;">联系人</span><input id="sponsorLinkman" name="sponsorLinkman" type="text" style="width:60px;"/>
				      	<span style="margin:0 5px 0 10px;">电话/手机</span><input id="sponsorTel" name="sponsorTel" type="text" style="width:95px;"/>
				    </div>
				    <div style="height:20px;padding:8px 15px 2px 15px;">
				      	<span style="margin:0 5px 0 10px;">供试品类型</span>
				      	<select id="tiCode" name="tiCode" style="width:108px;">   
						    <option value="00"></option>   
						    <option value="01">医药</option>   
						    <option value="02">农药</option>   
						    <option value="03">化学品</option>   
						</select>  
				    	<span style="margin:0 5px 0 10px;">供试品编号</span><input id="tiNo" name="tiNo" type="text" style="width:135px;"/>
				      	<span style="margin:0 5px 0 10px;">供试品名称</span><input id="tiName" name="tiName" type="text" style="width:150px;"/>
				    </div>
				    <div style="height:20px;padding:8px 15px 2px 15px;">
				    	<span style="margin:0 5px 0 10px;">项目编号</span><input id="studyNo" name="studyNo" type="text" style="width:120px;"/>
				      	<span style="margin:0 5px 0 10px;">项目名称</span><input id="studyName" name="studyName" type="text" />
				      	<span style="margin:0 5px 0 10px;">是否GLP</span>
				      	<select id="glpFlag" name="glpFlag" style="width:58px;">
				      		<option value="-1"></option>
				      		<option value="1">是</option>
				      		<option value="0">否</option>
				      	</select>
				      	<span style="margin:0 5px 0 10px;">SD</span><input id="sd" name="sd" type="text" style="width:67px;"/>
				      	<span style="margin:0 5px 0 10px;">
				      	<a class="easyui-linkbutton" onclick="onSubmit();" data-options="iconCls:'icon-search',plain:true">查询</a></span>
				    </div>
				    <div class="panel-tool" style="top:15px;">
						<a class="layout-button-up" onclick="$('#layoutMainDiv').layout('collapse','north');"></a>
					</div>
				</form>
		    </div>   
		    
		</div>  
				
	</div>
	
</div>
</body>
</html>
