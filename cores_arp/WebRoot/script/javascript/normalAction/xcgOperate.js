function addXcgButton(){
	var normalid=$('#normalid').val();
	showXcgAddDialog('afterAddXcgDialog','add','添加血常规检疫信息',normalid);
	
}
function showXcgAddDialog(clickName,addOrEdit,title,normalid){
	$('#xcg_normalid').val(normalid);
	initXcgCheckArea();
	initXcgVeterinarian();
	initXcgProtector();
	initXcgRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#xcg_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("xcgAddDialog2").style.display="block";
	
	$('#xcgAddDialog').dialog('setTitle',title);
	$('#xcgAddDialog').dialog('open'); 
	document.getElementById("xcgAdd_event").href="javascript:"+clickName+"();";
}
function afterAddXcgDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveXcgButton(){
		var monkeyid=document.getElementById("xcg_monkeyid").value;
		if( $('#xcgAddForm').form('validate') ){
			$('#saveXcgDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/xcgAction_addNormalXCG.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#xcgAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveXcgDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#xcgAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var xcgAdd_event=document.getElementById("xcgAdd_event");
						xcgAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveXcgDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#xcgAddDialog').dialog('close'); 
							var xcgAdd_event=document.getElementById("xcgAdd_event");
							xcgAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}
function initXcgCheckArea(){
	$('#checkarea').combobox({
		url:sybp()+'/xAction_getCheckArea.action',
		valueField:'id',
		textField:'text',
		editable:false
	});
}
function initXcgVeterinarian(){
	$('#xcg_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initXcgProtector(){
	$('#xcg_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initXcgRecorder(){
	$('#xcg_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookXcgButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showXcgLookDialog(row,monkeyid,'查看血常规检疫信息');
	}
}
function showXcgLookDialog(row,monkeyid,title){
	var monkeyid=document.getElementById("monkeyids").value;
	document.getElementById("xcgLookDialog2").style.display="block";
	
	$('#xcgLookDialog').dialog('setTitle',title);
	$('#xcgLookDialog').dialog('open');	 
	
	
  	    	$('#lookXcg').datagrid({
			url : sybp()+'/xcgAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
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
			           {title:'WBC',field:'wbc',width:80,halign:'center',align:'center'},
			           {title:'RBC',field:'rbc',width:80,halign:'center',align:'center'},
						{title:'HGB',field:'hgb',width:80,halign:'center',align:'center'},
						{title:'HCT',field:'hct',width:80,halign:'center',align:'center'},
						{title:'PLT',field:'plt',width:80,halign:'center',align:'center'},
						{title:'MCV',field:'mcv',width:80,halign:'center',align:'center'},
				           {title:'MCH',field:'mch',width:80,halign:'center',align:'center'},
							{title:'MCHC',field:'mchc',width:80,halign:'center',align:'center'},
							{title:'LYM',field:'lym',width:80,halign:'center',align:'center'},
							{title:'MID',field:'mid',width:80,halign:'center',align:'center'},
							{title:'GRA',field:'gra',width:80,halign:'center',align:'center'},
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