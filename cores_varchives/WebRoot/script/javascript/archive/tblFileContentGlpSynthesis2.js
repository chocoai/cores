/****开始********************************************************/

	function newOneGlpSynthesis2(addOrEdit){
		$('#addOrEditGlpSynthesis2').val(addOrEdit);
		setStatus10(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentGlpSynthesis2Dialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddGlpSynthesis2Label").innerHTML='连续添加';//连续添加显示
			$('#continueAddGlpSynthesis2Button').css('display','');
			
			$('#oneFileRecordId10').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag10').val(4);//后面也没有使用这个值，所以可以不用维护
			
			$('#department2').combobox('setValue','');
			$('#docName2').val('');
			
			$('#oneArchiveTypeCode10').val('');
			$('#oneArchiveTypeName10').val('');
			$('#oneArchiveCode10').val('');
			$('#oneArchiveCode10').attr('readOnly',false);
			$('#oneStorePosition10').combotree('setValue','');
			$('#oneArchiveTitle10').val('');
			$('#oneArchiveMaker10').combobox('setValue','');
			//$('#oneFileOperator10').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=4',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator10').val(r.last);
						}
			 	  }
			});
			$('#oneFileDate10').datebox('setValue',$('#todayDate').val());
			$('#oneRemark10').val('');
			$('#oneKeepDate10').datebox('setValue','');
			$('#oneKeyWord10').val('');
			$('#oneDestoryDate10').datebox('setValue','');
			
			$('#isLong10').attr('checked',true);
			
			$('#isForValidGlpSynthesis2').attr('disabled',false);
			
			$('#chooseOneArchiveTypeCode10').linkbutton('enable');
			$('#oneStorePosition10').combotree('enable');
			$('#oneArchiveTitle10').attr('readOnly',false);
			$('#oneArchiveMedia10').val('');
			
			$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('setTitle','添加基建资料');
			$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddGlpSynthesis2Label").innerHTML='';//连续添加不显示
			$('#continueAddGlpSynthesis2Button').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
			
			var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag10').val(4);
				
				$('#department2').combobox('setValue',row.departmentId);
				$('#docName2').val(row.docName);
				
				$('#oneFileRecordId10').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia10').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidGlpSynthesis2').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode10').val(row.archiveTypeCode);
				$('#oneArchiveTypeName10').val(row.archiveTypeName);
				$('#oneArchiveCode10').val(row.archiveCode);
				$('#oneArchiveCode10').attr('readOnly',true);
				$('#oneStorePosition10').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle10').val(row.archiveTitle);
				$('#oneArchiveMaker10').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator10').val(row.fileOperator);
				$('#oneFileDate10').datebox('setValue',row.fileDate);
				$('#oneRemark10').val(row.remark);
				$('#oneKeepDate10').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong10').attr('checked',true);
				}else{
					$('#isLong10').attr('checked',false);
				}
				$('#oneKeyWord10').val(row.keyWord);
				$('#oneDestoryDate10').datebox('setValue',row.destoryDate);
				
				$('#isForValidGlpSynthesis2').attr('disabled',true);
				$('#oneArchiveTypeCode10').attr('readOnly',true);
				$('#oneArchiveTypeName10').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode10').linkbutton('disable');
				$('#oneStorePosition10').combotree('disable');
				$('#oneArchiveTitle10').attr('readOnly',true);
				
				
				
				$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('setTitle','编辑基建资料');
				$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddGlpSynthesis2Label").innerHTML='连续追加';//连续添加显示
			$('#continueAddGlpSynthesis2Button').css('display','');
			
			//document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			//$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
			
			var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag10').val(4);
				
				$('#department2').combobox('setValue','');
				$('#docName2').val('');
				
				$('#oneFileRecordId10').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia10').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidGlpSynthesis2').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode10').val(row.archiveTypeCode);
				$('#oneArchiveTypeName10').val(row.archiveTypeName);
				$('#oneArchiveCode10').val(row.archiveCode);
				$('#oneArchiveCode10').attr('readOnly',true);
				$('#oneStorePosition10').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle10').val(row.archiveTitle);
				$('#oneArchiveMaker10').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator10').val(row.fileOperator);
				$('#oneFileDate10').datebox('setValue',$('#todayDate').val());
				$('#oneRemark10').val('');
				$('#oneKeepDate10').datebox('setValue','');
			
				$('#isLong10').attr('checked',true);
				
				$('#oneKeyWord10').val('');
				$('#oneDestoryDate10').datebox('setValue','');
				
				$('#isForValidGlpSynthesis2').attr('disabled',true);
				$('#oneArchiveTypeCode10').attr('readOnly',true);
				$('#oneArchiveTypeName10').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode10').linkbutton('disable');
				$('#oneStorePosition10').combotree('disable');
				$('#oneArchiveTitle10').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('setTitle','追加基建资料');
				$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddGlpSynthesis2Label").innerHTML='';//连续添加不显示
			$('#continueAddGlpSynthesis2Button').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
			
			var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag10').val(4);
				
				$('#department2').combobox('setValue',row.departmentId);
				$('#docName2').val(row.docName);
				
				$('#oneFileRecordId10').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia10').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidGlpSynthesis2').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode10').val(row.archiveTypeCode);
				$('#oneArchiveTypeName10').val(row.archiveTypeName);
				$('#oneArchiveCode10').val(row.archiveCode);
				$('#oneArchiveCode10').attr('readOnly',true);
				$('#oneStorePosition10').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle10').val(row.archiveTitle);
				$('#oneArchiveMaker10').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator10').val(row.fileOperator);
				$('#oneFileDate10').datebox('setValue',row.fileDate);
				$('#oneRemark10').val(row.remark);
				$('#oneKeepDate10').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong10').attr('checked',true);
				}else{
					$('#isLong10').attr('checked',false);
				}
				$('#oneKeyWord10').val(row.keyWord);
				$('#oneDestoryDate10').datebox('setValue',row.destoryDate);
				
				$('#isForValidGlpSynthesis2').attr('disabled',true);
				$('#oneArchiveTypeCode10').attr('readOnly',true);
				$('#oneArchiveTypeName10').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode10').linkbutton('disable');
				$('#oneStorePosition10').combotree('disable');
				$('#oneArchiveTitle10').attr('readOnly',true);
			
				
				$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('setTitle','查看基建资料');
				$('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus10(addOrEdit){
		
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag10').attr('readOnly',false);
			
			$('#department2').combobox('enable');
			$('#docName2').attr('readOnly',false);
			
			$('#oneFileRecordId10').attr('readOnly',false);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia10').attr('readOnly',false);
			
			$('#isForValidGlpSynthesis2').css('display','');
			$('#isForValidGlpSynthesis2Label').css('display','');
			
			
			$('#oneArchiveTypeCode10').attr('readOnly',true);
			$('#oneArchiveTypeName10').attr('readOnly',true);
			$('#oneArchiveCode10').attr('readOnly',false);
			$('#oneStorePosition10').combotree('enable');
			$('#oneArchiveTitle10').attr('readOnly',false);
			$('#oneArchiveMaker10').combobox('enable');
			$('#oneFileOperator10').attr('readOnly',false);
			$('#oneFileDate10').datebox('enable');
			$('#oneRemark10').attr('readOnly',false);
			$('#oneKeepDate10').datebox('disable');
			
			$('#oneKeyWord10').attr('readOnly',false);
			$('#oneDestoryDate10').datebox('enable');
			
			$('#chooseOneArchiveTypeCode10').linkbutton('enable');
			
			$('#isLong10').attr('disabled',false);
			
			$('#oneDestoryDate10Label').css('display','none');
			$('#oneDestoryDate10Label2').css('display','none');
			$('#saveButton10').css('display','');
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag10').attr('readOnly',true);
			
			$('#department2').combobox('disable');
			$('#docName2').attr('readOnly',true);
			
			$('#oneFileRecordId10').attr('readOnly',true);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia10').attr('readOnly',true);
			
			
			$('#isForValidGlpSynthesis2').css('display','none');
			$('#isForValidGlpSynthesis2Label').css('display','none');
			
			$('#oneArchiveTypeCode10').attr('readOnly',true);
			$('#oneArchiveTypeName10').attr('readOnly',true);
			$('#oneArchiveCode10').attr('readOnly',true);
			$('#oneStorePosition10').combotree('disable');
			$('#oneArchiveTitle10').attr('readOnly',true);
			$('#oneArchiveMaker10').combobox('disable');
			$('#oneFileOperator10').attr('readOnly',true);
			$('#oneFileDate10').datebox('disable');
			$('#oneRemark10').attr('readOnly',true);
			$('#oneKeepDate10').datebox('disable');
			
			$('#oneKeyWord10').attr('readOnly',true);
			$('#oneDestoryDate10').datebox('disable');
			
			$('#isForValidGlpSynthesis2').attr('disabled',true);
			
			$('#chooseOneArchiveTypeCode10').linkbutton('disable');
			
			$('#isLong10').attr('disabled',true);
			
			$('#oneDestoryDate10Label').css('display','');
			$('#oneDestoryDate10Label2').css('display','');
			$('#saveButton10').css('display','none');
		}
		
	}
	
	//保存
	function saveOneFileContentGlpSynthesis2(){
		var oneArchiveTypeName = $('#oneArchiveTypeName10').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentGlpSynthesis2Form').form('validate') ){
	             var addOrEdit = $('#addOrEditGlpSynthesis2').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidGlpSynthesis2').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition10L').val($('#oneStorePosition10').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentGlpSynthesis2Action_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentGlpSynthesis2Form').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentGlpSynthesis2Datagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddGlpSynthesis2Button').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId10').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag10').val(4);
			        		  				
			        		  				$('#department2').combobox('setValue','');
			        		  				$('#docName2').val('');
			        		  				
			        		  				//$('#oneArchiveTypeCode10').val('');
			        		  				//$('#oneArchiveTypeName10').val('');
			        		  				$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode10').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode10').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition10').combotree('setValue','');
			        		  				$('#oneArchiveTitle10').val('');
			        		  				//$('#oneArchiveMaker10').combobox('setValue','');
			        		  				//$('#oneFileOperator10').val('');
			        		  				$('#oneFileDate10').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark10').val('');
			        		  				$('#oneKeepDate10').datebox('setValue','');
			        		  				$('#oneKeyWord10').val('');
			        		  				$('#oneDestoryDate10').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateGlpSynthesisRsn2();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidGlpSynthesis2').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentGlpSynthesis2Action_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentGlpSynthesis2Form').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentGlpSynthesis2Datagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddGlpSynthesis2Button').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId10').val('');
			        		  				////1：专题；2：QA检查资料；10：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag10').val(4);
			        		  				
			        		  				$('#department2').combobox('setValue','');
			        		  				$('#docName2').val('');
			        		  				
			        		  			//	$('#oneArchiveTypeCode1').val('');
			        		  			//	$('#oneArchiveTypeName1').val('');
			        		  			//	$('#oneArchiveCode1').val('');
			        		  			//	$('#oneStorePosition1').val('');
			        		  			//	$('#oneArchiveTitle1').val('');
			        		  			//	$('#oneArchiveMaker1').combobox('setValue','');
			        		  			//	$('#oneFileOperator1').combobox('setValue','');
			        		  			//	$('#oneFileDate1').datebox('setValue','');
			        		  			//	$('#oneRemark1').val('');
			        		  			//	$('#oneKeepDate1').datebox('setValue','');
			        		  			//	$('#oneKeyWord1').val('');
			        		  			//	$('#oneDestoryDate1').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	            	 
	            	 
	             }
	             
	         }
		}else{
			$.messager.alert('提示框','请选择档案分类代号');
		}
	  
	}
	
	function writeOperateGlpSynthesisRsn2()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateGlpSynthesisRsn2').val(r);
					qm_showQianmingDialog('afterSignUpdateGlpSynthesis2Record');
				}else {
					writeOperateGlpSynthesisRsn2();
				}
			}
	 });
	}
	function afterSignUpdateGlpSynthesis2Record()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidGlpSynthesis2').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentGlpSynthesis2Action_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentGlpSynthesis2Form').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	  //contentWind.$('#searchRecordButton').click();
   		  	    	
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentGlpSynthesis2Datagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
		  	    	
   		  	    	  $('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchGlpSynthesis2Record(departmentLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		//alert(NoType+"=="+isFileDate+"=="+start+"=="+end+"=="+keepEndDate+"=="+isDestory+"=="+isValid+"=="+searchString);
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid({
		    	url : sybp()+'/tblFileContentGlpSynthesisAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileContentGlpSynthesis2Datagrid');
			$.ajax({
				url : sybp()+'/tblFileContentGlpSynthesis2Action_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					department:departmentLeft,
					page:dg.datagrid('options').pageNumber,
			 		rows:dg.datagrid('options').pageSize,
				},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('loadData',r);
	   		  	}
		   });
		 }
		
	}
	
	function deleteOneGlpSynthesis2() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
		
		var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteGlpSynthesisRsn2(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteGlpSynthesisRsn2(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 综合资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateGlpSynthesisRsn2').val(r);
					qm_showQianmingDialog('afterSignDeleteGlpSynthesis2Record');
				}else {
					writeDeleteGlpSynthesisRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteGlpSynthesis2Record()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
		
		var operateRsn = $('#operateGlpSynthesisRsn2').val();
		var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentGlpSynthesis2Action_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneGlpSynthesis2() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
		
		var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDestroyGlpSynthesisRsn2(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyGlpSynthesisRsn2(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁   基建资料档案:'+archiveCode+' 吗？');
		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		/*
		$.messager.prompt('提示框','销毁是对于整个档案，确定要销毁 综合资料档案:'+archiveCode+' 吗？如果确认销毁请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateGlpSynthesisRsn').val(r);
					qm_showQianmingDialog('afterSignDestroyGlpSynthesisRecord');
				}else {
					writeDestroyGlpSynthesisRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});*/
	}
	function afterSignDestroyGlpSynthesis2Record()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesis2Frame').contentWindow;
		
		//var operateRsn = $('#operateGlpSynthesisRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		var row = childWind.$('#tblFileContentGlpSynthesis2Datagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentGlpSynthesis2Action_destroy.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
			 		operateRsn:operateRsn,
			 		destoryDate:destoryDate,
		 		},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentGlpSynthesis2Dialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
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
	
	
/****结束********************************************************/
