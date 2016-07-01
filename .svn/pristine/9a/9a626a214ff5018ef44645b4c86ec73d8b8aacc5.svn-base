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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/study.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/clinicaltest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/path.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/schedule.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/yydb.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/qa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/studyAction/contract.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/reqView.js" charset="utf-8"></script>
</head>
<body >
<input id = "yearListStr" type="hidden" name = "yearListStr" value = "${yearListStr}"/>
<input id = "yearListStr2" type="hidden" name = "yearListStr2" value = "${yearListStr2}"/>
<input id = "studyNoListStr" type="hidden" name = "studyNoListStr" value = "${studyNoListStr}"/>
<input id = "contractCodeListStr" type="hidden" name = "contractCodeListStr" value = "${contractCodeListStr}"/>
<input id = "yearStr" type="hidden" name = "yearStr" value = "${year}"/>
<div class="easyui-layout" style="width:100%;height:100%;">   
    <div data-options="region:'west'" style="width:190px;padding:5px;">
    	<!-- 专题 -->
    	<div id="studyDiv">
	    	<div style="padding-left:15px;padding-bottom:5px;">
		    	<input id="year" class="easyui-combobox" name="year" style="width:100px;height:19px;"  
		    		data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto'" /> 
		    	年<br/>
		    	<div style="height: 30px;line-height: 30px;">
			    	<input id="radio1" name="testItemType" type="radio" value ="01" onclick="updateStudyNoDatagrid();"
			    		style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" checked="checked"/>
			    		<a href="javascript:onRadioCk('1');" style="text-decoration:none;color:#000;">医药</a>
			    	<input id="radio2" name="testItemType" type="radio" value ="02" onclick="updateStudyNoDatagrid();"
			    		style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;"/>
			    		<a href="javascript:onRadioCk('2');" style="text-decoration:none;color:#000;">农药</a>
			    	<input id="radio3" name="testItemType" type="radio" value ="03" onclick="updateStudyNoDatagrid();"
			    		style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;"/>
			    		<a href="javascript:onRadioCk('3');" style="text-decoration:none;color:#000;">化学品</a>
		    	</div>
	    	</div>
	    	<div style="padding-left:15px;padding-bottom:5px;">
				<table id="studyNo" ></table>
	    	</div>
    	</div>
    	<!-- 合同 -->
    	<div id="contractDiv2">
    		<div style="padding-left:15px;padding-bottom:5px;">
		    	<input id="year2" class="easyui-combobox" name="year" style="width:100px;height:19px;"  
		    		data-options="valueField:'id',textField:'text',editable:false,panelHeight:'auto'" /> 
		    	年<br/>
	    	</div>
	    	<div style="padding-left:15px;padding-bottom:5px;">
				<table id="contractCode" ></table>
	    	</div>
    	</div>
    </div>   
    <div data-options="region:'center'" style="padding:0px;">
    	<div style="margin-top:5px;padding-left:15px;padding-bottom:5px;border-bottom: 1px solid #ebebeb;">
    		系统名称：<input id="systemName" name="systemNameIndex" class="easyui-combobox"  style="height:19px;"  
    						data-options="valueField:'id',textField:'text',editable:false,
    						panelHeight:'auto',width:130,
    						url:sybp()+'/homeAction_loadSystemName.action'" />  
    	</div>
    	<div style="padding-left:15px;padding-bottom:5px;">
	    	<!-- 临检  start -->
    		<div id ="clinicaltestDiv" class ="absoluteMainBox" style="padding-left:15px;padding-top:6px;">
    			<div style="padding-bottom:5px;">
		    		类型：
		    		<select id="operateType_clinicaltest" class="easyui-combobox" name="dataType"
		    			 style="width:148px;height:19px;" 
		    			 data-options="valueField:'id',textField:'text',
		    			 	editable:false,panelHeight:'auto'">   
					    <option value="1">临检指标重测</option>   
					    <option value="2">临检数据编辑\删除</option>   
					</select>  
		    	</div>
		    	<div style="padding-bottom:5px;">
		    		<table id="clinicalData"></table>
		    	</div>
    		</div>
    		<!-- 临检  end -->
    		<!-- 病理  start -->
    		<div id ="pathDiv" class ="absoluteMainBox" style="display:none;">
    			<div id="indexTabsDiv" class="easyui-tabs" fit="true" border="false" style="height:100%;width:100%;">
					<div title="解剖所见" border="false" style="overflow: hidden;">
				    	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
				    		类型：
				    		<select id="operateType_path" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="8">全部</option>   
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
				    		<select id="operateType2_path" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="7">全部</option>   
							    <option value="5">申请变更</option>   
							    <option value="6">申请撤销</option>   
							</select>  
				    	</div>
				    	<div style="padding-left:15px;padding-bottom:5px;">
				    		<table id="anatomyReq"></table>
				    	</div>
					</div>
					<div title="脏器称重" border="false" style="overflow: hidden;">
					  	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
				    		类型：
				    		<select id="operateType4_path" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="12">全部</option>   
							    <option value="13">重新称量</option>   
							    <option value="14">删除</option>   
							    <option value="15">编辑</option>   
							</select>  
				    	</div>
				    	<div style="padding-left:15px;padding-bottom:5px;">
				    		<table id="visceraWeighTable"></table>
				    	</div>
					</div>
					<div title="脏器固定" border="false" style="overflow: hidden;">
					  	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
				    		类型：
				    		<select id="operateType5_path" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="16">全部</option>   
							    <option value="17">数据确认_添加</option>   
							    <option value="18">数据修改_添加</option>   
							</select>  
				    	</div>
				    	<div style="padding-left:15px;padding-bottom:5px;">
				    		<table id="visceraFixedTable"></table>
				    	</div>
					</div>
					<div title="组织学检查" border="false" style="overflow: hidden;">
					  	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
				    		类型：
				    		<select id="operateType3_path" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="9">全部</option>   
							    <option value="10">镜检数据-添加</option>  
							    <option value="11">镜检数据-删除</option>  
							</select>  
				    	</div>
				    	<div style="padding-left:15px;padding-bottom:5px;">
				    		<table id="histopathCheck"></table>
				    	</div>
					</div>
					<div title="死亡日期" border="false" style="overflow: hidden;">
					  	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
				    		类型：
				    		<select id="operateType6_path" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="19">全部</option>   
							</select>  
				    	</div>
				    	<div style="padding-left:15px;padding-bottom:5px;">
				    		<table id="deadDateTable"></table>
				    	</div>
					</div>
				 </div>
    		</div>
    		<!-- 病理  end -->
    		<!-- 综合 start -->
    		<div id ="scheduleDiv" class ="absoluteMainBox" style="padding-left:15px;;padding-top:6px;display:none;">
    			<div style="padding-bottom:5px;">
		    		类型：
		    		<select id="operateType_schedule" class="easyui-combobox" name="dataType"
		    			 style="width:148px;height:19px;" 
		    			 data-options="valueField:'id',textField:'text',
		    			 	editable:false,panelHeight:'auto'">   
					    <option value="4">全部</option>
					    <option value="1">重新任命SD</option>
					    <option value="2">重新任命QA检查员</option>
					    <option value="3">重新任命病理专题负责人</option>
					</select>  
		    	</div>
		    	<div style="padding-bottom:5px;">
		    		<table id="appointTable"></table>
		    	</div>
    		</div>
    		<!-- 综合 end -->
    		<!-- 一般毒理  start -->
    		<div id ="yydbDiv" class ="absoluteMainBox" style="display:none;">
    			<div id="yydbTabsDiv" class="easyui-tabs" fit="true" border="false" style="height:100%;width:100%;">
					<div title="专题操作日志" border="false" style="overflow: hidden;">
				    	<div style="padding-top:5px;padding-left:15px;padding-bottom:6px;">
				    		类型：
				    		<select id="operateType_yydb" class="easyui-combobox" 
				    			 style="width:148px;height:19px;" 
				    			 data-options="valueField:'id',textField:'text',
				    			 	editable:false,panelHeight:'auto'">   
							    <option value="ALL">全部</option>   
							    <option value="TESTCODE">专题信息</option>   
							    <option value="ANIMAL">动物分组</option>   
							    <option value="WEIGHT">体重称重</option>   
							    <option value="FOOD">摄食</option>   
							    <option value="TESTOTHER">其他</option>   
							</select>  
				    	</div>
				    	<div style="padding-left:15px;padding-bottom:5px;">
				    		<table id="yydbtable"></table>
				    	</div>
					</div>
					<div title="数据修改跟踪日志" border="false" style="overflow: hidden;">
				    	<div style="padding-top:5px;padding-left:15px;padding-bottom:5px;">
				    		<table id="yydbtable2"></table>
				    	</div>
					</div>
				 </div>
    		</div>
    		<!-- 一般毒理  end -->
    		<!-- QA  start -->
    		<div id ="qaDiv" class ="absoluteMainBox" style="padding-left:15px;padding-top:6px;">
    			<div style="padding-bottom:5px;">
		    		类型：
		    		<select id="operateType_qa" class="easyui-combobox" name="dataType"
		    			 style="width:148px;height:19px;" 
		    			 data-options="valueField:'id',textField:'text',
		    			 	editable:false,panelHeight:'auto'">   
					    <option value="0">全部</option>   
					    <option value="808">专题方案提交</option>   
					    <option value="809">专题报告提交</option>  
					    <option value="822">QA接收专题方案</option>   
					    <option value="842">QA接收专题报告</option>  
					    <option value="834">方案阅读完成</option>  
					     
					    <option value="801">检查计划提交</option>   
					    <option value="810">检查计划审批</option>  
					    
					    <option value="817">检查计划变更申请</option>  
					    <option value="844">检查计划变更申请撤销</option>  
					    <option value="830">检查计划变更申请审批</option>  
					    
					    <option value="832">QA申请临时检查人</option>  
					    <option value="833">QA申请临时检查人审批</option>  
					    
					    <option value="803">检查报告提交</option>  
					    <option value="818">检查报告审批</option>  
					    <option value="820">检查报告（SD回复）</option>  
					    <option value="821">检查报告（延迟整改）</option>  
					    <option value="804">FM批复检查报告（SD回复）</option>  
					    <option value="805">FM批复检检查报告（延迟整改）</option>  
					    <option value="819">QA接收检查报告（SD回复或延迟整改）</option>  
					    
					     
					</select>  
		    	</div>
		    	<div style="padding-bottom:5px;">
		    		<table id="qatable"></table>
		    	</div>
    		</div>
    		<!-- QA  end -->
    		<!-- 委托管理  start -->
    		<div id ="contractDiv" class ="absoluteMainBox" style="padding-left:15px;padding-top:6px;">
    			委托合同
		    	<div style="padding-bottom:5px;">
		    		<table id="contracttable"></table>
		    	</div>
		    	供试品
		    	<div style="padding-bottom:5px;">
		    		<table id="testitemtable"></table>
		    	</div>
		    	委托项目
		    	<div style="padding-bottom:5px;">
		    		<table id="studyitemtable"></table>
		    	</div>
    		</div>
    		<!-- 委托管理  end -->
    	</div>
    </div>   
	<%@ include file="/WEB-INF/jsp/pathAction/reqView.jspf"%>
</div> 
</body>
</html>