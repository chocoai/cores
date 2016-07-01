<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	var studyNoPara;//专题编号
	var studydirector;//课题负责人
	var pathDirector;//专题病理负责人
	var animalType;//动物种类
	var reqId;
    $(function(){
    	tableHeight = document.body.clientHeight - 30;
		tableWidth  = document.body.clientWidth;
    	studyNoPara = $('#studyNoPara').val();
    	pathDirector= $('#pathDirector').val();
    	reqId = $('#reqId').val();
//    	studyState = $('#studyState').val();
//    	if(studyState>0){
//    		$('#editPathPlanCheckButton').linkbutton('disable');
//			$('#editPathPlanVisceraWeighButton').linkbutton('disable');
//    	}
    	//初始化病理计划-脏器/组织学检查表
        $('#anatomyReqable').datagrid({
        	url:sybp()+'/tblAnatomyReqAction_loadList.action?studyNoPara='+encodeURIComponent(studyNoPara),
        	title:'',
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
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
				formatter: function(value,row,index){
				  return index+1;
		        } 
			},{
				title:'申请编号',
				field:'reqNo',
				width:80,
				halign:'center',
				align:'left',
				hidden:true
			},{
				title:'解剖开始日期',
				field:'beginDate',
				width:80,
				halign:'center',
				align:'left'
			},{
				title:'解剖结束日期',
				field:'endDate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'解剖原因',
				field:'anatomyRsn',
				width:80,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 1 ){
						return "计划解剖";
					}else if( value == 2 ){
						return "濒死解剖";
					}else if( value == 3 ){
						return "死亡解剖";
					}else if( value == 4 ){
						return "安乐死解剖";
					}
			    } 
			},{
				title:'动物数量',
				field:'animalNumber',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'创建人',
				field:'author',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'创建日期',
				field:'createTime',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'是否临时',
				field:'tempFlag',
				width:80,
				halign:'center',
				align:'center',
				hidden:false,
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "否";
					}else if( value == 1 ){
						return "是";
					}else if( value == 2 ){
						return "是(已确认)";
					}
		        }
			},{
				title:'申请状态',
				field:'submitFlag',
				width:80,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
				if ( value == 0 ){
					return "未提交";
				}else if( value == 1 ){
					return "提交";
				}else if( value == 2 ){
					return "变更中";
				}else if(value==3){
					return "数据已确认";
				}else if(value==-1){
					return "已撤销";
				}else{
					return "临时申请";
				}
		    }
			},{
				title:'状态',
				field:'state',
				width:80,
				halign:'center',
				align:'center',
				hidden:true,
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "";
					}else if( value == 1 ){
						return "";
					}else{
						return "";
					}
			    }
			}
			]],
			toolbar:'#toolbar',
			onLoadSuccess:function(data){
	             $('#addAnatomyReq').linkbutton('enable');
	             $('#editAnatomyReqButton').linkbutton('disable');
	             $('#deleteAnatomyReqButton').linkbutton('disable');
	             $('#submitAnatomyReqButton').linkbutton('disable');
	             $('#changeAnatomyReqButton').linkbutton('disable');

	             $('#confirmTempReqButton').linkbutton('disable');
	             $('#viewAnatomyReqButton').linkbutton('disable');
	             $('#viewAnatomyReqDetailButton').linkbutton('disable');
	             $('#printButton_list').linkbutton('disable');
//	             var reqId= $('#reqId').val();
	             if(reqId != ""){
				          for(var i = 0 ; i< data.rows.length;i++){
				             if(reqId == data.rows[i].id){
				            	$('#anatomyReqable').datagrid('selectRow',i);
				             }
				          }
				 }
			},
			onSelect:function(rowIndex, rowData){
				 $('#printButton_list').linkbutton('enable');
				 //撤销
				 $('#cancelAnatomyReqButton').linkbutton('disable');
				 $('#changeAnatomyReqButton').linkbutton('disable');
				if(rowData.submitFlag==0){
					$('#editAnatomyReqButton').linkbutton('enable');
		            $('#deleteAnatomyReqButton').linkbutton('enable');
		            $('#submitAnatomyReqButton').linkbutton('enable');
		            $('#viewAnatomyReqDetailButton').linkbutton('disable');
				}else if(rowData.submitFlag==1){
					$('#editAnatomyReqButton').linkbutton('disable');
		            $('#deleteAnatomyReqButton').linkbutton('disable');
		            $('#submitAnatomyReqButton').linkbutton('disable');
		            $('#viewAnatomyReqDetailButton').linkbutton('disable');
		            //撤销
		            $('#cancelAnatomyReqButton').linkbutton('enable');
		            $('#changeAnatomyReqButton').linkbutton('enable');
				}else if(rowData.submitFlag==3){
				    $('#viewAnatomyReqDetailButton').linkbutton('enable');
				}else if(rowData.submitFlag==-1){
					$('#editAnatomyReqButton').linkbutton('disable');
		            $('#deleteAnatomyReqButton').linkbutton('disable');
		            $('#submitAnatomyReqButton').linkbutton('disable');
		            $('#viewAnatomyReqDetailButton').linkbutton('disable');
					$('#printButton_list').linkbutton('disable');
				}else if(rowData.submitFlag == 2){
					$('#editAnatomyReqButton').linkbutton('enable');
		            $('#submitAnatomyReqButton').linkbutton('enable');
				}
				
				if(rowData.tempFlag==1){
					$('#confirmTempReqButton').linkbutton('enable');
				}else{
					$('#confirmTempReqButton').linkbutton('disable');
				}
				$('#addAnatomyReq').linkbutton('enable');
	            $('#viewAnatomyReqButton').linkbutton('enable');
	           
		    },
         });
        
        
    	$('#layoutMainDiv').css('display','');
     });//匿名函数结束
     //添加按钮事件
	function onAddAnatomyReqButton(){
		window.location.href=sybp()+'/tblAnatomyReqAction_toAddEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&addOrEdit='+'add';
    }
    //编辑按钮事件
    function onEditAnatomyReqButton(){
        var row=$('#anatomyReqable').datagrid('getSelected');
    	window.location.href=sybp()+'/tblAnatomyReqAction_toAddEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)
    	+'&addOrEdit='+'edit'+'&reqNo='+row.reqNo+'&reqId='+row.id+'&submitFlag='+row.submitFlag;
    }
    //删除按钮事件
    function onDeleteAnatomyReqButton(){
        var row=$('#anatomyReqable').datagrid('getSelected');
        if(row!=null){
        	$.messager.confirm('确认对话框', '确认删除此申请？', function(r){
 				if (r){
 					$.ajax({
 			    		url:sybp()+'/tblAnatomyReqAction_delete.action?studyNoPara='+encodeURIComponent(studyNoPara),
 			    		type:'post',
 			    		cache:false,
 			    		data:{
 						 id:row.id
 			    		},
 			    		dataType:'json',
 			  	    		success:function(r){
 			  	    			if(r){
 			  	    				$('#anatomyReqable').datagrid('reload');
 			  	    				parent.parent.showMessager(1,'删除成功',true,5000);
 			  	    				
 			  	    			}
 			  	    		}
 			  	    	});
 				}
 			});
        }
    }
    //提交按钮事件
    function onSubmitAnatomyReqButton(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	if(row!=null){
    		$.ajax({
        		url:sybp()+'/tblAnatomyReqAnimalListAction_submitCheck.action?studyNo='+encodeURIComponent(studyNoPara)+'&anatomyReqNo='+row.reqNo,
        		type:'post',
        		cache:false,
        		data:{
        		},
        		dataType:'json',
      	    		success:function(r){
      	    			if(r){
    	  	    			if(r.haveReqAnimalNumber>0){
    	  	    				parent.parent.showMessager(2,'已有动物被提交申请解剖,请重新编辑',true,5000);
    	  	    				window.location.href=sybp()+'/tblAnatomyReqAction_toAddEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&addOrEdit='+'edit'+'&reqNo='+row.reqNo+'&reqId='+row.id;
    	  	    				return;
    		  	    		}else{
    		  	    			$.messager.confirm('确认对话框', '确认提交此申请？', function(r){
    		  	   				if (r){
    		  	   					qm_showQianmingDialog('eventAfterSubmit');
    		  	   					
    		  	   				}
    		  	   			});
        		  	    	}
      	    			}
      	    		}
      	    	});
        }
    	
    }
    //变更按钮事件
    function onChangeAnatomyReqButton(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	if(row!=null){
    		$.ajax({
        		url:sybp()+'/tblAnatomyReqAction_changeCheck.action?studyNo='+encodeURIComponent(studyNoPara)+'&reqNo='+row.reqNo,
        		type:'post',
        		cache:false,
        		data:{
        		},
        		dataType:'json',
      	    		success:function(r){
      	    			if(r){
    	  	    			if(!r.success){
    	  	    				parent.parent.showMessager(2,'该申请对应解剖任务已解剖完成，不可以变更！',true,5000);
    		  	    		}else{
	    		  	   			qm_showQianmingDialog('eventAfterChange');
        		  	    	}
      	    			}
      	    		}
      	    	});
        }
    	
    }
    //更新
    function eventAfterChange(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	$.ajax({
	    		url:sybp()+'/tblAnatomyReqAction_change.action',
	    		type:'post',
	    		cache:false,
	    		data:{
				 id:row.id
	    		},
	    		dataType:'json',
	  	    		success:function(r){
	  	    			if(r){
		  	    			if(r.success){
			  	    			reqId=r.reqId;
		  	    				$('#anatomyReqable').datagrid('reload');
		  	    				parent.parent.showMessager(1,'变更状态设置成功',true,5000);
			  	    		}else{
			  	    			parent.parent.showMessager(2,'与服务器交互错误！',true,5000);
				  	    	}
	  	    			}
	  	    		}
	  	    	});
    }
    //撤销按钮事件
    function onCancelAnatomyReqButton(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	if(row!=null){
    		$.ajax({
        		url:sybp()+'/tblAnatomyReqAction_cancelCheck.action?studyNo='+encodeURIComponent(studyNoPara)+'&reqNo='+row.reqNo,
        		type:'post',
        		cache:false,
        		data:{
        		},
        		dataType:'json',
      	    		success:function(r){
      	    			if(r){
    	  	    			if(!r.success){
    	  	    				parent.parent.showMessager(2,'该申请对应解剖任务已创建，不可以撤销！',true,5000);
    		  	    		}else{
    		  	    			$.messager.confirm('确认对话框', '确定撤销此申请？', function(r){
	    		  	   				if (r){
	    		  	   					qm_showQianmingDialog('eventAfterCancel');
	    		  	   					
	    		  	   				}
	    		  	   			});
        		  	    	}
      	    			}
      	    		}
      	    	});
        }
    	
    }
    //签名后撤销至数据库
    function eventAfterCancel(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	$.ajax({
	    		url:sybp()+'/tblAnatomyReqAction_cancel.action',
	    		type:'post',
	    		cache:false,
	    		data:{
				 id:row.id
	    		},
	    		dataType:'json',
	  	    		success:function(r){
	  	    			if(r){
		  	    			reqId=r.reqId;
	  	    				$('#anatomyReqable').datagrid('reload');
	  	    				parent.parent.showMessager(1,'撤销成功',true,5000);
	  	    			}
	  	    		}
	  	    	});
    }
    
    //确认临时申请
    function onConfirmTempReqButton()
    {
		qm_showQianmingDialog('afterSignOnConfirmTempReqButton');
    }
    function afterSignOnConfirmTempReqButton()
    {
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	$.ajax({
	    		url:sybp()+'/tblAnatomyReqAction_confirmTempReq.action',
	    		type:'post',
	    		cache:false,
	    		data:{
				 id:row.id
	    		},
	    		dataType:'json',
	  	    		success:function(r){
	  	    			if(r){
		  	    			reqId=r.reqId;
	  	    				$('#anatomyReqable').datagrid('reload');
	  	    				parent.parent.showMessager(1,'确认临时申请成功',true,5000);
	  	    			}
	  	    		}
	  	    	});
    }
    //签名后提交至数据库
    function eventAfterSubmit(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	$.ajax({
	    		url:sybp()+'/tblAnatomyReqAction_submit.action',
	    		type:'post',
	    		cache:false,
	    		data:{
				 id:row.id
	    		},
	    		dataType:'json',
	  	    		success:function(r){
	  	    			if(r){
		  	    			reqId=r.reqId;
	  	    				$('#anatomyReqable').datagrid('reload');
	  	    				parent.parent.showMessager(1,'提交成功',true,5000);
	  	    			}
	  	    		}
	  	    	});
    }
    //查看数据按钮事件
    function onViewAnatomyReqButton(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	window.location.href=sybp()+'/tblAnatomyReqAction_toAddEdit.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&addOrEdit='+'view'+'&reqNo='+row.reqNo+'&reqId='+row.id;
    }
    //数据浏览按钮事件
    function onViewAnatomyReqDetailButton(){
    	var row=$('#anatomyReqable').datagrid('getSelected');
    	window.location.href=sybp()+'/tblAnatomyReqAction_toCheckDetail.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+row.reqNo+'&reqId='+row.id+'&beginDate='+row.beginDate+'&createTime='+row.createTime;
    }

    //打印按钮事件
    function onPrintButton(){
        var row=$('#anatomyReqable').datagrid('getSelected');
        if(row && row.state == 1){
	    	$.messager.defaults = { ok: "打印变更后申请", cancel: "打印变更前申请" };
	    	$.messager.confirm('确认对话框', '请选择打印变更前解剖申请或打印变更后解剖申请？', function(r){
		    	parent.parent.showMessager(2,'数据加载中...',true,5000);
				if(r){
			    	window.location.href=sybp()+'/tblAnatomyReqAction_toReport.action?studyNoPara='+studyNoPara+'&reqNo='+row.reqNo+'&reqId='+row.id;
				}else{
					window.location.href=sybp()+'/tblAnatomyReqAction_toReport.action?studyNoPara='+studyNoPara+'&reqNo='+row.reqNo+'&reqId='+row.id+'&printHis='+1;
				}
			});
	    	$.messager.defaults = { ok: "确定", cancel: "取消" };
        }else{
	    	parent.parent.showMessager(2,'数据加载中...',true,5000);
	    	window.location.href=sybp()+'/tblAnatomyReqAction_toReport.action?studyNoPara='+studyNoPara+'&reqNo='+row.reqNo+'&reqId='+row.id;
        }
    }
</script>
</head>
<body>
      <!-- 课题编号 -->
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <!-- 课题负责人 -->
    <s:hidden id="studydirector" name="studydirector"></s:hidden>
    <!-- 专题病理负责人 -->
    <s:hidden id="pathDirector" name="pathDirector"></s:hidden>
    <!-- 动物种类 -->
    <s:hidden id="animalType" name="animalType"></s:hidden>
    <!-- 申请ID -->
    <s:hidden id="reqId" name="reqId"></s:hidden>
    <div id="layoutMainDiv" style="width:100%;height:100%; display:none; overflow: hidden;"> 
			<div  border="false" style="overflow: auto;">
				<table id="anatomyReqable" ></table>
            </div>
	        <div id="toolbar">
				<a id="addAnatomyReq" class="easyui-linkbutton"  onclick="onAddAnatomyReqButton();" data-options="iconCls:'icon-tableadd',plain:true,disabled:true">新建</a>
				<a id="editAnatomyReqButton" class="easyui-linkbutton" onclick="onEditAnatomyReqButton();" data-options="iconCls:'icon-tableedit2',plain:true" disabled="true">编辑</a>
				<a id="deleteAnatomyReqButton" class="easyui-linkbutton" onclick="onDeleteAnatomyReqButton()" data-options="iconCls:'icon-tabledelete',plain:true,disabled:true">删除</a>
				<a id="submitAnatomyReqButton" class="easyui-linkbutton" onclick="onSubmitAnatomyReqButton()" data-options="iconCls:'icon-tablego',plain:true,disabled:true">提交</a>
				
				<a id="changeAnatomyReqButton" class="easyui-linkbutton" onclick="onChangeAnatomyReqButton()" data-options="iconCls:'icon-tablerefresh',plain:true,disabled:true">变更</a>
				
				<a id="cancelAnatomyReqButton" class="easyui-linkbutton" onclick="onCancelAnatomyReqButton()" data-options="iconCls:'icon-undo',plain:true,disabled:true">撤销</a>
				<a id="confirmTempReqButton" class="easyui-linkbutton" onclick="onConfirmTempReqButton()" data-options="iconCls:'icon-confirm',plain:true,disabled:true">确认临时申请</a>
				<a id="viewAnatomyReqButton" class="easyui-linkbutton" onclick="onViewAnatomyReqButton()" data-options="iconCls:'icon-view',plain:true,disabled:true">查看</a>
				<a id="viewAnatomyReqDetailButton" class="easyui-linkbutton" onclick="onViewAnatomyReqDetailButton()" data-options="iconCls:'icon-view',plain:true,disabled:true">数据浏览</a>
				<a id="printButton_list"  class="easyui-linkbutton" onclick="onPrintButton()" data-options="iconCls:'icon-print',plain:true">打印</a>
			</div>
			<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
   </div>
</body>
</html>
