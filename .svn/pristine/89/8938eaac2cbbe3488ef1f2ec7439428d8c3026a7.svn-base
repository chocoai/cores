<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>临检申请新建/编辑</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/clinicalTest.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/clinicalTestApply.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
var testPhase;
var testPhaseNum;
var firstspan;
var threespan;
$(function(){
	temp =  $('#temp').val();
	if(temp == 2){
		$('#tHematSelectDiv').css('display','none');   
		$('#BloodCoagSelectDiv').css('display','none');   
		$('#tBioChemSelectDiv').css('display','none');   
		$('#UrineSelectDiv').css('display','none');   
		$('#animalSelectDiv').css('display','none');   
		$('#TemporaryDiv').css('display','');
	}else{
		$('#NotSubmitDiv').css('display','');
    }
	testPhase = $('#testPhase').val();
	testPhaseNum=$('#testPhaseNum');
	firstspan=$('#firstspan');
	threespan=$('#threespan');
	if(testPhase==1 ||testPhase==5 || testPhase==6 ){
		testPhaseNum.css('display','none');
	} else {
		testPhaseNum.css('display','');
		if(testPhase==7){
			testPhaseNum.width(150);
		}
	}
	if(testPhase==1){
		firstspan.html("");
		threespan.html("");
	}else if (testPhase==2) {
		firstspan.html("第");
		threespan.html("次检查");
	}else if (testPhase==3) {
		firstspan.html("第");
		threespan.html("周检查");
	}else if (testPhase==4) {
		firstspan.html("第");
		threespan.html("周检查");
	}else {
		firstspan.html("");
		threespan.html("");
	}
	//easyloader.load(['datebox','linkbutton','form','combobox','messager'],function(){
//	});
		$('#beginDate').datebox({
			formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d;
			},
		
			onSelect: function(date){
				//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
				//var v = $('#dd').datebox('getValue');	// get datebox value
				$('#endDate').datebox('setValue', date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());	// set datebox value
			}
		
		});
		$('#endDate').datebox({
			formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d;
			}
		});
		$('#saveButton').linkbutton({
			iconCls:'icon-save',
			plain:true
		});
		$('#animalCombobox').combobox('setValue','');
		
	//只读
	$(".datebox :text").attr("readonly","readonly");
});

/**
 * 变更实验阶段
 * @return
 */
function changePhase(){
	testPhase = $('#testPhase').val();
	testPhaseNum=$('#testPhaseNum');
	firstspan=$('#firstspan');
	threespan=$('#threespan');
	testPhaseNum.val('');
	testPhaseNum.width(30);
	document.getElementById('firstspan').innerHTML='';
	document.getElementById('threespan').innerHTML='';
	threespan.css('display','none');
	if(testPhase==1 ||testPhase==5 ||testPhase==6){
		testPhaseNum.css('display','none');
	} else {
		testPhaseNum.css('display','');
	}
	if(testPhase==1 ||testPhase==5||testPhase==6){
		//firstspan.html("");
		//threespan.html("");
		document.getElementById('firstspan').innerHTML='';
		document.getElementById('threespan').innerHTML='';
	}else if (testPhase==2) {
		//firstspan.html("第");
		//threespan.html("次检查");
		document.getElementById('firstspan').innerHTML='第';
		document.getElementById('threespan').innerHTML='次检查';
		threespan.css('display','');
	}else if (testPhase==3) {
		//firstspan.html("第");
		//threespan.html("周检查");
		document.getElementById('firstspan').innerHTML='第';
		document.getElementById('threespan').innerHTML='周检查';
		threespan.css('display','');
	}else if (testPhase==4) {
		//firstspan.html("第");
		//threespan.html("周检查");
		document.getElementById('firstspan').innerHTML='第';
		document.getElementById('threespan').innerHTML='周检查';
		threespan.css('display','');
	}else  if (testPhase==7) {
		//firstspan.html("");
		//threespan.html("");
		document.getElementById('firstspan').innerHTML='';
		document.getElementById('threespan').innerHTML='';
		testPhaseNum.width(150);
	}else{
		document.getElementById('firstspan').innerHTML='';
		document.getElementById('threespan').innerHTML='';
	}
}

function saveformal(){

	$('#errorMsg').html('');
	if(!testPhaseCheck()){
		//$('#errorMsg').html("请填写试验阶段！");
		parent.parent.showMessager(2,'请填写试验阶段！',true,5000);
		
	} else if (!reqDateCheck()) {
		//$('#errorMsg').html("请填写计划检查日期！");
		parent.parent.showMessager(2,'请填写计划检查日期！',true,5000);
	}else{
	   var PReqNo = $('#PReqNo').val();
			$.ajax({
				url:sybp()+'/tblClinicalTestReqAction_formalsave.action?reqNoPara='+PReqNo,
				type:'post',
				data:$('#tblClinicalTestReqForm').serialize(),
				dataType:'json',
				success:function(r){
					if(r && r.success){
						document.getElementById('tblClinicalTestReqId').value=r.currentid;
						parent.parent.showMessager(1,r.msg,true,5000);
					}else{
						parent.parent.showMessager(3,r.msg,true,5000);
					}
				}
			});
	}	
}

function changeFormal(){
	$('#errorMsg').html('');
	if(!testPhaseCheck()){
		//$('#errorMsg').html("请填写试验阶段！");
		parent.parent.showMessager(2,'请填写试验阶段！',true,5000);
		
	} else if (!reqDateCheck()) {
		//$('#errorMsg').html("请填写计划检查日期！");
		parent.parent.showMessager(2,'请填写计划检查日期！',true,5000);
	}else{
	var PReqNo = $('#PReqNo').val();
	$.messager.confirm('确定','转为正式前将保存数据，是否继续？',function(r){
		if(r){    
			//3.ajax保存
			$.ajax({
				url:sybp()+'/tblClinicalTestReqAction_formalsave.action?reqNoPara='+PReqNo,
				type:'post',
				data:$('#tblClinicalTestReqForm').serialize(),
				dataType:'json',
				success:function(r){
				if(r && r.success){
					qm_showQianmingDialog('afterToFormal');
				}
			}
			});
	    } 
	});
	}
}

function afterToFormal(){
	var PReqNo = $('#PReqNo').val();
	$.ajax({
		url:sybp()+'/tblClinicalTestReqAction_aftertoformal.action?reqNoPara='+PReqNo+'&esType='+17,
		type:'post',
		data:$('#tblClinicalTestReqForm').serialize(),
		dataType:'json',
		success:function(r){
		if(r && r.success){
			parent.parent.showMessager(1,'签字成功',true,5000);
			window.location.href= sybp()+"${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara="+$('#studyNo').val();
		}
	}
	});
}

function outPortFormal(){

	$('#errorMsg').html('');
	if(!testPhaseCheck()){
		//$('#errorMsg').html("请填写试验阶段！");
		parent.parent.showMessager(2,'请填写试验阶段！',true,5000);
		
	} else if (!reqDateCheck()) {
		//$('#errorMsg').html("请填写计划检查日期！");
		parent.parent.showMessager(2,'请填写计划检查日期！',true,5000);
	}else{
	var PReqNo = $('#PReqNo').val();
	$.messager.confirm('确定','打印前将保存数据，是否继续？',function(r){
		if(r){    
			//3.ajax保存
			$.ajax({
				url:sybp()+'/tblClinicalTestReqAction_formalsave.action?reqNoPara='+PReqNo,
				type:'post',
				data:$('#tblClinicalTestReqForm').serialize(),
				dataType:'json',
				success:function(r){
				if(r && r.success){
					//4.打印预览
					parent.parent.showMessager(2,'数据加载中...',true,5000);
					window.location.href="${pageContext.request.contextPath}/tblClinicalTestReqAction_ireport.action?studyNoPara="
						+r.studyNo+"&reqNoPara="+r.currentReqNo+"&toWhere=apply" ;//
				}
			}
			});
	    } 
	});
	}
}



</script>
</head>
<body>
<div class="content">
<s:hidden id="PReqNo" name="PReqNo"></s:hidden>

<!-- 工具栏（签字）开始 -->
	<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
	    <!-- 未提交申请的 -->
	    <div id="NotSubmitDiv" style="display:none">
		<a id="saveButton"   class="easyui-linkbutton"  plain="true" onclick="save();"  data-options="iconCls:'icon-save'">保存</a>
		<a id="outPortButton" class="easyui-linkbutton" plain="true"  onclick="outPort();" data-options="iconCls:'icon-print'">打印预览</a>
		<a id="submitButton" class="easyui-linkbutton" plain="true"  onclick="checkBeforeSign();" data-options="iconCls:'icon-redo'">提交</a>
		<a id="backButton" class="easyui-linkbutton" plain="true"  href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara=${studyNoPara}" data-options="iconCls:'icon-back'">返回</a>
		<span id="errorMsg" style="color:red"></span>
		</div>
		<!-- 临时申请的 -->
		<div id="TemporaryDiv" style="display:none">
		<a id="saveButton"   class="easyui-linkbutton"  plain="true" onclick="saveformal();"  data-options="iconCls:'icon-save'">保存</a>
		<a id="outPortButton" class="easyui-linkbutton" plain="true"  onclick="outPortFormal();" data-options="iconCls:'icon-print'">打印预览</a>
		<a id="submitButton" class="easyui-linkbutton" plain="true"  onclick="changeFormal();" data-options="iconCls:'icon-redo'">签字确认</a>
		<a id="backButton" class="easyui-linkbutton" plain="true"  href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara=${studyNoPara}" data-options="iconCls:'icon-back'">返回</a>
		<span id="errorMsg" style="color:red"></span>
		</div>
		
	</div>
<!--显示表单内容-->
<s:form id="tblClinicalTestReqForm" action="tblClinicalTestReqAction_save" method="POST">
<!-- 
<s:hidden name="tblClinicalTestReq.id"></s:hidden>
<s:hidden id="reqNo" name="tblClinicalTestReq.reqNo"></s:hidden>
 -->
<input type="hidden" id="tblClinicalTestReqId" name="tblClinicalTestReq.id" value="${tblClinicalTestReq.id}"/>
<input type="hidden" id="reqNo" name="tblClinicalTestReq.reqNo" value="${tblClinicalTestReq.reqNo}"/>
<s:hidden id="studyNo" name="studyNoPara"></s:hidden>
<input type="hidden" id="temp" name="tblClinicalTestReq.temp" value="${tblClinicalTestReq.temp}"/>
<font color="red"><span id="span1"></span></font>
    
    <!-- 表单内容显示 -->
        <div class="edit_table">
            <table cellpadding="5" cellspacing="0" class=""  width="100%">
                <tr height="40px" align="left">
                	<td width="40%">专题编号：<s:property value="studyNoPara"/></td>
                    <td width="20%">动物种属：<s:property value="tblClinicalTestReq.tblStudyPlan.animalType"/></td>
                    <td>专题类型：<s:if test="tblClinicalTestReq.tblStudyPlan.isGLP == 1">GLP研究</s:if><s:else>非GLP研究</s:else> </td>
                </tr>
                <tr height="40px"><td width="15%">实验阶段:
                    <s:select disabled="%{tblClinicalTestReq.es==0 || tblClinicalTestReq.temp == 2?false:true }" id="testPhase" name="testPhaseType" onchange="changePhase();"  list="testPhaseMap" listKey="key" listValue="value"></s:select>
                    <span id="firstspan"></span> 
                    <s:textfield id="testPhaseNum" name="testPhaseNum"  cssStyle=" display:none;width:30px;overflow:visible;"></s:textfield>
                    <span id="threespan"></span>
                    </td>
                <td colspan="2" width="15%">计划检查日期:
                		<s:textfield id="beginDate" name="tblClinicalTestReq.beginDate"  onchange="initEndDate();"> 
							<s:param name="value" ><s:date name="tblClinicalTestReq.beginDate" format="yyyy-MM-dd" /></s:param>
						</s:textfield>
                		～
                		<s:textfield id="endDate" name="tblClinicalTestReq.endDate" > 
								<s:param name="value" ><s:date name="tblClinicalTestReq.endDate" format="yyyy-MM-dd" /></s:param> 
						</s:textfield>
                </td>
                </tr>
            </table>
            <table cellpadding="5" cellspacing="0" class=""  width="100%">
            	<tr bgcolor="#ddfdfe">
            		<td width="17%">
            			血液检验
            			&nbsp;&nbsp;&nbsp;
            			<span id="tHematSelectDiv">
            			<a class="easyui-linkbutton" onclick="selectAll('dictHematList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictHematList');" plain="true">清空</a>
            			</span>
            		</td>
            		<td width="17%">
            			血凝检验&nbsp;&nbsp;&nbsp;
            			<span id="BloodCoagSelectDiv">
            			<a class="easyui-linkbutton" onclick="selectAll('dictBloodCoagList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictBloodCoagList');" plain="true">清空</a>
            			</span>
            		</td>
            		<td width="17%">
            			生化检验&nbsp;&nbsp;&nbsp;
                         <span id="tBioChemSelectDiv">
            			<a class="easyui-linkbutton" onclick="selectAll('dictBioChemList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictBioChemList');" plain="true">清空</a>
            			</span>
            		</td>
            		<td width="17%">
            			尿液检验 &nbsp;&nbsp;&nbsp;
            			<span id="UrineSelectDiv">
            			<a class="easyui-linkbutton" onclick="selectAll('dictUrineList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictUrineList');" plain="true">清空</a>
            			</span>
            		</td>
            		<td >动物编号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		<span id="animalSelectDiv">
            		选择
            		<input id="animalCombobox" class="easyui-combobox" style="height:19px;width:80px;"data-options="valueField:'id',textField:'text',
	            		url:'${pageContext.request.contextPath}/tblClinicalTestReqAction_select.action?studyNoPara=${studyNoPara}',editable:false
	            		,onSelect: function(record){    
						    onCCSelect(record);
						},panelHeight:'auto'"/>动物
					</span>	
            		</td>
            	</tr>
            	<tr>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#hematList" var="obj">
            					<input name="dictHematList" value="${obj.name}" type="checkbox" class="dictHematList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						${obj.flag ? 'checked':''}
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#bloodCoagList" var="obj">
            					<input name="dictBloodCoagList" value="${obj.name}" type="checkbox" class="dictBloodCoagList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						${obj.flag ? 'checked':''}
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#bioChemList" var="obj">
            					<input name="dictBioChemList" value="${obj.name}" type="checkbox" class="dictBioChemList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						${obj.flag  ? 'checked':''}
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="#urineList" var="obj">
            					<input name="dictUrineList" value="${obj.name}" type="checkbox" class="dictUrineList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						${obj.flag ? 'checked':''}
            					/>
            					${obj.nameAbbr}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            			<table cellpadding="5" cellspacing="0"  width="100%" style="border-left:1px solid #e3e6eb;">
            				<tr><td width="26px"><!--<a href="#" onclick="animalCheckAll()" >全选</a>--></td><td width="50px">动物编号</td><td>性别</td><td>动物ID号</td></tr>
            				<s:iterator value="tblAnimalList" var="obj">
            				<tr>
            					<td>
            						<input id="animalIdCheckbox" class="animalId_${obj.dissectBatch}" type= "checkbox" id="animalId_${status.index}" name="animalIds" value="${obj.id}" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
					          			<s:iterator value="animalIds" var="a">            
					          			<s:if test="#obj.id==#a">                
					          				checked="checked"           
					          			</s:if>        
					          			</s:iterator>
					          		/>
            					</td>
            					<td><s:property value="#obj.animalCode"/></td>
            					<td><s:property value="%{#obj.gender==1?'♂':'♀'}"/></td>
            					<td><s:property value="#obj.animalId"/></td>
            				</tr>
            				</s:iterator>
            			</table>
            			</div>
            		</td>
            	</tr>
            </table>
            <table cellpadding="5" cellspacing="0" class=""  width="100%">
            	<tr><td width="20%">其他检查项目</td>
                    <td><s:textfield  id="testOther" name="tblClinicalTestReq.testOther"  />
                    </td>
                </tr>
                <tr><td width="20%">备注</td>
                    <td><s:textfield id="remark" name="tblClinicalTestReq.remark"   cssStyle="width:60%" /></td>
                </tr>
            </table>
        </div>
</s:form>
<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</div>

</body>
</html>
