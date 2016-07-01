/****开始********************************************************/

	function newOneAdministration(addOrEdit){
		$('#addOrEditAdministration').val(addOrEdit);
		setStatus6(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentAdministrationDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddAdministrationLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddAdministrationButton').css('display','');
			
			$('#oneFileRecordId6').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag6').val(7);//后面也没有使用这个值，所以可以不用维护
			
			$('#docTypeFlag').combobox('setValue','');
			$('#docTypeName').val('');
			$('#docName2').val('');
			$('#docCode').val('');
			$('#dispatchUnit').val('');
			$('#dispatchDate').datebox('setValue','');
			$('#receiptMan').combobox('setValue','');
			$('#receiptDate').datebox('setValue','');
			
			
			$('#oneArchiveTypeCode6').val('');
			$('#oneArchiveTypeName6').val('');
			$('#oneArchiveCode6').val('');
			$('#oneArchiveCode6').attr('readOnly',false);
			$('#oneStorePosition6').combotree('setValue','');
			$('#oneArchiveTitle6').val('');
			$('#oneArchiveMaker6').combobox('setValue','');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=7',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator6').val(r.last);
						}
			 	  }
			});
			//$('#oneFileOperator6').val('');
			$('#oneFileDate6').datebox('setValue',$('#todayDate').val());
			$('#oneRemark6').val('');
			$('#oneKeepDate6').datebox('setValue','');
			
			$('#isLong6').attr('checked',true);
			
			$('#oneKeyWord6').val('');
			$('#oneDestoryDate6').datebox('setValue','');
			$('#oneArchiveMedia6').val('');
			
			$('#isForValidAdministration').attr('disabled',false);
			$('#chooseOneArchiveTypeCode6').linkbutton('enable');
			$('#oneStorePosition6').combotree('enable');
			$('#oneArchiveTitle6').attr('readOnly',false);
			
			
			$('#AddOrEditFileContentAdministrationDialog').dialog('setTitle','添加行政综合资料');
			$('#AddOrEditFileContentAdministrationDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddAdministrationLabel").innerHTML='';//连续添加不显示
			$('#continueAddAdministrationButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag6').val(7);
				
				$('#docTypeFlag').combobox('setValue',row.docTypeFlag);
				$('#docTypeName').val(row.docTypeName);
				$('#docName2').val(row.docName);
				$('#docCode').val(row.docCode);
				$('#dispatchUnit').val(row.dispatchUnit);
				$('#dispatchDate').datebox('setValue',row.dispatchDate);
				$('#receiptMan').combobox('setValue',row.receiptMan);
				$('#receiptDate').datebox('setValue',row.receiptDate);
				
				$('#oneFileRecordId6').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia6').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidAdministration').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode6').val(row.archiveTypeCode);
				$('#oneArchiveTypeName6').val(row.archiveTypeName);
				$('#oneArchiveCode6').val(row.archiveCode);
				$('#oneArchiveCode6').attr('readOnly',true);
				$('#oneStorePosition6').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle6').val(row.archiveTitle);
				$('#oneArchiveMaker6').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator6').val(row.fileOperator);
				$('#oneFileDate6').datebox('setValue',row.fileDate);
				$('#oneRemark6').val(row.remark);
				$('#oneKeepDate6').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong6').attr('checked',true);
				}else{
					$('#isLong6').attr('checked',false);
				}
				$('#oneArchiveMedia6').val(row.archiveMedia);
				
				$('#oneKeyWord6').val(row.keyWord);
				$('#oneDestoryDate6').datebox('setValue',row.destoryDate);
				
				$('#isForValidAdministration').attr('disabled',true);
				$('#oneArchiveTypeCode6').attr('readOnly',true);
				$('#oneArchiveTypeName6').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode6').linkbutton('disable');
				$('#oneStorePosition6').combotree('disable');
				$('#oneArchiveTitle6').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentAdministrationDialog').dialog('setTitle','编辑行政综合资料');
				$('#AddOrEditFileContentAdministrationDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddAdministrationLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddAdministrationButton').css('display','');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag6').val(7);
				
				$('#docTypeFlag').combobox('setValue','');
				$('#docTypeName').val('');
				$('#docName2').val('');
				$('#docCode').val('');
				$('#dispatchUnit').val('');
				$('#dispatchDate').datebox('setValue','');
				$('#receiptMan').combobox('setValue','');
				$('#receiptDate').datebox('setValue','');
				
				$('#oneFileRecordId6').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia6').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidAdministration').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode6').val(row.archiveTypeCode);
				$('#oneArchiveTypeName6').val(row.archiveTypeName);
				$('#oneArchiveCode6').val(row.archiveCode);
				$('#oneArchiveCode6').attr('readOnly',true);
				$('#oneStorePosition6').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle6').val(row.archiveTitle);
				$('#oneArchiveMaker6').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator6').val(row.fileOperator);
				$('#oneFileDate6').datebox('setValue',$('#todayDate').val());
				$('#oneRemark6').val('');
				$('#oneKeepDate6').datebox('setValue','');
				
				$('#isLong6').attr('checked',true);
				
				$('#oneArchiveMedia6').val('');
				
				$('#oneKeyWord6').val('');
				$('#oneDestoryDate6').datebox('setValue','');
				
				$('#isForValidAdministration').attr('disabled',true);
				$('#oneArchiveTypeCode6').attr('readOnly',true);
				$('#oneArchiveTypeName6').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode6').linkbutton('disable');
				$('#oneStorePosition6').combotree('disable');
				$('#oneArchiveTitle6').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentAdministrationDialog').dialog('setTitle','追加行政综合资料');
				$('#AddOrEditFileContentAdministrationDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddAdministrationLabel").innerHTML='';//连续添加不显示
			$('#continueAddAdministrationButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				
				$('#docTypeFlag').combobox('setValue',row.docTypeFlag);
				$('#docTypeName').val(row.docTypeName);
				$('#docName2').val(row.docName);
				$('#docCode').val(row.docCode);
				$('#dispatchUnit').val(row.dispatchUnit);
				$('#dispatchDate').datebox('setValue',row.dispatchDate);
				$('#receiptMan').combobox('setValue',row.receiptMan);
				$('#receiptDate').datebox('setValue',row.receiptDate);
				
				$('#oneFileRecordId6').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia6').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidAdministration').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode6').val(row.archiveTypeCode);
				$('#oneArchiveTypeName6').val(row.archiveTypeName);
				$('#oneArchiveCode6').val(row.archiveCode);
				$('#oneArchiveCode6').attr('readOnly',true);
				$('#oneStorePosition6').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle6').val(row.archiveTitle);
				$('#oneArchiveMaker6').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator6').val(row.fileOperator);
				$('#oneFileDate6').datebox('setValue',row.fileDate);
				$('#oneRemark6').val(row.remark);
				$('#oneKeepDate6').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong6').attr('checked',true);
				}else{
					$('#isLong6').attr('checked',false);
				}
				$('#oneArchiveMedia6').val(row.archiveMedia);
				
				$('#oneKeyWord6').val(row.keyWord);
				$('#oneDestoryDate6').datebox('setValue',row.destoryDate);
				
				$('#isForValidAdministration').attr('disabled',true);
				$('#oneArchiveTypeCode6').attr('readOnly',true);
				$('#oneArchiveTypeName6').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode6').linkbutton('disable');
				$('#oneStorePosition6').combotree('disable');
				$('#oneArchiveTitle6').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentAdministrationDialog').dialog('setTitle','查看行政综合资料');
				$('#AddOrEditFileContentAdministrationDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus6(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			
			//-------------------------------------------------------------
			$('#isForValidAdministration').css('display','');
			$('#isForValidAdministrationLabel').css('display','');
			
			$('#oneArchiveMaker6').attr('disabled',false);
			$('#oneFileOperator6').attr('disabled',false);
			$('#oneFileDate6').datebox('enable');
			$('#oneRemark6').attr('readOnly',false);
			$('#oneKeepDate6').datebox('disable');
			$('#oneKeyWord6').attr('readOnly',false);
			$('#oneDestoryDate6').datebox('disable');
			$('#oneArchiveMedia6').attr('readOnly',false);
			
			$('#oneDestoryDate6Label').css('display','none');
			$('#oneDestoryDate6Label2').css('display','none');
			$('#saveButton6').css('display','');
			
			$('#docTypeFlag').combobox('enable');
			$('#docTypeName').attr('readOnly',false);
			$('#docName2').attr('readOnly',false);
			$('#docCode').attr('readOnly',false);
			$('#dispatchUnit').attr('readOnly',false);
			$('#dispatchDate').datebox('enable');
			$('#receiptMan').combobox('enable');
			$('#receiptDate').datebox('enable');
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia6').attr('readOnly',false);
			
			
			$('#isLong6').attr('disabled',false);
			
			
			$('#oneArchiveTypeName6').attr('readOnly',true);
			
		}else if(addOrEdit==4){
			
			//-------------------------------------------------------------
			$('#isForValidAdministration').css('display','none');
			$('#isForValidAdministrationLabel').css('display','none');
			
			$('#oneArchiveMaker6').attr('disabled',true);
			$('#oneFileOperator6').attr('disabled',true);
			$('#oneFileDate6').datebox('disable');
			$('#oneRemark6').attr('readOnly',true);
			$('#oneKeepDate6').datebox('disable');
			$('#oneKeyWord6').attr('readOnly',true);
			$('#oneDestoryDate6').datebox('disable');
			$('#oneArchiveMedia6').attr('readOnly',true);
			
			$('#oneDestoryDate6Label').css('display','');
			$('#oneDestoryDate6Label2').css('display','');
			$('#saveButton6').css('display','none');
			
			$('#docTypeFlag').combobox('disable');
			$('#docTypeName').attr('readOnly',true);
			$('#docName2').attr('readOnly',true);
			$('#docCode').attr('readOnly',true);
			$('#dispatchUnit').attr('readOnly',true);
			$('#dispatchDate').datebox('disable');
			$('#receiptMan').combobox('disable');
			$('#receiptDate').datebox('disable');
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia6').attr('readOnly',true);
			
			
			$('#isLong6').attr('disabled',true);
			
			$('#oneArchiveTypeName6').attr('readOnly',true);
		}
		
	}

	
	//保存
	function saveOneFileContentAdministration(){
		var oneArchiveTypeName = $('#oneArchiveTypeName6').val();
		if(oneArchiveTypeName!='')
		{
			
			if( $('#oneTblFileContentAdministrationForm').form('validate') ){
			    if($('#isLong6').attr('checked')!='checked'&&$('#oneKeepDate6').datebox('getValue')=='')
				{
			    	$.messager.alert('提示框','请选择保存期限！');
				}else {
		             var addOrEdit = $('#addOrEditAdministration').val();
		            
		             if(addOrEdit == '1'){//新建
		            	 var validationFlag=0;
		            	 var isValidate = $('#isForValidAdministration').attr('checked');
		                 if(isValidate=='checked')
		                 {
		                	 validationFlag=1;
		                 }
		                 $('#oneStorePosition6L').val($('#oneStorePosition6').combotree('getText'));
			             $.ajax({
			        	 	  	url : sybp()+'/tblFileContentAdministrationAction_save.action?validationFlag='+validationFlag,
			        		  	type: 'post',
			        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  	data: $('#oneTblFileContentAdministrationForm').serialize(),
			        		  	dataType:'json',
			        		  	success:function(r){
			        		  	      if(r.success){
				        		  	      //添加datagrid
			        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
			        		  	    		
			        		  	    //	});
			        		  	    	  //r.fileRecordId
			        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
			        		  	    	//  contentWind.$('#searchRecordButton').click();
			        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
			        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentAdministrationFrame').contentWindow
			        		  	    	var dg = studyFrame.$('#tblFileContentAdministrationDatagrid');
			        		  	    	dg.datagrid('insertRow',{
			        		  	    		index:0,
			        		  	    		row:r.record,
			        		  	    	});
			        		  	    	dg.datagrid('selectRow',0);
			        		  	    	
			        		  	    	  if($('#continueAddAdministrationButton').attr('checked')!='checked')
			        		  	    	  {
			        		  	    		  $('#AddOrEditFileContentAdministrationDialog').dialog('close');
			        		  	    	  }else {
				        		  	    		$('#oneFileRecordId6').val('');
				        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				        		  				$('#oneArchiveTypeFlag6').val(7);
				        		  				
				        		  				$('#docTypeFlag').combobox('setValue','');
				        		  				$('#docTypeName').val('');
				        		  				$('#docName2').val('');
				        		  				$('#docCode').val('');
				        		  				$('#dispatchUnit').val('');
				        		  				$('#dispatchDate').datebox('setValue','');
				        		  				$('#receiptMan').combobox('setValue','');
				        		  				$('#receiptDate').datebox('setValue','');
				        		  				
				        		  				//$('#oneArchiveTypeCode6').val('');
				        		  				//$('#oneArchiveTypeName6').val('');
				        		  				$.ajax({
				        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode6').val(),
				        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				        		  		 		  	dataType:'json',
				        		  		 		  	success:function(r){
				        		  		 		  	      if(r&&r.archiveCode){
				        		  		 		  	    	 $('#oneArchiveCode6').val(r.archiveCode);//+1
				        		  		 		  	      }
				        		  		 		  	}
				        		  		 		  });   
				        		  				
				        		  				
				        		  				$('#oneStorePosition6').combotree('setValue','');
				        		  				$('#oneArchiveTitle6').val('');
				        		  				//$('#oneArchiveMaker6').combobox('setValue','');
				        		  				//$('#oneFileOperator6').val('');
				        		  			//	$('#oneFileDate6').datebox('setValue','');
				        		  				$('#oneRemark6').val('');
				        		  				$('#oneKeepDate6').datebox('setValue','');
				        		  				$('#oneKeyWord6').val('');
				        		  				$('#oneDestoryDate6').datebox('setValue','');
			        		  	    	  }
			        		  	        }else if(!r.success){
			        		  	            $.messager.alert('提示',r.msg,'info');
			        		  	        }else{
			        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
			        		  	        }
			        		  	}
			        		  });   
		             }else if(addOrEdit=='2'){//编辑
		            	
		            	 writeOperateAdministrationRsn();
		            	 
		             }else if(addOrEdit=='3'){//追加归档
		            	 var validationFlag=0;
		            	 var isValidate = $('#isForValidAdministration').attr('checked');
		                 if(isValidate=='checked')
		                 {
		                	 validationFlag=1;
		                 }
			            
			             $.ajax({
			        	 	  	url : sybp()+'/tblFileContentAdministrationAction_appendSave.action?validationFlag='+validationFlag,
			        		  	type: 'post',
			        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  	data: $('#oneTblFileContentAdministrationForm').serialize(),
			        		  	dataType:'json',
			        		  	success:function(r){
			        		  	      if(r.success){
				        		  	      //添加datagrid
			        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
			        		  	    		
			        		  	    //	});
			        		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
			        		  	    	 //contentWind.$('#searchRecordButton').click();
			        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
			        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentAdministrationFrame').contentWindow
			        		  	    	var dg = studyFrame.$('#tblFileContentAdministrationDatagrid');
			        		  	    	dg.datagrid('insertRow',{
			        		  	    		index:0,
			        		  	    		row:r.record,
			        		  	    	});
			        		  	    	dg.datagrid('selectRow',0);
			        		  	    	  
			        		  	    	  if($('#continueAddAdministrationButton').attr('checked')!='checked')
			        		  	    	  {
			        		  	    		  $('#AddOrEditFileContentAdministrationDialog').dialog('close');
			        		  	    	  }else {
				        		  	    		$('#oneFileRecordId6').val('');
				        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				        		  				$('#oneArchiveTypeFlag6').val(7);
				        		  				
				        		  				$('#docTypeFlag').combobox('setValue','');
				        		  				$('#docTypeName').val('');
				        		  				$('#docName2').val('');
				        		  				$('#docCode').val('');
				        		  				$('#dispatchUnit').val('');
				        		  				$('#dispatchDate').datebox('setValue','');
				        		  				$('#receiptMan').combobox('setValue','');
				        		  				$('#receiptDate').datebox('setValue','');
				        		  				
				        		  				
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
		        	 $('#oneTblFileContentAdministrationForm').form('validate')
		         }
			
		}else{
			$.messager.alert('提示框','请选择档案分类代号');
		}
	  
	}
	
	function writeOperateAdministrationRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateAdministrationRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateAdministrationRecord');
				}else {
					writeOperateAdministrationRsn();
				}
			}
	 });
	}
	function afterSignUpdateAdministrationRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidAdministration').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentAdministrationAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentAdministrationForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	  //contentWind.$('#searchRecordButton').click();
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentAdministrationFrame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentAdministrationDatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
		  	    	
   		  	    	  $('#AddOrEditFileContentAdministrationDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchAdministrationRecord(docTypeFlag,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
		if(childWind.$){
			var dg = childWind.$('#tblFileContentAdministrationDatagrid');
			//如果直接是url会把page和rows直接传过去的。
			 $.ajax({
				 	url : sybp()+'/tblFileContentAdministrationAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
		   		  	type: 'post',
		   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		   		  	data: {
				 		searchString:searchString,
				 		docTypeFlag:docTypeFlag,
				 		page:dg.datagrid('options').pageNumber,
				 		rows:dg.datagrid('options').pageSize,
				 	},
		   		  	dataType:'json',
		   		  	success:function(r){
		   		  		dg.datagrid('loadData',r);
		   		  	}
			 });
			
		 }
			 
		
	}
	
	function deleteOneAdministration() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteAdministrationRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteAdministrationRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要销毁 行政资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认销毁填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateAdministrationRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteAdministrationRecord');
				}else {
					writeDeleteAdministrationRsn(archiveCode,fileRecordSn);
				}
			}
		});
	}
	function afterSignDeleteAdministrationRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
		
		var operateRsn=$('#operateAdministrationRsn').val();
		var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentAdministrationAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentAdministrationDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	
	function destroyOneAdministration()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			writeDestroyAdministrationRsn(row.archiveCode,row.fileRecordSn);
			
		}
	}
	function writeDestroyAdministrationRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁   行政综合档案:'+archiveCode+' 吗？');

		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		/*
		//$.messager.prompt('提示框','确定要销毁 行政综合资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
		$.messager.prompt('提示框','销毁是对于整个档案，确定要销毁 行政综合资料档案:'+archiveCode+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateAdministrationRsn').val(r);
					qm_showQianmingDialog('afterSignDestroyAdministrationRecord');
				}else {
					writeDestroyAdministrationRsn(archiveCode,fileRecordSn);
				}
			}
		});*/
	}
	function afterSignDestroyAdministrationRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentAdministrationFrame').contentWindow;
		//var operateRsn=$('#operateAdministrationRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		
		var row = childWind.$('#tblFileContentAdministrationDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentAdministrationAction_destroy.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
			 		operateRsn:operateRsn,
			 		destoryDate:destoryDate
			 	},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentAdministrationDialog').dialog('close');
	   		  	    	  
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
