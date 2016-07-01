<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES通知信息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<script src="${pageContext.request.contextPath}/script/javascript/tblNotification.js" type="text/javascript"></script>
<script type="text/javascript">
    var tableHeight;
    $(function(){
    	tableHeight = document.body.clientHeight - 32;

    	//初始化
    	init();
        $('#maxdatebox').datebox('setValue',$('#endDate').val());
    	$('#mindatebox').datebox('setValue',$('#startDate').val());
    	$('#maxdatebox').datebox('validate');
        $('#layoutMainDiv').css('display','');  
        //启动定时器 
        parent.startClock();
    });//匿名函数结束

    //页面初始化
    function init(){
        //表格初始化
    	$('#receievrTable').datagrid({    
       	    //url:sybp()+'/tblNotificationAction_loadList.action?readFlag=-1',
            //title:'收件箱',
   			height:tableHeight,
   			width:600,
   			nowarp:  false,//单元格里自动换行
   			fitColumns:true,
   			//pagination:true,//分页
   			singleSelect:true,
   			sortName:'id',
       	    columns :[[{
   				title:'id',
   				field:'id',
   				width:10,
   				halign:'center',
   				align:'center',
   				checkbox:true,
   				hidden:true
   			},{
   				title:'',
   				field:'readFlag',
   				width:40,
   				halign:'center',
   				align:'center',
   				formatter: function(value,row,index){
	   				if ( value == 0 ){
	   				    //return "<img src='script/jquery-easyui1.3/jquery-easyui-1.3.2/themes/icons/email.png'/>";
	   					return "<img src='script/jquery-easyui1.3/jquery-easyui-1.3.2/themes/icons/emailRed.png'/>";
	   				}else{
	   					//return "<img src='script/jquery-easyui1.3/jquery-easyui-1.3.2/themes/icons/emailopen.png'/>";
	   					return "<img src='script/jquery-easyui1.3/jquery-easyui-1.3.2/themes/icons/emailGreen.png'/>";
	   				}
   				}
   			},{
   				title:'发送者',
   				field:'sender',
   				width:80,
   				halign:'center',
   				align:'left'
   			},{
   				title:'标题',
   				field:'msgTitle',
   				width:280,
   				halign:'center',
   				align:'left',
   			},{
   				title:'时间',
   				field:'sendTime',
   				width:120,
   				halign:'center',
   				align:'left'
   			}]],
   			onClickRow:function(rowIndex, rowData){
   				if(rowData ){
   					view(rowData.id);
   	   				if(rowData.readFlag == 0){
	   	   				$('#receievrTable').datagrid('updateRow',{
		   	   				index: rowIndex,
		   	   				row: {
		   	   					id: rowData.id,
		   	   					readFlag: 1,
		   	   				sender: rowData.sender,
		   	   				msgTitle: rowData.msgTitle,
		   	   				sendTime: rowData.sendTime,
		   	   				}
		   	   			});
   	   	   			}
   	   			}
   	   			
   			},
   			toolbar:'#toolbar',
       	});
       	//开始日期databox
    	$('#mindatebox').datebox({    
   		 	onChange:function(newValue, oldValue){
	        	//var startTime =  $('#mindatebox').datebox('getValue');
	            var endTime = $('#maxdatebox').datebox('getValue');
	            var sender= $('#sender').combobox('getValue');
	            if(newValue && endTime){
		            loadTableData(newValue,endTime,sender);
		        }
			}
		});  
 		//结束日期databox
		$('#maxdatebox').datebox({    
			onChange:function(newValue, oldValue){
				var startTime =  $('#mindatebox').datebox('getValue');
	            //var endTime = $('#maxdatebox').datebox('getValue');
	            var sender= $('#sender').combobox('getValue');
	            if(newValue && startTime){
		            loadTableData(startTime,newValue,sender);
		        }
			}
		});  
		//发送者combobox
		$('#sender').combobox({
			onChange:function(newValue, oldValue){
				var startTime =  $('#mindatebox').datebox('getValue');
	            var endTime = $('#maxdatebox').datebox('getValue');
	            //var sender= $('#sender').combobox('getValue');
	            if(endTime && startTime){
		            loadTableData(startTime,endTime,newValue);
		        }
			}
		});
    }
	//根据条件,加载datagrid数据
	function loadTableData(startDate,endDate,sender){
		if(startDate && endDate){
			$('#receievrTable').datagrid({    
	       	    url:sybp()+'/tblNotificationAction_loadList.action?startDate='+startDate+'&endDate='+
	       	    endDate+'&sender='+encodeURIComponent(sender),
			});
		}
	}
</script> 
</head>
<body>
<!-- 开始时间 -->
<s:hidden id="startDate" name="startDate"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endDate" name="endDate" ></s:hidden>   
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%;display:none;">
	<div region="center" border="false" style="">
		<div style="margin:5px;border:1px solid #e3e6eb;height:93%;">
			<iframe src=""  frameborder="0" style="border:0;width:100%;height:99.4%;" name="view"></iframe>
		</div>
	</div>
	<div id="west" region="west" border="false" style="width:600px;">
		<table id="receievrTable"></table>
	</div>
	<div id="toolbar">
   	    &nbsp;&nbsp; 日期范围
              &nbsp;
       	&nbsp;从&nbsp;&nbsp;<input id="mindatebox" type="text" class="easyui-datebox" style="width:90px;height:19px;" editable="false"></input>	
     	  	&nbsp;至&nbsp;&nbsp;<input id="maxdatebox" type="text" class="easyui-datebox" style="width:90px;height:19px;" editable="false"></input>   	
		&nbsp;&nbsp;&nbsp;&nbsp;发送者
   		<input id="sender" class="easyui-combobox" data-options="valueField:'id',textField:'text',panelHeight:'auto',
   			url:'${pageContext.request.contextPath}/tblNotificationAction_loadAllSender.action'" style="width:70px;height:19px;"/>
	</div>
</div>
</body>  

</html>