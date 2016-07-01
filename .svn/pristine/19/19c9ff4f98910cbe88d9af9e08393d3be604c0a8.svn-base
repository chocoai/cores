<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>临检数据</title>
    <%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
    <script type="text/javascript" charset="utf-8">

       dataScreeningTable('','','','');
    	var bioChemTable;
    	var hematTable;
    	var bloodCoagTable;
    	var urineTable;
    	var DataScreeningTable;
    	$(function(){
        	//血生化datagrid
    		bioChemTable=$('#bioChemTable').datagrid({
				height:430,
				width:1155,
				fit:false,
				fitColumns:false,
				resizable:true,
				onClickRow :function(rowIndex, rowData){
    			$('#hematTable').datagrid('unselectAll');
	     		$('#bloodCoagTable').datagrid('unselectAll');
	     		$('#urineTable').datagrid('unselectAll');
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
			    }
            });
        	//血常规datagrid
    		hematTable=$('#hematTable').datagrid({
    			height:430,
    			width:1155,
				fit:false,
				fitColumns:false,
				resizable:true,
				onClickRow :function(rowIndex, rowData){
    			$('#bioChemTable').datagrid('unselectAll');
	     		$('#bloodCoagTable').datagrid('unselectAll');
	     		$('#urineTable').datagrid('unselectAll');
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
			    } 
            });
        	//血凝datagrid
    		bloodCoagTable=$('#bloodCoagTable').datagrid({
    			height:430,
    			width:1155,
				fit:false,
				fitColumns:false,
				resizable:true,
				onClickRow :function(rowIndex, rowData){
    			$('#hematTable').datagrid('unselectAll');
	     		$('#bioChemTable').datagrid('unselectAll');
	     		$('#urineTable').datagrid('unselectAll');
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
			    }
            });
        	//尿常规datagrid
    		urineTable=$('#urineTable').datagrid({
    			height:430,
				width:1155,
				fit:false,
				fitColumns:false,
				resizable:true,
				onClickRow :function(rowIndex, rowData){
    			$('#hematTable').datagrid('unselectAll');
	     		$('#bloodCoagTable').datagrid('unselectAll');
	     		$('#bioChemTable').datagrid('unselectAll');
				 $(this).datagrid('unselectAll');
				 $(this).datagrid('selectRow',rowIndex);
			    }
            });
            
		            $.ajax({
						url : sybp()+'/tblClinicalTestDataAction_loadRowsAndColumns.action',
						type: 'post',
						data: {
							studyNoPara:$('#studyNoPara').val(),
							reqNoPara:$('#reqNoPara').val(),
							AdditionalApplications:$('#AdditionalApplications').val(),
							isValidationPara:$('#isValidationPara').val(),
							isValidationPara1:$('#isValidationPara1').val(),
							testItemPara: 1,
							combinedWithAnimal:$('#combinedWithAnimal').val()
						},
						dataType:'json',
						success:function(r){
							if(r){
								bioChemTable.datagrid({
									columns:r.columns
								});
								bioChemTable.datagrid('loadData',r.rows);
							}
						}
		            });
		            $.ajax({
						url : sybp()+'/tblClinicalTestDataAction_loadRowsAndColumns.action',
						type: 'post',
						data: {
							studyNoPara:$('#studyNoPara').val(),
							reqNoPara:$('#reqNoPara').val(),
							AdditionalApplications:$('#AdditionalApplications').val(),
							isValidationPara:$('#isValidationPara').val(),
							isValidationPara1:$('#isValidationPara1').val(),
							testItemPara: 2,
							combinedWithAnimal:$('#combinedWithAnimal').val()
						},
						dataType:'json',
						success:function(r){
							if(r){
								hematTable.datagrid({
									columns:r.columns
								});
								hematTable.datagrid('loadData',r.rows);
							}
						}
		            });
		            $.ajax({
						url : sybp()+'/tblClinicalTestDataAction_loadRowsAndColumns.action',
						type: 'post',
						data: {
							studyNoPara:$('#studyNoPara').val(),
							reqNoPara:$('#reqNoPara').val(),
							AdditionalApplications:$('#AdditionalApplications').val(),
							isValidationPara:$('#isValidationPara').val(),
							isValidationPara1:$('#isValidationPara1').val(),
							testItemPara: 3,
							combinedWithAnimal:$('#combinedWithAnimal').val()
						},
						dataType:'json',
						success:function(r){
							if(r){
								bloodCoagTable.datagrid({
									columns:r.columns
								});
								bloodCoagTable.datagrid('loadData',r.rows);
							}
						}
		            });
		            $.ajax({
						url : sybp()+'/tblClinicalTestDataAction_loadRowsAndColumns.action',
						type: 'post',
						data: {
							studyNoPara:$('#studyNoPara').val(),
							reqNoPara:$('#reqNoPara').val(),
							AdditionalApplications:$('#AdditionalApplications').val(),
							isValidationPara:$('#isValidationPara').val(),
							isValidationPara1:$('#isValidationPara1').val(),
							testItemPara: 4,
							combinedWithAnimal:$('#combinedWithAnimal').val()
						},
						dataType:'json',
						success:function(r){
							if(r){
								urineTable.datagrid({
									columns:r.columns
								});
								urineTable.datagrid('loadData',r.rows);
							}
						}
		            });

		            $('#tabs').tabs({onSelect:function(title,index){
		            	$('#hematTable').datagrid('unselectAll');
			     		$('#bloodCoagTable').datagrid('unselectAll');
			     		$('#urineTable').datagrid('unselectAll');
			     		$('#bioChemTable').datagrid('unselectAll');
			         }


			            });

		            setTab(); 

		            showCombinedWithAnimal();
		            $('#titleDiv').css('display','');
		            $('#DataScreeningDialogDIV').css('display','');
		           
		            
        });

    	/**
    	 * 是否只显示已选重数据
    	 * @return
    	 */
    	function onCheckbox(){
    		var tab = $('#tabs').tabs('getSelected');
    		var index = $('#tabs').tabs('getTabIndex',tab);
        	var AdditionalApplications = $('#AdditionalApplications').val();
    		var isValidationPara=$('#isValidationPara').val();
    		var isValidationPara1=$('#isValidationPara1').val();
    		var studyNoPara = encodeURIComponent($('#studyNoPara').val());
    		var reqNoPara = $('#reqNoPara').val();
    		var combinedWithAnimal=$('#combinedWithAnimal').val();
    		if(isValidationPara == 0){
    			isValidationPara=1;
    			isValidationPara1 = 1;
    			if( AdditionalApplications == "all"){
    				window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
    				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
            	}else{
            		window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
					+studyNoPara+'&reqNoPara='+reqNoPara+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
                }
    			
    			
    		}else{
    			isValidationPara=0;
    			if( AdditionalApplications == "all"){
    				window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
    				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
         	    }else{
         	    	 window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
 					 +studyNoPara+'&reqNoPara='+reqNoPara+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
                }
    			
    			
    			
    		}
    	}

    	/**
    	 * 是否只显示已确认数据
    	 * @return
    	 */
    	function onCheckbox1(){
    		var tab = $('#tabs').tabs('getSelected');
    		var index = $('#tabs').tabs('getTabIndex',tab);


        	var AdditionalApplications = $('#AdditionalApplications').val();
    		var isValidationPara=$('#isValidationPara').val();
    		var isValidationPara1=$('#isValidationPara1').val();
    		var studyNoPara = encodeURIComponent($('#studyNoPara').val());
    		var reqNoPara = $('#reqNoPara').val();
    		var combinedWithAnimal=$('#combinedWithAnimal').val();
    		if(isValidationPara1 == 0){
    			isValidationPara1 = 1;
    			if( AdditionalApplications == "all"){
    				window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
    				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="
    				+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
            	}else{
            		window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
					+studyNoPara+'&reqNoPara='+reqNoPara+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
                }
    			 
    		}else{
    			isValidationPara1 = 0;
    			if( AdditionalApplications == "all"){
    				window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
    				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
         	    }else{
         	    	 window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
 					 +studyNoPara+'&reqNoPara='+reqNoPara+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
                }
    			
    			
    		}
    	}


    	function setTab(){
    	var sTab = $('#setTab').val();
    	if (sTab == "0") {
    	$("#tabs").tabs("select", 0);
    	}
    	if (sTab == "1") {
    	$("#tabs").tabs("select", 1);
    	//$("#tabs-2").attr("data-options", "selected:true");
    	}
    	if (sTab == "2") {
    	$("#tabs").tabs("select", 2);
    	}
    	if (sTab == "3") {
    	$("#tabs").tabs("select", 3);
    	}
    	var isValidationPara=$('#isValidationPara').val();
    	    if(isValidationPara == 1){
    	    	document.getElementById("checkbox1").disabled=true;
    	    	document.getElementById("checkbox1a").disabled=true;
            }else{
            	document.getElementById("checkbox1").disabled=false;
            	document.getElementById("checkbox1a").disabled=false;
            }
    	
    	}
    	function TestDataScreeningButton(){
        	
    		var DataScreeningReqNo ;
    		var DataScreeningAnimalCode;
    		var DataScreeningAnimalId;
    		var testItem;
    		var tabl1 =$('#bioChemTable').datagrid('getSelected');
    		var tabl2 =$('#hematTable').datagrid('getSelected');
    		var tabl3 =$('#bloodCoagTable').datagrid('getSelected');
    		var tabl4 =$('#urineTable').datagrid('getSelected');
    		if( tabl1 != null || tabl2 != null || tabl3 != null || tabl4 != null){
    		if(tabl1 != null){
    			DataScreeningReqNo = tabl1.ReqNo;
        		DataScreeningAnimalCode = tabl1.animalCode;
        		DataScreeningAnimalId = tabl1.animalId;
        		testItem =1 ;
        		$('#DataScreeningReqNo').val(tabl1.ReqNo);
        		$('#DataScreeningAnimalCode').val(tabl1.animalCode);
        		$('#DataScreeningAnimalId').val(tabl1.animalId);
        		
            }else if(tabl2 != null){
            	DataScreeningReqNo = tabl2.ReqNo;
        		DataScreeningAnimalCode = tabl2.animalCode;
        		DataScreeningAnimalId = tabl2.animalId;
        		$('#DataScreeningReqNo').val(tabl2.ReqNo);
        		$('#DataScreeningAnimalCode').val(tabl2.animalCode);
        		$('#DataScreeningAnimalId').val(tabl2.animalId);
        		testItem =2 ;

            }else if(tabl3 != null){
        		DataScreeningReqNo = tabl3.ReqNo;
        		DataScreeningAnimalCode = tabl3.animalCode;
        		DataScreeningAnimalId = tabl3.animalId;
        		$('#DataScreeningReqNo').val(tabl3.ReqNo);
        		$('#DataScreeningAnimalCode').val(tabl3.animalCode);
        		$('#DataScreeningAnimalId').val(tabl3.animalId);
        		testItem =3 ;
        		
            }else if(tabl4 != null){
            	DataScreeningReqNo = tabl4.ReqNo;
        		DataScreeningAnimalCode = tabl4.animalCode;
        		DataScreeningAnimalId = tabl4.animalId;
        		$('#DataScreeningReqNo').val(tabl4.ReqNo);
        		$('#DataScreeningAnimalCode').val(tabl4.animalCode);
        		$('#DataScreeningAnimalId').val(tabl4.animalId);
        		testItem =4 ;
            }
    		dataScreeningTable(encodeURIComponent($('#studyNoPara').val()),DataScreeningReqNo,DataScreeningAnimalId,testItem);
			$('#DataScreeningDialog').dialog('open');
		    $('#DataScreeningDialog2').dialog('open');
		   
		    
    		}else{
    			$.messager.alert('提示','请选择筛选动物!');
            }

    		
        }
    
    	function dataScreeningTable(studyNoPara,reqNoPara,animalId,testItemPara){
    		DataScreeningTable=$('#DataScreeningTable').datagrid({
    				url : sybp()+'/tblClinicalTestDataAction_DataScreeningloadRowsAndColumns.action?studyNoPara='+studyNoPara
    				+'&reqNoPara='+reqNoPara+'&animalId='+animalId+'&testItemPara='+testItemPara,
    				title:'',
    				height: 190,
    				width:255,
    				iconCls:'',//'icon-save',
    				//pagination:true,//下面状态栏
    				pageSize:100,
    				pageList:[50,100],
    				//fit:true,
    				fitColumns:true,//不出现横向滚动条
    				nowarp:  false,//单元格里自动换行
    				//border:false,
    				//idField:'id', //pk
    				//sortName:'aniSerialNum',//排序字段
    				//sortOrder:'desc',//排序方法
    				fit:false,
    				striped:true,
    				fitColumns:false,
    				pagination:false,
    				columns :[[{
    					field:'ckid',
    					checkbox:true
    				},{
    					title : '',
    					field : 'dataId',
    					hidden:true
    				},{
    					title : '检验指标',
    					field : 'testIndexAbbr',
    					width : 100
    				},{
    					title :'检验结果',
    					field :'testData',
    					width :99
    				}]],
    				onLoadSuccess:function(data){
    			     var rows =$('#DataScreeningTable').datagrid('getRows');
    			     for(var i=0;i<rows.length;i++){ 
        			     if(rows[i].ckid == 1){
        			    	 $('#DataScreeningTable').datagrid('selectRow',i);
            			 }
    			     }
				   
    		        }

	     	});
    	   }

       function DataScreeningSaveButton(){
    	var rows =$('#DataScreeningTable').datagrid('getChecked');
    	var rows1 =$('#DataScreeningTable').datagrid('getRows');
    	var selecteds ='?';
    	 var selecteds1 = '&';
		if(rows.length <1){
			for(var i=0;i<rows1.length;i++){ 
				selecteds1 = selecteds1 +'selecteds1 ='+$('#DataScreeningTable').datagrid('getRows')[i].dataId+'&'
			 }
		}else{
			for(var i=0;i<rows.length;i++){ 
				selecteds = selecteds +'selecteds ='+$('#DataScreeningTable').datagrid('getChecked')[i].dataId+'&'
			 }
			for(var j=0;j<rows1.length;j++){ 
				selecteds1 = selecteds1 +'selecteds1 ='+$('#DataScreeningTable').datagrid('getRows')[j].dataId+'&'
			 }
		} 
			$.ajax({
				url:sybp()+'/tblClinicalTestDataAction_TestDataSelecteds.action'+selecteds+selecteds1,
				type:'post',
				cache:false,
			    dataType:'json',
			    success:function(r){
				   if(r && r.success){
						var tab = $('#tabs').tabs('getSelected');
			    		var index = $('#tabs').tabs('getTabIndex',tab);


			        	var AdditionalApplications = $('#AdditionalApplications').val();
			    		var isValidationPara=$('#isValidationPara').val();
			    		var isValidationPara1=$('#isValidationPara1').val();
			    		var studyNoPara = encodeURIComponent($('#studyNoPara').val());
			    		var reqNoPara = $('#reqNoPara').val();
			    		var combinedWithAnimal=$('#combinedWithAnimal').val();
					   if( AdditionalApplications == "all"){
		    				window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
		    				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
		            	}else{
		            		window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
							+studyNoPara+'&reqNoPara='+reqNoPara+"&isValidationPara="+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
		                }
					   

					   }
			    	
			    }
			});
       }

       function onCheckbox2(){
    	var tab = $('#tabs').tabs('getSelected');
   		var index = $('#tabs').tabs('getTabIndex',tab);
   		var isValidationPara=$('#isValidationPara').val();
   		var isValidationPara1=$('#isValidationPara1').val();
   		var studyNoPara = encodeURIComponent($('#studyNoPara').val());
   		var reqNoPara = $('#reqNoPara').val();
   		var combinedWithAnimal=$('#combinedWithAnimal').val();
             if( combinedWithAnimal == "0"){
            	 combinedWithAnimal=1;
            	 window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
 				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="
 				+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
              }else if(combinedWithAnimal == "1"){
            	  combinedWithAnimal=0;
            	  window.location.href=sybp()+'/tblClinicalTestDataAction_list.action?studyNoPara='
   				+studyNoPara+'&reqNoPara='+reqNoPara+'&AdditionalApplications='+"all"+"&isValidationPara="
   				+isValidationPara+"&isValidationPara1="+isValidationPara1+"&setTab="+index+"&combinedWithAnimal="+combinedWithAnimal;
              }
       }

       //如果不是显示关联数据，无合并动物
       function showCombinedWithAnimal(){
    	   var AdditionalApplications = $('#AdditionalApplications').val();
    	   if(AdditionalApplications != "all"){
    		   document.getElementById("checkbox2").disabled=true;
    		   document.getElementById("checkbox2a").disabled=true;
        	}
       }
       //导出uri
       function onOutExcel()
       {
           var testItemPara =1;
           var title = $('#tabs').tabs("getSelected").panel('options').title; 
          var length=0;
	   		
	   			if(title=="生化检验")
		   		{
	   				testItemPara=1;
	   				length =$('#bioChemTable').datagrid('getRows').length;
	   				
	   			}else if(title=="血液检验")
	   			{
	   				testItemPara=2;
	   				length =$('#hematTable').datagrid('getRows').length;
	   			}else if(title=="血凝检验")
	   			{
	   				testItemPara=3;
	   				length =$('#bloodCoagTable').datagrid('getRows').length;
	   			}else if(title=="尿液检验")
	   			{
	   				testItemPara=4;
	   				length =$('#urineTable').datagrid('getRows').length;
	   			}
	   			
		if(length>0)
			window.location.href=sybp()+'/tblClinicalTestDataAction_outExcel.action?studyNoPara='+$('#studyNoPara').val()+'&reqNoPara='+$('#reqNoPara').val()+'&isValidationPara='+$('#isValidationPara').val()+'&isValidationPara1='+$('#isValidationPara1').val()+'&AdditionalApplications='+$('#AdditionalApplications').val()+'&combinedWithAnimal='+$('#combinedWithAnimal').val()+'&testItemPara='+testItemPara;
		else
			$.messager.alert("提示","没有数据！");      
           
       }
       
       function onOutBioChemExcel()
       {
    	   $.ajax({
				url : sybp()+'/tblClinicalTestDataAction_outExcel.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					reqNoPara:$('#reqNoPara').val(),
					AdditionalApplications:$('#AdditionalApplications').val(),
					isValidationPara:$('#isValidationPara').val(),
					isValidationPara1:$('#isValidationPara1').val(),
					testItemPara: 1,
					combinedWithAnimal:$('#combinedWithAnimal').val()
				},
				dataType:'json',
				success:function(r){
					if(!r.success)	
						$.messager.alert("提示","没有数据！");
					if(r.success)
						$.messager.alert("提示","生化导出成功！");	
				},
				error:function(r) {
					$.messager.alert("提示","生化导出异常！");	
               },
				
          });
       }
        function onOutHematExcel()
       {
    	   $.ajax({
				url : sybp()+'/tblClinicalTestDataAction_outExcel.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					reqNoPara:$('#reqNoPara').val(),
					AdditionalApplications:$('#AdditionalApplications').val(),
					isValidationPara:$('#isValidationPara').val(),
					isValidationPara1:$('#isValidationPara1').val(),
					testItemPara: 2,
					combinedWithAnimal:$('#combinedWithAnimal').val()
				},
				dataType:'json',
				success:function(r){
					if(!r.success)	
						$.messager.alert("提示","没有数据！");
					if(r.success)
						$.messager.alert("提示","血液导出成功！");	
				},
				error:function(r) {
					$.messager.alert("提示","血液导出异常！");	
               },
				
          });
       }
       function onOutBloodCoagExcel()
       {
    	   $.ajax({
				url : sybp()+'/tblClinicalTestDataAction_outExcel.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					reqNoPara:$('#reqNoPara').val(),
					AdditionalApplications:$('#AdditionalApplications').val(),
					isValidationPara:$('#isValidationPara').val(),
					isValidationPara1:$('#isValidationPara1').val(),
					testItemPara: 3,
					combinedWithAnimal:$('#combinedWithAnimal').val()
				},
				dataType:'json',
				success:function(r){
					if(!r.success)	
						$.messager.alert("提示","没有数据！");
					if(r.success)
						$.messager.alert("提示","血凝导出成功！");	
				},
				error:function(r) {
					$.messager.alert("提示","血凝导出异常！");	
               },
				
          });
       }
       function onOutUrineExcel()
       {
    	   $.ajax({
				url : sybp()+'/tblClinicalTestDataAction_outExcel.action',
				type: 'post',
				data: {
					studyNoPara:$('#studyNoPara').val(),
					reqNoPara:$('#reqNoPara').val(),
					AdditionalApplications:$('#AdditionalApplications').val(),
					isValidationPara:$('#isValidationPara').val(),
					isValidationPara1:$('#isValidationPara1').val(),
					testItemPara: 4,
					combinedWithAnimal:$('#combinedWithAnimal').val()
				},
				dataType:'json',
				success:function(r){
					if(r.success)
						$.messager.alert("提示","尿常规导出成功！");
					if(!r.success)	
						$.messager.alert("提示","没有数据！");
				},
				error:function(r) {
					$.messager.alert("提示","尿常规导出异常！");	
                },
				
           });
       }
       
       
	</script>
  </head>
  <body class="easyui-layout">
  <s:hidden id="setTab" name="setTab" ></s:hidden>
  <s:label id="createDate" name="createDate"></s:label>
  <s:hidden id="studyNoPara" name="studyNoPara" ></s:hidden>
  <s:hidden id="reqNoPara" name="reqNoPara"></s:hidden>
  <!-- 是否显示关联临检 -->
  <s:hidden id="AdditionalApplications" name="AdditionalApplications"></s:hidden>
  <s:hidden id="isValidationPara" name="isValidationPara"></s:hidden>
  <s:hidden id="isValidationPara1" name="isValidationPara1"></s:hidden>
  <s:hidden id="combinedWithAnimal" name="combinedWithAnimal"></s:hidden>
  	 <div region="north" id="titleDiv" title="" style="height:35px;display:none;">
  	 	<a class="easyui-linkbutton" style="width:50px;" href="${pageContext.request.contextPath}/tblClinicalTestReqAction_list.action?studyNoPara=${studyNoPara}" data-options="iconCls:'icon-back',plain:true">返回</a>
  	 	<a class="easyui-linkbutton" style="width:80px;"  data-options="iconCls:'icon-edit',plain:true" onclick="TestDataScreeningButton();">数据筛选</a>
  	    <input id="checkbox" type='checkbox' ${isValidationPara == 1 ?'checked':''}  style="width:30px;vertical-align:middle" onclick="onCheckbox();" /><a href="javascript:onCheckbox();">只显示已选择数据</a>
  	    <input id="checkbox1" type='checkbox' ${isValidationPara1 == 1 ?'checked':''}  style="width:30px;vertical-align:middle;display:none" onclick="onCheckbox1();" /><a id="checkbox1a" style="display:none" href="javascript:onCheckbox1();">只显示已确认数据</a>
  	    <input id="checkbox2" type='checkbox' ${combinedWithAnimal == 1 ?'checked':''}  style="width:30px;vertical-align:middle" onclick="onCheckbox2();" /><a id="checkbox2a" href="javascript:onCheckbox2();">同一动物的数据合并显示</a>
  	    <!--   &nbsp; &nbsp; <a style="width:80px;" > 【 临检已确认数据(<a style="color:#00EE00;font-size:22px;">■</a>:已选择&nbsp;,&nbsp;<a style="font-size:22px;">■</a>:未选择)&nbsp;,&nbsp;临检未确认数据(<a style="color:red;font-size:22px;" >■</a>) 】</a> -->
  	  	    <a class="easyui-linkbutton" id="outExcel" onclick="onOutExcel();" data-options="iconCls:'icon-save',plain:true">导出excel</a>
  	 </div>
 	 <div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="生化检验" border="false" style="overflow: hidden;">
			   <div>
				<table id="bioChemTable" ></table>
				</div>
			</div>
			<div title="血液检验" border="false" style="overflow: hidden;">
				<div>
				<table id="hematTable" ></table></div>
			</div>
			<div title="血凝检验" border="false" style="overflow: hidden;">
				<div>
				<table id="bloodCoagTable" ></table></div>
			</div>
			<div title="尿液检验" border="false" style="overflow: hidden;">
				<div>
				<table id="urineTable" ></table>
				</div>
			</div>
			
		</div>
 	 </div>
 	 <div id="DataScreeningDialogDIV" style="display:none;">
 	 <div id="DataScreeningDialog" class="easyui-dialog" title="数据筛选" modal="true" closed="true" draggable="false"
		data-options="toolbar:'#dialogToolbar'" style="display:'';width:310px;height:380px;" closable="true">
			<div id="DataScreeningDialog2" style="display:'';" closable="true">
		    <div >
		   	  <div class="edit_table">
                <table class="tiprpAppInd" style="border-collapse:collapse;position: absolute;left:30px;">
                     <tr><td width="60px;">申请编号</td>
                    <td width="110px;">
                        	<input id="DataScreeningReqNo" style="width:80px;" type="text" name="" class="validatebox" required="true" readonly="true"/>
                     </td>
                     </tr>
                     <tr>
                    <td width="60px;">动物ID</td>
                    <td width="110px;">
                        	<input id="DataScreeningAnimalId" style="width:80px;" type="text" name="" class="validatebox" required="true" readonly="true"/>
                     </td>
                    </tr>
                     <tr>
                    <td width="60px;">动物编号</td>
                    <td width="110px;">
                        	<input id="DataScreeningAnimalCode" style="width:80px;" type="text" name="" class="validatebox" required="true" readonly="true"/>
                     </td>
                    </tr>
                </table>
                   <div  class="edit_table" style="border-collapse:collapse;position: absolute;top:150px;left:30px;width:205px;" >
                <table id="DataScreeningTable" ></table>
                </div>
            </div>
		    </div>
		    </div>
	</div>
	<!-- 工具栏 开始-->
	<div id="dialogToolbar">
		<a id="saveDialogButton" class="easyui-linkbutton" onclick="DataScreeningSaveButton();" data-options="iconCls:'icon-add',plain:true">确定</a>
		<a id="backButton" class="easyui-linkbutton" onclick="javascript:$('#DataScreeningDialog').dialog('close');" data-options="iconCls:'icon-back',plain:true">返回</a>

	</div>
	<!-- 工具栏 结束-->
 	</div> 
 	  
  </body>
</html>
