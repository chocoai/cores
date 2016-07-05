function addXyshButton(){
	var id=$('#normalid').val();
	showXyshAddDialog('afterAddXyshDialog','add','添加血液生化检疫信息',id);
	
}
function showXyshAddDialog(clickName,addOrEdit,title,id){
	$('#xysh_normalid').val(id);
	initXyshVeterinarian();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#xysh_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("xyshAddDialog2").style.display="block";
	
	$('#xyshAddDialog').dialog('setTitle',title);
	$('#xyshAddDialog').dialog('open'); 
	document.getElementById("xyshAdd_event").href="javascript:"+clickName+"();";
}
function afterAddXyshDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveXyshButton(){
		var monkeyid=document.getElementById("xysh_monkeyid").value;
		if( $('#xyshAddForm').form('validate') ){
			$('#saveXyshDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/xyshAction_addNormalXYSH.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#xyshAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveXyshDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#xyshAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var xyshAdd_event=document.getElementById("xyshAdd_event");
						xyshAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveXyshDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#xyshAddDialog').dialog('close'); 
							var xyshAdd_event=document.getElementById("xyshAdd_event");
							xyshAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initXyshVeterinarian(){
	$('#xcg_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookXyshButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showXyshLookDialog(row,monkeyid,'查看血常规检疫信息');
	}
}
function showXyshLookDialog(row,monkeyid,title){
	var monkeyid=document.getElementById("monkeyids").value;
	document.getElementById("xyshLookDialog2").style.display="block";
	
	$('#xyshLookDialog').dialog('setTitle',title);
	$('#xyshLookDialog').dialog('open');	 
	
	
  	    	$('#lookXysh').datagrid({
			url : sybp()+'/xyshAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
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
			           {title:'AST',field:'ast',width:80,halign:'center',align:'center'},
			           {title:'ALT',field:'alt',width:80,halign:'center',align:'center'},
						{title:'ALP',field:'alp',width:80,halign:'center',align:'center'},
						{title:'TP',field:'tp',width:80,halign:'center',align:'center'},
						{title:'ALB',field:'alb',width:80,halign:'center',align:'center'},
						{title:'GGT',field:'ggt',width:80,halign:'center',align:'center'},
				           {title:'TBIL',field:'tbil',width:80,halign:'center',align:'center'},
							{title:'BUN',field:'bun',width:80,halign:'center',align:'center'},
							{title:'CREA',field:'crea',width:80,halign:'center',align:'center'},
							{title:'GLU',field:'glu',width:80,halign:'center',align:'center'},
							{title:'TG',field:'tg',width:80,halign:'center',align:'center'},
							{title:'CHOL',field:'chol',width:80,halign:'center',align:'center'},
							{title:'LDH',field:'ldh',width:80,halign:'center',align:'center'},
							{title:'CK',field:'ck',width:80,halign:'center',align:'center'},
							{title:'NA',field:'na',width:80,halign:'center',align:'center'},
							{title:'K',field:'k',width:80,halign:'center',align:'center'},
							{title:'CI',field:'ci',width:80,halign:'center',align:'center'},
							
			{title:'检测兽医',field:'veterinarian',width:80,halign:'center',align:'center'}
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