<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.lanen.util.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>解剖详情</title>
   <%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
 	
	<script type="text/javascript">
		var studyNoPara;
		var reqNo;
		 $(function(){
			var height=document.body.clientHeight-125;
			var width=document.body.clientWidth-20;
			studyNoPara = $('#studyNoPara').val();
			reqNo = $('#reqNo').val();
			$('#visceraWeightCompareTable').datagrid({
				height:height,
				//fit:false,
				width:width,
				fitColumns:false,
				singleSelect:true,//只能选一行
				resizable:true,
			});
			
			
			$('#visceraFixedCompareTable').datagrid({
				height:height-50,
				width:width,
				singleSelect:true,//只能选一行
				
			});
			$('#visceraWeightCompareTable').datagrid("freezeRow",1);
			
			$('#visceraFixedCompareTable').datagrid("freezeRow",1);
			$('#visceraFixedCompareTable').datagrid("freezeRow",2);
			
			$('#anatomyTable').datagrid("freezeRow",1);
			
			$('#anatomyTable').datagrid({
				url : sybp()+'/tblAnatomyReqAction_getAnatomyCheckData.action?studyNoPara='+encodeURIComponent(studyNoPara)+"&reqNo="+encodeURIComponent(reqNo),
				//title:'解剖所见',
				height: height,
				width:width,
				nowarp:  false,//单元格里自动换行
				singleSelect:true,
				border:false,
				columns:[[
				         //  {field:'id',title:'id',width:75},
				           {field:'animalCode',title:'动物编号',width:75},
				           {field:'visceraName',title:'脏器',width:119},
				           {field:'anatomyFingding',title:'解剖所见',width:217},
				           {field:'operator',title:'操作者',width:76},
				           {field:'operateTime',title:'记录时间',width:128},
				      ]],

			});
			$('#visceraWeightTable').datagrid({
				url :  sybp()+'/tblAnatomyReqAction_getVisceraWeightData.action?studyNoPara='+encodeURIComponent(studyNoPara)+"&reqNo="+encodeURIComponent(reqNo),
				//title:'脏器称重',
				//height: 450,
				singleSelect:true,//只能选一行
				columns:[
							[
							//{field:'id',title:"id",width:75},
							//{field:'sessionId',title:"sessionId",width:75},
							{field:'animalCode',title:"动物编号",width:68},
							{field:'visceraName',title:"脏器",width:113},
							{field:'weight',title:"重量",width:61},
							{field:'attachedVisceraNames',title:"附加脏器",width:107},
							{field:'fixedWeightFlag',title:"固定后称重",width:75,
								formatter: function(value,row,index){
									if ( value == 0 ){
										return "否";
									}else if( value == 1 ){
										return "是";
									}
							    }
							},
							{field:'balCode',title:"天平编号",width:78},
							{field:'operator',title:"操作者",width:61},
							{field:'operateTime',title:"记录时间",width:120},
				
							]
						],
				
			});
			//脏器固定详细信息
			$('#visceraFixedTable').datagrid({
				url : sybp()+'/tblAnatomyReqAction_getVisceraFixedData.action?studyNoPara='+encodeURIComponent(studyNoPara)+"&reqNo="+encodeURIComponent(reqNo),
				//title:'脏器固定',
				//height: 400,
				singleSelect:true,//只能选一行
				columns:[
							[
							//{field:'id',title:'id',width:72},
							{field:'animalCode',title:'动物编号',width:108},
							{field:'visceraName',title:'脏器名称',width:184},
							{field:'operator',title:'操作者',width:70},
							{field:'operateTime',title:'操作时间',width:178},
							]
						],
			});
			//初始化剖检脏器下拉列表
			$('#visceraName').combobox({
  				 url:sybp()+'/tblAnatomyReqAction_getAnatomyCheckVisceraData.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+encodeURIComponent(reqNo),
    			 valueField:'visceraName',
   				 textField:'visceraName',
   				 onSelect: function(rec){
	           	 	var visceraName = $('#visceraName').combobox('getText');
	           	    var finding = $('#fingdings').combobox('getText');
		           	  $.ajax({
						url:sybp()+'/tblAnatomyReqAction_getAnatomyCheckData.action',
						type: 'post',
						data: {
							studyNoPara:studyNoPara,
							reqId:$('#reqId').val(),
							reqNo:reqNo,
							visceraName:visceraName,
							finding:finding
						},
						dataType:'json',
						success:function(r){
							if(r){
								$('#anatomyTable').datagrid('loadData',r);
							}
						}
		            });
				}
	        });
			$('#visceraName').combobox("select","全部");
			//初始化剖检病变下拉列表
			$('#fingdings').combobox({
  				 url:sybp()+'/tblAnatomyReqAction_getAnatomyCheckFindingData.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+encodeURIComponent(reqNo),
   				 valueField:'anatomyFingding',
   				 textField:'anatomyFingding',
   				 onSelect: function(rec){
				    var visceraName = $('#visceraName').combobox('getText');
           	 		var finding = $('#fingdings').combobox('getText');
   					$.ajax({
						url:sybp()+'/tblAnatomyReqAction_getAnatomyCheckData.action',
						type: 'post',
						data: {
							studyNoPara:studyNoPara,
							reqId:$('#reqId').val(),
							reqNo:reqNo,
							visceraName:visceraName,
							finding:finding
						},
						dataType:'json',
						success:function(r){
							if(r){
								$('#anatomyTable').datagrid('loadData',r);
							}
						}
		            });
				}
			});
			$('#fingdings').combobox("select","全部");
			//初始化脏器称重的脏器下拉表
			$('#weightVisceraName').combobox({
  				 url:sybp()+'/tblAnatomyReqAction_getVisceraWeightVisceraData.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+encodeURIComponent(reqNo),
    			 valueField:'visceraName',
   				 textField:'visceraName',
   				 onSelect: function(rec){
	           	 	var visceraName = $('#weightVisceraName').combobox('getText');
					//var title = $('#visceraWeightTabPane').tabs("getSelected").panel('options').title;
					 //	if(title=='脏器重量')
					//{
						//$('#weightOutExcel').linkbutton('enable');
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraWeightCompareData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
								visceraName:visceraName
							},
							dataType:'json',
							success:function(r){
								if(r && r.success){
									$('#visceraWeightCompareTable').datagrid({
										columns:r.columns
									});
									$('#visceraWeightCompareTable').datagrid('loadData',r.rows);
								}else{
									$('#visceraWeightCompareTable').datagrid({
										columns:[]
									});
									$('#visceraWeightCompareTable').datagrid('loadData',{total:0,rows:[]});
								}
							}
			            });
						
					/*}
					else if(title=='详细信息'){
						$('#weightOutExcel').linkbutton('disable');
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraWeightData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
								visceraName:visceraName
							},
							dataType:'json',
							success:function(r){
								if(r){
									//alert(r);
									$('#visceraWeightTable').datagrid('loadData',r);
								}
							}
			            });
					}*/
		           	  
				}
	        });
			$('#weightVisceraName').combobox("select","全部");
	        //初始化脏器固定的脏器列表
	        $('#fixedVisceraName').combobox({
  				 url:sybp()+'/tblAnatomyReqAction_getVisceraFixedVisceraData.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&reqNo='+encodeURIComponent(reqNo),
    			 valueField:'visceraName',
   				 textField:'visceraName',
   				 onSelect: function(rec){
	           	 	var visceraName = $('#fixedVisceraName').combobox('getText');
					//var title = $('#visceraFixedTabPane').tabs("getSelected").panel('options').title;
					//if(title=='脏器固定')
					//{
						//$('#fixedOutExcel').linkbutton('enable');
						
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraFixedCompareData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
								visceraName:visceraName
							},
							dataType:'json',
							success:function(r){
								if(r){
									if(r && r.success){
										$('#visceraFixedCompareTable').datagrid({
											columns:r.columns
										});
										$('#visceraFixedCompareTable').datagrid('loadData',r.rows);
										$('#remark').innerHTML=r.remark;
									}else{
										$('#visceraFixedCompareTable').datagrid({
											columns:[]
										});
										$('#visceraFixedCompareTable').datagrid('loadData',{total:0,rows:[]});
										$('#remark').innerHTML='备注：';
									}
									
								}
							}
			            });
					/*}
					else if(title=='详细信息')
					{
						$('#fixedOutExcel').linkbutton('disable');
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraFixedData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
								visceraName:visceraName
							},
							dataType:'json',
							success:function(r){
								if(r){
									$('#visceraFixedTable').datagrid('loadData',r);
								}
							}
			            });
					}*/
					
			     }

	        });
	        $('#fixedVisceraName').combobox("select","全部");
			//整个大的tabs的设置
			$('#anatomyDetailTabPane').tabs({
					onSelect:function(title,index){
			        	//更新数据
			        	if(title=='解剖所见')
			        	{
			        		$('#outExcel').linkbutton('disable');
			        		 $.ajax({
									url:sybp()+'/tblAnatomyReqAction_getAnatomyCheckData.action',
									type: 'post',
									data: {
										studyNoPara:studyNoPara,
										reqId:$('#reqId').val(),
										reqNo:reqNo
									},
									dataType:'json',
									success:function(r){
										if(r){
											$('#anatomyTable').datagrid('loadData',r);
										}
									}
					            });
			        	
			    		}
			    		else if(title=='脏器称重')
			        	{
			    			//alert(title);
			    			//$('#weightOutExcel').linkbutton('enable');
			    			$('#outExcel').linkbutton('enable');
			    			var visceraName = $('#weightVisceraName').combobox('getText');
			    			$.ajax({
			    				url:sybp()+'/tblAnatomyReqAction_getVisceraWeightCompareData.action',
			    				type: 'post',
			    				data: {
			    					studyNoPara:$('#studyNoPara').val(),
			    					reqId:$('#reqId').val(),
			    					reqNo:reqNo,
			    					visceraName:visceraName
			    				},
			    				dataType:'json',
			    				success:function(r){
			    					if(r && r.success){
			    						$('#visceraWeightCompareTable').datagrid({
			    							columns:r.columns
			    						});
			    						$('#visceraWeightCompareTable').datagrid('loadData',r.rows);
			    					}else{
			    						$('#visceraWeightCompareTable').datagrid({
			    							columns:[]
			    						});
			    						$('#visceraWeightCompareTable').datagrid('loadData',{total:0,rows:[]});
			    					}
			    				}
			                });
			    			
			    		}
			    		else if(title=='脏器固定')
			        	{
			    			//alert(title);
			    			//$('#fixedOutExcel').linkbutton('enable');
			    			$('#outExcel').linkbutton('enable');
			    			var visceraName = $('#fixedVisceraName').combobox('getText');
							$.ajax({
								url:sybp()+'/tblAnatomyReqAction_getVisceraFixedCompareData.action',
								type: 'post',
								data: {
									studyNoPara:$('#studyNoPara').val(),
									reqId:$('#reqId').val(),
									reqNo:reqNo,
									visceraName:visceraName
								},
								dataType:'json',
								success:function(r){
									if(r){
										if(r && r.success){
											$('#visceraFixedCompareTable').datagrid({
												columns:r.columns
											});
											$('#visceraFixedCompareTable').datagrid('loadData',r.rows);
											$('#remark').html(r.remark);
										}else{
											$('#visceraFixedCompareTable').datagrid({
												columns:[]
											});
											$('#visceraFixedCompareTable').datagrid('loadData',{total:0,rows:[]});
											$('#remark').html('备注');
										}
										
										
									}
								}
				            });
			    		}
			    		
					}
			});
			
			//脏器重量
			//$('#weightOutExcel').linkbutton('enable');
			$.ajax({
				url:sybp()+'/tblAnatomyReqAction_getVisceraWeightCompareData.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					reqId:$('#reqId').val(),
					reqNo:reqNo,
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						$('#visceraWeightCompareTable').datagrid({
							columns:r.columns
						});
						$('#visceraWeightCompareTable').datagrid('loadData',r.rows);
					}else{
						$('#visceraWeightCompareTable').datagrid({
							columns:[]
						});
						$('#visceraWeightCompareTable').datagrid('loadData',{total:0,rows:[]});
					}
				}
            });
            /*
			//脏器称重的tabs
			$('#visceraWeightTabPane').tabs({
				onSelect:function(title,index){
					if(title=='脏器重量')
					{
						$('#weightOutExcel').linkbutton('enable');
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraWeightCompareData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
							},
							dataType:'json',
							success:function(r){
								if(r && r.success){
									$('#visceraWeightCompareTable').datagrid({
										columns:r.columns
									});
									$('#visceraWeightCompareTable').datagrid('loadData',r.rows);
								}else{
									$('#visceraWeightCompareTable').datagrid({
										columns:[]
									});
									$('#visceraWeightCompareTable').datagrid('loadData',{total:0,rows:[]});
								}
							}
			            });
						
					}
					else if(title=='详细信息'){
						$('#weightOutExcel').linkbutton('disable');
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraWeightData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
							},
							dataType:'json',
							success:function(r){
								if(r){
									//alert(r);
									$('#visceraWeightTable').datagrid('loadData',r);
								}
							}
			            });
					}
				}
				
		    });*/
		    
			
			//脏器固定
			//$('#fixedOutExcel').linkbutton('enable');
						
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraFixedCompareData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
							},
							dataType:'json',
							success:function(r){
								if(r){
									if(r && r.success){
										$('#visceraFixedCompareTable').datagrid({
											columns:r.columns
										});
										$('#visceraFixedCompareTable').datagrid('loadData',r.rows);
										$('#remark').html(r.remark);
									}else{
										$('#visceraFixedCompareTable').datagrid({
											columns:[]
										});
										$('#visceraFixedCompareTable').datagrid('loadData',{total:0,rows:[]});
										$('#remark').html('备注');
									}
									
									
								}
							}
			            });
			/*
			//脏器固定tabpane
			$('#visceraFixedTabPane').tabs({
				onSelect:function(title,index){
					if(title=='脏器固定')
					{
						$('#fixedOutExcel').linkbutton('enable');
						
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraFixedCompareData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo,
							},
							dataType:'json',
							success:function(r){
								if(r){
									if(r && r.success){
										$('#visceraFixedCompareTable').datagrid({
											columns:r.columns
										});
										$('#visceraFixedCompareTable').datagrid('loadData',r.rows);
										$('#remark').html(r.remark);
									}else{
										$('#visceraFixedCompareTable').datagrid({
											columns:[]
										});
										$('#visceraFixedCompareTable').datagrid('loadData',{total:0,rows:[]});
										$('#remark').html('备注');
									}
									
									
								}
							}
			            });
					}
					else if(title=='详细信息')
					{
						$('#fixedOutExcel').linkbutton('disable');
						$.ajax({
							url:sybp()+'/tblAnatomyReqAction_getVisceraFixedData.action',
							type: 'post',
							data: {
								studyNoPara:$('#studyNoPara').val(),
								reqId:$('#reqId').val(),
								reqNo:reqNo
							},
							dataType:'json',
							success:function(r){
								if(r){
									$('#visceraFixedTable').datagrid('loadData',r);
								}
							}
			            });
					}
				}

		    });
			*/
			
		 });
		 /**返回按钮事件*/
		function onBackButton(){
			var studyNoPara = $('#studyNoPara').val();
			window.location.href=sybp()+'/tblAnatomyReqAction_list.action?studyNoPara='+encodeURIComponent(studyNoPara);
		}
		function onOutExcelButton()
		{
			var title = $('#anatomyDetailTabPane').tabs("getSelected").panel('options').title;  
			var visceraName = $('#weightVisceraName').combobox('getText');
			var length = 0;
			var methodName = 'weightOutExcel';
			var remark="";
			if(title=='脏器称重')
			{
				length = $('#visceraWeightCompareTable').datagrid("getRows").length;
				
				
				methodName = "weightOutExcel";
				visceraName = $('#weightVisceraName').combobox('getText');
			}
			if(title=='脏器固定')
			{
				remark=$('#remark').val();
				length = $('#visceraFixedCompareTable').datagrid("getRows").length;
				methodName="fixedOutExcel";
				visceraName = $('#fixedVisceraName').combobox('getText');
			}
			if(length>0)
				window.location.href = sybp()+'/tblAnatomyReqAction_'+methodName+'.action?studyNoPara='+$('#studyNoPara').val()+'&reqId='+$('#reqId').val()+'&reqNo='+reqNo+"&visceraName="+visceraName+'&remark='+remark;
			else
				$.messager.alert("提示","没有数据！");	
		}
		//称重转出为excel
		/*function onWeightOutExcelButton()
	    {
			window.location.href = sybp()+"/tblAnatomyReqAction_weightOutExcel.action?studyNoPara="+$('#studyNoPara').val()+"&reqId="+$('#reqId').val()+"&reqNo="+reqNo;
		    $.ajax({
				url:sybp()+'/tblAnatomyReqAction_weightOutExcel.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					//parentComponent:parent.serialize(),
					reqId:$('#reqId').val(),
					reqNo:reqNo
				},
				dataType:'json',
				success:function(r){
					
					$.messager.alert("","称重转出成功！");	
				},
				error:function(r){
					//$.messager.alert("失败","ajax失败l");
				}
            });     
	    }
		//固定转出为excel
		function onFixedOutExcelButton()
	    {
			window.location.href = sybp()+'/tblAnatomyReqAction_fixedOutExcel.action?studyNoPara='+$('#studyNoPara').val()+"&reqId="+$('#reqId').val()+"&reqNo"+reqNo;
		    $.ajax({
				url:sybp()+'/tblAnatomyReqAction_fixedOutExcel.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					//parentComponent:parent.serialize(),
					reqId:$('#reqId').val(),
					reqNo:reqNo
				},
				dataType:'json',
				success:function(r){
					$.messager.alert("","固定转出成功！");	
				},
				error:function(r){
					//$.messager.alert("失败","固定ajax失败l");
				}
            });   
	    }*/  
			
	</script>

  </head>
  
  <body scroll="no">
     
    
    <s:hidden id="pathdirector" name="pathdirector"></s:hidden>
    <s:hidden id="createTimeLabel" name="createTime" ></s:hidden>
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <s:hidden id="reqId" name="reqId"></s:hidden>
    <s:hidden id="reqNo" name="reqNo"></s:hidden>
	<!-- 
	  <div region="center" title="" style="overflow: hidden;">
	 -->
	 <!-- 基本信息 -->
	 	<div style="padding:5px 5px;">
	 	 <!-- 课题编号 -->
	 	 <a class="easyui-linkbutton" onclick="onBackButton();" data-options="iconCls:'icon-back',plain:true">返回</a>
	 	  <a class="easyui-linkbutton" id="outExcel" onclick="onOutExcelButton();" data-options="iconCls:'icon-save',plain:true">导出excel</a>
				
	 	 	<!-- 
	 	 	 课题编号
    		<s:label style="padding:20px;" id="studyNoParaLabel" name="studyNoPara"></s:label>
	 	 	 -->
	 		申请日期
	 		
	 		<%=request.getParameter("createTime") %>
	 		<!-- 
	 		<s:label id="createTimeLabel" name="createTime" format="yyyy-MM-dd" />
	 		 -->
	 		&nbsp;&nbsp;&nbsp;
	 		计划解剖日期
	 		<%=request.getParameter("beginDate") %>
	 		<!-- 
	 		<s:label style="padding:20px;" id="beginDateLabel" name="beginDate"></s:label>
	 		 -->
	 	</div>
	 
	 	<div id="anatomyDetailTabPane" class="easyui-tabs" fit="true" border="1"
	 	style=" position:absolute;top:38px;bottom:12px; border: 1px solid #c8c8c8; overflow-y: scroll;">
	 		<div title="解剖所见" style="padding:5px 5px;">
					&nbsp;
					<label>脏器</label>
					<input id="visceraName" class="easyui-combo"/>
					&nbsp;&nbsp;<label>病变</label>
					<input id="fingdings" class="easyui-combo"/>
					
        		 <div>
					<table style="overflow: scroll;" id="anatomyTable" class="easyui-datagrid" border="1"></table>
					<div></div>
				</div>
				
    		</div>
    		<div title="脏器称重" style="padding:5px 5px;">
	 	
    		  &nbsp;
    			<label>脏器</label>
				<input id="weightVisceraName" class="easyui-combo"/>
				<!-- 
				&nbsp;&nbsp;
				 <a class="easyui-linkbutton" id="weightOutExcel" onclick="onWeightOutExcelButton();" data-options="iconCls:'icon-save',plain:true">导出excel</a>
				 -->
	 	 		
				<!-- 
        		 <div class="easyui-tabs" id="visceraWeightTabPane">
				 -->
        		 	<div title="脏器重量">
        		 		<table style="overflow: scroll;" id="visceraWeightCompareTable" class="easyui-datagrid" border="1"></table>
        		 		<div></div>
        		 	</div>
        		 	<!-- 
        		 	<div title="详细信息" >
						<table id="visceraWeightTable" class="easyui-datagrid" border="1"></table>
					</div>
        		 	 -->
					<!-- 
				</div>
					 -->
    		</div>
    		<div title="脏器固定" style="padding:5px 5px;">
	 	
    		 	&nbsp;
        		<label>脏器</label>
				<input id="fixedVisceraName" class="easyui-combo"/>
				<!-- 
				&nbsp;&nbsp;
				 <a class="easyui-linkbutton" id="fixedOutExcel" onclick="onFixedOutExcelButton();" data-options="iconCls:'icon-save',plain:true">导出excel</a>
				 -->
        		<!-- 
        		 <div class="easyui-tabs" id="visceraFixedTabPane">
        		 -->
        		 	<div title="脏器固定">
        		 		<table style="overflow: scroll;" id="visceraFixedCompareTable" class="easyui-datagrid"></table>
        		 		
        		 		<div>
	        		 		<textarea id="remark" disabled="disabled" style=" height:50px;font-size:13px;width:99%;"></textarea>
        		 		</div>
        		 	</div>
        		 	<!-- 
        		 	<div title="详细信息" >
						<table id="visceraFixedTable" class="easyui-datagrid" border="1"></table>
					</div>
        		 	 -->
				</div>
				<!-- 
    		</div>
				 -->
    		  
	 	</div>
	 	

  </body>
</html>
