var reportNumberTable;
var tableHeight;//当前页面可见区域高度 - 35
var tableWidth;

$(function(){
	tableHeight = document.body.clientHeight - 35;
	tableWidth  = document.body.clientWidth-2;
	//初始化datagrid
	initReportNumberTable();
	//按钮单击事件
	document.getElementById('editOne').onclick = editOne;
	//显示整个页面
	$('#layoutMainDiv').css('display','');
});

/**初始化datagrid
 * @return
 */
function initReportNumberTable(){
	reportNumberTable=$('#reportNumberTable').datagrid({
		url : sybp()+"/dictReportNumberAction_loadList.action",
		title:'',
		iconCls:'',//'icon-save',
		singleSelect:true,//只能选一行
		pagination:false,//下面状态栏
		height:tableHeight,
		width:tableWidth,  
		//fit:true,
		fitColumns:false,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		border:false,
		idField:'id', //pk
		toolbar:'#toolbar',
		columns :[[{title :'',field :'id',hidden:true}
		,{title : '报表名称',field : 'reportName',width : 300,halign:'left'}
		,{field:'number',title:'编号',width:200,halign:'center',align:'center'}
		]],
		onSelect:function(rowIndex, rowData){
			$('#editOne').linkbutton('enable');
		}
	});//end datagrid
}

/**编辑
 * @return
 */
function editOne(){
	var selectItem = reportNumberTable.datagrid('getSelected');
	if(selectItem){
		showPathAddEditDialog(selectItem,callback);
	}else{
		
	}
}

/**编辑后函数
 * @return
 */
function callback(){
	reportNumberTable.datagrid('reload');
	parent.showMessager(1,'保存成功!',true,5000);
}