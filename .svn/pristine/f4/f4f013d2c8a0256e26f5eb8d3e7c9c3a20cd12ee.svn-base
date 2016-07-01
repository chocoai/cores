<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnatomyReqAction/addEditAnatomyReq.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnatomyReqAction/editAnatomyReqAnimalList.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnatomyReqAction/editAnatomyReqPathPlanCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnatomyReqAction/editAnatomyReqVisceraWeigh.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnatomyReqAction/editAnatomyReqAttachedViscera.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	var studyNoPara;//专题编号
	var studydirector;//课题负责人
	var pathDirector;//专题病理负责人
	var animalType;//动物种类
	var addOrEdit; //添加编辑判断
	var submitFlag;
	var abnVisceraFixedFlag;//异常组织固定标识
	var abnVisceraHistopathCheckFlag;//异常组织镜检标识
	var abnVisceraWeighFlag;//异常组织称重标识
	var reqNo;//申请编号
	var reqId;//申请ID
	var pathCheckVisceraRows;//编辑解剖申请-脏器/组织学检查点击确定按钮后临时存放可选的脏器列表
	var visceraWeighRows;//编辑解剖申请-脏器称重点击确定按钮后临时存放可选的脏器列表
	var animalListRows;//编辑解剖申请-申请动物列表点击确定按钮后临时存放可选的动物列表
    $(function(){
    	tableHeight = document.body.clientHeight - 160;
		tableWidth  = document.body.clientWidth/2-50;
    	studyNoPara = $('#studyNoPara').val();
    	pathDirector= $('#pathdirector').val();
    	animalType= $('#animalType').val();
    	addOrEdit= $('#addOrEdit').val();
    	submitFlag= $('#submitFlag').val();
    	abnVisceraFixedFlag=$('#abnVisceraFixedFlag').val();
    	reqNo= $('#reqNo').val();
    	reqId= $('#reqId').val();
    	if(abnVisceraFixedFlag==1){
    		document.getElementById("abnVisceraCheck").checked = true;
        }
    	abnVisceraHistopathCheckFlag=$('#abnVisceraHistopathCheckFlag1').val();
		if(abnVisceraHistopathCheckFlag==1){
			document.getElementById("abnVisceraCheck2").checked = true;    	
		}
    	abnVisceraWeighFlag=$('#abnVisceraWeighFlag').val();
		//if(abnVisceraWeighFlag==1){
			//document.getElementById("abnVisceraWeigh").checked = true;     	
		//}
    	$('#animalType1').val(animalType);
    	$('#pathDirector1').val(pathDirector);
    	synchronizationDissectBeginDateAndEndDate();
    	initAbnVisceraCheckboxs();
//    	if(studyState>0){
//    		$('#editPathPlanCheckButton').linkbutton('disable');
//			$('#editPathPlanVisceraWeighButton').linkbutton('disable');
 //   	}
        //初始化解剖申请-动物列表
        initAnatomyReqAnimalList(addOrEdit);
        //初始化解剖申请-脏器称重
        initAnatomyReqVisceraWeighTable(addOrEdit);
       //初始化解剖申请-脏器/组织学检查
        initAnatomyReqPathPlanCheckTable(addOrEdit);
        //解剖原因如果选择计划解剖，加载解剖次数以供选择，选择其他则隐藏解剖次数下拉框
        $('#anatomyRsn').combobox({
        	onSelect:function(rec){
               if(rec.value==1){
            	   $('#dissectNumDiv').css('display','');
            	   $('#dissectNum').combobox({
                       url:sybp()+'/tblDissectPlanAction_dissectNumByStudyNo.action?studyNoPara='+studyNoPara,
                       valueField: 'id',    
                       textField: 'text',
                       editable:false,
                       required:true,
                       width:114,
                       panelHeight:100,
                       onSelect:function(rec){//如果选定解剖次数，申请解剖动物列表将默认加载计划解剖动物列表，可以编辑
            		     $('#anatomyReqAnimalList').datagrid('loadData', { total: 0, rows: [] });  
            		     initAnatomyReqAnimalList('dissectNum');
            		     var dissectNum=$('#dissectNum').combobox('getValue');
            		     $.ajax({
            	        		url:sybp()+'/tblDissectPlanAction_getDissectPlanDateByDissectNum.action?studyNoPara='+studyNoPara+'&dissectNumPara='+dissectNum,
            	        		type:'post',
            	        		data:{},
            	        		dataType:'json',
            	      	    		success:function(r){
            	      	    			if(r){
            	      	    				$('#beginDatebox').datebox('setValue', r.beginDate);
            	      	    				$('#endDatebox').datebox('setValue', r.endDate);
            	      	    				$('#testPhase').combobox('setText', r.describe);
            	      	    			}
            	      	    		}
            	      	    	});
            	       }
                    });
               }else{
            	   $('#dissectNum').combobox({required:false});
            	   $('#dissectNum').combobox('setValue', '');
            	   $('#dissectNumDiv').css('display','none');
 //           	   $('#anatomyReqAnimalList').datagrid('loadData', { total: 0, rows: [] });  
            	   $('#beginDatebox').datebox('setValue', '');
	    		   $('#endDatebox').datebox('setValue', '');
               }
            },
         });
        initAnatomyRsn();
        if(addOrEdit=='edit'||addOrEdit=='view'){
    		$.ajax({
        		url:sybp()+'/tblAnatomyReqAction_toEdit.action?reqId='+reqId,
        		type:'post',
        		data:{},
        		dataType:'json',
      	    		success:function(r){
      	    			if(r){
      	    				$('#anatomyRsn').combobox('setValue', r.anatomyRsn);
      	    				if(r.anatomyRsn!=1){
      	    					$('#dissectNumDiv').css('display','none');  
          	    				}
      	    				$('#dissectNum').combobox('setValue', r.anatomyPlanNum);
      	    				$('#beginDatebox').datebox('setValue', r.beginDate);
      	    				$('#endDatebox').datebox('setValue', r.endDate);
      	    				$('#testPhase').combobox('setText', r.testPhase);	
      	    			}
      	    		}
      	    	});
        }
        
    	$('#pathPlanDiv').css('display','');
    	$('#toolbar').css('display',''); 
    	if(addOrEdit=='view'){
    		$('#saveAnatomyReqButton').linkbutton('disable');
    		$('#submitAnatomyReqButton').linkbutton('disable');
    		$('#editAnatomyReqAnimalListButton').linkbutton('disable');
    		$('#editPathPlanCheckTableButton').linkbutton('disable');
    		$('#editPathPlanVisceraWeighButton').linkbutton('disable');
    		$('#anatomyRsn').combobox('disable');
    		$('#dissectNum').combobox('disable');
    		$('#testPhase').combobox('disable');
    		$('#beginDatebox').datebox('disable');
    		$('#endDatebox').datebox('disable');
        }else if(addOrEdit=='edit' && submitFlag == 2){
        	$('#anatomyRsn').combobox('disable');
    		$('#dissectNum').combobox('disable');
    		$('#testPhase').combobox('disable');
    		$('#beginDatebox').datebox('disable');
    		$('#endDatebox').datebox('disable');
        }
     });//匿名函数结束
	function editPathPlanCheck(){
		showEditPathPlanCheckDialog('','edit','编辑脏器剖检/固定/镜检内容');
     }
    function editPathPlanVisceraWeigh(){
    	showEditPathPlanVisceraWeighDialog('','edit','编辑脏器称重内容');
    }
    //将解剖原因初始选择为计划解剖，并加载解剖计划中的解剖次数以供选择
    function initAnatomyRsn(){
    	$('#anatomyRsn').combobox('select',1);
    	$('#dissectNumDiv').css('display','');
    }
    //取消按钮事件
    function backButton(){
    	window.location.href=sybp()+'/tblAnatomyReqAction_list.action?studyNoPara='+studyNoPara+'&reqId='+reqId;
    }
    //解剖开始日期和解剖结束日期默认为同一天
    function synchronizationDissectBeginDateAndEndDate(){
    	$('#beginDatebox').datebox({
    	    onSelect: function(date){
    	        var beginDate=$('#beginDatebox').datebox('getValue');
    	        $('#endDatebox').datebox('setValue', beginDate);
    	    }
    	});


    }
	//打印
    function printButton(){
        if(addOrEdit == 'view'){
        	parent.parent.showMessager(2,'数据加载中...',true,5000);
        	window.location.href=sybp()+'/tblAnatomyReqAction_toReport.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+reqNo+'&reqId='+reqId;
        }else{
        	if( $('#anatomyReqForm').form('validate') ){
        		$('#saveAnatomyReqButton').linkbutton('disable');
        		$('#submitAnatomyReqButton').linkbutton('disable');
        		//打印前保存
        		addAnatomyReqToDB(2);
        	}
        }
    }
    
</script>
</head>
<body scroll="no">
      <!-- 课题编号 -->
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <!-- 课题负责人 -->
    <s:hidden id="studydirector" name="studydirector"></s:hidden>
    <!-- 专题病理负责人 -->
    <s:hidden id="pathdirector" name="pathdirector"></s:hidden>
    <!-- 动物种类 -->
    <s:hidden id="animalType" name="animalType"></s:hidden>
    <!-- 添加编辑判断 -->
    <s:hidden id="addOrEdit" name="addOrEdit"></s:hidden>
    <!-- 添加提交标识判断 -->
    <s:hidden id="submitFlag" name="submitFlag"></s:hidden>
    <!-- 申请编号-->
    <s:hidden id="reqNo" name="reqNo"></s:hidden>
    <!-- 申请ID -->
    <s:hidden id="reqId" name="reqId"></s:hidden>
    <!-- 异常组织固定标志 -->
    <s:hidden id="abnVisceraFixedFlag" name="abnVisceraFixedFlag"></s:hidden>
    <!-- 异常组织镜检标志 -->
    <s:hidden id="abnVisceraHistopathCheckFlag1" name="abnVisceraHistopathCheckFlag"></s:hidden>
    <!-- 异常组织称重标志 -->
    <s:hidden id="abnVisceraWeighFlag" name="abnVisceraWeighFlag"></s:hidden>
    <div id="pathPlanDiv"  style="width:100%;height:100%; display:none;overflow:hidden">
       <div id="toolbar" style="display:none;">
			<a id="saveAnatomyReqButton"  class="easyui-linkbutton" onclick="saveAnatomyReq()" data-options="iconCls:'icon-tablesave',plain:true">保存</a>
			<a id="submitAnatomyReqButton"  class="easyui-linkbutton" onclick="submitAnatomyReq()" data-options="iconCls:'icon-tablego',plain:true">提交</a>
			<a id="printButton"  class="easyui-linkbutton" onclick="printButton()" data-options="iconCls:'icon-print',plain:true">打印</a>
			<a id="backButton"  class="easyui-linkbutton" onclick="backButton()" data-options="iconCls:'icon-nook',plain:true">取消</a>
		</div>
	   <div id="viewAnatomyReqDiv" style="width:100%;height:100%;">
	      <div style="margin-top:1px;margin-left:1px" class="edit_table">
	       <form id="anatomyReqForm">
			   <table cellpadding="2" cellspacing="0"    style="border-top:1px solid #e3e6eb;border-bottom:1px solid #e3e6eb;"  width="100%">
			    <tr><!-- style="border-right:1px solid #e3e6eb;" -->
			      <td  >&nbsp; 病理专题负责人：<input id="pathDirector1" style="width:83px;border:1px;border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none"  readonly="true"/></td>
			      <td colspan="2">动物种类：<input id="animalType1"  style="width:100px;border:1px;border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none"  readonly="true"/></td>
			    </tr>
			    <tr  style="border-top:1px solid #e3e6eb;">
			        <!--  width="320" style="border-right:1px solid #e3e6eb;border-bottom:1px solid #e3e6eb;"-->
	               <td width="320" >
	                   <table >
	                    <tr>
	                       <td>&nbsp;解剖原因：<select id="anatomyRsn" class="easyui-combobox" name="anatomyRsn" style="width:100px;" editable=false required=true panelHeight=85>   
												    <option value="1">计划解剖</option>   
												    <option value="2">濒死解剖</option>   
												    <option value="3">死亡解剖</option>   
												    <option value="4">安乐死解剖</option>   
									</select> 
						   </td>
						   <td>
						      <div id="dissectNumDiv"  style="display:none;width:100%"> <input id="dissectNum" style="width:60px;"/></div> 
						   </td>
	                    </tr>
	                   </table>    <!-- 
					    &nbsp;解剖原因：<select id="anatomyRsn" class="easyui-combobox" name="anatomyRsn" style="width:100px;" editable=false required=true panelHeight=75>   
												    <option value="1">计划解剖</option>   
												    <option value="2">濒死解剖</option>   
												    <option value="3">死亡解剖</option>   
									</select> 
	                </td>
	                <td>
	                        <div id="dissectNumDiv"  style="display:none;width:100%"> <input id="dissectNum" style="width:60px;"/></div> 
	                --></td>
	                <td>实验阶段：<select id="testPhase" class="easyui-combobox" name="testPhase" style="width:85px;" panelHeight=113  editable=true >
	                                <option value="1">检疫期</option>   
								    <option value="2">适应期</option>   
								    <option value="3">给药期</option>  
								    <option value="4">恢复期</option> 
								    <option value="5">其他阶段</option>
								</select>  
								
		                    &nbsp; &nbsp; 解剖日期：<input id="beginDatebox" type="text" class="easyui-datebox" required=true style="width:100px;height:19px;" editable="false"/>
		                    ---&nbsp;&nbsp;<input id="endDatebox" type="text" class="easyui-datebox" required=true style="width:100px;height:19px;" editable="false"/>                               
	                 </td>   
	            <!--    <td width="320" style="border-right:1px solid #e3e6eb;border-left:1px solid #e3e6eb;">
		                     &nbsp; 解剖日期：<input id="beginDatebox" type="text" class="easyui-datebox" required=true style="width:100px;height:19px;" editable="false"/>
		                    ---&nbsp;&nbsp;<input id="endDatebox" type="text" class="easyui-datebox" required=true style="width:100px;height:19px;" editable="false"/>                               
	                 </td> --> 
	                
	             </tr>
	                   
	                                             
	                                                   
	                  
	               <!-- 
	               <td  rowspan="2">
	                                                 实验阶段：<select id="testPhase" class="easyui-combobox" name="testPhase" style="width:100px;" panelHeight=113 editable=false required=true>
	                                <option value="1">检疫期</option>   
								    <option value="2">适应期</option>   
								    <option value="3">给药期</option>  
								    <option value="4">恢复期</option> 
								    <option value="5">其他</option>
								</select>                    
	                </td>-->
	              
	               <!--  
	              <tr height="30px">
	                <td style="border-right:1px solid #e3e6eb;">
	                     &nbsp; 解剖日期：<input id="beginDatebox" type="text" class="easyui-datebox" required=true style="width:100px;height:19px;" editable="false"/>
	                    ---&nbsp;&nbsp;<input id="endDatebox" type="text" class="easyui-datebox" required=true style="width:100px;height:19px;" editable="false"/>                               
	               </td>
	                
              </tr>-->
			   </table> 
	       </form> 
	           
       </div>
	          <table>
	            <tr>
	               <td>
	                 <div style="width:190px;;border:1px solid #E0E0E0 " title="申请解剖动物">
					      <div style="align:center;"> 
			        	   <table id="anatomyReqAnimalList" ></table>
			        	  </div><br/>
			        	  <div style="align:center;margin-bottom:1px;margin-left:2px">
			        	         动物数量：<input id="animalNumber" style="width:30px;border:1px;border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none" readOnly="true"/>只
			        	  </div>
			         </div> 
	               </td>
	               <td>
	                   <div style="width:425px;;border:1px solid #E0E0E0 ">
					       <table id="anatomyReqPathPlanCheckTable" ></table><br/>
					       <div style="align:center;margin-bottom:1px;margin-left:2px">
					       <input type="checkbox" id="abnVisceraAnatomyCheck" name="abnVisceraAnatomyCheck" value="1" style="vertical-align:middle;" checked="checked"/>异常组织和脏器剖检&nbsp;&nbsp; 
					         <input type="checkbox" id="abnVisceraCheck" name="abnVisceraCheck" value="1" style="vertical-align:middle;" checked="checked"/>异常组织和脏器固定&nbsp;&nbsp;
					         <input type="checkbox" id="abnVisceraCheck2" name="abnVisceraCheck2" value="1" style="vertical-align:middle;" checked="checked"/>异常组织和脏器镜检
					       </div>
					   </div>
	               </td>
	               <td>
	                    <div style="width:497px;;border:1px solid #E0E0E0 ">
	        	           <table id="anatomyReqVisceraWeighTable" ></table><br/>
	        	           <!-- <div style="align:center;margin-bottom:1px;margin-left:2px">  
	        	             <input type="checkbox" id="abnVisceraWeigh" name="abnVisceraWeigh" value="1" style="vertical-align:middle;" checked="checked"/>&nbsp;异常组织和脏器称重
	        	           </div> -->
	        	       </div>
	               </td>
	            </tr>
	          </table>
		
	
		
		<div id="anatomyReqAnimalListToobar">
		    <a id="editAnatomyReqAnimalListButton"   class="easyui-linkbutton" onclick="editAnatomyReqAnimalListButton()" data-options="iconCls:'icon-edit',plain:true">编辑动物列表</a>
		</div>
		<div id="anatomyReqPathPlanCheckToobar">
		    <a id="editPathPlanCheckTableButton"   class="easyui-linkbutton" onclick="editPathPlanCheckTableButton()" data-options="iconCls:'icon-edit',plain:true">编辑脏器剖检/固定/镜检内容</a>
		</div>
		<div id="anatomyReqVisceraWeighToobar">
		    <a id="editPathPlanVisceraWeighButton"  class="easyui-linkbutton" onclick="editPathPlanVisceraWeighButton()" data-options="iconCls:'icon-edit',plain:true">编辑脏器称重内容</a>
		</div>
		</div>
		<%@include file="/WEB-INF/jsp/tblPathPlanAction/editPathPlanCheck.jspf" %>
        <%@include file="/WEB-INF/jsp/tblPathPlanAction/editPathPlanVisceraWeigh.jspf" %>
        <%@include file="/WEB-INF/jsp/tblPathPlanAction/editPathPlanAttachedViscera.jspf" %>
        <%@include file="/WEB-INF/jsp/tblAnatomyReqAction/editAnatomyReqAnimalList.jspf" %>
        <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
    </div>
        
</body>
</html>
