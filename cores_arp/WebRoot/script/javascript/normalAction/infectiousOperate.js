function addInfectiousButton(){
	var normalid=$('#normalid').val();
	showInfectiousAddDialog('afterAddInfectiousDialog','add','添加传染病检测信息',normalid);
}
function showInfectiousAddDialog(clickName,addOrEdit,title,normalid){
	$('#infectious_normalid').val(normalid);
	initInfectiousVeterinarian();
	initInfectiousProtector();
	initInfectiousRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#inf_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("infectiousAddDialog2").style.display="block";
	
	$('#infectiousAddDialog').dialog('setTitle',title);
	$('#infectiousAddDialog').dialog('open'); 
	document.getElementById("infectiousAdd_event").href="javascript:"+clickName+"();";
}
function afterAddInfectiousDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveInfectiousButton(){
		var monkeyid=document.getElementById("inf_monkeyid").value;
		if( $('#infectiousAddForm').form('validate') ){
			$('#saveInfectiousDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/infectiousAction_addNormalInfectious.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#infectiousAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveInfectiousDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#infectiousAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var infectiousAdd_event=document.getElementById("infectiousAdd_event");
						infectiousAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveInfectiousDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#infectiousAddDialog').dialog('close'); 
							var infectiousAdd_event=document.getElementById("infectiousAdd_event");
							infectiousAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initInfectiousVeterinarian(){
	$('#inf_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initInfectiousProtector(){
	$('#inf_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initInfectiousRecorder(){
	$('#inf_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookInfectiousButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showInfectiousLookDialog(row,monkeyid,'查看传染病检测信息');
	}
}
function showInfectiousLookDialog(row,monkeyid,title){
	var monkeyid=document.getElementById("monkeyids").value;
	document.getElementById("infectiousLookDialog2").style.display="block";
	
	$('#infectiousLookDialog').dialog('setTitle',title);
	$('#infectiousLookDialog').dialog('open');	 
	
	
  	    	$('#lookInfectious').datagrid({
			url : sybp()+'/infectiousAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
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
			           {title:'传染病名称',field:'crbmc',width:80,halign:'center',align:'center'},
			{title:'检测兽医',field:'veterinarian',width:80,halign:'center',align:'center'},
			{title:'保定人员',field:'protector',width:80,halign:'center',align:'center'},
			{title:'记录人员',field:'recorder',width:80,halign:'center',align:'center'},
			{title:'检测药品名称',field:'drugsname',width:80,halign:'center',align:'center'},
			{title:'药剂量',field:'drugscount',width:80,halign:'center',align:'center'},
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