/****开始********************************************************/

	function newOneSOP(addOrEdit){
		$('#addOrEditSOP').val(addOrEdit);
		setStatus2(addOrEdit);
		var sopflag = $('#sopflag').val();
		var sopFlagStr="";
		if(sopflag==''||sopflag==0){
			sopFlagStr="SOP资料";
		}else{
			sopFlagStr="SOP表格";
		}
		/* 显示Dialog */
		document.getElementById("AddOrEditFileContentSOPDialog2").style.display="block";
		
		if(addOrEdit==1)//新建
		{
			document.getElementById("continueAddSOPLabel").innerHTML='连续添加';//连续添加显示
			$('#continueAddSOPButton').css('display','');
			
			$('#oneFileRecordId2').val('');
			////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			$('#oneArchiveTypeFlag2').val(3);//后面也没有使用这个值，所以可以不用维护
			
			$('#soptypeCode').combotree('setValue','');
			$('#sopcode').val('');
			$('#sopver').val('');
			$('#sopname').val('');
			$('#file').val('');
			$('#fileLabel').val('');
			
			$('#sopeffectiveDate').datebox('setValue',$('#todayDate').val());
			$('#sopinvalidDate').datebox('setValue','');
			
			/*
			$('#oneArchiveTypeCode2').val('');
			$('#oneArchiveTypeName2').val('');
			$('#oneArchiveCode2').val('');
			$('#oneArchiveCode2').attr('readOnly',false);
			$('#oneStorePosition2').combotree('setValue','');
			$('#oneArchiveTitle2').val('');
			$('#oneArchiveMaker2').combobox('setValue','');
			//$('#oneFileOperator2').val('');
			$.ajax({
		 	 	  url : sybp()+'/tblFileIndexAction_getLastFileOperate.action?archiveTypeFlag=3',
		 		  contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		 		  dataType:'json',
		 		  success:function(r){
						if(r){
							$('#oneFileOperator2').val(r.last);
						}
			 	  }
			});
			$('#oneFileDate2').datebox('setValue',$('#todayDate').val());
			$('#oneRemark2').val('');
			$('#oneKeepDate2').datebox('setValue','');
			$('#oneKeyWord2').val('');
			$('#oneDestoryDate2').datebox('setValue','');
			$('#oneArchiveMedia2').val('');
		
			$('#isLong2').attr('checked',true);
			$('#chooseOneArchiveTypeCode2').linkbutton('enable');
			$('#oneStorePosition2').combotree('enable');
			$('#oneArchiveTitle2').attr('readOnly',false);
			*/
		//	$('#isForValidSOP').attr('disabled',false);
			
			$('#AddOrEditFileContentSOPDialog').dialog('setTitle','添加'+sopFlagStr);
			$('#AddOrEditFileContentSOPDialog').dialog('open'); 
			
		}else if(addOrEdit==2){//编辑
			document.getElementById("continueAddSOPLabel").innerHTML='';//连续添加不显示
			$('#continueAddSOPButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可修改！");
			}else if(row.isInvalid==true){
				$.messager.alert("提示框","该SOP已经作废,不可修改！");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag2').val(3);
				$('#soptypeCode').combotree('setText',row.soptypeName);
				//$('#soptypeCode').combotree('setValue',row.soptypeCode);
				$('#sopcode').val(row.sopcode);
				$('#sopver').val(row.sopver);
				$('#sopname').val(row.sopname);
				$('#sopeffectiveDate').datebox('setValue',row.sopeffectiveDate);
				$('#sopinvalidDate').datebox('setValue',row.sopinvalidDate);
				
				$('#oneFileRecordId2').val(row.fileRecordId);
				
				$("input:radio[value="+row.checkItemType+"][name='checkItemType']").attr('checked','true'); 
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia2').val(row.archiveMedia);
				
				if(row.validationFlag==1){
					$('#isForValidSOP').attr('checked',true);
				}
				/*
				$('#oneArchiveTypeCode2').val(row.archiveTypeCode);
				$('#oneArchiveTypeName2').val(row.archiveTypeName);
				$('#oneArchiveCode2').val(row.archiveCode);
				$('#oneArchiveCode2').attr('readOnly',true);
				$('#oneStorePosition2').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle2').val(row.archiveTitle);
				$('#oneArchiveMaker2').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator2').val(row.fileOperator);
				$('#oneFileDate2').datebox('setValue',row.fileDate);
				$('#oneRemark2').val(row.remark);
				$('#oneKeepDate2').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong2').attr('checked',true);
				}else{
					$('#isLong2').attr('checked',false);
				}
				$('#oneKeyWord2').val(row.keyWord);
				$('#oneDestoryDate2').datebox('setValue',row.destoryDate);
				
				//file.outerHTML=file.outerHTML.replace(/value=\w/g,'')
				//alert("value="+document.getElementsByName('sopfile2FileName').val());
				//alert("11="+document.getElementById('file').outerHTML);
				//document.getElementById('file').outerHTML=document.getElementById('file').outerHTML.replace(/value="(*)"/g, 'value='+row.sopfile+'');
				//alert("22=="+document.getElementById('file').outerHTML);
				$('#fileLabel').val(row.sopfile);
				//document.getElementsByName('sopfile2FileName').val(row.sopfile);
				$('#isForValidSOP').attr('disabled',true);
				$('#oneArchiveTypeCode2').attr('readOnly',true);
				$('#oneArchiveTypeName2').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode2').linkbutton('disable');
				$('#oneStorePosition2').combotree('disable');
				$('#oneArchiveTitle2').attr('readOnly',true);
				 */
				
				$('#AddOrEditFileContentSOPDialog').dialog('setTitle','编辑'+sopFlagStr);
				$('#AddOrEditFileContentSOPDialog').dialog('open'); 
			}
		}else if(addOrEdit==3){//追加归档
			document.getElementById("continueAddSOPLabel").innerHTML='连续追加';//连续添加显示
			$('#continueAddSOPButton').css('display','');
			
			//document.getElementById("continueAddLabel").innerHTML='';//连续添加不显示
			//$('#continueAddButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else if(row.destoryDate!=null&&row.destoryDate!=''){
				$.messager.alert("提示框","该档案已经被销毁,不可追加！");
			}else if(row.isInvalid==true){
				$.messager.alert("提示框","该SOP已经作废,不可追加！");
			}else{
				$('#oneArchiveTypeFlag2').val(3);
				
				$('#soptypeCode').combotree('setValue','');
				$('#sopcode').val('');
				$('#sopver').val('');
				$('#sopname').val('');
				$('#sopeffectiveDate').datebox('setValue',$('#todayDate').val());
				$('#sopinvalidDate').datebox('setValue','');
				
				/*if(row.validationFlag==1){
					$('#isForValidSOP').attr('checked',true);
				}*/
				/*
				$('#oneFileRecordId2').val('');
				
				//$("input:radio[value="+row.NoType+"][name='NoType']").attr('checked','true'); 
				//$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				$('#oneArchiveMedia2').val('');
				
				
				
				$('#oneArchiveTypeCode2').val(row.archiveTypeCode);
				$('#oneArchiveTypeName2').val(row.archiveTypeName);
				$('#oneArchiveCode2').val(row.archiveCode);
				$('#oneArchiveCode2').attr('readOnly',true);
				$('#oneStorePosition2').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle2').val(row.archiveTitle);
				$('#oneArchiveMaker2').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator2').val(row.fileOperator);
				$('#oneFileDate2').datebox('setValue',$('#todayDate').val());
				$('#oneRemark2').val('');
				$('#oneKeepDate2').datebox('setValue','');
				
				$('#isLong2').attr('checked',true);
				
				$('#oneKeyWord2').val('');
				$('#oneDestoryDate2').datebox('setValue','');
				$('#file').val('');
				$('#fileLabel').val('');
				
				$('#isForValidSOP').attr('disabled',true);
				$('#oneArchiveTypeCode2').attr('readOnly',true);
				$('#oneArchiveTypeName2').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode2').linkbutton('disable');
				$('#oneStorePosition2').combotree('disable');
				$('#oneArchiveTitle2').attr('readOnly',true);
				*/
				
				$('#AddOrEditFileContentSOPDialog').dialog('setTitle','追加'+sopFlagStr);
				$('#AddOrEditFileContentSOPDialog').dialog('open'); 
			}
			
			
		}else if(addOrEdit==4){//查看
			document.getElementById("continueAddSOPLabel").innerHTML='';//连续添加不显示
			$('#continueAddSOPButton').css('display','none');
			
			var contentWind = document.getElementById('archiveMainFrame').contentWindow;
			var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
			
			var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
			if(row==null||row=='')//没有选择
			{
				$.messager.alert("提示框","请选择一条记录");
			}else{
				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				$('#oneArchiveTypeFlag2').val(3);
				$('#soptypeCode').combotree('setText',row.soptypeName);
				//$('#soptypeCode').combotree('setValue',row.soptypeCode);
				$('#sopcode').val(row.sopcode);
				$('#sopver').val(row.sopver);
				$('#sopname').val(row.sopname);
				$('#sopeffectiveDate').datebox('setValue',row.sopeffectiveDate);
				$('#sopinvalidDate').datebox('setValue',row.sopinvalidDate);
				
				$('#oneFileRecordId2').val(row.fileRecordId);
				
				$("input:radio[value="+row.checkItemType+"][name='checkItemType']").attr('checked','true'); 
				$("input:radio[value="+row.archiveMediaFlag+"][name='tblFileRecord.archiveMediaFlag']").attr('checked','true'); 
				
				if(row.validationFlag==1){
					$('#isForValidSOP').attr('checked',true);
				}
				/*
				$('#oneArchiveMedia2').val(row.archiveMedia);
				
				$('#oneArchiveTypeCode2').val(row.archiveTypeCode);
				$('#oneArchiveTypeName2').val(row.archiveTypeName);
				$('#oneArchiveCode2').val(row.archiveCode);
				$('#oneArchiveCode2').attr('readOnly',true);
				$('#oneStorePosition2').combotree('setValue',row.storePosition);
				$('#oneArchiveTitle2').val(row.archiveTitle);
				$('#oneArchiveMaker2').combobox('setValue',row.archiveMaker);
				$('#oneFileOperator2').val(row.fileOperator);
				$('#oneFileDate2').datebox('setValue',row.fileDate);
				$('#oneRemark2').val(row.remark);
				$('#oneKeepDate2').datebox('setValue',row.keepDate);
				if(row.keepDate==null||row.keepDate==''){
					$('#isLong2').attr('checked',true);
				}else{
					$('#isLong2').attr('checked',false);
				}
				$('#oneKeyWord2').val(row.keyWord);
				$('#oneDestoryDate2').datebox('setValue',row.destoryDate);
				
				$('#oneArchiveTypeCode2').attr('readOnly',true);
				$('#oneArchiveTypeName2').attr('readOnly',true);
				$('#chooseOneArchiveTypeCode2').linkbutton('disable');
				$('#oneStorePosition2').combotree('disable');
				$('#oneArchiveTitle2').attr('readOnly',true);
				//$('#file').val(row.sopfile);
				$('#fileLabel').val(row.sopfile);
				*/
				$('#isForValidSOP').attr('disabled',true);
				
				$('#AddOrEditFileContentSOPDialog').dialog('setTitle','查看'+sopFlagStr);
				$('#AddOrEditFileContentSOPDialog').dialog('open'); 
			}
		}
		
		
	}
	
	function setStatus2(addOrEdit){
		if(addOrEdit==1||addOrEdit==2||addOrEdit==3){
			$('#oneArchiveTypeFlag2').attr('readOnly',false);
			//$('#soptypeCode').combotree('setText',row.soptypeName);
			$('#soptypeCode').combotree('enable');
			$('#sopcode').attr('readOnly',false);
			$('#sopver').attr('readOnly',false);
			$('#sopname').attr('readOnly',false);
			$('#sopeffectiveDate').datebox('enable');
			$('#sopinvalidDate').css('display','none');
			$('#sopinvalidDate').datebox('enable');
			
			
			$("input:radio[name='checkItemType']").attr('disabled',false); 
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',false); 
			$('#oneFileRecordId2').attr('readOnly',false);
			$('#oneArchiveMedia2').attr('readOnly',false);
			
			
		//	$('#isForValidSOP').css('display','');
			//$('#isForValidSOPLabel').css('display','');
			
			/*
			$('#oneArchiveTypeCode2').attr('readOnly',true);
			$('#oneArchiveTypeName2').attr('readOnly',true);
			$('#oneArchiveCode2').attr('readOnly',false);
			$('#oneStorePosition2').combotree('enable');
			$('#oneArchiveTitle2').attr('readOnly',false);
			$('#oneArchiveMaker2').combobox('enable');
			$('#oneFileOperator2').attr('readOnly',false);
			$('#oneFileDate2').datebox('enable');
			$('#oneRemark2').attr('readOnly',false);
			$('#oneKeepDate2').datebox('disable');
			
			$('#oneKeyWord2').attr('readOnly',false);
			$('#oneDestoryDate2').datebox('enable');
			$('#isLong2').attr('disabled',false);
			
			$('#chooseOneArchiveTypeCode2').linkbutton('enable');
			
			$('#oneDestoryDate2Label').css('display','none');
			$('#oneDestoryDate2Label2').css('display','none');
			 */
			$('#saveButton2').css('display','');
			if(addOrEdit==1||addOrEdit==3)
			{
				$('#file').attr('disabled',false);
				$('#fileLabel').attr('disabled',false);
			}else{
				$('#file').attr('disabled',true);
				$('#fileLabel').attr('disabled',true);
			}
		}else if(addOrEdit==4){
			$('#oneArchiveTypeFlag2').attr('readOnly',true);
			//$('#soptypeCode').combotree('setText',row.soptypeName);
			$('#soptypeCode').combotree('disable');
			$('#sopcode').attr('readOnly',true);
			$('#sopver').attr('readOnly',true);
			$('#sopname').attr('readOnly',true);
			$('#sopeffectiveDate').datebox('disable');
			$('#sopinvalidDate').css('display','');
			$('#sopinvalidDate').datebox('disable');
			
			$('#oneFileRecordId2').attr('readOnly',true);
			
			$("input:radio[name='checkItemType']").attr('disabled',true); 
			$("input:radio[name='tblFileRecord.archiveMediaFlag']").attr('disabled',true); 
			$('#oneArchiveMedia2').attr('readOnly',true);
			
			
			$('#isForValidSOP').css('display','none');
			$('#isForValidSOPLabel').css('display','none');
			/*
			$('#oneArchiveTypeCode2').attr('readOnly',true);
			$('#oneArchiveTypeName2').attr('readOnly',true);
			$('#oneArchiveCode2').attr('readOnly',true);
			$('#oneStorePosition2').combotree('disable');
			$('#oneArchiveTitle2').attr('readOnly',true);
			$('#oneArchiveMaker2').combobox('disable');
			$('#oneFileOperator2').attr('readOnly',true);
			$('#oneFileDate2').datebox('disable');
			$('#oneRemark2').attr('readOnly',true);
			$('#oneKeepDate2').datebox('disable');
			
			$('#oneKeyWord2').attr('readOnly',true);
			$('#oneDestoryDate2').datebox('disable');
			
			$('#chooseOneArchiveTypeCode2').linkbutton('disable');
			
			$('#isLong2').attr('disabled',true);
			
			$('#oneDestoryDate2Label').css('display','');
			$('#oneDestoryDate2Label2').css('display','');
			 */
			$('#file').attr('disabled',true);
			$('#fileLabel').attr('disabled',true);
			$('#saveButton2').css('display','none');
		}
		
	}
	
	function getVerByCode(){
		var sopcode = $('#sopcode').val();
		var sopflag = $('#sopflag').val();
		
		 $.ajax({
 	 	  	url : sybp()+'/tblFileContentSOPAction_getVerByCode.action?sopcode='+sopcode+'&sopflag='+sopflag,
 		  	type: 'post',
 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
 		  //	data: $('#oneTblFileContentSOPForm').serialize(),
 		  	dataType:'json',
 		  	success:function(r){
			 	if(r&&r.success)
			 	{
			 		$('#sopver').val(r.sopver);
					$('#sopver').attr('readOnly',false);
					if(r.sopname!=null&&r.sopname!='')
					{
						$('#sopname').val(r.sopname);
						//map.put("soptypeName", sop.getSoptypeName());
						
						$('#soptypeCode').combotree('setValue',r.soptypeCode);
						$('#soptypeCode').combotree('setText',r.soptypeName);
						
						if($('#addOrEditSOP').val()==1){
							/*
							$('#oneArchiveCode2').val(r.archiveCode);
							$('#oneStorePosition2').combotree('setValue',r.storePosition);
							$('#oneArchiveMaker2').combobox('setValue',r.archiveMaker);
							$('#oneArchiveTitle2').val(r.sopname);
							
							$('#oneArchiveTypeCode2').val(r.archiveTypeCode);
							$('#oneArchiveTypeName2').val(r.archiveTypeName);
							//map.put("sopflag", sop.getSopflag());//0sop 1sop表格
							$('#oneRemark2').val(r.remark);
							$('#oneKeyWord2').val(r.keyWord);
							*/
						}
					
					}
					
			 	}else if(r&&!r.success){
			 		$.messager.alert('提示框',r.msg);
			 		//$('#sopcode').focus();
			 	}
			 		
		 	}
		 });
		
	}
	//保存
	function saveOneFileContentSOP(){
		//var oneArchiveTypeName = $('#oneArchiveTypeName2').val();
		//if(oneArchiveTypeName!='')
		//{
		    if( $('#oneTblFileContentSOPForm').form('validate') ){
	             var addOrEdit = $('#addOrEditSOP').val();
	            
	             if(addOrEdit == '1'){//新建
	            	 var validationFlag=0;
	            	 /*var isValidate = $('#isForValidSOP').attr('checked');
	                 if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }*/
	               //  $('#oneStorePosition2L').val($('#oneStorePosition2').combotree('getText'));
	               //文件上传的form
	        		 $('#oneTblFileContentSOPForm').form({    
	        			 	//url : sybp()+'/tblFileContentSOPAction_save.action?validationFlag='+validationFlag,
	        			    type:'post',
	        				dataType:'json',
	        			    onSubmit: function(){    
	        					var isValid = $(this).form('validate');
	        					if (!isValid){
	        						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
	        					}
	        					return isValid;	// 返回false终止表单提交
	        			    },    
	        			    success:function(data){   
	        			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
	        			    	//$.getJSON();
	        			    	var r=$.parseJSON(data);
	        			    	//alert("=转换以后：==form success="+r.success+"==="+r.record);
	        			    	if(r&&r.success){
			        		  	      
		        		  	    	  //var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    	 //contentWind.$('#searchRecordButton').click();
		        		  	    	
			        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
			        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentSOPFrame').contentWindow
			        		  	    	var dg = studyFrame.$('#tblFileContentSOPDatagrid');
			        		  	    	
			        		  	    	var type = dg.treegrid('find',r.typeId);
			        		  	    	
			        		  	    	if(type==null||type=='')
			        		  	    	{
			        		  	    		/*dg.treegrid('append',{
			        		  	    			//parent: node.id,  //  if not assigned, append as root nodes. the node has a 'id' value that defined through 'idField' property
			        		  	    			data: [{
			        		  	    				fileRecordId:r.typeId,
				        		  		            soptypeCode:r.record.soptypeCode,
				        		  		            soptypeName:r.record.soptypeName,
				        		  		            sopcode:'',
				        		  		            sopver:'',
			        		  	    			}]
			        		  	    		});*/
			        		  	    		
				        		  	    	
			        		  	    		var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
			        		  	    		contentWind.searchRecord();
			        		  	    		
			        		  	    	}else{
			        		  	    		dg.treegrid('append',{
			        		  	    			parent: r.typeId,  // the node has a 'id' value that defined through 'idField' property
			        		  	    			data: [r.record],
			        		  	    		});
			        		  	    	}
			        		  	    	
			        		  	    	dg.treegrid('select',r.record.fileRecordId);
			        		  	    	
			        		  	    	
			        		  	    	  if($('#continueAddSOPButton').attr('checked')!='checked')
			        		  	    	  {
			        		  	    		  $('#AddOrEditFileContentSOPDialog').dialog('close');
			        		  	    	  }else {
				        		  	    		$('#oneFileRecordId2').val('');
				        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
				        		  				$('#oneArchiveTypeFlag2').val(3);
				        		  				
				        		  				$('#soptypeCode').combotree('setValue','');
				        						$('#sopcode').val('');
				        						$('#sopver').val('');
				        						$('#sopname').val('');
				        						$('#sopeffectiveDate').datebox('setValue',$('#todayDate').val());
				        						$('#sopinvalidDate').datebox('setValue','');
				        		  				
				        						/*
				        		  				//$('#oneArchiveTypeCode2').val('');
				        		  			//	$('#oneArchiveTypeName2').val('');
				        						$.ajax({
				        		  		 	 	  	url : sybp()+'/tblFileIndexAction_getMaxArchiveCode.action?archiveTypeCode='+$('#oneArchiveTypeCode2').val(),
				        		  		 		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				        		  		 		  	dataType:'json',
				        		  		 		  	success:function(r){
				        		  		 		  	      if(r&&r.archiveCode){
				        		  		 		  	    	 $('#oneArchiveCode2').val(r.archiveCode);//+1
				        		  		 		  	      }
				        		  		 		  	}
				        		  		 		  }); 
				        		  				
				        		  				$('#oneStorePosition2').combotree('setValue','');
				        		  				$('#oneArchiveTitle2').val('');
				        		  				//$('#oneArchiveMaker2').combobox('setValue','');
				        		  				//$('#oneFileOperator2').val('');
				        		  				$('#oneFileDate2').datebox('setValue',$('#todayDate').val());
				        		  				$('#oneRemark2').val('');
				        		  				$('#oneKeepDate2').datebox('setValue','');
				        		  				$('#oneKeyWord2').val('');
				        		  				$('#oneDestoryDate2').datebox('setValue','');
				        		  				*/
			        		  	    	  }
			        		  	    	
		        		  	        }else if(!r.success){
		        		  	            $.messager.alert('提示',r.msg,'info');
		        		  	        }else{
		        		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
		        		  	        }
	        			    	
	        			    } 
	        			});  

	        			
	        		$.messager.progress({title: '请稍后',
	        				msg: '处理中...',text:''});	// 显示进度条
	        		$('#oneTblFileContentSOPForm').attr('action',    
	        			 	sybp()+'/tblFileContentSOPAction_save.action?validationFlag='+validationFlag);
	        		$('#oneTblFileContentSOPForm').submit();
	        		/*
	                 $.ajax({
		        	 	  	url : sybp()+'/tblFileContentSOPAction_save.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentSOPForm').serialize(),
		        		  	dataType:'json',
		        		  	success:function(r){
		        		  	      
		        		  	}
		        		  });*/   
	             }else if(addOrEdit=='2'){//编辑
	            	
	            	 writeOperateSOPRsn();
	            	 
	             }else if(addOrEdit=='3'){//追加归档
	            	 var validationFlag=0;
	            	// var isValidate = $('#isForValidSOP').attr('checked');
	                /* if(isValidate=='checked')
	                 {
	                	 validationFlag=1;
	                 }*/
		            
		            /* $.ajax({
		        	 	  	url : sybp()+'/tblFileContentSOPAction_appendSave.action?validationFlag='+validationFlag,
		        		  	type: 'post',
		        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		        		  	data: $('#oneTblFileContentSOPForm').serialize(),
		        		  	dataType:'json',*/
		        //文件上传的form
			       $('#oneTblFileContentSOPForm').form({    
			    	  // url : sybp()+'/tblFileContentSOPAction_appendSave.action?validationFlag='+validationFlag,
			           type:'post',
			           dataType:'json',
			           onSubmit: function(){    
			        			var isValid = $(this).form('validate');
			        			if (!isValid){
			        				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			        			}
			        			return isValid;	// 返回false终止表单提交
			        	 },    
			        	success:function(data){   
			        			    
			        		$.messager.progress('close');	// 如果提交成功则隐藏进度条
			        			//$.getJSON();
			        		var r=$.parseJSON(data);
		        		  	if(r.success){
			        		  	      //添加datagrid
		        		  	   
//		        		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//		        		  	    	 contentWind.$('#searchRecordButton').click();
		        		  	    	
		        		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		        		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentSOPFrame').contentWindow
		        		  	    	var dg = studyFrame.$('#tblFileContentSOPDatagrid');
		        		  	    	/*dg.datagrid('insertRow',{
		        		  	    		index:0,
		        		  	    		row:r.record,
		        		  	    	});
		        		  	    	dg.datagrid('selectRow',0);*/
		        		  	    	var type = dg.treegrid('find',r.typeId);
		        		  	    	
		        		  	    	if(type==null||type=='')
		        		  	    	{
		        		  	    		/*dg.treegrid('append',{
		        		  	    			//parent: node.id,  //  if not assigned, append as root nodes. the node has a 'id' value that defined through 'idField' property
		        		  	    			data: [{
		        		  	    				fileRecordId:r.typeId,
			        		  		            soptypeCode:r.record.soptypeCode,
			        		  		            soptypeName:r.record.soptypeName,
			        		  		            sopcode:'',
			        		  		            sopver:'',
		        		  	    			}]
		        		  	    		});*/
		        		  	    		var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		        		  	    		contentWind.searchRecord();
					   		  	        
		        		  	    	}else{
		        		  	    		dg.treegrid('append',{
		        		  	    			parent: r.typeId,  // the node has a 'id' value that defined through 'idField' property
		        		  	    			data: [r.record],
		        		  	    		});
		        		  	    		
		        		  	    	}
		        		  	    	
		        		  	    	dg.treegrid('select',r.record.fileRecordId);
		        		  	    	
		        		  	    	/*if($('#file').val()!=''){
	        		  	    	         $.ajaxFileUpload({
	        		  	    		          url:sybp()+'/tblFileContentSOPAction_importSOPFile.action?fileRecordId='+r.record.fileRecordId,      //需要链接到服务器地址
	        		  	    		          secureuri:false,
	        		  	    		          fileElementId:'file',                        //文件选择框的id属性
	        		  	    		          dataType: 'json',                                     //服务器返回的格式，可以是xml
	        		  	    		          success: function (data, status){     
	        		  	    		              if(data&&data.success){
	        		  	    		            	  if(data.fileFileName!=''&&data.fileFileName!=null)
	        		  	    		            	  {
		        		  	    		            		dg.treegrid('update',{
			        		  	  				   		  		id: r.record.fileRecordId,
			        		  	  				   		  		row:r.record,
			        		  	  				   		  	});
	        		  	  				   		  	 
		        		  	    		            		dg.datagrid('updateRow',{
		        				        		  	    		index:0,
		        				        		  	    		row:{
		        				        		  	    			sopfile:data.fileFileName,//不为 空可以
		        				        		  	    		},
		        				        		  	    	}); 
	        		  	    		            	  }
	        		  	    		              }
	        		  	    		          },
	        		  	    		          error: function (data, status, e){
	        		  	    		        	  $.messager.alert('提示框','SOP入档成功，SOP文件上传失败！');
	        		  	    		          }
	        		  	    	      	});
	        		  	    		}*/
		        		  	    	  if($('#continueAddSOPButton').attr('checked')!='checked')
		        		  	    	  {
		        		  	    		  $('#AddOrEditFileContentSOPDialog').dialog('close');
		        		  	    	  }else {
			        		  	    		$('#oneFileRecordId2').val('');
			        		  				////1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
			        		  				$('#oneArchiveTypeFlag2').val(3);
			        		  				
			        		  				$('#soptypeCode').combotree('setValue','');
			        						$('#sopcode').val('');
			        						$('#sopver').val('');
			        						$('#sopname').val('');
			        						$('#sopeffectiveDate').datebox('setValue',$('#todayDate').val());
			        						$('#sopinvalidDate').datebox('setValue','');
			        		  				
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
			       $.messager.progress({title: '请稍后',
       				msg: '处理中...',text:''});	// 显示进度条
			       $('#oneTblFileContentSOPForm').attr('action',    
			    		   sybp()+'/tblFileContentSOPAction_appendSave.action?validationFlag='+validationFlag);
			       $('#oneTblFileContentSOPForm').submit();
	            	 
	             }
	             
	        }else{
	 			$.messager.alert('提示框','表单验证没有通过！');
	 		}
		/*}else{
			$.messager.alert('提示框','请选择档案分类代号');
		}*/
	  
	}
	
	function writeOperateSOPRsn()
	{
		$.messager.prompt('提示框', '请输入修改原因', function(r){
			if(typeof(r)!='undefined')
			{
				if (r){
					$('#operateSOPRsn').val(r);
					qm_showQianmingDialog('afterSignUpdateSOPRecord');
				}else {
					writeOperateRsn();
				}
			}
	 });
	}
	function afterSignUpdateSOPRecord()
	{
		var validationFlag=0;
   	 	//var isValidate = $('#isForValidSOP').attr('checked');
       /* if(isValidate=='checked')
        {
       	 	validationFlag=1;
        }*/
       
      /*  $.ajax({
   	 	  	url : sybp()+'/tblFileContentSOPAction_update.action?validationFlag='+validationFlag,
   		  	type: 'post',
   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
   		  	data: $('#oneTblFileContentSOPForm').serialize(),
   		  	dataType:'json',
   		  	success:function(r){*/
        $('#oneTblFileContentSOPForm').form({    
        	//url : sybp()+'/tblFileContentSOPAction_update.action?validationFlag='+validationFlag,
	           type:'post',
	           dataType:'json',
	           onSubmit: function(){    
	        			var isValid = $(this).form('validate');
	        			if (!isValid){
	        				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
	        			}
	        			return isValid;	// 返回false终止表单提交
	        	 },    
	        	success:function(data){   
	        			    
	        		$.messager.progress('close');	// 如果提交成功则隐藏进度条
	        			//$.getJSON();
	        		var r=$.parseJSON(data);
   		  	      if(r&&r.success){
       		  	      
//   		  	    	  var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
//   		  	    	  contentWind.$('#searchRecordButton').click();
   		  	    	
	   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
	   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentSOPFrame').contentWindow
		  	    	    var dg = studyFrame.$('#tblFileContentSOPDatagrid');
			  	    	//var index = dg.datagrid('getRowIndex',dg.datagrid('getSelected'));
			  	    	/*dg.datagrid('update',{
			  	    		id:r.record.fileRecordId,
			  	    		row:r.record,
			  	    	});*/
			  	    //	dg.datagrid('selectRow',index);
	   		  	    	
	   		  	    	//alert("update typeid="+r.typeId);
		   		  	    if(r.typeId==null||r.typeId==''||typeof(r.typeId)=='undefinded')
		   		  	    {
		   		  	  // alert("update 类别没变的处理="+r.typeId+"==="+r.record.fileRecordId);
		   		  	    	//sop类别没有改变
				   		  	 dg.treegrid('update',{
				   		  		id: r.record.fileRecordId,
				   		  		row:r.record,
				   		  	});
				   		  	 
		   		  	    }else {
		   		  	    	//sop类别改变了
		   		  	   //alert("update 类别变了的处理="+r.typeId+"==="+r.record.fileRecordId);
		   		  	    	
		   		  	   
			   		  	   var type = dg.treegrid('find',r.typeId);
				  	    	
				  	    	if(type==null||type=='')
				  	    	{
				  	    		/*dg.treegrid('append',{
				  	    			//parent: node.id,  //  if not assigned, append as root nodes. the node has a 'id' value that defined through 'idField' property
				  	    			data: [{
				  	    				fileRecordId:r.typeId,
					  		            soptypeCode:r.record.soptypeCode,
					  		            soptypeName:r.record.soptypeName,
					  		            sopcode:'',
					  		            sopver:'',
				  	    			}]
				  	    		});*/
				  	    		var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
				  	    		contentWind.searchRecord();
				  	    		
				  	    	}else{
					  	    	dg.treegrid('remove',r.record.fileRecordId);//先删除，再增加
					  	    	dg.treegrid('append',{
					  	    		parent: r.typeId,  // the node has a 'id' value that defined through 'idField' property
					  	    		data: [r.record],
					  	    	});
				  	    	}
				  	    	dg.treegrid('select',r.record.fileRecordId);
		   		  	    }
   		  	    	
   		  	    	  
   		  	    	  $('#AddOrEditFileContentSOPDialog').dialog('close');
   		  	    	  
   		  	        }else if(!r.success){
   		  	            $.messager.alert('提示',r.msg,'info');
   		  	        }else{
   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
   		  	        }
   		  	}
   		  });   
        $.messager.progress({title: '请稍后',
				msg: '处理中...',text:''});	// 显示进度条
        $('#oneTblFileContentSOPForm').attr('action',    
        		sybp()+'/tblFileContentSOPAction_update.action?validationFlag='+validationFlag);
	    $('#oneTblFileContentSOPForm').submit();
	    
	}
	
	function searchSOPRecord(isAll,isNowValid,isInvalid,needChange,changeEndDate,yearNum,yearNumUnit,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		//var tab = contentWind.$('#researchCheckTabs').tabs('getSelected');
		//var index = contentWind.$('#researchCheckTabs').tabs('getTabIndex',tab);
		var sopflag = 0;
		if(contentWind.$!=null)
		{
			var sopFrame = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow
		 	sopflag = sopFrame.$('input[name="sopflag"]:checked').val();
		}
		//alert(NoType+"=="+isFileDate+"=="+start+"=="+end+"=="+keepEndDate+"=="+isDestory+"=="+isValid+"=="+searchString);
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
		if(childWind.$){
			/*childWind.$('#tblFileContentSOPDatagrid').treegrid({
				//isNowValid,isInvalid,changeEndDate,yearNum,
		    	url : sybp()+'/tblFileContentSOPAction_loadList.action?isAll='+isAll+'&isNowValid='+isNowValid+'&isInvalid='+isInvalid+'&needChange='+needChange+'&changeEndDate='+changeEndDate+'&yearNum='+yearNum+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});*/
			
			var dg = childWind.$('#tblFileContentSOPDatagrid');
			//alert("treegrid page="+dg.treegrid('options').pageNumber+" pageSize="+dg.treegrid('options').pageSize);
			//-1未归档 0归档 其他为全部
			$.ajax({
				url : sybp()+'/tblFileContentSOPAction_loadList.action?fileFlag=1&isAll='+isAll+'&isNowValid='+isNowValid+'&isInvalid='+isInvalid+'&needChange='+needChange+'&changeEndDate='+changeEndDate+'&yearNum='+yearNum+'&yearNumUnit='+yearNumUnit+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {
					searchString:searchString,
					page:dg.treegrid('options').pageNumber,
			 		rows:dg.treegrid('options').pageSize,
			 		sopflag:sopflag,
				},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  		childWind.$('#tblFileContentSOPDatagrid').treegrid('loadData',r);
	   		  	}
		   });
		 }
		
	}
	
	function deleteOneSOP() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else{
			writeDeleteSOPRsn(row.archiveCode,row.fileRecordSn);
			
		}
		
	}
	function writeDeleteSOPRsn(archiveCode,fileRecordSn)
	{
		$.messager.prompt('提示框','确定要删除 SOP档案:'+archiveCode+'，序号：'+fileRecordSn+' 吗？如果确认删除请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateSOPRsn').val(r);
					qm_showQianmingDialog('afterSignDeleteSOPRecord');
				}else {
					writeDeleteSOPRsn(archiveCode,fileRecordSn);
				}
			}
			
		});
	}
	function afterSignDeleteSOPRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
		
		var operateRsn = $('#operateSOPRsn').val();
		var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentSOPAction_delete.action?fileRecordId='+row.fileRecordId ,
	   		  	type: 'post',
	   		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	   		  	data: {operateRsn:operateRsn},
	   		  	dataType:'json',
	   		  	success:function(r){
	   		  	      if(r&&r.success){
		   		  	    	 // var contentWind = document.getElementById('archiveLeftFrame').contentWindow;
		   		  	    	 // contentWind.$('#searchRecordButton').click();
	   		  	    	
		   		  	    	var contentWindMain = document.getElementById('archiveMainFrame').contentWindow;
		   		  	    	var studyFrame = contentWindMain.document.getElementById('tblFileContentSOPFrame').contentWindow
			  	    	    var dg = studyFrame.$('#tblFileContentSOPDatagrid');
		   		  	    	dg.treegrid('remove',r.fileRecordId);
		   		  	    	  
		   		  	    	 $('#AddOrEditFileContentSOPDialog').dialog('close');
	   		  	    	  
	   		  	        }else if(!r.success){
	   		  	            $.messager.alert('提示',r.msg,'info');
	   		  	        }else{
	   		  	              parent.parent.showMessager(3,'与数据库交互异常',true,5000);
	   		  	        }
	   		  	}
	   		  });   
	}
	function destroyOneSOP() {
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
		
		var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
		if(row==null||row=='')//没有选择
		{
			$.messager.alert("提示框","请选择一条记录");
		}else if(row.destoryDate!=null&&row.destoryDate!=''){
			$.messager.alert("提示框","该SOP已经被销毁！");
		}else{
			writeDestroySOPRsn(row.archiveCode,row.fileRecordSn);
		}
		
	}
	function writeDestroySOPRsn(archiveCode,fileRecordSn)
	{
		document.getElementById("otherOperateDialog2").style.display="block";
		$('#otherOperateLabel').html('销毁是对于整个档案，确定要销毁  SOP档案:'+archiveCode+' 吗？');
		$('#otherOperateReason').val('');
		$('#otherOperateDate').datebox('setValue','');
		$('#otherOperateType').val(3);
		$('#otherOperateDialog').dialog('setTitle','销毁档案');
		$('#otherOperateDialog').dialog('open');
		/*$.messager.prompt('提示框','销毁是对于整个档案，确定要销毁 SOP档案:'+archiveCode+' 吗？如果确认销毁请填写原因',function(r){
			if(typeof(r)!='undefined')
			{
				if(r)
				{
					$('#operateSOPRsn').val(r);
					qm_showQianmingDialog('afterSignDestroySOPRecord');
				}else {
					writeDestroySOPRsn(archiveCode,fileRecordSn);
				}
			}
			
		});*/
	}
	function afterSignDestroySOPRecord()
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblFileContentSOPFrame').contentWindow;
		
		//var operateRsn = $('#operateSOPRsn').val();
		var operateRsn = $('#otherOperateReason').val()
		var destoryDate = $('#otherOperateDate').datebox('getValue');
		var row = childWind.$('#tblFileContentSOPDatagrid').treegrid('getSelected');
		 $.ajax({
	   	 	  	url : sybp()+'/tblFileContentSOPAction_destroy.action?fileRecordId='+row.fileRecordId ,
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
	   		  	    	contentWind.searchRecord();
	   		  	    	
	   		  	    	  $('#AddOrEditFileContentSOPDialog').dialog('close');
	   		  	    	  
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
