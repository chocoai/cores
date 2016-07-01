<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var taskTypeActionTable;
	var tableSize ;
	$(function(){
	//taskIds  currentResId='+$('#currentResId').val()
		taskTypeActionTable= $('#taskTypeActionTable').treegrid({ 
			url : sybp()+'/tblTaskTypeAction_loadList.action?taskIds='+ $('#taskIds').val(),
			idField:'id',    
			height: 525,
		    treeField:'taskName', 
		    animate:false,   
		    singleSelect: false, //支持多选
		    idField:'id',
		    columns:[[  
		    //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理  7分析 8生态毒理
		                {title:'id',field:'id',width:180,hidden:true},
				        {title:'任务属性',field:'taskKind',width:180,hidden:true},      
				        {title:'任务名称',field:'taskName',width:180,
							formatter: function(value,row,index){
							if ( value == 1){
								return "委托管理";
							}else if(value == 2){
			                     return "动物试验";
							}else if(value == 3){
			                     return "临床检验";
							}else if(value == 4){
			                     return "毒性病理";
							}else if(value == 5){
			                     return "QA管理";
							}else if(value == 6){
			                     return "供试品管理";
							}else if(value == 7){
			                     return "分析";
							}else if(value == 8){
			                     return "生态毒理";
							}else{
								return value;
							}}
						 },   
						{title:'任务总负责人/可见范围',field:'canSee',width:400},      
				        {title:'有效性',field:'validFlag',width:180,hidden:true},
				        {field:'_parentId',title:'_parentId',width:180,hidden:true}
				    ]],
				  //工具栏
					toolbar:'#toolbar',
			    	onClickRow :function(row){
			         var validFlag = $(this).treegrid('getSelections');
			         for(var i = 0;i<validFlag.length;i++){
                       if(validFlag[i].validFlag == 1){
						   $(this).treegrid('unselect',validFlag[i].id);
                       }
				    }
			           if(row._parentId == ""){
			        	   $('#editTaskTypeButton').linkbutton('disable');
						   $('#delTaskTyperButton').linkbutton('disable');
						   $('#addTaskLeaderButton').linkbutton('enable');
						   
						   
						   $(this).treegrid('unselectAll');
						   $(this).treegrid('select',row.id);
					   }else{
						   var pid = $(this).treegrid('getParent',row.id).id;
                           $(this).treegrid('unselect',pid);
                           var rows = $(this).treegrid('getSelections');
                           for(var i = 0;i<rows.length;i++){
                            if(rows[i]._parentId == ""){
                            	  $(this).treegrid('unselect',rows[i].id);
                                }
                           }
                           $('#addTaskLeaderButton').linkbutton('disable');
						   $('#delTaskTyperButton').linkbutton('enable');
						   if(rows.length == 0){
							   $('#editTaskTypeButton').linkbutton('disable');
							   $('#delTaskTyperButton').linkbutton('disable');
							}else if(rows.length > 1  ){
							   $('#editTaskTypeButton').linkbutton('disable');
							   $('#delTaskTyperButton').linkbutton('enable');
							}else{
							   $('#editTaskTypeButton').linkbutton('enable');
							   $('#delTaskTyperButton').linkbutton('enable');
							}
						  
					   }
			       },
					rowStyler: function(row){
						 if (row.validFlag== 1 ){
							return 'background-color:#F2F2F2;color:#080808;';
						 }
					},	
					onLoadSuccess:function(row, data){
		     	      if(data && data.taskIds){
		     	        
		     	          $('#taskTypeActionTable').treegrid('select',data.taskIds);
		     	          if(data.taskIds == ('1' ||'2'||'3'||'4'||'5'||'6'||'7'||'8')){
		     	            $('#addTaskLeaderButton').linkbutton('enable');
		     	            $('#editTaskTypeButton').linkbutton('disable');
					        $('#delTaskTyperButton').linkbutton('disable');
		     	          }else{
		     	            $('#taskTypeActionTable').treegrid('expandTo',data.taskIds);
	     			        $('#editTaskTypeButton').linkbutton('enable');
					        $('#delTaskTyperButton').linkbutton('enable'); 
					        $('#addTaskLeaderButton').linkbutton('disable');
		     	          }
		     	          
		     	          
	     			      
					      
				      }
				      
		           }
		 });
	});
	//新增
	function onaddTaskTypeButton(){
		var row = $('#taskTypeActionTable').treegrid('getSelected');
		if(row == null ){
			window.location.href=sybp()+'/tblTaskTypeAction_addUI.action';
	    }else{
	        var id = row.id;
	        var prow = $('#taskTypeActionTable').treegrid('getParent',id);
	        if(prow== null || prow==""){
	           window.location.href=sybp()+'/tblTaskTypeAction_addUI.action?taskKind='+row.taskKind+'&id='+id;
	        }else{
	           window.location.href=sybp()+'/tblTaskTypeAction_addUI.action?taskKind='+prow.taskKind+'&id='+id;
	        }
		}
	}
	//编辑
	function oneditTaskTypeButton(){
		var rows = $('#taskTypeActionTable').treegrid('getSelections');
		if(rows.length == 1){
			window.location.href=sybp()+'/tblTaskTypeAction_editUI.action?id='+rows[0].id;
		}else if(rows.length == 0){
			parent.showMessager(2,'请选择编辑行',true,5000);
		}
	}
	//删除
	function ondelTaskTyperButton(){
		var rows = $('#taskTypeActionTable').treegrid('getSelections');

		  var ary = new Array();
		  var ary1 = new Array();
		  for(var i = 0 ;i< rows.length;i++){
				var taskId = rows[i].id;
				ary = ary.concat(taskId);
				ary1 = ary1.concat( rows[i].taskName);
		 }
		   var taskIds = ary.join(",");
		   var name = ary1.join(",");
		if(rows.length >= 1){
			$.messager.confirm('提示', '确定将\' '+name+'\'设置为无效 ?', function(r){
				if (r){
					$.ajax({
						url:sybp()+'/tblTaskTypeAction_delete.action?taskIds='+taskIds,
						type:'post',
						//data:taskIds:taskIds,
						dataType:'json',
						success:function(r){
							if(r && r.success){
								$('#taskTypeActionTable').treegrid('reload');
								//禁用按钮（编辑，删除）
								$('#editTaskTypecButton').linkbutton('disable');
								$('#delTaskTypeButton').linkbutton('disable');
								parent.showMessager(1,r.msg,true,5000);
							}else{
								parent.showMessager(3,r.msg,true,5000);
							}
						}
					});
				}
			});
		}else if(rows.length == 0){
			parent.showMessager(2,'请选择设置行',true,5000);
		}
	}

//安排负责人
	function onaddTaskLeaderButton(){
		var node = $('#taskTypeActionTable').treegrid('getSelected');
		window.location.href=sybp()+'/tblTaskTypeAction_toAddUI.action?taskKind='+node.id;
	}
</script>
</head>
<body>
<s:hidden id="taskIds" name="taskIds"></s:hidden>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">常规任务设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<table id="taskTypeActionTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addTaskTypeButton"  class="easyui-linkbutton" onclick="onaddTaskTypeButton();" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editTaskTypeButton" class="easyui-linkbutton" onclick="oneditTaskTypeButton();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delTaskTyperButton" class="easyui-linkbutton" onclick="ondelTaskTyperButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">设置无效</a>
			<a id="addTaskLeaderButton"  class="easyui-linkbutton" onclick="onaddTaskLeaderButton();" data-options="iconCls:'icon-user',disabled:true,plain:true">设置任务总负责人</a>
		</div>
	</div>
	 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	 <%@include file="/WEB-INF/jsp/tblTaskTypeAction/novaliDialog.jspf" %>
</body>
</html>