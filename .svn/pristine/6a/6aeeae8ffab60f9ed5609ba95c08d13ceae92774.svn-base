<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>临检申请新建/编辑</title>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/jquery-1.8.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/easyloader.js" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/syUtils.js" charset="utf-8"></script>
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
	testPhase = $('#testPhase').val();
	testPhaseNum=$('#testPhaseNum');
	firstspan=$('#firstspan');
	threespan=$('#threespan');
	if(testPhase==1){
		testPhaseNum.css('display','none');
	} else {
		testPhaseNum.css('display','');
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
	easyloader.load(['datebox','linkbutton','form','combobox','messager'],function(){
		$('#beginDate').datebox({
			formatter:function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return y+'-'+m+'-'+d;
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
	});
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
	if(testPhase==1){
		testPhaseNum.css('display','none');
	} else {
		testPhaseNum.css('display','');
	}
	if(testPhase==1){
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
	}else {
		//firstspan.html("");
		//threespan.html("");
		document.getElementById('firstspan').innerHTML='';
		document.getElementById('threespan').innerHTML='';
		testPhaseNum.width(150);
	}
}
</script>
</head>
<body>
<div class="content">
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
<!--toolbar
<div class="toolbar">
	<ul>
		<li class="first" style="display: ${tblClinicalTestReq.es==0?'':'none' }"><a href="#" onclick="save()"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />保存</a></li>
		<li class="first" ><a href="javascript:void(0);" onclick="outPort();"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />预览</a></li>
		<li class="first"><a href="javascript:void(0);" onclick="checkBeforeSign();"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />提交</a></li>
		<li class="last"><a href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara=${studyNoPara}"><img src="${pageContext.request.contextPath}/style/images/icon_add.png" />返回</a></li>
    </ul>
</div>-->
<!-- 工具栏（签字）开始 -->
	<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
		<a id="saveButton"   class="easyui-linkbutton"  plain="true" onclick="save();"  data-options="iconCls:'icon-save'">保存</a>
		<a id="outPortButton" class="easyui-linkbutton" plain="true"  onclick="outPort();" data-options="iconCls:'icon-print'">打印预览</a>
		<a id="submitButton" class="easyui-linkbutton" plain="true"  onclick="checkBeforeSign();" data-options="iconCls:'icon-redo'">提交</a>
		<a id="backButton" class="easyui-linkbutton" plain="true"  href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara=${studyNoPara}" data-options="iconCls:'icon-back'">返回</a>
		<span id="errorMsg" style="color:red"></span>
	</div>
<!--显示表单内容-->
<s:form id="tblClinicalTestReqForm" action="tblClinicalTestReqAction_save" method="POST">
<s:hidden name="tblClinicalTestReq.id"></s:hidden>
<s:hidden id="reqNo" name="tblClinicalTestReq.reqNo"></s:hidden>
<s:hidden id="studyNo" name="studyNoPara"></s:hidden>

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
                    <s:select disabled="%{tblClinicalTestReq.es==0?false:true }" id="testPhase" name="testPhaseType" onchange="changePhase();"  list="testPhaseMap" listKey="key" listValue="value"></s:select>
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
            			血液常规检查
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictHematList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictHematList');" plain="true">清空</a>
            		</td>
            		<td width="17%">
            			凝血功能检查
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictBloodCoagList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictBloodCoagList');" plain="true">清空</a>
            		</td>
            		<td width="17%">
            			血液生化检查
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictBioChemList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictBioChemList');" plain="true">清空</a>
            		</td>
            		<td width="17%">
            			尿液检查
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<a class="easyui-linkbutton" onclick="selectAll('dictUrineList');" plain="true">全选</a>
            			<a class="easyui-linkbutton" onclick="unSelectAll('dictUrineList');" plain="true">清空</a>
            		</td>
            		<td >动物编号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择
            		<input id="animalCombobox" class="easyui-combobox" style="height:19px;width:80px;"data-options="valueField:'id',textField:'text',
	            		url:'${pageContext.request.contextPath}/tblClinicalTestReqAction_select.action?studyNoPara=${studyNoPara}',editable:false
	            		,onSelect: function(record){    
						    onCCSelect(record);
						},panelHeight:'auto'"/>动物
            		</td>
            	</tr>
            	<tr>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="hematMap" var="map">
            					<input name="dictHematList" value="${map.key}" type="checkbox" class="dictHematList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						<s:iterator value="dictHematList" var="s">            
					          			<s:if test="#map.key==#s">                
					          			checked="checked"           
					          			</s:if>        
					          			</s:iterator>
            					/>
            					${map.value}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="bloodCoagMap" var="map">
            					<input name="dictBloodCoagList" value="${map.key}" type="checkbox" class="dictBloodCoagList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						<s:iterator value="dictBloodCoagList" var="s">            
					          			<s:if test="#map.key==#s">                
					          			checked="checked"           
					          			</s:if>        
					          		</s:iterator>
            					/>
            					${map.value}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="bioChemMap" var="map">
            					<input name="dictBioChemList" value="${map.key}" type="checkbox" class="dictBioChemList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						<s:iterator value="dictBioChemList" var="s">            
					          			<s:if test="#map.key==#s">                
					          			checked="checked"           
					          			</s:if>        
					          		</s:iterator>
            					/>
            					${map.value}<br/>
            				</s:iterator>
            			</div>
            		</td>
            		<td>
            			<div style="height: 300px; overflow-y: auto">
            				<s:iterator value="urineMap" var="map">
            					<input name="dictUrineList" value="${map.key}" type="checkbox" class="dictUrineList" onclick="${tblClinicalTestReq.es==0?'':'return false'}"
            						<s:iterator value="dictUrineList" var="s">            
					          			<s:if test="#map.key==#s">                
					          			checked="checked"           
					          			</s:if>        
					          		</s:iterator>
            					/>
            					${map.value}<br/>
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
            						<input id="animalIdCheckbox" class="animalId_${obj.dissectBatch}" type= "checkbox" id="animalId_${status.index}" name="animalIds" value="${obj.id}" onclick="animalCheckClick('${tblClinicalTestReq.es}');"
					          			<s:iterator value="animalIds" var="a">            
					          			<s:if test="#obj.id==#a">                
					          				checked="checked"           
					          			</s:if>        
					          			</s:iterator>
					          		/>
            					</td>
            					<td><s:property value="#obj.animalCode"/></td>
            					<td><s:property value="%{#obj.gender==1?'雄':'雌'}"/></td>
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

</div>

</body>
</html>
