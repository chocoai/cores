<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
		var tableHeight;//当前页面可见区域高度 - 86
		var tableWidth;
		var oneStudyResTable ;
		var studyNo;
		$(function(){
			tableHeight = document.body.clientHeight - 86;
			tableWidth  = document.body.clientWidth;
			studyNo = $('#studyNo').val();
			oneStudyResTable=$('#oneStudyResTable').datagrid({
				url : sybp()+'/tblStudyResAction_loadOneStudyRes.action?studyNo='+studyNo,
				title:'',
				height:tableHeight,
				width:tableWidth,
				iconCls:'',//'icon-save',
				nowarp:  false,//单元格里自动换行
				fit:false,
				striped:true,
				fitColumns:false,
				pagination:false,
				singleSelect:false,
				columns :[[{
					title:'',
					field:'id',
					width:150,
					hidden:true
				},{
					title:'专题编号',
					field:'studyNo',
					width:150,
					halign:'center',
					align:'center'
				},{
					title:'房间',
					field:'resName',
					width:150,
					halign:'center',
					align:'center'
				},{
					title:'确认人',
					field:'signer',
					width:80,
					halign:'center',
					align:'center'
				}]],
				//工具栏
				toolbar:'#toolbar',
				onClickRow:function(rowIndex, rowData){
					var rows = $('#oneStudyResTable').datagrid('getSelections');
					for(var i = 0; i < rows.length;i++){
					    if(rows[i].signer == '' || rows[i].signer == null){
					      $('#delResManagerButton').linkbutton('enable');
				    	  $('#signResManagerButton').linkbutton('enable');
					   }else{
					      $('#delResManagerButton').linkbutton('disable');
				    	  $('#signResManagerButton').linkbutton('disable');
				    	  break;
					   }
					}
			    },
				onSelect :function(rowIndex, rowData){
				   
				    //if(rowData.signer == '' || rowData.signer == null){
				    //	$('#delResManagerButton').linkbutton('enable');
				    //	$('#signResManagerButton').linkbutton('enable');
					//}else if(rowData.endDate !='' && rowData.endDate !=null){
				    //	$('#delResManagerButton').linkbutton('disable');
				    //	$('#signResManagerButton').linkbutton('disable');
					//}else{
				    //	$('#delResManagerButton').linkbutton('disable');
				    //	$('#signResManagerButton').linkbutton('disable');
					//}
			    },
			    onLoadSuccess:function(data){
				    if(data.rows.length<1){
				    	onSelectButton();
					}
				}
			   
		   	}); //end datagrid
			
		  //显示整个布局
			$('#layoutMainDiv').css('display','');	

			
			//按钮置为无效
	    	$('#delResManagerButton').linkbutton('disable');
	    	$('#signResManagerButton').linkbutton('disable');
	    	
	    	
				   	
		});//匿名函数结束

		//添加按钮事件（打开选择窗口）
		function onSelectButton(){
		    onlyChooseAnimalHouseRes();
		    $('#selectStudyResDialog').dialog('open');
			$('#selectStudyResDialog2').dialog('open');
		}
		//只可以选择房间但可以多选
		function onlyChooseAnimalHouseRes(){
			AnimalHouseTable= $('#AnimalHouseTable').treegrid({ 
				url : sybp()+'/tblAnimalHouseAction_loadList4.action?rid='+$('#pResID').val(),
				idField:'id',    
			    treeField:'resName', 
			    height: 280,
				width:260,
			    animate:false,   
			    singleSelect: false, //支持多选
			    columns:[[  
			                {title:'id',field:'id',width:10,hidden:true},
					        {field:'resKind',title:'resKind',width:10,hidden:true}, 
					        {title:'资源名称',field:'resName',width:235},    
					        {field:'_parentId',title:'_parentId',width:10,hidden:true},
					    ]],
					  //工具栏
						toolbar:'#toolbar',
				    	onClickRow :function(row){
				           var resKind = row.resKind;
				           if(resKind != "3"){
				        	   var cid = $('#AnimalHouseTable').treegrid('getChildren',row.id);
				        	   if(cid.length>0){
				        		   $('#AnimalHouseTable').treegrid('expandTo',cid[0].id);
				        	   }
				        	   $('#AnimalHouseTable').treegrid('unselectRow',row.id);
				           }
				       },
				       onCheckAll:function(){
				    	   $.messager.alert('提示','请选择具体房间!','info');
				    
				       }
				       
			 });
		}
		//选择房间窗口上的 确定按钮
		function saveStudyRes(){
			var nodes = $('#AnimalHouseTable').treegrid('getSelections');
			if(nodes.length < 1){
				$.messager.alert('提示','请先选择房间!','info');
			}else{
				 var ary = new Array();
		         for(var j=0;j<nodes.length;j++){
						var id  =  nodes[j].id;
						ary = ary.concat(id);
				 }
				 var ids = ary.join(","); 
				 $.ajax({
						url:sybp()+'/tblStudyResAction_addStudyRes.action',
						type:'post',
						data:{
							resIds:ids,
							studyNo:studyNo
							},
						dataType:'json',
						success:function(r){
								if(r && r.success){
									oneStudyResTable.datagrid('reload');
									$('#selectStudyResDialog').dialog('close');
									parent.parent.showMessager(1,r.msg,true,5000);
									
									var ids = r.obj;
									$('#signAllIds').val(ids)
									$.messager.defaults = { ok: "是", cancel: "否" }; 
									
					                $.messager.confirm('确认','是否签字提交!',function(r){    
					                		  if (r){
					                			 qm_showQianmingDialog('afterSignAll');
					                		  }else{
					                		  }    
					                }); 
									
								}else{
									$.messager.alert('警告',r.msg);
								}
							}
					});
		    }
			
		}
		
	 function afterSignAll(){
		  var ids = $('#signAllIds').val();
		  $.ajax({
					url:sybp()+'/tblStudyResAction_signStudy.action',
					type:'post',
					data:{
						allStudyRes:ids
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
							oneStudyResTable.datagrid('reload');
								//按钮置为无效
						    	$('#delResManagerButton').linkbutton('disable');
						    	$('#signResManagerButton').linkbutton('disable');
						    	
								parent.parent.showMessager(1,'签字成功',true,5000);
							}else{
									$.messager.alert('警告',"与数据库交互异常");
							}
						}
				});
		}
		//删除
		function onDelButton(){
		    var rowData = oneStudyResTable.datagrid('getSelections');
			if(null != rowData &&  rowData.length > 0){
			    $.messager.confirm('确认对话框', '是否确认删除？', function(r){
					if(r){
					   onDelect();
					}
				});
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		//真删除
		function onDelect(){
			var rows = oneStudyResTable.datagrid('getSelections');
			if(null != rows  &&  rows.length > 0){
			    var ary = new Array();
				for(var j=0;j<rows.length;j++){
						if(rows[j].signer==""||rows[j].signer==null){
										var gettaskName  =  rows[j].id;
										ary = ary.concat(gettaskName);
						}
					}
				var getSelections = ary.join(",");
					$.ajax({
						url:sybp()+'/tblStudyResAction_delete.action',
						type:'post',
						data:{
							id:getSelections
						},
						dataType:'json',
						success:function(r){
								if(r && r.success){
									//var rowIndex = oneStudyResTable.datagrid('getRowIndex',rowData);
									//oneStudyResTable.datagrid('deleteRow',rowIndex);
				    				//按钮置为无效
							    	$('#delResManagerButton').linkbutton('disable');
							    	$('#signResManagerButton').linkbutton('disable');
							    	oneStudyResTable.datagrid('reload');
									parent.parent.showMessager(1,'删除成功',true,5000);
								}else{
									$.messager.alert('警告',r.msg);
								}
							}
					});
				}else{
					$.messager.alert('警告','请先选择数据');
				}
		}
		//签字
		function onSignButton(){
			var rowData = oneStudyResTable.datagrid('getSelections');
			if(null != rowData &&  rowData.length > 0){
			    $.messager.confirm('确认对话框', '确认签字后将不可更改，是否确认签字？', function(r){
					if (r){
					  	qm_showQianmingDialog('afterSign');
					}
				});
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		//签字事件
		function afterSign(){
			var rows = oneStudyResTable.datagrid('getSelections');
			if(null != rows  &&  rows.length > 0){
			    var ary = new Array();
				for(var j=0;j<rows.length;j++){
						if(rows[j].signer==""||rows[j].signer==null){
										var gettaskName  =  rows[j].id;
										ary = ary.concat(gettaskName);
						}
					}
				var getSelections = ary.join(",");
				$.ajax({
					url:sybp()+'/tblStudyResAction_sign.action',
					type:'post',
					data:{
						id:getSelections
						},
					dataType:'json',
					success:function(r){
							if(r && r.success){
								oneStudyResTable.datagrid('reload');
								//按钮置为无效
						    	$('#delResManagerButton').linkbutton('disable');
						    	$('#signResManagerButton').linkbutton('disable');
						    	
								parent.parent.showMessager(1,'签字成功',true,5000);
							}else{
								$.messager.alert('警告',r.msg);
							}
						}
				});
			}else{
				$.messager.alert('警告','请先选择数据');
			}
		}
		
		//回到list页面
		function onExitButton(){
			window.location.href=sybp()+'/tblStudyResAction_list.action?studyNo='+studyNo+'&isSelectAllStudy=1';
		}
		
</script>
</head>
<body>
<s:hidden id="studyNo" name="studyNo"/>	
<s:hidden id="pResID" name="pResID"/>	
<s:hidden id="signAllIds" name="signAllIds"/>	
<div id="layoutMainDiv" class="easyui-tabs" fit="true" border="true" style="display:none">
	<div title="专题房间分配"  border="false" style="overflow:hidden;">
		<!-- 工具栏-start -->
		<div style="height: 27px; padding-left: 5px; padding-top: 1px; border: 1px solid #c9c9c9;">
			<a id="addResManagerButton" class="easyui-linkbutton"  plain="true"
						onclick="onSelectButton();" data-options="iconCls:'icon-houseadd'">添加</a>

			<a id="delResManagerButton" class="easyui-linkbutton" plain="true"
						onclick="onDelButton();" data-options="iconCls:'icon-housedel'">删除</a>
			<a id="signResManagerButton" class="easyui-linkbutton" plain="true"
						onclick="onSignButton();" data-options="iconCls:'icon-edit'">签字确认</a>
			<a id="exitButton" class="easyui-linkbutton" plain="true"
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
		</div>
		<!-- 工具栏-end-->
		<div style="border-bottom: 1px solid #c8c8c8; height:20px;margin-top:5px;">
			&nbsp;&nbsp;&nbsp;&nbsp;专题编号：<s:property value="{#studyNo}"/>
		</div>
		<div>
			<table id="oneStudyResTable" ></table>
		</div>
		<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
		 <%@include file="/WEB-INF/jsp/tblStudyResAction/selectStudyRes.jspf" %>
	</div>
</div>

</body>
</html>