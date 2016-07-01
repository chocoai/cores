<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES 档案管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentStudy.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentQA.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentSOP.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentGlpSynthesis.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentGlpSynthesis2.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentInstrument.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentEmployee.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentAdministration.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentContract.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileRecordSmplReserve.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileRecordSpecimen.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblLog2.js" charset="utf-8"></script>


<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>

<script type="text/javascript">
	  
//var tableHeight = window.screen.height-197;
var tableWidth  = window.screen.width-36;
var leftWidth = 270;

$(function(){
  	var tableHeight = document.body.clientHeight-75;
   //tableWidth  = document.body.clientWidth-25;

	$('#dictArchiveTypeTable').datagrid({
		//url : sybp()+'/tblFileContentStudyAction_getArchiveTypes.action',
		title:'',
		height: 250,
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
			width:235
		},{
			title:'类别代码',
			field:'archiveTypeCode',
			width:150
		}]],
		onDblClickRow:function(rowindex,rowData){
			chooseOneArchiveType();
		}
		
	}); //end datagrid

	/*
	$('#otherOperation').combobox({
		onSelect:function(record){
			selctOneOperate();
		}
	});
	*/

	initButton();

	$('#changePosition').combotree({
		 valueField:'text',
		 textField:'text',
		 editable:true,
		 url:sybp()+'/tblFileContentStudyAction_loadArchivePositionTree.action',	
		 onSelect:function(n){
			hasOtherInThisPlace(n.text,'change');
	 	 }
	 });

	
	
	$("#oneStorePosition6").next().children("input:text").mouseout(function() {
		if($('#addOrEditAdministration').val()==1){
			var storePosition = $('#oneStorePosition6').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition7").next().children("input:text").mouseout(function() {
		if($('#addOrEditContract').val()==1){
			var storePosition = $('#oneStorePosition7').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition5").next().children("input:text").mouseout(function() {
		if($('#addOrEditEmployee').val()==1){
			var storePosition = $('#oneStorePosition5').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition3").next().children("input:text").mouseout(function() {
		if($('#addOrEditGlpSynthesis').val()==1){
			var storePosition = $('#oneStorePosition3').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition4").next().children("input:text").mouseout(function() {
		if($('#addOrEditInstrument').val()==1){
			var storePosition = $('#oneStorePosition4').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition1").next().children("input:text").mouseout(function() {
		if($('#addOrEditQA').val()==1){
			var storePosition = $('#oneStorePosition1').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition2").next().children("input:text").mouseout(function() {
		if($('#addOrEditSOP').val()==1){
			var storePosition = $('#oneStorePosition2').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition").next().children("input:text").mouseout(function() {
		if($('#addOrEditStudy').val()==1){
			var storePosition = $('#oneStorePosition').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition8").next().children("input:text").mouseout(function() {
		if($('#addOrEditSmplReserve').val()==1){
			var storePosition = $('#oneStorePosition8').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	$("#oneStorePosition9").next().children("input:text").mouseout(function() {
		if($('#addOrEditSpecimen').val()==1){
			var storePosition = $('#oneStorePosition9').combotree('getText');
			hasOtherInThisPlace(storePosition);
		}
	});
	
	
});//匿名函数结束

function initButton(){
	var privilegeType = $('#privilegeType').val();
	if(privilegeType!=''&&privilegeType=='0')
	{
		$('#new').linkbutton('disable');
		$('#edit').linkbutton('disable');
		$('#delete').linkbutton('disable');
		$('#newAppend').linkbutton('disable');
		$('#otherOperation').linkbutton('disable');
		
	}else if(privilegeType=='1'){
		$('#new').linkbutton('enable');
		$('#edit').linkbutton('enable');
		$('#delete').linkbutton('enable');
		$('#newAppend').linkbutton('enable');
		$('#otherOperation').linkbutton('enable');
	}

}

function newOne()
{
	 var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 var sopflag = 0;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
		if(index==2)
		{
			var sopFrame = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow
			
		 	sopflag = sopFrame.$('input[name="sopflag"]:checked').val();
		 	$('#sopflag').val(sopflag);
		}
	 }else{
		 index = 0;
	 }
	 if(index==0)
	 {
		newOneStudy(1);
	 }
	 
	 if(index==1)
	 {
		newOneQA(1);
	 }
	 
	 if(index==2)
	 {
		 newOneSOP(1);
	 }
	 if(index==3)
	 {
		 newOneGlpSynthesis(1);
	 }
	 if(index==4)
	 {
		 newOneInstrument(1);
	 }
	 if(index==5)
	 {
		 newOneEmployee(1);
	 }
	 if(index==6)
	 {
		 newOneAdministration(1);
	 }
	 if(index==7)
	 {
		 newOneContract(1);
	 }
	 if(index==8)
	 {
		 newOneSmplReserve(1);
	 }
	 if(index==9)
	 {
		 newOneSpecimen(1);
	 }
	 if(index==10)
	 {
		 newOneGlpSynthesis2(1);
	 }
	 if(index==11)
	 {
		$.messager.alert('提示框','操作日志不可以新建！');
	 }
	 
}
function editOne()
{
	 var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
		 if(index==2)
		{
			var sopFrame = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow
				
			 sopflag = sopFrame.$('input[name="sopflag"]:checked').val();
			$('#sopflag').val(sopflag);
		}
	 }else{
		 index = 0;
	 }
	 if(index==0)
	 {
		newOneStudy(2);
	 }
	 if(index==1)
	 {
		newOneQA(2);
	 }
	 if(index==2)
	 {
		 newOneSOP(2);
	 }
	 if(index==3)
	 {
		 newOneGlpSynthesis(2);
	 }
	 if(index==4)
	 {
		 newOneInstrument(2);
	 }
	 if(index==5)
	 {
		 newOneEmployee(2);
	 }
	 if(index==6)
	 {
		 newOneAdministration(2);
	 }
	 if(index==7)
	 {
		 newOneContract(2);
	 }
	 if(index==8)
	 {
		 newOneSmplReserve(2);
	 }
	 if(index==9)
	 {
		 newOneSpecimen(2);
	 }
	 if(index==10)
	 {
		 newOneGlpSynthesis2(2);
	 }
	 if(index==11)
	 {
		$.messager.alert('提示框','操作日志不可以编辑！');
	 }
}
function deleteOne()
{
	var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
	 }else{
		 index = 0;
	 }
	 if(index==0)
	 {
		 deleteOneStudyRecord();
	 }
	 if(index==1)
	 {
		 deleteOneQA();
	 }
	 
	 if(index==2)
	 {
		 deleteOneSOP();
	 }
	 if(index==3)
	 {
		 deleteOneGlpSynthesis();
	 }
	 if(index==4)
	 {
		 deleteOneInstrument();
	 }
	 if(index==5)
	 {
		 deleteOneEmployee();
	 }
	 if(index==6)
	 {
		 deleteOneAdministration();
	 }
	 if(index==7)
	 {
		 deleteOneContract();
	 }
	 if(index==8)
	 {
		 deleteOneSmplReserve();
	 }
	 if(index==9)
	 {
		 deleteOneSpecimen();
	 }
	 if(index==10)
	 {
		 deleteOneGlpSynthesis2();
	 }
	 if(index==11)
	 {
		$.messager.alert('提示框','操作日志不可以删除！');
	 }
}

function newAppend()
{
	var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
		 if(index==2)
		{
			var sopFrame = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow
				
			 sopflag = sopFrame.$('input[name="sopflag"]:checked').val();
			 $('#sopflag').val(sopflag);
		}
	 }else{
		 index = 0;
	 }
	 if(index==0)
	 {
		 newOneStudy(3);
	 }
	 if(index==1)
	 {
		newOneQA(3);
	 }
	 
	 if(index==2)
	 {
		 newOneSOP(3);
	 }
	 if(index==3)
	 {
		 newOneGlpSynthesis(3);
	 }
	 if(index==4)
	 {
		 newOneInstrument(3);
	 }
	 if(index==5)
	 {
		 newOneEmployee(3);
	 }
	 if(index==6)
	 {
		 newOneAdministration(3);
	 }
	 if(index==7)
	 {
		 newOneContract(3);
	 }
	 if(index==8)
	 {
		 newOneSmplReserve(3);
	 }
	 if(index==9)
	 {
		 newOneSpecimen(3);
	 }
	 if(index==10)
	 {
		 newOneGlpSynthesis2(3);
	 }
	 if(index==11)
	 {
		$.messager.alert('提示框','操作日志不可以追加！');
	 }
}

function selectOneOperate(operate)
{
	/*
	<option value="0">位置变更</option>
	<option value="3">档案销毁</option>
	<option value="1">登记SOP作废</option>
	<option value="4">合同终止</option>
	<option value="2">登记员工离职</option>
	<option value="5">销毁供试品留样</option>
	<option value="6">销毁标本</option>
	*/
	//var operate = $('#otherOperation').combobox('getValue');
	var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
	 }else{
		 index = 0;
	 }
	// $('#otherOperation').combobox('unselect',operate);
	if(operate==0){//位置变更

		changeOnePosition();
		
	}else if(operate==1){
		
		if(index==2){
			//alert("sop作废");
			
			var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
		
			var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条SOP记录");
			}else if(row.isInvalid==true){
				$.messager.alert("提示框","该SOP已经作废");
			}else{

				document.getElementById("otherOperateDialog2").style.display="block";
				$('#otherOperateLabel').html('作废是对该SOP编号为:'+row.sopcode+' 的所有版本的，确定要作废该SOP吗?');
				$('#otherOperateReason').val('');
				$('#otherOperateDate').datebox('setValue','');
				$('#otherOperateType').val(1);
				$('#otherOperateDialog').dialog('setTitle','SOP作废');
				$('#otherOperateDialog').dialog('open'); 
				
			}
			
		}else{
			$.messager.alert('提示框','登记SOP作废操作必须是对SOP文件！');
		}
	}
	if(operate==2){
		
		if(index==5){
			var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.staffState==2){//离职
				$.messager.alert("提示框","该员工已经离职，不可再次离职");
			}else{
				
				document.getElementById("otherOperateDialog2").style.display="block";
				$('#otherOperateLabel').html('确定员工编号为:'+row.staffCode+' 的员工要离职吗?');
				$('#otherOperateReason').val('');
				$('#otherOperateDate').datebox('setValue','');
				$('#otherOperateType').val(2);
				$('#otherOperateDialog').dialog('setTitle','员工离职');
				$('#otherOperateDialog').dialog('open');
				
			}
		}else{
			$.messager.alert('提示框','员工离职必须是对人员档案资料！');
		}
	}
	if(operate==3){
		//档案销毁
		if(index==0)
		{
			destroyOneStudy();
		}
		if(index==1)
		{
		 	destroyOneQA();
		}
			 
		if(index==2)
		{
		 	destroyOneSOP();
		}
		if(index==3)
		{
		 	destroyOneGlpSynthesis();
		}
	    if(index==4)
		{
			destroyOneInstrument();
		}
		if(index==5)
		{
			destroyOneEmployee();
		}
		if(index==6)
		{
			destroyOneAdministration();
		}
		if(index==7)
		{
			destroyOneContract();
		}
		if(index==8)
		{
			destroyOneSmplReserve();
		}
		if(index==9)
		{
			destroyOneSpecimen();
		}
		if(index==10)
		 {
			destroyOneGlpSynthesis2();
		 }
		if(index==11)
		 {
			$.messager.alert('提示框','操作日志不可以销毁！');
		 }
	}
	if(operate==4){//合同终止
		
		if(index==7){
			var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.isTerminal==true){
				$.messager.alert("提示框","该合同已经终止，不可修改");
			}else {
				
				document.getElementById("otherOperateDialog2").style.display="block";
				$('#otherOperateLabel').html('确定要终止合同编号为:'+row.contractCode+' 的合同吗?');
				$('#otherOperateReason').val('');
				$('#otherOperateDate').datebox('setValue','');
				$('#otherOperateType').val(4);
				$('#otherOperateDialog').dialog('setTitle','合同终止');
				$('#otherOperateDialog').dialog('open');
				
			}
		}else{
			$.messager.alert('提示框','合同终止必须是对合同档案资料！');
		}
	}
	if(operate==5){//销毁留样
		if(index==8){
			destroyOneSmplReserveContent();
		}else{
			$.messager.alert('提示框','销毁留样必须是对供试品留样档案！');
		}
	}
	if(operate==6){//销毁标本
		if(index==9){
			destroyOneSpecimenContent();
		}else{
			$.messager.alert('提示框','销毁标本必须是标本档案！');
		}
	}

	
	
	
}
//位置变更
function changeOnePosition()
{
	var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	var index = 0;
	if(contentWind.$)
	{
		var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
	}
	var childWind = '';
	var row = '';
	if(index==0)
	{
		childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;	
		row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
	}
	if(index==1)
	{
		childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;	
		row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
	}
		 
	if(index==2)
	{
		childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;	
		row = childWind.$('#tblFileContentSOPDatagrid').datagrid('getSelected');
	}
	if(index==3)
	{
		childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;	
		row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
	}
    if(index==4)
	{
    	childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;	
		row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');;
	}
	if(index==5)
	{
		childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;	
		row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
	}
	if(index==6)
	{
		childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;	
		row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
	}
	if(index==7)
	{
		childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;	
		row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
	}
	if(index==8)
	{
		childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;	
		row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
	}
	if(index==9)
	{
		childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;	
		row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
	}
	if(index==10)
	{
		childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;	
		row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
	}
	if(index==11)
	{
		$.messager.alert('提示框','位置变更不适用操作日志！');
	}

	if(row==null||row==''){
		$.messager.alert('提示框','请选择一行记录！');
	}else if(row.destoryDate!=null&&row.destoryDate!=''){
		$.messager.alert("提示框","该档案已经被销毁,不可修改！");
	}else{
		
		if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert('提示框','该档案已经销毁！');
		}else{
			$.messager.alert('提示框','位置变更是对整个档案的！','info',function(){

				$('#newPosition').val(row.archiveCode);

				document.getElementById("changeArchivePostionDialog2").style.display="block";
				$('#changePostionLabel').html('档案编号：'+row.archiveCode+' 原位置在：'+row.storePosition+'');
				
				$('#changeArchivePostionDialog').dialog('open'); 			
						
			});
			
		}
		
	}
	
	 
}
function saveChangePosition()
{
	var r = $('#changePosition').combotree('getText');
	if(r!=null&&r!='')
	{
		$('#changeArchivePostionDialog').dialog('close'); 	
		
		$('#newPosition').val($('#newPosition').val()+"~~"+r);
		qm_showQianmingDialog('afterSignChangePosition');
	}
	
}

//位置变更
function afterSignChangePosition()
{
	var newPos = $('#newPosition').val();
	var codeAndPos = newPos.split("~~");
	$.ajax({
 	  	url : sybp()+'/tblFileIndexAction_changePos.action',
	  	type: 'post',
	  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	  	data: {
		  	archiveCode:codeAndPos[0],
		  	storePosition:codeAndPos[1]
		 },
	  	dataType:'json',
	  	success:function(r){
	  	      if(r.success){
		  	      
	  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	  	    	  contentWind.$('#searchRecordButton').click();
	  	    	
	  	    	  
	  	        }else if(!r.success){
	  	            $.messager.alert('提示',r.msg,'info');
	  	        }else{
	  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	  	        }
	  	}
	  });  
	
}

function saveOtherOperate()
{
	if($('#otherOperateReason').val()!=''&&$('#otherOperateDate').datebox('getValue')!='')
	{
		$('#otherOperateDialog').dialog('close');
		var type = $('#otherOperateType').val();
		/*
		<option value="0">位置变更</option>
		<option value="3">档案销毁</option>
		<option value="1">登记SOP作废</option>
		<option value="4">合同终止</option>
		<option value="2">登记员工离职</option>
		<option value="5">销毁供试品留样</option>
		<option value="6">销毁标本</option>
		*/
		if(type==1){
	   	 	qm_showQianmingDialog('afterSignSOPInvalidRecord');
		}else if(type==2){
	   	 	qm_showQianmingDialog('afterSignEmployeeLeave');
		}else if(type==3){
			//3，5，6都在JS上的
	   	 	//qm_showQianmingDialog('afterSignEmployeeLeave');
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var index = 0;
			if(contentWind.$)
			{
				var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
				index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
			}
			var childWind = '';
			var row = '';
			if(index==0)
			{
				//$('#operateRsn').val($('#otherOperateReason').val());
				qm_showQianmingDialog('afterSignDestroyStudyRecord');
			}
			if(index==1)
			{
				//$('#operateQARsn').val($('#otherOperateReason').val());
				qm_showQianmingDialog('afterSignDestroyQARecord');
			}
			if(index==2)
			{
				//$('#operateSOPRsn').val($('#otherOperateReason').val());
				qm_showQianmingDialog('afterSignDestroySOPRecord');
			}
			if(index==3)
			{
				qm_showQianmingDialog('afterSignDestroyGlpSynthesisRecord');
			}
			if(index==4)
			{
				qm_showQianmingDialog('afterSignDestroyInstrumentRecord');
			}
			if(index==5)
			{
				qm_showQianmingDialog('afterSignDestroyEmployeeRecord');
			}
			if(index==6)
			{
				qm_showQianmingDialog('afterSignDestroyAdministrationRecord');
			}
			if(index==7)
			{
				qm_showQianmingDialog('afterSignDestroyContractRecord');
			}
			if(index==8)
			{
				$('#operateSmplReserveRsn').val($('#otherOperateReason').val());
				qm_showQianmingDialog('afterSignDestroySmplReserveRecord');
			}
			if(index==9)
			{
				$('#operateSpecimenRsn').val($('#otherOperateReason').val());
				qm_showQianmingDialog('afterSignDestroySpecimenRecord');
			}
			if(index==10)
			{
				qm_showQianmingDialog('afterSignDestroyGlpSynthesis2Record');
			}
	   	 	
		}else if(type==4){
			qm_showQianmingDialog('afterSignTerminateDate');
		}else if(type==5){
	   	 	//qm_showQianmingDialog('afterSignEmployeeLeave');
			$('#operateSmplReserveRsn').val($('#otherOperateReason').val());
			qm_showQianmingDialog('afterSignDestroySmplReserveRecord');
			
		}else if(type==6){
	   	 	//qm_showQianmingDialog('afterSignEmployeeLeave');
			$('#operateSpecimenRsn').val($('#otherOperateReason').val());
			qm_showQianmingDialog('afterSignDestroySpecimenRecord');
		}
	}else{
		$.messager.alert('提示框','请输入时间和原因！');
	}
}

//SOP作废
function afterSignSOPInvalidRecord()
{
	var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
	
	var row = childWind.$('#tblFileContentSOPDatagrid').datagrid('getSelected');
	
		$.ajax({
	 	  	url : sybp()+'/tblFileContentSOPAction_invalidSOP.action?fileRecordId='+row.fileRecordId,
		  	type: 'post',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		  //	data: $('#oneTblFileContentSOPForm').serialize(),
		  	dataType:'json',
		  	success:function(r){
		  	      if(r.success){
   		  	      
		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		  	    	  contentWind.$('#searchRecordButton').click();
		  	    	
		  	    	  
		  	        }else if(!r.success){
		  	            $.messager.alert('提示',r.msg,'info');
		  	        }else{
		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		  	        }
		  	}
		  });  

	
}

//员工离职
function afterSignEmployeeLeave()
{
	var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
	
	var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
	
		$.ajax({
	 	  	url : sybp()+'/tblFileContentEmployeeAction_dismission.action?fileRecordId='+row.fileRecordId,
		  	type: 'post',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		  //	data: $('#oneTblFileContentSOPForm').serialize(),
		  	dataType:'json',
		  	success:function(r){
		  	      if(r.success){
   		  	      
		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		  	    	  contentWind.$('#searchRecordButton').click();
		  	    	
		  	    	  
		  	        }else if(!r.success){
		  	            $.messager.alert('提示',r.msg,'info');
		  	        }else{
		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		  	        }
		  	}
		  });  
	
}
//合同终止
function afterSignTerminateDate()
{
	var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
	
	var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
	
		$.ajax({
	 	  	url : sybp()+'/tblFileContentContractAction_terminalContract.action?fileRecordId='+row.fileRecordId,
		  	type: 'post',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		  //	data: $('#oneTblFileContentSOPForm').serialize(),
		  	dataType:'json',
		  	success:function(r){
		  	      if(r.success){
   		  	      
		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		  	    	  contentWind.$('#searchRecordButton').click();
		  	    	
		  	    	  
		  	        }else if(!r.success){
		  	            $.messager.alert('提示',r.msg,'info');
		  	        }else{
		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		  	        }
		  	}
		  });  
	
}

function chooseArchiveType()
{
	/* 显示Dialog */
	document.getElementById("chooseArchiveTypeDialog2").style.display="block";
	var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
	 }else{
		 index = 0;
	 }
	////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本;11基建
	$('#dictArchiveTypeTable').datagrid({
		url : sybp()+'/tblFileIndexAction_getArchiveTypes.action?archiveTypeFlag='+(index+1),
	});
	$('#dictArchiveTypeTable').datagrid('reload');
	$('#chooseArchiveTypeDialog').dialog('open'); 
}
function chooseOneArchiveType()
{
	var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
	 }else{
		 index = 0;
	 }
	 if(index==0)
		 index='';
	 
	var row = $('#dictArchiveTypeTable').datagrid('getSelected');
	if(row!=null&&row!='')
	{
		$('#oneArchiveTypeCode'+index).val(row.archiveTypeCode);
		$('#oneArchiveTypeName'+index).val(row.archiveTypeName);
		$.ajax({
 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+row.archiveTypeCode,
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	//data: $('#oneTblFileContentStudyForm').serialize(),
 		  	dataType:'json',
 		  	success:function(r){
 		  	      if(r&&r.archiveCode){
 		  	    	  $('#oneArchiveCode'+index).val(r.archiveCode);
 		  	    	  
 		  	      }
 		  	}
 		  });   
		$('#chooseArchiveTypeDialog').dialog('close'); 
	}else{
		$.messager.alert('提示框','请选择一个档案分类');
	}
}

function hasOtherInThisPlace(storePosition,change)
{
	var index = 0;
	 var contentWind = document.getElementById('archiveMainFrame').contentWindow;
	 if(contentWind.$)
	 {
		 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
	 }else{
		 index = 0;
	 }
	 if(index==0)
		 index='';
	var archiveCode = $('#oneArchiveCode8'+index).val();
	//var storePosition = $('#oneStorePosition'+index).combotree('getText');
	$.ajax({
	 	  	url : sybp()+'/tblFileIndexAction_hasOtherInThisPlace.action',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		  	data: {
		  		storePosition:storePosition,
		  		archiveCode:archiveCode,
			},
		  	dataType:'json',
		  	success:function(r){
		  	      if(r&&r.has){
		  	    	// $.messager.alert('提示框','档案 '+r.codes+'在该位置上'); 
		  	    	 if(change=='change'){
						$('#changePostionHasOtherTip').html('档案 '+r.codes+'在该位置上');
				  	 }else if(index==0){
						$('#positionTipCode').html('档案 '+r.codes+'在该位置上');
			  	     }else{
			  	    	$('#positionTipCode'+index).html('档案 '+r.codes+'在该位置上');
				  	 }
		  	      }else{
		  	    	 if(change=='change'){
						$('#changePostionHasOtherTip').html('');
				  	 }else if(index==0){
						$('#positionTipCode').html('');
				  	 }else{
				  	    $('#positionTipCode'+index).html('');
					 }
			  	  }
		  	}
	}); 
	
}
function changOtherButton(index)
{
	$('#otherOperation0').css('display','none');
	$('#otherOperation3').css('display','none');
	$('#otherOperation1').css('display','none');
	$('#otherOperation4').css('display','none');
	$('#otherOperation2').css('display','none');
	$('#otherOperation5').css('display','none');
	$('#otherOperation6').css('display','none');
	
	if(index==0){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
	}else if(index==1){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
	}else if(index==2){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
		$('#otherOperation1').css('display','');
	}else if(index==3){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
	}else if(index==4){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
	}else if(index==6){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
	}else if(index==7){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
		$('#otherOperation4').css('display','');
	}else if(index==5){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
		$('#otherOperation2').css('display','');
	}else if(index==8){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
		$('#otherOperation5').css('display','');
	}else if(index==9){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
		$('#otherOperation6').css('display','');
	}else if(index==10){
		$('#otherOperation0').css('display','');
		$('#otherOperation3').css('display','');
	}
}

function showExistFileDialog(){
	
	document.getElementById("existArchiveFileDialog2").style.display="block";
	$('#existArchiveFileDialog').dialog('open');
		
}

      
</script>
	  
</head>

<body >
	
	<div  class="container" style="overflow:hidden;">
		<s:hidden id="studyNoParam" name="studyNoParam"></s:hidden>
		<s:hidden id="newPosition"></s:hidden>
		<s:hidden id="destroyType"></s:hidden>
		<s:hidden id="todayDate" name="todayDate"></s:hidden>
	    <s:hidden id="privilegeType" name="privilegeType" ></s:hidden>
		
			<div class="header_box" id="headerbox" style="display:'';">
	    		<input id="userName" type="hidden" value="${user.userName}"></input>
	        <!-- top 开始 -->
	           <%@ include file="/WEB-INF/jsp/homeAction/top.jspf"%>
	        <!-- top 结束 -->
	        </div>
      		
        	<div style="display:block; position:absolute;top:64px;left:0px;right:0px; bottom:0px;width:100%;background:#fff;padding:0px 0px 0px 0px;overflow:auto;" >
	 	 	  <!-- 工具栏 开始-->
				<div id="tabsToolbar" style="position:absolute;left:280px;top:0px;right:0px;height:30px;border: 0px solid #c8c8c8;">
					<a id="new" class="easyui-linkbutton" onclick="newOne();"  data-options="iconCls:'icon-add',plain:true">新建</a>
					<a id="edit" class="easyui-linkbutton" onclick="editOne();"  data-options="iconCls:'icon-edit',plain:true">编辑</a>
					<a id="delete" class="easyui-linkbutton" onclick="deleteOne();"  data-options="iconCls:'icon-remove',plain:true">删除</a>
					<a id="newAppend" class="easyui-linkbutton" onclick="newAppend();"  data-options="iconCls:'icon-add',plain:true">追加归档</a>
					<div style="position:absolute;top:0px;right:10px;">
						<!-- 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-attach',plain:true" onclick="showExistFileDialog();">导入Excel格式的档案</a>
						 -->
        
						
						<a id="otherOperation0" class="easyui-linkbutton" onclick="selectOneOperate(0)"  data-options="iconCls:'icon-tableedit',plain:true" >位置变更</a>
						<a id="otherOperation3" class="easyui-linkbutton" onclick="selectOneOperate(3)"  data-options="iconCls:'icon-remove',plain:true" >档案销毁</a>
						<a id="otherOperation1" class="easyui-linkbutton" onclick="selectOneOperate(1)"  data-options="iconCls:'icon-remove',plain:true" style='display:none;'>登记SOP作废</a>
						<a id="otherOperation4" class="easyui-linkbutton" onclick="selectOneOperate(4)"  data-options="iconCls:'icon-edit',plain:true" style='display:none;'>合同终止</a>
						<a id="otherOperation2" class="easyui-linkbutton" onclick="selectOneOperate(2)"  data-options="iconCls:'icon-edit',plain:true" style='display:none;'>登记员工离职</a>
						<a id="otherOperation5" class="easyui-linkbutton" onclick="selectOneOperate(5)"  data-options="iconCls:'icon-remove',plain:true" style='display:none;'>销毁供试品留样</a>
						<a id="otherOperation6" class="easyui-linkbutton" onclick="selectOneOperate(6)"  data-options="iconCls:'icon-remove',plain:true" style='display:none;'>销毁标本</a>
					<!-- 
					其他操作 <select id="otherOperation" class="easyui-combobox" data-options="editable:false" >
						<option value="-1"></option>
						<option value="0">位置变更</option>
			  		 	<option value="3">档案销毁</option>
			  		 	<option value="1">登记SOP作废</option>
			  		 	<option value="4">合同终止</option>
			  		 	<option value="2">登记员工离职</option>
			  		 	<option value="5">销毁供试品留样</option>
			  		 	<option value="6">销毁标本</option>
			  		 	
					</select>
					 -->
					</div>
							
					
				</div>
			<!-- 工具栏 结束-->
		       <div id="archiveLeft" style="position:absolute;top:0px;left:5px;bottom:0px;width:270px;border: 1px solid #c8c8c8;">
		        	<iframe style="overflow: auto" id="archiveLeftFrame" name="archiveLeft" 
		        	 src="${pageContext.request.contextPath}/homeAction_left.action" width=100% height=100% frameborder=0 border=0 marginwidth=0 marginheight=0 scrolling=no allowtransparency=yes></iframe>
		        </div>
		         
		        <div id="archiveMain" style="position:absolute;top:30px;left:277px;right:0px; bottom:0px;" style="border: 1px solid #c8c8c8;">
		        	<iframe style="overflow: auto" id="archiveMainFrame" name="archiveMain" 
		        	src="${pageContext.request.contextPath}/homeAction_main.action" width=100% height=100% frameborder=no border=0 scrolling=no></iframe>
		        </div>
	
		   </div>
		 
       
    
	   	<%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
	   	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	   
	 	<!-- 各个tabs页面的子页面 -->
	    <%@include file="/WEB-INF/jsp/tblFileContentStudyAction/addOrEditFileContentStudy.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentQACheckAction/addOrEditFileContentQA.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentSOPAction/addOrEditFileContentSOP.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentSOPAction/chooseNotArchiveSOP.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentGlpSynthesisAction/addOrEditFileContentGlpSynthesis.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentGlpSynthesis2Action/addOrEditFileContentGlpSynthesis2.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentInstrumentAction/addOrEditFileContentInstrument.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentEmployeeAction/addOrEditFileContentEmployee.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentAdministrationAction/addOrEditFileContentAdministration.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileContentContractAction/addOrEditFileContentContract.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileRecordSmplReserveAction/addOrEditFileRecordSmplReserve.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileRecordSmplReserveAction/chooseSmplReserve.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileRecordSpecimenAction/addOrEditFileRecordSpecimen.jspf" %>
		<%@include file="/WEB-INF/jsp/tblFileRecordSpecimenAction/searchSpecimenCode.jspf" %>
		
		<%@include file="/WEB-INF/jsp/homeAction/chooseArchiveType.jspf" %>
		
		<%@include file="/WEB-INF/jsp/homeAction/changeArchivePosition.jspf" %>
		<%@include file="/WEB-INF/jsp/homeAction/otherOperate.jspf" %>
		
		<%@include file="/WEB-INF/jsp/importedSpecimenAction/importExistArchive.jspf" %>
	
    </div>

</body>
</html>




