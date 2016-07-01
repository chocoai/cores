/****开始********************************************************/

	function newOneQA(addOrEdit){
		$('#addOrEditQA').val(addOrEdit);
		setStatus1(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentQADialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddQALabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddQAButton').css('display','');
			
			$('#oneFileRecordId1').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag1').val(2);//后面也没有使用这个值，所以可以不用维护
			
			$('#studyNoQA').val('');
			$('#checkItemName').val('');
			$('#sdnameQA').val('');
			$('#inspector').combobox('setValue','');
			
			$('#oneArchiveTypeCode1').val('');
			$('#oneArchiveTypeName1').val('');
			$('#oneArchiveCode1').val('');
			$('#oneArchiveCode1').attr('readOnly',false);
			$('#oneStorePosition1').combotree('setValue','');
			$('#oneArchiveTitle1').val('');
			$('#oneArchiveMaker1').combobox('setValue','');
			$('#oneArchiveMedia1').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=2',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator1').val(r.last);
						}
			 	  }
			});
			
			$('#oneFileDate1').datebox('setValue',$('#todayDate').val());
			$('#oneRemark1').val('');
			$('#oneKeepDate1').datebox('setValue','');
			$('#oneKeyWord1').val('');
			$('#oneDestoryDate1').datebox('setValue','');
			
			$('#isLong1').attr('checked',true);
			
			$('#isForValidQA').attr('disabled',false);
			
			$('#chooseOneArchiveTypeCode1').linkbutton('enable');
			$('#oneStorePosition1').combotree('enable');
			$('#oneArchiveTitle1').attr('readOnly',false);
			
			
			$('#AddOrEditFileContentQADialog').dialog('setTitle','添加QA检查资料');
			$('#AddOrEditFileContentQADialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddQALabel").innerHTML='';//连续添加不显示
			$('#continueAddQAButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag1').val(2);
				
				$('#studyNoQA').val(row.studyNo);
				$('#checkItemName').val(row.checkItemName);
				$('#sdnameQA').val(row.sdname);
				$('#inspector').combobox('setValue',row.inspector);
				
				$('#oneFileRecordId1').val(row.fileRecordId);
				
				$("input:radio[value="+row.checkItemType+"][name='checkItemType']").attr('checked','true'); 
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia1').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidQA').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode1').val(row.archiveTypeCode);
				$('#oneArchiveTypeName1').val(row.archiveTypeName);
				$('#oneArchiveCode1').val(row.archiveCode);
				$('#oneArchiveCode1').attr('readOnly',true);
				$('#oneStorePosition1').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle1').val(row.archiveTitle);
				$('#oneArchiveMaker1').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator1').val(row.fileOperator);
				$('#oneFileDate1').datebox('setValue',row.fileDate);
				$('#oneRemark1').val(row.remark);
				$('#oneKeepDate1').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong1').attr('checked',true);
				}else{
					$('#isLong1').attr('checked',false);
				}
				$('#oneKeyWord1').val(row.keyWord);
				$('#oneDestoryDate1').datebox('setValue',row.destoryDate);
				
				$('#isForValidQA').attr('disabled',true);
				$('#oneArchiveTypeCode1').attr('readOnly',true);
				$('#oneArchiveTypeName1').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode1').linkbutton('disable');
				$('#oneStorePosition1').combotree('disable');
				$('#oneArchiveTitle1').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentQADialog').dialog('setTitle','编辑QA检查资料');
				$('#AddOrEditFileContentQADialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddQALabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddQAButton').css('display','');
			
			//document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			//$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag1').val(1);
				
				$('#studyNoQA').val('');
				$('#checkItemName').val('');
				$('#sdnameQA').val('');
				$('#inspector').combobox('setValue','');
				
				
				$('#oneFileRecordId1').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia1').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidQA').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode1').val(row.archiveTypeCode);
				$('#oneArchiveTypeName1').val(row.archiveTypeName);
				$('#oneArchiveCode1').val(row.archiveCode);
				$('#oneArchiveCode1').attr('readOnly',true);
				$('#oneStorePosition1').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle1').val(row.archiveTitle);
				$('#oneArchiveMaker1').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator1').val(row.fileOperator);
				$('#oneFileDate1').datebox('setValue',$('#todayDate').val());
				$('#oneRemark1').val('');
				$('#oneKeepDate1').datebox('setValue','');
				
				$('#isLong1').attr('checked',true);
				
				$('#oneKeyWord1').val('');
				$('#oneDestoryDate1').datebox('setValue','');
				
				$('#isForValidQA').attr('disabled',true);
				$('#oneArchiveTypeCode1').attr('readOnly',true);
				$('#oneArchiveTypeName1').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode1').linkbutton('disable');
				$('#oneStorePosition1').combotree('disable');
				$('#oneArchiveTitle1').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentQADialog').dialog('setTitle','追加QA检查资料');
				$('#AddOrEditFileContentQADialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddQALabel").innerHTML='';//连续添加不显示
			$('#continueAddQAButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag1').val(2);
				
				$('#studyNoQA').val(row.studyNo);
				$('#checkItemName').val(row.checkItemName);
				$('#sdnameQA').val(row.sdname);
				
				$('#inspector').combobox('setValue',row.inspector);
				
				$('#oneFileRecordId1').val(row.fileRecordId);
				
				$("input:radio[value="+row.checkItemType+"][name='checkItemType']").attr('checked','true'); 
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia1').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidQA').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode1').val(row.archiveTypeCode);
				$('#oneArchiveTypeName1').val(row.archiveTypeName);
				$('#oneArchiveCode1').val(row.archiveCode);
				$('#oneArchiveCode1').attr('readOnly',true);
				$('#oneStorePosition1').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle1').val(row.archiveTitle);
				$('#oneArchiveMaker1').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator1').val(row.fileOperator);
				$('#oneFileDate1').datebox('setValue',row.fileDate);
				$('#oneRemark1').val(row.remark);
				$('#oneKeepDate1').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong1').attr('checked',true);
				}else{
					$('#isLong1').attr('checked',false);
				}
				$('#oneKeyWord1').val(row.keyWord);
				$('#oneDestoryDate1').datebox('setValue',row.destoryDate);
				
				$('#isForValidQA').attr('disabled',true);
				$('#oneArchiveTypeCode1').attr('readOnly',true);
				$('#oneArchiveTypeName1').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode1').linkbutton('disable');
				$('#oneStorePosition1').combotree('disable');
				$('#oneArchiveTitle1').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentQADialog').dialog('setTitle','查看QA检查资料');
				$('#AddOrEditFileContentQADialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus1(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag1').attr('readOnly',false);
			
			$('#studyNoQA').attr('readOnly',false);
			$('#checkItemName').attr('readOnly',false);
			$('#sdnameQA').attr('readOnly',false);
			$('#inspector').combobox('enable');
			
			$('#oneFileRecordId1').attr('readOnly',false);
			
			$("input:radio[name='checkItemType']").attr('disabled',false); 
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia1').attr('readOnly',false);
		
			$('#isForValidQA').css('display','');
			$('#isForValidQALabel').css('display','');
			
			$('#oneArchiveTypeCode1').attr('readOnly',true);
			$('#oneArchiveTypeName1').attr('readOnly',true);
			$('#oneArchiveCode1').attr('readOnly',false);
			$('#oneStorePosition1').combotree('enable');
			$('#oneArchiveTitle1').attr('readOnly',false);
			$('#oneArchiveMaker1').combobox('enable');
			$('#oneFileOperator1').attr('readOnly',false);
			$('#oneFileDate1').datebox('enable');
			$('#oneRemark1').attr('readOnly',false);
			$('#oneKeepDate1').datebox('disable');
			
			$('#oneKeyWord1').attr('readOnly',false);
			$('#oneDestoryDate1').datebox('enable');
			
			$('#isForValidQA').attr('disabled',false);
			$('#chooseOneArchiveTypeCode1').linkbutton('enable');
			
			$('#isLong1').attr('disabled',false);
			
			$('#oneDestoryDate1Label').css('display','none');
			$('#oneDestoryDate1Label2').css('display','none');
			$('#saveButton1').css('display','');
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag1').attr('readOnly',true);
			
			$('#studyNoQA').attr('readOnly',true);
			$('#checkItemName').attr('readOnly',true);
			$('#sdnameQA').attr('readOnly',true);
			$('#inspector').combobox('disable');
			
			$('#oneFileRecordId1').attr('readOnly',true);
			
			$("input:radio[name='checkItemType']").attr('disabled',true); 
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia1').attr('readOnly',true);
		
			$('#isForValidQA').css('display','none');
			$('#isForValidQALabel').css('display','none');
			
			$('#oneArchiveTypeCode1').attr('readOnly',true);
			$('#oneArchiveTypeName1').attr('readOnly',true);
			$('#oneArchiveCode1').attr('readOnly',true);
			$('#oneStorePosition1').combotree('disable');
			$('#oneArchiveTitle1').attr('readOnly',true);
			$('#oneArchiveMaker1').combobox('disable');
			$('#oneFileOperator1').attr('readOnly',true);
			$('#oneFileDate1').datebox('disable');
			$('#oneRemark1').attr('readOnly',true);
			$('#oneKeepDate1').datebox('disable');
			
			$('#oneKeyWord1').attr('readOnly',true);
			$('#oneDestoryDate1').datebox('disable');
			
			$('#isLong1').attr('disabled',true);
			
			$('#isForValidQA').attr('disabled',true);
			$('#chooseOneArchiveTypeCode1').linkbutton('disable');
		
			$('#oneDestoryDate1Label').css('display','');
			$('#oneDestoryDate1Label2').css('display','');
			$('#saveButton1').css('display','none');
		}
		
	}
	
	function getStudyNameQA()
	{
		var studyNo=$('#studyNoQA').val();
		var checkItemType = $('input[name="checkItemType"]:checked').val();
		 $.ajax({
 	 	  	url : sybp()+'/tblFileContentStudyAction_getStudyNameByStudyNo.action?studyNo='+studyNo+'&studyNoType='+checkItemType,
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	//data: $('#oneTblFileContentForm').serialize(),
 		  	dataType:'json',
 		  	success:function(r){
 		  	      if(r&&r.success){
 		  	    	  $('#checkItemName').val(r.studyNoName);
 		  	    	  $('#sdnameQA').val(r.SDName);
 		  	    	  $('#inspector').combobox('setValue',r.qa);
 		  	    	  if($('#addOrEditQA').val()==1)
 		  	    	  {
 		  	    		  $('#oneArchiveTitle1').val(r.studyNoName);
 		  	    	  }
 		  	    	  if($('#oneFileOperator1').val()==''){
 		  	    		$('#oneFileOperator1').val(r.qa);
 		  	    	  }
 		  	        }else if(!r.success){
 		  	        	if(checkItemType=='1')
 		  	        	{
 		  	        		$.messager.alert('提示','该专题不存在','info');
 		  	        	}else if(checkItemType=='2')
 		  	        	{
 		  	        		$.messager.alert('提示','该合同不存在','info');
 		  	        	}
 		  	        }else{
 		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
 		  	        }
 		  	}
 		  });   
		
		
	}
	
	//保存
	function saveOneFileContentQA(){
		var oneArchiveTypeName = $('#oneArchiveTypeName1').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentQAForm').form('validate') ){
	             var addOrEdit = $('#addOrEditQA').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidQA').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition1L').val($('#oneStorePosition1').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentQACheckAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentQAForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentQAFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentQADatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddQAButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentQADialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId1').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag1').val(2);
			        		  				
			        		  				$('#studyNoQA').val('');
			        		  				$('#checkItemName').val('');
			        		  				$('#sdnameQA').val('');
			        		  				$('#inspector').combobox('setValue','');
			        		  				
			        		  				//$('#oneArchiveTypeCode1').val('');
			        		  				//$('#oneArchiveTypeName1').val('');
			        		  				$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode1').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode1').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition1').combotree('setValue','');
			        		  				$('#oneArchiveTitle1').val('');
			        		  				//$('#oneArchiveMaker1').combobox('setValue','');
			        		  				//$('#oneFileOperator1').val('');
			        		  				$('#oneFileDate1').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark1').val('');
			        		  				$('#oneKeepDate1').datebox('setValue','');
			        		  				$('#oneKeyWord1').val('');
			        		  				$('#oneDestoryDate1').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateQARsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidQA').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentQACheckAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentQAForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentQAFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentQADatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddQAButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentQADialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId1').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag').val(2);
			        		  				
			        		  				$('#studyNoQA').val('');
			        		  				$('#checkItemName').val('');
			        		  				$('#sdnameQA').val('');
			        		  				$('#inspector').combobox('setValue','');
			        		  				
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
	
	function writeOperateQARsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateQARsn').val(r);
					qm_showQianmingDialog('afterSignUpdateQARecord');
				}else {
					writeOperateRsn();
				}
			}
	 });
	}
	function afterSignUpdateQARecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidQA').attr('checked');
        if(isValidate=='checked')
        {
       	 validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentQACheckAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentQAForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	//  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	 // contentWind.$('#searchRecordButton').click();
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentQAFrame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentQADatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
		  	    	
   		  	    	  $('#AddOrEditFileContentQADialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchQARecord(checkItemType,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		//alert(NoType+"=="+isFileDate+"=="+start+"=="+end+"=="+keepEndDate+"=="+isDestory+"=="+isValid+"=="+searchString);
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileContentQADatagrid').datagrid({
		    		//fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString
				url : sybp()+'/tblFileContentQACheckAction_loadList.action?checkItemType='+checkItemType+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileContentQADatagrid');
			$.ajax({
				url : sybp()+'/tblFileContentQACheckAction_loadList.action?checkItemType='+checkItemType+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					page:dg.datagrid('options').pageNumber,
			 		rows:dg.datagrid('options').pageSize,
				},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileContentQADatagrid').datagrid('loadData',r);
	   		  	}
		   });
		 }
		
	}
	
	function deleteOneQA() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteQARsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteQARsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 QA档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateQARsn').val(r);
					qm_showQianmingDialog('afterSignDeleteQARecord');
				}else {
					writeDeleteQARsn(archiveCode,fileRecordSn);
				}
			}
			
			
		});
	}
	function afterSignDeleteQARecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
		
		var operateRsn = $('#operateQARsn').val();
		var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentQACheckAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentQADialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneQA() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			writeDestroyQARsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyQARsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁 QA档案:'+archiveCode+' 吗？');
		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		/*$.messager.prompt('提示框',,function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					
				}else {
					writeDestroyQARsn(archiveCode,fileRecordSn);
				}
			}
			
			
		});*/
	}
	function afterSignDestroyQARecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentQAFrame').contentWindow;
		
		//var operateRsn = $('#operateQARsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		var row = childWind.$('#tblFileContentQADatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentQACheckAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentQADialog').dialog('close');
	   		  	    	  
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
