/****开始********************************************************/

	function newOneEmployee(addOrEdit){
		$('#addOrEditEmployee').val(addOrEdit);
		setStatus5(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentEmployeeDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddEmployeeLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddEmployeeButton').css('display','');
			
			$('#oneFileRecordId5').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag5').val(6);//后面也没有使用这个值，所以可以不用维护
			
			$('#staffName').val('');
			$('#staffCode').val('');
			$('#staffDept').combobox('setValue','');
			$('#staffState').combobox('setValue','');
			$('#oneArchiveMedia5').val('');
			
			$('#oneArchiveTypeCode5').val('');
			$('#oneArchiveTypeName5').val('');
			$('#oneArchiveCode5').val('');
			$('#oneArchiveCode5').attr('readOnly',false);
			$('#oneStorePosition5').combotree('setValue','');
			$('#oneArchiveTitle5').val('');
			$('#oneArchiveMaker5').combobox('setValue','');
			//$('#oneFileOperator5').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=6',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator5').val(r.last);
						}
			 	  }
			});
			$('#oneFileDate5').datebox('setValue',$('#todayDate').val());
			$('#oneRemark5').val('');
			$('#oneKeepDate5').datebox('setValue','');
			
			$('#isLong5').attr('checked',true);
			
			$('#oneKeyWord5').val('');
			$('#oneDestoryDate5').datebox('setValue','');
			
			$('#isForValidEmployee').attr('disabled',false);
		
			$('#chooseOneArchiveTypeCode5').linkbutton('enable');
			$('#oneStorePosition5').combotree('enable');
			$('#oneArchiveTitle5').attr('readOnly',false);
			
			
			$('#AddOrEditFileContentEmployeeDialog').dialog('setTitle','添加人员档案');
			$('#AddOrEditFileContentEmployeeDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddEmployeeLabel").innerHTML='';//连续添加不显示
			$('#continueAddEmployeeButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag5').val(6);
				
				$('#staffName').val(row.staffName);
				$('#staffCode').val(row.staffCode);
				$('#staffDept').combobox('setValue',row.staffDept);
				$('#staffState').combobox('setValue',row.staffState);
				$('#oneArchiveMedia5').val('');
				
				$('#oneFileRecordId5').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia5').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidEmployee').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode5').val(row.archiveTypeCode);
				$('#oneArchiveTypeName5').val(row.archiveTypeName);
				$('#oneArchiveCode5').val(row.archiveCode);
				$('#oneArchiveCode5').attr('readOnly',true);
				$('#oneStorePosition5').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle5').val(row.archiveTitle);
				$('#oneArchiveMaker5').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator5').val(row.fileOperator);
				$('#oneFileDate5').datebox('setValue',row.fileDate);
				$('#oneRemark5').val(row.remark);
				$('#oneKeepDate5').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong5').attr('checked',true);
				}else{
					$('#isLong5').attr('checked',false);
				}
				$('#oneKeyWord5').val(row.keyWord);
				$('#oneDestoryDate5').datebox('setValue',row.destoryDate);
				
				$('#isForValidEmployee').attr('disabled',true);
				$('#oneArchiveTypeCode5').attr('readOnly',true);
				$('#oneArchiveTypeName5').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode5').linkbutton('disable');
				$('#oneStorePosition5').combotree('disable');
				$('#oneArchiveTitle5').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentEmployeeDialog').dialog('setTitle','编辑人员档案');
				$('#AddOrEditFileContentEmployeeDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddEmployeeLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddEmployeeButton').css('display','');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag5').val(6);
				
				$('#staffName').val('');
				$('#staffCode').val('');
				$('#staffDept').combobox('setValue','');
				$('#staffState').combobox('setValue','');
				
				$('#oneFileRecordId5').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia5').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidEmployee').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode5').val(row.archiveTypeCode);
				$('#oneArchiveTypeName5').val(row.archiveTypeName);
				$('#oneArchiveCode5').val(row.archiveCode);
				$('#oneArchiveCode5').attr('readOnly',true);
				$('#oneStorePosition5').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle5').val(row.archiveTitle);
				$('#oneArchiveMaker5').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator5').val(row.fileOperator);
				$('#oneFileDate5').datebox('setValue',$('#todayDate').val());
				$('#oneRemark5').val('');
				$('#oneKeepDate5').datebox('setValue','');
				
				$('#isLong5').attr('checked',true);
				
				$('#oneKeyWord5').val('');
				$('#oneDestoryDate5').datebox('setValue','');
				
				$('#isForValidEmployee').attr('disabled',true);
				$('#oneArchiveTypeCode5').attr('readOnly',true);
				$('#oneArchiveTypeName5').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode5').linkbutton('disable');
				$('#oneStorePosition5').combotree('disable');
				$('#oneArchiveTitle5').attr('readOnly',true);
				
				
				
				$('#AddOrEditFileContentEmployeeDialog').dialog('setTitle','追加人员档案');
				$('#AddOrEditFileContentEmployeeDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddEmployeeLabel").innerHTML='';//连续添加不显示
			$('#continueAddEmployeeButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				
				
				$('#staffName').val(row.staffName);
				$('#staffCode').val(row.staffCode);
				$('#staffDept').combobox('setValue',row.staffDept);
				$('#staffState').combobox('setValue',row.staffState);
				
				$('#oneFileRecordId5').val(row.fileRecordId);
				
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia5').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidEmployee').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode5').val(row.archiveTypeCode);
				$('#oneArchiveTypeName5').val(row.archiveTypeName);
				$('#oneArchiveCode5').val(row.archiveCode);
				$('#oneArchiveCode5').attr('readOnly',true);
				$('#oneStorePosition5').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle5').val(row.archiveTitle);
				$('#oneArchiveMaker5').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator5').val(row.fileOperator);
				$('#oneFileDate5').datebox('setValue',row.fileDate);
				$('#oneRemark5').val(row.remark);
				$('#oneKeepDate5').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong5').attr('checked',true);
				}else{
					$('#isLong5').attr('checked',false);
				}
				$('#oneKeyWord5').val(row.keyWord);
				$('#oneDestoryDate5').datebox('setValue',row.destoryDate);
				
				$('#isForValidEmployee').attr('disabled',true);
				$('#oneArchiveTypeCode5').attr('readOnly',true);
				$('#oneArchiveTypeName5').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode5').linkbutton('disable');
				$('#oneStorePosition5').combotree('disable');
				$('#oneArchiveTitle5').attr('readOnly',true);
				
				$('#AddOrEditFileContentEmployeeDialog').dialog('setTitle','查看人员档案');
				$('#AddOrEditFileContentEmployeeDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus5(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag5').attr('readOnly',false);
			
			$('#staffName').attr('readOnly',false);
			$('#staffCode').attr('readOnly',false);
			$('#staffDept').combobox('enable');
			$('#staffState').combobox('enable');
			$('#employeeStatusTR').css('display','none');
			
			$('#oneFileRecordId5').attr('readOnly',false);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneArchiveMedia5').attr('readOnly',false);
			
			
			$('#isForValidEmployee').css('display','');
			$('#isForValidEmployeeLabel').css('display','');
			
			
			$('#oneArchiveTypeCode5').attr('readOnly',true);
			$('#oneArchiveTypeName5').attr('readOnly',true);
			$('#oneArchiveCode5').attr('readOnly',false);
			$('#oneArchiveCode5').attr('readOnly',false);
			$('#oneStorePosition5').combotree('enable');
			$('#oneArchiveTitle5').attr('readOnly',false);
			$('#oneArchiveMaker5').combobox('enable');
			$('#oneFileOperator5').attr('readOnly',false);
			$('#oneFileDate5').datebox('enable');
			$('#oneRemark5').attr('readOnly',false);
			$('#oneKeepDate5').datebox('disable');
			
			$('#oneKeyWord5').attr('readOnly',false);
			$('#oneDestoryDate5').datebox('enable');
			
			$('#isForValidEmployee').attr('disabled',false);
			$('#oneArchiveTypeCode5').attr('readOnly',true);
			$('#oneArchiveTypeName5').attr('readOnly',true);
			$('#chooseOneArchiveTypeCode5').linkbutton('enable');
			$('#oneStorePosition5').combotree('enable');
			$('#oneArchiveTitle5').attr('readOnly',false);
			
			
			$('#isLong5').attr('disabled',false);
			
			
			$('#oneDestoryDate5Label').css('display','none');
			$('#oneDestoryDate5Label2').css('display','none');
			$('#saveButton5').css('display','');
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag5').attr('readOnly',true);
			
			$('#staffName').attr('readOnly',true);
			$('#staffCode').attr('readOnly',true);
			$('#staffDept').combobox('disable');
			$('#staffState').combobox('disable');
			$('#employeeStatusTR').css('display','');
			
			$('#oneFileRecordId5').attr('readOnly',true);
			
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia5').attr('readOnly',true);
			
			
			$('#isForValidEmployee').css('display','none');
			$('#isForValidEmployeeLabel').css('display','none');
			
			$('#oneArchiveTypeCode5').attr('readOnly',true);
			$('#oneArchiveTypeName5').attr('readOnly',true);
			$('#oneArchiveCode5').attr('readOnly',true);
			$('#oneArchiveCode5').attr('readOnly',true);
			$('#oneStorePosition5').combotree('disable');
			$('#oneArchiveTitle5').attr('readOnly',true);
			$('#oneArchiveMaker5').combobox('disable');
			$('#oneFileOperator5').attr('readOnly',true);
			$('#oneFileDate5').datebox('disable');
			$('#oneRemark5').attr('readOnly',true);
			$('#oneKeepDate5').datebox('disable');
			
			$('#oneKeyWord5').attr('readOnly',true);
			$('#oneDestoryDate5').datebox('disable');
			
			$('#isForValidEmployee').attr('disabled',true);
			$('#oneArchiveTypeCode5').attr('readOnly',true);
			$('#oneArchiveTypeName5').attr('readOnly',true);
			$('#chooseOneArchiveTypeCode5').linkbutton('disable');
			$('#oneStorePosition5').combotree('disable');
			$('#oneArchiveTitle5').attr('readOnly',true);
			
			$('#isLong5').attr('disabled',true);
			
			$('#oneDestoryDate5Label').css('display','');
			$('#oneDestoryDate5Label2').css('display','');
			$('#saveButton5').css('display','none');
		}
		
	}
	
	function getEmployeeByName()
	{
		var staffName=$('#staffName').val();
		 $.ajax({
 	 	  	url : sybp()+'/tblFileContentEmployeeAction_getEmployeeByName.action',
 		  	type: 'post',
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	data: {staffName:staffName},
 		  	dataType:'json',
 		  	success:function(r){
			 	if(r&&r.success){
			 		$('#staffCode').val(r.staffCode);
			 		$('#staffDept').combobox("setValue",r.staffDept);
			 		//$('#staffState').val(r.staffState);
			 		if($('#addOrEditEmployee').val()==1)
			 		{
			 			$('#oneArchiveTitle5').val(staffName);
			 		}
			 		
			 	}
		 	}
		 });
		
	}
	//保存
	function saveOneFileContentEmployee(){
		var oneArchiveTypeName = $('#oneArchiveTypeName5').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentEmployeeForm').form('validate') ){
	             var addOrEdit = $('#addOrEditEmployee').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidEmployee').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition5L').val($('#oneStorePosition5').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentEmployeeAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentEmployeeForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	//  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	// contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentEmployeeFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentEmployeeDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	
		        		  	    	  if($('#continueAddEmployeeButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentEmployeeDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId5').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag5').val(6);
			        		  				
			        		  				$('#staffName').val('');
			        		  				$('#staffCode').val('');
			        		  				$('#staffDept').combobox('setValue','');
			        		  				$('#staffState').combobox('setValue','');
			        		  				
			        		  				//$('#oneArchiveTypeCode5').val('');
			        		  				//$('#oneArchiveTypeName5').val('');
			        		  				$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode5').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode5').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				
			        		  				$('#oneStorePosition5').combotree('setValue','');
			        		  				$('#oneArchiveTitle5').val('');
			        		  				//$('#oneArchiveMaker5').combobox('setValue','');
			        		  				//$('#oneFileOperator5').val('');
			        		  				$('#oneFileDate5').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark5').val('');
			        		  				$('#oneKeepDate5').datebox('setValue','');
			        		  				$('#oneKeyWord5').val('');
			        		  				$('#oneDestoryDate5').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateEmployeeRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidEmployee').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentEmployeeAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentEmployeeForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentEmployeeFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentEmployeeDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	
		        		  	    	  if($('#continueAddEmployeeButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentEmployeeDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId5').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag5').val(6);
			        		  				
			        		  				$('#staffName').val('');
			        		  				$('#staffCode').val('');
			        		  				$('#staffDept').combobox('setValue','');
			        		  				$('#staffState').combobox('setValue','');
			        		  				
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
	
	function writeOperateEmployeeRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateEmployeeRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateEmployeeRecord');
				}else {
					writeOperateEmployeeRsn();
				}
			}
	 });
	}
	function afterSignUpdateEmployeeRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidEmployee').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentEmployeeAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentEmployeeForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	 // contentWind.$('#searchRecordButton').click();
   		  	    	
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentEmployeeFrame').contentWindow
	  	    	    var dg = studyFrame.$('#tblFileContentEmployeeDatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
   		  	    	  
   		  	    	  $('#AddOrEditFileContentEmployeeDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchEmployeeRecord(staffDeptLeft,staffStateLeft,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
		if(childWind.$){
			var dg = childWind.$('#tblFileContentEmployeeDatagrid');
			$.ajax({
				url : sybp()+'/tblFileContentEmployeeAction_loadList.action?fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					staffDept:staffDeptLeft,
					staffState:staffStateLeft,
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
	
	function deleteOneEmployee() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteEmployeeRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteEmployeeRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 仪器资料档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateEmployeeRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteEmployeeRecord');
				}else {
					writeDeleteEmployeeRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteEmployeeRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
		var operateRsn=$('#operateEmployeeRsn').val();
		
		var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentEmployeeAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentEmployeeDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneEmployee() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			writeDestroyEmployeeRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyEmployeeRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁   人员档案:'+archiveCode+' 吗？');
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
					$('#operateEmployeeRsn').val(r);
					qm_showQianmingDialog('afterSignDestroyEmployeeRecord');
				}else {
					writeDestroyEmployeeRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});*/
	}
	function afterSignDestroyEmployeeRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentEmployeeFrame').contentWindow;
		//var operateRsn=$('#operateEmployeeRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		
		var row = childWind.$('#tblFileContentEmployeeDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentEmployeeAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentEmployeeDialog').dialog('close');
	   		  	    	  
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
