function addParasiteButton(){
	var normalid=$('#normalid').val();
	showParasiteAddDialog('afterAddParasiteDialog','add','添加寄生虫检测信息',normalid);
	$.ajax({
					url:sybp()+'/parasiteAction_loadTable.action',
					type:'post',
					data:$('#parasiteAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					if(r ){
						 document.getElementById('par').innerHTML=r.tables;
					}
				 }
				});
}
function showParasiteAddDialog(clickName,addOrEdit,title,normalid){

	initParasiteVeterinarian();
	initParasiteProtector();
	initParasiteRecorder();
	$('#parasite_normalid').val(normalid);
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#par_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("parasiteAddDialog2").style.display="block";
	
	$('#parasiteAddDialog').dialog('setTitle',title);
	$('#parasiteAddDialog').dialog('open'); 
	document.getElementById("parasiteAdd_event").href="javascript:"+clickName+"();";
}
function afterAddParasiteDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#operateTable').datagrid('reload');
    }
function onDialogSaveParasiteButton(){
		var monkeyid=document.getElementById("par_monkeyid").value;
		if( $('#parasiteAddForm').form('validate') ){
			$('#saveParasiteDialogButton').linkbutton('disable');

				$.ajax({
					url:sybp()+'/parasiteAction_add.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#parasiteAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					 $('#saveParasiteDialogButton').linkbutton('enable');
					if(r && r.success){	
						$('#parasiteAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var parasiteAdd_event=document.getElementById("parasiteAdd_event");
						parasiteAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveParasiteDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#parasiteAddDialog').dialog('close'); 
							var parasiteAdd_event=document.getElementById("parasiteAdd_event");
							parasiteAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initParasiteVeterinarian(){
	$('#par_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initParasiteProtector(){
	$('#par_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}
function initParasiteRecorder(){
	$('#par_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
	
}

function lookParasiteButton(){
	var row= $('#normalTable').datagrid('getSelected');
	var monkeyid=document.getElementById("monkeyids").value;
	if(row!=null&&monkeyid!=null){
		showParasiteLookDialog(row,monkeyid,'查看寄生虫检测信息');
	}
}
function showParasiteLookDialog(row,monkeyid,title){
	document.getElementById("parasiteLookDialog2").style.display="block";
	
	$('#parasiteLookDialog').dialog('setTitle',title);
	$('#parasiteLookDialog').dialog('open');	 
	
	
  	    	$('#lookParasite').datagrid({
			url : sybp()+'/parasiteAction_loadListByMonkeyIdAndCdate.action',
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
			           
			           {title:'采样日期',field:'getybdate',width:80,
			        	   formatter:function(value,row,index){
			        		   if(value=='QuarantineN'){
			        			   return "常规检疫";
			        		   }
			        	   }
			},
			{title:'检疫时间',field:'cdate',width:80,halign:'center',align:'center'},
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
		    collapsible : true,//显示可折叠按钮 
		    view: detailview,
			detailFormatter: function(index, row){
				return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
			},
			onExpandRow:function(index,row){//注意3  
                $('#ddv-'+index).datagrid({  
                    url:sybp()+'/parasiteAction_loadListItem.action?monkeyid='+(row.monkeyid)+'&cdate='+row.cdate,  
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
                        }}   
                    ]],  
                    onResize:function(){  
                        $('#lookParasite').datagrid('fixDetailRowHeight',index);  
                    },  
                    onLoadSuccess:function(){  
                        setTimeout(function(){  
                            $('#lookParasite').datagrid('fixDetailRowHeight',index);  
                        },0);  
                    }  
                });  
                $('#lookParasite').datagrid('fixDetailRowHeight',index);  
            }
	   	});
  	        
}