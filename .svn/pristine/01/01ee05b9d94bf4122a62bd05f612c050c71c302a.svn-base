/**显示Dialog*/
function showDoseSettingDialog(clickName){
	document.getElementById("doseSettingDialog2").style.display="block";
	$('#doseSettingDialog').dialog('open'); 
	//回调函数
	this.myComeback = clickName;
	//填充数据(或清空数据)
	doseSettingFormFullEditData();
	//初始化form
	//initDoseSettingForm();
	
}
//填充数据(或清空数据)
function doseSettingFormFullEditData(){
    document.doseSettingForm.reset();//
    $('#doseSettingEdit').val('');
    var studyNoPara= encodeURIComponent($('#studyNoPara').val());
    if(studyNoPara){
    	$.ajax({
			url:sybp()+'/tblDoseSettingAction_loadBasicInfo.action?studyNoPara='+studyNoPara,
			type:'post',
			dataType:'json',
			success:function(r){
    			if(r && r.success == 'true'){
    				$('#doseSettingEdit').val('edit');
    				if(r.animalCodeMode == '2'){
    					$('#radioB').click();
    				}else if(r.animalCodeMode == '3'){
    					$('#radioC').click();
    				}else if(r.animalCodeMode == '9'){
    					$('#radio9').click();
    				}
    				if(r.isIndentical > 0){
    					$('#isIndentical').combobox('setValue',r.isIndentical);
    				}
    				if(r.isNoGender > 0){
    					$('#isNoGender').combobox('setValue',r.isNoGender);
    				}
    				
    				if(r.volumeUnit){
    					$('#volumeUnit').combobox('setValue',r.volumeUnit);
    				}
    				if(r.thicknessUnit){
    					$('#thicknessUnit').combobox('setValue',r.thicknessUnit);
    				}
    			}else{
    				if(r && r.animalCodeMode){
    					if(r.animalCodeMode == '2'){
        					$('#radioB').click();
        				}else if(r.animalCodeMode == '3'){
        					$('#radioC').click();
        				}else if(r.animalCodeMode == '9'){
        					$('#radio9').click();
        				}
    				}
    				$('#isIndentical').combobox('setValue',1);
    				$('#isNoGender').combobox('setValue',1);
    			}
    		}
    	});
    }
}

/**
 * 初始化form
 * @return
 */
function initDoseSettingForm(){
//	 var studyNoPara= $('#studyNoPara').val();
//	$('#doseSettingForm').form({    
//	    url:sybp()+'/tblDoseSettingAction_saveBasicInfo.action?studyNoPara='+studyNoPara,
//	    onSubmit: function(){    
//			var isValid = $(this).form('validate');
//			if (isValid){
////				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
//			}
//			return isValid;	// 返回false终止表单提交
//	    },    
//	    success:function(data){  
//	    	var r = jQuery.parseJSON(data); 
//	    	if(r && r.success == 'true'){
//	    		$('#doseSettingDialog').dialog('close');
//	    		parent.parent.showMessager(1,'剂量组基本信息保存成功！',true,5000);
//	    		myComeback(r.msg);
//	    	}else if(r && r.msg){
//	    		$.messager.alert('警告',r.msg);     
//	    	}else{
//	    		$.messager.alert('警告','与服务器交互错误');     
//	    	}
//	    } 
//	});    

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
 * 不分雌雄
 * @return
 */
function onIsNoGender(record){
	if(record.value == '2'){
		$('#isIndentical').combobox('setValue','1');
		$('#isIndentical').combobox('disable');
	}else{
		$('#isIndentical').combobox('enable');
	}
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

/**确定（保存）*/
function onDialogDoseSettingSaveButton(){
	
	var isValid = $('#doseSettingForm').form('validate');
	if(isValid){
		//qm_showQianmingDialog('submitForm');
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
			submitForm();
		}else{
			$.messager.alert('警告','容积单位与浓度单位必须同时选或同时不选。');    
		}
	}
	
	
}
/**
 * form 提交
 * @return
 */
function submitForm(){
	 var studyNoPara= encodeURIComponent($('#studyNoPara').val());
	 var doseSettingEdit = $('#doseSettingEdit').val();
	 
	 if(doseSettingEdit == 'edit'){
		 $.ajax({    
			 url:sybp()+'/tblDoseSettingAction_hasEditCheck.action?studyNoPara='+studyNoPara,
			 type:'post',
			 data:$('#doseSettingForm').serialize() ,
			 dataType:'json',
			 success:function(r){
    			if(r && r.success == 'true'){
    				$.messager.confirm('确认','编辑基本信息后，剂量组得重新设置，确定继续吗？',function(r){    
    				    if (r){    
    				    	$.ajax({    
    							 url:sybp()+'/tblDoseSettingAction_saveBasicInfo.action?studyNoPara='+studyNoPara,
    							 type:'post',
    							 data:$('#doseSettingForm').serialize() ,
    							 dataType:'json',
    							 success:function(r){  
    							 //var r = jQuery.parseJSON(data); 
    							 if(r && r.success == 'true'){
    								 isNoGenderFlag=$('#isNoGender').combobox('getValue');
    								 $('#doseSettingDialog').dialog('close');
    								 parent.parent.showMessager(1,'剂量组基本信息保存成功！',true,5000);
    								 myComeback(r.msg);
    							 }else if(r && r.msg){
    								 $.messager.alert('警告',r.msg);     
    							 }else{
    								 $.messager.alert('警告','与服务器交互错误');     
    							 }
    						 } 
    						 });   
    				    }    
    				}); 
    			}else{
    				 $('#doseSettingDialog').dialog('close');
    			}
    		}
    	});
	 }else{
		 $.ajax({    
			 url:sybp()+'/tblDoseSettingAction_saveBasicInfo.action?studyNoPara='+studyNoPara,
			 type:'post',
			 data:$('#doseSettingForm').serialize() ,
			 dataType:'json',
			 success:function(r){  
			 //var r = jQuery.parseJSON(data); 
			 if(r && r.success == 'true'){
				 $('#doseSettingDialog').dialog('close');
				 parent.parent.showMessager(1,'剂量组基本信息保存成功！',true,5000);
				 myComeback(r.msg);
			 }else if(r && r.msg){
				 $.messager.alert('警告',r.msg);     
			 }else{
				 $.messager.alert('警告','与服务器交互错误');     
			 }
		 } 
		 });    
	 }
}

