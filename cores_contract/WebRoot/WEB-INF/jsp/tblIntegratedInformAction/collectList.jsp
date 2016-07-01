<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>汇总查询</title>
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
	    
	    $('#studyType_testItemAndNo').combobox({  
		   onChange:function(newValue, oldValue){
		    var startTime =  $('#mindatebox').datebox('getValue');
		    var endTime = $('#maxdatebox').datebox('getValue');
		    $('#startTime').val(startTime);
		    $('#endTime').val(endTime);
		   if(newValue == -1){
		      showCollectListTable(startDate,endDate,"");
		      parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,"");
		   }else{
		     showCollectListTable(startDate,endDate,newValue);
		     parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,newValue);
		   }
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
		              showCollectListTable(startTime,endTime,tiCode);
		            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
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
		             showCollectListTable(startTime,endTime,tiCode);
		            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
			  }
		});  
	       	initTestItemAndNOCombobox();
	     	initstartTimeAndEndTimeAndtiCode();
	        showCollectListTable(startDate,endDate,tiCode);
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
   
   
   
   
    //显示tblContractTable
    function  showCollectListTable(startDate,endDate,tiCode){
        	$('#collectListTable').propertygrid({
			url : sybp()+'/tblIntegratedInformAction_LoadcollectList.action?startDate='
			+startDate+'&endDate='+endDate+'&tiCode='+tiCode,
			showGroup:false,
            scrollbarSize:0,
            width:800,
            height:500,
            showHeader:false,
            columns:[[ 
               {field:'name',title:'Name',width:100,sortable:true}, 
              {field:'value',title:'Value',width:200,sortable:true} 
             ]],
            
			onSelect:function(rowIndex, rowData){
			},
			onLoadSuccess:function(data){
			  if(data.lenght < 1){
			  }
			},
			onLoadError:function(){
			}
	   	}); //end datagrid
    }
   
   
    
    
    
    //初始化 供试品类型 开始日期 结束日期
    function  initstartTimeAndEndTimeAndtiCode(){
        var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		var tiCode = $('#tiCode').val();
		 if(tiCode && tiCode != ""){
	       $('#studyType_testItem').combobox('select', tiCode);
		 }else{
		    $('#studyType_testItem').combobox('select', "");
		 }
		$('#mindatebox').datebox('setValue', startDate);
		$('#maxdatebox').datebox('setValue', endDate);
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
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
   <!--  
    <div region="north" title="" style="height:30px;">
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a class="easyui-linkbutton" onclick="onAddButton();"
	       				 data-options="iconCls:'icon-add',plain:true" >添加客户</a>  
  		</div>
  	</div>-->
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="汇总查询" border="false" style="overflow: auto;">
			    <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;供试品类型
			    		<input id="studyType_testItemAndNo" class="easyui-combobox" style="height:19px;width:80px;"
			    		data-options="panelHeight:'auto'"/>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 合同日期范围
		                &nbsp;
			        	&nbsp;从&nbsp;&nbsp;<input id="mindatebox" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>	
			      	  	&nbsp;至&nbsp;&nbsp;<input id="maxdatebox" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>   	
			    		<!-- 搜索框 
			    		&nbsp;&nbsp;&nbsp;&nbsp;
			    		<span style="position:absolute; top:35px;">
			    		<input id="searchContract" ></input> 
			    		</span>-->
			    	</div>
			    
			    </div>
				<table id="collectListTable"></table>
            </div>
		</div>
        <div id="toolbar">
		</div>
 	</div>
</div>
</body>
</html>