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
/****SD开始********************************************************/
//初始化SD下拉选
function selectSDinputCombobox(){
		$('#selectSDinput').combobox({
			url:sybp()+'/tblAppointSDAction_selectSDinputCombobox.action',
			valueField:'id',
			textField:'text',
			required: true,    
			onSelect: function(record){    

			}
		});
	}

//关闭SD选择窗口
function onBackButton_selectSDDialog(){
  $('#selectSDDialog').dialog('close');
  
}

//保存SD
function saveAppointSD(){
   if( $('#selectSDForm').form('validate') ){
     var addOrEdit = $('#selectSDAddOrEdit').val();
     if(addOrEdit == "add"){
    	 $('#onSubmitAppointSDButton').linkbutton('disable');
         var sd = $('#selectSDinput').combobox('getValue');
         var appointDate = $('#appointDate').datebox('getValue');
         //var rows = $('#appointSDTable').datagrid('getSelections');
         // for(var j=0;j<rows.length;j++){
		 //   var getid =  $('#appointSDTable').datagrid('getSelections')[j].sid;
	 	 //   ary = ary.concat(getid);
	     // }
         // var getSelections = ary.join(",");
         var rows = $('#studyNotableDatagrid').datagrid('getRows');
         var studyNos = new Array();
		 var remarks = new Array();
		 var partners = new Array();
		 for(var i = 0 ;i < rows.length;i++){
			 $('#studyNotableDatagrid').datagrid('endEdit',i)
			 studyNos.push( $('#appointSDTable').datagrid('getSelections')[i].sid);
			 if(rows[i].remark == ""){
				 remarks.push("-1");
			 }else{
				 remarks.push(rows[i].remark);
			 }
			 if(rows[i].partner == ""){
				 partners.push("-1");
			 }else{
				 partners.push(rows[i].partner);
			 }
			  
         }
         $.ajax({
    	 	  	url : sybp()+'/tblAppointSDAction_toAppointSD.action?studyNos='+studyNos+'&remarks='+encodeURIComponent(remarks)
    	 	  	       +"&partners="+encodeURIComponent(partners),
    		  	type: 'post',
    		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    		  	data: {
    		  	sd:sd,
    		  	appointDate:appointDate,
    		  	//ids:getSelections
    		  	},
    		  	dataType:'json',
    		  	success:function(r){
    		  	        if(r.success){
    		  	          $.messager.confirm('确认','是否签字提交!',function(r){    
	                		  if (r){
	                			   qm_showQianmingDialog('afterSaveSubmitAppointSD');
	                		  }else{
	                			   $('#appointSDTable').datagrid('reload');
	                			   $('#appointSDTable').datagrid("unselectAll");
	                		   }    
	                       }); 
        		  	        onBackButton_selectSDDialog();
        		  			parent.parent.showMessager(1,'任命成功',true,5000);
        		  			$('#onSubmitAppointSDButton').linkbutton('enable');
        		  			$('#onAppointSDButton').linkbutton('disable'); 
			                $('#onAgainEditButton').linkbutton('disable'); 
			                $('#printerButton').linkbutton('disable');
			                $.messager.defaults = { ok: "是", cancel: "否" }; 
			            
			                
    		  	        }else{
    		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
    		  	        }
    		  	}
    		  });   
     }else{
         qm_showQianmingDialog('onAfterAgainEditButton');
     }
     
 }
}

//任命后直接提交
function afterSaveSubmitAppointSD(){
	var sd = $('#selectSDinput').combobox('getValue');
    var rows = $('#appointSDTable').datagrid('getSelections');
    var ary = new Array();
    for(var j=0;j<rows.length;j++){
	   var getid =  $('#appointSDTable').datagrid('getSelections')[j].id;
	   ary = ary.concat(getid);
    }
 	 var getSelections = ary.join(",");
 	 if(getSelections != "" ){
 		  $.ajax({
 			  	url : sybp()+'/tblAppointSDAction_afterSaveSubmitAppointSD.action',
 			  	type: 'post',
 			  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 			  	data: {
 			  	ids:getSelections,
 			  	},
 			  	dataType:'json',
 			  	success:function(r){
 			  	        if(r.success){
	 	  		  	          onBackButton_selectSDDialog();
	 	  		  	          $('#appointSDTable').datagrid('reload');
	 	  		  			  parent.parent.showMessager(1,'提交成功',true,5000);
	 	  		  			  $('#onSubmitAppointSDButton').linkbutton('disable');
	 	      		  		  $('#onAppointSDButton').linkbutton('disable'); 
	 				          $('#onAgainEditButton').linkbutton('enable'); 
	 				          $('#printerButton').linkbutton('enable');
	 			              $('#appointSDTable').datagrid("unselectAll");
 	  		  	         }else{
 	  		  	              parent.parent.showMessager(3,'提交失败',true,5000);
 	  		  	         }
 			  			
 			  	}
 			  });  
 		 
 	 }
 	
 	 
}

//提交SD任命
function submitAppointSDButton(){
  qm_showQianmingDialog('afterSubmitAppointSD');
}
//真正的提交任命
function afterSubmitAppointSD(){
   var rows = $('#appointSDTable').datagrid('getSelections');
     var ary = new Array();
      for(var j=0;j<rows.length;j++){
	     var getid =  $('#appointSDTable').datagrid('getSelections')[j].isSDM;
	        ary = ary.concat(getid);
     }
     var getSelections = ary.join(",");
     
     var ary1 = new Array();
      for(var j=0;j<rows.length;j++){
        var getid =  $('#appointSDTable').datagrid('getSelections')[j].isSDM;
      if(!getid){
        var sid =  $('#appointSDTable').datagrid('getSelections')[j].sid;
		ary1 = ary1.concat(sid);
      }else{
       continue;
      }
	    
     }
     var getSelections1 = ary1.join(",");
     $.ajax({
		  	url : sybp()+'/tblAppointSDAction_submitAppointSD.action',
		  	type: 'post',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		  	data: {
		  	ids:getSelections,
		  	sids:getSelections1
		  	},
		  	dataType:'json',
		  	success:function(r){
		  	        if(r.success){
    		  	        onBackButton_selectSDDialog();
    		  	        $('#appointSDTable').datagrid('reload');
    		  			parent.parent.showMessager(1,'提交成功',true,5000);
    		  			$('#onSubmitAppointSDButton').linkbutton('disable');
        		  		$('#onAppointSDButton').linkbutton('disable'); 
			            $('#onAgainEditButton').linkbutton('enable'); 
			            $('#printerButton').linkbutton('enable');
			            //var sdState = $('#studyItem_testItem').combobox('getValue');
	     		        //if(  sdState != "" ){
			                   $('#appointSDTable').datagrid("unselectAll");
	     		        //}
    		  	    }else{
    		  	    	parent.parent.showMessager(3,'提交失败',true,5000);
    		  	    }
		  			
		  	}
		  });             
   }
 //选择SD
 function onAppointSDButton(){
    showSelectSDDialog('add');
}
//重新任命SD
function onAgainEditButton(){
   showSelectSDDialog('edit');
}

//真正的重新任命
function onAfterAgainEditButton(){
         var sd = $('#selectSDinput').combobox('getValue');
         var rows1 = $('#appointSDTable').datagrid('getSelections');
         var ary = new Array();
      	 var getSelections = ary.join(",");
      	 var remarks = new Array();
      	 var studyNos = new Array();
      	 var partners = new Array();
      	 var rows = $('#studyNotableDatagrid').datagrid('getRows');
		 for(var i = 0 ;i < rows.length;i++){
			 $('#studyNotableDatagrid').datagrid('endEdit',i)
			 if(rows[i].remark == ""){
				 remarks.push("-1");
			 }else{
				 remarks.push(rows[i].remark);
			 }
			 if(rows[i].partner == ""){
				 partners.push("-1");
			 }else{
				 partners.push(rows[i].partner);
			 }
			 studyNos.push(rows1[i].isSDM);
         }
         $.ajax({
    		  	url : sybp()+'/tblAppointSDAction_updateAppointSD.action?studyNos='+studyNos+'&remarks='+encodeURIComponent(remarks)
    		  	    +"&partners="+encodeURIComponent(partners),
    		  	type: 'post',
    		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    		  	data: {
    		  	sd:sd,
    		  	},
    		  	dataType:'json',
    		  	success:function(r){
    		  	    if(r.success){
    		  	          onBackButton_selectSDDialog();
                          $('#appointSDTable').datagrid('reload');
                          $('#onSubmitAppointSDButton').linkbutton('disable');
        		  		  $('#onAppointSDButton').linkbutton('disable'); 
			              $('#onAgainEditButton').linkbutton('disable'); 
			              $('#printerButton').linkbutton('enable');
                          parent.parent.showMessager(1,'任命成功',true,5000);
                          //var sdState = $('#studyItem_testItem').combobox('getValue');
           		          //if(  sdState != "" ){
			                   $('#appointSDTable').datagrid("unselectAll");
           		          //}
    		  	    }else{
    		  	    	 parent.parent.showMessager(3,'与数据库交互异常',true,5000);
    		  	    }
    		  	}
    		  });   
      
      
}
function initstudyNotableDatagrid(){
	 $('#studyNotableDatagrid').datagrid({
	     width:550,
         height:220,
         singleSelect:true, //不支持多选
         fitColumns:false,
         nowarp:  false,//单元格里自动换行
         striped:true,
         columns:[[
            {field:'index',title:'序号',width:30,halign:'center',height:12},        
        	{field:'studyNo',title:'专题编号',width:120,halign:'center',height:12},    
            {field:'remark',title:'备注',width:195,halign:'center',height:12,editor:{type:'validatebox',options:{validType:'maxLength[200]'}}}, 
            {field:'partner',title:'成员',width:135,halign:'center',height:12,editor:{
            	type:'combobox',
            	options:{
	            	url:sybp()+'/tblAppointSDAction_selectSDinputCombobox2.action',
	    			valueField:'id',
	    			textField:'text',
	    			required: false,    
	    			onSelect: function(record){    
	
	    			}
                }
            }}, 
	     ]],
         rowStyler:function(){
	          return "color:#000000;";
         },
	 }); 
	 $('#studyNotableDatagrid').datagrid('loadData',{total:0,rows:[]}); 
}

//显示任命窗口
function showSelectSDDialog(addOrEdit){
       var rows = $('#appointSDTable').datagrid('getSelections');
       selectSDinputCombobox();
    	$('#selectSDAddOrEdit').val(addOrEdit);
    	if(addOrEdit == 'add'){
    		$('#appointDateTR').css('display','');
    		var date = new Date();
    		var currentDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    		$('#appointDate').datebox('setValue',currentDate);
    		$('#studyNotableDiv').height('220');
    	}else{
    		$('#appointDateTR').css('display','none');
    		$('#studyNotableDiv').height('250');
    	}
        //   var subtable = document.getElementById('studyNotable'); 
        //   while (subtable.rows.length > 1){
		//	  if(subtable.rows.length != 1){
	    //             subtable.deleteRow(subtable.rows.length-1);
	    //           }
		//	 }
		//
        //   for(var y = 1;y<=rows.length;y++){
		//	   var i=subtable.rows.length; 
		//	   var addrow=subtable.insertRow();//添加一行到第一行的位置行 
		//	   var col1=addrow.insertCell(0);//添加列 
		//	   col1.innerHTML=y; 
			   //  var col2=addrow.insertCell(1);//添加一行到第一列的位置行 
		//	   var col2=addrow.insertCell(); 
		//	   var j=subtable.rows[0].cells.length; //得到td的数目 
		//	   col2.className="table01-td01";
		//	   col2.innerHTML=rows[y-1].studyNo; 
		//   }
        initstudyNotableDatagrid();//初始化
		for(var i = 0;i<rows.length;i++){
			$('#studyNotableDatagrid').datagrid('appendRow',{
				index:i+1,
				studyNo: rows[i].studyNo,
				remark: rows[i].remark2,
				partner:rows[i].partner
			});
			$('#studyNotableDatagrid').datagrid('beginEdit',i);
		}
        $('#selectSDDialog').dialog('open');
        $('#selectSDDialog2').dialog('open');
}

/**打印任命书*/
function printer(){
var rows = $('#appointSDTable').datagrid('getSelections');
     var ary = new Array();
      for(var j=0;j<rows.length;j++){
	   var getid =  $('#appointSDTable').datagrid('getSelections')[j].studyNo;
	   ary = ary.concat(getid);
     }
     var getSelections = ary.join(",");

    $.ajax({
		  	url : sybp()+'/tblIntegratedInformAction_printNumber.action',
		  	type: 'post',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		  	data: {
		  	studyNo:getSelections,
		  	},
		  	dataType:'json',
		  	success:function(r){
		  	        if(r && r.success){
    		  	       if(getSelections){
						  	window.location.href = sybp()+'/tblIntegratedInformAction_ireport.action?studyNo='+getSelections;
						}
    		  	    }else{
    		  	      $.messager.confirm('确认对话框', '专题编号：'+r.studyNolist +' 已打印，确定继续吗？', function(r){
							if (r){
								window.location.href = sybp()+'/tblIntegratedInformAction_ireport.action?studyNo='+getSelections;
							}
					});
    		  	    }
		  			
		  	}
		  });      

}
/****SD结束********************************************************/
/****QA开始********************************************************/
//任命QA
function onAppointQAButton(){
        showSelectQADialog('add');
}

//重新任命QA
function onAgainEditQAButton(){
    showSelectQADialog('edit');
}

 //显示任命窗口QA
    function showSelectQADialog(addOrEdit){
           var rows = $('#appointSDTable').datagrid('getSelections');
           selectQAinputCombobox();
        	$('#selectQAAddOrEdit').val(addOrEdit);
               var subtable = document.getElementById('studyNoQAtable'); 
               while (subtable.rows.length > 1){
				  if(subtable.rows.length != 1){
		                 subtable.deleteRow(subtable.rows.length-1);
		               }
				 }
			
	           for(var y = 1;y<=rows.length;y++){
				   var i=subtable.rows.length; 
				   var addrow=subtable.insertRow();//添加一行到第一行的位置行 
				   var col1=addrow.insertCell(0);//添加列 
				   col1.innerHTML=y; 
				   //  var col2=addrow.insertCell(1);//添加一行到第一列的位置行 
				   var col2=addrow.insertCell(); 
				   var j=subtable.rows[0].cells.length; //得到td的数目 
				   col2.className="table01-td01";
				   col2.innerHTML=rows[y-1].studyNo; 
			   }
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
	             if(addOrEdit == "add"){
	                 var sd = $('#selectQAinput').combobox('getValue');
		             var rows = $('#appointSDTable').datagrid('getSelections');
		             var ary = new Array();
		             for(var j=0;j<rows.length;j++){
					   var getid =  $('#appointSDTable').datagrid('getSelections')[j].sid;
					   ary = ary.concat(getid);
				     }
			      	 var getSelections = ary.join(",");
		             $.ajax({
		        	 	  	url : sybp()+'/tblAppointSDAction_toAppointQA.action',
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
   		        		  			  parent.parent.showMessager(1,'任命成功',true,5000);
			        		  		  $('#onAppointQAButton').linkbutton('disable'); 
								      $('#onSubmitAppointQAButton').linkbutton('enable'); 
								      $('#onAgainEditQAButton').linkbutton('disable'); 
								      $.messager.defaults = { ok: "是", cancel: "否" };
								      $.messager.confirm('确认','是否签字提交!',function(r){    
					                	      if (r){
					                			   qm_showQianmingDialog('afterSaveSubmitAppointQA');
					                			  // $('#appointSDTable').datagrid('reload');
					                		  }else{
					                			   $('#appointSDTable').datagrid('reload');
					                		       //var sdState = $('#qastudyItem_testItem').combobox('getValue');
					                		       //if(  sdState != "" ){
		                			                   $('#appointSDTable').datagrid("unselectAll");
					                		      // }
					                		   }    
					                }); 
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
		var rows = $('#appointSDTable').datagrid('getSelections');
        var ary = new Array();
        for(var j=0;j<rows.length;j++){
		   var getid =  $('#appointSDTable').datagrid('getSelections')[j].id;
		   ary = ary.concat(getid);
	     }
     	 var getSelections = ary.join(",");
     	 $.ajax({
 		  	url : sybp()+'/tblAppointSDAction_afterSaveSubmitAppointQA.action',
 		  	type: 'post',
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	data: {
 		  	ids:getSelections,
 		  	},
 		  	dataType:'json',
 		  	success:function(r){
 		  	        if(r.success){
     		  	        onBackButton_selectSDDialog();
     		  	        $('#appointSDTable').datagrid('reload');
     		  			parent.parent.showMessager(1,'提交成功',true,5000);
     		  			$('#onAppointQAButton').linkbutton('disable'); 
					    $('#onSubmitAppointQAButton').linkbutton('disable'); 
					    $('#onAgainEditQAButton').linkbutton('enable'); 
					   // var sdState = $('#qastudyItem_testItem').combobox('getValue');
               		   // if( sdState != "" ){
   			                $('#appointSDTable').datagrid("unselectAll");
               		   // }
     		  	    }else{
     		  	    	parent.parent.showMessager(3,'提交失败',true,5000);
     		  	    }
 		  			
 		  	}
 		  });   
	}
	
	
	//提交任命QA
    function submitAppointQAButton(){
      qm_showQianmingDialog('afterSubmitAppointQA');
    }
    //真正的提交QA
	function afterSubmitAppointQA(){
	     var rows = $('#appointSDTable').datagrid('getSelections');
         var ary = new Array();
          for(var j=0;j<rows.length;j++){
		     var getid =  $('#appointSDTable').datagrid('getSelections')[j].qid;
		        ary = ary.concat(getid);
	     }
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
        		  	         onBackButton_selectSDDialog();
        		  	        $('#appointSDTable').datagrid('reload');
        		  			parent.parent.showMessager(1,'提交成功',true,5000);
        		  			$('#onAppointQAButton').linkbutton('disable'); 
					        $('#onSubmitAppointQAButton').linkbutton('disable'); 
					        $('#onAgainEditQAButton').linkbutton('enable'); 
					       // var sdState = $('#qastudyItem_testItem').combobox('getValue');
               		       // if(  sdState != "" ){
   			                   $('#appointSDTable').datagrid("unselectAll");
               		       // }
        		  	    }else{
        		  	     	parent.parent.showMessager(3,'提交失败',true,5000);
        		  	    }
    		  			
    		  	}
    		  });             
	
	}
	/**真正的重新任命*/
	function onAfterAgainEditQAButton(){
	     var sd = $('#selectQAinput').combobox('getValue');
             var rows = $('#appointSDTable').datagrid('getSelections');
             var ary = new Array();
             for(var j=0;j<rows.length;j++){
			   var getid =  $('#appointSDTable').datagrid('getSelections')[j].qid;
			   ary = ary.concat(getid);
		     }
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
        		  	    }else{
        		  	    	 parent.parent.showMessager(3,'任命失败',true,5000);
        		  	    }
        		  	}
        		  });   
          
	   
	}
/****QA结束********************************************************/
/****病理开始********************************************************/
	 //重新任命
	  function onAgainEditPAButton(){
	       showSelectPADialog('edit');
	  }
	  //真正的重新任命
	  function onAfterAgainEditPAButton(){
	       var sd = $('#selectPAinput').combobox('getValue');
           var rows = $('#appointSDTable').datagrid('getSelections');
           var ary = new Array();
           for(var j=0;j<rows.length;j++){
			   var getid =  $('#appointSDTable').datagrid('getSelections')[j].pid;
			   ary = ary.concat(getid);
		     }
	      	 var getSelections = ary.join(",");
           $.ajax({
      		  	url : sybp()+'/tblAppointSDAction_updateAppointPA.action',
      		  	type: 'post',
      		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
      		  	data: {
      		  	sd:sd,
      		  	ids:getSelections
      		  	},
      		  	dataType:'json',
      		  	success:function(r){
      		  	    if(r.success){
      		  	          onBackButton_selectPADialog();
                          $('#appointSDTable').datagrid('reload');
                          $('#appointSDTable').datagrid("unselectAll");
                          parent.parent.showMessager(1,'任命成功',true,5000);
                          $('#onAppointPAButton').linkbutton('disable'); 
   					      $('#onSubmitAppointPAButton').linkbutton('disable'); 
   					      $('#onAgainEditPAButton').linkbutton('enable'); 
      		  	    }else{
      		  	          parent.parent.showMessager(3,'任命失败',true,5000);
      		  	    }
      		  	}
      		  });   
	  
	  }
	  function submitAppointPAButton(){
		  qm_showQianmingDialog('afterSubmitAppointPA');
	  }
		 
		  //真正的提交PA
			function afterSubmitAppointPA(){
			     var rows = $('#appointSDTable').datagrid('getSelections');
	             var ary = new Array();
	              for(var j=0;j<rows.length;j++){
				     var getid =  $('#appointSDTable').datagrid('getSelections')[j].pid;
				        ary = ary.concat(getid);
			     }
		         var getSelections = ary.join(",");
		         
	             $.ajax({
	        		  	url : sybp()+'/tblAppointSDAction_submitAppointPA.action',
	        		  	type: 'post',
	        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	        		  	data: {
	        		  	ids:getSelections,
	        		  	},
	        		  	dataType:'json',
	        		  	success:function(r){
	        		  	        if(r.success){
		        		  	         onBackButton_selectPADialog();
		        		  	        $('#appointSDTable').datagrid('reload');
		        		  			parent.parent.showMessager(1,'提交成功',true,5000);
		        		  			 $('#onAppointPAButton').linkbutton('disable'); 
		    					     $('#onSubmitAppointPAButton').linkbutton('enable'); 
		    					     $('#onAgainEditPAButton').linkbutton('disable'); 
		    					      // var sdState = $('#pastudyItem_testItem').combobox('getValue');
	   	                		      //  if(  sdState != "" ){
	          			                   $('#appointSDTable').datagrid("unselectAll");
	   	                		       //}
		        		  	    }else{
		        		  	    	parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	    }
	        		  			
	        		  	}
	        		  });             
			
			}
			 //病理保存
	        function saveAppointPA(){
	            if( $('#selectPAForm').form('validate') ){
			             var addOrEdit = $('#selectPAAddOrEdit').val();
			             if(addOrEdit == "add"){
			                 var sd = $('#selectPAinput').combobox('getValue');
				             var rows = $('#appointSDTable').datagrid('getSelections');
				             var ary = new Array();
				             for(var j=0;j<rows.length;j++){
							   var getid =  $('#appointSDTable').datagrid('getSelections')[j].sid;
							   ary = ary.concat(getid);
						     }
					      	 var getSelections = ary.join(",");
				             $.ajax({
				        	 	  	url : sybp()+'/tblAppointSDAction_toAppointPA.action',
				        		  	type: 'post',
				        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				        		  	data: {
				        		  	sd:sd,
				        		  	ids:getSelections
				        		  	},
				        		  	dataType:'json',
				        		  	success:function(r){
				        		  	        if(r.success){
					        		  	         onBackButton_selectPADialog();
					        		  			 parent.parent.showMessager(1,'任命成功',true,5000);
					        		  			 $('#onAppointPAButton').linkbutton('disable'); 
					    					     $('#onSubmitAppointPAButton').linkbutton('enable'); 
					    					     $('#onAgainEditPAButton').linkbutton('disable'); 
					    					     $.messager.defaults = { ok: "是", cancel: "否" };
					    					     $.messager.confirm('确认','是否签字提交!',function(r){    
					   	                	      if (r){
					   	                			   qm_showQianmingDialog('afterSaveSubmitAppointPA');
					   	                			   //$('#appointSDTable').datagrid('reload');
					   	                		  }else{
					   	                			   $('#appointSDTable').datagrid('reload');
					   	                		       //var sdState = $('#pastudyItem_testItem').combobox('getValue');
					   	                		       //if(  sdState != "" ){
					          			                   $('#appointSDTable').datagrid("unselectAll");
					   	                		       //}
					   	                		  }    
					   	                     }); 
				        		  	        }else if(r.error){
				        		  	            $.messager.alert('提示',r.msg,'info');
				        		  	        }else{
				        		  	              parent.parent.showMessager(2,'与数据库交互异常',true,5000);
				        		  	        }
				        		  	}
				        		  });   
			             }else{
			                  qm_showQianmingDialog('onAfterAgainEditPAButton');
			             }
			             
			         }
	        
	        }
	        //保存后直接提交
	        function afterSaveSubmitAppointPA(){
	        	 var rows = $('#appointSDTable').datagrid('getSelections');
	        	 var ary = new Array();
	             for(var j=0;j<rows.length;j++){
				   var getid =  $('#appointSDTable').datagrid('getSelections')[j].id;
				   ary = ary.concat(getid);
			     }
		         var getSelections = ary.join(",");
	             $.ajax({
	        		  	url : sybp()+'/tblAppointSDAction_afterSaveSubmitAppointPA.action',
	        		  	type: 'post',
	        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	        		  	data: {
	        		  	ids:getSelections,
	        		  	},
	        		  	dataType:'json',
	        		  	success:function(r){
	        		  	        if(r.success){
		        		  	         onBackButton_selectPADialog();
		        		  	        $('#appointSDTable').datagrid('reload');
		        		  	        $('#appointSDTable').datagrid("unselectAll");
		        		  			parent.parent.showMessager(1,'提交成功',true,5000);
		        		  			 $('#onAppointPAButton').linkbutton('disable'); 
		    					     $('#onSubmitAppointPAButton').linkbutton('enable'); 
		    					     $('#onAgainEditPAButton').linkbutton('disable'); 
		        		  	    }else{
		        		  	    	parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	    }
	        		  			
	        		  	}
	        		  });             
	        }
	        
	      //显示任命窗口病理
	        function showSelectPADialog(addOrEdit){
	               var rows = $('#appointSDTable').datagrid('getSelections');
		           selectPAinputCombobox();
		        	$('#selectPAAddOrEdit').val(addOrEdit);
		               var subtable = document.getElementById('studyNoPAtable'); 
		               while (subtable.rows.length > 1){
						  if(subtable.rows.length != 1){
				                 subtable.deleteRow(subtable.rows.length-1);
				               }
						 }
					
			           for(var y = 1;y<=rows.length;y++){
						   var i=subtable.rows.length; 
						   var addrow=subtable.insertRow();//添加一行到第一行的位置行 
						   var col1=addrow.insertCell(0);//添加列 
						   col1.innerHTML=y; 
						   //  var col2=addrow.insertCell(1);//添加一行到第一列的位置行 
						   var col2=addrow.insertCell(); 
						   var j=subtable.rows[0].cells.length; //得到td的数目 
						   col2.className="table01-td01";
						   col2.innerHTML=rows[y-1].studyNo; 
					   }
		            $('#selectPADialog').dialog('open');
		            $('#selectPADialog2').dialog('open');
	        }
	        

			/**关闭病理窗口*/
			function onBackButton_selectPADialog(){
			     $('#selectPADialog').dialog('close');
			}
			
			//初始化病理下拉选
		   function selectPAinputCombobox(){
				$('#selectPAinput').combobox({
					url:sybp()+'/tblAppointSDAction_selectPAinputCombobox.action',
					valueField:'id',
					textField:'text',
					required: true,    
					onSelect: function(record){    

	 				}
				});
			}
			/**病理任命*/
			function  onAppointPAButton(){
			    showSelectPADialog('add');
			}

			function qm_cancelQianming(){
				$('#qianming').dialog('close'); 
				alert(2);
				$('#appointSDTable').datagrid('reload');
				$('#appointSDTable').datagrid("unselectAll");
			}
/****病理结束********************************************************/
