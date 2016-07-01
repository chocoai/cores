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
		
		$('#batchStartDate').datebox({
		     onSelect: function(date){
		         var startDate = $('#batchStartDate').datebox('getValue');
			     
			     if(!document.getElementById("checkboxEndDate").checked){
			        $('#batchEndDate').datebox('setValue', startDate);
			        
			     }
			     var endDate = $('#batchEndDate').datebox('getValue');
			     if((!dateCompare(startDate,endDate ) )&& (endDate!="") && (endDate != startDate  )){
			        $.messager.alert('提示','结束日期不能大于开始日期，请检查输入日期？','info'); 
			        //break;
			        
			     }else{
				     if(endDate==""){
				        endDate = '2033-01-01';
				     }
				        var rows = $('#tblSOLeaderTable').datagrid('getSelections');
					    var ary = new Array();
					    for(var j=0;j<rows.length;j++){		
								var gettaskName  =  rows[j].taskName;
								ary = ary.concat(gettaskName);
					    }
					    var getSelections = ary.join(",");
					    var rows = $('#selectTblTaskLeaderTable').treegrid('getSelections');
				        $('#selectTblTaskLeaderTable').treegrid({    
			            url:sybp()+'/tblTaskLeaderAction_selectBatachSetloadList.action?taskNames='+encodeURIComponent(getSelections)
			            +'&startDate='+startDate+'&endDate='+endDate,
			            idField:'id', 
			            onLoadSuccess:function(row, data){
			                  for(var i = 0;i<rows.length;i++){
				                  $('#selectTblTaskLeaderTable').treegrid('select',rows[i].id);
				               }
			            }
			            });
			     }
		     }
		 });
		$('#batchEndDate').datebox({
		     onSelect: function(date){
			     var startDate = $('#batchStartDate').datebox('getValue');
			     var endDate = $('#batchEndDate').datebox('getValue');
			     if((!dateCompare(startDate,endDate)) && endDate!=""){
			        $.messager.alert('提示','结束日期不能大于开始日期，请检查输入日期？','info'); 
			       // break;
			     }else{
				     if(endDate==""){
				       endDate = '2033-01-01';
				     }
				     var rows = $('#tblSOLeaderTable').datagrid('getSelections');
					  var ary = new Array();
					  for(var j=0;j<rows.length;j++){		
								var gettaskName  =  rows[j].taskName;
								ary = ary.concat(gettaskName);
					  }
					  var rows = $('#selectTblTaskLeaderTable').treegrid('getSelections');
					  var getSelections = ary.join(",");
				      $('#selectTblTaskLeaderTable').treegrid({    
			            url:sybp()+'/tblTaskLeaderAction_selectBatachSetloadList.action?taskNames='+encodeURIComponent(getSelections)
			            +'&startDate='+startDate+'&endDate='+endDate,
			                idField:'id', 
				            onLoadSuccess:function(row, data){
				               for(var i = 0;i<rows.length;i++){
				                  $('#selectTblTaskLeaderTable').treegrid('select',rows[i].id);
				               }
				            }
			          });
			     }
		      }
		 });
		
		
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
	  tblSOLeaderTable = $('#tblSOLeaderTable').datagrid({    
		    url:sybp()+'/tblSOLeaderAction_loadListBatchSet.action?startime='+startDate+'&endtime='+endDate,    
		    idField:'id',    
		    height:tableHeight,
		    animate:false,   
		    singleSelect:false, //不支持多选
		    columns:[[  
                {title:'id',field:'id',width:180,checkbox:true,sortable:'true'},
		        {field:'studyNo',title:'专题编号',width:180,sortable:'true'},
		        {field:'taskName',title:'任务名称',width:180,sortable:'true'},
		        {field:'startDate',title:'日程开始日期',width:180},
		        {field:'soleader',title:'操作者',width:570,sortable:'true'},
		        {field:'privilege',title:'privilege',width:180,hidden:true,sortable:'true'}
		    ]],
		    //工具栏
			toolbar:'#toolbar',  
			onSelect:function(row){
			    var rows = $('#tblSOLeaderTable').datagrid('getSelections');
			    if(rows != "" && rows.length > 0){
			       $('#signSOLLeaderButton').linkbutton('enable');
			       $('#batchSetButton').linkbutton('enable');
			    }else{
			       $('#signSOLLeaderButton').linkbutton('disable');
			       $('#batchSetButton').linkbutton('disable');
			    }
			    var ary = new Array();
				 for(var j=0;j<rows.length;j++){
						if(rows[j].soleader!=""&&rows[j].soleader!=null){
								var id  =  rows[j].id;
								ary = ary.concat(id);
						}
				  }
				 var ids = ary.join(",");
				 if(ids != "" ){
				       $.ajax({
				      		  	url : sybp()+'/tblSOLeaderAction_checkSignBatch.action',
				      		  	type: 'post',
				      		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				      		  	data: {
				      		  		scheduleIDs:ids
				      		  	},
				      		  	dataType:'json',
				      		  	success:function(r){
					      		  	if(r && r.success){
					      		  	   $('#signSOLLeaderButton').linkbutton('enable');
					      		    }else{
					      		  	    $('#signSOLLeaderButton').linkbutton('disable');
					      		  	}
				      		  			
				      		   }
				      });
				 
				 }
	     	},
	     	onUnselect:function(row){
	     	    var rows = $('#tblSOLeaderTable').datagrid('getSelections');
			    if(rows != "" && rows.length > 0){
			       $('#signSOLLeaderButton').linkbutton('enable');
			       $('#batchSetButton').linkbutton('enable');
			    }else{
			       $('#signSOLLeaderButton').linkbutton('disable');
			       $('#batchSetButton').linkbutton('disable');
			    }
			    var ary = new Array();
				 for(var j=0;j<rows.length;j++){
						if(rows[j].soleader!=""&&rows[j].soleader!=null){
								var id  =  rows[j].id;
								ary = ary.concat(id);
						}
				  }
				 var ids = ary.join(",");
				 if(ids != "" ){
				       $.ajax({
				      		  	url : sybp()+'/tblSOLeaderAction_checkSignBatch.action',
				      		  	type: 'post',
				      		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				      		  	data: {
				      		  		scheduleIDs:ids
				      		  	},
				      		  	dataType:'json',
				      		  	success:function(r){
					      		  	if(r && r.success){
					      		  	   $('#signSOLLeaderButton').linkbutton('enable');
					      		    }else{
					      		  	    $('#signSOLLeaderButton').linkbutton('disable');
					      		  	}
				      		  			
				      		   }
				      });
				 
				 }
	     	},
	     	onSelectAll:function(rows){
	     	    var rows = $('#tblSOLeaderTable').datagrid('getSelections');
			    if(rows != "" && rows.length > 0){
			       $('#signSOLLeaderButton').linkbutton('enable');
			       $('#batchSetButton').linkbutton('enable');
			    }else{
			       $('#signSOLLeaderButton').linkbutton('disable');
			       $('#batchSetButton').linkbutton('disable');
			    }
			     var ary = new Array();
				 for(var j=0;j<rows.length;j++){
						if(rows[j].soleader!=""&&rows[j].soleader!=null){
								var id  =  rows[j].id;
								ary = ary.concat(id);
						}
				  }
				 var ids = ary.join(",");
				 if(ids != "" ){
				       $.ajax({
				      		  	url : sybp()+'/tblSOLeaderAction_checkSignBatch.action',
				      		  	type: 'post',
				      		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				      		  	data: {
				      		  		scheduleIDs:ids
				      		  	},
				      		  	dataType:'json',
				      		  	success:function(r){
					      		  	if(r && r.success){
					      		  	   $('#signSOLLeaderButton').linkbutton('enable');
					      		    }else{
					      		  	    $('#signSOLLeaderButton').linkbutton('disable');
					      		  	}
				      		  			
				      		   }
				      });
				 
				 }
			    
	     	
	     	},
	     	onLoadSuccess:function(row, data){
	     	
		    }
		}); 
	}
	//返回
	function onExitButton(){
		window.location.href=sybp()+'/tblSOLeaderAction_list.action';
	}
	//签字确认
	function onSignButton(){
      qm_showQianmingDialog('afterSignTOLLeader');
    }
    //真签字
    function afterSignTOLLeader(){
       var rows = $('#tblSOLeaderTable').datagrid('getSelections');
	   var ary = new Array();
	   for(var j=0;j<rows.length;j++){
			if(rows[j].soleader!=""&&rows[j].soleader!=null){
					var id  =  rows[j].id;
					ary = ary.concat(id);
			}
	  }
	    var ids = ary.join(",");
	       $.ajax({
      		  	url : sybp()+'/tblSOLeaderAction_signBatchSetAction.action',
      		  	type: 'post',
      		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
      		  	data: {
      		  		scheduleIDs:ids
      		  	},
      		  	dataType:'json',
      		  	success:function(r){
      		  	 
	      		  	if(r && r.success){
	      		        	$('#tblSOLeaderTable').datagrid('reload');
	      		  			parent.parent.showMessager(1,'签字成功',true,5000);
	      		  			$('#saveDialogButton').linkbutton('enable');
	      		  	}else{
	      		  	       $.messager.alert('警告','与数据库交互异常！');
	      		  	}
      		  			
      		  	}
        });
    }
	//填充窗口
	function batchSetButton(){
	   var d = new Date();
	   var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
	   $('#batchStartDate').datebox('setValue',oneDayTime);
	   $('#batchEndDate').datebox('setValue',oneDayTime);
	   selectTblTaskLeaderTable();
	   $('#addBatchSetDialog').dialog('setTitle','常规任务设置批量处理');
	   $('#addBatchSetDialog').dialog('open');
	   $('#addBatchSetDialog2').dialog('open');
	}
	
	//补0函数 var strNow = nowDate.getFullYear()+"-"+(nowDate.getMonth() + 1)+"-"+nowDate.getDate();
	function appendZero(s){
			return ("00"+ s).substr((s+"").length);
	} 
	
	//常规任务负责人窗口
	function selectTblTaskLeaderTable(){
	  var rows = $('#tblSOLeaderTable').datagrid('getSelections');
	  var ary = new Array();
	  for(var j=0;j<rows.length;j++){
				var gettaskName  =  rows[j].taskName;
				ary = ary.concat(gettaskName);
	  }
	  var getSelections = ary.join(",");
	  $('#selectTblTaskLeaderTable').treegrid({    
		   url:sybp()+'/tblTaskLeaderAction_selectBatachSetloadList.action?taskNames='+encodeURIComponent(getSelections)
		    +'&startDate='+$('#batchStartDate').datebox('getValue')+'&endDate='+'2033-01-01',    
		    idField:'id',  
		    height: 380,  
		    width:980,
		    treeField:'taskName', 
		    animate:false,   
		    singleSelect: false, //支持多选
		    cascadeCheck :true , //普通级联（不包括未加载的子节点）
            deepCascadeCheck :true, //深度级联（包括未加载的子节点）
		    columns:[[  
                {title:'id',field:'id',width:180,checkbox:true},
		        {title:'',field:'taskName',width:100},      
		        {title:'',field:'tasks',width:825}, 
		        {field:'taskLeader',title:'负责人',width:80,hidden:true}, 
		        {field:'_parentId',title:'_parentId',width:20,hidden:true},
		        {field:'taskLeaderId',title:'taskLeaderId',width:20,hidden:true}
		    ]],
			onSelect :function(row){
			   if(row._parentId == ""){
			       var rows = $('#selectTblTaskLeaderTable').treegrid('getChildren',row.id);
			       for(var i = 0 ;i<rows.length;i++){
			         $('#selectTblTaskLeaderTable').treegrid('select',rows[i].id);
			       }
			   }
	     	},
	     	onUnselect:function(row){
			   if(row._parentId == "" && $('#select').val() == "true" ){
			       var rows = $('#selectTblTaskLeaderTable').treegrid('getChildren',row.id);
			       for(var i = 0 ;i<rows.length;i++){
			         $('#selectTblTaskLeaderTable').treegrid('unselect',rows[i].id);
			       }
			   }else{
			        var prow = $('#selectTblTaskLeaderTable').treegrid('getParent',row.id);
			         $('#select').val("false");
			        if(prow != null){
			          $('#selectTblTaskLeaderTable').treegrid('unselect',prow.id);
			        }
			   }
			   $('#select').val("true");
	     	},
			onLoadSuccess:function(row, data){
			   var rows =  $('#tblSOLeaderTable').datagrid('getSelections');
			   if("" != data.noTaskLeader){
			        $.messager.alert('提示',data.noTaskLeader + " 任务<br>暂无常规任务负责人,如需要添加,请添加常规任务负责人");
			        var name = data.noTaskLeader;
			        if( name.indexOf(",") > 0 ){
                      // alert('多个任务无负责人');
                      var array = name.split(",");
                      for (var i=0 ; i< array.length ; i++){ 
                          for (var j=0 ; j< rows.length ; j++){
                             if(array[i] == rows[j].taskName){
                               $('#tblSOLeaderTable').datagrid('unselectRow',$('#tblSOLeaderTable').datagrid('getRowIndex',rows[j]));
                             }
                          }
                      }
                    }else{
                          for (var j=0 ; j< rows.length ; j++){
                             if(name == rows[j].taskName){
                               $('#tblSOLeaderTable').datagrid('unselectRow',$('#tblSOLeaderTable').datagrid('getRowIndex',rows[j]));
                             }
                          }
                    }
			   }
			  
		    }
		}); 
	}
	
	//批量保存 前检查
	function addBatchSetSaveButton(){
	   var rows = $('#selectTblTaskLeaderTable').treegrid('getSelections');
	   var ary = new Array();
	   for(var j=0;j<rows.length;j++){
			if(rows[j].taskLeaderId!="" && rows[j].taskLeaderId!=null){
					var gettaskName  =  rows[j].id;
					ary = ary.concat(gettaskName);
			}
		  }
	   var getSelections = ary.join(",");//获取 TblTaskLeader 所选择的id
	   var rows2 = $('#tblSOLeaderTable').datagrid('getSelections');
	   if(rows2.length > 0 && rows.length > 0){
	     	 var ary2 = new Array();
			 for(var j=0;j<rows2.length;j++){
						var gettaskName  =  rows2[j].id;
						ary2 = ary2.concat(gettaskName);
				 }
			 var getSelections2 = ary2.join(",");//获取 日程scheduleID 所选择的id
	         $('#saveDialogButton').linkbutton('disable');
		     var startDate = $('#batchStartDate').datebox('getValue');
		     var endDate = $('#batchEndDate').datebox('getValue');
			 if(!document.getElementById("checkboxEndDate").checked){
				  endDate  = null;
	    	 }else{
	    	      
	    	 }
			$.ajax({
				url:sybp()+'/tblSOLeaderAction_saveBatchSetCheck.action',
				type:'post',
				data:{
					batchLeaderAndTaskName:getSelections,
			  		scheduleIDs:getSelections2,
			  		startDate:startDate,
			  		endDate:endDate
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						//批量保存
						saveBatchSet(getSelections,getSelections2,startDate,endDate)
					}else if( r ){
						$.messager.defaults = { ok: "是", cancel: "否", width:900 };
						$.messager.confirm('确认',r.msg,function(r){    
						    if (r){    
								//批量保存
								saveBatchSet(getSelections,getSelections2,startDate,endDate)
						    }else{
						      $('#saveDialogButton').linkbutton('enable');
						    }    
						}); 
					}
				}
			});
	   }else{
	       $.messager.alert('消息','请选择任务负责人！','info');
	   }
    }

	//批量保存
	function saveBatchSet(getSelections,getSelections2,startDate,endDate){
		$.ajax({
  		  	url : sybp()+'/tblSOLeaderAction_saveBatchSetAction.action',
  		  	type: 'post',
  		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
  		  	data: {
  		  		batchLeaderAndTaskName:getSelections,
  		  		scheduleIDs:getSelections2,
  		  		startDate:startDate,
  		  		endDate:endDate
  		  	},
  		  	dataType:'json',
  		  	success:function(r){
      		  	if(r && r.success){
      		  	        $.messager.defaults = { ok: "是", cancel: "否" }; 
		                $.messager.confirm('确认','是否签字提交,将提交所选任务下所有任务操作者!',function(r){    
		                		  if (r){
		                			 qm_showQianmingDialog('afterSignTOLLeader');
		                		  }else{
		                		    $('#saveDialogButton').linkbutton('enable');
		                		  }    
		                }); 
      		  	        $('#addBatchSetDialog').dialog('close');
      		        	$('#tblSOLeaderTable').datagrid('reload');
      		  			parent.showMessager(1,'任命成功',true,5000);
      		  			$('#saveDialogButton').linkbutton('enable');
      		  			
      		  	}else{
      		  	     parent.showMessager(3,'与数据库交互异常',true,5000);
      		  	}
  		  			
  		  	}
   	 	});
	}
    
    
    //指定结束日期
	function checkboxEndDate(){
	      if(document.getElementById("checkboxEndDate").checked){
		     	document.getElementById("checkboxEndDate").checked = false;
			    $('#batchEndDate').datebox('setValue','');
			    $('#batchEndDate').datebox({'disabled':true});
		   }else{
			    document.getElementById("checkboxEndDate").checked = true;
			    var startDate = $('#batchStartDate').datebox('getValue');
			    $('#batchEndDate').datebox('setValue',startDate);
			    $('#batchEndDate').datebox({'disabled':false});
		   }
	
	}
	
	function afterCheckboxEndDate(obj){
	    if(obj.checked == true){
		         var startDate = $('#batchStartDate').datebox('getValue');
			     $('#batchEndDate').datebox('setValue',startDate);
		     	 $('#batchEndDate').datebox({'disabled':false});
		   }else{
		        
			     $('#batchEndDate').datebox('setValue', '');
		         $('#batchEndDate').datebox({'disabled':true});
		   }
	}
</script>
</head>
<body>
<s:hidden id="startime" name="startime"></s:hidden>
<s:hidden id="endtime" name="endtime"></s:hidden>
<s:hidden id="privilege" name="privilege"></s:hidden>
<s:hidden id="select" name="select" value = "true"></s:hidden>
	<div class="easyui-layout" fit="true" border="false" >
		<div  region="center" title="" style="width:99.8%;">   
 	 	<div  class="easyui-tabs" fit="true" border="false" >
			<div title="批量设置专题任务操作者" id="tabsDict1" border="false" style="overflow: hidden;">
			<table id="tblSOLeaderTable" ></table>
		</div>
	   </div>
	  </div> 
	</div>
	<div id="toolbar" style="display:none">
			  &nbsp;&nbsp;&nbsp;&nbsp; <a >日期范围 &nbsp;&nbsp; &nbsp;&nbsp;<input  style="width:100px;" id="startDate" type="text" name="startDate"  class="easyui-datebox"  data-options="editable:false" /></a>
			 <a>&nbsp;&nbsp;&nbsp;&nbsp;～&nbsp;&nbsp;&nbsp;&nbsp;<input  style="width:100px;" id="endDate" type="text"  name="endDate"  class="easyui-datebox"  data-options="editable:false" /></a>
			 <a id="selectDialogButton" class="easyui-linkbutton" onclick="saveSelectTime();" data-options="iconCls:'icon-search',plain:true">查询</a>
			 <a id="batchSetButton" class="easyui-linkbutton" onclick="batchSetButton();" data-options="iconCls:'icon-dateend',plain:true,disabled:true">根据常规任务负责人填充</a> 
			 <a id="signSOLLeaderButton" class="easyui-linkbutton" plain="true"
						onclick="onSignButton();" data-options="iconCls:'icon-edit',disabled:true">签字确认</a>
			 <a id="exitButton" class="easyui-linkbutton" plain="true"
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
    </div>
	 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	  <%@include file="/WEB-INF/jsp/tblSOLeaderAction/addBatchSetDiv.jspf" %>
</body>
</html>