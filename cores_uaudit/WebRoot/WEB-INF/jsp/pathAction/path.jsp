<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES系统审计信息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style2.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/reqView.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
		//初始化  ‘年’combobox
		initYearCombobox();
		
		//初始化studyNo 表
		initStudyNo();

		//初始化 解剖所见、解剖申请 tab
		initTabs();
		//初始化operateType,operateType2  combobox
		initOperateTypeCombobox();
		initAnatomyCheck();
		initAnatomyReq();
	});
	//初始化studyNo 表
	function initStudyNo(){
		var tableHeight = document.body.clientHeight - 47;
		$('#studyNo').datagrid({
			//url:sybp()+'/clinicaltestAction_loadStudyNoList.action'
			singleSelect:true,
			height:tableHeight,
			width:145,
			columns :[[{
				field:'studyNo',
				title:'课题编码',
				width:142,
				halign:'center'
			}]],
			onLoadSuccess:function(data){
            	
			},
			onClickRow:function(rowIndex, rowData){
				updateDataGrid();
	   		}
		});

		var studyNoListStr = $('#studyNoListStr').val();
		$('#studyNo').datagrid('loadData', eval(studyNoListStr));
	}
	//初始化  ‘年’combobox
	function initYearCombobox(){
		var yearListStr = $('#yearListStr').val();
		$('#year').combobox('loadData', eval(yearListStr));
		var yearStr = $('#yearStr').val();
		$('#year').combobox('select',yearStr);
		$('#year').combobox({
			onSelect:function(record){
				$('#studyNo').datagrid({
					url:sybp()+'/pathAction_loadStudyNoList.action?year='+record.id
				});
			}
		});
	}

	//初始化 解剖所见、解剖申请 tab
	function initTabs(){
		$('#indexTabsDiv').tabs({
			onSelect:function(title,index){
    			if(title == "解剖所见"){
    				//initAnatomyCheck();
    				updateDataGrid();
    			}else if(title =='解剖申请'){
    				//initAnatomyReq();
    				updateDataGrid();
	    		}
		    }
		    		
    	});
	}
	//初始化operateType,operateType2  combobox
	function initOperateTypeCombobox(){
		$('#operateType').combobox({
			onSelect:function(record){
				if(record.id == 2 || record.id == 4){
					$('#anatomyCheck').datagrid('showColumn','oldAnatomyFinding');
															  
				}else{
					$('#anatomyCheck').datagrid('hideColumn','oldAnatomyFinding');
				}
				updateDataGrid();
			}
		});
		$('#operateType2').combobox({
			onSelect:function(record){
				updateDataGrid();
			}
		});
	}
	//初始化 anatomyCheck 表格
	function initAnatomyCheck(){
		var tableHeight = document.body.clientHeight - 47 -25;
		var tableWidth =  document.body.clientWidth - 225 ;
		$('#anatomyCheck').datagrid({
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
			columns :[[{
				field:'studyNo',
				title:'课题编码',
				width:142,
				halign:'center'
			},{
				field:'animalCode',
				title:'动物编号',
				width:55,
				halign:'center'
			},{
				field:'oldAnatomyFinding',
				title:'原解剖所见',
				width:202,
				halign:'center',
				hidden:true
			},{
				field:'anatomyFinding',
				title:'解剖所见',
				width:202,
				halign:'center'
			},{
				field:'operator',
				title:'记录者',
				width:50,
				halign:'center'
			},{
				field:'operateTime',
				title:'记录时间',
				width:110,
				halign:'center'
			},{
				field:'operate',
				title:'操作',
				width:50,
				halign:'center'
			},{
				field:'operater',
				title:'操作者',
				width:50,
				halign:'center'
			},{
				field:'operateRsn',
				title:'操作原因',
				width:142,
				halign:'center'
			},{
				field:'operateDate',
				title:'操作时间',
				width:110,
				halign:'center'
			}]]
		});
	}
	//初始化 anatomyReq 表格
	function initAnatomyReq(){
		var tableHeight = document.body.clientHeight - 47 -25;
		var tableWidth =  document.body.clientWidth - 225 ;
		$('#anatomyReq').datagrid({
			singleSelect:true,
			height:tableHeight,
			width:tableWidth,
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'专题编号',
				field:'studyNo',
				width:142,
				halign:'center',
				align:'left',
			},{
				title:'申请编号',
				field:'reqNo',
				width:60,
				halign:'center',
				align:'center',
			},{
				title:'解剖开始日期',
				field:'beginDate',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'解剖结束日期',
				field:'endDate',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'解剖原因',
				field:'anatomyRsn',
				width:100,
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
				title:'创建人',
				field:'author',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'创建日期',
				field:'createTime',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'申请状态',
				field:'submitFlag',
				width:90,
				halign:'center',
				align:'center'
			},{
				title:'1:变更前  2：边个后',
				field:'change',
				width:90,
				halign:'center',
				align:'center',
				hidden:true
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
			}]],
			onDblClickRow:function(rowIndex, rowData){
				showReqDialog(rowData.studyNo,rowData.reqNo,rowData.change);
			}
		});
	}

	function updateDataGrid(){
		var operateType ;
		var studyNoRow = $('#studyNo').datagrid('getSelected');
		var studyNo ;
		if(studyNoRow){
			studyNo = studyNoRow.studyNo;
		}
		var tab = $('#indexTabsDiv').tabs('getSelected');
		var index = $('#indexTabsDiv').tabs('getTabIndex',tab);
		if(studyNo && index == 0){
			operateType = $('#operateType').combobox('getValue');
			$('#anatomyCheck').datagrid({
				url:sybp()+'/pathAction_loadAnatomyCheckData.action?studyNo='
					+studyNo+'&operateType='+operateType
			});
		}else if(studyNo && index == 1){
			operateType = $('#operateType2').combobox('getValue');
			$('#anatomyReq').datagrid({
				url:sybp()+'/pathAction_loadAnatomyReqData.action?studyNo='
					+studyNo+'&operateType='+operateType
			});
		}
	}
</script>
	  
</head>

<body >
<input id = "yearListStr" type="hidden" name = "yearListStr" value = "${yearListStr}"/>
<input id = "studyNoListStr" type="hidden" name = "studyNoListStr" value = "${studyNoListStr}"/>
<input id = "yearStr" type="hidden" name = "yearStr" value = "${year}"/>
<div class="easyui-layout" style="width:100%;height:100%;">   
    <div data-options="region:'west'" style="width:190px;padding:5px;">
    	<div style="padding-left:15px;padding-bottom:10px;">
	    	<input id="year" class="easyui-combobox" name="year" style="width:100px;height:19px;"  
	    		data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto'" /> 
	    	年<br/>
    	</div>
    	<div style="padding-left:15px;padding-bottom:5px;">
			<table id="studyNo" ></table>
    	</div>
    </div>   
    <div data-options="region:'center'" style="padding:0px;">
   		 <div id="indexTabsDiv" class="easyui-tabs" fit="true" border="false" style="height:100%;width:100%;">
			<div title="解剖所见" border="false" style="overflow: hidden;">
		    	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
		    		类型：
		    		<select id="operateType" class="easyui-combobox" 
		    			 style="width:120px;height:19px;" 
		    			 data-options="valueField:'id',textField:'text',
		    			 	editable:false,panelHeight:'auto'">   
					    <option value="1">数据确认-添加</option>   
					    <option value="2">数据确认-编辑</option>   
					    <option value="3">数据确认-删除</option>   
					    <option value="4">数据修改</option>   
					</select>  
		    	</div>
		    	<div style="padding-left:15px;padding-bottom:5px;">
		    		<table id="anatomyCheck"></table>
		    	</div>
			</div>
			<div title="解剖申请" border="false" style="overflow: hidden;">
			  	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
		    		类型：
		    		<select id="operateType2" class="easyui-combobox" 
		    			 style="width:120px;height:19px;" 
		    			 data-options="valueField:'id',textField:'text',
		    			 	editable:false,panelHeight:'auto'">   
					    <option value="5">申请变更</option>   
					    <option value="6">申请撤销</option>   
					</select>  
		    	</div>
		    	<div style="padding-left:15px;padding-bottom:5px;">
		    		<table id="anatomyReq"></table>
		    	</div>
			</div>
		 </div>
    </div>
    <%@ include file="/WEB-INF/jsp/pathAction/reqView.jspf"%>
</div> 
</body>
</html>