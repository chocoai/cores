/**显示Dialog*/
function showCustomerAddEditDialog(clickName,addOrEdit,title){
	//主要产品下拉选
	tICodeLoadCombobox();
	/* 显示Dialog */
	document.getElementById("customerAddEditDialog2").style.display="block";
	//加载国家
	loadRigionLevel1();
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit"){
	  var row= $('#tblCustomerTable').datagrid('getSelected');
	  getPids(row);
		$.ajax({
    		url:sybp()+'/tblCustomerAction_todEitCustomer.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				fullEditInformation(r);
  	    			}
  	    		}
  	    	});

  		}
  
  		$('#addOrEdit').val(addOrEdit);
	$('#customerAddEditDialog').dialog('setTitle',title);
	$('#customerAddEditDialog').dialog('open'); 
	document.getElementById("customerAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
	var level1 = $('#tblRigionLevel1').combobox('getValue');
	if(level1=='1'){
		var level2 = $('#tblRigionLevel2').combobox('getValue');
		if(level2 != ''){
			level3 = $('#tblRigionLevel3').combobox('getValue');
			if(level3 != ''){
				$('#tblCustomerRegionId').val(level3);
			}else{
				 $('#tblCustomerRegionId').val(level2);
			}
		}else{
			 $('#tblCustomerRegionId').val(level1);
		}
	}else{
		$('#tblCustomerRegionId').val(level1);
	}
	
		if( $('#customerAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/tblCustomerAction_add.action',
					type:'post',
					data:$('#customerAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#customerAddEditDialog').dialog('close'); 
						$('#regionId').val(r.obj);
						$('#cuid').val(r.id);
						var customerAddEdit_event=document.getElementById("customerAddEdit_event");
						customerAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#customerAddEditDialog').dialog('close'); 
							var customerAddEdit_event=document.getElementById("customerAddEdit_event");
							customerAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/tblCustomerAction_edit.action',
					type:'post',
					data:$('#customerAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#customerAddEditDialog').dialog('close'); 
						$('#regionId').val(r.obj);
						$('#cuid').val(r.id);
						var customerAddEdit_event=document.getElementById("customerAddEdit_event");
						customerAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#customerAddEditDialog').dialog('close'); 
							var customerAddEdit_event=document.getElementById("customerAddEdit_event");
							customerAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}
//加载所选客户的regionId,以及在地区表中regionId可能有的父id及父id的父id
function getPids(row){
//	var row0= $('#tblCustomerTable1').datagrid('getSelected');
	$.ajax({
		url:sybp()+'/tblCustomerAction_getRegionIds.action',
		type:'post',
  		data:{
			 id:row.id
  		},
  		dataType:'json',
  		success:function(r){
  			if(r){
  				$('#regionId').val(r.regionId);
  				$('#level1pid').val(r.pid);
  				$('#level2pid').val(r.pid2);
  				loadRigionLevel1();
  			}
  		}
	});
}
//编辑客户时填充选中的客户信息到编辑界面
 function  fullEditInformation(r){
	   if(r == ""){
		   $('#customerNameadd').val('');
		   $('#tblCustomercheckName').val('');
		   $('#address').val('');
		   $('#linkMan').val('');
		   $('#tel').val('');
		   $('#mobile').val('');
		   $('#email').val('');
		   $('#http').val('');
		   $('#fax').val('');
		   $('#postalCode').val('');
 		   $('#customerType').combobox('select','1');
	  	   $('#tICode').combobox('select','01');
	   }else{
		    $('#tblCustomerId').val(r.id);
			$('#customerType').combobox('select',r.customerType);
			$('#customerNameadd').val(r.customerName);
			$('#tblCustomercheckName').val(r.customerName);
			$('#address').val(r.address);
			$('#linkMan').val(r.linkman);
			$('#tel').val(r.tel);
			$('#mobile').val(r.mobile);
			$('#email').val(r.email);
			$('#http').val(r.http);
			$('#fax').val(r.fax);
			$('#postalCode').val(r.postalCode);
			$('#tICode').combobox('select',r.tiCode);
	   }
	   
  }

//主要产品下拉选
function tICodeLoadCombobox(){
	//主要产品类型
	$('#tICode').combobox({    
		url : sybp()+'/tblCustomerAction_getAllDictTestItemType.action',
	    valueField:'id',    
	    textField:'text',
	    panelHeight:90,
	    editable:false,
//	    onChange:function(newValue, oldValue){
	//    
//	    },
//	    onLoadSuccess:function(none){
	//	
//	    }   
	});  
	
}


//国家下拉选
function loadRigionLevel1(){
	//国家ComboBox
	var allData = $('#tblRigionLevel1').combobox('getData');
	if(allData && allData.length < 1){
		$('#tblRigionLevel1').combobox({    
			url : sybp()+'/tblRegionAction_loadLevel1.action?level=1',
			valueField:'id',    
			textField:'text',
			required:true,
			onChange:function(newValue, oldValue){
			if(oldValue){
				if(newValue == 1){
					$("#tblRigionLevel2").combobox({    
						disabled:false});
					$("#tblRigionLevel3").combobox({    
						disabled:false});
					loadRigionLevel2(newValue);
					$('#tblRigionLevel2').combobox({    
						required:true});
				}else{    
					$("#tblRigionLevel2").combobox({    
						disabled:true});
					$("#tblRigionLevel3").combobox({    
						disabled:true});
					document.getElementById('rigionlevel2tr').style.display = 'none';
					document.getElementById('rigionlevel3tr').style.display = 'none';
					loadRigionLevel2(newValue);
					$('#tblRigionLevel2').combobox({    
						required:false});
				}
			}
		},
		onLoadSuccess:function(none){
			var rid = $('#regionId').val();
			if(rid ){
				document.getElementById("tblRigionLevel2").disabled =false;
				document.getElementById("tblRigionLevel3").disabled =false;
				var pid = $('#level1pid').val();
				var pid2 = $('#level2pid').val() ;
				if(rid == 1 || pid == 1||pid2 == 1){
					if(pid2 == "" && pid == ""){
						$('#tblRigionLevel1').combobox('select',rid);
						loadRigionLevel2(rid);
					}else if(pid != "" && pid2 == "" ){
						$('#tblRigionLevel1').combobox('select',pid);
						loadRigionLevel2(pid);
					}else{
						$('#tblRigionLevel1').combobox('select',pid2);
						loadRigionLevel2(pid2);
					}
					$('#tblRigionLevel2').combobox({    
						required:true});
				}else{
					$('#tblRigionLevel1').combobox('select',rid);
					document.getElementById('rigionlevel2tr').style.display = 'none';
					document.getElementById('rigionlevel3tr').style.display = 'none';
					document.getElementById("tblRigionLevel2").disabled =true;
					document.getElementById("tblRigionLevel3").disabled =true;
				}
			}else{
				$('#tblRigionLevel1').combobox('select','1');
				loadRigionLevel2(1);
			}
		}   
		});  
	}else{
		var rid = $('#regionId').val();
		if(rid ){
			var pid = $('#level1pid').val();
			var pid2 = $('#level2pid').val() ;
			if(rid == 1 || pid == 1||pid2 == 1){
				if(pid2 == "" && pid == ""){
					$('#tblRigionLevel1').combobox('select',rid);
					loadRigionLevel2(rid);
				}else if(pid != "" && pid2 == "" ){
					$('#tblRigionLevel1').combobox('select',pid);
					loadRigionLevel2(pid);
				}else{
					$('#tblRigionLevel1').combobox('select',pid2);
					loadRigionLevel2(pid2);
				}
				$('#tblRigionLevel2').combobox({    
					required:true});
			}else{
				$('#tblRigionLevel1').combobox('select',rid);
				document.getElementById('rigionlevel2tr').style.display = 'none';
				document.getElementById('rigionlevel3tr').style.display = 'none';
			}
		}else{
			$('#tblRigionLevel1').combobox('select','1');
			loadRigionLevel2(1);
		}
	}
}

//省份下拉选
function loadRigionLevel2(thepid){
	//省份
	$('#tblRigionLevel2').combobox({    
		url : sybp()+'/tblRegionAction_loadLevel1.action?level=2&pid='+thepid,
	    valueField:'id',    
	    textField:'text',
	    required:true,
	    editable:false,
	    onChange:function(newValue, oldValue){
          loadRigionLevel3(newValue);
      },
	    onLoadSuccess:function(none){
		 var rid = $('#regionId').val();
		 var pid = $('#level1pid').val();
		 var pid2 = $('#level2pid').val();
		 var onchange = $('#onchange').val();
		 if(thepid != "1"){
//			 $('#rigionlevel2tr').css('display','none');
//			 $('#rigionlevel3tr').css('display','none');
			  document.getElementById('rigionlevel2tr').style.display = 'none';
		      document.getElementById('rigionlevel3tr').style.display = 'none';
			 var value2 = $('#tblRigionLevel2').combobox('getValue');
           if(value2 != ""){
				 $('#tblRigionLevel2').combobox('select','');
               }
			 var value3 = $('#tblRigionLevel3').combobox('getValue');
			 if(value3 != ""){
			 	$('#tblRigionLevel3').combobox('select','');
			 }
	     }else{
				if(pid==""&&pid2==""){
				  $('#tblRigionLevel2').combobox('select','');
				}else if(pid!=""&&pid2==""){
					$('#tblRigionLevel2').combobox('select',rid);
				}else{
					$('#tblRigionLevel2').combobox('select',pid);
				}  
				 $('#rigionlevel2tr').css('display','');
				 $('#rigionlevel3tr').css('display','');
			 }
		
	    }   
	});
}

function loadRigionLevel3(pid3){
	//市区
	$('#tblRigionLevel3').combobox({    
		url : sybp()+'/tblRegionAction_loadLevel1.action?level=3&pid='+pid3,
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		    $('#tblRigionLevel3').combobox('select','');
			var rigionvalue= $('#tblRigionLevel2').combobox('getText');
		    if(rigionvalue =="北京市" ||
		       rigionvalue =="天津市" ||
		       rigionvalue =="上海市" ||
		       rigionvalue =="重庆市" 
		        ){
		       $('#province').val('直辖市： ');
		       $('#rigionlevel3tr').css('display','none');
		       $('#tblRigionLevel3').combobox('select','');
		    }else if(rigionvalue =="香港特别行政区" ||
				       rigionvalue =="澳门特别行政区" ){
		    	 $('#province').val('行 政 区 ');
			       $('#rigionlevel3tr').css('display','none');
			       $('#tblRigionLevel3').combobox('select','');
		    }else{
		       $('#rigionlevel3tr').css('display','');
		       $('#province').val('省     份 ');
		       var rid = $('#regionId').val();
				 var pid = $('#level1pid').val();
				 var pid2 = $('#level2pid').val();
				if(pid!=""&&pid2!=""){
				  if(pid == pid3){
					  $('#tblRigionLevel3').combobox('select',rid);
				  }else{
					  $('#tblRigionLevel3').combobox('select','');
				  }
				  
				}else{
					$('#tblRigionLevel3').combobox('select','');
				}
		    }
		  
		}
		 
	});
}

