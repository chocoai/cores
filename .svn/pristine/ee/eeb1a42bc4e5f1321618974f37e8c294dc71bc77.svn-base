//显示查询dialog
function showSelectCustomerDialog(clickName,title){
	/*签名Dialog*/
	 document.getElementById("selectCustomerDialog2").style.display="block";
	 $('#selectCustomerDialog').dialog('setTitle',title);
	 $('#selectCustomerDialog').dialog('open'); 
	 //加载下拉选
	 loadCustomerTreeGrid();
	 document.getElementById("selectCustomer_event").href="javascript:"+clickName+"();";
	 $('#clickName').val(clickName);
	 //加载客户信息表格
	 initCustomerTable();
	//初始化客户搜索框
	 initSearchbox();
	
//	 $('#customerName').bind('keyup', function(event) {/* 增加回车提交功能 */
//			if (event.keyCode == '13') {
//				
//			}
//		});
}
//初始化客户搜索框
function initSearchbox(){
	$('#customerName').searchbox({ 
		searcher:function(value,name){ 
			loadCustomerDataGrid(value);
		}
	}); 
}
//初始化客户表
function initCustomerTable(){
	 $('#selectCustomerTable').datagrid({
		  height:413,
		  width:770,
		  singleSelect:true,
		  columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'客户名称',
				field:'customerName',
				width:200,
				halign:'center'
			},{
				title:'客户类型',
				field:'customerType',
				width:10,
				hidden:true,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
				if ( value == "1" ){
					return "厂家";
				}else{
	                return "委托方";
				}
			}},{
				title:'联系人',
				field:'linkman',
				width:50,
				halign:'center',
				align:'center'
			},{
				title:'地址',
				field:'address',
				width:300,
				halign:'center',
				align:'left'
				
			},{
				title:'电话',
				field:'tel',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'手机',
				field:'mobile',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'电子邮件',
				field:'email',
				width:150,
				halign:'center',
				align:'left'
		    },{
				title:'网址',
				field:'http',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'传真',
				field:'fax',
				width:100,
				halign:'center',
				align:'center'
		    },{
				title:'邮编',
				field:'postalCode',
				width:50,
				halign:'center',
				align:'center'
		    },{
				title:'主要产品类型',
				field:'tiCode',
				width:100,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
				if ( value == "01" ){
					return "医药";
				}else if(value=="02"){
                    return "农药";
				}else{
					return "化学品";
				}
			}},{
				title:'有无合同',
				field:'contractMark',
				width:100,
				hidden:true,
				halign:'center',
				align:'center'
			}]],
			onDblClickRow:function(rowIndex, rowData){
		 		$('#cuid').val(rowData.id);
		 		$('#customerNameadd').val(rowData.customerName);
		 		$('#selectCustomerDialog').dialog('close');
		 		var selectCustomer_event=document.getElementById("selectCustomer_event");
		 		selectCustomer_event.click();
	 		}
	 });
}
//加载地址
function loadCustomerTreeGrid(){
	 $('#tblRigonTableTreeGrid').treegrid({ 
		url : sybp()+'/tblRegionAction_getTblRigonTreedatagrid.action',
		height:445,
		width:190,
		idField:'id',    
	    treeField:'regionName', 
	    animate:false,   
	    singleSelect: true, //不支持多选
	    columns:[[  
	                {title:'id',field:'id',width:0,hidden:true},
			        {title:'地区级别',field:'level',width:0,hidden:true}, 
			        {title:'地区名称',field:'regionName',width:169},    
			        {field:'_parentId',title:'_parentId',width:0,hidden:true}
			    ]],
		//工具栏
		onSelect:function(node){
		 loadCustomerDataGridByRegionId(node.id);
		}
	 });
	
}
//模糊查询
function loadCustomerDataGrid(inputValue){
	if(inputValue){
		$('#tblRigonTableTreeGrid').treegrid('unselectAll');
		$('#selectCustomerTable').datagrid({
			url : sybp()+'/tblCustomerAction_selectCustomerloadList.action?customerName='+ encodeURIComponent(inputValue),
	   	});
	}else{
		 var jsonstr = '{"total":0,"rows":[]}';  
		 var data = $.parseJSON(jsonstr); 
		$('#selectCustomerTable').datagrid('loadData',data);
		
	}
}
//根据地址下拉选地址查询
function loadCustomerDataGridByRegionId(regionId){
	$('#customerName').val('');
	$('#selectCustomerTable').datagrid({
		url : sybp()+'/tblCustomerAction_loadList.action?regionId='+regionId,
   	});
}
//确定按钮事件
function onDialogConfrimButton(){
	
	var rowData = $('#selectCustomerTable').datagrid('getSelected');
	if(rowData){
		$('#cuid').val(rowData.id);
		$('#customerNameadd').val(rowData.customerName);
		$('#selectCustomerDialog').dialog('close');
		var selectCustomer_event=document.getElementById("selectCustomer_event");
		selectCustomer_event.click();
	}else{
		$.messager.alert('警告','请先选择客户信息');
	}
}

//新增客户信息
function onNewCreate(){
	$('#selectCustomerDialog').dialog('close');
	showCustomerAddEditDialog('afterAddDialog0','add','登记客户信息');
}


//selectCustomer添加Dialog后事件
function afterAddDialog0(){
	//执行刷新地区栏目
//   parent.window.frames["left"].afterAddCustomer0();
	
  // parent.showMessager(1,'添加成功',true,5000);
	$('#sponsorId').val($('#cuid').val());
	$('#sponsorName').val($('#customerNameadd').val());
	$('#sponsorName').validatebox('validate');
	parent.window.frames["left"].frames["left0"].updateTblRigonTable();
	
}