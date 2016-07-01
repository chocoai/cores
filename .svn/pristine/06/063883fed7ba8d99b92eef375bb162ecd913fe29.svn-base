<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/main.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/homeAction/main.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddNo.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyPlanAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/studyTypeSelect.js" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<script type="text/javascript">
	$(function(){
		//显示整个布局
		$('#layoutMainDiv').css('display','');
	});
	//菜单01点击事件
	/** 新建一个 临检申请tab(清除其他标签)*/
	function addNewLogTab(){
		var mainTabs=$('#mainTabs');
			if (mainTabs.tabs('exists', '系统日志')) {
				mainTabs.tabs('select', '系统日志');
			}else{
				var length = mainTabs.tabs('tabs').length;
				for(var i=0;i<length;i++){
					mainTabs.tabs('close',0);
				}
				mainTabs.tabs('add', {
					title : '系统日志',
					closable : false,
					content : '<iframe src="' + '${pageContext.request.contextPath}/tblLogAction_list.action?" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				});
			}
	}
</script>
</head>
 <body >
 	<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;">   
  	<div region="north" title="" style="height:30px;">
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu1',iconCls:'icon-edit'" onclick="">专题管理</a>  
	  		<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu2'" onclick="">动物信息</a>
	  		<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton"   
	       				 data-options="menu:'#menu3'" onclick="">系统</a>
	       				  
					<div id="menu1" data-options="minWidth:'80px'">   
					    <div data-options="iconCls:'icon-add'" style="width:110px;" onclick="addNewStudyPlanDialog();">新建专题</div>   
					    <div disabled="true" data-options="iconCls:''" style="width:110px;">临床检验管理</div>  
					    <div disabled="true" data-options="iconCls:''" style="width:110px;">胶囊配制申请</div>
					    <div disabled="true" data-options="iconCls:''" style="width:110px;" onclick="">解剖申请</div>
					    <!-- 专题成员 -->
				        <p style="border-top:1px solid #e3e6eb;height:0px;"></p>
				        <div disabled="true" data-options="" style="width:110px;">专题成员</div>
					</div> 
					<div id="menu2" data-options="minWidth:'100px'">   
					    <div disabled="true" data-options="iconCls:''" style="width:100px;">动物信息</div>   
					    <div disabled="true" data-options="iconCls:''" style="width:110px;">动物体重</div> 
					</div> 
					<div id="menu3" data-options="minWidth:'100px'">   
					    <div style="width:100px;" onclick="addNewLogTab();">系统日志</div>    
					</div> 
  		</div>
  	</div>
 	<div region="center" title="" style="overflow: hidden;">
 		<div id="mainTabs" class="easyui-tabs" fit="true" border="false">
 		</div>
 	<!-- 
 			<div title="欢迎" style="padding:20px;">   
		      		  欢迎
		    </div> 
 	 -->
	</div>
<%@include file="/WEB-INF/jsp/studyPlanAction/studyPlanAddNo.jspf" %>
<%@include file="/WEB-INF/jsp/studyPlanAction/studyPlanAddEdit.jspf" %>
<%@include file="/WEB-INF/jsp/studyPlanAction/studyTypeSelect.jspf" %>
 	</div>
</body>
</html>
