function showAddressOperateDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("addressOperateDialog2").style.display="block";
	 $('#addressOperateDialog').dialog('setTitle',title);
	 $('#addressOperateDialog').dialog('open'); 
	$('#addressTable').datagrid({
		url : sybp()+'/salemonkeyAction_loadListAddress.action',
		title:'',
		height:330,
		width:475,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    pagination:false,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:100,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'客户名称',
			field:'name',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'手机',
			field:'telephone',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'传真',
			field:'fax',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'地址',
			field:'address',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'备注',
			field:'remark',
			width:200,
			halign:'center',
			align:'left'
		}
		]],
		onSelect:function(rowIndex, rowData){
	    $('#addAddressButton').linkbutton('enable');
	    $('#editAddressButton').linkbutton('enable');
	 	$('#delAddressButton').linkbutton('enable');
	   
         $('#backAddressButton').linkbutton('enable');
	},
	onLoadSuccess:function(data){
		$('#addAddressButton').linkbutton('enable');
	    $('#editAddressButton').linkbutton('disable');
	    $('#delAddressButton').linkbutton('disable');
	    $('#backAddressButton').linkbutton('enable');
	    var selectid = $('#aid').val();
        if(selectid != ""){
          for(var i = 0 ; i< data.rows.length;i++){
             if(selectid == data.rows[i].id){
            	$('#addressTable').datagrid('selectRow',i);
          }
       }
     }
	},
	onDblClickRow:function(rowIndex, rowData){//鼠标双击触发.
		showAddressAddEditDialog('afterEditAddressDialog','view','查看运送地信息');
	}
	});
  
}
function onaddAddressButton(){
	showAddressAddEditDialog('afterAddAddressDialog','add','添加运送地');
}
function afterAddAddressDialog(){
	parent.parent.showMessager(1,'添加成功',true,5000);
    $('#addressTable').datagrid('reload');
}
function oneditAddressButton(){
	showAddressAddEditDialog('afterEditAddressDialog','edit','编辑运送地');
}
function afterEditAddressDialog(){
	parent.parent.showMessager(1,'编辑成功',true,5000);
    $('#addressTable').datagrid('reload');
}
function ondelAddressButton(){
	 var row= $('#addressTable').datagrid('getSelected');
 	 if(null != row ){
      	$.messager.confirm('确认对话框', '确认删除此运送地？', function(r){
				if (r){
					delAddress(row.id);
					
				}
			});
     }else{
       $.messager.alert('提示','请选择要删除的运送地!');
     }
}
function delAddress(id){
   	$.ajax({
		url:sybp()+'/salemonkeyAction_delAddress.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.parent.showMessager(1,'运送地删除成功',true,5000);
				 $('#addressTable').datagrid('reload');
			}else{
				parent.parent.showMessager(2,'运送地删除失败',true,5000);
			}
		}
	});
} 
function showAddressAddEditDialog(clickName,addOrEdit,title){
	document.getElementById("addressAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		var preCode=CurentTime();
		//$('#disinfectantCode').val(preCode);
		$('#name').val('');
		$('#fax').val('');
		$('#telephone').val('');
		$('#address').val('');
		$('#remark').val('');
		$('#saveAddressInfoDialogButton').linkbutton('enable');
	}else if(addOrEdit == "edit"||addOrEdit == "view"){
		var row= $('#addressTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/salemonkeyAction_toEditAddress.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#customerId').val(r.id);
  	    				$('#name').val(r.name);
  	    				$('#fax').val(r.fax);
  	    				$('#telephone').val(r.telephone);
  	    				$('#address').val(r.address);
  	    				$('#remark').val(r.remark);
  	    			}
  	    		}
  	    	});
		if(addOrEdit == "view"){
			$('#saveAddressInfoDialogButton').linkbutton('disable');
		}else{
			$('#saveAddressInfoDialogButton').linkbutton('enable');
		}
		
	}
	$('#addOrEditDis').val(addOrEdit);
	$('#addressAddEditDialog').dialog('setTitle',title);
	$('#addressAddEditDialog').dialog('open'); 
	document.getElementById("addressAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onAddressDialogSaveButton(){
		if( $('#addressAddEditForm').form('validate')){
			$('#saveAddressInfoDialogButton').linkbutton('disable');
			
			if($('#addOrEditDis').val() == 'add'){
				$.ajax({
					url:sybp()+'/salemonkeyAction_addAddress.action',
					type:'post',
					data:$('#addressAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#saveAddressInfoDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#addressAddEditDialog').dialog('close'); 
						$('#aid').val(r.id);
						var addressAddEdit_event=document.getElementById("addressAddEdit_event");
						addressAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveAddressInfoDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#addressAddEditDialog').dialog('close'); 
							var addressAddEdit_event=document.getElementById("addressAddEdit_event");
							addressAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/salemonkeyAction_editSaveAddress.action',
					type:'post',
					data:$('#addressAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveAddressInfoDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#addressAddEditDialog').dialog('close'); 
						$('#aid').val(r.id);
						var addressAddEdit_event=document.getElementById("addressAddEdit_event");
						addressAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveAddressInfoDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#addressAddEditDialog').dialog('close'); 
							var addressAddEdit_event=document.getElementById("addressAddEdit_event");
							addressAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}
			
		}
}

//根据日期得到消毒液编号的前缀
function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var clock ="消" + year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day;
    return(clock); 
} 
//加载消毒液配方表
function  initDisinfectantmaterialDatagrid(id){
	 var datagrid; //定义全局变量datagrid
     var editRow = undefined; //定义全局变量：当前编辑的行
     datagrid = $("#disfectantmaterialTable").datagrid({
         url: sybp()+'/disinfectantMaterialAction_loadListByCode.action?disinfectant_id='+id, //请求的数据源
         iconCls: 'icon-save', //图标
         width:510,
         fitColumn: false, //列自适应宽度
         striped: true, //行背景交换
         nowap: true, //列内容多时自动折至第二行
         border: false,
         singleSelect:true,
         idField: 'ID', //主键
         columns: [[//显示的列
         {
        	 field: 'ID', 
        	 title: 'id',
        	 width: 100, 
        	 sortable: true,
        	 checkbox:true
          },{
             field: 'materialname',
             title: '原料名', 
             width: 100, 
             sortable: true,
             editor: { type: 'validatebox', options: { required: true} }
          },{ 
        	  field: 'content', 
        	  title: '含量', 
        	  width: 60,
              editor: { type: 'validatebox', options: { required: true} }
           },{ 
        	   field: 'productionbatch', 
        	   title: '生产批号', width: 100,
               editor: { type: 'validatebox', options: { } }
           },{
        	   field: 'validdate', 
        	   title: '有效日期', width: 100,
               editor: { type: 'datebox', options: {editable:false } }
           },{
        	   field: 'supplier', 
        	   title: '供应商', width: 110,
               editor: { type: 'validatebox', options: { } }
           }
            ]],
         queryParams: { action: 'query' }, //查询参数
         
         onAfterEdit: function (rowIndex, rowData, changes) {
             //endEdit该方法触发此事件
             console.info(rowData);
         },
         
         onLoadSuccess:function(data){
        	 $('#addDmButton').linkbutton('enable');
             $('#delDmButton').linkbutton('disable');
        	 if (data.rows.length==0) {
            	 $("#disfectantmaterialTable").datagrid('insertRow', {
                     index: 0, // index start with 0
                     row: {
                     }
                 });
                 //将新插入的那一行开户编辑状态
            	 $("#disfectantmaterialTable").datagrid('beginEdit', 0);
                 //给当前编辑的行赋值
                 editRow = 0;
        	 }else{
        		 var rows=$("#disfectantmaterialTable").datagrid("getRows");
     			 for(var i=0;i<rows.length;i++){
     				var rowIndex = $('#disfectantmaterialTable').datagrid('getRowIndex', rows[i]);
     				$('#disfectantmaterialTable').datagrid('beginEdit', rowIndex);
     			} 
        	 }
     	},
        onSelect:function(rowIndex, rowData){
	     $('#addDmButton').linkbutton('enable');
         $('#delDmButton').linkbutton('enable');
	},
     	toolbar:'#dmToolbar'
     });
}

function onaddDmButton(){
	$("#disfectantmaterialTable").datagrid('insertRow', {
        index: 0, // index start with 0
        row: {

        }
    });
    //将新插入的那一行开户编辑状态
	 $("#disfectantmaterialTable").datagrid('beginEdit', 0);
    //给当前编辑的行赋值

}
function ondelDmButton(){
	var rows = $("#disfectantmaterialTable").datagrid("getSelections");
	for(var i=0;i<rows.length;i++){
		var rowIndex=$('#disfectantmaterialTable').datagrid('getRowIndex', rows[i]);
		var rows1=$("#disfectantmaterialTable").datagrid("getRows");
		if(rows1.length>1){
			$("#disfectantmaterialTable").datagrid('deleteRow', rowIndex);
		}else{
			alert("消毒液配方必须要有数据");
		}
	}
}
function getDatagridDate(){
	
}
