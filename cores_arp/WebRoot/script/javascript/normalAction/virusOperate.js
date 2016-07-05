function addVirusButton(){
	var normalid=$('#normalid').val();
	showVirusAddDialog('afterAddVirusDialog','add','添加病毒检测信息',normalid);
	$.ajax({
					url:sybp()+'/virusAction_loadTable.action',
					type:'post',
					//data:$('#parasiteAddForm').serialize(),
					dataType:'json',
					contentType:"application/x-www-form-urlencoded;charset=utf-8",
					success:function(r){
					if(r ){
						 document.getElementById('vir').innerHTML=r.tables;
					}
				 }
				});
}
function showVirusAddDialog(clickName,addOrEdit,title,normalid){
	$('#virus_normalid').val(normalid);
	initVirusVeterinarian();
	initVirusProtector();
	initVirusRecorder();
	if(document.getElementById("monkeyids").value!=null&&document.getElementById("monkeyids").value!=""){
		$('#vir_monkeyid').val(document.getElementById("monkeyids").value);
	}else{
		alert("请添加动物编号");
		return;
	}
	
	document.getElementById("virusAddDialog2").style.display="block";
	
	$('#virusAddDialog').dialog('setTitle',title);
	$('#virusAddDialog').dialog('open'); 
	document.getElementById("virusAdd_event").href="javascript:"+clickName+"();";
}
function afterAddVirusDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#normalTable').datagrid('reload');
    }
function onDialogSaveVirusButton(){
		var monkeyid=document.getElementById("vir_monkeyid").value;
   		
		if( $('#virusAddForm').form('validate') ){
			$('#saveVirusDialogButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/virusAction_add.action?monkeyid='+monkeyid,
					type:'post',
					data:$('#virusAddForm').serialize(),
					dataType:'json',
					
					success:function(r){
					 $('#saveVirusDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#xueq').val('');
						$('#vir_cdate').datebox('setValue', '');
						$('#virusAddDialog').dialog('close'); 
						$('#nid').val(r.id);
						var virusAdd_event=document.getElementById("virusAdd_event");
						virusAdd_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveVirusDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#virusAddDialog').dialog('close'); 
							var virusAdd_event=document.getElementById("virusAdd_event");
							virusAdd_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			
			
		}
}

function initVirusVeterinarian(){
 	$('#vir_veterinarian').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 function initVirusProtector(){
 	$('#vir_protector').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 
 }
 
 function initVirusRecorder(){
 	$('#vir_recorder').combobox({    
 		url : sybp()+'/employeeAction_loadListEmployee.action',
 	    valueField:'id',    
 	    textField:'text',
 	    editable:false,
 	    onLoadSuccess:function(none){
 		   
 		}
 		 
 	});
 }
 function lookVirusButton(){
		var row= $('#normalTable').datagrid('getSelected');
		var monkeyid=document.getElementById("monkeyids").value;
		if(row!=null&&monkeyid!=null){
			showVirusLookDialog(row,monkeyid,'查看病毒检测信息');
		}
	}
 function showVirusLookDialog(row,monkeyid,title){
		document.getElementById("virusLookDialog2").style.display="block";
		
		$('#virusLookDialog').dialog('setTitle',title);
		$('#virusLookDialog').dialog('open');	 
		
		
	  	    	$('#lookVirus').datagrid({
				url : sybp()+'/virusAction_loadListByMonkeyIdAndCdate.action',
				title:'',
				height:tableHeight,
				width:tableWidth,
				nowarp:  false,//单元格里自动换行
				singleSelect:true,
				fitColumns:false,
			    pagination:true,//分页
				sortName:'id',
				columns :[
				          [
				           {title:'id',field:'id',width:150,hidden:true},
				           {title:'检疫时间',field:'cdate',width:150,align:'center'},
				           {title:'检疫类型',field:'ptype',width:150,
					formatter:function(value,row,index){
						if(value=='QuarantineN'){
							return "常规检疫";
						}
					}
				},
						{title:'血清号',field:'xueq',width:80,halign:'center',align:'center'},
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
	                    url:sybp()+'/virusAction_loadListItem.action?monkeyid='+(row.monkeyid)+'&cdate='+row.cdate,  
	                    fitColumns:true,  
	                    singleSelect:true,
	                    height:'auto',  
	                    columns:[[  
	                        {field:'q_id',title:'检测项目名称'},  
	                        {field:'qconfig_id',title:'检测方法',width:50},  
	                        {field:'resoult',title:'阴性/阳性',formatter:function(value, row, index){  
	                            if (value==0) {  
	                                return '阴性';  	                                                                                             
	                            }if(value==1){ 
	                            	return '阳性';
	                            }  
	                        }},  
	                        {field:'drugs_name',title:'药品名',width:100},  
	                        {field:'drugs_count',title:'用药量',width:50,align:'center'}  
	                    ]],  
	                    onResize:function(){  
	                        $('#lookVirus').datagrid('fixDetailRowHeight',index);  
	                    },  
	                    onLoadSuccess:function(){  
	                        setTimeout(function(){  
	                            $('#lookVirus').datagrid('fixDetailRowHeight',index);  
	                        },0);  
	                    }  
	                });  
	                $('#lookVirus').datagrid('fixDetailRowHeight',index);  
	            }
		   	});
	  	        
	}