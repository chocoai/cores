<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/clinicalTestApply.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
	<script type="text/javascript">
		var tblClinicalTestReqTable;
		var studyNoPara;

		var addClinicalTestReqButton;
		var editClinicalTestReqButton;
		var readClinicalTestReqButton;
		var submitClinicalTestReqButton;
		var delClinicalTestReqButton;
		var resultClinicalTestReqButton;
		var printClinicalTestReqButton;
		var	member ;
		$(function(){
			studyNoPara=$('#studyNoPara').val();
			member= $('#left_member').val();
			if(member==''){
				addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('enable');
				$('#NewAddNewApplyButton').css('display','');
			}else{
				addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
			}
			
			editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
			readClinicalTestReqButton=$('#readClinicalTestReqButton').linkbutton('disable');
			submitClinicalTestReqButton=$('#submitClinicalTestReqButton').linkbutton('disable');
			delClinicalTestReqButton=$('#delClinicalTestReqButton').linkbutton('disable');
			resultClinicalTestReqButton=$('#resultClinicalTestReqButton').linkbutton('disable');
			printClinicalTestReqButton=$('#printClinicalTestReqButton').linkbutton('disable');

			tblClinicalTestReqTable=$('#tblClinicalTestReqTable').datagrid({
				url : sybp()+"/tblClinicalTestReqAction_loadList.action?studyNoPara="+encodeURIComponent($('#studyNoPara').val()),
				title:'',
				iconCls:'',//'icon-save',
				singleSelect:true,//只能选一行
				pagination:false,//下面状态栏
				//pageSize:50,
				//pageList:[50,100],
				fit:true,
				fitColumns:false,//不出现横向滚动条
				nowarp:  false,//单元格里自动换行
				border:false,
				idField:'id', //pk
				//sortName:'aniSerialNum',//排序字段
				//sortOrder:'desc',//排序方法
				columns :[[{
					title :'',
					field :'id',
					hidden:true
				},{
					title : '申请编号',
					field : 'reqNo',
					width : 60,
					formatter : function(value,rowData,rowIndex){
					    if(rowData.parentReqNo != 0 ){
					    	return '<span title="'+value+'">&nbsp;&nbsp;'+value+'</span>';
						    }else{
								return '<span title="'+value+'">'+value+'</span>';
						}
					 }
				},{
					title:'试验阶段',
					field:'testPhase',
					width:100,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title : '父类申请',
					field : 'parentReqNo',
					width : 60,
					hidden:true
					
				},{
					title:'开始日期',
					field:'beginDate',
					width:100,
					//formartter 一定要返回个字符串
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'结束日期',
					field:'endDate',
					width:100,
					//formartter 一定要返回个字符串
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'创建日期',
					field:'createDate',
					width:100,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title:'是否提交',
					field:'es',
					width:60,
					formatter : function(value,rowData,rowIndex){
						if(value == 1){
							return '是';
						}else{
							return '否';
						}
					}
				},{
					title:'其他检查项目',
					field:'testOther',
					width:150,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				},{
					title : 'temp',
					field : 'temp',
					width : 60,
					hidden:true
					
				},{
					title:'备注',
					field:'remark',
					width:200,
					formatter : function(value,rowData,rowIndex){
						return '<span title="'+value+'">'+value+'</span>';
					}
				}]],
			
						
				//工具栏
				toolbar:'#toolbar',
				//单击
				onClickRow : function(rowIndex, rowData){
					member = $('#left_member').val();
					if(rowData.es == 0){
						if(member==''){
							addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('enable');
							editClinicalTestReqButton.linkbutton('enable');
							submitClinicalTestReqButton.linkbutton('enable');
							delClinicalTestReqButton.linkbutton('enable');
							$('#NewAdditionalApplyButton').css('display','');  
							$('#NewAddNewApplyButton').css('display','');
							$('#EstablishAssociationButton').css('display','');   
							$('#CancelConjunctionButton').css('display',''); 
						}else{
							addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
							editClinicalTestReqButton.linkbutton('disable');
							submitClinicalTestReqButton.linkbutton('disable');
							delClinicalTestReqButton.linkbutton('disable');
						}
						
						readClinicalTestReqButton.linkbutton('disable');
						resultClinicalTestReqButton.linkbutton('disable');
						printClinicalTestReqButton.linkbutton('enable');
						
					}else{
						if(member==''){
							addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('enable');
							
							submitClinicalTestReqButton.linkbutton('disable');
							delClinicalTestReqButton.linkbutton('disable');
							$('#NewAdditionalApplyButton').css('display','');   
							$('#NewAddNewApplyButton').css('display','');  
							$('#EstablishAssociationButton').css('display','');   
							$('#CancelConjunctionButton').css('display',''); 
                            if( rowData.temp == 2){
                            	submitClinicalTestReqButton.linkbutton('disable');
    							readClinicalTestReqButton.linkbutton('disable');  
    							editClinicalTestReqButton.linkbutton('enable');
                            }else{
    							readClinicalTestReqButton.linkbutton('enable'); 
    							editClinicalTestReqButton.linkbutton('disable'); 
                            }
						}else{
							addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
							editClinicalTestReqButton.linkbutton('disable');
							submitClinicalTestReqButton.linkbutton('disable');
							delClinicalTestReqButton.linkbutton('disable');
							readClinicalTestReqButton.linkbutton('enable');
						}
						
						
						resultClinicalTestReqButton.linkbutton('enable');
						printClinicalTestReqButton.linkbutton('enable');
					}
					  $(this).datagrid('unselectAll');
					  $(this).datagrid('selectRow',rowIndex);
				      $(this).datagrid('refreshRow',rowIndex);
				},
				rowStyler: function(index,row){
					var rowColor=$('#rowColor').val();
					   if (row.temp == 2  ){
									return 'color:red;';								   
					   }else if(row.temp == 3  ){
							 return 'color:blue;'; 
				        }
				   },		
			});
			
			$('#ParentClassNumber').combobox({
				onChange: function(newValue, oldValue){
					$('#ParentClassNumber').combobox('setValue',newValue);
				}
			});
			$('#otherjsp').css('display','');  
			$('#toolbar').css('display','');
			$('#TotalNumber').css('display','');
		});
		/**编辑按钮方法*/
		function onEditClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			if(rows.length == 1){
				var row = rows[0];
				if(row.es == 0 ){
					window.location.href=sybp()+'/tblClinicalTestReqAction_clinicalTestApply.action?studyNoPara='
					+studyNoPara+'&reqNoPara='+row.reqNo;
				}else if(row.es == 1 && row.temp == 2){
                    //临时转为正式
					window.location.href=sybp()+'/tblClinicalTestReqAction_clinicalTestApply.action?studyNoPara='
					+studyNoPara+'&reqNoPara='+row.reqNo;
			    }else{
					//$.messager.alert('提示','已提交无法编辑');
					parent.parent.showMessager(2,'已提交无法编辑',true,5000);
				}
			}else{
				//$.messager.alert('提示','请选择行');
				parent.parent.showMessager(2,'请选择行',true,5000);
			}
		}
        /**临时转为正式*/
		function toformalClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			var row = rows[0];
			//临时转为正式
            window.location.href=sybp()+'/tblClinicalTestReqAction_toformal.action?studyNoPara='+studyNoPara+'&reqNoPara='+row.reqNo;
	    }
		
		/**删除按钮方法*/
		function onDelClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			if(rows.length == 1){
				var row = rows[0];
				if(row.es == 0){
					deleteConfirm(studyNoPara,row.reqNo);
				}else{
					//$.messager.alert('提示','已提交无法删除');
					parent.parent.showMessager(2,'已提交无法删除',true,5000);
				}
			}else{
				//$.messager.alert('提示','请选择行');
				parent.parent.showMessager(2,'请选择行',true,5000);
			}
		}
		/**提交按钮方法*/
		function onSubmitClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			if(rows.length == 1){
				var row = rows[0];
				if(row.es == 0){
					checkBeforeSign2(studyNoPara,row.reqNo);
				}else{
					//$.messager.alert('提示','不可以重复提交');
					parent.parent.showMessager(2,'不可以重复提交',true,5000);
				}
			}else{
				//$.messager.alert('提示','请选择行');
				parent.parent.showMessager(2,'请选择行',true,5000);
			}
		}
		/**检测结果按钮方法*/
		function onResultClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			if(rows.length == 1){
				var row = rows[0];
				if(row.es == 1){

					$.ajax({
	    				url:sybp()+'/tblClinicalTestReqAction_CheckTheSelectedRelationship.action',
	    				type:'post',
	    				cache:false,
	    				data:{
						parentReqNo1:row.reqNo,
	            	    studyNoPara:$('#studyNoPara').val()
	    				},
	    				dataType:'json',
	    				success:function(r){
	    				 if(r && r.success ){
	    					 $('#AdditionalSelectionBox').dialog('open');
	    					 $('#AdditionalSelectionBox1').dialog('open');
	    			      }else{
	    			    	  window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
	    						+studyNoPara+'&reqNoPara='+row.reqNo+"&isValidationPara=0"+"&isValidationPara1=1";
	        			  }
	    				}
	    				});
				}else{
					//$.messager.alert('提示','未提交无法查看检测结果');
					parent.parent.showMessager(2,'未提交无法查看检测结果',true,5000);
				}
			}else{
				//$.messager.alert('提示','请选择行');
				parent.parent.showMessager(2,'请选择行',true,5000);
			}
		}

		/**选择是否显示与本次申请相关的其他申请**/
        function AdditionalSelectionBox(){
            var select = $("input[name='select']:checked").val();
            var row =tblClinicalTestReqTable.datagrid('getSelected');            
            var studyNoPara = encodeURIComponent($('#studyNoPara').val());
            if(select == 0){
            	 window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
					+studyNoPara+'&reqNoPara='+row.reqNo+"&isValidationPara=0&isValidationPara1=1";
            }else{
            	window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
				+studyNoPara+'&reqNoPara='+row.reqNo+'&AdditionalApplications='+"all"+"&isValidationPara=0&isValidationPara1=1&combinedWithAnimal=0";
            	
            }
        }

		
		
		
		/**打印预览按钮方法*/
		function onPrintClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			if(rows.length == 1){
				var row = rows[0];
				outPort2(studyNoPara,row.reqNo);
			}else{
				//$.messager.alert('提示','请选择行');
				parent.parent.showMessager(2,'请选择行',true,5000);
			}
		}
		/**编辑按钮方法*/
		function onReadClinicalTestReqButton(){
			var rows =tblClinicalTestReqTable.datagrid('getSelections');
			if(rows.length == 1){
				var row = rows[0];
				if(row.es == 1){
					window.location.href=sybp()+'/tblClinicalTestReqAction_view.action?studyNoPara='
					+studyNoPara+'&reqNoPara='+row.reqNo;
				}else{
					//$.messager.alert('提示','未提交无法查看');
					parent.parent.showMessager(2,'未提交无法查看',true,5000);
				}
			}else{
				//$.messager.alert('提示','请选择行');
				parent.parent.showMessager(2,'请选择行',true,5000);
			}
		}

		function AddNewItionalApply(){
			var select = $('#tblClinicalTestReqTable').datagrid('getSelected');
			var parentReqNo = select.parentReqNo;
			var NewEs = select.es;
			if(NewEs  == '1'){
			if(parentReqNo == 0 ){
			 if(select == null){
				 $.messager.alert('提示','选择相关联的临检申请?');
			 }else{
		      $('#connectionNumber').val(select.reqNo);
			  $('#AdditionalApplicationsDialog').dialog('open');
			  $('#AdditionalApplicationsDialog2').dialog('open');
			  }
			}else{
			 $.messager.alert('提示','此申请已建立关联关系!');
			}}else{
			  $.messager.alert('提示','此申请未提交,无法建立附加申请!');
			}
		}

		function CorrelationOperation(){

			
             var select = $('#tblClinicalTestReqTable').datagrid('getSelected');
             $('#SubclassNumber').val(select.reqNo);

             var arrayObj = new Array(); 
             
             var rows = $('#tblClinicalTestReqTable').datagrid('getRows');
             
             if(select.parentReqNo != 0){
            	 $.messager.alert('提示','此申请已经建立关联关系!');
               }else{
            	 for(var i = 1;i<rows.length+1;i++){
            		 var NewReqNo =  $('#tblClinicalTestReqTable').datagrid('getRows')[i-1].reqNo;
            		 var NewEs =  $('#tblClinicalTestReqTable').datagrid('getRows')[i-1].es;
                     if(rows[i-1].parentReqNo == 0 && NewReqNo < select.reqNo && NewEs == '1'){
                	   arrayObj = arrayObj.concat({"text": NewReqNo, "id": NewReqNo});
                  	 }
                  }
                 $('#ParentClassNumber').combobox('setValue','');
                 $('#ParentClassNumber').combobox('loadData',arrayObj);
                 $('#AddCorrelationOperationDialog').dialog('open');
    			 $('#AddCorrelationOperationDialog2').dialog('open');
                 }
              
		}
        //建立关联关系验证
		function EstablishRelationShip(){
			var parentValue = $('#ParentClassNumber').combobox('getValue');
			var sunValue=$('#SubclassNumber').val();
			var rows = $('#tblClinicalTestReqTable').datagrid('getRows');
            if(parentValue==sunValue){
            	 $.messager.alert('提示','同一个申请不能建立关联关系!');
            }else if(parentValue >rows.length ){
            	 $.messager.alert('提示','请选择正确的申请序号!');
            }else{
            	$.ajax({
    				url:sybp()+'/tblClinicalTestReqAction_EstablishRelationShip.action',
    				type:'post',
    				cache:false,
    				data:{
            		parentReqNo1:parentValue,
            		sunReqNo:sunValue,
            	    studyNoPara:$('#studyNoPara').val()
    				},
    				dataType:'json',
    				success:function(r){
    				 if(r && r.success ){
						  qm_showQianmingDialog('ToConfirmTheAssociation');
    			      }else{
    			    	  $.messager.alert('提示','实验动物不匹配，无法建立关联关系!');
        			  }
    				}
    				});
            }
    	}
        //建立关联关系签字成功
		function ToConfirmTheAssociation(){
			var parentValue = $('#ParentClassNumber').combobox('getValue');
			var sunValue=$('#SubclassNumber').val();
			$.ajax({
				url:sybp()+"/tblClinicalTestReqAction_ToConfirmTheAssociation.action?studyNoPara="+encodeURIComponent($('#studyNoPara').val())+"&esType=13",
				type:'post',
				data:{
            		parentReqNo1:parentValue,
            		sunReqNo:sunValue
    				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						parent.parent.showMessager(1,'签字成功',true,5000);
						window.location.href= sybp()+"${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara="+encodeURIComponent($('#studyNoPara').val());
					}else{
					    parent.parent.showMessager(3,'签字失败',true,5000);
					}
				}
			});
			}

       //解除关联关系
		function LiftTheRelationship(){
			 var select = $('#tblClinicalTestReqTable').datagrid('getSelected');
		     var parentReqNo = select.parentReqNo;
	         
			if(parentReqNo != 0){
			$.messager.confirm('确认对话框', '确认解除关联关系?', function(r){
				if (r){
					qm_showQianmingDialog('ToLiftTheRelationship');
				}
			});
			}else{
				$.messager.alert('提示','此申请不存在关联关系,请检查数据!');
			}
       }
		function ToLiftTheRelationship(){
			var select = $('#tblClinicalTestReqTable').datagrid('getSelected');
		    var parentReqNo = select.parentReqNo;
			var sunValue = select.reqNo;
			$.ajax({
				url:sybp()+"/tblClinicalTestReqAction_ToConfirmTheAssociation.action?studyNoPara="+encodeURIComponent($('#studyNoPara').val())+"&esType=14",
				type:'post',
				data:{
            		sunReqNo:sunValue
    				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						parent.parent.showMessager(1,'签字成功',true,5000);
						window.location.href= sybp()+"${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara="+encodeURIComponent($('#studyNoPara').val());
					}else{
					    parent.parent.showMessager(3,'签字失败',true,5000);
					}
				}
			});

			}
	</script>
</head>
<body>
<s:hidden id="left_member" name="left_member"></s:hidden>
<s:hidden id="studyNo"></s:hidden>
<s:hidden id="reqNo"></s:hidden>
<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
<s:hidden id="rowColor" name="rowColor"></s:hidden>
<div class="easyui-layout" fit="true" border="false" id="TotalNumber" style="display:none;" >
		<!-- table start -->
		<div region="center" border="false" style="border: 1px solid #c8c8c8;">
			<table id="tblClinicalTestReqTable" ></table>
		</div>
		<div   region="south" border="false" style="height:30px; border-bottom:  1px solid #c8c8c8;border-right:  1px solid #c8c8c8;border-left:  1px solid #c8c8c8;display:none; ">
		<!-- 状态栏  开始 -->
			<div style="width:100%;height:20px; padding-top:10px;">
			<span style="position:absolute;right:20px;">共&nbsp;${listSize}&nbsp;条记录</span>
			</div>
		<!-- 状态栏  结束 -->
		</div>
		<!-- table end -->
	<div id="toolbar" style="display:none;">
	   <!-- 
	   <a id="addClinicalTestReqButton" href="tblClinicalTestReqAction_clinicalTestApply.action?studyNoPara=${studyNoPara}" class="easyui-linkbutton" onclick="" data-options="iconCls:'icon-add',plain:true">新增</a>
	    -->
	   
	   <a id="addClinicalTestReqButton" href="tblClinicalTestReqAction_clinicalTestApply.action?studyNoPara=${studyNoPara}" class="easyui-splitbutton"  onclick=""  data-options="menu:'#menu0',iconCls:'icon-add',plain:true"  >新增</a>  
	        <div id="menu0"  class="easyui-menu" data-options="minWidth:'100px'" >   
				<div id="NewAddNewApplyButton"  style="display:none" data-options="iconCls:'icon-add'" style="width:110px;"  onclick=""><a href="tblClinicalTestReqAction_clinicalTestApply.action?studyNoPara=${studyNoPara}" style="color: #000000" >新增申请</a></div>   
				<div id="NewAdditionalApplyButton"  style="display:none" data-options="iconCls:'icon-add'" style="width:110px;"  onclick="AddNewItionalApply();">新建附加申请</div>   
				<div id="EstablishAssociationButton"  style="display:none" data-options="iconCls:''" onclick="CorrelationOperation();" style="width:110px;">关联到...</div> 
				<div id="CancelConjunctionButton" style="display:none" data-options="iconCls:''" onclick="LiftTheRelationship();" style="width:110px;">取消关联</div>
			</div> 
	       	

		<a id="editClinicalTestReqButton" class="easyui-linkbutton" onclick="onEditClinicalTestReqButton();" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		
		<a id="readClinicalTestReqButton" class="easyui-linkbutton" onclick="onReadClinicalTestReqButton();" data-options="iconCls:'icon-edit',plain:true">查看</a>
		
		<a id="submitClinicalTestReqButton" class="easyui-linkbutton" onclick="onSubmitClinicalTestReqButton();" data-options="iconCls:'icon-redo',plain:true">提交</a>
			
		<a id="delClinicalTestReqButton" class="easyui-linkbutton" onclick="onDelClinicalTestReqButton();" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<a id="resultClinicalTestReqButton" class="easyui-linkbutton" onclick="onResultClinicalTestReqButton();" data-options="iconCls:'icon-edit',plain:true">检测结果</a>
		<a id="printClinicalTestReqButton" class="easyui-linkbutton" onclick="onPrintClinicalTestReqButton();" data-options="iconCls:'icon-print',plain:true">打印预览</a>
	</div>
	<!--
	<div id="toolbar2" style="display:none;">
		<a id="readClinicalTestReqButton" class="easyui-linkbutton" onclick="onReadClinicalTestReqButton();" data-options="iconCls:'icon-edit',plain:true">查看</a>
		<a id="resultClinicalTestReqButton" class="easyui-linkbutton" onclick="onResultClinicalTestReqButton();" data-options="iconCls:'icon-edit',plain:true">检测结果</a>
		<a id="printClinicalTestReqButton" class="easyui-linkbutton" onclick="onPrintClinicalTestReqButton();" data-options="iconCls:'icon-print',plain:true">打印预览</a>
	</div>
	  -->
	
	
	</div>
	<div id="otherjsp" style="display:none;">
     <!-- 是否显示相关联的临检申请的  弹窗开始 -->
	<div id="AdditionalSelectionBox" class="easyui-dialog" title="选择检测结果数据" modal="true" closed="true" draggable="false" style="width:320px;height:180px;" closable="true">
	<div id="AdditionalSelectionBox2" >
		
			<table style="border:1px solid #EBEBEB;border-collapse:collapse;position: absolute;top:40px;left:20px;width:275px">
				<tr><td><input type="radio" name="select" value="0" id="checkboxone" checked="true"/></td><td height="30px;">只显示本次申请数据</td></tr>
				<tr><td><input type="radio" name="select" value="1" id="checkboxother"/></td><td height="30px;">显示与本次申请相关联的所有数据</td></tr>
			</table>
			<div style="border-collapse:collapse;position: absolute;top:120px;left:80px;width:275px">
			<a id="EnsureButton" class="easyui-linkbutton" onclick="AdditionalSelectionBox();" data-options="">确定</a>
			&nbsp;&nbsp;
			<a id="CancelButton" class="easyui-linkbutton" onclick="javascript:$('#AdditionalSelectionBox').dialog('close');" data-options="">取消</a>
	        </div>
	</div>
	</div>
    
    <%@include file="/WEB-INF/jsp/clinicalTestReqAction/CorrelationOperation.jspf" %>
    <%@include file="/WEB-INF/jsp/clinicalTestReqAction/AddNewItionalApply.jspf" %>
    </div>
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</body>
</html>
