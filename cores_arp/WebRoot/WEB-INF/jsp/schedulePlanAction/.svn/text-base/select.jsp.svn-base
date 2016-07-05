<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/schedulePlanAction/schedulePlanAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var taskKind;
	var allLeaderTable;
	var studyNoTable;
	var schedulePlanTable;
	var taskNameTable;
	var registerFinishTable;
	var oneDaySchedulePlanTable;
	var realName;
	
	 var tableHeight;//当前页面可见区域高度 - 86
	 var tableWidth;
	 
	$(function(){
	    //var tableHeightt =  parent.parent.parent.tableHeight() - 260;
	    //var tableWidthtt  = parent.parent.parent.tableWidth() - 205;
	    tableHeightt =window.screen.height-368;
	  	tableWidthtt  =window.screen.width-290;
        //根据任务类型生成不同的表格
		taskKind = $('#taskKind').val();
		
		$('#showStartime').datebox({
		     width:90,
		     onSelect: function(date){
			     if(document.getElementById("checkboxOneDay").checked){
			        $('#showEndtime').datebox('setValue', date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
			     }
		     }
		   });
		$('#showEndtime').datebox({width:90,'disabled':false, onSelect: function(date){}});
		$('#showStartime').datebox('setValue', $('#startime').val());
		$('#showEndtime').datebox('setValue', $('#endtime').val());
		$('#schedulePlanTable').datagrid({
			url : sybp()+'/schedulePlanAction_getSchedulePlandatagrid.action',
			title:'',
			height:tableHeightt,
			width:tableWidthtt,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			sortOrder:'desc',
			remoteSort:false,			
			fit:false,			
			resizable:true,//如果为true，允许列改变大小。
			columns :[[{
				title:'id',
				field:'id',
				width:50,
				sortable:true,
				hidden:true
			},{
				title:'计划类型',
				field:'typename',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'标题',
				field:'title',
				width:280,
				halign:'center',
				align:'center'
			},{
				title:'内容',
				field:'contant',
				width:280,
				halign:'center',
				align:'center'
			},{
				title:'开始日期',
				field:'begindate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'结束日期',
				field:'enddate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'计划操作人员',
				field:'ower',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'计划状态',
				field:'status',
				width:80,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
																			if (row.status==1){
																				return "新建";
																			} else if(row.status==2){
																				return "开始";
																			} else{
																				return "完成";
																			}
													}
			}
			
			]],
			onSelect:function(rowIndex, rowData){
				if(rowData.status==2){//状态为开始才能进行完成登记.
    		  		$('#taskFinishRegisterButton').linkbutton('enable');
    			}else{
    				$('#taskFinishRegisterButton').linkbutton('disable');
    			}  
    			if(rowData.status==1){//开始之后不能进行变更.
    				$('#editTask').linkbutton('enable');
    				$('#taskStartRegisterButton').linkbutton('enable');
    			}else{
    				$('#editTask').linkbutton('disable');
    				$('#taskStartRegisterButton').linkbutton('disable');
    			}
			},
			onBeforeLoad:function(){
				var value = $('#p').progressbar('getValue');
			            if(value == 100){
		            	  value = 10;
		                }  
						if (value < 100){
							value += Math.floor(Math.random() * 10);
							$('#p').progressbar('setValue', value);
							setTimeout(arguments.callee, 200);
						}
			},
			onDblClickRow:function(rowIndex, rowData){
				showSchedulePlanAddEditDialog('afterEditDialog','view','查看计划安排');
			},
			onLoadSuccess:function(data){
				$('#progressbar').css('display',''); 
                $('#p').progressbar('setValue', 100);
                $('#progressbar').css('display','none');
                
			   $('#taskFinishRegisterButton').linkbutton('disable');
			   $('#taskStartRegisterButton').linkbutton('disable');
			   $('#editTask').linkbutton('disable');
	           var selectid=$('#sid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#schedulePlanTable').datagrid('selectRow',i);
			            	$('#sid').val('');
			             }
			          }
			   }
			   //登记
			   var regid=$('#regid').val();
			   if(regid!=''){
			   //获取所有行.
			   	var rows = $("#schedulePlanTable").datagrid("getRows");
				for(var i = 0 ; i< rows.length;i++){
					if(regid == rows[i].id){
						$('#schedulePlanTable').datagrid('selectRow',i);
						//重置regid
						$('#regid').val('');
					}
				}
				}
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
		schedulePlanTable=$('#schedulePlanTable1').datagrid({
			height:tableHeightt,
			width:tableWidthtt,
			fit:false,
			fitColumns:false,
			resizable:true,
			frozenColumns:[[
			                {field:'id',title:'id',width:100,rowspan:2,halign:'center',hidden:true},
			                {field:'typename',title:'计划类型',width:100,rowspan:2,halign:'center'},
			                {field:'title',title:'标题',width:100,rowspan:2,halign:'center'},
			                {field:'contant',title:'内容',width:100,halign:'center'},
			                {field:'begindate',title:'开始日期',width:100,halign:'center'},
			                {field:'enddate',title:'结束日期',width:100,halign:'center'},
			                {field:'status',title:'计划状态',width:100,halign:'center',align:'center',formatter: function(value,row,index){
																			if (row.status==1){
																				return "新建";
																			} else if(row.status==2){
																				return "开始";
																			} else{
																				return "完成";
																			}
																	}
							}//,
			                //{field:'isremind',title:'是否已经提醒',width:100,halign:'center',align:'center',formatter:function(value,row,index){if(value==0){return "否"}else{return "是"}}},
			           ]],
			onClickRow :function(rowIndex, rowData){
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
		    }//,
		    //rowStyler: function(index,row){
			//    if(row.dateCol.indexOf('六') != -1 ){
			//		return 'background-color:#F2F2F2;color:#080808;';
			////	}else if(row.dateCol.indexOf('日') != -1){
			//		return 'background-color:#BFEFFF;color:#080808;';
			//	}	
			//}
        });
		 $.ajax({
				//url : sybp()+'/schedulePlanAction_getSchedulePlandatagrid.action',
				type: 'post',
				data: {
			         startTime: $('#startime').val(),
	                 endTime:$('#endtime').val(),
	                 allDate:true,
	                 taskKind:taskKind
				},
				dataType:'json',
				success:function(r){
					if(r){
						//schedulePlanTable.datagrid({
						//	columns:r.columns
						//});
						//schedulePlanTable.datagrid('loadData',r.rows);
					}
				},
				beforeSend:function(){ 
				        //$('#progressbar').css('display',''); 
	//		            var value = $('#p').progressbar('getValue');
	//		            if(value == 100){
	//	            	  value = 10;
	//	                }  
	//					if (value < 100){
	//						value += Math.floor(Math.random() * 10);
	//						$('#p').progressbar('setValue', value);
	//						setTimeout(arguments.callee, 200);
	//					}
                }, 
                complete:function(){ 
                   //$('#progressbar').css('display',''); 
                   //$('#p').progressbar('setValue', 100);
                   //$('#progressbar').css('display','none'); 
                } 
         });
         
         $('#p').progressbar({ 
             text: "数据加载中..." 
          }); 
         
         $('#registerTime').datebox({    
            onSelect :function(date){
               var myDate = new Date();
               var datea=dateCompare(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate(),date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
              if(datea){
                 $('#saveDialogButton1').linkbutton('disable');
              }else{
                 $('#saveDialogButton1').linkbutton('enable');
              }
               selectRegisterFinishTable();
            }
         });  
		 //selectStudyNoTable();
	   	 //selectAllUserDialong();
	 	 //AnimalHouseRes();
	 	 //selecttaskNameTable();
	 	 $('#selectLayoutMainDiv').css('display','');   
	 	//document.onkeydown = KeyDown;
        });//匿名函数结束
       //单日勾选
       function checkboxOneDay(){
           if(document.getElementById("checkboxOneDay").checked){
		     	document.getElementById("checkboxOneDay").checked = false;
		     	$('#showEndtime').datebox({'disabled':false});
		   }else{
			    document.getElementById("checkboxOneDay").checked = true;
			    var startime= $('#showStartime').datebox('getValue');
		     	$('#showEndtime').datebox('setValue', startime);
			    $('#showEndtime').datebox({'disabled':true});
		   }
       }
       function afterCheckboxOneDay(obj){
          if(obj.checked == true){
                 var startime= $('#showStartime').datebox('getValue');
		         $('#showEndtime').datebox('setValue', startime);
		     	 $('#showEndtime').datebox({'disabled':true});
		   }else{
		       $('#showEndtime').datebox({'disabled':false});
			   
		   }
       }
	   function makeFullScreen() {
	    
	      var fullScreen = $('#fullScreen').val();
	      if(fullScreen != "all"){
	         var tableHeight = parent.parent.parent.window.screen.height;
		     var tableWidth  = parent.parent.parent.window.screen.width;		 
	          parent. parent.parent.makeFullScreen();
	          parent.parent.sidebardisplayNone();	
	          parent.makeFullScreenlist();
	          $("#selectLayoutMainDiv").css("height",'100%');
              $("#selectLayoutMainDiv").css("width",'100%');
		      $('#layoutDiv1').layout("resize",{ height:tableHeight-30,width:tableWidth-28});
		      $('#layoutDiv1').layout("panel","center").panel("resize",{ height:tableHeight-30,width:tableWidth-28});
		      var select =  $('#selectConditions').val();
		      if(select == "one"){
		        $('#oneDaySchedulePlanTable').datagrid({height:tableHeight-168,width:tableWidth-29});
		      }else{
		        $('#schedulePlanTable').datagrid({height:tableHeight-168,width:tableWidth-29});
		      }
			 
	          $('#fullScreen').val("all");
	      }else{
	          var tableHeight = parent.parent.parent.tableHeight();
		      var tableWidth  = parent.parent.parent.tableWidth();		
	          parent.parent.parent.reset();
	          parent.parent.sidebardisplayshow();	
	          parent.resetScreenlist();
	          $("#selectLayoutMainDiv").css("height",'98%');
              $("#selectLayoutMainDiv").css("width",'98%');
		      $('#layoutDiv1').layout("resize",{ height:tableHeight-65,width:tableWidth-190});
		      $('#layoutDiv1').layout("panel","center").panel("resize",{ height:tableHeight-65,width:tableWidth-190});
		      var  tableHeight1 = document.body.clientHeight - 145;
	          var  tableWidth1  = document.body.clientWidth-6;
	          var select =  $('#selectConditions').val();
		      if(select == "one"){
		         $('#oneDaySchedulePlanTable').datagrid({height:tableHeight1,width:tableWidth1-18});
		      }else{
		          $('#schedulePlanTable').datagrid({height:tableHeight1,width:tableWidth1-18});
		      }
	          $('#fullScreen').val("reset");
	      }
      }
	    
       
	
	function selectTheUserName(){
	    var name =  $("#thisSelectUserName").val();
	    $('#realName11').combotree('setValue', name);
	    var name1  =$('#realName11').combotree('getText');
	       $('#selectqianming_message').html("");
        if(name == name1){
           $('#realName11').combotree('setValue', "");
           $('#selectqianming_message').html("用户名错误");
        }
	    
	}
	 
	 function selectUserNameonBlur(){
	  if(event.keyCode == 13){
	 	selectTheUserName();
		selectqm_passwordCheck();
	 }
    }
    
    function makeSureTime(){
      selectStudyNoTable();
    }
    
    //添加按钮事件
    function addTaskButton(){
    	showSchedulePlanAddEditDialog('afterAddDialog','add','新建计划安排');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#schedulePlanTable').datagrid('reload');
    }
    // 编辑按钮事件
    function editTaskButton(){
         var row= $('#schedulePlanTable').datagrid('getSelected');
        if(row != null ){
        	showSchedulePlanAddEditDialog('afterEditDialog','edit','变更计划安排');
        }else{
           $.messager.alert('提示','请选择变更的计划!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.parent.showMessager(1,'变更成功',true,5000);
		 $('#schedulePlanTable').datagrid('reload');
     }
     //计划完成登记.
function taskFinishRegisterButton(){
	var row= $('#schedulePlanTable').datagrid('getSelected');
	if(null != row ){
   	$.messager.confirm('确认对话框', '确认该计划已完成？', function(r){
			if (r){
				schedulePlanRegistered(row.id);
				
			}
		});
  }else{
    $.messager.alert('提示','请选择要登记的计划安排!');
  }
}
//计划完成登记(数据库保存)
function schedulePlanRegistered(id){
   	$.ajax({
		url:sybp()+'/schedulePlanAction_scheduleFinishRegis.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 parent.parent.showMessager(1,'登记成功',true,5000);
				 $('#regid').val(id);
				$('#schedulePlanTable').datagrid('reload');
			}else{
				$('#regid').val(id);
				 parent.parent.showMessager(2,'登记失败',true,5000);
			}
		}
	});
	var selectid=id;
	if(selectid != ""){
		var rows = $("#schedulePlanTable").datagrid("getRows");
		for(var i = 0 ; i<rows.length;i++){
			if(selectid == rows[i].id){
				$('#schedulePlanTable').datagrid('selectRow',i);
			}
		}
	}
}

//计划开始登记.
function taskStartRegisterButton(){
	var row= $('#schedulePlanTable').datagrid('getSelected');
	if(null != row ){
   	$.messager.confirm('确认对话框', '确认该计划开始进行？', function(r){
			if (r){
				schedulePlanStartRegistered(row.id);
				
			}
		});
  }else{
    $.messager.alert('提示','请选择要登记的计划安排!');
  }
}
//计划开始登记(数据库保存)
function schedulePlanStartRegistered(id){
   	$.ajax({
		url:sybp()+'/schedulePlanAction_scheduleStartRegis.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				 parent.parent.showMessager(1,'登记成功',true,5000);
				$('#schedulePlanTable').datagrid('reload');
				$('#regid').val(id);
			}else{
				 parent.parent.showMessager(2,'登记失败',true,5000);
				 $('#regid').val('');
			}
		}
	});
	
	//var selectid=id;
	//if(selectid != ""){
	    //获取所有行.
	//	var rows = $("#schedulePlanTable").datagrid("getRows");
	//	for(var i = 0 ; i< rows.length;i++){
	//		if(selectid == rows[i].id){
	//			$('#schedulePlanTable').datagrid('selectRow',i);
	//		}
	//	}
	//}
}
function selectConditionsButton(){
	showSchedulePlanSelectDialog('afterSelectDialog','筛选条件');
}
function afterSelectDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#schedulePlanTable').datagrid('reload');
}
</script>
</head>
<s:hidden id="sid" name="sid"></s:hidden>
<s:hidden id="regid" name="regid"></s:hidden>
<body >
  <div id="selectLayoutMainDiv"  style="width:99.6%;height:100%; display:none;"> 
    <s:hidden id="selectConditions" name="selectConditions" ></s:hidden>
    <!-- 任务类型 -->
    <s:hidden id="layoutHeight" name="layoutHeight" value="515"></s:hidden>
	<s:hidden id="taskKind" name="taskKind"></s:hidden>
	<s:hidden id="endtime" name="endtime"></s:hidden>
	<s:hidden id="startime" name="startime"></s:hidden>
	<s:hidden id="fullScreen" name="fullScreen"></s:hidden>
	<div id="layoutDiv1" class="easyui-layout" fit="true" border="false"  >
		<div region="north" border="false"  style="height:30px;">
		<div style="margin-left:20px;">
			<a id="taskStartRegisterButton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="taskStartRegisterButton();" data-options="plain:true">任务开始登记</a>
	      <a id="selectSchedulePlanButton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="selectConditionsButton();" data-options="iconCls:'icon-search',plain:true">条件查询</a>
	      <a id="taskFinishRegisterButton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="taskFinishRegisterButton();" data-options="plain:true">任务完成登记</a>
	      <!--  <a id="easyui-linkbutton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="makeFullScreen();" data-options="iconCls:'icon-monitor',plain:true">全屏/退出</a>-->
	      <a id="addTask" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="addTaskButton();" data-options="iconCls:'icon-monitor',plain:true">新建计划安排</a>
		  <a id="editTask" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="editTaskButton();" data-options="iconCls:'icon-monitor',plain:true">计划变更</a>
		</div>
		</div>
		<!-- table start -->
		<div  region="center" border=false style="border: 0px solid #c8c8c8;">
		    <div id="progressbar" style="position:absolute;z-index:100;width:400px;margin-top:-100px;margin-left:-200px;left:50%;top:50%;display:'';">
		    <div id="p" class="easyui-progressbar" style="width:400px;"></div>
		    </div>
		    <div id="schedulePlanTableDiv" >
			<table id="schedulePlanTable" style="display:'';" ></table></div>
			<div id="oneDaySchedulePlanTableDiv"  >
			<table id="oneDaySchedulePlanTable" style="display:none" ></table>
			</div>
			<br/>
			<div >
			<a  style="overflow:auto;margin-top: 0px;" 
			class="easyui-linkbutton" onclick="" data-options="iconCls:'',plain:true">描述</a>
	        <textarea id= "description" cols="125" rows="3"  style="overflow:auto;" readonly="readonly" ></textarea>
	       </div>
		</div>
		<!-- table end -->
	</div>
	<%@include file="/WEB-INF/jsp/schedulePlanAction/schedulePlanAddEdit.jspf" %>
	<%@include file="/WEB-INF/jsp/schedulePlanAction/schedulePlanSelect.jspf" %>
 </div>
	      
</body>
</html>