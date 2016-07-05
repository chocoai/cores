function showApprovalOperateDialog(title){
	/* 显示职位主Dialog */
	 document.getElementById("approvalOperateDialog2").style.display="block";
	 $('#approvalOperateDialog').dialog('setTitle',title);
	 $('#approvalOperateDialog').dialog('open'); 
	$('#approvalTable').datagrid({
		url : sybp()+'/salemonkeyAction_loadListApproval.action',
		title:'',
		height:330,
		width:775,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:900,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'许可证标题',
			field:'title',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'许可批号',
			field:'phao',
			width:220,
			halign:'center',
			align:'left'
		},{
			title:'申请主题',
			field:'head',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'申请内容',
			field:'content',
			width:220,
			halign:'center',
			align:'left'
		},{
			title:'批准日期',
			field:'approvaldate',
			width:100,
			halign:'center',
			align:'left'
		}
		]],
		onSelect:function(rowIndex, rowData){
	    $('#addApprovalButton').linkbutton('enable');
	    $('#editApprovalButton').linkbutton('enable');
	 	$('#delApprovalButton').linkbutton('enable');
	   
         $('#backApprovalButton').linkbutton('enable');
	},
	onLoadSuccess:function(data){
		$('#addApprovalButton').linkbutton('enable');
	    $('#editApprovalButton').linkbutton('disable');
	    $('#delApprovalButton').linkbutton('disable');
	    $('#backApprovalButton').linkbutton('enable');
	    var selectid = $('#apid').val();
        if(selectid != ""){
          for(var i = 0 ; i< data.rows.length;i++){
             if(selectid == data.rows[i].id){
            	$('#approvalTable').datagrid('selectRow',i);
          }
       }
     }
	},
	onDblClickRow:function(rowIndex, rowData){//鼠标双击触发.
		showApprovalAddEditDialog('afterEditAddressDialog','view','查看运送地信息');
	},
	pageSize : 12,//默认选择的分页是每页5行数据         
	pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
    striped : true,//设置为true将交替显示行背景。      
    collapsible : true//显示可折叠按钮 
	});
  
}
function onAddApprovalButton(){
	showApprovalAddEditDialog('afterAddApprovalDialog','add','添加许可证');
}
function afterAddApprovalDialog(){
	parent.parent.showMessager(1,'添加成功',true,5000);
    $('#approvalTable').datagrid('reload');
}
function onEditApprovalButton(){
	showApprovalAddEditDialog('afterEditApprovalDialog','edit','编辑许可证');
}
function afterEditApprovalDialog(){
	parent.parent.showMessager(1,'编辑成功',true,5000);
    $('#approvalTable').datagrid('reload');
}
function onDelApprovalButton(){
	 var row= $('#approvalTable').datagrid('getSelected');
 	 if(null != row ){
      	$.messager.confirm('确认对话框', '确认删除此许可证？', function(r){
				if (r){
					delApproval(row.id);
					
				}
			});
     }else{
       $.messager.alert('提示','请选择要删除的许可证!');
     }
}
function delApproval(id){
   	$.ajax({
		url:sybp()+'/salemonkeyAction_delApproval.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.parent.showMessager(1,'许可证删除成功',true,5000);
				 $('#approvalTable').datagrid('reload');
			}else{
				parent.parent.showMessager(2,'许可证删除失败',true,5000);
			}
		}
	});
} 
function showApprovalAddEditDialog(clickName,addOrEdit,title){
	document.getElementById("approvalAddEditDialog2").style.display="block";
	if(addOrEdit == "add"){
		var preCode=CurentTime();
		//$('#disinfectantCode').val(preCode);
		$('#title').val('');
		$('#phao').val('');
		$('#head').val('');
		$('#content').val('');
		$('#approvaldate').val('');
		$('#saveApprovalInfoDialogButton').linkbutton('enable');
	}else if(addOrEdit == "edit"||addOrEdit == "view"){
		var row= $('#approvalTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/salemonkeyAction_toEditApproval.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#approvalId').val(r.id);
  	    				$('#approvalTitle').val(r.title);
  	    				$('#phao').val(r.phao);
  	    				$('#head').val(r.head);
  	    				$('#content').val(r.content);
  	    				$('#approvaldate').val(r.approvaldate);
  	    			}
  	    		}
  	    	});
		if(addOrEdit == "view"){
			$('#saveApprovalInfoDialogButton').linkbutton('disable');
		}else{
			$('#saveApprovalInfoDialogButton').linkbutton('enable');
		}
		
	}
	$('#addOrEditDis').val(addOrEdit);
	$('#approvalAddEditDialog').dialog('setTitle',title);
	$('#approvalAddEditDialog').dialog('open'); 
	document.getElementById("approvalAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onApprovalDialogSaveButton(){
		if( $('#approvalAddEditForm').form('validate')){
			$('#saveApprovalInfoDialogButton').linkbutton('disable');
			
			if($('#addOrEditDis').val() == 'add'){
				$.ajax({
					url:sybp()+'/salemonkeyAction_addApproval.action',
					type:'post',
					data:$('#approvalAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#saveApprovalInfoDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#approvalAddEditDialog').dialog('close'); 
						$('#apid').val(r.id);
						var approvalAddEdit_event=document.getElementById("approvalAddEdit_event");
						approvalAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveApprovalInfoDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#approvalAddEditDialog').dialog('close'); 
							var approvalAddEdit_event=document.getElementById("approvalAddEdit_event");
							approvalAddEdit_event.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				 }
				});
			}else{
				$.ajax({
					url:sybp()+'/salemonkeyAction_editSaveApproval.action',
					type:'post',
					data:$('#approvalAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveApprovalInfoDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#approvalAddEditDialog').dialog('close'); 
						$('#apid').val(r.id);
						var approvalAddEdit_event=document.getElementById("approvalAddEdit_event");
						approvalAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveApprovalInfoDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#approvalAddEditDialog').dialog('close'); 
							var approvalAddEdit_event=document.getElementById("approvalAddEdit_event");
							approvalAddEdit_event.click();
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
