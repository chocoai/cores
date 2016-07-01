/****开始********************************************************/

	function newOneInstrument(addOrEdit){
		$('#addOrEditInstrument').val(addOrEdit);
		setStatus4(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentInstrumentDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddInstrumentLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddInstrumentButton').css('display','');
			
			$('#oneFileRecordId4').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag4').val(5);//后面也没有使用这个值，所以可以不用维护
			
			$('#instrumentId').val('');
			$('#instrumentName').val('');
			$('#instrumentModel').val('');
			$('#instrumentManufacturer').val('');
			$('#instrumentPurchaseDate').datebox('setValue','');
			
			
			$('#oneArchiveTypeCode4').val('');
			$('#oneArchiveTypeName4').val('');
			$('#oneArchiveCode4').val('');
			$('#oneArchiveCode4').attr('readOnly',false);
			$('#oneStorePosition4').combotree('setValue','');
			$('#oneArchiveTitle4').val('');
			$('#oneArchiveMaker4').combobox('setValue','');
			//$('#oneFileOperator4').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=5',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator4').val(r.last);
						}
			 	  }
			});
			$('#oneFileDate4').datebox('setValue',$('#todayDate').val());
			$('#oneRemark4').val('');
			$('#oneKeepDate4').datebox('setValue','');
			$('#oneKeyWord4').val('');
			$('#oneDestoryDate4').datebox('setValue','');
			$('#oneArchiveMedia4').val('');
			
			$('#isForValidInstrument').attr('disabled',false);
			
			$('#chooseOneArchiveTypeCode4').linkbutton('enable');
			$('#oneStorePosition4').combotree('enable');
			$('#oneArchiveTitle4').attr('readOnly',false);
			
			$('#isLong4').attr('checked',true);
			
			
			$('#AddOrEditFileContentInstrumentDialog').dialog('setTitle','添加仪器资料');
			$('#AddOrEditFileContentInstrumentDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddInstrumentLabel").innerHTML='';//连续添加不显示
			$('#continueAddInstrumentButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag4').val(5);
				
				$('#instrumentId').val(row.instrumentId);
				$('#instrumentName').val(row.instrumentName);
				$('#instrumentModel').val(row.instrumentModel);
				$('#instrumentManufacturer').val(row.instrumentManufacturer);
				$('#instrumentPurchaseDate').datebox('setValue',row.instrumentPurchaseDate);
				
				$('#oneFileRecordId4').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia4').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidInstrument').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode4').val(row.archiveTypeCode);
				$('#oneArchiveTypeName4').val(row.archiveTypeName);
				$('#oneArchiveCode4').val(row.archiveCode);
				$('#oneArchiveCode4').attr('readOnly',true);
				$('#oneStorePosition4').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle4').val(row.archiveTitle);
				$('#oneArchiveMaker4').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator4').val(row.fileOperator);
				$('#oneFileDate4').datebox('setValue',row.fileDate);
				$('#oneRemark4').val(row.remark);
				$('#oneKeepDate4').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong4').attr('checked',true);
				}else{
					$('#isLong4').attr('checked',false);
				}
				$('#oneKeyWord4').val(row.keyWord);
				$('#oneDestoryDate4').datebox('setValue',row.destoryDate);
				
				$('#isForValidInstrument').attr('disabled',true);
				$('#oneArchiveTypeCode4').attr('readOnly',true);
				$('#oneArchiveTypeName4').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode4').linkbutton('disable');
				$('#oneStorePosition4').combotree('disable');
				$('#oneArchiveTitle4').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentInstrumentDialog').dialog('setTitle','编辑仪器资料');
				$('#AddOrEditFileContentInstrumentDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddInstrumentLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddInstrumentButton').css('display','');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag4').val(5);
				
				$('#instrumentId').val('');
				$('#instrumentName').val('');
				$('#instrumentModel').val('');
				$('#instrumentManufacturer').val('');
				$('#instrumentPurchaseDate').datebox('setValue','');
				
				$('#oneFileRecordId4').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia4').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidInstrument').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode4').val(row.archiveTypeCode);
				$('#oneArchiveTypeName4').val(row.archiveTypeName);
				$('#oneArchiveCode4').val(row.archiveCode);
				$('#oneArchiveCode4').attr('readOnly',true);
				$('#oneStorePosition4').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle4').val(row.archiveTitle);
				$('#oneArchiveMaker4').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator4').val(row.fileOperator);
				$('#oneFileDate4').datebox('setValue',$('#todayDate').val());
				$('#oneRemark4').val('');
				$('#oneKeepDate4').datebox('setValue','');
				
				$('#isLong4').attr('checked',true);
				
				$('#oneKeyWord4').val('');
				$('#oneDestoryDate4').datebox('setValue','');
				
				$('#isForValidInstrument').attr('disabled',true);
				$('#oneArchiveTypeCode4').attr('readOnly',true);
				$('#oneArchiveTypeName4').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode4').linkbutton('disable');
				$('#oneStorePosition4').combotree('disable');
				$('#oneArchiveTitle4').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentInstrumentDialog').dialog('setTitle','追加仪器资料');
				$('#AddOrEditFileContentInstrumentDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddInstrumentLabel").innerHTML='';//连续添加不显示
			$('#continueAddInstrumentButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag4').val(5);
				
				$('#instrumentId').val(row.instrumentId);
				$('#instrumentName').val(row.instrumentName);
				$('#instrumentModel').val(row.instrumentModel);
				$('#instrumentManufacturer').val(row.instrumentManufacturer);
				$('#instrumentPurchaseDate').datebox('setValue',row.instrumentPurchaseDate);
				
				$('#oneFileRecordId4').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia4').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidInstrument').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode4').val(row.archiveTypeCode);
				$('#oneArchiveTypeName4').val(row.archiveTypeName);
				$('#oneArchiveCode4').val(row.archiveCode);
				$('#oneArchiveCode4').attr('readOnly',true);
				$('#oneStorePosition4').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle4').val(row.archiveTitle);
				$('#oneArchiveMaker4').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator4').val(row.fileOperator);
				$('#oneFileDate4').datebox('setValue',row.fileDate);
				$('#oneRemark4').val(row.remark);
				$('#oneKeepDate4').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong4').attr('checked',true);
				}else{
					$('#isLong4').attr('checked',false);
				}
				$('#oneKeyWord4').val(row.keyWord);
				$('#oneDestoryDate4').datebox('setValue',row.destoryDate);
				
				$('#isForValidInstrument').attr('disabled',true);
				$('#oneArchiveTypeCode4').attr('readOnly',true);
				$('#oneArchiveTypeName4').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode4').linkbutton('disable');
				$('#oneStorePosition4').combotree('disable');
				$('#oneArchiveTitle4').attr('readOnly',true);
			
				
				$('#AddOrEditFileContentInstrumentDialog').dialog('setTitle','查看仪器资料');
				$('#AddOrEditFileContentInstrumentDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus4(addOrEdit)
	{
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag4').attr('readOnly',false);
			
			$('#instrumentId').attr('readOnly',false);
			$('#instrumentName').attr('readOnly',false);
			$('#instrumentModel').attr('readOnly',false);
			$('#instrumentManufacturer').attr('readOnly',false);
			$('#instrumentPurchaseDate').datebox('enable');
			
			$('#oneFileRecordId4').attr('readOnly',false);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia4').attr('readOnly',false);
			
			
			$('#isForValidInstrument').css('display','');
			$('#isForValidInstrumentLabel').css('display','');
			
			
			$('#isLong4').attr('disabled',false);
			
			$('#oneArchiveTypeCode4').attr('readOnly',true);
			$('#oneArchiveTypeName4').attr('readOnly',true);
			$('#oneArchiveCode4').attr('readOnly',false);
			$('#oneStorePosition4').combotree('enable');
			$('#oneArchiveTitle4').attr('readOnly',false);
			$('#oneArchiveMaker4').combobox('enable');
			$('#oneFileOperator4').attr('readOnly',false);
			$('#oneFileDate4').datebox('enable');
			$('#oneRemark4').attr('readOnly',false);
			$('#oneKeepDate4').datebox('disable');
			
			$('#oneKeyWord4').attr('readOnly',false);
			$('#oneDestoryDate4').datebox('enable');
			
			$('#isForValidInstrument').attr('disabled',false);
			$('#oneArchiveTypeCode4').attr('readOnly',true);
			$('#oneArchiveTypeName4').attr('readOnly',true);
			$('#chooseOneArchiveTypeCode4').linkbutton('disable');
			$('#oneStorePosition4').combotree('disable');
			$('#oneArchiveTitle4').attr('readOnly',false);
			
			$('#oneDestoryDate4Label').css('display','none');
			$('#oneDestoryDate4Label2').css('display','none');
			$('#saveButton4').css('display','');
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag4').attr('readOnly',true);
			
			$('#instrumentId').attr('readOnly',true);
			$('#instrumentName').attr('readOnly',true);
			$('#instrumentModel').attr('readOnly',true);
			$('#instrumentManufacturer').attr('readOnly',true);
			$('#instrumentPurchaseDate').datebox('disable');
			
			$('#oneFileRecordId4').attr('readOnly',true);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia4').attr('readOnly',true);
			
			
			$('#isForValidInstrument').css('display','none');
			$('#isForValidInstrumentLabel').css('display','none');
			
			$('#oneArchiveTypeCode4').attr('readOnly',true);
			$('#oneArchiveTypeName4').attr('readOnly',true);
			$('#oneArchiveCode4').attr('readOnly',true);
			$('#oneStorePosition4').combotree('disable');
			$('#oneArchiveTitle4').attr('readOnly',true);
			$('#oneArchiveMaker4').combobox('disable');
			$('#oneFileOperator4').attr('readOnly',true);
			$('#oneFileDate4').datebox('disable');
			$('#oneRemark4').attr('readOnly',true);
			$('#oneKeepDate4').datebox('disable');
			
			$('#isLong4').attr('disabled',true);
			
			$('#oneKeyWord4').attr('readOnly',true);
			$('#oneDestoryDate4').datebox('disable');
			
			$('#isForValidInstrument').attr('disabled',true);
			$('#oneArchiveTypeCode4').attr('readOnly',true);
			$('#oneArchiveTypeName4').attr('readOnly',true);
			$('#chooseOneArchiveTypeCode4').linkbutton('enable');
			$('#oneStorePosition4').combotree('enable');
			$('#oneArchiveTitle4').attr('readOnly',true);
			
			$('#oneDestoryDate4Label').css('display','');
			$('#oneDestoryDate4Label2').css('display','');
			$('#saveButton4').css('display','none');
		}
	}
	
	function getInstrumentById()
	{
		var instrumentId=$('#instrumentId').val();
		 $.ajax({
 	 	  	url : sybp()+'/tblFileContentInstrumentAction_getInstrumentById.action?instrumentId='+instrumentId,
 		  	type: 'post',
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	//data: $('#oneTblFileContentInstrumentForm').serialize(),
 		  	dataType:'json',
 		  	success:function(r){
			 	if(r&&r.success){
			 		$('#instrumentName').val(r.instrumentName);
			 		$('#instrumentModel').val(r.instrumentModel);
			 		$('#instrumentManufacturer').val(r.instrumentManufacturer);
			 		$('#oneArchiveTitle4').val(r.instrumentName);
			 		//$('#instrumentPurchaseDate').val(r.instrumentPurchaseDate);
			 	}
		 	}
		 });
		
	}
	//保存
	function saveOneFileContentInstrument(){
		var oneArchiveTypeName = $('#oneArchiveTypeName4').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentInstrumentForm').form('validate') ){
	             var addOrEdit = $('#addOrEditInstrument').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidInstrument').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition4L').val($('#oneStorePosition4').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentInstrumentAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentInstrumentForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentInstrumentFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentInstrumentDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddInstrumentButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentInstrumentDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId4').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag4').val(5);
			        		  				
			        		  				$('#instrumentId').val('');
			        						$('#instrumentName').val('');
			        						$('#instrumentModel').val('');
			        						$('#instrumentManufacturer').val('');
			        						$('#instrumentPurchaseDate').datebox('setValue','');
			        		  				
			        		  				//$('#oneArchiveTypeCode4').val('');
			        		  				//$('#oneArchiveTypeName4').val('');
			        						$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode4').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode4').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition4').combotree('setValue','');
			        		  				$('#oneArchiveTitle4').val('');
			        		  				//$('#oneArchiveMaker4').combobox('setValue','');
			        		  				//$('#oneFileOperator4').val('');
			        		  				$('#oneFileDate4').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark4').val('');
			        		  				$('#oneKeepDate4').datebox('setValue','');
			        		  				$('#oneKeyWord4').val('');
			        		  				$('#oneDestoryDate4').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateInstrumentRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidInstrument').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentInstrumentAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentInstrumentForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentInstrumentFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentInstrumentDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddInstrumentButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentInstrumentDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId4').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag4').val(5);
			        		  				
			        		  				$('#instrumentId').val('');
			        						$('#instrumentName').val('');
			        						$('#instrumentModel').val('');
			        						$('#instrumentManufacturer').val('');
			        						$('#instrumentPurchaseDate').datebox('setValue','');
			        		  				
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
	
	function writeOperateInstrumentRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateInstrumentRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateInstrumentRecord');
				}else {
					writeOperateInstrumentRsn();
				}
			}
	 });
	}
	function afterSignUpdateInstrumentRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidInstrument').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentInstrumentAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentInstrumentForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	  //contentWind.$('#searchRecordButton').click();
   		  	    	
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentInstrumentFrame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentInstrumentDatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
   		  	    	  
   		  	    	  $('#AddOrEditFileContentInstrumentDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchInstrumentRecord(isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileContentInstrumentDatagrid').datagrid({
		    	url : sybp()+'/tblFileContentInstrumentAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileContentInstrumentDatagrid');
			$.ajax({
				url : sybp()+'/tblFileContentInstrumentAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					page:dg.datagrid('options').pageNumber,
			 		rows:dg.datagrid('options').pageSize,
				},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileContentInstrumentDatagrid').datagrid('loadData',r);
	   		  	}
		   });
		 
		 }
			 
		
	}
	
	function deleteOneInstrument() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteInstrumentRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteInstrumentRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 仪器资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateInstrumentRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteInstrumentRecord');
				}else {
					writeDeleteInstrumentRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteInstrumentRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
		
		var operateRsn = $('#operateInstrumentRsn').val();
		var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentInstrumentAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentInstrumentDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneInstrument() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			writeDestroyInstrumentRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyInstrumentRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁   仪器资料档案:'+archiveCode+' 吗？');
		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		/*$.messager.prompt('提示框','销毁是对于整个档案，确定要销毁 仪器资料档案:'+archiveCode+' 吗？如果确认销毁请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateInstrumentRsn').val(r);
					qm_showQianmingDialog('afterSignDestroyInstrumentRecord');
				}else {
					writeDestroyInstrumentRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});*/
	}
	function afterSignDestroyInstrumentRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentInstrumentFrame').contentWindow;
		//var operateRsn = $('#operateInstrumentRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		
		var row = childWind.$('#tblFileContentInstrumentDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentInstrumentAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentInstrumentDialog').dialog('close');
	   		  	    	  
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
