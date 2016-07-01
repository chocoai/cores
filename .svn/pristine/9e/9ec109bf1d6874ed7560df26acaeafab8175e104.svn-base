<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>
<style type="text/css">
	.tree-icon {
		width:0px;
	}
</style>
<script type="text/javascript">
	var tblSOLeaderTable;
	var tableSize ;
	$(function(){
	   
		var  startDate = $('#startime').val();
	    var  endDate = $('#endtime').val();
	   
		showSOLeaderTable(startDate,endDate);
		 $('#startDate').datebox('setValue',startDate);
		 $('#endDate').datebox('setValue',endDate);
		$('#toolbar').css('display','');
		
		
	});
	
	 function selectTimeButton(){
       $('#selectTimeDialog').dialog('open');
	   $('#selectTimeDialog2').dialog('open');
     }
    
     function saveSelectTime(){
        var  startDate = $('#startDate').datebox('getValue');
	    var  endDate = $('#endDate').datebox('getValue');
	    if(dateCompare(startDate,endDate)){
	         showSOLeaderTable(startDate,endDate)
	    }else{
	         $.messager.alert('警告','结束日期不能大于开始日期！');
	    }
     }
    
	
	function showSOLeaderTable(startDate,endDate){
	var tableHeight;//当前页面可见区域高度 - 30
	tableHeight = document.body.clientHeight - 30;
	  var solid = $('#solid').val();
	  tblSOLeaderTable = $('#tblSOLeaderTable').treegrid({    
		    url:sybp()+'/tblSOLeaderAction_loadList.action?startime='+startDate+'&endtime='+endDate+'&solid='+solid,    
		    idField:'id',    
		    treeField:'studyNo', 
		    height:tableHeight,
		    animate:false,   
		    singleSelect:true, //不支持多选
		    columns:[[  
                {title:'id',field:'id',width:180,hidden:true},
		        {field:'studyNo',title:'专题编号',width:180},
		        {field:'taskName',title:'任务名称',width:180},
		        {field:'startDate',title:'日程开始日期',width:180},
		        {field:'soleader',title:'操作者',width:600},
		        {field:'_parentId',title:'_parentId',width:180,hidden:true},   
		        {field:'privilege',title:'privilege',width:180,hidden:true}
		    ]],
		    //工具栏
			toolbar:'#toolbar',  
			onClickRow :function(row){
			    
	     	},
	     	onSelect:function(row){
	     	   if(row._parentId == ""){
	     	        var privilege = $('#privilege').val()
	     	        if(privilege != ""){
		     	        initPrivilege();
				       $('#onSetUpTaskLeaderButton').linkbutton('enable');
				       $('#editTaskLeaderButton').linkbutton('disable');
	     	        }else{
	     	           $('#onSetUpTaskLeaderButton').linkbutton('disable');
	     	        }
			       
			    }else{
			       initPrivilegeNone();
			       $('#onSetUpTaskLeaderButton').linkbutton('disable');
			       if(row.privilege == "1"){
			            $('#editTaskLeaderButton').linkbutton('enable');
				    }else{
				        $('#editTaskLeaderButton').linkbutton('disable');
				    }
			    }
	     	},
	     	onLoadSuccess:function(row, data){
	     	     $('#tblSOLeaderTable').treegrid('collapseAll');
		     	if(data && data.solid){
		     	     var row = $('#tblSOLeaderTable').treegrid('getParent',data.solid);
		     	     $('#tblSOLeaderTable').treegrid('select',data.solid);
		     	     if(row== ""){
		     	       $('#onSetUpTaskLeaderButton').linkbutton('enable');
		     	       $('#editTaskLeaderButton').linkbutton('disable');
		     	     }else{
		     	       $('#tblSOLeaderTable').treegrid('expandTo',data.solid);
	     			   $('#editTaskLeaderButton').linkbutton('enable');
		     	     }
				}else{
				   $('#onSetUpTaskLeaderButton').linkbutton('disable');
				}
		    }
		}); 
	}
	
	//根据权限显示
	function initPrivilege(){
	  var privilege = $('#privilege').val();
	  var privileges = privilege.split(","); //字符分割 
	  for(var i = 0; i < privileges.length ; i++){
	      $('#menubutton'+privileges[i]).css('display','');
	  }
	}
	
    //根据权限隐藏显示
	function initPrivilegeNone(){
	  var privilege = $('#privilege').val();
	  var privileges = privilege.split(","); //字符分割 
	  for(var i = 0; i < privileges.length ; i++){
	    
	      $('#menubutton'+privileges[i]).css('display','none');
	  }
	}
	function onSignSOLLeaderButton(){
		qm_showQianmingDialog('afterSignSOLLeader');
	}

	function afterSignSOLLeader(){
		var rows = $('#tblSOLeaderTable').treegrid('getSelections');
        var ary = new Array();
		for(var i = 0 ;i< rows.length;i++){
			var Ids = rows[i].id;
			ary = ary.concat(Ids);
	    }
	    var allIds = ary.join(",");
        if(rows.length == 1 && rows[0]._parentId == ""){
        	var studyNo = rows[0].studyNo;
        	$.ajax({
        		  	url : sybp()+'/tblSOLeaderAction_signSOLeader.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		  		studyNo:studyNo,
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  			$('#tblSOLeaderTable').treegrid('reload');
        		  			parent.showMessager(1,'签字成功',true,5000);
        		  	}
        		  });
        }else{
        	$.ajax({
    		  	url : sybp()+'/tblTOLeaderAction_signTOLeader.action',
    		  	type: 'post',
    		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    		  	data: {
    		  		scheduleId:allIds,
    		  	},
    		  	dataType:'json',
    		  	success:function(r){
    		  			$('#tblSOLeaderTable').treegrid('reload');
    		  			parent.showMessager(1,'签字成功',true,5000);
    		  	}
    		  });
        }
	}
	
	function oneditTaskLeaderButton(){
	    var rows = $('#tblSOLeaderTable').treegrid('getSelections');
		 //跳转到课题负责人
		 if(rows.length == 1 && rows[0]._parentId == ""){
			// window.location.href=sybp()+'/tblSOLeaderAction_editUI.action?studyNo='+ rows[0].studyNo+'&id='+rows[0].id;
	    }else{
	    	var rows = $('#tblSOLeaderTable').treegrid('getSelections');
	        var ary = new Array();
			for(var i = 0 ;i< rows.length;i++){
				var userId = rows[i].id;
				ary = ary.concat(userId);
		    }
		    var scheduleIds = ary.join(",");
			//跳转到任务分配	
	    	 window.location.href=sybp()+'/tblTOLeaderAction_editUI.action?scheduleIds='+ scheduleIds+'&studyNo='+ rows[0]._parentId;
	    }
	}
	//设置专题任务总负责人
	function onSetUpTaskLeaderButton(taskKind){
	    var rows = $('#tblSOLeaderTable').treegrid('getSelections');
	    window.location.href=sybp()+'/tblSOLeaderAction_editUI.action?studyNo='+ rows[0].studyNo+'&id='+rows[0].id+'&taskKind='+taskKind;
	}
	//批量设置 2014-12-03
	function batchSetButton(){
	    //跳转到批量设置
	   var  startDate = $('#startDate').datebox('getValue');
	   var  endDate = $('#endDate').datebox('getValue');
	   window.location.href=sybp()+'/tblSOLeaderAction_toBatchSet.action?startDate='+startDate+"&endDate="+endDate;
	}
	
</script>
</head>
<body>
<s:hidden id="solid" name="solid"></s:hidden>
<s:hidden id="startime" name="startime"></s:hidden>
<s:hidden id="endtime" name="endtime"></s:hidden>
<s:hidden id="privilege" name="privilege"></s:hidden>
	<div class="easyui-layout" fit="true" border="false" >
		<div  region="center" title="" style="width:99.8%;">   
 	 	<div  class="easyui-tabs" fit="true" border="false" >
			<div title="专题任务分配" id="tabsDict1" border="false" style="overflow: hidden;">
			<table id="tblSOLeaderTable" ></table>
		</div>
	   </div>
	  </div> 
	</div>
	<div id="toolbar" style="display:none">
			  &nbsp;&nbsp;&nbsp;&nbsp; <a >日期范围 &nbsp;&nbsp; &nbsp;&nbsp;<input  style="width:100px;" id="startDate" type="text" name="startDate"  class="easyui-datebox"  data-options="editable:false" /></a>
			 <a>&nbsp;&nbsp;&nbsp;&nbsp;～&nbsp;&nbsp;&nbsp;&nbsp;<input  style="width:100px;" id="endDate" type="text"  name="endDate"  class="easyui-datebox"  data-options="editable:false" /></a>
			 <a id="saveDialogButton" class="easyui-linkbutton" onclick="saveSelectTime();" data-options="iconCls:'icon-search',plain:true">查询</a>
		     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <a id="editTaskLeaderButton" class="easyui-linkbutton" onclick="oneditTaskLeaderButton();" data-options="iconCls:'icon-user',disabled:true,plain:true">设置专题任务操作者</a> 
			 <!-- <a id="" class="easyui-linkbutton" onclick="onSetUpTaskLeaderButton();" data-options="iconCls:'icon-usersuit',plain:true">设置专题任务负责人</a>-->
			 <a id="onSetUpTaskLeaderButton" class="easyui-menubutton"   data-options="menu:'#onSetUpTaskLeaderButtonmm',iconCls:'icon-usersuit'">设置专题任务负责人</a>  
				<div id="onSetUpTaskLeaderButtonmm" style="width:150px;">   
				<!-- 任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理  7分析 8生态毒理 -->
				    <div id="menubutton1" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(1);">委托管理</div>   
				    <div id="menubutton2" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(2);">动物试验</div>   
				    <div id="menubutton3" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(3);">临床检验</div>   
				    <div id="menubutton4" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(4);">毒性病理</div>   
				    <div id="menubutton5" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(5);">QA管理</div>   
				    <div id="menubutton6" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(6);">供试品管理</div>   
				    <div id="menubutton7" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(7);">分析</div>   
				    <div id="menubutton8" style="display:none" data-options="iconCls:''" onclick="onSetUpTaskLeaderButton(8);">生态毒理</div>   
				</div>  
						 <a id="batchSetButton" class="easyui-linkbutton" onclick="batchSetButton();" data-options="iconCls:'icon-dateend',plain:true">批量设置专题任务操作者</a> 
			 
    </div>
	
	
	 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</body>
</html>