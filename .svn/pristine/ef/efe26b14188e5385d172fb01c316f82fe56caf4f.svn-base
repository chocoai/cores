<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CoRES基于研究的检查</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>

<script type="text/javascript">
	
	function clickStudy(studyNo)
	{
		//alert(studyNo);
		var indexValue = $('#indexText').val();
		//alert("left传main的index="+indexValue);
		var url = sybp()+"/qAChkPlanAction_main.action?studyNoParam="+studyNo+"&afterTabOpenAction="+$('#afterTabOpenActionForLeft').val();
		
		if($('#selectStudyNoParaForLeft').val()!=null&&$('#selectStudyNoParaForLeft').val()!='')//从开始检查过来的
	    {
			//indexValue = 2;
			url += "&oneChkPlanId="+$('#oneChkPlanIdForLeft').val();
	    }
		if($('#selectChkReportCodeForLeft').val()!=null&&$('#selectChkReportCodeForLeft').val()!='')
	    {
		   // indexValue = 4;
		    url += "&selectChkReportCode="+$('#selectChkReportCodeForLeft').val();
	    }
	    url+="&index="+indexValue;
	    
		if(studyNo!='')
		{
			document.getElementById(studyNo).href=url;
			document.getElementById(studyNo).click();
		}else{
			
			document.getElementById('hiddenForClearTabs').href=url;
			document.getElementById('hiddenForClearTabs').click();
		}
	}
	function clickAppointQAButton(itemId,studyNo){
		parent.parent.onAppointQAButton(itemId,studyNo,1);
		
		//$('#studyDatagrid').datagrid('reload');
		//parent.document.getElementsByName("chkPlanMain").reload();
	}
	function reClickAppointQAButton(){
		var row = $('#studyDatagrid').datagrid('getSelected');
		if(row!=null)
		{
			if(row.qa==''||row.qa==undefined){
				parent.$.messager.alert("提示框","该专题还没QA，重新任命QA和任命QA是一样的！","",function(r){
					parent.parent.onAppointQAButton(row.itemId,row.studyNo,1);
					
					//$('#studyDatagrid').datagrid('reload');
					//parent.document.getElementsByName("chkPlanMain").reload();
				});
			}else{
				parent.parent.onAppointQAButton(row.itemId,row.studyNo,1);
				
				//$('#studyDatagrid').datagrid('reload');
				//parent.document.getElementsByName("chkPlanMain").reload();
			}
		}else{
			parent.$.messager.alert("提示框","请选择一个专题！");
		}
		
	}
	
	function clickStudyStatus()
	{
		 var studyType = $("input[name='searchTyper']:checked").val();
		 var role = $('#leftRole').val();
		//alert(studyType);
		if(studyType=='noFinish')
		{
			if(role=='QALead')
			{
				$('#reAppointQAForStudy').linkbutton('enable');
			}
			$('#studyStatus').combobox('enable');
			$('#startDate').datebox('disable');
			$('#endDate').datebox('disable');
			searchNoFinish();
		}else if(studyType='finish'){
			$('#reAppointQAForStudy').linkbutton('disable');
			
			$('#studyStatus').combobox('disable');
			$('#startDate').datebox('enable');
			$('#endDate').datebox('enable');
			searchFinish();
		}
		 
	}
	function searchNoFinish()
	{
		
		var studyStatus = $('#studyStatus').combobox('getValue');
		
		var studyName=$('#searchStudyBox').searchbox('getValue');
		$('#studyDatagrid').datagrid({
			url:sybp()+"/qAChkPlanAction_getStudyList.action?studyNoString="+studyName+"&studyStatus="+studyStatus,
			
    	});
		
		parent.parent.parent.$('#topInfoLabel').html("");

		//$('#studyDatagrid').datagrid('reload');
	}
	function searchFinish()
	{
		var start=$('#startDate').datebox('getValue');
		var end=$('#endDate').datebox('getValue');
		$('#studyDatagrid').datagrid({
			url:sybp()+'/qAChkPlanAction_getStudyList.action?start='+start+"&end="+end,
    	});
	
		parent.parent.parent.$('#topInfoLabel').html('');
	
		//$('#studyDatagrid').datagrid('reload');
	}
</script>

<script type="text/javascript">
	  
    	$(function(){
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight-30;
    		var tableWidth  = document.body.clientWidth;
    		var role = $('#leftRole').val();

    		//if($('#selectStudyNoParaForLeft').val()!=null&&$('#selectStudyNoParaForLeft').val()!='')//从开始检查过来的
        	//{
			//	$('#indexText').val(2);
				//$('#oneChkPlanIdForLeft').val()选择到具体的某个检查计划
				
        	//}
        //	if($('#selectChkReportCodeForLeft').val()!=null&&$('#selectChkReportCodeForLeft').val()!='')
        	//{
        		//$('#indexText').val(4);
        		//选择到具体的某个报告
          //  }
        	//alert(role);
    		$('#studyDatagrid').datagrid({
    			//url:sybp()+'/qAChkPlanAction_getStudyList.action',
				height:tableHeight-100,
				//width:tableWidth,
				width:270,
				singleSelect:true,
				columns:[[
						
						{
							title:'专题列表',
							field:'studyNo',
							width:113,
							halign:'center',
							align:'left',
							
							formatter: function(value,row,index){
								  
								 return "<a id='"+row.studyNo+"' disabled onclick='clickStudy(\""+row.studyNo+"\");' target='chkPlanMain' href='#' >"+value+"</a>";
							}
						},
						{
							title:'SD',
							field:'sd',
							width:60,
							halign:'center',
							align:'center',
							hidden:true,
						},
						{
							title:'noItem',
							field:'noItem',
							hidden:true,
						},
						{
							title:'itemId',
							field:'itemId',
							hidden:true,
						},
						{
							title:'finish',
							field:'finish',
							hidden:true,
						},
						
						{
							title:'QA检查员',
							field:'qa',
							width:65,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								//alert(row.studyNo+"==="+row.noItem);
								if(row.finish!=true&&role=='QALead')
								{
									if(value==undefined||value=='undefined'||value==null||value=='')
									{
										return '<a onclick=\'clickAppointQAButton(\"'+row.itemId+'\",\"'+row.studyNo+'\")\' href=\'#\'>指定QA检查员</a>';
										
									}else{
										return value;
									}
								}else{
									
									if(value==undefined||value=='undefined'||value==null||value=='')
									{
										return '';
										
									}else
									{
										return value;
									}
								}
							}
						},
						{
							title:'试验性质',
							field:'studyTypeName',
							hidden:false,
							width:200
						},

				]],
				/*
				rowStyler: function(index,row){
    				$('#studyDatagrid').datagrid('beginEdit',index);
	    		}*/
	    		onSelect:function(rowIndex, row){
		    		//alert("onchk:"+rowIndex+"==="+row);
	    			// return "<a id='"+row.studyNo+"' onclick='clickStudy(\""+row.studyNo+"\");' target='chkPlanMain' href='#' >"+value+"</a>";
					document.getElementById(row.studyNo).click();
					var label = "专题编号："+row.studyNo;
					if(row.sd!=null&&row.sd!=''){
						label += "&nbsp;&nbsp;&nbsp;SD:"+row.sd;
					}else{
						label += "&nbsp;&nbsp;&nbsp;SD:待任命";
					}
					if(row.qa!=null&&row.qa!=''){
						label += "&nbsp;&nbsp;&nbsp;QA:"+row.qa;
					}else{
						label += "&nbsp;&nbsp;&nbsp;QA:待任命";
					}
					label="<h4>"+label+"</h4>";
					parent.parent.parent.$('#topInfoLabel').html(label);
		    	},
		    	onLoadSuccess:function(data){
		    		if($('#selectStudyNoParaForLeft').val()!=null&&$('#selectStudyNoParaForLeft').val()!='')//从开始检查过来的
		    		{
		    			$('#studyDatagrid').datagrid('selectRow',0);
		    			var row = $('#studyDatagrid').datagrid('getSelected');
		    
		    			if($('#afterTabOpenActionForLeft').val()!=null&&$('#afterTabOpenActionForLeft').val()!='')
			            {
			            	//1方案2报告3任命qa
			            	if($('#afterTabOpenActionForLeft').val()=='3'){
			            		$('#afterTabOpenActionForLeft').val('');
			            		clickAppointQAButton(row.itemId,row.studyNo);
			            	}
			            }
		    		}else{
		    			document.getElementById('hiddenForClearTabs').click();
		    		}
        			//alert("load finid=="+data+"---"+data.rows.length);
			        /*if(data!=null&&data.rows.length>0)
			        {
			        	$('#studyDatagrid').datagrid('selectRow',0);
			        }else{
			        	document.getElementById('hiddenForClearTabs').click();
			        }*/
        		}
				
        	});
    		$('#searchStudyBox').searchbox({
				width:270,
				searcher:function(value,name){
					if($("input[name='searchTyper']:checked").val()=='noFinish')
	        		{
	        			searchNoFinish();
	        		}
    	   	 	},
    	    	prompt:'请输入专题编号'
        	});

        	$('#startDate').datebox({
        		onSelect: function(date){
        			searchFinish();
        		}
        	});
        	$('#endDate').datebox({
        		onSelect: function(date){
        			searchFinish();
        		}
        	});
        	$('#studyStatus').combobox({
        		 onSelect: function(rec){
        			searchNoFinish();
                	
           		 }
            });
            
        	$('#startDate').datebox('setValue',$('#startTime').val());
        	$('#endDate').datebox('setValue',$('#endTime').val());

        	if(role=="QALead")
        	{
				$('#checkInStudyByMandle').linkbutton('enable');
				$('#reAppointQAForStudy').linkbutton('enable');
        	}else
        	{
        		$('#checkInStudyByMandle').linkbutton('disable');
        		$('#reAppointQAForStudy').linkbutton('disable');
            }


        	if($('#selectStudyNoParaForLeft').val()!=null&&$('#selectStudyNoParaForLeft').val()!='')//从开始检查过来的
        	{
				//$('#indexText').val(2);
	        	$('#searchStudyBox').searchbox('setValue',$('#selectStudyNoParaForLeft').val());
        		
        		searchNoFinish();
        		
            }else{
            	searchNoFinish();
        		
            }
            
            
        });//匿名函数结束
      
	 
</script>
	  
</head>

<body >
	<s:hidden id="startTime" name="startTime"></s:hidden>
	<s:hidden id="endTime" name="endTime"></s:hidden>
	<!-- 
	<s:hidden id="leftRole" name="role"></s:hidden>
	 -->
	<input id="leftRole" type="hidden" value=${role }></input>
	<s:hidden id="haveRightForLeft" ></s:hidden>
	
	<s:hidden id="selectStudyNoParaForLeft" name="selectStudyNoPara"></s:hidden>
	<s:hidden id="oneChkPlanIdForLeft" name="oneChkPlanId"></s:hidden>
	
	<s:hidden id="selectChkReportCodeForLeft" name="selectChkReportCode"></s:hidden>
	
	<s:hidden id="afterTabOpenActionForLeft" name="afterTabOpenAction"></s:hidden>
	
	<a id='hiddenForClearTabs' onclick='clickStudy("");' target='chkPlanMain' href='#' ></a>
	
	<s:hidden id="indexText" name="indexText"></s:hidden>
	<div class="container">
		<label for="radioForNoFinishStudy">
			<input id="radioForNoFinishStudy" type="radio" onclick="clickStudyStatus();" name="searchTyper" value="noFinish" checked="checked"/>专题未完成<br/>
		</label>
			专题状态：
			  <select id="studyStatus" class="easyui-combobox" data-options="editable:false" style="width:203px;">
				<option value="20" selected="selected">全部未完成</option>
	  		 	<option value="0">待指定QA检查员</option>
	  		 	<option value="5">专题方案待接收</option>
	  		 	<option value="1">未制定检查计划</option>
	  		 	<option value="2">检查计划待审批</option>
	  		 	<option value="3">申请计划变更待审批</option>
	  		 	<option value="4">日程变更需修改检查计划</option>
	  		 	<option value="6">专题待检查</option>
	  		 	<option value="7">报告待审批</option>
	  		 	<option value="8">报告待处理回复或延迟</option>
	  		 	<option value="9">报告待再检查</option>
	  		 	<option value="10">专题报告待接收</option>
	  		 	
			</select>
		
         <div id="searchStudyBox" class="easyui-searchbox" style="position:absolute; margin-top:13px;"></div>
      
       <div style="display:block; position:absolute;top:64px;left:0px;right:0px; bottom:0px;" >
 	 	 
 	 	 <div id="studyDatagrid" style="border: 1px solid #c8c8c8;" >
 	 
		 </div>
		 <label for="radioForFinishStudy">
		 	<input id="radioForFinishStudy" type="radio" name="searchTyper" onclick="clickStudyStatus();" value="finish"/>专题已完成&nbsp;<br/>
		 </label>   
		    专题启动日期<div disabled id="startDate" class="easyui-datebox" style="width: 95px;"></div>~<div id="endDate" disabled class="easyui-datebox" style="width: 95px;"></div>
       
		 <div>
			<!-- 
			 onclick="parent.addNewStudyPlanDialog();"
			 -->
			<a id="checkInStudyByMandle" class="easyui-linkbutton" 
			onclick="parent.showStudyPlanAddEditDialog('afterSaveQAStudyChkIndex','add','新增专题检查索引')">手动登记专题</a> 
			<a id="reAppointQAForStudy" class="easyui-linkbutton"  onclick='reClickAppointQAButton()' href='#'>重新任命QA</a>
		</div>
 	  </div>
       
    </div>
    
 
     
</body>
</html>




