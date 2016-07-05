function addXButton(){
	var id=$('#normalid').val();
	showXAddDialog('afterAddXDialog','add','添加X光检疫信息',id);
	
}
function showXAddDialog(clickName,addOrEdit,title,id){
	$('#x_normalid').val(id);
	initXCheckArea();
	initXVeterinarian();
	initXProtector();
	initXRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#x_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("xAddDialog2").style.display="block";
	
	$('#xAddDialog').dialog('setTitle',title);
	$('#xAddDialog').dialog('open'); 
	document.getElementById("xAdd_event").href="javascript:"+clickName+"();";
}
function afterAddXDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveXButton(){
		var monkeyid=document.getElementById("x_monkeyid").value;
		if( $('#xAddForm').form('validate') ){
			$('#saveXDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/xAction_addNormalX.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#xAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveXDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#xAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var xAdd_event=document.getElementById("xAdd_event");
						xAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveXDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#xAddDialog').dialog('close'); 
							var xAdd_event=document.getElementById("xAdd_event");
							xAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}
function initXCheckArea(){
	$('#checkarea').combobox({
		url:sybp()+'/xAction_getCheckArea.action',
		valueField:'id',
		textField:'text',
		editable:false
	});
}
function initXVeterinarian(){
	$('#x_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initXProtector(){
	$('#x_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initXRecorder(){
	$('#x_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookXButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showXLookDialog(row,monkeyid,'查看X光检疫信息');
	}
}
function showXLookDialog(row,monkeyid,title){
	var monkeyid=document.getElementById("monkeyids").value;
	document.getElementById("xLookDialog2").style.display="block";
	
	$('#xLookDialog').dialog('setTitle',title);
	$('#xLookDialog').dialog('open');	 
	
	
  	    	$('#lookX').datagrid({
			url : sybp()+'/xAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
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
			           {title:'检疫类型',field:'ptype',width:80,halign:'center',align:'center'},
			           {title:'检测部位',field:'checkarea',width:80,halign:'center',align:'center'},
			           
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