function addTbButton(){
	var normalid=$('#normalid').val();
	showTbAddDialog('afterAddTbDialog','add','添加TB检测信息',normalid);
	
}
function showTbAddDialog(clickName,addOrEdit,title,normalid){
	$('#tb_normalid').val(normalid);
	initTbVeterinarian();
	initTbProtector();
	initTbRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#tb_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("tbAddDialog2").style.display="block";
	
	$('#tbAddDialog').dialog('setTitle',title);
	$('#tbAddDialog').dialog('open'); 
	document.getElementById("tbAdd_event").href="javascript:"+clickName+"();";
}
function afterAddTbDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveTbButton(){
		var monkeyid=document.getElementById("tb_monkeyid").value;
		if( $('#tbAddForm').form('validate') ){
			$('#saveTbDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/tbAction_addNormalTB.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#tbAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveTbDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#tbAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var tbAdd_event=document.getElementById("tbAdd_event");
						tbAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveTbDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#tbAddDialog').dialog('close'); 
							var tbAdd_event=document.getElementById("tbAdd_event");
							tbAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initTbVeterinarian(){
	$('#tb_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initTbProtector(){
	$('#tb_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initTbRecorder(){
	$('#tb_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookTbButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showTbLookDialog(row,monkeyid,'查看TB检测信息');
	}
}
function showTbLookDialog(row,monkeyid,title){
	var monkeyid=document.getElementById("monkeyids").value;
	document.getElementById("tbLookDialog2").style.display="block";
	
	$('#tbLookDialog').dialog('setTitle',title);
	$('#tbLookDialog').dialog('open');	 
	
	
  	    	$('#lookTb').datagrid({
			url : sybp()+'/tbAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
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
			           {title:'TB药剂',field:'q_id',width:80,halign:'center',align:'center'},
			           {title:'TB药剂量',field:'drugs_count',width:80,halign:'center',align:'center'},
			{title:'TB24判定结果',field:'tb24',width:80,halign:'center',align:'center'},
			{title:'TB24判定兽医',field:'tb24v',width:80,halign:'center',align:'center'},
			{title:'TB48判定结果',field:'tb48',width:80,halign:'center',align:'center'},
			{title:'TB48判定兽医',field:'tb48v',width:80,halign:'center',align:'center'},
			{title:'TB72判定结果',field:'tb72',width:80,halign:'center',align:'center'},
			{title:'TB72判定兽医',field:'tb72v',width:80,halign:'center',align:'center'},
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