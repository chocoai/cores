<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同付款记录</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/paymentRecordAddEdit.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
        var tableHeight;//当前页面可见区域高度 - 60
        var tableWidth;
        $(function(){
        	tableHeight = document.body.clientHeight - 28;
        	tableWidth  = document.body.clientWidth;
        	var contractCode=$('#currentContractCode').val();
//        	alert(contractCode);
        	$('#paymentRecordTable').datagrid({
    			url : sybp()+'/tblPaymentRecordAction_loadPaymentRecordList.action?contractCode='+contractCode,
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
    				width:150,
    				halign:'center',
    				align:'center',
    				hidden:true
    			},{
    				title:'合同编号',
    				field:'contractCode',
    				width:160,
    				halign:'center',
    				align:'left'
    			},{
    				title:'付款金额',
    				field:'amount',
    				width:100,
    				halign:'center',
    				align:'right'
    				
    			},{
    				title:'单位',
    				field:'priceUnit',
    				width:110,
    				halign:'center',
    				align:'left',
    				formatter: function(value,row,index){
	    				if ( value == 1 ){
	    					return "元";
	    				}else if(value == 2){
	    					return "美元";
	    				}else if(value == 3){
	    					return "欧元";
	    				}else{
	    					return "万元"
	        		    }
    				}
    			},{
    				title:'付款日期',
    				field:'paymentDate',
    				width:80,
    				halign:'center',
    				align:'left'
    			},{
    				title:'是否开具发票',
    				field:'receiptFlag',
    				width:80,
    				halign:'center',
    				align:'center',
    				formatter: function(value,row,index){
    				if ( value == 0 ){
    					return "是";
    				}else{
    					return "否";
    				}
    			}
    			},{
    				title:'操作者',
    				field:'operator',
    				width:80,
    				halign:'center',
    				align:'center'
    		    },{
    				title:'操作日期',
    				field:'operateTime',
    				width:80,
    				halign:'center',
    				align:'left'
    		    }]],
    		    onSelect:function(rowIndex, rowData){
    			     var falg = $('#addContract').val();
    			     if(falg == 0){
    			         $('#editPaymentRecordButton').linkbutton('disable');
	    				 $('#deletePaymentRecordButton').linkbutton('disable');
	    				 $('#addPaymentRecordButton').linkbutton('disable');
    			     }else{
    			        $('#editPaymentRecordButton').linkbutton('enable');
    				    $('#deletePaymentRecordButton').linkbutton('enable');
    				    $('#addPaymentRecordButton').linkbutton('enable'); 
    			     }
            		    
    			},
    			onLoadSuccess:function(data){
    			     var falg = $('#addContract').val();
    			     if(falg == 0){
    			         $('#editPaymentRecordButton').linkbutton('disable');
	    				 $('#deletePaymentRecordButton').linkbutton('disable');
	    				 $('#addPaymentRecordButton').linkbutton('disable');
    			     }else{
	    			     $('#editPaymentRecordButton').linkbutton('disable');
	    				 $('#deletePaymentRecordButton').linkbutton('disable');
	    				 $('#addPaymentRecordButton').linkbutton('enable');
	    				 var selectid = $('#prId').val();
	   				     if(selectid != ""){
	   				          for(var i = 0 ; i< data.rows.length;i++){
	   				             if(selectid == data.rows[i].id){
	   				            	$('#paymentRecordTable').datagrid('selectRow',i);
	   				          }
	   				       }
	   				     }
    			     }
    				    
    			},
    			toolbar:'#toolbar',
    			
    	   	});
        	$('#layoutMainDiv').css('display','');   
        });//匿名函数结束
      //添加按钮事件
        function onAddPaymentRecordButton(){
        	showPaymentRecordAddEditDialog('afterAddDialog','add','添加付款记录');
        }
//    	添加Dialog后事件
        function afterAddDialog(){
           parent.parent.showMessager(1,'添加成功',true,5000);
           $('#paymentRecordTable').datagrid('reload');
        }
      //编辑按钮事件
        function onEditButton(){
        	showPaymentRecordAddEditDialog('afterEditDialog','edit','编辑付款记录');
        }
      //  编辑Dialog后事件
        function afterEditDialog(){
        	 parent.parent.showMessager(1,'编辑成功',true,5000);
        	$('#paymentRecordTable').datagrid('reload');
           
        }
        //删除按钮事件
        function onDeleteButton(){
        	var row = $('#paymentRecordTable').datagrid('getSelected');
            if(null != row ){
              	$.messager.confirm('确认对话框', '确认删除此付款记录？', function(r){
     				if (r){
     					delPaymentRecord(row.id);
     				}
     			});
            }else{
               $.messager.alert('提示','请选择待删除的付款记录!');
            }
        }
      	//根据收款记录Id删除相应记录
        function delPaymentRecord(id){
    	   	$.ajax({
    	   		url : sybp()+'/tblPaymentRecordAction_delete.action?id='+id,
    	   		type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					 parent.parent.showMessager(1,'删除成功',true,5000);
    					 $('#paymentRecordTable').datagrid('reload');
    				}else{
    					parent.parent.showMessager(2,'删除失败',true,5000);
    				}
    			}
    		});
        }
</script>
</head>
<body >
<s:hidden id="prId" name="paymentRecordId"></s:hidden>
<s:hidden id="currentContractCode" name="contractCode"></s:hidden>
<!-- 是否只读 -->
<s:hidden id="addContract" name="addContract"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%;left:-1px;top:-1px; display:none;"> 
	<div region="center" title="" style="overflow: hidden;">
		<table id="paymentRecordTable"></table>
	</div>
	<div id="toolbar">
		<a id="addPaymentRecordButton" class="easyui-linkbutton" onclick="onAddPaymentRecordButton();" data-options="iconCls:'icon-tableadd',plain:true,disabled:true">添加收款记录</a>
		<a id="editPaymentRecordButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-tableedit2',plain:true,disabled:true">编辑收款记录</a>
		<a id="deletePaymentRecordButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-tabledelete',plain:true,disabled:true">删除收款记录</a> 
	</div>
     <%@ include file="/WEB-INF/jsp/tblPaymentRecordAction/paymentRecordAddEdit.jspf"%>
</div>
</body>
</html>
