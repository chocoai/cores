var tableHeight;//当前页面可见区域高度 - 30
var tableWidth;
window.onload = function(){
	tableHeight = document.body.clientHeight - 30;
	tableWidth  = document.body.clientWidth-2;
	$('#visceraTable').datagrid({
		url : sybp()+"/tblSuperficialTumorVisceraAction_noSuperficialList.action",
		title:'',
		height: tableHeight,
		width:200,
		iconCls:'',//'icon-save',
		//pagination:true,//下面状态栏
		//fit:true,
		fitColumns:true,//不出现横向滚动条
		singleSelect:true,
		nowarp:  false,//单元格里自动换行
		//border:false,
		idField:'visceraCode', //pk
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
		columns :[[{
			title : '脏器名称',
			field : 'visceraName',
			width : 180,
			halign:'center'
		},{
			title :'',
			field :'visceraCode',
			width :10,
			hidden:true
		}]],
		
		onDblClickRow :function(rowIndex, rowData){
			addSuperficialTumorViscera();
		},
		onLoadSuccess:function(data){
			$(this).datagrid('clearSelections');
			if($('#selectVisceraCoce1').val()){
				$(this).datagrid('selectRecord',$('#selectVisceraCoce1').val());
			}
		}
	});
	
	$('#superficialTumorVisceraTable').datagrid({
		url : sybp()+"/tblSuperficialTumorVisceraAction_superficialList.action",
		title:'',
		height: tableHeight,
		width:200,
		iconCls:'',//'icon-save',
		//pagination:true,//下面状态栏
		//fit:true,
		fitColumns:true,//不出现横向滚动条
		singleSelect:true,
		nowarp:  false,//单元格里自动换行
		//border:false,
		idField:'visceraCode', //pk
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
		columns :[[{
			title : '浅表肿瘤脏器名称',
			field : 'visceraName',
			width : 180,
			halign:'center'
		},{
			title :'',
			field :'visceraCode',
			width :10,
			hidden:true
		}]],
		
		onDblClickRow :function(rowIndex, rowData){
			removeSuperficialTumorViscera();
		},
		onLoadSuccess:function(data){
			$(this).datagrid('clearSelections');
			if($('#selectVisceraCoce2').val()){
				$(this).datagrid('selectRecord',$('#selectVisceraCoce2').val());
			}
		}
	});

}

$(function(){
	//显示整个布局
	$('#layoutMainDiv').css('display',''); 
});

/*添加行（添加按钮事件）*/
function addSuperficialTumorViscera(){
	//得到行
	var noSuperficialRow =$('#visceraTable').datagrid('getSelected');
//	var superficialRow =$('#superficialTumorVisceraTable').datagrid('getSelected');
	//1.判断选择是否为空
	if(!noSuperficialRow){
		//$.messager.alert('提示','请选择动物Id号');
		parent.parent.showMessager(2,'请选择脏器名称',true,3000);
		return;
	}
	$('#selectVisceraCoce1').val('');
	$('#selectVisceraCoce2').val(noSuperficialRow.visceraCode);
	$.ajax({
		url:sybp()+'/tblSuperficialTumorVisceraAction_addOne.action',
		type:'post',
		data:{
			visceraCode:noSuperficialRow.visceraCode,
			visceraName:noSuperficialRow.visceraName
		},
		dataType:'json',
		success: function(r){
			if(r && r.success){
				$('#visceraTable').datagrid('reload');
				$('#superficialTumorVisceraTable').datagrid('reload');
			}else{
				parent.parent.showMessager(3,r.msg,true,1000);
			}
		}
	});
}

/*删除行（添加按钮事件）*/
function removeSuperficialTumorViscera(){
	//得到行
//	var noSuperficialRow =$('#visceraTable').datagrid('getSelected');
	var superficialRow =$('#superficialTumorVisceraTable').datagrid('getSelected');
	//1.判断选择是否为空
	if(!superficialRow){
		parent.parent.showMessager(2,'请选择浅表肿瘤脏器名称',true,3000);
		return;
	}
	$('#selectVisceraCoce1').val(superficialRow.visceraCode);
	$('#selectVisceraCoce2').val('');
	$.ajax({
		url:sybp()+'/tblSuperficialTumorVisceraAction_removeOne.action',
		type:'post',
		data:{
			visceraCode:superficialRow.visceraCode,
			visceraName:superficialRow.visceraName
		},
		dataType:'json',
		success: function(r){
			if(r && r.success){
				$('#visceraTable').datagrid('reload');
				$('#superficialTumorVisceraTable').datagrid('reload');
			}else{
				parent.parent.showMessager(3,r.msg,true,1000);
			}
		}
	});
}
