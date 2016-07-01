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

function addSchedulePlan(){
	   var studyNoPara = $('#studyNoPara').val();
	   $('#SchedulePlanstudyNoPara').val(studyNoPara);
	   $('#addSchedulePlanDialog').dialog('open');
	   $('#addSchedulePlanDialog2').dialog('open');
}

//保存前检查
function schedulePlanDialogSave(){
	   var taskName=$('#taskName').val();
	   var startTime=$('#startTime').datebox('getValue');
	   var checkOne = document.getElementById('selectOne'); 
	   var checkMore = document.getElementById('selectMore'); 
	   if(taskName == ""){
		   $.messager.alert('提示','请输入任务名称!','info');
	   }else{
		   if(checkOne.checked){
			   if(startTime==""){
				   $.messager.alert('提示','请输入执行日期!','info');
			   }else{
				   eventAfterSavetblSchedulePlan();
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
					        	eventAfterSavetblSchedulePlan();
					        }
					   	}else if(selectNum.checked){
					   		var taskEndNum=$('#taskEndNum').val();
			                if(taskEndNum == "" ){
			                	 $.messager.alert('提示','请输入结束次数','info');
			                }else{
			                	eventAfterSavetblSchedulePlan();
			                }
					   	}
					}
					
					
			}
	   }

}

//保存
function eventAfterSavetblSchedulePlan(){
	   $.ajax({
     		url:sybp()+'/tblSchedulePlanAction_toSavetblSchedulePlan.action?',
     		type:'post',
     		cache:false,
     		data:$('#tblSchedulePlanform').serialize(),
     		dataType:'json',
     		success:function(r){
     			if( r && r.success ){
     				$('#addSchedulePlanDialog').dialog('close');
     				parent.parent.showMessager(1,'增加日程成功',true,5000);
     				window.location.reload();
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
	    	    	 qm_showQianmingDialog('eventAfterUpdatetblSchedulePlan');
	    	    }else{
	    	    	alert(1);
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
      		data:$('#updatetblSchedulePlanform').serialize(),
      		cache:false,
      		dataType:'json',
      		success:function(r){
      			if( r && r.success ){
      				$('#updateSchedulePlanDialog').dialog('close');
      				parent.parent.showMessager(1,'签字成功',true,5000);
      				window.location.reload();
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
     		data:$('#updatetblSchedulePlanform').serialize(),
     		cache:false,
     		dataType:'json',
     		success:function(r){
     			if( r && r.success ){
     				$('#updateSchedulePlanDialog').dialog('close');
     				parent.parent.showMessager(1,'编辑成功',true,5000);
     				window.location.reload();
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
   	//$('#taskEndNumTr').css('display','');   
   	}
   }
   //单选框选择次数时间
   function SelectTaskEndType(){
   	var checkOne = document.getElementById('selectTime'); 
   	var checkMore = document.getElementById('selectNum'); 
   	if(checkOne.checked){
   	$('#endTimeTr').css('display',''); 
       $('#taskEndNumTr').css('display','none');
   	}else if(checkMore.checked){
   	$('#endTimeTr').css('display','none');
   	$('#taskEndNumTr').css('display','');   
   	}
   }
   
   