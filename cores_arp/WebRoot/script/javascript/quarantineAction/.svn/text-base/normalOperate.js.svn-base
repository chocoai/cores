
function lookSurfaceButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showSurfaceLookDialog(row,monkeyid,'查看体表检测信息');
	}
}

function showSurfaceLookDialog(row,monkeyid,title){
	document.getElementById("surfaceLookDialog2").style.display="block";
	
	$('#surfaceLookDialog').dialog('setTitle',title);
	$('#surfaceLookDialog').dialog('open');	 
	
	
  	    	$('#lookSurface').datagrid({
			url : sybp()+'/surfaceAction_loadListByMonkeyId.action?monkeyid='+monkeyid,
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'检疫时间',
				field:'cdate',
				width:150
			},{
				title:'检疫类型',
				field:'ptype',
				width:150,
				formatter:function(value,row,index){
					if(value=='QuarantineN'){
						return "常规检疫";
					}
				}
			},{
				title:'检测兽医',
				field:'veterinarian',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'保定人员',
				field:'protector',
				width:150
			},{
				title:'记录人员',
				field:'recorder',
				width:150
			},{
				title:'体表状况',
				field:'remark',
				width:150
			}
			]],
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

function initSurfaceVeterinarian(){
	$('#sur_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});

}

function initSurfaceProtector(){
	$('#sur_protector').combobox({
		url:sybp()+'/employeeAction_loadListEmployee.action',
		valueField:'id',
		textField:'text',
		editable:false
	});

}

function initSurfaceRecorder(){
	$('#sur_recorder').combobox({
		url:sybp()+'/employeeAction_loadListEmployee.action',
		valueField:'id',
		textField:'text',
		editable:false
	
	});

}