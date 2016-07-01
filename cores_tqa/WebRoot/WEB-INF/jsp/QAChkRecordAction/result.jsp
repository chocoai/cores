<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="style/images/icon.png" rel="shortcut icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查结果</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js"></script>
<script type="text/javascript">
	
	
	function delRecord(chkRecordId)
	{
		$.ajax({
			url:sybp()+'/qAChkRecordAction_del.action?chkRecordId='+chkRecordId,
			type:'post',
			dataType:'json',
			success:function(){
			
			}
		});
		 var delRow = $('#qaChkResultDatagrid').datagrid('getSelected');
		 var index =  $('#qaChkResultDatagrid').datagrid('getRowIndex',delRow);
		 $('#qaChkResultDatagrid').datagrid('deleteRow',index);

	}
	
	function startCheck(chkPlanId)
	{
		var parentTabs = parent.document.getElementById("researchCheckTabs");
		
		var tab2=$(parentTabs).tabs('getTab',2);
		//alert(tab2.attr('title'));
		
		var pp = $(parentTabs).tabs('getSelected');
		var index = tabpane.tabs('getTabIndex',pp)
		
	}
	
		function searchChkRecordList()
		{
			var start1 = $('#realChkStartDate').datebox('getValue');
		    var end1 = $('#realChkEndDate').datebox('getValue');
			if(start1==null||start1=='')
			{
		    	start1 = $('#startChkDateForResult').val();
			}
			if(end1==null||end1=='')
			{
          		end1 = $('#endChkDateForResult').val();
			}
			var item = $('#chkProject').combobox('getValue');
			var status = $('#chkIndexStatus').combobox('getValue');
			
			$.ajax({
				url:sybp()+'/qAChkRecordAction_getRecordResultList.action?studyNoParam='+$('#studyNoParamForResult').val()+'&startChkDate='+start1+'&endChkDate='+end1+'&chkIndexStatus='+status,
				type:'post',
				dataType:'json',
				data:{
					qaIndexId:item,
				},
				success:function(r){
					$('#qaChkResultDatagrid').datagrid('loadData',r);
				}
			});
			
		}
		
	
      
</script>

<script type="text/javascript">
    	$(function(){
        	//在onload方法中获取，在外面可能会有document.body为空的现象
    		var tableHeight = document.body.clientHeight-30;
    		var tableWidth  = document.body.clientWidth-2;

    		//设置宽度
   		 	if(parent.parent.$('#changeSizeButton')&&parent.parent.$('#changeSizeButton').text()==">")
   			{
   			 tableWidth = tableWidth+290;
   	   	 	}
   	   	 	
			$('#qaChkResultDatagrid').datagrid({
        		height:tableHeight,
				//width:tableWidth,
				singleSelect:true,
				nowrap:false,
				columns:[[
						{
							title:'Id',
							field:'chkRecordId',
							hidden:true,
						},
						{
							title:'isSign',
							field:'isSign',
							hidden:true,
						},
						{
							title:'检查项目',
							field:'chkItemName',
							width:120,
							halign:'center',
							align:'left',
							
						},
						{
							title:'检查用表格',
							field:'chkTblName',
							width:130,
							halign:'center',
							align:'left',
							
						},
						{
							title:'检查项Id',
							field:'chkItemId',
							hidden:true,
						},
						{
							title:'计划检查日期',
							field:'planChkTime',
							width:80,
							halign:'center',
							align:'left',
							
						},
						{
							title:'实际检查时间',
							field:'chkTime',
							width:80,
							halign:'center',
							align:'center',
						},
						
						
						{
							title:'检查内容',
							field:'chkContent',
							width:200,
							halign:'center',
							align:'left',
						},
						{
							title:'chkResultDesc',
							field:'chkResultDesc',
							hidden:true,
						},
						{
							title:'检查发现',
							field:'chkResultFlag',
							width:120,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
									if(value==1)
									{
										//return "符合";
										return "√";
									}
									if(value==-1)
									{
										//return "不符合";
										//return "×";
										return row.chkResultDesc;
									}
									if(value==0)
									{
										//return "不适用";
										return "NA";
									}
		         		
								}
						},
						{
							title:'建议',
							field:'advice',
							width:150,
							halign:'center',
							align:'center',
						},
						
						{
							title:'检查员',
							field:'operator',
							width:80,
							halign:'center',
							align:'center',
						},
						{
							title:'操作',
							field:'qa',
							width:70,
							halign:'center',
							align:'center',
							formatter: function(value,row,index){
								if(row.isSign=='true'||row.isSign==true)
								{
									return "删除";
								}else{
									return "<a href='javascript:delRecord("+row.chkRecordId+");'>删除</a>";
								}
							}
						},
						

				]],
				onLoadSuccess:function()
				{
					initButtonStatue();
				}
				
        	});
			
        	
        
				
				
	            if($('#studyNoParamForResult').val()!=null&&$('#studyNoParamForResult').val()!='')
	            {   
			       
			        $('#realChkStartDate').datebox('setValue',$('#startChkDateForResult').val());
		            $('#realChkEndDate').datebox('setValue',$('#endChkDateForResult').val());
		            var start =  $('#realChkStartDate').datebox('getValue');
		            var end= $('#realChkEndDate').datebox('getValue');
					
		            $('#chkProject').combobox({
		            	url:sybp()+'/qAChkRecordAction_getItemsByDateInResultPage.action?studyNoParam='+$('#studyNoParamForResult').val()+'&startChkDate='+start+'&endChkDate='+end,
		        		valueField:'id',
		        		textField:'text',
		        		
			        });
	            	$('#qaChkResultDatagrid').datagrid({
	            			url:sybp()+'/qAChkRecordAction_getRecordResultList.action?studyNoParam='+$('#studyNoParamForResult').val()+'&startChkDate='+start+'&endChkDate='+end,
	            	});
	            	
	            }
	            $('#realChkStartDate').datebox({
	        		onSelect: function(date){
	            	 	var start =  $('#realChkStartDate').datebox('getValue');
			            var end= $('#realChkEndDate').datebox('getValue');
			            if(start==null||start==''){
					    	start = $('#startChkDateForResult').val();
						}
						if(end==null||end==''){
			          		end = $('#endChkDateForResult').val();
						}
	            	 	$('#chkProject').combobox({
			            	url:sybp()+'/qAChkRecordAction_getItemsByDateInResultPage.action?studyNoParam='+$('#studyNoParamForResult').val()+'&startChkDate='+start+'&endChkDate='+end,
				        });
				        
	            		searchChkRecordList();
	        		}
	        	});
	            $('#realChkEndDate').datebox({
	        		onSelect: function(date){
		            	var start =  $('#realChkStartDate').datebox('getValue');
			            var end= $('#realChkEndDate').datebox('getValue');
			            if(start==null||start=='')
						{
					    	start = $('#startChkDateForResult').val();
						}
						if(end==null||end=='')
						{
			          		end = $('#endChkDateForResult').val();
						}
	            	 	$('#chkProject').combobox({
			            	url:sybp()+'/qAChkRecordAction_getItemsByDateInResultPage.action?studyNoParam='+$('#studyNoParamForResult').val()+'&startChkDate='+start+'&endChkDate='+end,
				        });
	            		
	            		searchChkRecordList();
	        		}
	        	});
	            $('#chkProject').combobox({
	            	 onSelect: function(rec){
	            		searchChkRecordList();
	            	 }
		        });
	            $('#chkIndexStatus').combobox({
	            	 onSelect: function(rec){
	            		searchChkRecordList();
	            	 }
		        });
	            
	            
        });//匿名函数结束
      
      function signChkRecordInResultPage(){
	      	var comboData = $('#chkProject').combobox('getData');
	      	var chkIndexs = new Array();
			for(var i=0;i<comboData.length;i++){
				chkIndexs.push(comboData[i].id);
			}	      	
	      	
	      	$.ajax({
	    			url:sybp()+'/qAChkIndexAction_noSignChkIndexStatus.action?chkIndexs='+chkIndexs,
	    			type:'post',
	    			dataType:'json',
	    			success:function(r){
	    				if(r&&r.length>0)
	    				{
	    					document.getElementById("chooseSignChkIndexListDialog2").display="block";
	    					
	    					$('#chooseChkIndexList').datagrid('loadData',r);
			    	
  					 		$('#chooseSignChkIndexListDialog').dialog('open');
	    					
	    				}else if(r&&!r.success)
	    				{
	    					$.messager.alert("提示",r.msg);
	    				}
	    			}
	    	});
      	
      }
      function chooseSignChkIndexList()
      {
      	  var rows = $('#chooseChkIndexList').datagrid('getChecked');
      	  if(rows==null||rows==''){
      	  	$.messager.alert('提示框','请选择一个要签字的检查项！');
      	  }else{
      	  	qm_showQianmingDialog('afterSignQAChkIndex');
      	  }
      }
      //单个的签字 现无效
      	function afterConfirmSignChkRecordInResultPage()
        {
      		//$.inArray(value, array)  
      		var item = $('#chkProject').combobox('getValue');
      		if(item==null||item=='')
      		{
          		//没有提交的全部提交
          		$.messager.alert("提示","请选择一个项目提交");
          	}else{
          		$('#chkIndexId').val(item);
          		$.ajax({
    				url:sybp()+'/qAChkIndexAction_isFinish.action?chkIndexId='+$('#chkIndexId').val(),
    				type:'post',
    				dataType:'json',
    				success:function(r){
    					if(r&&r.success&&r.finish)
    					{
    						qm_showQianmingDialog('afterSignQAChkIndex');
    					}else if(r&&!r.success)
    					{
    						$.messager.alert("提示",r.msg);
    					}
    				}
    			});
          	}
      		
        }
      //签字过后的检查索引完成操作
     	function afterSignQAChkIndex()
     	{
     		$('#chooseSignChkIndexListDialog').dialog('close');
     	 	var rows = $('#chooseChkIndexList').datagrid('getChecked');
     	 	
     	 	var chkIndexs = new Array();
     	 	for(var i=0;i<rows.length;i++){
     	 		chkIndexs.push(rows[i].chkIndexId);
     	 	}
     	 	
     	 	//alert(sybp()+'/qAChkIndexAction_sign.action?chkIndexId='+$('#chkIndexId').val());
     		$.ajax({
    			url:sybp()+'/qAChkIndexAction_signMany.action?chkIndexs='+chkIndexs,
    			type:'post',
    			dataType:'json',
    			success:function(r){
     				
     				$('#qaChkResultDatagrid').datagrid('reload');
     				initButtonStatue();
    			},
    		});
     		
     	}
     	
        function writeReportInResultPage()
        {
	        $.ajax({
				url:sybp()+'/qAChkPlanAction_putChkPlanIdInSession.action?newReport=1',
				type:'post',
				dataType:'json',
				success:function(r){
			 		parent.$('#researchCheckTabs').tabs('select','检查报告');
					
				}
			});
        	
        }
        
        function initButtonStatue()
        {
            $('#signQAChkRecordInResultPage').linkbutton('disable');
            
            $('#writeQAChkReportInResultPage').linkbutton('enable');
            var rows = $('#qaChkResultDatagrid').datagrid('getRows');
            if(rows!=null&&rows!=''&&rows.length>0)
            {
                for(var i=0;i<rows.length;i++)
                {
	                if(rows[i].isSign!=true&&rows[i].isSign!='true')
	                {
	                	 $('#signQAChkRecordInResultPage').linkbutton('enable');
	                	 break;
	                }
                }
            }
        }
</script>
	  
</head>

<body >
	<s:hidden id="studyNoParamForResult" name="studyNoParam"></s:hidden>
	<s:hidden id="startChkDateForResult" name="startChkDateForResult"></s:hidden>
	<s:hidden id="endChkDateForResult"   name="endChkDateForResult"></s:hidden>
	<s:hidden id="chkIndexId" ></s:hidden>
	
	<div class="container">
		<div >
		
		<nobr>
            实际检查日期<div id="realChkStartDate" class="easyui-datebox" style="width: 100px;"></div>
            ~<div id="realChkEndDate" class="easyui-datebox" style="width: 100px;"></div>
           检查项目    <select id="chkProject" data-options="editable:false"  class="easyui-combobox" style="width:200px;">
			
		</select>        
            状态    <select id="chkIndexStatus" data-options="editable:false" class="easyui-combobox"  style="width:100px;">
			<option value="0">全部</option>
			<option value="1">未签字</option>
			<option value="2">已签字</option>
		</select>
		<a id="signQAChkRecordInResultPage" class="easyui-linkbutton" onclick="signChkRecordInResultPage();" data-options="iconCls:'icon-edit',plain:true">签字确认</a>
		<a id="writeQAChkReportInResultPage" class="easyui-linkbutton" onclick="writeReportInResultPage();" data-options="iconCls:'icon-edit',plain:true">填写检查报告</a>
		</nobr>
		</div>
    
       <div style="display:block; position:absolute;top:30px;left:0px;right:0px; bottom:0px;background:#fff;padding:0px 0px 0px 0px;overflow:auto;" >
 	 	 
 	 	 <div id="qaChkResultDatagrid" class="easyui-datagrid" style="border: 1px solid #c8c8c8;" class="easyui-tabs" border="false" >
 	 
		 </div>
		
 	  </div>
       
	</div>
   <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	<%@ include file="/WEB-INF/jsp/QAChkRecordAction/chooseSignChkIndexList.jspf"%>
</body>
</html>




