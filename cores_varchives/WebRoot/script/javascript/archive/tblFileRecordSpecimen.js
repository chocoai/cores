/****开始********************************************************/

	function newOneSpecimen(addOrEdit){
		$('#addOrEditSpecimen').val(addOrEdit);
		setStatus9(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileRecordSpecimenDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddSpecimenLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddSpecimenButton').css('display','');
			
			$('#oneFileRecordId9').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag9').val(10);//后面也没有使用这个值，所以可以不用维护
			
			$("input:radio[value='1'][name='studyNoType3']").attr('checked','true');//默认选择专题
			$('#specimenTypeFlag').combobox('setValue','');
			$('#studyNoSpecimen').val('');
			$('#studyNameSpecimen').val('');
			$('#sdSpecimen').combobox('setValue','');
			$('#fileNum').val('');
			$('#fileNumUnit').combobox('setValue','');
			
			
			$('#oneArchiveTypeCode9').val('');
			$('#oneArchiveTypeName9').val('');
			$('#oneArchiveCode9').val('');
			$('#oneArchiveCode9').attr('readOnly',false);
			$('#oneStorePosition9').combotree('setValue','');
			$('#oneArchiveTitle9').val('');
			$('#oneArchiveMaker9').combobox('setValue','');
			$('#oneFileOperator9').val('');
			$('#oneFileDate9').datebox('setValue',$('#todayDate').val());
			$('#oneRemark9').val('');
			$('#oneKeepDate9').datebox('setValue','');
			$('#oneKeyWord9').val('');
			$('#oneDestoryDate9').datebox('setValue','');
			
			$('#isLong9').attr('checked',true);
			
			$('#isForValidSpecimen').attr('disabled',false);
			$('#chooseOneArchiveTypeCode9').linkbutton('enable');
			$('#oneStorePosition9').combotree('enable');
			$('#oneArchiveTitle9').attr('readOnly',false);
			
			
			$('#AddOrEditFileRecordSpecimenDialog').dialog('setTitle','添加标本管理');
			$('#AddOrEditFileRecordSpecimenDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddSpecimenLabel").innerHTML='';//连续添加不显示
			$('#continueAddSpecimenButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
			
			var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag9').val(10);
				
				$("input:radio[value="+row.studyNoType+"][name='studyNoType3']").attr('checked','true');
				$('#specimenTypeFlag').combobox('setValue',row.specimenTypeFlag);
				$('#studyNoSpecimen').val(row.studyNo);
				$('#studyNameSpecimen').val(row.studyName);
				$('#sdSpecimen').combobox('setValue',row.sd);
				$('#fileNum').val(row.fileNum);
				$('#fileNumUnit').combobox('setValue',row.fileNumUnit);
				
				$('#oneFileRecordId9').val(row.fileRecordId);
				
				
				if(row.validationFlag==1){
					$('#isForValidSpecimen').attr('checked',true);
				}
				$('#oneArchiveTypeCode9').val(row.archiveTypeCode);
				$('#oneArchiveTypeName9').val(row.archiveTypeName);
				$('#oneArchiveCode9').val(row.archiveCode);
				$('#oneArchiveCode9').attr('readOnly',true);
				$('#oneStorePosition9').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle9').val(row.archiveTitle);
				$('#oneArchiveMaker9').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator9').val(row.fileOperator);
				$('#oneFileDate9').datebox('setValue',row.fileDate);
				$('#oneRemark9').val(row.remark);
				$('#oneKeepDate9').datebox('setValue',row.keepDate);
				
				$('#oneKeyWord9').val(row.keyWord);
				$('#oneDestoryDate9').datebox('setValue',row.destoryDate);
				
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong9').attr('checked',true);
				}else{
					$('#isLong9').attr('checked',false);
				}
				$('#isForValidSpecimen').attr('disabled',true);
				$('#oneArchiveTypeCode9').attr('readOnly',true);
				$('#oneArchiveTypeName9').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode9').linkbutton('disable');
				$('#oneStorePosition9').combotree('disable');
				$('#oneArchiveTitle9').attr('readOnly',true);
				
				
				$('#AddOrEditFileRecordSpecimenDialog').dialog('setTitle','编辑标本管理');
				$('#AddOrEditFileRecordSpecimenDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddSpecimenLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddSpecimenButton').css('display','');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
			
			var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag9').val(10);
				
				$("input:radio[value='1'][name='studyNoType3']").attr('checked','true');
				$('#specimenTypeFlag').combobox('setValue','');
				$('#studyNoSpecimen').val('');
				$('#studyNameSpecimen').val('');
				$('#sdSpecimen').combobox('setValue','');
				$('#fileNum').val('');
				$('#fileNumUnit').combobox('setValue','');
				
				$('#oneFileRecordId9').val(row.fileRecordId);
				
				
				if(row.validationFlag==1){
					$('#isForValidSpecimen').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode9').val(row.archiveTypeCode);
				$('#oneArchiveTypeName9').val(row.archiveTypeName);
				$('#oneArchiveCode9').val(row.archiveCode);
				$('#oneArchiveCode9').attr('readOnly',true);
				$('#oneStorePosition9').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle9').val(row.archiveTitle);
				$('#oneArchiveMaker9').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator9').val(row.fileOperator);
				$('#oneFileDate9').datebox('setValue',$('#todayDate').val());
				$('#oneRemark9').val('');
				$('#oneKeepDate9').datebox('setValue','');
				
				$('#isLong9').attr('checked',true);
				
				$('#oneKeyWord9').val('');
				$('#oneDestoryDate9').datebox('setValue','');
				
				$('#isForValidSpecimen').attr('disabled',true);
				$('#oneArchiveTypeCode9').attr('readOnly',true);
				$('#oneArchiveTypeName9').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode9').linkbutton('disable');
				$('#oneStorePosition9').combotree('disable');
				$('#oneArchiveTitle9').attr('readOnly',true);
				
				
				$('#AddOrEditFileRecordSpecimenDialog').dialog('setTitle','追加标本管理');
				$('#AddOrEditFileRecordSpecimenDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddSpecimenLabel").innerHTML='';//连续添加不显示
			$('#continueAddSpecimenButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
			
			var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag9').val(10);
				
				$("input:radio[value="+row.studyNoType+"][name='studyNoType3']").attr('checked','true');
				$('#specimenTypeFlag').combobox('setValue',row.specimenTypeFlag);
				$('#studyNoSpecimen').val(row.studyNo);
				$('#studyNameSpecimen').val(row.studyName);
				$('#sdSpecimen').combobox('setValue',row.sd);
				$('#fileNum').val(row.fileNum);
				$('#fileNumUnit').combobox('setValue',row.fileNumUnit);
				
				$('#oneFileRecordId9').val(row.fileRecordId);
				
				
				if(row.validationFlag==1){
					$('#isForValidSpecimen').attr('checked',true);
				}
				$('#oneArchiveTypeCode9').val(row.archiveTypeCode);
				$('#oneArchiveTypeName9').val(row.archiveTypeName);
				$('#oneArchiveCode9').val(row.archiveCode);
				$('#oneArchiveCode9').attr('readOnly',true);
				$('#oneStorePosition9').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle9').val(row.archiveTitle);
				$('#oneArchiveMaker9').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator9').val(row.fileOperator);
				$('#oneFileDate9').datebox('setValue',row.fileDate);
				$('#oneRemark9').val(row.remark);
				$('#oneKeepDate9').datebox('setValue',row.keepDate);
				
				$('#oneKeyWord9').val(row.keyWord);
				$('#oneDestoryDate9').datebox('setValue',row.destoryDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong9').attr('checked',true);
				}else{
					$('#isLong9').attr('checked',false);
				}
				$('#isForValidSpecimen').attr('disabled',true);
				$('#oneArchiveTypeCode9').attr('readOnly',true);
				$('#oneArchiveTypeName9').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode9').linkbutton('disable');
				$('#oneStorePosition9').combotree('disable');
				$('#oneArchiveTitle9').attr('readOnly',true);
				
				
				$('#AddOrEditFileRecordSpecimenDialog').dialog('setTitle','查看标本管理');
				$('#AddOrEditFileRecordSpecimenDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus9(addOrEdit)
	{
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag9').attr('readOnly',false);
			
			$("input:radio[name='studyNoType3']").attr('disabled',false);
			$('#specimenTypeFlag').combobox('enable');
			$('#studyNoSpecimen').attr('readOnly',false);
			$('#studyNameSpecimen').attr('readOnly',false);
			$('#sdSpecimen').combobox('enable');
			$('#fileNum').attr('readOnly',false);
			$('#fileNumUnit').combobox('enable');
			
			$('#oneFileRecordId9').attr('readOnly',false);
					
			$('#isForValidSpecimen').css('display','');
			$('#isForValidSpecimenLabel').css('display','');
			
			$('#oneArchiveTypeCode9').attr('readOnly',true);
			$('#oneArchiveTypeName9').attr('readOnly',true);
			$('#oneArchiveCode9').attr('readOnly',false);
			$('#oneStorePosition9').combotree('enable');
			$('#oneArchiveTitle9').attr('readOnly',false);
			$('#oneArchiveMaker9').combobox('enable');
			$('#oneFileOperator9').attr('readOnly',false);
			$('#oneFileDate9').datebox('enable');
			$('#oneRemark9').attr('readOnly',false);
			$('#oneKeepDate9').datebox('disable');
			
			$('#isLong9').attr('disabled',false);
			
			$('#oneKeyWord9').attr('readOnly',false);
			$('#oneDestoryDate9').datebox('enable');
		
			$('#chooseOneArchiveTypeCode9').linkbutton('enable');
		
			$('#oneDestoryDate9Label').css('display','none');
			$('#oneDestoryDate9Label2').css('display','none');
			$('#saveButton9').css('display','');
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag9').attr('readOnly',true);
			
			$("input:radio[name='studyNoType3']").attr('disabled',true);
			$('#specimenTypeFlag').combobox('disable');
			$('#studyNoSpecimen').attr('readOnly',true);
			$('#studyNameSpecimen').attr('readOnly',true);
			$('#sdSpecimen').combobox('disable');
			$('#fileNum').attr('readOnly',true);
			$('#fileNumUnit').combobox('disable');
			
			$('#oneFileRecordId9').attr('readOnly',true);
					
			$('#isForValidSpecimen').css('display','none');
			$('#isForValidSpecimenLabel').css('display','none');
			
			$('#oneArchiveTypeCode9').attr('readOnly',true);
			$('#oneArchiveTypeName9').attr('readOnly',true);
			$('#oneArchiveCode9').attr('readOnly',true);
			$('#oneStorePosition9').combotree('disable');
			$('#oneArchiveTitle9').attr('readOnly',true);
			$('#oneArchiveMaker9').combobox('disable');
			$('#oneFileOperator9').attr('readOnly',true);
			$('#oneFileDate9').datebox('disable');
			$('#oneRemark9').attr('readOnly',true);
			$('#oneKeepDate9').datebox('disable');
			
			$('#oneKeyWord9').attr('readOnly',true);
			$('#oneDestoryDate9').datebox('disable');
		
			$('#chooseOneArchiveTypeCode9').linkbutton('disable');
			
			$('#isLong9').attr('disabled',true);
			
			$('#oneDestoryDate9Label').css('display','');
			$('#oneDestoryDate9Label2').css('display','');
			$('#saveButton9').css('display','none');
		}
	}
	
	function getSpecimenByCode()
	{
		var studyNo=$('#studyNoSpecimen').val();
		var studyNoType = $('input[name="studyNoType3"]:checked').val();
		
		$.ajax({
	   	 	  	//url : sybp()+'/tblFileRecordSpecimenAction_getSpecimenByCode.action?studyNoType='+studyNoType+'&studyNo='+studyNo ,
	   		  	url: sybp()+'/tblFileContentStudyAction_getStudyNameByStudyNo.action?studyNoType='+studyNoType+'&studyNo='+studyNo ,
				type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	//data: $('#oneTblFileContentForm').serialize(),
	   		  	dataType:'json',
	   		  	success:function(r){
			 		if(r&&r.success){
							$('#studyNameSpecimen').val(r.studyNoName);
							$('#sdSpecimen').combobox('setValue',r.SDName);
							if($('#addOrEditSpecimen').val()==1)
							{
								$('#oneArchiveTitle9').val(r.studyNoName);
							}
							if($('#oneFileOperator9').val()==''){
								$('#oneFileOperator9').val(r.SDName);
							}
							
			 		}else{
			 			$('#studyNameSpecimen').val('');
						$('#sdSpecimen').combobox('setValue','');
						
			 		}
		 		}
		 	});
		 	
	}
	function openSearchSpecimenCodeDialog()
	{
		/* 显示Dialog */
		document.getElementById("searchSpecimenDialog2").style.display="block";
		$('#sliceCodeDatagrid').datagrid('loadData',[]);
		//$('#sliceAnimalDatagrid').datagrid('loadData',[]);
		$('#searchSpecimenCodeStudyNo').val('');
		
		$('#searchSpecimenDialog').dialog('setTitle','标本编号速查');
		$('#searchSpecimenDialog').dialog('open'); 
		
	}
	function getAnimalSpecimenByStudyNo(){
		var studyNoForS=$('#searchSpecimenCodeStudyNo').val();
		//getSliceAnimalByStudyNo
		//getSliceSpecimenByStudyNo
		/*$('#sliceAnimalDatagrid').datagrid({
			url : sybp()+'/tblFileRecordSpecimenAction_getSliceAnimalByStudyNo.action?studyNo='+studyNoForS,
			
		});*/
		var	type = $('input[name=specimenFrom]:checked').val();
		var specimenSearchCon = $('#specimenSearchCon').searchbox('getValue');
		if(type==1)
		{
			$('#sliceCodeDatagrid').datagrid({
				url : sybp()+'/tblFileRecordSpecimenAction_getSliceSpecimenByStudyNo.action?studyNo='+studyNoForS,
				
			});
		}else{
			
			 $.ajax({
				url : sybp()+'/importedSpecimenAction_getSliceSpecimenByStudyNoAndCon.action?studyNo='+studyNoForS,
     		  	type: 'post',
     		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     		  	data: {
				 	searchCon:specimenSearchCon,
			 	},
     		  	dataType:'json',
     		  	success:function(r){
				 
				 	$('#sliceCodeDatagrid').datagrid('loadData',r);
			    }
			 });
			
		}
		
		
	}
	//保存
	function saveOneFileRecordSpecimen(){
		var oneArchiveTypeName = $('#oneArchiveTypeName9').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileRecordSpecimenForm').form('validate') ){
	             var addOrEdit = $('#addOrEditSpecimen').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidSpecimen').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePosition9L').val($('#oneStorePosition9').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileRecordSpecimenAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileRecordSpecimenForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
//		        		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//		        		  	    	 contentWind.$('#searchRecordButton').click();
	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileRecordSpecimenFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileRecordSpecimenDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	  
		        		  	    	  if($('#continueAddSpecimenButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileRecordSpecimenDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId9').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag9').val(10);
			        		  				
			        		  				$("input:radio[value='1'][name='studyNoType3']").attr('checked','true');
			        						$('#specimenTypeFlag').combobox('setValue','');
			        						$('#studyNoSpecimen').val('');
			        						$('#studyNameSpecimen').val('');
			        						$('#sdSpecimen').combobox('setValue','');
			        						$('#fileNum').val('');
			        						$('#fileNumUnit').combobox('setValue','');
			        		  				
			        		  				//$('#oneArchiveTypeCode9').val('');
			        		  				//$('#oneArchiveTypeName9').val('');
			        						$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode9').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode9').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition9').combotree('setValue','');
			        		  				$('#oneArchiveTitle9').val('');
			        		  				//$('#oneArchiveMaker9').combobox('setValue','');
			        		  				//$('#oneFileOperator9').val('');
			        		  				$('#oneFileDate9').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark9').val('');
			        		  				$('#oneKeepDate9').datebox('setValue','');
			        		  				$('#oneKeyWord9').val('');
			        		  				$('#oneDestoryDate9').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateSpecimenRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidSpecimen').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileRecordSpecimenAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileRecordSpecimenForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
//		        		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//		        		  	    	 contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileRecordSpecimenFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileRecordSpecimenDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	
		        		  	    	  if($('#continueAddSpecimenButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileRecordSpecimenDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId9').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag9').val(10);
			        		  				
			        		  				//默认选择专题
			        		  				$("input:radio[value='1'][name='studyNoType3']").attr('checked','true');
			        						$('#specimenTypeFlag').combobox('setValue','');
			        						$('#studyNoSpecimen').val('');
			        						$('#studyNameSpecimen').val('');
			        						$('#sdSpecimen').combobox('setValue','');
			        						$('#fileNum').val('');
			        						$('#fileNumUnit').combobox('setValue','');
			        		  				
			        		  				
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
	
	function writeOperateSpecimenRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateSpecimenRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateSpecimenRecord');
				}else {
					writeOperateSpecimenRsn();
				}
			}
	 });
	}
	function afterSignUpdateSpecimenRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidSpecimen').attr('checked');
        if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileRecordSpecimenAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileRecordSpecimenForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
//   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//   		  	    	  contentWind.$('#searchRecordButton').click();
   		  	    	
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
   			  	    var studyFrame = contentWindMain.document.getElementById('tblFileRecordSpecimenFrame').contentWindow
   	  	    	    var dg = studyFrame.$('#tblFileRecordSpecimenDatagrid');
   		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
   		  	    	dg.datagrid('updateRow',{
   		  	    		index:index,
   		  	    		row:r.record,
   		  	    	});
   		  	    	dg.datagrid('selectRow',index);
   		  	    	  
   		  	    	  $('#AddOrEditFileRecordSpecimenDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchSpecimenRecord(isDestroySpecimen,type,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileRecordSpecimenDatagrid').datagrid({
		    	url : sybp()+'/tblFileRecordSpecimenAction_loadList.action?specimenTypeFlag='+type+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			var dg = childWind.$('#tblFileRecordSpecimenDatagrid');
			$.ajax({
				url : sybp()+'/tblFileRecordSpecimenAction_loadList.action?specimenTypeFlag='+type+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&isDestroySpecimen='+isDestroySpecimen,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					page:dg.datagrid('options').pageNumber,
			 		rows:dg.datagrid('options').pageSize,
				},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('loadData',r);
	   		  	}
		   });
		 }
			 
		
	}
	
	function deleteOneSpecimen() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
		
		var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteSpecimenRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteSpecimenRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 标本档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateSpecimenRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteSpecimenRecord');
				}else {
					writeDeleteSpecimenRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});
	}
	function afterSignDeleteSpecimenRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
		
		var operateRsn=$('#operateSpecimenRsn').val();
		var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileRecordSpecimenAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileRecordSpecimenDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneSpecimen() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
		
		var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			$('#destroyType').val(1);
			writeDestroySpecimenRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function destroyOneSpecimenContent() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
		
		var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.specimenDestoryDate!=null&&row.specimenDestoryDate!=''){
			$.messager.alert("提示框","该标本已经被销毁！");
		}else{
			$('#destroyType').val(2);
			writeDestroySpecimenRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroySpecimenRsn(archiveCode,fileRecordSn)
	{
		var type = $('#destroyType').val();
		var str = "销毁是对于整个档案，确定要销毁 标本档案:"+archiveCode+" 吗？";
		if(type!=''&&(type==2||type=='2')){
			str = "销毁的是标本，不是标本档案，确定要销毁吗？";
			document.getElementById("otherOperateDialog2").style.display="block";
			$('#otherOperateLabel').html(str);
			$('#otherOperateReason').val('');
			$('#otherOperateDate').datebox('setValue','');
			$('#otherOperateType').val(6);
			$('#otherOperateDialog').dialog('setTitle','销毁标本');
			$('#otherOperateDialog').dialog('open');
		}else {
			document.getElementById("otherOperateDialog2").style.display="block";
			$('#otherOperateLabel').html(str);
			$('#otherOperateReason').val('');
			$('#otherOperateDate').datebox('setValue','');
			$('#otherOperateType').val(3);
			$('#otherOperateDialog').dialog('setTitle','销毁档案');
			$('#otherOperateDialog').dialog('open');
			
		}
		/*
		$.messager.prompt('提示框',str,function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateSpecimenRsn').val(r);
					qm_showQianmingDialog('afterSignDestroySpecimenRecord');
				}else {
					writeDestroySpecimenRsn(archiveCode,fileRecordSn);
				}
			}else {
				
				//alert('点击取消3');
			}
			
			
		});*/
	}
	function afterSignDestroySpecimenRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileRecordSpecimenFrame').contentWindow;
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		
		var row = childWind.$('#tblFileRecordSpecimenDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileRecordSpecimenAction_destroy.action?fileRecordId='+row.fileRecordId+'&destroyType='+$('#destroyType').val() ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileRecordSpecimenDialog').dialog('close');
	   		  	    	  
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
