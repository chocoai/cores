<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>常规检疫</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/datagrid-detailview.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/normalAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/quarantineAction/normalOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/virusOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/parasiteOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/bacteriaOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/vaccineOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/infectiousOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/tbOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/xOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/xcgOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/xyshOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/normalAction/surfaceOperate.js" charset="utf-8"></script>
<style type="text/css">
	 .tree-icon {
		width:0px;
	}
	
</style>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 160;
		tableWidth  = document.body.clientWidth;
		initDatagrid();
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
    function initDatagrid(){
    	//初始化datagrid
		$('#normalTable').datagrid({
			url : sybp()+'/routineAction_loadList.action',
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},
			{
				title:'动物编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},
			{
				title:'体表',
				field:'surface',
				width:65,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="surface" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="surface" value="'+value+'" disabled="disabled" />';
					}
				}
			}
			,
			{
				title:'寄生虫',
				field:'parasite',
				width:65,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="parasite" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="parasite" value="'+value+'" disabled="disabled" />';
					}
				}
			}
			,
			{
				title:'病毒',
				field:'virus',
				width:35,
				halign:'center',
				align:'center',
				hidden:false ,
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="virus" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="virus" value="'+value+'" disabled="disabled" />';
					}
				}
			}
			,
			{
				title:'细菌',
				field:'bacteria',
				width:35,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="bacteria" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="bacteria" value="'+value+'" disabled="disabled" />';
					}
				}
			}
			,
			{
				title:'疫苗',
				field:'vaccine',
				width:35,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="vaccine" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="vaccine" value="'+value+'" disabled="disabled" />';
					}
				}
			},
			{
				title:'传染病',
				field:'infectious',
				width:65,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="infectious" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="infectious" value="'+value+'" disabled="disabled" />';
					}
				}
			},
			{
				title:'TB',
				field:'tb',
				width:35,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="tb" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="tb" value="'+value+'" disabled="disabled" />';
					}
				}
			},
			{
				title:'X光',
				field:'x',
				width:35,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="x" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="x" value="'+value+'" disabled="disabled" />';
					}
				}
			},
			{
				title:'血常规',
				field:'xcg',
				width:65,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="xcg" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="xcg" value="'+value+'" disabled="disabled" />';
					}
				}
			},
			{
				title:'血液生化',
				field:'xysh',
				width:65,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value){
						return '<input type="checkbox" name="xysh" value="'+value+'" disabled="disabled" checked="checked" />';
					}else{
						return '<input type="checkbox" name="xysh" value="'+value+'" disabled="disabled" />';
					}
				}
			},
			{
				title:'检疫时间',
				field:'checkdate',
				width:80,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
				$('#addNormalButton').linkbutton('enable');
				$('#editNormalButton').linkbutton('enable');
				$('#deleteNormalButton').linkbutton('enable');
			    $('#operateNormalButton').linkbutton('enable');
			    if(rowData.contractMark==0&&rowData.viewMark==0){
			      $('#selectContractButton').linkbutton('enable');
			    }else{
			      $('#selectContractButton').linkbutton('disable'); 
			    }
                  $('#exportExcelButton').linkbutton('enable');
			    
			},
			onLoadSuccess:function(data){
				$('#addNormalButton').linkbutton('enable');
				$('#editNormalButton').linkbutton('disable');
				$('#deleteNormalButton').linkbutton('disable');
				$('#operateNormalButton').linkbutton('disable');
			    $('#exportExcelButton').linkbutton('disable');
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	}); //end datagrid
    }
    function onSearchButton(){
       if( $('#normalForm').form('validate') ){
           var monkeyid = $('#smonkeyid').val();
           //var animaltype = $('#animaltype').combobox('getValue');
          // var roomCondition=$('#roomCondition').combobox('getValue');
          // var quyu = $('#quyu').combobox('getValue');
           $('#normalTable').datagrid({url : sybp()+'/routineAction_loadList.action?monkeyid='+monkeyid,
           pageNumber:1
           });
       
       }
    }
   
   
   /**操作*/
   function onOperateButton(){
   		var row= $('#normalTable').datagrid('getSelected');
     	document.getElementById("normalOperateDialog2").style.display="block";
	 	$('#normalOperateDialog').dialog('setTitle','常规检疫操作');
	 	$('#normalOperateDialog').dialog('open');   
	 
	 	if(row!=null){
	 		$('#monkeyids').val(row.monkeyid);
	 		$('#normalid').val(row.id);
	 	}
	 	if(row!=null){
	 		$('#look').linkbutton('enable');
	 		$('#look1').linkbutton('enable');
	 		$('#look2').linkbutton('enable');
	 		$('#look3').linkbutton('enable');
	 		$('#look4').linkbutton('enable');
	 		$('#look5').linkbutton('enable');
	 		$('#look6').linkbutton('enable');
	 		$('#look7').linkbutton('enable');
	 		$('#look8').linkbutton('enable');
	 		$('#look9').linkbutton('enable');
	 	}else{
	 		$('#look').linkbutton('disable');
	 		$('#look1').linkbutton('disable');
	 		$('#look2').linkbutton('disable');
	 		$('#look3').linkbutton('disable');
	 		$('#look4').linkbutton('disable');
	 		$('#look5').linkbutton('disable');
	 		$('#look6').linkbutton('disable');
	 		$('#look7').linkbutton('disable');
	 		$('#look8').linkbutton('disable');
	 		$('#look9').linkbutton('disable');
	 	};
	 	if(row.surface!=null&&row.surface!=""){
	 		$('#add').linkbutton('disable');	 		
	 	}else{
	 		$('#add').linkbutton('enable');
	 	}
	 	if(row.parasite!=null&&row.parasite!=""){
	 		$('#add1').linkbutton('disable');	 		
	 	}else{
	 		$('#add1').linkbutton('enable');
	 	}
	 	if(row.virus!=null&&row.virus!=""){
	 		$('#add2').linkbutton('disable');	 		
	 	}else{
	 		$('#add2').linkbutton('enable');
	 	}
	 	if(row.bacteria!=null&&row.bacteria!=""){
	 		$('#add3').linkbutton('disable');	 		
	 	}else{
	 		$('#add3').linkbutton('enable');
	 	}
	 	if(row.vaccine!=null&&row.vaccine!=""){
	 		$('#add4').linkbutton('disable');	 		
	 	}else{
	 		$('#add4').linkbutton('enable');
	 	}
	 	if(row.infectious!=null&&row.infectious!=""){
	 		$('#add5').linkbutton('disable');	 		
	 	}else{
	 		$('#add5').linkbutton('enable');
	 	}
	 	if(row.tb!=null&&row.tb!=""){
	 		$('#add6').linkbutton('disable');	 		
	 	}else{
	 		$('#add6').linkbutton('enable');
	 	}
	 	if(row.x!=null&&row.x!=""){
	 		$('#add7').linkbutton('disable');	 		
	 	}else{
	 		$('#add7').linkbutton('enable');
	 	}
	 	if(row.xcg!=null&&row.xcg!=""){
	 		$('#add8').linkbutton('disable');	 		
	 	}else{
	 		$('#add8').linkbutton('enable');
	 	}
	 	if(row.xysh!=null&&row.xysh!=""){
	 		$('#add9').linkbutton('disable');	 		
	 	}else{
	 		$('#add9').linkbutton('enable');
	 	}
   }
   
   function onDeleteButton(){
     	  var row= $('#normalTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此常规检疫记录？', function(r){
 				if (r){
 					delNormal(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的常规检疫记录!');
         }
      }
       function delNormal(id){
   	   	$.ajax({
   			url:sybp()+'/normalAction_delNormal.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'记录删除成功',true,5000);
   					$('#normalTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'记录删除失败',true,5000);
   				}
   			}
   		});
       }
</script>
</head>
<body>
<s:hidden id="nid" name="nid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<s:hidden id="selectId" name="selectId"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="常规检疫" border="false" style="overflow: auto;">
			    <div style="height:50px;background-color:#fafafa; ">
			     <form id="normalForm" action="" method="post">
			     	<table class="tableCss">
			       		<tr><td>动物编号</td><td><input id="smonkeyid" type="text" name="smonkeyid" class="easyui-validatebox" data-options="validType:'maxLength[20]'"  style="width:100px;"></input></td>   
			       			<td><a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a></td>
			       		</tr>
			         	<!--<tr>
			            	<td  colspan="2"  ><a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a></td>
			         	</tr>-->
			      </table>
 	          </form>
 	    
 	            </div>
				<table id="normalTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        	<a id="addNormalButton" class="easyui-linkbutton" onclick="onAddNormalButton();"  data-options="iconCls:'icon-monkeyadd',plain:true,disabled:true">添加</a>
        	<a id="editNormalButton" class="easyui-linkbutton" onclick="onEditNormalButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteNormalButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
			<a id="operateNormalButton" class="easyui-linkbutton" onclick="onOperateButton();"  data-options="iconCls:'icon-monkeyadd',plain:true,disabled:true">动物个体常规检疫操作</a>			
		</div>
 	</div>
 	<%@include file="/WEB-INF/jsp/normalAction/normalAddEdit.jspf" %>
 	<%@include file="/WEB-INF/jsp/quarantineAction/normalOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/surfaceAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/surfaceLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/virusAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/virusLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/parasiteAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/parasiteLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/bacteriaAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/bacteriaLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/vaccineAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/vaccineLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/infectiousAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/infectiousLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/tbAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/tbLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/xAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/xLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/xcgAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/xcgLook.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/xyshAdd.jspf" %>
    <%@include file="/WEB-INF/jsp/normalAction/xyshLook.jspf" %>
</div>
</body>
</html>