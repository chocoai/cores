var testitemEditRsn;


/**显示Dialog*/

function showTestItemAddEditDialog(clickName,addOrEdit,title,selectId){
	//加载供试品类型下拉选
	testItemtICodeLoadCombobox();
	//厂家名称
	//$('#testItemvenderName').val($('#testItemsponsorName').val());
	/*签名Dialog*/
	document.getElementById("testItemAddEditDialog2").style.display="block";
	$('#testItemAddOrEdit').val(addOrEdit);
	$('#testItemAddEditDialog').dialog('setTitle',title);
	
	//初始化 含量/浓度/纯度 下拉框
	//initTestItemContentLabelCombobox();
	
	//初始化 含量/浓度/纯度 下拉框
	initTestItemContentCombobox();
	
	//初始化 外观/状态  下拉框
	initPhysicalCombobox();
	
	//初始化存储条件  下拉框
	initStorageConditionCombobox();
	
	//稳定性与均一性分析   下框
	initanalysisCombobox();
	//样品检测后处理  下拉框
	initpostTreatmentCombobox();
	//留样量单位 下拉框
	initreserveUnitCombobox();
	
	var queren = $('#queren').val();
	 if(addOrEdit == 'confirm'){
		 setFormReadOnly(false);
		 
		 $('#testItemsaveDialogButton').css('display','');
		 $('#editsaveDialogButton').css('display','none');
		 
		 $('#tblTestItemtiNo').attr('disabled','true');
		 $('#testItemtICode').combobox('disable');
		 
		 $('#tblTestItemcontent').combobox('enable');
		 $('#tblTestItemcontentLabel').combobox('enable');
		 $('#tblTestItemphysical').combobox('enable');
		 $('#tblTestItemstorageCondition').combobox('enable');
		 if(queren == "false"){
	     	 $('#testItemsaveDialogButton').linkbutton('disable');
	     	 
	     	$('#tblTestItemreserveNum').hide();
	     	$('#tblTestItemreserveUnit2').hide();
	     	$('#reserveNum2').show();
	     	 
	     }else{
	        $('#testItemsaveDialogButton').linkbutton('enable');
	        
	        $('#tblTestItemreserveNum').show();
	     	$('#tblTestItemreserveUnit2').show();
	     	$('#reserveNum2').hide();
	     }
		 //$('#readonlytestItemAddEdit').css('display','none');
	 }else if(addOrEdit == 'edit'){
		 setFormReadOnly(false);
		 
		 $('#testItemsaveDialogButton').css('display','none');
		 $('#editsaveDialogButton').css('display','');
		 
		 $('#tblTestItemtiNo').attr('disabled','true');
		 $('#testItemtICode').combobox('disable');
		 
		 $('#tblTestItemcontent').combobox('enable');
		 $('#tblTestItemcontentLabel').combobox('enable');
		 $('#tblTestItemphysical').combobox('enable');
		 $('#tblTestItemstorageCondition').combobox('enable');
		 if(queren == "false"){
	     	 $('#testItemsaveDialogButton').linkbutton('disable');
	     	 $('#editsaveDialogButton').linkbutton('disable');
	     	 
	     	$('#tblTestItemreserveNum').hide();
	     	$('#tblTestItemreserveUnit2').hide();
	     	$('#reserveNum2').show();
	     	 
	     }else{
	    	 
	        $('#testItemsaveDialogButton').linkbutton('enable');
	        $('#editsaveDialogButton').linkbutton('enable');
	        
	        $('#tblTestItemreserveNum').show();
	     	$('#tblTestItemreserveUnit2').show();
	     	$('#reserveNum2').hide();
	     }
		 //$('#readonlytestItemAddEdit').css('display','none');
	 }else{
		 setFormReadOnly(true);
		 
		 $('#testItemsaveDialogButton').css('display','none');
		 $('#editsaveDialogButton').css('display','');
		 
		 $('#tblTestItemtiNo').attr('disabled','true');
		 $('#testItemtICode').combobox('disable');
		 
		 $('#tblTestItemcontent').combobox('disable');
		 $('#tblTestItemcontentLabel').combobox('disable');
		 $('#tblTestItemphysical').combobox('disable');
		 $('#tblTestItemstorageCondition').combobox('disable');
		 
		 $('#testItemsaveDialogButton').linkbutton('disable');
		 $('#editsaveDialogButton').linkbutton('disable');
		 $('#tblTestItemreserveNum').hide();
	     $('#tblTestItemreserveUnit2').hide();
	     $('#reserveNum2').show();
	       
		 //$('#readonlytestItemAddEdit').css('display','');
	 }
	 //填充
	 testItemtFullEditData(selectId);
	 
	document.getElementById("testItemAddEdit_event").href="javascript:"+clickName+"();";
	
	
	
}

function setFormReadOnly(flag){
	//flag为readonly的值
	
	if(flag)
	{
		$('#tblTestItemcontractCode').attr('readonly',true);
		$('#tblTestItemtiNo').attr('readonly',true);
		$('#tblTestItemtiName').attr('readonly',true);
		
		$('#tblTestItemreserveNum').attr('readonly',true);
		$('#tblTestItemreserveUnit2').attr('readonly',true);
		$('#reserveNum2').attr('readonly',true);
		$('#contractVenderName').attr('readonly',true);
		
		
		$('#testItemvenderName').attr('readonly',true);
		

		$('#tblTestItembatchNo').attr('readonly',true);
		$('#tblTestItemsealNo').attr('readonly',true);
		$('#tblTestItemfileNo').attr('readonly',true);
		$('#tblTestItemmeltPoint').attr('readonly',true);
		$('#tblTestItemboilPoint').attr('readonly',true);
		$('#tblTestItemphotolysis').attr('readonly',true);
		$('#tblTestItemvolatility').attr('readonly',true);
		$('#tblTestItemcomposition').attr('readonly',true);
		$('#tblTestItemdensity').attr('readonly',true);
		$('#tblTestItemwaterSolubility').attr('readonly',true);
		$('#tblTestItemwaterStability').attr('readonly',true);
		$('#tblTestItemsolventSolubility').attr('readonly',true);
		$('#tblTestItemsolventStability').attr('readonly',true);
		$('#tblTestItemph').attr('readonly',true);
		$('#tblTestItemsecurityMeasures').attr('readonly',true);
		$('#tblTestItemanalysis').attr('readonly',true);
		
		$('#tblTestItemcas').attr('readonly',true);
		
		$('#contractVerderButton').linkbutton('disable');
		
		$('#testItemtICode').combobox('disable');

		$('#tblTestItemcontent').attr('disabled',true);
		$('#tblTestItemcontentLabel').attr('disabled',true);
		
		$('#tblTestItemcontent').combobox('disable');
		$('#tblTestItemcontentLabel').combobox('disable');
		$('#tblTestItemphysical').combobox('disable');
		$('#tblTestItemstorageCondition').combobox('disable');
		$('#tblTestItempostTreatment').combobox('disable');
		
		
		$('#tblTestItemvalidityPeriod').datebox('disable');
		$('#isFailureDateFlag').combobox('disable');
		$('#failureDatePrecision').combobox('disable');
		
		$('#testItemsponsorIsVender1').attr('disabled',true);
		$('#testItemsponsorIsVender0').attr('disabled',true);
		
		$('#testItemsponsorIsVenderC1').attr('disabled',true);
		$('#testItemsponsorIsVenderC0').attr('disabled',true);
		
	}else{
		$('#tblTestItemcontractCode').attr('readonly',false);
		$('#tblTestItemtiNo').attr('readonly',false);
		$('#tblTestItemtiName').attr('readonly',false);
		
		$('#tblTestItemreserveNum').attr('readonly',false);
		$('#tblTestItemreserveUnit2').attr('readonly',false);
		$('#reserveNum2').attr('readonly',false);
		$('#contractVenderName').attr('readonly',false);
		
		
		$('#testItemvenderName').attr('readonly',false);
		

		$('#tblTestItembatchNo').attr('readonly',false);
		$('#tblTestItemsealNo').attr('readonly',false);
		$('#tblTestItemfileNo').attr('readonly',false);
		$('#tblTestItemmeltPoint').attr('readonly',false);
		$('#tblTestItemboilPoint').attr('readonly',false);
		$('#tblTestItemphotolysis').attr('readonly',false);
		$('#tblTestItemvolatility').attr('readonly',false);
		$('#tblTestItemcomposition').attr('readonly',false);
		$('#tblTestItemdensity').attr('readonly',false);
		$('#tblTestItemwaterSolubility').attr('readonly',false);
		$('#tblTestItemwaterStability').attr('readonly',false);
		$('#tblTestItemsolventSolubility').attr('readonly',false);
		$('#tblTestItemsolventStability').attr('readonly',false);
		$('#tblTestItemph').attr('readonly',false);
		$('#tblTestItemsecurityMeasures').attr('readonly',false);
		$('#tblTestItemanalysis').attr('readonly',false);
		
		$('#tblTestItemcas').attr('readonly',false);
		
		$('#contractVerderButton').linkbutton('enable');
		
		
	
		$('#testItemtICode').combobox('enable');
		$('#tblTestItemcontent').combobox('enable');
		$('#tblTestItemcontentLabel').combobox('enable');
		$('#tblTestItemphysical').combobox('enable');
		$('#tblTestItemstorageCondition').combobox('enable');
		$('#tblTestItempostTreatment').combobox('enable');
		
		
		$('#tblTestItemvalidityPeriod').datebox('enable');
		$('#isFailureDateFlag').combobox('enable');
		$('#failureDatePrecision').combobox('enable');
		
		$('#testItemsponsorIsVender1').attr('disabled',false);
		$('#testItemsponsorIsVender0').attr('disabled',false);
		
		$('#testItemsponsorIsVenderC1').attr('disabled',false);
		$('#testItemsponsorIsVenderC0').attr('disabled',false);

	}
	
}

//填充数据(或清空数据)
function testItemtFullEditData(r){
	$.ajax({
    		url:sybp()+'/tblTestItemAction_todEidtUI.action',
    		type:'post',
    		cache:false,
    		data:{
		      id:r
    		},
    		dataType:'json',
    		success:function(r){
    			if(r){
    				$('#testItemid').val(r.id);
    				$('#tblTestItemtiNo').val(r.tiNo);
    				$('#testItemtICode').combobox('select',r.tiCode);
    				$('#tblTestItemtiName').val(r.tiName);
    				   if(r.sponsorIsVender == 0){
    			        	document.getElementById('testItemsponsorIsVender0').click();
    			        }else{
    			        	document.getElementById('testItemsponsorIsVender1').click();
    			        }
    				    $('#oldtblTestItemtiNo').val(r.tiNo);
    			        $('#testItemvenderId').val(r.venderId);
    			        $('#testItemvenderName').val(r.venderName);
    			        $('#tblTestItemtiName').val(r.tiName)
    			        
    			        $('#tblTestItemcontent').combobox('select',r.content);
    			        $('#tblTestItemcontentLabel').combobox('select',r.contentLabel);
    			        $('#tblTestItemphysical').combobox('select',r.physical);
    			        
    			        $('#tblTestItemsealNo').val(r.sealNo);
    			        $('#tblTestItemfileNo').val(r.fileNo);
    			        $('#tblTestItemmeltPoint').val(r.meltPoint);
    			        $('#tblTestItemboilPoint').val(r.boilPoint);
    			        $('#tblTestItemphotolysis').val(r.photolysis);
    			        $('#tblTestItemvolatility').val(r.volatility);
    			        $('#tblTestItemcomposition').val(r.composition);
    			        $('#tblTestItemdensity').val(r.density);
    			        $('#tblTestItemwaterSolubility').val(r.waterSolubility);
    			        $('#tblTestItemwaterStability').val(r.waterStability);
    			        $('#tblTestItemsolventSolubility').val(r.solventSolubility);
    			        $('#tblTestItemsolventStability').val(r.solventStability);
    			        $('#tblTestItemph').val(r.ph);
    			        $('#tblTestItemsecurityMeasures').val(r.securityMeasures);
    			        $('#tblTestItemanalysis').combobox('select',r.analysis);
    			        $('#tblTestItempostTreatment').combobox('select',r.postTreatment);
    			        $('#tblTestItemcas').val(r.cas);
    			        
    			        $('#tblTestItemreserveNum').val(r.reserveNum);
    			        $('#tblTestItemreserveUnit').combobox('select',r.reserveUnit);
    			        //留样量2
    			        $('#reserveNum2').val(r.reserveNum2);
    			        $('#tblTestItemvalidityPeriod2').val(r.validityPeriod);
    			        if(r.failureDatePrecision==1&&r.validityPeriod.length>=4){
    			        	$('#tblTestItemvalidityPeriod').datebox('setValue',r.validityPeriod.substr(0,4));
    			        }else if(r.failureDatePrecision==2&&r.validityPeriod.length>=7){
    			        	$('#tblTestItemvalidityPeriod').datebox('setValue',r.validityPeriod.substr(0,7));
    			        }else if(r.failureDatePrecision==3){
    			        	$('#tblTestItemvalidityPeriod').datebox('setValue',r.validityPeriod);
    			        }else{ 
    			        	$('#tblTestItemvalidityPeriod').datebox('setValue','');
    			        }
    			        
    			        $('#isFailureDateFlag').combobox('setValue',r.isFailureDateFlag);
    					$('#failureDatePrecision').combobox('setValue',r.failureDatePrecision);
    					
    			        $('#tblTestItembatchNo').val(r.batchNo);
    			        
    			       $('#tblTestItemstorageCondition').combobox('select',r.storageCondition);
    			       if(r.sponsorIsVenderC == 0){
//   			        	  document.getElementById('testItemsponsorIsVenderC0').click();
   			        	  document.getElementById('testItemsponsorIsVenderC0').checked = true;
   			           }else{
//   			        	  document.getElementById('testItemsponsorIsVenderC1').click();
   			        	  document.getElementById('testItemsponsorIsVenderC1').checked = true;
   			           }
    			       if(r.venderNameC){
    			    	   $('#contractVenderId').val(r.venderIdC);
       			           $('#contractVenderName').val(r.venderNameC);
    			       }else{
    			    	   $('#contractVenderId').val(r.sponsorId);
       			           $('#contractVenderName').val(r.sponsorName);
    			       }
   			           $('#sponsorName').val(r.sponsorName);
   			           $('#sponsorId').val(r.sponsorId);
    				   $('#testItemAddEditDialog').dialog('open'); 
    			} 
    		}
    	});	
}

//供试品类型下拉选
function testItemtICodeLoadCombobox(){
	//主要产品类型
	$('#testItemtICode').combobox({    
		url : sybp()+'/tblTestItemAction_getAllDictTestItemType.action',
	    valueField:'id',    
	    textField:'text',
	    required:true,
	    panelHeight:90,
	    editable:false,
	    onSelect:function(record){
		   //var add = $('#testItemAddOrEdit').val();
		   //if(add == "confirm"){
			   //if(record.id == "01"){
				   //getMedicineNextTblTestItemPoolNum();
			   //}else{
				   //getOtherNextTblTestItemPoolNum();
			   //}
		   //}
		   
	    }
	    //onChange:function(newValue, oldValue){   
		//   alert(newValue);
	    //},
	    //onLoadSuccess:function(none){
	    //}   
	});  
}

//初始化 含量/浓度/纯度 下拉框
function initTestItemContentLabelCombobox(){
	$('#tblTestItemcontentLabel').combobox({
		url:sybp()+'/tblTestItemAction_contentLabel.action',
		valueField:'id',
		textField:'text'
	});
}
//初始化 含量/浓度/纯度 下拉框
function initTestItemContentCombobox(){
	$('#tblTestItemcontent').combobox({
		url:sybp()+'/tblTestItemAction_content.action',
		valueField:'id',
		textField:'text'
	});
}
//初始化 外观/状态  下拉框
function initPhysicalCombobox(){
	$('#tblTestItemphysical').combobox({
		url:sybp()+'/tblTestItemAction_physical.action',
		valueField:'id',
		textField:'text'
	});
}
//初始化存储条件  下拉框
function initStorageConditionCombobox(){
	$('#tblTestItemstorageCondition').combobox({
		url:sybp()+'/tblTestItemAction_storageCondition.action',
		valueField:'id',
		textField:'text'
	});
}

//稳定性与均一性分析   下框
function initanalysisCombobox(){
	$('#tblTestItemanalysis').combobox({
		url:sybp()+'/tblTestItemAction_analysis.action',
		valueField:'id',
		textField:'text'
	});
}
//样品检测后处理  下拉框
function initpostTreatmentCombobox(){
	$('#tblTestItempostTreatment').combobox({
		url:sybp()+'/tblTestItemAction_postTreatment.action',
		valueField:'id',
		textField:'text'
	});
}

//留样量单位 下拉框
function initreserveUnitCombobox(){
	$('#tblTestItemreserveUnit').combobox({
		url:sybp()+'/tblTestItemAction_reserveUnit.action',
		valueField:'id',
		textField:'text'
	});
}

/**确定签字*/
function ontestItemsaveDialogButton(){
//	$('#contractAddEditDialog').dialog('close'); 
//	var contractAddEdit_event=document.getElementById("contractAddEdit_event");
//	contractAddEdit_event.click();
	if( $('#testItemAddEditForm').form('validate') ){
		var tblTestItemreserveNum = $('#tblTestItemreserveNum').val();
		if(tblTestItemreserveNum){
			qm_showQianmingDialog('afterOnConfirm');
		}else{
			$.messager.confirm('对话框', '未录入供试品留样量，确定继续？<br>注：如继续，请在供试品管理系统中录入留样量。', function(r){
    			if(r){
    				qm_showQianmingDialog('afterOnConfirm');
    			}
    		});
		}
	}
}
/**保存*/
function onEditsaveDialogButton(){
//	$('#contractAddEditDialog').dialog('close'); 
//	var contractAddEdit_event=document.getElementById("contractAddEdit_event");
//	contractAddEdit_event.click();
	if( $('#testItemAddEditForm').form('validate') ){
		$.messager.prompt('提示信息', '请填写供试品编辑原因：', function(r){
			if (r){
				if(r != ""){
    			    testitemEditRsn = r;
    			    qm_showQianmingDialog('afterOnEditsave');
			    }
			}
		});
	}
}

function  afterOnEditsave(){
	var dateText = $('#tblTestItemvalidityPeriod').datebox('getText');
	var realDate = $('#tblTestItemvalidityPeriod2').val();
	
		var type = $('#failureDatePrecision').combobox('getValue');
		if(type==1&&dateText.length>=4){
			if(realDate.length<=4)
			{
				realDate = dateText.substr(0,4)+"-01-01";
			}else
				realDate = dateText.substr(0,4)+realDate.substr(4);
		}else if(type==2&&dateText.length>=7){
			if(realDate.length<=7)
			{
				realDate = dateText.substr(0,7)+"-01";
			}else
				realDate = dateText.substr(0,7)+realDate.substr(7);
		}else if(type==3){
			realDate = dateText;
		}
		$('#tblTestItemvalidityPeriod2').val(realDate);
	
	$('#testItemsaveDialogButton').linkbutton('disable');
	/*不可编辑
	 * var contentL = $('#tblTestItemcontentLabel').combobox('getText');
	if(contentL){
		$('#tblTestItemcontentLabel').combobox({data:{
			id:contentL,
			text:contentL
		}});
		$('#tblTestItemcontentLabel').combobox('select',contentL);

	}*/
	var content = $('#tblTestItemcontent').combobox('getText');
	if(content){
		$('#tblTestItemcontent').combobox({data:{
			id:content,
			text:content
		}});
		$('#tblTestItemcontent').combobox('select',content);

	}
	var physical = $('#tblTestItemphysical').combobox('getText');
	if(physical){
		$('#tblTestItemphysical').combobox({data:{
			id:physical,
			text:physical
		}});
		$('#tblTestItemphysical').combobox('select',physical);
		
	}
	var storageCondition = $('#tblTestItemstorageCondition').combobox('getText');
	if(storageCondition){
		$('#tblTestItemstorageCondition').combobox({data:{
			id:storageCondition,
			text:storageCondition
		}});
		$('#tblTestItemstorageCondition').combobox('select',storageCondition);
		
	}
	
	var postTreatment = $('#tblTestItempostTreatment').combobox('getText');
	if(postTreatment){
		$('#tblTestItempostTreatment').combobox({data:{
			id:postTreatment,
			text:postTreatment
		}});
		$('#tblTestItempostTreatment').combobox('select',postTreatment);
		
	}
	var analysis = $('#tblTestItemanalysis').combobox('getText');
	if(analysis){
		$('#tblTestItemanalysis').combobox({data:{
			id:analysis,
			text:analysis
		}});
		$('#tblTestItemanalysis').combobox('select',analysis);
		
	}
	var reserveUnit = $('#tblTestItemreserveUnit').combobox('getText');
	if(reserveUnit){
		$('#tblTestItemreserveUnit').combobox({data:{
			id:reserveUnit,
			text:reserveUnit
		}});
		$('#tblTestItemreserveUnit').combobox('select',reserveUnit);
		
	}
	if($('#testItemAddOrEdit').val() == 'edit' ){
		var tid=$('#testItemid').val();
		$.ajax({
			url:sybp()+'/tblTestItemAction_editSaveAfterSign.action?id='+tid+'&testitemEditRsn='+testitemEditRsn,
			type:'post',
			data:$('#testItemAddEditForm').serialize(),
			dataType:'json',
			success:function(r){
				$('#testItemsaveDialogButton').linkbutton('enable');
				if(r && r.success){
					$('#selectrowId').val(r.id);
					var testItemAddEdit_event=document.getElementById("testItemAddEdit_event");
					testItemAddEdit_event.click();
				}else{
					if(r.msg && r.msg != ""){
						 $.messager.alert('提示','与数据库交互异常');
					}else{
						 $.messager.alert('提示','请检查录入的数据');
					}
					 
				}
			}
		});
	}

}
function  afterOnConfirm(){
	
	$('#testItemsaveDialogButton').linkbutton('disable');
	
	var dateText = $('#tblTestItemvalidityPeriod').datebox('getText');
	var dateVal = $('#tblTestItemvalidityPeriod').datebox('getValue');
	var realDate = $('#tblTestItemvalidityPeriod2').val();
	
	var type = $('#failureDatePrecision').combobox('getValue');
	if(type==1&&dateText.length>=4){
		if(realDate.length<=4)
		{
			realDate = dateText.substr(0,4)+"-01-01";
		}else
			realDate = dateText.substr(0,4)+realDate.substr(4);
	}else if(type==2&&dateText.length>=7){
		if(realDate.length<=7)
		{
			realDate = dateText.substr(0,7)+"-01";
		}else
			realDate = dateText.substr(0,7)+realDate.substr(7);
	}else if(type==3){
			realDate = dateText;
	}
	$('#tblTestItemvalidityPeriod2').val(realDate);
	
	/*
	var contentL = $('#tblTestItemcontentLabel').combobox('getText');
	if(contentL){
		$('#tblTestItemcontentLabel').combobox({data:{
			id:contentL,
			text:contentL
		}});
		$('#tblTestItemcontentLabel').combobox('select',contentL);
		
	}*/
	var content = $('#tblTestItemcontent').combobox('getText');
	if(content){
		$('#tblTestItemcontent').combobox({data:{
			id:content,
			text:content
		}});
		$('#tblTestItemcontent').combobox('select',content);
		
	}
	var physical = $('#tblTestItemphysical').combobox('getText');
	if(physical){
		$('#tblTestItemphysical').combobox({data:{
			id:physical,
			text:physical
		}});
		$('#tblTestItemphysical').combobox('select',physical);
		
	}
	var storageCondition = $('#tblTestItemstorageCondition').combobox('getText');
	if(storageCondition){
		$('#tblTestItemstorageCondition').combobox({data:{
			id:storageCondition,
			text:storageCondition
		}});
		$('#tblTestItemstorageCondition').combobox('select',storageCondition);
		
	}
	
	var postTreatment = $('#tblTestItempostTreatment').combobox('getText');
	if(postTreatment){
		$('#tblTestItempostTreatment').combobox({data:{
			id:postTreatment,
			text:postTreatment
		}});
		$('#tblTestItempostTreatment').combobox('select',postTreatment);
		
	}
	var analysis = $('#tblTestItemanalysis').combobox('getText');
	if(analysis){
		$('#tblTestItemanalysis').combobox({data:{
			id:analysis,
			text:analysis
		}});
		$('#tblTestItemanalysis').combobox('select',analysis);
		
	}
	var reserveUnit = $('#tblTestItemreserveUnit').combobox('getText');
	if(reserveUnit){
		$('#tblTestItemreserveUnit').combobox({data:{
			id:reserveUnit,
			text:reserveUnit
		}});
		$('#tblTestItemreserveUnit').combobox('select',reserveUnit);
		
	}
	if($('#testItemAddOrEdit').val() == 'confirm' ){
		var tid=$('#testItemid').val();
		$.ajax({
			url:sybp()+'/tblTestItemAction_editSave.action?id='+tid,
			type:'post',
			data:$('#testItemAddEditForm').serialize(),
			dataType:'json',
			success:function(r){
			$('#testItemsaveDialogButton').linkbutton('enable');
			if(r && r.success){
				$('#selectrowId').val(r.id);
				var testItemAddEdit_event=document.getElementById("testItemAddEdit_event");
				testItemAddEdit_event.click();
			}else{
				if(r.msg && r.msg != ""){
					$.messager.alert('提示','与数据库交互异常');
				}else{
					$.messager.alert('提示','请检查录入的数据');
				}
				
			}
		}
		});
	}
	
}

/**委托方即厂家单选框事件*/
function onSponsorIsVenderRadio(){
	if(document.getElementById('testItemsponsorIsVender0').checked){
		document.getElementById('testItemsponsorIsVender1').click();
	}else{
		document.getElementById('testItemsponsorIsVender0').click();
	}
}


/**委托方即厂家单选框change事件*/
function testItemsponsorIsVenderChange(){
	//选择的是  ：是
	if(document.getElementById('testItemsponsorIsVender1').checked){
		var sponsorName =  $('#contractVenderName').val();
		if(sponsorName){
			$('#testItemvenderName').val(sponsorName);
			$('#testItemvenderId').val($('#contractVenderId').val());
			$('#testItemverderButton').linkbutton('disable');
			document.getElementById('testItemvenderName').disabled=true;
		}
	}else{
		$('#testItemverderButton').linkbutton('enable');
		document.getElementById('testItemvenderName').disabled=false;
	}
}

/**委托方即厂家单选框事件*/
function onSponsorIsVenderRadiCo(){
	if(document.getElementById('testItemsponsorIsVenderC0').checked){
		document.getElementById('testItemsponsorIsVenderC1').click();
	}else{
		document.getElementById('testItemsponsorIsVenderC0').click();
	}
}

/**报告出具方即提供方单选框change事件*/
function contractsponsorIsVenderChange(){
	//选择的是  ：是                                    
//	if(document.getElementById('testItemsponsorIsVenderC1').checked){
//		var sponsorName =  $('#sponsorName').val();
//		if(sponsorName){
//			$('#contractVenderName').val(sponsorName);
//			$('#contractVenderId').val($('#sponsorId').val());
//			$('#contractVerderButton').linkbutton('disable');
//			document.getElementById('contractVenderName').disabled=true;
//		}
//	}else{
//		$('#contractVerderButton').linkbutton('enable');
//		document.getElementById('contractVenderName').disabled=false;
//	}
}

/**选择厂家按钮事件*/
function onVerderButton(){
//	$('#venderId').val('50000000028');
//	$('#venderName').val('xx农药');
	document.getElementById("testItemvenderName").blur();
	showSelectCustomerDialog('testItemafterOnVerderButton','查询客户信息');
}

function onCVerderButton(){
	document.getElementById("contractVenderName").blur();
	showSelectCustomerDialog('contractafterOnVerderButton','查询客户信息');
}
function contractafterOnVerderButton(){
	$('#contractVenderId').val($('#cuid').val());
	$('#contractVenderName').val($('#customerNameadd').val());
	$('#contractVenderName').validatebox('validate');
	if(document.getElementById('testItemsponsorIsVender1').checked){
		$('#testItemvenderName').val($('#customerNameadd').val());
		$('#testItemvenderId').val($('#cuid').val());
		$('#testItemverderButton').linkbutton('disable');
	}
}
/**选择厂家按钮  后事件*/
function testItemafterOnVerderButton(){
	$('#testItemvenderId').val($('#cuid').val());
	$('#testItemvenderName').val($('#customerNameadd').val());
	$('#testItemvenderName').validatebox('validate');
}

//获取医药供试品编号
function getMedicineNextTblTestItemPoolNum(){
	    var prefixNo=$('#parentContractCode').val();
	    
	    //添加供试品编号
	    $.ajax({
      	url:sybp()+'/tblContractAction_getMedicineNextTblTestItemPoolNum.action',
			data:{
	    	     contractCode:prefixNo
			},
			dataType:'json',
			success:function(r){
				if(r){
					$('#tblTestItemtiNo').val(r.num);
				}
			}
      });
	
}

//获取医药外的其他供试品编号
function getOtherNextTblTestItemPoolNum(){
    var prefixNo=$('#parentContractCode').val();
    //添加供试品编号
    $.ajax({
  	url:sybp()+'/tblContractAction_getOtherNextTblTestItemPoolNum.action',
		data:{
    	     contractCode:prefixNo
		},
		dataType:'json',
		success:function(r){
			if(r){
				$('#tblTestItemtiNo').val(r.num);
			}
		}
  });

}
//添加供试品时非加载项清空
function deleteForTestItemEdit(){
	    getMedicineNextTblTestItemPoolNum();
	    $('#oldtblTestItemtiNo').val('');
        $('#tblTestItemtiName').val('')
        $('#tblTestItemcontent').val('');
        $('#tblTestItemcontentLabel').val('');
        $('#tblTestItemphysical').val('');
        $('#tblTestItemsealNo').val('');
        $('#tblTestItemfileNo').val('');
        $('#tblTestItemmeltPoint').val('');
        $('#tblTestItemboilPoint').val('');
        $('#tblTestItemphotolysis').val('');
        $('#tblTestItemvolatility').val('');
        $('#tblTestItemcomposition').val('');
        $('#tblTestItemdensity').val('');
        $('#tblTestItemwaterSolubility').val('');
        $('#tblTestItemwaterStability').val('');
        $('#tblTestItemsolventSolubility').val('');
        $('#tblTestItemsolventStability').val('');
        $('#tblTestItemph').val('');
        $('#tblTestItemsecurityMeasures').val('');
        $('#tblTestItemanalysis').val('');
        $('#tblTestItempostTreatment').val('');
        $('#tblTestItemcas').val('');
        $('#tblTestItemvalidityPeriod').val('');
        $('#tblTestItemvalidityPeriod2').val('');
       
        $('#isFailureDateFlag').combobox('setValue',0);
		$('#failureDatePrecision').combobox('setValue',3);
		
        $('#tblTestItemstorageCondition').val('');
}
//在综合查询的时候清空对话框
function selectadddeleteForTestItemEdit(){
	  // var row= $('#tblContractTable').datagrid('getSelected');
	   //var prefixNo=row.contractCode+'-';
	   getMedicineNextTblTestItemPoolNum();
	   $('#tblTestItemtiNo').val(prefixNo);
	   $('#tblTestItemcontractCode').val(row.contractCode);
	   $('#oldtblTestItemtiNo').val('');
       $('#tblTestItemtiName').val('')
       $('#tblTestItemcontent').val('');
       $('#tblTestItemcontentLabel').val('');
       $('#tblTestItemphysical').val('');
       $('#tblTestItemsealNo').val('');
       $('#tblTestItemfileNo').val('');
       $('#tblTestItemmeltPoint').val('');
       $('#tblTestItemboilPoint').val('');
       $('#tblTestItemphotolysis').val('');
       $('#tblTestItemvolatility').val('');
       $('#tblTestItemcomposition').val('');
       $('#tblTestItemdensity').val('');
       $('#tblTestItemwaterSolubility').val('');
       $('#tblTestItemwaterStability').val('');
       $('#tblTestItemsolventSolubility').val('');
       $('#tblTestItemsolventStability').val('');
       $('#tblTestItemph').val('');
       $('#tblTestItemsecurityMeasures').val('');
       $('#tblTestItemanalysis').val('');
       $('#tblTestItempostTreatment').val('');
       $('#tblTestItemcas').val('');
       $('#tblTestItemvalidityPeriod').val('');
       $('#tblTestItemvalidityPeriod2').val('');
       
       $('#isFailureDateFlag').combobox('setValue',0);
		$('#failureDatePrecision').combobox('setValue',3);
       $('#tblTestItemstorageCondition').val('');
}
