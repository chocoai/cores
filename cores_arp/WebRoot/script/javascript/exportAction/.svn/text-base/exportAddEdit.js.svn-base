function showExportAddDialog(clickName,addOrEdit,title){
	
	/* 显示职位主Dialog */
	 document.getElementById("exportAddDialog2").style.display="block";
	 if(addOrEdit == "add"){
		fullEditInformation("");
	 }
	 	initBossCombobox();
		initAreaCombobox();
		initRoomCombobox('');
		initMonkeyCombobox('','');
  		$('#addOrEdit').val(addOrEdit);
		$('#exportAddDialog').dialog('setTitle',title);
		$('#exportAddDialog').dialog('open'); 
		document.getElementById("exportAdd_event").href="javascript:"+clickName+"();";
  
}
function afterAddExportDialog(){
	parent.showMessager(1,'出口检疫添加成功',true,5000);
	$('#exportTable').datagrid('reload');
}
function onDialogSaveButton111(){
	if(document.getElementById("surface").checked==true){
		var surface="export";
	}else{
		var surface='';
	}
	if(document.getElementById("tb").checked==true){
		var tb="export";
	}if(document.getElementById("tb").checked==false){
		var tb='';
	}
	if(document.getElementById("parasite").checked==true){
		var parasite="export";
	}else{
		var parasite='';
	}
	if(document.getElementById("virus").checked==true){
		var virus='export';
	}if(document.getElementById("virus").checked==false){
		var virus='';
	}
	if(document.getElementById("bacteria").checked==true){
		var bacteria='export';
	}if(document.getElementById("bacteria").checked==false){
		var bacteria='';
	}
	if(document.getElementById("vaccine").checked==true){
		var vaccine='export';
	}if(document.getElementById("vaccine").checked==false){
		var vaccine='';
	}
	if(document.getElementById("x").checked==true){
		var x='export';
	}if(document.getElementById("x").checked==false){
		var x='';
	}
	var infectious;
	if(document.getElementById("infectious").checked==true){
		infectious='export';
	}if(document.getElementById("infectious").checked==false){
		infectious='';
	}
		if( $('#exportAddForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/exportAction_addExport.action?surface='+surface+'&tb='+tb+'&parasite='+parasite+'&virus='
					+virus+'&bacteria='+bacteria+'&vaccine='+vaccine+'&x='+x+'&infectious='+infectious,
					type:'post',
					data:$('#exportAddForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#exportAddDialog').dialog('close'); 
						$('#bid').val(r.id);
						var exportAdd_event=document.getElementById("exportAdd_event");
						exportAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#exportAddDialog').dialog('close'); 
							var exportAdd_event=document.getElementById("exportAdd_event");
							exportAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/normalAction_editSave.action',
					type:'post',
					data:$('#exportAddForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#exportAddDialog').dialog('close'); 
						$('#bid').val(r.id);
						var exportAdd_event=document.getElementById("exportAdd_event");
						exportAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#exportAddDialog').dialog('close'); 
							var exportAdd_event=document.getElementById("exportAdd_event");
							exportAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

 /*function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#title').val('');
	  	   $('#boss').val('');
	  	   $('#checkdate').datebox('select','');
	  	   $('#area').combobox('select','');
	  	   $('#room').combobox('select','');
	  	   $('#monkey').combobox('select','');
	  	   $('#monkeylist').val('');
	  	   $('#recorder').combobox('select','');
	  	   $('#protector').combobox('select','');
	   }else{
		   
	   }
	   
  }*/
function  fullEditInformation(r){
	   
		   $('#id').val('');
		   $('#title').val('');
	  	   $('#boss').val('');
	  	   //reload.后需优化.此方法js对select报错。
	  	   //$('#checkdate').datebox('select','');
	  	  // $('#area').combobox('select','');
	  	  // $('#room').combobox('select','');
	  	  // $('#monkey').combobox('select','');
	  	 //  $('#monkeylist').val('');
	  	  // $('#recorder').combobox('select','');
	  	  // $('#protector').combobox('select','');
	   
		   
	   
	   
}
 function initBossCombobox(){
 	$('#boss').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 
 function initBlongareaCombobox(){
	$('#area').combobox({    
		url : sybp()+'/areaAction_getPareaNameId.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		  
		},
		onSelect:function(rec){
			var id=$('#area').combobox('getValue');
		    $('#room').combobox({
		    	url : sybp()+'/areaAction_getAllRoomIdName.action?blongarea='+id,
		    	valueField:'id',    
	    		textField:'text',
	    		editable:false,
	    		onLoadSuccess:function(nonee){
				},
				onSelect:function(rec){
					var areaid=$('#area').combobox('getValue');
					var roomid=$('#room').combobox('getValue');
					$('#monkeyid').combobox({
						url : sybp()+'/individualAction_getRoomIndividual.action?roomid='+roomid+'&area='+areaid,
						valueField:'id',
						textField:'text',
						editable:false,
						onLoadSuccess:function(none){
						}
					});
				}
		    }); 
		}
			
	});
}
function initAreaCombobox(){
 	$('#area').combobox({    
 		url : sybp()+'/areaAction_getPareaNameId.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		  
 		},
 		onSelect:function(record){
 			initRoomCombobox(record.id);  
 		}
 			
 	});
 }
function initRoomCombobox(id){
	var areaid=$('#area').combobox('getValue');
	if(id!=''&&id!=null){
 	  $('#room').combobox({    
 		url : sybp()+'/areaAction_getAllRoomIdName.action?blongarea='+id,
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    onLoadSuccess:function(none){
 		},
 		onSelect:function(rec){
 			initMonkeyCombobox(rec.id,areaid);
 			//onSearchmonkeyButton();
 		}
 	  });
 	}else{
 		$('#room').combobox({ });   
 	}
}

function initMonkeyCombobox(roomid,areaid){
	if((roomid!=''&&roomid!=null)&&(areaid!=''&&areaid!=null)){
 	  $('#monkey').combobox({    
 		url : sybp()+'/individualAction_getRoomIndividual.action?roomid='+roomid+'&area='+areaid,
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    required:true,
 	    multiple:true,
 	    onLoadSuccess:function(none){
 		},
 		onSelect:function(rec){
 		var textobj=document.getElementById('monkeylist');
		textobj.innerHTML=$('#monkey').combobox('getText');
 		}
 	  });
 	}else{
 		$('#monkey').combobox({ });   
 	}
}

/*function initCombotree(){
 	  $('#monkey').combotree({    
 		url : sybp()+'/individualAction_loadMonkeyCom.action', 	    
 	    multiple:true,
 	    onLoadSuccess:function(none){
 		},
 		onSelect:function(rec){
 		var textobj=document.getElementById('monkeylist');
		textobj.innerHTML=$('#monkey').combotree('getSelected');
		
 		},
 		onClick:function(node){
 			//返回树对象  
 	        var tree = $(this).tree;  
 	        //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
 	        var isLeaf = tree('isLeaf', node.target);  
 	        if (!isLeaf) {  
 	            //清除选中  
 	            $('#monkey').combotree('clear');  
 	        }
 		}
 	  });
 	
}*/
//上传检疫信息附件
	function uploadCheckItemsButton(){
		var row = $('#exportTable').datagrid('getSelected');
		var regCheckFlg=row.status;
		//1:提交未检疫  2：检疫已录入.
		if(regCheckFlg=="1"){
			showCheckItemsAddDialog('afterupLoadAttachment','add','上传检疫信息附件');
			$('#attachmenttestItemCode').val(row.title);//检疫编号
			$('#id').val(row.id);//批次检疫信息id,唯一标识.
		}
	 }
 /**显示Dialog*/
	function showCheckItemsAddDialog(clickName,add,title){
		/*签名Dialog*/
		document.getElementById("attachmentAddEditDialog2").style.display="block";
		$('#attachmentAddOrEdit').val(addOrEdit);
		$('#attachmentAddEditDialog').dialog('setTitle',title);
		$('#attachmentAddEditDialog').dialog('open'); 
		document.getElementById("attachmentAddEdit_event").href="javascript:"+clickName+"();";
		//清空数据
		attachmentFullEditData();
		//初始化form
		initAttachmentForm();
	}
	function afterupLoadAttachment(){
	       // var row = $('#selectTestItemAttachmentTable').datagrid("getRows");
	       //if(row.length < 1){
	       //    var table = $('#testItemTable').datagrid("getSelected");
	       //    selectTestItemAttachmentTable(table.tiNo);
	      // }else{
	      //   $('#selectTestItemAttachmentTable').datagrid("reload");
	      // }
	       parent.showMessager(1, '检疫信息附件上传成功', true, 5000);
	       
	    }
//填充数据(或清空数据)
	function attachmentFullEditData(){
	    document.attachmentAddEditForm.reset();//
	}
 
	/**
	 * 文件选择框   onchange事件
	 * @param obj
	 * @return
	 */
	function attachmentFileChange(obj){
		var fullFileName = obj.value;
		if(!fullFileName){
			return false;
		}
	//	var pos = fileName.lastIndexOf("\");
	//			fileName.substring(pos+1); 
		
		
		//判断文件名是否存在，若存在则清空且提示
		var uploadFiles = document.getElementsByName('uploadFile');
		
		var num = 0;
		for(var i = 0;i < uploadFiles.length; i++){
			if(uploadFiles[i].value == fullFileName){
				num++;
			}
		} 
		var pos = fullFileName.lastIndexOf('\\');
		var fileName = fullFileName.substring(pos+1);
		if(num >1){
			$(obj).val('');
			$.messager.alert('警告',"文件'"+fileName+"'已选择，请选择其他文件。"); 
		}else{
			var pos1 = fileName.lastIndexOf('.');
			//有无后缀
			if(pos1>-1){
				suffix = fileName.substring(pos1+1);
				if(suffix == 'exe' || suffix == 'bat'){
					//无后缀
					$(obj).val('');
					$.messager.alert('警告',"不能上传以.exe或.bat结尾的文件。"); 
				}
			}else{
				//无后缀
				$(obj).val('');
				$.messager.alert('警告',"文件格式不正确。"); 
			}
			//if(!$('#describe').val()){
			//	$('#describe').val(fileName);
			//}
			//alert(fileName);
			//alert(fullFileName);
			var  object = document.getElementsByName('uploadFile');
	        for ( var  i = 0 ;i < object.length;i ++ ){
			    if(fullFileName == object[i].value){
			       var  object = document.getElementsByName('attachmentNames');
	               object[i].value=fileName;
	               break;
			    }
			}
		}
	}
	
	function onDialogAttachmentSaveButton(){
	   if($('#attachmentAddEditForm').form('validate')){
			//qm_showQianmingDialog('afterOnDialogAttachmentSaveButton');
		   afterOnDialogAttachmentSaveButton();
	   }
	}

 function afterOnDialogAttachmentSaveButton(){
        $.messager.progress({ text :'上传中...' });	// 显示进度条
	       $('#attachmentAddEditForm').submit(); 
 }
 
	function initAttachmentForm(){
		$('#attachmentAddEditForm').form({    
		    url:sybp()+'/exportAction_uploadFile.action',
		    onSubmit: function(){    
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){   
		    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
		    	if(data == 'true'){
		    		$('#attachmentAddEditDialog').dialog('close');
		    		document.getElementById("attachmentAddEdit_event").click();
		    		$('#exportTable').datagrid('reload');
		    		$('#exportCheckItemsAddButton').linkbutton('disable');
		    	}else{
		    		$.messager.alert('警告','文件上传失败'); 
		    	}
		    } 
		});    
	}