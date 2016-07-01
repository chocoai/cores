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
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript">
	var animalId;
	var animalCode;
	var animalIdCode;
	var studyNoPara=$('#studyNoPara').val();
	$(function(){
		animalId=$('#animalId').datagrid({
			url : sybp()+"/tblAnimalAction_animalIdList.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:150,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			singleSelect:true,
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title : '动物Id号',
				field : 'animalId',
				width : 100,
			},{
				title :'性别',
				field :'gender',
				formatter: function(value,row,index){
					if (value == 1){
						return '♂';
					} else {
						return '♀';
					}
				},
				width :30
			}]],
			onClickRow :function(rowIndex, rowData){
				$(this).datagrid('selectRow',rowIndex);
			},
			onDblClickRow :function(rowIndex, rowData){
				addCode();
			}
		}); //end animalId
		
		animalCode=$('#animalCode').datagrid({
			url : sybp()+"/tblAnimalAction_animalCodeList.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:150,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			singleSelect:true,
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title : '动物编号',
				field : 'animalCode',
				width : 100,
			},{
				title :'性别',
				field :'gender',
				formatter: function(value,row,index){
					if (value == 1){
						return '♂';
					} else {
						return '♀';
					}
				},
				width :30
			}]],
			onClickRow :function(rowIndex, rowData){
				$(this).datagrid('selectRow',rowIndex);
			},
			onDblClickRow :function(rowIndex, rowData){
				addCode();
			}
		}); //end animalCode

		animalIdCode=$('#animalIdCode').datagrid({
			url : sybp()+"/tblAnimalAction_animalIdCodeList.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:230,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			singleSelect:true,
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title : '动物Id号',
				field : 'animalId',
				width : 95
			},{
				title : '动物编号',
				field : 'animalCode',
				width : 95
			},{
				title :'性别',
				field :'gender',
				formatter: function(value,row,index){
					if (value == 1){
						return '♂';
					} else {
						return '♀';
					}
				},
				width :30
			}]],
			onClickRow :function(rowIndex, rowData){
				$(this).datagrid('selectRow',rowIndex);
			}
		}); //end animalIdCode
		//表头居中
		$('.datagrid-header div').css('textAlign','center');
	});
	/*添加行（添加按钮事件）*/
	function addCode(){
		//得到行
		var animalIdRow =animalId.datagrid('getSelected');
		var animalCodeRow =animalCode.datagrid('getSelected');
		//1.判断选择是否为空
		if(!animalIdRow){
			//$.messager.alert('提示','请选择动物Id号');
			parent.parent.showMessager(2,'请选择动物Id号',true,3000);
			return;
		}
		if(!animalCodeRow){
			parent.parent.showMessager(2,'请选择动物编号',true,3000);
			//$.messager.alert('提示','请选择动物编号');
			return;
		}
		//得到行索引
		var animalIdRowIndex =animalId.datagrid('getRowIndex',animalIdRow);
		var animalCodeRowIndex =animalCode.datagrid('getRowIndex',animalCodeRow);
		//主键Id
		var id =animalIdRow.id;
		var animalIdNum=animalIdRow.animalId;
		//性别
		var gender =animalIdRow.gender;
		//动物编号
		var animalCodeNum=animalCodeRow.animalCode;
		//2.判断性别是否匹配
		//if(gender!=animalCodeNum.substr(1,1)){
		if(gender!=animalCodeRow.gender){
			//$.messager.alert('提示','动物编号与性别不匹配');
			parent.parent.showMessager(2,'动物编号与性别不匹配',true,5000);
			return;
		}
		//3.符合条件，访问后台
		$.ajax({
			url:sybp()+'/tblAnimalAction_editAnimalCode.action',
			type:'post',
			data:{
				id:id,
				animalCode:animalCodeNum
			},
			dataType:'json',
			success: function(r){
				if(r && r.success){
					//动物id,code 添加
					animalIdCodeAddRow(animalIdRow,animalCodeNum);
					//删除行
					animalId.datagrid('deleteRow',animalIdRowIndex);
					animalCode.datagrid('deleteRow',animalCodeRowIndex);
					/*$.messager.show({
						title : '提示',
						msg : r.msg,
						timeout:1000
					});*/
					//parent.parent.showMessager(1,r.msg,true,1000);
				}else{
					parent.parent.showMessager(3,r.msg,true,1000);
					//$.messager.alert('失败',r.msg,'error');
				}
			}
		});
	}

	/**还原编号设置*/
	function removeIdCode(){
		var animalIdCodeRow = animalIdCode.datagrid('getSelected');
		//1.判断选择是否为空
		if(!animalIdCodeRow){
			//$.messager.alert('提示','请选择动物Id号编号');
			parent.parent.showMessager(2,'请选择动物Id号编号',true,3000);
			return;
		}
		//2.正常访问后台
		$.ajax({
			url:sybp()+'/tblAnimalAction_removeAnimalCode.action',
			type:'post',
			data:{
				id:animalIdCodeRow.id
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					animalIdCodeRemoveRow(animalIdCodeRow);
					/*$.messager.show({
						title : '提示',
						msg : r.msg,
						timeout:1000
					});*/
					//parent.parent.showMessager(1,r.msg,true,1000);
				}else{
					//$.messager.alert('失败',r.msg,'error');
					parent.parent.showMessager(3,r.msg,true,1000);
				}
			}
		});
	}
	/**animalIdCode添加行*/
	function animalIdCodeAddRow(animalIdRow,animalCodeNum){
		var rows = animalIdCode.datagrid('getRows');
		var index = rows.length;
		for(var i=0;i<rows.length;i++){
			if(Number(animalCodeNum)<=Number(rows[i].animalCode)){
				index=i;
				break;
			}
		}
		animalIdCode.datagrid('insertRow',{
			index : index,
			row:{
				id:animalIdRow.id,
				animalId:animalIdRow.animalId,
				animalCode:animalCodeNum,
				gender:animalIdRow.gender
			}
		});
		//清除所有选定行
		animalIdCode.datagrid('unselectAll');
		//选中添加行
		animalIdCode.datagrid('selectRow',index);
	}
	/* animalIdCode移除行*/
	function animalIdCodeRemoveRow(animalIdCodeRow){
		var animalCodeRows = animalCode.datagrid('getRows');
		var index=animalCodeRows.length;
		for(var i=0;i<animalCodeRows.length;i++){
			if(Number(animalIdCodeRow.animalCode)<=Number(animalCodeRows[i].animalCode)){
				index=i;
				break;
			}
		}
		animalCode.datagrid('insertRow',{
			index : index,
			row:{
				animalCode:animalIdCodeRow.animalCode,
				gender :animalIdCodeRow.gender
			}
		});
		//清除所有选定行
		animalCode.datagrid('unselectAll');
		//选中添加行
		animalCode.datagrid('selectRow',index);
		var animalIdRows = animalId.datagrid('getRows');
		index=animalIdRows.length;
		for(var i=0;i<animalIdRows.length;i++){
			if(Number(animalIdCodeRow.animalId)<=Number(animalIdRows[i].animalId)){
				index=i;
				break;
			}
		}
		animalId.datagrid('insertRow',{
			index : index,
			row:{
				id:animalIdCodeRow.id,
				gender:animalIdCodeRow.gender,
				animalId:animalIdCodeRow.animalId
			}
		});
		//清除所有选定行
		animalId.datagrid('unselectAll');
		//选中添加行
		animalId.datagrid('selectRow',index);

		//得到行索引
		var animalIdCodeRowIndex =animalIdCode.datagrid('getRowIndex',animalIdCodeRow);
		//删除行
		animalIdCode.datagrid('deleteRow',animalIdCodeRowIndex);
	}
	/**动物编号签字之前的检查*/
	function checkBeforeAnimalCodeSign(){
		var rows=animalCode.datagrid('getRows');
		if(rows.length<1){
			$.ajax({
				url:sybp()+'/tblAnimalAction_checkBeforeAnimalCodeSign.action',
				type:'post',
				cache:false,
				data:{
					studyNoPara:$('#studyNoPara').val(),
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						//$('#span1').html('');
						$.messager.confirm('确认对话框', '确认动物编号结果过后，动物编号将不可以再编辑，确定继续吗？', function(r){
									if (r){
										qm_showQianmingDialog('eventAfterAnimalCodeSign');
									}
							});
						
					}else{
						//$('#span1').html(r.msg);
						parent.parent.showMessager(2,r.msg,true,5000);
					}
				}
			});
		}else{
			//$('#span1').html("编号未录入完毕");
			parent.parent.showMessager(2,'编号未录入完毕',true,5000);
		}
	}
	/**动物编号签字之后的事件*/
	function eventAfterAnimalCodeSign(){
		window.location.href=sybp()+"/tblAnimalAction_sign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=6";
	}
	/**显示动物编号批量导入Dialog*/
	function showCodeFileDialog(){
		var rows =$('#animalIdCode').datagrid('getRows');
		if(rows.length>0){
			$.messager.confirm('确认对话框', '动物编号批量导入将覆盖原有数据，确定继续吗？', function(r){
				if (r){
					/*Dialog*/
					document.getElementById("codeFileDialog2").style.display="block";
					$('#codeFileDialog').dialog('open');
				}
		});
		}else{
			/*Dialog*/
			document.getElementById("codeFileDialog2").style.display="block";
			$('#codeFileDialog').dialog('open'); 
		}
	}
	/**执行文件上传操作*/
	function ajaxFileUpload(){
		$('#errorSpan').html('');
		if($('#excelCodeFile').val()!=''){
	         $.ajaxFileUpload({
		          url:sybp()+'/tblAnimalAction_importExcel.action?studyNoPara='+$('#studyNoPara').val(),            //需要链接到服务器地址
		          secureuri:false,
		          fileElementId:'excelCodeFile',                        //文件选择框的id属性
		          dataType: 'json',                                     //服务器返回的格式，可以是xml
		          success: function (data, status){     
		              if(data &&data.success){
		            	  $('#codeFileDialog').dialog('close'); 
		            	  parent.parent.showMessager(1,data.msg,true,5000);
		            	  $('#animalId').datagrid('load');
		            	  $('#animalCode').datagrid('load');
		            	  $('#animalIdCode').datagrid('load');
		            	  
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
</script>
</head>
<body>
	<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
	<div class="easyui-layout" fit="true" border="false" >
		<div region="north" style="height:30px;" border="false">
			<div  style=" left:20px; height:28;border: 1px solid #c8c8c8;">
		  		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true"  onclick="checkBeforeAnimalCodeSign();">确认动物编号结果</a>
		  		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'',plain:true" onclick="showCodeFileDialog();">动物编号批量导入</a>
			     <a class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" href="${pageContext.request.contextPath}/tblAnimalAction_animalList.action?studyNoPara=${studyNoPara}" >返回</a>
		        <font color="red"><span id="span1"></span></font>
   			 </div>
		</div>
		<div region="west" border="false" style="width:200px;">
			<table id="animalId" ></table>
		</div>
		<div region="center" border="false" style="">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false">
					<table id="animalCode" ></table>
				</div>
				<div region="east" border="false" style="width:100px;">
					<a class="easyui-linkbutton" onclick="addCode();" style="position:absolute;top:150px;left:20px;font-weight:bold;" plain="true">&nbsp;&nbsp;>&nbsp;&nbsp;</a><br/>
					<a class="easyui-linkbutton" onclick="removeIdCode();" style="position:absolute;top:200px;left:20px;font-weight:bold;" plain="true">&nbsp;&nbsp;<&nbsp;&nbsp;</a><br/>
				</div>
			</div>
		</div>
		<div region="east" border="false" style="width:700px;">
			<table id="animalIdCode"></table>
		</div>
	</div>
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
    <!-- 动物编号批量导入   弹窗开始 -->
	<div id="codeFileDialog" class="easyui-dialog" title="动物编号批量导入" modal="true" closed="true" style="display:'none';width:320px;height:420px;" closable="true">
	<div id="codeFileDialog2" style="display:none;">
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
					<img src="${pageContext.request.contextPath}/style/images/codeFile.png" alt="动物编号.xls" />
				</td>
				</tr>
			</table>
			</s:form>
	</div>
	</div>
	<!-- 弹窗结束-->
</body>
</html>
