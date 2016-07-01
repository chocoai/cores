function CustomerInspection(){
    	var member=$('#left_member').val();
    	 if(member==''){
    			$('#addSchedulePlanButton').linkbutton('enable');
    			$('#editSchedulePlanButton').linkbutton('enable');
    			$('#printSchedulePlanButton').linkbutton('enable');
	     }else if(member=='readonly'){
	    	 $('#addSchedulePlanButton').linkbutton('disable');
 			 $('#editSchedulePlanButton').linkbutton('disable');
 			 $('#printSchedulePlanButton').linkbutton('enable');
		 }
}
function changeCancelName()
{
	
	if($('#continueAddButton').attr("checked")=="checked")
	{
		
		$('#backButton').linkbutton({text:'关闭'});
		
	}else {
		
		$('#backButton').linkbutton({text:'取消'});
	
	}
}
function addSchedulePlan(addOrEdit){
	
	if(addOrEdit==0)
	{
	   var studyNoPara = $('#studyNoPara').val();
	   $('#periodUnit').combobox('select', '2');
	   $('#SchedulePlanstudyNoPara').val(studyNoPara);
	   var d = new Date();
	   var years = d.getFullYear();
	   var month = add_zero(d.getMonth()+1);
	   var days = add_zero(d.getDate());
	   var towDate = years+"-"+month+"-"+days;
	   $('#updateSchedulePlanscheduleID').val('');
	  // $('#startTime').datebox('setValue', towDate);
	   document.getElementById("taskName").readOnly = false;
	   document.getElementById("taskItemTypecomtree").readOnly = false;
	   $('#taskItemType').val("");
	  // $('#taskItemTypecomtree').combotree('setValue', '');
	   $('#taskName').val("");
	   $('#remark').val("");
	   
	   $('#selectOtherButton').css('display','');
	$('#continueAddButton').css('display','');
	//document.getElementById('continueAddButton').checked="";
	
	$('#continueAddLabel').css('display','');
	   $('#taskKind').combobox('setValue', '');
	   document.getElementById("taskName").readOnly = true;
	   document.getElementById("taskItemTypecomtree").readOnly = true;
	   taskItemTypecomtree = $("#taskItemTypecomtree").tree({
		  
		   onSelect: function(newValue){
		   //  var t = $('#taskItemTypecomtree').combotree('tree');
		     var t = $('#taskItemTypecomtree');
		     var n = t.tree('getSelected');		// 获取选择的节点
             //if(!($('#taskItemTypecomtree').combotree('tree').tree('isLeaf',n.target))){
             if(!($('#taskItemTypecomtree').tree('isLeaf',n.target))){
                  //$("#taskItemTypecomtree").combotree('setValue','');
                  // $('#taskItemTypecomtree').combotree('combo').combo('showPanel');
        	
             }else{
		    if( newValue.id== "0"){
		         $("#taskItemType").val('0') ;
		    	 $("#taskName").val("") ;
		    	 document.getElementById("taskName").readOnly = false;
		    	 document.getElementById("taskName").style.backgroundColor="#FFFFFF";
		    	     $('#taskKind').combobox('enable') ;
	        }else{
	           // var pid =$('#taskItemTypecomtree').combotree('tree').tree('getParent',n.target).id;
	           var pid =$('#taskItemTypecomtree').tree('getParent',n.target).id;
	            $("#taskItemType").val(pid) ;
                $("#taskName").val(newValue.text) ;
                document.getElementById("taskName").readOnly = true;
                document.getElementById("taskName").style.backgroundColor="#EDEDED";
                
                $('#taskKind').combobox('setValue', pid) ;
                 $('#taskKind').combobox('disable') ;
            }

          
	        	//var checkOne = document.getElementById('selectOne');
	        	//checkOne.checked = "checked";
		    
             SelectTaskEndType();
   		     SchedulePlannumber();
             }
		     
	    }
	   });
	}else if(addOrEdit==1){
		 var rows = $('#tblSchedulePlanTable').datagrid('getSelections');
		   if(rows.length < 1){
			   $.messager.alert('提示','请选择编辑日程!','info');
		  }else if(rows.length > 1){
			  $.messager.alert('提示','不能同时编辑多个日程!','info');
		  }else if(rows.length = 1 ){
			
			  var row = $('#tblSchedulePlanTable').datagrid('getSelected');
			  var studyNoPara = $('#studyNoPara').val();
			  var d = new Date();
			  var years = d.getFullYear();
			  var month = add_zero(d.getMonth()+1);
			  var days = add_zero(d.getDate());
			  var towDate = years+"-"+month+"-"+days;
			  $('#updateSchedulePlanscheduleID').val(row.scheduleID);
			  $('#startTime').datebox('setValue', towDate);	
			  $('#SchedulePlanstudyNoPara').val(studyNoPara);
			  if(row.taskKind == 0){
				  $('#taskKind').combobox('select', ''); 
			  }else{
				  $('#taskKind').combobox('select', row.taskKind); 
			  }
			  $('#remark').val(row.remark);
			  $('#periodUnit').combobox('select', '2');
			  $('#taskName').val(row.taskName);
			  
			 // $('#taskItemTypecomtree').attr("disabled",disabled);
			  $('#selectOtherButton').css('display','none');
			  $('#continueAddButton').css('display','none');
			  $('#continueAddLabel').css('display','none');
			  taskItemTypecomtree = $("#taskItemTypecomtree").tree({
				   onSelect: function(newValue){}
			  });
			  document.getElementById("taskName").readOnly = true;
			  $('#taskKind').combobox('disable') ;
		  }
	}
	
	if($('#tableDisplay2Id').attr("checked")=="checked")
		tableDisplay2();
	else {
		listDisplay2();
	}
	  
	   $('#addSchedulePlanDialog').dialog('open');
	   $('#addSchedulePlanDialog2').dialog('open');
	
	   
}
//表格显示
function listDisplay2(){	     
	 $.ajax({
			url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns.action',
			type: 'post',
			data: {
				studyNoPara:$('#studyNoPara').val(),
				isValidationPara:1,
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					$('#scheduleTable').datagrid({
						frozenColumns:r.frozenColumns,
						columns:r.columns
					});
					$('#scheduleTable').datagrid('loadData',r.rows);
				}
			}
     });
}

function tableDisplay2(){	
	  $.ajax({
			url : sybp()+'/tblSchedulePlanAction_loadRowsAndColumns2.action',
			type: 'post',
			data: {
				studyNoPara:$('#studyNoPara').val(),
				isValidationPara:1,
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					$('#scheduleTable').datagrid({
						frozenColumns:r.frozenColumns,
						columns:r.columns,
					});
					$('#scheduleTable').datagrid('loadData',r.rows);
				}else{

			    }
			}
 });
	
}
//保存前检查
function schedulePlanDialogSave(){
		var addOrEdit= $('#updateSchedulePlanscheduleID').val();

	  
	   var endTime=$('#updateendTime').datebox('getValue');
	   var period=$('#updateperiod').numberbox('getValue');
	   var periodUnit=$('#updateperiodUnit').combobox('getValue');
	   var num = $('#updataskEndNum').numberbox('getValue');
	  
	
	   var taskName=$('#taskName').val();
	   var startTime=$('#startTime').datebox('getValue');
	   var checkOne = document.getElementById('selectOne'); 
	   var checkMore = document.getElementById('selectMore'); 
	   var taskKind = $('#taskKind').combobox('getValue');
	   var remark = $('#remark').val();
	   if(taskKind == ""){
		   $.messager.alert('提示','请输入任务类别!','info');
	   }else if(taskName == ""){
		   $.messager.alert('提示','请输入任务名称!','info');
	   }else{
		   if(checkOne.checked){
			   if(startTime==""){
				   $.messager.alert('提示','请输入执行日期!','info');
			   }else{
				   if(addOrEdit=='')
               	{
               		eventAfterSavetblSchedulePlan();
               	}else {
               		 var row = $('#tblSchedulePlanTable').datagrid('getSelected');
	   	                   var signName = row.signName;
	   	                  if(signName != ""){
	   		    	    	   qm_showQianmingDialog('eventAfterUpdatetblSchedulePlan');
	   		    	       }else{
	   		    	    	  noSignUpdateSchedulePlan();
	   		    	       }
					}
			   }
			}else if(checkMore.checked){
					var endTime=$('#endTime').datebox('getValue');
					var period=$('#period').numberbox('getValue');;
					var periodUnit=$('#periodUnit').combobox('getValue');
					if(startTime==""){
						   $.messager.alert('提示','请输入首次执行日期!','info');
					}else if(period == "" ){
						   $.messager.alert('提示','请输入周期值!','info');
					}else if(  periodUnit == 0){
						   $.messager.alert('提示','请输入周期单位!','info');
				     }else{
						var selectTime = document.getElementById('selectTime'); 
					   	var selectNum = document.getElementById('selectNum'); 
					   	if(selectTime.checked){
					   		var OneMonth = startTime.substring(5,startTime.lastIndexOf ('-'));  
					 	    var OneDay = startTime.substring(startTime.length,startTime.lastIndexOf ('-')+1); 
					 	    var OneYear = startTime.substring(0,startTime.indexOf ('-'));  
					        var oneDate = OneYear+"/"+OneMonth+"/"+OneDay;
					 	    var TwoMonth = endTime.substring(5,endTime.lastIndexOf ('-'));  
					 	    var TwoDay = endTime.substring(endTime.length,endTime.lastIndexOf ('-')+1); 
					 	    var TwoYear = endTime.substring(0,endTime.indexOf ('-'));  
					        var TwoDate = TwoYear+"/"+TwoMonth+"/"+TwoDay;
					   		
					        if(endTime == ""){
					 		   $.messager.alert('提示','请输入结束日期!','info');
					 	    }else if(Date.parse(oneDate)>Date.parse(TwoDate)){
					     	   $.messager.alert('提示','输入的首次执行日期小于结束日期!','info');
					        }else{
					        	if(addOrEdit=='')
			                	{
			                		eventAfterSavetblSchedulePlan();
			                	}else {
			                		 var row = $('#tblSchedulePlanTable').datagrid('getSelected');
				   	                   var signName = row.signName;
				   	                   if(signName != ""){
				   		    	    	   qm_showQianmingDialog('eventAfterUpdatetblSchedulePlan');
				   		    	       }else{
				   		    	    	  noSignUpdateSchedulePlan();
				   		    	       }
								}
					        }
					   	}else if(selectNum.checked){
					   		var taskEndNum=$('#taskEndNum').val();
			                if(taskEndNum == "" ){
			                	 $.messager.alert('提示','请输入结束次数','info');
			                }else{
			                	if(addOrEdit=='')
			                	{
			                		eventAfterSavetblSchedulePlan();
			                	}else {
			                		 var row = $('#tblSchedulePlanTable').datagrid('getSelected');
				   	                   var signName = row.signName;
				   	                  if(signName != ""){
				   		    	    	   qm_showQianmingDialog('eventAfterUpdatetblSchedulePlan');
				   		    	       }else{
				   		    	    	  noSignUpdateSchedulePlan();
				   		    	       }
								}
			                }
					   	}
					}
					
					
			}
	   }
	
}

//保存
function eventAfterSavetblSchedulePlan(){
	   $.ajax({
     		url:sybp()+'/tblSchedulePlanAction_toSavetblSchedulePlan.action?taskKind='+$('#taskKind').combobox('getValue'),
     		type:'post',
     		cache:false,
     		data:$('#tblSchedulePlanform').serialize(),
     		dataType:'json',
     		success:function(r){
     			if( r && r.success ){
     				$('#addSchedulePlanDialog').dialog('close');
     				//alert("checked="+$('#continueAddButton').attr('checked'));
     				if($('#continueAddButton').attr('checked')!='checked')//点击保存
     				{
     					parent.parent.showMessager(1,'增加日程成功',true,5000);
     					window.location.reload();
     				}else {//点击继续添加
     					parent.parent.showMessager(1,'增加日程成功',true,5000);
     					addSchedulePlan(0);
					}
     			}else{
     				$.messager.alert('提示','请检查录入数据!','info');
     	   		}
     		}
     	});	
	
 }
   
   //编辑前的检查
   function editSchedulePlan(){
	   var rows = $('#tblSchedulePlanTable').datagrid('getSelections');
	   if(rows.length < 1){
		   $.messager.alert('提示','请选择编辑日程!','info');
	  }else if(rows.length > 1){
		  $.messager.alert('提示','不能同时编辑多个日程!','info');
	  }else if(rows.length = 1 ){
		
		  var row = $('#tblSchedulePlanTable').datagrid('getSelected');
		  var studyNoPara = $('#studyNoPara').val();
		  var d = new Date();
		  var years = d.getFullYear();
		  var month = add_zero(d.getMonth()+1);
		  var days = add_zero(d.getDate());
		  var towDate = years+"-"+month+"-"+days;
		  $('#updateSchedulePlanscheduleID').val(row.scheduleID);
		  $('#updatestartTime').datebox('setValue', towDate);	
		  $('#updateSchedulePlanstudyNoPara').val(studyNoPara);
		  if(row.taskKind == 0){
			  $('#updatetaskKind').combobox('select', ''); 
		  }else{
			  $('#updatetaskKind').combobox('select', row.taskKind); 
		  }
		  $('#updateperiodUnit').combobox('select', '2');
		  $('#updatetaskName').val(row.taskName);
		  $('#updateSchedulePlanDialog').dialog('open');
		  $('#updateSchedulePlanDialog2').dialog('open');
	  }
   }

	  

   
   //编辑日期时，时间不满两位数添0补齐
   function add_zero(temp){
    if(temp<10) return "0"+temp;
    else return temp;
   }

   //编辑前检查
   function schedulePlanDialogUpdate(){
	   var taskName=$('#updatetaskName').val();
	   var startTime=$('#updatestartTime').datebox('getValue');
	   var endTime=$('#updateendTime').datebox('getValue');
	   var period=$('#updateperiod').numberbox('getValue');
	   var periodUnit=$('#updateperiodUnit').combobox('getValue');
	   var num = $('#updataskEndNum').numberbox('getValue');
	   var checkOne = document.getElementById('updateselectOne'); 
	   var checkMore = document.getElementById('updateselectMore');
	   if(checkOne.checked){
	   	   if(startTime==""){
	   	       $.messager.alert('提示','请输入执行日期!','info');
	   	   }else{
	   		    var row = $('#tblSchedulePlanTable').datagrid('getSelected');
		        var signName = row.signName;
		        
	    	    if(signName != ""){
	    	    	eventAfterUpdatetblSchedulePlan();
	    	    }else{
	    	    	noSignUpdateSchedulePlan();
	    	    }
	   	   }
	    }else if(checkMore.checked){
	   		  var OneMonth = startTime.substring(5,startTime.lastIndexOf ('-'));  
	   		  var OneDay = startTime.substring(startTime.length,startTime.lastIndexOf ('-')+1); 
	   		  var OneYear = startTime.substring(0,startTime.indexOf ('-'));  
	   	      var oneDate = OneYear+"/"+OneMonth+"/"+OneDay;
	   	      //结束执行的时间
	   		  var TwoMonth = endTime.substring(5,endTime.lastIndexOf ('-'));  
	   		  var TwoDay = endTime.substring(endTime.length,endTime.lastIndexOf ('-')+1); 
	  		  var TwoYear = endTime.substring(0,endTime.indexOf ('-'));  
	   	      var TwoDate = TwoYear+"/"+TwoMonth+"/"+TwoDay;
	   	      if(startTime==""){
	   			   $.messager.alert('提示','请输入执行日期!','info');
	   	      }else if(period == "" ){
	   			   $.messager.alert('提示','请输入周期值!','info');
	   		  }else if(  periodUnit == 0){
	   			   $.messager.alert('提示','请输入周期单位!','info');
	   		  }else{
	   			  var selectTime = document.getElementById('updateselectTime'); 
	   			  var selectNum = document.getElementById('updateselectNum'); 
	   			  if(selectTime.checked){
	   				  if( endTime == "" ){
	   					  $.messager.alert('提示','请输入结束日期!','info');
	   			       }else if(Date.parse(oneDate)>Date.parse(TwoDate)){
	   			    	   $.messager.alert('提示','输入的首次执行日期小于结束日期!','info');
	   			       } else{
	   			    	   var row = $('#tblSchedulePlanTable').datagrid('getSelected');
	   			    	   var signName = row.signName;
	   			    	  
	   			    	   if(signName != ""){
	   			    		   qm_showQianmingDialog('eventAfterUpdatetblSchedulePlan');
	   		    	       }else{
	   		    	    	   noSignUpdateSchedulePlan();
	   		    	       }
	   	                }
	   			  }else if(selectNum.checked){
	   				  if( num == "" ){
	   	                	 $.messager.alert('提示','请输入结束次数','info');
	   	                }else{
	   	                   var row = $('#tblSchedulePlanTable').datagrid('getSelected');
	   	                   var signName = row.signName;
	   		    	       if(signName != ""){
	   		    	    	   qm_showQianmingDialog('eventAfterUpdatetblSchedulePlan');
	   		    	       }else{
	   		    	    	  noSignUpdateSchedulePlan();
	   		    	       }
	   	                }
	   			  }
				  
	   	  }
	   }
   }

   //签完字执行更新
   function eventAfterUpdatetblSchedulePlan(){
	   $.ajax({
      		url:sybp()+'/tblSchedulePlanAction_toupdatetblSchedulePlan.action?'+'esType=15',
      		type:'post',
      		data:$('#tblSchedulePlanform').serialize(),
      		cache:false,
      		dataType:'json',
      		success:function(r){
      			if( r && r.success ){
      				$('#addSchedulePlanDialog').dialog('close');
      				parent.parent.showMessager(1,'签字成功',true,5000);
      			//	window.location.reload();
      				$('#tblSchedulePlanTable').datagrid('reload');
      			}else{
      			 $.messager.alert('提示','请检查录入数据!','info');
      	   		}
      		}
      	});	
   }
   
   //未签字直接执行更新操作
   function noSignUpdateSchedulePlan(){
	   var row = $('#tblSchedulePlanTable').datagrid('getSelected');
	   $.ajax({
     		url:sybp()+'/tblSchedulePlanAction_noSignUpdateSchedulePlan.action?scheduleID='+row.scheduleID,
     		type:'post',
     		data:$('#tblSchedulePlanform').serialize(),
     		cache:false,
     		dataType:'json',
     		success:function(r){
     			if( r && r.success ){
     				$('#addSchedulePlanDialog').dialog('close');
     				parent.parent.showMessager(1,'编辑成功',true,5000);
     				//window.location.reload();
     				$('#tblSchedulePlanTable').datagrid('reload');
     			}else{
     			 $.messager.alert('提示','请检查录入数据!','info');
     	   		}
     		}
     	});	
   }
   

   //跳转到预览页面
   function previewSchedulePlan(){
     var studyNoPara = $('#studyNoPara').val();
     window.location.href= sybp()+"/tblSchedulePlanAction_previewSchedulePlan.action?isValidationPara=1&disPlaytype=1&studyNoPara="+$('#studyNoPara').val();
   }
   
 //单选框选择单次、周期
   function SchedulePlannumber(){
   	var checkOne = document.getElementById('selectOne'); 
   	var checkMore = document.getElementById('selectMore'); 
   	if(checkOne.checked){
   	$('#endTimeTr').css('display','none'); 
   	$('#periodTr').css('display','none');
   	$('#startTimeTr').val('执行日期');
   	$('#taskEndTypeTr').css('display','none');
    $('#taskEndNumTr').css('display','none');
   	}else if(checkMore.checked){
   	$('#endTimeTr').css('display',''); 
   	$('#periodTr').css('display',''); 
   	$('#startTimeTr').val('首次执行日期');
   	$('#taskEndTypeTr').css('display','');
   	SelectTaskEndType();
   	}
   	
   }
   //单选框选择次数时间
   function SelectTaskEndType(){
   	var checkOne = document.getElementById('selectTime'); 
   	var checkMore = document.getElementById('selectNum'); 
   	if(checkOne.checked){
   	$('#endTimeTr').css('display',''); 
    $('#taskEndNumTr').css('display','none');
   // $('#period').css('display',''); 
   	}else if(checkMore.checked){
   	$('#endTimeTr').css('display','none');
   	$('#taskEndNumTr').css('display','');   
 	//$('#period').css('display','none'); 
   	}
   }
   
   