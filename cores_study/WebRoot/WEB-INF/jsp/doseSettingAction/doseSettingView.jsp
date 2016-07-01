<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/doseSetting.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/doseSetting.css" media="screen" />
<script type="text/javascript">
	var doseSettingViewTable;
	var datagridlength ;
	var isSave = false;
	var isEditting = true;
	var studyNoPara ;
	var dosageUnit;
	var studyState;
	var tblDoseSettingListLength;
	var times = 0;
	$(function(){
		studyNoPara = $('#studyNoPara').val();
		dosageUnit = $('#dosageUnit').val();
		studyState = $('#studyState').val();
		tblDoseSettingListLength = $('#tblDoseSettingListLength').val();
		//显示整个页面
		document.getElementById("bodyDiv").style.display="block";
		document.getElementById("dosageNum2").style.display="block";
		if(tblDoseSettingListLength>0){
			$('#dosageNum').dialog('close');
			datagridlength = tblDoseSettingListLength;
			isEditting=false;
		}
		
		if( studyState == 1 || studyState == 2){
			$('#editButton').linkbutton('disable');
			$('#saveButton').linkbutton('disable');
			$('#undoButton').linkbutton('disable');
			$('#doseSettingViewTable').datagrid({
			   columns:[[
						{title:'id',field:'id',hidden:true,width:80},
						{title:'剂量组',field:'dosageNum',width:80,halign:'center'},
						{title:'剂量组说明',field:'dosageDesc',width:150,halign:'center',editor:{type:'validatebox',	options:{required : true}}},
						{title:'剂量( '+dosageUnit+' )',field:'dosage',width:80,halign:'center',align:'right',editor:{type:'validatebox',	options:{required : true,validType:'positiveNumber'}}},
						{title:'雄性数量',field:'maleNum',width:80,halign:'center',align:'right',editor:{type:'numberbox',	options:{required : true,min:1,precision:0}}},
						{title:'雌性数量',field:'femaleNum',width:80,halign:'center',align:'right',editor:{type:'numberbox',	options:{required : true,min:1,precision:0}}}
					]]
			});
		}else if(studyState == 3){
		    $('#doseSettingViewTable').datagrid({
			   columns:[[
						{title:'id',field:'id',hidden:true,width:80},
						{title:'剂量组',field:'dosageNum',width:80,halign:'center'},
						{title:'剂量组说明',field:'dosageDesc',width:150,halign:'center',editor:{type:'validatebox',	options:{required : true}}},
						{title:'剂量( '+dosageUnit+' )',field:'dosage',width:80,halign:'center',align:'right',editor:{type:'validatebox',	options:{required : true,validType:'positiveNumber'}}},
						{title:'雄性数量',field:'maleNum',width:80,halign:'center',align:'right'},
						{title:'雌性数量',field:'femaleNum',width:80,halign:'center',align:'right'}
					]]
			});
		}else if(studyState == 0){
		 	$('#doseSettingViewTable').datagrid({
			   columns:[[
						{title:'id',field:'id',hidden:true,width:80},
						{title:'剂量组',field:'dosageNum',width:80,halign:'center'},
						{title:'剂量组说明',field:'dosageDesc',width:150,halign:'center',editor:{type:'validatebox',	options:{required : true}}},
						{title:'剂量( '+dosageUnit+' )',field:'dosage',width:80,halign:'center',align:'right',editor:{type:'validatebox',	options:{required : true,validType:'positiveNumber'}}},
						{title:'雄性数量',field:'maleNum',width:80,halign:'center',align:'right',editor:{type:'numberbox',	options:{required : true,min:1,precision:0}}},
						{title:'雌性数量',field:'femaleNum',width:80,halign:'center',align:'right',editor:{type:'numberbox',	options:{required : true,min:1,precision:0}}}
					]]
			});
		}
		
		doseSettingViewTable=$('#doseSettingViewTable').datagrid({
			url:sybp()+'/tblDoseSettingAction_loadList.action?studyNoPara='+studyNoPara,
			fit:false,
			striped:true,
			fitColumns:false,
			pagination:false,
			toolbar:'#toolbar',
			onAfterEdit : function(rowIndex,rowData,changes){
				if(isSave){
					$.ajax({
						url:sybp()+'/tblDoseSettingAction_addNewList.action?studyNoPara='+studyNoPara,
						type:'post',
						data: rowData,
						dataType:'json',
						success:function(r){
							if(r && r.success){
								times++;
								if(times >= datagridlength){
									var updated = doseSettingViewTable.datagrid('getChanges');
									//$.messager.show({title:'提示',msg:r.msg,timeout:'5000',showType:'slide'});
									//window.location.href=sybp()+'/tblDoseSettingAction_saveOrupdateAll.action?studyNoPara='+studyNoPara;
									$.ajax({
										url:sybp()+'/tblDoseSettingAction_saveOrupdateAll.action?studyNoPara='+studyNoPara+'&updatedLength='+updated.length,
										type:'post',
										data: rowData,
										dataType:'json',
										success:function(r){
											if(!r.success){
												doseSettingViewTable.datagrid('loadData',r);
											}
											//设置回滚节点
											doseSettingViewTable.datagrid('acceptChanges');	
											isEditting=false;
											isSave = false;
											times =0;
											//保存成功之后
											
											$('#editButton').linkbutton('enable');
											$('#saveButton').linkbutton('disable');
											$('#undoButton').linkbutton('disable');
											parent.parent.showMessager(1,'保存成功',true,5000);
										}
									});
								}
							}
						}
					});
				}
			}
		
        });
      
		$('#dosageNumTotal').bind('keyup',function(e){/* 增加回车提交功能 */
			if(e.keyCode == '13'){
				$('#dosageNumTotal').numberbox('fix');
				submitDosageNumFrom();
			}
		});
		
		var member = $('#left_member').val();
        if(member!= ""){
		    $('#editButton').linkbutton('disable');
			$('#saveButton').linkbutton('disable');
			$('#undoButton').linkbutton('disable');
			$('#dosageNum').dialog('close');
		}
	});
	/*点击编辑按钮事件*/
	function onEditButton(){
		if(!isEditting){
			for(var i=0;i<datagridlength;i++){
				doseSettingViewTable.datagrid('beginEdit',i);
			} 
			isEditting=true;
			isSave = false;
			times =0;
			//编辑状态
			$('#editButton').linkbutton('disable');
			$('#saveButton').linkbutton('enable');
			$('#undoButton').linkbutton('enable');
		}
	}
	/*点击保存按钮事件*/
	function onSaveButton(){
		
		var pass=true;//验证是否通过标记
		for(var i=0;i<datagridlength;i++){
			if(!doseSettingViewTable.datagrid('validateRow',i)){
				pass=false;
			}
		} 
		if(pass){//验证通过
			isSave = true;//可以保存
			studyNoPara =$('#studyNoPara').val();
			for(i=0;i<datagridlength;i++){
				doseSettingViewTable.datagrid('endEdit',i)
			}
			var updated = doseSettingViewTable.datagrid('getChanges');
		}else{
			parent.parent.showMessager(2,'信息填写不完整！',true,5000);
		}
	}
	/*点击取消编辑按钮事件*/
	function onUndoButton(){
		isEditting=false;
		times=0;
		isSave=false;
		doseSettingViewTable.datagrid('rejectChanges');
		doseSettingViewTable.datagrid('unselectAll');
		//取消编辑后
		$('#editButton').linkbutton('enable');
		$('#saveButton').linkbutton('disable');
		$('#undoButton').linkbutton('disable');
	}
	//提交前提醒
	function submitDosageNumFrom(){
	    var total = $('#dosageNumTotal').val();
	    var mode = $('#animalCodeMode').numberbox('getValue')
	    if(mode == 1 ){
	       mode = "A";
	    }else if(mode == 2){
	       mode = "B";
	    }
	   $.messager.confirm('确认对话框', '剂量组数为'+total+"组，选择"+mode+"组分组方式，确定后不可再更改！", function(r){
	    if (r){
	       aftetsubmitDosageNumFrom();
    	}
      });

	   
	}
	/**提交form*/
	function aftetsubmitDosageNumFrom(){
		//var acm =$('#animalCodeMode').numberbox('getValue');
		//alert(acm);
		if($('#dosageNumFrom').form('validate')){
			$.ajax({
				type:"post",
				url:sybp()+'/tblDoseSettingAction_newDosageNum.action',
				data:$('#dosageNumFrom').serialize(),
				data:{
					dosageNumPara:$('#dosageNumTotal').numberbox('getValue'),
					animalCodeMode:$('#animalCodeMode').numberbox('getValue'),
					studyNoPara:$('#studyNoPara').val()
				},
				cache:false,
				dataType:'json',
				success:function(data){
					if(data){
				    	datagridlength=data.length;
			    		$('#dosageNum').dialog('close');
						doseSettingViewTable.datagrid('loadData',data);  
						for(var i=0;i<data.length;i++){
							doseSettingViewTable.datagrid('beginEdit',i);
						} 
						$('#editButton').linkbutton('disable');
						$('#saveButton').linkbutton('enable');
						$('#undoButton').linkbutton('enable');
				    }
				}
			});
		}
	}
</script>
</head>
<body>
	<div id="bodyDiv" style="display:none;">
		<s:hidden id="dosageUnit" name="dosageUnit"></s:hidden>
		<s:hidden id="studyState" name="studyState"></s:hidden>
		<s:hidden id="left_member" name="left_member"></s:hidden>
		<s:hidden id="tblDoseSettingListLength" name="tblDoseSettingListLength"></s:hidden>
		<table  id="doseSettingViewTable" >
			<tr><td>剂量组</td></tr>
		</table>  
		<div id="toolbar">
			<a id="editButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-edit',plain:true,disabled:false">编辑</a>
			<a id="saveButton" class="easyui-linkbutton" onclick="onSaveButton();" data-options="iconCls:'icon-save',plain:true,disabled:true">保存</a>
			<a id="undoButton" class="easyui-linkbutton" onclick="onUndoButton();" data-options="iconCls:'icon-undo',plain:true,disabled:true">取消编辑</a>
		</div>
	</div>
	<!-- 弹窗开始 -->
	<div id="dosageNum" class="easyui-dialog" title="剂量组设置" modal="true" closed="false" style="display:'none';width:480px;height:220px;" closable="false">
	<div id="dosageNum2" style="display:none;">
		<s:form id="dosageNumFrom" action="tblDoseSettingAction_newDosageNum.action" >
		<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
			<table width="100%">
				<tr><td height="10px;" colspan="2"></td></tr>
				<tr><td widht="30px;">&nbsp;&nbsp;&nbsp;&nbsp;剂量组数量</td>
					<td><input id="dosageNumTotal" class="easyui-numberbox" data-options="required:true, min:1,max:11111,precision:0,validType:['length[1,1]'],invalidMessage:'请输入1-9的数'" type="text" name="dosageNumPara"/></td></tr>
				<tr height="15px;"></tr>
				<tr style="border-top:1px solid #e3e6eb;height:30px;"><td colspan="2">&nbsp;&nbsp;&nbsp;
					动物编号规则
					<input id="animalCodeMode"  name="animalCodeMode" class="easyui-numberbox" style="width:1px;visibility:hidden;" missingMessage="请选择编号规则" data-options="required:true" readonly="readonly"/>
					</td></tr>
				<tr><td colspan="2">&nbsp;&nbsp;&nbsp;
					<input id="radioA"  type="radio" onclick="onRadioCk('1');"/> <a href="javascript:onRadioCk('1');" >A:组（1,2...）+性别（雄：1，雌：2）+动物排序号（1,2,3...）</a> 
				</td></tr>
				<tr height="30px;"><td colspan="2">&nbsp;&nbsp;&nbsp;
					<input id="radioB" type="radio" onclick="onRadioCk('2');"/> <a  href="javascript:onRadioCk('2');">B:性别（雄：1，雌：2）+组（0,1...）+动物组内序号（0,1,2...）</a>
				</td></tr>
				<tr height="30px;"><td widht="30px;" height="30px;"><td id="doseSettingbutton" align="left" valign="bottom">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitDosageNumFrom();">确定</a></td></tr>
			</table>
			</s:form>
	</div>
	</div>
	<!-- 弹窗结束-->
</body>
</html>
