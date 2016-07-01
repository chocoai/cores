<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>委托单位管理</title>
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
<script type="text/javascript">
var tableHeight;//当前页面可见区域高度 - 30
var tableWidth;
$(function(){
    var startDate = $('#startTime').val();
	var endDate = $('#endTime').val();
	var tiCode = $('#tiCode').val();
	tableHeight = document.body.clientHeight - 60;
	tableWidth  = document.body.clientWidth;
    var  selectCustomerName=  $('#selectCustomerName').val();
   
  //搜索框
   $('#searchContract').searchbox({ 
	    height:19,
	    width:450,
		searcher:function(value,name){ 
			 startTime =  $('#mindatebox').datebox('getValue');
			 endTime = $('#maxdatebox').datebox('getValue');
			 $('#startTime').val(startTime);
			 $('#endTime').val(endTime);
			 tiCode = $('#studyType_testItemAndNo').combobox('getValue');
		     $('#selectCustomerName').val(value);
		     showCustomerTable(startTime,endTime,tiCode,value);
		     parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
		}, 
		prompt:'模糊查询,请输入客户名称、联系人、电话或手机' 
	}); 
  
    
    $('#studyType_testItemAndNo').combobox({    
		   onChange:function(newValue, oldValue){
			    startTime =  $('#mindatebox').datebox('getValue');
			    endTime = $('#maxdatebox').datebox('getValue');
			    $('#startTime').val(startTime);
			    $('#endTime').val(endTime);
			    if(newValue == '-1'){
				    newValue='';
			    }
			   parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,newValue);
			  // var  selectCustomerName=  $('#selectCustomerName').val();
			  selectCustomerName = $('#searchContract').searchbox('getValue');
			   showCustomerTable(startTime,endTime,newValue,selectCustomerName);
		   }
	});  
   
    $('#mindatebox').datebox({    
	    required:true,
	     onSelect: function(date){
		        startTime =  $('#mindatebox').datebox('getValue');
	            endTime = $('#maxdatebox').datebox('getValue');
	            $('#startTime').val(startTime);
	            $('#endTime').val(endTime);
	            tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
	            selectCustomerName = $('#searchContract').searchbox('getValue');
	            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
	            showCustomerTable(startTime,endTime,tiCode,selectCustomerName);
		  }
	    
	});  
	
	$('#maxdatebox').datebox({    
	    required:true,
	     onSelect: function(date){
		        startTime =  $('#mindatebox').datebox('getValue');
	            endTime = $('#maxdatebox').datebox('getValue');
	            $('#startTime').val(startTime);
	            $('#endTime').val(endTime);
	            tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
	            selectCustomerName = $('#searchContract').searchbox('getValue');
	            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
	            showCustomerTable(startTime,endTime,tiCode,selectCustomerName);
		  }
	    
	});  
    
	
	initTestItemAndNOCombobox();
   	initstartTimeAndEndTimeAndtiCode();
    showCustomerTable(startDate,endDate,tiCode,selectCustomerName);
    var pager = $('#tblCustomerTable').datagrid('getPager');    // get the pager of datagrid
    var read = $('#read').val();
    if(read == "true" ){
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
		//初始化datagrid
		function  showCustomerTable(startDate,endDate,tiCode,selectCustomerName){
        	$('#tblCustomerTable').datagrid({
			url : sybp()+'/tblIntegratedInformAction_loadCustomerList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode+'&sponsorName='+encodeURIComponent(selectCustomerName),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,			//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:true,		//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'客户名称',
				field:'customerName',
				width:160,
				halign:'center',
				align:'left'
			},{
				title:'地址',
				field:'address',
				width:210,
				halign:'center',
				align:'left'
			},{
				title:'联系人',
				field:'linkman',
				width:56,
				halign:'center',
				align:'left'
				
			},{
				title:'电话',
				field:'tel',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'手机',
				field:'mobile',
				width:85,
				halign:'center',
				align:'center'
			},{
				title:'邮箱',
				field:'email',
				width:120,
				halign:'center',
				align:'left'
		    },{
				title:'网址',
				field:'http',
				width:120,
				halign:'center',
				align:'left'
		    },{
				title:'传真',
				field:'fax',
				width:100,
				halign:'center',
				align:'center'
		    },{
				title:'邮编',
				field:'postalCode',
				width:50,
				halign:'center',
				align:'center'
		    },{
				title:'合同数量',
				field:'conts',
				width:60,
				halign:'center',
				align:'center',
				
		    },{
				title:'供试品数量',
				field:'cts',
				width:70,
				halign:'center',
				align:'center'
		    },{
				title:'委托项目数量',
				field:'cis',
				width:80,
				halign:'center',
				align:'center'
		    }]],
			onSelect:function(rowIndex, rowData){
        		  //  $('#editCustomerButton').linkbutton('enable');
        		var falg = $('#addContract').val();
			    if(falg == 0){
				    $('#addContractButton').linkbutton('disable');
			    }else{
				    $('#addContractButton').linkbutton('enable'); 
			    }
				    $('#selectContractButton').linkbutton('enable'); 
				    
			},
			onLoadSuccess:function(data){
				    $('#selectContractButton').linkbutton('disable');
				    $('#addContractButton').linkbutton('disable');
				    //var selectid = $('#cuid').val();
				    // if(selectid != ""){
				     //  for(var i = 0 ; i< data.rows.length;i++){
				      //    if(selectid == data.rows[i].id){
				        //    	$('#tblCustomerTable').datagrid('selectRow',i);
				        //  }
				      // }
				    // }
				    if(data){
				    	$('#tblCustomerTable').datagrid('selectRow',0);
					}
			},
			toolbar:'#toolbar',
			
	   	}); //end datagrid
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
   
	

    //wan 编辑
    function onEditButton(){
         var row= $('#tblCustomerTable').datagrid('getSelected');
        if(row != null ){
          	showCustomerAddEditDialog('afterEditDialog','edit','编辑客户信息');
        }else{
           	$.messager.alert('提示','请选择编辑的客户!');
        }
    }
   

     function afterEditDialog(){
//        var cid = $('#regionId').val();
//        var cuid = $('#cuid').val();
//          var row=$('#tblCustomerTable').datagrid('getSelected');
          parent.showMessager(1,'编辑成功',true,5000);
//        parent.parent.parent.afterAddCustomer(cid,cuid);
          $('#tblCustomerTable').datagrid('reload');
//          $('#tblCustomerTable1').datagrid('selectRecord',$('#cuid').val()); 
          //selectRow
     }

    function onDeleteButton(){
    	  var row= $('#tblCustomerTable').datagrid('getSelected');
          if(row != null ){
        	  qm_showQianmingDialog('afterDelFunction');
          }else{
             $.messager.alert('提示','请选择删除的客户!');
          }
    	
        
   }
       function afterDelFunction(){
    	     var row= $('#tblCustomerTable1').datagrid('getSelected');
	    	$.ajax({
				url:sybp()+'/tblCustomerAction_delete.action?id='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){
	    		  if(r && r.success){
	    			  parent.showMessager(1,'删除成功',true,5000);
	    		       var data = $('#tblCustomerTable').datagrid('getRows');
	    		       	  for(var i = 0;i<data.length;i++){
						    if(data[i].id == row.id){
						        $('#tblCustomerTable').datagrid('deleteRow',i);
						     }
						  }
		           }
	    	    }  
		    });
       }

	/**查看合同*/
	function onViewContract(){
		 var row= $('#tblCustomerTable').datagrid('getSelected');
        if(row != null ){
            $.ajax({
                url:sybp()+'/tblContractAction_checkCustomer.action?sponsorId='+row.id,
                dataType:'json',
                success:function(r){
					if(r && r.success){
			            parent.window.frames["left"].frames["left1"].updateNameCombobox(row.id,row.customerName);
					}else{
						$.messager.alert('提示',r.msg);
					}
                }
            });
        }else{
           $.messager.alert('提示','请选择编辑的客户!');
        }
	}
	/**添加合同*/
	function onAddContractButton(){
		 var row= $('#tblCustomerTable').datagrid('getSelected');
	        if(row != null ){
	                fullEditData();
	            	showContractAddEditDialog('afterAddContractDialog','add','登记合同');
	            	$('#sponsorId').val(row.id);
	                $('#sponsorName').val(row.customerName);
	        }else{
	           $.messager.alert('提示','请选择编辑的客户!');
	        }
	}
    
   	//合同登记(add)Dialog后事件
       function afterAddContractDialog(){
          parent.showMessager(1,'合同登记成功',true,5000);
          parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#contractCode0').val());
       }
  
</script>
</head>
<body>
<s:hidden id="cuid" name="cuid"></s:hidden>
<!-- 地区id -->
<s:hidden id="regionId" name="regionId"></s:hidden>
<!-- 第一次查找的pid -->
<s:hidden id="level1pid" name="level1pid"></s:hidden>
<!-- 第二次查找的pid -->
<s:hidden id="level2pid" name="level2pid"></s:hidden>
<!-- 添加的合同编号 -->
<s:hidden id="contractCode0" name="contractCode0"></s:hidden>
<!-- 开始时间 -->
<s:hidden id="startTime" name="startTime"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endTime" name="endTime" ></s:hidden>
<!-- 供试品类型 -->
<s:hidden id="tiCode" name="tiCode" ></s:hidden>
<!-- 模糊查询客户名 -->
<s:hidden id="selectCustomerName"  ></s:hidden>
<!-- 是否有查看全部的权限 -->
<s:hidden id="read"  name="read"></s:hidden>
<!-- 是否有查看全部的权限 -->
<s:hidden id="addContract"  name="addContract"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
    
 	   
 	 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="委托单位信息" border="false" style="overflow: auto;">
			    <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
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
				<table id="tblCustomerTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<!-- 
			<a id="editCustomerButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			 -->
		<!-- 	<a id="deleteCustomerButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-remove',plain:true,disabled:true">删除</a> -->
			<a id="selectContractButton" class="easyui-linkbutton" onclick="onViewContract();" data-options="iconCls:'icon-tablemultiple',plain:true,disabled:true">查看合同</a>
			<a id="addContractButton" class="easyui-linkbutton" onclick="onAddContractButton();" data-options="iconCls:'icon-tableadd',plain:true,disabled:true"  >添加合同</a>
		</div>
 	</div>
 	<%@include file="/WEB-INF/jsp/tblCustomerAction/customerAddEdit.jspf" %>
 	<%@include file="/WEB-INF/jsp/tblCustomerAction/selectCustomer.jspf" %>
 	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 	<%@ include file="/WEB-INF/jsp/tblContractAction/contractAddEdit.jspf"%>
</div>
</body>
</html>
