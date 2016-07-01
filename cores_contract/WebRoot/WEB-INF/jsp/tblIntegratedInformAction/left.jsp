<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>CoRES项目管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<script type="text/javascript">
    var selectTable;
    $(function(){
	   	selectTable= $('#selectTable').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			    var id = rowData.id;
			    var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var tiCode = $('#tiCode').val();
			    if(id == 1){
			    	window.open(sybp()+'/tblIntegratedInformAction_customerList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode,'main');
				}else if(id == 2){
				    //合同查询
				    window.open(sybp()+'/tblIntegratedInformAction_contractList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode,'main');
				}else if(id == 3){
				    //供试品查询
				    window.open(sybp()+'/tblIntegratedInformAction_testItemList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode,'main');
				}else if(id == 4 ){
				    //委托项目查询
				     window.open(sybp()+'/tblIntegratedInformAction_studyItemList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode,'main');
				}else if(id == 5){
				    //汇总查询collect
				     window.open(sybp()+'/tblIntegratedInformAction_collectList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode,'main');
				}else if(id == 6){
				     window.open(sybp()+'/tblLogAction_list.action','main');
				}else if(id == 7){
					window.open(sybp()+'/tblIntegratedInformAction_highGradeQuery.action','main');
				}
			},	
			onLoadSuccess:function(row, data){
			    $('#selectTableDiv').css('display','');
			   	$('#selectTable').datagrid('selectRow',1);  
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		
	});
	//更新开始时间结束时间供试品类型
	function updateStartTimeAndEndTimeAndTiCode(startDate,endDate,tiCode){
	   $('#startDate').val(startDate);
	   $('#endDate').val(endDate);
	   $('#tiCode').val(tiCode);
	}
	//左边导航选中left2 
	function selectLeft2(){
		var selected = $('#selectTable').datagrid('getSelected');
		if(selected){
			$('#selectTable').datagrid('unselectAll');
			$('#selectTable').datagrid('selectRow',selected.id - 1);
		}else{
			$('#selectTable').datagrid('selectRow',1);
		}
	}
</script>
</head>
<body>
<!-- 开始时间 -->
<s:hidden id="startDate" name="startDate"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endDate" name="endDate" ></s:hidden>
<!-- 供试品类型 -->
<s:hidden id="tiCode" name="tiCode" ></s:hidden>
<s:hidden id="selectid" name="selectid" ></s:hidden>
<div id ="selectTableDiv" style="margin-top:10px;display:none;">
  <table id="selectTable" class="easyui-datagrid">   
    <thead >   
        <tr> 
             <th data-options="field:'id',width:185,hidden:true,align:'center'"  ></th> 
            <th data-options="field:'select',width:185,align:'left'"  ></th>   
        </tr>   
    </thead>   
    <tbody>   
         <tr > 
            <td>1</td>
            <td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托单位查询</td>
         </tr>   
         <tr>   
         <td>2</td>
           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同信息查询</td>
         </tr>  
          <tr>   
          <td>3</td>
           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供试品查询</td>
         </tr>  
         <tr>   
         <td>4</td>
           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委托项目查询</td>
         </tr>  
         <tr>   
         <td>5</td>
           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;汇总信息</td>
        </tr>   
        <tr>   
        <td>7</td>
           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高级查询</td>
        </tr>
        <tr>   
        <td>6</td>
           <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统日志</td>
        </tr>
    </tbody>   
</table>  
</div>
</body>
</html>