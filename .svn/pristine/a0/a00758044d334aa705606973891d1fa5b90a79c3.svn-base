var testitemEditRsn;//供试品编辑原因

/**显示Dialog*/
function showTestItemAddEditDialog(clickName,addOrEdit,title){
	//加载供试品类型下拉选
	testItemtICodeLoadCombobox();
	//厂家名称
	$('#testItemvenderName').val($('#testItemsponsorName').val());
	/*签名Dialog*/
	document.getElementById("testItemAddEditDialog2").style.display="block";
	$('#testItemAddOrEdit').val(addOrEdit);
	$('#testItemAddEditDialog').dialog('setTitle',title);
	 if(addOrEdit == 'edit' || addOrEdit == 'add' ||addOrEdit == 'selectadd'){
		   $('#readonlytestItemAddEdit').css('display','none');
	 }
	 if(addOrEdit == 'select'){
    	 $('#testItemsaveDialogButton').linkbutton('disable');
    	 onEditFullTestItem();
     }else{
    	 $('#testItemsaveDialogButton').linkbutton('enable');
     }
	if(addOrEdit == 'add'){
		$('#testItemtICode').combobox('select','01');
		$('#testItemAddEditDialog').dialog('open'); 
		var sponsorName =  $('#oldsponsorName').val();
		var oldvenderNameC =  $('#oldvenderNameC').val();
		document.getElementById('testItemsponsorIsVender1').click();
		if( oldvenderNameC){
			$('#testItemvenderName').val(oldvenderNameC);
			$('#testItemvenderId').val($('#testItemvenderIdC').val());
		}else{
			$('#testItemvenderName').val(sponsorName);
			$('#testItemvenderId').val($('#testItemsponsorId').val());
			
		}
		
		deleteForTestItemEdit();
	}else if(addOrEdit == 'edit' ){
		//判断
		onEditFullTestItem();
	}else if(addOrEdit == 'selectadd'){
		 $('#testItemtICode').combobox('select','01');
		 $('#testItemAddEditDialog').dialog('open'); 
		 document.getElementById('testItemsponsorIsVender1').click();
		 selectadddeleteForTestItemEdit();
    	 var row= $('#tblContractTable').datagrid('getSelected');
		 $('#testItemvenderName').val(row.sponsorName);
		 $('#testItemvenderId').val(row.sponsorId);
	}
	document.getElementById("testItemAddEdit_event").href="javascript:"+clickName+"();";
	
	//初始化 含量/浓度/纯度 下拉框标签
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
	
	//根据合同编号查询对应供试品类型
	if(addOrEdit == 'add'){
		var tblTestItemcontractCode = $('#tblTestItemcontractCode').val();
		$.ajax({
			url:sybp()+'/tblTestItemAction_ticode.action',
			type:'post',
			data:{
				contractCode:tblTestItemcontractCode
			},
			dataType:'json',
			success:function(r){
				if(r && r.success && r.msg){
					$('#testItemtICode').combobox('select',r.msg);
				}
			}
		});
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
	    			        $('#tblTestItemcontentLabel').combobox('setText',r.contentLabel);
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
	    			        $('#tblTestItemvalidityPeriod').val(r.validityPeriod);
	    			        $('#tblTestItembatchNo').val(r.batchNo);
	    			        //$('#tblTestItemvalidityPeriod').val(r.validityPeriod);
	    			        $("#isFailureDateFlag").combobox('setValue',r.isFailureDateFlag);
	    					$("#failureDatePrecision").combobox('setValue',r.failureDatePrecision);
	    					
	    			        if(r.failureDatePrecision==1&&r.validityPeriod!=''&&r.validityPeriod!=null){
	    			        	$('#tblTestItemvalidityPeriod').datebox('setValue',r.validityPeriod.substr(0,4));
	    			        	$('#tblTestItemvalidityPeriod2').val(r.validityPeriod);
	    			        }else if(r.failureDatePrecision==2&&r.validityPeriod!=''&&r.validityPeriod!=null){
	    			        	$('#tblTestItemvalidityPeriod').datebox('setValue',r.validityPeriod.substr(0,7));
	    			        	$('#tblTestItemvalidityPeriod2').val(r.validityPeriod);
	    			        }else if(r.failureDatePrecision==3&&r.validityPeriod!=''&&r.validityPeriod!=null){
	    			        	$('#tblTestItemvalidityPeriod').datebox('setValue',r.validityPeriod);
	    			        	$('#tblTestItemvalidityPeriod2').val(r.validityPeriod);
	    			        }else{
	    			        	$('#tblTestItemvalidityPeriod').datebox('setValue','');
	    			        	$('#tblTestItemvalidityPeriod2').val('');
	    			        }
	    			        
	    			        $('#tblTestItemstorageCondition').combobox('select',r.storageCondition);
	    			        
	    			        $('#contractState_testitemDialog').val(r.contractState);
	    			        $('#state_testitemDialog').val(r.state);
	    			        
	    					$('#testItemAddEditDialog').dialog('open'); 
	    			} 
	    		}
	    	});	
}

//供试品类型下拉选
function testItemtICodeLoadCombobox(){
	//主要产品类型
	$('#testItemtICode').combobox({    
		url : sybp()+'/tblCustomerAction_getAllDictTestItemType.action',
	    valueField:'id',    
	    textField:'text',
	    required:true,
	    panelHeight:90,
	    editable:false,
	    onSelect:function(record){
		   var add = $('#testItemAddOrEdit').val();
		   if(add == "add"){
			   if(record.id == "01"){
				   getMedicineNextTblTestItemPoolNum();
			   }else{
				   getOtherNextTblTestItemPoolNum();
			   }
		   }
		   
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
		textField:'text',
		onLoadSuccess:function(){
			$(this).combobox('setText','含量/浓度/纯度');
		}
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

/**确定（保存）*/
function ontestItemsaveDialogButton(){
//	$('#contractAddEditDialog').dialog('close'); 
//	var contractAddEdit_event=document.getElementById("contractAddEdit_event");
//	contractAddEdit_event.click();
	if( $('#testItemAddEditForm').form('validate') ){
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
		}
		*/
		
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
		
		
		if($('#testItemAddOrEdit').val() == 'add' || $('#testItemAddOrEdit').val() == 'selectadd' ){
			$.ajax({
				url:sybp()+'/tblTestItemAction_addTblTestItem.action',
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
							$.messager.alert('提示',r.msg);
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				}
			});
		}else{
			var contractState_testitemDialog = $('#contractState_testitemDialog').val();
			var state_testitemDialog = $('#state_testitemDialog').val();
			if(contractState_testitemDialog == 2 && state_testitemDialog == 1){
				$.messager.prompt('提示信息', '请填写供试品编辑原因：', function(r){
	    			if (r){
	    				if(r != ""){
		    			    testitemEditRsn = r;
		    			    qm_showQianmingDialog('afterSignTestItemEdit');
	    			    }else{
	    			    	$('#testItemsaveDialogButton').linkbutton('enable');
	    			    }
	    			}else{
	    				$('#testItemsaveDialogButton').linkbutton('enable');
	    			}
	    		});
			}else{
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
							$.messager.alert('提示',r.msg);
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				}
				});
			}
		}
	}
}

/**供试品编辑签字后事件
 * @return
 */
function afterSignTestItemEdit(){
	var tid=$('#testItemid').val();
	$.ajax({
		url:sybp()+'/tblTestItemAction_editSave.action?id='+tid+'&testitemEditRsn='+testitemEditRsn,
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
						$.messager.alert('提示',r.msg);
					}else{
						$.messager.alert('提示','请检查录入的数据');
					}
			}
		}
	});
}
/**委托方即厂家单选框事件*/
function onSponsorIsVenderRadio(){
	if(document.getElementById('sponsorIsVender0').checked){
		document.getElementById('sponsorIsVender1').click();
	}else{
		document.getElementById('sponsorIsVender0').click();
	}
}

/**委托方即厂家单选框change事件*/
function testItemsponsorIsVenderChange(){
	//选择的是  ：是
	if(document.getElementById('testItemsponsorIsVender1').checked){
		var sponsorName =  $('#oldsponsorName').val();
		if(sponsorName){
			$('#testItemvenderName').val(sponsorName);
			$('#testItemvenderId').val($('#testItemsponsorId').val());
			$('#testItemverderButton').linkbutton('disable');
		}else{
			var row= $('#tblContractTable').datagrid('getSelected');
			 $('#testItemvenderName').val(row.sponsorName);
			 $('#testItemvenderId').val(row.sponsorId);
		}
		document.getElementById('testItemvenderName').disabled=true;
	}else{
		$('#testItemverderButton').linkbutton('enable');
		document.getElementById('testItemvenderName').disabled=false;
	}
}

/**选择厂家按钮事件*/
function onVerderButton(){
//	$('#venderId').val('50000000028');
//	$('#venderName').val('xx农药');
	document.getElementById("testItemvenderName").blur();
	showSelectCustomerDialog('testItemafterOnVerderButton','查询客户信息');
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
        $("#isFailureDateFlag").combobox('setValue',0);
		$("#failureDatePrecision").combobox('setValue',3);
		
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
       
       $("#isFailureDateFlag").combobox('setValue',0);
	   $("#failureDatePrecision").combobox('setValue',3);
		
       $('#tblTestItemstorageCondition').val('');
}
