<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES SOP管理系统</title>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/archive/tblFileContentSOP.js" charset="utf-8"></script>
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
			width:240
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

	

	/*
	$('#changePosition').combotree({
		 valueField:'text',
		 textField:'text',
		 editable:true,
		 url:sybp()+'/tblFileIndexAction_loadArchivePositionTree.action',	
		 
	 });*/

	
	
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
	
	initButton();
	
});//匿名函数结束

function initButton(){
	var privilegeType = $('#privilegeType').val();
	
	if(privilegeType!=''&&privilegeType==0)
	{
		$('#new').linkbutton('disable');
		$('#edit').linkbutton('disable');
		$('#delete').linkbutton('disable');
		$('#newAppend').linkbutton('disable');
		$('#otherOperation1').linkbutton('disable');
	}else if(privilegeType==1){
		$('#new').linkbutton('enable');
		$('#edit').linkbutton('enable');
		$('#delete').linkbutton('enable');
		$('#newAppend').linkbutton('enable');
		$('#otherOperation1').linkbutton('enable');
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
		if(index==0)
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
		
		 newOneSOP(1);
	 }
	 
	 
	 if(index==1)
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
		 if(index==0)
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
		
		 newOneSOP(2);
	 }
	 
	 if(index==1)
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
		 deleteOneSOP();
	 }
	 
	 
	 if(index==1)
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
		 if(index==0)
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
		 newOneSOP(3);
	 }
	
	 if(index==1)
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
	if(operate==1){
		
		if(index==0){
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
	
	if(operate==3){
		//档案销毁
		if(index==0)
		{
			
		 	destroyOneSOP();
		}
		
		if(index==11)
		 {
			$.messager.alert('提示框','操作日志不可以销毁！');
		 }
	}
	
	

	
	
	
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
				qm_showQianmingDialog('afterSignDestroySOPRecord');
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
		  	    	 contentWind.searchRecord();
		  	    	
		  	    	  
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
	////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
	if(index==10)//基建，去查找综合资料
	{
		index=3;
	}
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
 	 	  	url : sybp()+'/tblFileIndexAction_getMaxArchiveCode.action?archiveTypeCode='+row.archiveTypeCode,
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

function hasOtherInThisPlace(storePosition)
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
		  	    	 if(index==0){
						$('#positionTipCode').html('档案 '+r.codes+'在该位置上');
			  	     }else{
			  	    	$('#positionTipCode'+index).html('档案 '+r.codes+'在该位置上');
				  	 }
		  	      }else{
		  	    	 if(index==0){
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
	//$('#otherOperation0').css('display','none');
	//$('#otherOperation3').css('display','none');
	$('#otherOperation1').css('display','none');
	
	
	if(index==0){
		//$('#otherOperation0').css('display','');
		//$('#otherOperation3').css('display','');
		$('#otherOperation1').css('display','');
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
		<s:hidden id="privilegeType" name="privilegeType"></s:hidden>
		
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
					<!-- 
					<a id="newAppend" class="easyui-linkbutton" onclick="newAppend();"  data-options="iconCls:'icon-add',plain:true">追加归档</a>
					 -->
					<div style="position:absolute;top:0px;right:10px;">
						<!-- 
						<a id="otherOperation0" class="easyui-linkbutton" onclick="selectOneOperate(0)"  data-options="iconCls:'icon-tableedit',plain:true" >位置变更</a>
						<a id="otherOperation3" class="easyui-linkbutton" onclick="selectOneOperate(3)"  data-options="iconCls:'icon-remove',plain:true" >档案销毁</a>
						 -->		
						<a id="otherOperation1" class="easyui-linkbutton" onclick="selectOneOperate(1)"  data-options="iconCls:'icon-remove',plain:true" style='display:none;'>登记SOP作废</a>
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
	   <%@include file="/WEB-INF/jsp/tblFileContentSOPAction/addOrEditFileContentSOP.jspf" %>
		
		<%@include file="/WEB-INF/jsp/homeAction/chooseArchiveType.jspf" %>
		
		<%@include file="/WEB-INF/jsp/homeAction/changeArchivePosition.jspf" %>
		<%@include file="/WEB-INF/jsp/homeAction/otherOperate.jspf" %>
		
	
    </div>

</body>
</html>




