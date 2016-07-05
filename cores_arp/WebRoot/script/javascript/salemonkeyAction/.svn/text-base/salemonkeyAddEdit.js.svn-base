function showSalemonkeyAddEditDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("saleMonkeyDialog2").style.display="block";
	 $('#saleMonkey').datagrid({
			url : sybp()+'/salemonkeyAction_loadSaleListBySale.action',
			title:'',
			height:190,
			width:798,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:false,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'订单名',
				field:'title',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'订单状态',
				field:'status',
				width:60,
				halign:'center',
				align:'left'
			},{
				title:'选猴条件',
				field:'tiaojian',
				width:200,
				halign:'center',
				align:'left'
				
			},{
				title:'订单数量',
				field:'salecount',
				width:55,
				halign:'center',
				align:'center'
			},{
				title:'出场日期',
				field:'outdate',
				width:75,
				halign:'center',
				align:'center'
		    },{
				title:'选择列表',
				field:'monkeylist',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'订单类型',
				field:'saletypeName',
				width:60,
				halign:'center',
				align:'center'
		    },{
				title:'订单运送地',
				field:'saleaddress',
				width:85,
				halign:'center',
				align:'left'
		    },{
				title:'负责人',
				field:'bossName',
				width:50,
				halign:'center',
				align:'left'
			}
			]],
			onSelect:function(rowIndex, rowData){
			   $('#saleAddButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
	    	   $('#saleAddButton').linkbutton('disable');
			},
			toolbar:'#dialogsaleMonkeyToolbar'
			
	   	});		
  
	 $('#saleMonkeyDialog').dialog('setTitle',title);
	 $('#saleMonkeyDialog').dialog('open'); 
	 document.getElementById("saleMonkeyDialog_event").href="javascript:"+clickName+"();";
  
}









/**添加出场记录（保存）*/
function onsaleAddButton(){
	    var row= $('#saleMonkey').datagrid('getSelected');
		if( row!=null){
			$('#saleAddButton').linkbutton('disable');
				$.ajax({
					url:sybp()+'/salemonkeyAction_addSaleMonkey.action',
					type:'post',
					data:{sale_id:row.id},
					dataType:'json',
					success:function(r){
					 $('#saleAddButton').linkbutton('enable');
					if(r && r.success){
						$('#saleMonkeyDialog').dialog('close'); 
						var saleMonkeyDialog_event=document.getElementById("saleMonkeyDialog_event");
						saleMonkeyDialog_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saleAddButton').linkbutton('enable');
						if(r && r.success){
							$('#saleMonkeyDialog').dialog('close'); 
							var saleMonkeyDialog_event=document.getElementById("saleMonkeyDialog_event");
							saleMonkeyDialog_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
}


