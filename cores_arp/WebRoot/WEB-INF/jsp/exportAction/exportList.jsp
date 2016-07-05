<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>防疫管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/exportAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/surfaceAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/tbAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/vaccineAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/virusAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/xAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/bacteriaAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/parasiteAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/infectiousAdd.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/addCheckItem.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#searchRemarks').searchbox({ 
		    height:19,
		    width:450,
			searcher:function(value,name){ 
			 var orderid =  $('#orderid').val();
			 var startdate = $('#startdate').datebox('getValue');
			 var enddate = $('#enddate').datebox('getValue');
		     showExportTable(monkeyid,dissectdate,enddate);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showExportTable('','','');
		
		$('#layoutMainDiv').css('display','');
		
		var row= $('#exportTable').datagrid('getSelected');
		/**处理菜单、按钮状态*/
		if(row==null){
				var item1=$('#addQcCheckItem')[0];
			   var item2=$('#addInParasiteCheckItem')[0];
			  // var item3=$('#addOutParasiteCheckItem')[0];
			   var item4=$('#addVirusCheckItem')[0];
			   var item5=$('#addBacteriaCheckItem')[0];
			   var item6=$('#addVaccineCheckItem')[0];
			   var item7=$('#addTbCheckItem')[0];
			   var item8=$('#addXcgCheckItem')[0];
			   var item9=$('#addXyshCheckItem')[0];
			   var item10=$('#addInfectiousCheckItem')[0];
			   var item11=$('#addXCheckItem')[0];
			   var item12=$('#addSurfaceCheckItem')[0];
			   $('#menu0').menu('disableItem',item1);
			   $('#menu0').menu('disableItem',item2);
			  // $('#menu0').menu('disableItem',item3);
			   $('#menu0').menu('disableItem',item4);
			   $('#menu0').menu('disableItem',item5);
			   $('#menu0').menu('disableItem',item6);
			   $('#menu0').menu('disableItem',item7);
			   $('#menu0').menu('disableItem',item8);
			   $('#menu0').menu('disableItem',item9);
			   $('#menu0').menu('disableItem',item10);
			   $('#menu0').menu('disableItem',item11);
			   $('#menu0').menu('disableItem',item12);
		}
    });//匿名函数结束
    function showExportTable(id,startdate,enddate){
    	$('#exportTable').datagrid({
			url : sybp()+'/exportAction_loadList.action?orderid='+id+'&startdate='+startdate+'&enddate='+enddate,
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
				field:'ids',
				width:150,
				hidden:true
			},{
				title:'检疫编号',
				field:'title',
				width:120,
				halign:'center',
				align:'center'
			},{
				title:'检疫时间',
				field:'checkdate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'驱虫(是否采样)',
				field:'qc',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'寄生虫(是否采样)',
				field:'parasite',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'病毒(是否采样)',
				field:'virus',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'细菌(是否采样)',
				field:'bacteria',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'疫苗(是否采样)',
				field:'vaccine',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'血常规(是否采样)',
				field:'xcg',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'TB(是否采样)',
				field:'tb',
				width:100,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'血生化(是否采样)',
				field:'xysh',
				width:110,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			}//,{title:'检疫状态',field:'status',width:100,halign:'center',align:'center',
			//	formatter:function(value,row,index){
			//		if(row.status=="1"){
			//			return '提交未检疫';
					//}else if(row.status=="2"){
					//	return '提交已检疫';
					//
			//		}else if(row.status=="2"){
			//			return '检疫已录入';
			//		}
			//	}
			//}
			,{
				title:'传染病(是否采样)',
				field:'infectious',
				width:110,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}},{
				title:'X光(是否采样)',
				field:'x',
				width:110,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}},{
				title:'体表(是否采样)',
				field:'surface',
				width:110,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}}
			]],
			onSelect:function(rowIndex, rowData){
			  $('#addExportButton').linkbutton('enable');
			  $('#lookExportButton').linkbutton('enable');
			  $('#exportExcelButton').linkbutton('enable');
			  //是否可以编辑
			  var flag=rowData.qc==''&&rowData.parasite==''&&rowData.virus==''&&rowData.bacteria==''&&rowData.vaccine==''&&rowData.xcg==''&&rowData.tb==''&&rowData.xysh==''&&rowData.infectious==''&&rowData.x==''&&rowData.surface=='';
			  if(flag){
			  		$('#editButton').linkbutton('enable');
			  }else{
			  		$('#editButton').linkbutton('disable');
			  }
			  if(rowData.qc==''){
			  	var item=$('#addQcCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addQcCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.parasite==''){
			  	var item=$('#addInParasiteCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  	//var item1=$('#addOutParasiteCheckItem')[0];
			  	//$('#menu0').menu('enableItem',item1);
			  }else{
			  	var item=$('#addInParasiteCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  	//var item1=$('#addOutParasiteCheckItem')[0];
			  	//$('#menu0').menu('disableItem',item1);
			  }
			  if(rowData.virus==''){
			  	var item=$('#addVirusCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addVirusCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.bacteria==''){
			  	var item=$('#addBacteriaCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addBacteriaCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.vaccine==''){
			  	var item=$('#addVaccineCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addVaccineCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.xcg==''){
			  	var item=$('#addXcgCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addXcgCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.tb==''){
			  	var item=$('#addTbCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addTbCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.xysh==''){
			  	var item=$('#addXyshCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addXyshCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.infectious==''){
			  	var item=$('#addInfectiousCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addInfectiousCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.x==''){
			  	var item=$('#addXCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addXCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			  if(rowData.surface==''){
			  	var item=$('#addSurfaceCheckItem')[0];
			  	$('#menu0').menu('enableItem',item);
			  }else{
			  	var item=$('#addSurfaceCheckItem')[0];
			  	$('#menu0').menu('disableItem',item);
			  }
			},
			onLoadSuccess:function(data){
			   $('#addExportButton').linkbutton('enable');
			   $('#lookExportButton').linkbutton('disable');
			   $('#exportExcelButton').linkbutton('disable');
			   $('#editButton').linkbutton('disable');
			   
	           var selectid=$('#exid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#exportTable').datagrid('selectRow',i);
			             }
			          }
			   }
			},
			onDblClickRow:function(rowIndex, rowData){//鼠标双击触发.
				showNormalAndExportCheckDialog('查看检疫信息');
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    }
    
    function showNormalAndExportCheckDialog(title){
		document.getElementById("showCheckDialog2").style.display="block";
		var row= $('#exportTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/exportAction_showCheck.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#view_title').val(r.title);
  	    				$('#view_checkdate').datebox('setValue',r.checkdate);
  	    				$('#view_monkeylist').val(r.monkeylist);
  	    				$('#view_boss').combobox('setValue',r.boss);
  	    			}
  	    		}
  	    	});
		$('#showCheckDialog').dialog('setTitle',title);
		$('#showCheckDialog').dialog('open'); 
	}
    function onLookButton(){
    	$('#layoutMainDiv').css('display','none');
    	$('#layoutOneDiv').css('display','');
    	var row= $('#exportTable').datagrid('getSelected');
    	$('#exportOneTable').datagrid({
			url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id,
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    //pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'动物编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'检疫时间',
				field:'checkdate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'体表',
				field:'surface',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'寄生虫',
				field:'parasite',
				width:60,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'病毒',
				field:'virus',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'细菌',
				field:'bacteria',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'疫苗',
				field:'vaccine',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'传染病',
				field:'infectious',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				title:'TB',
				field:'tb',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '√';
					}
				}
			},{
				//title:'X光',
				//field:'x',
				//width:80,
				//halign:'center',
				//align:'center',
				//formatter:function(value,row,index){
				//	if(value){
				//		return '√';
				//	}
				//},
				title:'X光',
				field:'result',
				width:90,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: {required: true } }
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#exportCheckItemsButton').linkbutton('enable');
    		  if(row.status=="1"){
    		  		$('#exportCheckItemsAddButton').linkbutton('enable');
    		  }
			},
			onLoadSuccess:function(data){
	    	   $('#exportCheckItemsButton').linkbutton('enable');
	    	   if(row.status=="1"){
	           		$('#exportCheckItemsAddButton').linkbutton('enable');
	           }
	           var selectid=$('#exid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#exportOneTable').datagrid('selectRow',i);
			             }
			          }
			   }
			   var rows=$("#exportOneTable").datagrid("getRows");
    			 for(var i=0;i<rows.length;i++){
      				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
      				$('#exportOneTable').datagrid('beginEdit', rowIndex);
      			}
			},
			toolbar:'#toolbar1',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    
    }
    //查找按钮事件
    function onSearchButton(){
    	var id =  $('#orderid').val();
    	var startdate =  $('#startdate').datebox('getValue');
    	var enddate =  $('#enddate').datebox('getValue');
    	showExportTable(id,startdate,enddate);
    }
	//添加按钮事件
    function onAddButton(){
    	window.location.href=sybp()+'/exportAction_addList.action?addOrEdit='+'add';
    }
    //导出按钮事件--检疫之前.
    function onExportExcelButton(){
    	var row= $('#exportTable').datagrid('getSelected');
    	var path = sybp()+'/exportAction_export.action?id='+row.id;
    	window.open(path,'target','param');
    }
    //导出--检疫之后.
    function onExportCheckItemsButton(){
    	var row= $('#exportTable').datagrid('getSelected');
    	var kind=$('#kind').val();
    	//alert(row.id);
    	//alert(kind);
    	var path = sybp()+'/exportAction_exportCheckItems.action?id='+row.id+'&kind='+kind;
    	window.open(path,'target','param');
    }
    function onEditButton(){
         var row= $('#exportTable').datagrid('getSelected');
        if(row != null ){
        	window.location.href=sybp()+'/exportAction_addList.action?normalid='+row.id+'&addOrEdit='+'edit';
        	//showExportAddEditDialog('afterEditDialog','edit','编辑常规治疗记录');
        }else{
           $.messager.alert('提示','请选择要录入的检疫编号记录!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#exportTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#exportTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此常规治疗记录？', function(r){
 				if (r){
 					delExport(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的常规治疗记录!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delExport(id){
   	   	$.ajax({
   			url:sybp()+'/exportAction_delExport.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'常规治疗删除成功',true,5000);
   					$('#exportTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'常规治疗删除失败',true,5000);
   				}
   			}
   		});
       }
       //返回. 
     function onBackButton(){
     	window.location.href="${pageContext.request.contextPath}/exportAction_list.action";
     }
</script>
</head>
<body>
<s:hidden id="exid" name="exid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="动物检疫" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;检疫编号：
			    		<input id="orderid" type="text" style="width:100px;"></input>检疫时间：<input id="startdate" type="text" name="startdate" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="enddate" type="text" name="enddate" class="easyui-datebox" style="width:120px;"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="exportTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        	<!--  <a id="lookExportButton" class="easyui-linkbutton" onclick="onLookButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">查看</a>-->
        	<a href="javascript:void(0)" id="splitbutton" class="easyui-splitbutton" data-options="menu:'#menu0',iconCls:'icon-edit'" onclick="">检疫管理</a>
        	<div id="menu0"  class="easyui-menu" data-options="minWidth:'80px'">   
				<div id="addQcCheckItem" data-options="iconCls:'icon-add'" style="width:110px;"  onclick="addQc();">驱虫</div>   
				<!--  <div id="addInParasiteCheckItem" data-options="iconCls:''" onclick="addInParasite();" style="width:110px;">体内寄生虫</div>
				<div id="addOutParasiteCheckItem" data-options="iconCls:''" onclick="addOutParasite();" style="width:110px;">体外寄生虫</div>--> 
				<div id="addInParasiteCheckItem" data-options="iconCls:''" onclick="addInParasite();" style="width:110px;">寄生虫</div>
				<div id="addVirusCheckItem" data-options="iconCls:''" onclick="addVirus();" style="width:110px;">病毒</div>
				<div id="addBacteriaCheckItem" data-options="iconCls:''" onclick="addBacteria();" style="width:110px;">细菌</div>
				<div id="addVaccineCheckItem" data-options="iconCls:'icon-add'" style="width:110px;"  onclick="addVaccine();">疫苗</div>   
				<div id="addTbCheckItem" data-options="iconCls:''" onclick="addTb();" style="width:110px;">TB</div>	
				<div id="addXcgCheckItem" data-options="iconCls:''" onclick="addXcg();" style="width:110px;">血常规</div>
				<div id="addXyshCheckItem" data-options="iconCls:''" onclick="addXysh();" style="width:110px;">血生化</div>
				<p style="border-top:1px solid #e3e6eb;height:0px;"></p>
				<div id="addInfectiousCheckItem" data-options="iconCls:''" onclick="addInfectious();" style="width:110px;">传染病</div>
				<div id="addXCheckItem" data-options="iconCls:''" onclick="addX();" style="width:110px;">X光</div>
				<div id="addSurfaceCheckItem" data-options="iconCls:''" onclick="addSurface();" style="width:110px;">体表</div>
			</div>
        	<a id="addExportButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
        	<a id="exportExcelButton" class="easyui-linkbutton" onclick="onExportExcelButton();"  data-options="iconCls:'icon-excel',plain:true,disabled:true">导出EXCEL</a>
        	<a id="editButton" class="easyui-linkbutton" onclick="onEditButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">编辑</a>
        	<!--  <a id="lookButton" class="easyui-linkbutton" onclick="onlookButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">查看</a>-->		 	
		</div>
 	</div>
 	<%@include file="/WEB-INF/jsp/exportAction/exportAdd.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/surfaceTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/tbTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/vaccineTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/virusTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/xTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/parasiteTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/infectiousTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/bacteriaTable.jspf" %>
 	<%@include file="/WEB-INF/jsp/exportAction/normalAndExportCheck.jspf" %>
</div>
<div id="layoutOneDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;">
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs1" class="easyui-tabs" fit="true" border="false">
            <div title="个体检疫信息" border="false" style="overflow: auto;">
                <div style="height:15px;">
			       <div style="float:left; width:100%;padding-top:1px;height:12px;">
			       	<s:hidden id="kind" name="kind"></s:hidden>
			    	</div>
			    
			    </div>
				<table id="exportOneTable" ></table>
            </div>
		</div>
        <div id="toolbar1">
			<a id="exportCheckItemsAddButton" class="easyui-linkbutton" onclick="uploadCheckItemsButton1();" data-options="iconCls:'icon-save',plain:true,disabled:true">检疫信息录入</a>
			<a id="exportCheckItemsButton" class="easyui-linkbutton" onclick="onExportCheckItemsButton()" data-options="iconCls:'icon-excel',plain:true,disabled:true">导出EXCEL</a>
			<a class="easyui-linkbutton" onclick="onBackButton();" data-options="iconCls:'icon-back',plain:true">返回</a>
		 	检疫编号：<span id="titleAndCheckItem1"/> &nbsp;
		</div>
 	</div>
 	 <!--  <%@ include file="/WEB-INF/jsp/exportAction/attachmentAddEdit.jspf" %>-->
</div>
</body>
</html>