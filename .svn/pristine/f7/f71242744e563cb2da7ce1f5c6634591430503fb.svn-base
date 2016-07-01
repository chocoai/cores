<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>main</title>
		<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
		<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
		<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
		<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>
		<script type="text/javascript">
	var tblTOLLeaderTable;
	var tableSize ;
	 var tableHeight;//当前页面可见区域高度 - 86
	 var tableWidth;
	$(function(){
	       tableHeight = document.body.clientHeight - 86;
			tableWidth  = document.body.clientWidth;
			var scheduleId = $('#scheduleId').val();
		    tblTOLLeaderTable=$('#tblTOLLeaderTable').datagrid({
			url : sybp()+'/tblTOLeaderAction_loadListTOL.action?scheduleId='+scheduleId,
			title:'',
			height:tableHeight,
			width:tableWidth,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			//pageSize:50,
			//pageList:[50,100],
			//fit:true,
			//fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//singleSelect:true,
			//border:false,
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
				title:'任务名称',
				field:'taskName',
				width:150
			},{
				title:'操作者',
				field:'tOLeader',
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
			}
            
             ]],
			//工具栏
			toolbar:'#toolbar',
			onClickRow :function(rowIndex, rowData){
		          if(rowData.signId == '' || rowData.signId == null){
				    	$('#delTOLLeaderButton').linkbutton('enable');
				    	$('#noavailTOLLeaderButton').linkbutton('disable');
				    	$('#signTOLLeaderButton').linkbutton('enable');
					}else if(rowData.endDate !='' && rowData.endDate !=null){
				    	$('#delTOLLeaderButton').linkbutton('disable');
				    	$('#noavailTOLLeaderButton').linkbutton('disable');
				    	$('#signTOLLeaderButton').linkbutton('disable');
					}else{
				    	$('#delTOLLeaderButton').linkbutton('disable');
				    	$('#noavailTOLLeaderButton').linkbutton('enable');
				    	$('#signTOLLeaderButton').linkbutton('disable');
					}
		    },
		    onSelect:function(rowIndex, rowData){   
		        
		    },
		    onLoadSuccess:function(data){
		      if(data.rows.length <= 0){
			      document.getElementById('addTOLLeaderButton').click();
		       }
			 }
	   	}); //end datagrid
	   	
	   	
	   	$('#startDate').datebox({
		     onSelect: function(date){
			     if(!document.getElementById("checkboxEndDate").checked){
			        $('#endDate').datebox('setValue', date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
			     }
		     }
		   });
		$('#endDate').datebox({
		     onSelect: function(date){
		          var startDate = $('#startDate').datebox('getValue');
		          if(dateCompare(startDate,date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate())){
		              
		          }else{
		           //结束日期比开始时期大
		           $.messager.alert('提示','结束日期不能早于开始时间，请检查输入日期范围！','info');
		          }
		     }
		 });
		   
	   	
		  //显示整个布局
		$('#layoutMainDiv').css('display','');	
	});
	
	//比较时间大小
    function dateCompare(startdate,enddate){   
         var arr=startdate.split("-");    
         var starttime=new Date(arr[0],arr[1],arr[2]);    
         var starttimes=starttime.getTime();   
         var arrs=enddate.split("-");    
         var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
         var lktimes=lktime.getTime();   
         if(starttimes>lktimes){   
            return false;   
         }else  {
            return true;   
         }  
    }  
	
	//指定结束日期
	function checkboxEndDate(){
	      if(document.getElementById("checkboxEndDate").checked){
		        document.getElementById("checkboxEndDate").checked = false ;
			    $('#endDate').datebox('setValue','');
			    $('#endDate').datebox({'disabled':true});
		   }else{
		        document.getElementById("checkboxEndDate").checked = true;
		     	var startDate = $('#startDate').datebox('getValue');
			    $('#endDate').datebox('setValue',startDate);
			    $('#endDate').datebox({'disabled':false});
		   }
	
	}
	
	function afterCheckboxEndDate(obj){
	    if(obj.checked == true){
		         var startDate = $('#startDate').datebox('getValue');
			     $('#endDate').datebox('setValue',startDate);
		         $('#endDate').datebox({'disabled':false});
		   }else{
		         $('#endDate').datebox('setValue', '');
		         $('#endDate').datebox({'disabled':true});
		     
		      
		   }
	}
	
	function onExitButton(){
	    var scheduleId = $('#scheduleId').val();
		window.location.href=sybp()+'/tblSOLeaderAction_list.action?id='+scheduleId;
	}
	  

	function addTOLeaderButton(){
		document.getElementById("selectAllUser").checked=false;
		$('#add2TOLeaderDialog').dialog('open');
		$('#add2TOLeaderDialog2').dialog('open');
	}
	//房间
	function addSOLeaderDialong1(){
		var studyNo = $('#studyNo').val();
		var id = $('#id').val();
		UserMemberTable=$('#UserMemberTable').datagrid({
			url : sybp()+'/tblTOLeaderAction_addSOLeader.action?studyNo='+$('#studyNo').val()+'&scheduleId='+$('#scheduleId').val(),
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
				title:'操作者',
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
			url : sybp()+'/tblTOLeaderAction_addFloorSOLeader.action?studyNo='+$('#studyNo').val()+'&scheduleId='+$('#scheduleId').val(),
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
				title:'操作者',
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
		var scheduleId = $('#scheduleId').val();
		var id = $('#id').val();
		UserMemberTable=$('#UserMemberTable').datagrid({
			url : sybp()+'/tblTOLeaderAction_selectAllSOLeader.action?id='+$('#id').val()+'&scheduleId='+$('#scheduleId').val(),
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
				title:'操作者',
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
     qm_showQianmingDialog('afterSignTOLLeader');
   }
   
   
   function afterSignTOLLeader(){
	    var rows = $('#tblTOLLeaderTable').datagrid('getSelected');
        var scheduleId = $('#scheduleId').val();
        var endDate = rows.endDate;
        var allIds = rows.id;
        	$.ajax({
        		  	url : sybp()+'/tblTOLeaderAction_signAllTOLeader.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		  		scheduleId:scheduleId,
        		  		resMans:allIds,
        		  		endDate:endDate
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  	      $('#delTOLLeaderButton').linkbutton('disable');
				        	$('#noavailTOLLeaderButton').linkbutton('disable');
				    	   $('#signTOLLeaderButton').linkbutton('disable');
        		  			$('#tblTOLLeaderTable').datagrid('reload');
        		  			parent.parent.showMessager(1,'签字成功',true,5000);
        		  	}
        		  });
	}
	
	function onDelButton(){
	   var row = $('#tblTOLLeaderTable').datagrid('getSelected');
	   var id = row.id;
	        $.ajax({
        		  	url : sybp()+'/tblTOLeaderAction_delectTOLeader.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		  		id:id
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  	        $('#delTOLLeaderButton').linkbutton('disable');
				            $('#noavailTOLLeaderButton').linkbutton('disable');
				    	    $('#signTOLLeaderButton').linkbutton('disable');
        		  			$('#tblTOLLeaderTable').datagrid('reload');
        		  			parent.parent.showMessager(1,'删除成功',true,5000);
        		  	}
        		  });
	}
	
	
	//置为无效
		function onNovailButton(){
			var rowData = tblTOLLeaderTable.datagrid('getSelected');
			if(null != rowData && (rowData.signId != null && rowData.signId !='')
					&& (rowData.endDate ==null || rowData.endDate == '' )){
				//qm_showQianmingDialog('afterSign');
				$('#novaliId').val(rowData.id);
				$('#novaliLeader').html(rowData.tOLeader);
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
				var rowData = tblTOLLeaderTable.datagrid('getSelected');
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
				url:sybp()+'/tblTOLeaderAction_setupInvalid.action',
				type:'post',
				data:$('#novaliForm').serialize(),
				dataType:'json',
				success:function(r){
						if(r && r.success){
							tblTOLLeaderTable.datagrid('reload');
							//按钮置为无效
					        $('#delTOLLeaderButton').linkbutton('disable');
				    	    $('#noavailTOLLeaderButton').linkbutton('disable');
				    	    $('#signTOLLeaderButton').linkbutton('disable');
					    	
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
			$('#endDate').datebox('setValue', oneDayTime);
			$('#startDate').datebox('setValue',oneDayTime);
			selectAllUser(3);
			$('#addTOLLeaderDialog').dialog('setTitle','专题操作者添加');
			$('#addTOLLeaderDialog').dialog('open');
			$('#addTOLLeaderDialog2').dialog('open');
		}
		
		//返回(添加dialog)
		function onBackButton(){
			$('#addTOLLeaderDialog').dialog('close');
		}
		
			//保存按钮（dialog）
		function onSaveButton(){
			var startDate = $('#startDate').datebox('getValue')
			var endDate = $('#endDate').datebox('getValue')
			var scheduleId = $('#scheduleId').val();
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
				   	if(document.getElementById("checkboxEndDate").checked){
		     	     // alert('指定结束日期');   
			        }else{
			          //alert('不指定结束日期');
			          endDate = null;   
			        }
				    
				    
					$.ajax({
						url:sybp()+'/tblTOLeaderAction_save.action',
						type:'post',
						data:{
							userNamesStr:userNamesStr,
							startDate:startDate,
							scheduleId:scheduleId,
							endDate :endDate
							},
						dataType:'json',
						success:function(r){
							if(r && r.success){
								tblTOLLeaderTable.datagrid('reload');
								onBackButton();

								//按钮置为无效
						    	$('#delTOLLeaderButton').linkbutton('disable');
				    	         $('#noavailTOLLeaderButton').linkbutton('disable');
				    	        $('#signTOLLeaderButton').linkbutton('disable');

								
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
		}
		
		function afterSignAll(){
		    var ids = $('#signAllIds').val();
		    $.ajax({
					url:sybp()+'/tblTOLeaderAction_signAll.action',
					type:'post',
					data:{
						ids:ids
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
							    $('#delTOLLeaderButton').linkbutton('disable');
				    	        $('#noavailTOLLeaderButton').linkbutton('disable');
				    	        $('#signTOLLeaderButton').linkbutton('disable');
								$('#tblTOLLeaderTable').datagrid('reload');
        		  			    parent.parent.showMessager(1,'签字成功',true,5000);
							}else{
								$.messager.alert('警告',"与数据库交互异常");
							}
				   }
				});
		
		}
		
</script>
	</head>
	<body>	    
		<s:hidden id="scheduleId" name="scheduleId"></s:hidden>
		<s:hidden id="taskName" name="taskName"></s:hidden>
		<s:hidden id="studyNo" name="studyNo"></s:hidden>
		<s:hidden id="taskKind" name="taskKind"></s:hidden>
		<s:hidden id="signAllIds" name="signAllIds"/>	
        <div id="layoutMainDiv" class="easyui-tabs" fit="true" border="true" style="display:none">
	   <div title="专题任务操作者设置"  border="false" style="overflow:hidden;">
		<!-- 工具栏-start -->
		<div style="height: 27px; padding-left: 5px; padding-top: 1px; border: 1px solid #c9c9c9;">
			<a id="addTOLLeaderButton" class="easyui-linkbutton"  plain="true"
						onclick="onAddButton();" data-options="iconCls:'icon-useradd'">添加</a>

			<a id="delTOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onDelButton();" data-options="iconCls:'icon-userdel',disabled:true">删除</a>
			<a id="noavailTOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onNovailButton();" data-options="iconCls:'icon-dateend',disabled:true">设置结束日期</a>
			<a id="signTOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onSignButton();" data-options="iconCls:'icon-edit',disabled:true">签字确认</a>
			<a id="exitButton" class="easyui-linkbutton" plain="true"
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
		</div>
		<!-- 工具栏-end-->
		<div style="border-bottom: 1px solid #c8c8c8; height:20px;margin-top:5px;">
			&nbsp;&nbsp;&nbsp;&nbsp;任务名称：<s:property value="{#taskName}"/>
		</div>
		<div>
		  <table id="tblTOLLeaderTable" ></table>
		</div>
	    <%@include file="/WEB-INF/jsp/tblTOLeaderAction/addTOLLeaderDialog.jspf" %>
	    <%@include file="/WEB-INF/jsp/tblTOLeaderAction/novaliDialog.jspf" %>
	    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	</div>
</div>
	</body>
</html>