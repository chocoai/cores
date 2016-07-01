/****开始********************************************************/

	function newOneContract(addOrEdit){
		$('#addOrEditContract').val(addOrEdit);
		setStatus7(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentContractDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddContractLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddContractButton').css('display','');
			
			$('#oneFileRecordId7').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag7').val(8);//后面也没有使用这个值，所以可以不用维护
			
			$('#beginDate').datebox('setValue','');
			$('#contractCode').val('');
			$('#contractName').val('');
			$('#contractTypeFlag').combobox('setValue','');
			$('#endDate').datebox('setValue','');
			$('#num').val('');
			$('#sponsorName').val('');
			$('#terminalDate').datebox('setValue','');
			$('#oneArchiveMedia7').val('');
			
			$('#oneArchiveTypeCode7').val('');
			$('#oneArchiveTypeName7').val('');
			$('#oneArchiveCode7').val('');
			$('#oneArchiveCode7').attr('readOnly',false);
			$('#oneStorePosition7').combotree('setValue','');
			$('#oneArchiveTitle7').val('');
			$('#oneArchiveMaker7').combobox('setValue','');
			//$('#oneFileOperator7').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=8',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator7').val(r.last);
						}
			 	  }
			});
			$('#oneFileDate7').datebox('setValue',$('#todayDate').val());
			$('#oneRemark7').val('');
			$('#oneKeepDate7').datebox('setValue','');
			
			$('#isLong7').attr('checked',true);
			
			$('#oneKeyWord7').val('');
			$('#oneDestoryDate7').datebox('setValue','');
			
			$('#isForValidContract').attr('disabled',false);
	
			$('#chooseOneArchiveTypeCode7').linkbutton('enable');
			$('#oneStorePosition7').combotree('enable');
			$('#oneArchiveTitle7').attr('readOnly',false);
			
			
			$('#AddOrEditFileContentContractDialog').dialog('setTitle','添加合同资料');
			$('#AddOrEditFileContentContractDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddContractLabel").innerHTML='';//连续添加不显示
			$('#continueAddContractButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else if(row.isTerminal==true){
				$.messager.alert("提示框","该合同已经终止，不可修改");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag7').val(8);
				
				$('#beginDate').datebox('setValue',row.beginDate);
				$('#contractCode').val(row.contractCode);
				$('#contractName').val(row.contractName);
				$('#contractTypeFlag').combobox('setValue',row.contractTypeFlag);
				$('#endDate').datebox('setValue',row.endDate);
				$('#num').val(row.num);
				$('#sponsorName').val(row.sponsorName);
				$('#terminalDate').datebox('setValue',row.terminalDate);
				
				$('#oneFileRecordId7').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia7').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidContract').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode7').val(row.archiveTypeCode);
				$('#oneArchiveTypeName7').val(row.archiveTypeName);
				$('#oneArchiveCode7').val(row.archiveCode);
				$('#oneArchiveCode7').attr('readOnly',true);
				$('#oneStorePosition7').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle7').val(row.archiveTitle);
				$('#oneArchiveMaker7').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator7').val(row.fileOperator);
				$('#oneFileDate7').datebox('setValue',row.fileDate);
				$('#oneRemark7').val(row.remark);
				$('#oneKeepDate7').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong7').attr('checked',true);
				}else{
					$('#isLong7').attr('checked',false);
				}
				
				$('#oneKeyWord7').val(row.keyWord);
				$('#oneDestoryDate7').datebox('setValue',row.destoryDate);
				
				$('#isForValidContract').attr('disabled',true);
				$('#oneArchiveTypeCode7').attr('readOnly',true);
				$('#oneArchiveTypeName7').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode7').linkbutton('disable');
				$('#oneStorePosition7').combotree('disable');
				$('#oneArchiveTitle7').attr('readOnly',true);
				
				
				
				$('#AddOrEditFileContentContractDialog').dialog('setTitle','编辑合同资料');
				$('#AddOrEditFileContentContractDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddContractLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddContractButton').css('display','');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag7').val(7);
				
				$('#beginDate').datebox('setValue','');
				$('#contractCode').val('');
				$('#contractName').val('');
				$('#contractTypeFlag').combobox('setValue','');
				$('#endDate').datebox('setValue','');
				$('#num').val('');
				$('#sponsorName').val('');
				$('#terminalDate').datebox('setValue','');
				
				$('#oneFileRecordId7').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia7').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidContract').attr('checked',true);
				}
				
				$('#oneArchiveMedia7').val('');
				$('#oneArchiveTypeCode7').val(row.archiveTypeCode);
				$('#oneArchiveTypeName7').val(row.archiveTypeName);
				$('#oneArchiveCode7').val(row.archiveCode);
				$('#oneArchiveCode7').attr('readOnly',true);
				$('#oneStorePosition7').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle7').val(row.archiveTitle);
				$('#oneArchiveMaker7').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator7').val(row.fileOperator);
				$('#oneFileDate7').datebox('setValue',$('#todayDate').val());
				$('#oneRemark7').val('');
				$('#oneKeepDate7').datebox('setValue','');
				
				$('#isLong7').attr('checked',true);
				
				$('#oneKeyWord7').val('');
				$('#oneDestoryDate7').datebox('setValue','');
				
				$('#isForValidContract').attr('disabled',true);
				$('#oneArchiveTypeCode7').attr('readOnly',true);
				$('#oneArchiveTypeName7').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode7').linkbutton('disable');
				$('#oneStorePosition7').combotree('disable');
				$('#oneArchiveTitle7').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentContractDialog').dialog('setTitle','追加合同资料');
				$('#AddOrEditFileContentContractDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddContractLabel").innerHTML='';//连续添加不显示
			$('#continueAddContractButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				
				$('#beginDate').datebox('setValue',row.beginDate);
				$('#contractCode').val(row.contractCode);
				$('#contractName').val(row.contractName);
				$('#contractTypeFlag').combobox('setValue',row.contractTypeFlag);
				$('#endDate').datebox('setValue',row.endDate);
				$('#num').val(row.num);
				$('#sponsorName').val(row.sponsorName);
				$('#terminalDate').datebox('setValue',row.terminalDate);
				
				$('#oneFileRecordId7').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia7').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidContract').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode7').val(row.archiveTypeCode);
				$('#oneArchiveTypeName7').val(row.archiveTypeName);
				$('#oneArchiveCode7').val(row.archiveCode);
				$('#oneArchiveCode7').attr('readOnly',true);
				$('#oneStorePosition7').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle7').val(row.archiveTitle);
				$('#oneArchiveMaker7').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator7').val(row.fileOperator);
				$('#oneFileDate7').datebox('setValue',row.fileDate);
				$('#oneRemark7').val(row.remark);
				$('#oneKeepDate7').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong7').attr('checked',true);
				}else{
					$('#isLong7').attr('checked',false);
				}
				$('#oneKeyWord7').val(row.keyWord);
				$('#oneDestoryDate7').datebox('setValue',row.destoryDate);
				
				$('#isForValidContract').attr('disabled',true);
				$('#oneArchiveTypeCode7').attr('readOnly',true);
				$('#oneArchiveTypeName7').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode7').linkbutton('disable');
				$('#oneStorePosition7').combotree('disable');
				$('#oneArchiveTitle7').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentContractDialog').dialog('setTitle','查看合同资料');
				$('#AddOrEditFileContentContractDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus7(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#beginDate').datebox('enable');
			$('#contractCode').attr('readOnly',false);
			$('#contractName').attr('readOnly',false);
			$('#contractTypeFlag').combobox('enable');
			$('#endDate').datebox('enable');
			$('#num').attr('readOnly',false);
			$('#sponsorName').attr('readOnly',false);
			$('#terminalDate').datebox('disable');
			
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia7').attr('readOnly',false);
			
			$('#isForValidContract').css('display','');
			$('#isForValidContractLabel').css('display','');
			
			
			$('#oneArchiveTypeCode7').attr('readOnly',true);
			$('#oneArchiveTypeName7').attr('readOnly',true);
			
			$('#oneArchiveCode7').attr('readOnly',false);
			$('#oneStorePosition7').combotree('enable');
			$('#oneArchiveTitle7').attr('readOnly',false);
			$('#oneArchiveMaker7').combobox('enable');
			$('#oneFileOperator7').attr('readOnly',false);
			$('#oneFileDate7').datebox('enable');
			$('#oneRemark7').attr('readOnly',false);
			$('#oneKeepDate7').datebox('disable');
			
			$('#oneKeyWord7').attr('readOnly',false);
			$('#oneDestoryDate7').datebox('enable');
			
			$('#isLong7').attr('disabled',false);
			
		
			$('#chooseOneArchiveTypeCode7').linkbutton('enable');
		
			
			$('#oneDestoryDate7Label').css('display','none');
			$('#oneDestoryDate7Label2').css('display','none');
			$('#saveButton7').css('display','');
		}else if(addOrEdit==4){
		
			
			$('#beginDate').datebox('disable');
			$('#contractCode').attr('readOnly',true);
			$('#contractName').attr('readOnly',true);
			$('#contractTypeFlag').combobox('disable');
			$('#endDate').datebox('disable');
			$('#num').attr('readOnly',true);
			$('#sponsorName').attr('readOnly',true);
			$('#terminalDate').datebox('disable');
			
			$('#oneFileRecordId7').attr('readOnly',true);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia7').attr('readOnly',true);
			
			$('#isForValidContract').css('display','none');
			$('#isForValidContractLabel').css('display','none');
			
			
			$('#oneArchiveTypeCode7').attr('readOnly',true);
			$('#oneArchiveTypeName7').attr('readOnly',true);
			$('#oneArchiveCode7').attr('readOnly',true);
			$('#oneStorePosition7').combotree('disable');
			$('#oneArchiveTitle7').attr('readOnly',true);
			$('#oneArchiveMaker7').combobox('disable');
			$('#oneFileOperator7').attr('readOnly',true);
			$('#oneFileDate7').datebox('disable');
			$('#oneRemark7').attr('readOnly',true);
			$('#oneKeepDate7').datebox('disable');
			
			$('#oneKeyWord7').attr('readOnly',true);
			$('#oneDestoryDate7').datebox('disable');
			
			
			$('#chooseOneArchiveTypeCode7').linkbutton('disable');
		
			
			$('#isLong7').attr('disabled',true);
			
			$('#oneDestoryDate7Label').css('display','');
			$('#oneDestoryDate7Label2').css('display','');
			$('#saveButton7').css('display','none');
		}
		
	}
	
	function getContractByCode()
	{
		var contractCode=$('#contractCode').val();
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentContractAction_getContractByCode.action?contractCode='+contractCode ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	//data: $('#oneTblFileContentForm').serialize(),
	   		  	dataType:'json',
	   		  	success:function(r){
			 		if(r&&r.success){
			 			/*
			 			map.put("contractName", contract.getContractName());
						map.put("sponsorName", contract.getSponsorName());
						map.put("finishDate", contract.getFinishDate());
						map.put("effectiveDate", contract.getEffectiveDate());
						map.put("signingDate", contract.getSigningDate());
						*/
						$('#beginDate').datebox('setValue',r.effectiveDate);
		  				$('#contractName').val(r.contractName);
		  				$('#endDate').datebox('setValue',r.finishDate);
		  				$('#sponsorName').val(r.sponsorName);
		  				//$('#terminalDate').datebox('setValue',r.finishDate);
		  				
		  				 if($('#addOrEditContract').val()==1)
	 		  	    	 {
		  					$('#oneArchiveTitle7').val(r.contractName);
	 		  	    	 }
		  				
						
			 		}
		 		}
		 	});
		 	
	}
	
	//保存
	function saveOneFileContentContract(){
		var oneArchiveTypeName = $('#oneArchiveTypeName7').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentContractForm').form('validate') ){
	             var addOrEdit = $('#addOrEditContract').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidContract').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition7L').val($('#oneStorePosition7').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentContractAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentContractForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	//  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	// contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentContractFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentContractDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	
		        		  	    	  if($('#continueAddContractButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentContractDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId7').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag7').val(8);
			        		  				
			        		  				$('#beginDate').datebox('setValue','');
			        		  				$('#contractCode').val('');
			        		  				$('#contractName').val('');
			        		  				$('#contractTypeFlag').combobox('setValue','');
			        		  				$('#endDate').datebox('setValue','');
			        		  				$('#num').val('');
			        		  				$('#sponsorName').val('');
			        		  				$('#terminalDate').datebox('setValue','');
			        		  				
			        		  				//$('#oneArchiveTypeCode7').val('');
			        		  				//$('#oneArchiveTypeName7').val('');
			        		  				$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode7').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode7').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				
			        		  				$('#oneStorePosition7').combotree('setValue','');
			        		  				$('#oneArchiveTitle7').val('');
			        		  				//$('#oneArchiveMaker7').combobox('setValue','');
			        		  				//$('#oneFileOperator7').val('');
			        		  				$('#oneFileDate7').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark7').val('');
			        		  				$('#oneKeepDate7').datebox('setValue','');
			        		  				$('#oneKeyWord7').val('');
			        		  				$('#oneDestoryDate7').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateContractRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidContract').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentContractAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentContractForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentContractFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentContractDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddContractButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentContractDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId7').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag7').val(8);
			        		  				
			        		  				$('#beginDate').datebox('setValue','');
			        		  				$('#contractCode').val('');
			        		  				$('#contractName').val('');
			        		  				$('#contractTypeFlag').combobox('setValue','');
			        		  				$('#endDate').datebox('setValue','');
			        		  				$('#num').val('');
			        		  				$('#sponsorName').val('');
			        		  				$('#terminalDate').datebox('setValue','');
			        		  				
			        		  				
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
	
	function writeOperateContractRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateContractRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateContractRecord');
				}else {
					writeOperateContractRsn();
				}
			}
	 });
	}
	function afterSignUpdateContractRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidContract').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentContractAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentContractForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	 // contentWind.$('#searchRecordButton').click();
   		  	    	
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentContractFrame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentContractDatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
   		  	    	  
   		  	    	  $('#AddOrEditFileContentContractDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchContractRecord(contractTypeFlagLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
		if(childWind.$){
			/*
			childWind.$('#tblFileContentContractDatagrid').datagrid({
		    	url : sybp()+'/tblFileContentContractAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileContentContractDatagrid');
			$.ajax({
				url : sybp()+'/tblFileContentContractAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
			 		searchString:searchString,
			 		contractTypeFlag:contractTypeFlagLeft,
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
	
	function deleteOneContract() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteContractRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteContractRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 仪器资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateContractRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteContractRecord');
				}else {
					writeDeleteContractRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteContractRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
		
		var operateRsn=$('#operateContractRsn').val();
		var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentContractAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentContractDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneContract() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			writeDestroyContractRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyContractRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁   合同档案:'+archiveCode+' 吗？');
		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		/*
		$.messager.prompt('提示框','销毁是对于整个档案，确定要销毁 仪器资料档案:'+archiveCode+' 吗？如果确认销毁请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateContractRsn').val(r);
					qm_showQianmingDialog('afterSignDestroyContractRecord');
				}else {
					writeDestroyContractRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});*/
	}
	function afterSignDestroyContractRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentContractFrame').contentWindow;
	//	var operateRsn=$('#operateContractRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		
		var row = childWind.$('#tblFileContentContractDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentContractAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentContractDialog').dialog('close');
	   		  	    	  
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
