<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
	<script type="text/javascript">
		var tblLogTable;
		var tableHeight;//当前页面可见区域高度 - 30
        var tableWidth;
		$(function(){
			tableHeight = document.body.clientHeight - 5;
	         tableWidth  = document.body.clientWidth-2;
			tblLogTable=$('#tblLogTable').datagrid({
				//url : sybp()+"/tblLogAction_loadLogList.action?systemNameIndex="+systemNameIndex,
				title:'',
				iconCls:'',//'icon-save',
				singleSelect:true,//只能选一行
				pagination:false,//下面状态栏
			    height:tableHeight,
			    width:tableWidth,  
				//fit:true,
				fitColumns:false,//不出现横向滚动条
				nowarp:  false,//单元格里自动换行
				border:false,
				idField:'id', //pk
				toolbar:'#toolbar',
				columns :[[{
					title :'',
					field :'id',
					hidden:true
				},{
					title : '操作类型',
					field : 'operatType',
					width : 110,
					halign:'center'
				},{
					title:'操作对象',
					field:'operatOject',
					width:190,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					},
					halign:'center'
				},{
					title:'操作者',
					field:'operator',
					width:70,
					//formartter 一定要返回个字符串
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					},
					halign:'center'
				},{
					title:'操作时间',
					field:'operatTime',
					width:130,
					//formartter 一定要返回个字符串
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					},
					halign:'center'
				},{
					title:'操作内容',
					field:'operatContent',
					width:300,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					},
					halign:'center'
				},{
					title:'操作位置',
					field:'operatHost',
					width:170,
					halign:'center',
					hidden:true
					
				},{
					title:'备注',
					field:'remark',
					width:350,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					},
					halign:'center'
				}]]
			});
			
			$('#beginDate').datebox({    
	         required:true,
	            onSelect: function(date){
					onSelectButton();
	  		  	}
	        });  
			
			$('#endDate').datebox({    
	         required:true,
	            onSelect: function(date){
					onSelectButton();
			       // var beginDate =  $('#beginDate').datebox('getValue');
		            //var endDate = $('#endDate').datebox('getValue');
		            // var falg = dateCompare(beginDate,endDate);
		            // if(falg){
		             
		            // }else{
		             // $.messager.alert('提示','开始日期不可大于结束日期!');
		            // }
	  		  }
	        });  
			$('#systemName').combobox({
				onChange:function(record,oldValue){
					$('#operatType').combobox('select','');
					$('#operatType').combobox({
						url:sybp()+'/tblLogAction_selectLogOperatTypeList.action?systemNameIndex='+record
					});
					onSelectButton();
				}
			});
			$('#systemName').combobox('select',1);
			//显示整个布局
	     	$('#layoutMainDiv').css('display','');   
		});

		
		/**查询按钮方法*/
		function onSelectButton(){
			if($('#selectForm').form('validate')){
				var systemNameIndex = $('#systemName').combobox('getValue');
				var beginDate = $('#beginDate').datebox('getValue');
				var endDate = $('#endDate').datebox('getValue');
				var operatType = $('#operatType').combobox('getValue');
				$('#tblLogTable').datagrid({
					url:sybp()+'/tblLogAction_loadLogList.action?systemNameIndex='+systemNameIndex+'&beginDate='+
						beginDate+'&endDate='+endDate+'&operatType='+operatType
					});
				/*
				$.ajax({
					url:sybp()+'/tblLogAction_loadLogList.action',
					type:'post',
					data:$('#selectForm').serialize(),
					dataType:'json',
					success:function(r){
						if(r){
							tblLogTable.datagrid('loadData',r);
						}
					}
				});
				*/
			}
		}
		
		//比较时间大小
    function dateCompare(startdate,enddate){   
         var arr=startdate.split("-");    
         var starttime=new Date(arr[0],arr[1],arr[2]);    
         var starttimes=starttime.getTime();   
         var arrs=enddate.split("-");    
         var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
         var lktimes=lktime.getTime();   
         if(starttimes>=lktimes){   
            return false;   
         }else  {
            return true;   
         }  
    }  
	</script>
</head>
<body>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:'none';"> 
	<div region="center" title="" style="overflow: hidden;">
		<table id="tblLogTable" ></table>
		<div id="toolbar" style="display:none;">
			<form id="selectForm" action="" method="post">
				<table>
					<tr>
						<td>
						  系统名称：<input id="systemName" name="systemNameIndex" class="easyui-combobox"  style="height:19px;"  
    						data-options="valueField:'id',textField:'text',editable:false,
    						panelHeight:'auto',width:130,
    						url:sybp()+'/homeAction_loadSystemName.action'" />  
						&nbsp;操作日期：<input id="beginDate" name="beginDate" value="${beginDate}" class="easyui-datebox" 
							data-options="width:90,editable:false" required="true"/>
							至<span style="width:3px;"></span>
							<input id="endDate" name="endDate" value="${endDate}" class="easyui-datebox" 
							data-options="width:90,editable:false" required="true"/>
						&nbsp;操作类型：<input id="operatType" name="operatType" class="easyui-combobox" 
							data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto',
							url:'${pageContext.request.contextPath}/tblLogAction_selectLogOperatTypeList.action?systemNameIndex=${systemNameIndex}',
							onSelect:function(){onSelectButton();},width:170"/>
						
						<!-- 
							<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-search',plain:true" 
								onclick="onSelectButton();">查询</a>
						 -->
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</div>
</div>
</body>
</html>
