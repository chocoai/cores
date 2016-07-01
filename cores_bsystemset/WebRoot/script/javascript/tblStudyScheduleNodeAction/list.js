	var studyNodeTable;
	var tableHeight ; //datagrid 高度
	var tableSize;
	window.onload=function(){
		tableHeight = document.body.clientHeight - 56; 
		$('#maindiv').css('display','');
		studyNodeTable=$('#studyNodeTable').datagrid({
			url : sybp()+'/tblStudyScheduleNodeAction_loadList.action?studyTypeCode='+$('#studyTypeCode').val(),
			title:'',
			height: tableHeight,
			iconCls:'',//'icon-save',
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			border:false,
			idField:'id',
			columns :[[{
				title : 'id',
				field : 'id',
				width : 10,
				hidden:true
			},{
				title:'专题类别编码',
				field:'studyTypeCode',
				width:10,
				hidden:true
			},{
				title:'节点序号',
				field:'nodeSn',
				width:10,
				hidden:true
			},{
				title:'节点名称',
				field:'nodeName',
				width:150,
				halign:'center'
			},{
				title:'试验分组后天数',
				field:'planDays',
				width:150,
				halign:'center',
				align:'center'
			},{
				title:'是否是默认节点 ',
				field:'defaultNode',
				width:10,
				hidden:true
			}]],
			//工具栏
			toolbar:'#toolbar',
			onSelect:function(rowIndex, rowData){
				$('#addToolbarButton').linkbutton('enable');
				$('#editToolbarButton').linkbutton('disable');
				$('#delToolbarButton').linkbutton('disable');
				
				$('#upToolbarButton').linkbutton('disable');
				$('#downToolbarButton').linkbutton('disable');
				
				if(rowData.defaultNode == 0){
					$('#editToolbarButton').linkbutton('enable');
					$('#delToolbarButton').linkbutton('enable');
					if(rowIndex >1){
						$('#upToolbarButton').linkbutton('enable');
					}
					if(rowIndex < tableSize - 2){
						$('#downToolbarButton').linkbutton('enable');
					}
				}else{
					if(rowData.nodeName != '试验分组'){
						$('#editToolbarButton').linkbutton('enable');
					}
				}
			},
			onLoadSuccess:function(data){
				var currentId = $('#currentId').val();
				if(currentId){
					$('#studyNodeTable').datagrid('selectRecord',currentId);
				}
				tableSize = data.total;
			},
			rowStyler: function(index,row){
				if (row.defaultNode == 1){
					return 'color:#ff9d04;';
				}
			}
		}); //end datagrid

		
	}
	
	//新增
	function onAddToolbarButton(){
		//打开对话框
		showStudyNodeAddEditDialog('afterAddStudyNode','add','添加专题进度节点'); 
	}
	//添加后执行
	function afterAddStudyNode(){
		parent.showMessager(1,'添加成功',true,5000);
		studyNodeTable.datagrid('load');
	}
	
	//编辑
	function onEditToolbarButton(){
		//打开对话框
		showStudyNodeAddEditDialog('afterEditStudyNode','edit','编辑专题进度节点'); 
	}
	//编辑后执行
	function afterEditStudyNode(){
		parent.showMessager(1,'编辑成功',true,5000);
		studyNodeTable.datagrid('load');
	}
	//删除
	function onDelToolbarButton(){
		var row = studyNodeTable.datagrid('getSelected');
		if(row && row.defaultNode != 1){
			$.messager.confirm('确认','您确认要删除"'+row.nodeName+'"吗？',function(r){    
			    if (r){    
			    	var data = {};   
			    	data['id'] = row.id;
			    	var isValidate = $.ajax({   
			    		url: sybp()+'/tblStudyScheduleNodeAction_delete.action',   
			    		dataType: "json",   
			    		data: data,   
			    		async: false,   
			    		cache: false,   
			    		type: "post"  
			    	}).responseText; 
			    	if(isValidate == 'true'){
			    		parent.showMessager(1,'数据删除成功',true,5000);
			    		studyNodeTable.datagrid('load');
			    	}
			    }    
			}); 
		}else{
			parent.showMessager(2,'请先选择数据行',true,5000);
		}
	}
	
	/**显示Dialog*/
	function showStudyNodeAddEditDialog(clickName,addOrEdit,title){
		$('#studyNodeAddOrEdit').val(addOrEdit);
		$('#studyNodeAddEditDialog').dialog('setTitle',title);
		$('#studyNodeAddEditDialog').dialog('open'); 
		document.getElementById("studyNode_click").href="javascript:"+clickName+"();";
		//设定表单默认值
		setDefaultData();
		$('#nodeNameCombobox').combobox('reload');
		
	}
	//combobox select事件
	function setNodeNameValue(value){
		$('#_nodeName').val(value);
		$('#_nodeName').validatebox('validate');
	}
	
	/**设定表单默认值*/
	function setDefaultData(){
		document.studyNodeForm.reset();
		$('#oldnodeName').val('');
		$('#id').val('');
		var studyNodeAddOrEdit = $('#studyNodeAddOrEdit').val();
		if(studyNodeAddOrEdit && studyNodeAddOrEdit == 'edit'){
			var row = studyNodeTable.datagrid('getSelected');
			if(row){
				$('#oldnodeName').val(row.nodeName);
				$('#_nodeName').val(row.nodeName);
				$('#planDays').val(row.planDays);
				$('#id').val(row.id);
			}
			if(row && row.defaultNode == 1){
				$("#_nodeName").attr("readonly","readonly");
				$('#nodeNameCombobox').combobox('disable');
			}
		}else{
			$("#_nodeName").removeAttr("readonly");
			$('#nodeNameCombobox').combobox('enable');
		}
	}
	/**确定*/
	function onDialogStudyNodeSaveButton(){
		if( $('#studyNodeForm').form('validate') ){
			$('#saveDialogButton').linkbutton('disable');
			if($('#studyNodeAddOrEdit').val() == 'add'){
				var row = studyNodeTable.datagrid('getSelected');
				$.ajax({
					url:sybp()+'/tblStudyScheduleNodeAction_addSave.action?studyTypeCode='+$('#studyTypeCode').val()+'&nodeSn='+row.nodeSn,
					type:'post',
					data:$('#studyNodeForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#currentId').val(r.id);
							$('#studyNodeAddEditDialog').dialog('close'); 
							var studyNode_click=document.getElementById("studyNode_click");
							studyNode_click.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				});
			}else{
				$.ajax({
					url:sybp()+'/tblStudyScheduleNodeAction_editSave.action?studyTypeCode='+$('#studyTypeCode').val(),
					type:'post',
					data:$('#studyNodeForm').serialize(),
					dataType:'json',
					success:function(r){
						$('#saveDialogButton').linkbutton('enable');
						if(r && r.success){
							$('#currentId').val(r.id);
							$('#studyNodeAddEditDialog').dialog('close'); 
							var studyNode_click=document.getElementById("studyNode_click");
							studyNode_click.click();
						}else{
							$.messager.alert('提示','请检查录入的数据');
						}
					}
				});
			}
			
		}
	}
	
	
	//上移
	function onUpToolbarButton(){
		var row = studyNodeTable.datagrid('getSelected');
		if(row){
				$.ajax({
					url:sybp()+'/tblStudyScheduleNodeAction_upNode.action',
					type:'post',
					data:{
						id:row.id
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							$('#currentId').val(r.id);
							parent.showMessager(1,"上移成功",true,5000);
							studyNodeTable.datagrid('load');
						}
					}
				});
		}
	}
	//下移
	function onDownToolbarButton(){
		var row = studyNodeTable.datagrid('getSelected');
		if(row){
				$.ajax({
					url:sybp()+'/tblStudyScheduleNodeAction_downNode.action',
					type:'post',
					data:{
						id:row.id
					},
					dataType:'json',
					success:function(r){
						if(r && r.success){
							$('#currentId').val(r.id);
							parent.showMessager(1,"下移成功",true,5000);
							studyNodeTable.datagrid('load');
						}
					}
				});
		}
	}