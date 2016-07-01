<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/style/css/style.css"
			media="screen" />
		<script language="javascript"
			src="${pageContext.request.contextPath}/script/javascript/qianming.js"
			charset="utf-8"></script>
		<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>
		<script type="text/javascript">
	var tblSOLLeaderTable;
	var tableSize ;
	var SOLStudyNo;
	 var tableHeight;//当前页面可见区域高度 - 86
	 var tableWidth;
	$(function(){
	    SOLStudyNo = $('#studyNo').val();
	    var taskKind =  $('#taskKind').val();
	      tableHeight = document.body.clientHeight - 86;
			tableWidth  = document.body.clientWidth;
		tblSOLLeaderTable=$('#tblSOLLeaderTable').datagrid({
			url : sybp()+'/tblSOLeaderAction_loadListSOL.action?studyNo='+SOLStudyNo+'&taskKind='+taskKind,
			title:'',
			height:tableHeight,
			width:tableWidth,
			iconCls:'',
			nowarp:  false,//单元格里自动换行
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			columns :[[{
				title:'',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'专题编号',
				field:'studyNo',
				width:150
			},{
				title:'负责人',
				field:'soleader',
				width:150
			},{
				title:'开始日期',
				field:'startDate',
				width:150
			},{
				title:'结束日期(确认人)',
				field:'endDate',
				width:150
            },{
				title:'确认人',
				field:'signId',
				width:150
			},{
				title:'',
				field:'finsh',
				width:150,
				hidden:true
			}
             ]],
			//工具栏
			toolbar:'#toolbar',
			onClickRow :function(rowIndex, rowData){
			    if(rowData.signId == '' || rowData.signId == null){
				    	$('#delSOLLeaderButton').linkbutton('enable');
				    	$('#noavailSOLLeaderButton').linkbutton('disable');
				    	$('#signSOLLeaderButton').linkbutton('enable');
					}else if(rowData.endDate !='' && rowData.endDate !=null){
				    	$('#delSOLLeaderButton').linkbutton('disable');
				    	$('#noavailSOLLeaderButton').linkbutton('disable');
				    	$('#signSOLLeaderButton').linkbutton('disable');
					}else{
				    	$('#delSOLLeaderButton').linkbutton('disable');
				    	$('#noavailSOLLeaderButton').linkbutton('enable');
				    	$('#signSOLLeaderButton').linkbutton('disable');
					}
		    },
		    onLoadSuccess:function(data){
			   if(data.rows.length <= 0){
				     document.getElementById('addSOLLeaderButton').click();
		       }
			 }
		    
	   	}); //end datagrid
		//addSOLeaderDialong1();
		
		
		  //显示整个布局
		$('#layoutMainDiv').css('display','');	
	});
	
	 
	function onExitButton(){
	    var studyNo = $('#studyNo').val();
		window.location.href=sybp()+'/tblSOLeaderAction_list.action?id='+studyNo;
	}
	  

	function addSOLeaderButton(){
		document.getElementById("selectAllUser").checked=false;
		$('#add2SOLeaderDialog').dialog('open');
		$('#add2SOLeaderDialog2').dialog('open');
	}
	//房间
	function addSOLeaderDialong1(){
		var studyNo = $('#studyNo').val();
		var id = $('#id').val();
		UserMemberTable=$('#UserMemberTable').datagrid({
			url : sybp()+'/tblSOLeaderAction_addSOLeader.action?studyNo='+$('#studyNo').val(),
	   	    title:'',
			height: 140,
			width:200,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title:'',
				field:'id',
				checkbox:true
			},{
				title:'负责人',
				field:'text',
				width:60
			}]],
			onClickRow :function(rowIndex, rowData){
			    
			},
			onLoadSuccess:function(data){
			
			}
		}); //end 
	}
	//楼层
	function addSOLeaderFloorDialong1(){
		var studyNo = $('#studyNo').val();
		UserMemberTable=$('#UserMemberTable').datagrid({
			url : sybp()+'/tblSOLeaderAction_addFloorSOLeader.action?studyNo='+$('#studyNo').val(),
	   	    title:'',
			height:140,
			width:200,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title:'',
				field:'id',
				checkbox:true
			},{
				title:'负责人',
				field:'text',
				width:60
			}]],
			onClickRow :function(rowIndex, rowData){
			    
			},
			onLoadSuccess:function(data){
				 
				}
		}); //end 
	}
	
	function selectAllUser1(){
	 var value=""; 
	 var radio=document.getElementsByName("room");  
	 for(var i=0;i<radio.length;i++){	if(radio[i].checked==true){	  value=radio[i].value;	  break;	}  }
	 if(value == 1){
                addSOLeaderDialong1();
             }else if(value == 2){
               addSOLeaderFloorDialong1();
             }else if(value == 3){
                selectAllUserDialong();
             }
	}
	//选择人员
	function selectAllUser(value){
             if(value == "1" ){
                addSOLeaderDialong1();
                document.getElementById("selectRoom").checked = "checked";   
             }else if(value == "2"){
               addSOLeaderFloorDialong1();
                document.getElementById("selectFloor").checked = "checked";   
             }else if(value == "3"){
                selectAllUserDialong();
                document.getElementById("selectAllUser").checked = "checked";   
             }
	}
   
   
   //补0函数 var strNow = nowDate.getFullYear()+"-"+(nowDate.getMonth() + 1)+"-"+nowDate.getDate();
	function appendZero(s){
			return ("00"+ s).substr((s+"").length);
	}  
   //全部人员
   function selectAllUserDialong(){
		var studyNo = $('#studyNo').val();
		var id = $('#id').val();
		   var taskKind =  $('#taskKind').val();
		UserMemberTable=$('#UserMemberTable').datagrid({
			url : sybp()+'/tblSOLeaderAction_selectAllSOLeader.action?id='+$('#id').val()+'&studyNo='+$('#studyNo').val()+'&taskKind='+taskKind,
	   	    title:'',
			height: 140,
			width:200,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title:'',
				field:'id',
				checkbox:true
			},{
				title:'负责人',
				field:'text',
				width:60
			}]],
			onClickRow :function(rowIndex, rowData){
			       
			},
			onLoadSuccess:function(data){
				
			}
		}); //end 
	}
	
   
   //-------------------------------
   function onSignButton(){
     var index = $('#index').val();
     qm_showQianmingDialog('afterSignSOLLeader');
   }
   
   
   function afterSignSOLLeader(){
	    var rows = $('#tblSOLLeaderTable').datagrid('getSelected');
        var studyNo = $('#studyNo').val();
        var endDate = rows.endDate;
        var allIds = rows.id;
        	$.ajax({
        		  	url : sybp()+'/tblSOLeaderAction_signAllSOLeader.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		  		studyNo:studyNo,
        		  		resMans:allIds,
        		  		endDate:endDate
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  	    if(r && r.success){
        		  	        $('#delSOLLeaderButton').linkbutton('disable');
						    $('#noavailSOLLeaderButton').linkbutton('disable');
						    $('#signSOLLeaderButton').linkbutton('disable');
        		  		    $('#tblSOLLeaderTable').datagrid('reload');
        		  		    parent.parent.showMessager(1,'签字成功',true,5000);
        		  	    }else{
        		  	         $.messager.alert('警告','与数据库交互异常');
        		  	    }
        		  	   
        		  	}
        		  });
	}
	
	function onDelButton(){
	   var row = $('#tblSOLLeaderTable').datagrid('getSelected');
	   var id = row.id;
	        $.ajax({
        		  	url : sybp()+'/tblSOLeaderAction_delectSOLeader.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		  		id:id
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  	        $('#delSOLLeaderButton').linkbutton('disable');
						    	$('#noavailSOLLeaderButton').linkbutton('disable');
						    	$('#signSOLLeaderButton').linkbutton('disable');
        		  			$('#tblSOLLeaderTable').datagrid('reload');
        		  			parent.parent.showMessager(1,'删除成功',true,5000);
        		  	}
        		  });
	}
	
	
	//置为无效
		function onNovailButton(){
			var rowData = tblSOLLeaderTable.datagrid('getSelected');
			if(null != rowData && (rowData.signId != null && rowData.signId !='')
					&& (rowData.endDate ==null || rowData.endDate == '' )){
				//qm_showQianmingDialog('afterSign');
				$('#novaliId').val(rowData.id);
				$('#novaliLeader').html(rowData.soleader);
				//$('#novailEndDate').datebox('setValue','');
				var d = new Date();
				var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
				  // $('#oneDayTime').datebox('setValue', oneDayTime);
				$('#novailEndDate').datebox('setValue',oneDayTime);
				
				$('#novaliDialog').dialog('open');
				$('#novaliDialog2').dialog('open');
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}

		//在置为无效dialog上的确定按钮事件
		function onButton_novaliDialog(){
			if( $('#novaliForm').form('validate') ){
				$('#novaliDialog').dialog('close');
				var endDate  = $('#novailEndDate').datebox('getValue');
				var rowData = tblSOLLeaderTable.datagrid('getSelected');
				if(dateCompare(rowData.startDate,endDate)){
				  qm_showQianmingDialog('afterSign_novail');
				}else{
				  	$.messager.alert('警告','结束日期不能早于开始日期！');
				}
			}
		}
		//置为无效，签字后事件
		function afterSign_novail(){
			$.ajax({
				url:sybp()+'/tblSOLeaderAction_setupInvalid.action',
				type:'post',
				data:$('#novaliForm').serialize(),
				dataType:'json',
				success:function(r){
						if(r && r.success){
							tblSOLLeaderTable.datagrid('reload');
							//按钮置为无效
					    	$('#delSOLLeaderButton').linkbutton('disable');
						    	$('#noavailSOLLeaderButton').linkbutton('disable');
						    	$('#signSOLLeaderButton').linkbutton('disable');
					    	
							parent.parent.showMessager(1,'设置结束日期成功',true,5000);
						}else{
							$.messager.alert('警告',r.msg);
						}
					}
			});
		}
		//在置为无效dialog上的返回按钮事件
		function onBackButton_novaliDialog(){
			$('#novaliDialog').dialog('close');
		}
	
	
	    //添加按钮事件
	    function onAddButton(){
	        var taskKind = $('#taskKind').val();
	        if(taskKind != 2){
	            $("#selectRoom").attr("disabled", true); 
	            $("#selectFloor").attr("disabled", true); 
	             $("#selectRooma").attr("disabled", true); 
	            $("#selectFloora").attr("disabled", true); 
	        }else{
	            $("#selectRoom").attr("disabled", false); 
	            $("#selectFloor").attr("disabled", false); 
	            $("#selectRooma").attr("disabled", false); 
	            $("#selectFloora").attr("disabled", false); 
	        }
	       
			var d = new Date();
			var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
			  // $('#oneDayTime').datebox('setValue', oneDayTime);
			$('#startDate').datebox('setValue',oneDayTime);
			selectAllUser(3);
			$('#addSOLLeaderDialog').dialog('setTitle','专题任务操作者添加');
			$('#addSOLLeaderDialog').dialog('open');
			$('#addSOLLeaderDialog2').dialog('open');
		}
		
		//返回(添加dialog)
		function onBackButton(){
			$('#addSOLLeaderDialog').dialog('close');
		}
		
			//保存按钮（dialog）
		function onSaveButton(){
			var startDate = $('#startDate').datebox('getValue')
			var studyNo = $('#studyNo').val();
			var taskKind =  $('#taskKind').val();
			if(startDate==null || startDate=='' ){
				$.messager.alert('警告','请选择开始日期');    
			}else{
				var selectedRows = $('#UserMemberTable').datagrid('getSelections');
				var userNames = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].id;
					userNames = userNames.concat(userName);
				}
				var userNamesStr = userNames.join(",");
				if(userNames.length > 0){
					$.ajax({
						url:sybp()+'/tblSOLeaderAction_save.action',
						type:'post',
						data:{
							userNamesStr:userNamesStr,
							startDate:startDate,
							studyNo:studyNo,
							taskKind:taskKind
							},
						dataType:'json',
						success:function(r){
							if(r && r.success){
								tblSOLLeaderTable.datagrid('reload');
								onBackButton();

								//按钮置为无效
						    	$('#delSOLLeaderButton').linkbutton('disable');
						    	$('#noavailSOLLeaderButton').linkbutton('disable');
						    	$('#signSOLLeaderButton').linkbutton('disable');
								parent.parent.showMessager(1,r.msg,true,5000);
								var ids = r.obj;
								$('#signAllIds').val(ids);
								parent.parent.showMessager(1,r.msg,true,5000);
								$.messager.defaults = { ok: "是", cancel: "否" }; 
								
				                $.messager.confirm('确认','是否签字提交!',function(r){    
				                		  if (r){
				                			 qm_showQianmingDialog('afterSignAll()');
				                		  }else{
				                		  }    
				                }); 
							}else{
								$.messager.alert('警告',r.msg);
							}
						}
		
					});
				}else{
					$.messager.alert('提示','请先选择负责人');  
				}

				
			}
				if( $('#taskLeaderAddForm').form('validate') ){
				
			}else{
				
			}
			//
		}
		
		function afterSignAll(){
		  var ids = $('#signAllIds').val();
		  $.ajax({
        		  	url : sybp()+'/tblSOLeaderAction_signAll.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data:{
						ids:ids
					},
        		  	dataType:'json',
        		  	success:function(r){
        		  	      if(r && r.success){
        		  	         $('#delSOLLeaderButton').linkbutton('disable');
						     $('#noavailSOLLeaderButton').linkbutton('disable');
						     $('#signSOLLeaderButton').linkbutton('disable');
        		  		     $('#tblSOLLeaderTable').datagrid('reload');
        		  		      parent.parent.showMessager(1,'签字成功',true,5000);
        		  	      }else{
        		  	         $.messager.alert('警告','与数据库交互异常');
        		  	      } 
        		  	       
        		  	}
        		  });
		}
</script>
	</head>
	<body>	    
		<s:hidden id="id" name="id"></s:hidden>
		<s:hidden id="studyNo" name="studyNo"></s:hidden>
		<s:hidden id="signAllIds" name="signAllIds"/>	
		<s:hidden id="taskKind" name="taskKind"></s:hidden>
        <div id="layoutMainDiv" class="easyui-tabs" fit="true" border="true" style="display:none">
	   <div title="专题任务负责人设置设置"  border="false" style="overflow:hidden;">
		<!-- 工具栏-start -->
		<div style="height: 27px; padding-left: 5px; padding-top: 1px; border: 1px solid #c9c9c9;">
			<a id="addSOLLeaderButton" class="easyui-linkbutton"  plain="true"
						onclick="onAddButton();" data-options="iconCls:'icon-useradd'">添加</a>

			<a id="delSOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onDelButton();" data-options="iconCls:'icon-userdel',disabled:true">删除</a>
			<a id="noavailSOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onNovailButton();" data-options="iconCls:'icon-dateend',disabled:true">设置结束日期</a>
			<a id="signSOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onSignButton();" data-options="iconCls:'icon-edit',disabled:true">签字确认</a>
			<a id="exitButton" class="easyui-linkbutton" plain="true"
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
		</div>
		<!-- 工具栏-end-->
		<div style="border-bottom: 1px solid #c8c8c8; height:20px;margin-top:5px;">
			&nbsp;&nbsp;&nbsp;&nbsp;专题编号：<s:property value="{#studyNo}"/>
			&nbsp;&nbsp;&nbsp;&nbsp;专题任务类别：<s:property value="{#taskKindName}"/>
		</div>
		<div>
		  <table id="tblSOLLeaderTable" ></table>
		</div>
	    <%@include file="/WEB-INF/jsp/tblSOLeaderAction/addSOLLeaderDialog.jspf" %>
	    <%@include file="/WEB-INF/jsp/tblSOLeaderAction/novaliDialog.jspf" %>
	    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</div>
	</body>
</html>