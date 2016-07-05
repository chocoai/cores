function addVaccineButton(){
	var normalid=$('#normalid').val();
	showVaccineAddDialog('afterAddVaccineDialog','add','添加疫苗接种信息',normalid);
	
}
function showVaccineAddDialog(clickName,addOrEdit,title,normalid){
	$('#vaccine_normalid').val(normalid);
	initVaccineYMLX();
	initVaccineVeterinarian();
	initVaccineProtector();
	initVaccineRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#vac_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("vaccineAddDialog2").style.display="block";
	
	$('#vaccineAddDialog').dialog('setTitle',title);
	$('#vaccineAddDialog').dialog('open'); 
	document.getElementById("vaccineAdd_event").href="javascript:"+clickName+"();";
}
function afterAddVaccineDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveVaccineButton(){
		var monkeyid=document.getElementById("vac_monkeyid").value;
		if( $('#vaccineAddForm').form('validate') ){
			$('#saveVaccineDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/vaccineAction_addNormalVaccine.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#vaccineAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveVaccineDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#vaccineAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var vaccineAdd_event=document.getElementById("vaccineAdd_event");
						vaccineAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveVaccineDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#vaccineAddDialog').dialog('close'); 
							var vaccineAdd_event=document.getElementById("vaccineAdd_event");
							vaccineAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}
function initVaccineYMLX(){
	$('#vac_ymlx').combobox({
		url:sybp()+'/vaccineAction_loadYMLX.action',
		valueField:'id',
		textField:'text',
		editable:false
	});
}
function initVaccineVeterinarian(){
	$('#vac_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initVaccineProtector(){
	$('#vac_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initVaccineRecorder(){
	$('#vac_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookVaccineButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showVaccineLookDialog(row,monkeyid,'查看疫苗接种信息');
	}
}
function showVaccineLookDialog(row,monkeyid,title){
	document.getElementById("vaccineLookDialog2").style.display="block";
	var monkeyid=document.getElementById("monkeyids").value;
	$('#vaccineLookDialog').dialog('setTitle',title);
	$('#vaccineLookDialog').dialog('open');	 
	
	
  	    	$('#lookVaccine').datagrid({
			url : sybp()+'/vaccineAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'monkeyid',
			columns :[
			          [
			           {title:'monkeyid',field:'monkeyid',width:150,hidden:true},
			           {title:'检疫时间',field:'cdate',width:80,halign:'center',align:'center'},
			           {title:'疫苗类型',field:'ymlx',width:80,halign:'center',align:'center'},
			{title:'检测兽医',field:'veterinarian',width:80,halign:'center',align:'center'},
			{title:'保定人员',field:'protector',width:80,halign:'center',align:'center'},
			{title:'记录人员',field:'recorder',width:80,halign:'center',align:'center'},
			
			{title:'备注',field:'remark',width:80,halign:'center',align:'center'}
					]
			          ],
			
			onSelect:function(rowIndex, rowData){
			},
			onLoadSuccess:function(data){
	          
			},
			toolbar:'',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
		    
	   	});
  	        
}