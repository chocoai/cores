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
		 var index = 0;
		 var contentWind = parent.document.getElementById('archiveMainFrame').contentWindow;
		 if(contentWind.$)
		 {
			 var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
			 var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
			 
			/*公共的参数publicSearchPart中的内容*/
			var searchString = $('#searchStringBox').searchbox('getValue');
			/*var isFileDate = 0;
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
			*/

			 if(index==0)//sop
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
				//parent.searchSOPRecord(isAll,isNowValid,isInvalid1,needChange,changeEndDate,yearNum,yearNumUnit,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
				parent.searchSOPRecord(isAll,isNowValid,isInvalid1,needChange,changeEndDate,yearNum,yearNumUnit,0,'','','',0,0,searchString);
		   			 
			 }
			 if(index==1)//日志
			 {
				var archiveTypeFlag = $('#leftArchiveTypeFlag').combobox('getValue');
				var logStartDate = $('#logStartDate').datebox('getValue');
				var logEndDate = $('#logEndDate').datebox('getValue');
				//parent.searchLog2(archiveTypeFlag,logStartDate,logEndDate,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
				parent.searchLog2(archiveTypeFlag,logStartDate,logEndDate,0,'','','',0,0,searchString);

			 }
			 
		 }else{
			 index = 0;
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
			//parent.searchSOPRecord(isAll,isNowValid,isInvalid1,needChange,changeEndDate,yearNum,yearNumUnit,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString);
			parent.searchSOPRecord(isAll,isNowValid,isInvalid1,needChange,changeEndDate,yearNum,yearNumUnit,0,'','','',0,0,searchString);
	   	
		 }
		 
		 
		
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
      		
			for(var i=0;i<2;i++)
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
				
				//archiveCode  or sopname  or sopcode 
				prop += 'SOP类型编号、SOP类型名称、SOP名称、SOP编号';
				
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
		<div style="width:100%; border:1px solid #c8c8c8; padding-bottom: 5px;">
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
		<!-- 
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
		 -->
			</table>      

		</div>
		<div id="Condition0" style="width:100%; border:0px solid #c8c8c8; display:'';">
	      
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
	      <div id="Condition1" style="width:100%;height:50%; border:0px solid #c8c8c8; display:none;">
	     	
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
	       					<option value="3" selected="selected">SOP资料</option>
	       					
	       				</select>
	       			</td>
	       		</tr>
	       		
	       </table>
	      </div>
	      
       	
 	</div>
       
    
    
 
     
</body>
</html>




