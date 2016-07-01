//比较时间大小
function dateCompare(startdate,enddate){   
     var arr=startdate.split("-");    
     var starttime=new Date(arr[0],arr[1],arr[2]);    
     var starttimes=starttime.getTime();   
     var arrs=enddate.split("-");    
     var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
     var lktimes=lktime.getTime();   
     if(starttimes>=lktimes){   
       return false;   
     }else{
       return true;
     }   
 }  





/****QA开始********************************************************/
//任命QA
function onAppointQAButton(itemId,studyNo,position){
		$('#pos').val(position);
        showSelectQADialog('add',itemId,studyNo);
}

//重新任命QA
function onAgainEditQAButton(){
    showSelectQADialog('edit',itemId,studyNo);
}

 //显示任命窗口QA
    function showSelectQADialog(addOrEdit,itemId,studyNo){
    		//selectQAinputCombobox();
            $('#selectQAAddOrEdit').val(addOrEdit);
            $('#itemId').val(itemId);
            $('#studyNoForQA').val(studyNo);
            
            $('#selectQADialog').dialog('open');
            $('#selectQADialog2').dialog('open');
    }
   //关闭QA
   function  onBackButton_selectQADialog(){
      $('#selectQADialog').dialog('close');
   }
    
   //初始化QA下拉选
   function selectQAinputCombobox(){
		$('#selectQAinput').combobox({
			url:sybp()+'/tblAppointSDAction_selectQAinputCombobox.action',
			valueField:'id',
			textField:'text',
			required: true,    
			onSelect: function(record){    

			}
		});
	}
	
	//保存QA
	function saveAppointQA(){
	       if( $('#selectQAForm').form('validate') ){
	             var addOrEdit = $('#selectQAAddOrEdit').val();
	             var itemId = $('#itemId').val();
	             var  studyNo = $('#studyNoForQA').val();
	             if(addOrEdit == "add"){
	                 var sd = $('#selectQAinput').combobox('getValue');//QA
		            
		             var ary = new Array();
		             ary.push(itemId);
			      	ary=ary.join(",");
			      	var ary2 = new Array();
			      	 ary2.push(studyNo);
			      	 ary2=ary2.join(",");
		             $.ajax({
		        	 	  	url : sybp()+'/tblAppointSDAction_toAppointQA.action',
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: {
		        		  		sd:sd,
		        		  		ids:ary,
		        		  		sids:ary2
		        		  	},
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  		
		        		  	      if(r.success){
			        		  	      onBackButton_selectQADialog();
   		        		  			   $.messager.defaults = { ok: "是", cancel: "否" };
								      $.messager.confirm('确认','是否签字提交!',function(r){    
					                	      if (r){
					                			   qm_showQianmingDialog('afterSaveSubmitAppointQA');
					                			 
					                		  }else{
					                			  
					                			  // parent.$('#topInfoLabel').html("");
					                			  // document.getElementById("chkPlanLeft").contentWindow.document.getElementById('hiddenForClearTabs').click();
					                			   //$('#studyDatagrid').datagrid('reload');
					                		     
		                			              // $('#studyDatagrid').datagrid("unselectAll");
					                		     
					                		   }    
								      }); 
								      $.messager.defaults = { ok: "确定", cancel: "取消" };
		        		  	        }else if(r.error){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else{
	                  qm_showQianmingDialog('onAfterAgainEditQAButton');
	             }
	             
	         }
	      
	  
	}
	//保存后提交任命
	function afterSaveSubmitAppointQA(){
		var studyNo = $('#studyNoForQA').val();
        var ary = new Array();
        	ary.push(studyNo);
        	
     	 var getSelections = ary.join(",");
     	 
     	$.messager.progress({title: '请稍后',
     						msg: '处理中...',
     						text:''}); 
     	 
     	 $.ajax({
 		  	url : sybp()+'/tblAppointSDAction_afterSaveSubmitAppointQA.action',
 		  	type: 'post',
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	data: {
 		  	ids:getSelections,
 		  	},
 		  	dataType:'json',
 		  	success:function(r){
 		  			$.messager.progress('close');
 		  		    if(r.success){
     		  	        var qa=$('#selectQAinput').combobox('getValue');
     		  	        var qaName=$('#selectQAinput').combobox('getText');
     		  	        
     		  	        var pos = $('#pos').val();
     		  	        if(pos=='1')
     		  	        {
     		  	        	parent.$('#topInfoLabel').html("");
     		  	        	//document.getElementById("chkPlanLeft").contentWindow.document.getElementById('hiddenForClearTabs').click();
     		  	        	document.getElementById("qAChkPlanMainFrame").contentWindow.document.getElementById("chkPlanLeft").contentWindow.$('#studyDatagrid').datagrid('reload');
     		  	        	//document.getElementById("qAChkPlanMainFrame").contentWindow.document.getElementById("chkPlanMain").reload();
     		  	        }else if(pos=='2'){
     		  	        	document.getElementById('qAChkPlanPlanFrame').contentWindow.$('#qaChkPlanDatagrid').datagrid('reload');
     		  	        }
     		  	     var msg='任命QA检查员后发邮件出现错误！';
					sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
     		  	        
     		  	    }else{
     		  	    	parent.parent.showMessager(3,'提交失败',true,5000);
     		  	    }
 		  			
 		  	},
 		  	error:function(err){
 		  		alert(err);
 		  	}
 		  });   
	}
	function sendNotification(msgTitle,msgContent,receiverList,msg){
		if(receiverList!=null&&receiverList!=''){
			
			  $.ajax({
		      	url : sybp()+'/tblAppointSDAction_sendNotification.action',
		      	type: 'post',
		      	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			         dataType:'json',
			         data:{
		      			msgTitle:msgTitle,
		      			msgContent:msgContent,
		      			receiverList:receiverList,
				      },
			         success:function(r){
						if(r&&!r.success){
							$.messager.alert('提示框',msg);
						}
			         }
		    });
		}else{
			//$.messager.alert('提示框','发邮件没有接收人存在');
		}
    }
	
	
	//提交任命QA
    function submitAppointQAButton(){
      qm_showQianmingDialog('afterSubmitAppointQA');
    }
    //真正的提交QA
	function afterSubmitAppointQA(){
		var itemId = $('#itemId').val();
         var ary = new Array();
         ary.push(itemId);
         var getSelections = ary.join(",");
         
         $.ajax({
    		  	url : sybp()+'/tblAppointSDAction_submitAppointQA.action',
    		  	type: 'post',
    		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    		  	data: {
    		  	ids:getSelections,
    		  	},
    		  	dataType:'json',
    		  	success:function(r){
    		  	        if(r.success){
        		  	         onBackButton_selectQADialog();
        		  	       
        		  			parent.$('#topInfoLabel').html("");
        		  			// document.getElementById("chkPlanLeft").contentWindow.document.getElementById('hiddenForClearTabs').click();
        		  			$('#studyDatagrid').datagrid("reload");
   			                   $('#studyDatagrid').datagrid("unselectAll");
   			                   
   			                var msg='提交任命QA后发邮件出现错误！';
							sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
							
        		  	    }else{
        		  	     	parent.parent.showMessager(3,'提交失败',true,5000);
        		  	    }
    		  			
    		  	}
    		  });             
	
	}
	/**真正的重新任命*/
	function onAfterAgainEditQAButton(){
	     var sd = $('#selectQAinput').combobox('getValue');
	     
	     	var itemId = $('#itemId').val();
	     	var ary = new Array();
	     	ary.push(itemId);
	      	 var getSelections = ary.join(",");
             $.ajax({
        		  	url : sybp()+'/tblAppointSDAction_updateAppointQA.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		  	sd:sd,
        		  	ids:getSelections
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  	    if(r.success){
        		  	          onBackButton_selectQADialog();
                              $('#appointSDTable').datagrid('reload');
                              $('#appointSDTable').datagrid("unselectAll");
                              parent.parent.showMessager(1,'任命成功',true,5000);
                              $('#onAppointQAButton').linkbutton('disable'); 
					          $('#onSubmitAppointQAButton').linkbutton('disable'); 
					          $('#onAgainEditQAButton').linkbutton('enable'); 
					          
					          var msg='重新任命QA后发邮件出现错误！';
					          sendNotification(r.msgTitle,r.msgContent,r.receiverList,msg);
								
        		  	    }else{
        		  	    	 parent.parent.showMessager(3,'任命失败',true,5000);
        		  	    }
        		  	}
        		  });   
          
	   
	}
/****QA结束********************************************************/
