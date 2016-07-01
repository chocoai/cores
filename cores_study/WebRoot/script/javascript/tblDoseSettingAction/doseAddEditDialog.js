/**显示Dialog*/
function showDoseAddEditDialog(clickName,addOrEdit){
	/*签名Dialog*/
	document.getElementById("doseAddEditDialog2").style.display="block";
	//$('#doseAddEditDialog').dialog('open'); 
	$('#addOrEdit').val(addOrEdit);
	if(addOrEdit == 'add'){
		$('#doseAddEditDialog').dialog('setTitle','添加剂量组'); 
	}else{
		$('#doseAddEditDialog').dialog('setTitle','编辑剂量组'); 
	}
	//剂量组说明
	initdosageDescCombobox();
	//回调函数
	this.myComeback = clickName;
	//填充数据(或清空数据)
	doseAddEditFormFullEditData();
	//初始化form
	initdoseAddEditForm();
	
}

function initdosageDescCombobox(){
	$('#dosageDesc').combobox({
		url:sybp()+'/tblDoseSettingAction_dosageDesc.action',
		valueField:'id',
		textField:'text'
	});
}
//填充数据(或清空数据)
function doseAddEditFormFullEditData(){
    document.doseAddEditForm.reset();//
    
    var studyNoPara= encodeURIComponent($('#studyNoPara').val());
    var addOrEdit = $('#addOrEdit').val();
    if(studyNoPara && addOrEdit == 'add'){
    	//添加
    	$.ajax({
			url:sybp()+'/tblDoseSettingAction_loadDoseGroupinfo.action?studyNoPara='+studyNoPara,
			type:'post',
			dataType:'json',
			success:function(r){
    			if(r && r.success == 'true'){
    				if(r.doseSettingFlag == 0){
    					//剂量单位
    					$('#dosageSpanUnit').text(r.dosageUnit);
    					
    					if(r.animalType){
    						animalTypeFlag=true;
    						isNoGenderFlag=r.isNoGender;
    						//有动物
    						if(r.isNoGender == 0){
    							$.messager.alert('警告','请先设置剂量组基本信息。'); 
    						}else if(r.isNoGender == 1){
    							//分雄雌
    							//动物数量
								$('#maleNumSpan').text('雄性动物数量');
								$('#maleNumTR').css('display','');
								$('#maleNum').attr('disabled',false);
								if(null!=lastMaleName){
									$('#maleNum').val(lastMaleName);
								}
								
								$('#femaleNumTR').css('display','');
								$('#femaleNum').attr('disabled',false);
								if(null!=lastFemaleName){
									$('#femaleNum').val(lastFemaleName);
								}
    							if(r.isIndentical == 1){
    								//雌雄剂量相同
    								$('#dosageSpan').text('剂量');
    								$('#femaleDosageTR').css('display','none');
    								$('#femaleDosage').attr('disabled',true);
    								
    								//给药容积
    								if(r.volumeUnit){
    									$('#maleVolumeUnit').text(r.volumeUnit);
    									$('#maleVolumeSpan').text('给药容积');
    									
    									$('#maleVolumeTR').css('display','');
    									$('#maleVolume').attr('disabled',false);
    									
    									$('#femaleVolumeTR').css('display','none');
    									$('#femaleVolume').attr('disabled',true);
    								}else{
    									$('#maleVolumeTR').css('display','none');
    									$('#maleVolume').attr('disabled',true);
    									
    									$('#femaleVolumeTR').css('display','none');
    									$('#femaleVolume').attr('disabled',true);
    								}
    	    						if(r.thicknessUnit){
    	    							$('#maleThicknessUnit').text(r.thicknessUnit);
    	    							$('#maleThicknessSpan').text('给药浓度');
    	    							//给药浓度
    	    							$('#maleThicknessTR').css('display','');
    	    							$('#maleThickness').attr('disabled',false);
    	    							
    	    							$('#femaleThicknessTR').css('display','none');
    	    							$('#femaleThickness').attr('disabled',true);
    	    						}else{
    	    							//给药浓度
    	    							$('#maleThicknessTR').css('display','none');
    	    							$('#maleThickness').attr('disabled',true);
    	    							
    	    							$('#femaleThicknessTR').css('display','none');
    	    							$('#femaleThickness').attr('disabled',true);
    	    						}
    							}else{
    								//不相同
    								$('#dosageSpan').text('雄性剂量');
    								$('#femaleDosageTR').css('display','');
    								$('#femaleDosage').attr('disabled',false);
    								$('#femaleDosageUnit').text(r.dosageUnit);
    								
    								//给药容积
    								if(r.volumeUnit){
    									$('#maleVolumeUnit').text(r.volumeUnit);
    									$('#femaleVolumeUnit').text(r.volumeUnit);
    									$('#maleVolumeSpan').text('雄性给药容积');
    									
    									$('#maleVolumeTR').css('display','');
    									$('#maleVolume').attr('disabled',false);
    									
    									$('#femaleVolumeTR').css('display','');
    									$('#femaleVolume').attr('disabled',false);
    								}else{
    									$('#maleVolumeTR').css('display','none');
    									$('#maleVolume').attr('disabled',true);
    									
    									$('#femaleVolumeTR').css('display','none');
    									$('#femaleVolume').attr('disabled',true);
    								}
    	    						if(r.thicknessUnit){
    	    							$('#maleThicknessUnit').text(r.thicknessUnit);
    									$('#femaleThicknessUnit').text(r.thicknessUnit);
    	    							$('#maleThicknessSpan').text('雄性给药浓度');
    	    							//给药浓度
    	    							$('#maleThicknessTR').css('display','');
    	    							$('#maleThickness').attr('disabled',false);
    	    							
    	    							$('#femaleThicknessTR').css('display','');
    	    							$('#femaleThickness').attr('disabled',false);
    	    						}else{
    	    							//给药浓度
    	    							$('#maleThicknessTR').css('display','none');
    	    							$('#maleThickness').attr('disabled',true);
    	    							
    	    							$('#femaleThicknessTR').css('display','none');
    	    							$('#femaleThickness').attr('disabled',true);
    	    						}
    							}
    							
        						
        						
    						}else{
    							//不分雄雌
    							
    							//动物数量
								$('#maleNumSpan').text('动物数量');
								$('#maleNumTR').css('display','');
								$('#maleNum').attr('disabled',false);
								if(null!=lastMaleName){
									$('#maleNum').val(lastNum);
								}
								$('#femaleNumTR').css('display','none');
								$('#femaleNum').attr('disabled',true);
								
    								//雌雄剂量相同
    								$('#dosageSpan').text('剂量');
    								$('#femaleDosageTR').css('display','none');
    								$('#femaleDosage').attr('disabled',true);
    								
    								//给药容积
    								if(r.volumeUnit){
    									$('#maleVolumeUnit').text(r.volumeUnit);
    									$('#maleVolumeSpan').text('给药容积');
    									
    									$('#maleVolumeTR').css('display','');
    									$('#maleVolume').attr('disabled',false);
    									
    									$('#femaleVolumeTR').css('display','none');
    									$('#femaleVolume').attr('disabled',true);
    								}else{
    									$('#maleVolumeTR').css('display','none');
    									$('#maleVolume').attr('disabled',true);
    									
    									$('#femaleVolumeTR').css('display','none');
    									$('#femaleVolume').attr('disabled',true);
    								}
    	    						if(r.thicknessUnit){
    	    							$('#maleThicknessUnit').text(r.thicknessUnit);
    	    							$('#maleThicknessSpan').text('给药浓度');
    	    							//给药浓度
    	    							$('#maleThicknessTR').css('display','');
    	    							$('#maleThickness').attr('disabled',false);
    	    							
    	    							$('#femaleThicknessTR').css('display','none');
    	    							$('#femaleThickness').attr('disabled',true);
    	    						}else{
    	    							//给药浓度
    	    							$('#maleThicknessTR').css('display','none');
    	    							$('#maleThickness').attr('disabled',true);
    	    							
    	    							$('#femaleThicknessTR').css('display','none');
    	    							$('#femaleThickness').attr('disabled',true);
    	    						}
    							
    						}
    						//打开dialog
    						$('#doseAddEditDialog').dialog('open'); 
    					}else{
    						//无动物
    						$('#dosageSpan').text('剂量');
    						$('#femaleDosageTR').css('display','none');
    						$('#femaleDosage').attr('disabled',true);
    						
    						$('#maleNumTR').css('display','none');
    						$('#maleNum').attr('disabled',true);
    						
    						$('#femaleNumTR').css('display','none');
    						$('#femaleNum').attr('disabled',true);
    						
    						$('#maleVolumeTR').css('display','none');
    						$('#maleVolume').attr('disabled',true);
    						
    						$('#femaleVolumeTR').css('display','none');
    						$('#femaleVolume').attr('disabled',true);
    						
    						$('#maleThicknessTR').css('display','none');
    						$('#maleThickness').attr('disabled',true);
    						
    						$('#femaleThicknessTR').css('display','none');
    						$('#femaleThickness').attr('disabled',true);
    						//打开dialog
    						$('#doseAddEditDialog').dialog('open'); 
    					}
    					
    				}else{
    					$.messager.alert('警告','剂量组设计已确认，不可编辑。');    
    				}
    				
    			}
    		}
    	});
    }else {
    	//编辑
    	var row = $('#doseSettingTable').datagrid('getSelected');
    	if(row && row.id){
    		//添加
        	$.ajax({
    			url:sybp()+'/tblDoseSettingAction_loadEditDoseGroupinfo.action',
    			type:'post',
    			dataType:'json',
    			data:{
        			studyNoPara:$('#studyNoPara').val(),
        			id:row.id
        		},
    			success:function(r){
        			if(r && r.success == 'true'){
        				if((r.doseSettingFlag == 0 )|| (r.state == "3" && r.doseSettingFlag == 1)|| (r.state == "0" && r.doseSettingFlag == 1)){
        					//剂量单位
        					$('#dosageSpanUnit').text(r.dosageUnit);
        					
        					if(r.animalType){
        						animalTypeFlag=true;
        						isNoGenderFlag=r.isNoGender;
        						//有动物
        						if(r.isNoGender == 0){
        							$.messager.alert('警告','请先设置剂量组基本信息。'); 
        						}else if(r.isNoGender == 1){
        							//分雄雌
        							//动物数量
    								$('#maleNumSpan').text('雄性动物数量');
    								$('#maleNumTR').css('display','');
    								$('#maleNum').attr('disabled',false);
    								
    								$('#femaleNumTR').css('display','');
    								$('#femaleNum').attr('disabled',false);
        							if(r.isIndentical == 1){
        								//雌雄剂量相同
        								$('#dosageSpan').text('剂量');
        								$('#femaleDosageTR').css('display','none');
        								$('#femaleDosage').attr('disabled',true);
        								
        								//给药容积
        								if(r.volumeUnit){
        									$('#maleVolumeUnit').text(r.volumeUnit);
        									$('#maleVolumeSpan').text('给药容积');
        									
        									$('#maleVolumeTR').css('display','');
        									$('#maleVolume').attr('disabled',false);
        									
        									$('#femaleVolumeTR').css('display','none');
        									$('#femaleVolume').attr('disabled',true);
        								}else{
        									$('#maleVolumeTR').css('display','none');
        									$('#maleVolume').attr('disabled',true);
        									
        									$('#femaleVolumeTR').css('display','none');
        									$('#femaleVolume').attr('disabled',true);
        								}
        	    						if(r.thicknessUnit){
        	    							$('#maleThicknessUnit').text(r.thicknessUnit);
        	    							$('#maleThicknessSpan').text('给药浓度');
        	    							//给药浓度
        	    							$('#maleThicknessTR').css('display','');
        	    							$('#maleThickness').attr('disabled',false);
        	    							
        	    							$('#femaleThicknessTR').css('display','none');
        	    							$('#femaleThickness').attr('disabled',true);
        	    						}else{
        	    							//给药浓度
        	    							$('#maleThicknessTR').css('display','none');
        	    							$('#maleThickness').attr('disabled',true);
        	    							
        	    							$('#femaleThicknessTR').css('display','none');
        	    							$('#femaleThickness').attr('disabled',true);
        	    						}
        							}else{
        								//不相同
        								$('#dosageSpan').text('雄性剂量');
        								$('#femaleDosageTR').css('display','');
        								$('#femaleDosage').attr('disabled',false);
        								$('#femaleDosageUnit').text(r.dosageUnit);
        								
        								//给药容积
        								if(r.volumeUnit){
        									$('#maleVolumeUnit').text(r.volumeUnit);
        									$('#femaleVolumeUnit').text(r.volumeUnit);
        									$('#maleVolumeSpan').text('雄性给药容积');
        									
        									$('#maleVolumeTR').css('display','');
        									$('#maleVolume').attr('disabled',false);
        									
        									$('#femaleVolumeTR').css('display','');
        									$('#femaleVolume').attr('disabled',false);
        								}else{
        									$('#maleVolumeTR').css('display','none');
        									$('#maleVolume').attr('disabled',true);
        									
        									$('#femaleVolumeTR').css('display','none');
        									$('#femaleVolume').attr('disabled',true);
        								}
        	    						if(r.thicknessUnit){
        	    							$('#maleThicknessUnit').text(r.thicknessUnit);
        									$('#femaleThicknessUnit').text(r.thicknessUnit);
        	    							$('#maleThicknessSpan').text('雄性给药浓度');
        	    							//给药浓度
        	    							$('#maleThicknessTR').css('display','');
        	    							$('#maleThickness').attr('disabled',false);
        	    							
        	    							$('#femaleThicknessTR').css('display','');
        	    							$('#femaleThickness').attr('disabled',false);
        	    						}else{
        	    							//给药浓度
        	    							$('#maleThicknessTR').css('display','none');
        	    							$('#maleThickness').attr('disabled',true);
        	    							
        	    							$('#femaleThicknessTR').css('display','none');
        	    							$('#femaleThickness').attr('disabled',true);
        	    						}
        							}
        							
            						
            						
        						}else{
        							//不分雄雌
        							
        							//动物数量
    								$('#maleNumSpan').text('动物数量');
    								$('#maleNumTR').css('display','');
    								$('#maleNum').attr('disabled',false);
    								
    								$('#femaleNumTR').css('display','none');
    								$('#femaleNum').attr('disabled',true);
    								
        								//雌雄剂量相同
        								$('#dosageSpan').text('剂量');
        								$('#femaleDosageTR').css('display','none');
        								$('#femaleDosage').attr('disabled',true);
        								
        								//给药容积
        								if(r.volumeUnit){
        									$('#maleVolumeUnit').text(r.volumeUnit);
        									$('#maleVolumeSpan').text('给药容积');
        									
        									$('#maleVolumeTR').css('display','');
        									$('#maleVolume').attr('disabled',false);
        									
        									$('#femaleVolumeTR').css('display','none');
        									$('#femaleVolume').attr('disabled',true);
        								}else{
        									$('#maleVolumeTR').css('display','none');
        									$('#maleVolume').attr('disabled',true);
        									
        									$('#femaleVolumeTR').css('display','none');
        									$('#femaleVolume').attr('disabled',true);
        								}
        	    						if(r.thicknessUnit){
        	    							$('#maleThicknessUnit').text(r.thicknessUnit);
        	    							$('#maleThicknessSpan').text('给药浓度');
        	    							//给药浓度
        	    							$('#maleThicknessTR').css('display','');
        	    							$('#maleThickness').attr('disabled',false);
        	    							
        	    							$('#femaleThicknessTR').css('display','none');
        	    							$('#femaleThickness').attr('disabled',true);
        	    						}else{
        	    							//给药浓度
        	    							$('#maleThicknessTR').css('display','none');
        	    							$('#maleThickness').attr('disabled',true);
        	    							
        	    							$('#femaleThicknessTR').css('display','none');
        	    							$('#femaleThickness').attr('disabled',true);
        	    						}
        							
        						}
        						//打开dialog
        						$('#doseAddEditDialog').dialog('open'); 
        					}else{
        						//无动物
        						$('#dosageSpan').text('剂量');
        						$('#femaleDosageTR').css('display','none');
        						$('#femaleDosage').attr('disabled',true);
        						
        						$('#maleNumTR').css('display','none');
        						$('#maleNum').attr('disabled',true);
        						
        						$('#femaleNumTR').css('display','none');
        						$('#femaleNum').attr('disabled',true);
        						
        						$('#maleVolumeTR').css('display','none');
        						$('#maleVolume').attr('disabled',true);
        						
        						$('#femaleVolumeTR').css('display','none');
        						$('#femaleVolume').attr('disabled',true);
        						
        						$('#maleThicknessTR').css('display','none');
        						$('#maleThickness').attr('disabled',true);
        						
        						$('#femaleThicknessTR').css('display','none');
        						$('#femaleThickness').attr('disabled',true);
        						//打开dialog
        						$('#doseAddEditDialog').dialog('open'); 
        					}
        				}else{
        					$.messager.alert('警告','剂量组设计已确认，不可编辑。');    
        				}
        				
//        				map.put("dosageDesc", tblDoseSetting.getDosageDesc());
//        				map.put("dosage", tblDoseSetting.getDosage());
//        				map.put("femaleDosage", tblDoseSetting.getFemaleDosage());
//        				map.put("maleNum", tblDoseSetting.getMaleNum());
//        				map.put("femaleNum", tblDoseSetting.getFemaleNum());
//        				map.put("femaleVolume", tblDoseSetting.getFemaleVolume());
//        				map.put("maleVolume",tblDoseSetting.getMaleVolume());
//        				map.put("maleThickness", tblDoseSetting.getMaleThickness());
//        				map.put("femaleThickness", tblDoseSetting.getFemaleThickness());
        				//$("#dosageDesc").val(r.dosageDesc);
        				$('#dosageDesc').combobox('select',r.dosageDesc);
        				
        				
        				$("#dosage").val(r.dosage);
        				$("#femaleDosage").val(r.femaleDosage);
        				$("#maleNum").val(r.maleNum);
        				$("#femaleNum").val(r.femaleNum);
        				$("#femaleVolume").val(r.femaleVolume);
        				$("#maleVolume").val(r.maleVolume);
        				$("#maleThickness").val(r.maleThickness);
        				$("#femaleThickness").val(r.femaleThickness);
        				
        				$('#doseId').val(row.id);
        				//再编辑
        				if(r.state == "3" && r.doseSettingFlag == 1){
        					$("#maleNum").attr('disabled',true);
            				$("#femaleNum").attr('disabled',true);
        				}else if(r.state == "0" && r.doseSettingFlag == 1){//编辑
        					$("#maleNum").attr('disabled',true);
        					$("#femaleNum").attr('disabled',true);
        				}else{
        					$("#maleNum").attr('disabled',false);
        					$("#femaleNum").attr('disabled',false);
        				}
        			}
        		}
        	});
    	}
    }
}

/**
 * 初始化form
 * @return
 */
function initdoseAddEditForm(){
//	var studyNoPara= $('#studyNoPara').val();
//	var addOrEdit = $('#addOrEdit').val();
//	if(addOrEdit == 'add') {
//		$('#doseAddEditForm').form({    
//			url:sybp()+'/tblDoseSettingAction_saveDoseGroupInfo.action?+studyNoPara='+studyNoPara,
//			onSubmit: function(){    
//				var isValid = $(this).form('validate');
//				return isValid;	// 返回false终止表单提交
//			},    
//			success:function(data){  
//				var r = jQuery.parseJSON(data); 
//				if(r && r.success == 'true'){
//					$('#doseAddEditDialog').dialog('close');
//					myComeback();
//					parent.parent.showMessager(1,"剂量组添加成功！",true,5000);
//				}else if(r && r.msg){
//					$.messager.alert('警告',r.msg);     
//				}else{
//					$.messager.alert('警告','与服务器交互错误');     
//				}
//			} 
//		});    
//	}else{
//		$('#doseAddEditForm').form({    
//			url:sybp()+'/tblDoseSettingAction_updateDoseGroupInfo.action?+studyNoPara='+studyNoPara,
//			onSubmit: function(){    
//				var isValid = $(this).form('validate');
//				return isValid;	// 返回false终止表单提交
//			},    
//			success:function(data){  
//				var r = jQuery.parseJSON(data); 
//				if(r && r.success == 'true'){
//					$('#doseAddEditDialog').dialog('close');
//					myComeback();
//					parent.parent.showMessager(1,"剂量组编辑成功！",true,5000);
//				}else if(r && r.msg){
//					$.messager.alert('警告',r.msg);     
//				}else{
//					$.messager.alert('警告','与服务器交互错误');     
//				}
//			} 
//		}); 
//	}

}
/**
 * Radio选择
 * @param radio
 * @return
 */
function onRadioCk(radio){
	$('#'+radio).click();
}
/**
 * 单位选择框的选择事件
 * @param record
 * @return
 */
function onUnitSelect(record,obj){
	if(record.id == '-1'){
		$(obj).combobox('clear');
		
	}
	return false;
}
//是否是大于零的数
function checkData(value){
	if(!isNaN(value)){
		if(Number(value) < 0){
			return false;
		}else{
			if(value.length<15){
				return true;
			}else{
				return false;
			}
		}
	}else{
		return false;
	}
}

/**确定（保存）*/
function onDialogSaveButton(){
	
	var isValid = $('#doseAddEditForm').form('validate');
	if(isValid){
		//qm_showQianmingDialog('submitForm');
		//剂量
		var value = $('#dosage').val();
		//给药容积
    	var maleVolume = $('#maleVolume').val();
		if(!checkData(value) ){
			$.messager.confirm('确认','剂量不是数值类型，确定继续？',function(r){    
			    if (r){
			    	saveForDialogSaveButton();
			    }  
			}); 
		}else{
			saveForDialogSaveButton();
		}
		
		
	}
	
	
}

/**确定里调用
 * @return
 */
function saveForDialogSaveButton(){
	
	var flag = true;
	if($('#volumeUnit').combobox('getValue')){
		if(!$('#thicknessUnit').combobox('getValue')){
			flag = false;
		}
	}else{
		if($('#thicknessUnit').combobox('getValue')){
			flag = false;
		}
	}
	if(flag){
		submitDoseAddEditForm();
	}else{
		$.messager.alert('警告','容积单位与浓度单位必须同时选或同时不选。');    
	}
}

/**
 * form 提交
 * @return
 */
function submitDoseAddEditForm(){
	//$('#doseAddEditForm').submit(); 
	var studyNoPara= encodeURIComponent($('#studyNoPara').val());
	var addOrEdit = $('#addOrEdit').val();
	var dosageDesc = $('#dosageDesc').combobox('getText');
	if(dosageDesc){
		$('#dosageDesc').combobox({data:{
			id:dosageDesc,
			text:dosageDesc
		}});
		$('#dosageDesc').combobox('select',dosageDesc);
		
	}
	if(addOrEdit == 'add') {
		$.ajax({    
			url:sybp()+'/tblDoseSettingAction_saveDoseGroupInfo.action?+studyNoPara='+studyNoPara,
			type:'post',
			dataType:'json',
			data:$('#doseAddEditForm').serialize() ,
			success:function(r){  
				//var r = jQuery.parseJSON(data); 
				if(r && r.success == 'true'){
					if( isNoGenderFlag==1){
						lastMaleName=$('#maleNum').val();
						lastFemaleName=$('#femaleNum').val();
					}else if( isNoGenderFlag==2){
						lastNum=$('#maleNum').val();
					}
					
					$('#doseAddEditDialog').dialog('close');
					$('#selectedId').val(r.msg);
					myComeback();
					parent.parent.showMessager(1,"剂量组添加成功！",true,5000);
				}else if(r && r.msg){
					$.messager.alert('警告',r.msg);     
				}else{
					$.messager.alert('警告','与服务器交互错误');     
				}
			} 
		});    
	}else{
		$.ajax({    
			url:sybp()+'/tblDoseSettingAction_updateDoseGroupInfo.action?+studyNoPara='+studyNoPara,
			type:'post',
			dataType:'json',
			data:$('#doseAddEditForm').serialize() ,
			success:function(r){   
				//var r = jQuery.parseJSON(data); 
				if(r && r.success == 'true'){
					if( isNoGenderFlag==1){
						lastMaleName=$('#maleNum').val();
						lastFemaleName=$('#femaleNum').val();
					}else if(isNoGenderFlag==2){
						lastNum=$('#maleNum').val();
					}
					$('#doseAddEditDialog').dialog('close');
					myComeback();
					parent.parent.showMessager(1,"剂量组编辑成功！",true,5000);
				}else if(r && r.msg){
					$.messager.alert('警告',r.msg);     
				}else{
					$.messager.alert('警告','与服务器交互错误');     
				}
			} 
		}); 
	}
}

