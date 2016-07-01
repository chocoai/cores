<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblPathPlanAction/editPathPlanCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblPathPlanAction/editPathPlanVisceraWeigh.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblPathPlanAction/editPathPlanAttachedViscera.js" charset="utf-8"></script>
<style type="text/css">
	.tree-icon {
		width:0px;
	}
</style>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	var studyNoPara;//专题编号
	var studyState;//试验状态
	var animalType;//专题动物种类（名）
    $(function(){
    	tableHeight = document.body.clientHeight - 50;
		tableWidth  = document.body.clientWidth/2-100;
    	studyNoPara = $('#studyNoPara').val();
    	studyState = $('#studyState').val();
    	animalType=$('#animalType').val();
    	var member = $('#left_member').val();
		if(member!= ""){
		    $('#editPathPlanCheckButton').linkbutton('disable');
			$('#editPathPlanVisceraWeighButton').linkbutton('disable');
		}else{
			if(studyState ==1 || studyState ==2){
	    		$('#editPathPlanCheckButton').linkbutton('disable');
				$('#editPathPlanVisceraWeighButton').linkbutton('disable');
	    	}else{
	    	    $('#editPathPlanCheckButton').linkbutton('enable');
				$('#editPathPlanVisceraWeighButton').linkbutton('enable');
	    	}
		}
    	//初始化病理计划-脏器/组织学检查表
        $('#pathPlanCheckTable').datagrid({
        	url:sybp()+'/tblPathPlanCheckAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara),
        	title:'脏器剖检/固定/镜检计划',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:tableHeight,
			width:464,
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'',
				field:'sn',
				width:50,
				halign:'center',
				align:'center',
//				hidden:true,
				formatter: function(value,row,index){
				return index+1;
		    } 
			},{
				title:'脏器名称',
				field:'visceraName',
				width:154,
				halign:'center',
				align:'left'
			},{
				title:'是否需要剖检',
				field:'atanomyCheckFlag',
				width:79,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    } 
			},{
				title:'是否需要固定',
				field:'visceraFixedFlag',
				width:79,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    } 
			},{
				title:'是否需要镜检',
				field:'histopathCheckFlag',
				width:79,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    } 
			}
			]],
			toolbar:'#pathPlanChecktoolbar'
         });
        //初始化病理计划-脏器称重表
        $('#pathPlanVisceraWeighTable').datagrid({
        	url:sybp()+'/tblPathPlanVisceraWeighAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara),
        	title:'脏器称重计划',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:tableHeight,
			width:547,
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'',
				field:'sn',
				width:50,
				halign:'center',
				align:'center',
//				hidden:true,
				formatter: function(value,row,index){
				return index+1;
		    } 
			},{
				title:'脏器名称',
				field:'visceraName',
				width:160,
				halign:'center',
				align:'left'
			},{
				title:'成对脏器分开称重',
				field:'partVisceraSeparateWeigh',
				width:105,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    } 
			},{
				title:'固定后称重',
				field:'fixedWeighFlag',
				width:68,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else{
						return "";
					}
			    }
			},{
				title:'附加脏器',
				field:'attachedViscera',
				width:140,
				halign:'center',
				align:'left'
			} 
			]],
			toolbar:'#pathPlanVisceraWeightoolbar'
         });
        
    	$('#pathPlanDiv').css('display','');
//    	$('#toolbar').css('display',''); 
     });//匿名函数结束
	function editPathPlanCheck(){
		showEditPathPlanCheckDialog('','edit','编辑脏器剖检/固定/镜检计划');
     }
    function editPathPlanVisceraWeigh(){
    	showEditPathPlanVisceraWeighDialog('','edit','编辑脏器称重计划');
    }
</script>
</head>
<body  scroll="no">
    <!-- 病理计划-脏器/组织学检查Id -->
    <s:hidden id="pathPlanCheckId" name="pathPlanCheckId"></s:hidden>
    <!-- 病理计划-脏器称重Id -->
    <s:hidden id="pathPlanVisceraWeighId" name="pathPlanVisceraWeighId"></s:hidden>
    <!-- 病理计划-脏器称重-附加脏器Id -->
    <s:hidden id="pathPlanAttachedVisceraId" name="pathPlanAttachedVisceraId"></s:hidden>
    <!-- 课题编号 -->
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <!-- 专题计划状态 -->
    <s:hidden id="studyState" name="studyState"></s:hidden>
    <!-- 专题动物种类 -->
    <s:hidden id="animalType" name="animalType"></s:hidden>
    <!-- 课题成员 -->
    <s:hidden id="left_member" name="left_member"></s:hidden>
    <div id="pathPlanDiv"  style="width:100%;height:100%; display:none; overflow: hidden;">
	<div id="pathPlanChecktoolbar" style="display:none;">
		<a id="editPathPlanCheckButton"  class="easyui-linkbutton" onclick="editPathPlanCheck()" data-options="iconCls:'icon-edit',plain:true" style="margin-left:13px;">编辑</a>
	</div>
   <div id="pathPlanVisceraWeightoolbar" style="display:none;">
		<a id="editPathPlanVisceraWeighButton"  class="easyui-linkbutton" onclick="editPathPlanVisceraWeigh()" data-options="iconCls:'icon-edit',plain:true" style="margin-left:13px;">编辑</a>
	</div>
   
   <div class="easyui-layout" fit="true" border="false" style="overflow: hidden;" >

		<div region="west" border="false" style="width:479px;padding-left:5px ">
        	<table id="pathPlanCheckTable" ></table> 
		</div>
		
		<div region="center" border="false" style="width:750px;">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false" style="border-left:thin dashed #E0E0E0;padding-left:10px ">
				<div><table id="pathPlanVisceraWeighTable" ></table></div>
				</div>

			</div>
		</div>
		<%@include file="/WEB-INF/jsp/tblPathPlanAction/editPathPlanCheck.jspf" %>
        <%@include file="/WEB-INF/jsp/tblPathPlanAction/editPathPlanVisceraWeigh.jspf" %>
        <%@include file="/WEB-INF/jsp/tblPathPlanAction/editPathPlanAttachedViscera.jspf" %>
	</div>
	
   </div>
   
</body>
</html>
