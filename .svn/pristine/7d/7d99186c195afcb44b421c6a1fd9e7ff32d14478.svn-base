<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES 档案管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>

<script type="text/javascript">
	
	
	function searchRecord()
	{
		$.messager.progress({title: '请稍后',
			msg: '处理中...',text:''});	// 显示进度条
			
		 var index = 0;
		 var contentWind = parent.document.getElementById('archiveMainFrame').contentWindow;
		 if(contentWind.$)
		 {
			 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
			 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
			 
			/*公共的参数publicSearchPart中的内容*/
			var searchString = $('#searchStringBox').searchbox('getValue');
			var isFileDate = 0;
			if($('#isFileDate').attr('checked')=='checked'){
				isFileDate=1;
			}
			var start=$('#startDate').datebox('getValue');
			var end=$('#endDate').datebox('getValue');
			var keepEndDate=$('#keepEndDate').datebox('getValue');
			var isDestory = 0;
			if($('#isDestory').attr('checked')=='checked'){
				isDestory=1;
			}
			var isValid=0;
			if($('#isValid').attr('checked')=='checked'){
				isValid=1;
			}
			
			 if(index==0)
			 {
				var studyNoType = $("input[name='studyNoType2']:checked").val();
				parent.searchStudyRecord(studyNoType,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			}
			 if(index==1)
			 {
				var checkItemType = $("input[name='checkItemType']:checked").val();
				parent.searchQARecord(checkItemType,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 
			 if(index==2)
			 {
					
				 //var isNowValid = $("input[name='isNowValid']:checked").val();
				 var isAll = $('#allSOPRecords').attr('checked')=='checked'? 1:0;
				 var isNowValid = $('#displayNowValid').attr('checked')=='checked'? 1:0;
				 var isInvalid1=0;
				 if($('#isInvalid').attr('checked')=='checked'){
					 isInvalid1=1;
				 }
				 var needChange = $('#displayNeedChange').attr('checked')=='checked'?1:0;
				 var changeEndDate=$('#changeEndDate').datebox('getValue');
				 var yearNum = $('#yearNum').val();
				 var yearNumUnit = $('#yearNumUnit').combobox('getValue');
			
				parent.searchSOPRecord(isAll,isNowValid,isInvalid1,needChange,changeEndDate,yearNum,yearNumUnit,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
		   			 
			 }
			 if(index==3)
			 {
				var departmentLeft = $('#departmentLeft').combobox('getValue');//departId
				parent.searchGlpSynthesisRecord(departmentLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==4)
			 {
				parent.searchInstrumentRecord(isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==5)
			 {
				var staffDeptLeft = $('#staffDeptLeft').combobox('getValue');//departName
 				var staffStateLeft = $('#staffStateLeft').combobox('getValue');//1在职 2 离职
				
				parent.searchEmployeeRecord(staffDeptLeft,staffStateLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==6)
			 {
				 var docTypeFlagLeft = $('#docTypeFlagLeft').combobox('getValue');
				parent.searchAdministrationRecord(docTypeFlagLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==7)
			 {
				 var contractTypeFlagLeft = $('#contractTypeFlagLeft').combobox('getValue');
				parent.searchContractRecord(contractTypeFlagLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==8)
			 {
				 var isSmplKeepEndDate = $('#smplKeepEndDateCheckBox').attr('checked')=='checked'?1:0;
				 var smplKeepEndDate=$('#smplKeepEndDate').datebox('getValue');
				var isDestroySmpl = $('#isDestroySmpl').attr('checked')=='checked'?1:0;
				
				var smplType = $('#leftSmplType').combobox('getValue');
				parent.searchSmplReserveRecord(isSmplKeepEndDate,smplKeepEndDate,isDestroySmpl,smplType,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==9)
			 {
				 //var isSpecimenKeepEndDate = $('#specimenKeepEndDateCheckBox').attr('checked')=='checked'?1:0;
				// var specimenKeepEndDate=$('#specimenKeepEndDate').datebox('getValue');
				var isDestroySpecimen = $('#isDestroySpecimen').attr('checked')=='checked'?1:0;
				
				var type = $('input[name="leftSpecimenTypeFlag"]:checked').val();
				parent.searchSpecimenRecord(isDestroySpecimen,type,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==10)
			 {
				 var departmentLeft = $('#department2Left').combobox('getValue');//departId
				 parent.searchGlpSynthesis2Record(departmentLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 if(index==11)
			 {
				var archiveTypeFlag = $('#leftArchiveTypeFlag').combobox('getValue');
				var logStartDate = $('#logStartDate').datebox('getValue');
				var logEndDate = $('#logEndDate').datebox('getValue');
				parent.searchLog2(archiveTypeFlag,logStartDate,logEndDate,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			 }
			 
		 }else{
			 index = 0;
		 }
		 
		 $.messager.progress('close');	// 显示进度条
		
		//document.getElementById('searchRecordButton').href=url;
		//document.getElementById('searchRecordButton').click();
	}

	function selectChangeCon()
	{
		if($('#displayNeedChange').attr('checked')=='checked') 
		{
				
			$('#changeEndDate').datebox('enable');
			
			
			$('#changeEndDate').datebox('setValue',$('#afterOneMonth').val());
			$('#yearNum').val(1);
		  
		   $('#displayNowValid').attr('checked',true);
		   $('#displayNowValid').attr('disabled',true);
		   $('#yearNum').attr('disabled',false);
		 	
		   $('#isInvalid').attr('checked',false);
		   $('#isInvalid').attr('disabled',true);
		}else{
			
			$('#changeEndDate').datebox('setValue','');
			$('#changeEndDate').datebox('disable');

			$('#yearNum').val('');
			$('#yearNum').attr('disabled',true);
			 
			$('#displayNowValid').attr('disabled',false);
			$('#isInvalid').attr('disabled',false);
			   
		}
		searchRecord();
		
	}
	function clickAllSOP()
	{
		if($('#allSOPRecords').attr('checked')=='checked'){
			 $('#displayNowValid').attr('disabled',true);
			 $('#displayNowValid').attr('checked',false);

			 $('#isInvalid').attr('disabled',true);
			 $('#isInvalid').attr('checked',false);

			 $('#displayNeedChange').attr('disabled',true);
			 $('#displayNeedChange').attr('checked',false);

			 $('#changeEndDate').datebox('disable');
		}else{
			 $('#displayNowValid').attr('disabled',false);
			
			 $('#isInvalid').attr('disabled',false);
			 
			 $('#displayNeedChange').attr('disabled',false);
			 
		}
		
	}
	
</script>

<script type="text/javascript">
	  
    	$(function(){
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight-30;
    		var tableWidth  = document.body.clientWidth;
    		var role = $('#indexText').val();

    		$('#searchStringBox').searchbox({
    		    searcher:function(value,name){
    				$('#searchHisList').css('display','none');
    				searchRecord();
    		    },
    		   // menu:'#mm',
    		    prompt:'请输入专题编号'
    		});
    		
    		//$('#searchStringBox')searchbox.find("input.searchbox-text");
    		
    		$('#searchStringBox').searchbox('textbox').mouseover(function(){
	    		//var value = $('#searchStringBox').searchbox("textbox").val();
	 			   
	    		document.getElementById("searchHisConFrame").contentWindow.location.reload();
			    setSearchFrameHeight();
	    		$('#searchHisList').css('display','');
       		});
    		
    		$('#searchStringBox').searchbox('textbox').mouseout(function(){
    			$('#searchHisList').css('display','none');
        	});
			
			var url='${pageContext.request.contextPath}/tblFileIndexAction_searchHisList.action?index='+$("#indexText").val();
        	$('#searchHisConFrame').attr('src',url);
        	
        });//匿名函数结束
        function setSearchFrameHeight()
        {
        	 var menuHeight = 0;
			    $('#searchHisConFrame').contents().find("span[name='searchHis_con']").each(  
					function(){  				
								menuHeight = menuHeight+20;  
							}  
				);
			    $('#searchHisConFrame').height(menuHeight);
        }
      	function changeCondition(index)
        {
      		
			for(var i=0;i<12;i++)
			{
      			$('#Condition'+i).css('display','none');
      			
				$('#departmentLeft').combobox('hidePanel');
				$('#staffDeptLeft').combobox('hidePanel');
				$('#staffStateLeft').combobox('hidePanel');
				$('#docTypeFlagLeft').combobox('hidePanel');
				$('#contractTypeFlagLeft').combobox('hidePanel');
				$('#leftSmplType').combobox('hidePanel');
				$('#leftArchiveTypeFlag').combobox('hidePanel');
      			
			}
			$('#Condition'+index).css('display','');
			
	
	        var prop = '请输入档案编号、题名、';
			if(index==0)
			{
				//archiveCode or studyNo
				prop += '专题编号、专题名称、委托单位、SD';
			}
			 if(index==1)
			 {
				//archiveCode or studyNo
				prop += '专题编号、专题/合同/检查项名、SD、QA';
			 }
			 
			 if(index==2)
			 {
				//archiveCode  or sopname  or sopcode 
				prop += 'SOP类型编号、SOP类型名称、SOP名称、SOP编号';
				
			 }
			 if(index==3)
			 {
				 //archiveCode or department docName
				 prop += '部门、资料名称';
			 }
			 if(index==4)
			 {
				 //archiveCode instrumentName instrumentId
				 prop += '仪器名称、仪器编号、仪器厂商、仪器型号';
			 }
			 if(index==5)
			 {
				 //archiveCode staffName staffCode
				 prop += '工号、姓名、部门';
				
			 }
			 if(index==6)
			 {
				 //archiveCode docName docCode
				 prop += '资料名称、编号、类别名、类别编号';
			 }
			 if(index==7)
			 {
				 //archiveCode contractName contractCode
				 prop += '合同编号、合同名称';
				
			 }
			 if(index==8)
			 {
				 //archiveCode smplName smplCode
				 prop += '供试品编号，供试品名称、批号、委托单位';
					
			 }
			 if(index==9)
			 {
				 //archiveCode studyNo studyName
				 prop += '合同/专题编号、合同/专题名称';
				 parent.parent.$('#otherOperation6').css('display','');
			 }
			 prop+='、关键字';
      		$('#searchStringBox').searchbox('setValue','');
      		$('#searchStringBox').searchbox({
      			prompt:prop,
            });
            
			var url='${pageContext.request.contextPath}/tblFileIndexAction_searchHisList.action?index='+$("#indexText").val();
        	$('#searchHisConFrame').attr('src',url);
			
        }
	 	function selectFileDate()
	 	{
		 	if($('#isFileDate').attr('checked')=='checked')
			{
	 		 	$('#startDate').datebox('enable');
		 	 	$('#endDate').datebox('enable');
			}else{
				$('#startDate').datebox('setValue','');
				$('#endDate').datebox('setValue','');
				$('#startDate').datebox('disable');
		 	 	$('#endDate').datebox('disable');
			}
		 	searchRecord();
		 	
		}
		function keepEndDateCheckBoxClick()
		{
			if($('#keepEndDateCheckBox').attr('checked')=='checked')
			{
				$('#keepEndDate').datebox('enable');
			}else{
				$('#keepEndDate').datebox('setValue','');
				$('#keepEndDate').datebox('disable');
			}
			searchRecord();
		}
		function smplKeepEndDateCheckBoxClick()
		{
			if($('#smplKeepEndDateCheckBox').attr('checked')=='checked')
			{
				$('#smplKeepEndDate').datebox('enable');
			}else{
				$('#smplKeepEndDate').datebox('setValue','');
				$('#smplKeepEndDate').datebox('disable');
			}
			searchRecord();
		}
</script>
	  
</head>

<body >
	
	<s:hidden id="indexText" ></s:hidden>
	<s:hidden id="afterOneMonth" name="afterOneMonth"></s:hidden>
	
	<div style="width:100%;height:100%;">
		<div style="width:100%; border:1px solid #c8c8c8; padding-bottom: 10px;">
			  <table style="width:100%;">
				<tr style="height:30px;">
					<td style="width:100%;">
						<div id="searchStringBox" class="easyui-searchbox" style="position:absolute;width:250px; margin-top:13px;"></div>
						<div id='searchHisList' style="position:absolute;width:240;height:380; margin-top:-1px;display:none;border:1px solid #c8c8c8;z-index:9999;"
						 onmouseover="document.getElementById('searchHisConFrame').contentWindow.location.reload();
							 		setSearchFrameHeight();
					 				$(this).css('display','');"
						 onmouseout="$(this).css('display','none');">
							<iframe  id="searchHisConFrame" 
							  marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%"
							  style="border:0px solid #0f0;"   >
	                        </iframe>
	                            
                             
						</div>
						
					</td>
				</tr>
				<tr style="height: 30px;">
					<td>
					    <input id="isFileDate" type="checkbox" class="checkboxC" onclick="selectFileDate();"   />归档日期
						     	
				      	<a id="searchRecordButton" class="easyui-linkbutton" onclick='searchRecord();' href='#' 
				      	style="position: absolute;right: 10px; display:none;">查询</a> 
						
					</td>
				</tr>   
				<tr>
					<td>
				        <div id="startDate" disabled class="easyui-datebox" data-options="onSelect:function(data){searchRecord();}" style="width: 110px;"></div>~<div id="endDate" disabled class="easyui-datebox" data-options="onSelect:function(data){searchRecord();}" style="width: 110px;"></div>
					</td>
				</tr>   	
				<tr>
					<td>
				       <input id="keepEndDateCheckBox" onclick="keepEndDateCheckBoxClick();" type="checkbox"  class="checkboxC"/>截至以下日期
				       
					</td>
				</tr>
				<tr>
					<td>
						<div disabled id="keepEndDate" class="easyui-datebox" data-options="onSelect:function(data){searchRecord();}" style="width: 110px;"></div>保存到期的档案
					</td>
				</tr>	       
		       <tr>
		       	<td>
		       		<label for="isDestory">
				       	<input id="isDestory" type="checkbox" onclick='searchRecord();' class="checkboxC"/>显示已销毁记录
		       		</label>
		       	</td>
		       </tr>
		       <tr>
		       	<td>
		       		<label for="isValid">
		      	 		<input id="isValid" type="checkbox" onclick='searchRecord();' class="checkboxC"/>显示验证记录
		      	 	</label>
		       	</td>
		       </tr>
			</table>      

		</div>
		<div id="Condition0" style="width:100%; border:0px solid #c8c8c8; display:'';">
	       <table  style="width:100%">
	      		<!-- 专题 -->
	       		<tr style="height:30px;">
	       			<td>
	       				<label for="allStudyNoType">
		       				<input id="allStudyNoType" type="radio" name="studyNoType2" value="" checked="checked"
		       				onclick='searchRecord();'/>全部
	       				</label>
	       				<label for="studyType1">
			  				<input id="studyType1" type="radio" name="studyNoType2" value="1" 
			  				onclick='searchRecord();'/>专题
			  			</label>
			  			<label for="studyType2">
			  				<input id="studyType2" type="radio" name="studyNoType2" value="2" 
			  				onclick='searchRecord();'/>合同
			  			</label>
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition1" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	       	<!-- QA检查 -->
	       		<tr style="height:30px;">
	       			<td>
	       				<label for="allCheckItemType">
				  			<input id="allCheckItemType" type="radio" name="checkItemType" value="" checked="checked"
				  			onclick='searchRecord();'/>全部
			  			</label>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td>
	       				<label for="checkItemType1">
				  			<input id="checkItemType1" type="radio" name="checkItemType" value="1"
				  			onclick='searchRecord();'/>按专题编号
			  			</label>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td>
			  			<label for="checkItemType2">
				  			<input id="checkItemType2" type="radio" name="checkItemType" value="2"
				  			onclick='searchRecord();'/>按合同编号
			  			</label>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td>
			  			<label for="checkItemType3">
				  			<input id="checkItemType3" type="radio" name="checkItemType" value="3"
				  			onclick='searchRecord();'/>按设施检查
			  			</label>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td>
			  			<label for="checkItemType4">
				  			<input id="checkItemType4" type="radio" name="checkItemType" value="4"
				  			onclick='searchRecord();'/>按过程检查
			  			</label>
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition2" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	       		<!-- SOP -->
	       		
	       		<tr style="height:30px;">
	       			<td>
	       				<label for="displayNowValid">
		       				<input id="displayNowValid" type="checkbox" class="checkboxC"  name="isNowValid"  value="1" 
		       				onclick='searchRecord();' checked="checked"/>仅现行SOP
	       				</label>
		       		</td>
	       		</tr>
	       		<tr>
	       			<td>
	       				<label for="isInvalid">
		       				<input id="isInvalid" type="checkbox" class="checkboxC" name="isNowValid"   value="3"
		       				onclick='searchRecord();'/>显示已作废SOP
	       				</label>
	       			</td>
	       		</tr>
	       		<tr>
	       			<td>
	       				<label for="displayNeedChange">
		       				<input id="displayNeedChange" type="checkbox" class="checkboxC" name="isNowValid"  value="2" 
		       					onclick=" selectChangeCon();  "
		       					/>仅显示
	       				</label>
		       				<div id="changeEndDate" class="easyui-datebox" disabled 
		       				data-options="onSelect:function(data){searchRecord();}" style="width: 90px;"></div>
		       				前需要修订的SOP
	       			</td>
	       		</tr>
	       		<tr>
	       			<td>修订期限为生效后
	       				<input id="yearNum" class="easyui-validatebox" disabled 
	       				onblur="if(isNaN(value)){value='';}else{searchRecord();}"  
	       				style="width:30px;height:20px;top:0px;right:5px;vertical-align: middle;"></input>
	       			 
	       				<select id="yearNumUnit" class="easyui-combobox" 
	       				data-options="editable:false,onSelect: function(rec){searchRecord();}" 
									style="width:40px;position:absolute;top:0px;right:60px;">
							<option value="1" selected="selected">年</option>
							<option value="2">月</option>
							<option value="3">日</option>
				  		 	
						</select>
	       			 
	       			</td>
	       		</tr>
	       		<tr>
	       			<td>
	       				<label for="allSOPRecords">
		       				<input id="allSOPRecords" type="checkbox" class="checkboxC" name="isNowValid" value="" 
		       				onclick=" searchRecord(); "  />显示所有版本的SOP
	       				</label>
	       			</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition3" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 综合 -->
	       		<tr style="height:30px;">
	       			<td>
	       				部门：<input id="departmentLeft" required="true" 
			  				class="easyui-combobox"  data-options="valueField:'id',textField:'name',
				    		url:sybp()+'/tblFileContentGlpSynthesisAction_getDepartmentList.action?isCond=1',
				    		onSelect: function(rec){searchRecord();}"/>
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition4" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 仪器 -->
	       		<tr style="height:30px;">
	       			<td>
	       				
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition5" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 人员 -->
	       				<tr style="height:30px;">
	       			
			  				<td>部门：</td>
			  				<td >
			  					<input id="staffDeptLeft" 
				    				class="easyui-combobox"  data-options="valueField:'name',textField:'name',
				    				url:sybp()+'/tblFileContentGlpSynthesisAction_getDepartmentList.action?isCond=1',
				    				onSelect: function(rec){searchRecord();}"/>
			  				</td>
			  			</tr>
			  			<tr>
			  				<td>员工状态：</td>
			  				<td >
			  					<select id="staffStateLeft" class="easyui-combobox" 
			  					data-options="onSelect: function(rec){searchRecord();}">
			  						<option value="0" selected="selected">全部    </option>
			  						<option value="1" >在职    </option>
			  						<option value="2">离职     </option>
			  					</select>
			  				
			  				</td>
			  			</tr>
			  	
	       		
	       </table>
	      </div>
	       <div id="Condition6" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 行政 -->
	       		<tr style="height:30px;">
	       			<td>
	       				类别：
	       				<select id="docTypeFlagLeft" class="easyui-combobox"
	       				data-options="
	       				 width:100,
	       				 valueField:'docTypeFlag',
						 textField:'docTypeName',
						 editable:false,
						 url:sybp()+'/tblFileContentAdministrationAction_getDocTypeList.action?hasAll=1',
						 onSelect: function(rec){searchRecord();}
						">
	       					<!-- 
	       						<option value="0">全部</option>
			  					<option value="1">GLP相关资料</option>
			  					<option value="2">外来文件</option>
			  					<option value="3">内部发文</option>
			  					<option value="4">人字头文件</option>
	       					 -->
			  			</select>
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition7" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 合同 -->
	       		<tr style="height:30px;">
	       			<td>
	       				合同类型：<select id="contractTypeFlagLeft" class="easyui-combobox" 
	       					data-options="onSelect: function(rec){searchRecord();}">
	       							<option value="0">全部</option>
			  						<option value="1">专题合同</option>
			  						<option value="2">公司内部合同</option>
			  						<option value="3">其他合同</option>
			  					</select>
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition8" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 供试品留样-->
	       		<tr style="height:30px;">
	       			<td>
	       				供试品类型：
	       				<select id="leftSmplType" class="easyui-combobox"
	       				data-options="onSelect: function(rec){searchRecord();}">
	       					<option value="0" selected="selected">全部</option>
	       					<option value="1">农药</option>
			  				<option value="2">医药</option>
			  				<option value="3">化学品</option>
	       				</select>
			  		</td>
	       		</tr>
	       		<tr>
					<td>
				       <input id="smplKeepEndDateCheckBox" onclick="smplKeepEndDateCheckBoxClick();" type="checkbox" class="checkboxC"/>截至以下日期
				       
					</td>
				</tr>
				<tr>
					<td>
						<div disabled id="smplKeepEndDate" class="easyui-datebox" 
						data-options="onSelect: function(rec){searchRecord();}" style="width: 110px;"></div>保存到期的供试品
					</td>
				</tr>
				<tr>	       
		       		<td>
			       		<label for="isDestroySmpl">
			      	 		<input id="isDestroySmpl" type="checkbox" class="checkboxC"
			      	 		onclick="searchRecord();"/>显示供试品销毁记录
			      	 	</label>
		       		</td>
	       		</tr>
	       </table>
	      </div>
	      <div id="Condition9" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 标本 -->
	       		<tr style="height:30px;">
	       			<td>
	       				标本类型：
	       				<label for="leftSpecimenTypeFlag0">
		       				<input id="leftSpecimenTypeFlag0" name="leftSpecimenTypeFlag" type="radio" value="0" checked="checked"
		       				onclick="searchRecord();">全部</input>
	       				</label>
	       				<label for="leftSpecimenTypeFlag1">
	       				<input id="leftSpecimenTypeFlag1" name="leftSpecimenTypeFlag" type="radio" value="1" onclick="searchRecord();">湿标本</input>
	       				</label >
	       				<label for="leftSpecimenTypeFlag2">
	       				<input id="leftSpecimenTypeFlag2" name="leftSpecimenTypeFlag" type="radio" value="2" onclick="searchRecord();">蜡块</input>
	       				</label>
	       				<label for="leftSpecimenTypeFlag3">
	       					<input id="leftSpecimenTypeFlag3" name="leftSpecimenTypeFlag" type="radio" value="3" onclick="searchRecord();">切片</input>
	       				</label>
			  		</td>
	       		</tr>
				<!-- 
	       		<tr>
					<td>
				       <input id="specimenKeepEndDateCheckBox" onclick="keepEndDateCheckBoxClick();" type="checkbox" class="checkboxC"/>截至以下日期
				       
					</td>
				</tr>
				<tr>
					<td>
						<div disabled id="specimenKeepEndDate" class="easyui-datebox" style="width: 110px;"></div>保存到期的标本
					</td>
				</tr>
				 -->
	       		<tr>
		       	<td>
		       		<label for="isDestroySpecimen">
		      	 		<input id="isDestroySpecimen" type="checkbox" class="checkboxC" onclick="searchRecord();"/>显示标本销毁记录
		      	 	</label>
		       	</td>
		       </tr>

	       </table>
	      </div>
	       <div id="Condition10" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 基建 -->
	       		<tr style="height:30px;">
	       			<td>
	       				部门：<input id="department2Left" required="true" 
			  				class="easyui-combobox"  data-options="valueField:'id',textField:'name',
				    		url:sybp()+'/tblFileContentGlpSynthesisAction_getDepartmentList.action?isCond=1',
				    		onSelect: function(rec){searchRecord();}"/>
			  		</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      <div id="Condition11" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	       <table  style="width:100%">
	      		<!-- 日志 -->
	       		<tr style="height:30px;">
	       			<td>
	       				日志时间： 
	       				<div id="logStartDate" class="easyui-datebox" style="width: 90px;" data-options="onSelect: function(rec){searchRecord();}"></div>
	       				~<div id="logEndDate" class="easyui-datebox" style="width: 90px;" data-options="onSelect: function(rec){searchRecord();}"></div>
					
	       				
			  		</td>
	       		</tr>
	       		<tr>
	       			<td>
	       				档案类别：
	       				<select id="leftArchiveTypeFlag" class="easyui-combobox"
	       				data-options="onSelect: function(rec){searchRecord();}">
	       					<option value="0" selected="selected">全部</option>
	       					<option value="1">专题</option>
	       					<option value="2">QA检查资料</option>
	       					<option value="3">SOP资料</option>
	       					<option value="4">综合资料</option>
	       					<option value="5">仪器资料</option>
	       					<option value="6">人员档案</option>
	       					<option value="7">行政综合资料</option>
	       					<option value="8">合同资料</option>
	       					<option value="9">供试品留样</option>
	       					<option value="10">标本管理</option>
	       					<option value="11">基建</option>
	       				</select>
	       			</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      
       	
 	</div>
       
    
    
 
     
</body>
</html>




