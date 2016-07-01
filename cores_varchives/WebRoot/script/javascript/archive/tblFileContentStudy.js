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





/****开始********************************************************/

	function newOneStudy(addOrEdit){
		
		$('#addOrEditStudy').val(addOrEdit);
		setStatus(addOrEdit);
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentStudyDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddButton').css('display','');
			
			$('#oneFileRecordId').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag').val(1);
			
			$('#studyNo').val('');
			$('#contractCodeInStudy').val('');
			$('#studyName').val('');
			$('#studySponerName').val('');
			$('#sdname').val('');
			$('#oneArchiveMediaEleCode').val('');
			$('#oneArchiveMedia').val('');
			
			$('#oneArchiveTypeCode').val('');
			$('#oneArchiveTypeName').val('');
			$('#oneArchiveCode').val('');
			$('#oneArchiveCode').attr('readOnly',false);
			$('#oneStorePosition').combotree('setValue','');
			$('#oneArchiveTitle').val('');
			$('#oneArchiveMaker').combobox('setValue','');
			$('#oneFileOperator').val('');
			
			$('#oneFileDate').datebox('setValue',$('#todayDate').val());
			$('#oneRemark').val('');
			$('#oneKeepDate').datebox('setValue','');
			$('#oneKeyWord').val('');
			$('#oneDestoryDate').datebox('setValue','');
			
			$('#isForValidStudy').attr('disabled',false);
			$('#chooseOneArchiveTypeCode').linkbutton('enable');
			$('#oneStorePosition').combotree('enable');
			$('#oneArchiveTitle').attr('readOnly',false);
			
			$('#isLong').attr('checked',true);
			
			$('#AddOrEditFileContentStudyDialog').dialog('setTitle','添加专题资料');
			$('#AddOrEditFileContentStudyDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else{
				$('#oneFileRecordId').val(row.fileRecordId);
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag').val(1);
				
				$('#studyNo').val(row.studyNo);
				$('#contractCodeInStudy').val(row.contractCode);
				$('#studyName').val(row.studyName);
				$('#studySponerName').val(row.studySponerName);
				$('#sdname').val(row.sdname);
				
				$('#oneFileRecordId').val(row.fileRecordId);
				
				$("input:radio[value="+row.studyNoType+"][name='studyNoType']").attr('checked','true'); 
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#archiveMedia').val(row.archiveMedia);
				$('#oneArchiveMediaEleCode').val(row.archiveMediaEleCode);
				
				
				if(row.validationFlag==1){
					$('#isForValidStudy').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode').val(row.archiveTypeCode);
				$('#oneArchiveTypeName').val(row.archiveTypeName);
				$('#oneArchiveCode').val(row.archiveCode);
				$('#oneArchiveCode').attr('readOnly',true);
				$('#oneStorePosition').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle').val(row.archiveTitle);
				$('#oneArchiveMaker').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator').val(row.fileOperator);
				$('#oneFileDate').datebox('setValue',row.fileDate);
				$('#oneRemark').val(row.remark);
				$('#oneKeepDate').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong').attr('checked',true);
				}else{
					$('#isLong').attr('checked',false);
				}
				
				$('#oneKeyWord').val(row.keyWord);
				$('#oneDestoryDate').datebox('setValue',row.destoryDate);
				
				$('#isForValidStudy').attr('disabled',true);
				$('#oneArchiveTypeCode').attr('readOnly',true);
				$('#oneArchiveTypeName').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode').linkbutton('disable');
				$('#oneStorePosition').combotree('disable');
				$('#oneArchiveTitle').attr('readOnly',true);
				
				
				$('#AddOrEditFileContentStudyDialog').dialog('setTitle','编辑专题资料');
				$('#AddOrEditFileContentStudyDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddButton').css('display','');
			
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else{
				$('#oneArchiveTypeFlag').val(1);
				
				$('#studyNo').val('');
				$('#contractCodeInStudy').val('');
				$('#studyName').val('');
				$('#studySponerName').val('');
				$('#sdname').val('');
				$('#oneArchiveMediaEleCode').val('');
				$('#oneArchiveMedia').val('');
				
				$('#oneFileRecordId').val('');
				
				//$("input:radio[value="+row.studyNoType+"][name='studyNoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#archiveMedia').val('');
				
				
				if(row.validationFlag==1){
					$('#isForValidStudy').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode').val(row.archiveTypeCode);
				$('#oneArchiveTypeName').val(row.archiveTypeName);
				$('#oneArchiveCode').val(row.archiveCode);
				$('#oneArchiveCode').attr('readOnly',true);
				$('#oneStorePosition').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle').val(row.archiveTitle);
				$('#oneArchiveMaker').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator').val(row.fileOperator);
				$('#oneFileDate').datebox('setValue',$('#todayDate').val());
				$('#oneRemark').val('');
				$('#oneKeepDate').datebox('setValue','');
				
				$('#oneKeyWord').val('');
				$('#oneDestoryDate').datebox('setValue','');
				
				$('#isForValidStudy').attr('disabled',true);
				$('#oneArchiveTypeCode').attr('readOnly',true);
				$('#oneArchiveTypeName').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode').linkbutton('disable');
				$('#oneStorePosition').combotree('disable');
				$('#oneArchiveTitle').attr('readOnly',true);
				
				
				$('#isLong').attr('checked',true);
				
				$('#AddOrEditFileContentStudyDialog').dialog('setTitle','追加专题资料');
				$('#AddOrEditFileContentStudyDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				$('#oneFileRecordId').val(row.fileRecordId);
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag').val(1);
				
				$('#studyNo').val(row.studyNo);
				$('#studyName').val(row.studyName);
				$('#studySponerName').val(row.studySponerName);
				$('#sdname').val(row.sdname);
				
				$('#oneFileRecordId').val(row.fileRecordId);
				
				$("input:radio[value="+row.studyNoType+"][name='studyNoType']").attr('checked','true'); 
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#archiveMedia').val(row.archiveMedia);
				$('#oneArchiveMediaEleCode').val(row.archiveMediaEleCode);
				
				if(row.validationFlag==1){
					$('#isForValidStudy').attr('checked',true);
				}
				
				$('#oneArchiveTypeCode').val(row.archiveTypeCode);
				$('#oneArchiveTypeName').val(row.archiveTypeName);
				$('#oneArchiveCode').val(row.archiveCode);
				$('#oneArchiveCode').attr('readOnly',true);
				$('#oneStorePosition').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle').val(row.archiveTitle);
				$('#oneArchiveMaker').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator').val(row.fileOperator);
				$('#oneFileDate').datebox('setValue',row.fileDate);
				$('#oneRemark').val(row.remark);
				$('#oneKeepDate').datebox('setValue',row.keepDate);
				
				$('#oneKeyWord').val(row.keyWord);
				$('#oneDestoryDate').datebox('setValue',row.destoryDate);
				
				$('#isForValidStudy').attr('disabled',true);
				$('#oneArchiveTypeCode').attr('readOnly',true);
				$('#oneArchiveTypeName').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode').linkbutton('disable');
				$('#oneStorePosition').combotree('disable');
				$('#oneArchiveTitle').attr('readOnly',true);
				
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong').attr('checked',true);
				}else{
					$('#isLong').attr('checked',false);
				}
				
				$('#AddOrEditFileContentStudyDialog').dialog('setTitle','查看专题资料');
				$('#AddOrEditFileContentStudyDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneFileRecordId').attr('readOnly',false);
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag').attr('readOnly',false);
			
			$('#studyNo').attr('readOnly',false);
			$('#studyName').attr('readOnly',false);
			$('#studySponerName').attr('readOnly',false);
			$('#sdname').attr('readOnly',false);
			
			$('#oneFileRecordId').attr('readOnly',false);
			
			$("input:radio[name='studyNoType']").attr('disabled',false); 
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#archiveMedia').attr('readOnly',false);
			$('#oneArchiveMediaEleCode').attr('readOnly',false);
			
			$('#isForValidStudy').css('display','');
			$('#isForValidStudyLabel').css('display','');
			
			$('#oneArchiveTypeCode').attr('readOnly',true);
			$('#oneArchiveTypeName').attr('readOnly',true);
			
			$('#oneArchiveCode').attr('readOnly',false);
			$('#oneStorePosition').combotree('enable');
			$('#oneArchiveTitle').attr('readOnly',false);
			$('#oneArchiveMaker').combobox('enable');
			$('#oneFileOperator').attr('readOnly',false);
			$('#oneFileDate').datebox('enable');
			$('#oneRemark').attr('readOnly',false);
			$('#oneKeepDate').datebox('disable');
			
			$('#oneKeyWord').attr('readOnly',false);
			$('#oneDestoryDate').datebox('enable');
			$('#contractCodeInStudy').attr('readOnly',false);
			
			$('#chooseOneArchiveTypeCode').linkbutton('enable');
		
			
			$('#isLong').attr('disabled',false);
			
			$('#oneDestoryDateLabel').css('display','none');
			$('#oneDestoryDateLabel2').css('display','none');
			$('#saveButton').css('display','');
		}else if(addOrEdit==4){
			$('#oneFileRecordId').attr('readOnly',true);
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag').attr('readOnly',true);
			
			$('#studyNo').attr('readOnly',true);
			$('#studyName').attr('readOnly',true);
			$('#studySponerName').attr('readOnly',true);
			$('#sdname').attr('readOnly',true);
			
			$('#oneFileRecordId').attr('readOnly',true);
			
			$("input:radio[name='studyNoType']").attr('disabled',true); 
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#archiveMedia').attr('readOnly',true);
			$('#oneArchiveMediaEleCode').attr('readOnly',true);
			
			$('#isForValidStudy').css('display','none');
			$('#isForValidStudyLabel').css('display','none');
			
			$('#oneArchiveTypeCode').attr('readOnly',true);
			$('#oneArchiveTypeName').attr('readOnly',true);
			$('#oneArchiveCode').attr('readOnly',true);
			$('#oneStorePosition').combotree('disable');
			$('#oneArchiveTitle').attr('readOnly',true);
			$('#oneArchiveMaker').combobox('disable');
			$('#oneFileOperator').attr('readOnly',true);
			$('#oneFileDate').datebox('disable');
			$('#oneRemark').attr('readOnly',true);
			$('#oneKeepDate').datebox('disable');
			
			$('#oneKeyWord').attr('readOnly',true);
			$('#oneDestoryDate').datebox('disable');
			
			$('#contractCodeInStudy').attr('readOnly',true);
			$('#chooseOneArchiveTypeCode').linkbutton('disable');
		
			
			$('#isLong').attr('disabled',true);
			
			$('#oneDestoryDateLabel').css('display','');
			$('#oneDestoryDateLabel2').css('display','');
			$('#saveButton').css('display','none');
		}
		
	}
	
	function selectStudyType() {
		var studyNoType = $('input[name="studyNoType"]:checked').val();
		if(studyNoType==1){
			$('#studyNoLabel').html("专题编号");
			$('#studyNameLabel').html("专题名称");
			$('#studySponerNameLabel').html("委托单位");
		}else{
			$('#studyNoLabel').html("合同编号");
			$('#studyNameLabel').html("合同名称");
			$('#studySponerNameLabel').html("委托单位");
		}
	}
	function getStudyName()
	{
		var studyNo=$('#studyNo').val();
		var studyNoType = $('input[name="studyNoType"]:checked').val();
		 $.ajax({
 	 	  	url : sybp()+'/tblFileContentStudyAction_getStudyNameByStudyNo.action?studyNo='+studyNo+'&studyNoType='+studyNoType,
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  	//data: $('#oneTblFileContentStudyForm').serialize(),
 		  	dataType:'json',
 		  	success:function(r){
 		  	      if(r&&r.success){
 		  	    	  $('#studyName').val(r.studyNoName);
 		  	    	  $('#studySponerName').val(r.studySponerName);
 		  	    	  $('#sdname').val(r.SDName);
 		  	    	  $('#contractCodeInStudy').val(r.contractCode);
 		  	    	  if($('#addOrEditStudy').val()==1)
 		  	    	  {
 		  	    	    $('#oneArchiveTitle').val(r.studyNoName);
 		  	    	  }
 		  	    	  if($('#oneFileOperator').val()==''){
 		  	    		  $('#oneFileOperator').val(r.SDName);
 		  	    	  }
 		  	    	
 		  	        }else if(!r.success){
 		  	            $.messager.alert('提示',r.msg,'info');
 		  	        }else{
 		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
 		  	        }
 		  	}
 		  });   
		
		
	}
	
	//保存
	function saveOneFileContentStudy(){
		var oneArchiveTypeName = $('#oneArchiveTypeName').val();
		if(oneArchiveTypeName!='')
		{
		    if( $('#oneTblFileContentStudyForm').form('validate') ){
	             var addOrEdit = $('#addOrEditStudy').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidStudy').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
	                 $('#oneStorePositionL').val($('#oneStorePosition').combotree('getText'));
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentStudyAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentStudyForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentStudyDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	  
		        		  	    	//var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	//contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentStudyFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentStudyDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	
		        		  	    	  if($('#continueAddButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentStudyDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag').val(1);
			        		  				
			        		  				$('#studyNo').val('');
			        		  				$('#contractCodeInStudy').val('');
			        		  				$('#studyName').val('');
			        		  				$('#studySponerName').val('');
			        		  				$('#sdname').val('');
			        		  				
			        		  				//$('#oneArchiveTypeCode').val('');
			        		  				//$('#oneArchiveTypeName').val('');
			        		  				$.ajax({
			        		  		 	 	  	url : sybp()+'/tblFileContentStudyAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode').val(),
			        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			        		  		 		  	dataType:'json',
			        		  		 		  	success:function(r){
			        		  		 		  	      if(r&&r.archiveCode){
			        		  		 		  	    	 $('#oneArchiveCode').val(r.archiveCode);//+1
			        		  		 		  	      }
			        		  		 		  	}
			        		  		 		  }); 
			        		  				$('#oneStorePosition').combotree('setValue','');
			        		  				$('#oneArchiveTitle').val('');
			        		  				//$('#oneArchiveMaker').combobox('setValue','');
			        		  				//$('#oneFileOperator').val('');
			        		  				$('#oneFileDate').datebox('setValue',$('#todayDate').val());
			        		  				$('#oneRemark').val('');
			        		  				$('#oneKeepDate').datebox('setValue','');
			        		  				$('#oneKeyWord').val('');
			        		  				$('#oneDestoryDate').datebox('setValue','');
		        		  	    	  }
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
		        		  	}
		        		  });   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	 var isValidate = $('#isForValidStudy').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }
		            
		             $.ajax({
		        	 	  	url : sybp()+'/tblFileContentStudyAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentStudyForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      if(r.success){
			        		  	      //添加datagrid
		        		  	    //	$('#tblFileContentStudyDatagrid').datagrid('appendRow',{
		        		  	    		
		        		  	    //	});
		        		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	// contentWind.$('#searchRecordButton').click();
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentStudyFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentStudyDatagrid');
		        		  	    	dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);
		        		  	    	
		        		  	    	  if($('#continueAddButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentStudyDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag').val(1);
			        		  				
			        		  				$('#studyNo').val('');
			        		  				$('#contractCodeInStudy').val('');
			        		  				$('#studyName').val('');
			        		  				$('#studySponerName').val('');
			        		  				$('#sdname').val('');
			        		  				
			        		  				$('#oneArchiveTypeCode').val('');
			        		  				$('#oneArchiveTypeName').val('');
			        		  				$('#oneArchiveCode').val('');
			        		  				$('#oneStorePosition').combotree('setValue','');
			        		  				$('#oneArchiveTitle').val('');
			        		  				$('#oneArchiveMaker').combobox('setValue','');
			        		  				$('#oneFileOperator').val('');
			        		  				$('#oneFileDate').datebox('setValue','');
			        		  				$('#oneRemark').val('');
			        		  				$('#oneKeepDate').datebox('setValue','');
			        		  				$('#oneKeyWord').val('');
			        		  				$('#oneDestoryDate').datebox('setValue','');
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
	
	function writeOperateRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateStudyRecord');
				}else {
					writeOperateRsn();
				}
			}
	 });
	}
	function afterSignUpdateStudyRecord()
	{
		var validationFlag=0;
   	 	var isValidate = $('#isForValidStudy').attr('checked');
        if(isValidate=='checked')
        {
       	 validationFlag=1;
        }
       
        $.ajax({
   	 	  	url : sybp()+'/tblFileContentStudyAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentStudyForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){
   		  	      if(r.success){
       		  	      
   		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
   		  	    	 // contentWind.$('#searchRecordButton').click();
   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentStudyFrame').contentWindow
		  	    	var dg = studyFrame.$('#tblFileContentStudyDatagrid');
		  	    	var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
		  	    	dg.datagrid('updateRow',{
		  	    		index:index,
		  	    		row:r.record,
		  	    	});
		  	    	dg.datagrid('selectRow',index);
		  	    	
   		  	    	  $('#AddOrEditFileContentStudyDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
		
	}
	
	function searchStudyRecord(studyNoType,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		//alert(studyNoType+"=="+isFileDate+"=="+start+"=="+end+"=="+keepEndDate+"=="+isDestory+"=="+isValid+"=="+searchString);
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileContentStudyDatagrid').datagrid({
		    		//fileStartDate,fileEndDate,keepEndDate,isDestory,isValid,searchString
				url : sybp()+'/tblFileContentStudyAction_loadList.action?studyNoType='+studyNoType+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			//childWind.$('#tblFileContentStudyDatagrid').datagrid('reload');
			var dg = childWind.$('#tblFileContentStudyDatagrid');
			$.ajax({
				url : sybp()+'/tblFileContentStudyAction_loadList.action?studyNoType='+studyNoType+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					page:dg.datagrid('options').pageNumber,
			 		rows:dg.datagrid('options').pageSize,
			 	},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileContentStudyDatagrid').datagrid('loadData',r);
	   		  	}
		   });
		 }
		
	}
	
	function deleteOneStudyRecord() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 专题档案:'+archiveCode+'，序号'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r){
					$('#operateRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteStudyRecord');
				}else {
					writeDeleteRsn(archiveCode,fileRecordSn);
				}
			}
		});
	}
	function afterSignDeleteStudyRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
		
		var operateRsn=$('#operateRsn').val();
		var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentStudyAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r.success){
	   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
	   		  	    	  contentWind.$('#searchRecordButton').click();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentStudyDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneStudy() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该档案已经被销毁！");
		}else{
			writeDestroyRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDestroyRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁 专题档案:'+archiveCode+' 吗？');
		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		
	}
	function afterSignDestroyStudyRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentStudyFrame').contentWindow;
		
	//	var operateRsn=$('#operateRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		var row = childWind.$('#tblFileContentStudyDatagrid').datagrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentStudyAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentStudyDialog').dialog('close');
	   		  	    	  
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
