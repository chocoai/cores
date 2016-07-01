<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblAnimal.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimal.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript">
var talAnimalTable;
var studyNoPara=$('#studyNoPara').val();
$(function(){
	editRow = 'undefined';
	talAnimalTable=$('#talAnimalTable').datagrid({
		url : sybp()+"/tblAnimalAction_loadList.action?studyNoPara="+$('#studyNoPara').val(),
		title:'',
		//height: 240,
		//width:560,
		iconCls:'',//'icon-save',
		pagination:false,//下面状态栏
		//pageSize:50,
		//pageList:[50,100],
		fit:true,
		fitColumns:false,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		border:false,
		idField:'id', //pk
		singleSelect:true,
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
		columns :[[{
			title :'',
			field :'id',
			//checkbox:true
			hidden:true
		},{
			title : 'No.',
			field : 'aniSerialNum',
			width : 30,
		},{
			title:'专题编号',
			field:'studyNo',
			width:100
		},{
			title:'动物ID号',
			field:'animalId',
			width:100,
			//formartter 一定要返回个字符串
			formatter : function(value,rowData,rowIndex){
				return '<span title="'+value+'">'+value+'</span>';
			}
		},{
			title:'动物编号',
			field:'animalCode',
			width:100,
			editor:{
				type:'validatebox',
				options:{
					required:true
				}
			},
			//formartter 一定要返回个字符串
			formatter : function(value,rowData,rowIndex){
				return '<span title="'+value+'">'+value+'</span>';
			}
		},{
			title:'性别',
			field:'gender',
			width:50,
			formatter :function(value,rowData,rowIndex){
				if(value == 1){
					return '♂';
				}else if(value == 2){
					return '♀';
				}else {
					return '';
				}
			}
		},{
			title:'体重',
			field:'weight',
			width:70
		},{
			title:'计划解剖次数',
			field:'dissectBatch',
			width:80,
			formatter:function(value,rowData,rowIndex){
				if(value==0){
					return '';
				}else{
					return value;
				}
			}
		}]],
		//工具栏
		toolbar:'#toolbar',
		//单击事件
		onClickRow : function(rowIndex, rowData){
			$('#OneCurrentSerialNum').combobox('select',rowData.aniSerialNum);
	 		$('#currentSerialNum').combobox('select',rowData.aniSerialNum);
	 		$('#currentSerialNum2').combobox('select',rowData.aniSerialNum);
		}
	}); //end datagrid

	//表头居中
	$('.datagrid-header div').css('textAlign','center');
	//管理按钮状态
	manageButtonState();
	//序号下拉框初始化 
	initSerialNum();
	//回车事件
	$('#oneMinAnimalId').bind('keyup',function(e){
		if(e.keyCode == '13'){
			$('#oneMinAnimalId').blur();
			submitAddOneForm();
		}
	});
	$('#minAnimalId').bind('keyup',function(e){
		if(e.keyCode == '13'){
			$('#minAnimalId').blur();
			submitMuchOneForm();
		}
	});
	$('#maxAnimalId').bind('keyup',function(e){
		if(e.keyCode == '13'){
			$('#maxAnimalId').blur();
			submitMuchOneForm();
		}
	});
	var isBigAnimal = $('#isBigAnimal').val();
	//alert(" functionisBigAnimal is "+isBigAnimal);
	if(isBigAnimal=='false')
	{
		$('#exampleImage1').css('display','');
		$('#exampleImage2').css('display','none');
		$('#bigAnimalSexTD').css('display','none');
		$('#oneBigAnimalSexTD').css('display','none');
	}
	else if(isBigAnimal=='true')
	{
		$('#exampleImage1').css('display','none');
		$('#exampleImage2').css('display','');
		$('#bigAnimalSexTD').css('display','');
		$('#oneBigAnimalSexTD').css('display','');
	}

});//匿名函数   end

	//管理按钮状态
	function manageButtonState(){
		var isAnimalIdES=$('#isAnimalIdES').val();
		if(isAnimalIdES == 1 && isAnimalCodeES == 1){
			$('#animalIdButton').linkbutton('disable');
		}
	}
	//序号下拉框初始化 
	function initSerialNum(){
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblAnimalAction_initSerialNum.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"json",
	        data:"studyNoPara="+$('#studyNoPara').val(),
	        async:false,
	        success:function(r)
	        {
				if(r){
					//
					$('#OneCurrentSerialNum').combobox('loadData',r.serialNumList);
					//$('#OneCurrentSerialNum').combobox({
					//	panelHeight:r.serialNumList.length*20,
					//});
					$('#OneCurrentSerialNum').combobox('select',r.currentSerialNum);
					//
					$('#currentSerialNum').combobox('loadData',r.serialNumList);
					//$('#currentSerialNum').combobox({
					//	panelHeight:r.serialNumList.length*20,
					//});
					$('#currentSerialNum').combobox('select',r.currentSerialNum);
					$('#currentSerialNum2').combobox('loadData',r.serialNumList);
					//$('#currentSerialNum').combobox({
					//	panelHeight:r.serialNumList.length*20,
					//});
					$('#currentSerialNum2').combobox('select',r.currentSerialNum);
				}
	        },
	        error:function()
	        {
	             parent.parent.showMessager(3,'与数据库交互错误',false);
	        }
	   });
	}
	/** 签字检查*/      
	function checkBeforeAnimalIdSign(){
		var studyNoPara =$('#studyNoPara').val();
		if(studyNoPara == '' || studyNoPara == null){
			return;
		}else {
			$.ajax({
		        type:"POST",
		        url:"${pageContext.request.contextPath}/tblAnimalAction_checkMaleFemaleSize.action",
		        contentType: "application/x-www-form-urlencoded; charset=utf-8",
		        dataType:"json",
		        data:"studyNoPara="+studyNoPara,
		        async:false,
		        success:function(r)
		        {
		            if(r.maleSize<r.minMaleSize||r.femaleSize<r.minFemaleSize)
			        {
		        		$.messager.alert("提示","总共有id号的雄性是"+r.maleSize+"个，雌性"+r.femaleSize+"个.</br>"+"剂量组需要的雄性"+r.minMaleSize+"个，雌性"+r.minFemaleSize+"个");
			        }else{
			        	$.ajax({
					        type:"POST",
					        url:"${pageContext.request.contextPath}/tblAnimalAction_signCheck.action",
					        contentType: "application/x-www-form-urlencoded; charset=utf-8",
					        dataType:"json",
					        data:"studyNoPara="+studyNoPara,
					        async:false,
					        success:function(r)
					        {
								if(r && r.success){
									if(r.animalIdError){
										 parent.parent.showMessager(2,r.animalIdError,true,5000);
										 //$("#errorMsg").html(r.animalIdError);
									}else if(r.msg){
										parent.parent.showMessager(2,r.msg,true,5000);
										 //$("#errorMsg").html(r.msg);
									}else{
										//$("#errorMsg").html('');
										$.messager.confirm('确认对话框', '确认动物ID号录入完毕过后，动物ID号将不可以再编辑，确定继续吗？', function(r){
												if (r){
													qm_showQianmingDialog('eventAfterAnimalIdSign');
												}
										});
									}
								}
					        },
					        error:function()
					        {
					             //$("#errorMsg").html("与服务器交互错误!");
					             parent.parent.showMessager(3,'与服务器交互错误!',true,5000);
					        }
					   });
				    }
			    }
			});
		
		
		}
		
	}
	/**签字过后方法*/
	function eventAfterAnimalIdSign(){
		$.ajax({
			url:sybp()+"/tblAnimalAction_animalIdSign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=5",
			type:'post',
			dataType:'json',
			success:function(r){
				if(r && r.success){
					var aclick=document.getElementById("aclick")
					aclick.click();
					parent.parent.showMessager(1,'签字成功',true,5000);
				}else{
				    parent.parent.showMessager(3,'签字失败',true,5000);
				}
			}
		});
	}
	/**显示动物编号批量导入Dialog*/
	function showIdFileDialog(){
		var rows =$('#talAnimalTable').datagrid('getRows');
		var noFull = false;
		for(var i=0;i<rows.length;i++){
			if(rows[i].animalId !=''){
				noFull=true;
				break;
			}
		}
		if(noFull){
			$.messager.confirm('确认对话框', '动物Id号批量导入将覆盖原有数据，确定继续吗？', function(r){
				if (r){
					/*Dialog*/
					document.getElementById("idFileDialog2").style.display="block";
					$('#idFileDialog').dialog('open');
				}
			});
		}else{
			/*Dialog*/
			document.getElementById("idFileDialog2").style.display="block";
			$('#idFileDialog').dialog('open'); 
		}
	}
	/**执行文件上传操作*/
	function ajaxFileUpload(){
		$('#errorSpan').html('');
		var isBigAnimal = $('#isBigAnimal').val();
		var methodName;
		if(isBigAnimal=='false')
			methodName='importIdExcel';
		if(isBigAnimal=='true')
			methodName='importIdExcelBigAnimal';
		if($('#excelCodeFile').val()!=''){
	         $.ajaxFileUpload({
		          url:sybp()+'/tblAnimalAction_'+methodName+'.action?studyNoPara='+$('#studyNoPara').val(),            //需要链接到服务器地址
		          secureuri:false,
		          fileElementId:'excelCodeFile',                        //文件选择框的id属性
		          dataType: 'json',                                     //服务器返回的格式，可以是xml
		          success: function (data, status){     
		              if(data &&data.success){
		            	  $('#idFileDialog').dialog('close'); 
		            	  for(var i = 0;i<data.animalMapList.length;i++){
								$('#talAnimalTable').datagrid('updateRow',{
						 			index: data.animalMapList[i].aniSerialNum-1,
						 			row: data.animalMapList[i]
						 		});
								$('#talAnimalTable').datagrid('selectRow',data.animalMapList[i].aniSerialNum-1);
							}
							
							$('#OneCurrentSerialNum').combobox('select',data.currentSerialNum);
					 		$('#currentSerialNum').combobox('select',data.currentSerialNum);
					 		$('#currentSerialNum2').combobox('select',data.currentSerialNum);
					 		
					 		parent.parent.showMessager(1,data.msg,true,5000);
		            	  
		              }else{
		            	$('#errorSpan').html(data.msg);
		              }
		          },
		          error: function (data, status, e){
		        	  $('#errorSpan').html('文件上传失败，请刷新页面后重试');
		          }
	      	});
		}else{
			$('#errorSpan').html('请选择待上传的文件');
		}
    }
    //上移按钮事件
    var talAnimalTable = $('#talAnimalTable');
    function onUpButton()
    {
    	var row =talAnimalTable.datagrid('getSelected');
		var rowIndex;
    	if(row != null){
			//获得行索引
			rowIndex =talAnimalTable.datagrid('getRowIndex',row);
			if(rowIndex > 0){
				//下一个数据
				var nextRowData = talAnimalTable.datagrid('getRows')[rowIndex-1];
				$.ajax({
					url:sybp()+'/tblAnimalAction_moveOrder.action',
					type:'post',
					data:{
						currentAnimalId:row.id,
						nextAnimalId:nextRowData.id
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							talAnimalTable.datagrid('updateRow',{
					 			index: rowIndex,
					 			row: r.nextRow
					 		});
							talAnimalTable.datagrid('updateRow',{
					 			index: rowIndex-1,
					 			row: r.currentRow
						 		
					 		});
							talAnimalTable.datagrid('selectRow',rowIndex-1);
							parent.showTopMessager(r.msg,true,5000);
						}else{
							parent.showTopMessager(r.msg,true,5000);
						}
					}
				});
			}else{
				parent.showMessager(2,'已经是第一行，无法再移',true,5000);
			}
		}else{
			parent.showMessager(2,'请先选择行',true,5000);
		}
		
    }
  //下移按钮事件
    function onDownButton()
    {
    	var row =$('#talAnimalTable').datagrid('getSelected');
		var rowIndex;
    	if(row != null){
			//获得行索引
			rowIndex =$('#talAnimalTable').datagrid('getRowIndex',row);
			var totalRowNum = $('#talAnimalTable').datagrid('getRows').length;  
			if(rowIndex < totalRowNum-1){
				//下一个数据
				var nextRowData = $('#talAnimalTable').datagrid('getRows')[rowIndex+1];
				$.ajax({
					url:sybp()+'/tblAnimalAction_moveOrder.action',
					type:'post',
					data:{
						currentAnimalId:row.id,
						nextAnimalId:nextRowData.id
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							
							$('#talAnimalTable').datagrid('updateRow',{
					 			index: rowIndex,
					 			row: r.nextRow
					 		});
							$('#talAnimalTable').datagrid('updateRow',{
					 			index: rowIndex+1,
					 			row: r.currentRow
					 		});
							$('#talAnimalTable').datagrid('selectRow',rowIndex+1);
							parent.showTopMessager(r.msg,true,5000);
						}else{
							parent.showTopMessager(r.msg,true,5000);
						}
					}
				});
			}else{
				parent.showMessager(2,'已经是最后一行，无法下移',true,5000);
			}
		}else{
			parent.showMessager(2,'请先选择行',true,5000);
		}
		
    }
</script>
</head>
<body>
<s:hidden id="isAnimalIdES" name="isAnimalIdES"></s:hidden>
<s:hidden id="listSize" name="listSize"></s:hidden>
<s:hidden id="isBigAnimal" name="isBigAnimal"></s:hidden>
<!-- 跳转到试验计划主页，如后转到animalList.jsp -->
<a id="aclick"  href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara=${studyNoPara}&toWhere=animalList" target="main"></a>
<div class="easyui-layout" fit="true" border="false" >
		<div region="center" border=false >
		<!-- table start -->
				<table id="talAnimalTable" ></table>
		<!-- table end -->
		</div>
		<div region="south" border="false" style="height:180px; width:100%">
			<!-- 状态栏  开始 -->
			<div style="width:100%;height:20px;border-top:1px solid #c8c8c8; padding-top:10px; ">
			<span id="errorMsg" style="color:red;position:absolute;left:20px;"></span>
			<span style="position:absolute;right:20px;">共&nbsp;${listSize}&nbsp;条记录</span>
			</div>
			<!-- 状态栏  结束 -->
			<div id="tabs" class="easyui-tabs" fit="true"  style="width:100%;height:100%;">   
			    <div title="Id号录入" style="padding:10px 20px;display:'';">  
			    	<s:form id="addOneForm" action="tblAnimalAction_editOneAnimalIdSave" method="post">
					<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden> 
			        <table width="440px;">
			        	<tr>
			        		<td width="80px">序号</td>
			        		<td width="100px">
			        			<input id="OneCurrentSerialNum" name="currentSerialNum" style="width:60px;text-align:left"
			        			class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false" required="true" missingMessage="请选择序号" />
			        		</td>
			        		<td width="270px;"></td>
			        	</tr>
			        	<tr>
			        		<td>动物Id号</td>
			        		<td><input id ="oneMinAnimalId" name="minAnimalId" class="easyui-validatebox" onfocus= "this.select();" required="true" type="text"  style="width:80px;"/>
			        		</td>
			        		<td id="oneBigAnimalSexTD" style="display:'';">动物性别
				        		
								<input value="1"  name="minAnimalSex" checked="checked" type="radio">雄</input>
								<input value="2"  name="minAnimalSex" type="radio">雌</input>
							</td>
			        		
			        	</tr>
			        	<tr>
			        		<td></td>
			        		<td><a id="submitAddOneButton" class="easyui-linkbutton" onclick="submitAddOneForm();" >确定</a></td>
			        		<td></td>
			        	</tr>
			        </table>
			        </s:form>
			    </div>   
			    <div title="Id号连续录入" data-options="closable:false" style="overflow:auto;padding:10px 20px;display:'';">   
			        <s:form id="addMuchForm" action="tblAnimalAction_editMuchAnimalIdSave" method="post">
					<s:hidden  name="studyNoPara"></s:hidden>
			        <table width="440px;">
			        	<tr>
			        		<td width="80px">序号</td>
			        		<td width="100px;">
			        		<input id="currentSerialNum" name="currentSerialNum" style="width:60px;text-align:left;"
			        			class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false"  required="true" missingMessage="请选择序号" />
			        			&nbsp;&nbsp;&nbsp;&nbsp;至 
			        		</td>
			        		<td width="270px;">
				        		<input id="currentSerialNum2" name="currentSerialNum2" style="width:60px;text-align:left"
				        			class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false"  required="true" missingMessage="请选择序号" />
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>起始动物Id号</td>
			        		<td>
			        			<input id ="minAnimalId" name="minAnimalId" class="easyui-numberbox" required="true" type="text" data-options="validType:'signlessInteger',max:999999999" style="width:80px;"/>
			        		</td>
			        		<td id="bigAnimalSexTD" style="display:'';">动物性别
				        		
								<input value="1"  name="minAnimalSex" checked="checked" type="radio">雄</input>
								<input value="2"  name="minAnimalSex" type="radio">雌</input>
							</td>
			        		
			        		
			        		
			        	</tr>
			        	<tr>
			        		<td></td>
			        		<td><a class="easyui-linkbutton" onclick="submitMuchOneForm();" >确定</a></td>
			        		<td></td>
			        	</tr>
			        </table>
			        </s:form>
			    </div>   
			</div> 
	         
		</div>
</div>
	<div id="toolbar">
  		<a id="signButton" class="easyui-linkbutton" onclick="checkBeforeAnimalIdSign();" data-options="iconCls:'icon-ok',plain:true" >确认动物ID号录入完毕</a>
  		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'',plain:true" onclick="showIdFileDialog();">动物Id号批量导入</a>
        <a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpButton();" data-options="iconCls:'icon-undo',plain:true">上移</a>
		<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownButton();" data-options="iconCls:'icon-redo',plain:true">下移</a>
        <a class="easyui-linkbutton" href="${pageContext.request.contextPath}/tblAnimalAction_animalList.action?studyNoPara=${studyNoPara}" data-options="iconCls:'icon-back',plain:true">返回</a>
        <font color="red"><span id="span1"></span></font>
    </div>
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%> 
    <!-- 动物Id号批量导入   弹窗开始 -->
	<div id="idFileDialog" class="easyui-dialog" title="动物Id号批量导入" modal="true" closed="true" style="display:'none';width:320px;height:420px;" closable="true">
	<div id="idFileDialog2" style="display:none;">
		<s:form id="codeFileFrom" action="" method="post"
			enctype="multipart/form-data" theme="simple" >
			<table width="100%">
				<tr><td height="10px;" colspan="2"></td></tr>
				<tr>
					<td><s:file id="excelCodeFile" name="excelCodeFile" cssStyle="width:220px;height:20px;" onclick="javascript:$('#errorSpan').html('');"></s:file></td>
					<td>
						<input type="button" value="上传文件" onclick="ajaxFileUpload();"/>
					</td>
				</tr>
				<tr><td height="10px;" colspan="2">&nbsp;<font color="red"><span id="errorSpan"></span></font></td></tr>
				<tr><td height="10px;" colspan="2">样例：</td></tr>
				<tr>
				<td colspan="2">
					<img id="exampleImage1" src="${pageContext.request.contextPath}/style/images/idFile.png" style="display:none;" alt="动物Id号.xls" />
					<img id="exampleImage2" src="${pageContext.request.contextPath}/style/images/idFile2.png" style="disply:none;" alt="动物Id号.xls" />
				
				</td>
				</tr>
			</table>
			</s:form>
	</div>
	</div>
	<!-- 弹窗结束-->
</body>
</html>
