function addBacteriaButton(){
	var normalid=$('#normalid').val();
	showBacteriaAddDialog('afterAddBacteriaDialog','add','添加细菌检测信息',normalid);
	$.ajax({
					url:sybp()+'/bacteriaAction_loadTable.action',
					type:'post',
					data:$('#bacteriaAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					if(r ){
						 document.getElementById('bac').innerHTML=r.tables;
					}
				 }
				});
}
function showBacteriaAddDialog(clickName,addOrEdit,title,normalid){
	$('#bacteria_normalid').val(normalid);
	initBacteriaVeterinarian();
	initBacteriaProtector();
	initBacteriaRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#bac_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("bacteriaAddDialog2").style.display="block";
	
	$('#bacteriaAddDialog').dialog('setTitle',title);
	$('#bacteriaAddDialog').dialog('open'); 
	document.getElementById("bacteriaAdd_event").href="javascript:"+clickName+"();";
}
function afterAddBacteriaDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveBacteriaButton(){
		var monkeyid=document.getElementById("bac_monkeyid").value;
		if( $('#bacteriaAddForm').form('validate') ){
			$('#saveBacteriaDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/bacteriaAction_addNormalBacteria.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#bacteriaAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveBacteriaDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#bacteriaAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var bacteriaAdd_event=document.getElementById("bacteriaAdd_event");
						bacteriaAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveBacteriaDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#bacteriaAddDialog').dialog('close'); 
							var bacteriaAdd_event=document.getElementById("bacteriaAdd_event");
							bacteriaAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initBacteriaVeterinarian(){
	$('#bac_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initBacteriaProtector(){
	$('#bac_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initBacteriaRecorder(){
	$('#bac_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookBacteriaButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showBacteriaLookDialog(row,monkeyid,'查看细菌检测信息');
	}
}
function showBacteriaLookDialog(row,monkeyid,title){
	document.getElementById("bacteriaLookDialog2").style.display="block";
	
	$('#bacteriaLookDialog').dialog('setTitle',title);
	$('#bacteriaLookDialog').dialog('open');	 
	
	
  	    	$('#lookBacteria').datagrid({
			url : sybp()+'/bacteriaAction_loadListByMonkeyIdAndCdate.action',
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
			{title:'检测兽医',field:'veterinarian',width:80,halign:'center',align:'center'},
			{title:'保定人员',field:'protector',width:80,halign:'center',align:'center'},
			{title:'记录人员',field:'recorder',width:80,halign:'center',align:'center'},
			{title:'检疫时间',field:'cdate',width:80,halign:'center',align:'center'},
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
		    collapsible : true,//显示可折叠按钮 
		    view: detailview,
			detailFormatter: function(index, row){
				return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
			},
			onExpandRow:function(index,row){//注意3  
                $('#ddv-'+index).datagrid({  
                    url:sybp()+'/bacteriaAction_loadListItem.action?monkeyid='+(row.monkeyid)+'&cdate='+row.cdate,  
                    fitColumns:true,  
                    singleSelect:true,
                    height:'auto',  
                    columns:[[  
                        {field:'q_name',title:'检测项目名称'},  
                        {field:'qconfig_name',title:'检测方法',width:50},  
                        {field:'resoult',title:'阴性/阳性',formatter:function(value, row, index){  
                            if (value==0) {  
                                return '阴性';  	                                                                                             
                            }if(value==1){ 
                            	return '阳性';
                            }  
                        }},
                        {field:'drugs_name',title:'药品名',width:50},
                        {field:'drugs_count',title:'用药量',width:50},
                    ]],  
                    onResize:function(){  
                        $('#lookBacteria').datagrid('fixDetailRowHeight',index);  
                    },  
                    onLoadSuccess:function(){  
                        setTimeout(function(){  
                            $('#lookBacteria').datagrid('fixDetailRowHeight',index);  
                        },0);  
                    }  
                });  
                $('#lookBacteria').datagrid('fixDetailRowHeight',index);  
            }
	   	});
  	        
}