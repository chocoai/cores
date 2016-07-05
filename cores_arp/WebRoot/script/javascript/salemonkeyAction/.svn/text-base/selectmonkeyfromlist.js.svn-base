function showSelectmonkeyDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("selectmonkeyDialog2").style.display="block";
	 var row= $('#selectmonkeyTable').datagrid('getSelected');
	 $('#saleid').val(row.id);
	 $('#selectmonkeyDialog').dialog('setTitle',title);
	 $('#selectmonkeyDialog').dialog('open'); 
	 document.getElementById("selectmonkeyDialog_event").href="javascript:"+clickName+"();";
  
}

function onSearchmonkeyButton(){
	var monkeyid=$('#selectmonkeyid').val();
	$('#selectMonkey').datagrid({
		url : sybp()+'/individualAction_findMonkeyByMonkeyid.action?monkeyid='+monkeyid,
		title:'',
		height:100,
		width:480,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			halign:'center',
			align:'center',
			hidden:true
		},
		{
			title:'动物编号',
			field:'monkeyid',
			width:75,
			halign:'center',
			align:'center'
		}
		,
		{
			title:'性别',
			field:'sex',
			width:35,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
			    if(row.id==0){
			    	return "";
			    }else{
				   if ( value == 0 ){
					return "公";
				   }else if(value == 1){
					return "母";
				   }
			    }
			}
		},
		{
			title:'种类',
			field:'animaltypeName',
			width:50,
			halign:'center',
			align:'center'
		}
		,
		{
			title:'年龄阶段',
			field:'agetype',
			width:55,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == 1 ){
					return "仔猴";
				}else if(value == 2){
					return "育成猴";
				}else if(value == 3){
				   return "成年猴";
				}else{
				    return "";
				}
			}
		}
		,
		{
			title:'饲养员',
			field:'keeperp',
			width:55,
			halign:'left',
			align:'left'
		}
		,
		{
			title:'目前体重(kg)',
			field:'currentweight',
			width:80,
			halign:'left',
			align:'left',
			formatter: function(value,row,index){
			  if ( value == 0 ){
			      return "";
			  }else{
				  return value;
			  }
		    }
		},
		{
			title:'区域',
			field:'quyu',
			width:60,
			halign:'left',
			align:'left'
		},
		{
			title:'房间',
			field:'roomName',
			width:70,
			halign:'left',
			align:'left'
		},
		{
			title:'称重日期',
			field:'currentdate',
			width:80,
			halign:'center',
			align:'center'
		},
		{
			title:'状态',
			field:'status',
			width:55,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if ( value == 1 ){
					return "在场";
				}else if(value == 2){
					return "待销售";
				}else {
				    return "";
				}
			}
		}
		,
		{
			title:'备注',
			field:'remark',
			width:100,
			halign:'left',
			align:'left'
		}
			
		]],
		onSelect:function(rowIndex, rowData){
		   var rows=$("#selectMonkey").datagrid("getRows");
		   if(rows[0].id!=0){
			   $('#selectButton').linkbutton('enable');  
		   }else{
			   $('#selectButton').linkbutton('disable');  
		   }
		},
		onLoadSuccess:function(data){
		   var rows=$("#selectMonkey").datagrid("getRows");
		   if(rows[0].id!=0){
			   $('#selectedButton').linkbutton('enable');  
		   }else{
			   $('#selectedButton').linkbutton('disable');  
		   }
		   $('#monkeyinfo').css('display','');
		   $('#selectToobar').css('display','');
		},
		toolbar:'#selectToobar'
   	});
}







//选中按钮事件
function onSelectedButton(){
	var rows=$("#selectMonkey").datagrid("getRows");
	var monkeyid=rows[0].monkeyid;
	var textValue=$('#monkeylist').val();
	textValue=textValue+monkeyid+",";
	$('#monkeylist').val(textValue);
}
/**确定（保存）*/
function onsaleSelectButton(){
		if( $('#selectmonkeyDialogForm').form('validate')){
			var list=$('#monkeylist').val();
			if(list==''){
				parent.parent.showMessager(3,'请先选择猴子!',true,5000);
				return ;
			}else{
				$('#saleSelectButton').linkbutton('disable');
			}
				$.ajax({
					url:sybp()+'/saleAction_addSelectMonkey.action',
					type:'post',
					data:$('#selectmonkeyDialogForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#saleSelectButton').linkbutton('enable');
					if(r && r.success){
						$('#selectmonkeyDialog').dialog('close'); 
						$('#did').val(r.id);
						var selectmonkeyDialog_event=document.getElementById("selectmonkeyDialog_event");
						selectmonkeyDialog_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saleSelectButton').linkbutton('enable');
						if(r && r.success){
							$('#disinfectantAddEditDialog').dialog('close'); 
							var disinfectantAddEdit_event=document.getElementById("disinfectantAddEdit_event");
							disinfectantAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
}


