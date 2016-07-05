function showDisinfectantOperateDialog(clickName,addOrEdit,title){
	/* 显示职位主Dialog */
	 document.getElementById("disfectantOperateDialog2").style.display="block";
	 $('#disfectantOperateDialog').dialog('setTitle',title);
	 $('#disfectantOperateDialog').dialog('open'); 
	$('#disfectantTable').datagrid({
		url : sybp()+'/disinfectantAction_loadList.action',
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
			title:'消毒液编码',
			field:'disinfectantCode',
			width:120,
			halign:'center',
			align:'left'
		},{
			title:'有效期',
			field:'validdate',
			width:120,
			halign:'center',
			align:'center'
		},{
			title:'配制者',
			field:'creator',
			width:100,
			halign:'center',
			align:'left'
		},{
			title:'配制日期',
			field:'createdDate',
			width:120,
			halign:'center',
			align:'center'
		},{
			title:'是否使用',
			field:'isUsed',
			width:200,
			halign:'center',
			align:'left',
			hidden:true
		}
//		,{
//			title:'修改者',
//			field:'roles',
//			width:60,
//			halign:'center',
//			align:'left',
//			hidden:true
//		},{
//			title:'修改时间',
//			field:'roles',
//			width:60,
//			halign:'center',
//			align:'left',
//			hidden:true
//		}
		]],
		onSelect:function(rowIndex, rowData){
	    $('#addDisfectantButton').linkbutton('enable');
	    if(rowData.isUsed==0){
	    	$('#editDisfectantButton').linkbutton('enable');
	 	    $('#delDisfectantButton').linkbutton('enable');
	    }else{
	    	$('#editDisfectantButton').linkbutton('disable');
	 	    $('#delDisfectantButton').linkbutton('disable');
	    }
	   
         $('#backDisfectantButton').linkbutton('enable');
	},
	onLoadSuccess:function(data){
		$('#addDisfectantButton').linkbutton('enable');
	    $('#editDisfectantButton').linkbutton('disable');
	    $('#delDisfectantButton').linkbutton('disable');
	    $('#backDisfectantButton').linkbutton('enable');
	    var selectid = $('#did').val();
        if(selectid != ""){
          for(var i = 0 ; i< data.rows.length;i++){
             if(selectid == data.rows[i].id){
            	$('#disfectantTable').datagrid('selectRow',i);
          }
       }
     }
	},
	onDblClickRow:function(rowIndex, rowData){
		showDisinfectantAddEditDialog('afterEditDisinfectantDialog','view','查看消毒液');
	}
	});
  
}
//添加消毒液按钮事件
function onaddDisfectantButton(){
	showDisinfectantAddEditDialog('afterAddDisinfectantDialog','add','添加消毒液');
}
//添加消毒液后事件
function afterAddDisinfectantDialog(){
	parent.parent.showMessager(1,'添加成功',true,5000);
    $('#disfectantTable').datagrid('reload');
}
//编辑消毒液按钮事件
function oneditDisfectantButton(){
	showDisinfectantAddEditDialog('afterEditDisinfectantDialog','edit','编辑消毒液');
}
//编辑消毒液后事件
function afterEditDisinfectantDialog(){
	parent.parent.showMessager(1,'编辑成功',true,5000);
    $('#disfectantTable').datagrid('reload');
}
//删除消毒液按钮事件
function ondelDisfectantButton(){
	 var row= $('#disfectantTable').datagrid('getSelected');
 	 if(null != row ){
      	$.messager.confirm('确认对话框', '确认删除此消毒液？', function(r){
				if (r){
					delDisinfectant(row.id);
					
				}
			});
     }else{
       $.messager.alert('提示','请选择要删除的消毒液!');
     }
}
//根据Id删除相应消毒液(数据库不保存)
function delDisinfectant(id){
   	$.ajax({
		url:sybp()+'/disinfectantAction_delDisinfectant.action',
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.parent.showMessager(1,'消毒液删除成功',true,5000);
				 $('#disfectantTable').datagrid('reload');
			}else{
				parent.parent.showMessager(2,'消毒液删除失败',true,5000);
			}
		}
	});
} 
//打开消毒液添加编辑Dialog
function showDisinfectantAddEditDialog(clickName,addOrEdit,title){
	document.getElementById("disinfectantAddEditDialog2").style.display="block";
	initCreatorCombobox();
	if(addOrEdit == "add"){
		initDisinfectantmaterialDatagrid(0);
		var preCode=CurentTime();
		$('#disinfectantCode').val(preCode);
		$('#validdate').datebox('setValue', '');
		$('#createdBy').combobox('select','');
		$('#createdDate').datebox('setValue', '');
		$('#saveDisDialogButton').linkbutton('enable');
	}else if(addOrEdit == "edit"||addOrEdit == "view"){
		var row= $('#disfectantTable').datagrid('getSelected');
		$.ajax({
    		url:sybp()+'/disinfectantAction_toEdit.action',
    		type:'post',
    		cache:false,
    		data:{
			 id:row.id
    		},
    		dataType:'json',
  	    		success:function(r){
  	    			if(r){
  	    				$('#disinfectantid').val(r.id);
  	    				$('#olddisinfectantCode').val(r.disinfectantCode);
  	    				$('#disinfectantCode').val(r.disinfectantCode);
  	    				$('#validdate').datebox('setValue', r.validdate);
  	    				$('#createdBy').combobox('select',r.createdBy);
  	    				$('#createdDate').datebox('setValue', r.createdDate);
  	    				initDisinfectantmaterialDatagrid(r.id);
  	    			}
  	    		}
  	    	});
		if(addOrEdit == "view"){
			$('#saveDisDialogButton').linkbutton('disable');
		}else{
			$('#saveDisDialogButton').linkbutton('enable');
		}
		
	}
	$('#addOrEditDis').val(addOrEdit);
	$('#disinfectantAddEditDialog').dialog('setTitle',title);
	$('#disinfectantAddEditDialog').dialog('open'); 
	document.getElementById("disinfectantAddEdit_event").href="javascript:"+clickName+"();";
}
/**确定（保存）*/
function onDisDialogSaveButton(){
		if( $('#disinfectantAddEditForm').form('validate')&&$('#disinfectantmaterialAddEditForm').form('validate') ){
			$('#saveDisDialogButton').linkbutton('disable');
			var rows=$("#disfectantmaterialTable").datagrid("getRows");
			for(var i=0;i<rows.length;i++){
				var rowIndex = $('#disfectantmaterialTable').datagrid('getRowIndex', rows[i]);
				$('#disfectantmaterialTable').datagrid('endEdit', rowIndex);
			}
			var rows1=$("#disfectantmaterialTable").datagrid("getRows");
			var ids=[];
			for (var i = 0; i < rows.length; i++) {
                ids.push(rows1[i].id);
            }
			var materialnames=[];
			for (var i = 0; i < rows.length; i++) {
				materialnames.push(rows1[i].materialname);
            }
			var contents=[];
			for (var i = 0; i < rows.length; i++) {
				contents.push(rows1[i].content);
            }
			var productionbatchs=[];
			for (var i = 0; i < rows.length; i++) {
				productionbatchs.push(rows1[i].productionbatch);
            }
			var validdates=[];
			for (var i = 0; i < rows.length; i++) {
				validdates.push(rows1[i].validdate);
            }
			var suppliers=[];
			for (var i = 0; i < rows.length; i++) {
				suppliers.push(rows1[i].supplier);
            }
			if($('#addOrEditDis').val() == 'add'){
				
				$.ajax({
					url:sybp()+'/disinfectantAction_add.action?materialnames='+encodeURIComponent(materialnames)+'&contents='+encodeURIComponent(contents)+'&productionbatchs='+encodeURIComponent(productionbatchs)+'&validdates='+encodeURIComponent(validdates)+'&suppliers='+encodeURIComponent(suppliers),
					type:'post',
					data:$('#disinfectantAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					 $('#saveDisDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#disinfectantAddEditDialog').dialog('close'); 
						$('#did').val(r.id);
						var disinfectantAddEdit_event=document.getElementById("disinfectantAddEdit_event");
						disinfectantAddEdit_event.click();
						//afterAddDialog0();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDisDialogButton').linkbutton('enable');
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
			}else{
				$.ajax({
					url:sybp()+'/disinfectantAction_editSave.action?ids='+encodeURIComponent(ids)+'&materialnames='+encodeURIComponent(materialnames)+'&contents='+encodeURIComponent(contents)+'&productionbatchs='+encodeURIComponent(productionbatchs)+'&validdates='+encodeURIComponent(validdates)+'&suppliers='+encodeURIComponent(suppliers),
					type:'post',
					data:$('#disinfectantAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
					$('#saveDisDialogButton').linkbutton('enable');
					if(r && r.success){
						$('#disinfectantAddEditDialog').dialog('close'); 
						$('#did').val(r.id);
						var disinfectantAddEdit_event=document.getElementById("disinfectantAddEdit_event");
						disinfectantAddEdit_event.click();
					}else{
						$.messager.alert('提示','请检查录入的数据');
						$('#saveDisDialogButton').linkbutton('enable');
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
}

//初始化配制者下拉选
function initCreatorCombobox(){
	$('#createdBy').combobox({    
		url : sybp()+'/employeeAction_loadListEmployee.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    onLoadSuccess:function(none){
		   
		}
		 
	});
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
