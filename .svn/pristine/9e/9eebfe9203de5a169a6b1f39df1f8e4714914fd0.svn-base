<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>

<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/selectCustomer.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/customerAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/customerAddEdit.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/contractAddEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblIntegratedInformAction/tblIntegratedInformAction.js" charset="utf-8"></script>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/contractAddEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/attachmentAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/tblTestItem.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTestItem.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/selectCustomer.js"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
	    var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		var tiCode = $('#tiCode').val();
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
	    var  selectContractCode=  $('#selectContractCode').val();
   
	   
	   $('#searchContract').searchbox({ 
	    height:19,
	    width:450,
		searcher:function(value,name){ 
		 var startTime =  $('#mindatebox').datebox('getValue');
		 var endTime = $('#maxdatebox').datebox('getValue');
		 $('#startTime').val(startTime);
		 $('#endTime').val(endTime);
		 var code = $('#studyType_testItemAndNo').combobox('getValue');
         $('#selectContractCode').val(value);
         showContractTable(startTime,endTime,code,value);
         parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,code);
		}, 
		prompt:'模糊查询,请输入合同编号、名称、委托方名称、联系人、电话、手机' 
		}); 
	   
	    
	    $('#studyType_testItemAndNo').combobox({    
		   onChange:function(newValue, oldValue){
		    var startTime =  $('#mindatebox').datebox('getValue');
		    var endTime = $('#maxdatebox').datebox('getValue');
		    $('#startTime').val(startTime);
		    $('#endTime').val(endTime);
		    if(newValue == '-1'){
			    newValue='';
		    }
		   parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,newValue);
		   selectCustomerName = $('#searchContract').searchbox('getValue');
		   showContractTable(startTime,endTime,newValue,selectContractCode);
		   }
		});  
	    
	    
	    
	    
	    $('#mindatebox').datebox({    
		    required:true,
		     onSelect: function(date){
			        var startTime =  $('#mindatebox').datebox('getValue');
		            var endTime = $('#maxdatebox').datebox('getValue');
		            $('#startTime').val(startTime);
		            $('#endTime').val(endTime);
		            var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		            selectCustomerName = $('#searchContract').searchbox('getValue');
		            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
		            showContractTable(startTime,endTime,tiCode,selectContractCode);
			  }
		    
		});  
		 $('#maxdatebox').datebox({    
		    required:true,
		     onSelect: function(date){
			        var startTime =  $('#mindatebox').datebox('getValue');
		            var endTime = $('#maxdatebox').datebox('getValue');
		            $('#startTime').val(startTime);
		            $('#endTime').val(endTime);
		            var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		            selectCustomerName = $('#searchContract').searchbox('getValue');
		            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
		            showContractTable(startTime,endTime,tiCode,selectContractCode);
			  }
		    
		});  
		    initTestItemAndNOCombobox();
	     	initstartTimeAndEndTimeAndtiCode();
	       showContractTable(startDate,endDate,tiCode,selectContractCode);
	   var pager = $('#tblContractTable').datagrid('getPager');    // get the pager of datagrid
		var read = $('#read').val();
	    if(read == "true"){
	         pager.pagination({
		        showPageList:false,
		        showRefresh:false,
		        loading:true,
		        displayMsg:	' 共 &nbsp;&nbsp;{total}&nbsp;&nbsp;条记录',
		        beforePageText:'',
		        afterPageText:'',
		    });
	    }else{
	        pager.pagination({
		        showPageList:false,
		        showRefresh:false,
		        loading:true,
		        displayMsg:	'(&nbsp;&nbsp;仅显示当前用户登记的合同信息&nbsp;&nbsp;)&nbsp;&nbsp;  共&nbsp;&nbsp;{total}&nbsp;&nbsp;条记录',
		        beforePageText:'',
		        afterPageText:'',
		    });
	    }
	
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
   
    //显示tblContractTable
    function  showContractTable(startDate,endDate,tiCode,selectContractCode){
        	$('#tblContractTable').datagrid({
			url : sybp()+'/tblIntegratedInformAction_loadcontractList.action?startDate='
			+startDate+'&endDate='+endDate+'&tiCode='+tiCode+'&contractCode='+encodeURIComponent(selectContractCode),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'sponsorId',
				field:'sponsorId',
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'合同编号',
				field:'contractCode',
				width:120,
				halign:'center',
				align:'left'
			},{
				title:'合同名称',
				field:'contractName',
				width:200,
				halign:'center',
				align:'left'
			},{
				title:'委托方名称',
				field:'sponsorName',
				width:160,
				halign:'center',
				align:'left'
				
			},{
				title:'委托方地址',
				field:'sponsorAddress',
				width:210,
				halign:'center',
				align:'left'
			},{
				title:'联系人',
				field:'sponsorLinkman',
				width:56,
				halign:'center',
				align:'left'
			},{
				title:'电话',
				field:'sponsorTel',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'手机',
				field:'sponsorMobile',
				width:85,
				halign:'center',
				align:'left'
		    },{
				title:'Email',
				field:'sponsorEmail',
				width:150,
				halign:'center',
				align:'left'
		    },{
				title:'传真',
				field:'sponsorFax',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'合同金额',
				field:'contractPrice',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'开始日期',
				field:'effectiveDate',
				width:80,
				halign:'center',
				align:'center'
		    },{
				title:'结束日期',
				field:'finishDate',
				width:80,
				halign:'center',
				align:'center'
		    },{
				title:'合同状态',
				field:'contractState',
				width:60,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
				if ( value == "0" ){
					return "未生效";
				}else if(value=="1"){
                    return "执行中";
				}else if(value=="2"){
                    return "可编辑";
				}else if(value=="3"){
                    return "完成";
				}else if(value=="-1"){
                    return "终止";
				}  
			}},{
				title:'操作者',
				field:'operator',
				width:56,
				halign:'center',
				align:'left'
		    },{
				title:'供试品数量',
				field:'countTestItem',
				width:70,
				halign:'center',
				align:'center'
		    },{
				title:'委托项目数量',
				field:'countstudyItem',
				width:85,
				halign:'center',
				align:'center'
		    },{
				title:'供试品类型',
				field:'tiCode',
				width:95,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == "01" ){
						return "医药";
					}else if(value=="02"){
	                    return "农药";
					}else if(value == "03"){
						return "化学品";
					}else{
						   return "";
					}
				},
				hidden:true
			},{
				title:'备注',
				field:'remark',
				width:200,
				halign:'center',
				align:'left'
		    }]],
			onSelect:function(rowIndex, rowData){
			   $('#selectContractButton').linkbutton('enable'); 
			  // $('#editContractButton').linkbutton('disable');
			  // $('#contractAttachmentButton').linkbutton('disable');
			  // $('#addTestItemButton').linkbutton('disable');
			  // $('contractAgainEditButton').linkbutton('disable');
			  // accessControl(rowData.contractCode);
			   
			},
			onLoadSuccess:function(data){
			    $('#selectContractButton').linkbutton('disable'); 
			   // $('#editContractButton').linkbutton('disable');
			   // $('#contractAttachmentButton').linkbutton('disable');
			   // $('#addTestItemButton').linkbutton('disable');
			    // var selectid = $('#selectid').val();
			    // if(selectid != ""){
			    //   for(var i = 0 ; i< data.rows.length;i++){
			    //      if(selectid == data.rows[i].id){
			    //        	$('#tblContractTable').datagrid('selectRow',i);
			    //      }
			    //   }
			   //  }

			     if(data){
				    	$('#tblContractTable').datagrid('selectRow',0);
					}
			 
			},
			toolbar:'#toolbar',
			
	   	}); //end datagrid
    }
   
   
    function accessControl(id){
        $.ajax({
			   url:sybp()+'/tblIntegratedInformAction_accessControl.action',
			   type:'post',
			   data:{id:id},
			   dataType:'json',
			   success:function(r){
			       if(r && r.success){
			         if(r.start == 2){
						document.getElementById('contractCode').disabled = true;
					}else{
						document.getElementById('contractCode').disabled = false;
					}
			        
			         if(r.start == 0 ){
			            if(r.name){
			             $('#editContractButton').linkbutton('enable');
			             $('contractAgainEditButton').linkbutton('disable');
			             $('#contractAttachmentButton').linkbutton('enable')
			              $('#addTestItemButton').linkbutton('enable');
			            }else{
			              $('#editContractButton').linkbutton('disable');
			              $('contractAgainEditButton').linkbutton('disable');
			              $('#contractAttachmentButton').linkbutton('disable')
			               $('#addTestItemButton').linkbutton('disable');
			            }
			         }else if(r.start == 1 ){
			            if(r.name){
			             $('#editContractButton').linkbutton('disable');
			             if(r.write){
			               $('#contractAgainEditButton').linkbutton('enable');
			             }else{
			               $('#contractAgainEditButton').linkbutton('disable');
			             }
			             $('#contractAttachmentButton').linkbutton('disable')
			             $('#addTestItemButton').linkbutton('disable');
			            }else{
			              $('#editContractButton').linkbutton('disable');
			              if(r.write){
			                $('#contractAgainEditButton').linkbutton('enable');
			              }else{
			                $('#contractAgainEditButton').linkbutton('disable');
			              }
			              $('#contractAttachmentButton').linkbutton('disable')
			              $('#addTestItemButton').linkbutton('disable');
			            }
			         }else if(r.start == 2){
			           if(r.name){
			               $('#editContractButton').linkbutton('enable');
			               $('#contractAgainEditButton').linkbutton('disable');
			               $('#contractAttachmentButton').linkbutton('enable')
			               $('#addTestItemButton').linkbutton('enable');
			           }else{
			               if(r.write){
			                $('#editContractButton').linkbutton('enable');
			                $('#contractAgainEditButton').linkbutton('disable');
			                $('#contractAttachmentButton').linkbutton('enable')
			                $('#addTestItemButton').linkbutton('enable');
			               }else{
			                $('#editContractButton').linkbutton('disable');
			                $('#contractAgainEditButton').linkbutton('disable');
			                $('#contractAttachmentButton').linkbutton('disable')
			                $('#addTestItemButton').linkbutton('disable');
			               }
			           }
			         }else if(r.start == 3 || r.start == -1 ){
			              $('#editContractButton').linkbutton('disable');
			              $('#contractAgainEditButton').linkbutton('disable');
			              $('#contractAttachmentButton').linkbutton('disable')
			              $('#addTestItemButton').linkbutton('disable');
			         }
			         
			         
			       }
			   }
			   });
    }
    
    
    //初始化 供试品类型 开始日期 结束日期
    function  initstartTimeAndEndTimeAndtiCode(){
        var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		var tiCode = $('#tiCode').val();
		 if(tiCode && tiCode != ""){
	       $('#studyType_testItemAndNo').combobox('select', tiCode);
		 }else{
		    $('#studyType_testItemAndNo').combobox('select', "");
		 }
		$('#mindatebox').datebox('setValue', startDate);
		$('#maxdatebox').datebox('setValue', endDate);
    }

   /**查看合同*/
	function onViewContract(){
		 var row= $('#tblContractTable').datagrid('getSelected');
        if(row != null ){
			  parent.window.frames["left"].frames["left1"].updateCodeCombobox(row.contractCode);
        }else{
           $.messager.alert('提示','请选择查看的合同!');
        }
	}
	
	/**编辑合同*/
	function onEditContractButton(){
		 var row= $('#tblContractTable').datagrid('getSelected');
	        if(row != null ){
	                $.ajax({
			        	url:sybp()+'/tblContractAction_editUI.action',
						data:{
			    			currentId:row.id
						},
						dataType:'json',
						success:function(r){
							if(r){
								fullEditData(r);
						     	showContractAddEditDialog('afterEditDialog','edit','编辑合同');
							}
						}
			        });
	        }else{
	           $.messager.alert('提示','请选择编辑的合同!');
	        }
	}
	//合同登记(edit)Dialog后事件
    function afterEditDialog(){
       parent.showMessager(1,'合同编辑成功',true,5000);
      // parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#contractCode').val());
      	$('#tblContractTable').datagrid('reload');
        $('#selectid').val($('#id').val());
    }
    //合同附件
	function onContractAttachmentButton(){
	     var row= $('#tblContractTable').datagrid('getSelected');
		showAttachementAddEditDialog('afterOnContractAttachmentButton','add','合同附件上传');
		$('#attachment_contractCode').val(row.contractCode);
	}
    //合同上传附件
    function afterOnContractAttachmentButton(){
		 parent.showMessager(1,'合同附件上传成功',true,5000);
		  var row= $('#tblContractTable').datagrid('getSelected');
		 window.location.href = sybp()+'/tblContractAction_main.action?currentId='+row.id;
		//parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#currentContractCode').val());
		 parent.window.frames["left"].frames["left1"].updateCodeCombobox(row.contractCode);
	}
	 //添加供试品  
    function onAddTestItemButton(){
       showTestItemAddEditDialog('afterSelectTestItemAddDialog','selectadd','添加供试品');
    }
    function afterSelectTestItemAddDialog(){
        parent.showMessager(1,'供试品添加成功',true,5000);
		  var row= $('#tblContractTable').datagrid('getSelected');
		 parent.window.frames["left"].frames["left1"].updateCodeCombobox(row.contractCode);
    }
    //再编辑
	function onAgainEditButton(){
			qm_showQianmingDialog('againEdit');
	}
	//真正再编辑
	function againEdit(){
	var row= $('#tblContractTable').datagrid('getSelected');
		$.ajax({
	    	url:sybp()+'/tblContractAction_againEdit.action',
			data:{
				currentId:row.id
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					 parent.showMessager(1,'合同置为可编辑',true,5000);
					 window.location.href = sybp()+'/tblContractAction_main.action?currentId='+row.id;
				}else{
					$.messager.alert('警告',r.msg);
				}
			}
	    });
	}
</script>
</head>
<body>
<s:hidden id="selectid" ></s:hidden>
<!-- 开始时间 -->
<s:hidden id="startTime" name="startTime"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endTime" name="endTime" ></s:hidden>
<!-- 供试品类型 -->
<s:hidden id="tiCode" name="tiCode" ></s:hidden>
<!-- 模糊查询合同编号 -->
<s:hidden id="selectContractCode"  ></s:hidden>
<!-- 模糊查询客户名 -->
<s:hidden id="selectCustomerName"  ></s:hidden>
<!-- 是否有查看全部的权限 -->
<s:hidden id="read"  name="read"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:99%; display:none;"> 
   <!--  
    <div region="north" title="" style="height:30px;">
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a class="easyui-linkbutton" onclick="onAddButton();"
	       				 data-options="iconCls:'icon-add',plain:true" >添加客户</a>  
  		</div>
  	</div>-->
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="合同信息查询" border="false" style="overflow: auto;">
			    <div style="height:30px;">
			       <div style="float:left; width:99%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;供试品类型
			    		<input id="studyType_testItemAndNo" class="easyui-combobox" style="height:19px;width:80px;"
			    		data-options="panelHeight:'auto'"/>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 合同日期范围
		                &nbsp;
			        	&nbsp;从&nbsp;&nbsp;<input id="mindatebox" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>	
			      	  	&nbsp;至&nbsp;&nbsp;<input id="maxdatebox" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>   	
			    		<!-- 搜索框 -->
			    		&nbsp;&nbsp;&nbsp;&nbsp;
			    		<span style="position:absolute; top:35px;">
			    		<input id="searchContract" ></input> 
			    		</span>
			    	</div>
			    
			    </div>
				<table id="tblContractTable" ></table>
            </div>
		</div>
        <div id="toolbar">
          			<a id="selectContractButton" class="easyui-linkbutton" onclick="onViewContract();" data-options="iconCls:'icon-tablemultiple',plain:true,disabled:true">查看合同</a>
          			<!-- 
          			<a id="editContractButton" class="easyui-linkbutton" plain="true" 
								onclick="onEditContractButton();" 
								data-options="iconCls:'icon-tableedit2',plain:true,disabled:true">编辑合同</a>
					<a id="contractAttachmentButton" class="easyui-linkbutton" 
								onclick="onContractAttachmentButton();" 
								data-options="iconCls:'icon-attach',plain:true,disabled:true">合同附件</a>
				   <a id="contractAgainEditButton" class="easyui-linkbutton" plain="true" 
								onclick="onAgainEditButton();" 
								data-options="iconCls:'icon-tablerefresh',plain:true,disabled:true"  >置为可编辑</a>
				   <a id="addTestItemButton" class="easyui-linkbutton"
								onclick="onAddTestItemButton();" 
								data-options="iconCls:'icon-tagblueadd',plain:true,disabled:true">添加供试品</a>
          			 -->
		</div>
 	</div>
 	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 	<%@ include file="/WEB-INF/jsp/tblContractAction/contractAddEdit.jspf"%>
 	 <%@include file="/WEB-INF/jsp/tblContractAction/attachmentAddEdit.jspf" %>
 	 <%@include file="/WEB-INF/jsp/tblContractAction/testItemAddorEdit.jspf" %>
 	 <%@ include file="/WEB-INF/jsp/tblCustomerAction/selectCustomer.jspf"%>
 	 <%@include file="/WEB-INF/jsp/tblCustomerAction/customerAddEdit.jspf" %>
 	 
</div>
</body>
</html>
