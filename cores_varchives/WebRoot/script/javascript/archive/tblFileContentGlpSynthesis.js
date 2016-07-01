/****开始********************************************************/

	function newOneGlpSynthesis(addOrEdit){
		$('#addOrEditGlpSynthesis').val(addOrEdit);
		setStatus3(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentGlpSynthesisDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddGlpSynthesisLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddGlpSynthesisButton').css('display','');
			
			$('#oneFileRecordId3').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag3').val(4);//后面也没有使用这个值，所以可以不用维护
			
			$('#department').combobox('setValue','');
			$('#docName').val('');
			
			$('#oneArchiveTypeCode3').val('');
			$('#oneArchiveTypeName3').val('');
			$('#oneArchiveCode3').val('');
			$('#oneArchiveCode3').attr('readOnly',false);
			$('#oneStorePosition3').combotree('setValue','');
			$('#oneArchiveTitle3').val('');
			$('#oneArchiveMaker3').combobox('setValue','');
			//$('#oneFileOperator3').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=4',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator3').val(r.last);
						}
			 	  }
			});
			$('#oneFileDate3').datebox('setValue',$('#todayDate').val());
			$('#oneRemark3').val('');
			$('#oneKeepDate3').datebox('setValue','');
			$('#oneKeyWord3').val('');
			$('#oneDestoryDate3').datebox('setValue','');
			
			$('#isLong3').attr('checked',true);
			
			$('#isForValidGlpSynthesis').attr('disabled',false);
			
			$('#chooseOneArchiveTypeCode3').linkbutton('enable');
			$('#oneStorePosition3').combotree('enable');
			$('#oneArchiveTitle3').attr('readOnly',false);
			$('#oneArchiveMedia3').val('');
			
			$('#AddOrEditFileContentGlpSynthesisDialog').dialog('setTitle','添加综合资料');
			$('#AddOrEditFileContentGlpSynthesisDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddGlpSynthesisLabel").innerHTML='';//连续添加不显示
			$('#continueAddGlpSynthesisButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag3').val(4);
				
				$('#department').combobox('setValue',row.departmentId);
				$('#docName').val(row.docName);
				
				$('#oneFileRecordId3').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia3').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidGlpSynthesis').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode3').val(row.archiveTypeCode);
				$('#oneArchiveTypeName3').val(row.archiveTypeName);
				$('#oneArchiveCode3').val(row.archiveCode);
				$('#oneArchiveCode3').attr('readOnly',true);
				$('#oneStorePosition3').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle3').val(row.archiveTitle);
				$('#oneArchiveMaker3').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator3').val(row.fileOperator);
				$('#oneFileDate3').datebox('setValue',row.fileDate);
				$('#oneRemark3').val(row.remark);
				$('#oneKeepDate3').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong3').attr('checked',true);
				}else{
					$('#isLong3').attr('checked',false);
				}
				$('#oneKeyWord3').val(row.keyWord);
				$('#oneDestoryDate3').datebox('setValue',row.destoryDate);
				
				$('#isForValidGlpSynthesis').attr('disabled',true);
				$('#oneArchiveTypeCode3').attr('readOnly',true);
				$('#oneArchiveTypeName3').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode3').linkbutton('disable');
				$('#oneStorePosition3').combotree('disable');
				$('#oneArchiveTitle3').attr('readOnly',true);
				
				
				
				$('#AddOrEditFileContentGlpSynthesisDialog').dialog('setTitle','编辑综合资料');
				$('#AddOrEditFileContentGlpSynthesisDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddGlpSynthesisLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddGlpSynthesisButton').css('display','');
			
			//document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			//$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag3').val(4);
				
				$('#department').combobox('setValue','');
				$('#docName').val('');
				
				$('#oneFileRecordId3').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia3').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidGlpSynthesis').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode3').val(row.archiveTypeCode);
				$('#oneArchiveTypeName3').val(row.archiveTypeName);
				$('#oneArchiveCode3').val(row.archiveCode);
				$('#oneArchiveCode3').attr('readOnly',true);
				$('#oneStorePosition3').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle3').val(row.archiveTitle);
				$('#oneArchiveMaker3').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator3').val(row.fileOperator);
				$('#oneFileDate3').datebox('setValue',$('#todayDate').val());
				$('#oneRemark3').val('');
				$('#oneKeepDate3').datebox('setValue','');
			
				$('#isLong3').attr('checked',true);
				
				$('#oneKeyWord3').val('');
				$('#oneDestoryDate3').datebox('setValue','');
				
				$('#isForValidGlpSynthesis').attr('disabled',true);
				$('#oneArchiveTypeCode3').attr('readOnly',true);
				$('#oneArchiveTypeName3').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode3').linkbutton('disable');
				$('#oneStorePosition3').combotree('disable');
				$('#oneArchiveTitle3').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentGlpSynthesisDialog').dialog('setTitle','追加综合资料');
				$('#AddOrEditFileContentGlpSynthesisDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddGlpSynthesisLabel").innerHTML='';//连续添加不显示
			$('#continueAddGlpSynthesisButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag3').val(4);
				
				$('#department').combobox('setValue',row.departmentId);
				$('#docName').val(row.docName);
				
				$('#oneFileRecordId3').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia3').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidGlpSynthesis').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode3').val(row.archiveTypeCode);
				$('#oneArchiveTypeName3').val(row.archiveTypeName);
				$('#oneArchiveCode3').val(row.archiveCode);
				$('#oneArchiveCode3').attr('readOnly',true);
				$('#oneStorePosition3').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle3').val(row.archiveTitle);
				$('#oneArchiveMaker3').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator3').val(row.fileOperator);
				$('#oneFileDate3').datebox('setValue',row.fileDate);
				$('#oneRemark3').val(row.remark);
				$('#oneKeepDate3').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong3').attr('checked',true);
				}else{
					$('#isLong3').attr('checked',false);
				}
				$('#oneKeyWord3').val(row.keyWord);
				$('#oneDestoryDate3').datebox('setValue',row.destoryDate);
				
				$('#isForValidGlpSynthesis').attr('disabled',true);
				$('#oneArchiveTypeCode3').attr('readOnly',true);
				$('#oneArchiveTypeName3').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode3').linkbutton('disable');
				$('#oneStorePosition3').combotree('disable');
				$('#oneArchiveTitle3').attr('readOnly',true);
			
				
				$('#AddOrEditFileContentGlpSynthesisDialog').dialog('setTitle','查看综合资料');
				$('#AddOrEditFileContentGlpSynthesisDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus3(addOrEdit){
		
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag3').attr('readOnly',false);
			
			$('#department').combobox('enable');
			$('#docName').attr('readOnly',false);
			
			$('#oneFileRecordId3').attr('readOnly',false);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia3').attr('readOnly',false);
			
			$('#isForValidGlpSynthesis').css('display','');
			$('#isForValidGlpSynthesisLabel').css('display','');
			
			
			$('#oneArchiveTypeCode3').attr('readOnly',true);
			$('#oneArchiveTypeName3').attr('readOnly',true);
			$('#oneArchiveCode3').attr('readOnly',false);
			$('#oneStorePosition3').combotree('enable');
			$('#oneArchiveTitle3').attr('readOnly',false);
			$('#oneArchiveMaker3').combobox('enable');
			$('#oneFileOperator3').attr('readOnly',false);
			$('#oneFileDate3').datebox('enable');
			$('#oneRemark3').attr('readOnly',false);
			$('#oneKeepDate3').datebox('disable');
			
			$('#oneKeyWord3').attr('readOnly',false);
			$('#oneDestoryDate3').datebox('enable');
			
			$('#chooseOneArchiveTypeCode3').linkbutton('enable');
			
			$('#isLong3').attr('disabled',false);
			
			$('#oneDestoryDate3Label').css('display','none');
			$('#oneDestoryDate3Label2').css('display','none');
			$('#saveButton3').css('display','');
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag3').attr('readOnly',true);
			
			$('#department').combobox('disable');
			$('#docName').attr('readOnly',true);
			
			$('#oneFileRecordId3').attr('readOnly',true);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia3').attr('readOnly',true);
			
			
			$('#isForValidGlpSynthesis').css('display','none');
			$('#isForValidGlpSynthesisLabel').css('display','none');
			
			$('#oneArchiveTypeCode3').attr('readOnly',true);
			$('#oneArchiveTypeName3').attr('readOnly',true);
			$('#oneArchiveCode3').attr('readOnly',true);
			$('#oneStorePosition3').combotree('disable');
			$('#oneArchiveTitle3').attr('readOnly',true);
			$('#oneArchiveMaker3').combobox('disable');
			$('#oneFileOperator3').attr('readOnly',true);
			$('#oneFileDate3').datebox('disable');
			$('#oneRemark3').attr('readOnly',true);
			$('#oneKeepDate3').datebox('disable');
			
			$('#oneKeyWord3').attr('readOnly',true);
			$('#oneDestoryDate3').datebox('disable');
			
			$('#isForValidGlpSynthesis').attr('disabled',true);
			
			$('#chooseOneArchiveTypeCode3').linkbutton('disable');
			
			$('#isLong3').attr('disabled',true);
			
			$('#oneDestoryDate3Label').css('display','');
			$('#oneDestoryDate3Label2').css('display','');
			$('#saveButton3').css('display','none');
		}
		
	}
	
	//保存
	function saveOneFileContentGlpSynthesis(){
		var oneArchiveTypeName = $('#oneArchiveTypeName3').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentGlpSynthesisForm').form('validate') ){
	             var addOrEdit = $('#addOrEditGlpSynthesis').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidGlpSynthesis').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition3L').val($('#oneStorePosition3').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentGlpSynthesisAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentGlpSynthesisForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentGlpSynthesisDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddGlpSynthesisButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentGlpSynthesisDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId3').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag3').val(4);
			        		  				
			        		  				$('#department').combobox('setValue','');
			        		  				$('#docName').val('');
			        		  				
			        		  				//$('#oneArchiveTypeCode3').val('');
			        		  				//$('#oneArchiveTypeName3').val('');
			        		  				$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode3').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode3').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition3').combotree('setValue','');
			        		  				$('#oneArchiveTitle3').val('');
			        		  				//$('#oneArchiveMaker3').combobox('setValue','');
			        		  				//$('#oneFileOperator3').val('');
			        		  				$('#oneFileDate3').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark3').val('');
			        		  				$('#oneKeepDate3').datebox('setValue','');
			        		  				$('#oneKeyWord3').val('');
			        		  				$('#oneDestoryDate3').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateGlpSynthesisRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidGlpSynthesis').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentGlpSynthesisAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentGlpSynthesisForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentGlpSynthesisDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddGlpSynthesisButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentGlpSynthesisDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId3').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag3').val(4);
			        		  				
			        		  				$('#department').combobox('setValue','');
			        		  				$('#docName').val('');
			        		  				
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
	
	function writeOperateGlpSynthesisRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateGlpSynthesisRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateGlpSynthesisRecord');
				}else {
					writeOperateGlpSynthesisRsn();
				}
			}
	 });
	}
	function afterSignUpdateGlpSynthesisRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidGlpSynthesis').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentGlpSynthesisAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentGlpSynthesisForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	  //contentWind.$('#searchRecordButton').click();
   		  	    	
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentGlpSynthesisDatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
		  	    	
   		  	    	  $('#AddOrEditFileContentGlpSynthesisDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchGlpSynthesisRecord(departmentLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		//alert(NoType+"=="+isFileDate+"=="+start+"=="+end+"=="+keepEndDate+"=="+isDestory+"=="+isValid+"=="+searchString);
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid({
		    	url : sybp()+'/tblFileContentGlpSynthesisAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileContentGlpSynthesisDatagrid');
			$.ajax({
				url : sybp()+'/tblFileContentGlpSynthesisAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
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
	   		  		childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('loadData',r);
	   		  	}
		   });
		 }
		
	}
	
	function deleteOneGlpSynthesis() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteGlpSynthesisRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteGlpSynthesisRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 综合资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateGlpSynthesisRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteGlpSynthesisRecord');
				}else {
					writeDeleteGlpSynthesisRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteGlpSynthesisRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
		
		var operateRsn = $('#operateGlpSynthesisRsn').val();
		var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentGlpSynthesisAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentGlpSynthesisDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneGlpSynthesis() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDestroyGlpSynthesisRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyGlpSynthesisRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁   综合资料档案:'+archiveCode+' 吗？');
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
	function afterSignDestroyGlpSynthesisRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentGlpSynthesisFrame').contentWindow;
		
		//var operateRsn = $('#operateGlpSynthesisRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		var row = childWind.$('#tblFileContentGlpSynthesisDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentGlpSynthesisAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentGlpSynthesisDialog').dialog('close');
	   		  	    	  
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
