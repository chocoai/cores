
var tableHeight;	//datagrid 高度
$(function(){
	tableHeight = document.body.clientHeight - 62;

	//初始化
	init();
    $('#maxdatebox').datebox('setValue',$('#endDate').val());
	$('#mindatebox').datebox('setValue',$('#startDate').val());
	$('#maxdatebox').datebox('validate');
    $('#layoutMainDiv').css('display','');  
});//匿名函数结束
    
  //页面初始化
function init(){
    //表格初始化
	$('#indexTable').datagrid({    
   	    //url:sybp()+'/tblNotificationAction_loadList.action?readFlag=-1',
        //title:'收件箱',
		height:tableHeight,
		width:750,
		nowarp:  false,//单元格里自动换行
		fitColumns:false,
		//pagination:true,//分页
		singleSelect:true,
		idField:'id',
   	    columns :[[{
			title:'序号',
			field:'id',
			width:30,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				return index+1;
   	    	}
		},{
			title:'专题编号',
			field:'studyNo',
			width:120,
			halign:'center',
			align:'center'
		},{
			title:'描述',
			field:'describe',
			width:190,
			halign:'center',
			align:'left'
		},{
			title:'提交者',
			field:'creater',
			width:52,
			halign:'center',
			align:'center',
		},{
			title:'提交日期',
			field:'createTime',
			width:75,
			halign:'center',
			align:'center'
		},{
			title:'备注',
			field:'remark',
			width:160,
			halign:'center',
			align:'left'
		},{
			title:'操作',
			field:'state',
			width:95,
			halign:'center',
			align:'left',
			formatter: function(value,row,index){
				if(value == 0){
					if($('#printAll').val() == 'true'){
						return '<a href="javascript:signHasPrint('+row.id+');">标记已打印</a>';
					}else{
						return '未打印';
					}
				}else if(value == 1){
					return '已打印';
				}else if(value == 2){
					return '已撤销';
				}
			}
		}]],
		onSelect:function(rowIndex, rowData){
			$('#cancelbutton').linkbutton('disable');
   			if(rowData){
   				view(rowData.id);
   				if(rowData.state == 0 && $('#write').val() == 'true' 
   					&& rowData.creater == $('#realName').val()){
   					var result = $.ajax({
   						url:sybp()+'/tblAttachmentIndexAction_isUncommitted.action',
   						type:'post',
   						data:{
   							id:rowData.id
   						},
   						async: false
   					}).responseText;
   					if(result == 'true'){
   						$('#cancelbutton').linkbutton('enable');
   					}
   				}
   			}
		},
		toolbar:'#toolbar',
		onLoadSuccess:function(data){
			var selectedId = $('#selectedId').val();
			if(selectedId){
				$('#indexTable').datagrid('selectRecord',selectedId);
				$('#selectdId').val('')
			}
		}
   	});
   	//开始日期databox
	$('#mindatebox').datebox({    
	 	onChange:function(newValue, oldValue){
			loadTableData();
		}
	});  
	//结束日期databox
	$('#maxdatebox').datebox({    
		onChange:function(newValue, oldValue){
			loadTableData();
		}
	});  
	//提交者combobox
	$('#creater').combobox({
		onChange:function(newValue, oldValue){
			loadTableData();
		}
	});
	//状态
	$('#state').combobox({
		onSelect:function(newValue, oldValue){
			loadTableData();
		}
	});
	
	
	//模糊查询
	$('#searchbox').searchbox({ 
	     height:19,
	     width:350,
		 searcher:function(value,name){ 
			loadTableData();
		}, 
		prompt:'模糊查询,请输入课题编号、描述' 
	});
}
//根据条件,加载datagrid数据
function loadTableData(){
	//禁用 撤销按钮
	$('#cancelbutton').linkbutton('disable');
	view(null);
    var startDate =  $('#mindatebox').datebox('getValue');
    var endDate = $('#maxdatebox').datebox('getValue');
    var creater =  $('#creater').combobox('getValue');
    var state = $('#state').combobox('getValue');
    var condition= $('#searchbox').searchbox('getValue');
	if( startDate && endDate){
		$('#indexTable').datagrid({    
       	    url:sybp()+'/tblAttachmentIndexAction_loadIndexList.action?startDate='+startDate+'&endDate='+
       	    endDate+'&creater='+creater+'&state='+state
       	    +'&condition='+encodeURIComponent(condition),
		});
	}
}
function reloadDatagrid(){
	//$('#indexTable').datagrid('reload');
	$('#state').combobox('select',$('#state').combobox('getValue'));
}
//标记已打印(索引及对应附件都标记打印)
function signHasPrint(id){
	$.messager.confirm('确认','您确认想要标记已打印吗？',function(r){    
	    if (r){    
	    	$.ajax({
	        	url:sybp()+'/tblAttachmentIndexAction_signHasPrint.action',
	        	type:'post',
	        	data:{
	        		id:id
	    		},
	        dataType:'json',
	        success:function(r){
		        	if(r && r.success){
		        		reloadDatagrid();
		        		parent.showMessager(1,r.msg,true,5000);
		        	}else if(r){
		        		parent.showMessager(2,r.msg,true,5000);
		        	}else{
		        		parent.showMessager(2,'与服务器交互错误！',true,5000);
		        	}
	    		}
	        });  
	    }    
	}); 
}


/**
 * 查看信息
 * @return
 */
function view(id){
	window.open('${pageContext.request.contextPath}/tblAttachmentIndexAction_view.action?id='+id, 'view')
}
/**
 * 添加 按钮响应函数
 * @return
 */
function onAddButton(){
	showAttachementAddDialog(afterAddSave,"文件上传");
}
/**
 * 撤销 响应函数
 * @return
 */
function onCancel(){
	var row = $('#indexTable').datagrid('getSelected');
	if(row){
		$.messager.confirm('确认','您确认想要撤销记录吗？',function(r){    
		    if (r){  
		    	qm_showQianmingDialog('cancelAttachment');
		    }    
		}); 
	}
}
/**
 * 撤销 文件
 * @return
 */
function cancelAttachment(){
	var row = $('#indexTable').datagrid('getSelected');
	 $.ajax({
     	url:sybp()+'/tblAttachmentIndexAction_cancel.action',
     	type:'post',
     	data:{
     	id:row.id
     },
     dataType:'json',
     success:function(r){
     	if(r && r.success){
     		reloadDatagrid();
     		parent.showMessager(1,r.msg,true,5000);
     	}else if(r){
     		parent.showMessager(2,r.msg,true,5000);
     	}else{
     		parent.showMessager(2,'与服务器交互错误！',true,5000);
     	}
     }
     });
}
/**
 * 文件上传后，更新页面数据
 * @param id
 * @return
 */
function afterAddSave(id){
	$('#selectedId').val(id);
	parent.showMessager(1,'文件上传成功！',true,5000);
	
	//若在全部上，则不变
	if($('#state').combobox('getValue') == '-1'){
		$('#state').combobox('select','-1');
	}else{
		//若不是，则定位未提交
		$('#state').combobox('select','0');
	}
	
	
	
}