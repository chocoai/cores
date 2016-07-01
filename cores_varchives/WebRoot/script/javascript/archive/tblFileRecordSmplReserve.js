/****开始********************************************************/

	function newOneSmplReserve(addOrEdit){
		$('#addOrEditSmplReserve').val(addOrEdit);
		setStatus8(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileRecordSmplReserveDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddSmplReserveLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddSmplReserveButton').css('display','');
			
			$('#oneFileRecordId8').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag8').val(9);//后面也没有使用这个值，所以可以不用维护
			
			$('#smplType').val('');
			$('#smplCode').val('');
			$('#batchCode').val('');
			$('#smplName').val('');
			$('#sponsorName2').val('');
			$('#reportUnitName').val('');
			$('#smplProvUnitName').val('');
			$('#container').val('');
			$('#reserveNum').val('');
			$('#reserveNumUnit').combobox('setValue','');
			$('#reserveBalance').val('');
			$('#gross').val('');
			$('#grossUnit').combobox('setValue','');
			$('#grossBalance').val('');
			
			$('#validDate').datebox('setValue','');
			$('#reserveDate').datebox('setValue','');
			$('#reserveMan').val('');
			$('#reserveRecMan').combobox('setValue','');
			$('#storageCondition').val('');
			
			$('#isLong8').attr('checked',true);
			
			$('#oneArchiveTypeCode8').val('');
			$('#oneArchiveTypeName8').val('');
			$('#oneArchiveCode8').val('');
			$('#oneArchiveCode8').attr('readOnly',false);
			$('#oneStorePosition8').combotree('setValue','');
			$('#oneArchiveTitle8').val('');
			$('#oneArchiveMaker8').combobox('setValue','');
			$('#oneFileOperator8').val('');

			$('#oneFileOperator8List').datagrid({
				url: sybp()+'/tblFileIndexAction_getLastFileOperateList.action?archiveTypeFlag=9',
			});
			
			$('#oneFileDate8').datebox('setValue',$('#todayDate').val());
			$('#oneRemark8').val('');
			$('#oneKeepDate8').datebox('setValue','');
			$('#oneKeyWord8').val('');
			$('#oneDestoryDate8').datebox('setValue','');
			
			$('#isForValidSmplReserve').attr('disabled',false);
		
			$('#chooseOneArchiveTypeCode8').linkbutton('enable');
			$('#oneStorePosition8').combotree('enable');
			$('#oneArchiveTitle8').attr('readOnly',false);
			
			
			$('#AddOrEditFileRecordSmplReserveDialog').dialog('setTitle','添加供试品留样');
			$('#AddOrEditFileRecordSmplReserveDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddSmplReserveLabel").innerHTML='';//连续添加不显示
			$('#continueAddSmplReserveButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
			
			var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag8').val(9);
				
				$('#smplType').val(row.smplType);
				$('#smplCode').val(row.smplCode);
				$('#batchCode').val(row.batchCode);
				$('#smplName').val(row.smplName);
				$('#sponsorName2').val(row.sponsorName);
				$('#reportUnitName').val(row.reportUnitName);
				$('#smplProvUnitName').val(row.smplProvUnitName);
				$('#container').val(row.container);
				$('#reserveNum').val(row.reserveNum);
				$('#reserveNumUnit').combobox('setValue',row.reserveNumUnit);
				$('#reserveBalance').val(row.reserveBalance);
				$('#gross').val(row.gross);
				$('#grossUnit').combobox('setValue',row.grossUnit);
				$('#grossBalance').val(row.grossBalance);
				
				$('#validDate').datebox('setValue',row.validDate);
				$('#reserveDate').datebox('setValue',row.reserveDate);
				$('#reserveMan').val(row.reserveMan);
				$('#reserveRecMan').combobox('setValue',row.reserveRecMan);
				$('#storageCondition').val(row.storageCondition);
				
				$('#oneFileRecordId8').val(row.fileRecordId);
				
				
				if(row.validationFlag==1){
					$('#isForValidSmplReserve').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode8').val(row.archiveTypeCode);
				$('#oneArchiveTypeName8').val(row.archiveTypeName);
				$('#oneArchiveCode8').val(row.archiveCode);
				$('#oneArchiveCode8').attr('readOnly',true);
				$('#oneStorePosition8').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle8').val(row.archiveTitle);
				$('#oneArchiveMaker8').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator8').val(row.fileOperator);
				$('#oneFileDate8').datebox('setValue',row.fileDate);
				$('#oneRemark8').val(row.remark);
				$('#oneKeepDate8').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong8').attr('checked',true);
				}else{
					$('#isLong8').attr('checked',false);
				}
				
				$('#oneKeyWord8').val(row.keyWord);
				$('#oneDestoryDate8').datebox('setValue',row.destoryDate);
				
				$('#isForValidSmplReserve').attr('disabled',true);
				$('#oneArchiveTypeCode8').attr('readOnly',true);
				$('#oneArchiveTypeName8').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode8').linkbutton('disable');
				$('#oneStorePosition8').combotree('disable');
				$('#oneArchiveTitle8').attr('readOnly',true);
				
				
				$('#AddOrEditFileRecordSmplReserveDialog').dialog('setTitle','编辑供试品留样');
				$('#AddOrEditFileRecordSmplReserveDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddSmplReserveLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddSmplReserveButton').css('display','');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
			
			var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag8').val(9);
				
				$('#smplType').val('');
				$('#smplCode').val('');
				$('#batchCode').val('');
				$('#smplName').val('');
				$('#sponsorName2').val('');
				$('#reportUnitName').val('');
				$('#smplProvUnitName').val('');
				$('#container').val('');
				$('#reserveNum').val('');
				$('#reserveNumUnit').combobox('setValue','');
				$('#reserveBalance').val('');
				$('#gross').val('');
				$('#grossUnit').combobox('setValue','');
				$('#grossBalance').val('');
				
				$('#validDate').datebox('setValue','');
				$('#reserveDate').datebox('setValue','');
				$('#reserveMan').val('');
				$('#reserveRecMan').combobox('setValue','');
				$('#storageCondition').val('');
				
				$('#oneFileRecordId8').val(row.fileRecordId);
			
				if(row.validationFlag==1){
					$('#isForValidSmplReserve').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode8').val(row.archiveTypeCode);
				$('#oneArchiveTypeName8').val(row.archiveTypeName);
				$('#oneArchiveCode8').val(row.archiveCode);
				$('#oneArchiveCode8').attr('readOnly',true);
				$('#oneStorePosition8').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle8').val(row.archiveTitle);
				$('#oneArchiveMaker8').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator8').val(row.fileOperator);
				$('#oneFileDate8').datebox('setValue',$('#todayDate').val());
				$('#oneRemark8').val('');
				$('#oneKeepDate8').datebox('setValue','');
				
				$('#isLong8').attr('checked',true);
				
				$('#oneKeyWord8').val('');
				$('#oneDestoryDate8').datebox('setValue','');
				
				$('#isForValidSmplReserve').attr('disabled',true);
				$('#oneArchiveTypeCode8').attr('readOnly',true);
				$('#oneArchiveTypeName8').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode8').linkbutton('disable');
				$('#oneStorePosition8').combotree('disable');
				$('#oneArchiveTitle8').attr('readOnly',true);
				
				
				$('#AddOrEditFileRecordSmplReserveDialog').dialog('setTitle','追加供试品留样');
				$('#AddOrEditFileRecordSmplReserveDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddSmplReserveLabel").innerHTML='';//连续添加不显示
			$('#continueAddSmplReserveButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
			
			var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag8').val(9);
				
				$('#smplType').val(row.smplType);
				$('#smplCode').val(row.smplCode);
				$('#batchCode').val(row.batchCode);
				$('#smplName').val(row.smplName);
				$('#sponsorName2').val(row.sponsorName);
				$('#reportUnitName').val(row.reportUnitName);
				$('#smplProvUnitName').val(row.smplProvUnitName);
				$('#container').val(row.container);
				$('#reserveNum').val(row.reserveNum);
				$('#reserveNumUnit').combobox('setValue',row.reserveNumUnit);
				$('#reserveBalance').val(row.reserveBalance);
				$('#gross').val(row.gross);
				$('#grossUnit').combobox('setValue',row.grossUnit);
				$('#grossBalance').val(row.grossBalance);
				
				$('#validDate').datebox('setValue',row.validDate);
				$('#reserveDate').datebox('setValue',row.reserveDate);
				$('#reserveMan').val(row.reserveMan);
				$('#reserveRecMan').combobox('setValue',row.reserveRecMan);
				$('#storageCondition').val(row.storageCondition);
				
				$('#oneFileRecordId8').val(row.fileRecordId);
				
				
				if(row.validationFlag==1){
					$('#isForValidSmplReserve').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode8').val(row.archiveTypeCode);
				$('#oneArchiveTypeName8').val(row.archiveTypeName);
				$('#oneArchiveCode8').val(row.archiveCode);
				$('#oneArchiveCode8').attr('readOnly',true);
				$('#oneStorePosition8').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle8').val(row.archiveTitle);
				$('#oneArchiveMaker8').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator8').val(row.fileOperator);
				$('#oneFileDate8').datebox('setValue',row.fileDate);
				$('#oneRemark8').val(row.remark);
				$('#oneKeepDate8').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong8').attr('checked',true);
				}else{
					$('#isLong8').attr('checked',false);
				}
				$('#oneKeyWord8').val(row.keyWord);
				$('#oneDestoryDate8').datebox('setValue',row.destoryDate);
				
				$('#isForValidSmplReserve').attr('disabled',true);
				$('#oneArchiveTypeCode8').attr('readOnly',true);
				$('#oneArchiveTypeName8').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode8').linkbutton('disable');
				$('#oneStorePosition8').combotree('disable');
				$('#oneArchiveTitle8').attr('readOnly',true);
				
				
				$('#AddOrEditFileRecordSmplReserveDialog').dialog('setTitle','查看供试品留样');
				$('#AddOrEditFileRecordSmplReserveDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus8(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag8').attr('readOnly',false);
			
			$('#smplType').combobox('enable');
			$('#smplCode').attr('readOnly',false);
			$('#batchCode').attr('readOnly',false);
			$('#smplName').attr('readOnly',false);
			$('#sponsorName2').attr('readOnly',false);
			$('#reportUnitName').attr('readOnly',false);
			$('#smplProvUnitName').attr('readOnly',false);
			$('#container').attr('readOnly',false);
			$('#reserveNum').attr('readOnly',false);
			$('#reserveNumUnit').combobox('enable');
			$('#reserveBalance').attr('readOnly',false);
			$('#gross').attr('readOnly',false);
			$('#grossUnit').combobox('enable');
			$('#grossBalance').attr('readOnly',false);
			
			$('#validDate').datebox('enable');
			$('#reserveDate').datebox('enable');
			$('#reserveMan').attr('readOnly',false);
			$('#reserveRecMan').combobox('enable');
			$('#storageCondition').attr('readOnly',false);
			
			$('#oneFileRecordId8').attr('readOnly',false);
			
			$('#isForValidSmplReserve').css('display','');
			$('#isForValidSmplReserveLabel').css('display','');
			
			$('#oneArchiveTypeCode8').attr('readOnly',true);
			$('#oneArchiveTypeName8').attr('readOnly',true);
			$('#oneArchiveCode8').attr('readOnly',false);
			$('#oneStorePosition8').combotree('enable');
			$('#oneArchiveTitle8').attr('readOnly',false);
			$('#oneArchiveMaker8').combobox('enable');
			$('#oneFileOperator8').attr('readOnly',false);
			$('#oneFileDate8').datebox('enable');
			$('#oneRemark8').attr('readOnly',false);
			$('#oneKeepDate8').datebox('disable');
			
			$('#isLong8').attr('disabled',false);
			
			$('#oneKeyWord8').attr('readOnly',false);
			$('#oneDestoryDate8').datebox('enable');
			
			$('#chooseOneArchiveTypeCode8').linkbutton('enable');
			
			$('#oneDestoryDate8Label').css('display','none');
			$('#oneDestoryDate8Label2').css('display','none');
			$('#saveButton8').css('display','');
			$('#chooseReserveButton').attr('disabled',false);
			
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag8').attr('readOnly',true);
			
			$('#smplType').combobox('disable');
			$('#smplCode').attr('readOnly',true);
			$('#batchCode').attr('readOnly',true);
			$('#smplName').attr('readOnly',true);
			$('#sponsorName2').attr('readOnly',true);
			$('#reportUnitName').attr('readOnly',true);
			$('#smplProvUnitName').attr('readOnly',true);
			$('#container').attr('readOnly',true);
			$('#reserveNum').attr('readOnly',true);
			$('#reserveNumUnit').combobox('disable');
			$('#reserveBalance').attr('readOnly',true);
			$('#gross').attr('readOnly',true);
			$('#grossUnit').combobox('disable');
			$('#grossBalance').attr('readOnly',true);
			
			$('#validDate').datebox('disable');
			$('#reserveDate').datebox('disable');
			$('#reserveMan').attr('readOnly',true);
			$('#reserveRecMan').combobox('disable');
			$('#storageCondition').attr('readOnly',true);
			
			$('#oneFileRecordId8').attr('readOnly',true);
			
			$('#isForValidSmplReserve').css('display','none');
			$('#isForValidSmplReserveLabel').css('display','none');
			
			$('#oneArchiveTypeCode8').attr('readOnly',true);
			$('#oneArchiveTypeName8').attr('readOnly',true);
			$('#oneArchiveCode8').attr('readOnly',true);
			$('#oneStorePosition8').combotree('disable');
			$('#oneArchiveTitle8').attr('readOnly',true);
			$('#oneArchiveMaker8').combobox('disable');
			$('#oneFileOperator8').attr('readOnly',true);
			$('#oneFileDate8').datebox('disable');
			$('#oneRemark8').attr('readOnly',true);
			$('#oneKeepDate8').datebox('disable');
			
			$('#isLong8').attr('disabled',true);
			
			$('#oneKeyWord8').attr('readOnly',true);
			$('#oneDestoryDate8').datebox('disable');
			
			$('#chooseOneArchiveTypeCode8').linkbutton('disable');
			
			$('#oneDestoryDate8Label').css('display','');
			$('#oneDestoryDate8Label2').css('display','');
			$('#saveButton8').css('display','none');
			
			$('#displayLastOperateList').css('display','none');
			$('#chooseReserveButton').attr('disabled',true);
		}
		
	}
	
	function getSmplReserveByCode()
	{
		var smplCode=$('#smplCode').val();
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileRecordSmplReserveAction_getSmplReserveByCode.action?smplCode='+smplCode ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	//data: $('#oneTblFileContentForm').serialize(),
	   		  	dataType:'json',
	   		  	success:function(r){
			 		if(r&&r.success){
			 			if(!r.more)
			 			{
			 				$('#chooseReserveButton').attr('disabled',true);
			 				
				 			$('#smplType').val(r.record.smplType);
							$('#smplCode').val(r.record.smplCode);
							$('#batchCode').val(r.record.batchCode);
							$('#smplName').val(r.record.smplName);
							$('#sponsorName2').val(r.record.sponsorName);
							$('#reportUnitName').val(r.record.reportUnitName);
							$('#smplProvUnitName').val(r.record.smplProvUnitName);
							//$('#container').val(r.
							$('#reserveNum').val(r.record.reserveNum);
							$('#reserveNumUnit').combobox('setValue',r.record.reserveNumUnit);
							$('#reserveBalance').val(r.record.reserveBalance);
							$('#gross').val(r.record.gross);
							$('#grossUnit').combobox('setValue',r.record.grossUnit);
							$('#grossBalance').val(r.record.grossBalance);
							
							$('#validDate').datebox('setValue',r.record.validDate);
							$('#reserveDate').datebox('setValue',r.record.reserveDate);
							$('#reserveMan').val(r.record.reserveMan);
							$('#storageCondition').val(r.record.storageCondition);
							
							if($('#oneArchiveTitle8').val()==''){
								$('#oneArchiveTitle8').val(r.record.smplName);
							}
							
							//$('#reserveRecMan').val(r.
			 			}else {
			 				/* 显示Dialog */
			 				document.getElementById("chooseSmplReserveDialog2").style.display="block";
			 				var smplCode=$('#smplCode').val();
			 				if(r.smplList!=''&&r.smplList!=null)
			 				{
			 					$('#chooseSmplReserveDataGrid').datagrid('loadData',r.smplList);
			 				}else{
			 					$('#chooseSmplReserveDataGrid').datagrid('loadData',[]);
			 				}
			 				$('#chooseReserveButton').attr('disabled',false);
			 				$('#chooseSmplReserveDialog').dialog('setTitle','选择供试品留样记录');
			 				$('#chooseSmplReserveDialog').dialog('open'); 	
						}
						
			 		}
		 		}
		 	});
		 	
	}
	
	function displayLastOperateList()
	{
		$('#oneFileOperator8ListDiv').css('display','');
		$('#oneFileOperator8List').datagrid('reload');
	}
	
	function openChooseSmpl()
	{
		/* 显示Dialog */
		document.getElementById("chooseSmplReserveDialog2").style.display="block";
		var smplCode=$('#smplCode').val();
		if(smplCode!=''&&smplCode!=null)
		{
			$('#chooseSmplReserveDataGrid').datagrid({
				url : sybp()+'/tblFileRecordSmplReserveAction_getSmplReserveByCode.action?smplCode='+smplCode,
			});
			//$('#chooseSmplReserveDataGrid').datagrid('reload');
		
			$('#chooseSmplReserveDialog').dialog('setTitle','选择供试品留样记录');
			$('#chooseSmplReserveDialog').dialog('open'); 	
		}else{
			$.messager.alert('提示框','请填写供试品编号');
		}
		
	}
	
	function chooseOneSmplReserve(){
		//当留样记录多余两条的时候进行选择的方法
		var row = $('#chooseSmplReserveDataGrid').datagrid('getSelected');
		if(row==null||row==''){
			$.messager.alert('提示框','请选择一条记录');
		}else {
			
			$('#smplType').val(row.smplType);
			$('#smplCode').val(row.smplCode);
			$('#batchCode').val(row.batchCode);
			$('#smplName').val(row.smplName);
			$('#sponsorName2').val(row.sponsorName);
			$('#reportUnitName').val(row.reportUnitName);
			$('#smplProvUnitName').val(row.smplProvUnitName);
			$('#container').val(row.container);
			$('#reserveNum').val(row.reserveNum);
			$('#reserveNumUnit').combobox('setValue',row.reserveNumUnit);
			$('#reserveBalance').val(row.reserveBalance);
			$('#gross').val(row.gross);
			$('#grossUnit').combobox('setValue',row.grossUnit);
			$('#grossBalance').val(row.grossBalance);
			
			$('#validDate').datebox('setValue',row.validDate);
			$('#reserveDate').datebox('setValue',row.reserveDate);
			$('#reserveMan').val(row.reserveMan);
			$('#reserveRecMan').combobox('setValue',row.reserveRecMan);
			$('#storageCondition').val(row.storageCondition);
			
			if($('#oneArchiveTitle8').val()=='')
			{
				$('#oneArchiveTitle8').val(row.smplName);
			}
			
			$('#chooseSmplReserveDialog').dialog('close'); 
		}
		
	}
	//保存
	function saveOneFileRecordSmplReserve(){
		var oneArchiveTypeName = $('#oneArchiveTypeName8').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileRecordSmplReserveForm').form('validate') ){
	             var addOrEdit = $('#addOrEditSmplReserve').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidSmplReserve').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition8L').val($('#oneStorePosition8').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileRecordSmplReserveAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileRecordSmplReserveForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
//		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//		        		  	    	// contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileRecordSmplReserveDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddSmplReserveButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileRecordSmplReserveDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId8').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag8').val(9);
			        		  				
			        		  				$('#smplType').val('');
			        						$('#smplCode').val('');
			        						$('#batchCode').val('');
			        						$('#smplName').val('');
			        						$('#sponsorName2').val('');
			        						$('#reportUnitName').val('');
			        						$('#smplProvUnitName').val('');
			        						$('#container').val('');
			        						$('#reserveNum').val('');
			        						$('#reserveNumUnit').combobox('setValue','');
			        						$('#reserveBalance').val('');
			        						$('#gross').val('');
			        						$('#grossUnit').combobox('setValue','');
			        						$('#grossBalance').val('');
			        						
			        						$('#validDate').datebox('setValue','');
			        						$('#reserveDate').datebox('setValue','');
			        						$('#reserveMan').val('');
			        						$('#reserveRecMan').combobox('setValue','');
			        		  				
			        		  				//$('#oneArchiveTypeCode8').val('');
			        		  				//$('#oneArchiveTypeName8').val('');
			        						$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode8').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode8').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition8').combotree('setValue','');
			        		  				$('#oneArchiveTitle8').val('');
			        		  				//$('#oneArchiveMaker8').combobox('setValue','');
			        		  				//$('#oneFileOperator8').val('');
			        		  				$('#oneFileDate8').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark8').val('');
			        		  				$('#oneKeepDate8').datebox('setValue','');
			        		  				$('#oneKeyWord8').val('');
			        		  				$('#oneDestoryDate8').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateSmplReserveRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidSmplReserve').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileRecordSmplReserveAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileRecordSmplReserveForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
//		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//		        		  	    	// contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileRecordSmplReserveDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddSmplReserveButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileRecordSmplReserveDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId8').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag8').val(9);
			        		  				
			        		  				$('#smplType').val('');
			        						$('#smplCode').val('');
			        						$('#batchCode').val('');
			        						$('#smplName').val('');
			        						$('#sponsorName2').val('');
			        						$('#reportUnitName').val('');
			        						$('#smplProvUnitName').val('');
			        						$('#container').val('');
			        						$('#reserveNum').val('');
			        						$('#reserveNumUnit').combobox('setValue','');
			        						$('#reserveBalance').val('');
			        						$('#gross').val('');
			        						$('#grossUnit').combobox('setValue','');
			        						$('#grossBalance').val('');
			        						
			        						$('#validDate').datebox('setValue','');
			        						$('#reserveDate').datebox('setValue','');
			        						$('#reserveMan').val('');
			        						$('#reserveRecMan').combobox('setValue','');
			        		  				
			        		  				
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
	             
	         }else{
	        	 $.messager.alert('提示框','请检查表单数据');
	         }
		}else{
			$.messager.alert('提示框','请选择档案分类代号');
		}
	  
	}
	
	function writeOperateSmplReserveRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateSmplReserveRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateSmplReserveRecord');
				}else {
					writeOperateSmplReserveRsn();
				}
			}
	 });
	}
	function afterSignUpdateSmplReserveRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidSmplReserve').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileRecordSmplReserveAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileRecordSmplReserveForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
//   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//   		  	    	  contentWind.$('#searchRecordButton').click();
   		  	 	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		  	    var studyFrame = contentWindMain.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow
  	    	    var dg = studyFrame.$('#tblFileRecordSmplReserveDatagrid');
	  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
	  	    	dg.datagrid('updateRow',{
	  	    		index:index,
	  	    		row:r.record,
	  	    	});
	  	    	dg.datagrid('selectRow',index);
	  	    	
   		  	    	  $('#AddOrEditFileRecordSmplReserveDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchSmplReserveRecord(isSmplKeepEndDate,smplKeepEndDate,isDestroySmpl,smplType,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid({
		    	url : sybp()+'/tblFileRecordSmplReserveAction_loadList.action?smplType='+smplType+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileRecordSmplReserveDatagrid');
			$.ajax({
				url : sybp()+'/tblFileRecordSmplReserveAction_loadList.action?smplType='+smplType+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&isSmplKeepEndDate='+isSmplKeepEndDate+'&smplKeepEndDate='+smplKeepEndDate+'&isDestroySmpl='+isDestroySmpl,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					page:dg.datagrid('options').pageNumber,
			 		rows:dg.datagrid('options').pageSize,
				},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('loadData',r);
	   		  	}
		   });
		 }
			 
		
	}
	
	function deleteOneSmplReserve() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
		
		var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteSmplReserveRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteSmplReserveRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 供试品留样档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateSmplReserveRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteSmplReserveRecord');
				}else {
					writeDeleteSmplReserveRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteSmplReserveRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
		
		var operateRsn=$('#operateSmplReserveRsn').val();
		var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileRecordSmplReserveAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileRecordSmplReserveDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneSmplReserve() {//销毁的是档案
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
		
		var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			$('#destroyType').val(1);
			writeDestroySmplReserveRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function destroyOneSmplReserveContent() {//销毁的是留样
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
		
		var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.smplDestoryDate!=null&&row.smplDestoryDate!=''){
			$.messager.alert("提示框","该留样已经被销毁！");
		}else{
			$('#destroyType').val(2);
			writeDestroySmplReserveRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroySmplReserveRsn(archiveCode,fileRecordSn)
	{
		var type = $('#destroyType').val();
		var str = '销毁是对于整个档案，确定要销毁 供试品留样档案:'+archiveCode+' 吗？';
		if(type!=''&&(type==2||type=='2')){
			str = "销毁的是供试品留样，不是供试品留样档案，确认要销毁该留样吗？";
			document.getElementById("otherOperateDialog2").style.display="block";
			$('#otherOperateLabel').html(str);
			$('#otherOperateReason').val('');
			$('#otherOperateDate').datebox('setValue','');
			$('#otherOperateType').val(5);
			$('#otherOperateDialog').dialog('setTitle','销毁供试品留样');
			$('#otherOperateDialog').dialog('open');
		}else{
			document.getElementById("otherOperateDialog2").style.display="block";
			$('#otherOperateLabel').html(str);
			$('#otherOperateReason').val('');
			$('#otherOperateDate').datebox('setValue','');
			$('#otherOperateType').val(3);
			$('#otherOperateDialog').dialog('setTitle','销毁档案');
			$('#otherOperateDialog').dialog('open');
		}
		/*$.messager.prompt('提示框',str,function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateSmplReserveRsn').val(r);
					qm_showQianmingDialog('afterSignDestroySmplReserveRecord');
				}else {
					writeDestroySmplReserveRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
		});*/
	}
	function afterSignDestroySmplReserveRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSmplReserveFrame').contentWindow;
	//	var operateRsn=$('#operateSmplReserveRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		
		var row = childWind.$('#tblFileRecordSmplReserveDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileRecordSmplReserveAction_destroy.action?fileRecordId='+row.fileRecordId+'&destroyType='+$('#destroyType').val() ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileRecordSmplReserveDialog').dialog('close');
	   		  	    	  
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
