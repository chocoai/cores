/**显示Dialog*/
function showSchedulePlanAddEditDialog(clickName,addOrEdit,title){
	initOwerCombobox();
	initSchedulePlanTypeCombobox();
	/* 显示Dialog */
	document.getElementById("schedulePlanAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		fullEditInformation("");
	}else if(addOrEdit == "edit" ||addOrEdit=="view"){
		var row= $('#schedulePlanTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/schedulePlanAction_toEdit.action',
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
		if(addOrEdit == "view"){
			$('#saveDialogButton').linkbutton('disable');
		}else{
			$('#saveDialogButton').linkbutton('enable');
		}

  	}
  
  	$('#addOrEdit').val(addOrEdit);
	$('#schedulePlanAddEditDialog').dialog('setTitle',title);
	$('#schedulePlanAddEditDialog').dialog('open'); 
	document.getElementById("schedulePlanAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDialogSaveButton(){
		if( $('#schedulePlanAddEditForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#addOrEdit').val() == 'add'){
				$.ajax({
					url:sybp()+'/schedulePlanAction_add.action',
					type:'post',
					data:$('#schedulePlanAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#schedulePlanAddEditDialog').dialog('close'); 
						$('#sid').val(r.id);
						var areaAddEdit_event=document.getElementById("schedulePlanAddEdit_event");
						areaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#schedulePlanAddEditDialog').dialog('close'); 
							var areaAddEdit_event=document.getElementById("schedulePlanAddEdit_event");
							areaAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/schedulePlanAction_editSave.action',
					type:'post',
					data:$('#schedulePlanAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#schedulePlanAddEditDialog').dialog('close');
						$('#sid').val(r.id);
						var areaAddEdit_event=document.getElementById("schedulePlanAddEdit_event");
						areaAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#schedulePlanAddEditDialog').dialog('close'); 
							var areaAddEdit_event=document.getElementById("schedulePlanAddEdit_event");
							areaAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

 function  fullEditInformation(r){
	   if(r == ""){
		   $('#id').val('');
		   $('#title').val('');
		   $('#contant').val('');
		   $('#begindate').datebox('setValue','');
		   $('#enddate').datebox('setValue', '');
		   $('#ower').combobox('select','');
		   
		   $('#typeid').combobox('select','');
		   $('#status').combobox('select','');
		   $('#remark').val('');
	   }else{
		   $('#id').val(r.id);
		   $('#title').val(r.title);
		   $('#contant').val(r.contant);
		   $('#begindate').datebox('setValue',r.begindate);
		   $('#enddate').datebox('setValue', r.enddate);
		   if(r.ower==0){
			   $('#ower').combobox('select','');
		   }else{
			   $('#ower').combobox('select',r.ower);
		   }
		   $('#typeid').combobox('select',r.typeid);
		   $('#status').combobox('select',r.status);
		   $('#remark').val(r.remark);
	   }
	   
  }

function initOwerCombobox(){
	$('#ower').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    required:true,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
function initSchedulePlanTypeCombobox(){
	$('#typeid').combobox({
		url:sybp()+'/schedulePlanAction_getSchedulePlanType.action',
		valueField:'id',
		textField:'text',
		editable:false,
		required:true
	});
}

function showSchedulePlanSelectDialog(clickName,title){
	initOwerCombobox1();
	initSchedulePlanTypeCombobox1();
	/* 显示Dialog */
	document.getElementById("schedulePlanSelectDialog2").style.display="block";
	
	$('#begindate1').datebox('setValue', '');
	$('#enddate1').datebox('setValue', '');
	$('#title1').val('');
	$('#typeid1').combobox('select','');
	$('#ower1').combobox('select','');
	$('#status1').combobox('select','');
	
  	$('#addOrEdit').val(addOrEdit);
	$('#schedulePlanSelectDialog').dialog('setTitle',title);
	$('#schedulePlanSelectDialog').dialog('open'); 
	document.getElementById("schedulePlanSelect_event").href="javascript:"+clickName+"();";
	
	var date=new Date();
	var year = date.getFullYear();       //年
    var month = date.getMonth() + 1;     //月
    var day = date.getDate();
    
    var time1=year+"-";
    
    var time2=year+'-';
    var month2=month+1;
    if(month<10){
    	time1+="0";
    }
    if(month2<10){
    	time2+="0";
    }
    time2+=month2+'-';
    time1+=month+"-";
    if(day<10){
    	
    	time1+="0";
    	time2+="0";
    }
    time1+=day;
    time2+=day;
    
    $('#begindate1').datebox('setValue',time1);
    $('#enddate1').datebox('setValue',time2);
}
function onDialogSelectButton(){
	var title=$('#title1').val();
	 var begindate =   $('#begindate1').datebox('getValue');
	 var enddate =   $('#enddate1').datebox('getValue');

	 var typeid=$('#typeid1').combobox('getValue');

	 var ower=$('#ower1').combobox('getValue'); 
	 var status=$('#status1').combobox('getValue');
	 
	 var statusVal='';
	 if(status==1){statusVal="新建"}
	 else if(status==2){statusVal="开始"}
	 else if(status==3){statusVal="结束"}
	 else{statusVal=""}
	if( $('#schedulePlanSelectForm').form('validate') ){
		$('#selectDialogButton').linkbutton('disable');
		$.ajax({
			url : sybp()+'/schedulePlanAction_getSchedulePlandatagrid.action',
			type: 'post',
			data: {
		         title:title,
                 begindate:begindate,
                 enddate:enddate,
                 ower:ower,
                 typeid:typeid,
                 status:status
                 //page:1,
                 //rows:12
			},
			dataType:'json',
			success:function(r){
				if(r){
					$('#selectDialogButton').linkbutton('enable');
					$('#schedulePlanSelectDialog').dialog('close');
					//schedulePlanTable.datagrid({
					//	columns:r.columns
					//});
					//schedulePlanTable.datagrid('loadData',r.rows);
					$('#schedulePlanTable').datagrid('loadData',r.rows);
					$('#schedulePlanTable').datagrid({
						url : sybp()+'/schedulePlanAction_getSchedulePlandatagrid.action?ower='+ower+'&typeid='+typeid+'&title='+title+'&begindate='+begindate+'&enddate='+enddate+'&status='+status
						//pagination:true,//分页
						//pageSize : 12,//默认选择的分页是每页5行数据         
						//pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
					    //nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
					    //striped : true,//设置为true将交替显示行背景。      
					    //collapsible : true//显示可折叠按钮
					});
					 
					$('#description').val("开始日期："+begindate +"， 结束日期："+enddate+"。 计划标题："+title+"。 计划操作者："+r.owerName+"。 计划类型："+r.typeName+"。 计划执行状态："+statusVal+"。");
				}
			},
			beforeSend:function(){ 
		        //$('#progressbar').css('display',''); 
	            var value = $('#p').progressbar('getValue');
	            if(value == 100){
	            	value = 10;
	            }
				if (value < 100){
					value += Math.floor(Math.random() * 10);
					$('#p').progressbar('setValue', value);
					setTimeout(arguments.callee, 200);
				}
        }, 
        complete:function(){ 
           $('#progressbar').css('display',''); 
           $('#p').progressbar('setValue', 100);
           $('#progressbar').css('display','none'); 
        } 
     });
	}
}
function initOwerCombobox1(){
	$('#ower1').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
}
function initSchedulePlanTypeCombobox1(){
	$('#typeid1').combobox({
		url:sybp()+'/schedulePlanAction_getSchedulePlanType.action',
		valueField:'id',
		textField:'text',
		editable:false
	});
}