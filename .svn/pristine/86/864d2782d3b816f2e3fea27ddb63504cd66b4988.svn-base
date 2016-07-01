var visceraTable;
var tableHeight;//当前页面可见区域高度 - 30
var tableWidth;
window.onload = function(){
	tableHeight = document.body.clientHeight - 35;
	tableWidth  = document.body.clientWidth-2;
	//treegrid 初始化
	visceraTable=$('#visceraTable').treegrid({
			url : sybp()+"/dictVisceraAction_loadList.action?sortType=1",
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
			idField:'visceraCode', //pk
			treeField:'visceraName', 
			toolbar:'#toolbar',
			columns :[[{title :'',field :'visceraCode',hidden:true}
			,{field:'_parentId',title:'_parentId',width:10,hidden:true}
			,{title : '脏器',field : 'visceraName',width : 150,halign:'center'}
			,{title : '拼音(首字母)',field : 'py',width : 100,halign:'center',align:'center'}
			,{title : '所属性别',field : 'gender',width : 70,halign:'center',align:'center'
				,formatter: function(value,row,index){
				if(value == 0){
					return "全部";
				}else if(value == 1){
					return "♂";
				}else if(value == 2){
					return "♀";
				}else{
					return "";
				}
			}}
			,{title : '',field : 'animalType',width : 100,halign:'center',align:'center',hidden:true}
			,{title : '',field : 'animalFlag',width : 100,halign:'center',align:'center',hidden:true}
			,{title : '所属动物种类',field : 'animalTypeNames',width : 180,halign:'center',align:'center'}
			,{title : '是否成对脏器',field : 'isPart',width : 80,halign:'center',align:'center'
				,formatter:function(value,row,index){
				if(value == 0){
					return "否";
				}else if(value == 1){
					return "是";
				}else{
					return "";
				}
			}}
			,{title : '英文',field : 'visceraNameEn',width : 160,halign:'center'}
			,{title : '日文',field : 'visceraNameJp',width : 160,halign:'center'}
			,{field:'level',title:'level',width:10,hidden:true}
			,{field:'sn',title:'sn',width:20,hidden:true}
			]],
			onSelect:function(row){
				//新建下一级     可用
				if(row.level == 1){
					$('#addSon').linkbutton('enable');
				}else{
					$('#addSon').linkbutton('disable');
				}
				//编辑\删除可用
				$('#editOne').linkbutton('enable');
				$('#delOne').linkbutton('enable');
				
				if(row && row.level == 1){
					var rootRows =$(this).treegrid('getRoots');
					var len = rootRows.length;
					//上移是否可用
					if(rootRows[0].visceraCode == row.visceraCode){
						$('#upOne').linkbutton('disable');
					}else{
						$('#upOne').linkbutton('enable');
					}
					//下移是否可用
					if(rootRows[len - 1].visceraCode == row.visceraCode){
						$('#downOne').linkbutton('disable');
					}else{
						$('#downOne').linkbutton('enable');
					}
					
				}else {
					//所有兄弟姐妹(包括自己)
					var siblings = $(this).treegrid('getChildren',row._parentId);
					var len = siblings.length;
					//上移是否可用
					if(siblings[0].visceraCode == row.visceraCode){
						$('#upOne').linkbutton('disable');
					}else{
						$('#upOne').linkbutton('enable');
					}
					//下移是否可用
					if(siblings[len - 1].visceraCode == row.visceraCode){
						$('#downOne').linkbutton('disable');
					}else{
						$('#downOne').linkbutton('enable');
					}
				}
			},
			onLoadSuccess:function(row,data){
				
				$('#addSon').linkbutton('disable');
				$('#editOne').linkbutton('disable');
				$('#delOne').linkbutton('disable');
				$('#upOne').linkbutton('disable');
				$('#upOne').linkbutton('disable');
				
				var selectId = $('#selectId').val();
				if(selectId){
					$(this).treegrid('select',selectId);
				}
				$('#sortType').combobox('setValue',1);
			}
		});//end treegrid

	//新建     响应函数
	document.getElementById('addOne').onclick = addOne;
	//新建下一级     响应函数
	document.getElementById('addSon').onclick = addSon;
	//编辑     响应函数
	document.getElementById('editOne').onclick = edit;
	//删除    响应函数
	document.getElementById('delOne').onclick = delOne;
	//上移  响应函数
	document.getElementById('upOne').onclick = upOne;
//	$('#upOne').click(upOne);
	//下移  响应函数
	document.getElementById('downOne').onclick = downOne;
//	$('#downOne').click(downOne);
	
	//脏器类别
	$('#visceraType').combobox({
		url:sybp()+'/dictVisceraAction_loadVisceraType.action',
	    valueField:'id',    
	    textField:'visceraTypeName',
	    required:true,
	    editable:false,
	    onSelect:function(record){
			if(record.id && '其他' != record.visceraTypeName){
				$('#visceraName').val(record.visceraTypeName);
				$('#py').val(makePy(record.visceraTypeName));
			}
		}
	});
	//所属动物种类
//	$('#animalType').combobox({
//		url:sybp()+'/dictVisceraAction_loadAnimalType.action',
//		valueField:'id',    
//		textField:'typeName',
//		editable:false
//	});
	//所属动物   datagrid
	$('#dictVisceraAnimal').datagrid({    
	    url:sybp()+'/dictVisceraAction_loadAnimalType.action',
	    height:235,
	    width:173,
	    showHeader:true,
	    idField:'typeName',
	    fitColumns:false,//不出现横向滚动条
	    columns:[[    
	        {field:'id',title:'',width:40,checkbox:true},    
	        {field:'typeName',title:'动物名称',width:125}   
	    ]],    
	    onClickRow: function(rowIndex, rowData){
//            if($('#visceraAnimalTD :checkbox')[0].disabled){
//            		$('#visceraAnimalTD :checkbox')[rowIndex+1].disabled = false;
//            		$('#visceraAnimalTD :checkbox')[rowIndex+1].click();
//            		$('#visceraAnimalTD :checkbox')[rowIndex+1].disabled = true;
//            }
        }
	});  

	//脏器名称  change 事件
	$('#visceraName').change(function(){
		$('#py').val(makePy(this.value));
	});
	
	//给性别A标签加 响应函数
	var genderTd = document.getElementById('genderTd');
	var genderA = genderTd.getElementsByTagName('a');
	var genderRadio = document.getElementsByName('gender');
	for(var i = 0;i < genderA.length;i++){
		genderA[i].onclick = function(){
			if(this.name == '1'){
				genderRadio[1].click();
			}else if(this.name == 2){
				genderRadio[2].click();
			}else{
				genderRadio[0].click();
				
			}
			this.blur();
		}
	}
	
	//给成对脏器A标签加 响应函数
	var isPartTd = document.getElementById('isPartTd');
	var isPartA = isPartTd.getElementsByTagName('a');
	var isPartRadio = document.getElementsByName('isPart');
	for(var i = 0;i < isPartA.length;i++){
		isPartA[i].onclick = function(){
			if(this.name == '1'){
				isPartRadio[1].click();
			}else{
				isPartRadio[0].click();
				
			}
			this.blur();
		}
	}
	//排序方式
	$('#sortType').combobox({
	    required:true,
	    editable:false,
	    onSelect:function(record){
//		alert(record.value);return;
		  loadListBySortType(record.value);
		}
	});
	
	//移动行数
	$('#lineNum').numberbox({
		onChange:function(newValue,oldValue){
			if(!newValue){
				$(this).numberbox('setValue',1);
			}
		}	
	});
}

$(function(){
	//显示整个布局
	$('#layoutMainDiv').css('display',''); 
});
//按照排序方式加载脏器列表
function loadListBySortType(sortType){
	visceraTable=$('#visceraTable').treegrid({
		url : sybp()+"/dictVisceraAction_loadList.action?sortType="+sortType,
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
		idField:'visceraCode', //pk
		treeField:'visceraName', 
		toolbar:'#toolbar',
		columns :[[{title :'',field :'visceraCode',hidden:true}
		,{field:'_parentId',title:'_parentId',width:10,hidden:true}
		,{title : '脏器',field : 'visceraName',width : 150,halign:'center'}
		,{title : '拼音(首字母)',field : 'py',width : 100,halign:'center',align:'center'}
		,{title : '所属性别',field : 'gender',width : 70,halign:'center',align:'center'
			,formatter: function(value,row,index){
			if(value == 0){
				return "全部";
			}else if(value == 1){
				return "♂";
			}else if(value == 2){
				return "♀";
			}else{
				return "";
			}
		}}
		,{title : '',field : 'animalType',width : 100,halign:'center',align:'center',hidden:true}
		,{title : '',field : 'animalFlag',width : 100,halign:'center',align:'center',hidden:true}
		,{title : '所属动物种类',field : 'animalTypeNames',width : 180,halign:'center',align:'center'}
		,{title : '是否成对脏器',field : 'isPart',width : 80,halign:'center',align:'center'
			,formatter:function(value,row,index){
			if(value == 0){
				return "否";
			}else if(value == 1){
				return "是";
			}else{
				return "";
			}
		}}
		,{title : '英文',field : 'visceraNameEn',width : 160,halign:'center'}
		,{title : '日文',field : 'visceraNameJp',width : 160,halign:'center'}
		,{field:'level',title:'level',width:10,hidden:true}
		,{field:'sn',title:'sn',width:20,hidden:true}
		]],
		onSelect:function(row){
			//新建下一级     可用
			if(row.level == 1){
				$('#addSon').linkbutton('enable');
			}else{
				$('#addSon').linkbutton('disable');
			}
			//编辑\删除可用
			$('#editOne').linkbutton('enable');
			$('#delOne').linkbutton('enable');
			
			if(row && row.level == 1){
				var rootRows =$(this).treegrid('getRoots');
				var len = rootRows.length;
				//上移是否可用
				if(rootRows[0].visceraCode == row.visceraCode){
					$('#upOne').linkbutton('disable');
				}else{
					$('#upOne').linkbutton('enable');
				}
				//下移是否可用
				if(rootRows[len - 1].visceraCode == row.visceraCode){
					$('#downOne').linkbutton('disable');
				}else{
					$('#downOne').linkbutton('enable');
				}
				
			}else {
				//所有兄弟姐妹(包括自己)
				var siblings = $(this).treegrid('getChildren',row._parentId);
				var len = siblings.length;
				//上移是否可用
				if(siblings[0].visceraCode == row.visceraCode){
					$('#upOne').linkbutton('disable');
				}else{
					$('#upOne').linkbutton('enable');
				}
				//下移是否可用
				if(siblings[len - 1].visceraCode == row.visceraCode){
					$('#downOne').linkbutton('disable');
				}else{
					$('#downOne').linkbutton('enable');
				}
			}
		},
		onLoadSuccess:function(row,data){
			
			$('#addSon').linkbutton('disable');
			$('#editOne').linkbutton('disable');
			$('#delOne').linkbutton('disable');
			$('#upOne').linkbutton('disable');
			$('#upOne').linkbutton('disable');
			
			var selectId = $('#selectId').val();
			if(selectId){
				$(this).treegrid('select',selectId);
			}
		}
	});
}
/**
 *  新建
 * @return
 */
function addOne(){
	showVisceraAddEditDialog('addOne','新建');
}

/**
 *  新建下一级
 * @return
 */
function addSon(){
	var selectRow = visceraTable.treegrid('getSelected');
	if(selectRow && selectRow.level == 1){
		showVisceraAddEditDialog('addSon','新建下一级');
	}else{
		$.messager.alert('警告','请先选择一级脏器数据');
	}
}
/**
 * 
 * @return
 */
function edit(){
	var selectRow = visceraTable.treegrid('getSelected');
	if(selectRow && selectRow.level==1){
		editOne();
	}else if(selectRow && selectRow.level==2){
		editSon();
	}else{
		$.messager.alert('警告','请先选择数据');
	}
}
/**
 *  编辑一级
 * @return
 */
function editOne(){
	showVisceraAddEditDialog('editOne','编辑');
}
/**
 *  编辑二级
 * @return
 */
function editSon(){
	showVisceraAddEditDialog('editSon','编辑');
}
/**
 *  删除
 * @return
 */
function delOne(){
	var selectRow = visceraTable.treegrid('getSelected');
	var msg = "";
	if(selectRow && selectRow.level==1){
		var sonRows = visceraTable.treegrid('getChildren',selectRow.visceraCode);
		if(sonRows && sonRows.length){
			msg ='该数据的下一级数据将同时被删除,确定继续？'
		}else{
			msg = '您确认想要删除记录吗？';
		}
	}else if(selectRow){
		msg = '您确认想要删除记录吗？';
	}
	$.messager.confirm('确认',msg,function(r){    
		if (r){    
			delVisceraById(selectRow.visceraCode);    
		}    
	});
}
/**
 * 根据visceraCode,删除数据
 * @return
 */
function delVisceraById(visceraCode){
	$.ajax({
		url:sybp()+'/dictVisceraAction_delOne.action',
		type:'post',
		data:{
			visceraCode:visceraCode
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.showMessager(1,'脏器删除成功!',true,5000);
				//刷新treegrid
				visceraTable.treegrid('reload');
			}else{
				$.messager.alert('警告','与服务器交互错误！');
			}
		}
	});
}

/**
 * 上移
 * @return
 */
function upOne(){
	var selectRow = visceraTable.treegrid('getSelected');
	var lineNum = $('#lineNum').numberbox('getValue');
	var sortType= $('#sortType').combobox('getValue');
	var sn=0;
	if(sortType==1){
		sn=selectRow.sn;
	}else if(sortType==2){
		sn=selectRow.snWeight;
	}else if(sortType==3){
		sn=selectRow.snFixed;
	}
	if(selectRow &&selectRow.level == 1){
		$('#selectId').val(selectRow.visceraCode);
		$.ajax({
			url:sybp()+'/dictVisceraAction_upOne.action?sortType='+sortType,
			type:'post',
			data:{
				sn:sn,
				lineNum:lineNum
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//刷新treegrid
					//parent.showMessager(1,'上移成功!',true,5000);
					visceraTable.treegrid('reload');
				}else{
					//刷新treegrid
					parent.showMessager(2,'与服务器交互错误！',true,5000);
				}
			}
		});
	}else if(selectRow &&selectRow.level == 2){
		$('#selectId').val(selectRow.visceraCode);
		$.ajax({
			url:sybp()+'/dictVisceraAction_upOne.action?sortType='+sortType,
			type:'post',
			data:{
				sn:sn,
				pvisceraCode:selectRow._parentId,
				lineNum:lineNum
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//刷新treegrid
					//parent.showMessager(1,'上移成功!',true,5000);
					visceraTable.treegrid('reload');
				}else{
					//刷新treegrid
					parent.showMessager(2,'与服务器交互错误！',true,5000);
				}
			}
		});
		
	}else{
		$.messager.alert('警告','请先选择数据！');
	}
}
/**
 * 下移
 * @return
 */
function downOne(){
	var selectRow = visceraTable.treegrid('getSelected');
	var lineNum = $('#lineNum').numberbox('getValue');
	var sortType= $('#sortType').combobox('getValue');
	var sn=0;
	if(sortType==1){
		sn=selectRow.sn;
	}else if(sortType==2){
		sn=selectRow.snWeight;
	}else if(sortType==3){
		sn=selectRow.snFixed;
	}
	if(selectRow &&selectRow.level == 1){
		$('#selectId').val(selectRow.visceraCode);
		$.ajax({
			url:sybp()+'/dictVisceraAction_downOne.action?sortType='+sortType,
			type:'post',
			data:{
				sn:sn,
				lineNum:lineNum
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//刷新treegrid
					//parent.showMessager(1,'下移成功!',true,5000);
					visceraTable.treegrid('reload');
				}else{
					//刷新treegrid
					parent.showMessager(2,'与服务器交互错误！',true,5000);
				}
			}
		});
	}else if(selectRow &&selectRow.level == 2){
		$('#selectId').val(selectRow.visceraCode);
		$.ajax({
			url:sybp()+'/dictVisceraAction_downOne.action?sortType='+sortType,
			type:'post',
			data:{
				sn:sn,
				pvisceraCode:selectRow._parentId,
				lineNum:lineNum
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//刷新treegrid
					//parent.showMessager(1,'下移成功!',true,5000);
					visceraTable.treegrid('reload');
				}else{
					//刷新treegrid
					parent.showMessager(2,'与服务器交互错误！',true,5000);
				}
			}
		});
		
	}else{
		$.messager.alert('警告','请先选择数据！');
	}
}


/**显示Dialog*/
function showVisceraAddEditDialog(addOrEdit,title){
	/*签名Dialog*/
	document.getElementById("visceraAddEditDialog2").style.display="block";
	$('#visceraAddOrEdit').val(addOrEdit);
	//初始化Dialog 显示数据
	initDialogData(addOrEdit);
	$('#visceraAddEditDialog').dialog('setTitle',title);
	$('#visceraAddEditDialog').dialog('open'); 
	
}
/**
 * 初始化Dialog 显示数据
 * @param addOrEdit
 * @return
 */
function initDialogData(addOrEdit){
	//新建
	if(addOrEdit == 'addOne'){
		document.getElementById('visceraCode').value = '';
		document.getElementById('pvisceraCode').value = '';
		document.getElementById('pVisceraName').firstChild.nodeValue = '无';
		//先启用，后禁用再禁用
		$('#visceraType').combobox('enable');
		$(':radio').attr('disabled',false);
		//$('#animalType').combobox('enable');
		
		$('#visceraType').combobox('select','');
		$('#oldVisceraName').val('');
		$('#visceraName').val('');
		$('#py').val('');
		$('#visceraNameJp').val('');
		$('#visceraNameEn').val('');
		document.getElementsByName('gender')[0].click();
		//$('#animalType').combobox('select','0');
		document.getElementsByName('isPart')[0].click();
		$('#dictVisceraAnimal').datagrid('uncheckAll');
		
		$('#visceraAnimalTD :checkbox').attr('disabled',false);
		//显示连续录入复选框
		document.getElementById("continueAddCheckBox").style.display="";
		document.getElementById("continueAddA").style.display="";
		document.getElementById("continueAddCheckBox").checked = false;
	}else if(addOrEdit == 'editOne'){
		var selectRow = visceraTable.treegrid('getSelected');
		document.getElementById('visceraCode').value = selectRow.visceraCode;
		document.getElementById('pvisceraCode').value = '';
		document.getElementById('pVisceraName').firstChild.nodeValue = '无';
		//先启用，后禁用再禁用
		$('#visceraType').combobox('enable');
		$(':radio').attr('disabled',false);
		//$('#animalType').combobox('enable');
		
		$('#visceraType').combobox('select',selectRow.visceraType);
		$('#oldVisceraName').val(selectRow.visceraName);
		$('#visceraName').val(selectRow.visceraName);
		$('#py').val(selectRow.py);
		$('#visceraNameJp').val(selectRow.visceraNameJp);
		$('#visceraNameEn').val(selectRow.visceraNameEn);
		document.getElementsByName('gender')[selectRow.gender].click();
		//$('#animalType').combobox('select',selectRow.animalType);
		document.getElementsByName('isPart')[selectRow.isPart].click();
		$('#dictVisceraAnimal').datagrid('uncheckAll');
		if(selectRow.animalFlag == 1){
			$('#dictVisceraAnimal').datagrid('checkAll');
		}else{
			var animalTypeNames = selectRow.animalTypeNames;
			selectAnimalTypeNames(animalTypeNames);
		}
		$('#visceraAnimalTD :checkbox').attr('disabled',false);
		//不显示连续录入复选框
		document.getElementById("continueAddCheckBox").style.display="none";
		document.getElementById("continueAddA").style.display="none";
	}else if(addOrEdit == 'addSon'){
		var selectRow = visceraTable.treegrid('getSelected');
		document.getElementById('visceraCode').value = '';
		document.getElementById('pvisceraCode').value = selectRow.visceraCode;
		document.getElementById('pVisceraName').firstChild.nodeValue = selectRow.visceraName;
		
		$('#visceraType').combobox('select',selectRow.visceraType);
		//先启用，后禁用再禁用
		$('#visceraType').combobox('enable');
		$(':radio').attr('disabled',false);
		//$('#animalType').combobox('enable');
		
		$('#oldVisceraName').val('');
		$('#visceraName').val('');
		$('#py').val('');
		$('#visceraNameJp').val('');
		$('#visceraNameEn').val('');
		document.getElementsByName('gender')[selectRow.gender].click();
		//$('#animalType').combobox('select',selectRow.animalType);
		document.getElementsByName('isPart')[0].click();
		
		$('#visceraType').combobox('disable');
		$(':radio').attr('disabled',true);
		//$('#animalType').combobox('disable');
		$('#dictVisceraAnimal').datagrid('uncheckAll');
		if(selectRow.animalFlag == 1){
			$('#dictVisceraAnimal').datagrid('checkAll');
		}else{
			var animalTypeNames = selectRow.animalTypeNames;
			selectAnimalTypeNames(animalTypeNames);
		}
		
		//$('#visceraAnimalTD :checkbox').attr('disabled',true);
		
		//不显示连续录入复选框
		document.getElementById("continueAddCheckBox").style.display="none";
		document.getElementById("continueAddA").style.display="none";
	}else if(addOrEdit == 'editSon'){
		var selectRow = visceraTable.treegrid('getSelected');
		var parentRow = visceraTable.treegrid('getParent',selectRow.visceraCode);
		document.getElementById('visceraCode').value = selectRow.visceraCode;
		document.getElementById('pvisceraCode').value = selectRow._parentId;
		//
		document.getElementById('pVisceraName').firstChild.nodeValue = parentRow.visceraName;
		//先启用，后禁用再禁用
		$('#visceraType').combobox('enable');
		$(':radio').attr('disabled',false);
		//$('#animalType').combobox('enable');
		
		$('#visceraType').combobox('select',selectRow.visceraType);
		$('#oldVisceraName').val(selectRow.visceraName);
		$('#visceraName').val(selectRow.visceraName);
		$('#py').val(selectRow.py);
		$('#visceraNameJp').val(selectRow.visceraNameJp);
		$('#visceraNameEn').val(selectRow.visceraNameEn);
		document.getElementsByName('gender')[selectRow.gender].click();
		//$('#animalType').combobox('select',selectRow.animalType);
		document.getElementsByName('isPart')[selectRow.isPart].click();
		
		$('#visceraType').combobox('disable');
		$(':radio').attr('disabled',true);
		//$('#animalType').combobox('disable');
		$('#dictVisceraAnimal').datagrid('uncheckAll');
		if(selectRow.animalFlag == 1){
			$('#dictVisceraAnimal').datagrid('checkAll');
		}else{
			var animalTypeNames = selectRow.animalTypeNames;
			selectAnimalTypeNames(animalTypeNames);
		}
		//$('#visceraAnimalTD :checkbox').attr('disabled',true);
		
		//不显示连续录入复选框
		document.getElementById("continueAddCheckBox").style.display="none";
		document.getElementById("continueAddA").style.display="none";
	}
}

/**选择指定动物s
 * @param animalTypeNames
 * @return
 */
function selectAnimalTypeNames(animalTypeNames){
	var animalTypeArray = animalTypeNames.split(',');
	if(animalTypeArray.length > 0){
		for(var i = 0;i<animalTypeArray.length;i++){
			$('#dictVisceraAnimal').datagrid('selectRecord',animalTypeArray[i]);
		}
	}
}

/**
 * 确定
 * @return
 */
function onConfirmButton(){
	//确定   设为不可用
	//$('#confirmButton').linkbutton('disable');
	if($('#visceraAddEditForm').form('validate')){
		var addOrEdit = $('#visceraAddOrEdit').val();
		//新建
		if(addOrEdit.trim() == "addOne"){
			var selectedRows = $('#dictVisceraAnimal').datagrid('getSelections');
			if(selectedRows.length <1){
				$.messager.alert('警告','请选择所属动物！');    
			}else{
				var animaTypeNameArray = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].typeName;
					animaTypeNameArray = animaTypeNameArray.concat(userName);
				}
				var animaTypeNames = animaTypeNameArray.join(",");
				
				$.ajax({
					url:sybp()+'/dictVisceraAction_addOne.action?animaTypeNames='+encodeURIComponent(animaTypeNames),
					type:'post',
					data:$('#visceraAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#confirmButton').linkbutton('enable');
						if(r && r.success){
							//刷新treegrid
							parent.showMessager(1,'脏器新建成功!',true,5000);
							$('#selectId').val(r.msg);
							visceraTable.treegrid('reload');
							$('#visceraAddEditDialog').dialog('close');
							if(document.getElementById("continueAddCheckBox").checked){
								showVisceraAddEditDialog('addOne','新建');
								document.getElementById("continueAddCheckBox").checked = true;
							}
						}else{
							$.messager.alert('警告','参数交互错误!');
						}
					}
				});
			}
		}else if(addOrEdit.trim() == "editOne"){
			var selectedRows = $('#dictVisceraAnimal').datagrid('getSelections');
			if(selectedRows.length <1){
				$.messager.alert('警告','请选择所属动物！');    
			}else{
				var animaTypeNameArray = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].typeName;
					animaTypeNameArray = animaTypeNameArray.concat(userName);
				}
				var animaTypeNames = animaTypeNameArray.join(",");
				$.ajax({
					url:sybp()+'/dictVisceraAction_editOne.action?animaTypeNames='+encodeURIComponent(animaTypeNames),
					type:'post',
					data:$('#visceraAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#confirmButton').linkbutton('enable');
						if(r && r.success){
							//刷新treegrid
							parent.showMessager(1,'脏器编辑成功!',true,5000);
							$('#selectId').val(r.msg);
							visceraTable.treegrid('reload');
							$('#visceraAddEditDialog').dialog('close');
						}else{
							$.messager.alert('警告','参数交互错误!');
						}
					}
				});
			}
		}else if(addOrEdit.trim() == "addSon"){
			var selectedRows = $('#dictVisceraAnimal').datagrid('getSelections');
			if(selectedRows.length <1){
				$.messager.alert('警告','请选择所属动物！');    
			}else{
				var animaTypeNameArray = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].typeName;
					animaTypeNameArray = animaTypeNameArray.concat(userName);
				}
				var animaTypeNames = animaTypeNameArray.join(",");
				$.ajax({
					url:sybp()+'/dictVisceraAction_addSon.action?animaTypeNames='+encodeURIComponent(animaTypeNames),
					type:'post',
					data:$('#visceraAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#confirmButton').linkbutton('enable');
						if(r && r.success){
							//刷新treegrid
							parent.showMessager(1,'脏器新建成功!',true,5000);
							$('#selectId').val(r.msg);
							visceraTable.treegrid('reload');
							$('#visceraAddEditDialog').dialog('close');
						}else{
							$.messager.alert('警告','参数交互错误!');
						}
					}
				});
			}
		}else if(addOrEdit.trim() == "editSon"){
			var selectedRows = $('#dictVisceraAnimal').datagrid('getSelections');
			if(selectedRows.length <1){
				$.messager.alert('警告','请选择所属动物！');    
			}else{
				var animaTypeNameArray = new Array();
				for(var i =0;i<selectedRows.length;i++){
					var userName = selectedRows[i].typeName;
					animaTypeNameArray = animaTypeNameArray.concat(userName);
				}
				var animaTypeNames = animaTypeNameArray.join(",");
				$.ajax({
					url:sybp()+'/dictVisceraAction_editSon.action?animaTypeNames='+encodeURIComponent(animaTypeNames),
					type:'post',
					data:$('#visceraAddEditForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#confirmButton').linkbutton('enable');
						if(r && r.success){
							//刷新treegrid
							parent.showMessager(1,'脏器编辑成功!',true,5000);
							$('#selectId').val(r.msg);
							visceraTable.treegrid('reload');
							$('#visceraAddEditDialog').dialog('close');
						}else{
							$.messager.alert('警告','参数交互错误!');
						}
					}
				});
			}
		}
	}
	//确定   设为可用
	//$('#confirmButton').linkbutton('enable');
}

/**
 * 获得拼音首字母
 * @param str
 * @return
 */
function getPYCode(str)  
{  
     var result = "";  
     
      for(var i=0;i<str.length;i++)  
      {  
    	 alert(str.charAt(i).toString());
         result += getPY(str.charAt(i).toString());  
         alert(result);
      }  
      return result;  
}
/**
 * 获得单个汉字 拼音首字母
 * @param s
 * @return
 */
function getPY(s)  
{  
     if(s !="")  { 
    	//兼容性有问题
    	window.execScript("tmp=asc(\""+s+"\")", "vbscript");
         tmp = 65536 + tmp;  
         var py = "";  
         if(tmp>=45217 && tmp<=45252) {     
             py = "A"  
         } else if(tmp>=45253 && tmp<=45760) {  
             py = "B"  
         } else if(tmp>=45761 && tmp<=46317) {  
             py = "C"  
         } else if(tmp>=46318 && tmp<=46825) {  
             py = "D"  
         } else if(tmp>=46826 && tmp<=47009) {  
             py = "E"  
         } else if(tmp>=47010 && tmp<=47296) {  
             py = "F"  
         } else if((tmp>=47297 && tmp<=47613) || (tmp == 63193)) {  
             py = "G"  
         } else if(tmp>=47614 && tmp<=48118) {  
             py = "H"  
         } else if(tmp>=48119 && tmp<=49061) {  
             py = "J"  
         } else if(tmp>=49062 && tmp<=49323) {  
             py = "K"  
         } else if(tmp>=49324 && tmp<=49895) {  
             py = "L"  
         } else if(tmp>=49896 && tmp<=50370) {  
             py = "M"  
         } else if(tmp>=50371 && tmp<=50613) {  
             py = "N"  
         } else if(tmp>=50614 && tmp<=50621) {  
             py = "O"  
         } else if(tmp>=50622 && tmp<=50905) {  
             py = "P"  
         } else if(tmp>=50906 && tmp<=51386) {  
             py = "Q"  
         } else if(tmp>=51387 && tmp<=51445) {  
             py = "R"  
         } else if(tmp>=51446 && tmp<=52217) {  
             py = "S"  
         } else if(tmp>=52218 && tmp<=52697) {  
             py = "T"  
         } else if(tmp>=52698 && tmp<=52979) {  
             py = "W"  
         } else if(tmp>=52980 && tmp<=53688) {  
             py = "X"  
         } else if(tmp>=53689 && tmp<=54480) {  
             py = "Y"  
         } else if(tmp>=54481 && tmp<=62289) {  
             py = "Z"  
         } else {  
             py =s.charAt(0);  
         }  
         return py;  
    }else{
    	return '';
    } 
}   

//汉字拼音首字母列表 本列表包含了20902个汉字,用于配合 ToChineseSpell
//函数使用,本表收录的字符的Unicode编码范围为19968至40869, XDesigner 整理
var strChineseFirstPY ="YDYQSXMWZSSXJBYMGCCZQPSSQBYCDSCDQLDYLYBSSJGYZZJJFKCCLZDHWDWZJLJPFYYNWJJTMYHZWZHFLZPPQHGSCYYYNJQYXXGJHHSDSJNKKTMOMLCRXYPSNQSECCQZGGLLYJLMYZZSECYKYYHQWJSSGGYXYZYJWWKDJHYCHMYXJTLXJYQBYXZLDWRDJRWYSRLDZJPCBZJJBRCFTLECZSTZFXXZHTRQHYBDLYCZSSYMMRFMYQZPWWJJYFCRWFDFZQPYDDWYXKYJAWJFFXYPSFTZYHHYZYSWCJYXSCLCXXWZZXNBGNNXBXLZSZSBSGPYSYZDHMDZBQBZCWDZZYYTZHBTSYYBZGNTNXQYWQSKBPHHLXGYBFMJEBJHHGQTJCYSXSTKZHLYCKGLYSMZXYALMELDCCXGZYRJXSDLTYZCQKCNNJWHJTZZCQLJSTSTBNXBTYXCEQXGKWJYFLZQLYHYXSPSFXLMPBYSXXXYDJCZYLLLSJXFHJXPJBTFFYABYXBHZZBJYZLWLCZGGBTSSMDTJZXPTHYQTGLJSCQFZKJZJQNLZWLSLHDZBWJNCJZYZSQQYCQYRZCJJWYBRTWPYFTWEXCSKDZCTBZHYZZYYJXZCFFZZMJYXXSDZZOTTBZLQWFCKSZSXFYRLNYJMBDTHJXSQQCCSBXYYTSYFBXDZTGBCNSLCYZZPSAZYZZSCJCSHZQYDXLBPJLLMQXTYDZXSQJTZPXLCGLQTZWJBHCTSYJSFXYEJJTLBGXSXJMYJQQPFZASYJNTYDJXKJCDJSZCBARTDCLYJQMWNQNCLLLKBYBZZSYHQQLTWLCCXTXLLZNTYLNEWYZYXCZXXGRKRMTCNDNJTSYYSSDQDGHSDBJGHRWRQLYBGLXHLGTGXBQJDZPYJSJYJCTMRNYMGRZJCZGJMZMGXMPRYXKJNYMSGMZJYMKMFXMLDTGFBHCJHKYLPFMDXLQJJSMTQGZSJLQDLDGJYCALCMZCSDJLLNXDJFFFFJCZFMZFFPFKHKGDPSXKTACJDHHZDDCRRCFQYJKQCCWJDXHWJLYLLZGCFCQDSMLZPBJJPLSBCJGGDCKKDEZSQCCKJGCGKDJTJDLZYCXKLQSCGJCLTFPCQCZGWPJDQYZJJBYJHSJDZWGFSJGZKQCCZLLPSPKJGQJHZZLJPLGJGJJTHJJYJZCZMLZLYQBGJWMLJKXZDZNJQSYZMLJLLJKYWXMKJLHSKJGBMCLYYMKXJQLBMLLKMDXXKWYXYSLMLPSJQQJQXYXFJTJDXMXXLLCXQBSYJBGWYMBGGBCYXPJYGPEPFGDJGBHBNSQJYZJKJKHXQFGQZKFHYGKHDKLLSDJQXPQYKYBNQSXQNSZSWHBSXWHXWBZZXDMNSJBSBKBBZKLYLXGWXDRWYQZMYWSJQLCJXXJXKJEQXSCYETLZHLYYYSDZPAQYZCMTLSHTZCFYZYXYLJSDCJQAGYSLCQLYYYSHMRQQKLDXZSCSSSYDYCJYSFSJBFRSSZQSBXXPXJYSDRCKGJLGDKZJZBDKTCSYQPYHSTCLDJDHMXMCGXYZHJDDTMHLTXZXYLYMOHYJCLTYFBQQXPFBDFHHTKSQHZYYWCNXXCRWHOWGYJLEGWDQCWGFJYCSNTMYTOLBYGWQWESJPWNMLRYDZSZTXYQPZGCWXHNGPYXSHMYQJXZTDPPBFYHZHTJYFDZWKGKZBLDNTSXHQEEGZZYLZMMZYJZGXZXKHKSTXNXXWYLYAPSTHXDWHZYMPXAGKYDXBHNHXKDPJNMYHYLPMGOCSLNZHKXXLPZZLBMLSFBHHGYGYYGGBHSCYAQTYWLXTZQCEZYDQDQMMHTKLLSZHLSJZWFYHQSWSCWLQAZYNYTLSXTHAZNKZZSZZLAXXZWWCTGQQTDDYZTCCHYQZFLXPSLZYGPZSZNGLNDQTBDLXGTCTAJDKYWNSYZLJHHZZCWNYYZYWMHYCHHYXHJKZWSXHZYXLYSKQYSPSLYZWMYPPKBYGLKZHTYXAXQSYSHXASMCHKDSCRSWJPWXSGZJLWWSCHSJHSQNHCSEGNDAQTBAALZZMSSTDQJCJKTSCJAXPLGGXHHGXXZCXPDMMHLDGTYBYSJMXHMRCPXXJZCKZXSHMLQXXTTHXWZFKHCCZDYTCJYXQHLXDHYPJQXYLSYYDZOZJNYXQEZYSQYAYXWYPDGXDDXSPPYZNDLTWRHXYDXZZJHTCXMCZLHPYYYYMHZLLHNXMYLLLMDCPPXHMXDKYCYRDLTXJCHHZZXZLCCLYLNZSHZJZZLNNRLWHYQSNJHXYNTTTKYJPYCHHYEGKCTTWLGQRLGGTGTYGYHPYHYLQYQGCWYQKPYYYTTTTLHYHLLTYTTSPLKYZXGZWGPYDSSZZDQXSKCQNMJJZZBXYQMJRTFFBTKHZKBXLJJKDXJTLBWFZPPTKQTZTGPDGNTPJYFALQMKGXBDCLZFHZCLLLLADPMXDJHLCCLGYHDZFGYDDGCYYFGYDXKSSEBDHYKDKDKHNAXXYBPBYYHXZQGAFFQYJXDMLJCSQZLLPCHBSXGJYNDYBYQSPZWJLZKSDDTACTBXZDYZYPJZQSJNKKTKNJDJGYYPGTLFYQKASDNTCYHBLWDZHBBYDWJRYGKZYHEYYFJMSDTYFZJJHGCXPLXHLDWXXJKYTCYKSSSMTWCTTQZLPBSZDZWZXGZAGYKTYWXLHLSPBCLLOQMMZSSLCMBJCSZZKYDCZJGQQDSMCYTZQQLWZQZXSSFPTTFQMDDZDSHDTDWFHTDYZJYQJQKYPBDJYYXTLJHDRQXXXHAYDHRJLKLYTWHLLRLLRCXYLBWSRSZZSYMKZZHHKYHXKSMDSYDYCJPBZBSQLFCXXXNXKXWYWSDZYQOGGQMMYHCDZTTFJYYBGSTTTYBYKJDHKYXBELHTYPJQNFXFDYKZHQKZBYJTZBXHFDXKDASWTAWAJLDYJSFHBLDNNTNQJTJNCHXFJSRFWHZFMDRYJYJWZPDJKZYJYMPCYZNYNXFBYTFYFWYGDBNZZZDNYTXZEMMQBSQEHXFZMBMFLZZSRXYMJGSXWZJSPRYDJSJGXHJJGLJJYNZZJXHGXKYMLPYYYCXYTWQZSWHWLYRJLPXSLSXMFSWWKLCTNXNYNPSJSZHDZEPTXMYYWXYYSYWLXJQZQXZDCLEEELMCPJPCLWBXSQHFWWTFFJTNQJHJQDXHWLBYZNFJLALKYYJLDXHHYCSTYYWNRJYXYWTRMDRQHWQCMFJDYZMHMYYXJWMYZQZXTLMRSPWWCHAQBXYGZYPXYYRRCLMPYMGKSJSZYSRMYJSNXTPLNBAPPYPYLXYYZKYNLDZYJZCZNNLMZHHARQMPGWQTZMXXMLLHGDZXYHXKYXYCJMFFYYHJFSBSSQLXXNDYCANNMTCJCYPRRNYTYQNYYMBMSXNDLYLYSLJRLXYSXQMLLYZLZJJJKYZZCSFBZXXMSTBJGNXYZHLXNMCWSCYZYFZLXBRNNNYLBNRTGZQYSATSWRYHYJZMZDHZGZDWYBSSCSKXSYHYTXXGCQGXZZSHYXJSCRHMKKBXCZJYJYMKQHZJFNBHMQHYSNJNZYBKNQMCLGQHWLZNZSWXKHLJHYYBQLBFCDSXDLDSPFZPSKJYZWZXZDDXJSMMEGJSCSSMGCLXXKYYYLNYPWWWGYDKZJGGGZGGSYCKNJWNJPCXBJJTQTJWDSSPJXZXNZXUMELPXFSXTLLXCLJXJJLJZXCTPSWXLYDHLYQRWHSYCSQYYBYAYWJJJQFWQCQQCJQGXALDBZZYJGKGXPLTZYFXJLTPADKYQHPMATLCPDCKBMTXYBHKLENXDLEEGQDYMSAWHZMLJTWYGXLYQZLJEEYYBQQFFNLYXRDSCTGJGXYYNKLLYQKCCTLHJLQMKKZGCYYGLLLJDZGYDHZWXPYSJBZKDZGYZZHYWYFQYTYZSZYEZZLYMHJJHTSMQWYZLKYYWZCSRKQYTLTDXWCTYJKLWSQZWBDCQYNCJSRSZJLKCDCDTLZZZACQQZZDDXYPLXZBQJYLZLLLQDDZQJYJYJZYXNYYYNYJXKXDAZWYRDLJYYYRJLXLLDYXJCYWYWNQCCLDDNYYYNYCKCZHXXCCLGZQJGKWPPCQQJYSBZZXYJSQPXJPZBSBDSFNSFPZXHDWZTDWPPTFLZZBZDMYYPQJRSDZSQZSQXBDGCPZSWDWCSQZGMDHZXMWWFYBPDGPHTMJTHZSMMBGZMBZJCFZWFZBBZMQCFMBDMCJXLGPNJBBXGYHYYJGPTZGZMQBQTCGYXJXLWZKYDPDYMGCFTPFXYZTZXDZXTGKMTYBBCLBJASKYTSSQYYMSZXFJEWLXLLSZBQJJJAKLYLXLYCCTSXMCWFKKKBSXLLLLJYXTYLTJYYTDPJHNHNNKBYQNFQYYZBYYESSESSGDYHFHWTCJBSDZZTFDMXHCNJZYMQWSRYJDZJQPDQBBSTJGGFBKJBXTGQHNGWJXJGDLLTHZHHYYYYYYSXWTYYYCCBDBPYPZYCCZYJPZYWCBDLFWZCWJDXXHYHLHWZZXJTCZLCDPXUJCZZZLYXJJTXPHFXWPYWXZPTDZZBDZCYHJHMLXBQXSBYLRDTGJRRCTTTHYTCZWMXFYTWWZCWJWXJYWCSKYBZSCCTZQNHXNWXXKHKFHTSWOCCJYBCMPZZYKBNNZPBZHHZDLSYDDYTYFJPXYNGFXBYQXCBHXCPSXTYZDMKYSNXSXLHKMZXLYHDHKWHXXSSKQYHHCJYXGLHZXCSNHEKDTGZXQYPKDHEXTYKCNYMYYYPKQYYYKXZLTHJQTBYQHXBMYHSQCKWWYLLHCYYLNNEQXQWMCFBDCCMLJGGXDQKTLXKGNQCDGZJWYJJLYHHQTTTNWCHMXCXWHWSZJYDJCCDBQCDGDNYXZTHCQRXCBHZTQCBXWGQWYYBXHMBYMYQTYEXMQKYAQYRGYZSLFYKKQHYSSQYSHJGJCNXKZYCXSBXYXHYYLSTYCXQTHYSMGSCPMMGCCCCCMTZTASMGQZJHKLOSQYLSWTMXSYQKDZLJQQYPLSYCZTCQQPBBQJZCLPKHQZYYXXDTDDTSJCXFFLLCHQXMJLWCJCXTSPYCXNDTJSHJWXDQQJSKXYAMYLSJHMLALYKXCYYDMNMDQMXMCZNNCYBZKKYFLMCHCMLHXRCJJHSYLNMTJZGZGYWJXSRXCWJGJQHQZDQJDCJJZKJKGDZQGJJYJYLXZXXCDQHHHEYTMHLFSBDJSYYSHFYSTCZQLPBDRFRZTZYKYWHSZYQKWDQZRKMSYNBCRXQBJYFAZPZZEDZCJYWBCJWHYJBQSZYWRYSZPTDKZPFPBNZTKLQYHBBZPNPPTYZZYBQNYDCPJMMCYCQMCYFZZDCMNLFPBPLNGQJTBTTNJZPZBBZNJKLJQYLNBZQHKSJZNGGQSZZKYXSHPZSNBCGZKDDZQANZHJKDRTLZLSWJLJZLYWTJNDJZJHXYAYNCBGTZCSSQMNJPJYTYSWXZFKWJQTKHTZPLBHSNJZSYZBWZZZZLSYLSBJHDWWQPSLMMFBJDWAQYZTCJTBNNWZXQXCDSLQGDSDPDZHJTQQPSWLYYJZLGYXYZLCTCBJTKTYCZJTQKBSJLGMGZDMCSGPYNJZYQYYKNXRPWSZXMTNCSZZYXYBYHYZAXYWQCJTLLCKJJTJHGDXDXYQYZZBYWDLWQCGLZGJGQRQZCZSSBCRPCSKYDZNXJSQGXSSJMYDNSTZTPBDLTKZWXQWQTZEXNQCZGWEZKSSBYBRTSSSLCCGBPSZQSZLCCGLLLZXHZQTHCZMQGYZQZNMCOCSZJMMZSQPJYGQLJYJPPLDXRGZYXCCSXHSHGTZNLZWZKJCXTCFCJXLBMQBCZZWPQDNHXLJCTHYZLGYLNLSZZPCXDSCQQHJQKSXZPBAJYEMSMJTZDXLCJYRYYNWJBNGZZTMJXLTBSLYRZPYLSSCNXPHLLHYLLQQZQLXYMRSYCXZLMMCZLTZSDWTJJLLNZGGQXPFSKYGYGHBFZPDKMWGHCXMSGDXJMCJZDYCABXJDLNBCDQYGSKYDQTXDJJYXMSZQAZDZFSLQXYJSJZYLBTXXWXQQZBJZUFBBLYLWDSLJHXJYZJWTDJCZFQZQZZDZSXZZQLZCDZFJHYSPYMPQZMLPPLFFXJJNZZYLSJEYQZFPFZKSYWJJJHRDJZZXTXXGLGHYDXCSKYSWMMZCWYBAZBJKSHFHJCXMHFQHYXXYZFTSJYZFXYXPZLCHMZMBXHZZSXYFYMNCWDABAZLXKTCSHHXKXJJZJSTHYGXSXYYHHHJWXKZXSSBZZWHHHCWTZZZPJXSNXQQJGZYZYWLLCWXZFXXYXYHXMKYYSWSQMNLNAYCYSPMJKHWCQHYLAJJMZXHMMCNZHBHXCLXTJPLTXYJHDYYLTTXFSZHYXXSJBJYAYRSMXYPLCKDUYHLXRLNLLSTYZYYQYGYHHSCCSMZCTZQXKYQFPYYRPFFLKQUNTSZLLZMWWTCQQYZWTLLMLMPWMBZSSTZRBPDDTLQJJBXZCSRZQQYGWCSXFWZLXCCRSZDZMCYGGDZQSGTJSWLJMYMMZYHFBJDGYXCCPSHXNZCSBSJYJGJMPPWAFFYFNXHYZXZYLREMZGZCYZSSZDLLJCSQFNXZKPTXZGXJJGFMYYYSNBTYLBNLHPFZDCYFBMGQRRSSSZXYSGTZRNYDZZCDGPJAFJFZKNZBLCZSZPSGCYCJSZLMLRSZBZZLDLSLLYSXSQZQLYXZLSKKBRXBRBZCYCXZZZEEYFGKLZLYYHGZSGZLFJHGTGWKRAAJYZKZQTSSHJJXDCYZUYJLZYRZDQQHGJZXSSZBYKJPBFRTJXLLFQWJHYLQTYMBLPZDXTZYGBDHZZRBGXHWNJTJXLKSCFSMWLSDQYSJTXKZSCFWJLBXFTZLLJZLLQBLSQMQQCGCZFPBPHZCZJLPYYGGDTGWDCFCZQYYYQYSSCLXZSKLZZZGFFCQNWGLHQYZJJCZLQZZYJPJZZBPDCCMHJGXDQDGDLZQMFGPSYTSDYFWWDJZJYSXYYCZCYHZWPBYKXRYLYBHKJKSFXTZJMMCKHLLTNYYMSYXYZPYJQYCSYCWMTJJKQYRHLLQXPSGTLYYCLJSCPXJYZFNMLRGJJTYZBXYZMSJYJHHFZQMSYXRSZCWTLRTQZSSTKXGQKGSPTGCZNJSJCQCXHMXGGZTQYDJKZDLBZSXJLHYQGGGTHQSZPYHJHHGYYGKGGCWJZZYLCZLXQSFTGZSLLLMLJSKCTBLLZZSZMMNYTPZSXQHJCJYQXYZXZQZCPSHKZZYSXCDFGMWQRLLQXRFZTLYSTCTMJCXJJXHJNXTNRZTZFQYHQGLLGCXSZSJDJLJCYDSJTLNYXHSZXCGJZYQPYLFHDJSBPCCZHJJJQZJQDYBSSLLCMYTTMQTBHJQNNYGKYRQYQMZGCJKPDCGMYZHQLLSLLCLMHOLZGDYYFZSLJCQZLYLZQJESHNYLLJXGJXLYSYYYXNBZLJSSZCQQCJYLLZLTJYLLZLLBNYLGQCHXYYXOXCXQKYJXXXYKLXSXXYQXCYKQXQCSGYXXYQXYGYTQOHXHXPYXXXULCYEYCHZZCBWQBBWJQZSCSZSSLZYLKDESJZWMYMCYTSDSXXSCJPQQSQYLYYZYCMDJDZYWCBTJSYDJKCYDDJLBDJJSODZYSYXQQYXDHHGQQYQHDYXWGMMMAJDYBBBPPBCMUUPLJZSMTXERXJMHQNUTPJDCBSSMSSSTKJTSSMMTRCPLZSZMLQDSDMJMQPNQDXCFYNBFSDQXYXHYAYKQYDDLQYYYSSZBYDSLNTFQTZQPZMCHDHCZCWFDXTMYQSPHQYYXSRGJCWTJTZZQMGWJJTJHTQJBBHWZPXXHYQFXXQYWYYHYSCDYDHHQMNMTMWCPBSZPPZZGLMZFOLLCFWHMMSJZTTDHZZYFFYTZZGZYSKYJXQYJZQBHMBZZLYGHGFMSHPZFZSNCLPBQSNJXZSLXXFPMTYJYGBXLLDLXPZJYZJYHHZCYWHJYLSJEXFSZZYWXKZJLUYDTMLYMQJPWXYHXSKTQJEZRPXXZHHMHWQPWQLYJJQJJZSZCPHJLCHHNXJLQWZJHBMZYXBDHHYPZLHLHLGFWLCHYYTLHJXCJMSCPXSTKPNHQXSRTYXXTESYJCTLSSLSTDLLLWWYHDHRJZSFGXTSYCZYNYHTDHWJSLHTZDQDJZXXQHGYLTZPHCSQFCLNJTCLZPFSTPDYNYLGMJLLYCQHYSSHCHYLHQYQTMZYPBYWRFQYKQSYSLZDQJMPXYYSSRHZJNYWTQDFZBWWTWWRXCWHGYHXMKMYYYQMSMZHNGCEPMLQQMTCWCTMMPXJPJJHFXYYZSXZHTYBMSTSYJTTQQQYYLHYNPYQZLCYZHZWSMYLKFJXLWGXYPJYTYSYXYMZCKTTWLKSMZSYLMPWLZWXWQZSSAQSYXYRHSSNTSRAPXCPWCMGDXHXZDZYFJHGZTTSBJHGYZSZYSMYCLLLXBTYXHBBZJKSSDMALXHYCFYGMQYPJYCQXJLLLJGSLZGQLYCJCCZOTYXMTMTTLLWTGPXYMZMKLPSZZZXHKQYSXCTYJZYHXSHYXZKXLZWPSQPYHJWPJPWXQQYLXSDHMRSLZZYZWTTCYXYSZZSHBSCCSTPLWSSCJCHNLCGCHSSPHYLHFHHXJSXYLLNYLSZDHZXYLSXLWZYKCLDYAXZCMDDYSPJTQJZLNWQPSSSWCTSTSZLBLNXSMNYYMJQBQHRZWTYYDCHQLXKPZWBGQYBKFCMZWPZLLYYLSZYDWHXPSBCMLJBSCGBHXLQHYRLJXYSWXWXZSLDFHLSLYNJLZYFLYJYCDRJLFSYZFSLLCQYQFGJYHYXZLYLMSTDJCYHBZLLNWLXXYGYYHSMGDHXXHHLZZJZXCZZZCYQZFNGWPYLCPKPYYPMCLQKDGXZGGWQBDXZZKZFBXXLZXJTPJPTTBYTSZZDWSLCHZHSLTYXHQLHYXXXYYZYSWTXZKHLXZXZPYHGCHKCFSYHUTJRLXFJXPTZTWHPLYXFCRHXSHXKYXXYHZQDXQWULHYHMJTBFLKHTXCWHJFWJCFPQRYQXCYYYQYGRPYWSGSUNGWCHKZDXYFLXXHJJBYZWTSXXNCYJJYMSWZJQRMHXZWFQSYLZJZGBHYNSLBGTTCSYBYXXWXYHXYYXNSQYXMQYWRGYQLXBBZLJSYLPSYTJZYHYZAWLRORJMKSCZJXXXYXCHDYXRYXXJDTSQFXLYLTSFFYXLMTYJMJUYYYXLTZCSXQZQHZXLYYXZHDNBRXXXJCTYHLBRLMBRLLAXKYLLLJLYXXLYCRYLCJTGJCMTLZLLCYZZPZPCYAWHJJFYBDYYZSMPCKZDQYQPBPCJPDCYZMDPBCYYDYCNNPLMTMLRMFMMGWYZBSJGYGSMZQQQZTXMKQWGXLLPJGZBQCDJJJFPKJKCXBLJMSWMDTQJXLDLPPBXCWRCQFBFQJCZAHZGMYKPHYYHZYKNDKZMBPJYXPXYHLFPNYYGXJDBKXNXHJMZJXSTRSTLDXSKZYSYBZXJLXYSLBZYSLHXJPFXPQNBYLLJQKYGZMCYZZYMCCSLCLHZFWFWYXZMWSXTYNXJHPYYMCYSPMHYSMYDYSHQYZCHMJJMZCAAGCFJBBHPLYZYLXXSDJGXDHKXXTXXNBHRMLYJSLTXMRHNLXQJXYZLLYSWQGDLBJHDCGJYQYCMHWFMJYBMBYJYJWYMDPWHXQLDYGPDFXXBCGJSPCKRSSYZJMSLBZZJFLJJJLGXZGYXYXLSZQYXBEXYXHGCXBPLDYHWETTWWCJMBTXCHXYQXLLXFLYXLLJLSSFWDPZSMYJCLMWYTCZPCHQEKCQBWLCQYDPLQPPQZQFJQDJHYMMCXTXDRMJWRHXCJZYLQXDYYNHYYHRSLSRSYWWZJYMTLTLLGTQCJZYABTCKZCJYCCQLJZQXALMZYHYWLWDXZXQDLLQSHGPJFJLJHJABCQZDJGTKHSSTCYJLPSWZLXZXRWGLDLZRLZXTGSLLLLZLYXXWGDZYGBDPHZPBRLWSXQBPFDWOFMWHLYPCBJCCLDMBZPBZZLCYQXLDOMZBLZWPDWYYGDSTTHCSQSCCRSSSYSLFYBFNTYJSZDFNDPDHDZZMBBLSLCMYFFGTJJQWFTMTPJWFNLBZCMMJTGBDZLQLPYFHYYMJYLSDCHDZJWJCCTLJCLDTLJJCPDDSQDSSZYBNDBJLGGJZXSXNLYCYBJXQYCBYLZCFZPPGKCXZDZFZTJJFJSJXZBNZYJQTTYJYHTYCZHYMDJXTTMPXSPLZCDWSLSHXYPZGTFMLCJTYCBPMGDKWYCYZCDSZZYHFLYCTYGWHKJYYLSJCXGYWJCBLLCSNDDBTZBSCLYZCZZSSQDLLMQYYHFSLQLLXFTYHABXGWNYWYYPLLSDLDLLBJCYXJZMLHLJDXYYQYTDLLLBUGBFDFBBQJZZMDPJHGCLGMJJPGAEHHBWCQXAXHHHZCHXYPHJAXHLPHJPGPZJQCQZGJJZZUZDMQYYBZZPHYHYBWHAZYJHYKFGDPFQSDLZMLJXKXGALXZDAGLMDGXMWZQYXXDXXPFDMMSSYMPFMDMMKXKSYZYSHDZKXSYSMMZZZMSYDNZZCZXFPLSTMZDNMXCKJMZTYYMZMZZMSXHHDCZJEMXXKLJSTLWLSQLYJZLLZJSSDPPMHNLZJCZYHMXXHGZCJMDHXTKGRMXFWMCGMWKDTKSXQMMMFZZYDKMSCLCMPCGMHSPXQPZDSSLCXKYXTWLWJYAHZJGZQMCSNXYYMMPMLKJXMHLMLQMXCTKZMJQYSZJSYSZHSYJZJCDAJZYBSDQJZGWZQQXFKDMSDJLFWEHKZQKJPEYPZYSZCDWYJFFMZZYLTTDZZEFMZLBNPPLPLPEPSZALLTYLKCKQZKGENQLWAGYXYDPXLHSXQQWQCQXQCLHYXXMLYCCWLYMQYSKGCHLCJNSZKPYZKCQZQLJPDMDZHLASXLBYDWQLWDNBQCRYDDZTJYBKBWSZDXDTNPJDTCTQDFXQQMGNXECLTTBKPWSLCTYQLPWYZZKLPYGZCQQPLLKCCYLPQMZCZQCLJSLQZDJXLDDHPZQDLJJXZQDXYZQKZLJCYQDYJPPYPQYKJYRMPCBYMCXKLLZLLFQPYLLLMBSGLCYSSLRSYSQTMXYXZQZFDZUYSYZTFFMZZSMZQHZSSCCMLYXWTPZGXZJGZGSJSGKDDHTQGGZLLBJDZLCBCHYXYZHZFYWXYZYMSDBZZYJGTSMTFXQYXQSTDGSLNXDLRYZZLRYYLXQHTXSRTZNGZXBNQQZFMYKMZJBZYMKBPNLYZPBLMCNQYZZZSJZHJCTZKHYZZJRDYZHNPXGLFZTLKGJTCTSSYLLGZRZBBQZZKLPKLCZYSSUYXBJFPNJZZXCDWXZYJXZZDJJKGGRSRJKMSMZJLSJYWQSKYHQJSXPJZZZLSNSHRNYPZTWCHKLPSRZLZXYJQXQKYSJYCZTLQZYBBYBWZPQDWWYZCYTJCJXCKCWDKKZXSGKDZXWWYYJQYYTCYTDLLXWKCZKKLCCLZCQQDZLQLCSFQCHQHSFSMQZZLNBJJZBSJHTSZDYSJQJPDLZCDCWJKJZZLPYCGMZWDJJBSJQZSYZYHHXJPBJYDSSXDZNCGLQMBTSFSBPDZDLZNFGFJGFSMPXJQLMBLGQCYYXBQKDJJQYRFKZTJDHCZKLBSDZCFJTPLLJGXHYXZCSSZZXSTJYGKGCKGYOQXJPLZPBPGTGYJZGHZQZZLBJLSQFZGKQQJZGYCZBZQTLDXRJXBSXXPZXHYZYCLWDXJJHXMFDZPFZHQHQMQGKSLYHTYCGFRZGNQXCLPDLBZCSCZQLLJBLHBZCYPZZPPDYMZZSGYHCKCPZJGSLJLNSCDSLDLXBMSTLDDFJMKDJDHZLZXLSZQPQPGJLLYBDSZGQLBZLSLKYYHZTTNTJYQTZZPSZQZTLLJTYYLLQLLQYZQLBDZLSLYYZYMDFSZSNHLXZNCZQZPBWSKRFBSYZMTHBLGJPMCZZLSTLXSHTCSYZLZBLFEQHLXFLCJLYLJQCBZLZJHHSSTBRMHXZHJZCLXFNBGXGTQJCZTMSFZKJMSSNXLJKBHSJXNTNLZDNTLMSJXGZJYJCZXYJYJWRWWQNZTNFJSZPZSHZJFYRDJSFSZJZBJFZQZZHZLXFYSBZQLZSGYFTZDCSZXZJBQMSZKJRHYJZCKMJKHCHGTXKXQGLXPXFXTRTYLXJXHDTSJXHJZJXZWZLCQSBTXWXGXTXXHXFTSDKFJHZYJFJXRZSDLLLTQSQQZQWZXSYQTWGWBZCGZLLYZBCLMQQTZHZXZXLJFRMYZFLXYSQXXJKXRMQDZDMMYYBSQBHGZMWFWXGMXLZPYYTGZYCCDXYZXYWGSYJYZNBHPZJSQSYXSXRTFYZGRHZTXSZZTHCBFCLSYXZLZQMZLMPLMXZJXSFLBYZMYQHXJSXRXSQZZZSSLYFRCZJRCRXHHZXQYDYHXSJJHZCXZBTYNSYSXJBQLPXZQPYMLXZKYXLXCJLCYSXXZZLXDLLLJJYHZXGYJWKJRWYHCPSGNRZLFZWFZZNSXGXFLZSXZZZBFCSYJDBRJKRDHHGXJLJJTGXJXXSTJTJXLYXQFCSGSWMSBCTLQZZWLZZKXJMLTMJYHSDDBXGZHDLBMYJFRZFSGCLYJBPMLYSMSXLSZJQQHJZFXGFQFQBPXZGYYQXGZTCQWYLTLGWSGWHRLFSFGZJMGMGBGTJFSYZZGZYZAFLSSPMLPFLCWBJZCLJJMZLPJJLYMQDMYYYFBGYGYZMLYZDXQYXRQQQHSYYYQXYLJTYXFSFSLLGNQCYHYCWFHCCCFXPYLYPLLZYXXXXXKQHHXSHJZCFZSCZJXCPZWHHHHHAPYLQALPQAFYHXDYLUKMZQGGGDDESRNNZLTZGCHYPPYSQJJHCLLJTOLNJPZLJLHYMHEYDYDSQYCDDHGZUNDZCLZYZLLZNTNYZGSLHSLPJJBDGWXPCDUTJCKLKCLWKLLCASSTKZZDNQNTTLYYZSSYSSZZRYLJQKCQDHHCRXRZYDGRGCWCGZQFFFPPJFZYNAKRGYWYQPQXXFKJTSZZXSWZDDFBBXTBGTZKZNPZZPZXZPJSZBMQHKCYXYLDKLJNYPKYGHGDZJXXEAHPNZKZTZCMXCXMMJXNKSZQNMNLWBWWXJKYHCPSTMCSQTZJYXTPCTPDTNNPGLLLZSJLSPBLPLQHDTNJNLYYRSZFFJFQWDPHZDWMRZCCLODAXNSSNYZRESTYJWJYJDBCFXNMWTTBYLWSTSZGYBLJPXGLBOCLHPCBJLTMXZLJYLZXCLTPNCLCKXTPZJSWCYXSFYSZDKNTLBYJCYJLLSTGQCBXRYZXBXKLYLHZLQZLNZCXWJZLJZJNCJHXMNZZGJZZXTZJXYCYYCXXJYYXJJXSSSJSTSSTTPPGQTCSXWZDCSYFPTFBFHFBBLZJCLZZDBXGCXLQPXKFZFLSYLTUWBMQJHSZBMDDBCYSCCLDXYCDDQLYJJWMQLLCSGLJJSYFPYYCCYLTJANTJJPWYCMMGQYYSXDXQMZHSZXPFTWWZQSWQRFKJLZJQQYFBRXJHHFWJJZYQAZMYFRHCYYBYQWLPEXCCZSTYRLTTDMQLYKMBBGMYYJPRKZNPBSXYXBHYZDJDNGHPMFSGMWFZMFQMMBCMZZCJJLCNUXYQLMLRYGQZCYXZLWJGCJCGGMCJNFYZZJHYCPRRCMTZQZXHFQGTJXCCJEAQCRJYHPLQLSZDJRBCQHQDYRHYLYXJSYMHZYDWLDFRYHBPYDTSSCNWBXGLPZMLZZTQSSCPJMXXYCSJYTYCGHYCJWYRXXLFEMWJNMKLLSWTXHYYYNCMMCWJDQDJZGLLJWJRKHPZGGFLCCSCZMCBLTBHBQJXQDSPDJZZGKGLFQYWBZYZJLTSTDHQHCTCBCHFLQMPWDSHYYTQWCNZZJTLBYMBPDYYYXSQKXWYYFLXXNCWCXYPMAELYKKJMZZZBRXYYQJFLJPFHHHYTZZXSGQQMHSPGDZQWBWPJHZJDYSCQWZKTXXSQLZYYMYSDZGRXCKKUJLWPYSYSCSYZLRMLQSYLJXBCXTLWDQZPCYCYKPPPNSXFYZJJRCEMHSZMSXLXGLRWGCSTLRSXBZGBZGZTCPLUJLSLYLYMTXMTZPALZXPXJTJWTCYYZLBLXBZLQMYLXPGHDSLSSDMXMBDZZSXWHAMLCZCPJMCNHJYSNSYGCHSKQMZZQDLLKABLWJXSFMOCDXJRRLYQZKJMYBYQLYHETFJZFRFKSRYXFJTWDSXXSYSQJYSLYXWJHSNLXYYXHBHAWHHJZXWMYLJCSSLKYDZTXBZSYFDXGXZJKHSXXYBSSXDPYNZWRPTQZCZENYGCXQFJYKJBZMLJCMQQXUOXSLYXXLYLLJDZBTYMHPFSTTQQWLHOKYBLZZALZXQLHZWRRQHLSTMYPYXJJXMQSJFNBXYXYJXXYQYLTHYLQYFMLKLJTMLLHSZWKZHLJMLHLJKLJSTLQXYLMBHHLNLZXQJHXCFXXLHYHJJGBYZZKBXSCQDJQDSUJZYYHZHHMGSXCSYMXFEBCQWWRBPYYJQTYZCYQYQQZYHMWFFHGZFRJFCDPXNTQYZPDYKHJLFRZXPPXZDBBGZQSTLGDGYLCQMLCHHMFYWLZYXKJLYPQHSYWMQQGQZMLZJNSQXJQSYJYCBEHSXFSZPXZWFLLBCYYJDYTDTHWZSFJMQQYJLMQXXLLDTTKHHYBFPWTYYSQQWNQWLGWDEBZWCMYGCULKJXTMXMYJSXHYBRWFYMWFRXYQMXYSZTZZTFYKMLDHQDXWYYNLCRYJBLPSXCXYWLSPRRJWXHQYPHTYDNXHHMMYWYTZCSQMTSSCCDALWZTCPQPYJLLQZYJSWXMZZMMYLMXCLMXCZMXMZSQTZPPQQBLPGXQZHFLJJHYTJSRXWZXSCCDLXTYJDCQJXSLQYCLZXLZZXMXQRJMHRHZJBHMFLJLMLCLQNLDXZLLLPYPSYJYSXCQQDCMQJZZXHNPNXZMEKMXHYKYQLXSXTXJYYHWDCWDZHQYYBGYBCYSCFGPSJNZDYZZJZXRZRQJJYMCANYRJTLDPPYZBSTJKXXZYPFDWFGZZRPYMTNGXZQBYXNBUFNQKRJQZMJEGRZGYCLKXZDSKKNSXKCLJSPJYYZLQQJYBZSSQLLLKJXTBKTYLCCDDBLSPPFYLGYDTZJYQGGKQTTFZXBDKTYYHYBBFYTYYBCLPDYTGDHRYRNJSPTCSNYJQHKLLLZSLYDXXWBCJQSPXBPJZJCJDZFFXXBRMLAZHCSNDLBJDSZBLPRZTSWSBXBCLLXXLZDJZSJPYLYXXYFTFFFBHJJXGBYXJPMMMPSSJZJMTLYZJXSWXTYLEDQPJMYGQZJGDJLQJWJQLLSJGJGYGMSCLJJXDTYGJQJQJCJZCJGDZZSXQGSJGGCXHQXSNQLZZBXHSGZXCXYLJXYXYYDFQQJHJFXDHCTXJYRXYSQTJXYEFYYSSYYJXNCYZXFXMSYSZXYYSCHSHXZZZGZZZGFJDLTYLNPZGYJYZYYQZPBXQBDZTZCZYXXYHHSQXSHDHGQHJHGYWSZTMZMLHYXGEBTYLZKQWYTJZRCLEKYSTDBCYKQQSAYXCJXWWGSBHJYZYDHCSJKQCXSWXFLTYNYZPZCCZJQTZWJQDZZZQZLJJXLSBHPYXXPSXSHHEZTXFPTLQYZZXHYTXNCFZYYHXGNXMYWXTZSJPTHHGYMXMXQZXTSBCZYJYXXTYYZYPCQLMMSZMJZZLLZXGXZAAJZYXJMZXWDXZSXZDZXLEYJJZQBHZWZZZQTZPSXZTDSXJJJZNYAZPHXYYSRNQDTHZHYYKYJHDZXZLSWCLYBZYECWCYCRYLCXNHZYDZYDYJDFRJJHTRSQTXYXJRJHOJYNXELXSFSFJZGHPZSXZSZDZCQZBYYKLSGSJHCZSHDGQGXYZGXCHXZJWYQWGYHKSSEQZZNDZFKWYSSTCLZSTSYMCDHJXXYWEYXCZAYDMPXMDSXYBSQMJMZJMTZQLPJYQZCGQHXJHHLXXHLHDLDJQCLDWBSXFZZYYSCHTYTYYBHECXHYKGJPXHHYZJFXHWHBDZFYZBCAPNPGNYDMSXHMMMMAMYNBYJTMPXYYMCTHJBZYFCGTYHWPHFTWZZEZSBZEGPFMTSKFTYCMHFLLHGPZJXZJGZJYXZSBBQSCZZLZCCSTPGXMJSFTCCZJZDJXCYBZLFCJSYZFGSZLYBCWZZBYZDZYPSWYJZXZBDSYUXLZZBZFYGCZXBZHZFTPBGZGEJBSTGKDMFHYZZJHZLLZZGJQZLSFDJSSCBZGPDLFZFZSZYZYZSYGCXSNXXCHCZXTZZLJFZGQSQYXZJQDCCZTQCDXZJYQJQCHXZTDLGSCXZSYQJQTZWLQDQZTQCHQQJZYEZZZPBWKDJFCJPZTYPQYQTTYNLMBDKTJZPQZQZZFPZSBNJLGYJDXJDZZKZGQKXDLPZJTCJDQBXDJQJSTCKNXBXZMSLYJCQMTJQWWCJQNJNLLLHJCWQTBZQYDZCZPZZDZYDDCYZZZCCJTTJFZDPRRTZTJDCQTQZDTJNPLZBCLLCTZSXKJZQZPZLBZRBTJDCXFCZDBCCJJLTQQPLDCGZDBBZJCQDCJWYNLLZYZCCDWLLXWZLXRXNTQQCZXKQLSGDFQTDDGLRLAJJTKUYMKQLLTZYTDYYCZGJWYXDXFRSKSTQTENQMRKQZHHQKDLDAZFKYPBGGPZREBZZYKZZSPEGJXGYKQZZZSLYSYYYZWFQZYLZZLZHWCHKYPQGNPGBLPLRRJYXCCSYYHSFZFYBZYYTGZXYLXCZWXXZJZBLFFLGSKHYJZEYJHLPLLLLCZGXDRZELRHGKLZZYHZLYQSZZJZQLJZFLNBHGWLCZCFJYSPYXZLZLXGCCPZBLLCYBBBBUBBCBPCRNNZCZYRBFSRLDCGQYYQXYGMQZWTZYTYJXYFWTEHZZJYWLCCNTZYJJZDEDPZDZTSYQJHDYMBJNYJZLXTSSTPHNDJXXBYXQTZQDDTJTDYYTGWSCSZQFLSHLGLBCZPHDLYZJYCKWTYTYLBNYTSDSYCCTYSZYYEBHEXHQDTWNYGYCLXTSZYSTQMYGZAZCCSZZDSLZCLZRQXYYELJSBYMXSXZTEMBBLLYYLLYTDQYSHYMRQWKFKBFXNXSBYCHXBWJYHTQBPBSBWDZYLKGZSKYHXQZJXHXJXGNLJKZLYYCDXLFYFGHLJGJYBXQLYBXQPQGZTZPLNCYPXDJYQYDYMRBESJYYHKXXSTMXRCZZYWXYQYBMCLLYZHQYZWQXDBXBZWZMSLPDMYSKFMZKLZCYQYCZLQXFZZYDQZPZYGYJYZMZXDZFYFYTTQTZHGSPCZMLCCYTZXJCYTJMKSLPZHYSNZLLYTPZCTZZCKTXDHXXTQCYFKSMQCCYYAZHTJPCYLZLYJBJXTPNYLJYYNRXSYLMMNXJSMYBCSYSYLZYLXJJQYLDZLPQBFZZBLFNDXQKCZFYWHGQMRDSXYCYTXNQQJZYYPFZXDYZFPRXEJDGYQBXRCNFYYQPGHYJDYZXGRHTKYLNWDZNTSMPKLBTHBPYSZBZTJZSZZJTYYXZPHSSZZBZCZPTQFZMYFLYPYBBJQXZMXXDJMTSYSKKBJZXHJCKLPSMKYJZCXTMLJYXRZZQSLXXQPYZXMKYXXXJCLJPRMYYGADYSKQLSNDHYZKQXZYZTCGHZTLMLWZYBWSYCTBHJHJFCWZTXWYTKZLXQSHLYJZJXTMPLPYCGLTBZZTLZJCYJGDTCLKLPLLQPJMZPAPXYZLKKTKDZCZZBNZDYDYQZJYJGMCTXLTGXSZLMLHBGLKFWNWZHDXUHLFMKYSLGXDTWWFRJEJZTZHYDXYKSHWFZCQSHKTMQQHTZHYMJDJSKHXZJZBZZXYMPAGQMSTPXLSKLZYNWRTSQLSZBPSPSGZWYHTLKSSSWHZZLYYTNXJGMJSZSUFWNLSOZTXGXLSAMMLBWLDSZYLAKQCQCTMYCFJBSLXCLZZCLXXKSBZQCLHJPSQPLSXXCKSLNHPSFQQYTXYJZLQLDXZQJZDYYDJNZPTUZDSKJFSLJHYLZSQZLBTXYDGTQFDBYAZXDZHZJNHHQBYKNXJJQCZMLLJZKSPLDYCLBBLXKLELXJLBQYCXJXGCNLCQPLZLZYJTZLJGYZDZPLTQCSXFDMNYCXGBTJDCZNBGBQYQJWGKFHTNPYQZQGBKPBBYZMTJDYTBLSQMPSXTBNPDXKLEMYYCJYNZCTLDYKZZXDDXHQSHDGMZSJYCCTAYRZLPYLTLKXSLZCGGEXCLFXLKJRTLQJAQZNCMBYDKKCXGLCZJZXJHPTDJJMZQYKQSECQZDSHHADMLZFMMZBGNTJNNLGBYJBRBTMLBYJDZXLCJLPLDLPCQDHLXZLYCBLCXZZJADJLNZMMSSSMYBHBSQKBHRSXXJMXSDZNZPXLGBRHWGGFCXGMSKLLTSJYYCQLTSKYWYYHYWXBXQYWPYWYKQLSQPTNTKHQCWDQKTWPXXHCPTHTWUMSSYHBWCRWXHJMKMZNGWTMLKFGHKJYLSYYCXWHYECLQHKQHTTQKHFZLDXQWYZYYDESBPKYRZPJFYYZJCEQDZZDLATZBBFJLLCXDLMJSSXEGYGSJQXCWBXSSZPDYZCXDNYXPPZYDLYJCZPLTXLSXYZYRXCYYYDYLWWNZSAHJSYQYHGYWWAXTJZDAXYSRLTDPSSYYFNEJDXYZHLXLLLZQZSJNYQYQQXYJGHZGZCYJCHZLYCDSHWSHJZYJXCLLNXZJJYYXNFXMWFPYLCYLLABWDDHWDXJMCXZTZPMLQZHSFHZYNZTLLDYWLSLXHYMMYLMBWWKYXYADTXYLLDJPYBPWUXJMWMLLSAFDLLYFLBHHHBQQLTZJCQJLDJTFFKMMMBYTHYGDCQRDDWRQJXNBYSNWZDBYYTBJHPYBYTTJXAAHGQDQTMYSTQXKBTZPKJLZRBEQQSSMJJBDJOTGTBXPGBKTLHQXJJJCTHXQDWJLWRFWQGWSHCKRYSWGFTGYGBXSDWDWRFHWYTJJXXXJYZYSLPYYYPAYXHYDQKXSHXYXGSKQHYWFDDDPPLCJLQQEEWXKSYYKDYPLTJTHKJLTCYYHHJTTPLTZZCDLTHQKZXQYSTEEYWYYZYXXYYSTTJKLLPZMCYHQGXYHSRMBXPLLNQYDQHXSXXWGDQBSHYLLPJJJTHYJKYPPTHYYKTYEZYENMDSHLCRPQFDGFXZPSFTLJXXJBSWYYSKSFLXLPPLBBBLBSFXFYZBSJSSYLPBBFFFFSSCJDSTZSXZRYYSYFFSYZYZBJTBCTSBSDHRTJJBYTCXYJEYLXCBNEBJDSYXYKGSJZBXBYTFZWGENYHHTHZHHXFWGCSTBGXKLSXYWMTMBYXJSTZSCDYQRCYTWXZFHMYMCXLZNSDJTTTXRYCFYJSBSDYERXJLJXBBDEYNJGHXGCKGSCYMBLXJMSZNSKGXFBNBPTHFJAAFXYXFPXMYPQDTZCXZZPXRSYWZDLYBBKTYQPQJPZYPZJZNJPZJLZZFYSBTTSLMPTZRTDXQSJEHBZYLZDHLJSQMLHTXTJECXSLZZSPKTLZKQQYFSYGYWPCPQFHQHYTQXZKRSGTTSQCZLPTXCDYYZXSQZSLXLZMYCPCQBZYXHBSXLZDLTCDXTYLZJYYZPZYZLTXJSJXHLPMYTXCQRBLZSSFJZZTNJYTXMYJHLHPPLCYXQJQQKZZSCPZKSWALQSBLCCZJSXGWWWYGYKTJBBZTDKHXHKGTGPBKQYSLPXPJCKBMLLXDZSTBKLGGQKQLSBKKTFXRMDKBFTPZFRTBBRFERQGXYJPZSSTLBZTPSZQZSJDHLJQLZBPMSMMSXLQQNHKNBLRDDNXXDHDDJCYYGYLXGZLXSYGMQQGKHBPMXYXLYTQWLWGCPBMQXCYZYDRJBHTDJYHQSHTMJSBYPLWHLZFFNYPMHXXHPLTBQPFBJWQDBYGPNZTPFZJGSDDTQSHZEAWZZYLLTYYBWJKXXGHLFKXDJTMSZSQYNZGGSWQSPHTLSSKMCLZXYSZQZXNCJDQGZDLFNYKLJCJLLZLMZZNHYDSSHTHZZLZZBBHQZWWYCRZHLYQQJBEYFXXXWHSRXWQHWPSLMSSKZTTYGYQQWRSLALHMJTQJSMXQBJJZJXZYZKXBYQXBJXSHZTSFJLXMXZXFGHKZSZGGYLCLSARJYHSLLLMZXELGLXYDJYTLFBHBPNLYZFBBHPTGJKWETZHKJJXZXXGLLJLSTGSHJJYQLQZFKCGNNDJSSZFDBCTWWSEQFHQJBSAQTGYPQLBXBMMYWXGSLZHGLZGQYFLZBYFZJFRYSFMBYZHQGFWZSYFYJJPHZBYYZFFWODGRLMFTWLBZGYCQXCDJYGZYYYYTYTYDWEGAZYHXJLZYYHLRMGRXXZCLHNELJJTJTPWJYBJJBXJJTJTEEKHWSLJPLPSFYZPQQBDLQJJTYYQLYZKDKSQJYYQZLDQTGJQYZJSUCMRYQTHTEJMFCTYHYPKMHYZWJDQFHYYXWSHCTXRLJHQXHCCYYYJLTKTTYTMXGTCJTZAYYOCZLYLBSZYWJYTSJYHBYSHFJLYGJXXTMZYYLTXXYPZLXYJZYZYYPNHMYMDYYLBLHLSYYQQLLNJJYMSOYQBZGDLYXYLCQYXTSZEGXHZGLHWBLJHEYXTWQMAKBPQCGYSHHEGQCMWYYWLJYJHYYZLLJJYLHZYHMGSLJLJXCJJYCLYCJPCPZJZJMMYLCQLNQLJQJSXYJMLSZLJQLYCMMHCFMMFPQQMFYLQMCFFQMMMMHMZNFHHJGTTHHKHSLNCHHYQDXTMMQDCYZYXYQMYQYLTDCYYYZAZZCYMZYDLZFFFMMYCQZWZZMABTBYZTDMNZZGGDFTYPCGQYTTSSFFWFDTZQSSYSTWXJHXYTSXXYLBYQHWWKXHZXWZNNZZJZJJQJCCCHYYXBZXZCYZTLLCQXYNJYCYYCYNZZQYYYEWYCZDCJYCCHYJLBTZYYCQWMPWPYMLGKDLDLGKQQBGYCHJXY"; 
//此处收录了375个多音字
var oMultiDiff={"19969":"DZ","19975":"WM","19988":"QJ","20048":"YL","20056":"SC","20060":"NM","20094":"QG","20127":"QJ","20167":"QC","20193":"YG","20250":"KH","20256":"ZC","20282":"SC","20285":"QJG","20291":"TD","20314":"YD","20340":"NE","20375":"TD","20389":"YJ","20391":"CZ","20415":"PB","20446":"YS","20447":"SQ","20504":"TC","20608":"KG","20854":"QJ","20857":"ZC","20911":"PF","20504":"TC","20608":"KG","20854":"QJ","20857":"ZC","20911":"PF","20985":"AW","21032":"PB","21048":"XQ","21049":"SC","21089":"YS","21119":"JC","21242":"SB","21273":"SC","21305":"YP","21306":"QO","21330":"ZC","21333":"SDC","21345":"QK","21378":"CA","21397":"SC","21414":"XS","21442":"SC","21477":"JG","21480":"TD","21484":"ZS","21494":"YX","21505":"YX","21512":"HG","21523":"XH","21537":"PB","21542":"PF","21549":"KH","21571":"E","21574":"DA","21588":"TD","21589":"O","21618":"ZC","21621":"KHA","21632":"ZJ","21654":"KG","21679":"LKG","21683":"KH","21710":"A","21719":"YH","21734":"WOE","21769":"A","21780":"WN","21804":"XH","21834":"A","21899":"ZD","21903":"RN","21908":"WO","21939":"ZC","21956":"SA","21964":"YA","21970":"TD","22003":"A","22031":"JG","22040":"XS","22060":"ZC","22066":"ZC","22079":"MH","22129":"XJ","22179":"XA","22237":"NJ","22244":"TD","22280":"JQ","22300":"YH","22313":"XW","22331":"YQ","22343":"YJ","22351":"PH","22395":"DC","22412":"TD","22484":"PB","22500":"PB","22534":"ZD","22549":"DH","22561":"PB","22612":"TD","22771":"KQ","22831":"HB","22841":"JG","22855":"QJ","22865":"XQ","23013":"ML","23081":"WM","23487":"SX","23558":"QJ","23561":"YW","23586":"YW","23614":"YW","23615":"SN","23631":"PB","23646":"ZS","23663":"ZT","23673":"YG","23762":"TD","23769":"ZS","23780":"QJ","23884":"QK","24055":"XH","24113":"DC","24162":"ZC","24191":"GA","24273":"QJ","24324":"NL","24377":"TD","24378":"QJ","24439":"PF","24554":"ZS","24683":"TD","24694":"WE","24733":"LK","24925":"TN","25094":"ZG","25100":"XQ","25103":"XH","25153":"PB","25170":"PB","25179":"KG","25203":"PB","25240":"ZS","25282":"FB","25303":"NA","25324":"KG","25341":"ZY","25373":"WZ","25375":"XJ","25384":"A","25457":"A","25528":"SD","25530":"SC","25552":"TD","25774":"ZC","25874":"ZC","26044":"YW","26080":"WM","26292":"PB","26333":"PB","26355":"ZY","26366":"CZ","26397":"ZC","26399":"QJ","26415":"ZS","26451":"SB","26526":"ZC","26552":"JG","26561":"TD","26588":"JG","26597":"CZ","26629":"ZS","26638":"YL","26646":"XQ","26653":"KG","26657":"XJ","26727":"HG","26894":"ZC","26937":"ZS","26946":"ZC","26999":"KJ","27099":"KJ","27449":"YQ","27481":"XS","27542":"ZS","27663":"ZS","27748":"TS","27784":"SC","27788":"ZD","27795":"TD","27812":"O","27850":"PB","27852":"MB","27895":"SL","27898":"PL","27973":"QJ","27981":"KH","27986":"HX","27994":"XJ","28044":"YC","28065":"WG","28177":"SM","28267":"QJ","28291":"KH","28337":"ZQ","28463":"TL","28548":"DC","28601":"TD","28689":"PB","28805":"JG","28820":"QG","28846":"PB","28952":"TD","28975":"ZC","29100":"A","29325":"QJ","29575":"SL","29602":"FB","30010":"TD","30044":"CX","30058":"PF","30091":"YSP","30111":"YN","30229":"XJ","30427":"SC","30465":"SX","30631":"YQ","30655":"QJ","30684":"QJG","30707":"SD","30729":"XH","30796":"LG","30917":"PB","31074":"NM","31085":"JZ","31109":"SC","31181":"ZC","31192":"MLB","31293":"JQ","31400":"YX","31584":"YJ","31896":"ZN","31909":"ZY","31995":"XJ","32321":"PF","32327":"ZY","32418":"HG","32420":"XQ","32421":"HG","32438":"LG","32473":"GJ","32488":"TD","32521":"QJ","32527":"PB","32562":"ZSQ","32564":"JZ","32735":"ZD","32793":"PB","33071":"PF","33098":"XL","33100":"YA","33152":"PB","33261":"CX","33324":"BP","33333":"TD","33406":"YA","33426":"WM","33432":"PB","33445":"JG","33486":"ZN","33493":"TS","33507":"QJ","33540":"QJ","33544":"ZC","33564":"XQ","33617":"YT","33632":"QJ","33636":"XH","33637":"YX","33694":"WG","33705":"PF","33728":"YW","33882":"SR","34067":"WM","34074":"YW","34121":"QJ","34255":"ZC","34259":"XL","34425":"JH","34430":"XH","34485":"KH","34503":"YS","34532":"HG","34552":"XS","34558":"YE","34593":"ZL","34660":"YQ","34892":"XH","34928":"SC","34999":"QJ","35048":"PB","35059":"SC","35098":"ZC","35203":"TQ","35265":"JX","35299":"JX","35782":"SZ","35828":"YS","35830":"E","35843":"TD","35895":"YG","35977":"MH","36158":"JG","36228":"QJ","36426":"XQ","36466":"DC","36710":"JC","36711":"ZYG","36767":"PB","36866":"SK","36951":"YW","37034":"YX","37063":"XH","37218":"ZC","37325":"ZC","38063":"PB","38079":"TD","38085":"QY","38107":"DC","38116":"TD","38123":"YD","38224":"HG","38241":"XTC","38271":"ZC","38415":"YE","38426":"KH","38461":"YD","38463":"AE","38466":"PB","38477":"XJ","38518":"YT","38551":"WK","38585":"ZC","38704":"XS","38739":"LJ","38761":"GJ","38808":"SQ","39048":"JG","39049":"XJ","39052":"HG","39076":"CZ","39271":"XT","39534":"TD","39552":"TD","39584":"PB","39647":"SB","39730":"LG","39748":"TPB","40109":"ZQ","40479":"ND","40516":"HG","40536":"HG","40583":"QJ","40765":"YQ","40784":"QJ","40840":"YK","40863":"QJG"};
//参数,中文字符串
//返回值:拼音首字母串数组
function makePy(str){
if(typeof(str) != "string")
throw new Error(-1,"函数makePy需要字符串类型参数!");
var arrResult = new Array(); //保存中间结果的数组
for(var i=0,len=str.length;i<len;i++){
//获得unicode码
var ch = str.charAt(i);
//检查该unicode码是否在处理范围之内,在则返回该码对映汉字的拼音首字母,不在则调用其它函数处理
arrResult.push(checkCh(ch));
}
//处理arrResult,返回所有可能的拼音首字母串数组
return mkRslt(arrResult);
}
function checkCh(ch){
var uni = ch.charCodeAt(0);
//如果不在汉字处理范围之内,返回原字符,也可以调用自己的处理函数
if(uni > 40869 || uni < 19968)
return ch; //dealWithOthers(ch);
//检查是否是多音字,是按多音字处理,不是就直接在strChineseFirstPY字符串中找对应的首字母
return (oMultiDiff[uni]?oMultiDiff[uni]:(strChineseFirstPY.charAt(uni-19968)));
}
function mkRslt(arr){
var arrRslt = [""];
for(var i=0,len=arr.length;i<len;i++){
var str = arr[i];
var strlen = str.length;
if(strlen == 1){
for(var k=0;k<arrRslt.length;k++){
arrRslt[k] += str;
}
}else{
var tmpArr = arrRslt.slice(0);
arrRslt = [];
for(k=0;k<strlen;k++){
//复制一个相同的arrRslt
var tmp = tmpArr.slice(0);
//把当前字符str[k]添加到每个元素末尾
for(var j=0;j<tmp.length;j++){
tmp[j] += str.charAt(k);
}
//把复制并修改后的数组连接到arrRslt上
arrRslt = arrRslt.concat(tmp);
}
}
}
return arrRslt;
}
//两端去空格函数
String.prototype.trim = function() {    return this.replace(/(^\s*)|(\s*$)/g,""); }


//顺序选择Combobox事件

