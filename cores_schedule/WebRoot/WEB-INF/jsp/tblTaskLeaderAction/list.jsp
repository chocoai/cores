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
<script type="text/javascript">
	var tblTaskLeaderTable;
	var tableSize ;
	$(function(){
		tblTaskLeaderTable = $('#tblTaskLeaderTable').treegrid({    
		    url:sybp()+'/tblTaskLeaderAction_loadList.action?tlid='+$('#tlid').val(),    
		    idField:'id',  
		    height: 525,  
		    treeField:'taskKind', 
		    animate:false,   
		    singleSelect: true, //不支持多选
		    columns:[[  
                {title:'id',field:'id',width:180,hidden:true},
		        {title:'任务类别',field:'taskKind',width:180},      
		        {field:'taskLeader',title:'负责人',width:450}, 
		        {title:'任务名称',field:'taskTypeID',width:120,hidden:true},    
		        {field:'_parentId',title:'_parentId',width:20,hidden:true},
		        {field:'canSee',title:'可见范围',width:240}
		    ]],
		    //工具栏
			toolbar:'#toolbar',  
			onClickRow :function(row){
			    if(row._parentId != ""){
			        $('#addTaskLeaderButton').linkbutton('enable');
			    }else{
			        $('#addTaskLeaderButton').linkbutton('disable');
			    }
			  
	     	},
			onLoadSuccess:function(row, data){
		     	      if(data && data.tlid){
         	            $('#tblTaskLeaderTable').treegrid('expandTo',data.tlid);
	     			    $('#tblTaskLeaderTable').treegrid('select',data.tlid);
	     			    $('#addTaskLeaderButton').linkbutton('enable');
				      }
		    }
		}); 

		
		$('#easyuiIayout').css('display','');
		
	});
	//安排负责人
	function onaddTaskLeaderButton(){
		var node = $('#tblTaskLeaderTable').treegrid('getSelected');
		window.location.href=sybp()+'/tblTaskLeaderAction_addUI.action?id='+node.id;
	}
	//编辑
	function oneditTaskLeaderButton(){
		var node = $('#tblTaskLeaderTable').treegrid('getSelected');
		window.location.href=sybp()+'/tblTaskLeaderAction_editUI.action?id='+node.id;
	
	}

    function ondelTaskLeaderButton(){
	    qm_showQianmingDialog('afterDelTaskLeader');
    }

	function ondelTaskLeaderButton(){
		var rows = $('#tblTaskLeaderTable').treegrid('getSelections');

        var ary = new Array();
		for(var i = 0 ;i< rows.length;i++){
			var userId = rows[i].id;
			ary = ary.concat(userId);
	    }
	    var userIds = ary.join(",");
	    $.ajax({
	    	url : sybp()+'/tblTaskLeaderAction_delect.action',
			type: 'post',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data: {
				getUserIds:userIds,
			},
			dataType:'json',
			success:function(r){
				if(r){
					for(var i = 0;i<ary.length;i++){
						$('#tblTaskLeaderTable').treegrid('remove',ary[i]);
					}
				}
			}
		});
	}
	
	function onNovailButton(){
			var rowData = $('#tblTaskLeaderTable').treegrid('getSelected');
			if(null != rowData && (rowData.signId != null && rowData.signId !='')
					&& (rowData.endTime ==null || rowData.endTime == '' )){
			    $('#novaliId').val(rowData.id);
				$('#novaliLeader').html(rowData.taskLeader);
				var d = new Date();
				var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
				$('#novailEndDate').datebox('setValue',oneDayTime);
				$('#novaliDialog').dialog('open');
				$('#novaliDialog2').dialog('open');
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		//补0函数
		function appendZero(s){
			return ("00"+ s).substr((s+"").length);
		}  
		
		
	//在置为无效dialog上的确定按钮事件
		function onButton_novaliDialog(){
		  var endDate = $('#novailEndDate').datebox('getValue');
		  	var rowData = $('#tblTaskLeaderTable').treegrid('getSelected');
		  	var flag=dateCompare(rowData.startTime,endDate)
		  	if(flag){
			  	if( $('#novaliForm').form('validate') ){
					$('#novaliDialog').dialog('close');
					qm_showQianmingDialog('afterSign_novail');
				}
		  	}else{
		  	    $.messager.alert('警告','结束日期不能早于开始日期!');
		  	}
		}
		//置为无效，签字后事件
		function afterSign_novail(){
		var rowData = $('#tblTaskLeaderTable').treegrid('getSelected');
			$.ajax({
				url:sybp()+'/tblTaskLeaderAction_setupInvalid.action',
				type:'post',
				data:$('#novaliForm').serialize(),
				dataType:'json',
				success:function(r){
						if(r && r.success){
							//$('#tblTaskLeaderTable').treegrid('reload');
							//$('#tblTaskLeaderTable').treegrid('expandTo',rowData.id);
	     			        //$('#tblTaskLeaderTable').treegrid('select',rowData.id);
	     			        var pid = $('#tblTaskLeaderTable').treegrid('getParent',rowData.id);
	     			        window.location.href=sybp()+'/tblTaskLeaderAction_list.action?id='+pid.id;
							//按钮置为无效
							parent.showMessager(1,'设置成功',true,5000);
						}
					}
			});
		}
		
		//在置为无效dialog上的返回按钮事件
		function onBackButton_novaliDialog(){
			$('#novaliDialog').dialog('close');
		}
	
</script>
</head>
<body>
<s:hidden id="tlid" name="tlid"></s:hidden>
	<div class="easyui-layout" fit="true" border="false"   >
		<div  region="center" title="" style="width:99.8%;">   
 	 	<div  class="easyui-tabs" fit="true" border="false" >
			<div title="常规任务分配" id="tabsDict1" border="false" style="overflow: hidden;">
			 <table id="tblTaskLeaderTable" ></table>
			</div>
	   </div>
	  </div> 
	</div>
	 <div id = "easyuiIayout" style="display:none">
	<div id="toolbar">
			<a id="addTaskLeaderButton"  class="easyui-linkbutton" onclick="onaddTaskLeaderButton();" data-options="iconCls:'icon-user',disabled:true,plain:true">操作者设置</a>
    </div>
	 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	 <%@include file="/WEB-INF/jsp/tblTaskLeaderAction/novaliDialog.jspf" %>
	 </div>
</body>
</html>