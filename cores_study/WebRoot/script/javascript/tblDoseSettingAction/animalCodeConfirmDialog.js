/**显示Dialog*/
function showAnimalCodeConfirmDialog(){
	document.getElementById("animalCodeConfirmDialog2").style.display="block";
	$('#animalCodeConfirmDialog').dialog('open'); 
	initAnimalCodeConfirmTable();
}

function initAnimalCodeConfirmTable(){
	var studyNoPara = encodeURIComponent($('#studyNoPara').val());
	$('#animalCodeConfirmTable').datagrid({    
		height:305,
		nowarp:  false,//单元格里自动换行
		fitColumns:false,
		idField:'id',
		singleSelect:true,
		border:false,
		url:sybp()+"/tblDoseSettingAction_loadAnimalCodeList.action?studyNoPara="+studyNoPara,
   	    columns :[[{
			title:'',
			field:'id',
			width:10,
			halign:'center',
			align:'center',
			hidden:true
		},{
			title:'剂量组',
			field:'dosageNum',
			width:50,
			halign:'center',
			align:'center'
		},{
			title:'剂量组说明',
			field:'dosageDesc',
			width:150,
			halign:'center',
			align:'center',
		},{
			title:'性别',
			field:'gender',
			width:55,
			halign:'center',
			align:'center',
			formatter: function(value,row,index){
				if (value == 1){
					return '♂';
				} else if (value == 2){
					return '♀';
				}else{
					return '';
				}
			}

		},{
			title:'动物编号',
			field:'animalCode',
			width:125,
			halign:'center',
			align:'center',
			hidden:false
		}]]
		
   	});
}
/**
 * 是
 * @return
 */
function onCodeConfirm(){
	$('#animalCodeConfirmDialog').dialog('close');
	qm_showQianmingDialog('confirmSign');
}
