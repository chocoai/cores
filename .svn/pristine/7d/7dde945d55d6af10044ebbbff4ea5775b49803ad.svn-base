/**显示Dialog*/
function showAnimalCodeDialog(){
	document.getElementById("animalCodeDialog2").style.display="block";
	$('#animalCodeDialog').dialog('open'); 
	initAnimalCodeTable();
}

function initAnimalCodeTable(){
	var studyNoPara = encodeURIComponent($('#studyNoPara').val());
	$('#animalCodeTable').datagrid({    
		height:380,
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
		}]],
		toolbar:'#animalCodeToolbar',
		onSelect:function(rowIndex, rowData){
			$('#animalCodeDelButton').linkbutton('enable');
			$('#animalCodeEditbutton').linkbutton('enable');
		},
		onLoadSuccess:function(data){
			$('#animalCodeAddbutton').linkbutton('enable');
			$('#animalCodeDelButton').linkbutton('disable');
			$('#animalCodeEditbutton').linkbutton('disable');
			var selectedId = $('#selectedId2').val();
			if(selectedId){
				$(this).datagrid('selectRecord',selectedId);
				$('#selectedId2').val('');
			}
		}
   	});
}
/**
 * 添加
 * @return
 */
function onAnimalCodeAddButton(){
	showAnimalCodeAddEditDialog(comeback_add,'add','');
}
function comeback_add(selectedId2){
	$('#selectedId2').val(selectedId2);
	$('#animalCodeTable').datagrid('reload');
}

/**
 * edit
 * @return
 */
function onAnimalCodeEditButton(){
	var row = $('#animalCodeTable').datagrid('getSelected');
	if(row){
		showAnimalCodeAddEditDialog(comeback_edit,'edit',row);
	}
}
function comeback_edit(selectedId2){
	$('#selectedId2').val(selectedId2);
	$('#animalCodeTable').datagrid('reload');
}
//删除
function onAnimalCodeDelButton(){
	var row = $('#animalCodeTable').datagrid('getSelected');
	var studyNoPara= encodeURIComponent($('#studyNoPara').val());
	if(row){
		$.ajax({    
			url:sybp()+'/tblDoseSettingAction_deleteAnimalCode.action',
			type:'post',
			dataType:'json',
			data:{
				codeId:row.id,
				studyNo:studyNoPara
			},
			success:function(r){   
			if(r && r.success == 'true'){
				$('#animalCodeTable').datagrid('reload');
			}else if(r && r.msg){
				$.messager.alert('警告',r.msg);     
			}else{
				$.messager.alert('警告','与服务器交互错误');     
			}
		} 
		}); 
	}
}